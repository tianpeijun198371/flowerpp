/**
 * TypeCoursePK.java.
 * User: keyh  Date: 2004-9-2
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.course.courseconfig.model;

import com.ulearning.ulms.tools.message.model.MessageUserModelPK;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import java.io.Serializable;


public class TypeRelationIDPK implements Serializable
{
        private int typeid;
        private int relationid;

        public TypeRelationIDPK()
        {
        }

        public TypeRelationIDPK(int typeid, int relationid)
        {
                this.typeid = typeid;
                this.relationid = relationid;
        }

        public int getTypeid()
        {
                return typeid;
        }

        public void setTypeid(int typeid)
        {
                this.typeid = typeid;
        }

        public int getRelationid()
        {
                return relationid;
        }

        public void setRelationid(int relationid)
        {
                this.relationid = relationid;
        }

        public String toString()
        {
                return new ToStringBuilder(this).append("typeid", getTypeid())
                        .append("relationid", getRelationid())
                        .toString();
        }

        public boolean equals(Object other)
        {
                if ((this == other))
                {
                        return true;
                }

                if (!(other instanceof TypeRelationIDPK))
                {
                        return false;
                }

                TypeRelationIDPK castOther = (TypeRelationIDPK) other;

                return new EqualsBuilder().append(this.getTypeid(),
                        castOther.getTypeid())
                        .append(this.getRelationid(),
                                castOther.getRelationid()).isEquals();
        }

        public int hashCode()
        {
                return new HashCodeBuilder().append(getTypeid()).append(getRelationid())
                        .toHashCode();
        }
}
