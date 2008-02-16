/** * AssignProcessForm.java.
 * User: xiejh  Date: 2004-4-22 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.tools.assignment.assignprocess.form;

import com.ulearning.ulms.core.util.StringUtil;
import com.ulearning.ulms.tools.assignment.assignprocess.model.AssignProcessModel;
import com.ulearning.ulms.tools.upload.model.UploadForm;

import java.util.Date;


public class AssignProcessForm extends UploadForm
{
        private int assignProcessID = 0;
        private int relationID = 0;
        private String relationType = null;
        private String state = null;
        private int userID = 0;
        private Date access_datetime = null;
        private Date submit_datetime = null;
        private String stuRemark = null;
        private String attach_path_stu = null;
        private Date update_datetime = null;
        private String score = null;
        private String teaRemark = null;
        private String attach_path_tea = null;
        private String flag = ""; //1浏览 上传作业 3修改作业 4查看批改作业
        private AssignProcessModel assignProcessModel = null;

        public AssignProcessModel getAssignProcessModel()
        {
                assignProcessModel = new AssignProcessModel();
                assignProcessModel.setAssignProcessID(this.assignProcessID);
                assignProcessModel.setRelationID(this.relationID);
                assignProcessModel.setRelationType(this.relationType);
                assignProcessModel.setState(this.state);
                assignProcessModel.setUserID(this.userID);
                assignProcessModel.setAccess_datetime(this.access_datetime);
                assignProcessModel.setSubmit_datetime(this.submit_datetime);
                assignProcessModel.setStuRemark(this.stuRemark);
                assignProcessModel.setAttach_path_stu(this.attach_path_stu);
                assignProcessModel.setUpdate_datetime(this.update_datetime);
                assignProcessModel.setScore(this.score);
                assignProcessModel.setTeaRemark(this.teaRemark);
                assignProcessModel.setAttach_path_tea(this.attach_path_tea);

                return assignProcessModel;
        }

        public AssignProcessForm getAssignProcessForm(
                AssignProcessModel assignProcessModel)
        {
                AssignProcessForm assignProcessForm = new AssignProcessForm();
                assignProcessForm.setAssignProcessID(assignProcessModel.getAssignProcessID());
                assignProcessForm.setRelationID(assignProcessModel.getRelationID());
                assignProcessForm.setRelationType(assignProcessModel.getRelationType());
                assignProcessForm.setState(assignProcessModel.getState());
                assignProcessForm.setUserID(assignProcessModel.getUserID());
                assignProcessForm.setAccess_datetime(assignProcessModel.getAccess_datetime());
                assignProcessForm.setSubmit_datetime(assignProcessModel.getSubmit_datetime());
                assignProcessForm.setStuRemark(StringUtil.nullToStr(
                        assignProcessModel.getStuRemark()));
                assignProcessForm.setAttach_path_stu(StringUtil.nullToStr(
                        assignProcessModel.getAttach_path_stu()));
                assignProcessForm.setUpdate_datetime(assignProcessModel.getUpdate_datetime());
                assignProcessForm.setScore(StringUtil.nullToStr(
                        assignProcessModel.getScore()));
                assignProcessForm.setTeaRemark(StringUtil.nullToStr(
                        assignProcessModel.getTeaRemark()));
                assignProcessForm.setAttach_path_tea(StringUtil.nullToStr(
                        assignProcessModel.getAttach_path_tea()));

                return assignProcessForm;
        }

        public int getAssignProcessID()
        {
                return assignProcessID;
        }

        public void setAssignProcessID(int assignProcessID)
        {
                this.assignProcessID = assignProcessID;
        }

        public int getRelationID()
        {
                return relationID;
        }

        public void setRelationID(int relationID)
        {
                this.relationID = relationID;
        }

        public String getRelationType()
        {
                return relationType;
        }

        public void setRelationType(String relationType)
        {
                this.relationType = relationType;
        }

        public String getState()
        {
                return state;
        }

        public void setState(String state)
        {
                this.state = state;
        }

        public int getUserID()
        {
                return userID;
        }

        public void setUserID(int userID)
        {
                this.userID = userID;
        }

        public Date getAccess_datetime()
        {
                return access_datetime;
        }

        public void setAccess_datetime(Date access_datetime)
        {
                this.access_datetime = access_datetime;
        }

        public Date getSubmit_datetime()
        {
                return submit_datetime;
        }

        public void setSubmit_datetime(Date submit_datetime)
        {
                this.submit_datetime = submit_datetime;
        }

        public String getStuRemark()
        {
                return stuRemark;
        }

        public void setStuRemark(String stuRemark)
        {
                this.stuRemark = stuRemark;
        }

        public String getAttach_path_stu()
        {
                return attach_path_stu;
        }

        public void setAttach_path_stu(String attach_path_stu)
        {
                this.attach_path_stu = attach_path_stu;
        }

        public Date getUpdate_datetime()
        {
                return update_datetime;
        }

        public void setUpdate_datetime(Date update_datetime)
        {
                this.update_datetime = update_datetime;
        }

        public String getScore()
        {
                return score;
        }

        public void setScore(String score)
        {
                this.score = score;
        }

        public String getTeaRemark()
        {
                return teaRemark;
        }

        public void setTeaRemark(String teaRemark)
        {
                this.teaRemark = teaRemark;
        }

        public String getAttach_path_tea()
        {
                return attach_path_tea;
        }

        public void setAttach_path_tea(String attach_path_tea)
        {
                this.attach_path_tea = attach_path_tea;
        }

        public String getFlag()
        {
                return flag;
        }

        public void setFlag(String flag)
        {
                this.flag = flag;
        }
}
