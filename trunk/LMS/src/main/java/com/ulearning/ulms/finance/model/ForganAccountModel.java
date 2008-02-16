package com.ulearning.ulms.finance.model;

import org.apache.commons.lang.builder.ToStringBuilder;

import java.io.Serializable;

import java.util.Date;


/**
 * 机构总帐Form
 *
 * @author Hibernate CodeGenerator
 * @ date 2005.12.08
 */
public class ForganAccountModel implements Serializable
{
        /**
         * identifier field
         */
        private int oacotId; //ID

        /**
         * persistent field
         */
        private int orgId; //机构主键

        /**
         * nullable persistent field
         */
        private String oacotOrganName; //机构名称

        /**
         * nullable persistent field
         */
        private double oacotTotal; //帐户余额

        /**
         * nullable persistent field
         */
        private int oacotStatus; //帐户状态  1：可用  2：不可用   3：...

        /**
         * nullable persistent field
         */
        private Date oacotLastUpdate; //最后一次更新时间

        /**
         * nullable persistent field
         */
        private String oacotLastUpUser; //最后一次更新人

        /**
         * nullable persistent field
         */
        private String oacotDescription; // 描述

        /**
         * nullable persistent field
         */
        private String oacotRemark1; //留用1

        /**
         * nullable persistent field
         */
        private String oacotRemark2; //留用2

        /**
         * full constructor
         */
        public ForganAccountModel(int orgId, String oacotOrganName,
                                  double oacotTotal, int oacotStatus, Date oacotLastUpdate,
                                  String oacotLastUpUser, String oacotDescription, String oacotRemark1,
                                  String oacotRemark2)
        {
                this.orgId = orgId;
                this.oacotOrganName = oacotOrganName;
                this.oacotTotal = oacotTotal;
                this.oacotStatus = oacotStatus;
                this.oacotLastUpdate = oacotLastUpdate;
                this.oacotLastUpUser = oacotLastUpUser;
                this.oacotDescription = oacotDescription;
                this.oacotRemark1 = oacotRemark1;
                this.oacotRemark2 = oacotRemark2;
        }

        /**
         * default constructor
         */
        public ForganAccountModel()
        {
        }

        /**
         * minimal constructor
         */
        public ForganAccountModel(int orgId)
        {
                this.orgId = orgId;
        }

        public int getOacotId()
        {
                return this.oacotId;
        }

        public void setOacotId(int oacotId)
        {
                this.oacotId = oacotId;
        }

        public int getOrgId()
        {
                return this.orgId;
        }

        public void setOrgId(int orgId)
        {
                this.orgId = orgId;
        }

        public String getOacotOrganName()
        {
                return this.oacotOrganName;
        }

        public void setOacotOrganName(String oacotOrganName)
        {
                this.oacotOrganName = oacotOrganName;
        }

        public double getOacotTotal()
        {
                return this.oacotTotal;
        }

        public void setOacotTotal(double oacotTotal)
        {
                this.oacotTotal = oacotTotal;
        }

        public int getOacotStatus()
        {
                return this.oacotStatus;
        }

        public void setOacotStatus(int oacotStatus)
        {
                this.oacotStatus = oacotStatus;
        }

        public Date getOacotLastUpdate()
        {
                return this.oacotLastUpdate;
        }

        public void setOacotLastUpdate(Date oacotLastUpdate)
        {
                this.oacotLastUpdate = oacotLastUpdate;
        }

        public String getOacotLastUpUser()
        {
                return this.oacotLastUpUser;
        }

        public void setOacotLastUpUser(String oacotLastUpUser)
        {
                this.oacotLastUpUser = oacotLastUpUser;
        }

        public String getOacotDescription()
        {
                return this.oacotDescription;
        }

        public void setOacotDescription(String oacotDescription)
        {
                this.oacotDescription = oacotDescription;
        }

        public String getOacotRemark1()
        {
                return this.oacotRemark1;
        }

        public void setOacotRemark1(String oacotRemark1)
        {
                this.oacotRemark1 = oacotRemark1;
        }

        public String getOacotRemark2()
        {
                return this.oacotRemark2;
        }

        public void setOacotRemark2(String oacotRemark2)
        {
                this.oacotRemark2 = oacotRemark2;
        }

        public String toString()
        {
                return new ToStringBuilder(this).append("oacotId", getOacotId())
                        .toString();
        }
}
