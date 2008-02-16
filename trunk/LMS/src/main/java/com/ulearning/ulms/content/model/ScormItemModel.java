package com.ulearning.ulms.content.model;

import org.apache.commons.lang.builder.ToStringBuilder;

import java.io.Serializable;


/**
 * @author Hibernate CodeGenerator
 */
public class ScormItemModel implements Serializable
{
        /**
         * identifier field
         */
        private int itemID;

        /**
         * persistent field
         */
        private int relationID;

        /**
         * persistent field
         */
        private String type;

        /**
         * nullable persistent field
         */
        private String identifier;

        /**
         * nullable persistent field
         */
        private String itemType;

        /**
         * nullable persistent field
         */
        private String title;

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
        private String dataFromLms;

        /**
         * nullable persistent field
         */
        private String prerequisites;

        /**
         * nullable persistent field
         */
        private int masteryScore;

        /**
         * nullable persistent field
         */
        private String maxTimeAllowed;

        /**
         * nullable persistent field
         */
        private String timeLimitAction;

        /**
         * nullable persistent field
         */
        private int sequence;

        /**
         * nullable persistent field
         */
        private int theLevel;

        /**
         * persistent field
         */
        private int contentID;

        /**
         * full constructor
         */
        public ScormItemModel(int relationID, String type, String identifier,
                              String itemType, String title, String launch, String parameterString,
                              String dataFromLms, String prerequisites, int masteryScore,
                              String maxTimeAllowed, String timeLimitAction, int sequence,
                              int theLevel, int contentID)
        {
                this.relationID = relationID;
                this.type = type;
                this.identifier = identifier;
                this.itemType = itemType;
                this.title = title;
                this.launch = launch;
                this.parameterString = parameterString;
                this.dataFromLms = dataFromLms;
                this.prerequisites = prerequisites;
                this.masteryScore = masteryScore;
                this.maxTimeAllowed = maxTimeAllowed;
                this.timeLimitAction = timeLimitAction;
                this.sequence = sequence;
                this.theLevel = theLevel;
                this.contentID = contentID;
        }

        /**
         * default constructor
         */
        public ScormItemModel()
        {
        }

        /**
         * minimal constructor
         */
        public ScormItemModel(int relationID, String type, int contentID)
        {
                this.relationID = relationID;
                this.type = type;
                this.contentID = contentID;
        }

        public int getItemID()
        {
                return this.itemID;
        }

        public void setItemID(int itemID)
        {
                this.itemID = itemID;
        }

        public int getRelationID()
        {
                return this.relationID;
        }

        public void setRelationID(int relationID)
        {
                this.relationID = relationID;
        }

        public String getType()
        {
                return this.type;
        }

        public void setType(String type)
        {
                this.type = type;
        }

        public String getIdentifier()
        {
                return this.identifier;
        }

        public void setIdentifier(String identifier)
        {
                this.identifier = identifier;
        }

        public String getItemType()
        {
                return this.itemType;
        }

        public void setItemType(String itemType)
        {
                this.itemType = itemType;
        }

        public String getTitle()
        {
                return this.title;
        }

        public void setTitle(String title)
        {
                this.title = title;
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

        public String getDataFromLms()
        {
                return this.dataFromLms;
        }

        public void setDataFromLms(String dataFromLms)
        {
                this.dataFromLms = dataFromLms;
        }

        public String getPrerequisites()
        {
                return this.prerequisites;
        }

        public void setPrerequisites(String prerequisites)
        {
                this.prerequisites = prerequisites;
        }

        public int getMasteryScore()
        {
                return this.masteryScore;
        }

        public void setMasteryScore(int masteryScore)
        {
                this.masteryScore = masteryScore;
        }

        public String getMaxTimeAllowed()
        {
                return this.maxTimeAllowed;
        }

        public void setMaxTimeAllowed(String maxTimeAllowed)
        {
                this.maxTimeAllowed = maxTimeAllowed;
        }

        public String getTimeLimitAction()
        {
                return this.timeLimitAction;
        }

        public void setTimeLimitAction(String timeLimitAction)
        {
                this.timeLimitAction = timeLimitAction;
        }

        public int getSequence()
        {
                return this.sequence;
        }

        public void setSequence(int sequence)
        {
                this.sequence = sequence;
        }

        public int getTheLevel()
        {
                return this.theLevel;
        }

        public void setTheLevel(int theLevel)
        {
                this.theLevel = theLevel;
        }

        public int getContentID()
        {
                return this.contentID;
        }

        public void setContentID(int contentID)
        {
                this.contentID = contentID;
        }

        public String toString()
        {
                return new ToStringBuilder(this).append("itemID", getItemID()).toString();
        }
}
