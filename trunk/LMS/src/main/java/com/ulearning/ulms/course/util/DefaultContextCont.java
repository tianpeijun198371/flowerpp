/*
 * Copyright (c) 2005 Your Corporation. All Rights Reserved.
 */
package com.ulearning.ulms.course.util;

import com.ulearning.ulms.core.context.ContextConstants;
import com.ulearning.ulms.util.log.LogUtil;

import org.apache.webapp.admin.TreeBuilder;
import org.apache.webapp.admin.TreeControl;
import org.apache.webapp.admin.TreeControlNode;

import java.util.Locale;

import javax.servlet.ServletContext;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.PageContext;


public class DefaultContextCont implements ContextConstants
{
        ServletContext servletContext = null;
        ServletRequest servletRequest = null;

        //private List courseTree = null;
        public DefaultContextCont(ServletRequest sr, ServletContext sc)
        {
                init(sr, sc);
        }

        public DefaultContextCont(PageContext pc)
        {
                init(pc.getRequest(), pc.getServletContext());
        }

        public ServletContext getServletContext()
        {
                return servletContext;
        }

        private void init(ServletRequest sr, ServletContext sc)
        {
                servletRequest = sr;
                servletContext = sc;
        }

        /**
         * Get the client-side locale.
         *
         * @return
         */
        public Locale getLocale()
        {
                return servletRequest.getLocale();
        }

        public TreeControl getTree(String treeType, HttpServletRequest request)
        {
                TreeControl control = null;
                TreeControl secControl = null;

                HttpSession session = request.getSession();
                String relationID = "0";

                if (request.getAttribute("relationID") != null)
                {
                        relationID = (String) request.getAttribute("relationID");
                }

                String typeInContext = treeType + relationID;

                LogUtil.info("syetw",
                        "DefaultContextCont=" + servletContext.getAttribute(typeInContext));

                if (servletContext.getAttribute(typeInContext) != null)
                {
                        control = (TreeControl) servletContext.getAttribute(typeInContext);
                        secControl = (TreeControl) control.clone(); //clone a tree
                        session.setAttribute(treeType, secControl);
                }
                else
                {
                        String id = "1";
                        TreeControlNode root = new TreeControlNode("ROOT-NODE", null, null,
                                "setupTree.do?select=ROOT-NODE", "content", true, "Root");
                        control = new TreeControl(root);

                        TreeBuilder builder = TreeBuilderFactory.getTreeBuilder(treeType, id);

                        //LogUtil.info("syetem", "builder========" + builder);
                        if (builder != null)
                        {
                                builder.buildTree(control, null, request);
                        }

                        secControl = (TreeControl) control.clone(); //clone a tree

                        session.setAttribute(treeType, secControl);
                        servletContext.setAttribute(typeInContext, control);
                }

                return control;
        }
}
