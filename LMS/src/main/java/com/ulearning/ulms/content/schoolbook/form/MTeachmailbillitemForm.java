/**
 * MTeachmailbillitemForm.java.
 * User: liz  Date: 2006-5-22
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.content.schoolbook.form;

import com.ulearning.ulms.content.schoolbook.model.MTeachmailbillitemTab;

import org.apache.struts.action.ActionForm;


public class MTeachmailbillitemForm extends ActionForm
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
        private double tcmitemPrice = 0;

        /**
         * 征订数量
         */
        private int tcmitemQuantity = 0;

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

        public MTeachmailbillitemTab getMod()
        {
                MTeachmailbillitemTab mod = new MTeachmailbillitemTab();
                mod.setBsifbookname(bsifbookname);
                mod.setBsifid(bsifid);
                mod.setRemark1(remark1);
                mod.setRemark2(remark2);
                mod.setRemark3(remark3);
                mod.setRemark4(remark4);
                mod.setRemark5(remark5);
                mod.setTcmitemPrice(tcmitemPrice);
                mod.setTcmitemQuantity(tcmitemQuantity);
                mod.setTcmlid(tcmlid);
                mod.setTitemid(titemid);

                return mod;
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

        public double getTcmitemPrice()
        {
                return tcmitemPrice;
        }

        public void setTcmitemPrice(double tcmitemPrice)
        {
                this.tcmitemPrice = tcmitemPrice;
        }

        public int getTcmitemQuantity()
        {
                return tcmitemQuantity;
        }

        public void setTcmitemQuantity(int tcmitemQuantity)
        {
                this.tcmitemQuantity = tcmitemQuantity;
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
