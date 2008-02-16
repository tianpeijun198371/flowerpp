/**
 * SysConfigForm.java.
 * User: huangsb  Date: 2004-4-27
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd. * All rights reserved.
 */
package com.ulearning.ulms.admin.sysconfig.form;

import com.ulearning.ulms.admin.sysconfig.model.SmtpServerModel;
import com.ulearning.ulms.admin.sysconfig.model.SysConfigModel;
import com.ulearning.ulms.core.util.StringUtil;
import com.ulearning.ulms.tools.upload.model.UploadForm;

import java.util.Calendar;
import java.util.Date;


public class SysConfigForm extends UploadForm
{
        private int orgID = 0;
        private String name = null;
        private String isCheckIP = "1";
        private String isRecordLog = "1";
        private String isCheckSec = "1";
        private int loginStastic = 0;
        private String isCheckTotal = "1";
        private String isFree = "0";
        private String isRemote = "0";
        private String isWithLicence = "1";
        private int PWDLength = 4;
        private String province = null;
        private String address = null;
        private String postalcode = null;
        private String telephone = null;
        private String email = null;
        private String platName = null;
        private String masterEmail = null;
        private String platLogo = null;
        private String platInfo = null;
        private String platCopyright = null;
        private String helpUrl = null;
        private String supportEmail = null;
        private String isLogLogin = "1"; //用户登陆日志
        private String isLogModPassword = "1"; //密码修改日志
        private String serviceItem = null;
        private String isNeedConfirm = "1";
        private String isSelfRegist = "0";
        private String isNeedSMTPAuth = "0";
        private String isNeedLDAP = "0";
        private String host = null;
        private String username = null;
        private String password = null;
        private String isLimitMaxOnLineUsers = "0"; // 0 不限制用户数
        private int maxOnLineUsers = 100; //最多100人
        private String isAllowMultiLogin = "1"; //0 disable 1 enable 同一账号多人使用 ;
        private String isAllowDownloadCourseWare = "0"; // 0 disable 1 enable 是否可以下载课件;
        private long downloadCourseWareSizeLimit = -1;
        private Date downloadCourseWareStartDate = null; //下载时间开始 ;
        private Date downloadCourseWareEndDate = null; // 下载时间结束时间;

        public SysConfigForm()
        {
        }

        public SysConfigForm(int orgID)
        {
                this.orgID = orgID;
        }

        /**
         * below  variable  usr in the   T_SMTPServer_Tab  table
         */

