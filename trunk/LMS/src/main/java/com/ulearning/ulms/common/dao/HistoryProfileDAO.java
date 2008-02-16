/**
 * HistoryProfileDAO.java.
 * User: fengch  Date: 2004-4-27
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.common.dao;

import com.ulearning.ulms.common.exceptions.HistoryProfileSysException;
import com.ulearning.ulms.common.model.HistoryProfileListModel;
import com.ulearning.ulms.common.model.HistoryProfileModel;

import java.util.Date;


public interface HistoryProfileDAO
{
        /**
         * get  the user-relation profiles
         * type
         * ==0:�γ̼�¼
         * ==1:֤���¼
         * ==2:��Ŀ��¼
         *
         * @throws HistoryProfileSysException
         */
        public HistoryProfileListModel get(int userID, int type, int pageNo,
                                           int pageSize) throws HistoryProfileSysException;

        /**
         * get  the user-relationed profile
         * type  :see SecurityConstants.
         *
         * @throws com.ulearning.ulms.common.exceptions.HistoryProfileSysException
         *
         */
        public HistoryProfileModel get(int userID, int relationID, int type)
                throws HistoryProfileSysException;

        /**
         * get  the user-relationed profile
         * type  :see SecurityConstants.
         *
         * @throws com.ulearning.ulms.common.exceptions.HistoryProfileSysException
         *
         */
        public HistoryProfileListModel get(int userID, int type,
                                           java.util.Date StartDate, java.util.Date endDate)
                throws HistoryProfileSysException;

        /**
         * @param userID
         * @param type
         * @param StartDate
         * @param endDate
         * @return
         * @throws HistoryProfileSysException
         */
        public HistoryProfileListModel getByShowConditon(int userID, int type,
                                                         String StartDate, String endDate) throws HistoryProfileSysException;

        /**
         * update  a profile.
         *
         * @throws HistoryProfileSysException
         */
        public void update(HistoryProfileModel hpm)
                throws HistoryProfileSysException;

        /**
         * update  a profile' state,enrollmentDate,completionDate.
         *
         * @throws com.ulearning.ulms.common.exceptions.HistoryProfileSysException
         *
         */
        public void update(int userID, int relationID, int type, int state,
                           Date enrollmentDate, Date completionDate)
                throws HistoryProfileSysException;

        /**
         * update  a profile' state.
         * �������ڣ�����insert.
         *
         * @throws com.ulearning.ulms.common.exceptions.HistoryProfileSysException
         *
         */
        public void update(int userID, int relationID, int type, int state)
                throws HistoryProfileSysException;

        /**
         * insert a profile.
         *
         * @throws HistoryProfileSysException
         */
        public void insert(HistoryProfileModel hpm)
                throws HistoryProfileSysException;

        /**
         * delete a profile
         *
         * @throws HistoryProfileSysException
         */
        public void delete(int profileID) throws HistoryProfileSysException;

        /**
         * �����ض�asp���ض����ڣ��·ݣ�����ѵ�˴�
         *
         * @param aspID
         * @param date
         * @return
         * @throws HistoryProfileSysException
         */
        public int getHistoryPersonalTimeReport(int aspID, Date date)
                throws HistoryProfileSysException;

        /**
         * �����ض�asp���ض����ڵ�����ѵ�˴�
         *
         * @param aspID
         * @param startDate
         * @param endDate
         * @return
         * @throws HistoryProfileSysException
         */
        public int getHistoryPersonalTimeReport(int aspID, Date startDate,
                                                Date endDate) throws HistoryProfileSysException;

        /**
         * �����û�����ѧ��.<br>
         *
         * @param userID
         * @param startDate
         * @param endDate
         * @return
         */
        public float getUserTotalPeriod(int userID, Date startDate, Date endDate)
                throws HistoryProfileSysException;

        public float getUserTotalPassPeriod(int userID, Date startDate, Date endDate)
                throws HistoryProfileSysException;
}
