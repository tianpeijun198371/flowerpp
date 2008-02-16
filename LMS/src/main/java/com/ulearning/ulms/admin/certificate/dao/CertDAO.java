/**
 * CertDAO.java.
 * User: huangsb  Date: 2004-4-26
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd. * All rights reserved.
 */
package com.ulearning.ulms.admin.certificate.dao;

import com.ulearning.ulms.admin.certificate.exceptions.CertDAOSysException;
import com.ulearning.ulms.admin.certificate.form.CertCourseForm;
import com.ulearning.ulms.admin.certificate.form.CertForm;
import com.ulearning.ulms.admin.certificate.form.CertTreeForm;
import com.ulearning.ulms.admin.certificate.model.CertCurrentStatus;
import com.ulearning.ulms.course.model.CatalogModel;

import java.util.List;


public interface CertDAO
{
        /**
         * @param catalogID
         * @throws CertDAOSysException
         */
        public CertTreeForm getTree(int catalogID) throws CertDAOSysException;

        /**
         * @param catalogID
         * @param aspID
         * @return
         * @throws CertDAOSysException
         */
        public CertTreeForm getTree(int catalogID, int aspID, int orgID)
                throws CertDAOSysException;

        /**
         * @param catalogID
         * @throws CertDAOSysException
         */
        public CatalogModel getCatalog(int catalogID) throws CertDAOSysException;

        /**
         * @param values
         * @throws CertDAOSysException
         */
        public void deleteCert(List values) throws CertDAOSysException;

        /**
         * @param values
         * @throws CertDAOSysException
         */
        public void deleteCatalog(List values) throws CertDAOSysException;

        /**
         * @param value
         * @throws CertDAOSysException
         */
        public void updateCatalog(CatalogModel value) throws CertDAOSysException;

        /**
         * @param value
         * @throws CertDAOSysException
         */
        public void createCatalog(CatalogModel value) throws CertDAOSysException;

        /**
         * @param catalogID
         * @throws CertDAOSysException
         */
        public List getCatalogPath(int catalogID) throws CertDAOSysException;

        public List getCatalogPath(int catalogID, int aspID, int orgID)
                throws CertDAOSysException;

        public int addCert(CertForm details) throws CertDAOSysException;

        public void updateCert(CertForm details) throws CertDAOSysException;

        public void removeCert(String certificateID) throws CertDAOSysException;

        public CertForm getCert(int certificateID) throws CertDAOSysException;

        /**
         * 根据证书编号返回 CertForm . <br>
         *
         * @param certCode
         * @return
         * @throws CertDAOSysException
         */
        public CertForm getCert(int aspID, int orgID, String certCode)
                throws CertDAOSysException;

        public int getCertID(String certCode, int aspID, int orgID)
                throws CertDAOSysException;

        public List getCertList(int catalogID) throws CertDAOSysException;

        public List getCertList(int catalogID, int aspID, int orgID)
                throws CertDAOSysException;

        public List getMyStudyingCertList(int userID) throws CertDAOSysException;

        public List getMymanagingCertList(int userID) throws CertDAOSysException;

        public List search(String certKey) throws CertDAOSysException;

        public List search(String certKey, int aspID, int orgID)
                throws CertDAOSysException;

        public void addCourseToCert(CertCourseForm ccf) throws CertDAOSysException;

        public void updateCertCourse(CertCourseForm ccf) throws CertDAOSysException;

        public void removeCourseFromCert(CertCourseForm ccf)
                throws CertDAOSysException;

        public void removeCourseFormCert(int courseID) throws CertDAOSysException;

        public List getMyCertList(int userID) throws CertDAOSysException;

        public List getNotMyCertList(int userID) throws CertDAOSysException;

        public List getCourseListFromCert(String certificateID, int type)
                throws CertDAOSysException;

        public boolean isCourseAlone(int courseID) throws CertDAOSysException;

        public int getAspTotalCert(int aspID, int orgID, String startDate,
                                   String endDate) throws CertDAOSysException;

        public List getAspCertInfo(int aspID, int orgID, String startDate,
                                   String endDate) throws CertDAOSysException;

        public CertCurrentStatus getCertNowStatus(int certificateID, int type)
                throws CertDAOSysException;

        public int getPublishCourseNum(int certificateID)
                throws CertDAOSysException;

        public int getClassIDByCertID(int certificateID) throws CertDAOSysException;

        public List getAllCertList(int aspID, int orgID) throws CertDAOSysException;

        //得到masterid下的全部班级
        public List getCertByMasterID(int masterID) throws CertDAOSysException;

        //得到培训班中正在学习，和已经完成学习的人员
        public List getCertStudayingUser(int relationid) throws CertDAOSysException;
}
