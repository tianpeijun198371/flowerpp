/**
 * Created by IntelliJ IDEA.
 * User: zengwj
 * Date: 2004-9-3
 * Time: 16:10:24
 * To change this template use File | Settings | File Templates.
 */
package com.ulearning.ulms.admin.certificate.model;

import org.apache.commons.lang.builder.ToStringBuilder;

import java.io.Serializable;

import java.util.Date;


public class CertModel implements Serializable
{
        /**
         * identifier field
         */
        private int certificateID;

        /**
         * persistent field
         */
        private String code;

        /**
         * persistent field
         */
        private String name;

        /**
         * persistent field
         */
        private int catalogID;

        /**
         * persistent field
         */
        private int orgID;

        /**
         * persistent field
         */
        private int aspID;

        /**
         * persistent field
         */
        private String keyWord;

        /**
         * persistent field
         */
        private String status;

        /**
         * persistent field
         */
        private int creator;

        /**
         * nullable persistent field
         */
        private String feePolicy;

        /**
         * nullable persistent field
         */
        private double fee;

        /**
         * nullable persistent field
         */
        private int life;

        /**
         * nullable persistent field
         */
        private String guest;

        /**
         * nullable persistent field
         */
        private String description;

        /**
         * nullable persistent field
         */
        private String manage;

        /**
         * nullable persistent field
         */
        private String compulsoryType;

        /**
         * nullable persistent field
         */
        private double compulsoryCredit;

        /**
         * nullable persistent field
         */
        private String totalType;

        /**
         * nullable persistent field
         */
        private double totalCredit;

        /**
         * nullable persistent field
         */
        private String regMethed;

        /**
         * nullable persistent field
         */
        private Date regTimeBegin;

        /**
         * nullable persistent field
         */
        private Date regTimeEnd;

        /**
         * nullable persistent field
         */
        private String password;

        /**
         * nullable persistent field
         */
        private String lifeForever;

        /**
         * nullable persistent field
         */
        private Date lifeBeginDate;

        /**
         * nullable persistent field
         */
        private Date lifeEndDate;

        /**
         * nullable persistent field
         */
        private String selectMethod;

        /**
         * nullable persistent field
         */
        private Date selectBeginDate;

        /**
         * nullable persistent field
         */
        private Date selectEndDate;
        private int certType;
        private String approveUser;
        private int masterID;
        private String contactPeople = "";
        private String contactMode = "";
        private String url = "";
        private String remark = "";
        private String operator = ""; //操作员
        private int studenthow; //允许学生人数

        /**
         * full constructor
         */
        public CertModel(int certificateID, String code, String name,
                         int catalogID, int orgID, int aspID, String keyWord, String status,
                         int creator, String feePolicy, double fee, int life, String guest,
                         String description, String manage, String compulsoryType,
                         double compulsoryCredit, String totalType, double totalCredit,
                         String regMethed, Date regTimeBegin, Date regTimeEnd, String password,
                         String lifeForever, Date lifeBeginDate, Date lifeEndDate,
                         String selectMethod, Date selectBeginDate, Date selectEndDate,
                         int certType, String approveUser, int masterID, String operator,
                         int studenthow)
        {
                this.certificateID = certificateID;
                this.code = code;
                this.name = name;
                this.catalogID = catalogID;
                this.orgID = orgID;
                this.aspID = aspID;
                this.keyWord = keyWord;
                this.status = status;
                this.creator = creator;
                this.feePolicy = feePolicy;
                this.fee = fee;
                this.life = life;
                this.guest = guest;
                this.description = description;
                this.manage = manage;
                this.compulsoryType = compulsoryType;
                this.compulsoryCredit = compulsoryCredit;
                this.totalType = totalType;
                this.totalCredit = totalCredit;
                this.regMethed = regMethed;
                this.regTimeBegin = regTimeBegin;
                this.regTimeEnd = regTimeEnd;
                this.password = password;
                this.lifeForever = lifeForever;
                this.lifeBeginDate = lifeBeginDate;
                this.lifeEndDate = lifeEndDate;
                this.selectMethod = selectMethod;
                this.selectBeginDate = selectBeginDate;
                this.selectEndDate = selectEndDate;
                this.certType = certType;
                this.approveUser = approveUser;
                this.masterID = masterID;
                this.operator = operator;
                this.studenthow = studenthow;
        }

        /**
         * default constructor
         */
        public CertModel()
        {
        }

        /**
         * minimal constructor
         */
        public CertModel(String code, String name, String key, String status)
        {
                this.code = code;
                this.name = name;
                this.keyWord = key;
                this.status = status;
        }

        public int getCertificateID()
        {
                return this.certificateID;
        }

        public void setCertificateID(int certificateID)
        {
                this.certificateID = certificateID;
        }

        public String getCode()
        {
                return this.code;
        }

        public void setCode(String code)
        {
                this.code = code;
        }

        public String getName()
        {
                return this.name;
        }

        public void setName(String name)
        {
                this.name = name;
        }

        public int getCatalogID()
        {
                return catalogID;
        }

        public void setCatalogID(int catalogID)
        {
                this.catalogID = catalogID;
        }

        public int getOrgID()
        {
                return orgID;
        }

        public void setOrgID(int orgID)
        {
                this.orgID = orgID;
        }

        public int getAspID()
        {
                return aspID;
        }

