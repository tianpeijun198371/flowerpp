/**
 * CertForm.java.
 * User: huangsb  Date: 2004-4-26
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd. * All rights reserved.
 */
package com.ulearning.ulms.admin.certificate.form;

import com.ulearning.ulms.admin.certificate.model.CertPlanModel;

import org.apache.struts.action.ActionForm;

import java.util.Date;


public class CertPlanForm extends ActionForm
{
        private int cpAcotID = 0; //prime key
        private String cpCertName = " ";
        private int cpUserNumber = 0;
        private double cpCharge = 0.00; //the charge for this cert
        private Date cpComeDate = new java.sql.Date(System.currentTimeMillis()); //begin time
        private Date cpGoDate = new java.sql.Date(System.currentTimeMillis()); //end time
        private String cpTrainPlace = " ";
        private String cpRestPlace = " ";
        private double cpRestCharge = 0.00;
        private String cpFoodPlace = " ";
        private double cpFoodCharge = 0.00;
        private String cpTeacher = " ";
        private String cpremark = " ";
        private String cpremark1 = " ";
        private String cpremark2 = " ";
        private String asscpComeDate; //辅助cpComeDate 接收JSP页面的日期值　
        private String asscpGoDate; //辅助cpGoDate 接收JSP页面的日期值　

        public CertPlanForm(int cpAcotID, String cpCertName, int cpUserNumber,
                            double cpCharge, Date cpComeDate, Date cpGoDate, String cpTrainPlace,
                            String cpRestPlace, double cpRestCharge, String cpFoodPlace,
                            double cpFoodCharge, String cpTeacher, String cpremark,
                            String cpremark1, String cpremark2)
        {
                this.cpAcotID = cpAcotID;
                this.cpCertName = cpCertName;
                this.cpUserNumber = cpUserNumber;
                this.cpCharge = cpCharge;
                this.cpComeDate = cpComeDate;
                this.cpGoDate = cpGoDate;
                this.cpTrainPlace = cpTrainPlace;
                this.cpRestPlace = cpRestPlace;
                this.cpRestCharge = cpRestCharge;
                this.cpFoodPlace = cpFoodPlace;
                this.cpFoodCharge = cpFoodCharge;
                this.cpTeacher = cpTeacher;
                this.cpremark = cpremark;
                this.cpremark1 = cpremark1;
                this.cpremark2 = cpremark2;
        }

        public CertPlanForm()
        {
        }

        public CertPlanForm(CertPlanModel mod)
        {
                this.cpAcotID = mod.getCpacotid();
                this.cpCertName = mod.getCpcertname();
                this.cpUserNumber = mod.getCpusernumber();
                this.cpCharge = mod.getCpcharge();
                this.cpComeDate = mod.getCpcomedate();
                this.cpGoDate = mod.getCpgodate();
                this.cpTrainPlace = mod.getCptrainplace();
                this.cpRestPlace = mod.getCprestplace();
                this.cpRestCharge = mod.getCprestcharge();
                this.cpFoodPlace = mod.getCpfoodplace();
                this.cpFoodCharge = mod.getCpfoodcharge();
                this.cpTeacher = mod.getCpteacher();
                this.cpremark = mod.getCpremark();
                this.cpremark1 = mod.getCpremark1();
                this.cpremark2 = mod.getCpremark2();
        }

        public String getAsscpComeDate()
        {
                return asscpComeDate;
        }

        public void setAsscpComeDate(String asscpComeDate)
        {
                this.asscpComeDate = asscpComeDate;
        }

        public String getAsscpGoDate()
        {
                return asscpGoDate;
        }

        public void setAsscpGoDate(String asscpGoDate)
        {
                this.asscpGoDate = asscpGoDate;
        }

        public void setCpAcotID(int cpAcotID)
        {
                this.cpAcotID = cpAcotID;
        }

        public void setCpCertName(String cpCertName)
        {
                this.cpCertName = cpCertName;
        }

        public void setCpUserNumber(int cpUserNumber)
        {
                this.cpUserNumber = cpUserNumber;
        }

        public void setCpCharge(double cpCharge)
        {
                this.cpCharge = cpCharge;
        }

        public void setCpComeDate(Date cpComeDate)
        {
                this.cpComeDate = cpComeDate;
        }

        public void setCpGoDate(Date cpGoDate)
        {
                this.cpGoDate = cpGoDate;
        }

        public void setCpTrainPlace(String cpTrainPlace)
        {
                this.cpTrainPlace = cpTrainPlace;
        }

        public void setCpRestPlace(String cpRestPlace)
        {
                this.cpRestPlace = cpRestPlace;
        }

        public void setCpRestCharge(double cpRestCharge)
        {
                this.cpRestCharge = cpRestCharge;
        }

        public void setCpFoodPlace(String cpFoodPlace)
        {
                this.cpFoodPlace = cpFoodPlace;
        }

        public void setCpFoodCharge(double cpFoodCharge)
        {
                this.cpFoodCharge = cpFoodCharge;
        }

        public void setCpTeacher(String cpTeacher)
        {
                this.cpTeacher = cpTeacher;
        }

        public void setCpremark(String cpremark)
        {
                this.cpremark = cpremark;
        }

        public void setCpremark1(String cpremark1)
        {
                this.cpremark1 = cpremark1;
        }

        public void setCpremark2(String cpremark2)
        {
                this.cpremark2 = cpremark2;
        }

        public int getCpAcotID()
        {
                return cpAcotID;
        }

        public String getCpCertName()
        {
                return cpCertName;
        }

        public int getCpUserNumber()
        {
                return cpUserNumber;
        }

        public double getCpCharge()
        {
                return cpCharge;
        }

        public Date getCpComeDate()
        {
                return cpComeDate;
        }

        public Date getCpGoDate()
        {
                return cpGoDate;
        }

        public String getCpTrainPlace()
        {
                return cpTrainPlace;
        }

        public String getCpRestPlace()
        {
                return cpRestPlace;
        }

        public double getCpRestCharge()
        {
                return cpRestCharge;
        }

        public String getCpFoodPlace()
        {
                return cpFoodPlace;
        }

        public double getCpFoodCharge()
        {
                return cpFoodCharge;
        }

        public String getCpTeacher()
        {
                return cpTeacher;
        }

        public String getCpremark()
        {
                return cpremark;
        }

        public String getCpremark1()
        {
                return cpremark1;
        }

        public String getCpremark2()
        {
                return cpremark2;
        }

        public CertPlanModel getModel()
        {
                CertPlanModel ccm = new CertPlanModel();
                ccm.setCpacotid(this.getCpAcotID());
                ccm.setCpcertname(this.getCpCertName());
                ccm.setCpusernumber(this.getCpUserNumber());
                ccm.setCpcharge(this.getCpCharge());
                ccm.setCpcomedate(this.getCpComeDate());
                ccm.setCpgodate(this.getCpGoDate());
                ccm.setCptrainplace(this.getCpTrainPlace());
                ccm.setCprestplace(this.getCpRestPlace());
                ccm.setCprestcharge(this.getCpRestCharge());
                ccm.setCpfoodplace(this.getCpFoodPlace());
                ccm.setCpfoodcharge(this.getCpFoodCharge());
                ccm.setCpteacher(this.getCpTeacher());
                ccm.setCpremark(this.getCpremark());
                ccm.setCpremark1(this.getCpremark1());
                ccm.setCpremark2(this.getCpremark2());

                return ccm;
        }
}
