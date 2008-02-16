/**
 * UpdateCourseContentAction.java.
 * User: huangsb  Date: 2004-5-6
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd. * All rights reserved.
 */
package com.ulearning.ulms.course.action;

import com.ulearning.ulms.core.util.Config;
import com.ulearning.ulms.course.dao.CourseContentDAO;
import com.ulearning.ulms.course.dao.CourseContentDAOFactory;
import com.ulearning.ulms.course.model.CourseContentForm;
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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class UpdateCourseContentAction extends UploadAction
{
        public ActionForward execute(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response)
                throws Exception
        {
                MultipartParamUtils mp = new MultipartParamUtils(request,
                        1024 * 1014 * 10);
                String theLink = mp.getParameter("theLink");

                String theFile = mp.getParameter("theFile");

                String updateLink = mp.getParameter("updateLink");

                String resultScreen = "success";
                CourseContentForm ccf = (CourseContentForm) form;

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

                        if ((theLink != null))
                        {
                                ccf.setLink(theLink);
                        }
                        else
                        {
                                if ((theLink == null) &&
                                        (!String.valueOf(request.getAttribute("size"))
                                                .equals("0")))
                                {
                                        ccf.setLink(ccf.getFilePath() +
                                                request.getAttribute("newFileName"));
                                }
                                else
                                {
                                        ccf.setLink(updateLink);
                                }
                        }
                }

                CourseContentDAO dao = CourseContentDAOFactory.getDAO();
                dao.updateCourseContent(ccf);

                HttpSession session = request.getSession();
                TreeControl control = (TreeControl) session.getAttribute(LMSConstants.TREE_COURSECONTENT);

                // LogUtil.info("updateCourseContent","updateCourseContent============control="+control);
                TreeControl controlInContext = (TreeControl) getServlet()
                        .getServletContext()
                        .getAttribute(LMSConstants.TREE_COURSECONTENT +
                                String.valueOf(ccf.getRelationID()));

                //LogUtil.info("updateCourseContent","updateCourseContent============controlInContext="+controlInContext);
                String tempStr = "";

                if (ccf.getIsContent() == 0)
                {
                        tempStr = "nodeID/" + String.valueOf(ccf.getNodeID());
                }
                else
                {
                        tempStr = "document/" + String.valueOf(ccf.getNodeID());
                }

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
                        TreeControlNode currentNode = control.findNode(tempStr);

                        // TreeControlNode

                        //   LogUtil.info("updateCourseContent","updateCourseContent1============currentNode="+currentNode);
                        if (currentNode != null)
                        {
                                currentNode.setLabel(ccf.getNodeTitle());
                                currentNode.setAction(href);
                        }
                }

                if (controlInContext != null)
                {
                        TreeControlNode currentNode = controlInContext.findNode(tempStr);

                        //    LogUtil.info("updateCourseContent","updateCourseContent2============currentNode="+currentNode);
                        if (currentNode != null)
                        {
                                currentNode.setLabel(ccf.getNodeTitle());
                                currentNode.setAction(href);
                        }
                }

                LogUtil.info("system",
                        "[UpdateCourseContentAction]===========resultScreen = " +
                                resultScreen);
                request.setAttribute("mp", mp);
                request.setAttribute("catalogID", "1");
                request.setAttribute("relationID", String.valueOf(ccf.getRelationID()));
                request.setAttribute("parentID", String.valueOf(ccf.getParentID()));

                // Forward to result page
                return mapping.findForward(resultScreen);
        }
}
