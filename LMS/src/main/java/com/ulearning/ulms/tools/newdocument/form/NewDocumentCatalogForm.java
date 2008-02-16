/**
 * NewDocumentCatalogForm.java.
 * User: Administrator  Date: 2005-3-14
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.tools.newdocument.form;

import com.ulearning.ulms.tools.newdocument.model.NewDocumentCatalogModel;

import java.util.Date;

import org.apache.struts.action.ActionForm;

public class NewDocumentCatalogForm extends ActionForm

{

        private int catalogID;

        private int parentID;

        private int aspID;

        private String type;

        private String catalogName;

        private String description;

        private Date createDate;

        private Date modifyDate;

        public NewDocumentCatalogForm()
        {
        }

        public NewDocumentCatalogForm(Date modifyDate, int catalogID, int parentID,
                                      int aspID, String type, String catalogName, String description, Date dreateDate)
        {
                this.modifyDate = modifyDate;
                this.catalogID = catalogID;
                this.parentID = parentID;
                this.aspID = aspID;
                this.type = type;
                this.catalogName = catalogName;
                this.description = description;
                this.createDate = dreateDate;
        }

        public NewDocumentCatalogModel getModel()
        {
                NewDocumentCatalogModel model = new NewDocumentCatalogModel();
                model.setCatalogID(this.catalogID);
                model.setAspID(this.aspID);
                model.setParentID(this.parentID);
                model.setType(this.type);
                model.setCatalogName(this.catalogName);
                model.setDescription(this.description);
                model.setModifyDate(this.modifyDate);
                model.setCreateDate(this.createDate);
                return model;
        }

        public Date getModifyDate()
        {
                return modifyDate;
        }

        public void setModifyDate(Date modifyDate)
        {
                this.modifyDate = modifyDate;
        }

        public int getCatalogID()
        {
                return catalogID;
        }

        public void setCatalogID(int catalogID)
        {
                this.catalogID = catalogID;
        }

        public int getParentID()
        {
                return parentID;
        }

        public void setParentID(int parentID)
        {
                this.parentID = parentID;
        }

        public int getAspID()
        {
                return aspID;
        }

        public void setAspID(int aspID)
        {
                this.aspID = aspID;
        }

        public String getType()
        {
                return type;
        }

        public void setType(String type)
        {
                this.type = type;
        }

        public String getCatalogName()
        {
                return catalogName;
        }

        public void setCatalogName(String catalogName)
        {
                this.catalogName = catalogName;
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

        public void setCreateDate(Date dreateDate)
        {
                this.createDate = dreateDate;
        }
}

