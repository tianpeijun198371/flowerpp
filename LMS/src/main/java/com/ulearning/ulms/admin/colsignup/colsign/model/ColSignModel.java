/**
 * Created by IntelliJ IDEA.
 * User: zengwj
 * Date: 2004-8-30
 * Time: 13:23:05
 * To change this template use File | Settings | File Templates.
 */
package com.ulearning.ulms.admin.colsignup.colsign.model;

import org.apache.commons.lang.builder.ToStringBuilder;

import java.util.Date;


public class ColSignModel
{
        /**
         * identifier field
         */
        private int colSignID;

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
        private Date createTime;

        /**
         * nullable persistent field
         */
        private int creator;

        /**
         * nullable persistent field
         */
        private String isSubmit;

        /**
         * nullable persistent field
         */
        private String approved;

        /**
         * nullable persistent field
         */
        private String desc1;

        /**
         * nullable persistent field
         */
        private int orgID;

        /**
         * full constructor
         */
        public ColSignModel(String name, String description, Date createTime,
                            int creator, String isSubmit, String approved, String desc1, int orgID)
        {
                this.name = name;
                this.description = description;
                this.createTime = createTime;
                this.creator = creator;
                this.isSubmit = isSubmit;
                this.approved = approved;
                this.desc1 = desc1;
                this.orgID = orgID;
        }

        /**
         * default constructor
         */
        public ColSignModel()
        {
        }

        /**
         * minimal constructor
         */
        public ColSignModel(String name)
        {
                this.name = name;
        }

        public int getColSignID()
        {
                return this.colSignID;
        }

        public void setColSignID(int colSignID)
        {
                this.colSignID = colSignID;
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

        public Date getCreateTime()
        {
                return this.createTime;
        }

        public void setCreateTime(Date createTime)
        {
                this.createTime = createTime;
        }

        public int getCreator()
        {
                return this.creator;
        }

        public void setCreator(int creator)
        {
                this.creator = creator;
        }

        public String getIsSubmit()
        {
                return this.isSubmit;
        }

        public void setIsSubmit(String isSubmit)
        {
                this.isSubmit = isSubmit;
        }

        public String getApproved()
        {
                return this.approved;
        }

        public void setApproved(String approved)
        {
                this.approved = approved;
        }

        public String getDesc1()
        {
                return this.desc1;
        }

        public void setDesc1(String desc1)
        {
                this.desc1 = desc1;
        }

        public int getOrgID()
        {
                return this.orgID;
        }

        public void setOrgID(int orgID)
        {
                this.orgID = orgID;
        }

        public String toString()
        {
                return new ToStringBuilder(this).append("colSignID", getColSignID())
                        .toString();
        }
}
