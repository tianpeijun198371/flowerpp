package com.ulearning.ulms.content.schoolbook.model;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import java.io.Serializable;

import java.util.Date;


/**
 * @author Hibernate CodeGenerator
 */
public class MTeachbeseinfoTab implements Serializable
{
        /**
         * identifier field
         */
        private long id;

        /**
         * nullable persistent field
         */
        private String isbn;

        /**
         * persistent field
         */
        private String bsifbookname;

        /**
         * nullable persistent field
         */
        private int bsifsortid;

        /**
         * nullable persistent field
         */
        private String bdifsortname;

        /**
         * nullable persistent field
         */
        private int bsifpublishid;

        /**
         * nullable persistent field
         */
        private String bsifpublishname;

        /**
         * nullable persistent field
         */
        private Date bsifpublishdate;

        /**
         * nullable persistent field
         */
        private String bsifauthor;

        /**
         * nullable persistent field
         */
        private String bsifeditor;

        /**
         * nullable persistent field
         */
        private String bsifholdtrue;

        /**
         * nullable persistent field
         */
        private int bsifimpression;

        /**
         * nullable persistent field
         */
        private double bsifword;

        /**
         * nullable persistent field
         */
        private Date bsifappenddate;

        /**
         * nullable persistent field
         */
        private String bsifappenddept;

        /**
         * nullable persistent field
         */
        private float bsifprice;

        /**
         * nullable persistent field
         */
        private float bsifmailprice;

        /**
         * nullable persistent field
         */
        private String bsifintr;

        /**
         * nullable persistent field
         */
        private String bsifcover;

        /**
         * nullable persistent field
         */
        private String bsifcatalong;

        /**
         * nullable persistent field
         */
        private String bsifmemo;

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
        private String remark4;

        /**
         * nullable persistent field
         */
        private String remark5;

        /**
         * full constructor
         */
        public MTeachbeseinfoTab(String isbn, String bsifbookname, int bsifsortid,
                                 String bdifsortname, int bsifpublishid, String bsifpublishname,
                                 Date bsifpublishdate, String bsifauthor, String bsifeditor,
                                 String bsifholdtrue, int bsifimpression, double bsifword,
                                 Date bsifappenddate, String bsifappenddept, float bsifprice,
                                 float bsifmailprice, String bsifintr, String bsifcover,
                                 String bsifcatalong, String bsifmemo, String remark1, String remark2,
                                 String remark3, String remark4, String remark5)
        {
                this.isbn = isbn;
                this.bsifbookname = bsifbookname;
                this.bsifsortid = bsifsortid;
                this.bdifsortname = bdifsortname;
                this.bsifpublishid = bsifpublishid;
                this.bsifpublishname = bsifpublishname;
                this.bsifpublishdate = bsifpublishdate;
                this.bsifauthor = bsifauthor;
                this.bsifeditor = bsifeditor;
                this.bsifholdtrue = bsifholdtrue;
                this.bsifimpression = bsifimpression;
                this.bsifword = bsifword;
                this.bsifappenddate = bsifappenddate;
                this.bsifappenddept = bsifappenddept;
                this.bsifprice = bsifprice;
                this.bsifmailprice = bsifmailprice;
                this.bsifintr = bsifintr;
                this.bsifcover = bsifcover;
                this.bsifcatalong = bsifcatalong;
                this.bsifmemo = bsifmemo;
                this.remark1 = remark1;
                this.remark2 = remark2;
                this.remark3 = remark3;
                this.remark4 = remark4;
                this.remark5 = remark5;
        }

        /**
         * default constructor
         */
        public MTeachbeseinfoTab()
        {
        }

        /**
         * minimal constructor
         */
        public MTeachbeseinfoTab(String bsifbookname)
        {
                this.bsifbookname = bsifbookname;
        }

        public long getId()
        {
                return this.id;
        }

        public void setId(long id)
        {
                this.id = id;
        }

        public String getIsbn()
        {
                return this.isbn;
        }

        public void setIsbn(String isbn)
        {
                this.isbn = isbn;
        }

        public String getBsifbookname()
        {
                return this.bsifbookname;
        }

        public void setBsifbookname(String bsifbookname)
        {
                this.bsifbookname = bsifbookname;
        }

        public int getBsifsortid()
        {
                return this.bsifsortid;
        }

