/**
 * Created by IntelliJ IDEA.
 * User: zengwj
 * String: 2004-9-7
 * Time: 13:26:50
 * To change this template use File | Settings | File Templates.
 */
package com.ulearning.ulms.admin.graduation.model;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import java.io.Serializable;


public class GraduationModel implements Serializable
{
        /**
         * identifier field
         */
        private GraduationModelPK comp_id;

        /**
         * nullable persistent field
         */
        private double compulsoryScore;

        /**
         * nullable persistent field
         */
        private double electiveScore;

        /**
         * nullable persistent field
         */
        private String remark;

        /**
         * nullable persistent field
         */
        private String status;

        /**
         * nullable persistent field
         */
        private String certNo;

        /**
         * nullable persistent field
         */
        private String startDate;

        /**
         * nullable persistent field
         */
        private String graduateDate;

        /**
         * full constructor
         */
        public GraduationModel(GraduationModelPK comp_id, double compulsoryScore,
                               double electiveScore, String remark, String status, String certNo,
                               String startDate, String graduateDate)
        {
                this.comp_id = comp_id;
                this.compulsoryScore = compulsoryScore;
                this.electiveScore = electiveScore;
                this.remark = remark;
                this.status = status;
                this.certNo = certNo;
                this.startDate = startDate;
                this.graduateDate = graduateDate;
        }

        /**
         * default constructor
         */
        public GraduationModel()
        {
        }

        /**
         * minimal constructor
         */
        public GraduationModel(GraduationModelPK comp_id)
        {
                this.comp_id = comp_id;
        }

        public GraduationModelPK getComp_id()
        {
                return this.comp_id;
        }

        public void setComp_id(GraduationModelPK comp_id)
        {
                this.comp_id = comp_id;
        }

        public double getCompulsoryScore()
        {
                return this.compulsoryScore;
        }

        public void setCompulsoryScore(double compulsoryScore)
        {
                this.compulsoryScore = compulsoryScore;
        }

        public double getElectiveScore()
        {
                return this.electiveScore;
        }

        public void setElectiveScore(double electiveScore)
        {
                this.electiveScore = electiveScore;
        }

        public String getRemark()
        {
                return this.remark;
        }

        public void setRemark(String remark)
        {
                this.remark = remark;
        }

        public String getStatus()
        {
                return this.status;
        }

        public void setStatus(String status)
        {
                this.status = status;
        }

        public String getCertNo()
        {
                return this.certNo;
        }

        public void setCertNo(String certNo)
        {
                this.certNo = certNo;
        }

        public String getStartDate()
        {
                return this.startDate;
        }

        public void setStartDate(String startDate)
        {
                this.startDate = startDate;
        }

        public String getGraduateDate()
        {
                return this.graduateDate;
        }

        public void setGraduateDate(String graduateDate)
        {
                this.graduateDate = graduateDate;
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

                if (!(other instanceof GraduationModel))
                {
                        return false;
                }

                GraduationModel castOther = (GraduationModel) other;

                return new EqualsBuilder().append(this.getComp_id(),
                        castOther.getComp_id()).isEquals();
        }

        public int hashCode()
        {
                return new HashCodeBuilder().append(getComp_id()).toHashCode();
        }
}
