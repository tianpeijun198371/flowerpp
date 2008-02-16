package com.ulearning.ulms.content.form;

import com.ulearning.ulms.content.model.ContentModel;
import com.ulearning.ulms.core.util.ClobUtil;
import com.ulearning.ulms.tools.upload.model.UploadForm;
import org.apache.commons.lang.builder.ToStringBuilder;

import java.util.Date;


/**
 * @author Hibernate CodeGenerator
 */
public class ContentForm extends UploadForm
{
        public ContentForm(int contentID, int parentID, int relationID, String type, String identifier, String title, String subject, String description, String contentClob, String source, int languageID, String keyword, String coverage, String structure, String aggregationlevel, String status, String version, String format, String mimeType, long contentSize, String location, String installationRemarks, String duration, String difficulty, int typicallEarningTime, String rights, String free, String copyRight, int userID, String creator, String publisher, String contributor, int contentTypeID, int courseContentTypeID, int downloadTimes, Date createDate, Date lastModDate, String remark, String remark1, String remark2, String remark3, String remark4, String remark5, String remark6, String remark7, String beginDate, String endDate, Date displayBeginDate, Date displayEndDate, int orderIndex, String userful, String openGuest, String view, String changeFile, String radionbt, String index, int depth, String auditByAdmin, String auditBySubAdmin)
        {
                this.contentID = contentID;
                this.parentID = parentID;
                this.relationID = relationID;
                this.type = type;
                this.identifier = identifier;
                this.title = title;
                this.subject = subject;
                this.description = description;
                this.contentClob = contentClob;
                this.source = source;
                this.languageID = languageID;
                this.keyword = keyword;
                this.coverage = coverage;
                this.structure = structure;
                this.aggregationlevel = aggregationlevel;
                this.status = status;
                this.version = version;
                this.format = format;
                this.mimeType = mimeType;
                this.contentSize = contentSize;
                this.location = location;
                this.installationRemarks = installationRemarks;
                this.duration = duration;
                this.difficulty = difficulty;
                this.typicallEarningTime = typicallEarningTime;
                this.rights = rights;
                isFree = free;
                isCopyRight = copyRight;
                this.userID = userID;
                this.creator = creator;
                this.publisher = publisher;
                this.contributor = contributor;
                this.contentTypeID = contentTypeID;
                this.courseContentTypeID = courseContentTypeID;
                this.downloadTimes = downloadTimes;
                this.createDate = createDate;
                this.lastModDate = lastModDate;
                this.remark = remark;
                this.remark1 = remark1;
                this.remark2 = remark2;
                this.remark3 = remark3;
                this.remark4 = remark4;
                this.remark5 = remark5;
                this.remark6 = remark6;
                this.remark7 = remark7;
                this.beginDate = beginDate;
                this.endDate = endDate;
                this.displayBeginDate = displayBeginDate;
                this.displayEndDate = displayEndDate;
                this.orderIndex = orderIndex;
                isUserful = userful;
                isOpenGuest = openGuest;
                isView = view;
                isChangeFile = changeFile;
                this.radionbt = radionbt;
                this.index = index;
                this.depth = depth;
                this.auditByAdmin = auditByAdmin;
                this.auditBySubAdmin = auditBySubAdmin;
        }

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
        private String contentClob = " ";

        /**
         * nullable persistent field
         */
        private String source;

        /**
         * nullable persistent field
         */
        private int languageID;

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
        private long contentSize;

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
        private int userID;

        /**
         * nullable persistent field
         */
        private String creator;

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
        private int courseContentTypeID;

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
        private String remark7 = " ";
        private String beginDate;
        private String endDate;

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
        private String isChangeFile;
        private String radionbt;
        private String index;
        private int depth;
        private String auditByAdmin;
        private String auditBySubAdmin;

        

