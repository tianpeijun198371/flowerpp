package com.ulearning.ulms.video.model;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import com.ulearning.ulms.organ.model.OrganModel;


/**
 * table="T_videoORGAN_TAB"
 */
public class VideoOrganModel implements Serializable
{

        /**
         * identifier field
         */
        private VideoOrganModelPK comp_id;

        /** nullable persistent field */

        /**
         * nullable persistent field
         */
        private OrganModel tmOrgTab;

        /**
         * full constructor
         */
        public VideoOrganModel(VideoOrganModelPK comp_id, OrganModel tmOrgTab)
        {
                this.comp_id = comp_id;
                this.tmOrgTab = tmOrgTab;
        }

        /**
         * default constructor
         */
        public VideoOrganModel()
        {
        }

        /**
         * minimal constructor
         */
        public VideoOrganModel(VideoOrganModelPK comp_id)
        {
                this.comp_id = comp_id;
        }

        /**
         * generator-class="assigned"
         */
        public VideoOrganModelPK getComp_id()
        {
                return this.comp_id;
        }

        public void setComp_id(VideoOrganModelPK comp_id)
        {
                this.comp_id = comp_id;
        }

        /**
         *
         *             update="false"
         *             insert="false"
         *
         *
         *             name="vclassid"
         *
         */
        /**
         * update="false"
         * insert="false"
         * <p/>
         * <p/>
         * name="ORGID"
         */
        public OrganModel getOrganModel()
        {
                return this.tmOrgTab;
        }

        public void setTmOrgTab(OrganModel organModel)
        {
                this.tmOrgTab = organModel;
        }

        public String toString()
        {
                return new ToStringBuilder(this)
                        .append("comp_id", getComp_id())
                        .toString();
        }

        public boolean equals(Object other)
        {
                if ((this == other))
                {
                        return true;
                }
                if (!(other instanceof VideoOrganModel))
                {
                        return false;
                }
                VideoOrganModel castOther = (VideoOrganModel) other;
                return new EqualsBuilder()
                        .append(this.getComp_id(), castOther.getComp_id())
                        .isEquals();
        }

        public int hashCode()
        {
                return new HashCodeBuilder()
                        .append(getComp_id())
                        .toHashCode();
        }

}
