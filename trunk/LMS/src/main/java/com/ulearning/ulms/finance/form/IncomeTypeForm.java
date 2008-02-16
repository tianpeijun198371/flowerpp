/**
 * IncomeTypeForm.java.
 * User: liz  Date: 2005-12-8
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.finance.form;

import com.ulearning.ulms.finance.model.FincomeTypeModel;


/**
 * ��֧���ͱ��Form
 *
 * @author Liz
 * @ date 2005.12.08
 */
public class IncomeTypeForm
{
        private int incomeID = 0; //ID
        private String incomeName; //����
        private int incomeType = 0; //�������˱��  1������ ;2������
        private String incomeRemark1; //����1
        private String incomeRemark2; //����2

        //private FincomeTypeModel typeModel = null;
        public IncomeTypeForm getIncomeTypeForm(FincomeTypeModel typeModel)
        {
                IncomeTypeForm typeFrm = new IncomeTypeForm();
                typeFrm.setIncomeID(typeModel.getIncomeId());
                typeFrm.setIncomeName(typeModel.getIncomeName());
                typeFrm.setIncomeType(typeModel.getIncomeType());
                typeFrm.setIncomeRemark1(typeModel.getIncomeRemark1());
                typeFrm.setIncomeRemark2(typeModel.getIncomeRemark2());

                return typeFrm;
        }

        public void setIncomeRemark2(String incomeRemark2)
        {
                this.incomeRemark2 = incomeRemark2;
        }

        public void setIncomeRemark1(String incomeRemark1)
        {
                this.incomeRemark1 = incomeRemark1;
        }

        public void setIncomeType(int incomeType)
        {
                this.incomeType = incomeType;
        }

        public void setIncomeName(String incomeName)
        {
                this.incomeName = incomeName;
        }

        public void setIncomeID(int incomeID)
        {
                this.incomeID = incomeID;
        }

        public String getIncomeName()
        {
                return incomeName;
        }

        public int getIncomeType()
        {
                return incomeType;
        }

        public String getIncomeRemark1()
        {
                return incomeRemark1;
        }

        public String getIncomeRemark2()
        {
                return incomeRemark2;
        }

        public int getIncomeID()
        {
                return incomeID;
        }
}
