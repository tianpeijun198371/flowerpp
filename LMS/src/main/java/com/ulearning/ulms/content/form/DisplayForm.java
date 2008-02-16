/**
 * DisplayForm.java.
 * User: shid Date: 2005-6-14 16:34:33
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.content.form;

import org.apache.struts.action.ActionForm;

import java.sql.Clob;

import java.util.Date;


public class DisplayForm extends ActionForm
{
        private int thing; //判断是什么，也可用来排序，1为目录，2为资源

        /**
         * identifier field
         */
        private int contentID;

        /**
         * persistent field
         */
        private int parentID;

        /**
         * persistent field
         */
        private int relationID;

        /**
         * nullable persistent field
         */
        private String type;

        /**
         * persistent field
         */
        private String identifier;

        /**
         * persistent field
         */
        private String title;

        /**
         * nullable persistent field
         */
        private String subject;

        /**
         * nullable persistent field
         */
        private String description;
        private Clob contentClob;

        /**
         * nullable persistent field
         */
        private String source;

        /**
         * nullable persistent field
         */
        private String language;

        /**
         * nullable persistent field
         */
        private String keyword;

        /**
         * nullable persistent field
         */
        private String coverage;

        /**
         * nullable persistent field
         */
        private String structure;

        /**
         * nullable persistent field
         */
        private String aggregationlevel;

        /**
         * nullable persistent field
         */
        private String status;

        /**
         * nullable persistent field
         */
        private String version;

        /**
         * nullable persistent field
         */
        private String format;

        /**
         * nullable persistent field
         */
        private String mimeType;

        /**
         * nullable persistent field
         */
        private int contentSize;

        /**
         * nullable persistent field
         */
        private String location;

        /**
         * nullable persistent field
         */
        private String installationRemarks;

        /**
         * nullable persistent field
         */
        private String duration;

        /**
         * nullable persistent field
         */
        private String difficulty;

        /**
         * nullable persistent field
         */
        private int typicallEarningTime;

        /**
         * nullable persistent field
         */
        private String rights;

        /**
         * nullable persistent field
         */
        private String isFree;

        /**
         * nullable persistent field
         */
        private String isCopyRight;

        /**
         * nullable persistent field
         */
        private String creator = "";

        /**
         * nullable persistent field
         */
        private String publisher;

        /**
         * nullable persistent field
         */
        private String contributor;

        /**
         * persistent field
         */
        private int contentTypeID;

        /**
         * nullable persistent field
         */
        private int downloadTimes;

        /**
         * nullable persistent field
         */
        private Date createDate;

        /**
         * nullable persistent field
         */
        private Date lastModDate;

        /**
         * nullable persistent field
         */
        private String remark;

        /**
         * nullable persistent field
         */
        private String remark1;

        /**
         * nullable persistent field
         */
        private String remark2;

        /**
         * nullable persistent field
         */
        private String remark3;

        /**
         * nullable persistent field
         */
        private String remark4;

        /**
         * nullable persistent field
         */
        private String remark5;

        /**
         * nullable persistent field
         */
        private String remark6;

        /**
         * nullable persistent field
         */
        private String remark7;

        /**
         * nullable persistent field
         */
        private Date displayBeginDate;

        /**
         * nullable persistent field
         */
        private Date displayEndDate;

        /**
         * nullable persistent field
         */
        private int orderIndex;

        /**
         * nullable persistent field
         */
        private String isUserful;

        /**
         * nullable persistent field
         */
        private String isOpenGuest;

        /**
         * nullable persistent field
         */
        private String isView;

        /**
         * nullable persistent field
         */
        private int depth;

        /**
         * identifier field
         */
        private int contentCatalogID;

        public int getContentID()
        {
                return this.contentID;
        }

        public void setContentID(int contentID)
        {
                this.contentID = contentID;
        }

        public int getParentID()
        {
                return this.parentID;
        }

        public void setParentID(int parentID)
        {
                this.parentID = parentID;
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

        public String getTitle()
        {
                return this.title;
        }

        public void setTitle(String title)
        {
                this.title = title;
        }

        public String getSubject()
        {
                return this.subject;
        }

        public void setSubject(String subject)
        {
                this.subject = subject;
        }

        public String getDescription()
        {
                return this.description;
        }

        public void setDescription(String description)
        {
                this.description = description;
        }

        public String getSource()
        {
                return this.source;
        }

        public void setSource(String source)
        {
                this.source = source;
        }

        public String getLanguage()
        {
                return this.language;
        }

        public void setLanguage(String language)
        {
                this.language = language;
        }

        public String getKeyword()
        {
                return this.keyword;
        }

        public void setKeyword(String keyword)
        {
                this.keyword = keyword;
        }

        public String getCoverage()
        {
                return this.coverage;
        }

        public void setCoverage(String coverage)
        {
                this.coverage = coverage;
        }

        public String getStructure()
        {
                return this.structure;
        }

        public void setStructure(String structure)
        {
                this.structure = structure;
        }

        public String getAggregationlevel()
        {
                return this.aggregationlevel;
        }

        public void setAggregationlevel(String aggregationlevel)
        {
                this.aggregationlevel = aggregationlevel;
        }

        public String getStatus()
        {
                return this.status;
        }

        public void setStatus(String status)
        {
                this.status = status;
        }

        public String getVersion()
        {
                return this.version;
        }

        public void setVersion(String version)
        {
                this.version = version;
        }

        public String getFormat()
        {
                return this.format;
        }

        public void setFormat(String format)
        {
                this.format = format;
        }

        public String getMimeType()
        {
                return this.mimeType;
        }

        public void setMimeType(String mimeType)
        {
                this.mimeType = mimeType;
        }

        public int getContentSize()
        {
                return this.contentSize;
        }

        public void setContentSize(int contentSize)
        {
                this.contentSize = contentSize;
        }

        public String getLocation()
        {
                return this.location;
        }

        public void setLocation(String location)
        {
                this.location = location;
        }

        public String getInstallationRemarks()
        {
                return this.installationRemarks;
        }

        public void setInstallationRemarks(String installationRemarks)
        {
                this.installationRemarks = installationRemarks;
        }

        public String getDuration()
        {
                return this.duration;
        }

        public void setDuration(String duration)
        {
                this.duration = duration;
        }

        public String getDifficulty()
        {
                return this.difficulty;
        }

        public void setDifficulty(String difficulty)
        {
                this.difficulty = difficulty;
        }

        public int getTypicallEarningTime()
        {
                return this.typicallEarningTime;
        }

        public void setTypicallEarningTime(int typicallEarningTime)
        {
                this.typicallEarningTime = typicallEarningTime;
        }

        public String getRights()
        {
                return this.rights;
        }

        public void setRights(String rights)
        {
                this.rights = rights;
        }

        public String getIsFree()
        {
                return this.isFree;
        }

        public void setIsFree(String isFree)
        {
                this.isFree = isFree;
        }

        public String getIsCopyRight()
        {
                return this.isCopyRight;
        }

        public void setIsCopyRight(String isCopyRight)
        {
                this.isCopyRight = isCopyRight;
        }

        public String getCreator()
        {
                return this.creator;
        }

        public void setCreator(String creator)
        {
                this.creator = creator;
        }

        public String getPublisher()
        {
                return this.publisher;
        }

        public void setPublisher(String publisher)
        {
                this.publisher = publisher;
        }

        public String getContributor()
        {
                return this.contributor;
        }

        public void setContributor(String contributor)
        {
                this.contributor = contributor;
        }

        public int getDownloadTimes()
        {
                return this.downloadTimes;
        }

        public void setDownloadTimes(int downloadTimes)
        {
                this.downloadTimes = downloadTimes;
        }

        public Date getCreateDate()
        {
                return this.createDate;
        }

        public void setCreateDate(Date createDate)
        {
                this.createDate = createDate;
        }

        public Date getLastModDate()
        {
                return this.lastModDate;
        }

        public void setLastModDate(Date lastModDate)
        {
                this.lastModDate = lastModDate;
        }

        public String getRemark()
        {
                return this.remark;
        }

        public void setRemark(String remark)
        {
                this.remark = remark;
        }

        public String getRemark1()
        {
                return this.remark1;
        }

        public void setRemark1(String remark1)
        {
                this.remark1 = remark1;
        }

        public String getRemark2()
        {
                return this.remark2;
        }

        public void setRemark2(String remark2)
        {
                this.remark2 = remark2;
        }

        public String getRemark3()
        {
                return this.remark3;
        }

        public void setRemark3(String remark3)
        {
                this.remark3 = remark3;
        }

        public String getRemark4()
        {
                return this.remark4;
        }

        public void setRemark4(String remark4)
        {
                this.remark4 = remark4;
        }

        public String getRemark5()
        {
                return this.remark5;
        }

        public void setRemark5(String remark5)
        {
                this.remark5 = remark5;
        }

        public String getRemark6()
        {
                return this.remark6;
        }

        public void setRemark6(String remark6)
        {
                this.remark6 = remark6;
        }

        public String getRemark7()
        {
                return this.remark7;
        }

        public void setRemark7(String remark7)
        {
                this.remark7 = remark7;
        }

        public Date getDisplayBeginDate()
        {
                return this.displayBeginDate;
        }

        public void setDisplayBeginDate(Date displayBeginDate)
        {
                this.displayBeginDate = displayBeginDate;
        }

        public Date getDisplayEndDate()
        {
                return this.displayEndDate;
        }

        public void setDisplayEndDate(Date displayEndDate)
        {
                this.displayEndDate = displayEndDate;
        }

        public int getOrderIndex()
        {
                return this.orderIndex;
        }

        public void setOrderIndex(int orderIndex)
        {
                this.orderIndex = orderIndex;
        }

        public String getIsUserful()
        {
                return this.isUserful;
        }

        public void setIsUserful(String isUserful)
        {
                this.isUserful = isUserful;
        }

        public String getIsOpenGuest()
        {
                return this.isOpenGuest;
        }

        public void setIsOpenGuest(String isOpenGuest)
        {
                this.isOpenGuest = isOpenGuest;
        }

        public String getIsView()
        {
                return this.isView;
        }

        public void setIsView(String isView)
        {
                this.isView = isView;
        }

        public int getDepth()
        {
                return this.depth;
        }

        public void setDepth(int depth)
        {
                this.depth = depth;
        }

        public Clob getContentClob()
        {
                return contentClob;
        }

        public void setContentClob(Clob contentClob)
        {
                this.contentClob = contentClob;
        }

        public int getContentTypeID()
        {
                return contentTypeID;
        }

        public void setContentTypeID(int contentTypeID)
        {
                this.contentTypeID = contentTypeID;
        }

        public int getContentCatalogID()
        {
                return this.contentCatalogID;
        }

        public void setContentCatalogID(int contentCatalogID)
        {
                this.contentCatalogID = contentCatalogID;
        }

        public int getThing()
        {
                return thing;
        }

        public void setThing(int thing)
        {
                this.thing = thing;
        }
}
