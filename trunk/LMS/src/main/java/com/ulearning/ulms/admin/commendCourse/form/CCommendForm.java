/**
 * CCommendForm.java.
 * User: zengwj Date: 2005-5-18 10:37:10
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.admin.commendCourse.form;

import com.ulearning.ulms.admin.commendCourse.model.CCommendModel;
import com.ulearning.ulms.tools.upload.model.UploadForm;

import java.util.Date;


public class CCommendForm extends UploadForm
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
        private String beginTimeValue;
        private Date endTime;
        private String endTimeValue;

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

        /**
         * nullable persistent field
         */
        private String description;

        public CCommendForm()
        {
        }

        public CCommendForm(CCommendModel model)
        {
                this.ccourseID = model.getCcourseID();
                this.name = model.getName();
                this.beginTime = model.getBeginTime();
                this.abstractStr = model.getAbstractStr();
                this.endTime = model.getEndTime();
                this.aimStu = model.getAimStu();
                this.courseAim = model.getCourseAim();
                this.period = model.getPeriod();
                this.beriefInfo = model.getBeriefInfo();
                this.courseType = model.getCourseType();
                this.studyType = model.getStudyType();
                this.courseValue = model.getCourseValue();
                this.publishTime = model.getPublishTime();
                this.formerCourse = model.getFormerCourse();
                this.afterCourse = model.getAfterCourse();
                this.orgId = model.getOrgId();
                this.displayed = model.getDisplayed();
                this.description = model.getDescription();
                this.desc0 = model.getDesc0();
                this.desc1 = model.getDesc1();
        }

        public CCommendModel getModel()
        {
                CCommendModel model = new CCommendModel();
                model.setCcourseID(this.ccourseID);
                model.setName(this.name);
                model.setBeginTime(this.beginTime);
                model.setAbstractStr(this.abstractStr);
                model.setEndTime(this.endTime);
                model.setAimStu(this.aimStu);
                model.setCourseAim(this.courseAim);
                model.setPeriod(this.period);
                model.setPeriod(this.period);
                model.setCourseType(this.courseType);
                model.setBeriefInfo(this.beriefInfo);
                model.setStudyType(this.studyType);
                model.setCourseValue(this.courseValue);
                model.setPublishTime(this.publishTime);
                model.setFormerCourse(this.formerCourse);
                model.setAfterCourse(this.afterCourse);
                model.setOrgId(this.orgId);
                model.setDisplayed(this.displayed);
                model.setDescription(this.description);
                model.setDesc0(this.desc0);
                model.setDesc1(this.desc1);

                return model;
        }

        public String getName()
        {
                return name;
        }

        public void setName(String name)
        {
                this.name = name;
        }

        public Date getBeginTime()
        {
                return beginTime;
        }

        public void setBeginTime(Date beginTime)
        {
                this.beginTime = beginTime;
        }

        public String getAbstractStr()
        {
                return abstractStr;
        }

        public void setAbstractStr(String abstractStr)
        {
                this.abstractStr = abstractStr;
        }

        public String getAimStu()
        {
                return aimStu;
        }

        public void setAimStu(String aimStu)
        {
                this.aimStu = aimStu;
        }

        public String getCourseAim()
        {
                return courseAim;
        }

        public void setCourseAim(String courseAim)
        {
                this.courseAim = courseAim;
        }

        public String getPeriod()
        {
                return period;
        }

        public void setPeriod(String period)
        {
                this.period = period;
        }

        public String getBeriefInfo()
        {
                return beriefInfo;
        }

        public void setBeriefInfo(String beriefInfo)
        {
                this.beriefInfo = beriefInfo;
        }

        public String getCourseType()
        {
                return courseType;
        }

        public void setCourseType(String courseType)
        {
                this.courseType = courseType;
        }

        public Date getPublishTime()
        {
                return publishTime;
        }

        public void setPublishTime(Date publishTime)
        {
                this.publishTime = publishTime;
        }

        public String getCourseValue()
        {
                return courseValue;
        }

        public void setCourseValue(String courseValue)
        {
                this.courseValue = courseValue;
        }

        public String getMonthType()
        {
                return monthType;
        }

        public void setMonthType(String monthType)
        {
                this.monthType = monthType;
        }

        public int getOrgId()
        {
                return orgId;
        }

        public void setOrgId(int orgId)
        {
                this.orgId = orgId;
        }

        public String getDesc0()
        {
                return desc0;
        }

        public void setDesc0(String desc0)
        {
                this.desc0 = desc0;
        }

        public String getDesc1()
        {
                return desc1;
        }

        public void setDesc1(String desc1)
        {
                this.desc1 = desc1;
        }

        public int getDisplayed()
        {
                return displayed;
        }

        public void setDisplayed(int displayed)
        {
                this.displayed = displayed;
        }

        public String getBeginTimeValue()
        {
                return beginTimeValue;
        }

        public void setBeginTimeValue(String beginTimeValue)
        {
                this.beginTimeValue = beginTimeValue;
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

        public String getEndTimeValue()
        {
                return endTimeValue;
        }

        public void setEndTimeValue(String endTimeValue)
        {
                this.endTimeValue = endTimeValue;
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
}
