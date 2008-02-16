package com.ulearning.ulms.tools.assignment.model;

import org.apache.commons.lang.builder.ToStringBuilder;

import java.io.Serializable;

import java.util.Date;


/**
 * @author Hibernate CodeGenerator
 */
public class AssignmentModel implements Serializable
{
        /**
         * identifier field
         */
        private int assignmentID = 0;

        /**
         * nullable persistent field
         */
        private int parentID = 0;

        /**
         * nullable persistent field
         */
        private int courseID = 0;

        /**
         * nullable persistent field
         */
        private int rootID = 0;

        /**
         * nullable persistent field
         */
        private int orderIndex = 0;

        /**
         * persistent field
         */
        private String name;

        /**
         * nullable persistent field
         */
        private String description;

        /**
         * nullable persistent field
         */
        private String isViewable;

        /**
         * nullable persistent field
         */
        private String linkFileUrl;

        /**
         * nullable persistent field
         */
        private String linkFileName;

        /**
         * nullable persistent field
         */
        private String type;

        /**
         * nullable persistent field
         */
        private String fileType;

        /**
         * nullable persistent field
         */
        private String isTrackNumber;

        /**
         * nullable persistent field
         */
        private int viewNumber = 0;

        /**
         * nullable persistent field
         */
        private String isAvailable;

        /**
         * nullable persistent field
         */
        private Date createTime;

        /**
         * nullable persistent field
         */
        private Date disAfterTime;

        /**
         * nullable persistent field
         */
        private Date disUntilTime;
        private String canDelete = null;
        private String exerciseType = null;

        /**
         * full constructor
         */
        public AssignmentModel(int parentID, int courseID, int rootID,
                               int orderIndex, String name, String description, String isViewable,
                               String linkFileUrl, String linkFileName, String type, String fileType,
                               String isTrackNumber, int viewNumber, String isAvailable,
                               Date createTime, Date disAfterTime, Date disUntilTime)
        {
                this.parentID = parentID;
                this.courseID = courseID;
                this.rootID = rootID;
                this.orderIndex = orderIndex;
                this.name = name;
                this.description = description;
                this.isViewable = isViewable;
                this.linkFileUrl = linkFileUrl;
                this.linkFileName = linkFileName;
                this.type = type;
                this.fileType = fileType;
                this.isTrackNumber = isTrackNumber;
                this.viewNumber = viewNumber;
                this.isAvailable = isAvailable;
                this.createTime = createTime;
                this.disAfterTime = disAfterTime;
                this.disUntilTime = disUntilTime;
        }

        /**
         * default constructor
         */
        public AssignmentModel()
        {
        }

        /**
         * minimal constructor
         */
        public AssignmentModel(String name)
        {
                this.name = name;
        }

        public int getAssignmentID()
        {
                return this.assignmentID;
        }

        public void setAssignmentID(int assignmentID)
        {
                this.assignmentID = assignmentID;
        }

        public int getParentID()
        {
                return this.parentID;
        }

        public void setParentID(int parentID)
        {
                this.parentID = parentID;
        }

        public int getCourseID()
        {
                return this.courseID;
        }

        public void setCourseID(int courseID)
        {
                this.courseID = courseID;
        }

        public int getRootID()
        {
                return this.rootID;
        }

        public void setRootID(int rootID)
        {
                this.rootID = rootID;
        }

        public int getOrderIndex()
        {
                return this.orderIndex;
        }

        public void setOrderIndex(int orderIndex)
        {
                this.orderIndex = orderIndex;
        }

        public String getName()
        {
                return this.name;
        }

        public void setName(String name)
        {
                this.name = name;
        }

        public String getDescription()
        {
                return this.description;
        }

        public void setDescription(String description)
        {
                this.description = description;
        }

        public String getIsViewable()
        {
                return this.isViewable;
        }

        public void setIsViewable(String isViewable)
        {
                this.isViewable = isViewable;
        }

        public String getLinkFileUrl()
        {
                return this.linkFileUrl;
        }

        public void setLinkFileUrl(String linkFileUrl)
        {
                this.linkFileUrl = linkFileUrl;
        }

        public String getLinkFileName()
        {
                return this.linkFileName;
        }

        public void setLinkFileName(String linkFileName)
        {
                this.linkFileName = linkFileName;
        }

        public String getType()
        {
                return this.type;
        }

        public void setType(String type)
        {
                this.type = type;
        }

        public String getFileType()
        {
                return this.fileType;
        }

        public void setFileType(String fileType)
        {
                this.fileType = fileType;
        }

        public String getIsTrackNumber()
        {
                return this.isTrackNumber;
        }

        public void setIsTrackNumber(String isTrackNumber)
        {
                this.isTrackNumber = isTrackNumber;
        }

        public int getViewNumber()
        {
                return this.viewNumber;
        }

        public void setViewNumber(int viewNumber)
        {
                this.viewNumber = viewNumber;
        }

        public String getIsAvailable()
        {
                return this.isAvailable;
        }

        public void setIsAvailable(String isAvailable)
        {
                this.isAvailable = isAvailable;
        }

        public Date getCreateTime()
        {
                return this.createTime;
        }

        public void setCreateTime(Date createTime)
        {
                this.createTime = createTime;
        }

        public Date getDisAfterTime()
        {
                return this.disAfterTime;
        }

        public void setDisAfterTime(Date disAfterTime)
        {
                this.disAfterTime = disAfterTime;
        }

        public Date getDisUntilTime()
        {
                return this.disUntilTime;
        }

        public void setDisUntilTime(Date disUntilTime)
        {
                this.disUntilTime = disUntilTime;
        }

        public String toString()
        {
                return new ToStringBuilder(this).append("assignmentID",
                        getAssignmentID()).toString();
        }

        public String getCanDelete()
        {
                return canDelete;
        }

        public void setCanDelete(String canDelete)
        {
                this.canDelete = canDelete;
        }

        public String getExerciseType()
        {
                return exerciseType;
        }

        public void setExerciseType(String exerciseType)
        {
                this.exerciseType = exerciseType;
        }
}
