package com.ulearning.ulms.video.model;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import com.ulearning.ulms.user.model.UserModel;


/**
 * table="T_videoUSER_TAB"
 */
public class VideouserModel implements Serializable
{

        /**
         * identifier field
         */
        private VideouserModelPK comp_id;

        /** nullable persistent field */

        /**
         * nullable persistent field
         */
        private UserModel uuserTab;

        /**
         * full constructor
         */
        public VideouserModel(VideouserModelPK comp_id, UserModel uuserTab)
        {
                this.comp_id = comp_id;
                this.uuserTab = uuserTab;
        }

        /**
         * default constructor
         */
        public VideouserModel()
        {
        }

        /**
         * minimal constructor
         */
        public VideouserModel(VideouserModelPK comp_id)
        {
                this.comp_id = comp_id;
        }

        /**
         * generator-class="assigned"
         */
        public VideouserModelPK getComp_id()
        {
                return this.comp_id;
        }

        public void setComp_id(VideouserModelPK comp_id)
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
/*
    public VideoModel getTvideoTab() {
        return this.tvideoTab;
    }

    public void setTvideoTab(VideoModel tvideoTab) {
        this.tvideoTab = tvideoTab;
    }
*/

        /**
         * update="false"
         * insert="false"
         * <p/>
         * <p/>
         * name="USERID"
         */
        public UserModel getUuserTab()
        {
                return this.uuserTab;
        }

        public void setUuserTab(UserModel uuserTab)
        {
                this.uuserTab = uuserTab;
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
                if (!(other instanceof VideouserModel))
                {
                        return false;
                }
                VideouserModel castOther = (VideouserModel) other;
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
