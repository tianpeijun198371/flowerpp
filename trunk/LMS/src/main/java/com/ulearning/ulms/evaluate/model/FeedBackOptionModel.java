/**
 * feedBackOptionModel.java.
 * User: yud Date: 2005-6-29 16:43:06
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.evaluate.model;

import java.util.Date;


public class FeedBackOptionModel
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

        public FeedBackOptionModel()
        {
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
