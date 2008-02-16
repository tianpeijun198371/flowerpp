package com.ulearning.ulms.admin.cerdangan.model;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import java.io.Serializable;


/**
 * @author Hibernate CodeGenerator
 */
public class CerNewdanganTab implements Serializable
{
        /**
         * identifier field
         */
        private int danganid;

        /**
         * persistent field
         */
        private String certname;

        /**
         * nullable persistent field
         */
        private String time;

        /**
         * nullable persistent field
         */
        private String students;

        /**
         * nullable persistent field
         */
        private String maincontent;

        /**
         * nullable persistent field
         */
        private Integer number;

        /**
         * nullable persistent field
         */
        private String date;

        /**
         * nullable persistent field
         */
        private Float daycharge;

        /**
         * nullable persistent field
         */
        private String charge;

        /**
         * nullable persistent field
         */
        private String trainplace;

        /**
         * nullable persistent field
         */
        private String traintype;

        /**
         * nullable persistent field
         */
        private String projtype;

        /**
         * nullable persistent field
         */
        private String trainlever;

        /**
         * nullable persistent field
         */
        private String projhost;

        /**
         * nullable persistent field
         */
        private String joiner;

        /**
         * nullable persistent field
         */
        private String manage;

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
        private String remark1;

        /**
         * full constructor
         */
        public CerNewdanganTab(String certname, String time, String students,
                               String maincontent, Integer number, String date, Float daycharge,
                               String charge, String trainplace, String traintype, String projtype,
                               String trainlever, String projhost, String joiner, String manage,
                               String remark2, String remark3, String remark1)
        {
                this.certname = certname;
                this.time = time;
                this.students = students;
                this.maincontent = maincontent;
                this.number = number;
                this.date = date;
                this.daycharge = daycharge;
                this.charge = charge;
                this.trainplace = trainplace;
                this.traintype = traintype;
                this.projtype = projtype;
                this.trainlever = trainlever;
                this.projhost = projhost;
                this.joiner = joiner;
                this.manage = manage;
                this.remark2 = remark2;
                this.remark3 = remark3;
                this.remark1 = remark1;
        }

        /**
         * default constructor
         */
        public CerNewdanganTab()
        {
        }

        /**
         * minimal constructor
         */
        public CerNewdanganTab(String certname)
        {
                this.certname = certname;
        }

        public int getDanganid()
        {
                return this.danganid;
        }

        public void setDanganid(int danganid)
        {
                this.danganid = danganid;
        }

        public String getCertname()
        {
                return this.certname;
        }

        public void setCertname(String certname)
        {
                this.certname = certname;
        }

        public String getTime()
        {
                return this.time;
        }

        public void setTime(String time)
        {
                this.time = time;
        }

        public String getStudents()
        {
                return this.students;
        }

        public void setStudents(String students)
        {
                this.students = students;
        }

        public String getMaincontent()
        {
                return this.maincontent;
        }

        public void setMaincontent(String maincontent)
        {
                this.maincontent = maincontent;
        }

        public Integer getNumber()
        {
                return this.number;
        }

        public void setNumber(Integer number)
        {
                this.number = number;
        }

        public String getDate()
        {
                return this.date;
        }

        public void setDate(String date)
        {
                this.date = date;
        }

        public Float getDaycharge()
        {
                return this.daycharge;
        }

        public void setDaycharge(Float daycharge)
        {
                this.daycharge = daycharge;
        }

        public String getCharge()
        {
                return this.charge;
        }

        public void setCharge(String charge)
        {
                this.charge = charge;
        }

        public String getTrainplace()
        {
                return this.trainplace;
        }

        public void setTrainplace(String trainplace)
        {
                this.trainplace = trainplace;
        }

        public String getTraintype()
        {
                return this.traintype;
        }

        public void setTraintype(String traintype)
        {
                this.traintype = traintype;
        }

        public String getProjtype()
        {
                return this.projtype;
        }

        public void setProjtype(String projtype)
        {
                this.projtype = projtype;
        }

        public String getTrainlever()
        {
                return this.trainlever;
        }

        public void setTrainlever(String trainlever)
        {
                this.trainlever = trainlever;
        }

        public String getProjhost()
        {
                return this.projhost;
        }

        public void setProjhost(String projhost)
        {
                this.projhost = projhost;
        }

        public String getJoiner()
        {
                return this.joiner;
        }

        public void setJoiner(String joiner)
        {
                this.joiner = joiner;
        }

        public String getManage()
        {
                return this.manage;
        }

        public void setManage(String manage)
        {
                this.manage = manage;
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

        public String getRemark1()
        {
                return this.remark1;
        }

        public void setRemark1(String remark1)
        {
                this.remark1 = remark1;
        }

        public String toString()
        {
                return new ToStringBuilder(this).append("danganid", getDanganid())
                        .toString();
        }

        public boolean equals(Object other)
        {
                if (!(other instanceof CerNewdanganTab))
                {
                        return false;
                }

                CerNewdanganTab castOther = (CerNewdanganTab) other;

                return new EqualsBuilder().append(this.getDanganid(),
                        castOther.getDanganid()).isEquals();
        }

        public int hashCode()
        {
                return new HashCodeBuilder().append(getDanganid()).toHashCode();
        }
}
