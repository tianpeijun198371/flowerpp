/**
 * PreferenceOracle.java.
 * User: keyh  Date: 2004-8-4
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.tools.calendar.dao;

import com.ulearning.ulms.tools.calendar.exceptions.PreferenceSysException;
import com.ulearning.ulms.tools.calendar.form.PreferenceForm;
import com.ulearning.ulms.util.DBUtil;
import com.ulearning.ulms.util.log.LogUtil;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;


public class PreferenceDAOOracle extends PreferenceDAOImpl
{
        public void insert(PreferenceForm pf) throws PreferenceSysException
        {
                int userID = pf.getUserID();
                int calLoginView = pf.getLoginView();
                String timeZone = pf.getTimeZone();
                int firstDayOfWeek = pf.getFirstDayOfWeek();
                String hourStyle = pf.getHourStyle();
                String isViewLunarCalendar = pf.getIsViewLunarCalendar();
                String sql_str = "insert into T_CalUserPref_Tab" +
                        "(UserID,LoginView,TimeZone," +
                        "FirstDayOfWeek,HourStyle,isViewLunarCalendar) values(" + userID +
                        "," + calLoginView + ",'" + timeZone + "'," + firstDayOfWeek +
                        ",'" + hourStyle + "','" + isViewLunarCalendar + "')";

                Connection conn = null;
                Statement stmt = null;

                try
                {
                        conn = DBUtil.getConnection();
                        stmt = conn.createStatement();
                        LogUtil.debug("Preference", "[PreferenceDAOOracle] " + sql_str);

                        int totalCountInserted = stmt.executeUpdate(sql_str);
                        LogUtil.debug("Preference",
                                "[PreferenceDAOOracle insert Rows:] " + totalCountInserted);
                }
                catch (SQLException se)
                {
                        se.printStackTrace();
                        throw new PreferenceSysException(se);
                }
                finally
                {
                        closeStatement(stmt);
                        closeConnection(conn);
                }
        }

        public void update(PreferenceForm pf) throws PreferenceSysException
        {
                int userID = pf.getUserID();
                int LoginView = pf.getLoginView();
                String timeZone = pf.getTimeZone();
                int firstDayOfWeek = pf.getFirstDayOfWeek();
                String hourStyle = pf.getHourStyle();
                String isViewLunarCalendar = pf.getIsViewLunarCalendar();
                String sql_str = "update T_CalUserPref_Tab set  " + "LoginView = " +
                        LoginView + "," + "TimeZone='" + timeZone + "'," +
                        "FirstDayOfWeek =" + firstDayOfWeek + "," + "HourStyle='" +
                        hourStyle + "'," + "IsViewLunarCalendar='" + isViewLunarCalendar +
                        "'" + " where UserID=" + userID;

                Connection conn = null;
                Statement stmt = null;

                try
                {
                        conn = DBUtil.getConnection();
                        stmt = conn.createStatement();
                        LogUtil.debug("Preference", "[PreferenceDAOOracle] " + sql_str);
                        stmt.executeUpdate(sql_str);
                }
                catch (SQLException se)
                {
                        se.printStackTrace();
                        throw new PreferenceSysException(se);
                }
                finally
                {
                        closeStatement(stmt);
                        closeConnection(conn);
                }
        }
}
