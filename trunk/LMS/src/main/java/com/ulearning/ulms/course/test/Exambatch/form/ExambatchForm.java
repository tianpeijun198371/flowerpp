/**
 * Copyright (c) 2000-2005.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.course.test.Exambatch.form;

import com.ulearning.ulms.course.test.Exambatch.model.ExambatchModel;

import org.apache.struts.action.ActionForm;

import java.util.Date;


/**
 * Class description goes here.
 * <p/>
 * 考场的实体对象
 * User: zhuyr
 * Date: 20051121
 * Time: 135243
 */
public class ExambatchForm extends ActionForm
{
        private int exambatchID = 0;
        private int paperID = 0;
        private int classID = 0;
        private int creatuserID = 0;
        private String exambatchname = "";
        private String beginTime;
        private String lastTime;
        private Date exambegintime;
        private Date examendtime;
        private String remark1 = "";
        private String remark2 = "";
        private String remark3 = "";
        private String remark4 = "";
        private String remark5 = "";
        private String remark6 = "";

        public ExambatchForm()
        {
        }

        public String getBeginTime()
        {
                return beginTime;
        }

        public void setBeginTime(String beginTime)
        {
                this.beginTime = beginTime;
        }

        public String getLastTime()
        {
                return lastTime;
        }

        public void setLastTime(String lastTime)
        {
                this.lastTime = lastTime;
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

        public ExambatchModel getExambatchModel()
        {
                ExambatchModel exambatchModel = new ExambatchModel();
                exambatchModel.setExambatchID(this.exambatchID);
                exambatchModel.setPaperID(this.paperID);
                exambatchModel.setClassID(this.classID);
                exambatchModel.setCreatuserID(this.creatuserID);
                exambatchModel.setExambatchname(this.exambatchname);
                exambatchModel.setExambegintime(this.exambegintime);
                exambatchModel.setExamendtime(this.examendtime);
                exambatchModel.setRemark1(this.remark1);
                exambatchModel.setRemark2(this.remark2);
                exambatchModel.setRemark3(this.remark3);
                exambatchModel.setRemark4(this.remark4);
                exambatchModel.setRemark5(this.remark5);
                exambatchModel.setRemark6(this.remark6);

                return exambatchModel;
        }

        public ExambatchForm getExambatchForm(ExambatchModel theModel)
        {
                ExambatchForm exambatchForm = new ExambatchForm();
                exambatchForm.setExambatchID(theModel.getExambatchID());
                exambatchForm.setPaperID(theModel.getPaperID());
                exambatchForm.setClassID(theModel.getClassID());
                exambatchForm.setCreatuserID(theModel.getCreatuserID());
                exambatchForm.setExambatchname(theModel.getExambatchname());
                exambatchForm.setExambegintime(theModel.getExambegintime());
                exambatchForm.setExamendtime(theModel.getExamendtime());
                exambatchForm.setRemark1(theModel.getRemark1());
                exambatchForm.setRemark2(theModel.getRemark2());
                exambatchForm.setRemark3(theModel.getRemark3());
                exambatchForm.setRemark4(theModel.getRemark4());
                exambatchForm.setRemark5(theModel.getRemark5());
                exambatchForm.setRemark6(theModel.getRemark6());

                return exambatchForm;
        }
}
