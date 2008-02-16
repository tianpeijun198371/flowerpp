/**
 * UpdateUserAction.java.
 * User: fengch  Date: 2004-4-28
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.course.action;

import com.ulearning.ulms.core.security.bean.SecurityConstants;
import com.ulearning.ulms.core.util.Config;
import com.ulearning.ulms.core.util.StringUtil;
import com.ulearning.ulms.course.dao.CourseUserDAO;
import com.ulearning.ulms.course.dao.CourseUserDAOFactory;
import com.ulearning.ulms.course.helper.CourseHelper;
import com.ulearning.ulms.course.helper.CourseUserHelper;
import com.ulearning.ulms.course.model.*;
import com.ulearning.ulms.course.webimpls.CourseUserWebImpl;
import com.ulearning.ulms.user.bean.UserHelper;
import com.ulearning.ulms.util.log.LogUtil;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class UpdateUserAction extends Action
{
        public ActionForward execute(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response)
                throws Exception
        {
                boolean isStudent = false;

                String resultScreen = "success";
                String errorScreen = "error";
                LogUtil.info("course", "[UpdateUserAction]===========Begin");

                String courseID_str = request.getParameter("relationID");
                int relationID = Integer.parseInt(courseID_str);
                String loginName = request.getParameter("loginName");
                int type = StringUtil.parseInt((String) request.getParameter("type"));

                LogUtil.info("course",
                        "[UpdateUserAction]===========loginName = " + loginName);

                int userID = UserHelper.getUserID(loginName);

                if (userID == 0)
                {
                        // Forward to error page
                        return mapping.findForward(errorScreen);
                }

                int state = StringUtil.parseInt((String) request.getParameter("state"));
                int roleID;
                ArrayList roles = new ArrayList();

                CourseRoleListModel courseRoles = new CourseRoleListModel(userID, roles);

                CourseUserModel cumOld = CourseUserHelper.getCourseUser(relationID,
                        type, userID);
                Date joinTime = cumOld.getJoinTime();
                Date applyTime = cumOld.getApplyTime();
                Date finishedTime = cumOld.getFinishedTime();

                CourseUserModel cum = new CourseUserModel(relationID, type, userID,
                        state, "", loginName, courseRoles, joinTime, applyTime,
                        finishedTime);

                Enumeration names;
                String name;
                names = request.getParameterNames();

                while (names.hasMoreElements())
                {
                        name = new String((String) names.nextElement());

                        if (name.startsWith("roleID"))
                        {
                                roleID = Integer.parseInt(request.getParameter(name));

                                if ((roleID == SecurityConstants.ROLE_COURSR_STUDENT) ||
                                        !isStudent)
                                {
                                        isStudent = true;
                                }

                                roles.add(new CourseRoleModel(roleID, ""));
                        }
                }

                LogUtil.info("course",
                        "[UpdateUserAction]===========resultScreen = " + resultScreen);
                CourseUserHelper.updateCourseUser(cum);

                if (type != SecurityConstants.USER_COURSE_RELATION)
                {
                        List courseList = CourseHelper.getCourseList(relationID, type);
                        LogUtil.info("select",
                                "[UpdateUserAction]===========courseList size = " +
                                        courseList.size());

                        try
                        {
                                if (courseList != null)
                                {
                                        for (int i = 0; i < courseList.size(); i++)
                                        {
                                                LogUtil.info("course",
                                                        "[UpdateUserAction]===========Child courseName = " +
                                                                ((CourseModel) courseList.get(i)).getMasterName());

                                                CourseModel courseModel = (CourseModel) courseList.get(i);
                                                cum = new CourseUserModel(courseModel.getCourseID(),
                                                        SecurityConstants.USER_COURSE_RELATION, userID,
                                                        state, "", loginName, courseRoles, joinTime,
                                                        applyTime, finishedTime);

                                                try
                                                {
                                                        CourseUserHelper.updateCourseUser(cum);
                                                }
                                                catch (Exception e)
                                                {
                                                        continue;
                                                }
                                        }
                                }
                        }
                        catch (Exception cae)
                        {
                                cae.printStackTrace();
                        }
                }

                // Forward to result page
                return mapping.findForward(resultScreen);
        }
}
