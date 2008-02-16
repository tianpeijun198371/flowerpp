/**
 * MessageUserModelPK.java.
 * User: keyh  Date: 2004-8-25
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.tools.message.model;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import java.io.Serializable;


/**
 * @author Hibernate CodeGenerator
 */
public class MessageUserModelPK implements Serializable
{
        /**
         * identifier field
         */
        private int messageid;

        /**
         * identifier field
         */
        private int userid;

        /**
         * full constructor
         */
        public MessageUserModelPK(int messageid, int userid)
        {
                this.messageid = messageid;
                this.userid = userid;
        }

        /**
         * default constructor
         */
        public MessageUserModelPK()
        {
        }

        public int getMessageid()
        {
                return this.messageid;
        }

        public void setMessageid(int messageid)
        {
                this.messageid = messageid;
        }

        public int getUserid()
        {
                return this.userid;
        }

        public void setUserid(int userid)
        {
                this.userid = userid;
        }

        public String toString()
        {
                return new ToStringBuilder(this).append("messageid", getMessageid())
                        .append("userid", getUserid()).toString();
        }

        public boolean equals(Object other)
        {
                if ((this == other))
                {
                        return true;
                }

                if (!(other instanceof MessageUserModelPK))
                {
                        return false;
                }

                MessageUserModelPK castOther = (MessageUserModelPK) other;

                return new EqualsBuilder().append(this.getMessageid(),
                        castOther.getMessageid())
                        .append(this.getUserid(),
                                castOther.getUserid()).isEquals();
        }

        public int hashCode()
        {
                return new HashCodeBuilder().append(getMessageid()).append(getUserid())
                        .toHashCode();
        }
}
