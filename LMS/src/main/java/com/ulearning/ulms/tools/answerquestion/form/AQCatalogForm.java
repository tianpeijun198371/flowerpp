package com.ulearning.ulms.tools.answerquestion.form;

import org.apache.struts.action.ActionForm;

import java.util.Date;


/**
 * @author Hibernate CodeGenerator
 */
public class AQCatalogForm extends ActionForm
{
        private int catalogID;
        private int parentID;
        private Date createDate;
        private String catalogName;
        private String type;
        private Date modifyDate;
        private String description;

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

        public String getCatalogName()
        {
                return catalogName;
        }

        public void setCatalogName(String catalogName)
        {
                this.catalogName = catalogName;
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
