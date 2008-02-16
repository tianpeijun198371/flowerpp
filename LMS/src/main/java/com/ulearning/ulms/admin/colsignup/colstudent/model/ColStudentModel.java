/**
 * Created by IntelliJ IDEA.
 * User: zengwj
 * Date: 2004-8-30
 * Time: 13:55:58
 * To change this template use File | Settings | File Templates.
 */
package com.ulearning.ulms.admin.colsignup.colstudent.model;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;


public class ColStudentModel
{
        /**
         * identifier field
         */
        private ColStudentModelPK comp_id;

        /**
         * persistent field
         */
        private String approved;

        /**
         * persistent field
         */
        private String feeState;

        public ColStudentModel(ColStudentModelPK comp_id, String approved)
        {
                this.comp_id = comp_id;
                this.approved = approved;
        }

        public ColStudentModel()
        {
        }

        public ColStudentModelPK getComp_id()
        {
                return comp_id;
        }

        public void setComp_id(ColStudentModelPK comp_id)
        {
                this.comp_id = comp_id;
        }

        public String getApproved()
        {
                return approved;
        }

        public void setApproved(String approved)
        {
                this.approved = approved;
        }

        public String getFeeState()
        {
                return feeState;
        }

        public void setFeeState(String feeState)
        {
                this.feeState = feeState;
        }

        /**
         * nullable persistent field
         */
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

                if (!(other instanceof ColStudentModel))
                {
                        return false;
                }

                ColStudentModel castOther = (ColStudentModel) other;

                return new EqualsBuilder().append(this.getComp_id(),
                        castOther.getComp_id()).isEquals();
        }

        public int hashCode()
        {
                return new HashCodeBuilder().append(getComp_id()).toHashCode();
        }
}
