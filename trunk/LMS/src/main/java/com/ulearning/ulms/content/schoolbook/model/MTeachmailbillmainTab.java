package com.ulearning.ulms.content.schoolbook.model;

import com.ulearning.ulms.content.schoolbook.form.MTeachmailbillmainform;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import java.io.Serializable;

import java.util.Date;


/**
 * @author Hibernate CodeGenerator
 */
public class MTeachmailbillmainTab implements Serializable
{
        /**
         * identifier field
         */
        private int tcmlid;

        /**
         * 征订单位
         */
        private String tcmllevydept;

        /**
         * 征订单位详细地址
         */
        private String tcmllevyadds;

        /**
         * 征订经办人
         */
        private String tcmllevyoperator;

        /**
         * 合计金额
         */
        private double tcmltotal;

        /**
         * 征订电话
         */
        private String tcmllevyphone;

        /**
         * 征订传真
         */
        private String tcmllevyfax;

        /**
         * 征订邮编
         */
        private String tcmllevymailcode;

        /**
         * 征订汇款方式 1：银行   2：邮局
         */
        private int tcmllevyrem;

        /**
         * 联系人
         */
        private String tcmlman;

        /**
         * 联系电话
         */
        private String tcmlphone;

        /**
         * 传真
         */
        private String tcmlfax;

        /**
         * 邮局收款人
         */
        private String tcmlpostman;

        /**
         * 邮局地址
         */
        private String tcmlpostadds;

        /**
         * 邮局邮编
         */
        private String tcmlpostcode;

        /**
         * 银行收款单位
         */
        private String tcmlbankcom;

        /**
         * 开户行
         */
        private String tcmlbankname;

        /**
         * 帐号
         */
        private String tcmlbankaccount;

        /**
         * 添单日期
         */
        private Date tcmldate;

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
        public MTeachmailbillmainTab(String tcmllevydept, String tcmllevyadds,
                                     String tcmllevyoperator, double tcmltotal, String tcmllevyphone,
                                     String tcmllevyfax, String tcmllevymailcode, int tcmllevyrem,
                                     String tcmlman, String tcmlphone, String tcmlfax, String tcmlpostman,
                                     String tcmlpostadds, String tcmlpostcode, String tcmlbankcom,
                                     String tcmlbankname, String tcmlbankaccount, String remark1,
                                     String remark2, String remark3, String remark4, String remark5,
                                     Date tcmldate)
        {
                this.tcmllevydept = tcmllevydept;
                this.tcmllevyadds = tcmllevyadds;
                this.tcmllevyoperator = tcmllevyoperator;
                this.tcmltotal = tcmltotal;
                this.tcmllevyphone = tcmllevyphone;
                this.tcmllevyfax = tcmllevyfax;
                this.tcmllevymailcode = tcmllevymailcode;
                this.tcmllevyrem = tcmllevyrem;
                this.tcmlman = tcmlman;
                this.tcmlphone = tcmlphone;
                this.tcmlfax = tcmlfax;
                this.tcmlpostman = tcmlpostman;
                this.tcmlpostadds = tcmlpostadds;
                this.tcmlpostcode = tcmlpostcode;
                this.tcmlbankcom = tcmlbankcom;
                this.tcmlbankname = tcmlbankname;
                this.tcmlbankaccount = tcmlbankaccount;
                this.remark1 = remark1;
                this.remark2 = remark2;
                this.remark3 = remark3;
                this.remark4 = remark4;
                this.remark5 = remark5;
                this.tcmldate = tcmldate;
        }

        /**
         * default constructor
         */
        public MTeachmailbillmainTab()
        {
        }

        public MTeachmailbillmainform getFrm()
        {
                MTeachmailbillmainform frm = new MTeachmailbillmainform();
                frm.setRemark1(remark1);
                frm.setRemark2(remark2);
                frm.setRemark3(remark3);
                frm.setRemark4(remark4);
                frm.setRemark5(remark5);
                frm.setTcmlbankaccount(tcmlbankaccount);
                frm.setTcmlbankcom(tcmlbankcom);
                frm.setTcmlbankname(tcmlbankname);
                frm.setTcmldate(tcmldate);
                frm.setTcmlfax(tcmlfax);
                frm.setTcmlid(tcmlid);
                frm.setTcmllevyadds(tcmllevyadds);
                frm.setTcmllevydept(tcmllevydept);

                frm.setTcmllevyfax(tcmllevyfax);
                frm.setTcmllevymailcode(tcmllevymailcode);
                frm.setTcmllevyoperator(tcmllevyoperator);
                frm.setTcmllevyphone(tcmllevyphone);
                frm.setTcmllevyrem(tcmllevyrem);
                frm.setTcmlman(tcmlman);
                frm.setTcmlphone(tcmlphone);
                frm.setTcmlpostadds(tcmlpostadds);
                frm.setTcmlpostcode(tcmlpostcode);
                frm.setTcmlpostman(tcmlpostman);
                frm.setTcmltotal(tcmltotal);

                return frm;
        }

