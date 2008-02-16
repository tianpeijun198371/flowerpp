/**
 * RChargeitemForm.java.
 * User: liz  Date: 2006-4-29
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.tools.report.general.model;

import org.apache.struts.action.ActionForm;

public class RChargeitemForm extends ActionForm
{
        /**
         * identifier field
         */
        private int chargeid;

        /**
         * nullable persistent field ����
         */
        private String chargename;

        /**
         * nullable persistent field ������λ
         */
        private String chargework;

        /**
         * nullable persistent field ��ʽ 0���ʾ� 1������ 2������
         */
        private int chargesendmode = 0;

        /**
         * nullable persistent field �����
         */
        private double chargesum = 0;

        /**
         * nullable persistent field ������� 0��δ���� 1���ѵ���
         */
        private int chargecircs = 0;

        /**
         * nullable persistent field �Ƿ��ѿ���Ʊ0��δ�� 1���ѿ�
         */
        private int chargeinvomark = 0;

        /**
         * nullable persistent field �Ƿ񷢷����� 0��δ���� 1���ѷ���
         */
        private int chargeposmark = 0;

        /**
         * nullable persistent field ��ϵ��ʽ
         */
        private String chargephone;

        /**
         * nullable persistent field �ʱ�
         */
        private String chargeposcode;

        /**
         * nullable persistent field ʡ��
         */
        private String chargeprovince;

        /**
         * nullable persistent field ��ע
         */
        private String chargememo;

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

        public RChargeitemModel getModel()
        {
                RChargeitemModel mod = new RChargeitemModel();
                mod.setChargecircs(chargecircs);
                mod.setChargeid(chargeid);
                mod.setChargeinvomark(chargeinvomark);
                mod.setChargememo(chargememo);
                mod.setChargename(chargename);
                mod.setChargephone(chargephone);
                mod.setChargeposcode(chargeposcode);
                mod.setChargeposmark(chargeposmark);
                mod.setChargeprovince(chargeprovince);
                mod.setChargesendmode(chargesendmode);
                mod.setChargesum(chargesum);
                mod.setChargework(chargework);
                mod.setRemark1(remark1);
                mod.setRemark2(remark2);
                mod.setRemark3(remark3);
                return mod;

        }

        public int getChargeid()
        {
                return chargeid;
        }

        public void setChargeid(int chargeid)
        {
                this.chargeid = chargeid;
        }

        public String getChargename()
        {
                return chargename;
        }

        public void setChargename(String chargename)
        {
                this.chargename = chargename;
        }

        public String getChargework()
        {
                return chargework;
        }

        public void setChargework(String chargework)
        {
                this.chargework = chargework;
        }

        public int getChargesendmode()
        {
                return chargesendmode;
        }

        public void setChargesendmode(int chargesendmode)
        {
                this.chargesendmode = chargesendmode;
        }

        public double getChargesum()
        {
                return chargesum;
        }

        public void setChargesum(double chargesum)
        {
                this.chargesum = chargesum;
        }

        public int getChargecircs()
        {
                return chargecircs;
        }

        public void setChargecircs(int chargecircs)
        {
                this.chargecircs = chargecircs;
        }

        public int getChargeinvomark()
        {
                return chargeinvomark;
        }

        public void setChargeinvomark(int chargeinvomark)
        {
                this.chargeinvomark = chargeinvomark;
        }

        public int getChargeposmark()
        {
                return chargeposmark;
        }

        public void setChargeposmark(int chargeposmark)
        {
                this.chargeposmark = chargeposmark;
        }

        public String getChargephone()
        {
                return chargephone;
        }

        public void setChargephone(String chargephone)
        {
                this.chargephone = chargephone;
        }

        public String getChargeposcode()
        {
                return chargeposcode;
        }

        public void setChargeposcode(String chargeposcode)
        {
                this.chargeposcode = chargeposcode;
        }

        public String getChargeprovince()
        {
                return chargeprovince;
        }

        public void setChargeprovince(String chargeprovince)
        {
                this.chargeprovince = chargeprovince;
        }

        public String getChargememo()
        {
                return chargememo;
        }

        public void setChargememo(String chargememo)
        {
                this.chargememo = chargememo;
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
