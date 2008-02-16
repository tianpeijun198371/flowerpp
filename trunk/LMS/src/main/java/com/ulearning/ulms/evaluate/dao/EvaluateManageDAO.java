/**
 * EvaluateManageDAO.java.
 * User: fengch Date: 2005-6-10 17:08:16
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.evaluate.dao;

import com.ulearning.ulms.evaluate.exceptions.EvaluateManageSysException;
import com.ulearning.ulms.evaluate.model.*;

import java.util.Date;
import java.util.List;


public interface EvaluateManageDAO
{
        /**
         * add a Access.
         *
         * @param am
         * @throws EvaluateManageSysException
         */
        public void addAccess(EAccessModel am) throws EvaluateManageSysException;

        /**
         * check Accord sort
         * sort:0--all;1--year;2--month;3--day;4--hour;5--userid;
         *
         * @param sort
         * @param num
         * @return
         * @throws EvaluateManageSysException
         */
        public List statAccordSort(int sort, int num)
                throws EvaluateManageSysException;

        /**
         * get min year
         *
         * @return
         * @throws EvaluateManageSysException
         */
        public List getMinYear() throws EvaluateManageSysException;

        /**
         * add staytime
         *
         * @param bm
         * @throws EvaluateManageSysException
         */
        public void addStayTime(StayTimeModel bm) throws EvaluateManageSysException;

        /**
         * update staytime
         *
         * @param bm
         * @throws EvaluateManageSysException
         */
        public void updateStayTime(StayTimeModel bm)
                throws EvaluateManageSysException;

        /**
         * get list of rankname
         *
         * @return
         * @throws EvaluateManageSysException
         */
        public List getRankName() throws EvaluateManageSysException;

        /**
         * get rank point
         *
         * @return
         * @throws EvaluateManageSysException
         */
        public List getRankPoint() throws EvaluateManageSysException;

        /**
         * get list by orgID(moduleID)
         *
         * @param orgID
         * @return
         * @throws EvaluateManageSysException
         */
        public List getByModuleID(int orgID) throws EvaluateManageSysException;

        /**
         * get list of point conversion
         *
         * @return
         * @throws EvaluateManageSysException
         */
        public List getPointConversion() throws EvaluateManageSysException;

        /**
         * check--is there this userID
         *
         * @param userID
         * @return
         * @throws EvaluateManageSysException
         */
        public List getIsHaveUser(int userID) throws EvaluateManageSysException;

        /**
         * add a ERecord.
         *
         * @param em
         * @throws EvaluateManageSysException
         */
        public void addERecord(ERecordModel em) throws EvaluateManageSysException;

        /**
         * get list by point
         *
         * @return
         * @throws EvaluateManageSysException
         */
        public List getERecord() throws EvaluateManageSysException;

        /**
         * update a ERecord.
         *
         * @param em
         * @throws EvaluateManageSysException
         */
        public void updateERecord(ERecordModel em)
                throws EvaluateManageSysException;

        /**
         * is this userID in ERecord
         *
         * @param userID
         * @return
         * @throws EvaluateManageSysException
         */
        public List isThisUserInERecord(int userID)
                throws EvaluateManageSysException;

        /**
         * update RankStandard of user
         *
         * @param em
         * @throws EvaluateManageSysException
         */
        public void updateERecordRankStandard(ERecordRankStandardModel em)
                throws EvaluateManageSysException;

        /**
         * update PointConversion
         *
         * @param em
         * @throws EvaluateManageSysException
         */
        public void updateERecordPointConversion(ERecordPointConversionModel em)
                throws EvaluateManageSysException;

        /**
         * @param em
         * @throws EvaluateManageSysException
         */
        public void addFeedBack(FeedBackModel em) throws EvaluateManageSysException;
}
