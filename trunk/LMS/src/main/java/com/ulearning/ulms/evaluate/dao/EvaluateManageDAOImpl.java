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
import com.ulearning.ulms.util.log.LogUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class EvaluateManageDAOImpl implements EvaluateManageDAO
{
        /**
         * when user login, insert a new data
         *
         * @param am
         * @throws EvaluateManageSysException
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
         * @throws EvaluateManageSysException
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
         * @throws EvaluateManageSysException
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
                        hql = " from EAccessModel where to_char(accessDate,'yyyy') =" +
                                num;
                }
                else if (sort == 2)
                {
                        hql = " from EAccessModel where to_char(accessDate,'mm') =" + num;
                }
                else if (sort == 3)
                {
                        hql = " from EAccessModel where to_char(accessDate,'dd') =" + num;
                }
                else if (sort == 4)
                {
                        hql = " from EAccessModel where to_char(accessDate,'hh24') =" +
                                num;
                }
                else if (sort == 5)
                {
                        hql = "select pm.userID,count(pm.accessID) from EAccessModel pm group by pm.userID order by count(pm.accessID) desc";
                }

                System.out.println("hql         2006     03---30 = " + hql);

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
         * @throws EvaluateManageSysException
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
         * @throws EvaluateManageSysException
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
         * @throws EvaluateManageSysException
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
         * @throws EvaluateManageSysException
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
         * @throws EvaluateManageSysException
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
         * @throws EvaluateManageSysException
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
         * @throws EvaluateManageSysException
         */
        public List getPointConversion() throws EvaluateManageSysException
        {
                String hql = " from ERecordPointConversionModel";
                LogUtil.debug("system",
                        "[ERecordModel]====================the sql string is : " + hql);

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
         * @throws EvaluateManageSysException
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
         * @throws EvaluateManageSysException
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
         * @throws EvaluateManageSysException
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
         * get list by point
         *
         * @return
         * @throws EvaluateManageSysException
         */
        public int getERecord(int userID) throws EvaluateManageSysException
        {
                String hql = " from ERecordModel where userID=" + userID +
                        " order by point desc";
                int tt = 0;

                try
                {
                        List l = HibernateDAO.find(hql);

                        if ((l != null) && (l.size() > 0))
                        {
                                tt = ((ERecordModel) l.get(0)).getPoint();
                        }
                }
                catch (ULMSSysException e)
                {
                        throw new EvaluateManageSysException(e);
                }

                return tt;
        }

        /**
         * is this userID in table ERecord
         *
         * @param userID
         * @return
         * @throws EvaluateManageSysException
         */
        public List isThisUserInERecord(int userID)
                throws EvaluateManageSysException
        {
                String hql = " from ERecordModel where userID = " + userID;
                LogUtil.debug("system",
                        "[ERecordModel]====================the sql string is : " + hql);

                List list = new ArrayList();

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
         * @throws EvaluateManageSysException
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
         * @throws EvaluateManageSysException
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
         * @throws EvaluateManageSysException
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
