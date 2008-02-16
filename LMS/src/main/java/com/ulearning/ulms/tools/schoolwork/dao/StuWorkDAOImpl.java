/**
 * StuWorkDAOImpl.java
 * created by yud
 * Date 2005.04.11
 * Copyright (c) 2000-2005.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.tools.schoolwork.dao;


import com.ulearning.ulms.core.exceptions.ULMSSysException;
import com.ulearning.ulms.core.util.DateTimeUtil;
import com.ulearning.ulms.tools.schoolwork.exceptions.StuWorkSysException;
import com.ulearning.ulms.tools.schoolwork.model.StuWorkModel;
import com.ulearning.ulms.util.HibernateDAO;
import com.ulearning.ulms.util.HibernateUtil;
import com.ulearning.ulms.util.log.DebugUtil;

import java.util.List;
import java.util.Date;
import java.util.ArrayList;

import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Session;
import net.sf.hibernate.SessionFactory;
import net.sf.hibernate.cfg.Configuration;
import net.sf.hibernate.Query;


public class StuWorkDAOImpl implements StuWorkDAO
{
        /**
         * @param userswId
         * @return
         * @throws StuWorkSysException
         */
        public StuWorkModel getStuModel(int userswId) throws StuWorkSysException
        {
                StuWorkModel stuWorkModel = null;
                List clList = null;
                String sql_str = "from StuWorkModel where userswId = " + userswId;

                try
                {
                        clList = HibernateDAO.find(sql_str);

                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new StuWorkSysException(e);
                }
                for (int i = 0; i < clList.size(); i++)
                {
                        stuWorkModel = (StuWorkModel) clList.get(i);
                }
                return stuWorkModel;
        }

        /**
         * @param userID
         * @return
         * @throws StuWorkSysException
         */
        public List getStuList(int swID, int userID)
                throws StuWorkSysException
        {
                List tmList = null;
                String sql_str = "from StuWorkModel where userId=" + userID
                        + " and swId=" + swID;

                try
                {
                        tmList = HibernateDAO.find(sql_str);
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new StuWorkSysException(e);
                }
                return tmList;
        }


        public List getChouChaList(int swID, int orgID, String firstDate, String lastDate)
                throws StuWorkSysException
        {
                Date startDate = DateTimeUtil.parseDate(firstDate);
                Date endDate = DateTimeUtil.parseDate(lastDate);
                com.ulearning.ulms.core.util.Calendar calendar = new com.ulearning.ulms.core.util.Calendar(endDate);
                endDate = calendar.nextDay();

                Session session = null;
                List courseList = new ArrayList();
                List list = new ArrayList();
                String sql_str = "from StuWorkModel where orgID=" + orgID
                        + " and swId = " + swID
                        + " and createDate >= :beginDate" +
                        " and createDate <= :endDate";

                try
                {
                        session = HibernateUtil.getSession();
                        Query query = session.createQuery(sql_str);
                        query.setParameter("beginDate", startDate);
                        query.setParameter("endDate", endDate);
                        courseList = query.list();
                        list = (courseList);
                        session.flush();
                        session.connection().commit();
                }
                catch (Exception e)
                {
                        e.printStackTrace();
                        throw new StuWorkSysException(e);
                }
                finally
                {
                        try
                        {
                                if (session != null)
                                {
                                        HibernateUtil.releaseSession(session);
                                }
                        }
                        catch (HibernateException he)
                        {
                                he.printStackTrace();
                        }
                }
                return list;
        }

        /**
         * @param swID
         * @return
         * @throws StuWorkSysException
         */
        public List getall(int swID) throws StuWorkSysException
        {
                List tmList = null;
                String sql_str = "from StuWorkModel where swId=" + swID;

                try
                {
                        tmList = HibernateDAO.find(sql_str);
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new StuWorkSysException(e);
                }
                return tmList;
        }

        /**
         * created by yud
         *
         * @param addStuWorkModel
         * @throws StuWorkSysException
         */
        public void insertStuWork(StuWorkModel addStuWorkModel) throws StuWorkSysException
        {
                try
                {
                        HibernateDAO.add(addStuWorkModel);
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new StuWorkSysException(e);
                }
        }

        /**
         * created by yud
         *
         * @param updStuWorkModel
         * @throws StuWorkSysException
         */
        public void updateStuWork(StuWorkModel updStuWorkModel) throws StuWorkSysException
        {
                try
                {
                        HibernateDAO.update(updStuWorkModel);
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new StuWorkSysException(e);
                }
        }

        /**
         * created by yud
         *
         * @param l
         * @throws StuWorkSysException
         */
        public void deleteStuWork(List l) throws StuWorkSysException
        {
                String sql_str = "";
                if (l.size() > 0)
                {
                        sql_str = " from StuWorkModel where userswId = " + (Integer) l.get(0);
                        for (int i = 1; i < l.size(); i++)
                        {
                                sql_str += " or userswId = " + (Integer) l.get(i) + "";
                        }
                }

                try
                {
                        DebugUtil.print("[Debug ]  = " + sql_str);
                        HibernateDAO.delete(sql_str);
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new StuWorkSysException(e);
                }
        }

}
