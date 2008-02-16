/**
 * Copyright (c) 2000-2005.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.admin.gradeterm.model;

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
public class GradeTermModel implements Serializable
{
        private int gradetID = 0;
        private String grade = "";
        private String term = "";
        private String speciality = "";
        private String remark1 = "";
        private String remark2 = "";
        private String remark3 = "";

        /**
         * default constructor
         */
        public GradeTermModel()
        {
        }

        /**
         * full constructor
         */
        public GradeTermModel(int gradetID, String grade, String term,
                              String speciality, String remark1, String remark2, String remark3)
        {
                this.gradetID = gradetID;
                this.grade = grade;
                this.term = term;
                this.speciality = speciality;
                this.remark1 = remark1;
                this.remark2 = remark2;
                this.remark3 = remark3;
        }

        public int getGradetID()
        {
                return gradetID;
        }

        public void setGradetID(int gradetID)
        {
                this.gradetID = gradetID;
        }

        public String getGrade()
        {
                return grade;
        }

        public void setGrade(String grade)
        {
                this.grade = grade;
        }

        public String getTerm()
        {
                return term;
        }

        public void setTerm(String term)
        {
                this.term = term;
        }

        public String getSpeciality()
        {
                return speciality;
        }

        public void setSpeciality(String speciality)
        {
                this.speciality = speciality;
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
                return new ToStringBuilder(this).append("gradetID", getGradetID())
                        .toString();
        }
}
