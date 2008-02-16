package com.ulearning.ulms.admin.teacher.model;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * Created by IntelliJ IDEA.
 * User: suh
 * Date: 2006-3-16
 * Time: 11:55:14
 * To change this template use File | Settings | File Templates.
 */
import java.io.Serializable;


public class TeacherGroup implements Serializable
{
        private int uteachgroupID = 0;
        private int userID = 0;
        private int g_Ship_ID = 0;
        private String remark = "";

        /**
         * default constructor
         */
        public TeacherGroup()
        {
        }

        /**
         * full constructor
         */
        public TeacherGroup(int uteachgroupID, int userID, int g_Ship_ID,
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
