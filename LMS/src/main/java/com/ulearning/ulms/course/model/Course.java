package com.ulearning.ulms.course.model;

import org.apache.commons.lang.builder.ToStringBuilder;

import java.io.Serializable;

import java.util.Date;


/**
 * @author Hibernate CodeGenerator
 */
public class Course implements Serializable
{
        /**
         * identifier field
         */
        private int courseid;

        /**
         * persistent field
         */
        private int masterid;

        /**
         * persistent field
         */
        private String coursecode;

        /**
         * persistent field
         */
        private String name;

        /**
         * nullable persistent field
         */
        private String description;

        /**
         * nullable persistent field
         */
        private int orgid;
        private int aspid;

        /**
         * nullable persistent field
         */
        private String type;

        /**
         * persistent field
         */
        private String key;

        /**
         * persistent field
         */
        private int creator;

        /**
         * nullable persistent field
         */
        private String ischarge;

        /**
         * nullable persistent field
         */
        private float charge;

        /**
         * nullable persistent field
         */
        private float membercharge;

        /**
         * nullable persistent field
         */
        private String chargeinfo;

        /**
         * nullable persistent field
         */
        private Date establishdate;

        /**
         * nullable persistent field
         */
        private Date modifydate;

        /**
         * nullable persistent field
         */
        private String allowfreedomreg;

        /**
         * nullable persistent field
         */
        private String needapprove;

        /**
         * nullable persistent field
         */
        private String registermode;

        /**
         * nullable persistent field
         */
        private String guest;

        /**
         * nullable persistent field
         */
        private int catalogid;

        /**
         * nullable persistent field
         */
        private int objectid;

        /**
         * nullable persistent field
         */
        private String issendmail;

        /**
         * nullable persistent field
         */
        private String istimelimit;

        /**
         * nullable persistent field
         */
        private Date regstartdate;

        /**
         * nullable persistent field
         */
        private Date regenddate;

        /**
         * nullable persistent field
         */
        private String ispassword;

        /**
         * nullable persistent field
         */
        private String password;

        /**
         * nullable persistent field
         */
        private String lifesort;

        /**
         * nullable persistent field
         */
        private Date lifestartdate;

        /**
         * nullable persistent field
         */
        private Date lifeenddate;

        /**
         * nullable persistent field
         */
        private int lifetime;

        /**
         * nullable persistent field
         */
        private String status;

        /**
         * nullable persistent field
         */
        private int maximumenrollment;

        /**
         * nullable persistent field
         */
        private int minimumenrollment;

        /**
         * nullable persistent field
         */
        private float period;

        /**
         * nullable persistent field
         */
        private float credit;

        /**
         * nullable persistent field
         */
        private String scoretype;

        /**
         * nullable persistent field
         */
        private String scorelimit;

        /**
         * nullable persistent field
         */
        private int teachmode;

        /**
         * nullable persistent field
         */
        private String logo;
        private String operator; //操作员

        private String logopic; //操作员
        private String isCommend; //是否推荐
        private String remark; //授课时间说明        

        public Course()
        {
        }

        public Course(int courseid, int masterid, String coursecode, String name,
                      String description, int orgid, int aspid, String type, String key,
                      int creator, String ischarge, float charge, float membercharge,
                      String chargeinfo, Date establishdate, Date modifydate,
                      String allowfreedomreg, String needapprove, String registermode,
                      String guest, int catalogid, int objectid, String issendmail,
                      String istimelimit, Date regstartdate, Date regenddate,
                      String ispassword, String password, String lifesort,
                      Date lifestartdate, Date lifeenddate, int lifetime, String status,
                      int maximumenrollment, int minimumenrollment, float period,
                      float credit, String scoretype, String scorelimit, int teachmode,
                      String logo, String operator,String logopic,String isCommend,String remark)
        {
                this.courseid = courseid;
                this.masterid = masterid;
                this.coursecode = coursecode;
                this.name = name;
                this.description = description;
                this.orgid = orgid;
                this.aspid = aspid;
                this.type = type;
                this.key = key;
                this.creator = creator;
                this.ischarge = ischarge;
                this.charge = charge;
                this.membercharge = membercharge;
                this.chargeinfo = chargeinfo;
                this.establishdate = establishdate;
                this.modifydate = modifydate;
                this.allowfreedomreg = allowfreedomreg;
                this.needapprove = needapprove;
                this.registermode = registermode;
                this.guest = guest;
                this.catalogid = catalogid;
                this.objectid = objectid;
                this.issendmail = issendmail;
                this.istimelimit = istimelimit;
                this.regstartdate = regstartdate;
                this.regenddate = regenddate;
                this.ispassword = ispassword;
                this.password = password;
                this.lifesort = lifesort;
                this.lifestartdate = lifestartdate;
                this.lifeenddate = lifeenddate;
                this.lifetime = lifetime;
                this.status = status;
                this.maximumenrollment = maximumenrollment;
                this.minimumenrollment = minimumenrollment;
                this.period = period;
                this.credit = credit;
                this.scoretype = scoretype;
                this.scorelimit = scorelimit;
                this.teachmode = teachmode;
                this.logo = logo;
                this.operator = operator;
                this.logopic=logopic;
                this.isCommend= isCommend;
                this.remark= remark;
        }

        public int getCourseid()
        {
                return courseid;
        }

        public void setCourseid(int courseid)
        {
                this.courseid = courseid;
        }

        public int getMasterid()
        {
                return masterid;
        }

        public void setMasterid(int masterid)
        {
                this.masterid = masterid;
        }

        public String getCoursecode()
        {
                return coursecode;
        }

        public void setCoursecode(String coursecode)
        {
                this.coursecode = coursecode;
        }

