package com.ulearning.ulms.admin.sysconfig.model;

import com.ulearning.ulms.admin.sysconfig.form.SysConfigForm;

import org.apache.commons.lang.builder.ToStringBuilder;

import java.io.Serializable;

import java.util.Date;


/**
 * @author Hibernate CodeGenerator
 */
public class SysConfigModel implements Serializable
{
        /**
         * identifier field
         */
        private int orgid;

        /**
         * nullable persistent field
         */
        private String name;

        /**
         * nullable persistent field
         */
        private String ischeckip;

        /**
         * nullable persistent field
         */
        private String isrecordlog;

        /**
         * nullable persistent field
         */
        private String ischecksec;

        /**
         * nullable persistent field
         */
        private int loginstastic;

        /**
         * nullable persistent field
         */
        private String ischecktotal;

        /**
         * nullable persistent field
         */
        private String isfree;

        /**
         * nullable persistent field
         */
        private String isremote;

        /**
         * nullable persistent field
         */
        private String iswithlicence;

        /**
         * nullable persistent field
         */
        private String seviceno;

        /**
         * nullable persistent field
         */
        private int pwdlength;

        /**
         * nullable persistent field
         */
        private String province;

        /**
         * nullable persistent field
         */
        private String address;

        /**
         * nullable persistent field
         */
        private String postalcode;

        /**
         * nullable persistent field
         */
        private String telephone;

        /**
         * nullable persistent field
         */
        private String email;

        /**
         * nullable persistent field
         */
        private String platname;

        /**
         * nullable persistent field
         */
        private String masteremail;

        /**
         * nullable persistent field
         */
        private String platlogo;

        /**
         * nullable persistent field
         */
        private String platinfo;

        /**
         * nullable persistent field
         */
        private String platcopyright;

        /**
         * nullable persistent field
         */
        private String helpurl;

        /**
         * nullable persistent field
         */
        private String supportemail;

        /**
         * nullable persistent field
         */
        private String isloglogin;

        /**
         * nullable persistent field
         */
        private String islogmodpassword;

        /**
         * nullable persistent field
         */
        private String serviceitem;

        /**
         * nullable persistent field
         */
        private String isneedconfirm;

        /**
         * nullable persistent field
         */
        private String isneedsmtpauth;

        /**
         * nullable persistent field
         */
        private String isneedldap;

        /**
         * nullable persistent field
         */
        private String isselfregist;
        private String isLimitMaxOnLineUsers = "0"; // 0 不限制用户数
        private int maxOnLineUsers = 100; //最多100人
        private String isAllowMultiLogin = "1"; //0 disable 1 enable 同一账号多人使用 ;
        private String isAllowDownloadCourseWare = "0"; // 0 disable 1 enable 是否可以下载课件;
        private Date downloadCourseWareStartDate = null; //下载时间开始 ;
        private Date downloadCourseWareEndDate = null; // 下载时间结束时间;
        private long downloadCourseWareSizeLimit = -1;

        /**
         * full constructor
         */
        public SysConfigModel(int orgid, String name, String ischeckip,
                              String isrecordlog, String ischecksec, int loginstastic,
                              String ischecktotal, String isfree, String isremote,
                              String iswithlicence, String seviceno, int pwdlength, String province,
                              String address, String postalcode, String telephone, String email,
                              String platname, String masteremail, String platlogo, String platinfo,
                              String platcopyright, String helpurl, String supportemail,
                              String isloglogin, String islogmodpassword, String serviceitem,
                              String isneedconfirm, String isneedsmtpauth, String isneedldap,
                              String isselfregist, String isLimitMaxOnLineUsers, int maxOnLineUsers,
                              String isAllowMultiLogin, String isAllowDownloadCourseWare,
                              Date downloadCourseWareStartDate, Date downloadCourseWareEndDate,
                              long downloadCourseWareSizeLimit)
        {
                this.orgid = orgid;
                this.name = name;
                this.ischeckip = ischeckip;
                this.isrecordlog = isrecordlog;
                this.ischecksec = ischecksec;
                this.loginstastic = loginstastic;
                this.ischecktotal = ischecktotal;
                this.isfree = isfree;
                this.isremote = isremote;
                this.iswithlicence = iswithlicence;
                this.seviceno = seviceno;
                this.pwdlength = pwdlength;
                this.province = province;
                this.address = address;
                this.postalcode = postalcode;
                this.telephone = telephone;
                this.email = email;
                this.platname = platname;
                this.masteremail = masteremail;
                this.platlogo = platlogo;
                this.platinfo = platinfo;
                this.platcopyright = platcopyright;
                this.helpurl = helpurl;
                this.supportemail = supportemail;
                this.isloglogin = isloglogin;
                this.islogmodpassword = islogmodpassword;
                this.serviceitem = serviceitem;
                this.isneedconfirm = isneedconfirm;
                this.isneedsmtpauth = isneedsmtpauth;
                this.isneedldap = isneedldap;
                this.isselfregist = isselfregist;

                this.isLimitMaxOnLineUsers = isLimitMaxOnLineUsers; // 0 不限制用户数
                this.maxOnLineUsers = maxOnLineUsers; //最多100人
                this.isAllowMultiLogin = isAllowMultiLogin; //0 disable 1 enable 同一账号多人使用 ;
                this.isAllowDownloadCourseWare = isAllowDownloadCourseWare; // 0 disable 1 enable 是否可以下载课件;
                this.downloadCourseWareStartDate = downloadCourseWareStartDate; //下载时间开始 ;
                this.downloadCourseWareEndDate = downloadCourseWareEndDate; //下载结束时间;
                this.downloadCourseWareSizeLimit = downloadCourseWareSizeLimit; //最大下载大小
        }