        public void setBsifsortid(int bsifsortid)
        {
                this.bsifsortid = bsifsortid;
        }

        public String getBdifsortname()
        {
                return this.bdifsortname;
        }

        public void setBdifsortname(String bdifsortname)
        {
                this.bdifsortname = bdifsortname;
        }

        public int getBsifpublishid()
        {
                return this.bsifpublishid;
        }

        public void setBsifpublishid(int bsifpublishid)
        {
                this.bsifpublishid = bsifpublishid;
        }

        public String getBsifpublishname()
        {
                return this.bsifpublishname;
        }

        public void setBsifpublishname(String bsifpublishname)
        {
                this.bsifpublishname = bsifpublishname;
        }

        public Date getBsifpublishdate()
        {
                return this.bsifpublishdate;
        }

        public void setBsifpublishdate(Date bsifpublishdate)
        {
                this.bsifpublishdate = bsifpublishdate;
        }

        public String getBsifauthor()
        {
                return this.bsifauthor;
        }

        public void setBsifauthor(String bsifauthor)
        {
                this.bsifauthor = bsifauthor;
        }

        public String getBsifeditor()
        {
                return this.bsifeditor;
        }

        public void setBsifeditor(String bsifeditor)
        {
                this.bsifeditor = bsifeditor;
        }

        public String getBsifholdtrue()
        {
                return this.bsifholdtrue;
        }

        public void setBsifholdtrue(String bsifholdtrue)
        {
                this.bsifholdtrue = bsifholdtrue;
        }

        public int getBsifimpression()
        {
                return this.bsifimpression;
        }

        public void setBsifimpression(int bsifimpression)
        {
                this.bsifimpression = bsifimpression;
        }

        public double getBsifword()
        {
                return this.bsifword;
        }

        public void setBsifword(double bsifword)
        {
                this.bsifword = bsifword;
        }

        public Date getBsifappenddate()
        {
                return this.bsifappenddate;
        }

        public void setBsifappenddate(Date bsifappenddate)
        {
                this.bsifappenddate = bsifappenddate;
        }

        public String getBsifappenddept()
        {
                return this.bsifappenddept;
        }

        public void setBsifappenddept(String bsifappenddept)
        {
                this.bsifappenddept = bsifappenddept;
        }

        public float getBsifprice()
        {
                return this.bsifprice;
        }

        public void setBsifprice(float bsifprice)
        {
                this.bsifprice = bsifprice;
        }

        public float getBsifmailprice()
        {
                return this.bsifmailprice;
        }

        public void setBsifmailprice(float bsifmailprice)
        {
                this.bsifmailprice = bsifmailprice;
        }

        public Serializable getBsifintr()
        {
                return this.bsifintr;
        }

        public void setBsifintr(String bsifintr)
        {
                this.bsifintr = bsifintr;
        }

        public String getBsifcover()
        {
                return this.bsifcover;
        }

        public void setBsifcover(String bsifcover)
        {
                this.bsifcover = bsifcover;
        }

        public Serializable getBsifcatalong()
        {
                return this.bsifcatalong;
        }

        public void setBsifcatalong(String bsifcatalong)
        {
                this.bsifcatalong = bsifcatalong;
        }

        public String getBsifmemo()
        {
                return this.bsifmemo;
        }

        public void setBsifmemo(String bsifmemo)
        {
                this.bsifmemo = bsifmemo;
        }

        public String getRemark1()
        {
                return this.remark1;
        }

        public void setRemark1(String remark1)
        {
                this.remark1 = remark1;
        }

        public Serializable getRemark2()
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

        public Serializable getRemark4()
        {
                return this.remark4;
        }

        public void setRemark4(String remark4)
        {
                this.remark4 = remark4;
        }

        public String getRemark5()
        {
                return this.remark5;
        }

        public void setRemark5(String remark5)
        {
                this.remark5 = remark5;
        }

        public String toString()
        {
                return new ToStringBuilder(this).append("id", getId()).toString();
        }

        public boolean equals(Object other)
        {
                if (!(other instanceof MTeachbeseinfoTab))
                {
                        return false;
                }

                MTeachbeseinfoTab castOther = (MTeachbeseinfoTab) other;

                return new EqualsBuilder().append(this.getId(), castOther.getId())
                        .isEquals();
        }

        public int hashCode()
        {
                return new HashCodeBuilder().append(getId()).toHashCode();
        }
}
