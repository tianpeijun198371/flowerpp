package com.ulearning.ulms.tools.backup.model;

import org.apache.commons.lang.builder.ToStringBuilder;

import java.io.Serializable;

import java.util.Date;


/**
 * @author Hibernate CodeGenerator
 */
public class BackupModel implements Serializable
{
        /**
         * identifier field
         */
        private int backupID;

        /**
         * nullable persistent field
         */
        private String dbName;

        /**
         * nullable persistent field
         */
        private String dbType;

        /**
         * nullable persistent field
         */
        private String username;

        /**
         * nullable persistent field
         */
        private String password;

        /**
         * nullable persistent field
         */
        private String filePath;

        /**
         * nullable persistent field
         */
        private String operateType;

        /**
         * nullable persistent field
         */
        private int userID;

        /**
         * nullable persistent field
         */
        private Date createDate;

        /**
         * full constructor
         */
        public BackupModel(String dbName, String dbType, String username,
                           String password, String filePath, String operateType, int userID,
                           Date createDate)
        {
                this.dbName = dbName;
                this.dbType = dbType;
                this.username = username;
                this.password = password;
                this.filePath = filePath;
                this.operateType = operateType;
                this.userID = userID;
                this.createDate = createDate;
        }

        /**
         * default constructor
         */
        public BackupModel()
        {
        }

        public int getBackupID()
        {
                return this.backupID;
        }

        public void setBackupID(int backupID)
        {
                this.backupID = backupID;
        }

        public String getDbName()
        {
                return this.dbName;
        }

        public void setDbName(String dbName)
        {
                this.dbName = dbName;
        }

        public String getDbType()
        {
                return this.dbType;
        }

        public void setDbType(String dbType)
        {
                this.dbType = dbType;
        }

        public String getUsername()
        {
                return this.username;
        }

        public void setUsername(String username)
        {
                this.username = username;
        }

        public String getPassword()
        {
                return this.password;
        }

        public void setPassword(String password)
        {
                this.password = password;
        }

        public String getFilePath()
        {
                return this.filePath;
        }

        public void setFilePath(String filePath)
        {
                this.filePath = filePath;
        }

        public String getOperateType()
        {
                return this.operateType;
        }

        public void setOperateType(String operateType)
        {
                this.operateType = operateType;
        }

        public int getUserID()
        {
                return this.userID;
        }

        public void setUserID(int userID)
        {
                this.userID = userID;
        }

        public Date getCreateDate()
        {
                return this.createDate;
        }

        public void setCreateDate(Date createDate)
        {
                this.createDate = createDate;
        }

        public String toString()
        {
                return new ToStringBuilder(this).append("backupID", getBackupID())
                        .toString();
        }
}
