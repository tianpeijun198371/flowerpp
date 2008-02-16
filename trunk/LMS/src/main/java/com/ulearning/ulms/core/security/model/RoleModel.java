package com.ulearning.ulms.core.security.model;

import org.apache.commons.lang.builder.ToStringBuilder;

import java.io.Serializable;

import java.util.Set;


/**
 * @author Hibernate CodeGenerator
 */
public class RoleModel implements Serializable
{
        /**
         * identifier field
         */
        private int roleid;

        /**
         * persistent field
         */
        private int sysid;

        /**
         * nullable persistent field
         */
        private String name;

        /**
         * nullable persistent field
         */
        private String description;

        /**
         * persistent field
         */
        private Set secRolepermTabs;

        /**
         * full constructor
         */
        public RoleModel(int sysid, String name, String description,
                         Set secRolepermTabs)
        {
                this.sysid = sysid;
                this.name = name;
                this.description = description;
                this.secRolepermTabs = secRolepermTabs;
        }

        /**
         * default constructor
         */
        public RoleModel()
        {
        }

        /**
         * minimal constructor
         */
        public RoleModel(int sysid, Set secRolepermTabs)
        {
                this.sysid = sysid;
                this.secRolepermTabs = secRolepermTabs;
        }

        public int getRoleid()
        {
                return this.roleid;
        }

        public void setRoleid(int roleid)
        {
                this.roleid = roleid;
        }

        public int getSysid()
        {
                return this.sysid;
        }

        public void setSysid(int sysid)
        {
                this.sysid = sysid;
        }

        public String getName()
        {
                return this.name;
        }

        public void setName(String name)
        {
                this.name = name;
        }

        public String getDescription()
        {
                return this.description;
        }

        public void setDescription(String description)
        {
                this.description = description;
        }

        public Set getSecRolepermTabs()
        {
                return this.secRolepermTabs;
        }

        public void setSecRolepermTabs(Set secRolepermTabs)
        {
                this.secRolepermTabs = secRolepermTabs;
        }

        public String toString()
        {
                return new ToStringBuilder(this).append("roleid", getRoleid()).toString();
        }
}