        public String getName()
        {
                return name;
        }

        public void setName(String name)
        {
                this.name = name;
        }

        public String getDescription()
        {
                return description;
        }

        public void setDescription(String description)
        {
                this.description = description;
        }

        public int getOrgid()
        {
                return orgid;
        }

        public void setOrgid(int orgid)
        {
                this.orgid = orgid;
        }

        public int getAspid()
        {
                return aspid;
        }

        public void setAspid(int aspid)
        {
                this.aspid = aspid;
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

        public int getCreator()
        {
                return creator;
        }

        public void setCreator(int creator)
        {
                this.creator = creator;
        }

        public String getIscharge()
        {
                return ischarge;
        }

        public void setIscharge(String ischarge)
        {
                this.ischarge = ischarge;
        }

        public float getCharge()
        {
                return charge;
        }

        public void setCharge(float charge)
        {
                this.charge = charge;
        }

        public float getMembercharge()
        {
                return membercharge;
        }

        public void setMembercharge(float membercharge)
        {
                this.membercharge = membercharge;
        }

        public String getChargeinfo()
        {
                return chargeinfo;
        }

        public void setChargeinfo(String chargeinfo)
        {
                this.chargeinfo = chargeinfo;
        }

        public Date getEstablishdate()
        {
                return establishdate;
        }

        public void setEstablishdate(Date establishdate)
        {
                this.establishdate = establishdate;
        }

        public Date getModifydate()
        {
                return modifydate;
        }

        public void setModifydate(Date modifydate)
        {
                this.modifydate = modifydate;
        }

        public String getAllowfreedomreg()
        {
                return allowfreedomreg;
        }

        public void setAllowfreedomreg(String allowfreedomreg)
        {
                this.allowfreedomreg = allowfreedomreg;
        }

        public String getNeedapprove()
        {
                return needapprove;
        }

        public void setNeedapprove(String needapprove)
        {
                this.needapprove = needapprove;
        }

        public String getRegistermode()
        {
                return registermode;
        }

        public void setRegistermode(String registermode)
        {
                this.registermode = registermode;
        }

        public String getGuest()
        {
                return guest;
        }

        public void setGuest(String guest)
        {
                this.guest = guest;
        }

        public int getCatalogid()
        {
                return catalogid;
        }

        public void setCatalogid(int catalogid)
        {
                this.catalogid = catalogid;
        }

        public int getObjectid()
        {
                return objectid;
        }

        public void setObjectid(int objectid)
        {
                this.objectid = objectid;
        }

        public String getIssendmail()
        {
                return issendmail;
        }

        public void setIssendmail(String issendmail)
        {
                this.issendmail = issendmail;
        }

        public String getIstimelimit()
        {
                return istimelimit;
        }

        public void setIstimelimit(String istimelimit)
        {
                this.istimelimit = istimelimit;
        }

        public Date getRegstartdate()
        {
                return regstartdate;
        }

        public void setRegstartdate(Date regstartdate)
        {
                this.regstartdate = regstartdate;
        }

        public Date getRegenddate()
        {
                return regenddate;
        }

        public void setRegenddate(Date regenddate)
        {
                this.regenddate = regenddate;
        }

        public String getIspassword()
        {
                return ispassword;
        }

        public void setIspassword(String ispassword)
        {
                this.ispassword = ispassword;
        }

        public String getPassword()
        {
                return password;
        }

        public void setPassword(String password)
        {
                this.password = password;
        }

        public String getLifesort()
        {
                return lifesort;
        }

        public void setLifesort(String lifesort)
        {
                this.lifesort = lifesort;
        }

        public Date getLifestartdate()
        {
                return lifestartdate;
        }

        public void setLifestartdate(Date lifestartdate)
        {
                this.lifestartdate = lifestartdate;
        }

        public Date getLifeenddate()
        {
                return lifeenddate;
        }

        public void setLifeenddate(Date lifeenddate)
        {
                this.lifeenddate = lifeenddate;
        }

        public int getLifetime()
        {
                return lifetime;
        }

        public void setLifetime(int lifetime)
        {
                this.lifetime = lifetime;
        }

        public String getStatus()
        {
                return status;
        }

        public void setStatus(String status)
        {
                this.status = status;
        }

        public int getMaximumenrollment()
        {
                return maximumenrollment;
        }

        public void setMaximumenrollment(int maximumenrollment)
        {
                this.maximumenrollment = maximumenrollment;
        }

        public int getMinimumenrollment()
        {
                return minimumenrollment;
        }

        public void setMinimumenrollment(int minimumenrollment)
        {
                this.minimumenrollment = minimumenrollment;
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

        public String getScoretype()
        {
                return scoretype;
        }

        public void setScoretype(String scoretype)
        {
                this.scoretype = scoretype;
        }

        public String getScorelimit()
        {
                return scorelimit;
        }

        public void setScorelimit(String scorelimit)
        {
                this.scorelimit = scorelimit;
        }

        public int getTeachmode()
        {
                return teachmode;
        }

        public void setTeachmode(int teachmode)
        {
                this.teachmode = teachmode;
        }

        public String getLogo()
        {
                return logo;
        }

        public void setLogo(String logo)
        {
                this.logo = logo;
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

        public String getRemark()
        {
                return remark;
        }

        public void setRemark(String remark)
        {
                this.remark = remark;
        }

        public String getIsCommend()
        {
                return isCommend;
        }

        public void setIsCommend(String commend)
        {
                isCommend = commend;
        }

        /**
         * full constructor
         */
        public String toString()
        {
                return new ToStringBuilder(this).append("courseid", getCourseid())
                        .toString();
        }
}
