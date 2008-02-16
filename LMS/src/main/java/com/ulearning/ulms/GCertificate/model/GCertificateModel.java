/**
 * Copyright (c) 2000-2005.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.GCertificate.model;

import org.apache.commons.lang.builder.ToStringBuilder;

import java.io.Serializable;

import java.util.Date;


/**
 * Class description goes here.
 * <p/>
 * Created by Auto Code Produce System
 * User: xiejh
 * Date: 20060111
 * Time: 110302
 */
public class GCertificateModel implements Serializable
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

        /**
         * default constructor
         */
        public GCertificateModel()
        {
        }

        /**
         * full constructor
         */
        public GCertificateModel(int gID, int gUserID, String gUserName,
                                 int gCourseID, String gCourseName, String gCGrade, String gCTime,
                                 String gBak1, String gBak2, String gBak3)
        {
                this.gID = gID;
                this.gUserID = gUserID;
                this.gUserName = gUserName;
                this.gCourseID = gCourseID;
                this.gCourseName = gCourseName;
                this.gCGrade = gCGrade;
                this.gCTime = gCTime;
                this.gBak1 = gBak1;
                this.gBak2 = gBak2;
                this.gBak3 = gBak3;
        }

        public String getgBak3()
        {
                return gBak3;
        }

        public void setgBak3(String gBak3)
        {
                this.gBak3 = gBak3;
        }

        public String getgUserName()
        {
                return gUserName;
        }

        public void setgUserName(String gUserName)
        {
                this.gUserName = gUserName;
        }

        public int getgCourseID()
        {
                return gCourseID;
        }

        public void setgCourseID(int gCourseID)
        {
                this.gCourseID = gCourseID;
        }

        public String getgCourseName()
        {
                return gCourseName;
        }

        public void setgCourseName(String gCourseName)
        {
                this.gCourseName = gCourseName;
        }

        public String getgCGrade()
        {
                return gCGrade;
        }

        public void setgCGrade(String gCGrade)
        {
                this.gCGrade = gCGrade;
        }

        public String getgCTime()
        {
                return gCTime;
        }

        public void setgCTime(String gCTime)
        {
                this.gCTime = gCTime;
        }

        public String getgBak1()
        {
                return gBak1;
        }

        public void setgBak1(String gBak1)
        {
                this.gBak1 = gBak1;
        }

        public String getgBak2()
        {
                return gBak2;
        }

        public void setgBak2(String gBak2)
        {
                this.gBak2 = gBak2;
        }

        public int getgID()
        {
                return gID;
        }

        public void setgID(int gID)
        {
                this.gID = gID;
        }

        public int getgUserID()
        {
                return gUserID;
        }

        public void setgUserID(int gUserID)
        {
                this.gUserID = gUserID;
        }

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

        public String toString()
        {
                return new ToStringBuilder(this).append("gID", getGID()).toString();
        }
}
