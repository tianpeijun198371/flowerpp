/**
 * Copyright (c) 2000-2005.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.content.uteachergroup.model;

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
public class UTeacherGroupModel implements Serializable
{
        private int uteachgroupID = 0;
        private int userID = 0;
        private int g_Ship_ID = 0;
        private String remark = "";

        /**
         * default constructor
         */
        public UTeacherGroupModel()
        {
        }

        /**
         * full constructor
         */
        public UTeacherGroupModel(int uteachgroupID, int userID, int g_Ship_ID,
                                  String remark)
        {
                this.uteachgroupID = uteachgroupID;
                this.userID = userID;
                this.g_Ship_ID = g_Ship_ID;
                this.remark = remark;
        }

        public int getUteachgroupID()
        {
                return uteachgroupID;
        }

        public void setUteachgroupID(int uteachgroupID)
        {
                this.uteachgroupID = uteachgroupID;
        }

        public int getUserID()
        {
                return userID;
        }

        public void setUserID(int userID)
        {
                this.userID = userID;
        }

        public int getG_Ship_ID()
        {
                return g_Ship_ID;
        }

        public void setG_Ship_ID(int g_Ship_ID)
        {
                this.g_Ship_ID = g_Ship_ID;
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
                return new ToStringBuilder(this).append("uteachgroupID",
                        getUteachgroupID()).toString();
        }
}
