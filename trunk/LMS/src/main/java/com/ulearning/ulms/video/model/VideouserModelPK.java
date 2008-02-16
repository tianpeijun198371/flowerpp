package com.ulearning.ulms.video.model;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;


/**
 * @author Hibernate CodeGenerator
 */
public class VideouserModelPK implements Serializable
{

        /**
         * identifier field
         */
        private int vclassid;

        /**
         * identifier field
         */
        private int userid;

        /**
         * identifier field
         */
        private String type;

        /**
         * full constructor
         */
        public VideouserModelPK(int vclassid, int userid, String type)
        {
                this.vclassid = vclassid;
                this.userid = userid;
                this.type = type;
        }

        /**
         * default constructor
         */
        public VideouserModelPK()
        {
        }

        /**
         * column="vclassid"
         * length="22"
         */
        public int getvclassid()
        {
                return this.vclassid;
        }

        public void setvclassid(int vclassid)
        {
                this.vclassid = vclassid;
        }

        /**
         * column="USERID"
         * length="22"
         */
        public int getUserid()
        {
                return this.userid;
        }

        public void setUserid(int userid)
        {
                this.userid = userid;
        }

        /**
         * column="TYPE"
         * length="1"
         */
        public String getType()
        {
                return this.type;
        }

        public void setType(String type)
        {
                this.type = type;
        }

        public String toString()
        {
                return new ToStringBuilder(this)
                        .append("vclassid", getvclassid())
                        .append("userid", getUserid())
                        .append("type", getType())
                        .toString();
        }

        public boolean equals(Object other)
        {
                if ((this == other))
                {
                        return true;
                }
                if (!(other instanceof VideouserModelPK))
                {
                        return false;
                }
                VideouserModelPK castOther = (VideouserModelPK) other;
                return new EqualsBuilder()
                        .append(this.getvclassid(), castOther.getvclassid())
                        .append(this.getUserid(), castOther.getUserid())
                        .append(this.getType(), castOther.getType())
                        .isEquals();
        }

        public int hashCode()
        {
                return new HashCodeBuilder()
                        .append(getvclassid())
                        .append(getUserid())
                        .append(getType())
                        .toHashCode();
        }

}
