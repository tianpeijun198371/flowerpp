package com.ulearning.ulms.course.test.question.base.model;

import org.apache.commons.lang.builder.ToStringBuilder;

import java.io.Serializable;

import java.util.Date;


/**
 * @author Hibernate CodeGenerator
 */
public class QuestionModel implements Serializable
{
        /**
         * identifier field
         */
        private int questionID;

        /**
         * persistent field
         */
        private String title;

        /**
         * persistent field
         */
        private String type;

        /**
         * persistent field
         */
        private String isContent;

        /**
         * persistent field
         */
        private int hardLevel;

        /**
         * nullable persistent field
         */
        private String key;

        /**
         * nullable persistent field
         */
        private int parentID;

        /**
         * nullable persistent field
         */
        private String chapter;

        /**
         * nullable persistent field
         */
        private String scope;

        /**
         * nullable persistent field
         */
        private String point;

        /**
         * nullable persistent field
         */
        private String object;

        /**
         * nullable persistent field
         */
        private int courseID;

        /**
         * nullable persistent field
         */
        private float score;

        /**
         * nullable persistent field
         */
        private String correctReply;

        /**
         * nullable persistent field
         */
        private String incorrectReply;

        /**
         * nullable persistent field
         */
        private String correctAnswer;

        /**
         * nullable persistent field
         */
        private String link;

        /**
         * nullable persistent field
         */
        private Date createTime;

        /**
         * nullable persistent field
         */
        private Date updateTime;

        /**
         * nullable persistent field
         */
        private String description;

        /**
         * nullable persistent field
         */
        private String remark;

        /**
         * nullable persistent field
         */
        private String desc1;

        /**
         * nullable persistent field
         */
        private String desc2;

        /**
         * nullable persistent field
         */
        private String desc3;

        /**
         * nullable persistent field
         */
        private String desc4;

        /**
         * nullable persistent field
         */
        private String desc5;

        /**
         * nullable persistent field
         */
        private String desc6;

        /**
         * nullable persistent field
         */
        private String desc7;

        /**
         * full constructor
         */
        public QuestionModel(String title, String type, String isContent,
                             int hardLevel, String key, int parentID, String chapter, String scope,
                             String point, String object, int courseID, float score,
                             String correctReply, String incorrectReply, String correctAnswer,
                             String link, Date createTime, Date updateTime, String description,
                             String remark, String desc1, String desc2, String desc3, String desc4,
                             String desc5, String desc6, String desc7)
        {
                this.title = title;
                this.type = type;
                this.isContent = isContent;
                this.hardLevel = hardLevel;
                this.key = key;
                this.parentID = parentID;
                this.chapter = chapter;
                this.scope = scope;
                this.point = point;
                this.object = object;
                this.courseID = courseID;
                this.score = score;
                this.correctReply = correctReply;
                this.incorrectReply = incorrectReply;
                this.correctAnswer = correctAnswer;
                this.link = link;
                this.createTime = createTime;
                this.updateTime = updateTime;
                this.description = description;
                this.remark = remark;
                this.desc1 = desc1;
                this.desc2 = desc2;
                this.desc3 = desc3;
                this.desc4 = desc4;
                this.desc5 = desc5;
                this.desc6 = desc6;
                this.desc7 = desc7;
        }

        /**
         * default constructor
         */
        public QuestionModel()
        {
        }

        /**
         * minimal constructor
         */
        public QuestionModel(String title, String type, String isContent,
                             int hardLevel)
        {
                this.title = title;
                this.type = type;
                this.isContent = isContent;
                this.hardLevel = hardLevel;
        }

        public int getQuestionID()
        {
                return this.questionID;
        }

        public void setQuestionID(int questionID)
        {
                this.questionID = questionID;
        }

        public String getTitle()
        {
                return this.title;
        }

        public void setTitle(String title)
        {
                this.title = title;
        }

        public String getType()
        {
                return this.type;
        }

        public void setType(String type)
        {
                this.type = type;
        }

        public String getIsContent()
        {
                return this.isContent;
        }

        public void setIsContent(String isContent)
        {
                this.isContent = isContent;
        }

        public int getHardLevel()
        {
                return this.hardLevel;
        }

        public void setHardLevel(int hardLevel)
        {
                this.hardLevel = hardLevel;
        }

        public String getKey()
        {
                return this.key;
        }

        public void setKey(String key)
        {
                this.key = key;
        }

        public int getParentID()
        {
                return this.parentID;
        }

        public void setParentID(int parentID)
        {
                this.parentID = parentID;
        }

        public String getChapter()
        {
                return this.chapter;
        }

        public void setChapter(String chapter)
        {
                this.chapter = chapter;
        }

        public String getScope()
        {
                return this.scope;
        }

        public void setScope(String scope)
        {
                this.scope = scope;
        }

        public String getPoint()
        {
                return this.point;
        }

        public void setPoint(String point)
        {
                this.point = point;
        }

        public String getObject()
        {
                return this.object;
        }

        public void setObject(String object)
        {
                this.object = object;
        }

        public int getCourseID()
        {
                return this.courseID;
        }

        public void setCourseID(int courseID)
        {
                this.courseID = courseID;
        }

        public float getScore()
        {
                return this.score;
        }

        public void setScore(float score)
        {
                this.score = score;
        }

        public String getCorrectReply()
        {
                return this.correctReply;
        }

        public void setCorrectReply(String correctReply)
        {
                this.correctReply = correctReply;
        }

        public String getIncorrectReply()
        {
                return this.incorrectReply;
        }

        public void setIncorrectReply(String incorrectReply)
        {
                this.incorrectReply = incorrectReply;
        }

        public String getCorrectAnswer()
        {
                return this.correctAnswer;
        }

        public void setCorrectAnswer(String correctAnswer)
        {
                this.correctAnswer = correctAnswer;
        }

        public String getLink()
        {
                return this.link;
        }

        public void setLink(String link)
        {
                this.link = link;
        }

        public Date getCreateTime()
        {
                return this.createTime;
        }

        public void setCreateTime(Date createTime)
        {
                this.createTime = createTime;
        }

        public Date getUpdateTime()
        {
                return this.updateTime;
        }

        public void setUpdateTime(Date updateTime)
        {
                this.updateTime = updateTime;
        }

        public String getDescription()
        {
                return this.description;
        }

        public void setDescription(String description)
        {
                this.description = description;
        }

        public String getRemark()
        {
                return this.remark;
        }

        public void setRemark(String remark)
        {
                this.remark = remark;
        }

        public String getDesc1()
        {
                return this.desc1;
        }

        public void setDesc1(String desc1)
        {
                this.desc1 = desc1;
        }

        public String getDesc2()
        {
                return this.desc2;
        }

        public void setDesc2(String desc2)
        {
                this.desc2 = desc2;
        }

        public String getDesc3()
        {
                return this.desc3;
        }

        public void setDesc3(String desc3)
        {
                this.desc3 = desc3;
        }

        public String getDesc4()
        {
                return this.desc4;
        }

        public void setDesc4(String desc4)
        {
                this.desc4 = desc4;
        }

        public String getDesc5()
        {
                return this.desc5;
        }

        public void setDesc5(String desc5)
        {
                this.desc5 = desc5;
        }

        public String getDesc6()
        {
                return this.desc6;
        }

        public void setDesc6(String desc6)
        {
                this.desc6 = desc6;
        }

        public String getDesc7()
        {
                return this.desc7;
        }

        public void setDesc7(String desc7)
        {
                this.desc7 = desc7;
        }

        public String toString()
        {
                return new ToStringBuilder(this).append("questionID", getQuestionID())
                        .toString();
        }
}
