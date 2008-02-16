/**
 * Created by IntelliJ IDEA.
 * User: zengwj
 * Date: 2004-8-31
 * Time: 10:14:43
 * To change this template use File | Settings | File Templates.
 */
package com.ulearning.ulms.course.model;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import java.io.Serializable;


public class CourseUserPK implements Serializable
{
        /**
         * identifier field
         */
        private int userID;

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
        public CourseUserPK(int userID, int relationID, int type)
        {
                this.userID = userID;
                this.relationID = relationID;
                this.type = type;
        }

        /**
         * default constructor
         */
        public CourseUserPK()
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
                        .append("relationID", getRelationID())
                        .append("type", getType()).toString();
        }

        public boolean equals(Object other)
        {
                if ((this == other))
                {
                        return true;
                }

                if (!(other instanceof CourseUserPK))
                {
                        return false;
                }

                CourseUserPK castOther = (CourseUserPK) other;

                return new EqualsBuilder().append(this.getUserID(),
                        castOther.getUserID())
                        .append(this.getRelationID(),
                                castOther.getRelationID())
                        .append(this.getType(), castOther.getType())
                        .isEquals();
        }

        public int hashCode()
        {
                return new HashCodeBuilder().append(getUserID()).append(getRelationID())
                        .append(getType()).toHashCode();
        }
}
