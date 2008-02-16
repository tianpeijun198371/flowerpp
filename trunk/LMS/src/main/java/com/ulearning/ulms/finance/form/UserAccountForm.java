/**
 * UserAccountForm.java.
 * User: liz  Date: 2005-12-8
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.finance.form;

import com.ulearning.ulms.finance.model.FuserAccountModel;

import java.util.Date;


/**
 * �����ʻ�����Form
 *
 * @author Liz
 * @ date 2005.12.08
 */
public class UserAccountForm
{
        private int uAcotID = 0; //ID
        private int userID = 0; //�û�����
        private String uAcotUserName; //�û�����
        private double uAcotTotal = 0.0; //�ʻ����
        private int uAcotStatus = 0; //�ʻ�״̬  1������  2��������   3��...
        private Date uAcotlastUpDate; //���һ�θ���ʱ��
        private String uAcotLastUser; //���һ�θ�����
        private String uAcotDescription; // ����
        private String uAcotRemark1; //����1
        private String uAcotRemark2; //����2
        FuserAccountModel mainmodel = null;

        public FuserAccountModel getFuserAccountModel()
        {
                mainmodel = new FuserAccountModel();
                mainmodel.setUacotDescription(this.uAcotDescription);
                mainmodel.setUacotId(this.uAcotID);
                mainmodel.setUacotlastUpDate(this.uAcotlastUpDate);
                mainmodel.setUacotLastUser(this.uAcotLastUser);
                mainmodel.setUacotRemark1(this.uAcotRemark1);
                mainmodel.setUacotRemark2(this.uAcotRemark2);
                mainmodel.setUacotStatus(this.uAcotStatus);
                mainmodel.setUacotTotal(this.uAcotTotal);
                mainmodel.setUacotUserName(this.uAcotUserName);
                mainmodel.setUserId(this.userID);

                return mainmodel;
        }

        public UserAccountForm getUserAccountForm(FuserAccountModel model)
        {
                UserAccountForm form = new UserAccountForm();
                form.setuAcotDescription(model.getUacotDescription());
                form.setuAcotID(model.getUacotId());
                form.setuAcotlastUpDate(model.getUacotlastUpDate());
                form.setuAcotLastUser(model.getUacotLastUser());
                form.setuAcotRemark1(model.getUacotRemark1());
                form.setuAcotRemark2(model.getUacotRemark2());
                form.setuAcotStatus(model.getUacotStatus());
                form.setuAcotTotal(model.getUacotTotal());
                form.setuAcotUserName(model.getUacotUserName());
                form.setUserID(model.getUserId());

                return form;
        }

        public int getuAcotID()
        {
                return uAcotID;
        }

        public void setuAcotID(int uAcotID)
        {
                this.uAcotID = uAcotID;
        }

        public int getUserID()
        {
                return userID;
        }

        public void setUserID(int userID)
        {
                this.userID = userID;
        }

        public String getuAcotUserName()
        {
                return uAcotUserName;
        }

        public void setuAcotUserName(String uAcotUserName)
        {
                this.uAcotUserName = uAcotUserName;
        }

        public double getuAcotTotal()
        {
                return uAcotTotal;
        }

        public void setuAcotTotal(double uAcotTotal)
        {
                this.uAcotTotal = uAcotTotal;
        }

        public int getuAcotStatus()
        {
                return uAcotStatus;
        }

        public void setuAcotStatus(int uAcotStatus)
        {
                this.uAcotStatus = uAcotStatus;
        }

        public Date getuAcotlastUpDate()
        {
                return uAcotlastUpDate;
        }

        public void setuAcotlastUpDate(Date uAcotlastUpDate)
        {
                this.uAcotlastUpDate = uAcotlastUpDate;
        }

        public String getuAcotLastUser()
        {
                return uAcotLastUser;
        }

        public void setuAcotLastUser(String uAcotLastUser)
        {
                this.uAcotLastUser = uAcotLastUser;
        }

        public String getuAcotDescription()
        {
                return uAcotDescription;
        }

        public void setuAcotDescription(String uAcotDescription)
        {
                this.uAcotDescription = uAcotDescription;
        }

        public String getuAcotRemark1()
        {
                return uAcotRemark1;
        }

        public void setuAcotRemark1(String uAcotRemark1)
        {
                this.uAcotRemark1 = uAcotRemark1;
        }

        public String getuAcotRemark2()
        {
                return uAcotRemark2;
        }

        public void setuAcotRemark2(String uAcotRemark2)
        {
                this.uAcotRemark2 = uAcotRemark2;
        }
}
