/**
 * feedBackOptionForm.java.
 * User: YuD Date: 2005-6-29 16:42:52
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.evaluate.form;

import com.ulearning.ulms.evaluate.model.FeedBackOptionModel;

import org.apache.struts.action.ActionForm;

import java.util.Date;


public class FeedBackOptionForm extends ActionForm
{
        private int feedbackOptionID;
        private int relationID;
        private String type;
        private String status;
        private String isViewResult;
        private String isAnonymous;
        private Date effectiveDate;
        private Date expiredDate;
        private Date updateDate;
        private Date createDate;
        private String remark;
        private String remark1;
        private String remark2;
        private String remark3;
        private String remark4;

        public FeedBackOptionForm()
        {
        }

        public FeedBackOptionModel getfeedBackOptionModel()
        {
                FeedBackOptionModel feedBackOptionModel = new FeedBackOptionModel();
                feedBackOptionModel.setFeedbackOptionID(this.feedbackOptionID);
                feedBackOptionModel.setRelationID(this.relationID);
                feedBackOptionModel.setType(this.type);
                feedBackOptionModel.setStatus(this.status);
                feedBackOptionModel.setisViewResult(this.isViewResult);
                feedBackOptionModel.setisAnonymous(this.isAnonymous);
                feedBackOptionModel.setEffectiveDate(this.effectiveDate);
                feedBackOptionModel.setExpiredDate(this.expiredDate);
                feedBackOptionModel.setUpdateDate(this.updateDate);
                feedBackOptionModel.setCreateDate(this.createDate);
                feedBackOptionModel.setRemark(this.remark);
                feedBackOptionModel.setRemark1(this.remark1);
                feedBackOptionModel.setRemark2(this.remark2);
                feedBackOptionModel.setRemark3(this.remark3);
                feedBackOptionModel.setRemark4(this.remark4);

                return feedBackOptionModel;
        }

        public FeedBackOptionForm getfeedOptionForm(
                FeedBackOptionModel feedBackOptionModel)
        {
                FeedBackOptionForm feedBackOptionForm = new FeedBackOptionForm();

                feedBackOptionForm.setFeedbackOptionID(feedBackOptionModel.getFeedbackOptionID());
                feedBackOptionModel.setRelationID(feedBackOptionModel.getRelationID());
                feedBackOptionModel.setType(feedBackOptionModel.getType());
                feedBackOptionModel.setStatus(feedBackOptionModel.getStatus());
                feedBackOptionModel.setisViewResult(feedBackOptionModel.getisViewResult());
                feedBackOptionModel.setisAnonymous(feedBackOptionModel.getisAnonymous());
                feedBackOptionModel.setEffectiveDate(feedBackOptionModel.getEffectiveDate());
                feedBackOptionModel.setExpiredDate(feedBackOptionModel.getExpiredDate());
                feedBackOptionModel.setUpdateDate(feedBackOptionModel.getUpdateDate());
                feedBackOptionModel.setCreateDate(feedBackOptionModel.getCreateDate());
                feedBackOptionModel.setRemark(feedBackOptionModel.getRemark());
                feedBackOptionModel.setRemark1(feedBackOptionModel.getRemark1());
                feedBackOptionModel.setRemark2(feedBackOptionModel.getRemark2());
                feedBackOptionModel.setRemark3(feedBackOptionModel.getRemark3());
                feedBackOptionModel.setRemark4(feedBackOptionModel.getRemark4());

                return feedBackOptionForm;
        }

        public int getFeedbackOptionID()
        {
                return feedbackOptionID;
        }

        public void setFeedbackOptionID(int feedbackOptionID)
        {
                this.feedbackOptionID = feedbackOptionID;
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

        public void setType(String type)
        {
                this.type = type;
        }

        public String getStatus()
        {
                return status;
        }

        public void setStatus(String status)
        {
                this.status = status;
        }

        public String getisViewResult()
        {
                return isViewResult;
        }

        public void setisViewResult(String isViewResult)
        {
                this.isViewResult = isViewResult;
        }

        public String getisAnonymous()
        {
                return isAnonymous;
        }

        public void setisAnonymous(String isAnonymous)
        {
                this.isAnonymous = isAnonymous;
        }

        public Date getEffectiveDate()
        {
                return effectiveDate;
        }

        public void setEffectiveDate(Date effectiveDate)
        {
                this.effectiveDate = effectiveDate;
        }

        public Date getExpiredDate()
        {
                return expiredDate;
        }

        public void setExpiredDate(Date expiredDate)
        {
                this.expiredDate = expiredDate;
        }

        public Date getUpdateDate()
        {
                return updateDate;
        }

        public void setUpdateDate(Date updateDate)
        {
                this.updateDate = updateDate;
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
