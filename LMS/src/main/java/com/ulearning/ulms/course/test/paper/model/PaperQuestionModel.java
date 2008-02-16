package com.ulearning.ulms.course.test.paper.model;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import java.io.Serializable;


/**
 * @author Hibernate CodeGenerator
 */
public class PaperQuestionModel implements Serializable
{
        /**
         * identifier field
         */
        private PaperQuestionModelPK comp_id;

        /**
         * nullable persistent field
         */
        private float score;

        /**
         * nullable persistent field
         */
        private String type;

        /**
         * full constructor
         */
        public PaperQuestionModel(PaperQuestionModelPK comp_id, float score,
                                  String type)
        {
                this.comp_id = comp_id;
                this.score = score;
                this.type = type;
        }

        /**
         * default constructor
         */
        public PaperQuestionModel()
        {
        }

        /**
         * minimal constructor
         */
        public PaperQuestionModel(PaperQuestionModelPK comp_id)
        {
                this.comp_id = comp_id;
        }

        public PaperQuestionModelPK getComp_id()
        {
                return this.comp_id;
        }

        public void setComp_id(PaperQuestionModelPK comp_id)
        {
                this.comp_id = comp_id;
        }

        public float getScore()
        {
                return this.score;
        }

        public void setScore(float score)
        {
                this.score = score;
        }

        public String getType()
        {
                return this.type;
        }

        public void setType(String type)
        {
                this.type = type;
        }

        public String toString()
        {
                return new ToStringBuilder(this).append("comp_id", getComp_id())
                        .toString();
        }

        public boolean equals(Object other)
        {
                if ((this == other))
                {
                        return true;
                }

                if (!(other instanceof PaperQuestionModel))
                {
                        return false;
                }

                PaperQuestionModel castOther = (PaperQuestionModel) other;

                return new EqualsBuilder().append(this.getComp_id(),
                        castOther.getComp_id()).isEquals();
        }

        public int hashCode()
        {
                return new HashCodeBuilder().append(getComp_id()).toHashCode();
        }
}
