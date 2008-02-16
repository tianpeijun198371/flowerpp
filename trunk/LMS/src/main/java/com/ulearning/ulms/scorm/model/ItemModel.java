/**
 * ItemModel.java.
 * User: fengch  Date: 2004-7-28
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.scorm.model;

import java.io.Serializable;


public class ItemModel implements Serializable
{
        private int itemID;
        private int courseContentNodeID;
        private int relationID;
        private int type;
        private String identifier;
        private String itemType;
        private String title;
        private String launch;
        private String parameterString;
        private String dataFromLMS;
        private String prerequisites;
        private float masteryScore;
        private String maxTimeAllowed;
        private String timeLimitAction;
        private int sequence;
        private int theLevel;

        public ItemModel()
        {
        }

        public ItemModel(int itemID, int courseContentNodeID, int relationID,
                         int type, String identifier, String itemType, String title,
                         String launch, String parameterString, String dataFromLMS,
                         String prerequisites, float masteryScore, String maxTimeAllowed,
                         String timeLimitAction, int sequence, int theLevel)
        {
                this.itemID = itemID;
                this.courseContentNodeID = courseContentNodeID;
                this.relationID = relationID;
                this.type = type;
                this.identifier = identifier;
                this.itemType = itemType;
                this.title = title;
                this.launch = launch;
                this.parameterString = parameterString;
                this.dataFromLMS = dataFromLMS;
                this.prerequisites = prerequisites;
                this.masteryScore = masteryScore;
                this.maxTimeAllowed = maxTimeAllowed;
                this.timeLimitAction = timeLimitAction;
                this.sequence = sequence;
                this.theLevel = theLevel;
        }

        public int getItemID()
        {
                return itemID;
        }

        public void setItemID(int itemID)
        {
                this.itemID = itemID;
        }

        public int getCourseContentNodeID()
        {
                return courseContentNodeID;
        }

        public void setCourseContentNodeID(int courseContentNodeID)
        {
                this.courseContentNodeID = courseContentNodeID;
        }

        public int getRelationID()
        {
                return relationID;
        }

        public void setRelationID(int relationID)
        {
                this.relationID = relationID;
        }

        public int getType()
        {
                return type;
        }

        public void setType(int type)
        {
                this.type = type;
        }

        public String getIdentifier()
        {
                return identifier;
        }

        public void setIdentifier(String identifier)
        {
                this.identifier = identifier;
        }

        public String getItemType()
        {
                return itemType;
        }

        public void setItemType(String itemType)
        {
                this.itemType = itemType;
        }

        public String getTitle()
        {
                return title;
        }

        public void setTitle(String title)
        {
                this.title = title;
        }

        public String getLaunch()
        {
                return launch;
        }

        public void setLaunch(String launch)
        {
                this.launch = launch;
        }

        public String getParameterString()
        {
                return parameterString;
        }

        public void setParameterString(String parameterString)
        {
                this.parameterString = parameterString;
        }

        public String getDataFromLMS()
        {
                return dataFromLMS;
        }

        public void setDataFromLMS(String dataFromLMS)
        {
                this.dataFromLMS = dataFromLMS;
        }

        public String getPrerequisites()
        {
                return prerequisites;
        }

        public void setPrerequisites(String prerequisites)
        {
                this.prerequisites = prerequisites;
        }

        public float getMasteryScore()
        {
                return masteryScore;
        }

        public void setMasteryScore(float masteryScore)
        {
                this.masteryScore = masteryScore;
        }

        public String getMaxTimeAllowed()
        {
                return maxTimeAllowed;
        }

        public void setMaxTimeAllowed(String maxTimeAllowed)
        {
                this.maxTimeAllowed = maxTimeAllowed;
        }

        public String getTimeLimitAction()
        {
                return timeLimitAction;
        }

        public void setTimeLimitAction(String timeLimitAction)
        {
                this.timeLimitAction = timeLimitAction;
        }

        public int getSequence()
        {
                return sequence;
        }

        public void setSequence(int sequence)
        {
                this.sequence = sequence;
        }

        public int getTheLevel()
        {
                return theLevel;
        }

        public void setTheLevel(int theLevel)
        {
                this.theLevel = theLevel;
        }
}
