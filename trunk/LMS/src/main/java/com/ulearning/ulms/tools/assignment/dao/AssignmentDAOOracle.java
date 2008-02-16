/** * AssignmentDAOOracle.java.
 * User: xiejh  Date: 2004-4-22 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.tools.assignment.dao;

import com.ulearning.ulms.core.exceptions.ULMSSysException;
import com.ulearning.ulms.tools.assignment.exceptions.AssignmentDAOSysException;
import com.ulearning.ulms.tools.assignment.form.AssignmentForm;
import com.ulearning.ulms.user.exceptions.UserDAOSysException;
import com.ulearning.ulms.util.log.LogUtil;

import java.io.*;

import java.sql.SQLException;
import java.sql.Statement;


public class AssignmentDAOOracle extends AssignmentDAOImpl
{
        /**
         * Insert a new assignment record to database
         * @param assignmentForm   the value object to be added
         * @throws AssignmentDAOSysException
         */

        /*
          public int addAssignment(AssignmentForm assignmentForm) throws AssignmentDAOSysException
          {
                  java.sql.Date dayToInsert = new java.sql.Date(System.currentTimeMillis());
                  String disAfterTime = "to_date('" + assignmentForm.getDisAfterTime() + "','yyyy-mm-dd')";
                  String disUntilTime = "to_date('" + assignmentForm.getDisUntilTime() + "','yyyy-mm-dd')";
                  String createTime  = "to_date('" + dayToInsert + "','yyyy-mm-dd')";
                  String rootID="";
                  if (assignmentForm.getParentID()==0)
                  {
                            rootID="assignmentID.nextval";
                  }
                  else
                  {
                            rootID=String.valueOf(assignmentForm.getRootID());
                  }
                  String sqlStr = "insert into T_Assignment_Tab values(assignmentID.nextval," +
                          assignmentForm.getCourseID() + "," +
                          assignmentForm.getParentID() + "," +
                          rootID + "," +
                          assignmentForm.getOrderIndex() + ",'" +
                          assignmentForm.getName() + "','" +
                          assignmentForm.getDescription() + "','" +
                          assignmentForm.getViewable() + "','" +
                          assignmentForm.getLinkFileUrl() + "','" +
                          assignmentForm.getLinkFileName() + "','" +
                          assignmentForm.getType() + "','" +
                          assignmentForm.getFileType() + "','" +
                          assignmentForm.getTrackNumber() + "'," +
                          assignmentForm.getViewNumber() + ",'" +
                          assignmentForm.getAvailable() + "'," +
                          createTime + "," +
                          disAfterTime + "," +
                          disUntilTime + ")";
                      {
                      }
                  try
                  {
                      LogUtil.debug("system", "[AssignmentDAOOracle]====================the sql string is : " + sqlStr);
                  }
                  catch (ULMSSysException se)
                  {
                          se.printStackTrace();
                          throw new AssignmentDAOSysException("SQLException while updating " + "assignment; Serial = " + assignmentForm.getName() + " :\n" + se);
                  }
          }
        */

        /**
         * Update assignmentInfo by the new Form
         * @param assignmentForm   value object for changed
         * @throws AssignmentDAOSysException
         */

        /*
          public void updateAssignment(AssignmentForm assignmentForm) throws AssignmentDAOSysException
          {
                  String disAfterTime = "to_date('" + assignmentForm.getDisAfterTime() + "','yyyy-mm-dd')";
                  String disUntilTime = "to_date('" + assignmentForm.getDisUntilTime() + "','yyyy-mm-dd')";
                  String sqlStr = "update T_Assignment_Tab set " +
                      "CourseID = " + assignmentForm.getCourseID() + ", " +
                      "ParentID = " + assignmentForm.getParentID() + ", " +
                      "RootID = " + assignmentForm.getRootID() + ", " +
                      "OrderIndex = " + assignmentForm.getOrderIndex() + ", " +
                      "Name = '" + assignmentForm.getName() + "', " +
                      "Description = '" + assignmentForm.getDescription() + "', " +
                      "IsViewable = '" + assignmentForm.getViewable() + "', " +
                      "LinkFileUrl = '" + assignmentForm.getLinkFileUrl() + "', " +
                      "LinkFileName = '" + assignmentForm.getLinkFileName() + "', " +
                      "Type = '" + assignmentForm.getType() + "', " +
                      "FileType = '" + assignmentForm.getFileType() + "', " +
                      "IsTrackNumber = '" + assignmentForm.getTrackNumber() + "', " +
                      "ViewNumber = " + assignmentForm.getViewNumber() + ", " +
                      "IsAvailable = '" + assignmentForm.getAvailable() + "', " +
                      "DisAfterTime = " + disAfterTime + ", " +
                      "DisUntilTime = " + disUntilTime + " " +
                      "where AssignmentID = " + assignmentForm.getAssignmentID();
                  {
                  }
                  try
          {
                  LogUtil.debug("system", "[AssignmentDAOOracle]====================the sql string is : " + sqlStr);
          } catch (ULMSSysException se)
          {
                  se.printStackTrace();
                  throw new AssignmentDAOSysException("SQLException while updating " + "account; Serial = "
                      + assignmentForm.getName() + " :\n" + se);
          }
          }
        */
}
