/**
 * Copyright (c) 2000-2005.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.content.ugroupship.model;

import org.apache.commons.lang.builder.ToStringBuilder;

import java.io.Serializable;


/**
 * Class description goes here.
 * <p/>
 * Created by Auto Code Produce System
 * User: xiejh
 * Date: 20060317
 * Time: 103906
 */
public class UGroupShipModel implements Serializable
{
        private int g_Ship_ID = 0;
        private int userID = 0;
        private String groupName = "";
        private String perdUrl = "";
        private String remark = "";

        /**
         * default constructor
         */
        public UGroupShipModel()
        {
        }

        /**
         * full constructor
         */
        public UGroupShipModel(int g_Ship_ID, int userID, String groupName,
                               String perdUrl, String remark)
        {
                this.g_Ship_ID = g_Ship_ID;
                this.userID = userID;
                this.groupName = groupName;
                this.perdUrl = perdUrl;
                this.remark = remark;
        }

        public int getG_Ship_ID()
        {
                return g_Ship_ID;
        }

        public void setG_Ship_ID(int g_Ship_ID)
        {
                this.g_Ship_ID = g_Ship_ID;
        }

        public int getUserID()
        {
                return userID;
        }

        public void setUserID(int userID)
        {
                this.userID = userID;
        }

        public String getGroupName()
        {
                return groupName;
        }

        public void setGroupName(String groupName)
        {
                this.groupName = groupName;
        }

        public String getPerdUrl()
        {
                return perdUrl;
        }

        public void setPerdUrl(String perdUrl)
        {
                this.perdUrl = perdUrl;
        }

        public String getRemark()
        {
                return remark;
        }

        public void setRemark(String remark)
        {
                this.remark = remark;
        }

        public String toString()
        {
                return new ToStringBuilder(this).append("g_Ship_ID", getG_Ship_ID())
                        .toString();
        }
}
