package com.ulearning.ulms.tools.answerquestion.model;

import java.io.Serializable;

import java.util.Date;


/**
 * @author Hibernate CodeGenerator
 */
public class AQCatalogModel implements Serializable
{
        private int catalogID;
        private int parentID;
        private Date createDate;
        private String catalogName;
        private String type;
        private Date modifyDate;
        private String description;

        public AQCatalogModel()
        {
        }

        public AQCatalogModel(int parentID, String catalogName, String description,
                              String type)
        {
                this.parentID = parentID;
                this.catalogName = catalogName;
                this.description = description;
                this.type = type;
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

        public Date getCreateDate()
        {
                return createDate;
        }

        public void setCreateDate(Date createDate)
        {
                this.createDate = createDate;
        }

        public String getCatalogName()
        {
                return catalogName;
        }

        public void setCatalogName(String catalogName)
        {
                this.catalogName = catalogName;
        }

        public Date getModifyDate()
        {
                return modifyDate;
        }

        public void setModifyDate(Date modifyDate)
        {
                this.modifyDate = modifyDate;
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
}
