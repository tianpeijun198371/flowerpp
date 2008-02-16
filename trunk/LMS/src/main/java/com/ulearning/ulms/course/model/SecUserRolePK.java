/**
 * Created by IntelliJ IDEA.
 * User: zengwj
 * Date: 2004-8-31
 * Time: 10:18:07
 * To change this template use File | Settings | File Templates.
 */
package com.ulearning.ulms.course.model;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import java.io.Serializable;


public class SecUserRolePK implements Serializable
{
        /**
         * identifier field
         */
        private int userID;

        /**
         * identifier field
         */
        private int roleID;

        /**
         * identifier field
         */
        private int relationID;

        /**
         * identifier field
         */
        private int type;

        /**
         * full constructor
         */
        public SecUserRolePK(int userID, int roleID, int relationID, int type)
        {
                this.userID = userID;
                this.roleID = roleID;
                this.relationID = relationID;
                this.type = type;
        }

        /**
         * default constructor
         */
        public SecUserRolePK()
        {
        }

        public int getUserID()
        {
                return this.userID;
        }

        public void setUserID(int userID)
        {
                this.userID = userID;
        }

        public int getRoleID()
        {
                return this.roleID;
        }

        public void setRoleID(int roleID)
        {
                this.roleID = roleID;
        }

        public int getRelationID()
        {
                return this.relationID;
        }

        public void setRelationID(int relationID)
        {
                this.relationID = relationID;
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
                return new ToStringBuilder(this).append("userID", getUserID())
                        .append("roleID", getRoleID())
                        .append("relationID", getRelationID())
                        .append("type", getType()).toString();
        }

        public boolean equals(Object other)
        {
                if ((this == other))
                {
                        return true;
                }

                if (!(other instanceof SecUserRolePK))
                {
                        return false;
                }

                SecUserRolePK castOther = (SecUserRolePK) other;

                return new EqualsBuilder().append(this.getUserID(),
                        castOther.getUserID())
                        .append(this.getRoleID(),
                                castOther.getRoleID())
                        .append(this.getRelationID(),
                                castOther.getRelationID())
                        .append(this.getType(), castOther.getType())
                        .isEquals();
        }

        public int hashCode()
        {
                return new HashCodeBuilder().append(getUserID()).append(getRoleID())
                        .append(getRelationID()).append(getType())
                        .toHashCode();
        }
}
