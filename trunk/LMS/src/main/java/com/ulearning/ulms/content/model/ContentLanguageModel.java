package com.ulearning.ulms.content.model;

import org.apache.commons.lang.builder.ToStringBuilder;

import java.io.Serializable;

import java.util.Date;


/**
 * @author Hibernate CodeGenerator
 */
public class ContentLanguageModel implements Serializable
{
        private int languageID;

        /**
         * identifier field
         */
        private String language;

        /**
         * persistent field
         */
        private String languageName;

        /**
         * nullable persistent field
         */
        private String remark;

        /**
         * nullable persistent field
         */
        private String isDefault;

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
        public ContentLanguageModel(String language, String languageName,
                                    String remark, String isDefault, Date createDate, Date lastModDate,
                                    String remark1, String remark2, String remark3, String status)
        {
                this.language = language;
                this.languageName = languageName;
                this.remark = remark;
                this.isDefault = isDefault;
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
        public ContentLanguageModel()
        {
        }

        /**
         * minimal constructor
         */
        public ContentLanguageModel(String language, String languageName)
        {
                this.language = language;
                this.languageName = languageName;
        }

        public String getLanguage()
        {
                return this.language;
        }

        public void setLanguage(String language)
        {
                this.language = language;
        }

        public String getLanguageName()
        {
                return this.languageName;
        }

        public void setLanguageName(String languageName)
        {
                this.languageName = languageName;
        }

        public String getRemark()
        {
                return this.remark;
        }

        public void setRemark(String remark)
        {
                this.remark = remark;
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

        public int getLanguageID()
        {
                return languageID;
        }

        public void setLanguageID(int languageID)
        {
                this.languageID = languageID;
        }

        public String toString()
        {
                return new ToStringBuilder(this).append("language", getLanguage())
                        .toString();
        }
}
