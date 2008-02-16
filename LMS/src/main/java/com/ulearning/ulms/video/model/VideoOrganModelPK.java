package com.ulearning.ulms.video.model;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;


/**
 * @author Hibernate CodeGenerator
 */
public class VideoOrganModelPK implements Serializable
{

        /**
         * identifier field
         */
        private int vclassid;

        /**
         * identifier field
         */
        private int orgid;

        /**
         * identifier field
         */
        private String type;

        /**
         * full constructor
         */
        public VideoOrganModelPK(int vclassid, int orgid, String type)
        {
                this.vclassid = vclassid;
                this.orgid = orgid;
                this.type = type;
        }

        /**
         * default constructor
         */
        public VideoOrganModelPK()
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
         * column="ORGID"
         * length="22"
         */
        public int getOrgid()
        {
                return this.orgid;
        }

        public void setOrgid(int orgid)
        {
                this.orgid = orgid;
        }

        /**
         * column="TYPE"
         * length="22"
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
                        .append("orgid", getOrgid())
                        .append("type", getType())
                        .toString();
        }

        public boolean equals(Object other)
        {
                if ((this == other))
                {
                        return true;
                }
                if (!(other instanceof VideoOrganModelPK))
                {
                        return false;
                }
                VideoOrganModelPK castOther = (VideoOrganModelPK) other;
                return new EqualsBuilder()
                        .append(this.getvclassid(), castOther.getvclassid())
                        .append(this.getOrgid(), castOther.getOrgid())
                        .append(this.getType(), castOther.getType())
                        .isEquals();
        }

        public int hashCode()
        {
                return new HashCodeBuilder()
                        .append(getvclassid())
                        .append(getOrgid())
                        .append(getType())
                        .toHashCode();
        }

}
