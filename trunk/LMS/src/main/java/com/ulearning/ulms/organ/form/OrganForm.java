/**
 * OrganForm
 * User: dengj
 * Date: Apr 14, 2006
 * Time: 5:24:20 PM
 * Copyright (c) 2006-2007.Beijing WenhuaOnline Sci-tech DevelopmentCo.,Ltd.
 */
package com.ulearning.ulms.organ.form;

import com.ulearning.ulms.organ.model.OrganModel;
import com.ulearning.ulms.tools.upload.model.UploadForm;

import java.util.Date;


public class OrganForm extends UploadForm
{
        private int orgID = 0;
        private int parentID = 0; // required
        private int aspID = 1; // required
        private int layer = 0;
        private int orgType = 0; // required
        private String orgName = null; // required
        private int orgStatus = 0;
        private int forGuest = 0;
        private String orgNO = null; // required; unique
        private int isAsp = 0; // required
        private String description = null;
        private String province = null;
        private String city = null;
        private String county = null;
        private String member = null;
        private String dnsName = null;
        private String remark1 = null;
        private String remark2 = null;
        private String remark3 = null;
        private String remark4 = null;
        private String remark5 = null;
        private Date startDate = null;
        private Date endDate = null;
        private String startDateStr = null;
        private String endDateStr = null;
        private String schoolID = null;
        private String logo = null;
        private Date regDate = null;
        private String isCheck = null;


        public OrganForm()
        {
        }

        public OrganForm(OrganModel om)
        {
                if (om != null)
                {
                        this.orgID = om.getOrgid();
                        this.parentID = om.getParentid();
                        this.aspID = om.getAspid();
                        this.layer = om.getLayer();
                        this.orgType = om.getOrgtype();
                        this.orgName = om.getOrgname();
                        this.orgStatus = Integer.parseInt(om.getOrgstatus());
                        this.forGuest = Integer.parseInt(om.getForguest());
                        this.orgNO = om.getOrgno();
                        this.isAsp = Integer.parseInt(om.getIsasp());
                        this.description = om.getDescription();
                        this.province = om.getProvince();
                        this.city = om.getCity();
                        this.county = om.getCounty();
                        this.member = om.getMember();
                        this.dnsName = om.getDnsname();
                        this.remark1 = om.getRemark1();
                        this.remark2 = om.getRemark2();
                        this.remark3 = om.getRemark3();
                        this.remark4 = om.getRemark4();
                        this.remark5 = om.getRemark5();
                        this.startDate = om.getStartDate();
                        this.endDate = om.getEndDate();
                        this.schoolID=om.getSchoolid();
                        this.logo=om.getLogo();
                        this.regDate= om.getRegDate();
                        this.isCheck=om.getIsCheck();

                }
        }

        public OrganModel getOrganModel()
        {
                OrganModel om = new OrganModel();
                om.setOrgid(this.orgID);
                om.setParentid(this.parentID);
                om.setAspid(this.aspID);
                om.setLayer(this.layer);
                om.setOrgtype(this.orgType);
                om.setOrgname(this.orgName);
                om.setOrgstatus(new Integer(this.orgStatus).toString());
                om.setForguest(new Integer(this.forGuest).toString());
                om.setOrgno(this.orgNO);
                om.setIsasp(new Integer(this.isAsp).toString());
                om.setDescription(this.description);
                om.setProvince(this.province);
                om.setCity(this.city);
                om.setCounty(this.county);
                om.setMember(this.member);
                om.setDnsname(this.dnsName);
                om.setRemark1(this.remark1);
                om.setRemark2(this.remark2);
                om.setRemark3(this.remark3);
                om.setRemark4(this.remark4);
                om.setRemark5(this.remark5);
                om.setStartDate(this.startDate);
                om.setEndDate(this.endDate);
                om.setSchoolid(this.schoolID);
                om.setLogo(this.logo);
                om.setRegDate(this.regDate);
                om.setIsCheck(this.isCheck);
                return om;
        }

        public int getOrgID()
        {
                return orgID;
        }

        public void setOrgID(int orgID)
        {
                this.orgID = orgID;
        }

