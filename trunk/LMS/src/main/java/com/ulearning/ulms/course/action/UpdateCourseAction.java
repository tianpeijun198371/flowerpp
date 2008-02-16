/**
 * UpdateCourseAction.java.
 * User: fengch  Date: 2004-4-28
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.course.action;

import com.ulearning.ulms.core.util.Config;
import com.ulearning.ulms.core.util.StringUtil;
import com.ulearning.ulms.core.util.UploadUtil;
import com.ulearning.ulms.core.util.MultipartParamUtil;
import com.ulearning.ulms.course.dao.CourseDAO;
import com.ulearning.ulms.course.dao.CourseDAOFactory;
import com.ulearning.ulms.course.exceptions.CourseCodeRepeatedException;
import com.ulearning.ulms.course.exceptions.CourseSysException;
import com.ulearning.ulms.course.helper.CourseHelper;
import com.ulearning.ulms.course.model.CourseModel;
import com.ulearning.ulms.course.model.JieFoCourseForm;
import com.ulearning.ulms.course.util.TreeFlush;
import com.ulearning.ulms.course.webimpls.CourseWebImpl;
import com.ulearning.ulms.util.LMSConstants;
import com.ulearning.ulms.util.log.LogUtil;
import com.ulearning.ulms.tools.upload.action.MultipartParamUtils;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import org.apache.webapp.admin.TreeControl;
import org.apache.webapp.admin.TreeControlNode;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Date;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class UpdateCourseAction extends Action
{
        protected static Log logger = LogFactory.getLog(UpdateCourseAction.class);

        public ActionForward execute(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response)
                throws Exception
        {

                MultipartParamUtil mp = new MultipartParamUtil(request,
                        1024 * 1014 * 10);
                request.setAttribute("mp", mp);

                logger.info("mp=" + mp);
                if (mp != null)
                {
                        logger.info("mp catalogID=" + mp.getParameter("catalogID"));
                        logger.info("mp pager.offset=" + mp.getParameter("pager.offset"));
                        logger.info("mp courseKey=" + mp.getParameter("courseKey"));
                }
                logger.info("request catalogID=" + request.getParameter("catalogID"));
                logger.info("request pager.offset=" + request.getParameter("pager.offset"));
                String resultScreen = "success";
                CourseModel cm = (CourseModel) form;

                logger.info("cm.getCatalogID=" + cm.getCatalogID());
                logger.info("cm.getPageroffset=" + cm.getPageroffset());

                String scoreType = (String) request.getParameter("scoreType");
                LogUtil.info("course",
                        "[UpdateCourseAction]===========ScoreType=" + scoreType);

                String scoreLimit = request.getParameter("scoreLimit" + scoreType);
                LogUtil.info("course",
                        "[UpdateCourseAction]===========scoreLimit=" + scoreLimit);
                cm.setScoreLimit(scoreLimit);

                int needApprove = 0;
                LogUtil.debug("course",
                        "[UpdateCourseAction]============cm.getNeedApprove = " +
                                cm.getNeedApprove());
                LogUtil.debug("course",
                        "[UpdateCourseAction]============request.getParameter(\"needApprove\") = " +
                                request.getParameter("needApprove"));

                if (request.getParameter("needApprove") != null)
                {
                        needApprove = 1;
                }

                LogUtil.debug("course",
                        "[UpdateCourseAction]============needApprove = " + needApprove);

                int aspID = 0;
                HttpSession session = request.getSession();
                ServletContext servletContext = getServlet().getServletContext();

                if (session.getAttribute(LMSConstants.USER_ASPID_KEY) != null)
                {
                        String str = (String) session.getAttribute(LMSConstants.USER_ASPID_KEY);
                        aspID = StringUtil.parseInt(str);
                }

                int orgID = Integer.parseInt((String) session.getAttribute(
                        LMSConstants.USER_ORGID_KEY));
                String logo = request.getParameter("logo");
                cm.setLogo(logo);

                if (CourseHelper.isExistCourseCode(cm.getCourseCode(),
                        cm.getCourseID(), aspID))
                {
                        LogUtil.debug("course", "[CourseDAOImpl]  课程代码已存在!");
                        throw new CourseCodeRepeatedException("课程代码已存在!");
                }


                CourseDAO dao = CourseDAOFactory.getDAO();
                Date estade = dao.getCourse(cm.getCourseID()).getEstablishDate();
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
                                LogUtil.debug("course", "[UpdateCourseAction] Exeception====================" + e);
                                throw e;
                        }
                        LogUtil.info("course", "[UpdateCourseAction]===========1");

                        String tmp = StringUtils.trimToEmpty((String) request.getAttribute("newFilePath"));
                        System.out.println("tmp             file   path= " + tmp);
                        if (tmp != null && !tmp.equals(""))
                        {
                                cm.setLogopic(tmp);
                        }

                }
                //System.out.println("estade ======================================="+estade);
                cm.setEstablishDate(estade);
                cm.setModifyDate(new Date());
                dao.updateCourse(cm);

                //update jiefocourse
                try
                {
                        if (Config.getIsIntegrateJieFo())
                        {
                                JieFoCourseForm jff = new JieFoCourseForm();
                                jff.setTrainID(cm.getCourseID());
                                jff.setTrainName(cm.getName());
                                dao.updateJieFoCourse(jff);
                        }
                }
                catch (Exception e)
                {
                        e.printStackTrace(); //To change body of catch statement use File | Settings | File Templates.
                }

                String treeType = LMSConstants.TREE_PUBLISHED_COURSE;
                TreeControl control = (TreeControl) session.getAttribute(treeType);

                if (control != null)
                {
                        TreeControlNode currentNode = control.findNode("courseIDs/" +
                                cm.getCourseID());

                        if (currentNode != null)
                        {
                                currentNode.setLabel(cm.getName());
                        }
                }

                //更新树
                TreeFlush.flush(servletContext, session, orgID, treeType);

                request.setAttribute("catalogID", String.valueOf(cm.getCatalogID()));
                System.out.println("UpdateCourseAction Config.isXLNProject() = " + Config.isXLNProject());
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
                // Forward to result page
                return mapping.findForward(resultScreen);
        }
}
