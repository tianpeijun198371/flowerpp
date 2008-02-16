/**
 * CourseUserModel.java.
 * User: fengch  Date: 2004-5-9
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.course.model;

import org.apache.struts.action.ActionForm;

import java.io.Serializable;

import java.util.Date;


public class CourseUserModel extends ActionForm implements Serializable
{
        private int courseID;
        private int userID;
        private int state;
        private int relationID;
        private int type = 1;
        private String name;
        private String loginName;
        private String sex;
        private String orgName;
        private CourseRoleListModel courseRoles;
        private Date joinTime;
        private Date applyTime;
        private Date finishedTime;
        private String score;
        private int isPass;
        private float period;
        private float credit;
        private String courseName;
        private String courseCode;

        //辅助数据
        private String useraccount;

        public CourseUserModel()
        {
        }

        public CourseUserModel(CourseUser cu)
        {
                this.relationID = ((CourseUserPK) cu.getComp_id()).getRelationID();
                this.userID = ((CourseUserPK) cu.getComp_id()).getUserID();
                this.type = ((CourseUserPK) cu.getComp_id()).getType();
                this.state = Integer.parseInt(cu.getState());
                this.joinTime = cu.getJoinTime();
                this.applyTime = cu.getApplyTime();
                this.finishedTime = cu.getFinishedTime();
        }

        //添加一用户帐户余额信息，按班添加用户时使用
        public CourseUserModel(int relationID, int userID, int state, String name,
                               String loginName, Date joinTime, Date applyTime, Date finishedTime,
                               String useraccount)
        {
                this.relationID = relationID;
                this.courseID = relationID;
                this.userID = userID;
                this.state = state;
                this.name = name;
                this.loginName = loginName;
                this.joinTime = joinTime;
                this.applyTime = applyTime;
                this.finishedTime = finishedTime;
                this.useraccount = useraccount;
        }

        public CourseUserModel(int relationID, int type, int userID, int state,
                               String name, String loginName, Date joinTime, Date applyTime,
                               Date finishedTime)
        {
                this.relationID = relationID;
                this.courseID = relationID;
                this.userID = userID;
                this.state = state;
                this.type = type;
                this.name = name;
                this.loginName = loginName;
                this.joinTime = joinTime;
                this.applyTime = applyTime;
                this.finishedTime = finishedTime;
        }

        public CourseUserModel(int relationID, int userID, int state, String name,
                               String loginName, CourseRoleListModel courseRoles, Date joinTime,
                               Date applyTime, Date finishedTime)
        {
                this.relationID = relationID;
                this.courseID = relationID;
                this.userID = userID;
                this.state = state;
                this.name = name;
                this.loginName = loginName;
                this.courseRoles = courseRoles;
                this.joinTime = joinTime;
                this.applyTime = applyTime;
                this.finishedTime = finishedTime;
        }

        public CourseUserModel(int relationID, int type, int userID, int state,
                               String name, String loginName, CourseRoleListModel courseRoles,
                               Date joinTime, Date applyTime, Date finishedTime)
        {
                this.relationID = relationID;
                this.courseID = relationID;
                this.userID = userID;
                this.state = state;
                this.type = type;
                this.name = name;
                this.loginName = loginName;
                this.courseRoles = courseRoles;
                this.joinTime = joinTime;
                this.applyTime = applyTime;
                this.finishedTime = finishedTime;
        }

        public CourseUserModel(int relationID, int userID, int state, String name,
                               String loginName, String orgName, CourseRoleListModel courseRoles,
                               Date joinTime, Date applyTime, Date finishedTime)
        {
                this.relationID = relationID;
                this.courseID = relationID;
                this.userID = userID;
                this.state = state;
                this.name = name;
                this.loginName = loginName;
                this.orgName = orgName;
                this.courseRoles = courseRoles;
                this.joinTime = joinTime;
                this.applyTime = applyTime;
                this.finishedTime = finishedTime;
        }

        public CourseUserModel(int relationID, int userID, int state, String name,
                               String loginName, String sex, String orgName,
                               CourseRoleListModel courseRoles, Date joinTime, Date applyTime,
                               Date finishedTime)
        {
                this.relationID = relationID;
                this.courseID = relationID;
                this.userID = userID;
                this.state = state;
                this.name = name;
                this.loginName = loginName;
                this.sex = sex;
                this.orgName = orgName;
                this.courseRoles = courseRoles;
                this.joinTime = joinTime;
                this.applyTime = applyTime;
                this.finishedTime = finishedTime;
        }

        public CourseUserModel(int relationID, int type, int userID, int state,
                               String name, String loginName, String sex, String orgName,
                               CourseRoleListModel courseRoles, Date joinTime, Date applyTime,
                               Date finishedTime)
        {
                this.relationID = relationID;
                this.courseID = relationID;
                this.type = type;
                this.userID = userID;
                this.state = state;
                this.name = name;
                this.loginName = loginName;
                this.sex = sex;
                this.orgName = orgName;
                this.courseRoles = courseRoles;
                this.joinTime = joinTime;
                this.applyTime = applyTime;
                this.finishedTime = finishedTime;
        }

        public CourseUserModel(int relationID, int type, int userID, int state,
                               String name, String loginName, String orgName,
                               CourseRoleListModel courseRoles, Date joinTime, Date applyTime,
                               Date finishedTime)
        {
                this.relationID = relationID;
                this.courseID = relationID;
                this.type = type;
                this.userID = userID;
                this.state = state;
                this.name = name;
                this.loginName = loginName;
                this.orgName = orgName;
                this.courseRoles = courseRoles;
                this.joinTime = joinTime;
                this.applyTime = applyTime;
                this.finishedTime = finishedTime;
        }

        public String getUseraccount()
        {
                return useraccount;
        }

        public void setUseraccount(String useraccount)
        {
                this.useraccount = useraccount;
        }

        public String getOrgName()
        {
                return orgName;
        }

        public void setOrgName(String orgName)
        {
                this.orgName = orgName;
        }

        public String getSex()
        {
                return sex;
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

        public float getPeriod()
        {
                return period;
        }

        public void setPeriod(float period)
        {
                this.period = period;
        }

        public void setSex(String sex)
        {
                this.sex = sex;
        }

        public String getScore()
        {
                return score;
        }

        public void setScore(String score)
        {
                this.score = score;
        }

        public int getIsPass()
        {
                return this.isPass;
        }

        public void setIsPass(int isPass)
        {
                this.isPass = isPass;
        }

        public int getCourseID()
        {
                return courseID;
        }

        public void setCourseID(int courseID)
        {
                this.courseID = courseID;
        }

        public int getUserID()
        {
                return userID;
        }

        public void setUserID(int userID)
        {
                this.userID = userID;
        }

        public int getState()
        {
                return state;
        }

        public void setState(int state)
        {
                this.state = state;
        }

        public String getName()
        {
                return name;
        }

        public void setName(String name)
        {
                this.name = name;
        }

        public String getLoginName()
        {
                return loginName;
        }

        public void setLoginName(String loginName)
        {
                this.loginName = loginName;
        }

        public CourseRoleListModel getCourseRoles()
        {
                return courseRoles;
        }

        public void setCourseRoles(CourseRoleListModel courseRoles)
        {
                this.courseRoles = courseRoles;
        }

        public Date getJoinTime()
        {
                return joinTime;
        }

        public void setJoinTime(Date joinTime)
        {
                this.joinTime = joinTime;
        }

        public Date getApplyTime()
        {
                return applyTime;
        }

        public void setApplyTime(Date applyTime)
        {
                this.applyTime = applyTime;
        }

        public Date getFinishedTime()
        {
                return finishedTime;
        }

        public void setFinishedTime(Date finishedTime)
        {
                this.finishedTime = finishedTime;
        }

        public int isPass()
        {
                return isPass;
        }

        public void setPass(int pass)
        {
                isPass = pass;
        }

        public int getPass()
        {
                return isPass;
        }

        public float getCredit()
        {
                return credit;
        }

        public void setCredit(float credit)
        {
                this.credit = credit;
        }

        public String getCourseName()
        {
                return courseName;
        }

        public void setCourseName(String courseName)
        {
                this.courseName = courseName;
        }

        public String getCourseCode()
        {
                return courseCode;
        }

        public void setCourseCode(String courseCode)
        {
                this.courseCode = courseCode;
        }

        public CourseUser getCourseUser()
        {
                CourseUser cu = new CourseUser();
                CourseUserPK cupk = new CourseUserPK(userID, relationID, type);
                cu.setComp_id(cupk);
                cu.setApplyTime(this.applyTime);
                cu.setState(String.valueOf(this.state));
                cu.setJoinTime(this.joinTime);
                cu.setFinishedTime(this.finishedTime);

                return cu;
        }
}
