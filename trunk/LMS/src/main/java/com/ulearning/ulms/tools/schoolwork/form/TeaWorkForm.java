/**
 * TeaWorkForm.java.
 * User: yud  Date: 2005-4-18
 *
 * Copyright (c) 2000-2005.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.tools.schoolwork.form;

import com.ulearning.ulms.tools.upload.model.UploadForm;
import com.ulearning.ulms.tools.schoolwork.model.TeaWorkModel;
import org.apache.struts.action.ActionForm;

import java.util.Date;

public class TeaWorkForm extends UploadForm
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

        public TeaWorkModel getTeaWorkModel()
        {
                TeaWorkModel teaWorkModel = new TeaWorkModel();
                teaWorkModel.setPostilSWId(this.postilSWId);
                teaWorkModel.setUserswId(this.userswId);
                teaWorkModel.setUserId(this.userId);
                teaWorkModel.setCommment(this.commment);
                teaWorkModel.setLink(this.link);
                teaWorkModel.setLinkTitle(this.linkTitle);
                teaWorkModel.setScore(this.score);
                teaWorkModel.setIsExisitError(this.isExisitError);
                teaWorkModel.setCreateDate(this.createDate);
                teaWorkModel.setModifyDate(this.modifyDate);

                return teaWorkModel;
        }

        public TeaWorkForm getTeaWorkForm(TeaWorkModel TeaWorkModel)
        {
                TeaWorkForm teaWorkForm = new TeaWorkForm();

                teaWorkForm.setUserswId(TeaWorkModel.getUserswId());
                teaWorkForm.setPostilSWId(TeaWorkModel.getPostilSWId());
                teaWorkForm.setUserId(TeaWorkModel.getUserId());
                teaWorkForm.setLink(TeaWorkModel.getLink());
                teaWorkForm.setLinkTitle(TeaWorkModel.getLinkTitle());
                teaWorkForm.setCommment(TeaWorkModel.getCommment());
                teaWorkForm.setScore(TeaWorkModel.getScore());
                teaWorkForm.setIsExisitError(TeaWorkModel.getIsExisitError());
                teaWorkForm.setCreateDate(TeaWorkModel.getCreateDate());
                teaWorkForm.setModifyDate(TeaWorkModel.getModifyDate());

                return teaWorkForm;
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

