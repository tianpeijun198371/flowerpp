/**
 * Created by IntelliJ IDEA.
 * Backup: xiejh
 * Date: Apr 8, 2004
 * Time: 9:36:03 AM
 * To change this template use Options | File Templates.
 */
package com.ulearning.ulms.tools.backup.dao;

import com.ulearning.ulms.core.exceptions.ULMSSysException;
import com.ulearning.ulms.tools.backup.exceptions.BackupDAOSysException;
import com.ulearning.ulms.tools.backup.form.BackupForm;
import com.ulearning.ulms.util.log.LogUtil;

import java.sql.Statement;


public class BackupDAOOracle extends BackupDAOImpl
{
        /**
         * Insert a new backup record to database
         * @param backupForm   the value object to be added
         * @throws BackupDAOSysException
         */

        /*
          public void addBackup(BackupForm backupForm) throws BackupDAOSysException
          {
                 java.sql.Date dayToInsert = new java.sql.Date(System.currentTimeMillis());
                 java.sql.Time timeToInsert = new java.sql.Time(System.currentTimeMillis());
                 String createDate  = "to_date('" + dayToInsert +" "+timeToInsert+ "','yyyy-mm-dd hh24:mi:ss')";
                 String sqlStr = "insert into T_Backup_Tab values(backupID.nextval,'" +
                          backupForm.getDbName() + "','" +
                          backupForm.getDbType() + "','" +
                          backupForm.getUsername() + "','" +
                          backupForm.getPassword() + "','" +
                          backupForm.getFilePath() + "','" +
                          backupForm.getOperateType() + "'," +
                          backupForm.getUserID() + "," +
                          createDate + ")";
                  try
                  {
                      LogUtil.debug("system", "[BackupDAOOracle]====================the sql string is : " + sqlStr);
                  } catch (ULMSSysException se)
                  {
                      throw new BackupDAOSysException("SQLException while addBackup; sqlStr = " + sqlStr + " :\n" + se);
                  }
          }
        */

        /**
         * Update backupInfo by the new Form
         * @param backupForm   value object for changed
         * @throws BackupDAOSysException
         * BackupID
         */

        /*
          public void updateBackup(BackupForm backupForm) throws BackupDAOSysException
          {
              java.sql.Date dayToInsert = new java.sql.Date(System.currentTimeMillis());
              java.sql.Time timeToInsert = new java.sql.Time(System.currentTimeMillis());
              String updateDate  = "to_date('" + dayToInsert +" "+timeToInsert+ "','yyyy-mm-dd hh24:mi:ss')";
              String sqlStr = "update T_Backup_Tab set " +
                      "DBName = '" + backupForm.getDbName() + "', " +
                      "DBType = '" + backupForm.getDbType() + "', " +
                      "Username = '" + backupForm.getUsername() + "', " +
                      "Password = '" + backupForm.getPassword() + "', " +
                      "FilePath = '" + backupForm.getFilePath() + "', " +
                      "OperateType = '" + backupForm.getOperateType() + "', " +
                      "UserID = " + backupForm.getUserID() + ", " +
                      "createDate = " + updateDate + " " +
                      "where BackupID = " + backupForm.getBackupID();
              try
              {
                      LogUtil.debug("system", "[BackupDAOOracle]====================the sql string is : " + sqlStr);

              } catch (ULMSSysException se)
              {
                  throw new BackupDAOSysException("SQLException while updateBackup; sqlStr = " + sqlStr + " :\n" + se);
              }
          }
        */
}
