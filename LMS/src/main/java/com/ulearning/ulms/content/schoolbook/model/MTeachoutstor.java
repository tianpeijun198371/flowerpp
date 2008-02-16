package com.ulearning.ulms.content.schoolbook.model;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import java.io.Serializable;

import java.util.Date;


/**
 * @author Hibernate CodeGenerator
 */
public class MTeachoutstor implements Serializable
{
        /**
         * identifier field
         */
        private long id;

        /**
         * nullable persistent field
         */
        private Date tcoutdate;

        /**
         * nullable persistent field
         */
        private String tcoutoperator;

        /**
         * nullable persistent field
         */
        private String tcoutmemo;

        /**
         * nullable persistent field
         */
        private int tcinaudmark;

        /**
         * nullable persistent field
         */
        private String tcoutaudman;

        /**
         * nullable persistent field
         */
        private int tcoutmode;

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
        public MTeachoutstor(Date tcoutdate, String tcoutoperator,
                             String tcoutmemo, int tcinaudmark, String tcoutaudman, int tcoutmode,
                             String remark1, String remark2, String remark3)
        {
                this.tcoutdate = tcoutdate;
                this.tcoutoperator = tcoutoperator;
                this.tcoutmemo = tcoutmemo;
                this.tcinaudmark = tcinaudmark;
                this.tcoutaudman = tcoutaudman;
                this.tcoutmode = tcoutmode;
                this.remark1 = remark1;
                this.remark2 = remark2;
                this.remark3 = remark3;
        }

        /**
         * default constructor
         */
        public MTeachoutstor()
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

        public Date getTcoutdate()
        {
                return this.tcoutdate;
        }

        public void setTcoutdate(Date tcoutdate)
        {
                this.tcoutdate = tcoutdate;
        }

        public String getTcoutoperator()
        {
                return this.tcoutoperator;
        }

        public void setTcoutoperator(String tcoutoperator)
        {
                this.tcoutoperator = tcoutoperator;
        }

        public String getTcoutmemo()
        {
                return this.tcoutmemo;
        }

        public void setTcoutmemo(String tcoutmemo)
        {
                this.tcoutmemo = tcoutmemo;
        }

        public int getTcinaudmark()
        {
                return this.tcinaudmark;
        }

        public void setTcinaudmark(int tcinaudmark)
        {
                this.tcinaudmark = tcinaudmark;
        }

        public String getTcoutaudman()
        {
                return this.tcoutaudman;
        }

        public void setTcoutaudman(String tcoutaudman)
        {
                this.tcoutaudman = tcoutaudman;
        }

        public int getTcoutmode()
        {
                return this.tcoutmode;
        }

        public void setTcoutmode(int tcoutmode)
        {
                this.tcoutmode = tcoutmode;
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
                if (!(other instanceof MTeachoutstor))
                {
                        return false;
                }

                MTeachoutstor castOther = (MTeachoutstor) other;

                return new EqualsBuilder().append(this.getId(), castOther.getId())
                        .isEquals();
        }

        public int hashCode()
        {
                return new HashCodeBuilder().append(getId()).toHashCode();
        }
}
