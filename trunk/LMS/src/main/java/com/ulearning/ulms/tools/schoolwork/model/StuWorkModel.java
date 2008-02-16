/**
 * creater: yud
 * Copyright (c) 2000-2005.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.tools.schoolwork.model;

import java.io.Serializable;
import java.util.Date;

public class StuWorkModel implements Serializable
{
        private int userswId;
        private int swId;
        private String userId;
        private String orgID;
        private String link;
        private String linkTitle;
        private String description;
        private Date createDate;
        private Date modifyDate;


        public StuWorkModel()
        {

        }

        public int getUserswId()
        {
                return userswId;
        }

        public void setUserswId(int userswId)
        {
                this.userswId = userswId;
        }

        public int getSwId()
        {
                return swId;
        }

        public void setSwId(int swId)
        {
                this.swId = swId;
        }

        public String getUserId()
        {
                return userId;
        }

        public void setOrgID(String orgID)
        {
                this.orgID = orgID;
        }

        public String getOrgID()
        {
                return orgID;
        }

        public void setUserId(String userId)
        {
                this.userId = userId;
        }

        public String getLink()
        {
                return link;
        }

        public void setLink(String link)
        {
                this.link = link;
        }

        public String getLinkTitle()
        {
                return linkTitle;
        }

        public void setLinkTitle(String linkTitle)
        {
                this.linkTitle = linkTitle;
        }

        public String getDescription()
        {
                return description;
        }

        public void setDescription(String description)
        {
                this.description = description;
        }

        public Date getCreateDate()
        {
                return createDate;
        }

        public void setCreateDate(Date createDate)
        {
                this.createDate = createDate;
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

