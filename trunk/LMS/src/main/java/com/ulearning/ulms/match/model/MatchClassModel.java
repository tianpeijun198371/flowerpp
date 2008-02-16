/**
 * MatchClassModel.java.
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


public class MatchClassModel implements Serializable
{
        /**
         * identifier field
         */
        private MatchClassModelPK comp_id;

        /**
         * Type==0: Modele=ASP，针对ASP进行匹配
         * Type==1: Modele=Course，针对课程进行匹配
         * Type==2: Modele=Certificate，针对证书进行匹配
         */
        private String type;

        /**
         * persistent field
         */
        private int frequenceid;

        /**
         * full constructor
         */
        public MatchClassModel(MatchClassModelPK comp_id, String type,
                               int frequenceid)
        {
                this.comp_id = comp_id;
                this.type = type;
                this.frequenceid = frequenceid;
        }

        /**
         * default constructor
         */
        public MatchClassModel()
        {
        }

        /**
         * minimal constructor
         */
        public MatchClassModel(MatchClassModelPK comp_id, int frequenceid)
        {
                this.comp_id = comp_id;
                this.frequenceid = frequenceid;
        }

        public MatchClassModelPK getComp_id()
        {
                return this.comp_id;
        }

        public void setComp_id(MatchClassModelPK comp_id)
        {
                this.comp_id = comp_id;
        }

        public String getType()
        {
                return this.type;
        }

        public void setType(String type)
        {
                this.type = type;
        }

        public int getFrequenceid()
        {
                return this.frequenceid;
        }

        public void setFrequenceid(int frequenceid)
        {
                this.frequenceid = frequenceid;
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

                if (!(other instanceof MatchClassModel))
                {
                        return false;
                }

                MatchClassModel castOther = (MatchClassModel) other;

                return new EqualsBuilder().append(this.getComp_id(),
                        castOther.getComp_id()).isEquals();
        }

        public int hashCode()
        {
                return new HashCodeBuilder().append(getComp_id()).toHashCode();
        }
}
