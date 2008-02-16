/*
 * Copyright (c) 2004 Your Corporation. All Rights Reserved.
 */
package com.ulearning.ulms.course.model;

import com.ulearning.ulms.tools.upload.model.UploadForm;

import org.apache.struts.action.ActionForm;

import java.util.Date;


/**
 * @author <a href="mailto:youmail@yourdomain.com">yourname</a> Date: 2004-10-19
 */
public class ProgramForm extends CourseContentForm
{
        private int courseID;
        private int masterID;
        private String name;
        private String courseCode;
        private String masterName;
        private String description;
        private int orgID;
        private String type1;
        private String key;
        private int isInCatalog;
        private int creator;
        private int isCharge;
        private float charge1;
        private float memberCharge;
        private String chargeInfo;
        private Date establishDate;
        private Date modifyDate;
        private int allowFreedomReg;
        private int needApprove;
        private int registerMode;
        private int guest;
        private int catalogID;
        private int objectID;
        private int isSendMail;
        private int isTimeLimit;
        private String regStartDateValue;
        private Date regStartDate;
        private Date regEndDate;
        private String regEndDateValue;
        private int isPassword;
        private String password1;
        private int lifeSort;
        private Date lifeStartDate;
        private Date lifeEndDate;
        private String lifeStartDateValue;
        private String lifeEndDateValue;
        private int lifeTime;
        private int status;
        private int maximumEnrollment;
        private int minimumEnrollment;
        private float period;
        private float credit;
        private int scoreType;
        private String scoreLimit;
        private int teachMode;
        private String logo;
        private String plan;
        private String register;
        private int courseUserStatus;

        public ProgramForm()
        {
        }

        public CourseModel getCourseModel()
        {
                CourseModel cm = new CourseModel();
                cm.setCourseID(this.courseID);
                cm.setMasterID(this.masterID);
                cm.setName(this.name);
                cm.setCourseCode(this.courseCode);
                cm.setMasterName(this.masterName);
                cm.setDescription(this.description);
                cm.setOrgID(this.orgID);
                cm.setType(this.type1);
                cm.setKey(this.key);
                cm.setInCatalog(this.isInCatalog);
                cm.setCreator(this.creator);
                cm.setIsCharge(this.isCharge);
                cm.setCharge(this.charge1);
                cm.setMemberCharge(this.memberCharge);
                cm.setChargeInfo(this.chargeInfo);
                cm.setEstablishDate(this.establishDate);
                cm.setModifyDate(this.modifyDate);
                cm.setAllowFreedomReg(this.allowFreedomReg);
                cm.setNeedApprove(this.needApprove);
                cm.setRegisterMode(this.registerMode);
                cm.setCatalogID(this.catalogID);
                cm.setObjectID(this.objectID);
                cm.setIsSendMail(this.isSendMail);
                cm.setIsTimeLimit(this.isTimeLimit);
                cm.setRegStartDate(this.regStartDate);
                cm.setRegStartDateValue(this.regStartDateValue);
                cm.setRegStartDate(this.regEndDate);
                cm.setRegEndDateValue(this.regEndDateValue);
                cm.setIsPassword(this.isPassword);
                cm.setPassword(this.password1);
                cm.setLifeSort(this.lifeSort);
                cm.setLifeStartDate(this.lifeStartDate);
                cm.setLifeEndDate(this.lifeEndDate);
                cm.setLifeStartDateValue(this.lifeStartDateValue);
                cm.setLifeEndDateValue(this.lifeEndDateValue);
                cm.setLifeTime(this.lifeTime);
                cm.setStatus(this.status);
                cm.setMaximumEnrollment(this.maximumEnrollment);
                cm.setMinimumEnrollment(this.minimumEnrollment);
                cm.setPeriod(this.period);
                cm.setCredit(this.credit);
                cm.setScoreType(this.scoreType);
                cm.setScoreLimit(this.scoreLimit);
                cm.setTeachMode(this.teachMode);
                cm.setLogo(this.logo);
                cm.setPlan(this.plan);
                cm.setRegister(this.register);

                return cm;
        }

