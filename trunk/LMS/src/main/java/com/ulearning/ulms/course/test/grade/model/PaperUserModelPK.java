package com.ulearning.ulms.course.test.grade.model;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import java.io.Serializable;


/**
 * @author Hibernate CodeGenerator
 */
public class PaperUserModelPK implements Serializable
{
        /**
         * identifier field
         */
        private int paperid;

        /**
         * identifier field
         */
        private int userid;

        /**
         * full constructor
         */
        public PaperUserModelPK(int paperid, int userid)
        {
                this.paperid = paperid;
                this.userid = userid;
        }

        /**
         * default constructor
         */
        public PaperUserModelPK()
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

        public int getUserid()
        {
                return this.userid;
        }

        public void setUserid(int userid)
        {
                this.userid = userid;
        }

        public String toString()
        {
                return new ToStringBuilder(this).append("paperid", getPaperid())
                        .append("userid", getUserid()).toString();
        }

        public boolean equals(Object other)
        {
                if ((this == other))
                {
                        return true;
                }

                if (!(other instanceof PaperUserModelPK))
                {
                        return false;
                }

                PaperUserModelPK castOther = (PaperUserModelPK) other;

                return new EqualsBuilder().append(this.getPaperid(),
                        castOther.getPaperid())
                        .append(this.getUserid(),
                                castOther.getUserid()).isEquals();
        }

        public int hashCode()
        {
                return new HashCodeBuilder().append(getPaperid()).append(getUserid())
                        .toHashCode();
        }
}
