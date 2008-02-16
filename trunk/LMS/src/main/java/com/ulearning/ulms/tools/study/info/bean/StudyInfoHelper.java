/**
 * Copyright (c)2006 Beijing WenhuaOnline Sci-tech DevelopmentCo.,Ltd. 
 * All rights reserved. 
 *
 * User: zhangly
 * Date: 2007-10-24 9:27:26 
 */

package com.ulearning.ulms.tools.study.info.bean;

import com.ulearning.ulms.tools.study.info.dao.StudyInfoDAO;
import com.ulearning.ulms.tools.study.info.dao.StudyInfoDAOFactory;
import com.ulearning.ulms.tools.study.info.dao.StudyInfoDAOImpl;
import com.ulearning.ulms.tools.study.info.exceptions.StudyInfoDAOSysException;
import com.ulearning.ulms.tools.study.info.form.StudyInfoForm;
import com.ulearning.ulms.core.util.DateTimeUtil;
import com.ulearning.ulms.util.LMSConstants;
import com.ulearning.ulms.tools.study.info.form.StudyInfoForm;
import com.ulearning.ulms.tools.study.info.dao.StudyInfoDAO;
import com.ulearning.ulms.tools.study.info.dao.StudyInfoDAOFactory;
import com.ulearning.ulms.tools.study.info.dao.StudyInfoDAOImpl;
import com.ulearning.ulms.tools.study.info.exceptions.StudyInfoDAOSysException;
import com.ulearning.ulms.course.model.CourseModel;
import com.ulearning.ulms.course.exceptions.CourseSysException;
import com.ulearning.ulms.user.exceptions.UserDAOSysException;
import com.ulearning.ulms.user.dao.UserDAO;
import com.ulearning.ulms.user.dao.UserDAOFactory;
import com.ulearning.ulms.admin.certificate.dao.CertDAO;
import com.ulearning.ulms.admin.certificate.dao.CertDAOFactory;
import com.ulearning.ulms.admin.certificate.exceptions.CertDAOSysException;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;

import java.util.*;

public class StudyInfoHelper
{
        private static String[][] comments = new String[9][4];

        static
        {
                //����
                comments[0][0] = "�Դ���ʶ������ǿ���Ի����¼��������Ϣ�Ĳ�׽�������ж�����ǿ";
                comments[0][1] = "�Դ�����һ����ʶ���������Ի����¼��������Ϣ��һ���Ĳ�׽�������ж�����";
                comments[0][2] = "�Դ���ʶ������һ�㣬�Ի����¼��������Ϣ�Ĳ�׽�������ж�����һ��";
                comments[0][3] = "�Դ���ʶ�������ϲ�Ի����¼��������Ϣ�Ĳ�׽�������ж������ϲ�";
                //�ʻ�
                comments[1][0] = "�������յ��ʵ�ƴд֪ʶ�����ܹ��������";
                comments[1][1] = "����������ã��ܻ������ã��������ǿ";
                comments[1][2] = "�ʻ�����պ�Ӧ������һ�㣬���ֽ϶���";
                comments[1][3] = "�ʻ�������׺�����ƴд���������սϲ�";
                //�Ự
                comments[2][0] = "���ճ��Ľ��ʻỰӦ��������ǿ���ܴﵽ���Ե��ۺ���������";
                comments[2][1] = "����һ�����ճ����ʻỰ�������Ϻõ��ۺ�������ѧ����";
                comments[2][2] = "��������һ�㣬�������ܹ���ȷʹ��";
                comments[2][3] = "�����龰�ͽ��ʵ�����������ۺ�������";
                //�﷨
                comments[3][0] = "�﷨֪ʶ�����ι̣���������Ӧ��";
                comments[3][1] = "�﷨���սϺã�������Ȼ��Щ�﷨ģ������";
                comments[3][2] = "���ղ�����ʵ������ʹ�õ�������ǿ";
                comments[3][3] = "����ϲ�﷨һ֪��⣬���ջ���";
                //�Ķ�
                comments[4][0] = "�Ķ�����ǿ�������������Ķ����ɣ��������н��ʣ�����һ����������ж�˼ά����";
                comments[4][1] = "�ܻ��������Ķ����ϵ���Ϣ���߱�һ���������ж�����";
                comments[4][2] = "���һ�㣬�Ķ�����ƽ���������󲿷���ƪ��Ϣ";
                comments[4][3] = "�Ķ�����������Ķ���ƪ�����������";
                //д��
                comments[5][0] = "д��֪ʶ������ʵ��������������н�ǿ��д������";
                comments[5][1] = "���±Ƚ��������ܻ���������Ҫ��������";
                comments[5][2] = "����ǿ�����Ҫ����˼��";
                comments[5][3] = "д����������ѱ����Լ�����˼������ܶ�";
                //�ۺ�
                comments[6][0] = "���ڻ���֪ʶ���ۺ�����������ǿ�����н�ǿ�������˼ά�ж�����";
                comments[6][1] = "�������л���֪ʶ���ۺ����ã��߱�һ���������ж�����";
                comments[6][2] = "���һ�㣬����ƽ���������󲿷���ƪ��Ϣ��";
                comments[6][3] = "�ۺ����������ϲ������ƪ����������ϲ�";
                //����
                comments[7][0] = "ѧ�������������������յ�ʮ����ʵ������ȷ������";
                comments[7][1] = "ѧ������������ã��󲿷��������ݻ������ա�";
                comments[7][2] = "�����������һ�㣬�����ǿ�����������ϰ��";
                comments[7][3] = "��������������ֹۣ�ؽ�����Ρ�";
                //������
                comments[8][0] = "ѧ�����ۺ�����ǿ,���������˸���֪ʶ������";
                comments[8][1] = "ѧ�����ۺ������Ϻ�,���������˸���֪ʶ������";
                comments[8][2] = "ѧ�����ۺ�����һ��,����֪ʶ���ݻ��д������";
                comments[8][3] = "ѧ�����ۺ������ܲ�,��Ҫ�ӻ���֪ʶ����ѧ��";
        }

