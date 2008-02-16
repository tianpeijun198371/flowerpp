/**
 * Copyright (c) 2000-2005.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.admin.gradetermcourse.model;

import org.apache.commons.lang.builder.ToStringBuilder;

import java.io.Serializable;

import java.util.Date;


/**
 * Class description goes here.
 * <p/>
 * Created by Auto Code Produce System
 * User: xiejh
 * Date: 20060321
 * Time: 182730
 */
public class GradeTermCourseModel implements Serializable
{
        private int gradeCID = 0;
        private int gradeTID = 0;
        private String courseName = "";
        private String description = "";
        private String remark1 = "";
        private String remark2 = "";
        private String remark3 = "";

        /**
         * default constructor
         */
        public GradeTermCourseModel()
        {
        }

        /**
         * full constructor
         */
        public GradeTermCourseModel(int gradeCID, int gradeTID, String courseName,
                                    String description, String remark1, String remark2, String remark3)
        {
                this.gradeCID = gradeCID;
                this.gradeTID = gradeTID;
                this.courseName = courseName;
                this.description = description;
                this.remark1 = remark1;
                this.remark2 = remark2;
                this.remark3 = remark3;
        }

        public int getGradeCID()
        {
                return gradeCID;
        }

        public void setGradeCID(int gradeCID)
        {
                this.gradeCID = gradeCID;
        }

        public int getGradeTID()
        {
                return gradeTID;
        }

        public void setGradeTID(int gradeTID)
        {
                this.gradeTID = gradeTID;
        }

        public String getCourseName()
        {
                return courseName;
        }

        public void setCourseName(String courseName)
        {
                this.courseName = courseName;
        }

        public String getDescription()
        {
                return description;
        }

        public void setDescription(String description)
        {
                this.description = description;
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

        public String toString()
        {
                return new ToStringBuilder(this).append("gradeCID", getGradeCID())
                        .toString();
        }
}
