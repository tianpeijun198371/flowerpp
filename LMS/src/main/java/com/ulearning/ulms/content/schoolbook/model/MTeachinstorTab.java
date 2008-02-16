package com.ulearning.ulms.content.schoolbook.model;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import java.io.Serializable;

import java.util.Date;


/**
 * @author Hibernate CodeGenerator
 */
public class MTeachinstorTab implements Serializable
{
        /**
         * identifier field
         */
        private long id;

        /**
         * nullable persistent field
         */
        private Date tcindate;

        /**
         * nullable persistent field
         */
        private String tcinoperator;

        /**
         * nullable persistent field
         */
        private String tcinmemo;

        /**
         * nullable persistent field
         */
        private int tcinaudmark;

        /**
         * nullable persistent field
         */
        private String tcinaudman;

        /**
         * nullable persistent field
         */
        private String remark1;

        /**
         * nullable persistent field
         */
        private String remark2;

        /**
         * nullable persistent field
         */
        private String remark3;

        /**
         * full constructor
         */
        public MTeachinstorTab(Date tcindate, String tcinoperator, String tcinmemo,
                               int tcinaudmark, String tcinaudman, String remark1, String remark2,
                               String remark3)
        {
                this.tcindate = tcindate;
                this.tcinoperator = tcinoperator;
                this.tcinmemo = tcinmemo;
                this.tcinaudmark = tcinaudmark;
                this.tcinaudman = tcinaudman;
                this.remark1 = remark1;
                this.remark2 = remark2;
                this.remark3 = remark3;
        }

        /**
         * default constructor
         */
        public MTeachinstorTab()
        {
        }

        public long getId()
        {
                return this.id;
        }

        public void setId(long id)
        {
                this.id = id;
        }

        public Date getTcindate()
        {
                return this.tcindate;
        }

        public void setTcindate(Date tcindate)
        {
                this.tcindate = tcindate;
        }

        public String getTcinoperator()
        {
                return this.tcinoperator;
        }

        public void setTcinoperator(String tcinoperator)
        {
                this.tcinoperator = tcinoperator;
        }

        public String getTcinmemo()
        {
                return this.tcinmemo;
        }

        public void setTcinmemo(String tcinmemo)
        {
                this.tcinmemo = tcinmemo;
        }

        public int getTcinaudmark()
        {
                return this.tcinaudmark;
        }

        public void setTcinaudmark(int tcinaudmark)
        {
                this.tcinaudmark = tcinaudmark;
        }

        public String getTcinaudman()
        {
                return this.tcinaudman;
        }

        public void setTcinaudman(String tcinaudman)
        {
                this.tcinaudman = tcinaudman;
        }

        public String getRemark1()
        {
                return this.remark1;
        }

        public void setRemark1(String remark1)
        {
                this.remark1 = remark1;
        }

        public String getRemark2()
        {
                return this.remark2;
        }

        public void setRemark2(String remark2)
        {
                this.remark2 = remark2;
        }

        public String getRemark3()
        {
                return this.remark3;
        }

        public void setRemark3(String remark3)
        {
                this.remark3 = remark3;
        }

        public String toString()
        {
                return new ToStringBuilder(this).append("id", getId()).toString();
        }

        public boolean equals(Object other)
        {
                if (!(other instanceof MTeachinstorTab))
                {
                        return false;
                }

                MTeachinstorTab castOther = (MTeachinstorTab) other;

                return new EqualsBuilder().append(this.getId(), castOther.getId())
                        .isEquals();
        }

        public int hashCode()
        {
                return new HashCodeBuilder().append(getId()).toHashCode();
        }
}
