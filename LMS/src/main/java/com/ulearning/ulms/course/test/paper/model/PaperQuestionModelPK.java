package com.ulearning.ulms.course.test.paper.model;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import java.io.Serializable;


/**
 * @author Hibernate CodeGenerator
 */
public class PaperQuestionModelPK implements Serializable
{
        /**
         * identifier field
         */
        private int paperid;

        /**
         * identifier field
         */
        private int questionid;

        /**
         * full constructor
         */
        public PaperQuestionModelPK(int paperid, int questionid)
        {
                this.paperid = paperid;
                this.questionid = questionid;
        }

        /**
         * default constructor
         */
        public PaperQuestionModelPK()
        {
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
                return new ToStringBuilder(this).append("paperid", getPaperid())
                        .append("questionid", getQuestionid())
                        .toString();
        }

        public boolean equals(Object other)
        {
                if ((this == other))
                {
                        return true;
                }

                if (!(other instanceof PaperQuestionModelPK))
                {
                        return false;
                }

                PaperQuestionModelPK castOther = (PaperQuestionModelPK) other;

                return new EqualsBuilder().append(this.getPaperid(),
                        castOther.getPaperid())
                        .append(this.getQuestionid(),
                                castOther.getQuestionid()).isEquals();
        }

        public int hashCode()
        {
                return new HashCodeBuilder().append(getPaperid()).append(getQuestionid())
                        .toHashCode();
        }
}
