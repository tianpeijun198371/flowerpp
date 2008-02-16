package com.ulearning.ulms.admin.webconfig.webcustom.model;

import org.apache.commons.lang.builder.ToStringBuilder;

import java.io.Serializable;

import java.util.Date;


/**
 * @author Hibernate CodeGenerator
 */
public class WebCustomModel implements Serializable
{
        /**
         * identifier field
         */
        private int customID;

        /**
         * persistent field
         */
        private int relationID;

        /**
         * persistent field
         */
        private String relationType;

        /**
         * nullable persistent field
         */
        private String configTypeName;

        /**
         * nullable persistent field
         */
        private Date updateDate;

        /**
         * nullable persistent field
         */
        private String description;

        /**
         * full constructor
         */
        public WebCustomModel(int relationID, String relationType,
                              String configTypeName, Date updateDate, String description)
        {
                this.relationID = relationID;
                this.relationType = relationType;
                this.configTypeName = configTypeName;
                this.updateDate = updateDate;
                this.description = description;
        }

        /**
         * default constructor
         */
        public WebCustomModel()
        {
        }

        /**
         * minimal constructor
         */
        public WebCustomModel(int relationID, String relationType)
        {
                this.relationID = relationID;
                this.relationType = relationType;
        }

        public int getCustomID()
        {
                return this.customID;
        }

        public void setCustomID(int customID)
        {
                this.customID = customID;
        }

        public int getRelationID()
        {
                return this.relationID;
        }

        public void setRelationID(int relationID)
        {
                this.relationID = relationID;
        }

        public String getRelationType()
        {
                return this.relationType;
        }

        public void setRelationType(String relationType)
        {
                this.relationType = relationType;
        }

        public String getConfigTypeName()
        {
                return this.configTypeName;
        }

        public void setConfigTypeName(String configTypeName)
        {
                this.configTypeName = configTypeName;
        }

        public Date getUpdateDate()
        {
                return this.updateDate;
        }

        public void setUpdateDate(Date updateDate)
        {
                this.updateDate = updateDate;
        }

        public String getDescription()
        {
                return this.description;
        }

        public void setDescription(String description)
        {
                this.description = description;
        }

        public String toString()
        {
                return new ToStringBuilder(this).append("customID", getCustomID())
                        .toString();
        }
}
