package com.ulearning.ulms.bbs.model;

import java.io.Serializable;
import java.util.Date;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class Mvnforumthread implements Serializable {

    /** identifier field */
    private int  threadid;

    /** persistent field */
    private Integer forumid;

    /** persistent field */
    private String membername;

    /** persistent field */
    private String lastpostmembername;

    /** persistent field */
    private String threadtopic;

    /** persistent field */
    private String threadbody;

    /** persistent field */
    private Integer threadvotecount;

    /** persistent field */
    private Integer threadvotetotalstars;

    /** persistent field */
    private Date threadcreationdate;

    /** persistent field */
    private Date threadlastpostdate;

    /** persistent field */
    private Integer threadtype;

    /** persistent field */
    private Integer threadoption;

    /** persistent field */
    private Integer threadstatus;

    /** persistent field */
    private Integer threadhaspoll;

    /** persistent field */
    private Integer threadviewcount;

    /** persistent field */
    private Integer threadreplycount;

    /** persistent field */
    private String threadicon;

    /** persistent field */
    private Integer threadduration;

    /** persistent field */
    private Integer threadattachcount;

    /** full constructor */
    public Mvnforumthread(Integer forumid, String membername, String lastpostmembername, String threadtopic, String threadbody, Integer threadvotecount, Integer threadvotetotalstars, Date threadcreationdate, Date threadlastpostdate, Integer threadtype, Integer threadoption, Integer threadstatus, Integer threadhaspoll, Integer threadviewcount, Integer threadreplycount, String threadicon, Integer threadduration, Integer threadattachcount) {
        this.forumid = forumid;
        this.membername = membername;
        this.lastpostmembername = lastpostmembername;
        this.threadtopic = threadtopic;
        this.threadbody = threadbody;
        this.threadvotecount = threadvotecount;
        this.threadvotetotalstars = threadvotetotalstars;
        this.threadcreationdate = threadcreationdate;
        this.threadlastpostdate = threadlastpostdate;
        this.threadtype = threadtype;
        this.threadoption = threadoption;
        this.threadstatus = threadstatus;
        this.threadhaspoll = threadhaspoll;
        this.threadviewcount = threadviewcount;
        this.threadreplycount = threadreplycount;
        this.threadicon = threadicon;
        this.threadduration = threadduration;
        this.threadattachcount = threadattachcount;
    }

    /** default constructor */
    public Mvnforumthread() {
    }

    public int getThreadid() {
        return this.threadid;
    }

    public void setThreadid(int threadid) {
        this.threadid = threadid;
    }

    public Integer getForumid() {
        return this.forumid;
    }

    public void setForumid(Integer forumid) {
        this.forumid = forumid;
    }

    public String getMembername() {
        return this.membername;
    }

    public void setMembername(String membername) {
        this.membername = membername;
    }

    public String getLastpostmembername() {
        return this.lastpostmembername;
    }

    public void setLastpostmembername(String lastpostmembername) {
        this.lastpostmembername = lastpostmembername;
    }

    public String getThreadtopic() {
        return this.threadtopic;
    }

    public void setThreadtopic(String threadtopic) {
        this.threadtopic = threadtopic;
    }

    public String getThreadbody() {
        return this.threadbody;
    }

    public void setThreadbody(String threadbody) {
        this.threadbody = threadbody;
    }

    public Integer getThreadvotecount() {
        return this.threadvotecount;
    }

    public void setThreadvotecount(Integer threadvotecount) {
        this.threadvotecount = threadvotecount;
    }

    public Integer getThreadvotetotalstars() {
        return this.threadvotetotalstars;
    }

    public void setThreadvotetotalstars(Integer threadvotetotalstars) {
        this.threadvotetotalstars = threadvotetotalstars;
    }

    public Date getThreadcreationdate() {
        return this.threadcreationdate;
    }

    public void setThreadcreationdate(Date threadcreationdate) {
        this.threadcreationdate = threadcreationdate;
    }

    public Date getThreadlastpostdate() {
        return this.threadlastpostdate;
    }

    public void setThreadlastpostdate(Date threadlastpostdate) {
        this.threadlastpostdate = threadlastpostdate;
    }

    public Integer getThreadtype() {
        return this.threadtype;
    }

    public void setThreadtype(Integer threadtype) {
        this.threadtype = threadtype;
    }

    public Integer getThreadoption() {
        return this.threadoption;
    }

    public void setThreadoption(Integer threadoption) {
        this.threadoption = threadoption;
    }

    public Integer getThreadstatus() {
        return this.threadstatus;
    }

    public void setThreadstatus(Integer threadstatus) {
        this.threadstatus = threadstatus;
    }

    public Integer getThreadhaspoll() {
        return this.threadhaspoll;
    }

    public void setThreadhaspoll(Integer threadhaspoll) {
        this.threadhaspoll = threadhaspoll;
    }

    public Integer getThreadviewcount() {
        return this.threadviewcount;
    }

    public void setThreadviewcount(Integer threadviewcount) {
        this.threadviewcount = threadviewcount;
    }

    public Integer getThreadreplycount() {
        return this.threadreplycount;
    }

    public void setThreadreplycount(Integer threadreplycount) {
        this.threadreplycount = threadreplycount;
    }

    public String getThreadicon() {
        return this.threadicon;
    }

    public void setThreadicon(String threadicon) {
        this.threadicon = threadicon;
    }

    public Integer getThreadduration() {
        return this.threadduration;
    }

    public void setThreadduration(Integer threadduration) {
        this.threadduration = threadduration;
    }

    public Integer getThreadattachcount() {
        return this.threadattachcount;
    }

    public void setThreadattachcount(Integer threadattachcount) {
        this.threadattachcount = threadattachcount;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("THREADID", getThreadid())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof Mvnforumthread) ) return false;
        Mvnforumthread castOther = (Mvnforumthread) other;
        return new EqualsBuilder()
            .append(this.getThreadid(), castOther.getThreadid())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getThreadid())
            .toHashCode();
    }

}
