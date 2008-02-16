/**
 * Copyright (c) 2000-2005.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.GCertificate.form;

import com.ulearning.ulms.GCertificate.model.GCertificateModel;

import org.apache.struts.action.ActionForm;

import java.util.Date;


/**
 * Class description goes here.
 * <p/>
 * Created by Auto Code Produce System
 * User: xiejh
 * Date: 20060111
 * Time: 110302
 */
public class GCertificateForm extends ActionForm
{
        private int gID = 0;
        private int gUserID = 0;
        private String gUserName = "";
        private int gCourseID = 0;
        private String gCourseName = "";
        private String gCGrade = "";
        private String gCTime = "";
        private String gBak1 = "";
        private String gBak2 = "";
        private String gBak3 = "";

        public int getGID()
        {
                return gID;
        }

        public void setGID(int gID)
        {
                this.gID = gID;
        }

        public int getGUserID()
        {
                return gUserID;
        }

        public void setGUserID(int gUserID)
        {
                this.gUserID = gUserID;
        }

        public String getGUserName()
        {
                return gUserName;
        }

        public void setGUserName(String gUserName)
        {
                this.gUserName = gUserName;
        }

        public int getGCourseID()
        {
                return gCourseID;
        }

        public void setGCourseID(int gCourseID)
        {
                this.gCourseID = gCourseID;
        }

        public String getGCourseName()
        {
                return gCourseName;
        }

        public void setGCourseName(String gCourseName)
        {
                this.gCourseName = gCourseName;
        }

        public String getGCGrade()
        {
                return gCGrade;
        }

        public void setGCGrade(String gCGrade)
        {
                this.gCGrade = gCGrade;
        }

        public String getGCTime()
        {
                return gCTime;
        }

        public void setGCTime(String gCTime)
        {
                this.gCTime = gCTime;
        }

        public String getGBak1()
        {
                return gBak1;
        }

        public void setGBak1(String gBak1)
        {
                this.gBak1 = gBak1;
        }

        public String getGBak2()
        {
                return gBak2;
        }

        public void setGBak2(String gBak2)
        {
                this.gBak2 = gBak2;
        }

        public String getGBak3()
        {
                return gBak3;
        }

        public void setGBak3(String gBak3)
        {
                this.gBak3 = gBak3;
        }

        public GCertificateModel getGCertificateModel()
        {
                GCertificateModel gCertificateModel = new GCertificateModel();
                gCertificateModel.setGID(this.gID);
                gCertificateModel.setGUserID(this.gUserID);
                gCertificateModel.setGUserName(this.gUserName);
                gCertificateModel.setGCourseID(this.gCourseID);
                gCertificateModel.setGCourseName(this.gCourseName);
                gCertificateModel.setGCGrade(this.gCGrade);
                gCertificateModel.setGCTime(this.gCTime);
                gCertificateModel.setGBak1(this.gBak1);
                gCertificateModel.setGBak2(this.gBak2);
                gCertificateModel.setGBak3(this.gBak3);

                return gCertificateModel;
        }

        public GCertificateForm getGCertificateForm(GCertificateModel theModel)
        {
                GCertificateForm gCertificateForm = new GCertificateForm();
                gCertificateForm.setGID(theModel.getGID());
                gCertificateForm.setGUserID(theModel.getGUserID());
                gCertificateForm.setGUserName(theModel.getGUserName());
                gCertificateForm.setGCourseID(theModel.getGCourseID());
                gCertificateForm.setGCourseName(theModel.getGCourseName());
                gCertificateForm.setGCGrade(theModel.getGCGrade());
                gCertificateForm.setGCTime(theModel.getGCTime());
                gCertificateForm.setGBak1(theModel.getGBak1());
                gCertificateForm.setGBak2(theModel.getGBak2());
                gCertificateForm.setGBak3(theModel.getGBak3());

                return gCertificateForm;
        }
}