        public ContentModel getContentModel()
        {
                ContentModel cm=new ContentModel();
                cm.setContentID(this.contentID);
                cm.setParentID(this.parentID);
                cm.setRelationID(this.relationID);
                cm.setType(this.type);
                cm.setIdentifier(this.identifier);
                cm.setTitle(this.title);
                cm.setSubject(this.subject);
                cm.setDescription(this.description);
                cm.setContentClob(ClobUtil.getClob(this.contentClob));
                cm.setSource(this.source);
                cm.setLanguageID(this.languageID);
                cm.setKeyword(this.keyword);
                cm.setCoverage(this.coverage);
                cm.setStructure(this.structure);
                cm.setAggregationlevel(this.aggregationlevel);
                cm.setStatus(this.status);
                cm.setVersion(this.version);
                cm.setFormat(this.format);
                cm.setMimeType(this.mimeType);
                cm.setContentSize(this.contentSize);
                cm.setLocation(this.location);
                cm.setInstallationRemarks(this.installationRemarks);
                cm.setDuration(this.duration);
                cm.setDifficulty(this.difficulty);
                cm.setTypicallEarningTime(this.typicallEarningTime);
                cm.setRights(this.rights);
                cm.setFree(isFree);
                cm.setIsCopyRight(isCopyRight);
                cm.setUserID(this.userID);
                cm.setCreator(this.creator);
                cm.setPublisher(this.publisher);
                cm.setContributor(this.contributor);
                cm.setContentTypeID(this.contentTypeID);
                cm.setCourseContentTypeID(this.courseContentTypeID);
                cm.setDownloadTimes(this.downloadTimes);
                cm.setCreateDate(this.createDate);
                cm.setLastModDate(this.lastModDate);
                cm.setRemark(this.remark);
                cm.setRemark1(this.remark1);
                cm.setRemark2(this.remark2);
                cm.setRemark3(this.remark3);
                cm.setRemark4(this.remark4);
                cm.setRemark5(this.remark5);
                cm.setRemark6(this.remark6);
                cm.setRemark7(this.remark7);
                cm.setDisplayBeginDate(this.displayBeginDate);
                cm.setDisplayEndDate(this.displayEndDate);
                cm.setOrderIndex(this.orderIndex);
                cm.setIsUserful(isUserful);
                cm.setIsOpenGuest(isOpenGuest);
                cm.setIsView(isView);
                cm.setDepth(this.depth);
                cm.setAuditByAdmin(this.auditByAdmin);
                cm.setAuditBySubAdmin(this.auditBySubAdmin);
                cm.setTempClobString(this.contentClob);
                return cm;
        }

        /**
         * default constructor
         */
        public ContentForm()
        {
        }

        /**
         * minimal constructor
         */
        public ContentForm(int parentID, int relationID, String identifier,
                           String title, int contentTypeID)
        {
                this.parentID = parentID;
                this.relationID = relationID;
                this.identifier = identifier;
                this.title = title;
                this.contentTypeID = contentTypeID;
        }

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

        public int getLanguageID()
        {
                return this.languageID;
        }

        public void setLanguageID(int languageID)
        {
                this.languageID = languageID;
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

        public long getContentSize()
        {
                return this.contentSize;
        }

        public void setContentSize(long contentSize)
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

        public String getContentClob()
        {
                return contentClob;
        }

        public void setContentClob(String contentClob)
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

        public int getUserID()
        {
                return userID;
        }

        public void setUserID(int userID)
        {
                this.userID = userID;
        }

        public String getBeginDate()
        {
                return beginDate;
        }

        public void setBeginDate(String beginDate)
        {
                this.beginDate = beginDate;
        }

        public String getEndDate()
        {
                return endDate;
        }

        public void setEndDate(String endDate)
        {
                this.endDate = endDate;
        }

        public String getIsChangeFile()
        {
                return isChangeFile;
        }

        public void setIsChangeFile(String changeFile)
        {
                isChangeFile = changeFile;
        }

        public String getRadionbt()
        {
                return radionbt;
        }

        public void setRadionbt(String radionbt)
        {
                this.radionbt = radionbt;
        }

        public String getIndex()
        {
                return index;
        }

        public void setIndex(String index)
        {
                this.index = index;
        }

        public int getCourseContentTypeID()
        {
                return courseContentTypeID;
        }

        public void setCourseContentTypeID(int courseContentTypeID)
        {
                this.courseContentTypeID = courseContentTypeID;
        }

        public String getAuditByAdmin()
        {
                return auditByAdmin;
        }

        public void setAuditByAdmin(String auditByAdmin)
        {
                this.auditByAdmin = auditByAdmin;
        }

        public String getAuditBySubAdmin()
        {
                return auditBySubAdmin;
        }

        public void setAuditBySubAdmin(String auditBySubAdmin)
        {
                this.auditBySubAdmin = auditBySubAdmin;
        }

        public String toString()
        {
                return new ToStringBuilder(this).append("contentID", getContentID())
                        .toString();
        }
}
