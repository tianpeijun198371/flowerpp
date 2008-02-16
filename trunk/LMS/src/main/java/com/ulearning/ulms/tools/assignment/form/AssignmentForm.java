/** * AssignmentForm.java.
 * User: xiejh  Date: 2004-4-22 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.tools.assignment.form;

import com.ulearning.ulms.core.util.DateTimeUtil;
import com.ulearning.ulms.core.util.StringUtil;
import com.ulearning.ulms.tools.assignment.model.AssignmentModel;
import com.ulearning.ulms.tools.upload.model.UploadForm;

import org.apache.struts.action.ActionForm;

import java.sql.Date;


public class AssignmentForm extends UploadForm
{
        private int assignmentID = 0;
        private int courseID = 0;
        private int parentID = 0;
        private int rootID = 0;
        private int orderIndex = 0;
        private String name = null;
        private String description = null;
        private String isViewable = null;
        private String linkFileUrl = null;
        private String linkFileName = null;
        private String type = null;
        private String fileType = null;
        private String isTrackNumber = null;
        private int viewNumber = 0;
        private String isAvailable = null;
        private String canDelete = null;
        private String exerciseType = null;
        private String createTime = null; //Format for example:2004-04-04
        private String disAfterTime = null; //Format for example:2004-04-04
        private String disUntilTime = null; //Format for example:2004-04-04
        private AssignmentModel assignmentModel = null;

        public AssignmentModel getAssignmentModel()
        {
                assignmentModel = new AssignmentModel();
                assignmentModel.setAssignmentID(this.assignmentID);
                assignmentModel.setCourseID(this.courseID);
                assignmentModel.setParentID(this.parentID);
                assignmentModel.setRootID(this.rootID);
                assignmentModel.setOrderIndex(this.orderIndex);
                assignmentModel.setName(this.name);
                assignmentModel.setDescription(this.description);
                assignmentModel.setIsViewable(this.isViewable);
                assignmentModel.setLinkFileUrl(this.linkFileUrl);
                assignmentModel.setLinkFileName(this.linkFileName);
                assignmentModel.setType(this.type);
                assignmentModel.setFileType(this.fileType);
                assignmentModel.setIsTrackNumber(this.isTrackNumber);
                assignmentModel.setViewNumber(this.viewNumber);
                assignmentModel.setIsAvailable(this.isAvailable);
                assignmentModel.setCanDelete(this.canDelete);
                assignmentModel.setExerciseType(this.exerciseType);
                assignmentModel.setCreateTime(DateTimeUtil.toDate(this.createTime +
                        " 00:00:00"));
                assignmentModel.setDisAfterTime(DateTimeUtil.toDate(this.disAfterTime +
                        " 00:00:00"));
                assignmentModel.setDisUntilTime(DateTimeUtil.toDate(this.disUntilTime +
                        " 23:59:59"));

                return assignmentModel;
        }

        public AssignmentForm getAssignmentForm(AssignmentModel assignmentModel)
        {
                AssignmentForm assignmentForm = new AssignmentForm();
                assignmentForm.setAssignmentID(assignmentModel.getAssignmentID());
                assignmentForm.setCourseID(assignmentModel.getCourseID());
                assignmentForm.setParentID(assignmentModel.getParentID());
                assignmentForm.setRootID(assignmentModel.getRootID());
                assignmentForm.setOrderIndex(assignmentModel.getOrderIndex());
                assignmentForm.setName(assignmentModel.getName());
                assignmentForm.setDescription(StringUtil.nullToStr(
                        assignmentModel.getDescription()));
                assignmentForm.setViewable(assignmentModel.getIsViewable());
                assignmentForm.setLinkFileUrl(StringUtil.nullToStr(
                        assignmentModel.getLinkFileUrl()));
                assignmentForm.setLinkFileName(StringUtil.nullToStr(
                        assignmentModel.getLinkFileName()));
                assignmentForm.setType(assignmentModel.getType());
                assignmentForm.setFileType(assignmentModel.getFileType());
                assignmentForm.setTrackNumber(assignmentModel.getIsTrackNumber());
                assignmentForm.setViewNumber(assignmentModel.getViewNumber());
                assignmentForm.setAvailable(assignmentModel.getIsAvailable());
                assignmentForm.setCanDelete(assignmentModel.getCanDelete());
                assignmentForm.setExerciseType(assignmentModel.getExerciseType());
                assignmentForm.setCreateTime(DateTimeUtil.FormatDateToWeb1(
                        assignmentModel.getCreateTime()));
                System.out.println(assignmentModel.getCreateTime());
                assignmentForm.setDisAfterTime(DateTimeUtil.FormatDateToWeb1(
                        assignmentModel.getDisAfterTime()));
                assignmentForm.setDisUntilTime(DateTimeUtil.FormatDateToWeb1(
                        assignmentModel.getDisUntilTime()));

                return assignmentForm;
        }

        public int getAssignmentID()
        {
                return assignmentID;
        }

        public void setAssignmentID(int assignmentID)
        {
                this.assignmentID = assignmentID;
        }

        public int getCourseID()
        {
                return courseID;
        }

        public void setCourseID(int courseID)
        {
                this.courseID = courseID;
        }

        public int getParentID()
        {
                return parentID;
        }

        public void setParentID(int parentID)
        {
                this.parentID = parentID;
        }

        public int getRootID()
        {
                return rootID;
        }

        public void setRootID(int rootID)
        {
                this.rootID = rootID;
        }

        public int getOrderIndex()
        {
                return orderIndex;
        }

        public void setOrderIndex(int orderIndex)
        {
                this.orderIndex = orderIndex;
        }

        public String getName()
        {
                return name;
        }

        public void setName(String name)
        {
                this.name = name;
        }

        public String getDescription()
        {
                return description;
        }

        public void setDescription(String description)
        {
                this.description = description;
        }

        public String getViewable()
        {
                return isViewable;
        }

        public void setViewable(String viewable)
        {
                isViewable = viewable;
        }

        public String getLinkFileUrl()
        {
                return linkFileUrl;
        }

        public void setLinkFileUrl(String linkFileUrl)
        {
                this.linkFileUrl = linkFileUrl;
        }

        public String getLinkFileName()
        {
                return linkFileName;
        }

        public void setLinkFileName(String linkFileName)
        {
                this.linkFileName = linkFileName;
        }

        public String getType()
        {
                return type;
        }

        public void setType(String type)
        {
                this.type = type;
        }

        public String getFileType()
        {
                return fileType;
        }

        public void setFileType(String fileType)
        {
                this.fileType = fileType;
        }

        public String getTrackNumber()
        {
                return isTrackNumber;
        }

        public void setTrackNumber(String trackNumber)
        {
                isTrackNumber = trackNumber;
        }

        public int getViewNumber()
        {
                return viewNumber;
        }

        public void setViewNumber(int viewNumber)
        {
                this.viewNumber = viewNumber;
        }

        public String getAvailable()
        {
                return isAvailable;
        }

        public void setAvailable(String available)
        {
                isAvailable = available;
        }

        public String getCreateTime()
        {
                return createTime;
        }

        public void setCreateTime(String createTime)
        {
                this.createTime = createTime;
        }

        public String getDisAfterTime()
        {
                return disAfterTime;
        }

        public void setDisAfterTime(String disAfterTime)
        {
                this.disAfterTime = disAfterTime;
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

        public String getDisUntilTime()
        {
                return disUntilTime;
        }

        public void setDisUntilTime(String disUntilTime)
        {
                this.disUntilTime = disUntilTime;
        }
}
