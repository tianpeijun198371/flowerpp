package com.ulearning.ulms.finance.model;

import org.apache.commons.lang.builder.ToStringBuilder;

import java.io.Serializable;

import java.util.Date;


/**
 * �����ʻ���ϸForm
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
        private int orgId; //����ID

        /**
         * nullable persistent field
         */
        private String oadetailOrganName; //��������

        /**
         * persistent field
         */
        private int oadetailInOutType; //��֧ģʽ:   1����;2��֧

        /**
         * persistent field
         */
        private int oadetailTypeId; //�������֧����

        /**
         * nullable persistent field
         */
        private double oadetailAmount; // ���

        /**
         * nullable persistent field
         */
        private Date oadetailDate; //����ʱ��

        /**
         * persistent field
         */
        private int oadetailOperatorId; //����ԱID

        /**
         * nullable persistent field
         */
        private String oadetailOperatorName; //����Ա����

        /**
         * persistent field
         */
        private int oadetailEntityValue; //��֧����ʵ��ID��ֵ

        /**
         * nullable persistent field
         */
        private int oadetailRemitType; //��ʽ

        /**
         * nullable persistent field
         */
        private int oadetailInvoice; //��Ʊ��� 0��û��;1���ѿ�

        /**
         * nullable persistent field
         */
        private int oadetailAudit; //�Ƿ���� 0��δ���  1�������

        /**
         * nullable persistent field
         */
        private String oadetailMemo; //��ע

        /**
         * nullable persistent field
         */
        private String oadetailRemark1; //����1

        /**
         * nullable persistent field
         */
        private String oadetailRemark2; //����2
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
