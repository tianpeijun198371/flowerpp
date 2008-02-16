/**
 * PreferenceImpl.java.
 * User: keyh  Date: 2004-8-4
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.tools.calendar.dao;

import com.ulearning.ulms.core.exceptions.ULMSSysException;
import com.ulearning.ulms.tools.calendar.exceptions.CalendarSysException;
import com.ulearning.ulms.tools.calendar.exceptions.PreferenceSysException;
import com.ulearning.ulms.tools.calendar.form.EventForm;
import com.ulearning.ulms.tools.calendar.form.PreferenceForm;
import com.ulearning.ulms.tools.calendar.helper.PreferenceHelper;
import com.ulearning.ulms.tools.calendar.model.PreferenceModel;
import com.ulearning.ulms.util.DBUtil;
import com.ulearning.ulms.util.HibernateDAO;
import com.ulearning.ulms.util.HibernateUtil;
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

import java.util.*;


public class PreferenceDAOImpl implements PreferenceDAO
{
        public void insert(PreferenceForm pf) throws PreferenceSysException
        {
                try
                {
                        HibernateDAO.saveOrUpdateCopy(pf.getPreferenceModel());
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new PreferenceSysException(e);
                }
        }

        public void update(PreferenceForm pf) throws PreferenceSysException
        {
                try
                {
                        HibernateDAO.update(pf.getPreferenceModel());
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new PreferenceSysException(e);
                }
        }

        public void delete(int userID) throws PreferenceSysException
        {
                String hql = " from PreferenceModel where userid=" + userID;

                try
                {
                        HibernateDAO.delete(hql);
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new PreferenceSysException(e);
                }
        }

        public boolean isPrefExist(int userID) throws PreferenceSysException
        {
                String hql = " from PreferenceModel where UserID=" + userID;
                List preferenceList = null;

                try
                {
                        preferenceList = HibernateDAO.find(hql);

                        if (preferenceList.size() > 0)
                        {
                                return true;
                        }
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                }

                return false;
        }

        public PreferenceForm get(int userID) throws PreferenceSysException
        {
                String hql = " from PreferenceModel where userid=" + userID;
                PreferenceForm preferenceForm = new PreferenceForm();

                try
                {
                        List preferenceList = HibernateDAO.find(hql);
                        List list = new ArrayList();

                        if ((preferenceList != null) && (preferenceList.size() > 0))
                        {
                                list = getFormList(preferenceList);
                                preferenceForm = (PreferenceForm) list.get(0);
                        }
                        else
                        {
                                preferenceForm = getDefaultSetting(userID);
                        }
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                }

                return preferenceForm;
        }

        private List getFormList(List modelList)
        {
                List list = new ArrayList();

                for (int i = 0; i < modelList.size(); i++)
                {
                        PreferenceModel pm = new PreferenceModel();
                        pm = (PreferenceModel) modelList.get(i);

                        PreferenceForm pf = new PreferenceForm();
                        pf = pf.getPreferenceForm(pm);
                        list.add(pf);
                }

                return list;
        }

        private PreferenceForm converRs2Model(ResultSet rs)
        {
                PreferenceForm pf = new PreferenceForm();

                try
                {
                        pf.setUserID(rs.getInt("UserID"));
                        pf.setLoginView(rs.getInt("LoginView"));
                        pf.setTimeZone(rs.getString("TimeZone"));
                        pf.setFirstDayOfWeek(rs.getInt("FirstDayOfWeek"));
                        pf.setHourStyle(rs.getString("HourStyle"));
                        pf.setIsViewLunarCalendar(rs.getString("IsViewLunarCalendar"));
                }
                catch (SQLException se)
                {
                        se.printStackTrace();
                }

                return pf;
        }

        protected void closeConnection(Connection dbConnection)
                throws PreferenceSysException
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
                        throw new PreferenceSysException(se);
                }
        }

        protected void closeResultSet(ResultSet result)
                throws PreferenceSysException
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
                        throw new PreferenceSysException(se);
                }
        }

        protected void closeStatement(Statement stmt) throws PreferenceSysException
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
                        throw new PreferenceSysException(se);
                }
        }

        public PreferenceForm getDefaultSetting(int userID)
        {
                PreferenceForm pf = new PreferenceForm();
                Calendar cal = Calendar.getInstance();
                TimeZone tz = cal.getTimeZone();

                pf.setUserID(userID);
                pf.setLoginView(2);
                pf.setTimeZone(tz.getID());
                pf.setFirstDayOfWeek(0);
                pf.setHourStyle("0");
                pf.setIsViewLunarCalendar("0");

                return pf;
        }
}
