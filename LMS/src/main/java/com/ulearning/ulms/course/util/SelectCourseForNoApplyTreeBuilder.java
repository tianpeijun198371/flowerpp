/**
 * SelectCourseForNoApplyTreeBuilder.java.
 * User: fengch  Date: 2004-10-18
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.course.util;

import com.ulearning.ulms.admin.certificate.bean.CertHelper;
import com.ulearning.ulms.admin.certificate.form.CertForm;
import com.ulearning.ulms.core.security.bean.SecurityConstants;
import com.ulearning.ulms.core.util.StringUtil;
import com.ulearning.ulms.course.exceptions.CourseSysException;
import com.ulearning.ulms.course.helper.CourseHelper;
import com.ulearning.ulms.course.model.*;
import com.ulearning.ulms.course.webimpls.CourseWebImpl;
import com.ulearning.ulms.util.LMSConstants;
import com.ulearning.ulms.util.treeControl.UlmsTreeBuilder;

import org.apache.webapp.admin.ApplicationServlet;
import org.apache.webapp.admin.TreeControl;
import org.apache.webapp.admin.TreeControlNode;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


public class SelectCourseForNoApplyTreeBuilder implements UlmsTreeBuilder
{
        private String domain = "SelectCourseForNoApply";

        public void buildTree(TreeControl treeControl,
                              ApplicationServlet applicationServlet,
                              HttpServletRequest httpServletRequest)
        {
                HttpSession session = httpServletRequest.getSession();
                int aspID = 0;

                if (session.getAttribute(LMSConstants.USER_ASPID_KEY) != null)
                {
                        aspID = Integer.parseInt((String) session.getAttribute(
                                LMSConstants.USER_ASPID_KEY));
                }

                int orgID = 0;

                if (session.getAttribute(LMSConstants.USER_ORGID_KEY) != null)
                {
                        orgID = Integer.parseInt((String) session.getAttribute(
                                LMSConstants.USER_ORGID_KEY));
                }

                //Here need to get all the courses of ASP
                orgID = 0;
                buildTree(treeControl, aspID, orgID, httpServletRequest);
        }

        public void buildTree(TreeControl treeControl)
        {
        }

        public void buildTree(TreeControl treeControl, int aspID, int orgID,
                              HttpServletRequest httpServletRequest)
        {
                int catalogID = 0;
                TreeControlNode root = treeControl.getRoot();

                try
                {
                        CourseWebImpl courseWebImpl = new CourseWebImpl();
                        CourseTreeModel mtm = courseWebImpl.getTree(catalogID, aspID, orgID);

                        List courseList = new ArrayList();
                        List catalogList = new ArrayList();
                        List certList = new ArrayList();

                        if (mtm != null)
                        {
                                CatalogListModel catlm = mtm.getCatalogList();
                                CourseListModel clm = mtm.getCourseList();

                                catalogList = catlm.getList();
                                courseList = clm.getList();

                                certList = CertHelper.getCertList(catalogID, aspID, orgID);
                        }

                        //add cat node to tree
                        for (int i = 0; i < catalogList.size(); i++)
                        {
                                CatalogModel cat = (CatalogModel) catalogList.get(i);
                                TreeControlNode subtree = new TreeControlNode("catalogIDs/" +
                                        cat.getCatalogID(),
                                        httpServletRequest.getContextPath() +
                                                "/images/fclosed.gif", cat.getName(), null, null,
                                        false, domain);

                                root.addChild(subtree);
                                addSubtree(subtree, aspID, orgID, httpServletRequest);
                        }

                        //add certificate node to tree
                        for (int i = 0; i < certList.size(); i++)
                        {
                                if (CourseHelper.IsCourseAvailable(
                                        ((CertForm) certList.get(i)).getCertificateID(),
                                        SecurityConstants.USER_CERTIFICATE_RELATION))
                                {
                                        CertForm cf = (CertForm) certList.get(i);
                                        TreeControlNode subtree = new TreeControlNode(
                                                "certificateIDs/" + cf.getCertificateID(),
                                                httpServletRequest.getContextPath() +
                                                        "/images/cert.gif", cf.getName(),
                                                httpServletRequest.getContextPath() +
                                                        "/admin/course/course/certinfo.jsp?certificateID=" +
                                                        cf.getCertificateID(), null, false, domain);
                                        root.addChild(subtree);
                                }
                        }

                        //add course node to tree
                        for (int i = 0; i < courseList.size(); i++)
                        {
                                if (CourseHelper.IsCourseAvailable(
                                        ((CourseModel) courseList.get(i)).getCourseID(),
                                        SecurityConstants.USER_COURSE_RELATION))
                                {
                                        CourseModel cm = (CourseModel) courseList.get(i);
                                        TreeControlNode subtree = new TreeControlNode("courseID/" +
                                                cm.getCourseID(),
                                                httpServletRequest.getContextPath() +
                                                        "/images/course.gif", cm.getName(),
                                                httpServletRequest.getContextPath() +
                                                        "/mystudy/course/viewCourse.jsp?courseID=" +
                                                        cm.getCourseID(), null, false, domain);
                                        root.addChild(subtree);
                                }
                        }
                }
                catch (CourseSysException e)
                {
                        System.out.println(e);
                }
        }

        /**
         * Add the subtree of nodes required for user administration.
         *
         * @param root The root node of our tree control
         *             //* @param resources The MessageResources for our localized messages
         *             messages
         */
        protected void addSubtree(TreeControlNode root, int aspID, int orgID,
                                  HttpServletRequest httpServletRequest) throws CourseSysException
        {
                List temp = StringUtil.split(root.getName(), "/");
                int catalogID = ((!root.getName().equals(""))
                        ? Integer.parseInt((String) temp.get(1)) : 0);

                CourseWebImpl courseWebImpl = new CourseWebImpl();

                List courseList = new ArrayList();
                List catalogList = new ArrayList();
                List certList = new ArrayList();

                try
                {
                        CourseTreeModel mtm = courseWebImpl.getTree(catalogID, aspID, orgID);

                        if (mtm != null)
                        {
                                CatalogListModel catlm = mtm.getCatalogList();
                                CourseListModel clm = mtm.getCourseList();

                                catalogList = catlm.getList();
                                courseList = clm.getList();
                                certList = CertHelper.getCertList(catalogID, aspID, orgID);
                        }

                        //add cat node to tree
                        for (int i = 0; i < catalogList.size(); i++)
                        {
                                CatalogModel cat = (CatalogModel) catalogList.get(i);
                                TreeControlNode subtree = new TreeControlNode("catalogIDs/" +
                                        cat.getCatalogID(),
                                        httpServletRequest.getContextPath() +
                                                "/images/fclosed.gif", cat.getName(), null, null,
                                        false, domain);

                                root.addChild(subtree);
                                addSubtree(subtree, aspID, orgID, httpServletRequest);
                        }

                        //add course node to tree
                        for (int i = 0; i < courseList.size(); i++)
                        {
                                if (CourseHelper.IsCourseAvailable(
                                        ((CourseModel) courseList.get(i)).getCourseID(),
                                        SecurityConstants.USER_COURSE_RELATION))
                                {
                                        CourseModel cm = (CourseModel) courseList.get(i);
                                        TreeControlNode subtree = new TreeControlNode("courseID/" +
                                                cm.getCourseID(),
                                                httpServletRequest.getContextPath() +
                                                        "/images/course.gif", cm.getName(),
                                                httpServletRequest.getContextPath() +
                                                        "/mystudy/course/viewCourse.jsp?courseID=" +
                                                        cm.getCourseID(), null, false, domain);
                                        root.addChild(subtree);
                                }
                        }

                        //add certificate node to tree
                        for (int i = 0; i < certList.size(); i++)
                        {
                                if (CourseHelper.IsCourseAvailable(
                                        ((CertForm) certList.get(i)).getCertificateID(),
                                        SecurityConstants.USER_CERTIFICATE_RELATION))
                                {
                                        CertForm cf = (CertForm) certList.get(i);
                                        TreeControlNode subtree = new TreeControlNode(
                                                "certificateIDs/" + cf.getCertificateID(),
                                                httpServletRequest.getContextPath() +
                                                        "/images/cert.gif", cf.getName(),
                                                httpServletRequest.getContextPath() +
                                                        "/admin/course/course/certinfo.jsp?certificateID=" +
                                                        cf.getCertificateID(), null, false, domain);
                                        root.addChild(subtree);
                                }
                        }
                }
                catch (CourseSysException e)
                {
                        System.out.println(e);
                }
        }
}
