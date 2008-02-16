package com.ulearning.ulms.content.form;

import org.apache.struts.action.ActionForm;

import java.io.Serializable;

import java.util.Date;


/**
 * @author Hibernate CodeGenerator
 */
public class ContentConfigForm extends ActionForm implements Serializable
{
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
        private String type;
        private int relationID;

        /**
         * full constructor
         */
        public ContentConfigForm(int contentConfigID,
                                 String isAllowedCommonUserUpload, String isLimitSpace,
                                 String warningSpaceSize, String limitSpaceSize,
                                 String isLimitUploadFile, String limitUploadFileSize, Date createDate,
                                 Date lastModDate, String remark1, String remark2, String remark3,
                                 String status)
        {
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
        public ContentConfigForm()
        {
        }

        /**
         * minimal constructor
         */
        public ContentConfigForm(int contentConfigID)
        {
                this.contentConfigID = contentConfigID;
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

        public String getType()
        {
                return this.type;
        }

        public void setType(String type)
        {
                this.type = type;
        }

        public int getRelationID()
        {
                return this.relationID;
        }

        public void setRelationID(int relationID)
        {
                this.relationID = relationID;
        }
}
