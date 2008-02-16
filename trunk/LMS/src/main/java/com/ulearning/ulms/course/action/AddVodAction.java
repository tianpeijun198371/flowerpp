/**
 * AddVodAction.java.
 * User: huangsb  Date: 2004-8-19
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd. * All rights reserved.
 */
package com.ulearning.ulms.course.action;

import com.ulearning.ulms.core.util.Config;
import com.ulearning.ulms.core.util.ZipUtil;
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

import org.apache.webapp.admin.TreeControl;
import org.apache.webapp.admin.TreeControlNode;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class AddVodAction extends UploadAction
{
        public ActionForward execute(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response)
                throws Exception
        {
                HttpSession session = request.getSession();
                MultipartParamUtils mp = new MultipartParamUtils(request,
                        1024 * 1014 * 10);
                String resultScreen = "success";
                String path = request.getParameter("uploadPath");
                String index = request.getParameter("index");

                String strIndex = "";

                if ((index != null) && (!index.trim().equals("")))
                {
                        strIndex = index;
                }
                else
                {
                        strIndex = "content.htm";
                }

                LogUtil.debug("system",
                        "[strIndex] Exeception====================" + strIndex);

                CourseContentForm ccf = (CourseContentForm) form;
                int parentID = ccf.getParentID();

                CourseContentHelper.throwsReAddException(ccf);

                CourseContentDAO dao = CourseContentDAOFactory.getDAO();
                int nodeID = dao.addCourseContent(ccf);

                LogUtil.debug("system", "[aaa] Exeception====================" + path);
                ccf.setFilePath(path + File.separator + ccf.getRelationID() +
                        File.separator + ccf.getParentID() + File.separator + nodeID +
                        File.separator);
                LogUtil.debug("system",
                        "[filePath1] Exeception====================" + ccf.getFilePath());

                //String aaa = "";
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
                                        "[AddVodAction] Exeception====================" + e);
                        }

                        if (!String.valueOf(request.getAttribute("size")).equals("0"))
                        {
                                //ccf.setLink(ccf.getFilePath()+request.getAttribute("newFileName"));
                                ccf.setLink(path + "/" + ccf.getRelationID() + "/" +
                                        ccf.getParentID() + "/" + nodeID + "/" + strIndex);
                                ccf.setCatalog(ccf.getFilePath() +
                                        request.getAttribute("newFileName"));

                                //aaa = ccf.getFilePath();
                        }

                        //LogUtil.debug("system", "[unzipDir] Exeception====================" + aaa);
                        LogUtil.debug("system",
                                "[filePath2] Exeception====================" +
                                        ccf.getFilePath() + request.getAttribute("newFileName"));
                        ccf.setNodeID(nodeID);
                        dao.updateCourseContent(ccf);

                        String unzippath = Config.getUploadPhysicalPath() +
                                ccf.getFilePath() + request.getAttribute("newFileName");
                        LogUtil.debug("system",
                                "[unzippath] Exeception====================" + unzippath);

                        String unzipDir = Config.getUploadPhysicalPath() +
                                ccf.getFilePath();
                        LogUtil.debug("system",
                                "[unzipDir] Exeception====================" + unzipDir);
                        ZipUtil.unZip(unzippath, unzipDir);
                }

                LogUtil.debug("system",
                        "[parentID] parentID====================" + parentID);

                int courseID = ccf.getRelationID();
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
                System.out.println("controlInContext=" + controlInContext);

                String path1 = "";
                String imag = null;

                if (ccf.getIsHyperLink() == 1)
                {
                        path1 = ccf.getLink();
                }
                else
                {
                        path1 = Config.getUploadVirtualPath() + ccf.getLink();
                }

                if (ccf.getNodetype() == CourseContentKeys.STREAMMEDIA_NODETYPE)
                {
                        imag = "/mystudy/coursecontent/images/filetype/stream.gif";
                }

                imag =  Config.getContextRoot() + imag;

                if (control != null)
                {
                        TreeControlNode parentNode = control.findNode(nodeName);
                        TreeControlNode subtree = null;
                        control.selectNode(nodeName);

                        if (parentNode != null)
                        {
                                subtree = new TreeControlNode("document/" + nodeID, imag,
                                        ccf.getNodeTitle(), path1, null, false, "CourseContent");
                                parentNode.addChild(subtree);
                        }
                }

                if (controlInContext != null)
                {
                        TreeControlNode parentNode = controlInContext.findNode(nodeName);
                        TreeControlNode subtree = null;

                        if (parentNode != null)
                        {
                                subtree = new TreeControlNode("document/" + nodeID, imag,
                                        ccf.getNodeTitle(), path1, null, false, "CourseContent");
                                parentNode.addChild(subtree);
                        }
                }

                request.setAttribute("catalogID", "1");
                request.setAttribute("mp", mp);
                request.setAttribute("relationID", String.valueOf(ccf.getRelationID()));
                request.setAttribute("parentID", String.valueOf(ccf.getParentID()));

                return mapping.findForward(resultScreen);
        }
}
