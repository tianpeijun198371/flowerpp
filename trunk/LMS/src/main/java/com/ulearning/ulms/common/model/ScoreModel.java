/**
 * ScoreModel.java.
 * User: fengch  Date: 2004-4-27
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.common.model;

import java.io.Serializable;

import java.util.Date;


public class ScoreModel implements Serializable
{
        private int scoreID;
        private int userID;
        private int relationID;
        private int type;
        private String score;
        private int scoreType;
        private float credit;
        private int isPass;
        private int examType;
        private Date ModifyDate;
        private Date CreateDate;

        public ScoreModel()
        {
        }

        public ScoreModel(int scoreID, int userID, int relationID, int type,
                          String score, int scoreType, float credit, int pass, int examType)
        {
                this.scoreID = scoreID;
                this.userID = userID;
                this.relationID = relationID;
                this.type = type;
                this.score = score;
                this.scoreType = scoreType;
                this.credit = credit;
                isPass = pass;
                this.examType = examType;
        }

        public ScoreModel(int scoreID, int userID, int relationID, int type,
                          String score, int scoreType, float credit, int pass, int examType,
                          Date modifyDate, Date createDate)
        {
                this.scoreID = scoreID;
                this.userID = userID;
                this.relationID = relationID;
                this.type = type;
                this.score = score;
                this.scoreType = scoreType;
                this.credit = credit;
                isPass = pass;
                this.examType = examType;
                ModifyDate = modifyDate;
                CreateDate = createDate;
        }

        public ScoreHibernateModel getScoreHibernateModel()
        {
                return new ScoreHibernateModel(scoreID, userID, relationID,
                        String.valueOf(type), score, scoreType, credit,
                        String.valueOf(isPass), examType, CreateDate, ModifyDate);
        }

        public int getScoreID()
        {
                return scoreID;
        }

        public void setScoreID(int scoreID)
        {
                this.scoreID = scoreID;
        }

        public int getUserID()
        {
                return userID;
        }

        public void setUserID(int userID)
        {
                this.userID = userID;
        }

        public int getRelationID()
        {
                return relationID;
        }

        public void setRelationID(int relationID)
        {
                this.relationID = relationID;
        }

        public int getType()
        {
                return type;
        }

        public void setType(int type)
        {
                this.type = type;
        }

        public String getScore()
        {
                return score;
        }

        public void setScore(String score)
        {
                this.score = score;
        }

        public int getScoreType()
        {
                return scoreType;
        }

        public void setScoreType(int scoreType)
        {
                this.scoreType = scoreType;
        }

        public float getCredit()
        {
                return credit;
        }

        public void setCredit(float credit)
        {
                this.credit = credit;
        }

        public int getPass()
        {
                return isPass;
        }

        public void setPass(int pass)
        {
                isPass = pass;
        }

        public int getExamType()
        {
                return examType;
        }

        public void setExamType(int examType)
        {
                this.examType = examType;
        }

        public Date getModifyDate()
        {
                return ModifyDate;
        }

        public void setModifyDate(Date modifyDate)
        {
                ModifyDate = modifyDate;
        }

        public Date getCreateDate()
        {
                return CreateDate;
        }

        public void setCreateDate(Date createDate)
        {
                CreateDate = createDate;
        }
}
