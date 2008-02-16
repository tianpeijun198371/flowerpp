/**
 * Created by IntelliJ IDEA.
 * User: zengwj
 * Date: 2004-9-7
 * Time: 13:29:45
 * To change this template use File | Settings | File Templates.
 */
package com.ulearning.ulms.admin.graduation.model;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import java.io.Serializable;


public class GraduationModelPK implements Serializable
{
        /**
         * identifier field
         */
        private int relationID;

        /**
         * identifier field
         */
        private int type;

        /**
         * identifier field
         */
        private int userID;

        /**
         * full constructor
         */
        public GraduationModelPK(int relationID, int type, int userID)
        {
                this.relationID = relationID;
                this.type = type;
                this.userID = userID;
        }

        /**
         * default constructor
         */
        public GraduationModelPK()
        {
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

        public int getUserID()
        {
                return this.userID;
        }

        public void setUserID(int userID)
        {
                this.userID = userID;
        }

        public String toString()
        {
                return new ToStringBuilder(this).append("relationID", getRelationID())
                        .append("type", getType())
                        .append("userID", getUserID()).toString();
        }

        public boolean equals(Object other)
        {
                if ((this == other))
                {
                        return true;
                }

                if (!(other instanceof GraduationModelPK))
                {
                        return false;
                }

                GraduationModelPK castOther = (GraduationModelPK) other;

                return new EqualsBuilder().append(this.getRelationID(),
                        castOther.getRelationID())
                        .append(this.getType(), castOther.getType())
                        .append(this.getUserID(),
                                castOther.getUserID()).isEquals();
        }

        public int hashCode()
        {
                return new HashCodeBuilder().append(getRelationID()).append(getType())
                        .append(getUserID()).toHashCode();
        }
}
