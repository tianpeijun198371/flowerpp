/**
 * Created by IntelliJ IDEA.
 * User: zengwj
 * Date: 2004-8-31
 * Time: 10:17:18
 * To change this template use File | Settings | File Templates.
 */
package com.ulearning.ulms.course.model;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;


public class SecUserRoleModel
{
        /**
         * identifier field
         */
        private SecUserRolePK comp_id;

        /**
         * full constructor
         */
        public SecUserRoleModel(SecUserRolePK comp_id)
        {
                this.comp_id = comp_id;
        }

        /**
         * default constructor
         */
        public SecUserRoleModel()
        {
        }

        public SecUserRolePK getComp_id()
        {
                return this.comp_id;
        }

        public void setComp_id(SecUserRolePK comp_id)
        {
                this.comp_id = comp_id;
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

                if (!(other instanceof SecUserRoleModel))
                {
                        return false;
                }

                SecUserRoleModel castOther = (SecUserRoleModel) other;

                return new EqualsBuilder().append(this.getComp_id(),
                        castOther.getComp_id()).isEquals();
        }

        public int hashCode()
        {
                return new HashCodeBuilder().append(getComp_id()).toHashCode();
        }
}
