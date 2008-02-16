/**
 * Created by IntelliJ IDEA.
 * author: houct
 * Date: 2005/04/08
 * Time: 10:58:23 AM
 * To change this template use Options | File Templates.
 */
package com.ulearning.ulms.admin.topTen.dao;

import com.ulearning.ulms.admin.topTen.exceptions.TopTenDAOSysException;
import com.ulearning.ulms.admin.topTen.form.TopTenForm;
import com.ulearning.ulms.admin.topTen.model.TopTenModel;
import com.ulearning.ulms.core.exceptions.ULMSSysException;
import com.ulearning.ulms.util.DBUtil;
import com.ulearning.ulms.util.HibernateDAO;
import com.ulearning.ulms.util.HibernateUtil;
import com.ulearning.ulms.util.log.LogUtil;

import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Query;
import net.sf.hibernate.Session;
import net.sf.hibernate.SessionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;


public class TopTenDAOImpl implements TopTenDAO
{
        protected static SessionFactory sessionFactory = null;
        protected transient Connection dbConnection = null;
        protected transient DataSource datasource = null;

        public void addTopTen(TopTenForm details) throws TopTenDAOSysException
        {
                try
                {
                        HibernateDAO.add(details.getTopTenModel());
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new TopTenDAOSysException("" + e);
                }
        }

        public void updateTopTen(TopTenForm details) throws TopTenDAOSysException
        {
                try
                {
                        HibernateDAO.update(details.getTopTenModel());
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new TopTenDAOSysException("" + e);
                }
        }

        public void removeTopTen(int ID) throws TopTenDAOSysException
        {
                String hql = " from TopTenModel where ID = " + ID;

                try
                {
                        LogUtil.debug("TopTenDAOImll", "hql=======================" + hql);
                        HibernateDAO.delete(hql);
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new TopTenDAOSysException("" + e);
                }
        }

        public List getTopTenList(String type) throws TopTenDAOSysException
        {
                List tmList = null;
                List toptenList = new ArrayList();
                String hql = " from TopTenModel where type='" + type +
                        "' order by creattime desc, grade desc";

                try
                {
                        tmList = HibernateDAO.find(hql);

                        TopTenModel tm = null;

                        if (tmList != null)
                        {
                                for (int i = 0; i < tmList.size(); i++)
                                {
                                        tm = (TopTenModel) tmList.get(i);

                                        TopTenForm pf = new TopTenForm(tm);
                                        toptenList.add(pf);
                                }
                        }
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new TopTenDAOSysException("" + e);
                }

                return toptenList;
        }

        /**
         * Get the TopTen info via the unique ID
         *
         * @param ID
         * @return the prepared TopTenForm, default is null
         * @throws TopTenDAOSysException
         */
        public TopTenForm getTopTen(int ID) throws TopTenDAOSysException
        {
                TopTenForm res = null;

                List tmList = null;

                String hql = " from TopTenModel where ID = " + ID;

                try
                {
                        tmList = HibernateDAO.find(hql);
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new TopTenDAOSysException("" + e);
                }

                if ((tmList != null) && (tmList.size() > 0))
                {
                        TopTenModel tm = (TopTenModel) tmList.get(0);
                        res = new TopTenForm(tm);
                }

                return res;
        }

        public int getID(String name, String type) throws TopTenDAOSysException
        {
                int id = 0;
                String hql = " from TopTenModel where name = '" + name +
                        "' and type='" + type + "'";
                List tmList = null;

                try
                {
                        tmList = HibernateDAO.find(hql);

                        TopTenModel tm = null;

                        if ((tmList != null) && (tmList.size() > 0))
                        {
                                tm = (TopTenModel) tmList.get(0);
                                id = tm.getId();
                        }
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new TopTenDAOSysException("exception" + e);
                }

                return id;
        }

        public List getTopTenList(String type, String is_display)
                throws TopTenDAOSysException
        {
                List tmList = null;
                List toptenList = new ArrayList();
                String hql = "";
                Date t = new Date();
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(t);

                int month1 = calendar.get(Calendar.MONTH);
                String mon = String.valueOf(month1 + 1);

                if (type.equals("1"))
                {
                        hql = " from TopTenModel where type='" + type +
                                "' and is_display='" + is_display + "' order by grade desc";
                }
                else
                {
                        hql = " from TopTenModel where type='" + type +
                                "' and is_display='" + is_display + "' and desc1='" + mon +
                                "' order by grade desc";
                }

                try
                {
                        tmList = HibernateDAO.find(hql);

                        TopTenModel tm = null;

                        if (tmList != null)
                        {
                                for (int i = 0; i < tmList.size(); i++)
                                {
                                        tm = (TopTenModel) tmList.get(i);

                                        TopTenForm pf = new TopTenForm(tm);
                                        toptenList.add(pf);
                                }
                        }
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new TopTenDAOSysException("exception" + e);
                }

                return toptenList;
        }

