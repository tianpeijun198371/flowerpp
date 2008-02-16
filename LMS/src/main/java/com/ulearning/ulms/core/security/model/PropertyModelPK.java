package com.ulearning.ulms.core.security.model;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import java.io.Serializable;


/**
 * @author Hibernate CodeGenerator
 */
public class PropertyModelPK implements Serializable
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
         * full constructor
         */
        public PropertyModelPK(int moduleid, int proid)
        {
                this.moduleid = moduleid;
                this.proid = proid;
        }

        /**
         * default constructor
         */
        public PropertyModelPK()
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

        public String toString()
        {
                return new ToStringBuilder(this).append("moduleid", getModuleid())
                        .append("proid", getProid()).toString();
        }

        public boolean equals(Object other)
        {
                if ((this == other))
                {
                        return true;
                }

                if (!(other instanceof PropertyModelPK))
                {
                        return false;
                }

                PropertyModelPK castOther = (PropertyModelPK) other;

                return new EqualsBuilder().append(this.getModuleid(),
                        castOther.getModuleid())
                        .append(this.getProid(), castOther.getProid())
                        .isEquals();
        }

        public int hashCode()
        {
                return new HashCodeBuilder().append(getModuleid()).append(getProid())
                        .toHashCode();
        }
}