        //private String orgID = null;
        public SysConfigForm(SysConfigModel scm, SmtpServerModel ssm)
        {
                this.orgID = scm.getOrgid();

                this.name = StringUtil.nullToStr(scm.getName());
                this.isCheckIP = StringUtil.nullToStr(scm.getIscheckip());
                this.isRecordLog = StringUtil.nullToStr(scm.getIsrecordlog());
                this.isCheckSec = StringUtil.nullToStr(scm.getIschecksec());
                this.loginStastic = scm.getLoginstastic();
                this.isCheckTotal = StringUtil.nullToStr(scm.getIschecktotal());
                this.isFree = StringUtil.nullToStr(scm.getIsfree());
                this.isRemote = StringUtil.nullToStr(scm.getIsremote());
                this.isWithLicence = StringUtil.nullToStr(scm.getIswithlicence());
                this.PWDLength = scm.getPwdlength();
                this.province = StringUtil.nullToStr(scm.getProvince());
                this.address = StringUtil.nullToStr(scm.getAddress());
                this.postalcode = scm.getPostalcode();
                this.telephone = StringUtil.nullToStr(scm.getTelephone());
                this.email = StringUtil.nullToStr(scm.getEmail());
                this.platName = StringUtil.nullToStr(scm.getPlatname());
                this.masterEmail = StringUtil.nullToStr(scm.getMasteremail());
                this.platLogo = StringUtil.nullToStr(scm.getPlatlogo());
                this.platInfo = StringUtil.nullToStr(scm.getPlatinfo());
                this.platCopyright = StringUtil.nullToStr(scm.getPlatcopyright());
                this.helpUrl = StringUtil.nullToStr(scm.getHelpurl());
                this.supportEmail = StringUtil.nullToStr(scm.getSupportemail());
                this.isLogLogin = StringUtil.nullToStr(scm.getIsloglogin()); //用户登陆日志
                this.isLogModPassword = StringUtil.nullToStr(scm.getIslogmodpassword()); //密码修改日志
                this.serviceItem = StringUtil.nullToStr(scm.getServiceitem());
                this.isNeedConfirm = StringUtil.nullToStr(scm.getIsneedconfirm());
                this.isSelfRegist = StringUtil.nullToStr(scm.getIsselfregist());
                this.isNeedSMTPAuth = StringUtil.nullToStr(scm.getIsneedsmtpauth());
                this.isNeedLDAP = StringUtil.nullToStr(scm.getIsneedldap());

                this.isLimitMaxOnLineUsers = StringUtil.nullToStr(scm.getIsLimitMaxOnLineUsers());
                this.maxOnLineUsers = scm.getMaxOnLineUsers();
                this.isAllowMultiLogin = scm.getIsAllowMultiLogin();
                this.isAllowDownloadCourseWare = scm.getIsAllowDownloadCourseWare();
                this.downloadCourseWareStartDate = scm.getDownloadCourseWareStartDate();
                this.downloadCourseWareEndDate = scm.getDownloadCourseWareEndDate();
                this.downloadCourseWareSizeLimit = scm.getDownloadCourseWareSizeLimit();

                this.host = StringUtil.nullToStr(ssm.getHost());
                this.username = StringUtil.nullToStr(ssm.getUsername());
                this.password = StringUtil.nullToStr(ssm.getPassword());

                /**
                 * below  variable  usr in the   T_SMTPServer_Tab  table
                 */

                //private String orgID = null;
        }

        public long getDownloadCourseWareSizeLimit()
        {
                return downloadCourseWareSizeLimit;
        }

        public void setDownloadCourseWareSizeLimit(long downloadCourseWareSizeLimit)
        {
                this.downloadCourseWareSizeLimit = downloadCourseWareSizeLimit;
        }

        public String getIsLimitMaxOnLineUsers()
        {
                return isLimitMaxOnLineUsers;
        }

        public void setIsLimitMaxOnLineUsers(String isLimitMaxOnLineUsers)
        {
                this.isLimitMaxOnLineUsers = isLimitMaxOnLineUsers;
        }

        public String getIsAllowMultiLogin()
        {
                return isAllowMultiLogin;
        }

        public void setIsAllowMultiLogin(String isAllowMultiLogin)
        {
                this.isAllowMultiLogin = isAllowMultiLogin;
        }

        public String getIsAllowDownloadCourseWare()
        {
                return isAllowDownloadCourseWare;
        }

        public void setIsAllowDownloadCourseWare(String isAllowDownloadCourseWare)
        {
                this.isAllowDownloadCourseWare = isAllowDownloadCourseWare;
        }

        public Date getDownloadCourseWareStartDate()
        {
                return downloadCourseWareStartDate;
        }

        public void setDownloadCourseWareStartDate(Date downloadCourseWareStartDate)
        {
                this.downloadCourseWareStartDate = downloadCourseWareStartDate;
        }

        public Date getDownloadCourseWareEndDate()
        {
                return downloadCourseWareEndDate;
        }

        public void setDownloadCourseWareEndDate(Date downloadCourseWareEndDate)
        {
                this.downloadCourseWareEndDate = downloadCourseWareEndDate;
        }

        public int getMaxOnLineUsers()
        {
                return maxOnLineUsers;
        }

        public void setMaxOnLineUsers(int maxOnLineUsers)
        {
                this.maxOnLineUsers = maxOnLineUsers;
        }

