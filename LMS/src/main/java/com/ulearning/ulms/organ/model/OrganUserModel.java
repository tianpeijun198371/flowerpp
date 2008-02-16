package com.ulearning.ulms.organ.model;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import java.io.Serializable;

import java.math.BigDecimal;


/**
 * @author Hibernate CodeGenerator
 */
public class OrganUserModel implements Serializable
{
        /**
         * identifier field
         */
        private OrganUserModelPK comp_id;

        /**
         * nullable persistent field
         */
        private int type;

        /**
         * nullable persistent field
         */
        private int userID;

        /**
         * nullable persistent field
         */
        private int orgID;

        /**
         * full constructor
         */
        public OrganUserModel(OrganUserModelPK comp_id, int type, int userID,
                              int orgID)
        {
                this.comp_id = comp_id;
                this.type = type;
                this.userID = userID;
                this.orgID = orgID;
        }

        /**
         * default constructor
         */
        public OrganUserModel()
        {
        }

        /**
         * minimal constructor
         */
        public OrganUserModel(OrganUserModelPK comp_id)
        {
                this.comp_id = comp_id;
        }

        public OrganUserModelPK getComp_id()
        {
                return this.comp_id;
        }

        public void setComp_id(OrganUserModelPK comp_id)
        {
                this.comp_id = comp_id;
        }

        public int getType()
        {
                return this.type;
        }

        public void setType(int type)
        {
                this.type = type;
        }

        public int getUuserTab()
        {
                return this.userID;
        }

        public void setUuserTab(int userID)
        {
                this.userID = userID;
        }

        public int getTmOrgTab()
        {
                return this.orgID;
        }

        public void setTmOrgTab(int org)
        {
                this.orgID = orgID;
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

                if (!(other instanceof OrganUserModel))
                {
                        return false;
                }

                OrganUserModel castOther = (OrganUserModel) other;

                return new EqualsBuilder().append(this.getComp_id(),
                        castOther.getComp_id()).isEquals();
        }

        public int hashCode()
        {
                return new HashCodeBuilder().append(getComp_id()).toHashCode();
        }
}
