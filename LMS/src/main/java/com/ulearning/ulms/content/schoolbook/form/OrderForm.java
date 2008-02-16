package com.ulearning.ulms.content.schoolbook.form;

import com.ulearning.ulms.content.schoolbook.model.MTeachmailbillTab;

import org.apache.struts.action.ActionForm;

/**
 * 入库明晰
 */
import java.util.Date;


public class OrderForm extends ActionForm
{
        //征订单ID
        private int tcmlid = 0;

        //教材ID
        private int bsifid = 0;

        //教材名称
        private String bsifbookname = "";

        //邮购日期
        private String remark1 = "";

        //征订价
        private double tcmlmailprice = 0.00;

        //合计金额
        private double tcmltotal = 0.00;

        //征订单位
        private String tcmldept = "";

        //联系人
        private String tcmlman = "";

        //办理人
        private String tcmloperator = "";

        //征订数量
        private int tcmlquantity = 0;

        //联系电话
        private String tcmlphone = "";
        private String remark2 = "";
        private String remark3 = "";
        private String remark4 = "";
        private String remark5 = "";
        private String compay = ""; //出版社
        private String author = ""; //作者

        public MTeachmailbillTab getMod()
        {
                MTeachmailbillTab mod = new MTeachmailbillTab();
                mod.setTcmlid(tcmlid);
                mod.setBsifid(bsifid);
                mod.setBsifbookname(bsifbookname);
                mod.setTcmlmailprice(tcmlmailprice);
                mod.setTcmltotal(tcmltotal);
                mod.setTcmldept(tcmldept);
                mod.setTcmlman(tcmlman);
                mod.setTcmloperator(tcmloperator);
                mod.setTcmlquantity(tcmlquantity);
                mod.setTcmlphone(tcmlphone);
                mod.setRemark1(remark1);
                mod.setRemark2(remark2);
                mod.setRemark3(remark3);
                mod.setRemark4(remark4);
                mod.setRemark5(remark5);

                return mod;
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

        public int getBsifid()
        {
                return bsifid;
        }

        public void setBsifid(int bsifid)
        {
                this.bsifid = bsifid;
        }

        public int getTcmlid()
        {
                return tcmlid;
        }

        public void setTcmlid(int tcmlid)
        {
                this.tcmlid = tcmlid;
        }

        public String getBsifbookname()
        {
                return bsifbookname;
        }

        public void setBsifbookname(String bsifbookname)
        {
                this.bsifbookname = bsifbookname;
        }

        public String getRemark1()
        {
                return remark1;
        }

        public void setRemark1(String remark1)
        {
                this.remark1 = remark1;
        }

        public double getTcmlmailprice()
        {
                return tcmlmailprice;
        }

        public void setTcmlmailprice(double tcmlmailprice)
        {
                this.tcmlmailprice = tcmlmailprice;
        }

        public double getTcmltotal()
        {
                return tcmltotal;
        }

        public void setTcmltotal(double tcmltotal)
        {
                this.tcmltotal = tcmltotal;
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

        public String getTcmloperator()
        {
                return tcmloperator;
        }

        public void setTcmloperator(String tcmloperator)
        {
                this.tcmloperator = tcmloperator;
        }

        public int getTcmlquantity()
        {
                return tcmlquantity;
        }

        public void setTcmlquantity(int tcmlquantity)
        {
                this.tcmlquantity = tcmlquantity;
        }

        public String getTcmlphone()
        {
                return tcmlphone;
        }

        public void setTcmlphone(String tcmlphone)
        {
                this.tcmlphone = tcmlphone;
        }

        public String getCompay()
        {
                return compay;
        }

        public void setCompay(String compay)
        {
                this.compay = compay;
        }

        public String getAuthor()
        {
                return author;
        }

        public void setAuthor(String author)
        {
                this.author = author;
        }
}
