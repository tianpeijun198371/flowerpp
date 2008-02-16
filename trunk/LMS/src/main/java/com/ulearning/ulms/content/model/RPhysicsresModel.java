package com.ulearning.ulms.content.model;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import java.io.Serializable;


/**
 * @author Hibernate CodeGenerator
 */
public class RPhysicsresModel implements Serializable
{
        /**
         * identifier field
         */
        private int resId;

        /**
         * persistent field
         */
        private int aspid;

        /**
         * persistent field
         */
        private int orgid;

        /**
         * nullable persistent field
         */
        private String rescode;

        /**
         * nullable persistent field
         */
        private String resname;

        /**
         * nullable persistent field
         */
        private String ressize;

        /**
         * nullable persistent field
         */
        private String resfix;

        /**
         * nullable persistent field
         */
        private int resstate;

        /**
         * nullable persistent field
         */
        private String resother;

        /**
         * nullable persistent field
         */
        private int restype;

        /**
         * nullable persistent field
         */
        private int resmanageid;

        /**
         * nullable persistent field
         */
        private String resmanagename;

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

        /**
         * full constructor
         */
        public RPhysicsresModel(int aspid, int orgid, String rescode,
                                String resname, String ressize, String resfix, int resstate,
                                String resother, int restype, int resmanageid, String resmanagename,
                                String resremark1, String resremark2, String resremark3)
        {
                this.aspid = aspid;
                this.orgid = orgid;
                this.rescode = rescode;
                this.resname = resname;
                this.ressize = ressize;
                this.resfix = resfix;
                this.resstate = resstate;
                this.resother = resother;
                this.restype = restype;
                this.resmanageid = resmanageid;
                this.resmanagename = resmanagename;
                this.resremark1 = resremark1;
                this.resremark2 = resremark2;
                this.resremark3 = resremark3;
        }

        /**
         * default constructor
         */
        public RPhysicsresModel()
        {
        }

        /**
         * minimal constructor
         */
        public RPhysicsresModel(int aspid, int orgid)
        {
                this.aspid = aspid;
                this.orgid = orgid;
        }

        public int getResId()
        {
                return this.resId;
        }

        public void setResId(int resId)
        {
                this.resId = resId;
        }

        public int getAspid()
        {
                return this.aspid;
        }

        public void setAspid(int aspid)
        {
                this.aspid = aspid;
        }

        public int getOrgid()
        {
                return this.orgid;
        }

        public void setOrgid(int orgid)
        {
                this.orgid = orgid;
        }

        public String getRescode()
        {
                return this.rescode;
        }

        public void setRescode(String rescode)
        {
                this.rescode = rescode;
        }

        public String getResname()
        {
                return this.resname;
        }

        public void setResname(String resname)
        {
                this.resname = resname;
        }

        public String getRessize()
        {
                return this.ressize;
        }

        public void setRessize(String ressize)
        {
                this.ressize = ressize;
        }

        public String getResfix()
        {
                return this.resfix;
        }

        public void setResfix(String resfix)
        {
                this.resfix = resfix;
        }

        public int getResstate()
        {
                return this.resstate;
        }

        public void setResstate(int resstate)
        {
                this.resstate = resstate;
        }

        public String getResother()
        {
                return this.resother;
        }

        public void setResother(String resother)
        {
                this.resother = resother;
        }

        public int getRestype()
        {
                return this.restype;
        }

        public void setRestype(int restype)
        {
                this.restype = restype;
        }

        public int getResmanageid()
        {
                return this.resmanageid;
        }

        public void setResmanageid(int resmanageid)
        {
                this.resmanageid = resmanageid;
        }

        public String getResmanagename()
        {
                return this.resmanagename;
        }

        public void setResmanagename(String resmanagename)
        {
                this.resmanagename = resmanagename;
        }

        public String getResremark1()
        {
                return this.resremark1;
        }

        public void setResremark1(String resremark1)
        {
                this.resremark1 = resremark1;
        }

        public String getResremark2()
        {
                return this.resremark2;
        }

        public void setResremark2(String resremark2)
        {
                this.resremark2 = resremark2;
        }

        public String getResremark3()
        {
                return this.resremark3;
        }

        public void setResremark3(String resremark3)
        {
                this.resremark3 = resremark3;
        }

        public String toString()
        {
                return new ToStringBuilder(this).append("resid", getResId()).toString();
        }

        public boolean equals(Object other)
        {
                if (!(other instanceof RPhysicsresModel))
                {
                        return false;
                }

                RPhysicsresModel castOther = (RPhysicsresModel) other;

                return new EqualsBuilder().append(this.getResId(), castOther.getResId())
                        .isEquals();
        }

        public int hashCode()
        {
                return new HashCodeBuilder().append(getResId()).toHashCode();
        }
}
