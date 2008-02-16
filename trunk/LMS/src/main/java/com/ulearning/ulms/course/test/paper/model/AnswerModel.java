package com.ulearning.ulms.course.test.paper.model;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import java.io.Serializable;


/**
 * @author Hibernate CodeGenerator
 */
public class AnswerModel implements Serializable
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

        /**
         * default constructor
         */
        public AnswerModel()
        {
        }

        /**
         * full constructor
         */
        public AnswerModel(int answerID, int userID, int paperID, int questionID,
                           int type, String answer, float grade, int examtimes, String remark1,
                           String remark2, String remark3, String remark4, String remark5)
        {
                this.answerID = answerID;
                this.userID = userID;
                this.paperID = paperID;
                this.questionID = questionID;
                this.type = type;
                this.answer = answer;
                this.grade = grade;
                this.examtimes = examtimes;
                this.remark1 = remark1;
                this.remark2 = remark2;
                this.remark3 = remark3;
                this.remark4 = remark4;
                this.remark5 = remark5;
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

        public String toString()
        {
                return new ToStringBuilder(this).append("answerID", getAnswerID())
                        .toString();
        }
}