        public int getTcmlid()
        {
                return this.tcmlid;
        }

        public void setTcmlid(int tcmlid)
        {
                this.tcmlid = tcmlid;
        }

        public String getTcmllevydept()
        {
                return this.tcmllevydept;
        }

        public void setTcmllevydept(String tcmllevydept)
        {
                this.tcmllevydept = tcmllevydept;
        }

        public String getTcmllevyadds()
        {
                return this.tcmllevyadds;
        }

        public void setTcmllevyadds(String tcmllevyadds)
        {
                this.tcmllevyadds = tcmllevyadds;
        }

        public String getTcmllevyoperator()
        {
                return this.tcmllevyoperator;
        }

        public void setTcmllevyoperator(String tcmllevyoperator)
        {
                this.tcmllevyoperator = tcmllevyoperator;
        }

        public double getTcmltotal()
        {
                return this.tcmltotal;
        }

        public void setTcmltotal(double tcmltotal)
        {
                this.tcmltotal = tcmltotal;
        }

        public String getTcmllevyphone()
        {
                return this.tcmllevyphone;
        }

        public void setTcmllevyphone(String tcmllevyphone)
        {
                this.tcmllevyphone = tcmllevyphone;
        }

        public String getTcmllevyfax()
        {
                return this.tcmllevyfax;
        }

        public void setTcmllevyfax(String tcmllevyfax)
        {
                this.tcmllevyfax = tcmllevyfax;
        }

        public String getTcmllevymailcode()
        {
                return this.tcmllevymailcode;
        }

        public void setTcmllevymailcode(String tcmllevymailcode)
        {
                this.tcmllevymailcode = tcmllevymailcode;
        }

        public int getTcmllevyrem()
        {
                return this.tcmllevyrem;
        }

        public void setTcmllevyrem(int tcmllevyrem)
        {
                this.tcmllevyrem = tcmllevyrem;
        }

        public String getTcmlman()
        {
                return this.tcmlman;
        }

        public void setTcmlman(String tcmlman)
        {
                this.tcmlman = tcmlman;
        }

        public String getTcmlphone()
        {
                return this.tcmlphone;
        }

        public void setTcmlphone(String tcmlphone)
        {
                this.tcmlphone = tcmlphone;
        }

        public String getTcmlfax()
        {
                return this.tcmlfax;
        }

        public void setTcmlfax(String tcmlfax)
        {
                this.tcmlfax = tcmlfax;
        }

        public String getTcmlpostman()
        {
                return this.tcmlpostman;
        }

        public void setTcmlpostman(String tcmlpostman)
        {
                this.tcmlpostman = tcmlpostman;
        }

        public String getTcmlpostadds()
        {
                return this.tcmlpostadds;
        }

        public void setTcmlpostadds(String tcmlpostadds)
        {
                this.tcmlpostadds = tcmlpostadds;
        }

        public String getTcmlpostcode()
        {
                return this.tcmlpostcode;
        }

        public void setTcmlpostcode(String tcmlpostcode)
        {
                this.tcmlpostcode = tcmlpostcode;
        }

        public String getTcmlbankcom()
        {
                return this.tcmlbankcom;
        }

        public void setTcmlbankcom(String tcmlbankcom)
        {
                this.tcmlbankcom = tcmlbankcom;
        }

        public String getTcmlbankname()
        {
                return this.tcmlbankname;
        }

        public void setTcmlbankname(String tcmlbankname)
        {
                this.tcmlbankname = tcmlbankname;
        }

        public String getTcmlbankaccount()
        {
                return this.tcmlbankaccount;
        }

        public void setTcmlbankaccount(String tcmlbankaccount)
        {
                this.tcmlbankaccount = tcmlbankaccount;
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

        public Date getTcmldate()
        {
                return this.tcmldate;
        }

        public void setTcmldate(Date tcmldate)
        {
                this.tcmldate = tcmldate;
        }

        public String toString()
        {
                return new ToStringBuilder(this).append("tcmlid", getTcmlid()).toString();
        }

        public boolean equals(Object other)
        {
                if (!(other instanceof MTeachmailbillmainTab))
                {
                        return false;
                }

                MTeachmailbillmainTab castOther = (MTeachmailbillmainTab) other;

                return new EqualsBuilder().append(this.getTcmlid(),
                        castOther.getTcmlid()).isEquals();
        }

        public int hashCode()
        {
                return new HashCodeBuilder().append(getTcmlid()).toHashCode();
        }
}
