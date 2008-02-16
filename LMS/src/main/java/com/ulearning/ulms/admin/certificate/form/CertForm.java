/**
 * CertForm.java.
 * User: huangsb  Date: 2004-4-26
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd. * All rights reserved.
 */
package com.ulearning.ulms.admin.certificate.form;

import com.ulearning.ulms.admin.certificate.model.CertModel;
import com.ulearning.ulms.core.util.DateTimeUtil;
import com.ulearning.ulms.core.util.StringUtil;
import com.ulearning.ulms.course.util.CourseKeys;

import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;


public class CertForm extends ActionForm
{
        private int certificateID = 0;
        private String code = null;
        private String name = null;
        private int catalogID = 0;
        private int orgID = 0;
        private int aspID = 0;
        private String type = "1";
        private String status = "";
        private int creator = 0;
        private String key = null;
        private int feePolicy = 0;
        private double fee = 0;
        private int life = 0;
        private int guest = 0;
        private String description = null;
        private int manage = 0;
        private String compulsoryType = "";
        private double compulsoryCredit = 0;
        private String totalType = "";
        private double totalCredit = 0;
        private int regMethed = 0;
        private int approve = 0;
        private Date regTimeBegin = null;
        private Date regTimeEnd = null;
        private String password = "";
        private int lifeForever = 0;
        private Date lifeBeginDate = null; //new java.sql.Date(System.currentTimeMillis());
        private Date lifeEndDate = null; //new java.sql.Date(System.currentTimeMillis());
        private int selectMethod = 0;
        private Date selectBeginDate = new java.sql.Date(System.currentTimeMillis());
        private Date selectEndDate = new java.sql.Date(System.currentTimeMillis());
        private String courseIDStr = "";
        private String certType = "";
        private String approveUser = "";
        private String approveUser1 = "";
        private String approveUser2 = "";
        private String masterID = "";
        private String regStartDate = "";
        private String regEndDate = "";
        private String contactPeople = "";
        private String contactMode = "";
        private String url = "";
        private String remark = "";
        private String operator = ""; //操作员
        private int studenthow; //允许学生数

        public CertForm(CertModel cm)
        {
                this.certificateID = cm.getCertificateID();
                this.code = cm.getCode();
                this.name = cm.getName();
                this.catalogID = cm.getCatalogID();
                this.orgID = cm.getOrgID();
                this.aspID = cm.getAspID();
                this.key = cm.getKeyWord();
                this.status = cm.getStatus();
                this.creator = cm.getCreator();
                this.feePolicy = Integer.parseInt(cm.getFeePolicy());
                this.fee = cm.getFee();
                this.life = cm.getLife();
                this.guest = Integer.parseInt(cm.getGuest());
                this.description = cm.getDescription();
                this.manage = Integer.parseInt(cm.getManage());
                this.compulsoryType = cm.getCompulsoryType();
                this.compulsoryCredit = cm.getCompulsoryCredit();
                this.totalType = cm.getTotalType();
                this.totalCredit = cm.getTotalCredit();
                this.regMethed = Integer.parseInt(cm.getRegMethed());
                this.regTimeBegin = cm.getRegTimeBegin();
                this.regTimeEnd = cm.getRegTimeEnd();
                this.password = cm.getPassword();
                this.lifeForever = Integer.parseInt(cm.getLifeForever());
                this.lifeBeginDate = cm.getLifeBeginDate();
                this.lifeEndDate = cm.getLifeEndDate();
                this.selectMethod = Integer.parseInt(cm.getSelectMethod());
                this.selectBeginDate = cm.getSelectBeginDate();
                this.selectEndDate = cm.getSelectEndDate();
                this.certType = String.valueOf(cm.getCertType());
                this.approveUser = cm.getApproveUser();
                this.masterID = String.valueOf(cm.getMasterID());
                this.regStartDate = DateTimeUtil.FormatDateToWeb1(cm.getLifeBeginDate());
                this.regEndDate = DateTimeUtil.FormatDateToWeb1(cm.getLifeEndDate());
                this.contactMode = cm.getContactMode();
                this.contactPeople = cm.getContactPeople();
                this.url = cm.getUrl();
                this.remark = cm.getRemark();
                this.operator = cm.getOperator();
                this.studenthow = cm.getStudenthow();

                //process for certificate apply style, the default value for approve is 0(not checked),if the
                //regmethod == CourseKeys.REGISTER_TYPE_ALLOW_NEEDAPPROVE,the regmethod and approve are all 1(checked);
                if (cm.getRegMethed() != null)
                {
                        int regMethod = Integer.parseInt(cm.getRegMethed());

                        if (regMethod == CourseKeys.REGISTER_TYPE_ALLOW_NEEDAPPROVE)
                        {
                                this.regMethed = 1;
                                this.approve = 1;
                        }
                }
        }

