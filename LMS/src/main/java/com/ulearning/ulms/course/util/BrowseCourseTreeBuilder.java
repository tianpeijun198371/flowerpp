/**
 * Created by IntelliJ IDEA.
 * User: zengwj
 * Date: 2004-8-5
 * Time: 16:40:20
 * To change this template use File | Settings | File Templates.
 */
package com.ulearning.ulms.course.util;

import com.ulearning.ulms.admin.certificate.bean.CertHelper;
import com.ulearning.ulms.admin.certificate.exceptions.CertDAOSysException;
import com.ulearning.ulms.admin.certificate.form.CertForm;
import com.ulearning.ulms.admin.certificate.form.CertTreeForm;
import com.ulearning.ulms.admin.certificate.webimpls.CertWebImpl;
import com.ulearning.ulms.core.security.bean.SecurityConstants;
import com.ulearning.ulms.core.util.Config;
import com.ulearning.ulms.core.util.StringUtil;
import com.ulearning.ulms.course.exceptions.CourseSysException;
import com.ulearning.ulms.course.model.*;
import com.ulearning.ulms.course.webimpls.CourseWebImpl;
import com.ulearning.ulms.util.LMSConstants;
import com.ulearning.ulms.util.treeControl.UlmsTreeBuilder;
import com.ulearning.ulms.util.treeControl.UlmsTreeBuilder;

import org.apache.webapp.admin.ApplicationServlet;
import org.apache.webapp.admin.TreeControl;
import org.apache.webapp.admin.TreeControlNode;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


public class BrowseCourseTreeBuilder implements UlmsTreeBuilder
{
        private String domain = "BrowseCourse";

        public void buildTree(TreeControl treeControl,
                              ApplicationServlet applicationServlet,
                              HttpServletRequest httpServletRequest)
        {
                int catalogID = 0;
                TreeControlNode root = treeControl.getRoot();

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

                int type = StringUtil.parseInt(httpServletRequest.getParameter("type"));

                ArrayList courseList = new ArrayList();
                ArrayList catalogList = new ArrayList();
                List certList = null;

                CatalogListModel catalogs = null;
                CourseListModel courses = null;

                try
                {
                        if (type == SecurityConstants.USER_COURSE_RELATION)
                        {
                                CourseWebImpl courseWebImpl = new CourseWebImpl();
                                CourseTreeModel ctm = null;
                                /*if(aspID == 0)
                                ctm = courseWebImpl.getTree(catalogID);
                                else*/
                                ctm = courseWebImpl.getTree(catalogID, aspID, orgID);

                                courses = ctm.getCourseList();
                                courseList = courses.getList();
                                catalogs = ctm.getCatalogList();
                                catalogList = catalogs.getList();

                                //add course node to tree
                                for (int i = 0; i < courseList.size(); i++)
                                {
                                        CourseModel cm = (CourseModel) courseList.get(i);
                                        TreeControlNode subtree = new TreeControlNode("courseIDs/" +
                                                cm.getCourseID(), "../../images/course.gif",
                                                cm.getName(),
                                                "viewCourse.jsp?courseID=" + cm.getCourseID(),
                                                null, false, domain);
                                        root.addChild(subtree);
                                }
                        }
                        else if (type == SecurityConstants.USER_CERTIFICATE_RELATION)
                        {
                                CertWebImpl certWebImpl = new CertWebImpl();
                                CertTreeForm ctf = null;
                                /* if(aspID == 0)
                                {
                                        certList = CertHelper.getCertList(catalogID);
                                        ctf = certWebImpl.getTree(catalogID);
                                }
                                else
                                {*/
                                certList = CertHelper.getCertList(catalogID, aspID, orgID);
                                ctf = certWebImpl.getTree(catalogID, aspID, orgID);
                                /*}*/
                                catalogs = ctf.getCatalogList();
                                catalogList = catalogs.getList();

                                //add cert node to tree
                                for (int i = 0; i < courseList.size(); i++)
                                {
                                        CertForm cf = (CertForm) certList.get(i);
                                        TreeControlNode subtree = new TreeControlNode(
                                                "certificateIDs/" + cf.getCertificateID(),
                                                Config.getContextRoot() +
                                                        "/images/course.gif", cf.getName(),
                                                Config.getContextRoot() +
                                                        "/admin/course/course/certinfo.jsp?certificateID=" +
                                                        cf.getCertificateID(), null, false, domain);
                                        root.addChild(subtree);
                                }
                        }

                        //add cat node to tree
                        for (int i = 0; i < catalogList.size(); i++)
                        {
                                CatalogModel cat = (CatalogModel) catalogList.get(i);
                                TreeControlNode subtree = new TreeControlNode("catalogIDs/" +
                                        cat.getCatalogID(), "../../images/fclosed.gif",
                                        cat.getName(), null, null, false, domain);

                                root.addChild(subtree);
                                addSubtree(subtree, aspID, orgID, type);
                        }
                }
                catch (CourseSysException cse)
                {
                        System.out.println(cse);
                }
                catch (CertDAOSysException cse)
                {
                        System.out.println(cse);
                }
        }

