package com.ulearning.ulms.tools.report.general.model;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import java.io.Serializable;

/**
 * @author Hibernate CodeGenerator
 */
public class RChargeitemModel implements Serializable
{

        /**
         * identifier field
         */
        private int chargeid;

        /**
         * nullable persistent field
         */
        private String chargename;

        /**
         * nullable persistent field
         */
        private String chargework;

        /**
         * nullable persistent field
         */
        private int chargesendmode = 0;

        /**
         * nullable persistent field
         */
        private double chargesum = 0;

        /**
         * nullable persistent field
         */
        private int chargecircs = 0;

        /**
         * nullable persistent field
         */
        private int chargeinvomark = 0;

        /**
         * nullable persistent field
         */
        private int chargeposmark = 0;

        /**
         * nullable persistent field
         */
        private String chargephone;

        /**
         * nullable persistent field
         */
        private String chargeposcode;

        /**
         * nullable persistent field
         */
        private String chargeprovince;

        /**
         * nullable persistent field
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

        /**
         * full constructor
         */
        public RChargeitemModel(String chargename, String chargework, int chargesendmode, double chargesum, int chargecircs, int chargeinvomark, int chargeposmark, String chargephone, String chargeposcode, String chargeprovince, String chargememo, String remark1, String remark2, String remark3)
        {
                this.chargename = chargename;
                this.chargework = chargework;
                this.chargesendmode = chargesendmode;
                this.chargesum = chargesum;
                this.chargecircs = chargecircs;
                this.chargeinvomark = chargeinvomark;
                this.chargeposmark = chargeposmark;
                this.chargephone = chargephone;
                this.chargeposcode = chargeposcode;
                this.chargeprovince = chargeprovince;
                this.chargememo = chargememo;
                this.remark1 = remark1;
                this.remark2 = remark2;
                this.remark3 = remark3;
        }

        public RChargeitemForm getFrm(RChargeitemModel mod)
        {
                RChargeitemForm frm = new RChargeitemForm();
                frm.setChargecircs(mod.getChargecircs());
                frm.setChargeid(mod.getChargeid());
                frm.setChargeinvomark(mod.getChargeinvomark());
                frm.setChargememo(mod.getChargememo());
                frm.setChargename(mod.getChargename());
                frm.setChargephone(mod.getChargephone());
                frm.setChargeposcode(mod.getChargeposcode());
                frm.setChargeposmark(mod.getChargeposmark());
                frm.setChargeprovince(mod.getChargeprovince());
                frm.setChargesendmode(mod.getChargesendmode());
                frm.setChargesum(mod.getChargesum());
                frm.setChargework(mod.getChargework());
                frm.setRemark1(mod.getRemark1());
                frm.setRemark2(mod.getRemark2());
                frm.setRemark3(mod.getRemark3());
                return frm;

        }

        public RChargeitemModel()
        {

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

        public String toString()
        {
                return new ToStringBuilder(this)
                        .append("chargeid", getChargeid())
                        .toString();
        }

        public boolean equals(Object other)
        {
                if (!(other instanceof RChargeitemModel))
                {
                        return false;
                }
                RChargeitemModel castOther = (RChargeitemModel) other;
                return new EqualsBuilder()
                        .append(this.getChargeid(), castOther.getChargeid())
                        .isEquals();
        }

        public int hashCode()
        {
                return new HashCodeBuilder()
                        .append(getChargeid())
                        .toHashCode();
        }
}
