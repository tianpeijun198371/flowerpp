/**
 * Copyright (c) 2000-2005.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.admin.gradeusercourse.form;

import com.ulearning.ulms.admin.gradeusercourse.model.GradeUserCourseModel;

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
public class GradeUserCourseForm extends ActionForm
{
        private int gradeUCID = 0;
        private int gradetID = 0;
        private int gradecID = 0;
        private int gradeuserID = 0;
        private float coursegrade = 0;

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

        public GradeUserCourseModel getGradeUserCourseModel()
        {
                GradeUserCourseModel gradeUserCourseModel = new GradeUserCourseModel();
                gradeUserCourseModel.setGradeUCID(this.gradeUCID);
                gradeUserCourseModel.setGradetID(this.gradetID);
                gradeUserCourseModel.setGradecID(this.gradecID);
                gradeUserCourseModel.setGradeuserID(this.gradeuserID);
                gradeUserCourseModel.setCoursegrade(this.coursegrade);

                return gradeUserCourseModel;
        }

        public GradeUserCourseForm getGradeUserCourseForm(
                GradeUserCourseModel theModel)
        {
                GradeUserCourseForm gradeUserCourseForm = new GradeUserCourseForm();
                gradeUserCourseForm.setGradeUCID(theModel.getGradeUCID());
                gradeUserCourseForm.setGradetID(theModel.getGradetID());
                gradeUserCourseForm.setGradecID(theModel.getGradecID());
                gradeUserCourseForm.setGradeuserID(theModel.getGradeuserID());
                gradeUserCourseForm.setCoursegrade(theModel.getCoursegrade());

                return gradeUserCourseForm;
        }
}
