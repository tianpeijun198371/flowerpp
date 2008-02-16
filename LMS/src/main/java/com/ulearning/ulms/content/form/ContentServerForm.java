package com.ulearning.ulms.content.form;

import org.apache.commons.lang.builder.ToStringBuilder;

import org.apache.struts.action.ActionForm;

import java.io.Serializable;

import java.util.Date;


/**
 * @author Hibernate CodeGenerator
 */
public class ContentServerForm extends ActionForm implements Serializable
{
        /**
         * identifier field
         */
        private int contentServerID;

        /**
         * persistent field
         */
        private String name;

        /**
         * persistent field
         */
        private String host;

        /**
         * persistent field
         */
        private String port;

        /**
         * persistent field
         */
        private String physicalPath;

        /**
         * persistent field
         */
        private String virtualPath;

        /**
         * nullable persistent field
         */
        private Date createDate;

        /**
         * nullable persistent field
         */
        private Date lastModDate;
        private String isDefault;

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
        public ContentServerForm(String name, String host, String port,
                                 String physicalPath, String virtualPath, Date createDate,
                                 Date lastModDate, String remark1, String remark2, String remark3,
                                 String status)
        {
                this.name = name;
                this.host = host;
                this.port = port;
                this.physicalPath = physicalPath;
                this.virtualPath = virtualPath;
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
        public ContentServerForm()
        {
        }

        /**
         * minimal constructor
         */
        public ContentServerForm(String name, String host, String port,
                                 String physicalPath, String virtualPath)
        {
                this.name = name;
                this.host = host;
                this.port = port;
                this.physicalPath = physicalPath;
                this.virtualPath = virtualPath;
        }

        public int getContentServerID()
        {
                return this.contentServerID;
        }

        public void setContentServerID(int contentServerID)
        {
                this.contentServerID = contentServerID;
        }

        public String getName()
        {
                return this.name;
        }

        public void setName(String name)
        {
                this.name = name;
        }

        public String getHost()
        {
                return this.host;
        }

        public void setHost(String host)
        {
                this.host = host;
        }

        public String getPort()
        {
                return this.port;
        }

        public void setPort(String port)
        {
                this.port = port;
        }

        public String getPhysicalPath()
        {
                return this.physicalPath;
        }

        public void setPhysicalPath(String physicalPath)
        {
                this.physicalPath = physicalPath;
        }

        public String getVirtualPath()
        {
                return this.virtualPath;
        }

        public void setVirtualPath(String virtualPath)
        {
                this.virtualPath = virtualPath;
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

        public String getIsDefault()
        {
                return isDefault;
        }

        public void setIsDefault(String isDefault)
        {
                this.isDefault = isDefault;
        }

        public String toString()
        {
                return new ToStringBuilder(this).append("contentServerID",
                        getContentServerID()).toString();
        }
}
