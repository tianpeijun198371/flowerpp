package com.ulearning.ulms.core.security.model;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import java.io.Serializable;


/**
 * @author Hibernate CodeGenerator
 */
public class PropertyModel implements Serializable
{
        /**
         * identifier field
         */
        private PropertyModelPK comp_id;

        /**
         * persistent field
         */
        private int isavailable;

        /**
         * nullable persistent field
         */
        private String name;

        /**
         * nullable persistent field
         */
        private String prono;

        /**
         * nullable persistent field
         */
        private String description;

        /**
         * nullable persistent field
         */
        private int moduleID;

        /**
         * full constructor
         */
        public PropertyModel(PropertyModelPK comp_id, int isavailable, String name,
                             String prono, String description, int moduleID)
        {
                this.comp_id = comp_id;
                this.isavailable = isavailable;
                this.name = name;
                this.prono = prono;
                this.description = description;
                this.moduleID = moduleID;
        }

        /**
         * default constructor
         */
        public PropertyModel()
        {
        }

        /**
         * minimal constructor
         */
        public PropertyModel(PropertyModelPK comp_id, int isavailable)
        {
                this.comp_id = comp_id;
                this.isavailable = isavailable;
        }

        public PropertyModelPK getComp_id()
        {
                return this.comp_id;
        }

        public void setComp_id(PropertyModelPK comp_id)
        {
                this.comp_id = comp_id;
        }

        public int getIsavailable()
        {
                return this.isavailable;
        }

        public void setIsavailable(int isavailable)
        {
                this.isavailable = isavailable;
        }

        public String getName()
        {
                return this.name;
        }

        public void setName(String name)
        {
                this.name = name;
        }

        public String getProno()
        {
                return this.prono;
        }

        public void setProno(String prono)
        {
                this.prono = prono;
        }

        public String getDescription()
        {
                return this.description;
        }

        public void setDescription(String description)
        {
                this.description = description;
        }

        public int getModuleID()
        {
                return this.moduleID;
        }

        public void setModuleID(int moduleID)
        {
                this.moduleID = moduleID;
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

                if (!(other instanceof PropertyModel))
                {
                        return false;
                }

                PropertyModel castOther = (PropertyModel) other;

                return new EqualsBuilder().append(this.getComp_id(),
                        castOther.getComp_id()).isEquals();
        }

        public int hashCode()
        {
                return new HashCodeBuilder().append(getComp_id()).toHashCode();
        }
}