        /**
         * default constructor
         */
        public SysConfigModel()
        {
        }

        /**
         * minimal constructor
         */
        public SysConfigModel(int orgid)
        {
                this.orgid = orgid;
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

        public long getDownloadCourseWareSizeLimit()
        {
                return downloadCourseWareSizeLimit;
        }

        public void setDownloadCourseWareSizeLimit(long downloadCourseWareSizeLimit)
        {
                this.downloadCourseWareSizeLimit = downloadCourseWareSizeLimit;
        }

        public int getOrgid()
        {
                return this.orgid;
        }

        public void setOrgid(int orgid)
        {
                this.orgid = orgid;
        }

        public String getName()
        {
                return this.name;
        }

        public void setName(String name)
        {
                this.name = name;
        }

        public String getIscheckip()
        {
                return this.ischeckip;
        }

        public void setIscheckip(String ischeckip)
        {
                this.ischeckip = ischeckip;
        }

        public String getIsrecordlog()
        {
                return this.isrecordlog;
        }

        public void setIsrecordlog(String isrecordlog)
        {
                this.isrecordlog = isrecordlog;
        }

        public String getIschecksec()
        {
                return this.ischecksec;
        }

        public void setIschecksec(String ischecksec)
        {
                this.ischecksec = ischecksec;
        }

        public int getLoginstastic()
        {
                return this.loginstastic;
        }

        public void setLoginstastic(int loginstastic)
        {
                this.loginstastic = loginstastic;
        }

        public String getIschecktotal()
        {
                return this.ischecktotal;
        }

        public void setIschecktotal(String ischecktotal)
        {
                this.ischecktotal = ischecktotal;
        }

        public String getIsfree()
        {
                return this.isfree;
        }

        public void setIsfree(String isfree)
        {
                this.isfree = isfree;
        }

        public String getIsremote()
        {
                return this.isremote;
        }

        public void setIsremote(String isremote)
        {
                this.isremote = isremote;
        }

        public String getIswithlicence()
        {
                return this.iswithlicence;
        }

        public void setIswithlicence(String iswithlicence)
        {
                this.iswithlicence = iswithlicence;
        }

        public String getSeviceno()
        {
                return this.seviceno;
        }

        public void setSeviceno(String seviceno)
        {
                this.seviceno = seviceno;
        }

        public int getPwdlength()
        {
                return this.pwdlength;
        }

        public void setPwdlength(int pwdlength)
        {
                this.pwdlength = pwdlength;
        }

        public String getProvince()
        {
                return this.province;
        }

        public void setProvince(String province)
        {
                this.province = province;
        }

        public String getAddress()
        {
                return this.address;
        }

        public void setAddress(String address)
        {
                this.address = address;
        }

        public String getPostalcode()
        {
                return this.postalcode;
        }

        public void setPostalcode(String postalcode)
        {
                this.postalcode = postalcode;
        }

        public String getTelephone()
        {
                return this.telephone;
        }

        public void setTelephone(String telephone)
        {
                this.telephone = telephone;
        }

        public String getEmail()
        {
                return this.email;
        }

        public void setEmail(String email)
        {
                this.email = email;
        }

        public String getPlatname()
        {
                return this.platname;
        }

        public void setPlatname(String platname)
        {
                this.platname = platname;
        }

        public String getMasteremail()
        {
                return this.masteremail;
        }

        public void setMasteremail(String masteremail)
        {
                this.masteremail = masteremail;
        }

        public String getPlatlogo()
        {
                return this.platlogo;
        }

        public void setPlatlogo(String platlogo)
        {
                this.platlogo = platlogo;
        }

        public String getPlatinfo()
        {
                return this.platinfo;
        }

        public void setPlatinfo(String platinfo)
        {
                this.platinfo = platinfo;
        }

        public String getPlatcopyright()
        {
                return this.platcopyright;
        }

        public void setPlatcopyright(String platcopyright)
        {
                this.platcopyright = platcopyright;
        }

        public String getHelpurl()
        {
                return this.helpurl;
        }

        public void setHelpurl(String helpurl)
        {
                this.helpurl = helpurl;
        }

        public String getSupportemail()
        {
                return this.supportemail;
        }

        public void setSupportemail(String supportemail)
        {
                this.supportemail = supportemail;
        }

        public String getIsloglogin()
        {
                return this.isloglogin;
        }

        public void setIsloglogin(String isloglogin)
        {
                this.isloglogin = isloglogin;
        }

        public String getIslogmodpassword()
        {
                return this.islogmodpassword;
        }

        public void setIslogmodpassword(String islogmodpassword)
        {
                this.islogmodpassword = islogmodpassword;
        }

        public String getServiceitem()
        {
                return this.serviceitem;
        }

        public void setServiceitem(String serviceitem)
        {
                this.serviceitem = serviceitem;
        }

        public String getIsneedconfirm()
        {
                return this.isneedconfirm;
        }

        public void setIsneedconfirm(String isneedconfirm)
        {
                this.isneedconfirm = isneedconfirm;
        }

        public String getIsneedsmtpauth()
        {
                return this.isneedsmtpauth;
        }

        public void setIsneedsmtpauth(String isneedsmtpauth)
        {
                this.isneedsmtpauth = isneedsmtpauth;
        }

        public String getIsneedldap()
        {
                return this.isneedldap;
        }

        public void setIsneedldap(String isneedldap)
        {
                this.isneedldap = isneedldap;
        }

        public String getIsselfregist()
        {
                return this.isselfregist;
        }

        public void setIsselfregist(String isselfregist)
        {
                this.isselfregist = isselfregist;
        }

        public String toString()
        {
                return new ToStringBuilder(this).append("orgid", getOrgid()).toString();
        }
}
