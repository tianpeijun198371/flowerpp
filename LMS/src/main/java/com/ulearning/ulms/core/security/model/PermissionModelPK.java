package com.ulearning.ulms.core.security.model;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import java.io.Serializable;


/**
 * @author Hibernate CodeGenerator
 */
public class PermissionModelPK implements Serializable
{
        /**
         * identifier field
         */
        private int moduleid;

        /**
         * identifier field
         */
        private int permitid;

        /**
         * full constructor
         */
        public PermissionModelPK(int moduleid, int permitid)
        {
                this.moduleid = moduleid;
                this.permitid = permitid;
        }

        /**
         * default constructor
         */
        public PermissionModelPK()
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
                        .append("permitid", getPermitid())
                        .toString();
        }

        public boolean equals(Object other)
        {
                if ((this == other))
                {
                        return true;
                }

                if (!(other instanceof PermissionModelPK))
                {
                        return false;
                }

                PermissionModelPK castOther = (PermissionModelPK) other;

                return new EqualsBuilder().append(this.getModuleid(),
                        castOther.getModuleid())
                        .append(this.getPermitid(),
                                castOther.getPermitid()).isEquals();
        }

        public int hashCode()
        {
                return new HashCodeBuilder().append(getModuleid()).append(getPermitid())
                        .toHashCode();
        }
}
