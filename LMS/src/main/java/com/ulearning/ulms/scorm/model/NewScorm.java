/**
 * Copyright (c)2006 Beijing WenhuaOnline Sci-tech DevelopmentCo.,Ltd.
 * All rights reserved.
 *
 * User: fengch
 * Date: 2007-9-20 18:25:13
 */
package com.ulearning.ulms.scorm.model;

import java.util.Date;


public class NewScorm
{
        private Integer id;
        private Integer relationID;
        private String type;
        private String name;
        private String reference;
        private String summary;
        private String version;
        private Float maxGrade;
        private String gradeMethod;
        private Integer maxAttempt;
        private String updateFreq;
        private String md5hash;
        private Integer launch;
        private String skipView;
        private String hideBrowse;
        private String hideToc;
        private String hideNav;
        private String auto;
        private String popup;
        private String options;
        private Integer width;
        private Integer height;
        private Date createDate;
        private Date lastUpdateDate;

        public NewScorm()
        {
        }

        public NewScorm(Integer id, Integer relationID, String type, String name,
                        String reference, String summary, String version, Float maxGrade,
                        String gradeMethod, Integer maxAttempt, String updateFreq,
                        String md5hash, Integer launch, String skipView, String hideBrowse,
                        String hideToc, String hideNav, String auto, String popup,
                        String options, Integer width, Integer height, Date createDate,
                        Date lastUpdateDate)
        {
                this.id = id;
                this.relationID = relationID;
                this.type = type;
                this.name = name;
                this.reference = reference;
                this.summary = summary;
                this.version = version;
                this.maxGrade = maxGrade;
                this.gradeMethod = gradeMethod;
                this.maxAttempt = maxAttempt;
                this.updateFreq = updateFreq;
                this.md5hash = md5hash;
                this.launch = launch;
                this.skipView = skipView;
                this.hideBrowse = hideBrowse;
                this.hideToc = hideToc;
                this.hideNav = hideNav;
                this.auto = auto;
                this.popup = popup;
                this.options = options;
                this.width = width;
                this.height = height;
                this.createDate = createDate;
                this.lastUpdateDate = lastUpdateDate;
        }

        public Integer getId()
        {
                return id;
        }

        public void setId(Integer id)
        {
                this.id = id;
        }

        public Integer getRelationID()
        {
                return relationID;
        }

        public void setRelationID(Integer relationID)
        {
                this.relationID = relationID;
        }

        public String getType()
        {
                return type;
        }

        public void setType(String type)
        {
                this.type = type;
        }

        public String getName()
        {
                return name;
        }

        public void setName(String name)
        {
                this.name = name;
        }

        public String getReference()
        {
                return reference;
        }

        public void setReference(String reference)
        {
                this.reference = reference;
        }

        public String getSummary()
        {
                return summary;
        }

        public void setSummary(String summary)
        {
                this.summary = summary;
        }

        public String getVersion()
        {
                return version;
        }

        public void setVersion(String version)
        {
                this.version = version;
        }

        public Float getMaxGrade()
        {
                return maxGrade;
        }

        public void setMaxGrade(Float maxGrade)
        {
                this.maxGrade = maxGrade;
        }

        public String getGradeMethod()
        {
                return gradeMethod;
        }

        public void setGradeMethod(String gradeMethod)
        {
                this.gradeMethod = gradeMethod;
        }

        public Integer getMaxAttempt()
        {
                return maxAttempt;
        }

        public void setMaxAttempt(Integer maxAttempt)
        {
                this.maxAttempt = maxAttempt;
        }

        public String getUpdateFreq()
        {
                return updateFreq;
        }

        public void setUpdateFreq(String updateFreq)
        {
                this.updateFreq = updateFreq;
        }

        public String getMd5hash()
        {
                return md5hash;
        }

        public void setMd5hash(String md5hash)
        {
                this.md5hash = md5hash;
        }

        public Integer getLaunch()
        {
                return launch;
        }

        public void setLaunch(Integer launch)
        {
                this.launch = launch;
        }

        public String getSkipView()
        {
                return skipView;
        }

        public void setSkipView(String skipView)
        {
                this.skipView = skipView;
        }

        public String getHideBrowse()
        {
                return hideBrowse;
        }

        public void setHideBrowse(String hideBrowse)
        {
                this.hideBrowse = hideBrowse;
        }

        public String getHideToc()
        {
                return hideToc;
        }

        public void setHideToc(String hideToc)
        {
                this.hideToc = hideToc;
        }

        public String getHideNav()
        {
                return hideNav;
        }

        public void setHideNav(String hideNav)
        {
                this.hideNav = hideNav;
        }

        public String getAuto()
        {
                return auto;
        }

        public void setAuto(String auto)
        {
                this.auto = auto;
        }

        public String getPopup()
        {
                return popup;
        }

        public void setPopup(String popup)
        {
                this.popup = popup;
        }

        public String getOptions()
        {
                return options;
        }

        public void setOptions(String options)
        {
                this.options = options;
        }

        public Integer getWidth()
        {
                return width;
        }

        public void setWidth(Integer width)
        {
                this.width = width;
        }

        public Integer getHeight()
        {
                return height;
        }

        public void setHeight(Integer height)
        {
                this.height = height;
        }

        public Date getCreateDate()
        {
                return createDate;
        }

        public void setCreateDate(Date createDate)
        {
                this.createDate = createDate;
        }

        public Date getLastUpdateDate()
        {
                return lastUpdateDate;
        }

        public void setLastUpdateDate(Date lastUpdateDate)
        {
                this.lastUpdateDate = lastUpdateDate;
        }
}
