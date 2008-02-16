/**
 * UserRoleModel.java.
 * User: dengj  Date: 2004-8-27
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.core.security.model;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import java.io.Serializable;


public class UserRoleModel implements Serializable
{
        /**
         * identifier field
         */
        private UserRoleModelPK comp_id;

        /**
         * full constructor
         */
        public UserRoleModel(UserRoleModelPK comp_id)
        {
                this.comp_id = comp_id;
        }

        /**
         * default constructor
         */
        public UserRoleModel()
        {
        }

        public UserRoleModelPK getComp_id()
        {
                return this.comp_id;
        }

        public void setComp_id(UserRoleModelPK comp_id)
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

                if (!(other instanceof UserRoleModel))
                {
                        return false;
                }

                UserRoleModel castOther = (UserRoleModel) other;

                return new EqualsBuilder().append(this.getComp_id(),
                        castOther.getComp_id()).isEquals();
        }

        public int hashCode()
        {
                return new HashCodeBuilder().append(getComp_id()).toHashCode();
        }
}
