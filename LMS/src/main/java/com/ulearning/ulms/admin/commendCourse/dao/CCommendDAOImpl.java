/**
 * CCommendDAOImpl.java.
 * User: zengwj Date: 2005-5-18 11:18:56
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.admin.commendCourse.dao;

import com.ulearning.ulms.admin.commendCourse.exceptions.CCommendDAOSysException;
import com.ulearning.ulms.admin.commendCourse.form.CCommendForm;
import com.ulearning.ulms.admin.commendCourse.model.CCommendModel;
import com.ulearning.ulms.core.exceptions.ULMSSysException;
import com.ulearning.ulms.util.HibernateDAO;
import com.ulearning.ulms.util.log.LogUtil;

import net.sf.hibernate.SessionFactory;

import java.sql.Connection;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;


public class CCommendDAOImpl implements CCommendDAO
{
        protected static SessionFactory sessionFactory = null;
        protected transient Connection dbConnection = null;
        protected transient DataSource datasource = null;

        public void addCCommend(CCommendForm details)
                throws CCommendDAOSysException
        {
                try
                {
                        LogUtil.debug("[CCommendDAOImpl]",
                                "add CCommend, name =============" + details.getName());
                        HibernateDAO.add(details.getModel());
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        e.getMessage();
                        throw new CCommendDAOSysException(
                                "[CCommendDAOImpl] exception issue in addCCommend " + e);
                }
        }

        public void updateCCommend(CCommendForm details)
                throws CCommendDAOSysException
        {
                try
                {
                        LogUtil.debug("[CCommendDAOImpl]",
                                "update CCommend, name =============" + details.getName());
                        HibernateDAO.update(details.getModel());
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new CCommendDAOSysException(
                                "[CCommendDAOImpl] exception issue in updateCCommend " + e);
                }
        }

        public void removeCCommend(int ccourseID) throws CCommendDAOSysException
        {
                String hql = " from CCommendModel where ccourseID = " + ccourseID;

                try
                {
                        LogUtil.debug("[CCommendDAOImpl]",
                                "remove CCommend, courseID =================" + ccourseID);
                        HibernateDAO.delete(hql);
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new CCommendDAOSysException(
                                "[CCommendDAOImpl] exception issue in delCCommend " + e);
                }
        }

        public CCommendForm getCCommend(int ccourseID)
                throws CCommendDAOSysException
        {
                CCommendForm cCommendForm = null;
                String hql = " from CCommendModel where ccourseID = " + ccourseID;
                List tempList = null;

                try
                {
                        LogUtil.debug("[CCommendDAOImpl]",
                                "get CCommend, courseID =================" + ccourseID);
                        tempList = HibernateDAO.find(hql);

                        CCommendModel cCommendModel = null;

                        if ((tempList != null) && (tempList.size() > 0))
                        {
                                cCommendModel = (CCommendModel) tempList.get(0);
                                cCommendForm = new CCommendForm(cCommendModel);
                        }
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        cCommendForm = null;
                        throw new CCommendDAOSysException(
                                "[CCommendDAOImpl] exception issue in delCCommend " + e);
                }

                return cCommendForm;
        }

        public List getCCommend_month(String displayed, int monthType)
                throws CCommendDAOSysException
        {
                CCommendForm cCommendForm = null;
                List cCommendList = new ArrayList();
                StringBuffer hql = new StringBuffer();
                hql.append(" from CCommendModel where ccourseID >= 0 ");

                if (monthType >= 0)
                {
                        hql.append(" and monthType = " + monthType);
                }

                if (Integer.parseInt(displayed) >= 0)
                {
                        hql.append(" and displayed = '" + displayed + "'");
                }

                hql.append(" order by publishTime desc ");

                List tempList = null;

                try
                {
                        LogUtil.debug("[CCommendDAOImpl]",
                                "get CCommend, courseID =================" + hql.toString());
                        tempList = HibernateDAO.find(hql.toString());

                        CCommendModel cCommendModel = null;

                        for (int i = 0; (tempList != null) && (i < tempList.size()); i++)
                        {
                                cCommendModel = (CCommendModel) tempList.get(i);
                                cCommendForm = new CCommendForm(cCommendModel);
                                cCommendList.add(cCommendForm);
                        }
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        cCommendForm = null;
                        throw new CCommendDAOSysException(
                                "[CCommendDAOImpl] exception issue in delCCommend " + e);
                }

                return cCommendList;
        }
}
