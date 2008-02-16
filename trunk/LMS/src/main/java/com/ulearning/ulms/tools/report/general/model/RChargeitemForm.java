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
         * nullable persistent field 姓名
         */
        private String chargename;

        /**
         * nullable persistent field 工作单位
         */
        private String chargework;

        /**
         * nullable persistent field 汇款方式 0：邮局 1：银行 2：其它
         */
        private int chargesendmode = 0;

        /**
         * nullable persistent field 汇款金额
         */
        private double chargesum = 0;

        /**
         * nullable persistent field 汇款到帐情况 0：未到帐 1：已到帐
         */
        private int chargecircs = 0;

        /**
         * nullable persistent field 是否已开发票0：未开 1：已开
         */
        private int chargeinvomark = 0;

        /**
         * nullable persistent field 是否发放资料 0：未发放 1：已发放
         */
        private int chargeposmark = 0;

        /**
         * nullable persistent field 联系方式
         */
        private String chargephone;

        /**
         * nullable persistent field 邮编
         */
        private String chargeposcode;

        /**
         * nullable persistent field 省份
         */
        private String chargeprovince;

        /**
         * nullable persistent field 备注
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