        public CertForm()
        {
        }

        public CertForm(int certificateID, String name, String description)
        {
                this.certificateID = certificateID;
                this.name = name;
                this.description = description;
        }

        public CertForm(int certificateID, String name, String code,
                        String description)
        {
                this.certificateID = certificateID;
                this.name = name;
                this.code = code;
                this.description = description;
        }

        public String getCourseIDStr()
        {
                return courseIDStr;
        }

        public void setCourseIDStr(String courseIDStr)
        {
                this.courseIDStr = courseIDStr;
        }

        /**
         * Validate the properties that have been set from this HTTP request,
         * and return an <code>ActionErrors</code> object that encapsulates any
         * validation errors that have been found.  If no errors are found, return
         * <code>null</code> or an <code>ActionErrors</code> object with no
         * recorded error messages.
         *
         * @param mapping The mapping used to select this instance
         * @param request The servlet request we are processing
         */
        public ActionErrors validate(ActionMapping mapping,
                                     HttpServletRequest request)
        {
                ActionErrors errors = new ActionErrors();

                if ((name == null) || (name.length() < 1))
                {
                        errors.add("name", new ActionError("error.master.name.required"));
                }

                return (errors);
        }

        public int getCertificateID()
        {
                return certificateID;
        }

        public void setCertificateID(int certificateID)
        {
                this.certificateID = certificateID;
        }

        public String getCode()
        {
                return code;
        }

        public void setCode(String code)
        {
                this.code = code;
        }

