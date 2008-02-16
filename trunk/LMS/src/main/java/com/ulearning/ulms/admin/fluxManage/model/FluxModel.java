/**
 * FluxModel.java.
 * User: fengch  Date: 2004-6-23
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.admin.fluxManage.model;

import org.apache.commons.lang.builder.ToStringBuilder;

import java.util.Date;


public class FluxModel
{
        /**
         * identifier field
         */
        private int fluxID;
        private int orgID;
        private int clientID;
        private String clientLoginName;
        private String clientUserName;
        private String clientIP;
        private String clientBrowserName;
        private String clientBrowserVersion;
        private String clientOsName;
        private String clientOsVersion;
        private long flux;
        private Date createDate;
        private String description;

        public FluxModel()
        {
        }

        public FluxModel(int fluxID, int orgID, int clientID,
                         String clientLoginName, String clientUserName, String clientIP,
                         String clientBrowserName, String clientBrowserVersion,
                         String clientOsName, String clientOsVersion, long flux,
                         Date createDate, String description)
        {
                this.fluxID = fluxID;
                this.orgID = orgID;
                this.clientID = clientID;
                this.clientLoginName = clientLoginName;
                this.clientUserName = clientUserName;
                this.clientIP = clientIP;
                this.clientBrowserName = clientBrowserName;
                this.clientBrowserVersion = clientBrowserVersion;
                this.clientOsName = clientOsName;
                this.clientOsVersion = clientOsVersion;
                this.flux = flux;
                this.createDate = createDate;
                this.description = description;
        }

        public int getFluxID()
        {
                return fluxID;
        }

        public void setFluxID(int fluxID)
        {
                this.fluxID = fluxID;
        }

        public int getOrgID()
        {
                return orgID;
        }

        public void setOrgID(int orgID)
        {
                this.orgID = orgID;
        }

        public int getClientID()
        {
                return clientID;
        }

        public void setClientID(int clientID)
        {
                this.clientID = clientID;
        }

        public String getClientLoginName()
        {
                return clientLoginName;
        }

        public void setClientLoginName(String clientLoginName)
        {
                this.clientLoginName = clientLoginName;
        }

        public String getClientUserName()
        {
                return clientUserName;
        }

        public void setClientUserName(String clientUserName)
        {
                this.clientUserName = clientUserName;
        }

        public String getClientIP()
        {
                return clientIP;
        }

        public void setClientIP(String clientIP)
        {
                this.clientIP = clientIP;
        }

        public String getClientBrowserName()
        {
                return clientBrowserName;
        }

        public void setClientBrowserName(String clientBrowserName)
        {
                this.clientBrowserName = clientBrowserName;
        }

        public String getClientBrowserVersion()
        {
                return clientBrowserVersion;
        }

        public void setClientBrowserVersion(String clientBrowserVersion)
        {
                this.clientBrowserVersion = clientBrowserVersion;
        }

        public String getClientOsName()
        {
                return clientOsName;
        }

        public void setClientOsName(String clientOsName)
        {
                this.clientOsName = clientOsName;
        }

        public String getClientOsVersion()
        {
                return clientOsVersion;
        }

        public void setClientOsVersion(String clientOsVersion)
        {
                this.clientOsVersion = clientOsVersion;
        }

        public long getFlux()
        {
                return flux;
        }

        public void setFlux(long flux)
        {
                this.flux = flux;
        }

        public Date getCreateDate()
        {
                return createDate;
        }

        public void setCreateDate(Date createDate)
        {
                this.createDate = createDate;
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
                return new ToStringBuilder(this).append("FluxID", getFluxID()).toString();
        }
}
