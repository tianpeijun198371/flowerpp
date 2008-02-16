package com.ulearning.ulms.core.security.model;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import java.io.Serializable;


/**
 * @author Hibernate CodeGenerator
 */
public class ModulepropertyModel implements Serializable
{
        /**
         * identifier field
         */
        private ModulepropertyModelPK comp_id;

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
        public ModulepropertyModel(ModulepropertyModelPK comp_id,
                                   String description, int moduleID)
        {
                this.comp_id = comp_id;
                this.description = description;
                this.moduleID = moduleID;
        }

        /**
         * default constructor
         */
        public ModulepropertyModel()
        {
        }

        /**
         * minimal constructor
         */
        public ModulepropertyModel(ModulepropertyModelPK comp_id)
        {
                this.comp_id = comp_id;
        }

        public ModulepropertyModelPK getComp_id()
        {
                return this.comp_id;
        }

        public void setComp_id(ModulepropertyModelPK comp_id)
        {
                this.comp_id = comp_id;
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

                if (!(other instanceof ModulepropertyModel))
                {
                        return false;
                }

                ModulepropertyModel castOther = (ModulepropertyModel) other;

                return new EqualsBuilder().append(this.getComp_id(),
                        castOther.getComp_id()).isEquals();
        }

        public int hashCode()
        {
                return new HashCodeBuilder().append(getComp_id()).toHashCode();
        }
}
