package com.ulearning.ulms.content.model;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import java.io.Serializable;


/**
 * @author Hibernate CodeGenerator
 */
public class ScormUserScoModel implements Serializable
{
        /**
         * identifier field
         */
        private com.ulearning.ulms.content.model.ScormUserScoModelPK comp_id;

        /**
         * nullable persistent field
         */
        private String launch;

        /**
         * nullable persistent field
         */
        private String parameterString;

        /**
         * nullable persistent field
         */
        private String lessonStatus;

        /**
         * nullable persistent field
         */
        private String prerequisites;

        /**
         * nullable persistent field
         */
        private String exitString;

        /**
         * nullable persistent field
         */
        private String entry;

        /**
         * nullable persistent field
         */
        private int masteryScore;

        /**
         * nullable persistent field
         */
        private int sequence;

        /**
         * nullable persistent field
         */
        private String itemType;

        /**
         * full constructor
         */
        public ScormUserScoModel(
                com.ulearning.ulms.content.model.ScormUserScoModelPK comp_id,
                String launch, String parameterString, String lessonStatus,
                String prerequisites, String exitString, String entry,
                int masteryScore, int sequence, String itemType)
        {
                this.comp_id = comp_id;
                this.launch = launch;
                this.parameterString = parameterString;
                this.lessonStatus = lessonStatus;
                this.prerequisites = prerequisites;
                this.exitString = exitString;
                this.entry = entry;
                this.masteryScore = masteryScore;
                this.sequence = sequence;
                this.itemType = itemType;
        }

        /**
         * default constructor
         */
        public ScormUserScoModel()
        {
        }

        /**
         * minimal constructor
         */
        public ScormUserScoModel(
                com.ulearning.ulms.content.model.ScormUserScoModelPK comp_id)
        {
                this.comp_id = comp_id;
        }

        public com.ulearning.ulms.content.model.ScormUserScoModelPK getComp_id()
        {
                return this.comp_id;
        }

        public void setComp_id(
                com.ulearning.ulms.content.model.ScormUserScoModelPK comp_id)
        {
                this.comp_id = comp_id;
        }

        public String getLaunch()
        {
                return this.launch;
        }

        public void setLaunch(String launch)
        {
                this.launch = launch;
        }

        public String getParameterString()
        {
                return this.parameterString;
        }

        public void setParameterString(String parameterString)
        {
                this.parameterString = parameterString;
        }

        public String getLessonStatus()
        {
                return this.lessonStatus;
        }

        public void setLessonStatus(String lessonStatus)
        {
                this.lessonStatus = lessonStatus;
        }

        public String getPrerequisites()
        {
                return this.prerequisites;
        }

        public void setPrerequisites(String prerequisites)
        {
                this.prerequisites = prerequisites;
        }

        public String getExitString()
        {
                return this.exitString;
        }

        public void setExitString(String exitString)
        {
                this.exitString = exitString;
        }

        public String getEntry()
        {
                return this.entry;
        }

        public void setEntry(String entry)
        {
                this.entry = entry;
        }

        public int getMasteryScore()
        {
                return this.masteryScore;
        }

        public void setMasteryScore(int masteryScore)
        {
                this.masteryScore = masteryScore;
        }

        public int getSequence()
        {
                return this.sequence;
        }

        public void setSequence(int sequence)
        {
                this.sequence = sequence;
        }

        public String getItemType()
        {
                return this.itemType;
        }

        public void setItemType(String itemType)
        {
                this.itemType = itemType;
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

                if (!(other instanceof ScormUserScoModel))
                {
                        return false;
                }

                ScormUserScoModel castOther = (ScormUserScoModel) other;

                return new EqualsBuilder().append(this.getComp_id(),
                        castOther.getComp_id()).isEquals();
        }

        public int hashCode()
        {
                return new HashCodeBuilder().append(getComp_id()).toHashCode();
        }
}
