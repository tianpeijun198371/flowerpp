package com.ulearning.ulms.admin.certificate.model;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import java.io.Serializable;

import java.util.Date;


/**
 * @author Hibernate CodeGenerator
 */
public class CertPlanModel implements Serializable
{
        /**
         * identifier field
         */
        private int cpacotid;

        /**
         * persistent field
         */
        private String cpcertname;

        /**
         * nullable persistent field
         */
        private int cpusernumber;

        /**
         * nullable persistent field
         */
        private double cpcharge;

        /**
         * nullable persistent field
         */
        private Date cpcomedate;

        /**
         * nullable persistent field
         */
        private Date cpgodate;

        /**
         * nullable persistent field
         */
        private String cptrainplace;

        /**
         * nullable persistent field
         */
        private String cprestplace;

        /**
         * nullable persistent field
         */
        private double cprestcharge;

        /**
         * nullable persistent field
         */
        private String cpfoodplace;

        /**
         * nullable persistent field
         */
        private double cpfoodcharge;

        /**
         * nullable persistent field
         */
        private String cpteacher;

        /**
         * nullable persistent field
         */
        private String cpremark;

        /**
         * nullable persistent field
         */
        private String cpremark1;

        /**
         * nullable persistent field
         */
        private String cpremark2;

        /**
         * full constructor
         */
        public CertPlanModel(String cpcertname, int cpusernumber, double cpcharge,
                             Date cpcomedate, Date cpgodate, String cptrainplace,
                             String cprestplace, double cprestcharge, String cpfoodplace,
                             double cpfoodcharge, String cpteacher, String cpremark,
                             String cpremark1, String cpremark2)
        {
                this.cpcertname = cpcertname;
                this.cpusernumber = cpusernumber;
                this.cpcharge = cpcharge;
                this.cpcomedate = cpcomedate;
                this.cpgodate = cpgodate;
                this.cptrainplace = cptrainplace;
                this.cprestplace = cprestplace;
                this.cprestcharge = cprestcharge;
                this.cpfoodplace = cpfoodplace;
                this.cpfoodcharge = cpfoodcharge;
                this.cpteacher = cpteacher;
                this.cpremark = cpremark;
                this.cpremark1 = cpremark1;
                this.cpremark2 = cpremark2;
        }

        /**
         * default constructor
         */
        public CertPlanModel()
        {
        }

        /**
         * minimal constructor
         */
        public CertPlanModel(String cpcertname)
        {
                this.cpcertname = cpcertname;
        }

        public int getCpacotid()
        {
                return this.cpacotid;
        }

        public void setCpacotid(int cpacotid)
        {
                this.cpacotid = cpacotid;
        }

        public String getCpcertname()
        {
                return this.cpcertname;
        }

        public void setCpcertname(String cpcertname)
        {
                this.cpcertname = cpcertname;
        }

        public int getCpusernumber()
        {
                return this.cpusernumber;
        }

        public void setCpusernumber(int cpusernumber)
        {
                this.cpusernumber = cpusernumber;
        }

        public double getCpcharge()
        {
                return this.cpcharge;
        }

        public void setCpcharge(double cpcharge)
        {
                this.cpcharge = cpcharge;
        }

        public Date getCpcomedate()
        {
                return this.cpcomedate;
        }

        public void setCpcomedate(Date cpcomedate)
        {
                this.cpcomedate = cpcomedate;
        }

        public Date getCpgodate()
        {
                return this.cpgodate;
        }

        public void setCpgodate(Date cpgodate)
        {
                this.cpgodate = cpgodate;
        }

        public String getCptrainplace()
        {
                return this.cptrainplace;
        }

        public void setCptrainplace(String cptrainplace)
        {
                this.cptrainplace = cptrainplace;
        }

        public String getCprestplace()
        {
                return this.cprestplace;
        }

        public void setCprestplace(String cprestplace)
        {
                this.cprestplace = cprestplace;
        }

        public double getCprestcharge()
        {
                return this.cprestcharge;
        }

        public void setCprestcharge(double cprestcharge)
        {
                this.cprestcharge = cprestcharge;
        }

        public String getCpfoodplace()
        {
                return this.cpfoodplace;
        }

        public void setCpfoodplace(String cpfoodplace)
        {
                this.cpfoodplace = cpfoodplace;
        }

        public double getCpfoodcharge()
        {
                return this.cpfoodcharge;
        }

        public void setCpfoodcharge(double cpfoodcharge)
        {
                this.cpfoodcharge = cpfoodcharge;
        }

        public String getCpteacher()
        {
                return this.cpteacher;
        }

        public void setCpteacher(String cpteacher)
        {
                this.cpteacher = cpteacher;
        }

        public String getCpremark()
        {
                return this.cpremark;
        }

        public void setCpremark(String cpremark)
        {
                this.cpremark = cpremark;
        }

        public String getCpremark1()
        {
                return this.cpremark1;
        }

        public void setCpremark1(String cpremark1)
        {
                this.cpremark1 = cpremark1;
        }

        public String getCpremark2()
        {
                return this.cpremark2;
        }

        public void setCpremark2(String cpremark2)
        {
                this.cpremark2 = cpremark2;
        }

        public String toString()
        {
                return new ToStringBuilder(this).append("resuseid", getCpacotid())
                        .toString();
        }

        public boolean equals(Object other)
        {
                if (!(other instanceof CertPlanModel))
                {
                        return false;
                }

                CertPlanModel castOther = (CertPlanModel) other;

                return new EqualsBuilder().append(this.getCpacotid(),
                        castOther.getCpacotid()).isEquals();
        }

        public int hashCode()
        {
                return new HashCodeBuilder().append(getCpacotid()).toHashCode();
        }
}
