package com.ulearning.ulms.content.model;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import java.io.Serializable;


/**
 * @author Hibernate CodeGenerator
 */
public class ScormUserScoModelPK implements Serializable
{
        /**
         * identifier field
         */
        private int userID;

        /**
         * identifier field
         */
        private int scoID;

        /**
         * full constructor
         */
        public ScormUserScoModelPK(int userID, int scoID)
        {
                this.userID = userID;
                this.scoID = scoID;
        }

        /**
         * default constructor
         */
        public ScormUserScoModelPK()
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

        public int getScoID()
        {
                return this.scoID;
        }

        public void setScoID(int scoID)
        {
                this.scoID = scoID;
        }

        public String toString()
        {
                return new ToStringBuilder(this).append("userID", getUserID())
                        .append("scoID", getScoID()).toString();
        }

        public boolean equals(Object other)
        {
                if ((this == other))
                {
                        return true;
                }

                if (!(other instanceof ScormUserScoModelPK))
                {
                        return false;
                }

                ScormUserScoModelPK castOther = (ScormUserScoModelPK) other;

                return new EqualsBuilder().append(this.getUserID(),
                        castOther.getUserID()).append(this.getScoID(), castOther.getScoID())
                        .isEquals();
        }

        public int hashCode()
        {
                return new HashCodeBuilder().append(getUserID()).append(getScoID())
                        .toHashCode();
        }
}
