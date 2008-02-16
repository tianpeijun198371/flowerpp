/**
 * MatchItermModelPK.java.
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


/**
 * @author Hibernate CodeGenerator
 */
public class MatchItermModelPK implements Serializable
{
        /**
         * identifier field
         */
        private int matchid;

        /**
         * identifier field
         */
        private int itermid;

        /**
         * full constructor
         */
        public MatchItermModelPK(int matchid, int itermid)
        {
                this.matchid = matchid;
                this.itermid = itermid;
        }

        /**
         * default constructor
         */
        public MatchItermModelPK()
        {
        }

        public int getMatchid()
        {
                return this.matchid;
        }

        public void setMatchid(int matchid)
        {
                this.matchid = matchid;
        }

        public int getItermid()
        {
                return this.itermid;
        }

        public void setItermid(int itermid)
        {
                this.itermid = itermid;
        }

        public String toString()
        {
                return new ToStringBuilder(this).append("matchid", getMatchid())
                        .append("itermid", getItermid())
                        .toString();
        }

        public boolean equals(Object other)
        {
                if ((this == other))
                {
                        return true;
                }

                if (!(other instanceof MatchItermModelPK))
                {
                        return false;
                }

                MatchItermModelPK castOther = (MatchItermModelPK) other;

                return new EqualsBuilder().append(this.getMatchid(),
                        castOther.getMatchid())
                        .append(this.getItermid(),
                                castOther.getItermid()).isEquals();
        }

        public int hashCode()
        {
                return new HashCodeBuilder().append(getMatchid()).append(getItermid())
                        .toHashCode();
        }
}
