/**
 * Created by IntelliJ IDEA.
 * User: zengwj
 * Date: 2004-8-27
 * Time: 15:46:46
 * To change this template use File | Settings | File Templates.
 */
package com.ulearning.ulms.course.model;

import org.apache.commons.lang.builder.ToStringBuilder;

import java.io.Serializable;

import java.util.Date;


public class Catalog implements Serializable
{
        /**
         * identifier field
         */
        private int catalogID;

        /**
         * persistent field
         */
        private String name;

        /**
         * persistent field
         */
        private String type;

        /**
         * persistent field
         */
        private int parentID;

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
        private String description;


        private String defaultCourseIcon;

        private String icon;

        /**
         * nullable persistent field
         */
        private Date establishTime;

        public Catalog()
        {
        }

        public Catalog(int catalogID, String name, String type, int parentID,
                       int orgID, int aspID, String description, Date establishTime)
        {
                this.catalogID = catalogID;
                this.name = name;
                this.type = type;
                this.parentID = parentID;
                this.orgID = orgID;
                this.aspID = aspID;
                this.description = description;
                this.establishTime = establishTime;
        }

        public int getCatalogID()
        {
                return catalogID;
        }

        public void setCatalogID(int catalogID)
        {
                this.catalogID = catalogID;
        }

        public String getName()
        {
                return name;
        }

        public void setName(String name)
        {
                this.name = name;
        }

        public String getType()
        {
                return type;
        }

        public void setType(String type)
        {
                this.type = type;
        }

        public String getDefaultCourseIcon()
        {
                return defaultCourseIcon;
        }

        public void setDefaultCourseIcon(String defaultCourseIcon)
        {
                this.defaultCourseIcon = defaultCourseIcon;
        }

        public String getIcon()
        {
                return icon;
        }

        public void setIcon(String icon)
        {
                this.icon = icon;
        }

        public int getParentID()
        {
                return parentID;
        }

        public void setParentID(int parentID)
        {
                this.parentID = parentID;
        }

        public int getOrgID()
        {
                return orgID;
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

        public String getDescription()
        {
                return description;
        }

        public void setDescription(String description)
        {
                this.description = description;
        }

        public Date getEstablishTime()
        {
                return establishTime;
        }

        public void setEstablishTime(Date establishTime)
        {
                this.establishTime = establishTime;
        }

        /**
         * full constructor
         */
        public String toString()
        {
                return new ToStringBuilder(this).append("catalogID", getCatalogID())
                        .toString();
        }
}
