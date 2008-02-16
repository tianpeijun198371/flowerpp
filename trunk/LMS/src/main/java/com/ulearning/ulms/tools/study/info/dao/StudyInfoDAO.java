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

        //ѧ����������
        public List searchStudyInfoList(StudyInfoForm studyInfo,int orgID) throws StudyInfoDAOSysException;

        //����ʾ��У��ѧУ������
        public List getAllSchools (int orgID) throws StudyInfoDAOSysException;

        //����ѧУ����
        public List getAllSchools (int orgID, int examType) throws StudyInfoDAOSysException;

        //ƽ����
        public String[][]  getScoresBySchool (List getAllSchools,List getStudyInfoList) throws StudyInfoDAOSysException;

        //�����ƽ����
        public String[] getScoresByType(List getAllStudentsBySchool) throws StudyInfoDAOSysException;

        //ʾ��ѧУ������
        public List  getAllUsers (int orgID) throws StudyInfoDAOSysException;

        //ͳ���е�ѧУ��߳ɼ�
        public int getMaxNum(String school,int examType) throws StudyInfoDAOSysException;

        //ʾ��У����߷�
        public int getMaxSchNum(int orgID,int examType) throws StudyInfoDAOSysException;

        //ʾ��У����ͷ�
        public int getMinSchNum(int orgID,int examType) throws StudyInfoDAOSysException;

        //ͳ���е�ѧУ��ͳɼ�
        public int getMinNum(String school,int examType) throws StudyInfoDAOSysException;

        //ʾ��У���ܷ�
        public int allScores(int orgID,int examType) throws StudyInfoDAOSysException;

        //ʾ��У��ƽ����
        public int getAverageScore(int allUser,int getAllScores) throws StudyInfoDAOSysException;

        //ͳ����
        public List getAllStudents (String school,int examType) throws StudyInfoDAOSysException;

        //ʾ��У��ѧУ�е�ѧ������
        public int allStudents (String school,int examType,int orgID) throws StudyInfoDAOSysException;

        //ʾ��У�μӲ�ͬ���Ե�����
        public int allUser (int orgID ,int examType) throws StudyInfoDAOSysException;

        //ȡѧУ��ѧ����ѧ�����
        public List getAllStudentsBySchool (String school,int examType,int orgID,int type) throws StudyInfoDAOSysException;

        //ȡ��ͬ�Ծ���ܳɼ�
        public int totalScore (int orgID,int examType) throws StudyInfoDAOSysException;

        /*��ͬ�����ε�����*/
        //�����ʣ������ʣ��ͷ���
        public int usersNumber (int orgID,int examType,String school,double max,double min) throws StudyInfoDAOSysException;

        // ���������ε�����
        public int usersNum (int orgID,int examType,String school,int max,int min) throws StudyInfoDAOSysException;

        //public int getStudyInfoIDByPostalcode(String postalcode) throws StudyInfoDAOSysException;
}
