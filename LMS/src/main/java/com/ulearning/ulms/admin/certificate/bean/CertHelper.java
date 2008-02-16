/**
 * CertHelper.java.
 * User: huangsb  Date: 2004-4-26
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd. * All rights reserved.
 */
package com.ulearning.ulms.admin.certificate.bean;

import com.ulearning.ulms.admin.certificate.dao.CertDAO;
import com.ulearning.ulms.admin.certificate.dao.CertDAOFactory;
import com.ulearning.ulms.admin.certificate.exceptions.CertDAOSysException;
import com.ulearning.ulms.admin.certificate.form.CertForm;
import com.ulearning.ulms.admin.certificate.model.CertCurrentStatus;
import com.ulearning.ulms.core.exceptions.ULMSSysException;
import com.ulearning.ulms.core.security.bean.SecurityConstants;
import com.ulearning.ulms.course.courseconfig.bean.CreditCourseWiseHelper;
import com.ulearning.ulms.course.courseconfig.bean.GradeWeightingFactorHelper;

import java.util.ArrayList;
import java.util.List;


public class CertHelper
{
        /**
         * Wrapping the get Cert method for JSP and  the other modules
         *
         * @param certificateID
         * @return the Cert modle according to the certificateID
         */
        public static CertForm getCert(int certificateID)
        {
                CertForm cf = null;

                try
                {
                        CertDAO bookDao = CertDAOFactory.getDAO();
                        cf = bookDao.getCert(certificateID);
                }
                catch (CertDAOSysException udse)
                {
                        udse.printStackTrace();
                }

                return cf;
        }

        /**
         * Wrapping the get certList method for JSP and  the other modules
         *
         * @param catalogID
         * @return the Cert list according to the catalog
         */
        public static List getCertList(int catalogID)
        {
                List certList = null;

                try
                {
                        CertDAO CertDao = CertDAOFactory.getDAO();
                        certList = CertDao.getCertList(catalogID);
                }
                catch (CertDAOSysException cdse)
                {
                        cdse.printStackTrace();
                }

                return certList;
        }

        /**
         * Wrapping the get certList method for JSP and  the other modules
         *
         * @param catalogID
         * @param aspID
         * @return
         */
        public static List getCertList(int catalogID, int aspID)
        {
                List certList = new ArrayList();

                try
                {
                        CertDAO CertDao = CertDAOFactory.getDAO();
                        certList = CertDao.getCertList(catalogID, aspID, 0);
                }
                catch (CertDAOSysException cdse)
                {
                        cdse.printStackTrace();
                }

                return certList;
        }

        public static List getCertList(int catalogID, int aspID, int orgID)
        {
                List certList = new ArrayList();

                try
                {
                        CertDAO CertDao = CertDAOFactory.getDAO();
                        certList = CertDao.getCertList(catalogID, aspID, orgID);
                }
                catch (CertDAOSysException cdse)
                {
                        cdse.printStackTrace();
                }

                return certList;
        }

        /**
         * Wrapping the search cert method ,search the cert according to the cod,name and key
         *
         * @param certKey
         * @return the Cert list according to the ket,default return null
         */
        public static List search(String certKey)
        {
                List certList = null;

                try
                {
                        CertDAO CertDao = CertDAOFactory.getDAO();
                        certList = CertDao.search(certKey);
                }
                catch (CertDAOSysException cdse)
                {
                        cdse.printStackTrace();
                }

                return certList;
        }

        public static List search(String certKey, int aspID)
        {
                List certList = null;

                try
                {
                        CertDAO CertDao = CertDAOFactory.getDAO();
                        certList = CertDao.search(certKey, aspID, 0);
                }
                catch (CertDAOSysException cdse)
                {
                        cdse.printStackTrace();
                }

                return certList;
        }

        public static List search(String certKey, int aspID, int orgID)
        {
                List certList = null;

                try
                {
                        CertDAO CertDao = CertDAOFactory.getDAO();
                        certList = CertDao.search(certKey, aspID, orgID);
                }
                catch (CertDAOSysException cdse)
                {
                        cdse.printStackTrace();
                }

                return certList;
        }

        public static List getCourseListFromCert(String certificateID, int type)
        {
                List courseList = null;

                try
                {
                        CertDAO CertDao = CertDAOFactory.getDAO();
                        courseList = CertDao.getCourseListFromCert(certificateID, type);
                }
                catch (CertDAOSysException cdse)
                {
                        cdse.printStackTrace();
                }

                return courseList;
        }

        /**
         * Get Certificate ID according to the code
         *
         * @param certCode
         * @return the certificateID ,default is 0
         */
        public static int getCertID(String certCode, int aspID)
        {
                int certID = 0;

                try
                {
                        CertDAO CertDao = CertDAOFactory.getDAO();
                        certID = CertDao.getCertID(certCode, aspID, 0);
                }
                catch (CertDAOSysException cdse)
                {
                        cdse.printStackTrace();
                }

                return certID;
        }

        public static int getCertID(String certCode, int aspID, int orgID)
        {
                int certID = 0;

                try
                {
                        CertDAO CertDao = CertDAOFactory.getDAO();
                        certID = CertDao.getCertID(certCode, aspID, orgID);
                }
                catch (CertDAOSysException cdse)
                {
                        cdse.printStackTrace();
                }

                return certID;
        }

