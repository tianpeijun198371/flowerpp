/**
 * Copyright (c) 2000-2005.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.admin.gradetermcourse.form;

import com.ulearning.ulms.admin.gradetermcourse.model.GradeTermCourseModel;

import org.apache.struts.action.ActionForm;

import java.util.Date;


/**
 * Class description goes here.
 * <p/>
 * Created by Auto Code Produce System
 * User: xiejh
 * Date: 20060321
 * Time: 182730
 */
public class GradeTermCourseForm extends ActionForm
{
        private int gradeCID = 0;
        private int gradeTID = 0;
        private String courseName = "";
        private String description = "";
        private String remark1 = "";
        private String remark2 = "";
        private String remark3 = "";

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

        public GradeTermCourseModel getGradeTermCourseModel()
        {
                GradeTermCourseModel gradeTermCourseModel = new GradeTermCourseModel();
                gradeTermCourseModel.setGradeCID(this.gradeCID);
                gradeTermCourseModel.setGradeTID(this.gradeTID);
                gradeTermCourseModel.setCourseName(this.courseName);
                gradeTermCourseModel.setDescription(this.description);
                gradeTermCourseModel.setRemark1(this.remark1);
                gradeTermCourseModel.setRemark2(this.remark2);
                gradeTermCourseModel.setRemark3(this.remark3);

                return gradeTermCourseModel;
        }

        public GradeTermCourseForm getGradeTermCourseForm(
                GradeTermCourseModel theModel)
        {
                GradeTermCourseForm gradeTermCourseForm = new GradeTermCourseForm();
                gradeTermCourseForm.setGradeCID(theModel.getGradeCID());
                gradeTermCourseForm.setGradeTID(theModel.getGradeTID());
                gradeTermCourseForm.setCourseName(theModel.getCourseName());
                gradeTermCourseForm.setDescription(theModel.getDescription());
                gradeTermCourseForm.setRemark1(theModel.getRemark1());
                gradeTermCourseForm.setRemark2(theModel.getRemark2());
                gradeTermCourseForm.setRemark3(theModel.getRemark3());

                return gradeTermCourseForm;
        }
}
