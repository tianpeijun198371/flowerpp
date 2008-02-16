/** * AssignProcessDAOOracle.java.
 * User: xiejh  Date: 2004-4-22 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.tools.assignment.assignprocess.dao;

import com.ulearning.ulms.core.exceptions.ULMSSysException;
import com.ulearning.ulms.tools.assignment.assignprocess.exceptions.AssignProcessDAOSysException;
import com.ulearning.ulms.tools.assignment.assignprocess.form.AssignProcessForm;
import com.ulearning.ulms.user.exceptions.UserDAOSysException;
import com.ulearning.ulms.util.log.LogUtil;

import java.io.*;

import java.sql.SQLException;
import java.sql.Statement;


public class AssignProcessDAOOracle extends AssignProcessDAOImpl
{
        /**
         * Insert a new assignment record to database
         * @param assignProcessForm   the value object to be added
         * @throws AssignProcessDAOSysException
         */

        /*
          public void addAssignProcess(AssignProcessForm assignProcessForm) throws AssignProcessDAOSysException
          {
                  java.sql.Date dayToInsert = new java.sql.Date(System.currentTimeMillis());
                  java.sql.Time timeToInsert = new java.sql.Time(System.currentTimeMillis());
                  String createDate  = "to_date('" + dayToInsert +" "+timeToInsert+ "','yyyy-mm-dd hh24:mi:ss')";
                  String sqlStr = "insert into U_AssignProcess_Tab values(AssignProcessID.nextval," +
                          assignProcessForm.getRelationID() + "," +
                          assignProcessForm.getRelationType() + "," +
                          assignProcessForm.getState() + ",'" +
                          assignProcessForm.getUserID() + "'," +
                          createDate + "," +
                          "'','" +     //Submit_datetime
                          assignProcessForm.getStuRemark() + "','" +
                          assignProcessForm.getAttach_path_stu() + "'," +
                          "'','" +   // Update_datetime
                          assignProcessForm.getScore() + "','" +
                          assignProcessForm.getTeaRemark() + "','" +
                          assignProcessForm.getAttach_path_tea()+"')";
                      {
                      }
                  try
                  {
                      LogUtil.debug("system", "[AssignProcessDAOOracle]====================the sql string is : " + sqlStr);
                  }
                  catch (ULMSSysException se)
                  {
                          se.printStackTrace();
                          throw new AssignProcessDAOSysException("SQLException while updating " + "assignment; sqlStr = " + sqlStr + " :\n" + se);
                  }
          }
        */

        /**
         * Update assignmentInfo by the new Form
         * @param assignProcessForm   value object for changed
         * @throws AssignProcessDAOSysException
         */

        /*
          public void updateAssignProcess(AssignProcessForm assignProcessForm) throws AssignProcessDAOSysException
          {
                  java.sql.Date dayToInsert = new java.sql.Date(System.currentTimeMillis());
                  java.sql.Time timeToInsert = new java.sql.Time(System.currentTimeMillis());
                  String createDate  = "to_date('" + dayToInsert +" "+timeToInsert+ "','yyyy-mm-dd hh24:mi:ss')";
                  String Submit_datetime= "";
                  if (assignProcessForm.getFlag().equals("2"))
                  {
                      Submit_datetime="Submit_datetime ="+createDate+",";
                  }
                  String Update_datetime= "";
                  if (assignProcessForm.getFlag().equals("3"))
                  {
                          Update_datetime="Update_datetime ="+createDate+",";
                  }
                  String sqlStr = "update U_AssignProcess_Tab set " +
                      "RelationID = " + assignProcessForm.getRelationID() + ", " +
                      "RelationType = " + assignProcessForm.getRelationType() + ", " +
                      "State = " + assignProcessForm.getState() + ", " +
                      "UserID = " + assignProcessForm.getUserID() + ", " +
                       Submit_datetime +
                      "StuRemark = '" + assignProcessForm.getStuRemark() + "', " +
                      "Attach_path_stu = '" + assignProcessForm.getAttach_path_stu() + "', " +
                       Update_datetime +
                      "Score = '" + assignProcessForm.getScore() + "', " +
                      "TeaRemark = '" + assignProcessForm.getTeaRemark() + "', " +
                      "Attach_path_tea = '" + assignProcessForm.getAttach_path_tea() + "' "+
                      "where AssignProcessID = " + assignProcessForm.getAssignProcessID();
                  {
                  }
                  try
          {
                  LogUtil.debug("system", "[AssignProcessDAOOracle]====================the sql string is : " + sqlStr);
          } catch (ULMSSysException se)
          {
                  se.printStackTrace();
                  throw new AssignProcessDAOSysException("SQLException while updating AssignProcess   AssignProcessID= "
                      + assignProcessForm.getAssignProcessID() + " :\n" + se);
          }
          }
        */
}
