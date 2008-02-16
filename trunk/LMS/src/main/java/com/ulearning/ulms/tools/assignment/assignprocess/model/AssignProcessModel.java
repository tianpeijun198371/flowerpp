package com.ulearning.ulms.tools.assignment.assignprocess.model;

import org.apache.commons.lang.builder.ToStringBuilder;

import java.io.Serializable;

import java.util.Date;


/**
 * @author Hibernate CodeGenerator
 */
public class AssignProcessModel implements Serializable
{
        /**
         * identifier field
         */
        private int assignProcessID;

        /**
         * nullable persistent field
         */
        private int relationID;

        /**
         * nullable persistent field
         */
        private String relationType;

        /**
         * nullable persistent field
         */
        private String state;

        /**
         * nullable persistent field
         */
        private int userID;

        /**
         * nullable persistent field
         */
        private Date access_datetime;

        /**
         * nullable persistent field
         */
        private Date submit_datetime;

        /**
         * nullable persistent field
         */
        private String stuRemark;

        /**
         * nullable persistent field
         */
        private String attach_path_stu;

        /**
         * nullable persistent field
         */
        private Date update_datetime;

        /**
         * nullable persistent field
         */
        private String score;

        /**
         * nullable persistent field
         */
        private String teaRemark;

        /**
         * nullable persistent field
         */
        private String attach_path_tea;

        /**
         * full constructor
         */
        public AssignProcessModel(int relationID, String relationType,
                                  String state, int userID, Date access_datetime, Date submit_datetime,
                                  String stuRemark, String attach_path_stu, Date update_datetime,
                                  String score, String teaRemark, String attach_path_tea)
        {
                this.relationID = relationID;
                this.relationType = relationType;
                this.state = state;
                this.userID = userID;
                this.access_datetime = access_datetime;
                this.submit_datetime = submit_datetime;
                this.stuRemark = stuRemark;
                this.attach_path_stu = attach_path_stu;
                this.update_datetime = update_datetime;
                this.score = score;
                this.teaRemark = teaRemark;
                this.attach_path_tea = attach_path_tea;
        }

        /**
         * default constructor
         */
        public AssignProcessModel()
        {
        }

        public int getAssignProcessID()
        {
                return this.assignProcessID;
        }

        public void setAssignProcessID(int assignProcessID)
        {
                this.assignProcessID = assignProcessID;
        }

        public int getRelationID()
        {
                return this.relationID;
        }

        public void setRelationID(int relationID)
        {
                this.relationID = relationID;
        }

        public String getRelationType()
        {
                return this.relationType;
        }

        public void setRelationType(String relationType)
        {
                this.relationType = relationType;
        }

        public String getState()
        {
                return this.state;
        }

        public void setState(String state)
        {
                this.state = state;
        }

        public int getUserID()
        {
                return this.userID;
        }

        public void setUserID(int userID)
        {
                this.userID = userID;
        }

        public Date getAccess_datetime()
        {
                return this.access_datetime;
        }

        public void setAccess_datetime(Date access_datetime)
        {
                this.access_datetime = access_datetime;
        }

        public Date getSubmit_datetime()
        {
                return this.submit_datetime;
        }

        public void setSubmit_datetime(Date submit_datetime)
        {
                this.submit_datetime = submit_datetime;
        }

        public String getStuRemark()
        {
                return this.stuRemark;
        }

        public void setStuRemark(String stuRemark)
        {
                this.stuRemark = stuRemark;
        }

        public String getAttach_path_stu()
        {
                return this.attach_path_stu;
        }

        public void setAttach_path_stu(String attach_path_stu)
        {
                this.attach_path_stu = attach_path_stu;
        }

        public Date getUpdate_datetime()
        {
                return this.update_datetime;
        }

        public void setUpdate_datetime(Date update_datetime)
        {
                this.update_datetime = update_datetime;
        }

        public String getScore()
        {
                return this.score;
        }

        public void setScore(String score)
        {
                this.score = score;
        }

        public String getTeaRemark()
        {
                return this.teaRemark;
        }

        public void setTeaRemark(String teaRemark)
        {
                this.teaRemark = teaRemark;
        }

        public String getAttach_path_tea()
        {
                return this.attach_path_tea;
        }

        public void setAttach_path_tea(String attach_path_tea)
        {
                this.attach_path_tea = attach_path_tea;
        }

        public String toString()
        {
                return new ToStringBuilder(this).append("assignProcessID",
                        getAssignProcessID()).toString();
        }
}
