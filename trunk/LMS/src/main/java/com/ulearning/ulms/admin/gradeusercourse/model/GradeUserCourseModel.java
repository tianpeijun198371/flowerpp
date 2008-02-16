/**
 * Copyright (c) 2000-2005.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.admin.gradeusercourse.model;

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
public class GradeUserCourseModel implements Serializable
{
        private int gradeUCID = 0;
        private int gradetID = 0;
        private int gradecID = 0;
        private int gradeuserID = 0;
        private float coursegrade = 0;

        /**
         * default constructor
         */
        public GradeUserCourseModel()
        {
        }

        /**
         * full constructor
         */
        public GradeUserCourseModel(int gradeUCID, int gradetID, int gradecID,
                                    int gradeuserID, float coursegrade)
        {
                this.gradeUCID = gradeUCID;
                this.gradetID = gradetID;
                this.gradecID = gradecID;
                this.gradeuserID = gradeuserID;
                this.coursegrade = coursegrade;
        }

        public int getGradeUCID()
        {
                return gradeUCID;
        }

        public void setGradeUCID(int gradeUCID)
        {
                this.gradeUCID = gradeUCID;
        }

        public int getGradetID()
        {
                return gradetID;
        }

        public void setGradetID(int gradetID)
        {
                this.gradetID = gradetID;
        }

        public int getGradecID()
        {
                return gradecID;
        }

        public void setGradecID(int gradecID)
        {
                this.gradecID = gradecID;
        }

        public int getGradeuserID()
        {
                return gradeuserID;
        }

        public void setGradeuserID(int gradeuserID)
        {
                this.gradeuserID = gradeuserID;
        }

        public float getCoursegrade()
        {
                return coursegrade;
        }

        public void setCoursegrade(float coursegrade)
        {
                this.coursegrade = coursegrade;
        }

        public String toString()
        {
                return new ToStringBuilder(this).append("gradeUCID", getGradeUCID())
                        .toString();
        }
}
