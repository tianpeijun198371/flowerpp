package com.ulearning.ulms.content.schoolbook.model;

import com.ulearning.ulms.content.schoolbook.form.OrderForm;

import java.io.Serializable;


/**
 * @author Hibernate CodeGenerator
 */
public class MTeachmailbillTab implements Serializable
{
        /**
         * identifier field
         */
        private int tcmlid;

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
        private String tcmldept;

        /**
         * nullable persistent field
         */
        private String tcmlman;

        /**
         * nullable persistent field
         */
        private double tcmlmailprice;

        /**
         * nullable persistent field
         */
        private int tcmlquantity;

        /**
         * nullable persistent field
         */
        private double tcmltotal;

        /**
         * nullable persistent field
         */
        private String tcmlphone;

        /**
         * nullable persistent field
         */
        private String tcmloperator;

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

        public MTeachmailbillTab(int tcmlid, int bsifid, String bsifbookname,
                                 String tcmldept, String tcmlman, double tcmlmailprice,
                                 int tcmlquantity, double tcmltotal, String tcmlphone,
                                 String tcmloperator, String remark1, String remark2, String remark3,
                                 String remark4, String remark5)
        {
                this.tcmlid = tcmlid;
                this.bsifid = bsifid;
                this.bsifbookname = bsifbookname;
                this.tcmldept = tcmldept;
                this.tcmlman = tcmlman;
                this.tcmlmailprice = tcmlmailprice;
                this.tcmlquantity = tcmlquantity;
                this.tcmltotal = tcmltotal;
                this.tcmlphone = tcmlphone;
                this.tcmloperator = tcmloperator;
                this.remark1 = remark1;
                this.remark2 = remark2;
                this.remark3 = remark3;
                this.remark4 = remark4;
                this.remark5 = remark5;
        }

        public MTeachmailbillTab()
        {
        }

        public OrderForm getFrm()
        {
                OrderForm frm = new OrderForm();
                frm.setTcmlid(tcmlid);
                frm.setBsifid(bsifid);
                frm.setBsifbookname(bsifbookname);
                frm.setTcmldept(tcmldept);
                frm.setTcmlman(tcmlman);
                frm.setTcmlmailprice(tcmlmailprice);
                frm.setTcmlquantity(tcmlquantity);
                frm.setTcmltotal(tcmltotal);
                frm.setTcmlphone(tcmlphone);
                frm.setTcmloperator(tcmloperator);
                frm.setRemark1(remark1);
                frm.setRemark2(remark2);
                frm.setRemark3(remark3);
                frm.setRemark4(remark4);
                frm.setRemark5(remark5);

                return frm;
        }

        public int getTcmlid()
        {
                return tcmlid;
        }

        public void setTcmlid(int tcmlid)
        {
                this.tcmlid = tcmlid;
        }

        public int getBsifid()
        {
                return bsifid;
        }

        public void setBsifid(int bsifid)
        {
                this.bsifid = bsifid;
        }

        public String getBsifbookname()
        {
                return bsifbookname;
        }

        public void setBsifbookname(String bsifbookname)
        {
                this.bsifbookname = bsifbookname;
        }

        public String getTcmldept()
        {
                return tcmldept;
        }

        public void setTcmldept(String tcmldept)
        {
                this.tcmldept = tcmldept;
        }

        public String getTcmlman()
        {
                return tcmlman;
        }

        public void setTcmlman(String tcmlman)
        {
                this.tcmlman = tcmlman;
        }

        public double getTcmlmailprice()
        {
                return tcmlmailprice;
        }

        public void setTcmlmailprice(double tcmlmailprice)
        {
                this.tcmlmailprice = tcmlmailprice;
        }

        public int getTcmlquantity()
        {
                return tcmlquantity;
        }

        public void setTcmlquantity(int tcmlquantity)
        {
                this.tcmlquantity = tcmlquantity;
        }

        public double getTcmltotal()
        {
                return tcmltotal;
        }

        public void setTcmltotal(double tcmltotal)
        {
                this.tcmltotal = tcmltotal;
        }

        public String getTcmlphone()
        {
                return tcmlphone;
        }

        public void setTcmlphone(String tcmlphone)
        {
                this.tcmlphone = tcmlphone;
        }

        public String getTcmloperator()
        {
                return tcmloperator;
        }

        public void setTcmloperator(String tcmloperator)
        {
                this.tcmloperator = tcmloperator;
        }

        public String getRemark1()
        {
                return remark1;
        }

        public void setRemark1(String remark1)
        {
                this.remark1 = remark1;
        }

        public String getRemark2()
        {
                return remark2;
        }

        public void setRemark2(String remark2)
        {
                this.remark2 = remark2;
        }

        public String getRemark3()
        {
                return remark3;
        }

        public void setRemark3(String remark3)
        {
                this.remark3 = remark3;
        }

        public String getRemark4()
        {
                return remark4;
        }

        public void setRemark4(String remark4)
        {
                this.remark4 = remark4;
        }

        public String getRemark5()
        {
                return remark5;
        }

        public void setRemark5(String remark5)
        {
                this.remark5 = remark5;
        }
}