        /**
         * @param treeControl
         */
        public void buildTree(TreeControl treeControl)
        {
                ///
        }

        protected void addSubtree(TreeControlNode root, int aspID, int orgID,
                                  int type)
        {
                List temp = StringUtil.split(root.getName(), "/");
                int catalogID = ((!root.getName().equals(""))
                        ? Integer.parseInt((String) temp.get(1)) : 0);

                ArrayList courseList = new ArrayList();
                ArrayList catalogList = new ArrayList();
                List certList = null;

                CatalogListModel catalogs = null;
                CourseListModel courses = null;

                try
                {
                        if (type == SecurityConstants.USER_COURSE_RELATION)
                        {
                                CourseWebImpl courseWebImpl = new CourseWebImpl();
                                CourseTreeModel ctm = null;
                                /*if(aspID == 0)
                                ctm = courseWebImpl.getTree(catalogID);
                                else*/
                                ctm = courseWebImpl.getTree(catalogID, 0, aspID);

                                courses = ctm.getCourseList();
                                courseList = courses.getList();
                                catalogs = ctm.getCatalogList();
                                catalogList = catalogs.getList();

                                //add course node to tree
                                for (int i = 0; i < courseList.size(); i++)
                                {
                                        CourseModel cm = (CourseModel) courseList.get(i);
                                        TreeControlNode subtree = new TreeControlNode("courseIDs/" +
                                                cm.getCourseID(), "../../images/course.gif",
                                                cm.getName(),
                                                "viewCourse.jsp?courseID=" + cm.getCourseID(),
                                                null, false, domain);
                                        root.addChild(subtree);
                                }
                        }
                        else if (type == SecurityConstants.USER_CERTIFICATE_RELATION)
                        {
                                CertWebImpl certWebImpl = new CertWebImpl();
                                CertTreeForm ctf = null;
                                /* if(aspID == 0)
                                {
                                        certList = CertHelper.getCertList(catalogID);
                                        ctf = certWebImpl.getTree(catalogID);
                                }
                                else
                                {*/
                                certList = CertHelper.getCertList(catalogID, aspID);
                                ctf = certWebImpl.getTree(catalogID, 0, aspID);
                                /* }*/
                                catalogs = ctf.getCatalogList();
                                catalogList = catalogs.getList();

                                //add cert node to tree
                                for (int i = 0; i < courseList.size(); i++)
                                {
                                        CertForm cf = (CertForm) certList.get(i);
                                        TreeControlNode subtree = new TreeControlNode(
                                                "certificateIDs/" + cf.getCertificateID(),
                                                 Config.getContextRoot() +
                                                        "/images/course.gif", cf.getName(),
                                                 Config.getContextRoot() +
                                                        "/admin/course/course/certinfo.jsp?certificateID=" +
                                                        cf.getCertificateID(), null, false, domain);
                                        root.addChild(subtree);
                                }
                        }

                        //add cat node to tree
                        for (int i = 0; i < catalogList.size(); i++)
                        {
                                CatalogModel cat = (CatalogModel) catalogList.get(i);
                                TreeControlNode subtree = new TreeControlNode("catalogIDs/" +
                                        cat.getCatalogID(), "../../images/fclosed.gif",
                                        cat.getName(), null, null, false, domain);

                                root.addChild(subtree);
                                addSubtree(subtree, aspID, orgID, type);
                        }
                }
                catch (CourseSysException cse)
                {
                        System.out.println(cse);
                }
                catch (CertDAOSysException cse)
                {
                        System.out.println(cse);
                }
        }
}