        public void setAspID(int aspID)
        {
                this.aspID = aspID;
        }

        public int getCreator()
        {
                return creator;
        }

        public void setCreator(int creator)
        {
                this.creator = creator;
        }

        public String getKeyWord()
        {
                return this.keyWord;
        }

        public void setKeyWord(String keyWord)
        {
                this.keyWord = keyWord;
        }

        public String getStatus()
        {
                return this.status;
        }

        public void setStatus(String status)
        {
                this.status = status;
        }

        public String getFeePolicy()
        {
                return this.feePolicy;
        }

        public void setFeePolicy(String feePolicy)
        {
                this.feePolicy = feePolicy;
        }

        public double getFee()
        {
                return this.fee;
        }

        public void setFee(double fee)
        {
                this.fee = fee;
        }

        public int getLife()
        {
                return this.life;
        }

        public void setLife(int life)
        {
                this.life = life;
        }

        public String getGuest()
        {
                return this.guest;
        }

        public void setGuest(String guest)
        {
                this.guest = guest;
        }

        public String getDescription()
        {
                return this.description;
        }

        public void setDescription(String description)
        {
                this.description = description;
        }

        public String getManage()
        {
                return this.manage;
        }

        public void setManage(String manage)
        {
                this.manage = manage;
        }

        public String getCompulsoryType()
        {
                return this.compulsoryType;
        }

        public void setCompulsoryType(String compulsoryType)
        {
                this.compulsoryType = compulsoryType;
        }

        public double getCompulsoryCredit()
        {
                return this.compulsoryCredit;
        }

        public void setCompulsoryCredit(double compulsoryCredit)
        {
                this.compulsoryCredit = compulsoryCredit;
        }

        public String getTotalType()
        {
                return this.totalType;
        }

        public void setTotalType(String totalType)
        {
                this.totalType = totalType;
        }

        public double getTotalCredit()
        {
                return this.totalCredit;
        }

        public void setTotalCredit(double totalCredit)
        {
                this.totalCredit = totalCredit;
        }

        public String getRegMethed()
        {
                return this.regMethed;
        }

        public void setRegMethed(String regMethed)
        {
                this.regMethed = regMethed;
        }

        public Date getRegTimeBegin()
        {
                return this.regTimeBegin;
        }

        public void setRegTimeBegin(Date regTimeBegin)
        {
                this.regTimeBegin = regTimeBegin;
        }

        public Date getRegTimeEnd()
        {
                return this.regTimeEnd;
        }

        public void setRegTimeEnd(Date regTimeEnd)
        {
                this.regTimeEnd = regTimeEnd;
        }

        public String getPassword()
        {
                return this.password;
        }

        public void setPassword(String password)
        {
                this.password = password;
        }

        public String getLifeForever()
        {
                return this.lifeForever;
        }

        public void setLifeForever(String lifeForever)
        {
                this.lifeForever = lifeForever;
        }

        public Date getLifeBeginDate()
        {
                return this.lifeBeginDate;
        }

        public void setLifeBeginDate(Date lifeBeginDate)
        {
                this.lifeBeginDate = lifeBeginDate;
        }

        public Date getLifeEndDate()
        {
                return this.lifeEndDate;
        }

        public void setLifeEndDate(Date lifeEndDate)
        {
                this.lifeEndDate = lifeEndDate;
        }

        public String getSelectMethod()
        {
                return this.selectMethod;
        }

        public void setSelectMethod(String selectMethod)
        {
                this.selectMethod = selectMethod;
        }

        public Date getSelectBeginDate()
        {
                return this.selectBeginDate;
        }

        public void setSelectBeginDate(Date selectBeginDate)
        {
                this.selectBeginDate = selectBeginDate;
        }

        public Date getSelectEndDate()
        {
                return this.selectEndDate;
        }

        public void setSelectEndDate(Date selectEndDate)
        {
                this.selectEndDate = selectEndDate;
        }

        public int getCertType()
        {
                return certType;
        }

        public void setCertType(int certType)
        {
                this.certType = certType;
        }

        public String getApproveUser()
        {
                return approveUser;
        }

        public void setApproveUser(String approveUser)
        {
                this.approveUser = approveUser;
        }

        public int getMasterID()
        {
                return masterID;
        }

        public void setMasterID(int masterID)
        {
                this.masterID = masterID;
        }

        public String getContactPeople()
        {
                return contactPeople;
        }

        public void setContactPeople(String contactPeople)
        {
                this.contactPeople = contactPeople;
        }

        public String getContactMode()
        {
                return contactMode;
        }

        public void setContactMode(String contactMode)
        {
                this.contactMode = contactMode;
        }

        public String getUrl()
        {
                return url;
        }

        public void setUrl(String url)
        {
                this.url = url;
        }

        public String getRemark()
        {
                return remark;
        }

        public void setRemark(String remark)
        {
                this.remark = remark;
        }

        public String getOperator()
        {
                return operator;
        }

        public void setOperator(String operator)
        {
                this.operator = operator;
        }

        public int getStudenthow()
        {
                return studenthow;
        }

        public void setStudenthow(int studenthow)
        {
                this.studenthow = studenthow;
        }

        public String toString()
        {
                return new ToStringBuilder(this).append("certificateID",
                        getCertificateID()).toString();
        }
}
