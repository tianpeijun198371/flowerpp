package com.ulearning.ulms.common.model;

import com.ulearning.ulms.user.model.UserModel;

import org.apache.commons.lang.builder.ToStringBuilder;

import java.io.Serializable;

import java.util.Date;


/**
 * @author Hibernate CodeGenerator
 */
public class ScoreHibernateModel implements Serializable
{
        /**
         * identifier field
         */
        private int scoreid;

        /**
         * identifier field
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
        private float credit;

        /**
         * nullable persistent field
         */
        private String ispass;

        /**
         * nullable persistent field
         */
        private int examtype;

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
        public ScoreHibernateModel(int scoreid, int userid, int relationid,
                                   String type, String score, int scoretype, float credit, String ispass,
                                   int examtype, Date createdate, Date modifydate)
        {
                this.scoreid = scoreid;
                this.userid = userid;
                this.relationid = relationid;
                this.type = type;
                this.score = score;
                this.scoretype = scoretype;
                this.credit = credit;
                this.ispass = ispass;
                this.examtype = examtype;
                this.createdate = createdate;
                this.modifydate = modifydate;
        }

        /**
         * default constructor
         */
        public ScoreHibernateModel()
        {
        }

        /**
         * minimal constructor
         */
        public ScoreHibernateModel(int scoreid, String type)
        {
                this.scoreid = scoreid;
                this.type = type;
        }

        public int getScoreid()
        {
                return this.scoreid;
        }

        public void setScoreid(int scoreid)
        {
                this.scoreid = scoreid;
        }

        public int getUserid()
        {
                return userid;
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

        public int getExamtype()
        {
                return this.examtype;
        }

        public void setExamtype(int examtype)
        {
                this.examtype = examtype;
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

        public String toString()
        {
                return new ToStringBuilder(this).append("scoreid", getScoreid())
                        .toString();
        }
}
