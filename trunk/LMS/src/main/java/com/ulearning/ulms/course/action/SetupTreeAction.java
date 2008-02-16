/**
 * SetupTreeAction.java.
 * User: dengj  Date: 2004-7-17
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.course.action;

import com.ulearning.ulms.course.util.CertTreeBuilder;
import com.ulearning.ulms.course.util.CourseTreeBuilder;
import com.ulearning.ulms.util.LMSConstants;
import com.ulearning.ulms.util.treeControl.TreeBuilderFactory;

import org.apache.struts.action.*;

import org.apache.webapp.admin.ApplicationServlet;
import org.apache.webapp.admin.TreeBuilder;
import org.apache.webapp.admin.TreeControl;
import org.apache.webapp.admin.TreeControlNode;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class SetupTreeAction extends Action
{
        public static final String DOMAIN_KEY = "domain";
        public static final int INIT_PLUGIN_MAX = 10;
        public static final String TREEBUILDER_KEY = "treebuilders";
        public static final String ROOTNODENAME_KEY = "rootnodename";

        public ActionForward execute(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response)
                throws IOException, ServletException
        {
                ActionServlet servlet = getServlet();

                // Getting init parms from web.xml

                // Get the string to be displayed as root node while rendering the tree
                String rootnodeName = "CourseRoot";

                String domain = "Course";
                String treeType = LMSConstants.TREE_CERT;

                String id = null;
                id = request.getParameter("id");

                if (request.getParameter("treeType") != null)
                {
                        treeType = request.getParameter("treeType");
                }

                // Make the root node and tree control

                // The root node gets rendered only if its value
                // is set as an init-param in web.xml
                TreeControlNode root = new TreeControlNode("ROOT-NODE", null, null,
                        "setupTree.do?select=ROOT-NODE", "content", true, domain);

                //System.out.println("root = "+root.toString());
                TreeControl control = new TreeControl(root);

                //System.out.println("treeType = "+treeType);
                //System.out.println("id = "+id);
                //TreeBuilder builder = TreeBuilderFactory.getTreeBuilder(treeType,id);
                TreeBuilder builder = TreeBuilderFactory.getTreeBuilder(treeType, id);

                if (builder != null)
                {
                        builder.buildTree(control, null, request);
                }

                HttpSession session = request.getSession();
                session.setAttribute(treeType, control);

                String name = request.getParameter("select");

                if (name != null)
                {
                        control.selectNode(name);

                        // Forward back to the Blank page
                        return (mapping.findForward("Blank"));
                }

                return (mapping.findForward("success"));
        }
}
