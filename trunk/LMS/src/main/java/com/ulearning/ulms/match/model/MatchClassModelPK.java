/**
 * MatchClassModelPK.java.
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
public class MatchClassModelPK implements Serializable
{
        /**
         * identifier field
         */
        private int matchid;

        /**
         * identifier field
         */
        private int modeleid;

        /**
         * full constructor
         */
        public MatchClassModelPK(int matchid, int modeleid)
        {
                this.matchid = matchid;
                this.modeleid = modeleid;
        }

        /**
         * default constructor
         */
        public MatchClassModelPK()
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

        public int getModeleid()
        {
                return this.modeleid;
        }

        public void setModeleid(int modeleid)
        {
                this.modeleid = modeleid;
        }

        public String toString()
        {
                return new ToStringBuilder(this).append("matchid", getMatchid())
                        .append("modeleid", getModeleid())
                        .toString();
        }

        public boolean equals(Object other)
        {
                if ((this == other))
                {
                        return true;
                }

                if (!(other instanceof MatchClassModelPK))
                {
                        return false;
                }

                MatchClassModelPK castOther = (MatchClassModelPK) other;

                return new EqualsBuilder().append(this.getMatchid(),
                        castOther.getMatchid())
                        .append(this.getModeleid(),
                                castOther.getModeleid()).isEquals();
        }

        public int hashCode()
        {
                return new HashCodeBuilder().append(getMatchid()).append(getModeleid())
                        .toHashCode();
        }
}
