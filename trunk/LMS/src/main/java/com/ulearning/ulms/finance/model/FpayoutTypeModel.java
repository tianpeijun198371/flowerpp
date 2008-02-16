package com.ulearning.ulms.finance.model;

import org.apache.commons.lang.builder.ToStringBuilder;

import java.io.Serializable;


/**
 * 支出类型Form
 *
 * @author Hibernate CodeGenerator
 * @ date 2005.12.08
 */
public class FpayoutTypeModel implements Serializable
{
        /**
         * identifier field
         */
        private int payoutId; //ID

        /**
         * persistent field
         */
        private String payoutName; //支出名称

        /**
         * nullable persistent field
         */
        private int payoutType; //机构/个人标记  1：机构 ;2：个人

        /**
         * nullable persistent field
         */
        private String payoutRemark1; //留用1

        /**
         * nullable persistent field
         */
        private String payoutReamrk2; //留用2

        /**
         * full constructor
         */
        public FpayoutTypeModel(String payoutName, int payoutType,
                                String payoutRemark1, String payoutReamrk2)
        {
                this.payoutName = payoutName;
                this.payoutType = payoutType;
                this.payoutRemark1 = payoutRemark1;
                this.payoutReamrk2 = payoutReamrk2;
        }

        /**
         * default constructor
         */
        public FpayoutTypeModel()
        {
        }

        /**
         * minimal constructor
         */
        public FpayoutTypeModel(String payoutName)
        {
                this.payoutName = payoutName;
        }

        public int getPayoutId()
        {
                return this.payoutId;
        }

        public void setPayoutId(int payoutId)
        {
                this.payoutId = payoutId;
        }

        public String getPayoutName()
        {
                return this.payoutName;
        }

        public void setPayoutName(String payoutName)
        {
                this.payoutName = payoutName;
        }

        public int getPayoutType()
        {
                return this.payoutType;
        }

        public void setPayoutType(int payoutType)
        {
                this.payoutType = payoutType;
        }

        public String getPayoutRemark1()
        {
                return this.payoutRemark1;
        }

        public void setPayoutRemark1(String payoutRemark1)
        {
                this.payoutRemark1 = payoutRemark1;
        }

        public String getPayoutReamrk2()
        {
                return this.payoutReamrk2;
        }

        public void setPayoutReamrk2(String payoutReamrk2)
        {
                this.payoutReamrk2 = payoutReamrk2;
        }

        public String toString()
        {
                return new ToStringBuilder(this).append("payoutId", getPayoutId())
                        .toString();
        }
}
