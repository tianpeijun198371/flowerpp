/**
 * Copyright (c) 2000-2005.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.admin.gradeterm.form;

import com.ulearning.ulms.admin.gradeterm.model.GradeTermModel;
import com.ulearning.ulms.tools.upload.model.UploadForm;

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
public class GradeTermForm extends UploadForm
{
        private int gradetID = 0;
        private String grade = "";
        private String term = "";
        private String speciality = "";
        private String remark1 = "";
        private String remark2 = "";
        private String remark3 = "";

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

        public GradeTermModel getGradeTermModel()
        {
                GradeTermModel gradeTermModel = new GradeTermModel();
                gradeTermModel.setGradetID(this.gradetID);
                gradeTermModel.setGrade(this.grade);
                gradeTermModel.setTerm(this.term);
                gradeTermModel.setSpeciality(this.speciality);
                gradeTermModel.setRemark1(this.remark1);
                gradeTermModel.setRemark2(this.remark2);
                gradeTermModel.setRemark3(this.remark3);

                return gradeTermModel;
        }

        public GradeTermForm getGradeTermForm(GradeTermModel theModel)
        {
                GradeTermForm gradeTermForm = new GradeTermForm();
                gradeTermForm.setGradetID(theModel.getGradetID());
                gradeTermForm.setGrade(theModel.getGrade());
                gradeTermForm.setTerm(theModel.getTerm());
                gradeTermForm.setSpeciality(theModel.getSpeciality());
                gradeTermForm.setRemark1(theModel.getRemark1());
                gradeTermForm.setRemark2(theModel.getRemark2());
                gradeTermForm.setRemark3(theModel.getRemark3());

                return gradeTermForm;
        }
}
