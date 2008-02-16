/**
 * AccessDAO.java.
 * User: fengch  Date: 2004-4-26
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.tools.access.dao;

import com.ulearning.ulms.tools.access.exceptions.AccessSysException;
import com.ulearning.ulms.tools.access.model.Access;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public interface AccessDAO
{
        /**
         * publish a new access
         *
         * @param access the new access
         * @throws AccessSysException If an lmsSys error has occurred.
         */
        public Serializable insert(Access access) throws AccessSysException;

        public void update(Access access) throws AccessSysException;

        /**
         * delete some accesss
         *
         * @param accessIDs IDs of access
         * @throws AccessSysException If an lmsSys error has occurred.
         */
        public void delete(ArrayList accessIDs) throws AccessSysException;

        /**
         * 根据courseID删除访问记录
         *
         * @param courseID
         * @throws AccessSysException
         */
        public void deleteByCourseID(int courseID) throws AccessSysException;

        public void logoutUpdate(int userID) throws AccessSysException;

        /**
         * update a access
         *
         * @param userTime
         * @throws AccessSysException If an lmsSys error has occurred.
         */

        //public void updateUserTime(int AccessID,double userTime)  throws AccessSysException;

        /**
         * get a piece of information
         *
         * @param accessID user's ID
         * @return a string contains user's basic email information
         * @throws com.ulearning.ulms.tools.access.exceptions.AccessSysException
         *          If an ulmsSys error has occurred.
         */
        public Access get(int accessID) throws AccessSysException;

        /**
         * get all access according to type,and return one page
         *
         * @param UserID   UserID ID
         * @param ModuleID the ModuleID of access
         * @param pageNo   number of returned page
         * @param pageSize size of returned page
         * @return a model contains all accesss
         * @throws AccessSysException If an lmsSys error has occurred.
         */
        public List getAccessList(int UserID, int ModuleID, int CourseID,
                                  int CertificateID, int ProjectID, int OrgID, int pageNo, int pageSize)
                throws AccessSysException;

        public List getAccessListGroupUserID(String userIDStr, int ModuleID,
                                             int CourseID, int CertificateID, int ProjectID, int OrgID,
                                             String startDateTime, String endDateTime, int firstResult,
                                             int maxResults) throws AccessSysException;

        public List getAccessListGroupUserIDisSub(String userIDStr, int ModuleID,
                                                  int CourseID, int CertificateID, int ProjectID, int OrgID,
                                                  String startDateTime, String endDateTime, int firstResult,
                                                  int maxResults, String isSub) throws AccessSysException;

        public int getAccessCount(int UserID, int ModuleID, int CourseID,
                                  int CertificateID, int ProjectID, int OrgID) throws AccessSysException;

        public int getAccessCount(int UserID, int ModuleID, int CourseID,
                                  int CertificateID, int ProjectID, int OrgID, Date startDate,
                                  Date endDate) throws AccessSysException;

        //public int  getLastAccessID(int userID) throws AccessSysException  ;
}
