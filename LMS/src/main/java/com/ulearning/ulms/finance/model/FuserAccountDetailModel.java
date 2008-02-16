package com.ulearning.ulms.finance.model;

import org.apache.commons.lang.builder.ToStringBuilder;

import java.io.Serializable;

import java.util.Date;


/**
 * �����ʻ���֧��ϸForm
 *
 * @author Hibernate CodeGenerator
 * @ date 2005.12.08
 */
public class FuserAccountDetailModel implements Serializable
{
        /**
         * identifier field
         */
        private int uadetailId; //ID

        /**
         * persistent field
         */
        private int userId; //�û�ID

        /**
         * nullable persistent field
         */
        private String uadetailUserName; //�û�����

        /**
         * persistent field
         */
        private int uadetailInOutType; //��֧ģʽ:   1����;2��֧

        /**
         * persistent field
         */
        private int uadetailTypeId; //�������֧����

        /**
         * nullable persistent field
         */
        private double uadetailAmount; //���

        /**
         * nullable persistent field
         */
        private Date uadetailDate; //����ʱ��

        /**
         * persistent field
         */
        private int uadetailOperatorId; //����ԱID

        /**
         * nullable persistent field
         */
        private String uadetailOperatorName; //����Ա����

        /**
         * persistent field
         */
        private int uadetailEntityValue; //��֧����ʵ��ID��ֵ

        /**
         * nullable persistent field
         */
        private int uadetailRemitType; //��ʽ

        /**
         * nullable persistent field
         */
        private int uadetailInvoice; //��Ʊ��� 0��û��;1���ѿ�

        /**
         * nullable persistent field
         */
        private int uadetailAudit; //�Ƿ���� 0��δ���  1�������

        /**
         * nullable persistent field
         */
        private String uadetailMemo; //��ע

        /**
         * nullable persistent field
         */
        private String uadetailRemark1; //����1

        /**
         * nullable persistent field
         */
        private String uadetailRemark2; //����2
        private int aspId; //aspID
        private int orgId; //����ID

        /**
         * full constructor
         */
        public FuserAccountDetailModel(int userId, String uadetailUserName,
                                       int uadetailInOutType, int uadetailTypeId, double uadetailAmount,
                                       Date uadetailDate, int uadetailOperatorId, String uadetailOperatorName,
                                       int uadetailEntityValue, int uadetailRemitType, int uadetailInvoice,
                                       int uadetailAudit, String uadetailMemo, String uadetailRemark1,
                                       String uadetailRemark2, int aspId, int orgId)
        {
                this.userId = userId;
                this.uadetailUserName = uadetailUserName;
                this.uadetailInOutType = uadetailInOutType;
                this.uadetailTypeId = uadetailTypeId;
                this.uadetailAmount = uadetailAmount;
                this.uadetailDate = uadetailDate;
                this.uadetailOperatorId = uadetailOperatorId;
                this.uadetailOperatorName = uadetailOperatorName;
                this.uadetailEntityValue = uadetailEntityValue;
                this.uadetailRemitType = uadetailRemitType;
                this.uadetailInvoice = uadetailInvoice;
                this.uadetailAudit = uadetailAudit;
                this.uadetailMemo = uadetailMemo;
                this.uadetailRemark1 = uadetailRemark1;
                this.uadetailRemark2 = uadetailRemark2;
                this.aspId = aspId;
                this.orgId = orgId;
        }

        /**
         * default constructor
         */
        public FuserAccountDetailModel()
        {
        }

        /**
         * minimal constructor
         */
        public FuserAccountDetailModel(int userId, int uadetailInOutType,
                                       int uadetailTypeId, int uadetailOperatorId, int uadetailEntityValue)
        {
                this.userId = userId;
                this.uadetailInOutType = uadetailInOutType;
                this.uadetailTypeId = uadetailTypeId;
                this.uadetailOperatorId = uadetailOperatorId;
                this.uadetailEntityValue = uadetailEntityValue;
        }

        public int getUadetailId()
        {
                return this.uadetailId;
        }

        public void setUadetailId(int uadetailId)
        {
                this.uadetailId = uadetailId;
        }

        public int getUserId()
        {
                return this.userId;
        }

        public void setUserId(int userId)
        {
                this.userId = userId;
        }

        public String getUadetailUserName()
        {
                return this.uadetailUserName;
        }

        public void setUadetailUserName(String uadetailUserName)
        {
                this.uadetailUserName = uadetailUserName;
        }

        public int getUadetailInOutType()
        {
                return this.uadetailInOutType;
        }

        public void setUadetailInOutType(int uadetailInOutType)
        {
                this.uadetailInOutType = uadetailInOutType;
        }

        public int getUadetailTypeId()
        {
                return this.uadetailTypeId;
        }

        public void setUadetailTypeId(int uadetailTypeId)
        {
                this.uadetailTypeId = uadetailTypeId;
        }

        public double getUadetailAmount()
        {
                return this.uadetailAmount;
        }

        public void setUadetailAmount(double uadetailAmount)
        {
                this.uadetailAmount = uadetailAmount;
        }

        public Date getUadetailDate()
        {
                return this.uadetailDate;
        }

        public void setUadetailDate(Date uadetailDate)
        {
                this.uadetailDate = uadetailDate;
        }

        public int getUadetailOperatorId()
        {
                return this.uadetailOperatorId;
        }

        public void setUadetailOperatorId(int uadetailOperatorId)
        {
                this.uadetailOperatorId = uadetailOperatorId;
        }

        public String getUadetailOperatorName()
        {
                return this.uadetailOperatorName;
        }

        public void setUadetailOperatorName(String uadetailOperatorName)
        {
                this.uadetailOperatorName = uadetailOperatorName;
        }

        public int getUadetailEntityValue()
        {
                return this.uadetailEntityValue;
        }

        public void setUadetailEntityValue(int uadetailEntityValue)
        {
                this.uadetailEntityValue = uadetailEntityValue;
        }

        public int getUadetailRemitType()
        {
                return this.uadetailRemitType;
        }

        public void setUadetailRemitType(int uadetailRemitType)
        {
                this.uadetailRemitType = uadetailRemitType;
        }

        public int getUadetailInvoice()
        {
                return this.uadetailInvoice;
        }

        public void setUadetailInvoice(int uadetailInvoice)
        {
                this.uadetailInvoice = uadetailInvoice;
        }

        public int getUadetailAudit()
        {
                return this.uadetailAudit;
        }

        public void setUadetailAudit(int uadetailAudit)
        {
                this.uadetailAudit = uadetailAudit;
        }

        public String getUadetailMemo()
        {
                return this.uadetailMemo;
        }

        public void setUadetailMemo(String uadetailMemo)
        {
                this.uadetailMemo = uadetailMemo;
        }

        public String getUadetailRemark1()
        {
                return this.uadetailRemark1;
        }

        public void setUadetailRemark1(String uadetailRemark1)
        {
                this.uadetailRemark1 = uadetailRemark1;
        }

        public String getUadetailRemark2()
        {
                return this.uadetailRemark2;
        }

        public void setUadetailRemark2(String uadetailRemark2)
        {
                this.uadetailRemark2 = uadetailRemark2;
        }

        public int getAspId()
        {
                return aspId;
        }

        public void setAspId(int aspId)
        {
                this.aspId = aspId;
        }

        public int getOrgId()
        {
                return orgId;
        }

        public void setOrgId(int orgId)
        {
                this.orgId = orgId;
        }

        public String toString()
        {
                return new ToStringBuilder(this).append("uadetailId", getUadetailId())
                        .append("uadetailDate",
                                getUadetailDate()).toString();
        }
}
