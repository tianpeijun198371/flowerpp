/**
 * DefaultContext.java.
 * User: dengj  Date: 2004-7-14
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.core.context;

import com.ulearning.ulms.course.exceptions.CourseSysException;
import com.ulearning.ulms.course.helper.CourseHelper;
import com.ulearning.ulms.util.LMSConstants;
import com.ulearning.ulms.util.treeControl.TreeBuilderFactory;

import org.apache.webapp.admin.TreeBuilder;
import org.apache.webapp.admin.TreeControl;
import org.apache.webapp.admin.TreeControlNode;

import java.util.List;
import java.util.Locale;

import javax.servlet.ServletContext;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.PageContext;


public class DefaultContext implements ContextConstants
{
        ServletContext servletContext = null;
        ServletRequest servletRequest = null;
        private List courseTree = null;

        public DefaultContext(ServletRequest sr, ServletContext sc)
        {
                init(sr, sc);
        }

        public DefaultContext(PageContext pc)
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
                String orgID = "0";

                if (session.getAttribute(LMSConstants.USER_ORGID_KEY) != null)
                {
                        orgID = (String) session.getAttribute(LMSConstants.USER_ORGID_KEY);
                }

                String typeInContext = treeType + orgID;

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

        /**
         * @return
         */
        public List getCourseTree(int aspID)
        {
                if (servletContext != null)
                {
                        Object tree = servletContext.getAttribute(CONTEXT_COURSE_TREE);

                        if (tree != null)
                        {
                                courseTree = (List) tree;

                                return courseTree;
                        }
                        else
                        {
                                try
                                {
                                        courseTree = CourseHelper.getCourseTree(aspID);
                                }
                                catch (CourseSysException cs)
                                {
                                }
                        }
                }

                return courseTree;
        }

        /**
         * Retrieve an object from Context.
         *
         * @param key
         * @return
         */
        public Object get(Object key)
        {
                if ((key != null) && key instanceof String)
                {
                        String type = (String) key;

                        if (type.equals(CONTEXT_LOGGER))
                        {
                                return getTree(null, null);
                        }
                        else if (type.equals(CONTEXT_CONFIG))
                        {
                                return getTree(null, null);
                        }
                        else
                        {
                                return null;
                        }
                }
                else
                {
                        return null;
                }
        }
}
