/**
 * SchoolWorkForm.java.
 * User: yud  Date: 2005-4-12
 *
 * Copyright (c) 2000-2005.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.tools.schoolwork.form;

import com.ulearning.ulms.tools.schoolwork.model.SchoolWorkModel;
import com.ulearning.ulms.tools.upload.model.UploadForm;
import com.ulearning.ulms.core.util.DateTimeUtil;
import org.apache.struts.action.ActionForm;

import java.util.Date;

public class SchoolWorkForm extends UploadForm
{
        private int swID;
        private String userId;
        private String title;
        private String swKey;
        private String description;
        private String type;
        private int orderIndex;
        private String swLink;
        private String answerLink;
        private String answerLinkTitle;
        private String swLinkTitle;
        private String isAvailable;
        private String isPublishAnswer;
        private int parentID;
        private int relationID;
        private String relationName;
        private Date createDate;
        private Date modifyDate;
        private String isMessage;
        private String isMail;
        private String isUserful;
        private String isOpenGuest;
        private String isView;
        private int depth;
        private String status;
        private String displayBeginDate;
        private String displayEndDate;
        private String remark;
        private String remark1;
        private String remark2;
        private String remark3;
        private String remark4;
        private String remark5;
        private String remark6;

        public SchoolWorkModel getSchoolWorkModel()
        {
                SchoolWorkModel schoolWorkModel = new SchoolWorkModel();
                schoolWorkModel.setSwID(this.swID);
                schoolWorkModel.setUserId(this.userId);
                schoolWorkModel.setTitle(this.title);
                schoolWorkModel.setSwKey(this.swKey);
                schoolWorkModel.setDescription(this.description);
                schoolWorkModel.setType(this.type);
                schoolWorkModel.setOrderIndex(this.orderIndex);
                schoolWorkModel.setSwLink(this.swLink);
                schoolWorkModel.setAnswerLink(this.answerLink);
                schoolWorkModel.setAnswerLinkTitle(this.answerLinkTitle);
                schoolWorkModel.setSwLinkTitle(this.swLinkTitle);
                schoolWorkModel.setIsAvailable(this.isAvailable);
                schoolWorkModel.setIsPublishAnswer(this.isPublishAnswer);
                schoolWorkModel.setParentID(this.parentID);
                schoolWorkModel.setRelationID(this.relationID);
                schoolWorkModel.setRelationName(this.relationName);
                schoolWorkModel.setCreateDate(this.createDate);
                schoolWorkModel.setModifyDate(this.modifyDate);
                schoolWorkModel.setIsMessage(this.isMessage);
                schoolWorkModel.setIsMail(this.isMail);
                schoolWorkModel.setIsUserful(this.isUserful);
                schoolWorkModel.setIsOpenGuest(this.isOpenGuest);
                schoolWorkModel.setIsView(this.isView);
                schoolWorkModel.setDepth(this.depth);
                schoolWorkModel.setStatus(this.status);
                schoolWorkModel.setDisplayBeginDate(DateTimeUtil.parseDate(this.displayBeginDate));
                schoolWorkModel.setDisplayEndDate(DateTimeUtil.parseDate(this.displayEndDate));
                schoolWorkModel.setRemark(this.remark);
                schoolWorkModel.setRemark1(this.remark1);
                schoolWorkModel.setRemark2(this.remark2);
                schoolWorkModel.setRemark3(this.remark3);
                schoolWorkModel.setRemark4(this.remark4);
                schoolWorkModel.setRemark5(this.remark5);
                schoolWorkModel.setRemark6(this.remark6);
                return schoolWorkModel;
        }

        public SchoolWorkForm getSchoolWorkForm(SchoolWorkModel schoolWorkModel)
        {
                SchoolWorkForm schoolWorkForm = new SchoolWorkForm();
                schoolWorkForm.setSwID(schoolWorkModel.getSwID());
                schoolWorkForm.setUserId(schoolWorkModel.getUserId());
                schoolWorkForm.setTitle(schoolWorkModel.getTitle());
                schoolWorkForm.setCreateDate(schoolWorkModel.getCreateDate());
                schoolWorkForm.setSwKey(schoolWorkModel.getSwKey());
                schoolWorkForm.setDescription(schoolWorkModel.getDescription());
                schoolWorkForm.setType(schoolWorkModel.getType());
                schoolWorkForm.setOrderIndex(schoolWorkModel.getOrderIndex());
                schoolWorkForm.setSwLink(schoolWorkModel.getSwLink());
                schoolWorkForm.setIsUserful(schoolWorkModel.getIsUserful());
                schoolWorkForm.setAnswerLinkTitle(schoolWorkModel.getAnswerLinkTitle());
                schoolWorkForm.setSwLinkTitle(schoolWorkModel.getSwLinkTitle());
                schoolWorkForm.setModifyDate(schoolWorkModel.getModifyDate());
                schoolWorkForm.setParentID(schoolWorkModel.getParentID());
                schoolWorkForm.setRelationID(schoolWorkModel.getRelationID());
                schoolWorkForm.setRelationName(schoolWorkModel.getRelationName());
                schoolWorkForm.setAnswerLink(schoolWorkModel.getAnswerLink());
                schoolWorkForm.setStatus(schoolWorkModel.getStatus());
                schoolWorkForm.setIsAvailable(schoolWorkModel.getIsAvailable());
                schoolWorkForm.setIsPublishAnswer(schoolWorkModel.getIsPublishAnswer());
                schoolWorkForm.setIsMessage(schoolWorkModel.getIsMessage());
                schoolWorkForm.setIsMail(schoolWorkModel.getIsMail());
                schoolWorkForm.setView(schoolWorkModel.getIsView());
                schoolWorkForm.setIsOpenGuest(schoolWorkModel.getIsOpenGuest());
                schoolWorkForm.setDepth(schoolWorkModel.getDepth());
                schoolWorkForm.setDisplayBeginDate(DateTimeUtil.FormatDateToWeb1(schoolWorkModel.getDisplayBeginDate()));
                schoolWorkForm.setDisplayEndDate(DateTimeUtil.FormatDateToWeb1(schoolWorkModel.getDisplayEndDate()));
                schoolWorkForm.setRemark(schoolWorkModel.getRemark());
                schoolWorkForm.setRemark1(schoolWorkModel.getRemark1());
                schoolWorkForm.setRemark2(schoolWorkModel.getRemark2());
                schoolWorkForm.setRemark3(schoolWorkModel.getRemark3());
                schoolWorkForm.setRemark4(schoolWorkModel.getRemark4());
                schoolWorkForm.setRemark5(schoolWorkModel.getRemark5());
                schoolWorkForm.setRemark6(schoolWorkModel.getRemark6());

                return schoolWorkForm;
        }

        public int getSwID()
        {
                return swID;
        }

        public void setSwID(int swID)
        {
                this.swID = swID;
        }

        public String getUserId()
        {
                return userId;
        }

        public void setUserId(String userId)
        {
                this.userId = userId;
        }

        public String getTitle()
        {
                return title;
        }

        public void setTitle(String title)
        {
                this.title = title;
        }

        public String getSwKey()
        {
                return swKey;
        }

        public void setSwKey(String swKey)
        {
                this.swKey = swKey;
        }

        public String getDescription()
        {
                return description;
        }

        public void setDescription(String description)
        {
                this.description = description;
        }

        public String getType()
        {
                return type;
        }

        public void setType(String type)
        {
                this.type = type;
        }

        public int getOrderIndex()
        {
                return orderIndex;
        }

        public void setOrderIndex(int orderIndex)
        {
                this.orderIndex = orderIndex;
        }

        public String getSwLink()
        {
                return swLink;
        }

        public void setSwLink(String swLink)
        {
                this.swLink = swLink;
        }

        public String getAnswerLink()
        {
                return answerLink;
        }

        public void setAnswerLink(String answerLink)
        {
                this.answerLink = answerLink;
        }

        public String getAnswerLinkTitle()
        {
                return answerLinkTitle;
        }

        public void setAnswerLinkTitle(String answerLinkTitle)
        {
                this.answerLinkTitle = answerLinkTitle;
        }

        public String getSwLinkTitle()
        {
                return swLinkTitle;
        }

        public void setSwLinkTitle(String swLinkTitle)
        {
                this.swLinkTitle = swLinkTitle;
        }

        public String getIsAvailable()
        {
                return isAvailable;
        }

        public void setIsAvailable(String isAvailable)
        {
                this.isAvailable = isAvailable;
        }

        public String getIsPublishAnswer()
        {
                return isPublishAnswer;
        }

        public void setIsPublishAnswer(String isPublishAnswer)
        {
                this.isPublishAnswer = isPublishAnswer;
        }

        public int getParentID()
        {
                return parentID;
        }

        public void setParentID(int parentID)
        {
                this.parentID = parentID;
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

        public String getIsMessage()
        {
                return isMessage;
        }

        public void setIsMessage(String isMessage)
        {
                this.isMessage = isMessage;
        }

        public String getIsMail()
        {
                return isMail;
        }

        public void setIsMail(String isMail)
        {
                this.isMail = isMail;
        }

        public String getIsUserful()
        {
                return isUserful;
        }

        public void setIsUserful(String isUserful)
        {
                this.isUserful = isUserful;
        }

        public String getIsOpenGuest()
        {
                return isOpenGuest;
        }

        public void setIsOpenGuest(String isOpenGuest)
        {
                this.isOpenGuest = isOpenGuest;
        }

        public String getView()
        {
                return isView;
        }

        public void setView(String view)
        {
                this.isView = view;
        }

        public int getDepth()
        {
                return depth;
        }

        public void setDepth(int depth)
        {
                this.depth = depth;
        }

        public String getStatus()
        {
                return status;
        }

        public void setStatus(String status)
        {
                this.status = status;
        }

        public String getDisplayBeginDate()
        {
                return displayBeginDate;
        }

        public void setDisplayBeginDate(String displayBeginDate)
        {
                this.displayBeginDate = displayBeginDate;
        }

        public String getDisplayEndDate()
        {
                return displayEndDate;
        }

        public void setDisplayEndDate(String displayEndDate)
        {
                this.displayEndDate = displayEndDate;
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

        public String getRemark5()
        {
                return remark5;
        }

        public void setRemark5(String remark5)
        {
                this.remark5 = remark5;
        }

        public String getRemark6()
        {
                return remark6;
        }

        public void setRemark6(String remark6)
        {
                this.remark6 = remark6;
        }

}

