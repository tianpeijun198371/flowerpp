package com.ulearning.ulms.user.group.model;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;


/**
 * @author Hibernate CodeGenerator
 */
public class UserGroupModelPK implements Serializable
{

        /**
         * identifier field
         */
        private int groupId;

        /**
         * identifier field
         */
        private int userId;

        /**
         * full constructor
         */
        public UserGroupModelPK(int groupId, int userId)
        {
                this.groupId = groupId;
                this.userId = userId;
        }

        /**
         * default constructor
         */
        public UserGroupModelPK()
        {
        }

        public int getGroupId()
        {
                return this.groupId;
        }

        public void setGroupId(int groupId)
        {
                this.groupId = groupId;
        }

        public int getUserId()
        {
                return this.userId;
        }

        public void setUserId(int userId)
        {
                this.userId = userId;
        }

        public String toString()
        {
                return new ToStringBuilder(this)
                        .append("groupId", getGroupId())
                        .append("userId", getUserId())
                        .toString();
        }

        public boolean equals(Object other)
        {
                if ((this == other))
                {
                        return true;
                }
                if (!(other instanceof UserGroupModelPK))
                {
                        return false;
                }
                UserGroupModelPK castOther = (UserGroupModelPK) other;
                return new EqualsBuilder()
                        .append(this.getGroupId(), castOther.getGroupId())
                        .append(this.getUserId(), castOther.getUserId())
                        .isEquals();
        }

        public int hashCode()
        {
                return new HashCodeBuilder()
                        .append(getGroupId())
                        .append(getUserId())
                        .toHashCode();
        }

}
