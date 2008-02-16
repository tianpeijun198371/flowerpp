package com.ulearning.ulms.core.security.model;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import java.io.Serializable;


/**
 * @author Hibernate CodeGenerator
 */
public class ModulepropertyModelPK implements Serializable
{
        /**
         * identifier field
         */
        private int moduleid;

        /**
         * identifier field
         */
        private int proid;

        /**
         * identifier field
         */
        private int relationid;

        /**
         * identifier field
         */
        private int type;

        /**
         * full constructor
         */
        public ModulepropertyModelPK(int moduleid, int proid, int relationid,
                                     int type)
        {
                this.moduleid = moduleid;
                this.proid = proid;
                this.relationid = relationid;
                this.type = type;
        }

        /**
         * default constructor
         */
        public ModulepropertyModelPK()
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

        public int getProid()
        {
                return this.proid;
        }

        public void setProid(int proid)
        {
                this.proid = proid;
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

        public String toString()
        {
                return new ToStringBuilder(this).append("moduleid", getModuleid())
                        .append("proid", getProid())
                        .append("relationid", getRelationid())
                        .append("type", getType()).toString();
        }

        public boolean equals(Object other)
        {
                if ((this == other))
                {
                        return true;
                }

                if (!(other instanceof ModulepropertyModelPK))
                {
                        return false;
                }

                ModulepropertyModelPK castOther = (ModulepropertyModelPK) other;

                return new EqualsBuilder().append(this.getModuleid(),
                        castOther.getModuleid())
                        .append(this.getProid(), castOther.getProid())
                        .append(this.getRelationid(),
                                castOther.getRelationid())
                        .append(this.getType(), castOther.getType())
                        .isEquals();
        }

        public int hashCode()
        {
                return new HashCodeBuilder().append(getModuleid()).append(getProid())
                        .append(getRelationid()).append(getType())
                        .toHashCode();
        }
}
