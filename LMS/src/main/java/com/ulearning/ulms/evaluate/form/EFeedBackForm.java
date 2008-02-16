/**
 * eFeedBackModel.java.
 * User: YuD Date: 2005-6-13 10:40:52
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.evaluate.form;

import com.ulearning.ulms.evaluate.model.FeedBackModel;

import org.apache.struts.action.ActionForm;

import java.util.Date;


public class EFeedBackForm extends ActionForm
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

        public EFeedBackForm()
        {
        }

        public FeedBackModel geteFeedBackModel()
        {
                FeedBackModel feedBackModel = new FeedBackModel();
                feedBackModel.setFeedbackID(this.feedbackID);
                feedBackModel.setAspID(this.aspID);
                feedBackModel.setParentID(this.parentID);
                feedBackModel.setStatus(this.status);
                feedBackModel.setUserID(this.userID);
                feedBackModel.setRelationID(this.relationID);
                feedBackModel.setRelationName(this.relationName);
                feedBackModel.setType(this.type);
                feedBackModel.setScore(this.score);
                feedBackModel.setTitle(this.title);
                feedBackModel.setFeedBack(this.feedBack);
                feedBackModel.setUserName(this.userName);
                feedBackModel.setIsAnonymous(this.IsAnonymous);
                feedBackModel.setEmail(this.email);
                feedBackModel.setSource(this.source);
                feedBackModel.setCreateDate(this.createDate);
                feedBackModel.setRemark(this.remark);
                feedBackModel.setRemark1(this.remark1);
                feedBackModel.setRemark2(this.remark2);
                feedBackModel.setRemark3(this.remark3);
                feedBackModel.setRemark4(this.remark4);

                return feedBackModel;
        }

        public EFeedBackForm geteFeedBackForm(FeedBackModel feedBackModel)
        {
                EFeedBackForm eFeedBackForm = new EFeedBackForm();
                eFeedBackForm.setFeedbackID(feedBackModel.getFeedbackID());
                eFeedBackForm.setAspID(feedBackModel.getAspID());
                eFeedBackForm.setParentID(feedBackModel.getParentID());
                eFeedBackForm.setStatus(feedBackModel.getStatus());
                eFeedBackForm.setUserID(feedBackModel.getUserID());
                eFeedBackForm.setRelationID(feedBackModel.getRelationID());
                eFeedBackForm.setRelationName(feedBackModel.getRelationName());
                eFeedBackForm.setType(feedBackModel.getType());
                eFeedBackForm.setScore(feedBackModel.getScore());
                eFeedBackForm.setTitle(feedBackModel.getTitle());
                eFeedBackForm.setFeedBack(feedBackModel.getFeedBack());
                eFeedBackForm.setUserName(feedBackModel.getUserName());
                eFeedBackForm.setIsAnonymous(feedBackModel.getIsAnonymous());
                eFeedBackForm.setEmail(feedBackModel.getEmail());
                eFeedBackForm.setSource(feedBackModel.getSource());
                eFeedBackForm.setCreateDate(feedBackModel.getCreateDate());
                eFeedBackForm.setRemark(feedBackModel.getRemark());
                eFeedBackForm.setRemark1(feedBackModel.getRemark1());
                eFeedBackForm.setRemark2(feedBackModel.getRemark2());
                eFeedBackForm.setRemark3(feedBackModel.getRemark3());
                eFeedBackForm.setRemark4(feedBackModel.getRemark4());

                return eFeedBackForm;
        }

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

        public String getRelationName()
        {
                return relationName;
        }

        public void setRelationName(String relationName)
        {
                this.relationName = relationName;
        }

        public String getType()
        {
                return type;
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
