package com.ulearning.ulms.admin.sysconfig.model;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import java.io.Serializable;


/**
 * @author Hibernate CodeGenerator
 */
public class AutoInformModel implements Serializable
{
        /**
         * identifier field
         */
        private AutoInformModelPK comp_id;

        /**
         * nullable persistent field
         */
        private String name;

        /**
         * persistent field
         */
        private String ismail;

        /**
         * persistent field
         */
        private String ismsg;

        /**
         * nullable persistent field
         */
        private String istip;

        /**
         * nullable persistent field
         */
        private String description;

        /**
         * full constructor
         */
        public AutoInformModel(AutoInformModelPK comp_id, String name,
                               String ismail, String ismsg, String istip, String description)
        {
                this.comp_id = comp_id;
                this.name = name;
                this.ismail = ismail;
                this.ismsg = ismsg;
                this.istip = istip;
                this.description = description;
        }

        /**
         * default constructor
         */
        public AutoInformModel()
        {
        }

        /**
         * minimal constructor
         */
        public AutoInformModel(AutoInformModelPK comp_id, String ismail,
                               String ismsg)
        {
                this.comp_id = comp_id;
                this.ismail = ismail;
                this.ismsg = ismsg;
        }

        public AutoInformModelPK getComp_id()
        {
                return this.comp_id;
        }

        public void setComp_id(AutoInformModelPK comp_id)
        {
                this.comp_id = comp_id;
        }

        public String getName()
        {
                return this.name;
        }

        public void setName(String name)
        {
                this.name = name;
        }

        public String getIsmail()
        {
                return this.ismail;
        }

        public void setIsmail(String ismail)
        {
                this.ismail = ismail;
        }

        public String getIsmsg()
        {
                return this.ismsg;
        }

        public void setIsmsg(String ismsg)
        {
                this.ismsg = ismsg;
        }

        public String getIstip()
        {
                return this.istip;
        }

        public void setIstip(String istip)
        {
                this.istip = istip;
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

                if (!(other instanceof AutoInformModel))
                {
                        return false;
                }

                AutoInformModel castOther = (AutoInformModel) other;

                return new EqualsBuilder().append(this.getComp_id(),
                        castOther.getComp_id()).isEquals();
        }

        public int hashCode()
        {
                return new HashCodeBuilder().append(getComp_id()).toHashCode();
        }
}
