/*
 * Copyright (c) 2004 Your Corporation. All Rights Reserved.
 */
package com.ulearning.ulms.course.util;

import com.ulearning.ulms.content.util.ContentManageUtil;
import com.ulearning.ulms.core.util.Config;
import com.ulearning.ulms.core.util.StringUtil;
import com.ulearning.ulms.course.helper.CourseContentHelper;
import com.ulearning.ulms.course.model.CourseContentForm;
import com.ulearning.ulms.util.LMSConstants;
import com.ulearning.ulms.util.log.LogUtil;
import com.ulearning.ulms.util.treeControl.UlmsTreeBuilder;

import org.apache.commons.io.FilenameUtils;

import org.apache.webapp.admin.ApplicationServlet;
import org.apache.webapp.admin.TreeControl;
import org.apache.webapp.admin.TreeControlNode;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


public class CourseContentTreeBuilder implements UlmsTreeBuilder
{
        private String domain = "CourseContent";

        //  private int type = SecurityConstants.USER_COURSE_RELATION;
        public void buildTree(TreeControl treeControl,
                              ApplicationServlet applicationServlet,
                              HttpServletRequest httpServletRequest)
        {
                buildTree(treeControl, httpServletRequest);
        }

        /**
         * Add the required nodes to the specified <code>treeControl</code>
         * instance.
         *
         * @param treeControl The <code>TreeControl</code> to which we should
         *                    add our nodes
         *                    //* @param servlet     The controller servlet for the admin application
         *                    //* @param request     The servlet request we are processing
         */
        public void buildTree(TreeControl treeControl)
        {
        }

        public void buildTree(TreeControl treeControl,
                              HttpServletRequest httpServletRequest)
        {
                int relationID = 0;
                int parentID = 0;

                if ((String) httpServletRequest.getAttribute("relationID") != null)
                {
                        relationID = Integer.parseInt((String) httpServletRequest.getAttribute(
                                "relationID"));
                }

                //  LogUtil.info("syetem", "relationID========" + relationID);
                if ((String) httpServletRequest.getAttribute("parentID") != null)
                {
                        parentID = Integer.parseInt((String) httpServletRequest.getAttribute(
                                "parentID"));
                }

                //  LogUtil.info("syetem", "parentID========" + parentID);
                HttpSession session = httpServletRequest.getSession();
                int userID = StringUtil.parseInt((String) session.getAttribute(
                        LMSConstants.USERID_KEY));

                try
                {
                        //LogUtil.info("System", "I am here -courseContent");
                        TreeControlNode root = treeControl.getRoot();

                        List courseContList = CourseContentHelper.getCourseContentList(relationID,
                                parentID);

                        TreeControlNode subtree = null;

                        //LogUtil.info("System","courseContent====="+courseContList.size());
                        for (int i = 0; i < courseContList.size(); i++)
                        {
                                CourseContentForm ccf = (CourseContentForm) courseContList.get(i);
                                String name = ccf.getNodeTitle();
                                String link = null;
                                String img = null;
                                String fileName = null;
                                int ext;
                                int nodeType;
                                link = ccf.getLink();

                                if (link == null)
                                {
                                        link = "";
                                }

                                //LogUtil.debug("course","[listcoursecontent.jsp]---link="+link);
                                //LogUtil.debug("course","[listcoursecontent.jsp]---link.lastIndexOf(\".\")="+ext);
                                nodeType = ccf.getNodetype();
                                img = CourseContentHelper.getCourseContentImgURL(ccf,
                                        httpServletRequest);

                                String path = null;
                                String href = "";

                                if (ccf.getIsHyperLink() == 1)
                                {
                                        path = ccf.getLink();
                                }
                                else
                                {
                                        path = Config.getUploadVirtualPath() + ccf.getLink();
                                }

                                if (nodeType == CourseContentKeys.SCORM_NODETYPE)
                                {
                                        href = "javascript:runScorm(" + ccf.getNodeID() + "," +
                                                userID + ")";
                                }
                                else if (nodeType == CourseContentKeys.AICC_NODETYPE)
                                {
                                        href = "javascript:runAicc(" + ccf.getNodeID() + "," +
                                                userID + ")";
                                }
                                else
                                {
                                        href = "javascript:content('" + path + "')";
                                }

                                if (ccf.getIsContent() != 0)
                                {
                                        if(Config.isXLNProject() && nodeType == CourseContentKeys.SHITING_NODETYPE)
                                        {
                                                subtree = new TreeControlNode("document/" +
                                                        ccf.getNodeID(), img, ccf.getNodeTitle()+"[ÊÔÌý]", href,
                                                        null, false, domain);
                                        }
                                        else
                                        {
                                                 subtree = new TreeControlNode("document/" +
                                                ccf.getNodeID(), img, ccf.getNodeTitle(), href,
                                                null, false, domain);
                                        }
                                }
                                else
                                {
                                        subtree = new TreeControlNode("nodeID/" + ccf.getNodeID(),
                                                img, name, null, null, false, domain);
                                }

                                root.addChild(subtree);
                        }
                }
                catch (Exception e)
                {
                        e.printStackTrace();
                }
        }
}
