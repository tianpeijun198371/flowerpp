/**
 * AddOneUserAction.java.
 * User: fengch  Date: 2004-5-9
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.course.action;

import com.ulearning.ulms.admin.certificate.bean.CertHelper;
import com.ulearning.ulms.admin.certificate.model.CertCurrentStatus;
import com.ulearning.ulms.core.security.bean.SecurityConstants;
import com.ulearning.ulms.core.security.bean.SecurityHelper;
import com.ulearning.ulms.core.util.Config;
import com.ulearning.ulms.core.util.StringUtil;
import com.ulearning.ulms.course.exceptions.CourseAppException;
import com.ulearning.ulms.course.exceptions.CourseUserHasFullException;
import com.ulearning.ulms.course.exceptions.UserExistedInCourseException;
import com.ulearning.ulms.course.exceptions.UserNotExistAppException;
import com.ulearning.ulms.course.helper.CourseUserHelper;
import com.ulearning.ulms.course.model.*;
import com.ulearning.ulms.course.util.CourseKeys;
import com.ulearning.ulms.course.webimpls.CourseWebImpl;
import com.ulearning.ulms.user.bean.UserHelper;
import com.ulearning.ulms.util.LMSConstants;
import com.ulearning.ulms.util.log.LogUtil;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class AddOneUserAction extends Action
{
        CourseWebImpl cwi = new CourseWebImpl();

        public ActionForward execute(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response)
                throws Exception
        {
                String resultScreen = "success";
                String resultScreen_add = "success_add";
                String resultScreen_confirm = "success_confirm";
                int userID = 0;


                if(request.getParameter("isPopWin")!=null)
                {       //标志为弹出窗口
                        request.setAttribute("is_popup","1");
                }

                HttpSession session = request.getSession();

                //判断是否是从培训事务的“学习报名”提交
                String isTag = request.getParameter("isTag");

                LogUtil.info("course", "[AddOneUserAction]===========Begin");

                String relationID_str = request.getParameter("relationID");
                int relationID = Integer.parseInt(relationID_str);

                int type = StringUtil.parseInt((String) request.getParameter("type"));
                LogUtil.info("course", "[AddOneUserAction]===========type=" + type);

                int state = StringUtil.parseInt((String) request.getParameter("state"));
                LogUtil.info("course", "[AddOneUserAction]===========state=" + state);

                if (type == SecurityConstants.USER_COURSE_RELATION)
                { // is course

                        CourseCurrentStatus ccs = null;
                        ccs = cwi.getCourseNowStatus(relationID, type);

                        String registerPassword = request.getParameter("registerPassword");

                        // judge the Max user Limit
                        if (!ccs.isMaxEnrollStatus())
                        {
                                throw new CourseUserHasFullException("本" +
                                        Config.getCOURSE_I18N_NAME() + "用户已满！");
                        }

                        if (request.getParameter("fromViewCourse") != null)
                        { //from view course
                                LogUtil.info("course",
                                        "[AddOneUserAction]=========== is fromViewCourse");

                                String str = (String) session.getAttribute(LMSConstants.USERID_KEY);
                                userID = StringUtil.parseInt(str);

                                //verify the user password
                                /*String password = (String) request.getParameter("password");
                                userID = SecurityHelper.checkSecurity(loginName, password);
                                if (userID == 0)
                                {
                                        return mapping.findForward(errorScreen_invalidate_user);
                                }*/

                                //verify the course register password
                                if (registerPassword != null)
                                {
                                        registerPassword = registerPassword.trim();

                                        if (!registerPassword.equals(ccs.getEnrollPassword()))
                                        {
                                                throw new CourseAppException("输入的" +
                                                        Config.getCOURSE_I18N_NAME() + "注册密码错误！请重新输入!");
                                        }
                                }

                                //检查用户是否存在此课程中
                                checkUser(userID, SecurityConstants.ROLE_COURSR_STUDENT,
                                        relationID, type);

                                if (ccs.isApproveStatus())
                                { //need confirm
                                        LogUtil.info("course",
                                                "[AddOneUserAction]===========  need Confirm");
                                        state = CourseKeys.COURSE_USER_APPLY_STATE;
                                        resultScreen = resultScreen_confirm;
                                }
                                else
                                {
                                        //to add
                                        LogUtil.info("course",
                                                "[AddOneUserAction]=========== not need Confirm");
                                        state = CourseKeys.COURSE_USER_AVAILABLE_STATE;
                                        resultScreen = resultScreen_add;
                                }

                                CourseUserHelper.addCourseUser(relationID, type, userID, state,
                                        SecurityConstants.ROLE_COURSR_STUDENT);
                        } //end view course
                        else
                        { //from Course's CourseUserManage or SelectCourse

                                ArrayList roles = getRoles(request);
                                LogUtil.info("course",
                                        "[AddOneUserAction]-----from Course's CourseUserManage or SelectCourse");

                                LinkedList userIDList = (LinkedList) session.getAttribute(
                                        "existList");

                                //检查该课程用户数是否超出
                                LogUtil.debug(("course"),
                                        "[AddOneUserAction]=========== 检查该课程用户数是否超出");

                                List MaximumEntrollment = cwi.getCourseFormByParent(relationID);

                                if (cwi.getCourseFormCount(relationID) >= ((CourseModel) MaximumEntrollment.get(
                                        0)).getMaximumEnrollment())
                                {
                                        throw new CourseUserHasFullException("本" +
                                                Config.getCOURSE_I18N_NAME() + "的用户已满！");
                                }
                                else if ((((CourseModel) MaximumEntrollment.get(0)).getMaximumEnrollment() -
                                        cwi.getCourseFormCount(relationID)) < userIDList.size())
                                {
                                        throw new CourseUserHasFullException("本" +
                                                Config.getCOURSE_I18N_NAME() + "添加的用户过多！");
                                }

                                //检查用户是否存在此课程中
                                LogUtil.info("course",
                                        "[AddOneUserAction]=========== 检查用户是否存在此课程中");
                                checkUsers(userIDList, roles, relationID, type);

                                for (int i = 0; i < userIDList.size(); i++)
                                {
                                        LogUtil.info("course",
                                                "[AddOneUserAction]=========== userID1=" +
                                                        userIDList.get(i));
                                        userID = Integer.parseInt((String) userIDList.get(i));
                                        LogUtil.info("course",
                                                "[AddOneUserAction]=========== userID2=" + userID);

                                        CourseRoleListModel courseRoles = new CourseRoleListModel(userID,
                                                roles);
                                        Date joinTime = new Date();
                                        Date applyTime = new Date();
                                        Date finishedTime = null;

                                        CourseUserModel cum = new CourseUserModel(relationID, type,
                                                userID, state, "",
                                                (UserHelper.getUser(Integer.toString(userID))).getName(),
                                                courseRoles, joinTime, applyTime, finishedTime);
                                        CourseUserHelper.addCourseUser(cum);
                                }

                                session.removeAttribute("existList");
                        } //end from Course's CourseUserManage or SelectCourse
                } // end is course
                else
                { // is certificate
                        LogUtil.info("cert", "[AddOneUserAction]=========== is certificate");

                        String registerPassword = request.getParameter("registerPassword");
                        CertCurrentStatus ccs = CertHelper.getCertNowStatus(relationID, 0);

                        if (request.getParameter("fromViewCertificate") != null)
                        { //from view certificate
                                LogUtil.info("course",
                                        "[AddOneUserAction]=========== is from View Certificate");

                                String str = (String) session.getAttribute(LMSConstants.USERID_KEY);
                                userID = StringUtil.parseInt(str);

                                //verify the course register password
                                if (registerPassword != null)
                                {
                                        registerPassword = registerPassword.trim();

                                        if (!registerPassword.equals(ccs.getEnrollPassword()))
                                        {
                                                throw new CourseAppException("输入的" +
                                                        Config.getCERT_I18N_NAME() + "注册密码错误！请重新输入!");
                                        }
                                }

                                //检查用户是否存在此课程中
                                checkUser(userID, SecurityConstants.ROLE_COURSR_STUDENT,
                                        relationID, type);

                                if (ccs.isApproveStatus())
                                { //to add
                                        LogUtil.info("course",
                                                "[AddOneUserAction]===========  need Confirm");
                                        state = CourseKeys.COURSE_USER_APPLY_STATE;
                                        resultScreen = resultScreen_confirm;
                                }
                                else
                                { //need confirm
                                        LogUtil.info("course",
                                                "[AddOneUserAction]=========== not need Confirm");
                                        state = CourseKeys.COURSE_USER_AVAILABLE_STATE;
                                        resultScreen = resultScreen_add;
                                }

                                CourseUserHelper.addCourseUser(relationID, type, userID, state,
                                        SecurityConstants.ROLE_COURSR_STUDENT);
                        }
                        else
                        { //from certificate's CourseUserManage or SelectCourse
                                LogUtil.info("course",
                                        "[AddOneUserAction]-----from certificate's CourseUserManage or SelectCourse");

                                ArrayList roles = getRoles(request);
                                LinkedList userIDList = (LinkedList) session.getAttribute(
                                        "existList");

                                //检查用户是否存在此课程中
                                checkUsers(userIDList, roles, relationID, type);

                                for (int i = 0; i < userIDList.size(); i++)
                                {
                                        userID = Integer.parseInt((String) userIDList.get(i));

                                        CourseRoleListModel courseRoles = new CourseRoleListModel(userID,
                                                roles);
                                        Date joinTime = new Date();
                                        Date applyTime = new Date();
                                        Date finishedTime = null;

                                        CourseUserModel cum = new CourseUserModel(relationID, type,
                                                userID, state, "",
                                                (UserHelper.getUser(Integer.toString(userID))).getName(),
                                                courseRoles, joinTime, applyTime, finishedTime);

                                        CourseUserHelper.addCourseUser(cum);
                                }

                                session.removeAttribute("existList");
                        }
                }

                LogUtil.info("course",
                        "[AddOneUserAction]===========resultScreen = " + resultScreen);

                if (!((isTag == null) || isTag.equals("")))
                {
                        resultScreen = "success_personalSignUp";

                        if (isTag.equals("new"))
                        {
                                String strType = request.getParameter("strType");

                                //                        if(!(strType==null || strType.equals("")))
                                //                        {
                                if (strType.equals("left"))
                                {
                                        resultScreen = "newleft";
                                }
                                else if (strType.equals("right"))
                                {
                                        resultScreen = "newright";
                                }

                                //                        }
                                LogUtil.info("course",
                                        "[AddOneUserAction]===========resultScreen = " +
                                                resultScreen + "strType=" + strType);
                        }
                }

                // Forward to result page
                LogUtil.info("course",
                        "[AddOneUserAction]===========resultScreen = " + resultScreen);

                return mapping.findForward(resultScreen);
        }

        /**
         * 检查一个用户是否存在此课程中
         *
         * @param userID
         * @param roleID
         * @param relationID
         * @param type
         * @throws CourseAppException
         */
        private void checkUser(int userID, int roleID, int relationID, int type)
                throws CourseAppException
        {
                String exceptionMsg = "";
                String name = Config.getCOURSE_I18N_NAME();

                if (type == SecurityConstants.USER_CERTIFICATE_RELATION)
                {
                        name = Config.getCERT_I18N_NAME();
                }

                if (CourseUserHelper.isExisitCourseUserRole(userID, roleID, relationID,
                        type))
                {
                        exceptionMsg += ("用户-" +
                                UserHelper.getUser(String.valueOf(userID)).getLoginName() + "的角色-" +
                                SecurityHelper.getRole(roleID).getName() + "存在此" + name + "中!<br>");
                }

                if (!exceptionMsg.equals(""))
                {
                        throw new UserExistedInCourseException(exceptionMsg);
                }
        }

        /**
         * 检查多个用户是否存在此课程中
         *
         * @param users
         * @param roles
         * @param relationID
         * @param type
         * @throws CourseAppException
         */
        private void checkUsers(List users, List roles, int relationID, int type)
                throws CourseAppException
        {
                int roleID = 0;
                int userID = 0;
                String exceptionMsg = "<br>";
                String name = Config.getCOURSE_I18N_NAME();

                if (type == SecurityConstants.USER_CERTIFICATE_RELATION)
                {
                        name = Config.getCERT_I18N_NAME();
                }

                for (int j = 0; j < users.size(); j++)
                {
                        userID = Integer.parseInt((String) users.get(j));

                        for (int i = 0; i < roles.size(); i++)
                        {
                                roleID = ((CourseRoleModel) roles.get(i)).getRoleID();

                                if (CourseUserHelper.isExisitCourseUserRole(userID, roleID,
                                        relationID, type))
                                {
                                        exceptionMsg += ("■ 用户-" +
                                                UserHelper.getUser(String.valueOf(userID)).getLoginName() +
                                                "的角色-" + SecurityHelper.getRole(roleID).getName() +
                                                "已经存在此" + name + "中!<br>");
                                }
                        }
                }

                if (!exceptionMsg.equals("<br>"))
                {
                        throw new UserExistedInCourseException(exceptionMsg);
                }
        }

        /**
         * 返回角色list
         *
         * @param request
         * @return
         */
        private ArrayList getRoles(HttpServletRequest request)
        {
                ArrayList roles = new ArrayList();
                int roleID = 0;
                Enumeration names;
                String name;
                names = request.getParameterNames();

                while (names.hasMoreElements())
                {
                        name = new String((String) names.nextElement());

                        if (name.startsWith("roleID"))
                        {
                                roleID = Integer.parseInt(request.getParameter(name));
                                roles.add(new CourseRoleModel(roleID, ""));
                        }
                }

                return roles;
        }
}
