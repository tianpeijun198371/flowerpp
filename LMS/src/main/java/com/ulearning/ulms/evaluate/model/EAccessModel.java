/**
 * EAccessModel.java.
 * User: YuD Date: 2005-6-10 15:02:52
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.evaluate.model;

import java.io.Serializable;

import java.util.Date;


public class EAccessModel implements Serializable
{
        private int accessID;
        private String accessType;
        private int accessDescription;
        private int userID;
        private int moduleID;
        private String type;
        private int parentID;
        private String IP;
        private Date accessDate;
        private String remark1;
        private String remark2;
        private String remark3;

        public EAccessModel()
        {
        }

        public int getAccessID()
        {
                return accessID;
        }

        public void setAccessID(int accessID)
        {
                this.accessID = accessID;
        }

        public String getAccessType()
        {
                return accessType;
        }

        public void setAccessType(String accessType)
        {
                this.accessType = accessType;
        }

        public int getAccessDescription()
        {
                return accessDescription;
        }

        public void setAccessDescription(int accessDescription)
        {
                this.accessDescription = accessDescription;
        }

        public int getUserID()
        {
                return userID;
        }

        public void setUserID(int userID)
        {
                this.userID = userID;
        }

        public int getModuleID()
        {
                return moduleID;
        }

        public void setModuleID(int moduleID)
        {
                this.moduleID = moduleID;
        }

        public String getType()
        {
                return type;
        }

        public void setType(String type)
        {
                this.type = type;
        }

        public int getParentID()
        {
                return parentID;
        }

        public void setParentID(int parentID)
        {
                this.parentID = parentID;
        }

        public String getIP()
        {
                return IP;
        }

        public void setIP(String IP)
        {
                this.IP = IP;
        }

        public Date getAccessDate()
        {
                return accessDate;
        }

        public void setAccessDate(Date accessDate)
        {
                this.accessDate = accessDate;
        }

        public String getRemark1()
        {
                return remark1;
        }

        public void setRemark1(String remark1)
        {
                this.remark1 = remark1;
        }

        public String getRemark2()
        {
                return remark2;
        }

        public void setRemark2(String remark2)
        {
                this.remark2 = remark2;
        }

        public String getRemark3()
        {
                return remark3;
        }

        public void setRemark3(String remark3)
        {
                this.remark3 = remark3;
        }
}