        public int getCourseID()
        {
                return courseID;
        }

        public void setCourseID(int courseID)
        {
                this.courseID = courseID;
        }

        public int getMasterID()
        {
                return masterID;
        }

        public void setMasterID(int masterID)
        {
                this.masterID = masterID;
        }

        public String getName()
        {
                return name;
        }

        public void setName(String name)
        {
                this.name = name;
        }

        public String getCourseCode()
        {
                return courseCode;
        }

        public void setCourseCode(String courseCode)
        {
                this.courseCode = courseCode;
        }

        public String getMasterName()
        {
                return masterName;
        }

        public void setMasterName(String masterName)
        {
                this.masterName = masterName;
        }

        public String getDescription()
        {
                return description;
        }

        public void setDescription(String description)
        {
                this.description = description;
        }

        public int getOrgID()
        {
                return orgID;
        }

        public void setOrgID(int orgID)
        {
                this.orgID = orgID;
        }

        public String getType1()
        {
                return type1;
        }

        public void setType1(String type1)
        {
                this.type1 = type1;
        }

        public String getKey()
        {
                return key;
        }

        public void setKey(String key)
        {
                this.key = key;
        }

        public int getInCatalog()
        {
                return isInCatalog;
        }

        public void setInCatalog(int inCatalog)
        {
                isInCatalog = inCatalog;
        }

        public int getCreator()
        {
                return creator;
        }

        public void setCreator(int creator)
        {
                this.creator = creator;
        }

        public int getCharge()
        {
                return isCharge;
        }

        public void setCharge(int charge)
        {
                isCharge = charge;
        }

        public float getCharge1()
        {
                return charge1;
        }

        public void setCharge1(float charge1)
        {
                this.charge1 = charge1;
        }

        public float getMemberCharge()
        {
                return memberCharge;
        }

        public void setMemberCharge(float memberCharge)
        {
                this.memberCharge = memberCharge;
        }

        public String getChargeInfo()
        {
                return chargeInfo;
        }

        public void setChargeInfo(String chargeInfo)
        {
                this.chargeInfo = chargeInfo;
        }

        public Date getEstablishDate()
        {
                return establishDate;
        }

        public void setEstablishDate(Date establishDate)
        {
                this.establishDate = establishDate;
        }

        public Date getModifyDate()
        {
                return modifyDate;
        }

        public void setModifyDate(Date modifyDate)
        {
                this.modifyDate = modifyDate;
        }

        public int getAllowFreedomReg()
        {
                return allowFreedomReg;
        }

        public void setAllowFreedomReg(int allowFreedomReg)
        {
                this.allowFreedomReg = allowFreedomReg;
        }

        public int getNeedApprove()
        {
                return needApprove;
        }

        public void setNeedApprove(int needApprove)
        {
                this.needApprove = needApprove;
        }

        public int getRegisterMode()
        {
                return registerMode;
        }

        public void setRegisterMode(int registerMode)
        {
                this.registerMode = registerMode;
        }

        public int getGuest()
        {
                return guest;
        }

        public void setGuest(int guest)
        {
                this.guest = guest;
        }

        public int getCatalogID()
        {
                return catalogID;
        }

        public void setCatalogID(int catalogID)
        {
                this.catalogID = catalogID;
        }

        public int getObjectID()
        {
                return objectID;
        }

        public void setObjectID(int objectID)
        {
                this.objectID = objectID;
        }

        public int getSendMail()
        {
                return isSendMail;
        }

        public void setSendMail(int sendMail)
        {
                isSendMail = sendMail;
        }

        public int getTimeLimit()
        {
                return isTimeLimit;
        }

        public void setTimeLimit(int timeLimit)
        {
                isTimeLimit = timeLimit;
        }

        public String getRegStartDateValue()
        {
                return regStartDateValue;
        }

