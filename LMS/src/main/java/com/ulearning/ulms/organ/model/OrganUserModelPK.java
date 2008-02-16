package com.ulearning.ulms.organ.model;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import java.io.Serializable;


/**
 * @author Hibernate CodeGenerator
 */
public class OrganUserModelPK implements Serializable
{
        /**
         * identifier field
         */
        private int orgid;

        /**
         * identifier field
         */
        private int userid;

        /**
         * full constructor
         */
        public OrganUserModelPK(int orgid, int userid)
        {
                this.orgid = orgid;
                this.userid = userid;
        }

        /**
         * default constructor
         */
        public OrganUserModelPK()
        {
        }

        public int getOrgid()
        {
                return this.orgid;
        }

        public void setOrgid(int orgid)
        {
                this.orgid = orgid;
        }

        public int getUserid()
        {
                return this.userid;
        }

        public void setUserid(int userid)
        {
                this.userid = userid;
        }

        public String toString()
        {
                return new ToStringBuilder(this).append("orgid", getOrgid())
                        .append("userid", getUserid()).toString();
        }

        public boolean equals(Object other)
        {
                if ((this == other))
                {
                        return true;
                }

                if (!(other instanceof OrganUserModelPK))
                {
                        return false;
                }

                OrganUserModelPK castOther = (OrganUserModelPK) other;

                return new EqualsBuilder().append(this.getOrgid(), castOther.getOrgid())
                        .append(this.getUserid(),
                                castOther.getUserid()).isEquals();
        }

        public int hashCode()
        {
                return new HashCodeBuilder().append(getOrgid()).append(getUserid())
                        .toHashCode();
        }
}
