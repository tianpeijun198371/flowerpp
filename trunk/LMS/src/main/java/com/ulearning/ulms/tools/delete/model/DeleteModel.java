package com.ulearning.ulms.tools.delete.model;

import org.apache.commons.lang.builder.ToStringBuilder;

import java.io.Serializable;

import java.util.Date;


/**
 * @author Hibernate CodeGenerator
 */
public class DeleteModel implements Serializable
{
        /**
         * identifier field
         */
        private int deleteID;

        /**
         * nullable persistent field
         */
        private int relationID;

        /**
         * nullable persistent field
         */
        private String relationType;

        /**
         * nullable persistent field
         */
        private String state;

        /**
         * nullable persistent field
         */
        private String objectType;

        /**
         * nullable persistent field
         */
        private int saveTimeNum;

        /**
         * nullable persistent field
         */
        private String timeType;

        /**
         * nullable persistent field
         */
        private int saveRows;

        /**
         * nullable persistent field
         */
        private int userID;

        /**
         * nullable persistent field
         */
        private Date updateDate;
        private String tmp = "";
        private DeleteModel DeleteModel = null;

        /**
         * full constructor
         */
        public DeleteModel(int relationID, String relationType, String state,
                           String objectType, int saveTimeNum, String timeType, int saveRows,
                           int userID, Date updateDate)
        {
                this.relationID = relationID;
                this.relationType = relationType;
                this.state = state;
                this.objectType = objectType;
                this.saveTimeNum = saveTimeNum;
                this.timeType = timeType;
                this.saveRows = saveRows;
                this.userID = userID;
                this.updateDate = updateDate;
        }

        /**
         * default constructor
         */
        public DeleteModel()
        {
        }

        public int getDeleteID()
        {
                return this.deleteID;
        }

        public void setDeleteID(int deleteID)
        {
                this.deleteID = deleteID;
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

        public String getState()
        {
                return this.state;
        }

        public void setState(String state)
        {
                this.state = state;
        }

        public String getObjectType()
        {
                return this.objectType;
        }

        public void setObjectType(String objectType)
        {
                this.objectType = objectType;
        }

        public int getSaveTimeNum()
        {
                return this.saveTimeNum;
        }

        public void setSaveTimeNum(int saveTimeNum)
        {
                this.saveTimeNum = saveTimeNum;
        }

        public String getTimeType()
        {
                return this.timeType;
        }

        public void setTimeType(String timeType)
        {
                this.timeType = timeType;
        }

        public int getSaveRows()
        {
                return this.saveRows;
        }

        public void setSaveRows(int saveRows)
        {
                this.saveRows = saveRows;
        }

        public int getUserID()
        {
                return this.userID;
        }

        public void setUserID(int userID)
        {
                this.userID = userID;
        }

        public Date getUpdateDate()
        {
                return this.updateDate;
        }

        public void setUpdateDate(Date updateDate)
        {
                this.updateDate = updateDate;
        }

        public String getTmp()
        {
                return tmp;
        }

        public void setTmp(String tmp)
        {
                this.tmp = tmp;
        }

        public String toString()
        {
                return new ToStringBuilder(this).append("deleteID", getDeleteID())
                        .toString();
        }
}
