/**
 * Created by IntelliJ IDEA.
 * User: zengwj
 * Date: 2004-7-22
 * Time: 10:41:55
 * To change this template use File | Settings | File Templates.
 */
package com.ulearning.ulms.course.util;

import com.ulearning.ulms.admin.certificate.bean.CertHelper;
import com.ulearning.ulms.admin.certificate.form.CertForm;
import com.ulearning.ulms.core.security.bean.SecurityConstants;
import com.ulearning.ulms.core.util.Config;
import com.ulearning.ulms.core.util.StringUtil;
import com.ulearning.ulms.course.exceptions.CourseSysException;
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


public class PublishedCourseTreeBuilder implements UlmsTreeBuilder
{
        private String domain = "PublishedCourse";

        public void buildTree(TreeControl treeControl,
                              ApplicationServlet applicationServlet,
                              HttpServletRequest httpServletRequest)
        {
                HttpSession session = httpServletRequest.getSession();
                int aspID = 0;

                if (session.getAttribute(LMSConstants.USER_ASPID_KEY) != null)
                {
                        aspID = Integer.parseInt((String) (String) session.getAttribute(
                                LMSConstants.USER_ASPID_KEY));
                }

                int orgID = 0;

                if (session.getAttribute(LMSConstants.USER_ORGID_KEY) != null)
                {
                        orgID = Integer.parseInt((String) (String) session.getAttribute(
                                LMSConstants.USER_ORGID_KEY));
                }

                buildTree(treeControl, aspID, orgID);
        }

        /**
         * @param treeControl
         */
        public void buildTree(TreeControl treeControl)
        {
                ///
        }

        /**
         * @param treeControl
         * @param orgID
         */
        public void buildTree(TreeControl treeControl, int aspID, int orgID)
        {
                int catalogID = 0;
                TreeControlNode root = treeControl.getRoot();

                try
                {
                        CourseWebImpl courseWebImpl = new CourseWebImpl();
                        CourseTreeModel mtm = courseWebImpl.getTree(catalogID, aspID, orgID);

                        List courseList = new ArrayList();
                        List catalogList = new ArrayList();

                        if (mtm != null)
                        {
                                CatalogListModel catlm = mtm.getCatalogList();
                                CourseListModel clm = mtm.getCourseList();
                                catalogList = catlm.getList();
                                courseList = clm.getList();
                        }

                        //add cat node to tree
                        for (int i = 0; i < catalogList.size(); i++)
                        {
                                CatalogModel cat = (CatalogModel) catalogList.get(i);
                                TreeControlNode subtree = new TreeControlNode("catalogIDs/" +
                                        cat.getCatalogID(),
                                        Config.getContextRoot() + "/images/fclosed.gif",
                                        cat.getName(), null, null, false, domain);

                                root.addChild(subtree);
                                addSubtree(subtree, aspID, orgID);
                        }

                        //add course node to tree
                        for (int i = 0; i < courseList.size(); i++)
                        {
                                CourseModel cm = (CourseModel) courseList.get(i);
                                TreeControlNode subtree = new TreeControlNode("courseIDs/" +
                                        cm.getCourseID(),
                                        Config.getContextRoot() + "/images/course.gif",
                                        cm.getName(),
                                        Config.getContextRoot() +
                                                "/mystudy/course/viewCourse.jsp?courseID=" +
                                                cm.getCourseID(), null, false, domain);
                                root.addChild(subtree);
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
        protected void addSubtree(TreeControlNode root, int aspID, int orgID)
                throws CourseSysException
        {
                List temp = StringUtil.split(root.getName(), "/");
                int catalogID = ((!root.getName().equals(""))
                        ? Integer.parseInt((String) temp.get(1)) : 0);

                CourseWebImpl courseWebImpl = new CourseWebImpl();

                List courseList = new ArrayList();
                List catalogList = new ArrayList();

                try
                {
                        CourseTreeModel mtm = courseWebImpl.getTree(catalogID, aspID, orgID);

                        if (mtm != null)
                        {
                                CatalogListModel catlm = mtm.getCatalogList();
                                CourseListModel clm = mtm.getCourseList();
                                catalogList = catlm.getList();
                                courseList = clm.getList();
                        }

                        //add cat node to tree
                        for (int i = 0; i < catalogList.size(); i++)
                        {
                                CatalogModel cat = (CatalogModel) catalogList.get(i);
                                TreeControlNode subtree = new TreeControlNode("catalogIDs/" +
                                        cat.getCatalogID(),
                                         Config.getContextRoot() + "/images/fclosed.gif",
                                        cat.getName(), null, null, false, domain);

                                root.addChild(subtree);
                                addSubtree(subtree, aspID, orgID);
                        }

                        //add course node to tree
                        for (int i = 0; i < courseList.size(); i++)
                        {
                                CourseModel cm = (CourseModel) courseList.get(i);
                                TreeControlNode subtree = new TreeControlNode("courseIDs/" +
                                        cm.getCourseID(),
                                        Config.getContextRoot() + "/images/course.gif",
                                        cm.getName(),
                                        Config.getContextRoot() +
                                                "/mystudy/course/viewCourse.jsp?courseID=" +
                                                cm.getCourseID(), null, false, domain);
                                root.addChild(subtree);
                        }
                }
                catch (CourseSysException e)
                {
                        System.out.println(e);
                }
        }
}
