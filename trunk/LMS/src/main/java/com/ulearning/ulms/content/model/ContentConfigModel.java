package com.ulearning.ulms.content.model;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import java.io.Serializable;

import java.util.Date;


/**
 * @author Hibernate CodeGenerator
 */
public class ContentConfigModel implements Serializable
{
        /**
         * identifier field
         */
        private com.ulearning.ulms.content.model.ContentConfigModelPK comp_id;

        /**
         * persistent field
         */
        private int contentConfigID;

        /**
         * nullable persistent field
         */
        private String isAllowedCommonUserUpload;

        /**
         * nullable persistent field
         */
        private String isLimitSpace;

        /**
         * nullable persistent field
         */
        private String warningSpaceSize;

        /**
         * nullable persistent field
         */
        private String limitSpaceSize;

        /**
         * nullable persistent field
         */
        private String isLimitUploadFile;

        /**
         * nullable persistent field
         */
        private String limitUploadFileSize;

        /**
         * nullable persistent field
         */
        private Date createDate;

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
        public ContentConfigModel(
                com.ulearning.ulms.content.model.ContentConfigModelPK comp_id,
                int contentConfigID, String isAllowedCommonUserUpload,
                String isLimitSpace, String warningSpaceSize, String limitSpaceSize,
                String isLimitUploadFile, String limitUploadFileSize, Date createDate,
                Date lastModDate, String remark1, String remark2, String remark3,
                String status)
        {
                this.comp_id = comp_id;
                this.contentConfigID = contentConfigID;
                this.isAllowedCommonUserUpload = isAllowedCommonUserUpload;
                this.isLimitSpace = isLimitSpace;
                this.warningSpaceSize = warningSpaceSize;
                this.limitSpaceSize = limitSpaceSize;
                this.isLimitUploadFile = isLimitUploadFile;
                this.limitUploadFileSize = limitUploadFileSize;
                this.createDate = createDate;
                this.lastModDate = lastModDate;
                this.remark1 = remark1;
                this.remark2 = remark2;
                this.remark3 = remark3;
                this.status = status;
        }

        /**
         * default constructor
         */
        public ContentConfigModel()
        {
        }

        /**
         * minimal constructor
         */
        public ContentConfigModel(
                com.ulearning.ulms.content.model.ContentConfigModelPK comp_id,
                int contentConfigID)
        {
                this.comp_id = comp_id;
                this.contentConfigID = contentConfigID;
        }

        public com.ulearning.ulms.content.model.ContentConfigModelPK getComp_id()
        {
                return this.comp_id;
        }

        public void setComp_id(
                com.ulearning.ulms.content.model.ContentConfigModelPK comp_id)
        {
                this.comp_id = comp_id;
        }

        public int getContentConfigID()
        {
                return this.contentConfigID;
        }

        public void setContentConfigID(int contentConfigID)
        {
                this.contentConfigID = contentConfigID;
        }

        public String getIsAllowedCommonUserUpload()
        {
                return this.isAllowedCommonUserUpload;
        }

        public void setIsAllowedCommonUserUpload(String isAllowedCommonUserUpload)
        {
                this.isAllowedCommonUserUpload = isAllowedCommonUserUpload;
        }

        public String getIsLimitSpace()
        {
                return this.isLimitSpace;
        }

        public void setIsLimitSpace(String isLimitSpace)
        {
                this.isLimitSpace = isLimitSpace;
        }

        public String getWarningSpaceSize()
        {
                return this.warningSpaceSize;
        }

        public void setWarningSpaceSize(String warningSpaceSize)
        {
                this.warningSpaceSize = warningSpaceSize;
        }

        public String getLimitSpaceSize()
        {
                return this.limitSpaceSize;
        }

        public void setLimitSpaceSize(String limitSpaceSize)
        {
                this.limitSpaceSize = limitSpaceSize;
        }

        public String getIsLimitUploadFile()
        {
                return this.isLimitUploadFile;
        }

        public void setIsLimitUploadFile(String isLimitUploadFile)
        {
                this.isLimitUploadFile = isLimitUploadFile;
        }

        public String getLimitUploadFileSize()
        {
                return this.limitUploadFileSize;
        }

        public void setLimitUploadFileSize(String limitUploadFileSize)
        {
                this.limitUploadFileSize = limitUploadFileSize;
        }

        public Date getCreateDate()
        {
                return this.createDate;
        }

        public void setCreateDate(Date createDate)
        {
                this.createDate = createDate;
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

        public String toString()
        {
                return new ToStringBuilder(this).append("comp_id", getComp_id())
                        .toString();
        }

        public boolean equals(Object other)
        {
                if ((this == other))
                {
                        return true;
                }

                if (!(other instanceof ContentConfigModel))
                {
                        return false;
                }

                ContentConfigModel castOther = (ContentConfigModel) other;

                return new EqualsBuilder().append(this.getComp_id(),
                        castOther.getComp_id()).isEquals();
        }

        public int hashCode()
        {
                return new HashCodeBuilder().append(getComp_id()).toHashCode();
        }
}
