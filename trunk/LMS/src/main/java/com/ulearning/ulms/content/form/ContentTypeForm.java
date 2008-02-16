package com.ulearning.ulms.content.form;

import org.apache.commons.lang.builder.ToStringBuilder;

import org.apache.struts.action.ActionForm;

import java.io.Serializable;

import java.util.Date;


/**
 * @author Hibernate CodeGenerator
 */
public class ContentTypeForm extends ActionForm implements Serializable
{
        private int contentTypeID;
        private String contentType;
        private String contentTypeTemp;

        /**
         * nullable persistent field
         */
        private String name;

        /**
         * nullable persistent field
         */
        private String isDefault;
        private int orderIndex;

        /**
         * nullable persistent field
         */
        private String createDate;

        /**
         * nullable persistent field
         */
        private String remark;

        /**
         * nullable persistent field
         */
        private String lastModDate;

        /**
         * nullable persistent field
         */
        private String remark1;

        /**
         * nullable persistent field
         */
        private String remark2;

        /**
         * nullable persistent field
         */
        private String remark3;

        /**
         * nullable persistent field
         */
        private String status;

        /**
         * default constructor
         */
        public ContentTypeForm()
        {
        }

        public String getContentType()
        {
                return this.contentType;
        }

        public void setContentType(String contentType)
        {
                this.contentType = contentType;
        }

        public String getName()
        {
                return this.name;
        }

        public void setName(String name)
        {
                if (name == null)
                {
                        name = "";
                }
                else if (name.trim().equals("") ||
                        name.trim().equalsIgnoreCase("null"))
                {
                        name = "";
                }

                this.name = name;
        }

        public String getIsDefault()
        {
                return this.isDefault;
        }

        public void setIsDefault(String isDefault)
        {
                this.isDefault = isDefault;
        }

        public String getCreateDate()
        {
                return this.createDate;
        }

        public void setCreateDate(String createDate)
        {
                this.createDate = createDate;
        }

        public String getRemark()
        {
                return this.remark;
        }

        public void setRemark(String remark)
        {
                this.remark = remark;
        }

        public String getLastModDate()
        {
                return this.lastModDate;
        }

        public void setLastModDate(String lastModDate)
        {
                this.lastModDate = lastModDate;
        }

        public String getRemark1()
        {
                return this.remark1;
        }

        public void setRemark1(String remark1)
        {
                this.remark1 = remark1;
        }

        public String getRemark2()
        {
                return this.remark2;
        }

        public void setRemark2(String remark2)
        {
                this.remark2 = remark2;
        }

        public String getRemark3()
        {
                return this.remark3;
        }

        public void setRemark3(String remark3)
        {
                this.remark3 = remark3;
        }

        public String getStatus()
        {
                return this.status;
        }

        public void setStatus(String status)
        {
                this.status = status;
        }

        public int getOrderIndex()
        {
                return orderIndex;
        }

        public void setOrderIndex(int orderIndex)
        {
                this.orderIndex = orderIndex;
        }

        public String getContentTypeTemp()
        {
                return contentTypeTemp;
        }

        public void setContentTypeTemp(String contentTypeTemp)
        {
                this.contentTypeTemp = contentTypeTemp;
        }

        public int getContentTypeID()
        {
                return contentTypeID;
        }

        public void setContentTypeID(int contentTypeID)
        {
                this.contentTypeID = contentTypeID;
        }

        public String toString()
        {
                return new ToStringBuilder(this).append("contentType", getContentType())
                        .toString();
        }
}
