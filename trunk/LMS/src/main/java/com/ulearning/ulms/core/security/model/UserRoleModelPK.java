/**
 * UserRoleModelPK.java.
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


/**
 * @author Hibernate CodeGenerator
 */
public class UserRoleModelPK implements Serializable
{
        /**
         * identifier field
         */
        private int userid;

        /**
         * identifier field
         */
        private int roleid;

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
        public UserRoleModelPK(int userid, int roleid, int relationid, int type)
        {
                this.userid = userid;
                this.roleid = roleid;
                this.relationid = relationid;
                this.type = type;
        }

        /**
         * default constructor
         */
        public UserRoleModelPK()
        {
        }

        public int getUserid()
        {
                return this.userid;
        }

        public void setUserid(int userid)
        {
                this.userid = userid;
        }

        public int getRoleid()
        {
                return this.roleid;
        }

        public void setRoleid(int roleid)
        {
                this.roleid = roleid;
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
                return new ToStringBuilder(this).append("userid", getUserid())
                        .append("roleid", getRoleid())
                        .append("relationid", getRelationid())
                        .append("type", getType()).toString();
        }

        public boolean equals(Object other)
        {
                if ((this == other))
                {
                        return true;
                }

                if (!(other instanceof UserRoleModelPK))
                {
                        return false;
                }

                UserRoleModelPK castOther = (UserRoleModelPK) other;

                return new EqualsBuilder().append(this.getUserid(),
                        castOther.getUserid())
                        .append(this.getRoleid(),
                                castOther.getRoleid())
                        .append(this.getRelationid(),
                                castOther.getRelationid())
                        .append(this.getType(), castOther.getType())
                        .isEquals();
        }

        public int hashCode()
        {
                return new HashCodeBuilder().append(getUserid()).append(getRoleid())
                        .append(getRelationid()).append(getType())
                        .toHashCode();
        }
}
