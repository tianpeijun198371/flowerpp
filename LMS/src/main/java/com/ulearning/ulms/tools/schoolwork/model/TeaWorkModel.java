/**
 * creater: yud
 * Copyright (c) 2000-2005.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.tools.schoolwork.model;

import java.io.Serializable;
import java.util.Date;

public class TeaWorkModel implements Serializable
{
        private int postilSWId;
        private int userswId;
        private int userId;
        private String link;
        private String linkTitle;
        private String commment;
        private String score;
        private String isExisitError;
        private Date createDate;
        private Date modifyDate;


        public TeaWorkModel()
        {

        }

        public int getPostilSWId()
        {
                return postilSWId;
        }

        public void setPostilSWId(int postilSWId)
        {
                this.postilSWId = postilSWId;
        }

        public int getUserswId()
        {
                return userswId;
        }

        public void setUserswId(int userswId)
        {
                this.userswId = userswId;
        }

        public int getUserId()
        {
                return userId;
        }

        public void setUserId(int userId)
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

        public String getCommment()
        {
                return commment;
        }

        public void setCommment(String commment)
        {
                this.commment = commment;
        }

        public String getScore()
        {
                return score;
        }

        public void setScore(String score)
        {
                this.score = score;
        }

        public String getIsExisitError()
        {
                return isExisitError;
        }

        public void setIsExisitError(String isExisitError)
        {
                this.isExisitError = isExisitError;
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

