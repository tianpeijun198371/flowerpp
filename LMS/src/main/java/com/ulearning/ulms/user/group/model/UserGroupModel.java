/**
 * Copyright (c) 2000-2005.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */

package com.ulearning.ulms.user.group.model;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * Class description goes here.
 * <p/>
 * Created by Auto Code Produce System
 * User: xiejh
 * Date: 20051130
 * Time: 142230
 */

public class UserGroupModel implements Serializable
{

        private int usergroupid = 0;
        private int groupid = 0;
        private int userid = 0;
        private String remark1 = "";
        private String remark2 = "";
        private String remark3 = "";
        private String remark4 = "";


        public int getUsergroupid()
        {
                return usergroupid;
        }


        public void setUsergroupid(int usergroupid)
        {
                this.usergroupid = usergroupid;
        }


        public int getGroupid()
        {
                return groupid;
        }


        public void setGroupid(int groupid)
        {
                this.groupid = groupid;
        }


        public int getUserid()
        {
                return userid;
        }


        public void setUserid(int userid)
        {
                this.userid = userid;
        }


        public String getRemark1()
        {
                return remark1;
        }


        public void setRemark1(String remark1)
        {
                this.remark1 = remark1;
        }


        public String getRemark2()
        {
                return remark2;
        }


        public void setRemark2(String remark2)
        {
                this.remark2 = remark2;
        }


        public String getRemark3()
        {
                return remark3;
        }


        public void setRemark3(String remark3)
        {
                this.remark3 = remark3;
        }


        public String getRemark4()
        {
                return remark4;
        }


        public void setRemark4(String remark4)
        {
                this.remark4 = remark4;
        }


        /**
         * default constructor
         */
        public UserGroupModel()
        {
        }

        /**
         * full constructor
         */
        public UserGroupModel(int usergroupid, int groupid, int userid, String remark1, String remark2, String remark3, String remark4)
        {
                this.usergroupid = usergroupid;
                this.groupid = groupid;
                this.userid = userid;
                this.remark1 = remark1;
                this.remark2 = remark2;
                this.remark3 = remark3;
                this.remark4 = remark4;
        }

        public String toString()
        {
                return new ToStringBuilder(this)
                        .append("usergroupid", getUsergroupid())
                        .toString();
        }


}
