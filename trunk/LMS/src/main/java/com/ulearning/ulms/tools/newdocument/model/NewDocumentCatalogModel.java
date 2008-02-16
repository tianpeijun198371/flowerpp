/**
 * CatalogModel.java.
 * User: Administrator  Date: 2005-3-10
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.tools.newdocument.model;


import java.util.Date;

public class NewDocumentCatalogModel
{
        private int catalogID;

        private int parentID;

        private int aspID;

        private String type;

        private String catalogName;

        private String description;

        private Date createDate;

        private Date modifyDate;

        public NewDocumentCatalogModel()
        {
        }

        public NewDocumentCatalogModel(int parentID, int aspID, String type, String catalogName, String description)
        {
                this.parentID = parentID;
                this.aspID = aspID;
                this.type = type;
                this.catalogName = catalogName;
                this.description = description;
        }

        public NewDocumentCatalogModel(Date modifyDate, int catalogID, int parentID, int aspID, String type, String catalogName, String description, Date createDate)
        {
                this.modifyDate = modifyDate;
                this.catalogID = catalogID;
                this.parentID = parentID;
                this.aspID = aspID;
                this.type = type;
                this.catalogName = catalogName;
                this.description = description;
                this.createDate = createDate;
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

        public Date getModifyDate()
        {
                return modifyDate;
        }

        public void setModifyDate(Date modifyDate)
        {
                this.modifyDate = modifyDate;
        }

}
