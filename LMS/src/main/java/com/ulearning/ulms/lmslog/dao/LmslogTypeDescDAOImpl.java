/**
 * LmslogTypeDescDAOImpl.java.
 * User: keyh  Date: 2004-8-21
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.lmslog.dao;

import com.ulearning.ulms.core.exceptions.ULMSSysException;
import com.ulearning.ulms.lmslog.exceptions.LmslogOperDescSysException;
import com.ulearning.ulms.lmslog.exceptions.LmslogTypeDescSysException;
import com.ulearning.ulms.lmslog.form.LmslogOperDescForm;
import com.ulearning.ulms.lmslog.form.LmslogTypeDescForm;
import com.ulearning.ulms.lmslog.model.LmslogOperDescModel;
import com.ulearning.ulms.lmslog.model.LmslogTypeDescModel;
import com.ulearning.ulms.util.DBUtil;
import com.ulearning.ulms.util.HibernateDAO;
import com.ulearning.ulms.util.HibernateUtil;
import com.ulearning.ulms.util.log.DebugUtil;
import com.ulearning.ulms.util.log.LogUtil;

import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Query;
import net.sf.hibernate.Session;
import net.sf.hibernate.SessionFactory;
import net.sf.hibernate.cfg.Configuration;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class LmslogTypeDescDAOImpl implements LmslogTypeDescDAO
{
        public void insert(LmslogTypeDescForm ltdf)
                throws LmslogTypeDescSysException
        {
                try
                {
                        HibernateDAO.add(ltdf.getLmslogTypeDescModel());
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new LmslogTypeDescSysException(e);
                }
        }

        public LmslogTypeDescForm get(int logTypeID)
                throws LmslogTypeDescSysException
        {
                String hql = " from  LmslogOperDescModel where logtypeid=" + logTypeID;
                List logTypeList = null;
                List list = new ArrayList();
                LmslogTypeDescForm ltf = new LmslogTypeDescForm();

                try
                {
                        logTypeList = HibernateDAO.find(hql);

                        if (logTypeList.size() > 0)
                        {
                                list = getFormList(logTypeList);
                                ltf = (LmslogTypeDescForm) list.get(0);
                        }
                }
                catch (Exception e)
                {
                        e.printStackTrace();
                }

                return ltf;
        }

        public List getAll() throws LmslogTypeDescSysException
        {
                String hql = " from  LmslogTypeDescModel";
                List logTypeList = null;
                List list = new ArrayList();

                try
                {
                        logTypeList = HibernateDAO.find(hql);
                        list = getFormList(logTypeList);
                }
                catch (Exception e)
                {
                        e.printStackTrace();
                }

                return list;
        }

        private List getFormList(List modelList)
        {
                ArrayList list = new ArrayList();

                for (int i = 0; i < modelList.size(); i++)
                {
                        LmslogTypeDescModel ltm = new LmslogTypeDescModel();
                        LmslogTypeDescForm ltf = new LmslogTypeDescForm();
                        ltm = (LmslogTypeDescModel) modelList.get(i);
                        ltf.setLogTypeID(ltm.getLogtypeid());
                        ltf.setLogTypeDesc(ltm.getLogtypedesc());
                        list.add(ltf);
                }

                return list;
        }

        private LmslogTypeDescForm converRs2Model(ResultSet rs)
        {
                LmslogTypeDescForm ltdf = new LmslogTypeDescForm();

                try
                {
                        ltdf.setLogTypeID(rs.getInt("LogTypeID"));
                        ltdf.setLogTypeDesc(rs.getString("LogTypeDesc"));
                }
                catch (SQLException se)
                {
                        se.printStackTrace();
                }

                return ltdf;
        }

        protected void closeConnection(Connection dbConnection)
                throws LmslogTypeDescSysException
        {
                try
                {
                        if (dbConnection != null)
                        {
                                dbConnection.close();
                        }
                }
                catch (SQLException se)
                {
                        throw new LmslogTypeDescSysException(se);
                }
        }

        protected void closeResultSet(ResultSet result)
                throws LmslogTypeDescSysException
        {
                try
                {
                        if (result != null)
                        {
                                result.close();
                        }
                }
                catch (SQLException se)
                {
                        throw new LmslogTypeDescSysException(se);
                }
        }

        protected void closeStatement(Statement stmt)
                throws LmslogTypeDescSysException
        {
                try
                {
                        if (stmt != null)
                        {
                                stmt.close();
                        }
                }
                catch (SQLException se)
                {
                        throw new LmslogTypeDescSysException(se);
                }
        }
}
