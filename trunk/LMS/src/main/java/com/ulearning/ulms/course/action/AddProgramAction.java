/*
 * Copyright (c) 2004 Your Corporation. All Rights Reserved.
 */
package com.ulearning.ulms.course.action;

import com.ulearning.ulms.core.util.Config;
import com.ulearning.ulms.core.util.StringUtil;
import com.ulearning.ulms.course.dao.CourseContentDAO;
import com.ulearning.ulms.course.dao.CourseContentDAOFactory;
import com.ulearning.ulms.course.dao.CourseDAO;
import com.ulearning.ulms.course.dao.CourseDAOFactory;
import com.ulearning.ulms.course.exceptions.CourseCodeRepeatedException;
import com.ulearning.ulms.course.exceptions.CourseSysException;
import com.ulearning.ulms.course.helper.CourseHelper;
import com.ulearning.ulms.course.model.CourseContentForm;
import com.ulearning.ulms.course.model.CourseModel;
import com.ulearning.ulms.course.model.JieFoCourseForm;
import com.ulearning.ulms.course.util.CourseContentKeys;
import com.ulearning.ulms.course.util.CourseKeys;
import com.ulearning.ulms.course.webimpls.CourseWebImpl;
import com.ulearning.ulms.tools.upload.action.MultipartParamUtils;
import com.ulearning.ulms.tools.upload.action.UploadAction;
import com.ulearning.ulms.tools.upload.model.UploadForm;
import com.ulearning.ulms.user.bean.UserHelper;
import com.ulearning.ulms.util.LMSConstants;
import com.ulearning.ulms.util.log.LogUtil;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import org.apache.webapp.admin.TreeControl;
import org.apache.webapp.admin.TreeControlNode;

import java.util.Date;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 * @author <a href="mailto:youmail@yourdomain.com">yourname</a> Date: 2004-10-18
 */
