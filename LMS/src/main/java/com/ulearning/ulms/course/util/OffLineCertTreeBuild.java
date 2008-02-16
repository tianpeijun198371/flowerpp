/**
 * OffLineCertTreeBuild.java.
 * User: shid Date: 2006-2-9 11:40:13
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.course.util;

import com.ulearning.ulms.core.security.bean.SecurityConstants;
import com.ulearning.ulms.core.util.Config;
import com.ulearning.ulms.core.util.StringUtil;
import com.ulearning.ulms.course.exceptions.CourseSysException;
import com.ulearning.ulms.course.model.*;
import com.ulearning.ulms.course.webimpls.MasterWebImpl;
import com.ulearning.ulms.util.LMSConstants;

import org.apache.webapp.admin.ApplicationServlet;
import org.apache.webapp.admin.TreeControl;
import org.apache.webapp.admin.TreeControlNode;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


public class OffLineCertTreeBuild extends CertTreeBuilder
{
        private String domain = "Cert";
        private int type = 0; //SecurityConstants.USER_OFFLINE_CERTIFICATE_RELATION;
        private int catalogType = CourseKeys.CATALOG_MASTER_CERT;

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

                buildTree(treeControl, aspID, orgID);
        }

        public void buildTree(TreeControl treeControl)
        {
        }

        public void buildTree(TreeControl treeControl, int aspID, int orgID)
        {
                try
                {
                        TreeControlNode root = treeControl.getRoot();
                        int catalogID = 0;

                        ArrayList masterList = new ArrayList();
                        ArrayList catalogList = new ArrayList();

                        MasterWebImpl mwi = new MasterWebImpl();
                        MasterTreeModel mtm = null;
                        //if(aspID != 0)
                        mtm = mwi.getTree(catalogID, type, aspID, orgID, catalogType);

                        /*else
                      mtm = mwi.getTree(catalogID,type);*/
                        if (mtm != null)
                        {
                                MasterListModel masters = mtm.getMasterList();
                                CatalogListModel catalogs = mtm.getCatalogList();

                                masterList = masters.getList();
                                catalogList = catalogs.getList();
                        }

                        //add cat node to tree
                        for (int i = 0; i < catalogList.size(); i++)
                        {
                                CatalogModel cm = (CatalogModel) catalogList.get(i);
                                TreeControlNode subtree = new TreeControlNode("catalogIDs/" +
                                        cm.getCatalogID(),
                                        Config.getContextRoot() + "/images/fclosed.gif",
                                        cm.getName(), null, "content", false, domain);
                                root.addChild(subtree);
                                addSubtree(subtree, aspID, orgID);
                        }

                        //add master node to tree
                        for (int i = 0; i < masterList.size(); i++)
                        {
                                MasterModel mm = (MasterModel) masterList.get(i);
                                TreeControlNode subtree = new TreeControlNode("masterIDs/" +
                                        mm.getMasterID(),
                                         Config.getContextRoot() + "/images/cert.gif",
                                        mm.getName(),
                                         Config.getContextRoot() +
                                                "/admin/course/master/viewMaster.jsp?masterID=" +
                                                mm.getMasterID(), null, false, domain);
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

                ArrayList masterList = new ArrayList();
                ArrayList catalogList = new ArrayList();
                MasterWebImpl mwi = new MasterWebImpl();

                MasterTreeModel mtm = null;
                //if(aspID != 0)
                mtm = mwi.getTree(catalogID, type, aspID, orgID, catalogType);

                /* else
              mtm = mwi.getTree(catalogID,type);*/
                try
                {
                        if (mtm != null)
                        {
                                MasterListModel masters = mtm.getMasterList();
                                CatalogListModel catalogs = mtm.getCatalogList();

                                masterList = masters.getList();
                                catalogList = catalogs.getList();

                                //add cat node to tree
                                for (int i = 0; i < catalogList.size(); i++)
                                {
                                        CatalogModel cm = (CatalogModel) catalogList.get(i);
                                        TreeControlNode subtree = new TreeControlNode("catalogIDs/" +
                                                cm.getCatalogID(),
                                                Config.getContextRoot() +
                                                        "/images/fclosed.gif", cm.getName(), null,
                                                "content", false, domain);
                                        root.addChild(subtree);
                                        addSubtree(subtree, aspID, orgID);
                                }

                                //add master node to tree
                                for (int i = 0; i < masterList.size(); i++)
                                {
                                        MasterModel mm = (MasterModel) masterList.get(i);
                                        TreeControlNode subtree = new TreeControlNode("masterIDs/" +
                                                mm.getMasterID(),
                                                Config.getContextRoot() + "/images/cert.gif",
                                                mm.getName(),
                                                Config.getContextRoot() +
                                                        "/admin/course/master/viewMaster.jsp?masterID=" +
                                                        mm.getMasterID(), null, false, domain);
                                        root.addChild(subtree);
                                }
                        }
                }
                catch (CourseSysException e)
                {
                        throw e;
                }
        }
}
