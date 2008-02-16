package com.ulearning.ulms.course.test.paper.model;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import java.io.Serializable;


/**
 * @author Hibernate CodeGenerator
 */
public class AnswerModelPK implements Serializable
{
        /**
         * identifier field
         */
        private int userid;

        /**
         * identifier field
         */
        private int paperid;
        private int examtimes;

        /**
         * identifier field
         */
        private int questionid;

        /**
         * full constructor
         */
        public AnswerModelPK(int userid, int paperid, int questionid)
        {
                this.userid = userid;
                this.paperid = paperid;
                this.questionid = questionid;
        }

        /**
         * default constructor
         */
        public AnswerModelPK()
        {
        }

        public int getExamtimes()
        {
                return examtimes;
        }

        public void setExamtimes(int examtimes)
        {
                this.examtimes = examtimes;
        }

        public int getUserid()
        {
                return this.userid;
        }

        public void setUserid(int userid)
        {
                this.userid = userid;
        }

        public int getPaperid()
        {
                return this.paperid;
        }

        public void setPaperid(int paperid)
        {
                this.paperid = paperid;
        }

        public int getQuestionid()
        {
                return this.questionid;
        }

        public void setQuestionid(int questionid)
        {
                this.questionid = questionid;
        }

        public String toString()
        {
                return new ToStringBuilder(this).append("userid", getUserid())
                        .append("paperid", getPaperid())
                        .append("questionid", getQuestionid())
                        .toString();
        }

        public boolean equals(Object other)
        {
                if ((this == other))
                {
                        return true;
                }

                if (!(other instanceof AnswerModelPK))
                {
                        return false;
                }

                AnswerModelPK castOther = (AnswerModelPK) other;

                return new EqualsBuilder().append(this.getUserid(),
                        castOther.getUserid())
                        .append(this.getPaperid(),
                                castOther.getPaperid())
                        .append(this.getQuestionid(),
                                castOther.getQuestionid()).isEquals();
        }

        public int hashCode()
        {
                return new HashCodeBuilder().append(getUserid()).append(getPaperid())
                        .append(getQuestionid()).toHashCode();
        }
}