        /**
         * Wrapping the get plan method for JSP and  the other modules
         *
         * @param StudyInfoID
         * @return the admin modle according to the planID
         */
        public static StudyInfoForm getStudyInfo(int StudyInfoID)
        {
                StudyInfoForm pf = null;
                System.out.println("getStudyInfo start!");
                try
                {
                        StudyInfoDAO studyInfoDao = StudyInfoDAOFactory.getDAO();

                        pf = studyInfoDao.getStudyInfo(StudyInfoID);
                }
                catch (StudyInfoDAOSysException udse)
                {
                        udse.printStackTrace();
                }

                return pf;
        }

        /**
         * Gets the accounting commments for sudent according to the score and the question type
         *
         * @param totalScore
         * @param reallyScore
         * @param type
         * @return
         */
        public static String getCommments(float totalScore, float reallyScore, int type)
        {
                String commments = null;

                int scope = 0;
                double score = reallyScore;
                try
                {
                        if ((totalScore * 0.9 <= score) && (score <= totalScore))
                        {
                                scope = 0;
                        }
                        else if ((totalScore * 0.7 <= score) && (score < totalScore * 0.9))
                        {
                                scope = 1;
                        }
                        else if ((totalScore * 0.6 <= score) && (score < totalScore * 0.7))
                        {
                                scope = 2;
                        }
                        else if (totalScore * 0.6 > score)
                        {
                                scope = 3;
                        }
                        commments = comments[type][scope];
                }
                catch (StudyInfoDAOSysException udse)
                {
                        udse.printStackTrace();
                }
                return commments;
        }

        public List getStudyRecordList(int courseID, int userID)
        {
                List studyRecordList = null;

                try
                {
                        StudyInfoDAO StudyInfoDAO = StudyInfoDAOFactory.getDAO();
                        studyRecordList = StudyInfoDAO.getStudyRecordList(courseID, userID);
                }
                catch (StudyInfoDAOSysException udse)
                {
                        udse.printStackTrace();
                }

                //studyRecordList = detectPlanDate(studyRecordList); //�����ж�����
                return studyRecordList;
        }

        public List getStudyRecordList(int courseID)
        {
                List studyRecordList = null;

                try
                {
                        StudyInfoDAO StudyInfoDAO = StudyInfoDAOFactory.getDAO();
                        studyRecordList = StudyInfoDAO.getStudyRecordList(courseID);
                }
                catch (StudyInfoDAOSysException udse)
                {
                        udse.printStackTrace();
                }

                return studyRecordList;
        }

