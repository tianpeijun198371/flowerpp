/**
 * Copyright (c) 2000-2005.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.admin.gradeuser.model;

import org.apache.commons.lang.builder.ToStringBuilder;

import java.io.Serializable;

import java.util.Date;


/**
 * Class description goes here.
 * <p/>
 * Created by Auto Code Produce System
 * User: xiejh
 * Date: 20060321
 * Time: 182730
 */
public class GradeUserModel implements Serializable
{
        private int gradeUserID = 0;
        private String gradeUserName = "";
        private String gradeUserpwd = "";
        private String entergrade = "";
        private String remark1 = "";
        private String remark2 = "";
        private String remark3 = "";

        /**
         * default constructor
         */
        public GradeUserModel()
        {
        }

        /**
         * full constructor
         */
        public GradeUserModel(int gradeUserID, String gradeUserName,
                              String gradeUserpwd, String entergrade, String remark1, String remark2,
                              String remark3)
        {
                this.gradeUserID = gradeUserID;
                this.gradeUserName = gradeUserName;
                this.gradeUserpwd = gradeUserpwd;
                this.entergrade = entergrade;
                this.remark1 = remark1;
                this.remark2 = remark2;
                this.remark3 = remark3;
        }

        public int getGradeUserID()
        {
                return gradeUserID;
        }

        public void setGradeUserID(int gradeUserID)
        {
                this.gradeUserID = gradeUserID;
        }

        public String getGradeUserName()
        {
                return gradeUserName;
        }

        public void setGradeUserName(String gradeUserName)
        {
                this.gradeUserName = gradeUserName;
        }

        public String getGradeUserpwd()
        {
                return gradeUserpwd;
        }

        public void setGradeUserpwd(String gradeUserpwd)
        {
                this.gradeUserpwd = gradeUserpwd;
        }

        public String getEntergrade()
        {
                return entergrade;
        }

        public void setEntergrade(String entergrade)
        {
                this.entergrade = entergrade;
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

        public String toString()
        {
                return new ToStringBuilder(this).append("gradeUserID", getGradeUserID())
                        .toString();
        }
}
