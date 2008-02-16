/**
 * CertWebImpl.java.
 * User: huangsb  Date: 2004-5-20
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd. * All rights reserved.
 */
package com.ulearning.ulms.admin.certificate.webimpls;

import com.ulearning.ulms.admin.certificate.dao.CertDAO;
import com.ulearning.ulms.admin.certificate.dao.CertDAOFactory;
import com.ulearning.ulms.admin.certificate.exceptions.CertDAOSysException;
import com.ulearning.ulms.admin.certificate.form.CertCourseForm;
import com.ulearning.ulms.admin.certificate.form.CertForm;
import com.ulearning.ulms.admin.certificate.form.CertTreeForm;
import com.ulearning.ulms.admin.certificate.model.CertCurrentStatus;
import com.ulearning.ulms.course.model.CatalogModel;

import java.util.List;


public class CertWebImpl
{
        private CertDAO dao;

        public CertWebImpl()
        {
                try
                {
                        dao = CertDAOFactory.getDAO();
                }
                catch (Exception ex)
                {
                        ex.printStackTrace();
                }
        }

        /**
         * @param catalogID
         * @return
         * @throws CertDAOSysException
         */
        public List getCertList(int catalogID) throws CertDAOSysException
        {
                System.out.println("CertWebImpl.getCertList(int catalogID)");

                List list = dao.getCertList(catalogID);

                return list;
        }

        public List getCertList(int catalogID, int aspID)
                throws CertDAOSysException
        {
                System.out.println("CertWebImpl.getCertList(int catalogID)");

                List list = dao.getCertList(catalogID, aspID, -1);

                return list;
        }

        public List getCertList(int catalogID, int aspID, int orgID)
                throws CertDAOSysException
        {
                System.out.println("CertWebImpl.getCertList(int catalogID)");

                List list = dao.getCertList(catalogID, aspID, orgID);

                return list;
        }

        /**
         * @param ccf
         * @throws CertDAOSysException
         */
        public void addCourseToCert(CertCourseForm ccf) throws CertDAOSysException
        {
                dao.addCourseToCert(ccf);
        }

        /**
         * @param ccf
         * @throws CertDAOSysException
         */
        public void removeCourseFromCert(CertCourseForm ccf)
                throws CertDAOSysException
        {
                dao.removeCourseFromCert(ccf);
        }

        /**
         * @param certificateID
         * @return
         * @throws CertDAOSysException
         */
        public List getCourseListFromCert(String certificateID, int type)
                throws CertDAOSysException
        {
                List list = dao.getCourseListFromCert(certificateID, type);

                return list;
        }

        /**
         * @param value
         * @throws CertDAOSysException
         */
        public void createCatalog(CatalogModel value) throws CertDAOSysException
        {
                dao.createCatalog(value);
        }

        /**
         * @param value
         * @throws CertDAOSysException
         */
        public void addCert(CertForm value) throws CertDAOSysException
        {
                dao.addCert(value);
        }

        /**
         * @param values
         * @throws CertDAOSysException
         */
        public void deleteCatalog(List values) throws CertDAOSysException
        {
                dao.deleteCatalog(values);
        }

        /**
         * @param certificateID
         * @throws CertDAOSysException
         */
        public void removeCert(String certificateID) throws CertDAOSysException
        {
                dao.removeCert(certificateID);
        }

        /**
         * @param values
         * @throws CertDAOSysException
         */
        public void deleteCert(List values) throws CertDAOSysException
        {
                dao.deleteCert(values);
        }

        /**
         * @param catalogID
         * @throws CertDAOSysException
         */
        public CatalogModel getCatalog(int catalogID) throws CertDAOSysException
        {
                CatalogModel cat;

                cat = dao.getCatalog(catalogID);

                return cat;
        }

        /**
         * @param catalogID
         * @throws CertDAOSysException
         */
        public List getCatalogPath(int catalogID) throws CertDAOSysException
        {
                List l;

                l = dao.getCatalogPath(catalogID);

                return l;
        }

        public List getCatalogPath(int catalogID, int aspID)
                throws CertDAOSysException
        {
                List l;

                l = dao.getCatalogPath(catalogID, aspID, -1);

                return l;
        }

        public List getCatalogPath(int catalogID, int aspID, int orgID)
                throws CertDAOSysException
        {
                List l;

                l = dao.getCatalogPath(catalogID, aspID, orgID);

                return l;
        }

        public CertForm getCert(int masterID) throws CertDAOSysException
        {
                CertForm l;

                l = dao.getCert(masterID);

                return l;
        }

        /**
         * @param catalogID
         * @throws CertDAOSysException
         */
        public CertTreeForm getTree(int catalogID) throws CertDAOSysException
        {
                CertTreeForm l;

                l = dao.getTree(catalogID);

                return l;
        }

        /**
         * @param catalogID
         * @throws CertDAOSysException
         */
        public CertTreeForm getTree(int catalogID, int aspID)
                throws CertDAOSysException
        {
                CertTreeForm l;

                l = dao.getTree(catalogID, aspID, -1);

                return l;
        }

        public CertTreeForm getTree(int catalogID, int aspID, int orgID)
                throws CertDAOSysException
        {
                CertTreeForm l;

                l = dao.getTree(catalogID, aspID, orgID);

                return l;
        }

        /**
         * @param value
         * @throws CertDAOSysException
         */
        public void updateCatalog(CatalogModel value) throws CertDAOSysException
        {
                dao.updateCatalog(value);
        }

        /**
         * @param value
         * @throws CertDAOSysException
         */
        public void updateCert(CertForm value) throws CertDAOSysException
        {
                dao.updateCert(value);
        }

        public CertCurrentStatus getCertNowStatus(int certificateID, int type)
                throws CertDAOSysException
        {
                return dao.getCertNowStatus(certificateID, type);
        }

        public int getPublishCourseNum(int certificateID)
                throws CertDAOSysException
        {
                return dao.getPublishCourseNum(certificateID);
        }
}