        public String getHost()
        {
                return host;
        }

        public void setHost(String host)
        {
                this.host = host;
        }

        public String getUsername()
        {
                return username;
        }

        public void setUsername(String username)
        {
                this.username = username;
        }

        public String getPassword()
        {
                return password;
        }

        public void setPassword(String password)
        {
                this.password = password;
        }

        public SmtpServerModel toSmtpServerModel()
        {
                SmtpServerModel ssm = new SmtpServerModel();
                ssm.setOrgid(this.orgID);
                ssm.setHost(this.host);
                ssm.setUsername(this.username);
                ssm.setPassword(this.password);

                return ssm;
        }

        public SysConfigModel toConfigModel()
        {
                SysConfigModel model = new SysConfigModel();
                model.setOrgid(this.orgID);

                model.setName(this.name);
                model.setIscheckip(this.isCheckIP);
                model.setIsrecordlog(this.isRecordLog);
                model.setIschecksec(this.isCheckSec);
                model.setLoginstastic(this.loginStastic);
                model.setIschecktotal(this.isCheckTotal);
                model.setIsfree(this.isFree);
                model.setIsremote(this.isRemote);
                model.setIswithlicence(this.isWithLicence);

                model.setPwdlength(this.PWDLength);
                model.setProvince(this.province);
                model.setAddress(this.address);
                model.setPostalcode(this.postalcode);
                model.setTelephone(this.telephone);
                model.setEmail(this.email);
                model.setPlatname(this.platName);
                model.setMasteremail(this.masterEmail);
                model.setPlatlogo(this.platLogo);
                model.setPlatinfo(this.platInfo);
                model.setPlatcopyright(this.platCopyright);
                model.setHelpurl(this.helpUrl);
                model.setSupportemail(this.supportEmail);
                model.setIsloglogin(this.isLogLogin);
                model.setIslogmodpassword(this.isLogModPassword); //密码修改日志
                model.setServiceitem(this.serviceItem);
                model.setIsneedconfirm(this.isNeedConfirm);
                model.setIsselfregist(this.isSelfRegist);
                model.setIsneedsmtpauth(this.isNeedSMTPAuth);
                model.setIsneedldap(this.isNeedLDAP);

                model.setIsLimitMaxOnLineUsers(this.isLimitMaxOnLineUsers);
                model.setMaxOnLineUsers(this.maxOnLineUsers);
                model.setIsAllowMultiLogin(this.isAllowMultiLogin);
                model.setIsAllowDownloadCourseWare(this.isAllowDownloadCourseWare);
                model.setDownloadCourseWareStartDate(this.downloadCourseWareStartDate);
                model.setDownloadCourseWareEndDate(this.downloadCourseWareEndDate);
                model.setDownloadCourseWareSizeLimit(this.downloadCourseWareSizeLimit);

                return model;
        }

        public int getOrgID()
        {
                return orgID;
        }

        public void setOrgID(int orgID)
        {
                this.orgID = orgID;
        }

        public String getIsNeedLDAP()
        {
                return isNeedLDAP;
        }

        public void setIsNeedLDAP(String isNeedLDAP)
        {
                this.isNeedLDAP = isNeedLDAP;
        }

        public String getIsNeedSMTPAuth()
        {
                return isNeedSMTPAuth;
        }

        public void setIsNeedSMTPAuth(String isNeedSMTPAuth)
        {
                this.isNeedSMTPAuth = isNeedSMTPAuth;
        }

        public String getIsNeedConfirm()
        {
                return isNeedConfirm;
        }

        public void setIsNeedConfirm(String isNeedConfirm)
        {
                this.isNeedConfirm = isNeedConfirm;
        }

        public String getServiceItem()
        {
                return serviceItem;
        }

        public void setServiceItem(String serviceItem)
        {
                this.serviceItem = serviceItem;
        }

        public String getIsLogModPassword()
        {
                return isLogModPassword;
        }