        public List getInfoList(int StudyInfoID)
        {
                List infoList = null;

                try
                {
                        StudyInfoDAO StudyInfoDAO = StudyInfoDAOFactory.getDAO();
                        infoList = StudyInfoDAO.getStudyRecordList(StudyInfoID);
                }
                catch (StudyInfoDAOSysException udse)
                {
                        udse.printStackTrace();
                }

                return infoList;
        }


        public static List getStudyInfoList(int orgID)
        {
                List infoList = null;

                try
                {
                        StudyInfoDAO StudyInfoDAO = StudyInfoDAOFactory.getDAO();
                        infoList = StudyInfoDAO.getStudyInfoList(orgID);
                }
                catch (StudyInfoDAOSysException udse)
                {
                        udse.printStackTrace();
                }

                return infoList;
        }

        public List searchStudyInfoList(StudyInfoForm studyInfo, int orgID)
        {
                List studyRecordList = null;

                try
                {
                        StudyInfoDAO studyInfoDAO = StudyInfoDAOFactory.getDAO();
                        studyRecordList = studyInfoDAO.searchStudyInfoList(studyInfo, orgID);
                }
                catch (StudyInfoDAOSysException udse)
                {
                        udse.printStackTrace();
                }

                return studyRecordList;
        }

        public static List getAllSchools(int orgID, int examType)
        {
                List studyInfoList = null;

                try
                {
                        StudyInfoDAO studyInfoDAO = StudyInfoDAOFactory.getDAO();
                        studyInfoList = studyInfoDAO.getAllSchools(orgID, examType);
                }
                catch (StudyInfoDAOSysException udse)
                {
                        udse.printStackTrace();
                }

                return studyInfoList;
        }

        public static List getAllSchools(int orgID)
        {
                List studyInfoList = null;
                try
                {
                        StudyInfoDAO StudyInfoDAO = StudyInfoDAOFactory.getDAO();
                        studyInfoList = StudyInfoDAO.getAllSchools(orgID);
                }
                catch (StudyInfoDAOSysException udse)
                {
                        udse.printStackTrace();
                }
                return studyInfoList;
        }

        public static List getAllStudents(String school, int examType)
        {
                List studyInfoList = null;
                try
                {
                        StudyInfoDAO StudyInfoDAO = StudyInfoDAOFactory.getDAO();
                        studyInfoList = StudyInfoDAO.getAllStudents(school, examType);
                }
                catch (StudyInfoDAOSysException udse)
                {
                        udse.printStackTrace();
                }
                return studyInfoList;
        }

        public static List getAllStudentsBySchool(String school, int examType, int orgID, int type)
        {
                List studyInfoList = null;
                try
                {
                        StudyInfoDAO StudyInfoDAO = StudyInfoDAOFactory.getDAO();
                        studyInfoList = StudyInfoDAO.getAllStudentsBySchool(school, examType, orgID, type);
                }
                catch (StudyInfoDAOSysException udse)
                {
                        udse.printStackTrace();
                }
                return studyInfoList;
        }


        public static String[][] getScoresBySchool(List getAllSchools, List getStudyInfoList)
        {
                String[][] typeInfo = new String[getAllSchools.size()][2];
                try
                {
                        StudyInfoDAO StudyInfoDAO = StudyInfoDAOFactory.getDAO();
                        typeInfo = StudyInfoDAO.getScoresBySchool(getAllSchools, getStudyInfoList);
                }
                catch (StudyInfoDAOSysException udse)
                {
                        udse.printStackTrace();
                }
                return typeInfo;
        }

        public static String[] getScoresByType(List getAllStudentsBySchool)
        {
                String[] info = new String[8];
                try
                {
                        StudyInfoDAO StudyInfoDAO = StudyInfoDAOFactory.getDAO();
                        info = StudyInfoDAO.getScoresByType(getAllStudentsBySchool);
                }
                catch (StudyInfoDAOSysException udse)
                {
                        udse.printStackTrace();
                }
                return info;
        }

        public static int getMaxNum(String school, int examType)
        {
                int maxNum = 0;

                try
                {
                        StudyInfoDAO StudyInfoDAO = StudyInfoDAOFactory.getDAO();
                        maxNum = StudyInfoDAO.getMaxNum(school, examType);
                }
                catch (StudyInfoDAOSysException udse)
                {
                        udse.printStackTrace();
                }

                return maxNum;
        }

