package com.ulearning.ulms.core.security.model;

import org.apache.commons.lang.builder.ToStringBuilder;

import java.io.Serializable;

import java.util.Set;


/**
 * @author Hibernate CodeGenerator
 */
public class ModuleModel implements Serializable
{
        /**
         * identifier field
         */
        private int moduleid;

        /**
         * nullable persistent field
         */
        private String name;

        /**
         * nullable persistent field
         */
        private int sysid;

        /**
         * nullable persistent field
         */
        private String description;

        /**
         * persistent field
         */
        private Set secPropertyTabs;

        /**
         * persistent field
         */
        private Set secModulepropertyTabs;

        /**
         * full constructor
         */
        public ModuleModel(String name, int sysid, String description,
                           Set secPropertyTabs, Set secModulepropertyTabs)
        {
                this.name = name;
                this.sysid = sysid;
                this.description = description;
                this.secPropertyTabs = secPropertyTabs;
                this.secModulepropertyTabs = secModulepropertyTabs;
        }

        /**
         * default constructor
         */
        public ModuleModel()
        {
        }

        /**
         * minimal constructor
         */
        public ModuleModel(Set secPropertyTabs, Set secModulepropertyTabs)
        {
                this.secPropertyTabs = secPropertyTabs;
                this.secModulepropertyTabs = secModulepropertyTabs;
        }

        public int getModuleid()
        {
                return this.moduleid;
        }

        public void setModuleid(int moduleid)
        {
                this.moduleid = moduleid;
        }

        public String getName()
        {
                return this.name;
        }

        public void setName(String name)
        {
                this.name = name;
        }

        public int getSysid()
        {
                return this.sysid;
        }

        public void setSysid(int sysid)
        {
                this.sysid = sysid;
        }

        public String getDescription()
        {
                return this.description;
        }

        public void setDescription(String description)
        {
                this.description = description;
        }

        public Set getSecPropertyTabs()
        {
                return this.secPropertyTabs;
        }

        public void setSecPropertyTabs(Set secPropertyTabs)
        {
                this.secPropertyTabs = secPropertyTabs;
        }

        public Set getSecModulepropertyTabs()
        {
                return this.secModulepropertyTabs;
        }

        public void setSecModulepropertyTabs(Set secModulepropertyTabs)
        {
                this.secModulepropertyTabs = secModulepropertyTabs;
        }

        public String toString()
        {
                return new ToStringBuilder(this).append("moduleid", getModuleid())
                        .toString();
        }
}