        public List getTopTen_T_List(String teacher_type, String is_display)
                throws TopTenDAOSysException
        {
                List tmList = null;
                List toptenList = new ArrayList();
                String hql = "";
                Date t = new Date();
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(t);

                int month1 = calendar.get(Calendar.MONTH);
                String mon = String.valueOf(month1); //取上个月的
                hql = " from TopTenModel where type='0' and is_display='" + is_display +
                        "' and desc0 = '" + teacher_type + "' and desc1='" + mon +
                        "' order by grade desc";

                try
                {
                        tmList = HibernateDAO.find(hql);

                        TopTenModel tm = null;

                        if (tmList != null)
                        {
                                for (int i = 0; i < tmList.size(); i++)
                                {
                                        tm = (TopTenModel) tmList.get(i);

                                        TopTenForm pf = new TopTenForm(tm);
                                        toptenList.add(pf);
                                }
                        }
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new TopTenDAOSysException("exception" + e);
                }

                return toptenList;
        }

        public List getTopTenList_month(String type, String month,
                                        String teacher_type) throws TopTenDAOSysException
        {
                Session session = null;
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                calendar.set(year, 0, 1);

                Date startDate = calendar.getTime();
                calendar.set(year, 11, 31);

                Date endDate = calendar.getTime();

                List toptenList = null;
                List tempList = new ArrayList();
                String hql = "";
                hql = " from TopTenModel where type='" + type + "' and desc1='" +
                        month + "'";

                if (Integer.parseInt(teacher_type) >= 0)
                {
                        hql += (" and desc0 = '" + teacher_type + "'" +
                                " and is_display = 1");
                }

                hql += " and creattime >= :beginDate and creattime <= :endDate";
                hql += " order by grade desc";

                try
                {
                        session = HibernateUtil.getSession();

                        Query query = session.createQuery(hql);
                        query.setParameter("beginDate", startDate);
                        query.setParameter("endDate", endDate);
                        tempList = query.list();
                        toptenList = getTopTenModelList(tempList);
                        session.flush();
                        session.connection().commit();
                }
                catch (Exception e)
                {
                        e.printStackTrace();
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

                return toptenList;
        }

        public List getTopTenList_month(String type, String month)
                throws TopTenDAOSysException
        {
                List tmList = null;
                List toptenList = new ArrayList();
                String hql = "";
                hql = " from TopTenModel where type='" + type + "' and desc1='" +
                        month + "'  order by grade desc";

                try
                {
                        tmList = HibernateDAO.find(hql);

                        TopTenModel tm = null;

                        if (tmList != null)
                        {
                                for (int i = 0; i < tmList.size(); i++)
                                {
                                        tm = (TopTenModel) tmList.get(i);

                                        TopTenForm pf = new TopTenForm(tm);
                                        toptenList.add(pf);
                                }
                        }
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new TopTenDAOSysException("exception" + e);
                }

                return toptenList;
        }

        protected void closeConnection() throws TopTenDAOSysException
        {
                try
                {
                        if ((dbConnection != null) && !dbConnection.isClosed())
                        {
                                dbConnection.close();
                        }
                }
                catch (SQLException se)
                {
                        throw new TopTenDAOSysException("SQL Exception while closing " +
                                "DB connection : \n" + se);
                }
        }

        protected void closeResultSet(ResultSet result)
                throws TopTenDAOSysException
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
                        throw new TopTenDAOSysException("SQL Exception while closing " +
                                "Result Set : \n" + se);
                }
        }

        protected void closeStatement(PreparedStatement stmt)
                throws TopTenDAOSysException
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
                        throw new TopTenDAOSysException("SQL Exception while closing " +
                                "Statement : \n" + se);
                }
        }

        private List getTopTenModelList(List tempList)
        {
                List returnList = null;

                if ((tempList != null) && (tempList.size() > 0))
                {
                        returnList = new ArrayList();

                        for (int i = 0; i < tempList.size(); i++)
                        {
                                TopTenModel model = new TopTenModel();
                                model = (TopTenModel) tempList.get(i);

                                TopTenForm topTenForm = new TopTenForm(model);
                                returnList.add(topTenForm);
                        }
                }

                return returnList;
        }

        protected Connection getConnection() throws TopTenDAOSysException
        {
                try
                {
                        dbConnection = DBUtil.getConnection();
                }
                catch (Exception se)
                {
                        throw new TopTenDAOSysException("SQL Exception while getting " +
                                "DB connection : \n" + se);
                }

                return dbConnection;
        }
}
