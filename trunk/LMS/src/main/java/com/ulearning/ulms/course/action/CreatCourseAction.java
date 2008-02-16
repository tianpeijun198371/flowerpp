/**
 * CreatCourseAction.java.
 * User: fengch  Date: 2004-5-9
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.course.action;

import com.ulearning.ulms.core.security.bean.SecurityConstants;
import com.ulearning.ulms.core.security.bean.SecurityHelper;
import com.ulearning.ulms.core.util.Config;
import com.ulearning.ulms.core.util.StringUtil;
import com.ulearning.ulms.core.util.UploadUtil;
import com.ulearning.ulms.course.exceptions.CourseCodeRepeatedException;
import com.ulearning.ulms.course.helper.CourseHelper;
import com.ulearning.ulms.course.model.CourseModel;
import com.ulearning.ulms.course.util.TreeFlush;
import com.ulearning.ulms.course.webimpls.CourseWebImpl;
import com.ulearning.ulms.util.LMSConstants;
import com.ulearning.ulms.util.log.LogUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.webapp.admin.TreeControl;
import org.apache.webapp.admin.TreeControlNode;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;


public class CreatCourseAction extends Action
{
        public ActionForward execute(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response)
                throws Exception
        {
                String resultScreen = "success";

                LogUtil.debug("course", "[CreatCourseAction]===========Begin");

                CourseModel cm = (CourseModel) form;

                String scoreType = (String) request.getParameter("scoreType");
                LogUtil.debug("course",
                        "[CreatCourseAction]===========ScoreType=" + scoreType);

                //课程名称和聊天室名称重名，修改了课程的名称
                String name = request.getParameter("name1");
                cm.setName(name);

                String logo = request.getParameter("logo");
                cm.setLogo(logo);

                String scoreLimit = request.getParameter("scoreLimit" + scoreType);
                LogUtil.debug("course",
                        "[CreatCourseAction]===========scoreLimit=" + scoreLimit);
                cm.setScoreLimit(scoreLimit);
                cm.setEstablishDate(new Date());
                LogUtil.debug("course",
                        "[CreatCourseAction]===========new date()=" + new Date());

                int needApprove = 0;
                LogUtil.debug("course",
                        "[CreatCourseAction]============cm.getNeedApprove = " +
                                cm.getNeedApprove());
                LogUtil.debug("course",
                        "[CreatCourseAction]============request.getParameter(\"needApprove\") = " +
                                request.getParameter("needApprove"));

                if (request.getParameter("needApprove") != null)
                {
                        needApprove = 1;
                }

                LogUtil.debug("course",
                        "[CreatCourseAction]============needApprove = " + needApprove);
                cm.setNeedApprove(needApprove);

                LogUtil.debug("course",
                        "[CreatCourseAction]============liftStartDate = " +
                                cm.getLifeStartDateValue());
                LogUtil.debug("course",
                        "[CreatCourseAction]============liftEndDate = " +
                                cm.getLifeEndDateValue());
                LogUtil.debug("course",
                        "[CreatCourseAction]============regStartDate = " +
                                cm.getRegStartDateValue());
                LogUtil.debug("course",
                        "[CreatCourseAction]============regEndDate = " +
                                cm.getRegEndDateValue());
                cm.setModifyDate(new Date());

                CourseWebImpl cw = new CourseWebImpl();
                LogUtil.debug("course",
                        "[CourseDAOImpl]===========period1=" + cm.getPeriod());
                LogUtil.debug("course",
                        "[CourseDAOImpl]===========credit1=" + cm.getCredit());

                LogUtil.debug("course",
                        "[CourseDAOImpl]===========period2=" + cm.getCourse().getPeriod());
                LogUtil.debug("course",
                        "[CourseDAOImpl]===========credit2=" + cm.getCourse().getCredit());

                int aspID = 0;
                int orgID = 0;
                HttpSession session = request.getSession();
                ServletContext serveletContext = getServlet().getServletContext();

                if (session.getAttribute(LMSConstants.USER_ASPID_KEY) != null)
                {
                        String str = (String) session.getAttribute(LMSConstants.USER_ASPID_KEY);
                        aspID = StringUtil.parseInt(str);
                }

                if (session.getAttribute(LMSConstants.USER_ORGID_KEY) != null)
                {
                        String str = (String) session.getAttribute(LMSConstants.USER_ORGID_KEY);
                        orgID = StringUtil.parseInt(str);
                }

                if (CourseHelper.isExistCourseCode(cm.getCourseCode(), -1, aspID))
                {
                        LogUtil.debug("course", "[CourseDAOImpl]  课程代码已存在!");
                        throw new CourseCodeRepeatedException("课程代码已存在!");
                }

                if (request.getContentType().startsWith("multipart/form-data"))
                {
                        try
                        {
                                UploadUtil.executeUpload(cm,
                                        request, response);
                        }
                        catch (Exception e)
                        {
                                e.printStackTrace();
                                throw e;
                        }
                        String tmp = StringUtils.trimToEmpty((String) request.getAttribute("newFilePath"));
                        cm.setLogopic(tmp);
                }
                cm.setAspID(aspID);
                cm.setOrgID(orgID);

                int catalogID = cm.getCatalogID();
                int courseID = cw.createCourse(cm);
                boolean validate = CourseHelper.IsCourseAvailable(courseID,
                        SecurityConstants.USER_COURSE_RELATION);

                //and a course node to tree and context
                TreeControl control = null;
                String treeType = LMSConstants.TREE_PUBLISHED_COURSE;
                control = (TreeControl) session.getAttribute(treeType);

                String nodeName = "catalogIDs/" + catalogID;

                if (catalogID == 0)
                {
                        nodeName = "ROOT-NODE";
                }

                if (control != null)
                {
                        TreeControlNode parentNode = control.findNode(nodeName);
                        parentNode.setExpanded(true);
                        control.selectNode(nodeName);

                        if (parentNode != null)
                        {
                                TreeControlNode subtree = new TreeControlNode("courseIDs/" +
                                        courseID,
                                         Config.getContextRoot() + "/images/course.gif",
                                        cm.getName(),
                                         Config.getContextRoot() +
                                                "/mystudy/course/viewCourse.jsp?courseID=" + courseID,
                                        null, false, "Publish");
                                parentNode.addChild(subtree);
                        }
                }

                //更新树
                TreeFlush.flush(serveletContext, session, orgID, treeType);

                String actionStr = request.getParameter("actionStr");

                LogUtil.info("CreatCourseAction",
                        "actionStr =====================" + actionStr);

                if (actionStr != null)
                {
                        if (actionStr.equals("successandselectcourse"))
                        {
                                request.setAttribute("relationID", String.valueOf(courseID));
                                request.setAttribute("type",
                                        String.valueOf(SecurityConstants.USER_COURSE_RELATION));
                                resultScreen = "successandselectcourse";
                        }
                }

                request.setAttribute("catalogID", String.valueOf(catalogID));

                //ddChart(request,session,serveletContext);
                if ((Config.get("is-integrate-chat") != null) &&
                        Config.get("is-integrate-chat").toLowerCase().equals("true"))
                {
                        resultScreen = "success_chat";
                }

                if(SecurityHelper.getUserRoleForPortal(cm.getCreator(),aspID,1)==SecurityConstants.ROLE_COURSE_TEACHER)
                {
                        request.setAttribute(LMSConstants.ERROR_PAGE_INFO,
                                "您创建的班级已经成功，请等待审批!");
                        return mapping.findForward(LMSConstants.ERROR_FORWARD);   
                }

                // Forward to result page
                LogUtil.info("course",
                        "[CreatCourseAction]===========resultScreen = " + resultScreen);

                if(Config.isXLNProject())
                {
                        String catalogIDParam = StringUtils.trimToEmpty(request.getParameter("catalogID"));
                        String iscoursecertParam = StringUtils.trimToEmpty(request.getParameter("iscoursecert"));
                        String returnIDParam = StringUtils.trimToEmpty(request.getParameter("returnID"));
                        String courseKeyParam = StringUtils.trimToEmpty(request.getParameter("courseKey"));
                        int offser= NumberUtils.toInt(request.getParameter("pager.offset"));
                        ActionForward forward = mapping.findForward(resultScreen);
                        StringBuffer bf = new StringBuffer(forward.getPath());
                        bf.append("?catalogID=" + catalogIDParam + "&iscoursecert=" + iscoursecertParam +
                                "&returnID=" + returnIDParam + "&courseKey=" + courseKeyParam
                                + "&pager.offset=" + offser);
                        return new ActionForward(bf.toString(), true);
                }
                return mapping.findForward(resultScreen);
        }
}
