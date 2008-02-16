package com.ulearning.ulms.tools.report.general.model;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import java.io.Serializable;

/**
 * @author Hibernate CodeGenerator
 */
public class RCertreportModel implements Serializable
{

        /**
         * identifier field
         */
        private int certrid;

        /**
         * nullable persistent field
         */
        private String certrname;

        /**
         * nullable persistent field
         */
        private String certrwork;

        /**
         * nullable persistent field
         */
        private double certrsum;

        /**
         * nullable persistent field
         */
        private int certrpaymark;

        /**
         * nullable persistent field
         */
        private double certrpaysum;

        /**
         * nullable persistent field
         */
        private int certrseemark;

        /**
         * nullable persistent field
         */
        private int certrprepaymode;

        /**
         * nullable persistent field
         */
        private double certrprepaysum;

        /**
         * nullable persistent field
         */
        private int certrinvomark;

        /**
         * nullable persistent field
         */
        private String certrposcode;

        /**
         * nullable persistent field
         */
        private String certrprovince;

        /**
         * nullable persistent field
         */
        private String certrmemo;

        /**
         * nullable persistent field  ≈‡—µ∞‡√˚≥∆
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

        public RCertreportForm getFrm()
        {
                RCertreportForm frm = new RCertreportForm();
                frm.setCertrid(certrid);
                frm.setCertrinvomark(certrinvomark);
                frm.setCertrmemo(certrmemo);
                frm.setCertrname(certrname);
                frm.setCertrpaymark(certrpaymark);
                frm.setCertrpaysum(certrpaysum);
                frm.setCertrposcode(certrposcode);
                frm.setCertrprepaymode(certrprepaymode);
                frm.setCertrprepaysum(certrprepaysum);
                frm.setCertrprovince(certrprovince);
                frm.setCertrseemark(certrseemark);
                frm.setCertrsum(certrsum);
                frm.setCertrwork(certrwork);
                frm.setRemark1(remark1);
                frm.setRemark2(remark2);
                frm.setRemark3(remark3);
                return frm;
        }

        /**
         * full constructor
         */
        public RCertreportModel(String certrname, String certrwork, double certrsum, int certrpaymark, double certrpaysum, int certrseemark, int certrprepaymode, double certrprepaysum, int certrinvomark, String certrposcode, String certrprovince, String certrmemo, String remark1, String remark2, String remark3)
        {
                this.certrname = certrname;
                this.certrwork = certrwork;
                this.certrsum = certrsum;
                this.certrpaymark = certrpaymark;
                this.certrpaysum = certrpaysum;
                this.certrseemark = certrseemark;
                this.certrprepaymode = certrprepaymode;
                this.certrprepaysum = certrprepaysum;
                this.certrinvomark = certrinvomark;
                this.certrposcode = certrposcode;
                this.certrprovince = certrprovince;
                this.certrmemo = certrmemo;
                this.remark1 = remark1;
                this.remark2 = remark2;
                this.remark3 = remark3;
        }

        /**
         * default constructor
         */
        public RCertreportModel()
        {
        }

        public int getCertrid()
        {
                return this.certrid;
        }

        public void setCertrid(int certrid)
        {
                this.certrid = certrid;
        }

        public String getCertrname()
        {
                return this.certrname;
        }

        public void setCertrname(String certrname)
        {
                this.certrname = certrname;
        }

        public String getCertrwork()
        {
                return this.certrwork;
        }

        public void setCertrwork(String certrwork)
        {
                this.certrwork = certrwork;
        }

        public double getCertrsum()
        {
                return this.certrsum;
        }

        public void setCertrsum(double certrsum)
        {
                this.certrsum = certrsum;
        }

        public int getCertrpaymark()
        {
                return this.certrpaymark;
        }

        public void setCertrpaymark(int certrpaymark)
        {
                this.certrpaymark = certrpaymark;
        }

        public double getCertrpaysum()
        {
                return this.certrpaysum;
        }

        public void setCertrpaysum(double certrpaysum)
        {
                this.certrpaysum = certrpaysum;
        }

        public int getCertrseemark()
        {
                return this.certrseemark;
        }

        public void setCertrseemark(int certrseemark)
        {
                this.certrseemark = certrseemark;
        }

        public int getCertrprepaymode()
        {
                return this.certrprepaymode;
        }

        public void setCertrprepaymode(int certrprepaymode)
        {
                this.certrprepaymode = certrprepaymode;
        }

        public double getCertrprepaysum()
        {
                return this.certrprepaysum;
        }

        public void setCertrprepaysum(double certrprepaysum)
        {
                this.certrprepaysum = certrprepaysum;
        }

        public int getCertrinvomark()
        {
                return this.certrinvomark;
        }

        public void setCertrinvomark(int certrinvomark)
        {
                this.certrinvomark = certrinvomark;
        }

        public String getCertrposcode()
        {
                return this.certrposcode;
        }

        public void setCertrposcode(String certrposcode)
        {
                this.certrposcode = certrposcode;
        }

        public String getCertrprovince()
        {
                return this.certrprovince;
        }

        public void setCertrprovince(String certrprovince)
        {
                this.certrprovince = certrprovince;
        }

        public String getCertrmemo()
        {
                return this.certrmemo;
        }

        public void setCertrmemo(String certrmemo)
        {
                this.certrmemo = certrmemo;
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

        public String toString()
        {
                return new ToStringBuilder(this)
                        .append("certrid", getCertrid())
                        .toString();
        }

        public boolean equals(Object other)
        {
                if (!(other instanceof RCertreportModel))
                {
                        return false;
                }
                RCertreportModel castOther = (RCertreportModel) other;
                return new EqualsBuilder()
                        .append(this.getCertrid(), castOther.getCertrid())
                        .isEquals();
        }

        public int hashCode()
        {
                return new HashCodeBuilder()
                        .append(getCertrid())
                        .toHashCode();
        }

}
