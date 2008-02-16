package com.ulearning.ulms.lmslog.model;

import org.apache.commons.lang.builder.ToStringBuilder;

import java.io.Serializable;

import java.util.Date;


/**
 * @author Hibernate CodeGenerator
 */
public class LmslogModel implements Serializable
{
        /**
         * identifier field
         */
        private int logid;

        /**
         * persistent field
         */
        private int logtypeid;

        /**
         * persistent field
         */
        private int userid;

        /**
         * persistent field
         */
        private int orgid;

        /**
         * nullable persistent field
         */
        private String userip;

        /**
         * persistent field
         */
        private int operationtypeid;

        /**
         * nullable persistent field
         */
        private String operationtable;

        /**
         * nullable persistent field
         */
        private int operationobjectid;

        /**
         * nullable persistent field
         */
        private Date operationtime;
        private String description;

        /**
         * full constructor
         */
        public LmslogModel(String description, int logid, int logtypeid,
                           int userid, int orgid, String userip, int operationtypeid,
                           String operationtable, int operationobjectid, Date operationtime)
        {
                this.description = description;
                this.logid = logid;
                this.logtypeid = logtypeid;
                this.userid = userid;
                this.orgid = orgid;
                this.userip = userip;
                this.operationtypeid = operationtypeid;
                this.operationtable = operationtable;
                this.operationobjectid = operationobjectid;
                this.operationtime = operationtime;
        }

        /**
         * default constructor
         */
        public LmslogModel()
        {
        }

        /**
         * minimal constructor
         */
        public LmslogModel(int logid, int logtypeid, int userid, int orgid,
                           int operationtypeid)
        {
                this.logid = logid;
                this.logtypeid = logtypeid;
                this.userid = userid;
                this.orgid = orgid;
                this.operationtypeid = operationtypeid;
        }

        public int getLogid()
        {
                return this.logid;
        }

        public void setLogid(int logid)
        {
                this.logid = logid;
        }

        public int getLogtypeid()
        {
                return this.logtypeid;
        }

        public void setLogtypeid(int logtypeid)
        {
                this.logtypeid = logtypeid;
        }

        public int getUserid()
        {
                return this.userid;
        }

        public void setUserid(int userid)
        {
                this.userid = userid;
        }

        public int getOrgid()
        {
                return this.orgid;
        }

        public void setOrgid(int orgid)
        {
                this.orgid = orgid;
        }

        public String getUserip()
        {
                return this.userip;
        }

        public void setUserip(String userip)
        {
                this.userip = userip;
        }

        public int getOperationtypeid()
        {
                return this.operationtypeid;
        }

        public void setOperationtypeid(int operationtypeid)
        {
                this.operationtypeid = operationtypeid;
        }

        public String getOperationtable()
        {
                return this.operationtable;
        }

        public void setOperationtable(String operationtable)
        {
                this.operationtable = operationtable;
        }

        public int getOperationobjectid()
        {
                return this.operationobjectid;
        }

        public void setOperationobjectid(int operationobjectid)
        {
                this.operationobjectid = operationobjectid;
        }

        public Date getOperationtime()
        {
                return this.operationtime;
        }

        public void setOperationtime(Date operationtime)
        {
                this.operationtime = operationtime;
        }

        public String getDescription()
        {
                return description;
        }

        public void setDescription(String description)
        {
                this.description = description;
        }

        public String toString()
        {
                return new ToStringBuilder(this).append("logid", getLogid()).toString();
        }
}
