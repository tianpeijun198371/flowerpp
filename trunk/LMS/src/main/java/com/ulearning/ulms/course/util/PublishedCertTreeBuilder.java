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


public class PublishedCertTreeBuilder implements UlmsTreeBuilder
{
        private String domain = "PublishedCert";

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

                //asp下的 所有课程  huangsb
                buildTree(treeControl, aspID, 0);
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

                        List catalogList = new ArrayList();
                        List certList = new ArrayList();

                        if (mtm != null)
                        {
                                CatalogListModel catlm = mtm.getCatalogList();
                                catalogList = catlm.getList();
                                certList = CertHelper.getCertList(catalogID, aspID, orgID);
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

                        //add certificate node to tree
                        for (int i = 0; i < certList.size(); i++)
                        {
                                CertForm cf = (CertForm) certList.get(i);
                                String certName = cf.getName();

                                if (Integer.parseInt(cf.getCertType()) == SecurityConstants.USER_OFFLINE_CERTIFICATE_RELATION)
                                {
                                        certName += "  [脱产]";
                                }
                                else
                                if (Integer.parseInt(cf.getCertType()) == SecurityConstants.USER_CERTIFICATE_RELATION)
                                {
                                        certName += "  [远程]";
                                }

                                // System.out.println("certName ====================================== " + certName);
                                //System.out.println("cf.getCertType()================================ " + cf.getCertType());
                                TreeControlNode subtree = new TreeControlNode("certificateIDs/" +
                                        cf.getCertificateID(),
                                        Config.getContextRoot() + "/images/cert.gif",
                                        certName,
                                         Config.getContextRoot() +
                                                "/admin/course/course/certinfo.jsp?certificateID=" +
                                                cf.getCertificateID(), null, false, domain);
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

                List catalogList = new ArrayList();
                List certList = new ArrayList();

                try
                {
                        CourseTreeModel mtm = courseWebImpl.getTree(catalogID, aspID, orgID);

                        if (mtm != null)
                        {
                                CatalogListModel catlm = mtm.getCatalogList();
                                catalogList = catlm.getList();
                                certList = CertHelper.getCertList(catalogID, aspID, orgID);
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

                        //add certificate node to tree
                        for (int i = 0; i < certList.size(); i++)
                        {
                                CertForm cf = (CertForm) certList.get(i);
                                String certName = cf.getName();

                                if (Integer.parseInt(cf.getCertType()) == SecurityConstants.USER_OFFLINE_CERTIFICATE_RELATION)
                                {
                                        certName += "  [脱产]";
                                }
                                else
                                if (Integer.parseInt(cf.getCertType()) == SecurityConstants.USER_CERTIFICATE_RELATION)
                                {
                                        certName += "  [远程]";
                                }

                                TreeControlNode subtree = new TreeControlNode("certificateIDs/" +
                                        cf.getCertificateID(),
                                        Config.getContextRoot() + "/images/cert.gif",
                                        certName,
                                        Config.getContextRoot() +
                                                "/admin/course/course/certinfo.jsp?certificateID=" +
                                                cf.getCertificateID(), null, false, domain);
                                root.addChild(subtree);
                        }
                }
                catch (CourseSysException e)
                {
                        System.out.println(e);
                }
        }
}
