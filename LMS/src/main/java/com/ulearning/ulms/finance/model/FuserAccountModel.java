package com.ulearning.ulms.finance.model;

import org.apache.commons.lang.builder.ToStringBuilder;

import java.io.Serializable;

import java.util.Date;


/**
 * 个人帐户总帐
 *
 * @author Hibernate CodeGenerator
 * @ date 2005.12.09
 */
public class FuserAccountModel implements Serializable
{
        /**
         * identifier field
         */
        private int uacotId; //ID

        /**
         * persistent field
         */
        private int userId; //用户主键

        /**
         * nullable persistent field
         */
        private String uacotUserName; //用户名称

        /**
         * nullable persistent field
         */
        private double uacotTotal; //帐户余额

        /**
         * nullable persistent field
         */
        private int uacotStatus; //帐户状态  1：可用  2：不可用   3：...

        /**
         * nullable persistent field
         */
        private Date uacotlastUpDate; //最后一次更新时间

        /**
         * nullable persistent field
         */
        private String uacotLastUser; //最后一次更新人

        /**
         * nullable persistent field
         */
        private String uacotDescription; // 描述

        /**
         * nullable persistent field
         */
        private String uacotRemark1; //留用1

        /**
         * nullable persistent field
         */
        private String uacotRemark2; //留用2

        /**
         * full constructor
         */
        public FuserAccountModel(int userId, String uacotUserName,
                                 double uacotTotal, int uacotStatus, Date uacotlastUpDate,
                                 String uacotLastUser, String uacotDescription, String uacotRemark1,
                                 String uacotRemark2)
        {
                this.userId = userId;
                this.uacotUserName = uacotUserName;
                this.uacotTotal = uacotTotal;
                this.uacotStatus = uacotStatus;
                this.uacotlastUpDate = uacotlastUpDate;
                this.uacotLastUser = uacotLastUser;
                this.uacotDescription = uacotDescription;
                this.uacotRemark1 = uacotRemark1;
                this.uacotRemark2 = uacotRemark2;
        }

        /**
         * default constructor
         */
        public FuserAccountModel()
        {
        }

        /**
         * minimal constructor
         */
        public FuserAccountModel(int userId)
        {
                this.userId = userId;
        }

        public int getUacotId()
        {
                return this.uacotId;
        }

        public void setUacotId(int uacotId)
        {
                this.uacotId = uacotId;
        }

        public int getUserId()
        {
                return this.userId;
        }

        public void setUserId(int userId)
        {
                this.userId = userId;
        }

        public String getUacotUserName()
        {
                return this.uacotUserName;
        }

        public void setUacotUserName(String uacotUserName)
        {
                this.uacotUserName = uacotUserName;
        }

        public double getUacotTotal()
        {
                return this.uacotTotal;
        }

        public void setUacotTotal(double uacotTotal)
        {
                this.uacotTotal = uacotTotal;
        }

        public int getUacotStatus()
        {
                return this.uacotStatus;
        }

        public void setUacotStatus(int uacotStatus)
        {
                this.uacotStatus = uacotStatus;
        }

        public Date getUacotlastUpDate()
        {
                return this.uacotlastUpDate;
        }

        public void setUacotlastUpDate(Date uacotlastUpDate)
        {
                this.uacotlastUpDate = uacotlastUpDate;
        }

        public String getUacotLastUser()
        {
                return this.uacotLastUser;
        }

        public void setUacotLastUser(String uacotLastUser)
        {
                this.uacotLastUser = uacotLastUser;
        }

        public String getUacotDescription()
        {
                return this.uacotDescription;
        }

        public void setUacotDescription(String uacotDescription)
        {
                this.uacotDescription = uacotDescription;
        }

        public String getUacotRemark1()
        {
                return this.uacotRemark1;
        }

        public void setUacotRemark1(String uacotRemark1)
        {
                this.uacotRemark1 = uacotRemark1;
        }

        public String getUacotRemark2()
        {
                return this.uacotRemark2;
        }

        public void setUacotRemark2(String uacotRemark2)
        {
                this.uacotRemark2 = uacotRemark2;
        }

        public String toString()
        {
                return new ToStringBuilder(this).append("uacotId", getUacotId())
                        .toString();
        }
}
