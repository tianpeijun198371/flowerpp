/**
 * CalendarDAOOracle.java.
 * User: keyh  Date: 2004-8-4
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.tools.calendar.dao;

import com.ulearning.ulms.tools.calendar.exceptions.CalendarSysException;
import com.ulearning.ulms.tools.calendar.form.EventForm;
import com.ulearning.ulms.tools.calendar.model.EventModel;
import com.ulearning.ulms.util.DBUtil;
import com.ulearning.ulms.util.log.DebugUtil;
import com.ulearning.ulms.util.log.LogUtil;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;


public class CalendarDAOOracle extends CalendarDAOImpl
{
        /*public void insert(EventForm ef) throws CalendarSysException
          {
                  int relationID = ef.getRelationID();
                  int type = ef.getType();
                  int userID = ef.getUserID();
                  int orgID = ef.getOrgID();
                  String eventDate = ef.getEventDate();
                  int startHour = ef.getStartHour();
                  int stopHour = ef.getStopHour();
                  int startMin = ef.getStartMin();
                  int stopMin = ef.getStopMin();
                  String eventTitle = ef.getEventTitle();
                  String eventDetail = ef.getEventDetail();
                  String isRemind = ef.getIsRemind();
                  String remindTime = ef.getRemindTime();
                  if (eventDate == null)
                  {
                          eventDate = "''";
                  }
                  else
                  {
                          eventDate = "to_date('" + eventDate + "','yyyy-mm-dd')";
                  }
                  String sql_str = "insert into T_Calendar_tab (" +
                          "EventID,RelationID,Type,UserID,OrgID,EventDate," +
                          "EventStartHour,EventStopHour,EventStartMin,EventStopMin," +
                          "EventTitle,EventDetail,IsRemind,RemindTime" +
                          ") values(eventID.nextval," +
                          relationID + "," + type + "," + userID + "," + orgID + "," + eventDate + "," +
                          startHour + "," + stopHour + "," + startMin + "," + stopMin + ",'" +
                          eventTitle + "','" + eventDetail + "','" + isRemind + "','"
                          + remindTime + "')";
                  Connection conn = null;
                  Statement stmt = null;
                  try
                  {
                          conn = DBUtil.getConnection();
                          stmt = conn.createStatement();
                          LogUtil.debug("Calendar", "[CalendarDAOOracle] " + sql_str);
                          DebugUtil.print(sql_str);
                          int totalCountInserted = stmt.executeUpdate(sql_str);
                          LogUtil.debug("Calendar", "[CalendarDAOOracle insert Rows:] " + totalCountInserted);
                  }
                  catch (SQLException se)
                  {
                          se.printStackTrace();
                          throw new CalendarSysException(se);
                  }
                  finally
                  {
                          closeStatement(stmt);
                          closeConnection(conn);
                  }
                  //To change body of implemented methods use File | Settings | File Templates.
          }
        */
}
