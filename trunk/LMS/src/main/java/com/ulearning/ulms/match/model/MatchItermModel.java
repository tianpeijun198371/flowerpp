/**
 * MatchItermModel.java.
 * User: zhangy Date: 2005-6-2 14:32:34
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.match.model;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import java.io.Serializable;


public class MatchItermModel implements Serializable
{
        /**
         * identifier field
         */
        private MatchItermModelPK comp_id;

        /**
         * nullable persistent field
         */
        private String matchvalue;

        /**
         * nullable persistent field
         */
        private String operator;

        /**
         * full constructor
         */
        public MatchItermModel(MatchItermModelPK comp_id, String matchvalue,
                               String operator)
        {
                this.comp_id = comp_id;
                this.matchvalue = matchvalue;
                this.operator = operator;
        }

        /**
         * default constructor
         */
        public MatchItermModel()
        {
        }

        /**
         * minimal constructor
         */
        public MatchItermModel(MatchItermModelPK comp_id)
        {
                this.comp_id = comp_id;
        }

        public MatchItermModelPK getComp_id()
        {
                return this.comp_id;
        }

        public void setComp_id(MatchItermModelPK comp_id)
        {
                this.comp_id = comp_id;
        }

        public String getMatchvalue()
        {
                return this.matchvalue;
        }

        public void setMatchvalue(String matchvalue)
        {
                this.matchvalue = matchvalue;
        }

        public String getOperator()
        {
                return this.operator;
        }

        public void setOperator(String operator)
        {
                this.operator = operator;
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

                if (!(other instanceof MatchItermModel))
                {
                        return false;
                }

                MatchItermModel castOther = (MatchItermModel) other;

                return new EqualsBuilder().append(this.getComp_id(),
                        castOther.getComp_id()).isEquals();
        }

        public int hashCode()
        {
                return new HashCodeBuilder().append(getComp_id()).toHashCode();
        }
}
