/**
 * FinanceCerReportForm.java.
 * User: liz  Date: 2006-1-5
 * 财务统计报表，班级报表
 * All rights reserved.
 */
package com.ulearning.ulms.finance.form;

import org.apache.struts.action.ActionForm;

import java.util.Date;


public class FinanceCerReportForm extends ActionForm
{
        private String serialNo; //序号
        private String userName; //
        private String deptName;
        private String sumout;
        private String postalcode;
        private String cername;
        private String mail;
        private String phone;
        private Date date = null;
        private String account = null;
        private String useId = null;
        private String pay = "";
        private String howToPay = "";

        public String getPay()
        {
                return pay;
        }

        public void setPay(String pay)
        {
                this.pay = pay;
        }

        public String getHowToPay()
        {
                return howToPay;
        }

        public void setHowToPay(String howToPay)
        {
                this.howToPay = howToPay;
        }

        public String getUseId()
        {
                return useId;
        }

        public void setUseId(String useId)
        {
                this.useId = useId;
        }

        public String getAccount()
        {
                return account;
        }

        public void setAccount(String account)
        {
                this.account = account;
        }

        public Date getDate()
        {
                return date;
        }

        public void setDate(Date date)
        {
                this.date = date;
        }

        public String getMail()
        {
                return mail;
        }

        public void setMail(String mail)
        {
                this.mail = mail;
        }

        public String getPhone()
        {
                return phone;
        }

        public void setPhone(String phone)
        {
                this.phone = phone;
        }

        public String getCername()
        {
                return cername;
        }

        public void setCername(String cername)
        {
                this.cername = cername;
        }

        public String getSerialNo()
        {
                return serialNo;
        }

        public void setSerialNo(String serialNo)
        {
                this.serialNo = serialNo;
        }

        public String getUserName()
        {
                return userName;
        }

        public void setUserName(String userName)
        {
                this.userName = userName;
        }

        public String getDeptName()
        {
                return deptName;
        }

        public void setDeptName(String deptName)
        {
                this.deptName = deptName;
        }

        public String getSumout()
        {
                return sumout;
        }

        public void setSumout(String sumout)
        {
                this.sumout = sumout;
        }

        public String getPostalcode()
        {
                return postalcode;
        }

        public void setPostalcode(String postalcode)
        {
                this.postalcode = postalcode;
        }
}
