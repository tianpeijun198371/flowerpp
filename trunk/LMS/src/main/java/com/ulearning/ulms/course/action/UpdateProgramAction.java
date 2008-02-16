/*
 * Copyright (c) 2004 HuaXia. All Rights Reserved.
 */
package com.ulearning.ulms.course.action;

import com.ulearning.ulms.core.util.Config;
import com.ulearning.ulms.core.util.StringUtil;
import com.ulearning.ulms.course.dao.CourseContentDAO;
import com.ulearning.ulms.course.dao.CourseContentDAOFactory;
import com.ulearning.ulms.course.dao.CourseDAO;
import com.ulearning.ulms.course.dao.CourseDAOFactory;
import com.ulearning.ulms.course.exceptions.CourseCodeRepeatedException;
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
import com.ulearning.ulms.util.LMSConstants;
import com.ulearning.ulms.util.log.LogUtil;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import org.apache.webapp.admin.TreeControl;
import org.apache.webapp.admin.TreeControlNode;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class UpdateProgramAction extends UploadAction
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

                int courseID = Integer.parseInt(mp.getParameter("courseID"));
                CourseWebImpl cw = new CourseWebImpl();
                CourseModel cm = cw.getCourse(courseID);

                System.out.println("----------- courseID=" + courseID);

                int value = 1;
                float valuef = 0;
                String param = "";
                param = mp.getParameter("name");
                cm.setName(param);

                param = mp.getParameter("key");
                cm.setKey(param);

                param = mp.getParameter("description");
                cm.setDescription(param);

                cm.setModifyDate(new Date());

                int aspID = 0;
                HttpSession session = request.getSession();

                if (session.getAttribute(LMSConstants.USER_ASPID_KEY) != null)
                {
                        String str = (String) session.getAttribute(LMSConstants.USER_ASPID_KEY);
                        aspID = StringUtil.parseInt(str);
                        cm.setOrgID(aspID);
                }

                cw.updateCourse(cm);

                //add jiefocours
                /*                try
                {
                        if (Config.getIsIntegrateJieFo())
                        {
                                CourseDAO dao = CourseDAOFactory.getDAO();
                                JieFoCourseForm jff = new JieFoCourseForm();
                                jff.setTrainID(courseID);
                                jff.setTrainName(cm.getName());
                                dao.updateJieFoCourse(jff);
                        }
                }
                catch (Exception e)
                {
                        e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }*/

                //and a course node to tree and context
                TreeControl control = (TreeControl) session.getAttribute(LMSConstants.TREE_PUBLISH);
                TreeControl controlInContext = (TreeControl) getServlet()
                        .getServletContext()
                        .getAttribute(LMSConstants.TREE_PUBLISH +
                                aspID);
                TreeControl selectControl = (TreeControl) session.getAttribute(LMSConstants.TREE_SELECT);
                TreeControl selectControlInContext = (TreeControl) getServlet()
                        .getServletContext()
                        .getAttribute(LMSConstants.TREE_SELECT +
                                aspID);

                if (control != null)
                {
                        TreeControlNode currentNode = control.findNode("courseIDs/" +
                                cm.getCourseID());

                        if (currentNode != null)
                        {
                                currentNode.setLabel(cm.getName());
                        }

                        currentNode = controlInContext.findNode("courseIDs/" +
                                cm.getCourseID());

                        if (currentNode != null)
                        {
                                currentNode.setLabel(cm.getName());
                        }
                }

                if (selectControl != null)
                {
                        TreeControlNode currentNode = selectControl.findNode("courseID/" +
                                cm.getCourseID());

                        if (currentNode != null)
                        {
                                currentNode.setLabel(cm.getName());
                        }

                        if (selectControlInContext != null)
                        {
                                currentNode = selectControlInContext.findNode("courseID/" +
                                        cm.getCourseID());
                        }

                        if (currentNode != null)
                        {
                                currentNode.setLabel(cm.getName());
                        }
                }

                request.setAttribute("catalogID", String.valueOf(cm.getCatalogID()));

                String teachModestr = mp.getParameter("teachMode");
                int teachMode = -1;

                if (teachModestr != null)
                {
                        teachMode = Integer.parseInt(teachModestr);
                }

                String isUpdateCourseContent = mp.getParameter("isUpdateCourseContent");

                if ((teachMode == CourseKeys.TEACH_MODE_COURSEWARE) &&
                        isUpdateCourseContent.equals("1"))
                {
                        String nodeStr = mp.getParameter("NodeID");

                        int nodeID = Integer.parseInt(nodeStr);
                        CourseContentForm ccf = (CourseContentForm) form;
                        ccf.setNodeID(nodeID);
                        ccf.setRelationID(courseID);
                        ccf.setType(CourseContentKeys.TYPE_PROGRAM);

                        System.out.println("the File value===" +
                                mp.getParameter("theFile"));
                        System.out.println("the Link value===" + theLink);
                        System.out.println("the nodeTitle value===" +
                                mp.getParameter("nodeTitle"));

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
                        dao.updateCourseContent(ccf);
                        LogUtil.info("system",
                                "[AddCourseContent]===========resultScreen = " + resultScreen);
                        request.setAttribute("mp", mp);
                }

                // Forward to result page
                return mapping.findForward(resultScreen);
        }
}
