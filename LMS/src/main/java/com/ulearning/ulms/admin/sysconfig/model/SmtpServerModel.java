package com.ulearning.ulms.admin.sysconfig.model;

import org.apache.commons.lang.builder.ToStringBuilder;

import java.io.Serializable;

import java.math.BigDecimal;


/**
 * @author Hibernate CodeGenerator
 */
public class SmtpServerModel implements Serializable
{
        /**
         * identifier field
         */
        private int orgid;

        /**
         * nullable persistent field
         */
        private String host;

        /**
         * nullable persistent field
         */
        private String username;

        /**
         * nullable persistent field
         */
        private String password;

        /**
         * full constructor
         */
        public SmtpServerModel(int orgid, String host, String username,
                               String password)
        {
                this.orgid = orgid;
                this.host = host;
                this.username = username;
                this.password = password;
        }

        /**
         * default constructor
         */
        public SmtpServerModel()
        {
        }

        /**
         * minimal constructor
         */
        public SmtpServerModel(int orgid)
        {
                this.orgid = orgid;
        }

        public int getOrgid()
        {
                return this.orgid;
        }

        public void setOrgid(int orgid)
        {
                this.orgid = orgid;
        }

        public String getHost()
        {
                return this.host;
        }

        public void setHost(String host)
        {
                this.host = host;
        }

        public String getUsername()
        {
                return this.username;
        }

        public void setUsername(String username)
        {
                this.username = username;
        }

        public String getPassword()
        {
                return this.password;
        }

        public void setPassword(String password)
        {
                this.password = password;
        }

        public String toString()
        {
                return new ToStringBuilder(this).append("orgid", getOrgid()).toString();
        }
}
