package com.ulearning.ulms.finance.model;

import org.apache.commons.lang.builder.ToStringBuilder;

import java.io.Serializable;


/**
 * 收支类型表的Form
 *
 * @author Hibernate CodeGenerator
 * @ date 2005.12.08
 */
public class FincomeTypeModel implements Serializable
{
        /**
         * identifier field
         */
        private int incomeId; //ID

        /**
         * persistent field
         */
        private String incomeName; //名称

        /**
         * nullable persistent field
         */
        private int incomeType; //机构个人标记  1：机构 ;2：个人

        /**
         * nullable persistent field
         */
        private String incomeRemark1; //留用1

        /**
         * nullable persistent field
         */
        private String incomeRemark2; //留用2

        /**
         * full constructor
         */
        public FincomeTypeModel(String incomeName, int incomeType,
                                String incomeRemark1, String incomeRemark2)
        {
                this.incomeName = incomeName;
                this.incomeType = incomeType;
                this.incomeRemark1 = incomeRemark1;
                this.incomeRemark2 = incomeRemark2;
        }

        /**
         * default constructor
         */
        public FincomeTypeModel()
        {
        }

        /**
         * minimal constructor
         */
        public FincomeTypeModel(String incomeName)
        {
                this.incomeName = incomeName;
        }

        public int getIncomeId()
        {
                return this.incomeId;
        }

        public void setIncomeId(int incomeId)
        {
                this.incomeId = incomeId;
        }

        public String getIncomeName()
        {
                return this.incomeName;
        }

        public void setIncomeName(String incomeName)
        {
                this.incomeName = incomeName;
        }

        public int getIncomeType()
        {
                return this.incomeType;
        }

        public void setIncomeType(int incomeType)
        {
                this.incomeType = incomeType;
        }

        public String getIncomeRemark1()
        {
                return this.incomeRemark1;
        }

        public void setIncomeRemark1(String incomeRemark1)
        {
                this.incomeRemark1 = incomeRemark1;
        }

        public String getIncomeRemark2()
        {
                return this.incomeRemark2;
        }

        public void setIncomeRemark2(String incomeRemark2)
        {
                this.incomeRemark2 = incomeRemark2;
        }

        public String toString()
        {
                return new ToStringBuilder(this).append("incomeId", getIncomeId())
                        .toString();
        }
}
