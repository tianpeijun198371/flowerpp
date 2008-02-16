/**
 * Copyright (c)2006 Beijing WenhuaOnline Sci-tech DevelopmentCo.,Ltd. 
 * All rights reserved. 
 *
 * User: Fengch
 * Date: 2007-11-8 10:16:20
 */


package com.ulearning.ulms.tools.meeting.form;

import org.apache.struts.action.ActionForm;

import java.util.Date;

public class MeetingForm  extends ActionForm
{
        private int id;
        private int relationID;
        private String type;
        private String meetingType;
        private String name;
        private String reference;
        private String summary;
        private int cameras;
        private int capacity;
        private String compereCode;
        private int createrId;
        private String enabledREC;
        private String startTimeStr;
        private String endTimeStr;
        private Date startTime;
        private Date endTime;
        private String fixed;
        private int lectureSize;
        private String lectureURL;
        private String recordURL;
        private String secrecy;
        private String services;
        private String specify;
        private String specifyCode;
        private int auditStatus;
        private int status;
         private int primaryServer;
        private Date createDate;
        private Date lastUpdateDate;
        private int aspID;
        public MeetingForm()
        {
        }

        public int getId()
        {
                return id;
        }

        public void setId(int id)
        {
                this.id = id;
        }

        public int getAspID()
        {
                return aspID;
        }

        public void setAspID(int aspID)
        {
                this.aspID = aspID;
        }

        public int getRelationID()
        {
                return relationID;
        }

        public void setRelationID(int relationID)
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

        public int getPrimaryServer()
        {
                return primaryServer;
        }

        public void setPrimaryServer(int primaryServer)
        {
                this.primaryServer = primaryServer;
        }

        public String getMeetingType()
        {
                return meetingType;
        }

        public void setMeetingType(String meetingType)
        {
                this.meetingType = meetingType;
        }

        public String getName()
        {
                return name;
        }

        public void setName(String name)
        {
                this.name = name;
        }

        public String getStartTimeStr()
        {
                return startTimeStr;
        }

        public void setStartTimeStr(String startTimeStr)
        {
                this.startTimeStr = startTimeStr;
        }

        public String getEndTimeStr()
        {
                return endTimeStr;
        }

        public void setEndTimeStr(String endTimeStr)
        {
                this.endTimeStr = endTimeStr;
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

        public int getCameras()
        {
                return cameras;
        }

        public void setCameras(int cameras)
        {
                this.cameras = cameras;
        }

        public int getCapacity()
        {
                return capacity;
        }

        public void setCapacity(int capacity)
        {
                this.capacity = capacity;
        }

        public String getCompereCode()
        {
                return compereCode;
        }

        public void setCompereCode(String compereCode)
        {
                this.compereCode = compereCode;
        }

        public int getCreaterId()
        {
                return createrId;
        }

        public void setCreaterId(int createrId)
        {
                this.createrId = createrId;
        }

        public String getEnabledREC()
        {
                return enabledREC;
        }

        public void setEnabledREC(String enabledREC)
        {
                this.enabledREC = enabledREC;
        }

        public Date getStartTime()
        {
                return startTime;
        }

        public void setStartTime(Date startTime)
        {
                this.startTime = startTime;
        }

        public Date getEndTime()
        {
                return endTime;
        }

        public void setEndTime(Date endTime)
        {
                this.endTime = endTime;
        }

        public String getFixed()
        {
                return fixed;
        }

        public void setFixed(String fixed)
        {
                this.fixed = fixed;
        }

        public int getLectureSize()
        {
                return lectureSize;
        }

        public void setLectureSize(int lectureSize)
        {
                this.lectureSize = lectureSize;
        }

        public String getLectureURL()
        {
                return lectureURL;
        }

        public void setLectureURL(String lectureURL)
        {
                this.lectureURL = lectureURL;
        }

        public String getRecordURL()
        {
                return recordURL;
        }

        public void setRecordURL(String recordURL)
        {
                this.recordURL = recordURL;
        }

        public String getSecrecy()
        {
                return secrecy;
        }

        public void setSecrecy(String secrecy)
        {
                this.secrecy = secrecy;
        }

        public String getServices()
        {
                return services;
        }

        public void setServices(String services)
        {
                this.services = services;
        }

        public String getSpecify()
        {
                return specify;
        }

        public void setSpecify(String specify)
        {
                this.specify = specify;
        }

        public String getSpecifyCode()
        {
                return specifyCode;
        }

        public void setSpecifyCode(String specifyCode)
        {
                this.specifyCode = specifyCode;
        }

        public int getAuditStatus()
        {
                return auditStatus;
        }

        public void setAuditStatus(int auditStatus)
        {
                this.auditStatus = auditStatus;
        }

        public int getStatus()
        {
                return status;
        }

        public void setStatus(int status)
        {
                this.status = status;
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
