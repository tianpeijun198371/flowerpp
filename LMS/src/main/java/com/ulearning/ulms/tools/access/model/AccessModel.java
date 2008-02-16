package com.ulearning.ulms.tools.access.model;

import org.apache.commons.lang.builder.ToStringBuilder;

import java.io.Serializable;

import java.util.Date;


/**
 * @author Hibernate CodeGenerator
 */
public class AccessModel implements Serializable
{
        /**
         * identifier field
         */
        private int accessID;

        /**
         * nullable persistent field
         */
        private int userID;

        /**
         * nullable persistent field
         */
        private Date createDateTime;

        /**
         * nullable persistent field
         */
        private int moduleID;

        /**
         * nullable persistent field
         */
        private int courseID;

        /**
         * nullable persistent field
         */
        private int certificateID;

        /**
         * nullable persistent field
         */
        private int projectID;

        /**
         * nullable persistent field
         */
        private int orgID;

        /**
         * nullable persistent field
         */
        private String ip;

        /**
         * nullable persistent field
         */
        private double userTime;
        private double sumTime;

        /**
         * full constructor
         */
        public AccessModel(int userID, Date createDateTime, int moduleID,
                           int courseID, int certificateID, int projectID, int orgID, String ip,
                           float userTime)
        {
                this.userID = userID;
                this.createDateTime = createDateTime;
                this.moduleID = moduleID;
                this.courseID = courseID;
                this.certificateID = certificateID;
                this.projectID = projectID;
                this.orgID = orgID;
                this.ip = ip;
                this.userTime = userTime;
        }

        /**
         * default constructor
         */
        public AccessModel()
        {
        }

        public double getSumTime()
        {
                return sumTime;
        }

        public void setSumTime(double sumTime)
        {
                this.sumTime = sumTime;
        }

        public int getAccessID()
        {
                return this.accessID;
        }

        public void setAccessID(int accessID)
        {
                this.accessID = accessID;
        }

        public int getUserID()
        {
                return this.userID;
        }

        public void setUserID(int userID)
        {
                this.userID = userID;
        }

        public Date getCreateDateTime()
        {
                return this.createDateTime;
        }

        public void setCreateDateTime(Date createDateTime)
        {
                this.createDateTime = createDateTime;
        }

        public int getModuleID()
        {
                return this.moduleID;
        }

        public void setModuleID(int moduleID)
        {
                this.moduleID = moduleID;
        }

        public int getCourseID()
        {
                return this.courseID;
        }

        public void setCourseID(int courseID)
        {
                this.courseID = courseID;
        }

        public int getCertificateID()
        {
                return this.certificateID;
        }

        public void setCertificateID(int certificateID)
        {
                this.certificateID = certificateID;
        }

        public int getProjectID()
        {
                return this.projectID;
        }

        public void setProjectID(int projectID)
        {
                this.projectID = projectID;
        }

        public int getOrgID()
        {
                return this.orgID;
        }

        public void setOrgID(int orgID)
        {
                this.orgID = orgID;
        }

        public String getIp()
        {
                return this.ip;
        }

        public void setIp(String ip)
        {
                this.ip = ip;
        }

        public double getUserTime()
        {
                return this.userTime;
        }

        public void setUserTime(double userTime)
        {
                this.userTime = userTime;
        }

        public String toString()
        {
                return new ToStringBuilder(this).append("accessID", getAccessID())
                        .toString();
        }
}
