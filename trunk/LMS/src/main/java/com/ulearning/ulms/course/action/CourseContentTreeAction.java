/*
 * Copyright (c) 2004 Your Corporation. All Rights Reserved.
 */
package com.ulearning.ulms.course.action;

import com.ulearning.ulms.core.util.StringUtil;
import com.ulearning.ulms.course.helper.CourseContentHelper;
import com.ulearning.ulms.course.model.CourseContentForm;
import com.ulearning.ulms.tools.upload.action.MultipartParamUtils;
import com.ulearning.ulms.util.LMSConstants;
import com.ulearning.ulms.util.log.LogUtil;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import org.apache.webapp.admin.TreeControl;
import org.apache.webapp.admin.TreeControlNode;

import java.io.IOException;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class CourseContentTreeAction extends Action
{
        public ActionForward execute(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response)
                throws IOException, ServletException
        {
                String resultScreen = "success";
                String relationID = request.getParameter("relationID");
                String parentID = request.getParameter("parentID");
                HttpSession session = request.getSession();
                String name = null;
                String treeType = request.getParameter("treeType");

                TreeControl control = (TreeControl) session.getAttribute(treeType);

                // Handle a select item event
                name = request.getParameter("select");

                //LogUtil.info("coursecontent", "[TreeControlAction]select=" + name);
                if (name != null)
                {
                        ///   LogUtil.debug("course", "[TreeControlAction]Select event on " + name);
                        control.selectNode(name);
                }

                TreeControlNode node = null;
                name = request.getParameter("tree");

                int userID = StringUtil.parseInt((String) session.getAttribute(
                        LMSConstants.USERID_KEY));

                if ((name != null) && (name.indexOf("/") > 0) && (control != null))
                {
                        node = control.findNode(name);

                        List tempList = StringUtil.split(name, "/");
                        parentID = (String) tempList.get(1);
                        LogUtil.info("System", "houhx=========node=" + node);

                        //add organ node to tree
                        List contentList = null;
                        contentList = CourseContentHelper.getCourseContentList(Integer.parseInt(
                                relationID), Integer.parseInt(parentID));

                        // CourseContentForm of = null;
                        //TreeControlNode subtree = null;
                        //System.out.println("houhx================contentList=" + contentList.size());
                        if (node != null)
                        {
                                //  CourseContentHelper.genarateSubtree(node,Integer.parseInt(relationID),Integer.parseInt(parentID),true,control);
                                //    LogUtil.info("CourseContentControl", "Found Node: " + name);
                                if (contentList != null)
                                {
                                        for (int i = 0; i < contentList.size(); i++)
                                        {
                                                CourseContentForm ccf = (CourseContentForm) contentList.get(i);
                                                TreeControlNode subtree = CourseContentHelper.genarateSubtreeNode(ccf,
                                                        userID);
                                                TreeControlNode temp = null;
                                                String nodeName = null;

                                                if (ccf.getIsContent() == 0)
                                                {
                                                        nodeName = "nodeID/" +
                                                                String.valueOf(ccf.getNodeID());

                                                        //temp = control.findNode("nodeID/" + String.valueOf(of.getNodeID()));
                                                }
                                                else
                                                {
                                                        nodeName = "document/" +
                                                                String.valueOf(ccf.getNodeID());

                                                        // temp = control.findNode("document/" +String.valueOf(of.getNodeID()));
                                                }

                                                temp = control.findNode(nodeName);

                                                //  LogUtil.info("System", "houhx=========subtree=" + subtree);
                                                // LogUtil.info("System", "houhx=========temp=" + temp);
                                                if (temp == null)
                                                {
                                                        if (subtree != null)
                                                        {
                                                                node.addChild(subtree);
                                                        }
                                                }
                                        }
                                }

                                // Handle a tree expand/contract event
                                node.setExpanded(!node.isExpanded());
                                control.selectNode(name);

                                if (!node.isExpanded())
                                {
                                        TreeControlNode parentNode = node.getParent();

                                        if (parentNode != null)
                                        {
                                                String temp = parentNode.getName();

                                                if (!temp.equals("ROOT-NODE"))
                                                {
                                                        List parentList = StringUtil.split(temp, "/");
                                                        parentID = (String) parentList.get(1);
                                                }
                                                else
                                                {
                                                        parentID = "0";
                                                }
                                        }
                                }
                        }
                }

                MultipartParamUtils mp = new MultipartParamUtils(request,
                        1024 * 1014 * 10);
                request.setAttribute("parentID", parentID);
                request.setAttribute("mp", mp);
                request.setAttribute("catalogID", "1");
                request.setAttribute("relationID", relationID);

                return mapping.findForward(resultScreen);
        }
}
