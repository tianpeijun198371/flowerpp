/**
 * Created by IntelliJ IDEA.
 * User: zengwj
 * Date: 2004-6-19
 * Time: 11:21:40
 * To change this template use File | Settings | File Templates.
 */
package com.ulearning.ulms.course.test.grade.form;

import java.io.Serializable;

import java.util.List;


public class PaperModel implements Serializable
{
        private int paperID = 0;
        private int type = 0;
        private float totalGrade = 0;
        private float objective = 0;
        private float subjective = 0;
        private String title = "";
        private String typeName = "";
        private List questionList = null;

        public PaperModel(int paperID)
        {
                this.paperID = paperID;
        }

        public PaperModel()
        {
        }

        public int getPaperID()
        {
                return paperID;
        }

        public void setPaperID(int paperID)
        {
                this.paperID = paperID;
        }

        public int getType()
        {
                return type;
        }

        public void setType(int type)
        {
                this.type = type;
        }

        public float getTotalGrade()
        {
                return totalGrade;
        }

        public void setTotalGrade(float totalGrade)
        {
                this.totalGrade = totalGrade;
        }

        public float getObjective()
        {
                return objective;
        }

        public void setObjective(float objective)
        {
                this.objective = objective;
        }

        public float getSubjective()
        {
                return subjective;
        }

        public void setSubjective(float subjective)
        {
                this.subjective = subjective;
        }

        public String getTitle()
        {
                return title;
        }

        public void setTitle(String title)
        {
                this.title = title;
        }

        public String getTypeName()
        {
                return typeName;
        }

        public void setTypeName(String typeName)
        {
                this.typeName = typeName;
        }

        public List getQuestionList()
        {
                return questionList;
        }

        public void setQuestionList(List questionList)
        {
                this.questionList = questionList;
        }
}
