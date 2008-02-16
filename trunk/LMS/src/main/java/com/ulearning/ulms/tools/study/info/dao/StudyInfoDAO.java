/**
 * Copyright (c)2006 Beijing WenhuaOnline Sci-tech DevelopmentCo.,Ltd. 
 * All rights reserved. 
 *
 * User: zhangly
 * Date: 2007-10-23 15:21:33 
 */

package com.ulearning.ulms.tools.study.info.dao;

import com.ulearning.ulms.tools.study.info.exceptions.StudyInfoDAOSysException;
import com.ulearning.ulms.tools.study.info.form.StudyInfoForm;
import com.ulearning.ulms.admin.plan.exceptions.PlanDAOSysException;
import com.ulearning.ulms.organ.exceptions.OrganDAOSysException;

import java.util.List;

public interface StudyInfoDAO
{
        public int addStudyInfo(StudyInfoForm details) throws StudyInfoDAOSysException;

        public void updateStudyInfo(StudyInfoForm details) throws StudyInfoDAOSysException;

        public void removeStudyInfo(String StudyInfoID) throws StudyInfoDAOSysException;

        public StudyInfoForm getStudyInfo(int StudyInfoID) throws StudyInfoDAOSysException;

        public List getInfoList(int StudyInfoID) throws StudyInfoDAOSysException;

        public List getStudyInfoList(int OrgID) throws StudyInfoDAOSysException;

        public List getStudyRecordList(int courseID) throws StudyInfoDAOSysException;

        public List getStudyRecordList(int courseID, int userID) throws StudyInfoDAOSysException;

        //学情调查的搜索
        public List searchStudyInfoList(StudyInfoForm studyInfo,int orgID) throws StudyInfoDAOSysException;

        //所有示范校中学校的名称
        public List getAllSchools (int orgID) throws StudyInfoDAOSysException;

        //所有学校名称
        public List getAllSchools (int orgID, int examType) throws StudyInfoDAOSysException;

        //平均分
        public String[][]  getScoresBySchool (List getAllSchools,List getStudyInfoList) throws StudyInfoDAOSysException;

        //各项的平均分
        public String[] getScoresByType(List getAllStudentsBySchool) throws StudyInfoDAOSysException;

        //示范学校所有人
        public List  getAllUsers (int orgID) throws StudyInfoDAOSysException;

        //统计中的学校最高成绩
        public int getMaxNum(String school,int examType) throws StudyInfoDAOSysException;

        //示范校的最高分
        public int getMaxSchNum(int orgID,int examType) throws StudyInfoDAOSysException;

        //示范校的最低分
        public int getMinSchNum(int orgID,int examType) throws StudyInfoDAOSysException;

        //统计中的学校最低成绩
        public int getMinNum(String school,int examType) throws StudyInfoDAOSysException;

        //示范校的总分
        public int allScores(int orgID,int examType) throws StudyInfoDAOSysException;

        //示范校的平均分
        public int getAverageScore(int allUser,int getAllScores) throws StudyInfoDAOSysException;

        //统计中
        public List getAllStudents (String school,int examType) throws StudyInfoDAOSysException;

        //示范校中学校中的学生人数
        public int allStudents (String school,int examType,int orgID) throws StudyInfoDAOSysException;

        //示范校参加不同测试的人数
        public int allUser (int orgID ,int examType) throws StudyInfoDAOSysException;

        //取学校的学生的学情调查
        public List getAllStudentsBySchool (String school,int examType,int orgID,int type) throws StudyInfoDAOSysException;

        //取不同试卷的总成绩
        public int totalScore (int orgID,int examType) throws StudyInfoDAOSysException;

        /*不同分数段的人数*/
        //优秀率，及格率，低分率
        public int usersNumber (int orgID,int examType,String school,double max,double min) throws StudyInfoDAOSysException;

        // 各个分数段的人数
        public int usersNum (int orgID,int examType,String school,int max,int min) throws StudyInfoDAOSysException;

        //public int getStudyInfoIDByPostalcode(String postalcode) throws StudyInfoDAOSysException;
}
