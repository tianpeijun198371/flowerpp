/**
 * AddCourseContentAction.java.
 * User: huangsb  Date: 2004-5-6
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd. * All rights reserved.
 */
package com.ulearning.ulms.course.action;

import com.ulearning.ulms.core.exceptions.ULMSAppException;
import com.ulearning.ulms.core.util.Config;
import com.ulearning.ulms.course.dao.CourseContentDAO;
import com.ulearning.ulms.course.dao.CourseContentDAOFactory;
import com.ulearning.ulms.course.helper.CourseContentHelper;
import com.ulearning.ulms.course.model.CourseContentForm;
import com.ulearning.ulms.course.util.CourseContentKeys;
import com.ulearning.ulms.tools.upload.action.MultipartParamUtils;
import com.ulearning.ulms.tools.upload.action.UploadAction;
import com.ulearning.ulms.tools.upload.model.UploadForm;
import com.ulearning.ulms.util.LMSConstants;
import com.ulearning.ulms.util.log.LogUtil;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.MultipartRequestWrapper;

import org.apache.webapp.admin.TreeControl;
import org.apache.webapp.admin.TreeControlNode;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class AddCourseContentAction extends UploadAction
{
        public ActionForward execute(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response)
                throws Exception
        {
                System.out.println("[AddCourseContentAction]---###################c1=" +
                        request.getParameter("courseID"));

                if (request instanceof MultipartRequestWrapper)
                {
                        request = (HttpServletRequest) (MultipartRequestWrapper) request;
                        System.out.println(
                                "[AddCourseContentAction]---###################c2=" +
                                        request.getParameter("courseID"));
                }

                System.out.println("[AddCourseContentAction]---###################c3=" +
                        request.getParameter("courseID"));

                HttpSession session = request.getSession();
                MultipartParamUtils mp = new MultipartParamUtils(request,
                        1024 * 1014 * 10);
                String theLink = mp.getParameter("theLink");
                String resultScreen = "success";
                CourseContentForm ccf = (CourseContentForm) form;
                int courseID = ccf.getRelationID();
                int parentID = ccf.getParentID();

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
                                ccf.setCatalog(ccf.getFilePath() +
                                        request.getAttribute("newFileName"));
                        }
                        else
                        {
                                ccf.setLink(theLink);
                        }
                }

                //如果名称类型一样，抛出异常；
                CourseContentHelper.throwsReAddException(ccf);

                CourseContentDAO dao = CourseContentDAOFactory.getDAO();
                int nodeID = dao.addCourseContent(ccf);

                String nodeName = "nodeID/" + String.valueOf(parentID);

                if (parentID == 0)
                {
                        nodeName = "ROOT-NODE";
                }

                TreeControl control = (TreeControl) session.getAttribute(LMSConstants.TREE_COURSECONTENT);

                TreeControl controlInContext = (TreeControl) getServlet()
                        .getServletContext()
                        .getAttribute(LMSConstants.TREE_COURSECONTENT +
                                String.valueOf(courseID));

                //System.out.println("controlInContext=" + controlInContext);
                int iscontent = ccf.getIsContent();
                String link = ccf.getLink();

                if (link == null)
                {
                        link = "";
                }

                int ext = link.lastIndexOf(".");
                String fileName = null;
                int nodeType = ccf.getNodetype();
                String imag = CourseContentHelper.getCourseContentImgURL(ccf, request);

                String path = "";

                if (ccf.getIsHyperLink() == 1)
                {
                        path = ccf.getLink();
                }
                else
                {
                        path = Config.getUploadVirtualPath() + ccf.getLink();
                }

                String href = "javascript:content('" + path + "')";

                if (control != null)
                {
                        TreeControlNode parentNode = control.findNode(nodeName);
                        TreeControlNode subtree = null;
                        LogUtil.debug("CourseContent", "path=" + path);
                        control.selectNode(nodeName);

                        if (parentNode != null)
                        {
                                if (iscontent == 0)
                                {
                                        subtree = new TreeControlNode("nodeID/" + nodeID, imag,
                                                ccf.getNodeTitle(), null, null, false,
                                                "CourseContent");
                                }
                                else
                                {
                                        if(Config.isXLNProject() && nodeType == CourseContentKeys.SHITING_NODETYPE)
                                        {
                                                subtree = new TreeControlNode("document/" + nodeID, imag,
                                                ccf.getNodeTitle()+"[试听]", href, null, false,
                                                "CourseContent");

                                        }
                                        else
                                        {
                                                subtree = new TreeControlNode("document/" + nodeID, imag,
                                                ccf.getNodeTitle(), href, null, false,
                                                "CourseContent");

                                        }

                                }

                                parentNode.addChild(subtree);
                        }
                }

                if (controlInContext != null)
                {
                        TreeControlNode parentNode = controlInContext.findNode(nodeName);
                        TreeControlNode subtree = null;

                        if (parentNode != null)
                        {
                                if (iscontent == 0)
                                {
                                        subtree = new TreeControlNode("nodeID/" + nodeID, imag,
                                                ccf.getNodeTitle(), null, null, false,
                                                "CourseContent");
                                }
                                else
                                {
                                        if(Config.isXLNProject() && nodeType == CourseContentKeys.SHITING_NODETYPE)
                                        {
                                                subtree = new TreeControlNode("document/" + nodeID, imag,
                                                ccf.getNodeTitle()+"[试听]", path, null, false,
                                                "CourseContent");
                                        }
                                        else
                                        {
                                              subtree = new TreeControlNode("document/" + nodeID, imag,
                                                ccf.getNodeTitle(), path, null, false,
                                                "CourseContent");
                                        }

                                }

                                parentNode.addChild(0, subtree);
                        }
                }

                LogUtil.info("system",
                        "[AddCourseContent]===========resultScreen = " + resultScreen);
                request.setAttribute("mp", mp);
                request.setAttribute("catalogID", "1");
                request.setAttribute("relationID", String.valueOf(ccf.getRelationID()));
                request.setAttribute("parentID", String.valueOf(ccf.getParentID()));

                // Forward to result page
                return mapping.findForward(resultScreen);
        }
}
