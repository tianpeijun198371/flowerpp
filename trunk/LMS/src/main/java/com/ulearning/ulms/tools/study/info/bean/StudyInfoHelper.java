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
                //听力
                comments[0][0] = "对词语识别能力强，对话语或录音材料信息的捕捉、推理判断能力强";
                comments[0][1] = "对词语有一定的识别能力，对话语或录音材料信息有一定的捕捉、推理判断能力";
                comments[0][2] = "对词语识别能力一般，对话语或录音材料信息的捕捉、推理判断能力一般";
                comments[0][3] = "对词语识别能力较差，对话语或录音材料信息的捕捉、推理判断能力较差";
                //词汇
                comments[1][0] = "熟练掌握单词的拼写知识，并能够灵活运用";
                comments[1][1] = "掌握情况良好，能基本运用，但还需加强";
                comments[1][2] = "词汇的掌握和应用能力一般，出现较多错词";
                comments[1][3] = "词汇基本功底很弱，拼写、词义掌握较差";
                //会话
                comments[2][0] = "对日常的交际会话应用能力较强，能达到语言的综合熟练运用";
                comments[2][1] = "具有一定的日常交际会话能力，较好地综合运用所学语言";
                comments[2][2] = "交际能力一般，基本上能够正确使用";
                comments[2][3] = "对于情景和交际的运用能力差，综合能力差";
                //语法
                comments[3][0] = "语法知识掌握牢固，并能熟练应用";
                comments[3][1] = "语法掌握较好，但是仍然有些语法模糊不清";
                comments[3][2] = "掌握不够扎实，熟练使用的能力不强";
                comments[3][3] = "情况较差，语法一知半解，掌握混乱";
                //阅读
                comments[4][0] = "阅读能力强，能熟练掌握阅读技巧，熟练进行交际，具有一定的推理和判断思维能力";
                comments[4][1] = "能基本掌握阅读材料的信息，具备一定的理解和判断能力";
                comments[4][2] = "情况一般，阅读能力平常，能理解大部分语篇信息";
                comments[4][3] = "阅读能力差，对于阅读语篇的理解能力差";
                //写作
                comments[5][0] = "写作知识掌握扎实，语句流畅，具有较强的写作能力";
                comments[5][1] = "文章比较流畅，能基本表达出所要表达的内容";
                comments[5][2] = "能勉强表达所要表达的思想";
                comments[5][3] = "写作能力差，很难表达出自己的意思，错误很多";
                //综合
                comments[6][0] = "对于基础知识的综合运用能力较强，具有较强的推理和思维判断能力";
                comments[6][1] = "基本具有基础知识的综合运用，具备一定的理解和判断能力";
                comments[6][2] = "情况一般，能力平常，能理解大部分语篇信息。";
                comments[6][3] = "综合运用能力较差，对于语篇的理解能力较差";
                //语音
                comments[7][0] = "学生语音及读音规则掌握得十分扎实，能正确发音。";
                comments[7][1] = "学生掌握情况良好，大部分语音内容基本掌握。";
                comments[7][2] = "语音掌握情况一般，还需加强语音方面的练习。";
                comments[7][3] = "语音掌握情况不乐观，亟待补课。";
                //总评语
                comments[8][0] = "学生的综合能力强,熟练掌握了各项知识的内容";
                comments[8][1] = "学生的综合能力较好,基本掌握了各项知识的内容";
                comments[8][2] = "学生的综合能力一般,部分知识内容还有待于提高";
                comments[8][3] = "学生的综合能力很差,需要从基本知识内容学起";
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

                //studyRecordList = detectPlanDate(studyRecordList); //调用判断逾期
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
