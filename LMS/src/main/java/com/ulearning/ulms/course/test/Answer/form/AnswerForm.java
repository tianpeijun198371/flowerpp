/**
 * Copyright (c) 2000-2005.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.course.test.Answer.form;

import com.ulearning.ulms.course.test.Answer.model.AnswerModel;

import org.apache.struts.action.ActionForm;

import java.util.Date;


/**
 * Class description goes here.
 * <p/>
 * Created by Auto Code Produce System
 * User: huangsb
 * Date: 20051121
 * Time: 135243
 */
public class AnswerForm extends ActionForm
{
        private int answerID = 0;
        private int userID = 0;
        private int paperID = 0;
        private int questionID = 0;
        private int type = 0;
        private String answer = "";
        private float grade = 0;
        private int examtimes = 0;
        private String remark1 = "";
        private String remark2 = "";
        private String remark3 = "";
        private String remark4 = "";
        private String remark5 = "";

        public AnswerForm()
        {
        }

        public int getAnswerID()
        {
                return answerID;
        }

        public void setAnswerID(int answerID)
        {
                this.answerID = answerID;
        }

        public int getUserID()
        {
                return userID;
        }

        public void setUserID(int userID)
        {
                this.userID = userID;
        }

        public int getPaperID()
        {
                return paperID;
        }

        public void setPaperID(int paperID)
        {
                this.paperID = paperID;
        }

        public int getQuestionID()
        {
                return questionID;
        }

        public void setQuestionID(int questionID)
        {
                this.questionID = questionID;
        }

        public int getType()
        {
                return type;
        }

        public void setType(int type)
        {
                this.type = type;
        }

        public String getAnswer()
        {
                return answer;
        }

        public void setAnswer(String answer)
        {
                this.answer = answer;
        }

        public float getGrade()
        {
                return grade;
        }

        public void setGrade(float grade)
        {
                this.grade = grade;
        }

        public int getExamtimes()
        {
                return examtimes;
        }

        public void setExamtimes(int examtimes)
        {
                this.examtimes = examtimes;
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

        public AnswerModel getAnswerModel()
        {
                AnswerModel answerModel = new AnswerModel();
                answerModel.setAnswerID(this.answerID);
                answerModel.setUserID(this.userID);
                answerModel.setPaperID(this.paperID);
                answerModel.setQuestionID(this.questionID);
                answerModel.setType(this.type);
                answerModel.setAnswer(this.answer);
                answerModel.setGrade(this.grade);
                answerModel.setExamtimes(this.examtimes);
                answerModel.setRemark1(this.remark1);
                answerModel.setRemark2(this.remark2);
                answerModel.setRemark3(this.remark3);
                answerModel.setRemark4(this.remark4);
                answerModel.setRemark5(this.remark5);

                return answerModel;
        }

        public AnswerForm getAnswerForm(AnswerModel theModel)
        {
                AnswerForm answerForm = new AnswerForm();
                answerForm.setAnswerID(theModel.getAnswerID());
                answerForm.setUserID(theModel.getUserID());
                answerForm.setPaperID(theModel.getPaperID());
                answerForm.setQuestionID(theModel.getQuestionID());
                answerForm.setType(theModel.getType());
                answerForm.setAnswer(theModel.getAnswer());
                answerForm.setGrade(theModel.getGrade());
                answerForm.setExamtimes(theModel.getExamtimes());
                answerForm.setRemark1(theModel.getRemark1());
                answerForm.setRemark2(theModel.getRemark2());
                answerForm.setRemark3(theModel.getRemark3());
                answerForm.setRemark4(theModel.getRemark4());
                answerForm.setRemark5(theModel.getRemark5());

                return answerForm;
        }
}
