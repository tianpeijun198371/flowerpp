/**
 * Created by IntelliJ IDEA.
 * User: zengwj
 * Date: 2004-7-21
 * Time: 17:50:45
 * To change this template use File | Settings | File Templates.
 */
package com.ulearning.ulms.course.util;

import com.ulearning.ulms.admin.certificate.webimpls.CertWebImpl;
import com.ulearning.ulms.core.security.bean.SecurityConstants;
import com.ulearning.ulms.course.exceptions.CourseSysException;
import com.ulearning.ulms.course.model.CatalogListModel;
import com.ulearning.ulms.course.model.MasterListModel;
import com.ulearning.ulms.course.model.MasterTreeModel;
import com.ulearning.ulms.course.webimpls.MasterWebImpl;
import com.ulearning.ulms.util.treeControl.UlmsTreeBuilder;

import org.apache.webapp.admin.ApplicationServlet;
import org.apache.webapp.admin.TreeControl;
import org.apache.webapp.admin.TreeControlNode;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;


public class CertCourseTreeBuilder implements UlmsTreeBuilder
{
        private String domain = "Cert";
        private int type = SecurityConstants.USER_COURSE_RELATION;

        public void buildTree(TreeControl treeControl,
                              ApplicationServlet applicationServlet,
                              HttpServletRequest httpServletRequest)
        {
                buildTree(treeControl);
        }

        public void buildTree(TreeControl treeControl)
        {
                try
                {
                        TreeControlNode root = treeControl.getRoot();
                        int catalogID = 0;

                        ArrayList masterList = new ArrayList();
                        ArrayList catalogList = new ArrayList();

                        MasterWebImpl mwi = new MasterWebImpl();
                        MasterTreeModel mtm = mwi.getTree(catalogID, type);

                        if (mtm != null)
                        {
                                MasterListModel masters = mtm.getMasterList();
                                CatalogListModel catalogs = mtm.getCatalogList();

                                masterList = masters.getList();
                                catalogList = catalogs.getList();
                        }

                        CertWebImpl certWebImpl = new CertWebImpl();

                        //List allMasterInCertList = certWebImpl.getCourseListFromCert(masterIDs,type);
                }
                catch (CourseSysException e)
                {
                        System.out.println(e);
                }
        }
}
