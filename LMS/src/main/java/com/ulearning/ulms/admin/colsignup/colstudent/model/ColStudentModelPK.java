package com.ulearning.ulms.admin.colsignup.colstudent.model;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import java.io.Serializable;


/**
 * @author Hibernate CodeGenerator
 */
public class ColStudentModelPK implements Serializable
{
        /**
         * identifier field
         */
        private int colSignDetailID;

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
        public ColStudentModelPK(int colSignDetailID, int relationID, int type)
        {
                this.colSignDetailID = colSignDetailID;
                this.relationID = relationID;
                this.type = type;
        }

        /**
         * default constructor
         */
        public ColStudentModelPK()
        {
        }

        public int getColSignDetailID()
        {
                return this.colSignDetailID;
        }

        public void setColSignDetailID(int colSignDetailID)
        {
                this.colSignDetailID = colSignDetailID;
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
                return new ToStringBuilder(this).append("colSignDetailID",
                        getColSignDetailID()).append("relationID", getRelationID())
                        .append("type", getType()).toString();
        }

        public boolean equals(Object other)
        {
                if ((this == other))
                {
                        return true;
                }

                if (!(other instanceof ColStudentModelPK))
                {
                        return false;
                }

                ColStudentModelPK castOther = (ColStudentModelPK) other;

                return new EqualsBuilder().append(this.getColSignDetailID(),
                        castOther.getColSignDetailID())
                        .append(this.getRelationID(),
                                castOther.getRelationID())
                        .append(this.getType(), castOther.getType())
                        .isEquals();
        }

        public int hashCode()
        {
                return new HashCodeBuilder().append(getColSignDetailID())
                        .append(getRelationID()).append(getType())
                        .toHashCode();
        }
}
