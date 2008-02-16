/**
 * LmslogOperDescDAOImpl.java.
 * User: keyh  Date: 2004-8-21
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.lmslog.dao;

import com.ulearning.ulms.core.exceptions.ULMSSysException;
import com.ulearning.ulms.lmslog.exceptions.LmslogOperDescSysException;
import com.ulearning.ulms.lmslog.form.LmslogOperDescForm;
import com.ulearning.ulms.lmslog.model.LmslogOperDescModel;
import com.ulearning.ulms.lmslog.util.LmslogConstants;
import com.ulearning.ulms.util.HibernateDAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.List;


public class LmslogOperDescDAOImpl implements LmslogOperDescDAO
{
        public void insert(LmslogOperDescForm lodf)
                throws LmslogOperDescSysException
        {
                try
                {
                        HibernateDAO.add(lodf.getLmslogOperModel());
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new LmslogOperDescSysException(e);
                }

                //To change body of implemented methods use File | Settings | File Templates.
        }

        public void testGet()
        {
        }

        public List getByLogType(int logTypeID) throws LmslogOperDescSysException
        {
                String hql = " from  LmslogOperDescModel  where logtypeid=" +
                        logTypeID;
                List logOperList = null;
                List list = new ArrayList();

                try
                {
                        logOperList = HibernateDAO.find(hql);
                        list = getFormList(logOperList);
                }
                catch (Exception e)
                {
                        e.printStackTrace();
                }

                return list;
        }

        public LmslogOperDescForm get(int operationTypeID)
                throws LmslogOperDescSysException
        {
                String hql = " from  LmslogOperDescModel where operationtypeid=" +
                        operationTypeID;
                List logOperList = null;
                List list = new ArrayList();
                LmslogOperDescForm lof = new LmslogOperDescForm();

                try
                {
                        logOperList = HibernateDAO.find(hql);
                        list = getFormList(logOperList);

                        if (list.size() > 0)
                        {
                                lof = (LmslogOperDescForm) list.get(0);
                        }
                }
                catch (Exception e)
                {
                        e.printStackTrace();
                }

                return lof;
        }

        public List getAll() throws LmslogOperDescSysException
        {
                String hql = " from  LmslogOperDescModel";
                List logOperList = null;
                List list = new ArrayList();

                try
                {
                        logOperList = HibernateDAO.find(hql);
                        list = getFormList(logOperList);
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
                        LmslogOperDescModel lom = new LmslogOperDescModel();
                        LmslogOperDescForm lof = new LmslogOperDescForm();
                        lom = (LmslogOperDescModel) modelList.get(i);
                        lof.setOperationTypeID(lom.getOperationtypeid());
                        lof.setLogTypeID(lom.getLogtypeid());
                        lof.setOperationName(lom.getOperationname());
                        lof.setOperationDesc(lom.getOperationdesc());
                        list.add(lof);
                }

                return list;
        }

        private LmslogOperDescForm converRs2Model(ResultSet rs)
        {
                LmslogOperDescForm lodf = new LmslogOperDescForm();

                try
                {
                        lodf.setOperationTypeID(rs.getInt("OperationTypeID"));
                        lodf.setLogTypeID(rs.getInt("LogTypeID"));
                        lodf.setOperationName(rs.getString("OperationName"));
                        lodf.setOperationDesc(rs.getString("OperationDesc"));
                }
                catch (SQLException se)
                {
                        se.printStackTrace();
                }

                return lodf;
        }

        protected void closeConnection(Connection dbConnection)
                throws LmslogOperDescSysException
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
                        throw new LmslogOperDescSysException(se);
                }
        }

        protected void closeResultSet(ResultSet result)
                throws LmslogOperDescSysException
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
                        throw new LmslogOperDescSysException(se);
                }
        }

        protected void closeStatement(Statement stmt)
                throws LmslogOperDescSysException
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
                        throw new LmslogOperDescSysException(se);
                }
        }
}
