package com.ulearning.ulms.admin.topTen.model;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import java.io.Serializable;

import java.util.Date;


/**
 * @author Hibernate CodeGenerator
 */
public class TopTenModel implements Serializable
{
        /**
         * identifier field
         */
        private int id;

        /**
         * identifier field
         */
        private String name;

        /**
         * identifier field
         */
        private String type;

        /**
         * identifier field
         */
        private String ct;

        /**
         * identifier field
         */
        private String duty;

        /**
         * identifier field
         */
        private int grade;

        /**
         * identifier field
         */
        private String link;

        /**
         * identifier field
         */
        private String description;

        /**
         * identifier field
         */
        private String isDisplay;

        /**
         * identifier field
         */
        private Date creattime;

        /**
         * identifier field
         */
        private String desc0;

        /**
         * identifier field
         */
        private String desc1;

        /**
         * full constructor
         */
        public TopTenModel(int id, String name, String type, String ct,
                           String duty, int grade, String link, String description,
                           String isDisplay, Date creattime, String desc0, String desc1)
        {
                this.id = id;
                this.name = name;
                this.type = type;
                this.ct = ct;
                this.duty = duty;
                this.grade = grade;
                this.link = link;
                this.description = description;
                this.isDisplay = isDisplay;
                this.creattime = creattime;
                this.desc0 = desc0;
                this.desc1 = desc1;
        }

        /**
         * default constructor
         */
        public TopTenModel()
        {
        }

        public int getId()
        {
                return this.id;
        }

        public void setId(int id)
        {
                this.id = id;
        }

        public String getName()
        {
                return this.name;
        }

        public void setName(String name)
        {
                this.name = name;
        }

        public String getType()
        {
                return this.type;
        }

        public void setType(String type)
        {
                this.type = type;
        }

        public String getCt()
        {
                return this.ct;
        }

        public void setCt(String ct)
        {
                this.ct = ct;
        }

        public String getDuty()
        {
                return this.duty;
        }

        public void setDuty(String duty)
        {
                this.duty = duty;
        }

        public int getGrade()
        {
                return this.grade;
        }

        public void setGrade(int grade)
        {
                this.grade = grade;
        }

        public String getLink()
        {
                return this.link;
        }

        public void setLink(String link)
        {
                this.link = link;
        }

        public String getDescription()
        {
                return this.description;
        }

        public void setDescription(String description)
        {
                this.description = description;
        }

        public String getIsDisplay()
        {
                return this.isDisplay;
        }

        public void setIsDisplay(String isDisplay)
        {
                this.isDisplay = isDisplay;
        }

        public Date getCreattime()
        {
                return this.creattime;
        }

        public void setCreattime(Date creattime)
        {
                this.creattime = creattime;
        }

        public String getDesc0()
        {
                return this.desc0;
        }

        public void setDesc0(String desc0)
        {
                this.desc0 = desc0;
        }

        public String getDesc1()
        {
                return this.desc1;
        }

        public void setDesc1(String desc1)
        {
                this.desc1 = desc1;
        }

        public String toString()
        {
                return new ToStringBuilder(this).append("id", getId())
                        .append("name", getName())
                        .append("type", getType())
                        .append("ct", getCt())
                        .append("duty", getDuty())
                        .append("grade", getGrade())
                        .append("link", getLink())
                        .append("description", getDescription())
                        .append("isDisplay", getIsDisplay())
                        .append("creattime", getCreattime())
                        .append("desc0", getDesc0())
                        .append("desc1", getDesc1()).toString();
        }

        public boolean equals(Object other)
        {
                if ((this == other))
                {
                        return true;
                }

                if (!(other instanceof TopTenModel))
                {
                        return false;
                }

                TopTenModel castOther = (TopTenModel) other;

                return new EqualsBuilder().append(this.getId(), castOther.getId())
                        .append(this.getName(), castOther.getName())
                        .append(this.getType(), castOther.getType())
                        .append(this.getCt(), castOther.getCt())
                        .append(this.getDuty(), castOther.getDuty())
                        .append(this.getGrade(), castOther.getGrade())
                        .append(this.getLink(), castOther.getLink())
                        .append(this.getDescription(),
                                castOther.getDescription())
                        .append(this.getIsDisplay(),
                                castOther.getIsDisplay())
                        .append(this.getCreattime(),
                                castOther.getCreattime())
                        .append(this.getDesc0(), castOther.getDesc0())
                        .append(this.getDesc1(), castOther.getDesc1())
                        .isEquals();
        }

        public int hashCode()
        {
                return new HashCodeBuilder().append(getId()).append(getName())
                        .append(getType()).append(getCt())
                        .append(getDuty()).append(getGrade())
                        .append(getLink()).append(getDescription())
                        .append(getIsDisplay())
                        .append(getCreattime()).append(getDesc0())
                        .append(getDesc1()).toHashCode();
        }
}
