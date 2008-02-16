/**
 * HistoryProfileModel.java.
 * User: fengch  Date: 2004-8-27 16:14
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.common.model;

import org.apache.commons.lang.builder.ToStringBuilder;

import java.io.Serializable;

import java.util.Date;


/**
 * @author Hibernate CodeGenerator
 */
public class HistoryModel implements Serializable
{
        /**
         * identifier field
         */
        private int profileid;

        /**
         * persistent field
         */
        private int userid;

        /**
         * nullable persistent field
         */
        private int relationid;

        /**
         * persistent field
         */
        private String type;

        /**
         * nullable persistent field
         */
        private String score;

        /**
         * nullable persistent field
         */
        private int scoretype;

        /**
         * nullable persistent field
         */
        private float period;

        /**
         * nullable persistent field
         */
        private float credit;

        /**
         * persistent field
         */
        private String ispass;

        /**
         * persistent field
         */
        private String state;

        /**
         * nullable persistent field
         */
        private String loginname;

        /**
         * nullable persistent field
         */
        private String username;

        /**
         * nullable persistent field
         */
        private String relationname;

        /**
         * nullable persistent field
         */
        private int relationcreatorid;

        /**
         * nullable persistent field
         */
        private String relationcreatorname;

        /**
         * nullable persistent field
         */
        private Date enrollmentdate;

        /**
         * nullable persistent field
         */
        private Date completiondate;

        /**
         * nullable persistent field
         */
        private int operatorid;

        /**
         * nullable persistent field
         */
        private String operatorloginname;

        /**
         * nullable persistent field
         */
        private String operatorticket;

        /**
         * nullable persistent field
         */
        private String operatorsign;

        /**
         * nullable persistent field
         */
        private Date createdate;

        /**
         * nullable persistent field
         */
        private Date modifydate;

        /**
         * full constructor
         */
        public HistoryModel(int profileid, int userid, int relationid, String type,
                            String state, String score, int scoretype, float period, float credit,
                            String ispass, String loginname, String username, String relationname,
                            int relationcreatorid, String relationcreatorname, Date enrollmentdate,
                            Date completiondate, int operatorid, String operatorloginname,
                            String operatorticket, String operatorsign, Date createdate,
                            Date modifydate)
        {
                this.profileid = profileid;
                this.userid = userid;
                this.relationid = relationid;
                this.type = type;
                this.score = score;
                this.scoretype = scoretype;
                this.period = period;
                this.credit = credit;
                this.state = state;
                this.ispass = ispass;
                this.loginname = loginname;
                this.username = username;
                this.relationname = relationname;
                this.relationcreatorid = relationcreatorid;
                this.relationcreatorname = relationcreatorname;
                this.enrollmentdate = enrollmentdate;
                this.completiondate = completiondate;
                this.operatorid = operatorid;
                this.operatorloginname = operatorloginname;
                this.operatorticket = operatorticket;
                this.operatorsign = operatorsign;
                this.createdate = createdate;
                this.modifydate = modifydate;
        }

        /**
         * default constructor
         */
        public HistoryModel()
        {
        }

        /**
         * minimal constructor
         */
        public HistoryModel(int profileid, int userid, String type, String state,
                            String ispass)
        {
                this.profileid = profileid;
                this.userid = userid;
                this.type = type;
                this.ispass = ispass;
                this.state = state;
        }

        public int getProfileid()
        {
                return this.profileid;
        }

        public void setProfileid(int profileid)
        {
                this.profileid = profileid;
        }

        public int getUserid()
        {
                return this.userid;
        }

        public void setUserid(int userid)
        {
                this.userid = userid;
        }

        public int getRelationid()
        {
                return this.relationid;
        }

        public void setRelationid(int relationid)
        {
                this.relationid = relationid;
        }

        public String getType()
        {
                return this.type;
        }

        public void setType(String type)
        {
                this.type = type;
        }

        public String getState()
        {
                return this.state;
        }

        public void setState(String state)
        {
                this.state = state;
        }

        public String getScore()
        {
                return this.score;
        }

        public void setScore(String score)
        {
                this.score = score;
        }

        public int getScoretype()
        {
                return this.scoretype;
        }

        public void setScoretype(int scoretype)
        {
                this.scoretype = scoretype;
        }

        public float getCredit()
        {
                return this.credit;
        }

        public void setCredit(float credit)
        {
                this.credit = credit;
        }

        public String getIspass()
        {
                return this.ispass;
        }

        public void setIspass(String ispass)
        {
                this.ispass = ispass;
        }

        public String getLoginname()
        {
                return this.loginname;
        }

        public void setLoginname(String loginname)
        {
                this.loginname = loginname;
        }

        public String getUsername()
        {
                return this.username;
        }

        public void setUsername(String username)
        {
                this.username = username;
        }

        public String getRelationname()
        {
                return this.relationname;
        }

        public void setRelationname(String relationname)
        {
                this.relationname = relationname;
        }

        public int getRelationcreatorid()
        {
                return this.relationcreatorid;
        }

        public void setRelationcreatorid(int relationcreatorid)
        {
                this.relationcreatorid = relationcreatorid;
        }

        public String getRelationcreatorname()
        {
                return this.relationcreatorname;
        }

        public void setRelationcreatorname(String relationcreatorname)
        {
                this.relationcreatorname = relationcreatorname;
        }

        public Date getEnrollmentdate()
        {
                return this.enrollmentdate;
        }

        public void setEnrollmentdate(Date enrollmentdate)
        {
                this.enrollmentdate = enrollmentdate;
        }

        public Date getCompletiondate()
        {
                return this.completiondate;
        }

        public void setCompletiondate(Date completiondate)
        {
                this.completiondate = completiondate;
        }

        public int getOperatorid()
        {
                return this.operatorid;
        }

        public void setOperatorid(int operatorid)
        {
                this.operatorid = operatorid;
        }

        public String getOperatorloginname()
        {
                return this.operatorloginname;
        }

        public void setOperatorloginname(String operatorloginname)
        {
                this.operatorloginname = operatorloginname;
        }

        public String getOperatorticket()
        {
                return this.operatorticket;
        }

        public void setOperatorticket(String operatorticket)
        {
                this.operatorticket = operatorticket;
        }

        public String getOperatorsign()
        {
                return this.operatorsign;
        }

        public void setOperatorsign(String operatorsign)
        {
                this.operatorsign = operatorsign;
        }

        public Date getCreatedate()
        {
                return this.createdate;
        }

        public void setCreatedate(Date createdate)
        {
                this.createdate = createdate;
        }

        public Date getModifydate()
        {
                return this.modifydate;
        }

        public void setModifydate(Date modifydate)
        {
                this.modifydate = modifydate;
        }

        public float getPeriod()
        {
                return period;
        }

        public void setPeriod(float period)
        {
                this.period = period;
        }

        public String toString()
        {
                return new ToStringBuilder(this).append("profileid", getProfileid())
                        .toString();
        }
}
