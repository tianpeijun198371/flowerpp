package com.ulearning.ulms.content.model;

import org.apache.commons.lang.builder.ToStringBuilder;

import java.io.Serializable;

import java.util.Date;


/**
 * @author Hibernate CodeGenerator
 */
public class ContentTypeModel implements Serializable
{
        private int contentTypeID;

        /**
         * identifier field
         */
        private String contentType;

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
        private Date createDate;

        /**
         * nullable persistent field
         */
        private String remark;

        /**
         * nullable persistent field
         */
        private Date lastModDate;

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
         * full constructor
         */
        public ContentTypeModel(int contentTypeID, String contentType, String name,
                                String isDefault, Date createDate, String remark, Date lastModDate,
                                String remark1, String remark2, String remark3, String status)
        {
                this.contentType = contentType;
                this.name = name;
                this.isDefault = isDefault;
                this.createDate = createDate;
                this.remark = remark;
                this.lastModDate = lastModDate;
                this.remark1 = remark1;
                this.remark2 = remark2;
                this.remark3 = remark3;
                this.status = status;
                this.contentTypeID = contentTypeID;
        }

        /**
         * default constructor
         */
        public ContentTypeModel()
        {
        }

        /**
         * minimal constructor
         */
        public ContentTypeModel(String contentType)
        {
                this.contentType = contentType;
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

        public Date getCreateDate()
        {
                return this.createDate;
        }

        public void setCreateDate(Date createDate)
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

        public Date getLastModDate()
        {
                return this.lastModDate;
        }

        public void setLastModDate(Date lastModDate)
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
