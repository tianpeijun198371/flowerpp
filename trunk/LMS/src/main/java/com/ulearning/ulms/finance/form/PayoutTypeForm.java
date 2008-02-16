/**
 * PayoutTypeForm.java.
 * User: liz  Date: 2005-12-8
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.finance.form;

import com.ulearning.ulms.finance.model.FpayoutTypeModel;


/**
 * 支出类型Form
 *
 * @author Liz
 * @ date 2005.12.08
 */
public class PayoutTypeForm
{
        private int payoutID = 0; //ID
        private String payoutName; //支出名称
        private int payoutType = 0; //机构/个人标记  1：机构 ;2：个人
        private String payoutRemark1; //留用1
        private String payoutRemark2; //留用2

        public PayoutTypeForm getPayoutTypeForm(FpayoutTypeModel outModel)
        {
                PayoutTypeForm outFrm = new PayoutTypeForm();
                outFrm.setPayoutID(outModel.getPayoutId());
                outFrm.setPayoutName(outModel.getPayoutName());
                outFrm.setPayoutRemark1(outModel.getPayoutRemark1());
                outFrm.setPayoutRemark2(outModel.getPayoutReamrk2());
                outFrm.setPayoutType(outModel.getPayoutType());

                return outFrm;
        }

        public int getPayoutID()
        {
                return payoutID;
        }

        public void setPayoutID(int payoutID)
        {
                this.payoutID = payoutID;
        }

        public String getPayoutName()
        {
                return payoutName;
        }

        public void setPayoutName(String payoutName)
        {
                this.payoutName = payoutName;
        }

        public int getPayoutType()
        {
                return payoutType;
        }

        public void setPayoutType(int payoutType)
        {
                this.payoutType = payoutType;
        }

        public String getPayoutRemark1()
        {
                return payoutRemark1;
        }

        public void setPayoutRemark1(String payoutRemark1)
        {
                this.payoutRemark1 = payoutRemark1;
        }

        public String getPayoutRemark2()
        {
                return payoutRemark2;
        }

        public void setPayoutRemark2(String payoutRemark2)
        {
                this.payoutRemark2 = payoutRemark2;
        }
}
