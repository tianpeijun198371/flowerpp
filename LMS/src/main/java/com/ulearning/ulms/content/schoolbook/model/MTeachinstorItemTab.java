package com.ulearning.ulms.content.schoolbook.model;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import java.io.Serializable;

import java.util.Date;


/**
 * @author Hibernate CodeGenerator
 */
public class MTeachinstorItemTab implements Serializable
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
         * nullable persistent field
         */
        private int tcinid;

        /**
         * nullable persistent field
         */
        private int bsifid;

        /**
         * nullable persistent field
         */
        private String bsifbookname;

        /**
         * nullable persistent field
         */
        private float tcintmprice;

        /**
         * nullable persistent field
         */
        private float tcintmquantity;

        /**
         * nullable persistent field
         */
        private float tcintmtotal;

        /**
         * nullable persistent field
         */
        private int tcintmsupplierid;

        /**
         * nullable persistent field
         */
        private String tcintmsuppliername;

        /**
         * nullable persistent field
         */
        private int tcintmsourceid;

        /**
         * nullable persistent field
         */
        private String tcintmsourcename;

        /**
         * full constructor
         */
        public MTeachinstorItemTab(Date tcindate, String tcinoperator,
                                   String tcinmemo, int tcinaudmark, String tcinaudman, String remark1,
                                   String remark2, String remark3, int tcinid, int bsifid,
                                   String bsifbookname, float tcintmprice, float tcintmquantity,
                                   float tcintmtotal, int tcintmsupplierid, String tcintmsuppliername,
                                   int tcintmsourceid, String tcintmsourcename)
        {
                this.tcindate = tcindate;
                this.tcinoperator = tcinoperator;
                this.tcinmemo = tcinmemo;
                this.tcinaudmark = tcinaudmark;
                this.tcinaudman = tcinaudman;
                this.remark1 = remark1;
                this.remark2 = remark2;
                this.remark3 = remark3;
                this.tcinid = tcinid;
                this.bsifid = bsifid;
                this.bsifbookname = bsifbookname;
                this.tcintmprice = tcintmprice;
                this.tcintmquantity = tcintmquantity;
                this.tcintmtotal = tcintmtotal;
                this.tcintmsupplierid = tcintmsupplierid;
                this.tcintmsuppliername = tcintmsuppliername;
                this.tcintmsourceid = tcintmsourceid;
                this.tcintmsourcename = tcintmsourcename;
        }

        /**
         * default constructor
         */
        public MTeachinstorItemTab()
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

        public int getTcinid()
        {
                return this.tcinid;
        }

        public void setTcinid(int tcinid)
        {
                this.tcinid = tcinid;
        }

        public int getBsifid()
        {
                return this.bsifid;
        }

        public void setBsifid(int bsifid)
        {
                this.bsifid = bsifid;
        }

        public String getBsifbookname()
        {
                return this.bsifbookname;
        }

        public void setBsifbookname(String bsifbookname)
        {
                this.bsifbookname = bsifbookname;
        }

        public float getTcintmprice()
        {
                return this.tcintmprice;
        }

        public void setTcintmprice(float tcintmprice)
        {
                this.tcintmprice = tcintmprice;
        }

        public float getTcintmquantity()
        {
                return this.tcintmquantity;
        }

        public void setTcintmquantity(float tcintmquantity)
        {
                this.tcintmquantity = tcintmquantity;
        }

        public float getTcintmtotal()
        {
                return this.tcintmtotal;
        }

        public void setTcintmtotal(float tcintmtotal)
        {
                this.tcintmtotal = tcintmtotal;
        }

        public int getTcintmsupplierid()
        {
                return this.tcintmsupplierid;
        }

        public void setTcintmsupplierid(int tcintmsupplierid)
        {
                this.tcintmsupplierid = tcintmsupplierid;
        }

        public String getTcintmsuppliername()
        {
                return this.tcintmsuppliername;
        }

        public void setTcintmsuppliername(String tcintmsuppliername)
        {
                this.tcintmsuppliername = tcintmsuppliername;
        }

        public int getTcintmsourceid()
        {
                return this.tcintmsourceid;
        }

        public void setTcintmsourceid(int tcintmsourceid)
        {
                this.tcintmsourceid = tcintmsourceid;
        }

        public String getTcintmsourcename()
        {
                return this.tcintmsourcename;
        }

        public void setTcintmsourcename(String tcintmsourcename)
        {
                this.tcintmsourcename = tcintmsourcename;
        }

        public String toString()
        {
                return new ToStringBuilder(this).append("id", getId()).toString();
        }

        public boolean equals(Object other)
        {
                if (!(other instanceof MTeachinstorItemTab))
                {
                        return false;
                }

                MTeachinstorItemTab castOther = (MTeachinstorItemTab) other;

                return new EqualsBuilder().append(this.getId(), castOther.getId())
                        .isEquals();
        }

        public int hashCode()
        {
                return new HashCodeBuilder().append(getId()).toHashCode();
        }
}
