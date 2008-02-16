/**
 * EvaluatemanageDAOImpl.java.
 * User: YuD  Date: 2005-06-13
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.evaluate.dao;

import com.ulearning.ulms.core.exceptions.ULMSSysException;
import com.ulearning.ulms.evaluate.exceptions.EvaluateManageSysException;
import com.ulearning.ulms.evaluate.model.*;
import com.ulearning.ulms.util.HibernateDAO;

import java.util.Date;
import java.util.List;


public class EvaluateManageDAOSQLServerImpl implements EvaluateManageDAO
{
        /**
         * when user login, insert a new data
         *
         * @param am
         * @throws com.ulearning.ulms.evaluate.exceptions.EvaluateManageSysException
         *
         */
        public void addAccess(EAccessModel am) throws EvaluateManageSysException
        {
                try
                {
                        HibernateDAO.add(am);
                }
                catch (ULMSSysException e)
                {
                        throw new EvaluateManageSysException(e);
                }
        }

        /**
         * get min year
         *
         * @return
         * @throws com.ulearning.ulms.evaluate.exceptions.EvaluateManageSysException
         *
         */
        public List getMinYear() throws EvaluateManageSysException
        {
                String hql = "select pm.accessDate from EAccessModel pm order by pm.accessDate";
                List list = null;

                try
                {
                        list = HibernateDAO.find(hql);
                }
                catch (ULMSSysException e)
                {
                        throw new EvaluateManageSysException(e);
                }

                return list;
        }

        /**
         * stat accord sort
         *
         * @param sort
         * @param num
         * @return
         * @throws com.ulearning.ulms.evaluate.exceptions.EvaluateManageSysException
         *
         */
        public List statAccordSort(int sort, int num)
                throws EvaluateManageSysException
        {
                String hql = null;
                List list = null;

                if (sort == 0)
                {
                        hql = " from EAccessModel";
                }
                else if (sort == 1)
                {
                        hql = " from EAccessModel where YEAR(accessDate) =" + num;
                }
                else if (sort == 2)
                {
                        hql = " from EAccessModel where MONTH(accessDate) =" + num;
                }
                else if (sort == 3)
                {
                        hql = " from EAccessModel where DAY(accessDate) =" + num;
                }
                else if (sort == 4)
                {
                        hql = " from EAccessModel where HOUR(accessDate) =" + num;
                }
                else if (sort == 5)
                {
                        hql = "select pm.userID,count(pm.accessID) from EAccessModel pm group by pm.userID order by count(pm.accessID) desc";
                }

                try
                {
                        list = HibernateDAO.find(hql);
                }
                catch (ULMSSysException e)
                {
                        throw new EvaluateManageSysException(e);
                }

                return list;
        }

        /**
         * record staytime of user.if login first£¬then add a new data
         *
         * @param bm
         * @throws com.ulearning.ulms.evaluate.exceptions.EvaluateManageSysException
         *
         */
        public void addStayTime(StayTimeModel bm) throws EvaluateManageSysException
        {
                try
                {
                        HibernateDAO.add(bm);
                }
                catch (ULMSSysException e)
                {
                        throw new EvaluateManageSysException(e);
                }
        }

        /**
         * record staytime of user.if not login first,then update the exist data
         *
         * @param bm
         * @throws com.ulearning.ulms.evaluate.exceptions.EvaluateManageSysException
         *
         */
        public void updateStayTime(StayTimeModel bm)
                throws EvaluateManageSysException
        {
                try
                {
                        HibernateDAO.update(bm);
                }
                catch (ULMSSysException e)
                {
                        throw new EvaluateManageSysException(e);
                }
        }

        /**
         * check--is this userID already exit
         *
         * @param userID
         * @return
         * @throws com.ulearning.ulms.evaluate.exceptions.EvaluateManageSysException
         *
         */
        public List getIsHaveUser(int userID) throws EvaluateManageSysException
        {
                String hql = " from StayTimeModel where userID = " + userID;
                List list = null;

                try
                {
                        list = HibernateDAO.find(hql);
                }
                catch (ULMSSysException e)
                {
                        throw new EvaluateManageSysException(e);
                }

                return list;
        }

        /**
         * get list of rankname
         *
         * @return
         * @throws com.ulearning.ulms.evaluate.exceptions.EvaluateManageSysException
         *
         */
        public List getRankName() throws EvaluateManageSysException
        {
                String hql = " from ERecordRankModel";
                List list = null;

                try
                {
                        list = HibernateDAO.find(hql);
                }
                catch (ULMSSysException e)
                {
                        throw new EvaluateManageSysException(e);
                }

                return list;
        }

        /**
         * get point of every rank
         *
         * @return
         * @throws com.ulearning.ulms.evaluate.exceptions.EvaluateManageSysException
         *
         */
        public List getRankPoint() throws EvaluateManageSysException
        {
                String hql = " from ERecordRankStandardModel";
                List list = null;

                try
                {
                        list = HibernateDAO.find(hql);
                }
                catch (ULMSSysException e)
                {
                        throw new EvaluateManageSysException(e);
                }

                return list;
        }

        /**
         * get list by moduleID
         *
         * @param orgID
         * @return
         * @throws com.ulearning.ulms.evaluate.exceptions.EvaluateManageSysException
         *
         */
        public List getByModuleID(int orgID) throws EvaluateManageSysException
        {
                String hql = " from EAccessModel where moduleID=" + orgID;
                List list = null;

                try
                {
                        list = HibernateDAO.find(hql);
                }
                catch (ULMSSysException e)
                {
                        throw new EvaluateManageSysException(e);
                }

                return list;
        }

        /**
         * get list of point conversion
         *
         * @return
         * @throws com.ulearning.ulms.evaluate.exceptions.EvaluateManageSysException
         *
         */
        public List getPointConversion() throws EvaluateManageSysException
        {
                String hql = " from ERecordPointConversionModel";
                List list = null;

                try
                {
                        list = HibernateDAO.find(hql);
                }
                catch (ULMSSysException e)
                {
                        throw new EvaluateManageSysException(e);
                }

                return list;
        }

        /**
         * add a new ERecord
         *
         * @param em
         * @throws com.ulearning.ulms.evaluate.exceptions.EvaluateManageSysException
         *
         */
        public void addERecord(ERecordModel em) throws EvaluateManageSysException
        {
                try
                {
                        HibernateDAO.add(em);
                }
                catch (ULMSSysException e)
                {
                        throw new EvaluateManageSysException(e);
                }
        }

        /**
         * update ERecord
         *
         * @param em
         * @throws com.ulearning.ulms.evaluate.exceptions.EvaluateManageSysException
         *
         */
        public void updateERecord(ERecordModel em)
                throws EvaluateManageSysException
        {
                try
                {
                        HibernateDAO.update(em);
                }
                catch (ULMSSysException e)
                {
                        throw new EvaluateManageSysException(e);
                }
        }

        /**
         * get list by point
         *
         * @return
         * @throws com.ulearning.ulms.evaluate.exceptions.EvaluateManageSysException
         *
         */
        public List getERecord() throws EvaluateManageSysException
        {
                String hql = " from ERecordModel order by point desc";
                List list = null;

                try
                {
                        list = HibernateDAO.find(hql);
                }
                catch (ULMSSysException e)
                {
                        throw new EvaluateManageSysException(e);
                }

                return list;
        }

        /**
         * is this userID in table ERecord
         *
         * @param userID
         * @return
         * @throws com.ulearning.ulms.evaluate.exceptions.EvaluateManageSysException
         *
         */
        public List isThisUserInERecord(int userID)
                throws EvaluateManageSysException
        {
                String hql = " from ERecordModel where userID = " + userID;
                List list = null;

                try
                {
                        list = HibernateDAO.find(hql);
                }
                catch (ULMSSysException e)
                {
                        throw new EvaluateManageSysException(e);
                }

                return list;
        }

        /**
         * update rank standard
         *
         * @param em
         * @throws com.ulearning.ulms.evaluate.exceptions.EvaluateManageSysException
         *
         */
        public void updateERecordRankStandard(ERecordRankStandardModel em)
                throws EvaluateManageSysException
        {
                try
                {
                        HibernateDAO.update(em);
                }
                catch (ULMSSysException e)
                {
                        throw new EvaluateManageSysException(e);
                }
        }

        /**
         * update point conversion
         *
         * @param em
         * @throws com.ulearning.ulms.evaluate.exceptions.EvaluateManageSysException
         *
         */
        public void updateERecordPointConversion(ERecordPointConversionModel em)
                throws EvaluateManageSysException
        {
                try
                {
                        HibernateDAO.update(em);
                }
                catch (ULMSSysException e)
                {
                        throw new EvaluateManageSysException(e);
                }
        }

        /**
         * @param em
         * @throws com.ulearning.ulms.evaluate.exceptions.EvaluateManageSysException
         *
         */
        public void addFeedBack(FeedBackModel em) throws EvaluateManageSysException
        {
                try
                {
                        HibernateDAO.add(em);
                }
                catch (ULMSSysException e)
                {
                        throw new EvaluateManageSysException(e);
                }
        }
}
