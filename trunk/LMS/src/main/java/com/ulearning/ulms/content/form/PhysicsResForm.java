/**
 * PhysicsResForm.java.
 * User: liz  Date: 2006-2-17
 * ��Դ�ֵ�form��
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.content.form;

import com.ulearning.ulms.content.model.RPhysicsresModel;

import org.apache.struts.action.ActionForm;


public class PhysicsResForm extends ActionForm
{
        /**
         * ����
         */
        private int resid = 0;

        /**
         * ����ASPID
         */
        private int aspid = 0;

        /**
         * ����ORGID
         */
        private int orgid = 0;

        /**
         * ��Դ���
         */
        private String rescode;

        /**
         * ��Դ����
         */
        private String resname;

        /**
         * ��Դ��ģ����
         */
        private String ressize;

        /**
         * �豸�������
         */
        private String resfix;

        /**
         * ��Դ״̬
         * 0������  1��������
         */
        private int resstate = 0;

        /**
         * ������Ϣ
         */
        private String resother;

        /**
         * ��Դ����
         * 0��û���� 1������ 2��������
         */
        private int restype = 0;
        private String resmanagename; //��Դ����Ա����
        private int resmanageid = 0; //��Դ����ԱID

        /**
         * nullable persistent field
         */
        private String resremark1;

        /**
         * nullable persistent field
         */
        private String resremark2;

        /**
         * nullable persistent field
         */
        private String resremark3;

        //������ʾ
        private String restypename; //��Դ�������ơ�
        private String orgname; //��������
        private String resstatename; //��ʩ״̬����
        private RPhysicsresModel mod = null;

        public PhysicsResForm()
        {
        }

        public PhysicsResForm(RPhysicsresModel mod)
        {
                this.aspid = mod.getAspid();
                this.orgid = mod.getOrgid();
                this.rescode = mod.getRescode();
                this.resfix = mod.getResfix();
                this.resid = mod.getResId();
                this.resname = mod.getResname();
                this.resother = mod.getResother();
                this.resmanageid = mod.getResmanageid();
                this.resmanagename = mod.getResmanagename();
                this.resremark1 = mod.getResremark1();
                this.resremark2 = mod.getResremark2();
                this.resremark3 = mod.getResremark3();
                this.ressize = mod.getRessize();
                this.resstate = mod.getResstate();
                this.restype = mod.getRestype();
        }

        public RPhysicsresModel makeModel()
        {
                mod = new RPhysicsresModel();
                mod.setAspid(this.getAspid());
                mod.setResId(this.getResid());
                mod.setOrgid(this.getOrgid());
                mod.setRescode(this.getRescode());
                mod.setResfix(this.getResfix());
                mod.setResname(this.getResname());
                mod.setResother(this.getResother());
                mod.setResmanageid(this.getResmanageid());
                mod.setResmanagename(this.getResmanagename());
                mod.setResremark1(this.getResremark1());
                mod.setResremark2(this.getResremark2());
                mod.setResremark3(this.getResremark3());
                mod.setRessize(this.getRessize());
                mod.setResstate(this.getResstate());
                mod.setRestype(this.getRestype());

                return mod;
        }

        public int getAspid()
        {
                return aspid;
        }

        public void setAspid(int aspid)
        {
                this.aspid = aspid;
        }

        public String getResstatename()
        {
                return resstatename;
        }

        public void setResstatename(String resstatename)
        {
                this.resstatename = resstatename;
        }

        public String getOrgname()
        {
                return orgname;
        }

        public void setOrgname(String orgname)
        {
                this.orgname = orgname;
        }

        public String getRestypename()
        {
                return restypename;
        }

        public void setRestypename(String restypename)
        {
                this.restypename = restypename;
        }

        public int getOrgid()
        {
                return orgid;
        }

        public void setOrgid(int orgid)
        {
                this.orgid = orgid;
        }

        public String getRescode()
        {
                return rescode;
        }

        public void setRescode(String rescode)
        {
                this.rescode = rescode;
        }

        public String getResname()
        {
                return resname;
        }

        public void setResname(String resname)
        {
                this.resname = resname;
        }

        public String getRessize()
        {
                return ressize;
        }

        public void setRessize(String ressize)
        {
                this.ressize = ressize;
        }

        public String getResfix()
        {
                return resfix;
        }

        public void setResfix(String resfix)
        {
                this.resfix = resfix;
        }

        public int getResstate()
        {
                return resstate;
        }

        public void setResstate(int resstate)
        {
                this.resstate = resstate;
        }

        public String getResother()
        {
                return resother;
        }

        public void setResother(String resother)
        {
                this.resother = resother;
        }

        public int getRestype()
        {
                return restype;
        }

        public void setRestype(int restype)
        {
                this.restype = restype;
        }

        public String getResmanagename()
        {
                return resmanagename;
        }

        public void setResmanagename(String resmanagename)
        {
                this.resmanagename = resmanagename;
        }

        public int getResmanageid()
        {
                return resmanageid;
        }

        public void setResmanageid(int resmanageid)
        {
                this.resmanageid = resmanageid;
        }

        public String getResremark1()
        {
                return resremark1;
        }

        public void setResremark1(String resremark1)
        {
                this.resremark1 = resremark1;
        }

        public String getResremark2()
        {
                return resremark2;
        }

        public void setResremark2(String resremark2)
        {
                this.resremark2 = resremark2;
        }

        public String getResremark3()
        {
                return resremark3;
        }

        public void setResremark3(String resremark3)
        {
                this.resremark3 = resremark3;
        }

        public int getResid()
        {
                return resid;
        }

        public void setResid(int resid)
        {
                this.resid = resid;
        }
}
