/**
 * AccessWebImpl.java.
 * User: fengch  Date: 2004-4-28
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.tools.access.webimpls;

import com.ulearning.ulms.tools.access.dao.AccessDAO;
import com.ulearning.ulms.tools.access.dao.AccessDAOFactory;
import com.ulearning.ulms.tools.access.exceptions.AccessSysException;
import com.ulearning.ulms.tools.access.model.Access;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class AccessWebImpl
{
        private AccessDAO dao;

        public AccessWebImpl() throws AccessSysException
        {
                try
                {
                        dao = AccessDAOFactory.getDAO();
                }
                catch (Exception ex)
                {
                        ex.printStackTrace();
                        throw new AccessSysException(ex);
                }
        }

        /**
         * get a piece of information
         *
         * @param accessID
         * @return a string contains user's basic email information
         * @throws com.ulearning.ulms.tools.access.exceptions.AccessSysException
         *          If an ulmsSys error has occurred.
         */
        public Access get(int accessID) throws AccessSysException
        {
                Access acm;

                acm = dao.get(accessID);

                return acm;
        }

        /**
         * get all time all the userID-related accesss according to the varible passed in
         * param   getType: ==0:get all the userID-related accesss
         * ==1:get all the platform accesss
         * ==2:get all the userID-related course accesss
         * ==3:get all the userID-related certificate accesss
         *
         * @param UserID   user ID
         * @param pageNo   number of returned page
         * @param pageSize size of returned page
         * @param ModuleID
         * @return a model contains all accesss releated in past N days
         * @throws com.ulearning.ulms.tools.access.exceptions.AccessSysException
         *          If an lmsSys error has occurred.
         */
        public List getAccessList(int UserID, int ModuleID, int CourseID,
                                  int CertificateID, int ProjectID, int OrgID, int pageNo, int pageSize)
                throws AccessSysException
        {
                List acm;
                acm = dao.getAccessList(UserID, ModuleID, CourseID, CertificateID,
                        ProjectID, OrgID, pageNo, pageSize);

                return acm;
        }

        public List getAccessListGroupUserID(String userIDStr, int ModuleID,
                                             int CourseID, int CertificateID, int ProjectID, int OrgID,
                                             String startDateTime, String endDateTime, int firstResult,
                                             int maxResults) throws AccessSysException
        {
                List acm = dao.getAccessListGroupUserID(userIDStr, ModuleID, CourseID,
                        CertificateID, ProjectID, OrgID, startDateTime, endDateTime,
                        firstResult, maxResults);

                return acm;
        }

        public List getAccessListGroupUserIDisSub(String userIDStr, int ModuleID,
                                                  int CourseID, int CertificateID, int ProjectID, int OrgID,
                                                  String startDateTime, String endDateTime, int firstResult,
                                                  int maxResults, String isCatalog) throws AccessSysException
        {
                List acm = dao.getAccessListGroupUserIDisSub(userIDStr, ModuleID,
                        CourseID, CertificateID, ProjectID, OrgID, startDateTime,
                        endDateTime, firstResult, maxResults, isCatalog);

                return acm;
        }

        public int getAccessCount(int UserID, int ModuleID, int CourseID,
                                  int CertificateID, int ProjectID, int OrgID) throws AccessSysException
        {
                int count = dao.getAccessCount(UserID, ModuleID, CourseID,
                        CertificateID, ProjectID, OrgID);

                return count;
        }

        public int getAccessCount(int UserID, int ModuleID, int CourseID,
                                  int CertificateID, int ProjectID, int OrgID, Date startDate,
                                  Date endDate) throws AccessSysException
        {
                int count = dao.getAccessCount(UserID, ModuleID, CourseID,
                        CertificateID, ProjectID, OrgID, startDate, endDate);

                return count;
        }

        /**
         * delete some accesss
         *
         * @param accessIDs IDs of access
         * @throws com.ulearning.ulms.tools.access.exceptions.AccessSysException
         *          If an lmsSys error has occurred.
         */
        public void delete(ArrayList accessIDs) throws AccessSysException
        {
                dao.delete(accessIDs);
        }

        /*
          public int getLastAccessID(int userID) throws AccessSysException
          {
                  return dao.getLastAccessID(userID);
          }
        */

        /**
         * publish a new access
         *
         * @param access the new access
         * @throws com.ulearning.ulms.tools.access.exceptions.AccessSysException
         *          If an lmsSys error has occurred.
         */
        public Serializable insert(Access access) throws AccessSysException
        {
                return dao.insert(access);
        }

        /**
         * update a access
         *
         * @param AccessID
         * @throws com.ulearning.ulms.tools.access.exceptions.AccessSysException
         *          If an lmsSys error has occurred.
         */
        public void updateUserTime(int AccessID, double userTime)
                throws AccessSysException
        {
                Access access = dao.get(AccessID);
                access.setUserTime(userTime);
                dao.update(access);
        }

        public void logoutUpdate(int userID) throws AccessSysException
        {
                dao.logoutUpdate(userID);
        }

        public int addCourseAccess(int userID, int moduleID, int courseID,
                                   int certificateID, int projectID, int orgID, String ip)
                throws AccessSysException
        {
                Access access = new Access();
                access.setUserID(userID);
                access.setModuleID(moduleID);
                access.setCourseID(courseID);
                access.setCertificateID(certificateID);
                access.setProjectID(projectID);
                access.setOrgID(orgID);
                access.setIp(ip);

                Serializable s = insert(access);

                return s.hashCode();
        }
}