        /**
         * Get all the studying certificate list according to the userID
         *
         * @param userID
         * @return prepared certList ,default is null
         */
        public static List getMyStudyingCertList(int userID)
        {
                List certList = null;

                try
                {
                        CertDAO CertDao = CertDAOFactory.getDAO();
                        certList = CertDao.getMyStudyingCertList(userID);
                }
                catch (CertDAOSysException cdse)
                {
                        cdse.printStackTrace();
                }

                return certList;
        }

        /**
         * Get all the managing certificate list according to the userID
         *
         * @param userID
         * @return prepared certList ,default is null
         */
        public static List getMymanagingCertList(int userID)
        {
                List certList = null;

                try
                {
                        CertDAO CertDao = CertDAOFactory.getDAO();
                        certList = CertDao.getMymanagingCertList(userID);
                }
                catch (CertDAOSysException cdse)
                {
                        cdse.printStackTrace();
                }

                return certList;
        }

        /**
         * Get all the managing certificate list according to the userID
         *
         * @param userID
         * @return prepared certList ,default is null
         */
        public static List getMyCertList(int userID)
        {
                List certList = null;

                try
                {
                        CertDAO CertDao = CertDAOFactory.getDAO();
                        certList = CertDao.getMyCertList(userID);
                }
                catch (CertDAOSysException cdse)
                {
                        cdse.printStackTrace();
                }

                return certList;
        }

        public static List getNotMyCertList(int userID)
        {
                List certList = null;

                try
                {
                        CertDAO CertDao = CertDAOFactory.getDAO();
                        certList = CertDao.getNotMyCertList(userID);
                }
                catch (CertDAOSysException cdse)
                {
                        cdse.printStackTrace();
                }

                return certList;
        }

        public static int getAspTotalCert(int aspID, String startDate,
                                          String endDate)
        {
                int total = 0;

                try
                {
                        CertDAO CertDao = CertDAOFactory.getDAO();
                        total = CertDao.getAspTotalCert(aspID, 0, startDate, endDate);
                }
                catch (CertDAOSysException cdse)
                {
                        cdse.printStackTrace();
                }

                return total;
        }

        public static int getAspTotalCert(int aspID, int orgID, String startDate,
                                          String endDate)
        {
                int total = 0;

                try
                {
                        CertDAO CertDao = CertDAOFactory.getDAO();
                        total = CertDao.getAspTotalCert(aspID, orgID, startDate, endDate);
                }
                catch (CertDAOSysException cdse)
                {
                        cdse.printStackTrace();
                }

                return total;
        }

        public static List getAspCertInfo(int aspID, String startDate,
                                          String endDate)
        {
                List certList = null;

                try
                {
                        CertDAO CertDao = CertDAOFactory.getDAO();
                        certList = CertDao.getAspCertInfo(aspID, 0, startDate, endDate);
                }
                catch (CertDAOSysException cdse)
                {
                        cdse.printStackTrace();
                }

                return certList;
        }

        public static List getAspCertInfo(int aspID, int orgID, String startDate,
                                          String endDate)
        {
                List certList = null;

                try
                {
                        CertDAO CertDao = CertDAOFactory.getDAO();
                        certList = CertDao.getAspCertInfo(aspID, orgID, startDate, endDate);
                }
                catch (CertDAOSysException cdse)
                {
                        cdse.printStackTrace();
                }

                return certList;
        }

        public static CertCurrentStatus getCertNowStatus(int certificateID, int type)
        {
                CertCurrentStatus ccs = new CertCurrentStatus();

                try
                {
                        CertDAO CertDao = CertDAOFactory.getDAO();
                        ccs = CertDao.getCertNowStatus(certificateID, type); //SecurityConstants.USER_COURSE_RELATION);
                }
                catch (CertDAOSysException cdse)
                {
                        cdse.printStackTrace();
                }

                return ccs;
        }

        public static void deletedRelationData(int certID)
                throws ULMSSysException
        {
                GradeWeightingFactorHelper.delete(SecurityConstants.USER_CERTIFICATE_RELATION,
                        certID);
                CreditCourseWiseHelper.delete(SecurityConstants.USER_CERTIFICATE_RELATION,
                        certID);
        }

        public static int getClassIDByCertID(int certificatID)
        {
                int returnID = -1;

                try
                {
                        CertDAO CertDao = CertDAOFactory.getDAO();
                        returnID = CertDao.getClassIDByCertID(certificatID);
                }
                catch (CertDAOSysException cdse)
                {
                        cdse.printStackTrace();
                }

                return returnID;
        }

        public static List getAllCertList(int aspID, int orgID)
                throws CertDAOSysException
        {
                List lt = null;

                try
                {
                        CertDAO CertDao = CertDAOFactory.getDAO();
                        lt = CertDao.getAllCertList(aspID, orgID);
                }
                catch (CertDAOSysException cdse)
                {
                        cdse.printStackTrace();
                }

                return lt;
        }

        public static List getCertByMasterID(int masterID)
                throws CertDAOSysException
        {
                List lt = null;

                try
                {
                        CertDAO CertDao = CertDAOFactory.getDAO();
                        lt = CertDao.getCertByMasterID(masterID);
                }
                catch (CertDAOSysException cdse)
                {
                        cdse.printStackTrace();
                }

                return lt;
        }

        public static List getCertStudayingUser(int relationid)
                throws CertDAOSysException
        {
                List lt = null;

                try
                {
                        CertDAO CertDao = CertDAOFactory.getDAO();
                        lt = CertDao.getCertStudayingUser(relationid);
                }
                catch (CertDAOSysException cdse)
                {
                        cdse.printStackTrace();
                }

                return lt;
        }
}
