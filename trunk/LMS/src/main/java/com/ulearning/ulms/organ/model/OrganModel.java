package com.ulearning.ulms.organ.model;

import org.apache.commons.lang.builder.ToStringBuilder;

import java.io.Serializable;

import java.util.Set;
import java.util.Date;


/**
 * @author Hibernate CodeGenerator
 */
public class OrganModel implements Serializable
{
        /**
         * identifier field
         */
        private int orgid;

        /**
         * persistent field
         */
        private int parentid;

        /**
         * persistent field
         */
        private int aspid;

        /**
         * nullable persistent field
         */
        private int layer;

        /**
         * persistent field
         */
        private int orgtype;

        /**
         * persistent field
         */
        private String orgname;

        /**
         * nullable persistent field
         */
        private String orgstatus;

        /**
         * nullable persistent field
         */
        private String forguest;

        /**
         * nullable persistent field
         */
        private String orgno;

        /**
         * nullable persistent field
         */
        private String isasp;

        /**
         * nullable persistent field
         */
        private String description;

        private String province = null;
        private String city = null;
        private String county = null;
        private String member = null;
        private String dnsname = null;
        private String remark1 = null;
        private String remark2 = null;
        private String remark3 = null;
        private String remark4 = null;
        private String remark5 = null;
        private Date startDate = null;
        private Date endDate = null;
        private String schoolid = null;
        private String logo = null;
        private Date regDate = null;
        private String isCheck = null;

        /**
         * persistent field
         */
        private Set ccolsignTabs;

        /**
         * persistent field
         */
        private Set tmBookTabs;

        /**
         * persistent field
         */
        private Set ccourseTabs;

        /**
         * persistent field
         */
        private Set cerCertificateTabs;

        /**
         * persistent field
         */
        private Set ccatalogTabs;

        /**
         * persistent field
         */
        private Set tmOrguserTabs;

        /**
         * full constructor
         */
        public OrganModel(int parentid, int aspid, int layer, int orgtype,
                          String orgname, String orgstatus, String forguest, String orgno,
                          String isasp, String description, String province,String city,String county,
                          String member,String dnsname,String remark1,String remark2, String remark3, String remark4,String remark5,
                          Date startDate,Date endDate ,String schoolid,String logo,Date regDate,String isCheck,Set ccolsignTabs, Set tmBookTabs,
                          Set ccourseTabs, Set cerCertificateTabs, Set ccatalogTabs,
                          Set tmOrguserTabs)
        {
                this.parentid = parentid;
                this.aspid = aspid;
                this.layer = layer;
                this.orgtype = orgtype;
                this.orgname = orgname;
                this.orgstatus = orgstatus;
                this.forguest = forguest;
                this.orgno = orgno;
                this.isasp = isasp;
                this.description = description;
                this.province =province;
                this.city = city;
                this.county = county;
                this.member =member;
                this.dnsname =dnsname;
                this.remark1=remark1;
                this.remark2=remark2;
                this.remark3=remark3;
                this.remark4=remark4;
                this.remark5=remark5;
                this.startDate=startDate;
                this.endDate = endDate;
                this.schoolid=schoolid;
                this.logo=logo;
                this.regDate=regDate;
                this.isCheck=isCheck;
                this.ccolsignTabs = ccolsignTabs;
                this.tmBookTabs = tmBookTabs;
                this.ccourseTabs = ccourseTabs;
                this.cerCertificateTabs = cerCertificateTabs;
                this.ccatalogTabs = ccatalogTabs;
                this.tmOrguserTabs = tmOrguserTabs;
        }

        /**
         * default constructor
         */
        public OrganModel()
        {
        }

