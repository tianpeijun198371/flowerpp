/**
 * Copyright (c)2006 Beijing WenhuaOnline Sci-tech DevelopmentCo.,Ltd.
 * All rights reserved.
 *
 * User: fengch
 * Date: 2007-9-21 11:43:33
 */
package com.ulearning.ulms.scorm.model;

public class NewScormScoes
{
        Integer id;
        Integer scormId;
        String manifest;
        String organization;
        String parent;
        String identifier;
        String launch;
        String scoType;
        String title;
        String parameterString;
        String dataFromLMS;
        String prerequisites;
        Float masteryScore;
        Float maxScore;
        String maxTimeAllowed;
        String timeLimitAction;
        Integer sequence;
        Integer theLevel;
        String commandLine;
        String systemVendor;

        public NewScormScoes()
        {
        }

        public NewScormScoes(Integer id, Integer scormId, String manifest,
                             String organization, String parent, String identifier, String launch,
                             String scormtype, String title, String parameterString,
                             String dataFromLMS, String prerequisites, Float masteryScore,
                             Float maxScore, String maxTimeAllowed, String timeLimitAction,
                             Integer sequence, Integer theLevel, String commandline,
                             String systemvendor)
        {
                this.id = id;
                this.scormId = scormId;
                this.manifest = manifest;
                this.organization = organization;
                this.parent = parent;
                this.identifier = identifier;
                this.launch = launch;
                this.scoType = scormtype;
                this.title = title;
                this.parameterString = parameterString;
                this.dataFromLMS = dataFromLMS;
                this.prerequisites = prerequisites;
                this.masteryScore = masteryScore;
                this.maxScore = maxScore;
                this.maxTimeAllowed = maxTimeAllowed;
                this.timeLimitAction = timeLimitAction;
                this.sequence = sequence;
                this.theLevel = theLevel;
                this.commandLine = commandline;
                this.systemVendor = systemvendor;
        }

        public Integer getId()
        {
                return id;
        }

        public void setId(Integer id)
        {
                this.id = id;
        }

        public Integer getScormId()
        {
                return scormId;
        }

        public void setScormId(Integer scormId)
        {
                this.scormId = scormId;
        }

        public String getManifest()
        {
                return manifest;
        }

        public void setManifest(String manifest)
        {
                this.manifest = manifest;
        }

        public String getOrganization()
        {
                return organization;
        }

        public void setOrganization(String organization)
        {
                this.organization = organization;
        }

        public String getParent()
        {
                return parent;
        }

        public void setParent(String parent)
        {
                this.parent = parent;
        }

        public String getIdentifier()
        {
                return identifier;
        }

        public void setIdentifier(String identifier)
        {
                this.identifier = identifier;
        }

        public String getLaunch()
        {
                return launch;
        }

        public void setLaunch(String launch)
        {
                this.launch = launch;
        }

        public String getScoType()
        {
                return scoType;
        }

        public void setScoType(String scoType)
        {
                this.scoType = scoType;
        }

        public String getTitle()
        {
                return title;
        }

        public void setTitle(String title)
        {
                this.title = title;
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

        public Float getMasteryScore()
        {
                return masteryScore;
        }

        public void setMasteryScore(Float masteryScore)
        {
                this.masteryScore = masteryScore;
        }

        public Float getMaxScore()
        {
                return maxScore;
        }

        public void setMaxScore(Float maxScore)
        {
                this.maxScore = maxScore;
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

        public Integer getSequence()
        {
                return sequence;
        }

        public void setSequence(Integer sequence)
        {
                this.sequence = sequence;
        }

        public Integer getTheLevel()
        {
                return theLevel;
        }

        public void setTheLevel(Integer theLevel)
        {
                this.theLevel = theLevel;
        }

        public String getCommandLine()
        {
                return commandLine;
        }

        public void setCommandLine(String commandLine)
        {
                this.commandLine = commandLine;
        }

        public String getSystemVendor()
        {
                return systemVendor;
        }

        public void setSystemVendor(String systemVendor)
        {
                this.systemVendor = systemVendor;
        }
}
