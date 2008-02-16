package com.ulearning.ulms.content.schoolbook.model;

import com.ulearning.ulms.content.schoolbook.form.MTeachmailbillitemForm;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import java.io.Serializable;


/**
 * @author Hibernate CodeGenerator
 */
public class MTeachmailbillitemTab implements Serializable
{
        /**
         * identifier field
         */
        private int titemid;

        /**
         * 主单ID
         */
        private int tcmlid;

        /**
         * 教材ID
         */
        private int bsifid;

        /**
         * 教材名称
         */
        private String bsifbookname;

        /**
         * 单价
         */
        private double tcmitemPrice;

        /**
         * 征订数量
         */
        private int tcmitemQuantity;

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
        public MTeachmailbillitemTab(int tcmlid, int bsifid, String bsifbookname,
                                     double tcmitemPrice, int tcmitemQuantity, String remark1,
                                     String remark2, String remark3, String remark4, String remark5)
        {
                this.tcmlid = tcmlid;
                this.bsifid = bsifid;
                this.bsifbookname = bsifbookname;
                this.tcmitemPrice = tcmitemPrice;
                this.tcmitemQuantity = tcmitemQuantity;
                this.remark1 = remark1;
                this.remark2 = remark2;
                this.remark3 = remark3;
                this.remark4 = remark4;
                this.remark5 = remark5;
        }

        /**
         * default constructor
         */
        public MTeachmailbillitemTab()
        {
        }

        public MTeachmailbillitemForm getFrm()
        {
                MTeachmailbillitemForm frm = new MTeachmailbillitemForm();
                frm.setBsifbookname(bsifbookname);
                frm.setBsifid(bsifid);
                frm.setRemark1(remark1);
                frm.setRemark2(remark2);
                frm.setRemark3(remark3);
                frm.setRemark4(remark4);
                frm.setRemark5(remark5);
                frm.setTcmitemPrice(tcmitemPrice);
                frm.setTcmitemQuantity(tcmitemQuantity);
                frm.setTcmlid(tcmlid);
                frm.setTitemid(titemid);

                return frm;
        }

        public int getTitemid()
        {
                return titemid;
        }

        public void setTitemid(int titemid)
        {
                this.titemid = titemid;
        }

        public int getTcmlid()
        {
                return this.tcmlid;
        }

        public void setTcmlid(int tcmlid)
        {
                this.tcmlid = tcmlid;
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

        public double getTcmitemPrice()
        {
                return this.tcmitemPrice;
        }

        public void setTcmitemPrice(double tcmitemPrice)
        {
                this.tcmitemPrice = tcmitemPrice;
        }

        public int getTcmitemQuantity()
        {
                return this.tcmitemQuantity;
        }

        public void setTcmitemQuantity(int tcmitemQuantity)
        {
                this.tcmitemQuantity = tcmitemQuantity;
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

        public String getRemark4()
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
                return new ToStringBuilder(this).append("tcmlid", getTcmlid()).toString();
        }

        public boolean equals(Object other)
        {
                if (!(other instanceof MTeachmailbillitemTab))
                {
                        return false;
                }

                MTeachmailbillitemTab castOther = (MTeachmailbillitemTab) other;

                return new EqualsBuilder().append(this.getTcmlid(),
                        castOther.getTcmlid()).isEquals();
        }

        public int hashCode()
        {
                return new HashCodeBuilder().append(getTcmlid()).toHashCode();
        }
}
