/**
 * PlanModel.java.
 * User: dengj  Date: 2004-8-23
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.admin.plan.model;

import org.apache.commons.lang.builder.ToStringBuilder;

import java.io.Serializable;

import java.util.Date;


/**
 * @author Hibernate CodeGenerator
 */
public class PlanModel implements Serializable
{
        /**
         * identifier field
         */
        private int planid;

        /**
         * persistent field
         */
        private String title;

        /**
         * persistent field
         */
        private int orgid;

        /**
         * nullable persistent field
         */
        private int submitorg;

        /**
         * nullable persistent field
         */
        private String iscontent;

        /**
         * nullable persistent field
         */
        private String link;

        /**
         * nullable persistent field
         */
        private String ishyperlink;

        /**
         * persistent field
         */
        private int parentid;

        /**
         * persistent field
         */
        private Date establishtime;

        /**
         * nullable persistent field
         */
        private String description;

        /**
         * full constructor
         */
        private String beginTimehour;
        private String endTimehour;
        private String beginTimemin;
        private String endTimemin;
        private String teacher;
        private String radiobutton;
        private int classdate;
        private String object;
        private String shape;
        private int number;
        private String satisfied;
        private String result;
        private String reason;
        private String cwsatisfied;
        private Date nowTime;

        public PlanModel(String title, int orgid, int submitorg, String iscontent,
                         String link, String ishyperlink, int parentid, Date establishtime,
                         String description, String beginTimehour, String endTimehour,
                         String beginTimemin, String endTimemin, String teacher,
                         String radiobutton, int classdate, String object, String shape,
                         int number, String satisfied, String result, String reason,
                         String cwsatisfied, Date nowTime)
        {
                this.title = title;
                this.orgid = orgid;
                this.submitorg = submitorg;
                this.iscontent = iscontent;
                this.link = link;
                this.ishyperlink = ishyperlink;
                this.parentid = parentid;
                this.establishtime = establishtime;
                this.description = description;
                this.beginTimehour = beginTimehour;
                this.endTimehour = endTimehour;
                this.beginTimemin = beginTimemin;
                this.endTimemin = endTimemin;
                this.teacher = teacher;
                this.radiobutton = radiobutton;
                this.classdate = classdate;
                this.object = object;
                this.shape = shape;
                this.number = number;
                this.satisfied = satisfied;
                this.result = result;
                this.reason = reason;
                this.cwsatisfied = cwsatisfied;
                this.nowTime = nowTime;
        }

        /**
         * default constructor
         */
        public PlanModel()
        {
        }

        /**
         * minimal constructor
         */
        public PlanModel(String title, int orgid, int parentid, Date establishtime)
        {
                this.title = title;
                this.orgid = orgid;
                this.parentid = parentid;
                this.establishtime = establishtime;
        }

        public int getNumber()
        {
                return number;
        }

        public void setNumber(int number)
        {
                this.number = number;
        }

        public int getPlanid()
        {
                return this.planid;
        }

        public void setPlanid(int planid)
        {
                this.planid = planid;
        }

        public String getTitle()
        {
                return this.title;
        }

        public void setTitle(String title)
        {
                this.title = title;
        }

        public int getOrgid()
        {
                return this.orgid;
        }

        public void setOrgid(int orgid)
        {
                this.orgid = orgid;
        }

        public int getSubmitorg()
        {
                return this.submitorg;
        }

        public void setSubmitorg(int submitorg)
        {
                this.submitorg = submitorg;
        }

        public String getIscontent()
        {
                return this.iscontent;
        }

        public void setIscontent(String iscontent)
        {
                this.iscontent = iscontent;
        }

        public String getLink()
        {
                return this.link;
        }

        public void setLink(String link)
        {
                this.link = link;
        }

        public String getIshyperlink()
        {
                return this.ishyperlink;
        }

        public void setIshyperlink(String ishyperlink)
        {
                this.ishyperlink = ishyperlink;
        }

        public int getParentid()
        {
                return this.parentid;
        }

        public void setParentid(int parentid)
        {
                this.parentid = parentid;
        }

        public Date getEstablishtime()
        {
                return this.establishtime;
        }

        public void setEstablishtime(Date establishtime)
        {
                this.establishtime = establishtime;
        }

        public String getDescription()
        {
                return this.description;
        }

        public void setDescription(String description)
        {
                this.description = description;
        }

        public String getBeginTimehour()
        {
                return this.beginTimehour;
        }

        public void setBeginTimehour(String beginTimehour)
        {
                this.beginTimehour = beginTimehour;
        }

        public String getEndTimehour()
        {
                return this.endTimehour;
        }

        public void setEndTimehour(String endTimehour)
        {
                this.endTimehour = endTimehour;
        }

        public String getBeginTimemin()
        {
                return this.beginTimemin;
        }

        public void setBeginTimemin(String beginTimemin)
        {
                this.beginTimemin = beginTimemin;
        }

        public String getEndTimemin()
        {
                return this.endTimemin;
        }

        public void setEndTimemin(String endTimemin)
        {
                this.endTimemin = endTimemin;
        }

        public String getTeacher()
        {
                return this.teacher;
        }

        public void setTeacher(String teacher)
        {
                this.teacher = teacher;
        }

        public String getRadiobutton()
        {
                return this.radiobutton;
        }

        public void setRadiobutton(String radiobutton)
        {
                this.radiobutton = radiobutton;
        }

        public int getClassdate()
        {
                return this.classdate;
        }

        public void setClassdate(int classdate)
        {
                this.classdate = classdate;
        }

        public String getObject()
        {
                return this.object;
        }

        public void setObject(String object)
        {
                this.object = object;
        }

        public String getShape()
        {
                return this.shape;
        }

        public void setShape(String shape)
        {
                this.shape = shape;
        }

        public String getSatisfied()
        {
                return this.satisfied;
        }

        public void setSatisfied(String satisfied)
        {
                this.satisfied = satisfied;
        }

        public String getResult()
        {
                return this.result;
        }

        public void setResult(String result)
        {
                this.result = result;
        }

        public String getReason()
        {
                return this.reason;
        }

        public void setReason(String reason)
        {
                this.reason = reason;
        }

        public String getCwsatisfied()
        {
                return this.cwsatisfied;
        }

        public void setCwsatisfied(String cwsatisfied)
        {
                this.cwsatisfied = cwsatisfied;
        }

        public Date getNowTime()
        {
                return this.nowTime;
        }

        public void setNowTime(Date nowTime)
        {
                this.nowTime = nowTime;
        }

        public String toString()
        {
                return new ToStringBuilder(this).append("planid", getPlanid()).toString();
        }
}
