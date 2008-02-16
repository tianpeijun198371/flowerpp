package com.ulearning.ulms.core.security.model;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import java.io.Serializable;


/**
 * @author Hibernate CodeGenerator
 */
public class PermissionModel implements Serializable
{
        /**
         * identifier field
         */
        private PermissionModelPK comp_id;

        /**
         * persistent field
         */
        private int proid;

        /**
         * persistent field
         */
        private int isavailable;

        /**
         * persistent field
         */
        private int type;

        /**
         * persistent field
         */
        private int defaultcheck;

        /**
         * nullable persistent field
         */
        private String name;

        /**
         * nullable persistent field
         */
        private String permitno;

        /**
         * nullable persistent field
         */
        private String description;

        /**
         * full constructor
         */
        public PermissionModel(PermissionModelPK comp_id, int proid,
                               int isavailable, int type, int defaultcheck, String name,
                               String permitno, String description)
        {
                this.comp_id = comp_id;
                this.proid = proid;
                this.isavailable = isavailable;
                this.type = type;
                this.defaultcheck = defaultcheck;
                this.name = name;
                this.permitno = permitno;
                this.description = description;
        }

        /**
         * default constructor
         */
        public PermissionModel()
        {
        }

        /**
         * minimal constructor
         */
        public PermissionModel(PermissionModelPK comp_id, int proid,
                               int isavailable, int type, int defaultcheck)
        {
                this.comp_id = comp_id;
                this.proid = proid;
                this.isavailable = isavailable;
                this.type = type;
                this.defaultcheck = defaultcheck;
        }

        public PermissionModelPK getComp_id()
        {
                return this.comp_id;
        }

        public void setComp_id(PermissionModelPK comp_id)
        {
                this.comp_id = comp_id;
        }

        public int getProid()
        {
                return this.proid;
        }

        public void setProid(int proid)
        {
                this.proid = proid;
        }

        public int getIsavailable()
        {
                return this.isavailable;
        }

        public void setIsavailable(int isavailable)
        {
                this.isavailable = isavailable;
        }

        public int getType()
        {
                return this.type;
        }

        public void setType(int type)
        {
                this.type = type;
        }

        public int getDefaultcheck()
        {
                return this.defaultcheck;
        }

        public void setDefaultcheck(int defaultcheck)
        {
                this.defaultcheck = defaultcheck;
        }

        public String getName()
        {
                return this.name;
        }

        public void setName(String name)
        {
                this.name = name;
        }

        public String getPermitno()
        {
                return this.permitno;
        }

        public void setPermitno(String permitno)
        {
                this.permitno = permitno;
        }

        public String getDescription()
        {
                return this.description;
        }

        public void setDescription(String description)
        {
                this.description = description;
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

                if (!(other instanceof PermissionModel))
                {
                        return false;
                }

                PermissionModel castOther = (PermissionModel) other;

                return new EqualsBuilder().append(this.getComp_id(),
                        castOther.getComp_id()).isEquals();
        }

        public int hashCode()
        {
                return new HashCodeBuilder().append(getComp_id()).toHashCode();
        }
}
