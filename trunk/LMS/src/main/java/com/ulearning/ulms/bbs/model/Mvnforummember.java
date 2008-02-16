/**
 * BBS≥…‘±Model
 */
package com.ulearning.ulms.bbs.model;
import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class Mvnforummember  implements Serializable{

    /** identifier field */
    private int memberid;

    /** persistent field */
    private String membername;

    /** persistent field */
    private String memberpassword;

    /** persistent field */
    private String memberfirstemail;

    /** persistent field */
    private String memberemail;

    /** persistent field */
    private Short memberemailvisible;

    /** persistent field */
    private Short membernamevisible;

    /** persistent field */
    private String memberfirstip;

    /** persistent field */
    private String memberlastip;

    /** persistent field */
    private Integer memberviewcount;

    /** persistent field */
    private Integer memberpostcount;

    /** persistent field */
    private Date membercreationdate;

    /** persistent field */
    private Date membermodifieddate;

    /** persistent field */
    private Date memberexpiredate;

    /** persistent field */
    private Date memberlastlogon;

    /** persistent field */
    private Integer memberoption;

    /** persistent field */
    private Integer memberstatus;

    /** persistent field */
    private String memberactivatecode;

    /** persistent field */
    private String membertemppassword;

    /** persistent field */
    private Integer membermessagecount;

    /** persistent field */
    private Integer membermessageoption;

    /** persistent field */
    private Short memberpostsperpage;

    /** persistent field */
    private Short memberwarncount;

    /** persistent field */
    private Integer membervotecount;

    /** persistent field */
    private Integer membervotetotalstars;

    /** persistent field */
    private Integer memberrewardpoints;

    /** persistent field */
    private String membertitle;

    /** persistent field */
    private Integer membertimezone;

    /** persistent field */
    private String membersignature;

    /** persistent field */
    private String memberavatar;

    /** persistent field */
    private String memberskin;

    /** persistent field */
    private String memberlanguage;

    /** persistent field */
    private String memberfirstname;

    /** persistent field */
    private String memberlastname;

    /** persistent field */
    private Short membergender;

    /** persistent field */
    private Date memberbirthday;

    /** persistent field */
    private String memberaddress;

    /** persistent field */
    private String membercity;

    /** persistent field */
    private String memberstate;

    /** persistent field */
    private String membercountry;

    /** persistent field */
    private String memberphone;

    /** persistent field */
    private String membermobile;

    /** persistent field */
    private String memberfax;

    /** persistent field */
    private String membercareer;

    /** persistent field */
    private String memberhomepage;

    /** persistent field */
    private String memberyahoo;

    /** persistent field */
    private String memberaol;

    /** persistent field */
    private String membericq;

    /** persistent field */
    private String membermsn;

    /** persistent field */
    private String membercoollink1;

    /** persistent field */
    private String membercoollink2;

    private Integer memberjifen;

    /** full constructor */
    public Mvnforummember(String membername, String memberpassword, String memberfirstemail, String memberemail, Short memberemailvisible, Short membernamevisible, String memberfirstip, String memberlastip, Integer memberviewcount, Integer memberpostcount, Date membercreationdate, Date membermodifieddate, Date memberexpiredate, Date memberlastlogon, Integer memberoption, Integer memberstatus, String memberactivatecode, String membertemppassword, Integer membermessagecount, Integer membermessageoption, Short memberpostsperpage, Short memberwarncount, Integer membervotecount, Integer membervotetotalstars, Integer memberrewardpoints, String membertitle, Integer membertimezone, String membersignature, String memberavatar, String memberskin, String memberlanguage, String memberfirstname, String memberlastname, Short membergender, Date memberbirthday, String memberaddress, String membercity, String memberstate, String membercountry, String memberphone, String membermobile, String memberfax, String membercareer, String memberhomepage, String memberyahoo, String memberaol, String membericq, String membermsn, String membercoollink1, String membercoollink2,Integer memberjifen) {
        this.membername = membername;
        this.memberpassword = memberpassword;
        this.memberfirstemail = memberfirstemail;
        this.memberemail = memberemail;
        this.memberemailvisible = memberemailvisible;
        this.membernamevisible = membernamevisible;
        this.memberfirstip = memberfirstip;
        this.memberlastip = memberlastip;
        this.memberviewcount = memberviewcount;
        this.memberpostcount = memberpostcount;
        this.membercreationdate = membercreationdate;
        this.membermodifieddate = membermodifieddate;
        this.memberexpiredate = memberexpiredate;
        this.memberlastlogon = memberlastlogon;
        this.memberoption = memberoption;
        this.memberstatus = memberstatus;
        this.memberactivatecode = memberactivatecode;
        this.membertemppassword = membertemppassword;
        this.membermessagecount = membermessagecount;
        this.membermessageoption = membermessageoption;
        this.memberpostsperpage = memberpostsperpage;
        this.memberwarncount = memberwarncount;
        this.membervotecount = membervotecount;
        this.membervotetotalstars = membervotetotalstars;
        this.memberrewardpoints = memberrewardpoints;
        this.membertitle = membertitle;
        this.membertimezone = membertimezone;
        this.membersignature = membersignature;
        this.memberavatar = memberavatar;
        this.memberskin = memberskin;
        this.memberlanguage = memberlanguage;
        this.memberfirstname = memberfirstname;
        this.memberlastname = memberlastname;
        this.membergender = membergender;
        this.memberbirthday = memberbirthday;
        this.memberaddress = memberaddress;
        this.membercity = membercity;
        this.memberstate = memberstate;
        this.membercountry = membercountry;
        this.memberphone = memberphone;
        this.membermobile = membermobile;
        this.memberfax = memberfax;
        this.membercareer = membercareer;
        this.memberhomepage = memberhomepage;
        this.memberyahoo = memberyahoo;
        this.memberaol = memberaol;
        this.membericq = membericq;
        this.membermsn = membermsn;
        this.membercoollink1 = membercoollink1;
        this.membercoollink2 = membercoollink2;
        this.memberjifen = memberjifen;
    }

    /** default constructor */
    public Mvnforummember() {
    }

    public int getMemberid() {
        return this.memberid;
    }

    public void setMemberid(int memberid) {
        this.memberid = memberid;
    }

    public String getMembername() {
        return this.membername;
    }

    public void setMembername(String membername) {
        this.membername = membername;
    }

    public String getMemberpassword() {
        return this.memberpassword;
    }

    public void setMemberpassword(String memberpassword) {
        this.memberpassword = memberpassword;
    }

    public String getMemberfirstemail() {
        return this.memberfirstemail;
    }

    public void setMemberfirstemail(String memberfirstemail) {
        this.memberfirstemail = memberfirstemail;
    }

    public String getMemberemail() {
        return this.memberemail;
    }

    public void setMemberemail(String memberemail) {
        this.memberemail = memberemail;
    }

    public Short getMemberemailvisible() {
        return this.memberemailvisible;
    }

    public void setMemberemailvisible(Short memberemailvisible) {
        this.memberemailvisible = memberemailvisible;
    }

    public Short getMembernamevisible() {
        return this.membernamevisible;
    }

    public void setMembernamevisible(Short membernamevisible) {
        this.membernamevisible = membernamevisible;
    }

    public String getMemberfirstip() {
        return this.memberfirstip;
    }

    public void setMemberfirstip(String memberfirstip) {
        this.memberfirstip = memberfirstip;
    }

    public String getMemberlastip() {
        return this.memberlastip;
    }

    public void setMemberlastip(String memberlastip) {
        this.memberlastip = memberlastip;
    }

    public Integer getMemberviewcount() {
        return this.memberviewcount;
    }

    public void setMemberviewcount(Integer memberviewcount) {
        this.memberviewcount = memberviewcount;
    }

    public Integer getMemberpostcount() {
        return this.memberpostcount;
    }

    public void setMemberpostcount(Integer memberpostcount) {
        this.memberpostcount = memberpostcount;
    }

    public Date getMembercreationdate() {
        return this.membercreationdate;
    }

    public void setMembercreationdate(Date membercreationdate) {
        this.membercreationdate = membercreationdate;
    }

    public Date getMembermodifieddate() {
        return this.membermodifieddate;
    }

    public void setMembermodifieddate(Date membermodifieddate) {
        this.membermodifieddate = membermodifieddate;
    }

    public Date getMemberexpiredate() {
        return this.memberexpiredate;
    }

    public void setMemberexpiredate(Date memberexpiredate) {
        this.memberexpiredate = memberexpiredate;
    }

    public Date getMemberlastlogon() {
        return this.memberlastlogon;
    }

    public void setMemberlastlogon(Date memberlastlogon) {
        this.memberlastlogon = memberlastlogon;
    }

    public Integer getMemberoption() {
        return this.memberoption;
    }

    public void setMemberoption(Integer memberoption) {
        this.memberoption = memberoption;
    }

    public Integer getMemberstatus() {
        return this.memberstatus;
    }

    public void setMemberstatus(Integer memberstatus) {
        this.memberstatus = memberstatus;
    }

    public String getMemberactivatecode() {
        return this.memberactivatecode;
    }

    public void setMemberactivatecode(String memberactivatecode) {
        this.memberactivatecode = memberactivatecode;
    }

    public String getMembertemppassword() {
        return this.membertemppassword;
    }

    public void setMembertemppassword(String membertemppassword) {
        this.membertemppassword = membertemppassword;
    }

    public Integer getMembermessagecount() {
        return this.membermessagecount;
    }

    public void setMembermessagecount(Integer membermessagecount) {
        this.membermessagecount = membermessagecount;
    }

    public Integer getMembermessageoption() {
        return this.membermessageoption;
    }

    public void setMembermessageoption(Integer membermessageoption) {
        this.membermessageoption = membermessageoption;
    }

    public Short getMemberpostsperpage() {
        return this.memberpostsperpage;
    }

    public void setMemberpostsperpage(Short memberpostsperpage) {
        this.memberpostsperpage = memberpostsperpage;
    }

    public Short getMemberwarncount() {
        return this.memberwarncount;
    }

    public void setMemberwarncount(Short memberwarncount) {
        this.memberwarncount = memberwarncount;
    }

    public Integer getMembervotecount() {
        return this.membervotecount;
    }

    public void setMembervotecount(Integer membervotecount) {
        this.membervotecount = membervotecount;
    }

    public Integer getMembervotetotalstars() {
        return this.membervotetotalstars;
    }

    public void setMembervotetotalstars(Integer membervotetotalstars) {
        this.membervotetotalstars = membervotetotalstars;
    }

    public Integer getMemberrewardpoints() {
        return this.memberrewardpoints;
    }

    public void setMemberrewardpoints(Integer memberrewardpoints) {
        this.memberrewardpoints = memberrewardpoints;
    }

    public String getMembertitle() {
        return this.membertitle;
    }

    public void setMembertitle(String membertitle) {
        this.membertitle = membertitle;
    }

    public Integer getMembertimezone() {
        return this.membertimezone;
    }

    public void setMembertimezone(Integer membertimezone) {
        this.membertimezone = membertimezone;
    }

    public String getMembersignature() {
        return this.membersignature;
    }

    public void setMembersignature(String membersignature) {
        this.membersignature = membersignature;
    }

    public String getMemberavatar() {
        return this.memberavatar;
    }

    public void setMemberavatar(String memberavatar) {
        this.memberavatar = memberavatar;
    }

    public String getMemberskin() {
        return this.memberskin;
    }

    public void setMemberskin(String memberskin) {
        this.memberskin = memberskin;
    }

    public String getMemberlanguage() {
        return this.memberlanguage;
    }

    public void setMemberlanguage(String memberlanguage) {
        this.memberlanguage = memberlanguage;
    }

    public String getMemberfirstname() {
        return this.memberfirstname;
    }

    public void setMemberfirstname(String memberfirstname) {
        this.memberfirstname = memberfirstname;
    }

    public String getMemberlastname() {
        return this.memberlastname;
    }

    public void setMemberlastname(String memberlastname) {
        this.memberlastname = memberlastname;
    }

    public Short getMembergender() {
        return this.membergender;
    }

    public void setMembergender(Short membergender) {
        this.membergender = membergender;
    }

    public Date getMemberbirthday() {
        return this.memberbirthday;
    }

    public void setMemberbirthday(Date memberbirthday) {
        this.memberbirthday = memberbirthday;
    }

    public String getMemberaddress() {
        return this.memberaddress;
    }

    public void setMemberaddress(String memberaddress) {
        this.memberaddress = memberaddress;
    }

    public String getMembercity() {
        return this.membercity;
    }

    public void setMembercity(String membercity) {
        this.membercity = membercity;
    }

    public String getMemberstate() {
        return this.memberstate;
    }

    public void setMemberstate(String memberstate) {
        this.memberstate = memberstate;
    }

    public String getMembercountry() {
        return this.membercountry;
    }

    public void setMembercountry(String membercountry) {
        this.membercountry = membercountry;
    }

    public String getMemberphone() {
        return this.memberphone;
    }

    public void setMemberphone(String memberphone) {
        this.memberphone = memberphone;
    }

    public String getMembermobile() {
        return this.membermobile;
    }

    public void setMembermobile(String membermobile) {
        this.membermobile = membermobile;
    }

    public String getMemberfax() {
        return this.memberfax;
    }

    public void setMemberfax(String memberfax) {
        this.memberfax = memberfax;
    }

    public String getMembercareer() {
        return this.membercareer;
    }

    public void setMembercareer(String membercareer) {
        this.membercareer = membercareer;
    }

    public String getMemberhomepage() {
        return this.memberhomepage;
    }

    public void setMemberhomepage(String memberhomepage) {
        this.memberhomepage = memberhomepage;
    }

    public String getMemberyahoo() {
        return this.memberyahoo;
    }

    public void setMemberyahoo(String memberyahoo) {
        this.memberyahoo = memberyahoo;
    }

    public String getMemberaol() {
        return this.memberaol;
    }

    public void setMemberaol(String memberaol) {
        this.memberaol = memberaol;
    }

    public String getMembericq() {
        return this.membericq;
    }

    public void setMembericq(String membericq) {
        this.membericq = membericq;
    }

    public String getMembermsn() {
        return this.membermsn;
    }

    public void setMembermsn(String membermsn) {
        this.membermsn = membermsn;
    }

    public String getMembercoollink1() {
        return this.membercoollink1;
    }

    public void setMembercoollink1(String membercoollink1) {
        this.membercoollink1 = membercoollink1;
    }

    public String getMembercoollink2() {
        return this.membercoollink2;
    }

    public void setMembercoollink2(String membercoollink2) {
        this.membercoollink2 = membercoollink2;
    }

    public Integer getMemberjifen() {
        return memberjifen;
    }

    public void setMemberjifen(Integer memberjifen) {
        this.memberjifen = memberjifen;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("memberid", getMemberid())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof Mvnforummember) ) return false;
        Mvnforummember castOther = (Mvnforummember) other;
        return new EqualsBuilder()
            .append(this.getMemberid(), castOther.getMemberid())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getMemberid())
            .toHashCode();
    }

}
