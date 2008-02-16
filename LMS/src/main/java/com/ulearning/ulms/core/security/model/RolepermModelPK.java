package com.ulearning.ulms.core.security.model;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import java.io.Serializable;


/**
 * @author Hibernate CodeGenerator
 */
public class RolepermModelPK implements Serializable
{
        /**
         * identifier field
         */
        private int moduleid;

        /**
         * identifier field
         */
        private int roleid;

        /**
         * identifier field
         */
        private int permitid;

        /**
         * full constructor
         */
        public RolepermModelPK(int moduleid, int roleid, int permitid)
        {
                this.moduleid = moduleid;
                this.roleid = roleid;
                this.permitid = permitid;
        }

        /**
         * default constructor
         */
        public RolepermModelPK()
        {
        }

        public int getModuleid()
        {
                return this.moduleid;
        }

        public void setModuleid(int moduleid)
        {
                this.moduleid = moduleid;
        }

        public int getRoleid()
        {
                return this.roleid;
        }

        public void setRoleid(int roleid)
        {
                this.roleid = roleid;
        }

        public int getPermitid()
        {
                return this.permitid;
        }

        public void setPermitid(int permitid)
        {
                this.permitid = permitid;
        }

        public String toString()
        {
                return new ToStringBuilder(this).append("moduleid", getModuleid())
                        .append("roleid", getRoleid())
                        .append("permitid", getPermitid())
                        .toString();
        }

        public boolean equals(Object other)
        {
                if ((this == other))
                {
                        return true;
                }

                if (!(other instanceof RolepermModelPK))
                {
                        return false;
                }

                RolepermModelPK castOther = (RolepermModelPK) other;

                return new EqualsBuilder().append(this.getModuleid(),
                        castOther.getModuleid())
                        .append(this.getRoleid(),
                                castOther.getRoleid())
                        .append(this.getPermitid(),
                                castOther.getPermitid()).isEquals();
        }

        public int hashCode()
        {
                return new HashCodeBuilder().append(getModuleid()).append(getRoleid())
                        .append(getPermitid()).toHashCode();
        }
}