        public String getName()
        {
                return name;
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

        public String getType()
        {
                return type;
        }

        public void setType(String type)
        {
                this.type = type;
        }

        public String getKey()
        {
                return key;
        }

        public void setKey(String key)
        {
                this.key = key;
        }

        public int getFeePolicy()
        {
                return feePolicy;
        }

        public void setFeePolicy(int feePolicy)
        {
                this.feePolicy = feePolicy;
        }

        public double getFee()
        {
                return fee;
        }

        public void setFee(double fee)
        {
                this.fee = fee;
        }

        public int getLife()
        {
                return life;
        }

        public void setLife(int life)
        {
                this.life = life;
        }

        public int getGuest()
        {
                return guest;
        }

        public void setGuest(int guest)
        {
                this.guest = guest;
        }

        public String getDescription()
        {
                return description;
        }

        public void setDescription(String description)
        {
                this.description = description;
        }

        public int getManage()
        {
                return manage;
        }

        public void setManage(int manage)
        {
                this.manage = manage;
        }

        public double getCompulsoryCredit()
        {
                return compulsoryCredit;
        }

        public void setCompulsoryCredit(double compulsoryCredit)
        {
                this.compulsoryCredit = compulsoryCredit;
        }

        public int getRegMethed()
        {
                return regMethed;
        }

        public void setRegMethed(int regMethed)
        {
                this.regMethed = regMethed;
        }

        public int getApprove()
        {
                return approve;
        }

        public void setApprove(int approve)
        {
                this.approve = approve;
        }

        public Date getRegTimeBegin()
        {
                return regTimeBegin;
        }

        public void setRegTimeBegin(Date regTimeBegin)
        {
                this.regTimeBegin = regTimeBegin;
        }

        public Date getRegTimeEnd()
        {
                return regTimeEnd;
        }

        public void setRegTimeEnd(Date regTimeEnd)
        {
                this.regTimeEnd = regTimeEnd;
        }

        public String getPassword()
        {
                return password;
        }

        public void setPassword(String password)
        {
                this.password = password;
        }

        public int getLifeForever()
        {
                return lifeForever;
        }

        public void setLifeForever(int lifeForever)
        {
                this.lifeForever = lifeForever;
        }

        public Date getLifeBeginDate()
        {
                return lifeBeginDate;
        }

        public void setLifeBeginDate(Date lifeBeginDate)
        {
                this.lifeBeginDate = lifeBeginDate;
        }

        public Date getLifeEndDate()
        {
                return lifeEndDate;
        }

        public void setLifeEndDate(Date lifeEndDate)
        {
                this.lifeEndDate = lifeEndDate;
        }

        public int getSelectMethod()
        {
                return selectMethod;
        }

        public void setSelectMethod(int selectMethod)
        {
                this.selectMethod = selectMethod;
        }

        public Date getSelectBeginDate()
        {
                return selectBeginDate;
        }

        public void setSelectBeginDate(Date selectBeginDate)
        {
                this.selectBeginDate = selectBeginDate;
        }

        public Date getSelectEndDate()
        {
                return selectEndDate;
        }

        public void setSelectEndDate(Date selectEndDate)
        {
                this.selectEndDate = selectEndDate;
        }

        public String getStatus()
        {
                return status;
        }

        public void setStatus(String status)
        {
                this.status = status;
        }

        public String getCompulsoryType()
        {
                return compulsoryType;
        }

        public void setCompulsoryType(String compulsoryType)
        {
                this.compulsoryType = compulsoryType;
        }

        public String getTotalType()
        {
                return totalType;
        }

        public void setTotalType(String totalType)
        {
                this.totalType = totalType;
        }

        public double getTotalCredit()
        {
                return totalCredit;
        }

        public void setTotalCredit(double totalCredit)
        {
                this.totalCredit = totalCredit;
        }

        public String getCertType()
        {
                return certType;
        }

        public void setCertType(String certType)
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

        public String getApproveUser1()
        {
                return approveUser1;
        }

        public void setApproveUser1(String approveUser1)
        {
                this.approveUser1 = approveUser1;
        }

        public String getApproveUser2()
        {
                return approveUser2;
        }

        public void setApproveUser2(String approveUser2)
        {
                this.approveUser2 = approveUser2;
        }

        public String getMasterID()
        {
                return masterID;
        }

        public void setMasterID(String masterID)
        {
                this.masterID = masterID;
        }

        public String getRegStartDate()
        {
                return regStartDate;
        }

        public void setRegStartDate(String regStartDate)
        {
                this.regStartDate = regStartDate;
        }

        public String getRegEndDate()
        {
                return regEndDate;
        }

        public void setRegEndDate(String regEndDate)
        {
                this.regEndDate = regEndDate;
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

        public CertModel getCertModel()
        {
                CertModel cm = new CertModel();
                cm.setCertificateID(this.certificateID);
                cm.setCode(this.code);
                cm.setName(this.name);
                cm.setCatalogID(this.catalogID);
                cm.setOrgID(this.orgID);
                cm.setAspID(this.aspID);
                cm.setKeyWord(this.key);
                cm.setStatus(this.status);
                cm.setCreator(this.creator);
                cm.setFeePolicy(String.valueOf(this.feePolicy));
                cm.setFee(this.fee);
                cm.setLife(this.life);
                cm.setGuest(String.valueOf(this.guest));
                cm.setDescription(this.description);
                cm.setManage(String.valueOf(this.manage));
                cm.setCompulsoryType(this.compulsoryType);
                cm.setCompulsoryCredit(this.compulsoryCredit);
                cm.setTotalType(this.totalType);
                cm.setTotalCredit(this.totalCredit);
                cm.setRegMethed(String.valueOf(this.regMethed));
                cm.setRegTimeBegin(this.regTimeBegin);
                cm.setRegTimeEnd(this.regTimeEnd);
                cm.setPassword(this.password);
                cm.setLifeForever(String.valueOf(this.lifeForever));
                cm.setContactMode(this.contactMode);
                cm.setContactPeople(this.contactPeople);
                cm.setUrl(this.url);
                cm.setRemark(this.remark);
                cm.setOperator(this.operator);
                cm.setStudenthow(this.studenthow);

                if ((this.regStartDate != null) && !this.regStartDate.equals(""))
                {
                        String[] tmp = StringUtil.splitString(this.regStartDate, "-");
                        cm.setLifeBeginDate(DateTimeUtil.toDate(tmp[1], tmp[2], tmp[0],
                                "0", "0", "0"));
                }
                else
                {
                        cm.setLifeBeginDate(this.lifeBeginDate);
                }

                if ((this.regEndDate != null) && !this.regEndDate.equals(""))
                {
                        String[] tmp = StringUtil.splitString(this.regEndDate, "-");
                        cm.setLifeEndDate(DateTimeUtil.toDate(tmp[1], tmp[2], tmp[0], "0",
                                "0", "0"));
                }
                else
                {
                        cm.setLifeEndDate(this.lifeEndDate);
                }

                cm.setSelectMethod(String.valueOf(this.selectMethod));
                cm.setSelectBeginDate(this.selectBeginDate);
                cm.setSelectEndDate(this.selectEndDate);
                cm.setCertType(StringUtil.parseInt(this.certType));
                cm.setApproveUser(this.approveUser1 + "|" + this.approveUser2);
                cm.setMasterID(StringUtil.parseInt(this.masterID));

                //process for certificate apply style, if the regmethod and approve are all 1(1 means the check box is checked)
                //the value for regmethod in model will be CourseKeys.REGISTER_TYPE_ALLOW_NEEDAPPROVE
                if ((regMethed == 1) && (approve == 1))
                {
                        cm.setRegMethed(new Integer(
                                CourseKeys.REGISTER_TYPE_ALLOW_NEEDAPPROVE).toString());
                }

                return cm;
        }
}
