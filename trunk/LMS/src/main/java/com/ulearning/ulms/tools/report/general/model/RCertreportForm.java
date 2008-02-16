/**
 * RCertreportForm.java.
 * User: liz  Date: 2006-4-30
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.tools.report.general.model;

import org.apache.struts.action.ActionForm;

public class RCertreportForm extends ActionForm
{

        /**
         * identifier field
         */
        private int certrid;

        /**
         * nullable persistent field ����
         */
        private String certrname;

        /**
         * nullable persistent field ������λ
         */
        private String certrwork;

        /**
         * nullable persistent field ���
         */
        private double certrsum;

        /**
         * nullable persistent field �Ƿ�ɷ�  0����1����
         */
        private int certrpaymark;

        /**
         * nullable persistent field �ɷѽ��
         */
        private double certrpaysum;

        /**
         * nullable persistent field �Ƿ񿼲� 0����1����
         */
        private int certrseemark;

        /**
         * nullable persistent field Ԥ���ʽ 1���ֽ�2��֧Ʊ3������
         */
        private int certrprepaymode;

        /**
         * nullable persistent field Ԥ������
         */
        private double certrprepaysum;

        /**
         * nullable persistent field Ԥ�����Ƿ񿪾߷�Ʊ 0��δ��1���ѿ�
         */
        private int certrinvomark;

        /**
         * nullable persistent field �ʱ�
         */
        private String certrposcode;

        /**
         * nullable persistent field ʡ��
         */
        private String certrprovince;

        /**
         * nullable persistent field ��ע
         */
        private String certrmemo;

        /**
         * nullable persistent field ��ѵ������
         */
        private String remark1;

        /**
         * nullable persistent field ����2
         */
        private String remark2;

        /**
         * nullable persistent field ����3
         */
        private String remark3;
        //������Ϣ


        public RCertreportModel getMod()
        {
                RCertreportModel mod = new RCertreportModel();

                mod.setCertrid(certrid);
                mod.setCertrinvomark(certrinvomark);
                mod.setCertrmemo(certrmemo);
                mod.setCertrname(certrname);
                mod.setCertrpaymark(certrpaymark);
                mod.setCertrpaysum(certrpaysum);
                mod.setCertrposcode(certrposcode);
                mod.setCertrprepaymode(certrprepaymode);
                mod.setCertrprepaysum(certrprepaysum);
                mod.setCertrprovince(certrprovince);
                mod.setCertrseemark(certrseemark);
                mod.setCertrsum(certrsum);
                mod.setCertrwork(certrwork);
                mod.setRemark1(remark1);
                mod.setRemark2(remark2);
                mod.setRemark3(remark3);
                return mod;
        }

        public int getCertrid()
        {
                return certrid;
        }

        public void setCertrid(int certrid)
        {
                this.certrid = certrid;
        }

        public String getCertrname()
        {
                return certrname;
        }

        public void setCertrname(String certrname)
        {
                this.certrname = certrname;
        }

        public String getCertrwork()
        {
                return certrwork;
        }

        public void setCertrwork(String certrwork)
        {
                this.certrwork = certrwork;
        }

        public double getCertrsum()
        {
                return certrsum;
        }

        public void setCertrsum(double certrsum)
        {
                this.certrsum = certrsum;
        }

        public int getCertrpaymark()
        {
                return certrpaymark;
        }

        public void setCertrpaymark(int certrpaymark)
        {
                this.certrpaymark = certrpaymark;
        }

        public double getCertrpaysum()
        {
                return certrpaysum;
        }

        public void setCertrpaysum(double certrpaysum)
        {
                this.certrpaysum = certrpaysum;
        }

        public int getCertrseemark()
        {
                return certrseemark;
        }

        public void setCertrseemark(int certrseemark)
        {
                this.certrseemark = certrseemark;
        }

        public int getCertrprepaymode()
        {
                return certrprepaymode;
        }

        public void setCertrprepaymode(int certrprepaymode)
        {
                this.certrprepaymode = certrprepaymode;
        }

        public double getCertrprepaysum()
        {
                return certrprepaysum;
        }

        public void setCertrprepaysum(double certrprepaysum)
        {
                this.certrprepaysum = certrprepaysum;
        }

        public int getCertrinvomark()
        {
                return certrinvomark;
        }

        public void setCertrinvomark(int certrinvomark)
        {
                this.certrinvomark = certrinvomark;
        }

        public String getCertrposcode()
        {
                return certrposcode;
        }

        public void setCertrposcode(String certrposcode)
        {
                this.certrposcode = certrposcode;
        }

        public String getCertrprovince()
        {
                return certrprovince;
        }

        public void setCertrprovince(String certrprovince)
        {
                this.certrprovince = certrprovince;
        }

        public String getCertrmemo()
        {
                return certrmemo;
        }

        public void setCertrmemo(String certrmemo)
        {
                this.certrmemo = certrmemo;
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
}
