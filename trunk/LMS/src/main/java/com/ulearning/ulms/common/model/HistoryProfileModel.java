/**
 * HistoryProfileModel.java.
 * User: fengch  Date: 2004-4-27
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.common.model;

import com.ulearning.ulms.core.util.StringUtil;

import java.io.Serializable;

import java.util.Date;


public class HistoryProfileModel implements Serializable
{
        private int profileID;
        private int userID;
        private int relationID;
        private int type;
        private int state;
        private String score;
        private int scoreType;
        private float credit;
        private float period;
        private int isPass;
        private String loginName;
        private String userName;
        private String relationName;
        private int relationCreatorID;
        private String relationCreatorName;
        private Date enrollmentDate;
        private Date completionDate;
        private int operatorID;
        private String operatorLoginName;
        private String operatorTicket;
        private String operatorSign;
        private Date createDate;
        private Date modifyDate;

        public HistoryProfileModel()
        {
        }

        public HistoryProfileModel(HistoryModel hm)
        {
                this.profileID = hm.getProfileid();
                this.userID = hm.getUserid();
                this.relationID = hm.getRelationid();
                this.type = StringUtil.parseInt(hm.getType());
                this.state = StringUtil.parseInt(hm.getState());
                this.score = hm.getScore();
                this.period = hm.getPeriod();
                this.scoreType = hm.getScoretype();
                this.credit = hm.getCredit();
                this.isPass = StringUtil.parseInt(hm.getIspass());
                this.loginName = hm.getLoginname();
                this.userName = hm.getUsername();
                this.relationName = hm.getRelationname();
                this.relationCreatorID = hm.getRelationcreatorid();
                this.relationCreatorName = hm.getRelationcreatorname();
                this.enrollmentDate = hm.getEnrollmentdate();
                this.completionDate = hm.getCompletiondate();
                this.operatorID = hm.getOperatorid();
                this.operatorLoginName = hm.getOperatorloginname();
                this.operatorTicket = hm.getOperatorticket();
                this.operatorSign = hm.getOperatorsign();
                this.createDate = hm.getCreatedate();
                this.modifyDate = hm.getModifydate();
        }

        public HistoryProfileModel(int userID, int relationID, int type, int state,
                                   String score, int scoreType, float period, float credit, int pass,
                                   String loginName, String userName, String relationName,
                                   int relationCreatorID, String relationCreatorName, Date enrollmentDate,
                                   Date completionDate, int operatorID, String operatorloginName,
                                   String operatorTicket, String operatorSign, Date modifyDate)
        {
                this.userID = userID;
                this.relationID = relationID;
                this.type = type;
                this.state = state;
                this.score = score;
                this.scoreType = scoreType;
                this.period = period;
                this.credit = credit;
                isPass = pass;
                this.loginName = loginName;
                this.userName = userName;
                this.relationName = relationName;
                this.relationCreatorID = relationCreatorID;
                this.relationCreatorName = relationCreatorName;
                this.enrollmentDate = enrollmentDate;
                this.completionDate = completionDate;
                this.operatorID = operatorID;
                this.operatorLoginName = operatorloginName;
                this.operatorTicket = operatorTicket;
                this.operatorSign = operatorSign;
                this.modifyDate = modifyDate;
        }

        public HistoryModel getHistoryModel()
        {
                return new HistoryModel(profileID, userID, relationID,
                        String.valueOf(type), String.valueOf(state), score, scoreType,
                        period, credit, String.valueOf(isPass), loginName, userName,
                        relationName, relationCreatorID, relationCreatorName,
                        enrollmentDate, completionDate, operatorID, operatorLoginName,
                        operatorTicket, operatorSign, createDate, modifyDate);
        }

        public int getProfileID()
        {
                return profileID;
        }

        public void setProfileID(int profileID)
        {
                this.profileID = profileID;
        }

        public float getPeriod()
        {
                return period;
        }

        public void setPeriod(float period)
        {
                this.period = period;
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

        public int getState()
        {
                return this.state;
        }

        public void setState(int state)
        {
                this.state = state;
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

        public String getLoginName()
        {
                return loginName;
        }

        public void setLoginName(String loginName)
        {
                this.loginName = loginName;
        }

        public String getUserName()
        {
                return userName;
        }

        public void setUserName(String userName)
        {
                this.userName = userName;
        }

        public String getRelationName()
        {
                return relationName;
        }

        public void setRelationName(String relationName)
        {
                this.relationName = relationName;
        }

        public int getRelationCreatorID()
        {
                return relationCreatorID;
        }

        public void setRelationCreatorID(int relationCreatorID)
        {
                this.relationCreatorID = relationCreatorID;
        }

        public String getRelationCreatorName()
        {
                return relationCreatorName;
        }

        public void setRelationCreatorName(String relationCreatorName)
        {
                this.relationCreatorName = relationCreatorName;
        }

        public Date getEnrollmentDate()
        {
                return enrollmentDate;
        }

        public void setEnrollmentDate(Date enrollmentDate)
        {
                this.enrollmentDate = enrollmentDate;
        }

        public Date getCompletionDate()
        {
                return completionDate;
        }

        public void setCompletionDate(Date completionDate)
        {
                this.completionDate = completionDate;
        }

        public int getOperatorID()
        {
                return operatorID;
        }

        public void setOperatorID(int operatorID)
        {
                this.operatorID = operatorID;
        }

        public String getOperatorloginName()
        {
                return operatorLoginName;
        }

        public void setOperatorLoginName(String operatorloginName)
        {
                this.operatorLoginName = operatorloginName;
        }

        public String getOperatorTicket()
        {
                return operatorTicket;
        }

        public void setOperatorTicket(String operatorTicket)
        {
                this.operatorTicket = operatorTicket;
        }

        public String getOperatorSign()
        {
                return operatorSign;
        }

        public void setOperatorSign(String operatorSign)
        {
                this.operatorSign = operatorSign;
        }

        public Date getCreateDate()
        {
                return createDate;
        }

        public void setCreateDate(Date createDate)
        {
                this.createDate = createDate;
        }

        public Date getModifyDate()
        {
                return modifyDate;
        }

        public void setModifyDate(Date modifyDate)
        {
                this.modifyDate = modifyDate;
        }
}