        public void setIsLogModPassword(String logModPassword)
        {
                this.isLogModPassword = logModPassword;
        }

        public String getIsLogLogin()
        {
                return isLogLogin;
        }

        public void setIsLogLogin(String logLogin)
        {
                isLogLogin = logLogin;
        }

        public String getSupportEmail()
        {
                return supportEmail;
        }

        public void setSupportEmail(String supportEmail)
        {
                this.supportEmail = supportEmail;
        }

        public String getHelpUrl()
        {
                return helpUrl;
        }

        public void setHelpUrl(String helpUrl)
        {
                this.helpUrl = helpUrl;
        }

        public String getName()
        {
                return name;
        }

        public void setName(String name)
        {
                this.name = name;
        }

        public String getIsCheckIP()
        {
                return isCheckIP;
        }

        public void setIsCheckIP(String isCheckIP)
        {
                this.isCheckIP = isCheckIP;
        }

        public String getIsRecordLog()
        {
                return isRecordLog;
        }

        public void setIsRecordLog(String isRecordLog)
        {
                this.isRecordLog = isRecordLog;
        }

        public String getIsCheckSec()
        {
                return isCheckSec;
        }

        public void setIsCheckSec(String isCheckSec)
        {
                this.isCheckSec = isCheckSec;
        }

        public int getLoginStastic()
        {
                return loginStastic;
        }

        public void setLoginStastic(int loginStastic)
        {
                this.loginStastic = loginStastic;
        }

        public String getIsCheckTotal()
        {
                return isCheckTotal;
        }

        public void setIsCheckTotal(String isCheckTotal)
        {
                this.isCheckTotal = isCheckTotal;
        }

        public String getIsFree()
        {
                return isFree;
        }

        public void setIsFree(String isFree)
        {
                this.isFree = isFree;
        }

        public String getIsRemote()
        {
                return isRemote;
        }

        public void setIsRemote(String isRemote)
        {
                this.isRemote = isRemote;
        }

        public String getIsWithLicence()
        {
                return isWithLicence;
        }

        public void setIsWithLicence(String isWithLicence)
        {
                this.isWithLicence = isWithLicence;
        }

        public String getIsSelfRegist()
        {
                return isSelfRegist;
        }

        public void setIsSelfRegist(String isSelfRegist)
        {
                this.isSelfRegist = isSelfRegist;
        }

        public int getPWDLength()
        {
                return PWDLength;
        }

        public void setPWDLength(int PWDLength)
        {
                this.PWDLength = PWDLength;
        }

        public String getProvince()
        {
                return province;
        }

        public void setProvince(String province)
        {
                this.province = province;
        }

        public String getAddress()
        {
                return address;
        }

        public void setAddress(String address)
        {
                this.address = address;
        }

        public String getPostalcode()
        {
                return postalcode;
        }

        public void setPostalcode(String postalcode)
        {
                this.postalcode = postalcode;
        }

        public String getTelephone()
        {
                return telephone;
        }

        public void setTelephone(String telephone)
        {
                this.telephone = telephone;
        }

        public String getEmail()
        {
                return email;
        }

        public void setEmail(String email)
        {
                this.email = email;
        }

        public String getPlatName()
        {
                return platName;
        }

        public void setPlatName(String platName)
        {
                this.platName = platName;
        }

        public String getMasterEmail()
        {
                return masterEmail;
        }

        public void setMasterEmail(String masterEmail)
        {
                this.masterEmail = masterEmail;
        }

        public String getPlatLogo()
        {
                return platLogo;
        }

        public void setPlatLogo(String platLogo)
        {
                this.platLogo = platLogo;
        }

        public String getPlatInfo()
        {
                return platInfo;
        }

        public void setPlatInfo(String platInfo)
        {
                this.platInfo = platInfo;
        }

        public String getPlatCopyright()
        {
                return platCopyright;
        }

        public void setPlatCopyright(String platCopyright)
        {
                this.platCopyright = platCopyright;
        }
}
