package com.ulearning.ulms.course.model;

import org.apache.commons.lang.builder.ToStringBuilder;

import java.io.Serializable;

import java.util.Date;


/**
 * @author Hibernate CodeGenerator
 */
public class Master implements Serializable
{
        /**
         * identifier field
         */
        private int masterID;

        /**
         * persistent field
         */
        private String masterCode;

        /**
         * persistent field
         */
        private String name;

        /**
         * nullable persistent field
         */
        private String description;

        /**
         * nullable persistent field
         */
        private String type;

        /**
         * nullable persistent field
         */
        private int orgID;

        /**
         * nullable persistent field
         */
        private int aspID;

        /**
         * nullable persistent field
         */
        private String key;

        /**
         * persistent field
         */
        private int creator;

        /**
         * nullable persistent field
         */
        private Date establishDate;

        /**
         * nullable persistent field
         */
        private Date modifyDate;

        /**
         * nullable persistent field
         */
        private String plan;

        /**
         * nullable persistent field
         */
        private int catalogID;

        /**
         * nullable persistent field
         */
        private int objectID;

        /**
         * nullable persistent field
         */
        private float period;

        /**
         * nullable persistent field
         */
        private float credit;

        /**
         * nullable persistent field
         */
        private String status;
        private String operator; //²Ù×÷Ô±

        /**
         * full constructor
         */
        public Master(int masterID, String masterCode, String name,
                      String description, String type, int orgID, int aspID, String key,
                      int creator, Date establishDate, Date modifyDate, String plan,
                      int catalogID, int objectID, float period, float credit, String status,
                      String operator)
        {
                this.masterID = masterID;
                this.masterCode = masterCode;
                this.name = name;
                this.description = description;
                this.type = type;
                this.orgID = orgID;
                this.aspID = aspID;
                this.key = key;
                this.creator = creator;
                this.establishDate = establishDate;
                this.modifyDate = modifyDate;
                this.plan = plan;
                this.catalogID = catalogID;
                this.objectID = objectID;
                this.period = period;
                this.credit = credit;
                this.status = status;
                this.operator = operator;
        }

        /**
         * default constructor
         */
        public Master()
        {
        }

        /**
         * minimal constructor
         */
        public Master(int masterID, String masterCode, String name, int creator)
        {
                this.masterID = masterID;
                this.masterCode = masterCode;
                this.name = name;
                this.creator = creator;
        }

        public int getMasterID()
        {
                return this.masterID;
        }

        public void setMasterID(int masterID)
        {
                this.masterID = masterID;
        }

        public String getMasterCode()
        {
                return this.masterCode;
        }

        public void setMasterCode(String masterCode)
        {
                this.masterCode = masterCode;
        }

        public String getName()
        {
                return this.name;
        }

        public void setName(String name)
        {
                this.name = name;
        }

        public String getDescription()
        {
                return this.description;
        }

        public void setDescription(String description)
        {
                this.description = description;
        }

        public String getType()
        {
                return this.type;
        }

        public void setType(String type)
        {
                this.type = type;
        }

        public int getOrgID()
        {
                return this.orgID;
        }

        public void setOrgID(int orgID)
        {
                this.orgID = orgID;
        }

        public int getAspID()
        {
                return aspID;
        }

        public void setAspID(int aspID)
        {
                this.aspID = aspID;
        }

        public String getKey()
        {
                return this.key;
        }

        public void setKey(String key)
        {
                this.key = key;
        }

        public int getCreator()
        {
                return this.creator;
        }

        public void setCreator(int creator)
        {
                this.creator = creator;
        }

        public Date getEstablishDate()
        {
                return this.establishDate;
        }

        public void setEstablishDate(Date establishDate)
        {
                this.establishDate = establishDate;
        }

        public Date getModifyDate()
        {
                return this.modifyDate;
        }

        public void setModifyDate(Date modifyDate)
        {
                this.modifyDate = modifyDate;
        }

        public String getPlan()
        {
                return this.plan;
        }

        public void setPlan(String plan)
        {
                this.plan = plan;
        }

        public int getCatalogID()
        {
                return this.catalogID;
        }

        public void setCatalogID(int catalogID)
        {
                this.catalogID = catalogID;
        }

        public int getObjectID()
        {
                return this.objectID;
        }

        public void setObjectID(int objectID)
        {
                this.objectID = objectID;
        }

        public float getPeriod()
        {
                return this.period;
        }

        public void setPeriod(float period)
        {
                this.period = period;
        }

        public float getCredit()
        {
                return this.credit;
        }

        public void setCredit(float credit)
        {
                this.credit = credit;
        }

        public String getStatus()
        {
                return this.status;
        }

        public void setStatus(String status)
        {
                this.status = status;
        }

        public String getOperator()
        {
                return operator;
        }

        public void setOperator(String operator)
        {
                this.operator = operator;
        }

        public String toString()
        {
                return new ToStringBuilder(this).append("masterID", getMasterID())
                        .toString();
        }
}
