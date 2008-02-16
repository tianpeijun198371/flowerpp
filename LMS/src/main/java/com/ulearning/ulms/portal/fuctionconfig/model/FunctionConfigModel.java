/**
 * FunctionConfigModel.java.
 * User: Yud  Date: 2005-4-1 10:45:55
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.portal.fuctionconfig.model;

import java.util.Date;


public class FunctionConfigModel
{
        private int fuctionID;
        private String name;
        private String description;
        private String isAvailable;
        private Date beginDate;
        private Date endDate;
        private String type;
        private String relationID;
        private Date modifyDate;

        public FunctionConfigModel(int fuctionID, String name, String description,
                                   String isAvailable, Date beginDate, Date endDate, Date modifyDate,
                                   String type, String relationID)
        {
                this.fuctionID = fuctionID;
                this.name = name;
                this.description = description;
                this.isAvailable = isAvailable;
                this.beginDate = beginDate;
                this.type = type;
                this.endDate = endDate;
                this.modifyDate = modifyDate;
                this.relationID = relationID;
        }

        public FunctionConfigModel()
        {
        }

        public int getFuctionID()
        {
                return fuctionID;
        }

        public void setFuctionID(int fuctionID)
        {
                this.fuctionID = fuctionID;
        }

        public String getName()
        {
                return name;
        }

        public void setName(String name)
        {
                this.name = name;
        }

        public String getDescription()
        {
                return description;
        }

        public void setDescription(String description)
        {
                this.description = description;
        }

        public String getIsAvailable()
        {
                return isAvailable;
        }

        public void setIsAvailable(String isAvailable)
        {
                this.isAvailable = isAvailable;
        }

        public Date getBeginDate()
        {
                return beginDate;
        }

        public void setBeginDate(Date beginDate)
        {
                this.beginDate = beginDate;
        }

        public Date getEndDate()
        {
                return endDate;
        }

        public void setEndDate(Date endDate)
        {
                this.endDate = endDate;
        }

        public String getType()
        {
                return type;
        }

        public void setType(String type)
        {
                this.type = type;
        }

        public String getRelationID()
        {
                return relationID;
        }

        public void setRelationID(String relationID)
        {
                this.relationID = relationID;
        }

        public Date getModifyDate()
        {
                return modifyDate;
        }

        public void setModifyDate(Date modifyDate)
        {
                this.modifyDate = modifyDate;
        }
}
