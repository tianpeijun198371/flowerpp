package com.ulearning.ulms.finance.model;

import org.apache.commons.lang.builder.ToStringBuilder;

import java.io.Serializable;

import java.util.Date;


/**
 * 机构帐户明细Form
 *
 * @author Hibernate CodeGenerator
 * @ date  2005.12.08
 */
public class ForganAccountDetailModel implements Serializable
{
        /**
         * identifier field
         */
        private int oadetailId; //ID

        /**
         * persistent field
         */
        private int orgId; //机构ID

        /**
         * nullable persistent field
         */
        private String oadetailOrganName; //机构名称

        /**
         * persistent field
         */
        private int oadetailInOutType; //收支模式:   1：收;2：支

        /**
         * persistent field
         */
        private int oadetailTypeId; //具体的收支类型

        /**
         * nullable persistent field
         */
        private double oadetailAmount; // 金额

        /**
         * nullable persistent field
         */
        private Date oadetailDate; //发生时间

        /**
         * persistent field
         */
        private int oadetailOperatorId; //操作员ID

        /**
         * nullable persistent field
         */
        private String oadetailOperatorName; //操作员名称

        /**
         * persistent field
         */
        private int oadetailEntityValue; //收支类型实体ID的值

        /**
         * nullable persistent field
         */
        private int oadetailRemitType; //汇款方式

        /**
         * nullable persistent field
         */
        private int oadetailInvoice; //开票情况 0：没开;1：已开

        /**
         * nullable persistent field
         */
        private int oadetailAudit; //是否审核 0：未审核  1：已审核

        /**
         * nullable persistent field
         */
        private String oadetailMemo; //备注

        /**
         * nullable persistent field
         */
        private String oadetailRemark1; //留用1

        /**
         * nullable persistent field
         */
        private String oadetailRemark2; //留用2
        private int aspId; //aspid

        /**
         * full constructor
         */
        public ForganAccountDetailModel(int orgId, String oadetailOrganName,
                                        int oadetailInOutType, int oadetailTypeId, double oadetailAmount,
                                        Date oadetailDate, int oadetailOperatorId, String oadetailOperatorName,
                                        int oadetailEntityValue, int oadetailRemitType, int oadetailInvoice,
                                        int oadetailAudit, String oadetailMemo, String oadetailRemark1,
                                        String oadetailRemark2, int aspId)
        {
                this.orgId = orgId;
                this.oadetailOrganName = oadetailOrganName;
                this.oadetailInOutType = oadetailInOutType;
                this.oadetailTypeId = oadetailTypeId;
                this.oadetailAmount = oadetailAmount;
                this.oadetailDate = oadetailDate;
                this.oadetailOperatorId = oadetailOperatorId;
                this.oadetailOperatorName = oadetailOperatorName;
                this.oadetailEntityValue = oadetailEntityValue;
                this.oadetailRemitType = oadetailRemitType;
                this.oadetailInvoice = oadetailInvoice;
                this.oadetailAudit = oadetailAudit;
                this.oadetailMemo = oadetailMemo;
                this.oadetailRemark1 = oadetailRemark1;
                this.oadetailRemark2 = oadetailRemark2;
                this.aspId = aspId;
        }

        /**
         * default constructor
         */
        public ForganAccountDetailModel()
        {
        }

        /**
         * minimal constructor
         */
        public ForganAccountDetailModel(int orgId, int oadetailInOutType,
                                        int oadetailTypeId, int oadetailOperatorId, int oadetailEntityValue)
        {
                this.orgId = orgId;
                this.oadetailInOutType = oadetailInOutType;
                this.oadetailTypeId = oadetailTypeId;
                this.oadetailOperatorId = oadetailOperatorId;
                this.oadetailEntityValue = oadetailEntityValue;
        }

        public int getOadetailId()
        {
                return this.oadetailId;
        }

        public void setOadetailId(int oadetailId)
        {
                this.oadetailId = oadetailId;
        }

        public int getOrgId()
        {
                return this.orgId;
        }

        public void setOrgId(int orgId)
        {
                this.orgId = orgId;
        }

        public String getOadetailOrganName()
        {
                return this.oadetailOrganName;
        }

        public void setOadetailOrganName(String oadetailOrganName)
        {
                this.oadetailOrganName = oadetailOrganName;
        }

        public int getOadetailInOutType()
        {
                return this.oadetailInOutType;
        }

        public void setOadetailInOutType(int oadetailInOutType)
        {
                this.oadetailInOutType = oadetailInOutType;
        }

        public int getOadetailTypeId()
        {
                return this.oadetailTypeId;
        }

        public void setOadetailTypeId(int oadetailTypeId)
        {
                this.oadetailTypeId = oadetailTypeId;
        }

        public double getOadetailAmount()
        {
                return this.oadetailAmount;
        }

        public void setOadetailAmount(double oadetailAmount)
        {
                this.oadetailAmount = oadetailAmount;
        }

        public Date getOadetailDate()
        {
                return this.oadetailDate;
        }

        public void setOadetailDate(Date oadetailDate)
        {
                this.oadetailDate = oadetailDate;
        }

        public int getOadetailOperatorId()
        {
                return this.oadetailOperatorId;
        }

        public void setOadetailOperatorId(int oadetailOperatorId)
        {
                this.oadetailOperatorId = oadetailOperatorId;
        }

        public String getOadetailOperatorName()
        {
                return this.oadetailOperatorName;
        }

        public void setOadetailOperatorName(String oadetailOperatorName)
        {
                this.oadetailOperatorName = oadetailOperatorName;
        }

        public int getOadetailEntityValue()
        {
                return this.oadetailEntityValue;
        }

        public void setOadetailEntityValue(int oadetailEntityValue)
        {
                this.oadetailEntityValue = oadetailEntityValue;
        }

        public int getOadetailRemitType()
        {
                return this.oadetailRemitType;
        }

        public void setOadetailRemitType(int oadetailRemitType)
        {
                this.oadetailRemitType = oadetailRemitType;
        }

        public int getOadetailInvoice()
        {
                return this.oadetailInvoice;
        }

        public void setOadetailInvoice(int oadetailInvoice)
        {
                this.oadetailInvoice = oadetailInvoice;
        }

        public int getOadetailAudit()
        {
                return this.oadetailAudit;
        }

        public void setOadetailAudit(int oadetailAudit)
        {
                this.oadetailAudit = oadetailAudit;
        }

        public String getOadetailMemo()
        {
                return this.oadetailMemo;
        }

        public void setOadetailMemo(String oadetailMemo)
        {
                this.oadetailMemo = oadetailMemo;
        }

        public String getOadetailRemark1()
        {
                return this.oadetailRemark1;
        }

        public void setOadetailRemark1(String oadetailRemark1)
        {
                this.oadetailRemark1 = oadetailRemark1;
        }

        public String getOadetailRemark2()
        {
                return this.oadetailRemark2;
        }

        public void setOadetailRemark2(String oadetailRemark2)
        {
                this.oadetailRemark2 = oadetailRemark2;
        }

        public int getAspId()
        {
                return aspId;
        }

        public void setAspId(int aspId)
        {
                this.aspId = aspId;
        }

        public String toString()
        {
                return new ToStringBuilder(this).append("oadetailId", getOadetailId())
                        .toString();
        }
}
