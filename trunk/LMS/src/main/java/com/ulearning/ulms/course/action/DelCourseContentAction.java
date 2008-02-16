/**
 * delCourseContentAction.java.
 * User: huangsb  Date: 2004-5-6
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd. * All rights reserved.
 */
package com.ulearning.ulms.course.action;

import com.ulearning.ulms.core.util.Config;
import com.ulearning.ulms.core.util.IOUtil;
import com.ulearning.ulms.course.dao.CourseContentDAO;
import com.ulearning.ulms.course.dao.CourseContentDAOFactory;
import com.ulearning.ulms.course.model.CourseContentForm;
import com.ulearning.ulms.course.util.CourseContentKeys;
import com.ulearning.ulms.scorm.util.ScormConstants;
import com.ulearning.ulms.util.LMSConstants;
import com.ulearning.ulms.util.log.LogUtil;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import org.apache.webapp.admin.TreeControl;
import org.apache.webapp.admin.TreeControlNode;

import java.io.File;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class DelCourseContentAction extends Action
{
        public ActionForward execute(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response)
                throws Exception
        {
                String resultScreen = "success";

                //   String type = request.getParameter("type");
                String[] nodeID = request.getParameterValues("nodeID");

                //ArrayList al = new ArrayList();
                HttpSession session = request.getSession();
                String relationID = request.getParameter("relationID");
                String parentID = request.getParameter("parentID");
                LogUtil.info("delcoursecontent", "relationID=" + relationID);

                TreeControl control = null;

                // TreeControl control1 = null;
                TreeControl controlInContext = null;
                control = (TreeControl) session.getAttribute(LMSConstants.TREE_COURSECONTENT);
                controlInContext = (TreeControl) getServlet().getServletContext()
                        .getAttribute(LMSConstants.TREE_COURSECONTENT +
                                relationID);

                TreeControlNode currentNode = null;

                if (nodeID != null)
                {
                        for (int i = 0; i < nodeID.length; i++)
                        {
                                //al.add(new Integer(nodeID[i]));
                                if (control != null)
                                {
                                        currentNode = control.findNode("nodeID/" + nodeID[i]);

                                        if (currentNode == null)
                                        {
                                                currentNode = control.findNode("document/" + nodeID[i]);
                                        }

                                        if (currentNode != null)
                                        {
                                                control.removeCurrentNode(currentNode);
                                        }

                                        if (controlInContext != null)
                                        {
                                                currentNode = controlInContext.findNode("nodeID/" +
                                                        nodeID[i]);

                                                if (currentNode == null)
                                                {
                                                        currentNode = controlInContext.findNode("document/" +
                                                                nodeID[i]);
                                                }

                                                if (currentNode != null)
                                                {
                                                        controlInContext.removeCurrentNode(currentNode);
                                                }
                                        }
                                }
                        }
                }

                // CourseContentDAO dao = CourseContentDAOFactory.getDAO();

                // if(type!=null&&Integer.parseInt(type)==LMSConstants.TREE_COURSECONTENT)
                CourseContentDAO dao = CourseContentDAOFactory.getDAO();
                String filePath = null;
                int nodeType;

                for (int i = 0; i < nodeID.length; i++)
                {
                        CourseContentForm ccf = dao.getCourseContent(nodeID[i]);

                        if (ccf.getIsContent() == 1)
                        {
                                nodeType = ccf.getNodetype();
                                LogUtil.debug("course",
                                        "[DelCourseContentAction]====================Config.getUploadPhysicalPath()=" +
                                                Config.getUploadPhysicalPath());

                                if ((nodeType == CourseContentKeys.FILE_NODETYPE) ||
                                        (nodeType == CourseContentKeys.STREAMMEDIA_NODETYPE))
                                {
                                        filePath = Config.getUploadPhysicalPath() + ccf.getLink();
                                        LogUtil.debug("course",
                                                "[DelCourseContentAction]====================delete file(not scorm)");
                                        LogUtil.debug("course",
                                                "[DelCourseContentAction]====================filePath=" +
                                                        filePath);

                                        try
                                        {
                                                File file = new File(filePath);

                                                if (file.exists())
                                                {
                                                        IOUtil.delAllFile(file);
                                                }
                                        }
                                        catch (Exception e)
                                        {
                                                e.printStackTrace();
                                        }
                                }
                                else if (nodeType == CourseContentKeys.SCORM_NODETYPE)
                                {
                                        LogUtil.debug("course",
                                                "[DelCourseContentAction]====================delete file(scorm)");

                                        try
                                        {
                                                //delete node's RTERoot
                                                filePath = ScormConstants.RTERoot + nodeID[i];
                                                LogUtil.debug("course",
                                                        "[DelCourseContentAction]====================node's RTERoot=" +
                                                                filePath);

                                                File file = new File(filePath);
                                                IOUtil.delAllFile(file);

                                                //delete node's ImportRoot
                                                filePath = ScormConstants.ImportRoot + nodeID[i];
                                                LogUtil.debug("course",
                                                        "[DelCourseContentAction]====================node's ImportRoot=" +
                                                                filePath);
                                                file = new File(filePath);
                                                IOUtil.delAllFile(file);
                                        }
                                        catch (Exception e)
                                        {
                                                e.printStackTrace();
                                        }
                                }
                        }

                        dao.removeCourseContent(nodeID[i]);
                }

                request.setAttribute("catalogID", "0");
                //request.setAttribute("mp", mp);
                request.setAttribute("relationID", relationID);
                request.setAttribute("parentID", parentID);

                // Forward to result page
                return mapping.findForward(resultScreen);
        }
}
