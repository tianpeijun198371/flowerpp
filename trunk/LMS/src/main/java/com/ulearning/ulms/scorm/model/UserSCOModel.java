/**
 * UserSCOModel.java.
 * User: fengch  Date: 2004-7-28
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.scorm.model;

import java.io.Serializable;


public class UserSCOModel implements Serializable
{
        private int userID;
        private int SCOID;
        private int courseContentNodeID;
        private String launch;
        private String parameterString;
        private String lessonStatus;
        private String prerequisites;
        private String exitString;
        private String entry;
        private float masteryScore;
        private int sequence;
        private String itemType;

        // the folows is from c_item_tab
        private int relationID;
        private int type;
        private String identifier;
        private String title;
        private String dataFromLMS;
        private String maxTimeAllowed;
        private String timeLimitAction;
        private int theLevel;

        public UserSCOModel()
        {
        }

        public UserSCOModel(int userID, int SCOID, int courseContentNodeID,
                            String launch, String parameterString, String lessonStatus,
                            String prerequisites, String exitString, String entry,
                            float masteryScore, int sequence, String itemType, int relationID,
                            int type, String identifier, String title, String dataFromLMS,
                            String maxTimeAllowed, String timeLimitAction, int theLevel)
        {
                this.userID = userID;
                this.SCOID = SCOID;
                this.courseContentNodeID = courseContentNodeID;
                this.launch = launch;
                this.parameterString = parameterString;
                this.lessonStatus = lessonStatus;
                this.prerequisites = prerequisites;
                this.exitString = exitString;
                this.entry = entry;
                this.masteryScore = masteryScore;
                this.sequence = sequence;
                this.itemType = itemType;
                this.relationID = relationID;
                this.type = type;
                this.identifier = identifier;
                this.title = title;
                this.dataFromLMS = dataFromLMS;
                this.maxTimeAllowed = maxTimeAllowed;
                this.timeLimitAction = timeLimitAction;
                this.theLevel = theLevel;
        }

        public UserSCOModel(int userID, int SCOID, int courseContentNodeID,
                            String launch, String parameterString, String lessonStatus,
                            String prerequisites, String exitString, String entry,
                            float masteryScore, int sequence, String itemType)
        {
                this.userID = userID;
                this.SCOID = SCOID;
                this.courseContentNodeID = courseContentNodeID;

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

        public int getUserID()
        {
                return userID;
        }

        public void setUserID(int userID)
        {
                this.userID = userID;
        }

        public int getSCOID()
        {
                return SCOID;
        }

        public void setSCOID(int SCOID)
        {
                this.SCOID = SCOID;
        }

        public int getCourseContentNodeID()
        {
                return courseContentNodeID;
        }

        public void setCourseContentNodeID(int courseContentNodeID)
        {
                this.courseContentNodeID = courseContentNodeID;
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

        public String getLessonStatus()
        {
                return lessonStatus;
        }

        public void setLessonStatus(String lessonStatus)
        {
                this.lessonStatus = lessonStatus;
        }

        public String getPrerequisites()
        {
                return prerequisites;
        }

        public void setPrerequisites(String prerequisites)
        {
                this.prerequisites = prerequisites;
        }

        public String getExitString()
        {
                return exitString;
        }

        public void setExitString(String exitString)
        {
                this.exitString = exitString;
        }

        public String getEntry()
        {
                return entry;
        }

        public void setEntry(String entry)
        {
                this.entry = entry;
        }

        public float getMasteryScore()
        {
                return masteryScore;
        }

        public void setMasteryScore(float masteryScore)
        {
                this.masteryScore = masteryScore;
        }

        public int getSequence()
        {
                return sequence;
        }

        public void setSequence(int sequence)
        {
                this.sequence = sequence;
        }

        public String getItemType()
        {
                return itemType;
        }

        public void setItemType(String itemType)
        {
                this.itemType = itemType;
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

        public String getTitle()
        {
                return title;
        }

        public void setTitle(String title)
        {
                this.title = title;
        }

        public String getDataFromLMS()
        {
                return dataFromLMS;
        }

        public void setDataFromLMS(String dataFromLMS)
        {
                this.dataFromLMS = dataFromLMS;
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

        public int getTheLevel()
        {
                return theLevel;
        }

        public void setTheLevel(int theLevel)
        {
                this.theLevel = theLevel;
        }
}
