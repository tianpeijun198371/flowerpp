package com.ulearning.ulms.admin.sysconfig.model;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import java.io.Serializable;


/**
 * @author Hibernate CodeGenerator
 */
public class AutoInformModelPK implements Serializable
{
        /**
         * identifier field
         */
        private int orgid;

        /**
         * identifier field
         */
        private int type;

        /**
         * full constructor
         */
        public AutoInformModelPK(int orgid, int type)
        {
                this.orgid = orgid;
                this.type = type;
        }

        /**
         * default constructor
         */
        public AutoInformModelPK()
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

        public int getType()
        {
                return this.type;
        }

        public void setType(int type)
        {
                this.type = type;
        }

        public String toString()
        {
                return new ToStringBuilder(this).append("orgid", getOrgid())
                        .append("type", getType()).toString();
        }

        public boolean equals(Object other)
        {
                if ((this == other))
                {
                        return true;
                }

                if (!(other instanceof AutoInformModelPK))
                {
                        return false;
                }

                AutoInformModelPK castOther = (AutoInformModelPK) other;

                return new EqualsBuilder().append(this.getOrgid(), castOther.getOrgid())
                        .append(this.getType(), castOther.getType())
                        .isEquals();
        }

        public int hashCode()
        {
                return new HashCodeBuilder().append(getOrgid()).append(getType())
                        .toHashCode();
        }
}