        public void setRegStartDateValue(String regStartDateValue)
        {
                this.regStartDateValue = regStartDateValue;
        }

        public Date getRegStartDate()
        {
                return regStartDate;
        }

        public void setRegStartDate(Date regStartDate)
        {
                this.regStartDate = regStartDate;
        }

        public Date getRegEndDate()
        {
                return regEndDate;
        }

        public void setRegEndDate(Date regEndDate)
        {
                this.regEndDate = regEndDate;
        }

        public String getRegEndDateValue()
        {
                return regEndDateValue;
        }

        public void setRegEndDateValue(String regEndDateValue)
        {
                this.regEndDateValue = regEndDateValue;
        }

        public int getPassword()
        {
                return isPassword;
        }

        public void setPassword(int password)
        {
                isPassword = password;
        }

        public String getPassword1()
        {
                return password1;
        }

        public void setPassword1(String password1)
        {
                this.password1 = password1;
        }

        public int getLifeSort()
        {
                return lifeSort;
        }

        public void setLifeSort(int lifeSort)
        {
                this.lifeSort = lifeSort;
        }

        public Date getLifeStartDate()
        {
                return lifeStartDate;
        }

        public void setLifeStartDate(Date lifeStartDate)
        {
                this.lifeStartDate = lifeStartDate;
        }

        public Date getLifeEndDate()
        {
                return lifeEndDate;
        }

        public void setLifeEndDate(Date lifeEndDate)
        {
                this.lifeEndDate = lifeEndDate;
        }

        public String getLifeStartDateValue()
        {
                return lifeStartDateValue;
        }

        public void setLifeStartDateValue(String lifeStartDateValue)
        {
                this.lifeStartDateValue = lifeStartDateValue;
        }

        public String getLifeEndDateValue()
        {
                return lifeEndDateValue;
        }

        public void setLifeEndDateValue(String lifeEndDateValue)
        {
                this.lifeEndDateValue = lifeEndDateValue;
        }

        public int getLifeTime()
        {
                return lifeTime;
        }

        public void setLifeTime(int lifeTime)
        {
                this.lifeTime = lifeTime;
        }

        public int getStatus()
        {
                return status;
        }

        public void setStatus(int status)
        {
                this.status = status;
        }

        public int getMaximumEnrollment()
        {
                return maximumEnrollment;
        }

        public void setMaximumEnrollment(int maximumEnrollment)
        {
                this.maximumEnrollment = maximumEnrollment;
        }

        public int getMinimumEnrollment()
        {
                return minimumEnrollment;
        }

        public void setMinimumEnrollment(int minimumEnrollment)
        {
                this.minimumEnrollment = minimumEnrollment;
        }

        public float getPeriod()
        {
                return period;
        }

        public void setPeriod(float period)
        {
                this.period = period;
        }

        public float getCredit()
        {
                return credit;
        }

        public void setCredit(float credit)
        {
                this.credit = credit;
        }

        public int getScoreType()
        {
                return scoreType;
        }

        public void setScoreType(int scoreType)
        {
                this.scoreType = scoreType;
        }

        public String getScoreLimit()
        {
                return scoreLimit;
        }

        public void setScoreLimit(String scoreLimit)
        {
                this.scoreLimit = scoreLimit;
        }

        public int getTeachMode()
        {
                return teachMode;
        }

        public void setTeachMode(int teachMode)
        {
                this.teachMode = teachMode;
        }

        public String getLogo()
        {
                return logo;
        }

        public void setLogo(String logo)
        {
                this.logo = logo;
        }

        public String getPlan()
        {
                return plan;
        }

        public void setPlan(String plan)
        {
                this.plan = plan;
        }

        public String getRegister()
        {
                return register;
        }

        public void setRegister(String register)
        {
                this.register = register;
        }

        public int getCourseUserStatus()
        {
                return courseUserStatus;
        }

        public void setCourseUserStatus(int courseUserStatus)
        {
                this.courseUserStatus = courseUserStatus;
        }
}
