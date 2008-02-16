/**
 * Created by IntelliJ IDEA.
 * Backup: xiejh
 * Date: Apr 7, 2004
 * Time: 4:51:49 PM
 * To change this template use Options | File Templates.
 */
package com.ulearning.ulms.tools.backup.form;

import com.ulearning.ulms.tools.backup.model.BackupModel;

import org.apache.struts.action.ActionForm;

import java.util.Date;


public class BackupForm extends ActionForm
{
        private int backupID = 0;
        private String dbName = null;
        private String dbType = null;
        private String username = null;
        private String password = null;
        private String filePath = null;
        private String operateType = null;
        private int userID = 0;
        private Date createDate = null;
        private BackupModel backupModel = null;

        public BackupModel getBackupModel()
        {
                backupModel = new BackupModel();
                backupModel.setBackupID(this.backupID);
                backupModel.setDbName(this.dbName);
                backupModel.setDbType(this.dbType);
                backupModel.setUsername(this.username);
                backupModel.setPassword(this.password);
                backupModel.setFilePath(this.filePath);
                backupModel.setOperateType(this.operateType);
                backupModel.setUserID(this.userID);
                backupModel.setCreateDate(this.createDate);

                return backupModel;
        }

        public BackupForm getBackupForm(BackupModel backupModel)
        {
                BackupForm backupForm = new BackupForm();
                backupForm.setBackupID(backupModel.getBackupID());
                backupForm.setDbName(backupModel.getDbName());
                backupForm.setDbType(backupModel.getDbType());
                backupForm.setUsername(backupModel.getUsername());
                backupForm.setPassword(backupModel.getPassword());
                backupForm.setFilePath(backupModel.getFilePath());
                backupForm.setOperateType(backupModel.getOperateType());
                backupForm.setUserID(backupModel.getUserID());
                backupForm.setCreateDate(backupModel.getCreateDate());

                return backupForm;
        }

        public int getBackupID()
        {
                return backupID;
        }

        public void setBackupID(int backupID)
        {
                this.backupID = backupID;
        }

        public String getDbName()
        {
                return dbName;
        }

        public void setDbName(String dbName)
        {
                this.dbName = dbName;
        }

        public String getDbType()
        {
                return dbType;
        }

        public void setDbType(String dbType)
        {
                this.dbType = dbType;
        }

        public String getUsername()
        {
                return username;
        }

        public void setUsername(String username)
        {
                this.username = username;
        }

        public String getPassword()
        {
                return password;
        }

        public void setPassword(String password)
        {
                this.password = password;
        }

        public String getFilePath()
        {
                return filePath;
        }

        public void setFilePath(String filePath)
        {
                this.filePath = filePath;
        }

        public String getOperateType()
        {
                return operateType;
        }

        public void setOperateType(String operateType)
        {
                this.operateType = operateType;
        }

        public int getUserID()
        {
                return userID;
        }

        public void setUserID(int userID)
        {
                this.userID = userID;
        }

        public Date getCreateDate()
        {
                return createDate;
        }

        public void setCreateDate(Date createDate)
        {
                this.createDate = createDate;
        }
}
