package com.ulearning.ulms.core.security.model;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import java.io.Serializable;


/**
 * @author Hibernate CodeGenerator
 */
public class RolepermModel implements Serializable
{
        /**
         * identifier field
         */
        private RolepermModelPK comp_id;

        /**
         * persistent field
         */
        private int orderindex;

        /**
         * persistent field
         */
        private int relationid;

        /**
         * persistent field
         */
        private int type;

        /**
         * persistent field
         */
        private int status;

        /**
         * nullable persistent field
         */
        private RoleModel secRoleTab;

        /**
         * full constructor
         */
        public RolepermModel(RolepermModelPK comp_id, int orderindex,
                             int relationid, int type, int status, RoleModel secRoleTab)
        {
                this.comp_id = comp_id;
                this.orderindex = orderindex;
                this.relationid = relationid;
                this.type = type;
                this.status = status;
                this.secRoleTab = secRoleTab;
        }

        /**
         * default constructor
         */
        public RolepermModel()
        {
        }

        /**
         * minimal constructor
         */
        public RolepermModel(RolepermModelPK comp_id, int orderindex,
                             int relationid, int type, int status)
        {
                this.comp_id = comp_id;
                this.orderindex = orderindex;
                this.relationid = relationid;
                this.type = type;
                this.status = status;
        }

        public RolepermModelPK getComp_id()
        {
                return this.comp_id;
        }

        public void setComp_id(RolepermModelPK comp_id)
        {
                this.comp_id = comp_id;
        }

        public int getOrderindex()
        {
                return this.orderindex;
        }

        public void setOrderindex(int orderindex)
        {
                this.orderindex = orderindex;
        }

        public int getRelationid()
        {
                return this.relationid;
        }

        public void setRelationid(int relationid)
        {
                this.relationid = relationid;
        }

        public int getType()
        {
                return this.type;
        }

        public void setType(int type)
        {
                this.type = type;
        }

        public int getStatus()
        {
                return this.status;
        }

        public void setStatus(int status)
        {
                this.status = status;
        }

        public RoleModel getSecRoleTab()
        {
                return this.secRoleTab;
        }

        public void setSecRoleTab(RoleModel secRoleTab)
        {
                this.secRoleTab = secRoleTab;
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

                if (!(other instanceof RolepermModel))
                {
                        return false;
                }

                RolepermModel castOther = (RolepermModel) other;

                return new EqualsBuilder().append(this.getComp_id(),
                        castOther.getComp_id()).isEquals();
        }

        public int hashCode()
        {
                return new HashCodeBuilder().append(getComp_id()).toHashCode();
        }
}
