/**
 * OrganAccountForm.java.
 * User: liz  Date: 2005-12-8
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.finance.form;

import com.ulearning.ulms.finance.model.ForganAccountModel;

import java.util.Date;


/**
 * 机构总帐Form
 *
 * @author Liz
 * @ date 2005.12.08
 */
public class OrganAccountForm
{
        private int oAcotID = 0; //ID
        private int orgID = 0; //机构主键
        private String oAcotOrganName; //机构名称
        private double oAcotTotal = 0.0; //帐户余额
        private int oAcotStatus = 0; //帐户状态  1：可用  2：不可用   3：...
        private Date oAcotlastUpDate; //最后一次更新时间
        private String oAcotLastUser; //最后一次更新人
        private String oAcotDescription; // 描述
        private String oAcotRemark1; //留用1
        private String oAcotRemark2; //留用2
        ForganAccountModel mainmodel = null;

        public ForganAccountModel getForganAccountModel()
        {
                mainmodel = new ForganAccountModel();
                mainmodel.setOacotDescription(this.oAcotDescription);
                mainmodel.setOacotId(this.oAcotID);
                mainmodel.setOacotLastUpdate(this.oAcotlastUpDate);
                mainmodel.setOacotLastUpUser(this.oAcotLastUser);
                mainmodel.setOacotRemark1(this.oAcotRemark1);
                mainmodel.setOacotRemark2(this.oAcotRemark2);
                mainmodel.setOacotStatus(this.oAcotStatus);
                mainmodel.setOacotTotal(this.oAcotTotal);
                mainmodel.setOacotOrganName(this.oAcotOrganName);
                mainmodel.setOrgId(this.orgID);

                return mainmodel;
        }

        public OrganAccountForm getOrganAccountForm(ForganAccountModel model)
        {
                OrganAccountForm form = new OrganAccountForm();
                form.setoAcotDescription(model.getOacotDescription());
                form.setoAcotID(model.getOacotId());
                form.setoAcotlastUpDate(model.getOacotLastUpdate());
                form.setoAcotLastUser(model.getOacotLastUpUser());
                form.setoAcotRemark1(model.getOacotRemark1());
                form.setoAcotRemark2(model.getOacotRemark2());
                form.setoAcotStatus(model.getOacotStatus());
                form.setoAcotTotal(model.getOacotTotal());
                form.setoAcotOrganName(model.getOacotOrganName());
                form.setOrgID(model.getOrgId());

                return form;
        }

        public int getoAcotID()
        {
                return oAcotID;
        }

        public void setoAcotID(int oAcotID)
        {
                this.oAcotID = oAcotID;
        }

        public int getOrgID()
        {
                return orgID;
        }

        public void setOrgID(int OrgID)
        {
                this.orgID = OrgID;
        }

        public String getoAcotOrganName()
        {
                return oAcotOrganName;
        }

        public void setoAcotOrganName(String oAcotOrganName)
        {
                this.oAcotOrganName = oAcotOrganName;
        }

        public double getoAcotTotal()
        {
                return oAcotTotal;
        }

        public void setoAcotTotal(double oAcotTotal)
        {
                this.oAcotTotal = oAcotTotal;
        }

        public int getoAcotStatus()
        {
                return oAcotStatus;
        }

        public void setoAcotStatus(int oAcotStatus)
        {
                this.oAcotStatus = oAcotStatus;
        }

        public Date getoAcotlastUpDate()
        {
                return oAcotlastUpDate;
        }

        public void setoAcotlastUpDate(Date oAcotlastUpDate)
        {
                this.oAcotlastUpDate = oAcotlastUpDate;
        }

        public String getoAcotLastUser()
        {
                return oAcotLastUser;
        }

        public void setoAcotLastUser(String oAcotLastUser)
        {
                this.oAcotLastUser = oAcotLastUser;
        }

        public String getoAcotDescription()
        {
                return oAcotDescription;
        }

        public void setoAcotDescription(String oAcotDescription)
        {
                this.oAcotDescription = oAcotDescription;
        }

        public String getoAcotRemark1()
        {
                return oAcotRemark1;
        }

        public void setoAcotRemark1(String oAcotRemark1)
        {
                this.oAcotRemark1 = oAcotRemark1;
        }

        public String getoAcotRemark2()
        {
                return oAcotRemark2;
        }

        public void setoAcotRemark2(String oAcotRemark2)
        {
                this.oAcotRemark2 = oAcotRemark2;
        }
}
