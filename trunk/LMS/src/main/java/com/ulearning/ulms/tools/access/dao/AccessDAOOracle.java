/**
 * AccessDAOOracle.java.
 * User: fengch  Date: 2004-4-26
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.tools.access.dao;

import com.ulearning.ulms.core.exceptions.ULMSSysException;
import com.ulearning.ulms.core.util.DateTimeUtil;
import com.ulearning.ulms.tools.access.exceptions.AccessAppException;
import com.ulearning.ulms.tools.access.exceptions.AccessSysException;
import com.ulearning.ulms.tools.access.model.Access;
import com.ulearning.ulms.tools.access.model.AccessList;
import com.ulearning.ulms.util.log.LogUtil;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;


public class AccessDAOOracle extends AccessDAOImpl
{
        /**
         * publish a new access
         *
         * @param access the new access
         * @throws AccessSysException If an lmsSys error has occurred.
         */

        /*
          public void insert(Access access)
                   throws AccessSysException
          {
                  String accessID = "AccessID.nextval";
                  int UserID = access.getUserID();
                  int ModuleID = access.getModuleID();
                  int CourseID = access.getCourseID();
                  int CertificateID = access.getCertificateID();
                  int ProjectID = access.getProjectID();
                  int OrgID = access.getOrgID();
                  String ip ="'"+access.getIp()+"'";
                  double UserTime = access.getUserTime();
                  java.sql.Date dayToInsert = new java.sql.Date(System.currentTimeMillis());
                  java.sql.Time timeToInsert = new java.sql.Time(System.currentTimeMillis());
                  String createDate  = "to_date('" + dayToInsert +" "+timeToInsert+ "','yyyy-mm-dd hh24:mi:ss')";
                  {
                  }
                  try
                  {
                          String sql_str="";
                          //prepare the sql string to insert
                          sql_str="insert into TM_Access_TAB(AccessID,UserID,CreateDateTime,"
                                  + " ModuleID,CourseID,CertificateID,ProjectID,OrgID,"
                                  + " IP,UserTime) values ("
                                  + accessID+","+UserID+","+ createDate+","
                                  + ModuleID+","+CourseID+","+CertificateID+","
                                  + ProjectID+","+  OrgID+","+ip +","
                                  + UserTime+")";
                          LogUtil.debug("system", "[AccessDAOOracle] "+sql_str);
                          LogUtil.debug("system", "[AccessDAOOracle] "+"一共插入 "+totalCountInserted+"行");
                  }
                  catch (ULMSSysException se)
                  {
                          se.printStackTrace();
                          throw new AccessSysException(se);
                  }
                  finally
                  {
                          try
                          {
                          }
                          catch (SQLException se)
                          {
                                  se.printStackTrace();
                          }
                  }
          }
        */
}
