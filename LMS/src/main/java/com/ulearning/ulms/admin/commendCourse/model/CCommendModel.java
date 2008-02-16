package com.ulearning.ulms.admin.commendCourse.model;

import org.apache.commons.lang.builder.ToStringBuilder;

import java.io.Serializable;

import java.util.Date;


/**
 * @author Hibernate CodeGenerator
 */
public class CCommendModel implements Serializable
{
        /**
         * identifier field
         */
        private int ccourseID;

        /**
         * persistent field
         */
        private String name;

        /**
         * persistent field
         */
        private Date beginTime;
        private Date endTime;

        /**
         * nullable persistent field
         */
        private String abstractStr;

        /**
         * persistent field
         */
        private String aimStu;

        /**
         * nullable persistent field
         */
        private String courseAim;

        /**
         * persistent field
         */
        private String period;

        /**
         * nullable persistent field
         */
        private String beriefInfo;

        /**
         * persistent field
         */
        private String courseType;

        /**
         * persistent field
         */
        private String studyType;

        /**
         * persistent field
         */
        private Date publishTime;

        /**
         * nullable persistent field
         */
        private String formerCourse;

        /**
         * nullable persistent field
         */
        private String afterCourse;

        /**
         * nullable persistent field
         */
        private String courseValue;

        /**
         * nullable persistent field
         */
        private String monthType;

        /**
         * nullable persistent field
         */
        private int orgId;

        /**
         * nullable persistent field
         */
        private int displayed;

        /**
         * nullable persistent field
         */
        private String desc0;

        /**
         * nullable persistent field
         */
        private String desc1;
        private String description;

        /**
         * full constructor
         */
        public CCommendModel(int ccourseID, String name, Date beginTime,
                             String abstractStr, String aimStu, String courseAim, String period,
                             String beriefInfo, String courseType, Date publishTime,
                             String courseValue, String monthType, int orgId, int displayed,
                             String desc0, String desc1)
        {
                this.ccourseID = ccourseID;
                this.name = name;
                this.beginTime = beginTime;
                this.abstractStr = abstractStr;
                this.aimStu = aimStu;
                this.courseAim = courseAim;
                this.period = period;
                this.beriefInfo = beriefInfo;
                this.courseType = courseType;
                this.publishTime = publishTime;
                this.courseValue = courseValue;
                this.monthType = monthType;
                this.orgId = orgId;
                this.displayed = displayed;
                this.desc0 = desc0;
                this.desc1 = desc1;
        }

        /**
         * default constructor
         */
        public CCommendModel()
        {
        }

        /**
         * minimal constructor
         */
        public CCommendModel(int ccourseID, String name, Date beginTime,
                             String aimStu, String period, String courseType, Date publishTime)
        {
                this.ccourseID = ccourseID;
                this.name = name;
                this.beginTime = beginTime;
                this.aimStu = aimStu;
                this.period = period;
                this.courseType = courseType;
                this.publishTime = publishTime;
        }

        public String getName()
        {
                return this.name;
        }

        public void setName(String name)
        {
                this.name = name;
        }

        public Date getBeginTime()
        {
                return this.beginTime;
        }

        public void setBeginTime(Date beginTime)
        {
                this.beginTime = beginTime;
        }

        public String getAbstractStr()
        {
                return this.abstractStr;
        }

        public void setAbstractStr(String abstractStr)
        {
                this.abstractStr = abstractStr;
        }

        public String getAimStu()
        {
                return this.aimStu;
        }

        public void setAimStu(String aimStu)
        {
                this.aimStu = aimStu;
        }

        public String getCourseAim()
        {
                return this.courseAim;
        }

        public void setCourseAim(String courseAim)
        {
                this.courseAim = courseAim;
        }

        public String getPeriod()
        {
                return this.period;
        }

        public void setPeriod(String period)
        {
                this.period = period;
        }

        public String getBeriefInfo()
        {
                return this.beriefInfo;
        }

        public void setBeriefInfo(String beriefInfo)
        {
                this.beriefInfo = beriefInfo;
        }

        public String getCourseType()
        {
                return this.courseType;
        }

        public void setCourseType(String courseType)
        {
                this.courseType = courseType;
        }

        public Date getPublishTime()
        {
                return this.publishTime;
        }

        public void setPublishTime(Date publishTime)
        {
                this.publishTime = publishTime;
        }

        public String getCourseValue()
        {
                return this.courseValue;
        }

        public void setCourseValue(String courseValue)
        {
                this.courseValue = courseValue;
        }

        public String getMonthType()
        {
                return this.monthType;
        }

        public void setMonthType(String monthType)
        {
                this.monthType = monthType;
        }

        public int getOrgId()
        {
                return this.orgId;
        }

        public void setOrgId(int orgId)
        {
                this.orgId = orgId;
        }

        public int getDisplayed()
        {
                return displayed;
        }

        public void setDisplayed(int displayed)
        {
                this.displayed = displayed;
        }

        public String getDesc0()
        {
                return this.desc0;
        }

        public void setDesc0(String desc0)
        {
                this.desc0 = desc0;
        }

        public String getDesc1()
        {
                return this.desc1;
        }

        public void setDesc1(String desc1)
        {
                this.desc1 = desc1;
        }

        public int getCcourseID()
        {
                return ccourseID;
        }

        public void setCcourseID(int ccourseID)
        {
                this.ccourseID = ccourseID;
        }

        public String getDescription()
        {
                return description;
        }

        public void setDescription(String description)
        {
                this.description = description;
        }

        public Date getEndTime()
        {
                return endTime;
        }

        public void setEndTime(Date endTime)
        {
                this.endTime = endTime;
        }

        public String getStudyType()
        {
                return studyType;
        }

        public void setStudyType(String studyType)
        {
                this.studyType = studyType;
        }

        public String getFormerCourse()
        {
                return formerCourse;
        }

        public void setFormerCourse(String formerCourse)
        {
                this.formerCourse = formerCourse;
        }

        public String getAfterCourse()
        {
                return afterCourse;
        }

        public void setAfterCourse(String afterCourse)
        {
                this.afterCourse = afterCourse;
        }

        public String toString()
        {
                return new ToStringBuilder(this).append("ccourseID", getCcourseID())
                        .toString();
        }
}
