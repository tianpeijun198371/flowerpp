package com.ulearning.ulms.content.form;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import java.io.Serializable;


/**
 * @author Hibernate CodeGenerator
 */
public class ContentConfigFormPK implements Serializable
{
        /**
         * identifier field
         */
        private String type;

        /**
         * identifier field
         */
        private int relationID;

        /**
         * full constructor
         */
        public ContentConfigFormPK(String type, int relationID)
        {
                this.type = type;
                this.relationID = relationID;
        }

        /**
         * default constructor
         */
        public ContentConfigFormPK()
        {
        }

        public String getType()
        {
                return this.type;
        }

        public void setType(String type)
        {
                this.type = type;
        }

        public int getRelationID()
        {
                return this.relationID;
        }

        public void setRelationID(int relationID)
        {
                this.relationID = relationID;
        }

        public String toString()
        {
                return new ToStringBuilder(this).append("type", getType())
                        .append("relationID", getRelationID())
                        .toString();
        }

        public boolean equals(Object other)
        {
                if ((this == other))
                {
                        return true;
                }

                if (!(other instanceof ContentConfigFormPK))
                {
                        return false;
                }

                ContentConfigFormPK castOther = (ContentConfigFormPK) other;

                return new EqualsBuilder().append(this.getType(), castOther.getType())
                        .append(this.getRelationID(),
                                castOther.getRelationID()).isEquals();
        }

        public int hashCode()
        {
                return new HashCodeBuilder().append(getType()).append(getRelationID())
                        .toHashCode();
        }
}