        public static int totalScore(int orgID, int examType)
        {
                int totalSco = 0;

                try
                {
                        StudyInfoDAO StudyInfoDAO = StudyInfoDAOFactory.getDAO();
                        totalSco = StudyInfoDAO.totalScore(orgID, examType);
                }
                catch (StudyInfoDAOSysException udse)
                {
                        udse.printStackTrace();
                }

                return totalSco;
        }

        public static int getMinNum(String school, int examType)
        {
                int minNum = 0;

                try
                {
                        StudyInfoDAO StudyInfoDAO = StudyInfoDAOFactory.getDAO();
                        minNum = StudyInfoDAO.getMinNum(school, examType);
                }
                catch (StudyInfoDAOSysException udse)
                {
                        udse.printStackTrace();
                }

                return minNum;
        }

        public static int getMinSchNum(int orgID, int examType)
        {
                int minNum = 0;

                try
                {
                        StudyInfoDAO StudyInfoDAO = StudyInfoDAOFactory.getDAO();
                        minNum = StudyInfoDAO.getMinSchNum(orgID, examType);
                }
                catch (StudyInfoDAOSysException udse)
                {
                        udse.printStackTrace();
                }

                return minNum;
        }

        public static int getMaxSchNum(int orgID, int examType)
        {
                int maxNum = 0;

                try
                {
                        StudyInfoDAO StudyInfoDAO = StudyInfoDAOFactory.getDAO();
                        maxNum = StudyInfoDAO.getMaxSchNum(orgID, examType);
                }
                catch (StudyInfoDAOSysException udse)
                {
                        udse.printStackTrace();
                }

                return maxNum;
        }

        public static int getAverageScore(int allUser, int getAllScores)
        {
                int averScore = 0;

                try
                {
                        StudyInfoDAO StudyInfoDAO = StudyInfoDAOFactory.getDAO();
                        averScore = StudyInfoDAO.getMaxSchNum(allUser, getAllScores);
                }
                catch (StudyInfoDAOSysException udse)
                {
                        udse.printStackTrace();
                }

                return averScore;
        }

        public static int allScores(int orgID, int examType)
        {
                int allStuNum = 0;

                try
                {
                        StudyInfoDAO StudyInfoDAO = StudyInfoDAOFactory.getDAO();
                        allStuNum = StudyInfoDAO.allScores(orgID, examType);
                }
                catch (StudyInfoDAOSysException udse)
                {
                        udse.printStackTrace();
                }

                return allStuNum;
        }

        public static int allStudents(String school, int examType,int orgID)
        {
                int allStuNum = 0;

                try
                {
                        StudyInfoDAO StudyInfoDAO = StudyInfoDAOFactory.getDAO();
                        allStuNum = StudyInfoDAO.allStudents(school, examType,orgID);
                }
                catch (StudyInfoDAOSysException udse)
                {
                        udse.printStackTrace();
                }

                return allStuNum;
        }

        public static int allUser(int orgID, int examType)
        {
                int alluser = 0;

                try
                {
                        StudyInfoDAO StudyInfoDAO = StudyInfoDAOFactory.getDAO();
                        alluser = StudyInfoDAO.allUser(orgID, examType);
                }
                catch (StudyInfoDAOSysException udse)
                {
                        udse.printStackTrace();
                }

                return alluser;
        }

        public static int usersNumber(int orgID, int examType, String school,double max,double min)
        {
                int usersNumber = 0;

                try
                {
                        StudyInfoDAO StudyInfoDAO = StudyInfoDAOFactory.getDAO();
                        usersNumber = StudyInfoDAO.usersNumber(orgID, examType, school,max,min);
                }
                catch (StudyInfoDAOSysException udse)
                {
                        udse.printStackTrace();
                }

                return usersNumber;
        }

         public static int usersNum(int orgID, int examType, String school,int max,int min)
        {
                int usersNum = 0;

                try
                {
                        StudyInfoDAO StudyInfoDAO = StudyInfoDAOFactory.getDAO();
                        usersNum = StudyInfoDAO.usersNum(orgID, examType, school,max,min);
                }
                catch (StudyInfoDAOSysException udse)
                {
                        udse.printStackTrace();
                }

                return usersNum;
        }
}
