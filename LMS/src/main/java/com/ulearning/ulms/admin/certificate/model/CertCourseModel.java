/**
 * Created by IntelliJ IDEA.
 * User: zengwj
 * Date: 2004-9-4
 * Time: 10:49:49
 * To change this template use File | Settings | File Templates.
 */
package com.ulearning.ulms.admin.certificate.model;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import java.io.Serializable;


public class CertCourseModel implements Serializable
{
        /**
         * identifier field
         */
        private CertCoursePK comp_id;

        /**
         * nullable persistent field
         */
        private double credit;

        /**
         * nullable persistent field
         */
        private int courseType;

        /**
         * nullable persistent field
         */
        private int sequence;

        /**
         * nullable persistent field
         */
        private String available;

        /**
         * nullable persistent field
         */
        private double period;

        /**
         * full constructor
         */
        public CertCourseModel(CertCoursePK comp_id, double credit, int courseType,
                               int sequence, String available, double period)
        {
                this.comp_id = comp_id;
                this.credit = credit;
                this.courseType = courseType;
                this.sequence = sequence;
                this.available = available;
                this.period = period;
        }

        /**
         * default constructor
         */
        public CertCourseModel()
        {
        }

        /**
         * minimal constructor
         */
        public CertCourseModel(CertCoursePK comp_id)
        {
                this.comp_id = comp_id;
        }

        public CertCoursePK getComp_id()
        {
                return this.comp_id;
        }

        public void setComp_id(CertCoursePK comp_id)
        {
                this.comp_id = comp_id;
        }

        public double getCredit()
        {
                return this.credit;
        }

        public void setCredit(double credit)
        {
                this.credit = credit;
        }

        public int getCourseType()
        {
                return this.courseType;
        }

        public void setCourseType(int courseType)
        {
                this.courseType = courseType;
        }

        public int getSequence()
        {
                return this.sequence;
        }

        public void setSequence(int sequence)
        {
                this.sequence = sequence;
        }

        public String getAvailable()
        {
                return this.available;
        }

        public void setAvailable(String available)
        {
                this.available = available;
        }

        public double getPeriod()
        {
                return this.period;
        }

        public void setPeriod(double period)
        {
                this.period = period;
        }

        public String toString()
        {
                return new ToStringBuilder(this).append("comp_id", getComp_id())
                        .toString();
        }

        public boolean equals(Object other)
        {
                if ((this == other))
                {
                        return true;
                }

                if (!(other instanceof CertCourseModel))
                {
                        return false;
                }

                CertCourseModel castOther = (CertCourseModel) other;

                return new EqualsBuilder().append(this.getComp_id(),
                        castOther.getComp_id()).isEquals();
        }

        public int hashCode()
        {
                return new HashCodeBuilder().append(getComp_id()).toHashCode();
        }
}
