package com.ulearning.ulms.course.test.grade.model;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import java.io.Serializable;

import java.util.Date;


/**
 * @author Hibernate CodeGenerator
 */
public class PaperUserModel implements Serializable
{
        /**
         * identifier field
         */
        private PaperUserModelPK comp_id;

        /**
         * persistent field
         */
        private int type;

        /**
         * nullable persistent field
         */
        private float subjective;

        /**
         * nullable persistent field
         */
        private float objective;

        /**
         * nullable persistent field
         */
        private float grade;

        /**
         * nullable persistent field
         */
        private int status;

        /**
         * nullable persistent field
         */
        private Date starttime;

        /**
         * nullable persistent field
         */
        private Date endtime;

        /**
         * nullable persistent field
         */
        private String description;

        /**
         * nullable persistent field
         */
        private String desc1;

        /**
         * nullable persistent field
         */
        private String desc2;

        /**
         * nullable persistent field
         */
        private String desc3;

        /**
         * nullable persistent field
         */
        private String desc4;

        /**
         * nullable persistent field
         */
        private int paperID;

        /**
         * full constructor
         */
        public PaperUserModel(PaperUserModelPK comp_id, int type, float subjective,
                              float objective, float grade, int status, Date starttime, Date endtime,
                              String description, String desc1, String desc2, String desc3,
                              String desc4, int paperID)
        {
                this.comp_id = comp_id;
                this.type = type;
                this.subjective = subjective;
                this.objective = objective;
                this.grade = grade;
                this.status = status;
                this.starttime = starttime;
                this.endtime = endtime;
                this.description = description;
                this.desc1 = desc1;
                this.desc2 = desc2;
                this.desc3 = desc3;
                this.desc4 = desc4;
                this.paperID = paperID;
        }

        /**
         * default constructor
         */
        public PaperUserModel()
        {
        }

        /**
         * minimal constructor
         */
        public PaperUserModel(PaperUserModelPK comp_id, int type)
        {
                this.comp_id = comp_id;
                this.type = type;
        }

        public PaperUserModelPK getComp_id()
        {
                return this.comp_id;
        }

        public void setComp_id(PaperUserModelPK comp_id)
        {
                this.comp_id = comp_id;
        }

        public int getType()
        {
                return this.type;
        }

        public void setType(int type)
        {
                this.type = type;
        }

        public float getSubjective()
        {
                return this.subjective;
        }

        public void setSubjective(float subjective)
        {
                this.subjective = subjective;
        }

        public float getObjective()
        {
                return this.objective;
        }

        public void setObjective(float objective)
        {
                this.objective = objective;
        }

        public float getGrade()
        {
                return this.grade;
        }

        public void setGrade(float grade)
        {
                this.grade = grade;
        }

        public int getStatus()
        {
                return this.status;
        }

        public void setStatus(int status)
        {
                this.status = status;
        }

        public Date getStarttime()
        {
                return this.starttime;
        }

        public void setStarttime(Date starttime)
        {
                this.starttime = starttime;
        }

        public Date getEndtime()
        {
                return this.endtime;
        }

        public void setEndtime(Date endtime)
        {
                this.endtime = endtime;
        }

        public String getDescription()
        {
                return this.description;
        }

        public void setDescription(String description)
        {
                this.description = description;
        }

        public String getDesc1()
        {
                return this.desc1;
        }

        public void setDesc1(String desc1)
        {
                this.desc1 = desc1;
        }

        public String getDesc2()
        {
                return this.desc2;
        }

        public void setDesc2(String desc2)
        {
                this.desc2 = desc2;
        }

        public String getDesc3()
        {
                return this.desc3;
        }

        public void setDesc3(String desc3)
        {
                this.desc3 = desc3;
        }

        public String getDesc4()
        {
                return this.desc4;
        }

        public void setDesc4(String desc4)
        {
                this.desc4 = desc4;
        }

        public int getTpaperTab()
        {
                return this.paperID;
        }

        public void setTpaperTab(int paperID)
        {
                this.paperID = paperID;
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

                if (!(other instanceof PaperUserModel))
                {
                        return false;
                }

                PaperUserModel castOther = (PaperUserModel) other;

                return new EqualsBuilder().append(this.getComp_id(),
                        castOther.getComp_id()).isEquals();
        }

        public int hashCode()
        {
                return new HashCodeBuilder().append(getComp_id()).toHashCode();
        }
}
