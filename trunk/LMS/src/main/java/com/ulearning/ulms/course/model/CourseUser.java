/**
 * Created by IntelliJ IDEA.
 * User: zengwj
 * Date: 2004-8-31
 * Time: 10:08:09
 * To change this template use File | Settings | File Templates.
 */
package com.ulearning.ulms.course.model;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import java.io.Serializable;

import java.util.Date;


public class CourseUser implements Serializable
{
        /**
         * identifier field
         */
        private CourseUserPK comp_id;

        /**
         * nullable persistent field
         */
        private String state;

        /**
         * nullable persistent field
         */
        private Date applyTime;

        /**
         * nullable persistent field
         */
        private Date joinTime;

        /**
         * nullable persistent field
         */
        private Date finishedTime;

        /**
         * full constructor
         */
        public CourseUser(CourseUserPK comp_id, String state, Date applyTime,
                          Date joinTime, Date finishedTime)
        {
                this.comp_id = comp_id;
                this.state = state;
                this.applyTime = applyTime;
                this.joinTime = joinTime;
                this.finishedTime = finishedTime;
        }

        /**
         * default constructor
         */
        public CourseUser()
        {
        }

        /**
         * minimal constructor
         */
        public CourseUser(CourseUserPK comp_id)
        {
                this.comp_id = comp_id;
        }

        public CourseUserPK getComp_id()
        {
                return this.comp_id;
        }

        public void setComp_id(CourseUserPK comp_id)
        {
                this.comp_id = comp_id;
        }

        public String getState()
        {
                return this.state;
        }

        public void setState(String state)
        {
                this.state = state;
        }

        public Date getApplyTime()
        {
                return this.applyTime;
        }

        public void setApplyTime(Date applyTime)
        {
                this.applyTime = applyTime;
        }

        public Date getJoinTime()
        {
                return this.joinTime;
        }

        public void setJoinTime(Date joinTime)
        {
                this.joinTime = joinTime;
        }

        public Date getFinishedTime()
        {
                return this.finishedTime;
        }

        public void setFinishedTime(Date finishedTime)
        {
                this.finishedTime = finishedTime;
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

                if (!(other instanceof CourseUser))
                {
                        return false;
                }

                CourseUser castOther = (CourseUser) other;

                return new EqualsBuilder().append(this.getComp_id(),
                        castOther.getComp_id()).isEquals();
        }

        public int hashCode()
        {
                return new HashCodeBuilder().append(getComp_id()).toHashCode();
        }
}
