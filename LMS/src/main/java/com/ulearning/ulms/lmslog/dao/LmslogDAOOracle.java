/**
 * lmslogDAOOracle.java.
 * User: keyh  Date: 2004-8-18
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.lmslog.dao;

import com.ulearning.ulms.lmslog.exceptions.LmslogSysException;
import com.ulearning.ulms.lmslog.form.LmslogForm;
import com.ulearning.ulms.util.DBUtil;
import com.ulearning.ulms.util.log.DebugUtil;
import com.ulearning.ulms.util.log.LogUtil;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.Date;


public class LmslogDAOOracle extends LmslogDAOImpl
{ /*
       public void insert(LmslogForm lmslogForm) throws LmslogSysException
       {
               int logID=lmslogForm.getLogID();
               int logTypeID=lmslogForm.getLogTypeID();
               int userID=lmslogForm.getUserID();
               int orgID=lmslogForm.getOrgID();
               String userIP=lmslogForm.getUserIP();
               int operationTypeID=lmslogForm.getOperationTypeID();
               String operationTable=lmslogForm.getOperationTable();
               int operationObjectID=lmslogForm.getOperationObjectID();
               java.sql.Date bDate = new java.sql.Date(System.currentTimeMillis());
               java.sql.Time bTime = new java.sql.Time(System.currentTimeMillis());
               String sql_str = "insert into T_Lmslog_Tab values(LogID.nextval, " +
                       logTypeID+","+userID+","+orgID+",'"+userIP+"',"+operationTypeID +",'"+
                       operationTable+"',"+operationObjectID+
                       ",to_date('" + bDate + " " + bTime + "','yyyy-mm-dd hh24:mi:ss'))" ;
               Connection conn = null;
               Statement stmt = null;
               try
               {
                       conn = DBUtil.getConnection();
                       stmt = conn.createStatement();
                       LogUtil.debug("Calendar", "[CalendarDAOOracle] " + sql_str);
                       int totalCountInserted = stmt.executeUpdate(sql_str);
                       LogUtil.debug("Calendar", "[CalendarDAOOracle insert Rows:] " + totalCountInserted);
               }
               catch (SQLException se)
               {
                       se.printStackTrace();
                       throw new LmslogSysException(se);
               }
               finally
               {
                       closeStatement(stmt);
                       closeConnection(conn);
               }
       }
     */
}
