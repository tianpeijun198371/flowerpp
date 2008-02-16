/**
 * Created by IntelliJ IDEA.
 * Delete: dengj
 * Date: Apr 8, 2004
 * Time: 9:36:03 AM
 * To change this template use Options | File Templates.
 */
package com.ulearning.ulms.tools.delete.dao;

import com.ulearning.ulms.core.exceptions.ULMSSysException;
import com.ulearning.ulms.tools.delete.exceptions.DeleteDAOSysException;
import com.ulearning.ulms.tools.delete.form.DeleteForm;
import com.ulearning.ulms.util.log.LogUtil;

import java.sql.Statement;


public class DeleteDAOOracle extends DeleteDAOImpl
{
        /**
         * Insert a new delete record to database
         * @param deleteForm   the value object to be added
         * @throws DeleteDAOSysException
         */

        /*
          public void addDelete(DeleteForm deleteForm) throws DeleteDAOSysException
          {
                 java.sql.Date dayToInsert = new java.sql.Date(System.currentTimeMillis());
                 java.sql.Time timeToInsert = new java.sql.Time(System.currentTimeMillis());
                 String createDate  = "to_date('" + dayToInsert +" "+timeToInsert+ "','yyyy-mm-dd hh24:mi:ss')";
                 String sqlStr = "insert into T_Delete_Tab values(deleteID.nextval," +
                          deleteForm.getRelationID() + ",'" +
                          deleteForm.getRelationType() + "','" +
                          deleteForm.getState() + "','" +
                          deleteForm.getObjectType() + "'," +
                          deleteForm.getSaveTimeNum() + ",'" +
                          deleteForm.getTimeType() + "'," +
                          deleteForm.getSaveRows() + "," +
                          deleteForm.getUserID() + "," +
                          createDate + ")";
                  try
                  {
                      LogUtil.debug("system", "[DeleteDAOOracle]====================the sql string is : " + sqlStr);
                  } catch (ULMSSysException se)
                  {
                      throw new DeleteDAOSysException("SQLException while addDelete; sqlStr = " + sqlStr + " :\n" + se);
                  }
          }
        */

        /**
         * Update deleteInfo by the new Form
         * @param deleteForm   value object for changed
         * @throws DeleteDAOSysException
         */

        /*
          public void updateDelete(DeleteForm deleteForm) throws DeleteDAOSysException
          {
              java.sql.Date dayToInsert = new java.sql.Date(System.currentTimeMillis());
              java.sql.Time timeToInsert = new java.sql.Time(System.currentTimeMillis());
              String updateDate  = "to_date('" + dayToInsert +" "+timeToInsert+ "','yyyy-mm-dd hh24:mi:ss')";
              String sqlStr = "update T_Delete_Tab set " +
                      "RelationID = " + deleteForm.getRelationID() + ", " +
                      "RelationType = '" + deleteForm.getRelationType() + "', " +
                      "State = '" + deleteForm.getState() + "', " +
                      "ObjectType = '" + deleteForm.getObjectType() + "', " +
                      "SaveTimeNum = " + deleteForm.getSaveTimeNum() + ", " +
                      "TimeType = '" + deleteForm.getTimeType() + "', " +
                      "SaveRows = " + deleteForm.getSaveRows() + ", " +
                      "UserID = " + deleteForm.getUserID() + " ," +
                      "updateDate = " + updateDate + " " +
                      "where DeleteID = " + deleteForm.getDeleteID();
              try
              {
                      LogUtil.debug("system", "[DeleteDAOOracle]====================the sql string is : " + sqlStr);

              } catch (ULMSSysException se)
              {
                  throw new DeleteDAOSysException("SQLException while updateDelete; sqlStr = " + sqlStr + " :\n" + se);
              }
          }
        */
}
