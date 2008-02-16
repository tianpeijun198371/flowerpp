/**
 * MTeachmailbillmainform.java.
 * User: liz  Date: 2006-5-22
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.content.schoolbook.form;

import com.ulearning.ulms.content.schoolbook.model.MTeachmailbillmainTab;

import org.apache.struts.action.ActionForm;

import java.util.Date;


public class MTeachmailbillmainform extends ActionForm
{
        /**
         * identifier field
         */
        private int tcmlid;

        /**
         * ������λ
         */
        private String tcmllevydept;

        /**
         * ������λ��ϸ��ַ
         */
        private String tcmllevyadds;

        /**
         * ����������
         */
        private String tcmllevyoperator;

        /**
         * �ϼƽ��
         */
        private double tcmltotal = 0;

        /**
         * �����绰
         */
        private String tcmllevyphone;

        /**
         * ��������
         */
        private String tcmllevyfax;

        /**
         * �����ʱ�
         */
        private String tcmllevymailcode;

        /**
         * ������ʽ 1������   2���ʾ�
         */
        private int tcmllevyrem = 0;

        /**
         * ��ϵ��
         */
        private String tcmlman;

        /**
         * ��ϵ�绰
         */
        private String tcmlphone;

        /**
         * ����
         */
        private String tcmlfax;

        /**
         * �ʾ��տ���
         */
        private String tcmlpostman;

        /**
         * �ʾֵ�ַ
         */
        private String tcmlpostadds;

        /**
         * �ʾ��ʱ�
         */
        private String tcmlpostcode;

        /**
         * �����տλ
         */
        private String tcmlbankcom;

        /**
         * ������
         */
        private String tcmlbankname;

        /**
         * �ʺ�
         */
        private String tcmlbankaccount;

        /**
         * ������
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

        public MTeachmailbillmainTab getMod()
        {
                MTeachmailbillmainTab mod = new MTeachmailbillmainTab();

                mod.setRemark1(remark1);
                mod.setRemark2(remark2);
                mod.setRemark3(remark3);
                mod.setRemark4(remark4);
                mod.setRemark5(remark5);
                mod.setTcmlbankaccount(tcmlbankaccount);
                mod.setTcmlbankcom(tcmlbankcom);
                mod.setTcmlbankname(tcmlbankname);
                mod.setTcmldate(tcmldate);
                mod.setTcmlfax(tcmlfax);
                mod.setTcmlid(tcmlid);
                mod.setTcmllevyadds(tcmllevyadds);

                mod.setTcmllevydept(tcmllevydept);
                mod.setTcmllevyfax(tcmllevyfax);
                mod.setTcmllevymailcode(tcmllevymailcode);
                mod.setTcmllevyoperator(tcmllevyoperator);
                mod.setTcmllevyphone(tcmllevyphone);
                mod.setTcmllevyrem(tcmllevyrem);
                mod.setTcmlman(tcmlman);
                mod.setTcmlphone(tcmlphone);
                mod.setTcmlpostadds(tcmlpostadds);
                mod.setTcmlpostcode(tcmlpostcode);
                mod.setTcmlpostman(tcmlpostman);
                mod.setTcmltotal(tcmltotal);

                return mod;
        }

        public int getTcmlid()
        {
                return tcmlid;
        }

        public void setTcmlid(int tcmlid)
        {
                this.tcmlid = tcmlid;
        }

        public String getTcmllevydept()
        {
                return tcmllevydept;
        }

        public void setTcmllevydept(String tcmllevydept)
        {
                this.tcmllevydept = tcmllevydept;
        }

        public String getTcmllevyadds()
        {
                return tcmllevyadds;
        }

        public void setTcmllevyadds(String tcmllevyadds)
        {
                this.tcmllevyadds = tcmllevyadds;
        }

        public String getTcmllevyoperator()
        {
                return tcmllevyoperator;
        }

        public void setTcmllevyoperator(String tcmllevyoperator)
        {
                this.tcmllevyoperator = tcmllevyoperator;
        }

        public double getTcmltotal()
        {
                return tcmltotal;
        }

        public void setTcmltotal(double tcmltotal)
        {
                this.tcmltotal = tcmltotal;
        }

        public String getTcmllevyphone()
        {
                return tcmllevyphone;
        }

        public void setTcmllevyphone(String tcmllevyphone)
        {
                this.tcmllevyphone = tcmllevyphone;
        }

        public String getTcmllevyfax()
        {
                return tcmllevyfax;
        }

        public void setTcmllevyfax(String tcmllevyfax)
        {
                this.tcmllevyfax = tcmllevyfax;
        }

        public String getTcmllevymailcode()
        {
                return tcmllevymailcode;
        }

        public void setTcmllevymailcode(String tcmllevymailcode)
        {
                this.tcmllevymailcode = tcmllevymailcode;
        }

        public int getTcmllevyrem()
        {
                return tcmllevyrem;
        }

        public void setTcmllevyrem(int tcmllevyrem)
        {
                this.tcmllevyrem = tcmllevyrem;
        }

        public String getTcmlman()
        {
                return tcmlman;
        }

        public void setTcmlman(String tcmlman)
        {
                this.tcmlman = tcmlman;
        }

        public String getTcmlphone()
        {
                return tcmlphone;
        }

        public void setTcmlphone(String tcmlphone)
        {
                this.tcmlphone = tcmlphone;
        }

        public String getTcmlfax()
        {
                return tcmlfax;
        }

        public void setTcmlfax(String tcmlfax)
        {
                this.tcmlfax = tcmlfax;
        }

        public String getTcmlpostman()
        {
                return tcmlpostman;
        }

        public void setTcmlpostman(String tcmlpostman)
        {
                this.tcmlpostman = tcmlpostman;
        }

        public String getTcmlpostadds()
        {
                return tcmlpostadds;
        }

        public void setTcmlpostadds(String tcmlpostadds)
        {
                this.tcmlpostadds = tcmlpostadds;
        }

        public String getTcmlpostcode()
        {
                return tcmlpostcode;
        }

        public void setTcmlpostcode(String tcmlpostcode)
        {
                this.tcmlpostcode = tcmlpostcode;
        }

        public String getTcmlbankcom()
        {
                return tcmlbankcom;
        }

        public void setTcmlbankcom(String tcmlbankcom)
        {
                this.tcmlbankcom = tcmlbankcom;
        }

        public String getTcmlbankname()
        {
                return tcmlbankname;
        }

        public void setTcmlbankname(String tcmlbankname)
        {
                this.tcmlbankname = tcmlbankname;
        }

        public String getTcmlbankaccount()
        {
                return tcmlbankaccount;
        }

        public void setTcmlbankaccount(String tcmlbankaccount)
        {
                this.tcmlbankaccount = tcmlbankaccount;
        }

        public Date getTcmldate()
        {
                return tcmldate;
        }

        public void setTcmldate(Date tcmldate)
        {
                this.tcmldate = tcmldate;
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
