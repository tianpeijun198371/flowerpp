/**
 * TreeFlush.java.
 * User: zengwj Date: 2005-8-12 12:14:13
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.course.util;

import com.ulearning.ulms.util.LMSConstants;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;


public class TreeFlush
{
        public static void flush(ServletContext servletContext,
                                 HttpSession session, int aspID, String treeType)
        {
                if (servletContext.getAttribute(LMSConstants.TREE_PUBLISH + aspID) != null)
                {
                        servletContext.removeAttribute(LMSConstants.TREE_PUBLISH + aspID);
                }

                if ((session.getAttribute(LMSConstants.TREE_PUBLISH) != null) &&
                        (treeType != null) &&
                        !treeType.equals(LMSConstants.TREE_PUBLISH))
                {
                        session.removeAttribute(LMSConstants.TREE_PUBLISH);
                }

                if (servletContext.getAttribute(LMSConstants.TREE_SELECT + aspID) != null)
                {
                        servletContext.removeAttribute(LMSConstants.TREE_SELECT + aspID);
                }

                if ((session.getAttribute(LMSConstants.TREE_SELECT) != null) &&
                        (treeType != null) &&
                        !treeType.equals(LMSConstants.TREE_SELECT))
                {
                        session.removeAttribute(LMSConstants.TREE_SELECT);
                }

                if (servletContext.getAttribute(LMSConstants.TREE_STUDENT_SIGNUP +
                        aspID) != null)
                {
                        servletContext.removeAttribute(LMSConstants.TREE_STUDENT_SIGNUP +
                                aspID);
                }

                if ((session.getAttribute(LMSConstants.TREE_STUDENT_SIGNUP) != null) &&
                        (treeType != null) &&
                        !treeType.equals(LMSConstants.TREE_STUDENT_SIGNUP))
                {
                        session.removeAttribute(LMSConstants.TREE_STUDENT_SIGNUP);
                }

                if (servletContext.getAttribute(LMSConstants.TREE_COURSE + aspID) != null)
                {
                        servletContext.removeAttribute(LMSConstants.TREE_COURSE + aspID);
                }

                if ((session.getAttribute(LMSConstants.TREE_COURSE) != null) &&
                        (treeType != null) &&
                        !treeType.equals(LMSConstants.TREE_COURSE))
                {
                        session.removeAttribute(LMSConstants.TREE_COURSE);
                }

                if (servletContext.getAttribute(LMSConstants.TREE_CERT + aspID) != null)
                {
                        servletContext.removeAttribute(LMSConstants.TREE_CERT + aspID);
                }

                if ((session.getAttribute(LMSConstants.TREE_CERT) != null) &&
                        (treeType != null) && !treeType.equals(LMSConstants.TREE_CERT))
                {
                        session.removeAttribute(LMSConstants.TREE_CERT);
                }

                if (servletContext.getAttribute(LMSConstants.TREE_PUBLISHED_CERT +
                        aspID) != null)
                {
                        servletContext.removeAttribute(LMSConstants.TREE_PUBLISHED_CERT +
                                aspID);
                }

                if ((session.getAttribute(LMSConstants.TREE_PUBLISHED_CERT) != null) &&
                        (treeType != null) &&
                        !treeType.equals(LMSConstants.TREE_PUBLISHED_CERT))
                {
                        session.removeAttribute(LMSConstants.TREE_PUBLISHED_CERT);
                }

                if (servletContext.getAttribute(LMSConstants.TREE_PUBLISHED_COURSE +
                        aspID) != null)
                {
                        servletContext.removeAttribute(LMSConstants.TREE_PUBLISHED_COURSE +
                                aspID);
                }

                if ((session.getAttribute(LMSConstants.TREE_PUBLISHED_COURSE) != null) &&
                        (treeType != null) &&
                        !treeType.equals(LMSConstants.TREE_PUBLISHED_COURSE))
                {
                        session.removeAttribute(LMSConstants.TREE_PUBLISHED_COURSE);
                }

                if (servletContext.getAttribute(LMSConstants.TREE_SELECT_NO_SELECT_COURSE +
                        aspID) != null)
                {
                        servletContext.removeAttribute(LMSConstants.TREE_SELECT_NO_SELECT_COURSE +
                                aspID);
                }

                if ((session.getAttribute(LMSConstants.TREE_SELECT_NO_SELECT_COURSE) != null) &&
                        (treeType != null) &&
                        !treeType.equals(LMSConstants.TREE_PUBLISHED_COURSE))
                {
                        session.removeAttribute(LMSConstants.TREE_SELECT_NO_SELECT_COURSE);
                }
        }
}
