/**
 * ERecordModel.java.
 * User: fengch Date: 2005-6-13 11:33:26
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.evaluate.model;

import java.util.Date;
import java.io.Serializable;


public class FeedBackModel  implements Serializable
{
        private int feedbackID;
        private int aspID;
        private int parentID;
        private int status;
        private int userID;
        private int relationID;
        private String relationName;
        private String type;
        private String score;
        private String title;
        private String feedBack;
        private String userName;
        private String IsAnonymous;
        private String email;
        private String source;
        private Date createDate;
        private String remark;
        private String remark1;
        private String remark2;
        private String remark3;
        private String remark4;

        public int getFeedbackID()
        {
                return feedbackID;
        }

        public void setFeedbackID(int feedbackID)
        {
                this.feedbackID = feedbackID;
        }

        public int getAspID()
        {
                return aspID;
        }

        public void setAspID(int aspID)
        {
                this.aspID = aspID;
        }

        public int getParentID()
        {
                return parentID;
        }

        public void setParentID(int parentID)
        {
                this.parentID = parentID;
        }

        public int getStatus()
        {
                return status;
        }

        public void setStatus(int status)
        {
                this.status = status;
        }

        public int getUserID()
        {
                return userID;
        }

        public void setUserID(int userID)
        {
                this.userID = userID;
        }

        public int getRelationID()
        {
                return relationID;
        }

        public void setRelationID(int relationID)
        {
                this.relationID = relationID;
        }

        public String getType()
        {
                return type;
        }

        public String getRelationName()
        {
                return relationName;
        }

        public void setRelationName(String relationName)
        {
                this.relationName = relationName;
        }

        public void setType(String type)
        {
                this.type = type;
        }

        public String getScore()
        {
                return score;
        }

        public void setScore(String score)
        {
                this.score = score;
        }

        public String getTitle()
        {
                return title;
        }

        public void setTitle(String title)
        {
                this.title = title;
        }

        public String getFeedBack()
        {
                return feedBack;
        }

        public void setFeedBack(String feedBack)
        {
                this.feedBack = feedBack;
        }

        public String getUserName()
        {
                return userName;
        }

        public void setUserName(String userName)
        {
                this.userName = userName;
        }

        public String getIsAnonymous()
        {
                return IsAnonymous;
        }

        public void setIsAnonymous(String isAnonymous)
        {
                IsAnonymous = isAnonymous;
        }

        public String getEmail()
        {
                return email;
        }

        public void setEmail(String email)
        {
                this.email = email;
        }

        public String getSource()
        {
                return source;
        }

        public void setSource(String source)
        {
                this.source = source;
        }

        public Date getCreateDate()
        {
                return createDate;
        }

        public void setCreateDate(Date createDate)
        {
                this.createDate = createDate;
        }

        public String getRemark()
        {
                return remark;
        }

        public void setRemark(String remark)
        {
                this.remark = remark;
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

        public String getRemark4()
        {
                return remark4;
        }

        public void setRemark4(String remark4)
        {
                this.remark4 = remark4;
        }
}
