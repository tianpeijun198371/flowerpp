/**
 * Copyright (c) 2000-2005.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.course.test.Exambatch.model;

import org.apache.commons.lang.builder.ToStringBuilder;

import java.io.Serializable;

import java.util.Date;


/**
 * Class description goes here.
 * <p/>
 * 考场的数据库映射
 * User: zhuyr
 * Date: 20051121
 * Time: 135243
 */
public class ExambatchModel implements Serializable
{
        private int exambatchID = 0;
        private int paperID = 0;
        private int classID = 0;
        private int creatuserID = 0;
        private String exambatchname = "";
        private Date exambegintime;
        private Date examendtime;
        private String remark1 = "";
        private String remark2 = "";
        private String remark3 = "";
        private String remark4 = "";
        private String remark5 = "";
        private String remark6 = "";

        /**
         * default constructor
         */
        public ExambatchModel()
        {
        }

        /**
         * full constructor
         */
        public ExambatchModel(int exambatchID, int paperID, int classID,
                              int creatuserID, String exambatchname, Date exambegintime,
                              Date examendtime, String remark1, String remark2, String remark3,
                              String remark4, String remark5, String remark6)
        {
                this.exambatchID = exambatchID;
                this.paperID = paperID;
                this.classID = classID;
                this.creatuserID = creatuserID;
                this.exambatchname = exambatchname;
                this.exambegintime = exambegintime;
                this.examendtime = examendtime;
                this.remark1 = remark1;
                this.remark2 = remark2;
                this.remark3 = remark3;
                this.remark4 = remark4;
                this.remark5 = remark5;
                this.remark6 = remark6;
        }

        public int getExambatchID()
        {
                return exambatchID;
        }

        public void setExambatchID(int exambatchID)
        {
                this.exambatchID = exambatchID;
        }

        public int getPaperID()
        {
                return paperID;
        }

        public void setPaperID(int paperID)
        {
                this.paperID = paperID;
        }

        public int getClassID()
        {
                return classID;
        }

        public void setClassID(int classID)
        {
                this.classID = classID;
        }

        public int getCreatuserID()
        {
                return creatuserID;
        }

        public void setCreatuserID(int creatuserID)
        {
                this.creatuserID = creatuserID;
        }

        public String getExambatchname()
        {
                return exambatchname;
        }

        public void setExambatchname(String exambatchname)
        {
                this.exambatchname = exambatchname;
        }

        public Date getExambegintime()
        {
                return exambegintime;
        }

        public void setExambegintime(Date exambegintime)
        {
                this.exambegintime = exambegintime;
        }

        public Date getExamendtime()
        {
                return examendtime;
        }

        public void setExamendtime(Date examendtime)
        {
                this.examendtime = examendtime;
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

        public String getRemark4()
        {
                return remark4;
        }

        public void setRemark4(String remark4)
        {
                this.remark4 = remark4;
        }

        public String getRemark5()
        {
                return remark5;
        }

        public void setRemark5(String remark5)
        {
                this.remark5 = remark5;
        }

        public String getRemark6()
        {
                return remark6;
        }

        public void setRemark6(String remark6)
        {
                this.remark6 = remark6;
        }

        public String toString()
        {
                return new ToStringBuilder(this).append("exambatchID", getExambatchID())
                        .toString();
        }
}