        /**
         * minimal constructor
         */
        public OrganModel(int parentid, int aspid, int orgtype, String orgname,
                          Set ccolsignTabs, Set tmBookTabs, Set ccourseTabs,
                          Set cerCertificateTabs, Set ccatalogTabs, Set tmOrguserTabs)
        {
                this.parentid = parentid;
                this.aspid = aspid;
                this.orgtype = orgtype;
                this.orgname = orgname;
                this.ccolsignTabs = ccolsignTabs;
                this.tmBookTabs = tmBookTabs;
                this.ccourseTabs = ccourseTabs;
                this.cerCertificateTabs = cerCertificateTabs;
                this.ccatalogTabs = ccatalogTabs;
                this.tmOrguserTabs = tmOrguserTabs;
        }

        public int getOrgid()
        {
                return this.orgid;
        }

        public void setOrgid(int orgid)
        {
                this.orgid = orgid;
        }

        public int getParentid()
        {
                return this.parentid;
        }

        public void setParentid(int parentid)
        {
                this.parentid = parentid;
        }

        public int getAspid()
        {
                return this.aspid;
        }

        public void setAspid(int aspid)
        {
                this.aspid = aspid;
        }

        public int getLayer()
        {
                return this.layer;
        }

        public void setLayer(int layer)
        {
                this.layer = layer;
        }

        public int getOrgtype()
        {
                return this.orgtype;
        }

        public void setOrgtype(int orgtype)
        {
                this.orgtype = orgtype;
        }

        public String getOrgname()
        {
                return this.orgname;
        }

        public void setOrgname(String orgname)
        {
                this.orgname = orgname;
        }

        public String getOrgstatus()
        {
                return this.orgstatus;
        }

        public void setOrgstatus(String orgstatus)
        {
                this.orgstatus = orgstatus;
        }

        public String getForguest()
        {
                return this.forguest;
        }

        public void setForguest(String forguest)
        {
                this.forguest = forguest;
        }

        public String getOrgno()
        {
                return this.orgno;
        }

        public void setOrgno(String orgno)
        {
                this.orgno = orgno;
        }

        public String getIsasp()
        {
                return this.isasp;
        }

        public void setIsasp(String isasp)
        {
                this.isasp = isasp;
        }

        public String getDescription()
        {
                return this.description;
        }

        public void setDescription(String description)
        {
                this.description = description;
        }

        public Set getCcolsignTabs()
        {
                return this.ccolsignTabs;
        }

        public void setCcolsignTabs(Set ccolsignTabs)
        {
                this.ccolsignTabs = ccolsignTabs;
        }

        public Set getTmBookTabs()
        {
                return this.tmBookTabs;
        }

        public void setTmBookTabs(Set tmBookTabs)
        {
                this.tmBookTabs = tmBookTabs;
        }

        public Set getCcourseTabs()
        {
                return this.ccourseTabs;
        }

        public void setCcourseTabs(Set ccourseTabs)
        {
                this.ccourseTabs = ccourseTabs;
        }

        public Set getCerCertificateTabs()
        {
                return this.cerCertificateTabs;
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

        public String getDnsname()
        {
                return dnsname;
        }

        public void setDnsname(String dnsname)
        {
                this.dnsname = dnsname;
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

        public String getSchoolid()
        {
                return schoolid;
        }

        public void setSchoolid(String schoolid)
        {
                this.schoolid = schoolid;
        }

        public void setCerCertificateTabs(Set cerCertificateTabs)
        {
                this.cerCertificateTabs = cerCertificateTabs;
        }

        public Set getCcatalogTabs()
        {
                return this.ccatalogTabs;
        }

        public void setCcatalogTabs(Set ccatalogTabs)
        {
                this.ccatalogTabs = ccatalogTabs;
        }

        public Set getTmOrguserTabs()
        {
                return this.tmOrguserTabs;
        }

        public void setTmOrguserTabs(Set tmOrguserTabs)
        {
                this.tmOrguserTabs = tmOrguserTabs;
        }

        public String toString()
        {
                return new ToStringBuilder(this).append("orgid", getOrgid()).toString();
        }

        public String getLogo()
        {
                return logo;
        }

        public void setLogo(String logo)
        {
                this.logo = logo;
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
