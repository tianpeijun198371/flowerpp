/**
 * GradeModel.java.
 * User: dengj  Date: 2004-8-26
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.course.test.grade.model;

import org.apache.commons.lang.builder.ToStringBuilder;

import java.io.Serializable;

import java.util.Date;
import java.util.Set;


/**
 * @author Hibernate CodeGenerator
 */
public class GradeModel implements Serializable
{
        /**
         * identifier field
         */
        private int gradeID;

        /**
         * persistent field
         */
        private int courseID;

        /**
         * persistent field
         */
        private String title;

        /**
         * persistent field
         */
        private int type;

        /**
         * nullable persistent field
         */
        private Date createTime;

        /**
         * nullable persistent field
         */
        private Date updateTime;

        /**
         * nullable persistent field
         */
        private String description;

        /**
         * nullable persistent field
         */
        private String desc1;

        /**
         * nullable persistent field
         */
        private String desc2;

        /**
         * nullable persistent field
         */
        private String desc3;

        /**
         * nullable persistent field
         */
        private String desc4;

        /**
         * persistent field
         */
        private Set tgradepaperTabs;

        /**
         * full constructor
         */
        public GradeModel(int gradeid, int courseid, String title, int type,
                          Date createtime, Date updatetime, String description, String desc1,
                          String desc2, String desc3, String desc4, Set tgradepaperTabs)
        {
                this.gradeID = gradeid;
                this.courseID = courseid;
                this.title = title;
                this.type = type;
                this.createTime = createtime;
                this.updateTime = updatetime;
                this.description = description;
                this.desc1 = desc1;
                this.desc2 = desc2;
                this.desc3 = desc3;
                this.desc4 = desc4;
                this.tgradepaperTabs = tgradepaperTabs;
        }

        /**
         * default constructor
         */
        public GradeModel()
        {
        }

        /**
         * minimal constructor
         */
        public GradeModel(int gradeid, int courseid, String title, int type,
                          Set tgradepaperTabs)
        {
                this.gradeID = gradeid;
                this.courseID = courseid;
                this.title = title;
                this.type = type;
                this.tgradepaperTabs = tgradepaperTabs;
        }

        public int getGradeID()
        {
                return this.gradeID;
        }

        public void setGradeID(int gradeID)
        {
                this.gradeID = gradeID;
        }

        public int getCourseID()
        {
                return this.courseID;
        }

        public void setCourseID(int courseID)
        {
                this.courseID = courseID;
        }

        public String getTitle()
        {
                return this.title;
        }

        public void setTitle(String title)
        {
                this.title = title;
        }

        public int getType()
        {
                return this.type;
        }

        public void setType(int type)
        {
                this.type = type;
        }

        public Date getCreateTime()
        {
                return this.createTime;
        }

        public void setCreateTime(Date createTime)
        {
                this.createTime = createTime;
        }

        public Date getUpdateTime()
        {
                return this.updateTime;
        }

        public void setUpdateTime(Date updateTime)
        {
                this.updateTime = updateTime;
        }

        public String getDescription()
        {
                return this.description;
        }

        public void setDescription(String description)
        {
                this.description = description;
        }

        public String getDesc1()
        {
                return this.desc1;
        }

        public void setDesc1(String desc1)
        {
                this.desc1 = desc1;
        }

        public String getDesc2()
        {
                return this.desc2;
        }

        public void setDesc2(String desc2)
        {
                this.desc2 = desc2;
        }

        public String getDesc3()
        {
                return this.desc3;
        }

        public void setDesc3(String desc3)
        {
                this.desc3 = desc3;
        }

        public String getDesc4()
        {
                return this.desc4;
        }

        public void setDesc4(String desc4)
        {
                this.desc4 = desc4;
        }

        public Set getTgradepaperTabs()
        {
                return this.tgradepaperTabs;
        }

        public void setTgradepaperTabs(Set tgradepaperTabs)
        {
                this.tgradepaperTabs = tgradepaperTabs;
        }

        public String toString()
        {
                return new ToStringBuilder(this).append("gradeID", getGradeID())
                        .toString();
        }
}