public class AddProgramAction extends UploadAction
{
        public ActionForward execute(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response)
                throws Exception
        {
                String resultScreen = "success";
                MultipartParamUtils mp = new MultipartParamUtils(request,
                        1024 * 1014 * 10);

                String theLink = mp.getParameter("theLink");
                System.out.println("get from mp");
                LogUtil.info("course", "[CreatCourseAction]===========Begin");

                CourseModel cm = new CourseModel();

                int value = 1;
                float valuef = 0;
                String param = mp.getParameter("orgID");

                if (param != null)
                {
                        value = Integer.parseInt(param);
                }

                cm.setOrgID(value);

                param = mp.getParameter("isPassword");

                if (param != null)
                {
                        value = Integer.parseInt(param);
                }

                cm.setIsPassword(value);

                param = mp.getParameter("type");
                cm.setType(param);

                param = mp.getParameter("masterID");

                if (param != null)
                {
                        value = Integer.parseInt(param);
                }

                cm.setMasterID(value);

                param = mp.getParameter("creator");

                if (param != null)
                {
                        value = Integer.parseInt(param);
                }

                cm.setCreator(value);

                param = mp.getParameter("catalogID");

                if (param != null)
                {
                        value = Integer.parseInt(param);
                }

                cm.setCatalogID(value);

                cm.setStatus(1);

                param = mp.getParameter("maximumEnrollment");

                if (param != null)
                {
                        value = Integer.parseInt(param);
                }

                cm.setMaximumEnrollment(value);

                param = mp.getParameter("period");

                if (param != null)
                {
                        valuef = Float.parseFloat(param);
                }

                cm.setPeriod(valuef);

                param = mp.getParameter("credit");

                if (param != null)
                {
                        valuef = Float.parseFloat(param);
                }

                cm.setCredit(valuef);

                cm.setScoreType(1);

                cm.setScoreLimit("60");

                cm.setIsCharge(0);

                cm.setObjectID(4);

                cm.setAllowFreedomReg(1);

                cm.setLifeSort(1);

                cm.setGuest(1);

                param = mp.getParameter("teachMode");

                if (param != null)
                {
                        value = Integer.parseInt(param);
                }

                cm.setTeachMode(value);

                param = mp.getParameter("scoreType");

                if (param != null)
                {
                        value = Integer.parseInt(param);
                }

                cm.setScoreType(value);

                param = mp.getParameter("name");
                cm.setName(param);

                param = mp.getParameter("courseCode");
                cm.setCourseCode(param);

                param = mp.getParameter("key");
                cm.setKey(param);

                param = mp.getParameter("description");
                cm.setDescription(param);

                cm.setEstablishDate(new Date());
                LogUtil.info("course",
                        "[CreatCourseAction]===========new date()=" + new Date());

                int needApprove = 0;
                LogUtil.debug("course",
                        "[CreatCourseAction]============cm.getNeedApprove = " +
                                cm.getNeedApprove());
                LogUtil.debug("course",
                        "[CreatCourseAction]============request.getParameter(\"needApprove\") = " +
                                request.getParameter("needApprove"));

                if (mp.getParameter("needApprove") != null)
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
                HttpSession session = request.getSession();
                ServletContext servletContext = getServlet().getServletContext();

                if (session.getAttribute(LMSConstants.USER_ASPID_KEY) != null)
                {
                        String str = (String) session.getAttribute(LMSConstants.USER_ASPID_KEY);
                        aspID = StringUtil.parseInt(str);
                        cm.setOrgID(aspID);
                }

                if (CourseHelper.isExistCourseCode(cm.getCourseCode(), -1, aspID))
                {
                        LogUtil.debug("course", "[AddProgramAction]  课程代码已存在!");
                        throw new CourseCodeRepeatedException("课程代码已存在!");
                }

                int courseID = cw.createCourse(cm);

                //add jiefocours
                try
                {
                        if (Config.getIsIntegrateJieFo())
                        {
                                CourseDAO dao = CourseDAOFactory.getDAO();
                                JieFoCourseForm jff = new JieFoCourseForm();
                                jff.setTrainID(courseID);
                                jff.setTrainName(cm.getName());
                                dao.addJieFoCourse(jff);
                        }
                }
                catch (Exception e)
                {
                        e.printStackTrace(); //To change body of catch statement use File | Settings | File Templates.
                }

                //and a course node to tree and context
                TreeControl control = null;
                TreeControl selectControl = null;

                control = (TreeControl) session.getAttribute(LMSConstants.TREE_PUBLISH);
                selectControl = (TreeControl) session.getAttribute(LMSConstants.TREE_SELECT);

                if (servletContext.getAttribute(LMSConstants.TREE_PUBLISH + aspID) != null)
                {
                        servletContext.removeAttribute(LMSConstants.TREE_PUBLISH + aspID);
                }

                if (servletContext.getAttribute(LMSConstants.TREE_SELECT + aspID) != null)
                {
                        servletContext.removeAttribute(LMSConstants.TREE_SELECT + aspID);
                }

                int catalogID = cm.getCatalogID();
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

                if (selectControl != null)
                {
                        TreeControlNode parentNode = selectControl.findNode(nodeName);

                        if (parentNode != null)
                        {
                                TreeControlNode subtree = new TreeControlNode("courseID/" +
                                        courseID,
                                        Config.getContextRoot() + "/images/course.gif",
                                        cm.getName(),
                                        Config.getContextRoot() +
                                                "/mystudy/course/viewCourse.jsp?courseID=" + courseID +
                                                "&isNeedApply=true", null, false, "Select");
                                parentNode.addChild(subtree);
                        }
                }

                request.setAttribute("catalogID", String.valueOf(catalogID));

                String teachModestr = request.getParameter("teachMode");
                int teachMode = -1;

                if (teachModestr != null)
                {
                        teachMode = Integer.parseInt(teachModestr);
                }

                if (teachMode == CourseKeys.TEACH_MODE_COURSEWARE)
                {
                        CourseContentForm ccf = (CourseContentForm) form;
                        ccf.setRelationID(courseID);
                        ccf.setType(CourseContentKeys.TYPE_PROGRAM);

                        if (request.getContentType().startsWith("multipart/form-data"))
                        {
                                try
                                {
                                        UploadForm uploadForm = ccf.getUploadForm();
                                        executeUpload(mapping, uploadForm, request, response);
                                }
                                catch (Exception e)
                                {
                                        LogUtil.debug("system",
                                                "[AddCourseContentAction] Exeception====================" +
                                                        e);
                                }

                                if (theLink == null)
                                {
                                        ccf.setLink(ccf.getFilePath() +
                                                request.getAttribute("newFileName"));
                                }
                                else
                                {
                                        ccf.setLink(theLink);
                                }
                        }

                        CourseContentDAO dao = CourseContentDAOFactory.getDAO();
                        dao.addCourseContent(ccf);
                        LogUtil.debug("system",
                                "[AddCourseContent]===========resultScreen = " + resultScreen);
                        request.setAttribute("mp", mp);
                }

                // Forward to result page
                return mapping.findForward(resultScreen);
        }
}
