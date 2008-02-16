/**
 * LmslogForm.java.
 * User: keyh  Date: 2004-8-18
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.lmslog.form;

import com.ulearning.ulms.lmslog.model.LmslogModel;

import org.apache.struts.action.ActionForm;

import java.util.Date;


public class LmslogForm extends ActionForm
{
        private int logID;
        private int logTypeID;
        private int userID;
        private int orgID;
        private String userIP;
        private int operationTypeID;
        private String operationTable;
        private int operationObjectID;
        private Date operationTime;
        private String description;
        private String userLoginName;
        private String userName;
        private String operationName;
        private String operationDesc;
        private String orgName;

        public LmslogForm()
        {
        }

        public LmslogForm(int logID, int logTypeID, int userID, int orgID,
                          String userIP, int operationTypeID, String operationTable,
                          int operationObjectID, Date operationTime, String description,
                          String userLoginName, String userName, String operationName,
                          String operationDesc, String orgName)
        {
                this.logID = logID;
                this.logTypeID = logTypeID;
                this.userID = userID;
                this.orgID = orgID;
                this.userIP = userIP;
                this.operationTypeID = operationTypeID;
                this.operationTable = operationTable;
                this.operationObjectID = operationObjectID;
                this.operationTime = operationTime;
                this.description = description;

                this.userLoginName = userLoginName;
                this.userName = userName;
                this.operationName = operationName;
                this.operationDesc = operationDesc;
                this.orgName = orgName;
        }

        public LmslogModel getLmslogModel()
        {
                LmslogModel lm = new LmslogModel();
                lm.setLogid(this.logID);
                lm.setLogtypeid(this.logTypeID);
                lm.setUserid(this.userID);
                lm.setOrgid(this.orgID);
                lm.setUserip(this.userIP);
                lm.setOperationtypeid(this.operationTypeID);
                lm.setOperationtable(this.operationTable);
                lm.setOperationobjectid(this.operationObjectID);
                lm.setOperationtime(this.operationTime);
                lm.setDescription(this.description);

                return lm;
        }

        public int getLogID()
        {
                return logID;
        }

        public void setLogID(int logID)
        {
                this.logID = logID;
        }

        public int getLogTypeID()
        {
                return logTypeID;
        }

        public void setLogTypeID(int logTypeID)
        {
                this.logTypeID = logTypeID;
        }

        public int getUserID()
        {
                return userID;
        }

        public void setUserID(int userID)
        {
                this.userID = userID;
        }

        public int getOrgID()
        {
                return orgID;
        }

        public void setOrgID(int orgID)
        {
                this.orgID = orgID;
        }

        public String getUserIP()
        {
                return userIP;
        }

        public void setUserIP(String userIP)
        {
                this.userIP = userIP;
        }

        public int getOperationTypeID()
        {
                return operationTypeID;
        }

        public void setOperationTypeID(int operationTypeID)
        {
                this.operationTypeID = operationTypeID;
        }

        public String getOperationTable()
        {
                return operationTable;
        }

        public void setOperationTable(String operationTable)
        {
                this.operationTable = operationTable;
        }

        public int getOperationObjectID()
        {
                return operationObjectID;
        }

        public void setOperationObjectID(int operationObjectID)
        {
                this.operationObjectID = operationObjectID;
        }

        public Date getOperationTime()
        {
                return operationTime;
        }

        public void setOperationTime(Date operationTime)
        {
                this.operationTime = operationTime;
        }

        public String getDescription()
        {
                return description;
        }

        public void setDescription(String description)
        {
                this.description = description;
        }

        public String getUserLoginName()
        {
                return userLoginName;
        }

        public void setUserLoginName(String userLoginName)
        {
                this.userLoginName = userLoginName;
        }

        public String getUserName()
        {
                return userName;
        }

        public void setUserName(String userName)
        {
                this.userName = userName;
        }

        public String getOperationName()
        {
                return operationName;
        }

        public void setOperationName(String operationName)
        {
                this.operationName = operationName;
        }

        public String getOperationDesc()
        {
                return operationDesc;
        }

        public void setOperationDesc(String operationDesc)
        {
                this.operationDesc = operationDesc;
        }

        public String getOrgName()
        {
                return orgName;
        }

        public void setOrgName(String orgName)
        {
                this.orgName = orgName;
        }
}