        public int getParentID()
        {
                return parentID;
        }

        public void setParentID(int parentID)
        {
                this.parentID = parentID;
        }

        public int getAspID()
        {
                return aspID;
        }

        public void setAspID(int aspID)
        {
                this.aspID = aspID;
        }

        /**
         * Get the organ layer from the organ tree, "0" is the top one and "1" is the seconde level
         *
         * @return prepared layer number
         */
        public int getLayer()
        {
                return layer;
        }

        public void setLayer(int layer)
        {
                this.layer = layer;
        }

        public int getOrgType()
        {
                return orgType;
        }

        public void setOrgType(int orgType)
        {
                this.orgType = orgType;
        }

        public String getOrgName()
        {
                return orgName;
        }

        public void setOrgName(String orgName)
        {
                this.orgName = orgName;
        }

        public String getProvince()
        {
                return province;
        }

        public void setProvince(String province)
        {
                this.province = province;
        }

        public String getCity()
        {
                return city;
        }

        public void setCity(String city)
        {
                this.city = city;
        }

        public String getCounty()
        {
                return county;
        }

        public void setCounty(String county)
        {
                this.county = county;
        }

        public String getMember()
        {
                return member;
        }

        public void setMember(String member)
        {
                this.member = member;
        }

        public String getDnsName()
        {
                return dnsName;
        }

        public void setDnsName(String dnsName)
        {
                this.dnsName = dnsName;
        }

        public String getRemark1()
        {
                return remark1;
        }

        public void setRemark1(String remark1)
        {
                this.remark1 = remark1;
        }

        public String getRemark2()
        {
                return remark2;
        }

        public void setRemark2(String remark2)
        {
                this.remark2 = remark2;
        }

        public String getRemark3()
        {
                return remark3;
        }

        public void setRemark3(String remark3)
        {
                this.remark3 = remark3;
        }

        public String getRemark4()
        {
                return remark4;
        }

        public void setRemark4(String remark4)
        {
                this.remark4 = remark4;
        }

        public String getRemark5()
        {
                return remark5;
        }

        public void setRemark5(String remark5)
        {
                this.remark5 = remark5;
        }

        public Date getStartDate()
        {
                return startDate;
        }

        public void setStartDate(Date startDate)
        {
                this.startDate = startDate;
        }

        public Date getEndDate()
        {
                return endDate;
        }

        public void setEndDate(Date endDate)
        {
                this.endDate = endDate;
        }

        public String getStartDateStr()
        {
                return startDateStr;
        }

        public void setStartDateStr(String startDateStr)
        {
                this.startDateStr = startDateStr;
        }

        public String getEndDateStr()
        {
                return endDateStr;
        }

        public void setEndDateStr(String endDateStr)
        {
                this.endDateStr = endDateStr;
        }

        public int getOrgStatus()
        {
                return orgStatus;
        }

        public void setOrgStatus(int orgStatus)
        {
                this.orgStatus = orgStatus;
        }

        public int getForGuest()
        {
                return forGuest;
        }

        public void setForGuest(int forGuest)
        {
                this.forGuest = forGuest;
        }

        public String getOrgNO()
        {
                return orgNO;
        }

        public String getLogo()
        {
                return logo;
        }

        public void setLogo(String logo)
        {
                this.logo = logo;
        }

        public void setOrgNO(String orgNO)
        {
                this.orgNO = orgNO;
        }

        public int getIsAsp()
        {
                return isAsp;
        }

        public void setIsAsp(int asp)
        {
                isAsp = asp;
        }

        public String getDescription()
        {
                return description;
        }

        public void setDescription(String description)
        {
                this.description = description;
        }

        public String getSchoolID()
        {
                return schoolID;
        }

        public void setSchoolID(String schoolID)
        {
                this.schoolID = schoolID;
        }

        public Date getRegDate()
        {
                return regDate;
        }

        public void setRegDate(Date regDate)
        {
                this.regDate = regDate;
        }

        public String getIsCheck()
        {
                return isCheck;
        }

        public void setIsCheck(String check)
        {
                isCheck = check;
        }
}
