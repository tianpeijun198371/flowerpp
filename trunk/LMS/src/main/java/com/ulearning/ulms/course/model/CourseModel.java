/**
 * CourseModel.java.
 * User: fengch  Date: 2004-5-26
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.course.model;

import com.ulearning.ulms.core.util.StringUtil;
import com.ulearning.ulms.tools.upload.model.UploadForm;

import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;


public class CourseModel extends UploadForm
{
        private int courseID;
        private int masterID;
        private String name;
        private String courseCode;
        private String masterName;
        private String description;
        private int orgID;
        private int aspID;
        private String type;
        private String key;
        private int isInCatalog;
        private int creator;
        private int isCharge;
        private float charge;
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
        private String password;
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
        private String operator; //操作人
        private String logopic; //操作人
        private String isCommend; //操作人
        private String remark; //操作人
        private int pageroffset=0;

        public CourseModel()
        {
        }

        public CourseModel(int courseID, String name, String courseCode,
                           String masterName, String description)
        {
                this.courseID = courseID;
                this.name = name;
                this.masterName = masterName;
                this.description = description;
                this.courseCode = courseCode;
        }

        public CourseModel(int courseID, String name, String courseCode,
                           String masterName, String description, int guest, String operator)
        {
                this.courseID = courseID;
                this.name = name;
                this.masterName = masterName;
                this.description = description;
                this.courseCode = courseCode;
                this.guest = guest;
                this.operator = operator;
        }

        public CourseModel(int courseID, String name, String courseCode,
                           String masterName, String description, int guest,
                           String operator,Date lifeStartDate,String logopic,String remark,String teachMode,int aspID)
        {
                this.courseID = courseID;
                this.name = name;
                this.masterName = masterName;
                this.description = description;
                this.courseCode = courseCode;
                this.guest = guest;
                this.operator = operator;
                this.lifeStartDate =lifeStartDate;
                this.logopic=logopic;
                this.remark =remark;
                this.teachMode=Integer.parseInt(teachMode);
                this.aspID = aspID;
        }

        public CourseModel(int courseID, String name, String courseCode,
                           String masterName, String description, int guest,
                           String operator,Date lifeStartDate,String logopic,String remark,String teachMode,int aspID,int catalogID)
        {
                this.courseID = courseID;
                this.name = name;
                this.masterName = masterName;
                this.description = description;
                this.courseCode = courseCode;
                this.guest = guest;
                this.operator = operator;
                this.lifeStartDate =lifeStartDate;
                this.logopic=logopic;
                this.remark =remark;
                this.teachMode=Integer.parseInt(teachMode);
                this.aspID = aspID;
                this.catalogID= catalogID;
        }

        public CourseModel(int courseID, String name, String courseCode,
                           String masterName, String description, int guest,
                           String operator,Date lifeStartDate,String logopic,String remark,int catalogID)
        {
                this.courseID = courseID;
                this.name = name;
                this.masterName = masterName;
                this.description = description;
                this.courseCode = courseCode;
                this.guest = guest;
                this.operator = operator;
                this.lifeStartDate =lifeStartDate;
                this.logopic=logopic;
                this.remark =remark;
                this.catalogID = catalogID;
        }

        public CourseModel(int courseID, String name, String courseCode,
                           String masterName, String description, int guest,
                           String operator,Date lifeStartDate,String logopic,String remark,int status,int aspID,int catalogID)
        {
                this.courseID = courseID;
                this.name = name;
                this.masterName = masterName;
                this.description = description;
                this.courseCode = courseCode;
                this.guest = guest;
                this.operator = operator;
                this.lifeStartDate =lifeStartDate;
                this.logopic=logopic;
                this.remark =remark;
                this.status= status;
                this.aspID = aspID;
                this.catalogID=catalogID;
        }

        public CourseModel(int courseID, String name, String courseCode,
                           String description, Date establishDate, float charge)
        {
                this.courseID = courseID;
                this.name = name;
                this.establishDate = establishDate;
                this.description = description;
                this.courseCode = courseCode;
                this.charge = charge;
        }

        public CourseModel(int courseID, String name, String description)
        {
                this.courseID = courseID;
                this.name = name;
                this.description = description;
        }

        public CourseModel(int courseID, int masterID, String name,
                           String courseCode, String masterName, String description, int orgID,
                           int aspID, String type, String key, int creator, int isCharge,
                           float charge, float memberCharge, String chargeInfo,
                           Date establishDate, Date modifyDate, int allowFreedomReg,
                           int needApprove, int registerMode, int guest, int catalogID,
                           int objectID, int sendMail, int isTimeLimit, Date regStartDate,
                           Date regEndDate, int isPassword, String password, int lifeSort,
                           Date lifeStartDate, Date lifeEndDate, int lifeTime, int status,
                           int maximumEnrollment, int minimumEnrollment, float period,
                           float credit, int scoreType, String scoreLimit, int teachMode,
                           String logo, String operator,String logopic,String isCommend,String remark)
        {
                this.courseID = courseID;
                this.masterID = masterID;
                this.name = name;
                this.courseCode = courseCode;
                this.masterName = masterName;
                this.description = description;
                this.orgID = orgID;
                this.aspID = aspID;
                this.type = type;
                this.key = key;
                this.creator = creator;
                this.isCharge = isCharge;
                this.charge = charge;
                this.memberCharge = memberCharge;
                this.chargeInfo = chargeInfo;
                this.establishDate = establishDate;
                this.modifyDate = modifyDate;
                this.allowFreedomReg = allowFreedomReg;
                this.needApprove = needApprove;
                this.registerMode = registerMode;
                this.guest = guest;
                this.catalogID = catalogID;
                this.objectID = objectID;
                this.isSendMail = sendMail;
                this.isTimeLimit = isTimeLimit;
                this.regStartDate = regStartDate;
                this.regEndDate = regEndDate;
                this.isPassword = isPassword;
                this.password = password;
                this.lifeSort = lifeSort;
                this.lifeStartDate = lifeStartDate;
                this.lifeEndDate = lifeEndDate;
                this.lifeTime = lifeTime;
                this.status = status;
                this.maximumEnrollment = maximumEnrollment;
                this.minimumEnrollment = minimumEnrollment;
                this.period = period;
                this.credit = credit;
                this.scoreType = scoreType;
                this.scoreLimit = scoreLimit;
                this.teachMode = teachMode;
                this.logo = logo;
                this.operator = operator;
                this.logopic=logopic;
                this.isCommend= isCommend;
                this.remark= remark;
        }

        public CourseModel(Course course)
        {
                this.courseID = course.getCourseid();
                this.masterID = course.getMasterid();
                this.name = course.getName();
                this.courseCode = course.getCoursecode();
                this.description = course.getDescription();
                this.orgID = course.getOrgid();
                this.aspID = course.getAspid();
                this.type = course.getType();
                this.key = course.getKey();
                this.creator = course.getCreator();
                this.isCharge = new Float(course.getIscharge()).intValue();
                this.charge = course.getCharge();
                this.memberCharge = course.getMembercharge();
                this.chargeInfo = course.getChargeinfo();
                this.establishDate = course.getEstablishdate();
                this.modifyDate = course.getModifydate();
                this.allowFreedomReg = Integer.parseInt(course.getAllowfreedomreg());
                this.needApprove = StringUtil.parseInt(course.getNeedapprove());
                this.registerMode = StringUtil.parseInt(course.getRegistermode());
                this.guest = StringUtil.parseInt(course.getGuest());
                this.catalogID = course.getCatalogid();
                this.objectID = course.getObjectid();
                this.isSendMail = StringUtil.parseInt(course.getIssendmail());
                this.isTimeLimit = StringUtil.parseInt(course.getIstimelimit());
                this.regStartDate = course.getRegstartdate();
                this.regEndDate = course.getRegenddate();
                this.isPassword = StringUtil.parseInt(course.getIspassword());
                this.password = course.getPassword();
                this.lifeSort = StringUtil.parseInt(course.getLifesort());
                this.lifeStartDate = course.getLifestartdate();
                this.lifeEndDate = course.getLifeenddate();
                this.lifeTime = course.getLifetime();
                this.status = StringUtil.parseInt(course.getStatus());
                this.maximumEnrollment = course.getMaximumenrollment();
                this.minimumEnrollment = course.getMinimumenrollment();
                this.period = course.getPeriod();
                this.credit = course.getCredit();
                this.scoreType = StringUtil.parseInt(course.getScoretype());
                this.scoreLimit = course.getScorelimit();
                this.teachMode = course.getTeachmode();
                this.logo = course.getLogo();
                this.operator = course.getOperator();
                this.logopic = course.getLogopic();
                this.isCommend= course.getIsCommend();
                this.remark= course.getRemark();
        }

        public String getMasterName()
        {
                return masterName;
        }

        public void setMasterName(String masterName)
        {
                this.masterName = masterName;
        }

        public int getIsCharge()
        {
                return isCharge;
        }

        public void setIsCharge(int isCharge)
        {
                this.isCharge = isCharge;
        }

        public int getIsPassword()
        {
                return isPassword;
        }

        public void setIsPassword(int password)
        {
                this.isPassword = password;
        }

        public int getCatalogID()
        {
                return catalogID;
        }

        public void setCatalogID(int catalogID)
        {
                this.catalogID = catalogID;
        }

        public float getCharge()
        {
                return charge;
        }

        public void setCharge(float charge)
        {
                this.charge = charge;
        }

        public String getChargeInfo()
        {
                return chargeInfo;
        }

        public void setChargeInfo(String chargeInfo)
        {
                this.chargeInfo = chargeInfo;
        }

        public String getCourseCode()
        {
                return courseCode;
        }

        public void setCourseCode(String courseCode)
        {
                this.courseCode = courseCode;
        }

        public int getCourseID()
        {
                return courseID;
        }

        public void setCourseID(int courseID)
        {
                this.courseID = courseID;
        }

        public int getCreator()
        {
                return creator;
        }

        public void setCreator(int creator)
        {
                this.creator = creator;
        }

        public float getCredit()
        {
                return credit;
        }

        public void setCredit(float credit)
        {
                this.credit = credit;
        }

        public String getDescription()
        {
                return description;
        }

        public void setDescription(String description)
        {
                this.description = description;
        }

        public Date getEstablishDate()
        {
                return establishDate;
        }

        public void setEstablishDate(Date establishDate)
        {
                this.establishDate = establishDate;
        }

        public int getGuest()
        {
                return guest;
        }

        public void setGuest(int guest)
        {
                this.guest = guest;
        }

        public int getRegisterMode()
        {
                return registerMode;
        }

        public void setRegisterMode(int registerMode)
        {
                this.registerMode = registerMode;
        }

        public int getInCatalog()
        {
                return isInCatalog;
        }

        public void setInCatalog(int inCatalog)
        {
                isInCatalog = inCatalog;
        }

        public int getIsSendMail()
        {
                return isSendMail;
        }

        public void setIsSendMail(int sendMail)
        {
                isSendMail = sendMail;
        }

        public int getIsTimeLimit()
        {
                return isTimeLimit;
        }

        public void setIsTimeLimit(int timeLimit)
        {
                isTimeLimit = timeLimit;
        }

        public String getKey()
        {
                return key;
        }

        public void setKey(String key)
        {
                this.key = key;
        }

        public Date getLifeEndDate()
        {
                return lifeEndDate;
        }

        public void setLifeEndDate(Date lifeEndDate)
        {
                this.lifeEndDate = lifeEndDate;
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

        public String getLogo()
        {
                return logo;
        }

        public void setLogo(String logo)
        {
                this.logo = logo;
        }

        public int getMasterID()
        {
                return masterID;
        }

        public void setMasterID(int masterID)
        {
                this.masterID = masterID;
        }

        public int getMaximumEnrollment()
        {
                return maximumEnrollment;
        }

        public void setMaximumEnrollment(int maximumEnrollment)
        {
                this.maximumEnrollment = maximumEnrollment;
        }

        public float getMemberCharge()
        {
                return memberCharge;
        }

        public void setMemberCharge(float memberCharge)
        {
                this.memberCharge = memberCharge;
        }

        public int getMinimumEnrollment()
        {
                return minimumEnrollment;
        }

        public void setMinimumEnrollment(int minimumEnrollment)
        {
                this.minimumEnrollment = minimumEnrollment;
        }

        public Date getModifyDate()
        {
                return modifyDate;
        }

        public void setModifyDate(Date modifyDate)
        {
                this.modifyDate = modifyDate;
        }

        public String getName()
        {
                return name;
        }

        public void setName(String name)
        {
                this.name = name;
        }

        public int getObjectID()
        {
                return objectID;
        }

        public void setObjectID(int objectID)
        {
                this.objectID = objectID;
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

        public String getPassword()
        {
                return password;
        }

        public void setPassword(String password)
        {
                this.password = password;
        }

        public float getPeriod()
        {
                return period;
        }

        public void setPeriod(float period)
        {
                this.period = period;
        }

        public String getPlan()
        {
                return plan;
        }

        public void setPlan(String plan)
        {
                this.plan = plan;
        }

        public Date getRegEndDate()
        {
                return regEndDate;
        }

        public void setRegEndDate(Date regEndDate)
        {
                this.regEndDate = regEndDate;
        }

        public String getRegister()
        {
                return register;
        }

        public void setRegister(String register)
        {
                this.register = register;
        }

        public Date getRegStartDate()
        {
                return regStartDate;
        }

        public void setRegStartDate(Date regStartDate)
        {
                this.regStartDate = regStartDate;
        }

        public String getScoreLimit()
        {
                return scoreLimit;
        }

        public void setScoreLimit(String scoreLimit)
        {
                this.scoreLimit = scoreLimit;
        }

        public int getScoreType()
        {
                return scoreType;
        }

        public void setScoreType(int scoreType)
        {
                this.scoreType = scoreType;
        }

        public int getStatus()
        {
                return status;
        }

        public void setStatus(int status)
        {
                this.status = status;
        }

        public int getTeachMode()
        {
                return teachMode;
        }

        public void setTeachMode(int teachMode)
        {
                this.teachMode = teachMode;
        }

        public String getType()
        {
                return type;
        }

        public void setType(String type)
        {
                this.type = type;
        }

        public int getCourseUserStatus()
        {
                return courseUserStatus;
        }

        public void setCourseUserStatus(int courseUserStatus)
        {
                this.courseUserStatus = courseUserStatus;
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

        public int getLifeTime()
        {
                return lifeTime;
        }

        public void setLifeTime(int lifeTime)
        {
                this.lifeTime = lifeTime;
        }

        public String getRegStartDateValue()
        {
                return regStartDateValue;
        }

        public void setRegStartDateValue(String regStartDateValue)
        {
                this.regStartDateValue = regStartDateValue;
        }

        public String getRegEndDateValue()
        {
                return regEndDateValue;
        }

        public void setRegEndDateValue(String regEndDateValue)
        {
                this.regEndDateValue = regEndDateValue;
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

        public String getOperator()
        {
                return operator;
        }

        public void setOperator(String operator)
        {
                this.operator = operator;
        }

        public String getLogopic()
        {
                return logopic;
        }

        public void setLogopic(String logopic)
        {
                this.logopic = logopic;
        }

        public String getIsCommend()
        {
                return isCommend;
        }

        public void setIsCommend(String commend)
        {
                isCommend = commend;
        }

        public String getRemark()
        {
                return remark;
        }

        public void setRemark(String remark)
        {
                this.remark = remark;
        }

        public int getPageroffset()
        {
                return pageroffset;
        }

        public void setPageroffset(int pageroffset)
        {
                this.pageroffset = pageroffset;
        }

        public Course getCourse()
        {
                Course course = new Course();
                course.setCourseid(courseID);
                course.setMasterid(masterID);
                course.setCoursecode(courseCode);
                course.setName(name);
                course.setDescription(description);
                course.setOrgid(orgID);
                course.setAspid(aspID);
                course.setType(type);
                course.setKey(key);
                course.setCreator(creator);
                course.setNeedapprove(String.valueOf(needApprove));
                course.setIscharge(String.valueOf(isCharge));
                course.setCharge(new Float(charge).intValue());
                course.setMembercharge(new Float(memberCharge).intValue());
                course.setChargeinfo(chargeInfo);
                setNeedApprove(needApprove);
                course.setEstablishdate(establishDate);
                course.setModifydate(modifyDate);
                course.setAllowfreedomreg(String.valueOf(allowFreedomReg));
                course.setRegistermode(String.valueOf(registerMode));
                course.setGuest(String.valueOf(guest));
                course.setCatalogid(catalogID);
                course.setObjectid(objectID);
                course.setIssendmail(String.valueOf(isSendMail));
                course.setIstimelimit(String.valueOf(isTimeLimit));
                course.setRegstartdate(regStartDate);
                course.setRegenddate(regEndDate);
                course.setIspassword(String.valueOf(isPassword));
                course.setPassword(password);
                course.setLifesort(String.valueOf(lifeSort));
                course.setLifestartdate(lifeStartDate);
                course.setLifeenddate(lifeEndDate);
                course.setLifetime(lifeTime);
                course.setStatus(String.valueOf(status));
                course.setMaximumenrollment(maximumEnrollment);
                course.setMinimumenrollment(minimumEnrollment);
                course.setPeriod(period);
                course.setCredit(credit);
                course.setScoretype(String.valueOf(scoreType));
                course.setScorelimit(scoreLimit);
                course.setTeachmode(teachMode);
                course.setLogo(logo);
                course.setOperator(operator);
                course.setLogopic(logopic);
                course.setIsCommend(isCommend);
                course.setRemark(remark);
                return course;
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
                        errors.add("name", new ActionError("error.course.name.required"));
                }

                if ((courseCode == null) || (courseCode.length() < 1))
                {
                        errors.add("courseCode",
                                new ActionError("error.course.courseCode.required"));
                }

                return (errors);
        }


}
