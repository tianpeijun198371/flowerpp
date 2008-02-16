/**
 * StuWorkForm.java.
 * User: yud  Date: 2005-4-13
 *
 * Copyright (c) 2000-2005.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.tools.schoolwork.form;

import com.ulearning.ulms.tools.schoolwork.model.StuWorkModel;
import com.ulearning.ulms.tools.upload.model.UploadForm;
import org.apache.struts.action.ActionForm;

import java.util.Date;

public class StuWorkForm extends UploadForm
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

        public StuWorkModel getStuWorkModel()
        {
                StuWorkModel stuWorkModel = new StuWorkModel();
                stuWorkModel.setSwId(this.swId);
                stuWorkModel.setUserswId(this.userswId);
                stuWorkModel.setUserId(this.userId);
                stuWorkModel.setOrgID(this.orgID);
                stuWorkModel.setDescription(this.description);
                stuWorkModel.setLink(this.link);
                stuWorkModel.setLinkTitle(this.linkTitle);
                stuWorkModel.setCreateDate(this.createDate);
                stuWorkModel.setModifyDate(this.modifyDate);
                return stuWorkModel;
        }

        public StuWorkForm getStuWorkForm(StuWorkModel stuWorkModel)
        {
                StuWorkForm stuWorkForm = new StuWorkForm();

                stuWorkForm.setUserswId(stuWorkModel.getUserswId());
                stuWorkForm.setSwId(stuWorkModel.getSwId());
                stuWorkForm.setUserId(stuWorkModel.getUserId());
                stuWorkForm.setOrgID(stuWorkModel.getOrgID());
                stuWorkForm.setLink(stuWorkModel.getLink());
                stuWorkForm.setLinkTitle(stuWorkModel.getLinkTitle());
                stuWorkForm.setDescription(stuWorkModel.getDescription());
                stuWorkForm.setCreateDate(stuWorkModel.getCreateDate());
                stuWorkForm.setModifyDate(stuWorkModel.getModifyDate());
                return stuWorkForm;
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

        public void setUserId(String userId)
        {
                this.userId = userId;
        }

        public void setOrgID(String orgID)
        {
                this.orgID = orgID;
        }

        public String getOrgID()
        {
                return orgID;
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

