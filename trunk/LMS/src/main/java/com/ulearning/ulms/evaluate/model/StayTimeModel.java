/**
 * StayTimeModel.java.
 * User: fengch Date: 2005-6-10 15:03:06
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.evaluate.model;

import java.util.Date;


public class StayTimeModel
{
        private int stayTimeID;
        private long stayTime;
        private int userID;
        private int moduleID;
        private String type;
        private int parentID;
        private Date createDate;
        private Date lastUpdateDate;
        private String remark1;
        private String remark2;
        private String remark3;

        public StayTimeModel()
        {
        }

        public int getStayTimeID()
        {
                return stayTimeID;
        }

        public void setStayTimeID(int stayTimeID)
        {
                this.stayTimeID = stayTimeID;
        }

        public long getStayTime()
        {
                return stayTime;
        }

        public void setStayTime(long stayTime)
        {
                this.stayTime = stayTime;
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

        public Date getCreateDate()
        {
                return createDate;
        }

        public void setCreateDate(Date createDate)
        {
                this.createDate = createDate;
        }

        public Date getLastUpdateDate()
        {
                return lastUpdateDate;
        }

        public void setLastUpdateDate(Date lastUpdateDate)
        {
                this.lastUpdateDate = lastUpdateDate;
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
