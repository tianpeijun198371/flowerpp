/**
 * Copyright (c)2006 Beijing WenhuaOnline Sci-tech DevelopmentCo.,Ltd. 
 * All rights reserved. 
 *
 * User: zhangly
 * Date: 2007-10-23 15:22:29 
 */

package com.ulearning.ulms.tools.study.info.dao;

import com.ulearning.ulms.content.exceptions.ContentManageSysException;
import com.ulearning.ulms.core.exceptions.ULMSSysException;
import com.ulearning.ulms.core.util.StringUtil;
import com.ulearning.ulms.tools.study.info.exceptions.StudyInfoDAOSysException;
import com.ulearning.ulms.tools.study.info.form.StudyInfoForm;
import com.ulearning.ulms.tools.study.info.model.StudyInfoModel;
import com.ulearning.ulms.util.DBUtil;
import com.ulearning.ulms.util.HibernateDAO;
import com.ulearning.ulms.util.HibernateUtil;
import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Query;
import net.sf.hibernate.Session;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class StudyInfoDAOImpl implements StudyInfoDAO
{
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        public int addStudyInfo(StudyInfoForm details) throws StudyInfoDAOSysException
        {
                try
                {
                        Serializable StudyInfoID = HibernateDAO.add(details.getStudyInfoModel());

                        return Integer.parseInt(StudyInfoID.toString());
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new StudyInfoDAOSysException("" + e);
                }
        }


        public void updateStudyInfo(StudyInfoForm details) throws StudyInfoDAOSysException
        {
                try
                {
                        HibernateDAO.update(details.getStudyInfoModel());
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new StudyInfoDAOSysException("" + e);
                }

        }

        public void removeStudyInfo(String StudyInfoID) throws StudyInfoDAOSysException
        {
                String hql = " from StudyInfoModel where studyInfoID = " + StudyInfoID;

                try
                {
                        HibernateDAO.delete(hql);
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new StudyInfoDAOSysException("" + e);
                }
        }

        public StudyInfoForm getStudyInfo(int studyInfoID) throws StudyInfoDAOSysException
        {
                StudyInfoForm studyInfoForm;
                try
                {
                        StudyInfoModel studyInfoModel = (StudyInfoModel) HibernateDAO.load(StudyInfoModel.class, new Integer(studyInfoID));
                        studyInfoForm = new StudyInfoForm(studyInfoModel);
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new StudyInfoDAOSysException("" + e);
                }
                return studyInfoForm;
        }

        public List getInfoList(int StudyInfoID) throws StudyInfoDAOSysException
        {
                String hql = " from StudyInfoModel where type = 1 and StudyInfoID = " + StudyInfoID;

                List infoList = new ArrayList();
                try
                {
                        List tmList = HibernateDAO.find(hql);
                        StudyInfoModel tm = null;

                        if (tmList != null)
                        {
                                for (int i = 0; i < tmList.size(); i++)
                                {
                                        tm = (StudyInfoModel) tmList.get(i);
                                        StudyInfoForm pf = new StudyInfoForm(tm);
                                        infoList.add(pf);
                                }
                        }
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new StudyInfoDAOSysException("" + e);
                }
                return infoList;
        }

        public List getStudyInfoList(int orgID) throws StudyInfoDAOSysException
        {
                String hql = "";
                if (orgID == 0)
                {
                        hql = " from StudyInfoModel where type = 1 ";
                }
                else
                {
                        hql = "from StudyInfoModel where type = 1 and OrgID = " + orgID;
                }
                List studyInfoList = new ArrayList();
                System.out.println("hql = " + hql);
                try
                {
                        List tmList = HibernateDAO.find(hql);
                        StudyInfoModel tm = null;

                        if (tmList != null)
                        {
                                for (int i = 0; i < tmList.size(); i++)
                                {
                                        tm = (StudyInfoModel) tmList.get(i);
                                        StudyInfoForm pf = new StudyInfoForm(tm);
                                        studyInfoList.add(pf);
                                }
                        }
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new StudyInfoDAOSysException("" + e);
                }
                return studyInfoList;
        }

        public List getStudyRecordList(int courseID) throws StudyInfoDAOSysException
        {
                String hql = " from StudyInfoModel where courseID = " + courseID;

                List studyRecordList = new ArrayList();
                try
                {
                        List tmList = HibernateDAO.find(hql);
                        StudyInfoModel tm = null;

                        if (tmList != null)
                        {
                                for (int i = 0; i < tmList.size(); i++)
                                {
                                        tm = (StudyInfoModel) tmList.get(i);
                                        StudyInfoForm pf = new StudyInfoForm(tm);
                                        studyRecordList.add(pf);
                                }
                        }
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new StudyInfoDAOSysException("" + e);
                }
                return studyRecordList;
        }

        public List getStudyRecordList(int courseID, int userID) throws StudyInfoDAOSysException
        {
                String hql = " from StudyInfoModel where type = 2 and courseID = " + courseID +
                        " and userID = " + userID;

                List studyRecordList = new ArrayList();
                try
                {
                        List tmList = HibernateDAO.find(hql);
                        StudyInfoModel tm = null;

                        if (tmList != null)
                        {
                                for (int i = 0; i < tmList.size(); i++)
                                {
                                        tm = (StudyInfoModel) tmList.get(i);
                                        StudyInfoForm pf = new StudyInfoForm(tm);
                                        studyRecordList.add(pf);
                                }
                        }
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new StudyInfoDAOSysException("" + e);
                }
                return studyRecordList;
        }

        public List searchStudyInfoList(StudyInfoForm studyInfo, int orgID) throws StudyInfoDAOSysException
        {
                String hql = "";
                if (orgID == 0)
                {
                        if (studyInfo.getExamType() == 0)
                        {

                                hql = "from StudyInfoModel where type = 1 and name like '%" +
                                        studyInfo.getName() + "%' and school like '%" + studyInfo.getSchool() + "%'";
                        }
                        else
                        {
                                hql = " from StudyInfoModel where type = 1 and examType=" + studyInfo.getExamType() + " and name like '%" +
                                        studyInfo.getName() + "%' " + " and school like '%" + studyInfo.getSchool() + "%'";
                        }
                }
                else
                {

                        if (studyInfo.getExamType() == 0)
                        {
                                hql = "from StudyInfoModel where orgID =" + studyInfo.getOrgID() + " and name like '%" +
                                        studyInfo.getName() + "%' and school like '%" + studyInfo.getSchool() + "%'";
                        }
                        else
                        {
                                hql = " from StudyInfoModel where orgID =" + studyInfo.getOrgID() + " and examType=" + studyInfo.getExamType() + " and name like '%" +
                                        studyInfo.getName() + "%' " + " and school like '%" + studyInfo.getSchool() + "%'";
                        }
                }
                List studyRecordList = new ArrayList();
                try
                {
                        List tmList = HibernateDAO.find(hql);
                        StudyInfoModel tm = null;

                        if (tmList != null)
                        {
                                for (int i = 0; i < tmList.size(); i++)
                                {
                                        tm = (StudyInfoModel) tmList.get(i);
                                        StudyInfoForm pf = new StudyInfoForm(tm);
                                        studyRecordList.add(pf);
                                }
                        }
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new StudyInfoDAOSysException("" + e);
                }
                return studyRecordList;
        }

        public List getAllUsers(int orgID) throws StudyInfoDAOSysException
        {
                String hql = " select count(name) from StudyInfoModel where type = 1 and orgID = " + orgID;
                List studyInfoList = new ArrayList();
                try
                {
                        List tmList = HibernateDAO.find(hql);
                        StudyInfoModel tm = null;

                        if (tmList != null)
                        {
                                for (int i = 0; i < tmList.size(); i++)
                                {
                                        tm = (StudyInfoModel) tmList.get(i);
                                        StudyInfoForm pf = new StudyInfoForm(tm);
                                        studyInfoList.add(pf);
                                }
                        }
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new StudyInfoDAOSysException("" + e);
                }
                return studyInfoList;
        }


        public List getAllSchools(int orgID) throws StudyInfoDAOSysException
        {
                List studyInfoList = new ArrayList();
                String sqlStr = "select distinct school from T_StudyInfo_Tab where type = 1 and orgID = " + orgID;

                try
                {
                        conn = getConnection();
                        stmt = conn.createStatement();
                        rs = stmt.executeQuery(sqlStr);
                        while (rs.next())
                        {
                                StudyInfoForm pf = new StudyInfoForm();
                                pf.setSchool(StringUtil.nullToStr(rs.getString("school")));
                                studyInfoList.add(pf);
                        }
                }
                catch (SQLException se)
                {
                        se.printStackTrace();
                        throw new StudyInfoDAOSysException("" + se);
                }
                finally
                {
                        closeResultSet(rs);
                        closeStatement(stmt);
                        closeConnection();
                }
                return studyInfoList;
        }

        protected void closeConnection() throws StudyInfoDAOSysException
        {
                try
                {
                        if ((conn != null) && !conn.isClosed())
                        {
                                conn.close();
                        }
                }
                catch (SQLException se)
                {
                        throw new StudyInfoDAOSysException("" + se);
                }
        }

        protected void closeStatement(Statement stmt) throws StudyInfoDAOSysException
        {
                try
                {
                        if (stmt != null)
                        {
                                stmt.close();
                        }
                }
                catch (SQLException se)
                {
                        throw new StudyInfoDAOSysException("SQL Exception while closing " +
                                "Statement : \n" + se);
                }
        }

        protected Connection getConnection() throws StudyInfoDAOSysException
        {
                try
                {
                        conn = DBUtil.getConnection();
                }
                catch (Exception se)
                {
                        throw new StudyInfoDAOSysException("SQL Exception while getting " +
                                "DB connection : \n" + se);
                }

                return conn;
        }

        protected void closeResultSet(ResultSet result)
                throws StudyInfoDAOSysException
        {
                try
                {
                        if (result != null)
                        {
                                result.close();
                        }
                }
                catch (SQLException se)
                {
                        throw new StudyInfoDAOSysException("SQL Exception while closing " +
                                "Result Set : \n" + se);
                }
        }

        public List getAllSchools(int orgID, int examType) throws StudyInfoDAOSysException
        {

                List studyInfoList = new ArrayList();
                Statement stmt = null;
                ResultSet rs = null;
                String sqlStr = "select distinct school from T_StudyInfo_Tab where type = 1 and orgID = " + orgID + "and examType = " + examType;

                try
                {
                        conn = getConnection();
                        stmt = conn.createStatement();
                        rs = stmt.executeQuery(sqlStr);
                        while (rs.next())
                        {
                                StudyInfoForm pf = new StudyInfoForm();
                                pf.setSchool(StringUtil.nullToStr(rs.getString("school")));
                                studyInfoList.add(pf);
                        }
                }
                catch (SQLException se)
                {
                        se.printStackTrace();
                        throw new StudyInfoDAOSysException("" + se);
                }
                finally
                {
                        closeResultSet(rs);
                        closeStatement(stmt);
                        closeConnection();
                }
                return studyInfoList;
        }

        public String[][] getScoresBySchool(List getAllSchools, List getStudyInfoList) throws StudyInfoDAOSysException
        {
                String[][] info = new String[getAllSchools.size()][2];
                int[][] scoreInfo = new int[getAllSchools.size()][2];
                for (int i = 0; i < getAllSchools.size(); i++)
                {
                        StudyInfoForm schools = (StudyInfoForm) getAllSchools.get(i);
                        info[i][0] = schools.getSchool();
                        scoreInfo[i][1] = 0;
                        for (int j = 0; j < getStudyInfoList.size(); j++)
                        {
                                StudyInfoForm infoList = (StudyInfoForm) getStudyInfoList.get(j);
                                if (infoList.getSchool().equalsIgnoreCase(info[i][0]))
                                {
                                        //学校的总成绩
                                        scoreInfo[i][0] += infoList.getZongChengJiScore();
                                        scoreInfo[i][1] = scoreInfo[i][1] + 1;
                                }

                        }
                        //info[i][1] = scoreInfo[i] + "";
                        float totalScore = (new Float(scoreInfo[i][0])).floatValue();
                        float totalStu = (new Float(scoreInfo[i][1])).floatValue();
                        info[i][1] = Float.toString(totalScore / totalStu);
                }
                return info;
        }


        public String[] getScoresByType(List getAllStudentsBySchool) throws StudyInfoDAOSysException
        {
                if (getAllStudentsBySchool == null)
                {
                        return null;
                }
                int[] scoreInfo = new int[8];
                String[] result = new String[8];
                int size = 0;
                for (int i = 0; i < getAllStudentsBySchool.size(); i++)
                {
                        StudyInfoForm studyInfo = (StudyInfoForm) getAllStudentsBySchool.get(i);
                        scoreInfo[0] += studyInfo.getTingLiScore();
                        scoreInfo[1] += studyInfo.getCiHuiScore();
                        scoreInfo[2] += studyInfo.getYueDuScore();
                        scoreInfo[3] += studyInfo.getYuFaScore();
                        scoreInfo[4] += studyInfo.getYuYinScore();
                        scoreInfo[5] += studyInfo.getHuiHuaScore();
                        scoreInfo[6] += studyInfo.getXieZuoScore();
                        scoreInfo[7] += studyInfo.getZongHeScore();
                }
                size = getAllStudentsBySchool.size();
                for (int i = 0; i < scoreInfo.length; i++)
                {
                        float tingLiScore = (new Float(scoreInfo[0])).floatValue();
                        float ciHuiScore = (new Float(scoreInfo[1])).floatValue();
                        float yueDuScore = (new Float(scoreInfo[2])).floatValue();
                        float yuFaScore = (new Float(scoreInfo[3])).floatValue();
                        float yuYinScore = (new Float(scoreInfo[4])).floatValue();
                        float huiHuaScore = (new Float(scoreInfo[5])).floatValue();
                        float xieZuoScore = (new Float(scoreInfo[6])).floatValue();
                        float zongHeScore = (new Float(scoreInfo[7])).floatValue();

                        result[0] = Float.toString(tingLiScore / size);
                        result[1] = Float.toString(ciHuiScore / size);
                        result[2] = Float.toString(yueDuScore / size);
                        result[3] = Float.toString(yuFaScore / size);
                        result[4] = Float.toString(yuYinScore / size);
                        result[5] = Float.toString(huiHuaScore / size);
                        result[6] = Float.toString(xieZuoScore / size);
                        result[7] = Float.toString(zongHeScore / size);

                }
                return result;
        }

        public int getMaxNum(String school, int examType) throws StudyInfoDAOSysException
        {
                int zongChengJiScore = 0;
                Statement stmt = null;
                ResultSet rs = null;
                String sql = "select max(zongChengJiScore) from T_STUDYINFO_TAB where type = 1 and school = '" + school + "'and examType =" + examType;
                try
                {
                        conn = getConnection();
                        stmt = conn.createStatement();
                        rs = stmt.executeQuery(sql);
                        while (rs.next())
                        {
                                zongChengJiScore = rs.getInt(1);
                        }
                }
                catch (SQLException se)
                {
                        se.printStackTrace();
                }
                finally
                {
                        closeResultSet(rs);
                        closeStatement(stmt);
                        closeConnection();
                }
                return zongChengJiScore;
        }

        public int getMinNum(String school, int examType) throws StudyInfoDAOSysException
        {
                int zongChengJiScore = 0;
                Statement stmt = null;
                ResultSet rs = null;
                String sql = "select min(zongChengJiScore) from T_STUDYINFO_TAB where type = 1 and school = '" + school + "'and examType =" + examType;
                try
                {
                        conn = getConnection();
                        stmt = conn.createStatement();
                        rs = stmt.executeQuery(sql);
                        while (rs.next())
                        {
                                zongChengJiScore = rs.getInt(1);
                        }
                }
                catch (SQLException se)
                {
                        se.printStackTrace();
                }
                finally
                {
                        closeResultSet(rs);
                        closeStatement(stmt);
                        closeConnection();
                }
                return zongChengJiScore;
        }

        //示范校的平均分
        public int allScores(int orgID, int examType) throws StudyInfoDAOSysException
        {
                int allScores = 0;
                Statement stmt = null;
                ResultSet rs = null;
                String sql = "select sum(zongChengJiScore) from T_STUDYINFO_TAB where type = 1 and orgID = " + orgID + " and examType =" + examType;
                try
                {
                        conn = getConnection();
                        stmt = conn.createStatement();
                        rs = stmt.executeQuery(sql);
                        while (rs.next())
                        {
                                allScores = rs.getInt(1);
                        }
                }
                catch (SQLException se)
                {
                        se.printStackTrace();
                }
                finally
                {
                        closeResultSet(rs);
                        closeStatement(stmt);
                        closeConnection();
                }
                return allScores;
        }

        //示范校的平均分
        public int getAverageScore(int allUser, int allScores) throws StudyInfoDAOSysException
        {
                int averScore = 0;

                averScore = allScores / allUser;

                return averScore;

        }

        public int getMaxSchNum(int orgID, int examType) throws StudyInfoDAOSysException
        {
                int zongChengJiScore = 0;
                Statement stmt = null;
                ResultSet rs = null;
                String sql = "select max(zongChengJiScore) from T_STUDYINFO_TAB where type = 1 and orgID = " + orgID + " and examType =" + examType;
                try
                {
                        conn = getConnection();
                        stmt = conn.createStatement();
                        rs = stmt.executeQuery(sql);
                        while (rs.next())
                        {
                                zongChengJiScore = rs.getInt(1);
                        }
                }
                catch (SQLException se)
                {
                        se.printStackTrace();
                }
                finally
                {
                        closeResultSet(rs);
                        closeStatement(stmt);
                        closeConnection();
                }
                return zongChengJiScore;
        }

        public int getMinSchNum(int orgID, int examType) throws StudyInfoDAOSysException
        {
                int zongChengJiScore = 0;
                Statement stmt = null;
                ResultSet rs = null;
                String sql = "select min(zongChengJiScore) from T_STUDYINFO_TAB where type = 1 and orgID = " + orgID + " and examType =" + examType;
                try
                {
                        conn = getConnection();
                        stmt = conn.createStatement();
                        rs = stmt.executeQuery(sql);
                        while (rs.next())
                        {
                                zongChengJiScore = rs.getInt(1);
                        }
                }
                catch (SQLException se)
                {
                        se.printStackTrace();
                }
                finally
                {
                        closeResultSet(rs);
                        closeStatement(stmt);
                        closeConnection();
                }
                return zongChengJiScore;
        }

        public List getAllStudents(String school, int examType) throws StudyInfoDAOSysException
        {
                List studyInfoList = new ArrayList();
                String sqlStr = "select name from T_STUDYINFO_TAB where type = 1 and school = '" + school + "' and examType = " + examType;
                try
                {
                        conn = getConnection();
                        stmt = conn.createStatement();
                        rs = stmt.executeQuery(sqlStr);
                        while (rs.next())
                        {
                                StudyInfoForm pf = new StudyInfoForm();
                                pf.setName(StringUtil.nullToStr(rs.getString("name")));
                                studyInfoList.add(pf);
                        }
                }
                catch (SQLException se)
                {
                        se.printStackTrace();
                        throw new StudyInfoDAOSysException("" + se);
                }
                finally
                {
                        closeResultSet(rs);
                        closeStatement(stmt);
                        closeConnection();
                }
                return studyInfoList;
        }

        //学校中的学生人数
        public int allStudents(String school, int examType, int orgID) throws StudyInfoDAOSysException
        {
                int allStuNum = 0;
                Statement stmt = null;
                ResultSet rs = null;
                String sql = "select count(name) from T_STUDYINFO_TAB where type = 1 and school = '" + school + "'and examType =" + examType + " and orgID = " + orgID;
                try
                {
                        conn = getConnection();
                        stmt = conn.createStatement();
                        rs = stmt.executeQuery(sql);
                        while (rs.next())
                        {
                                allStuNum = rs.getInt(1);
                        }
                }
                catch (SQLException se)
                {
                        se.printStackTrace();
                }
                finally
                {
                        closeResultSet(rs);
                        closeStatement(stmt);
                        closeConnection();
                }
                return allStuNum;
        }

        //示范校参加不同测试的人数
        public int allUser(int orgID, int examType) throws StudyInfoDAOSysException
        {
                int alluser = 0;
                Statement stmt = null;
                ResultSet rs = null;
                String sql = "select count(name) from T_STUDYINFO_TAB where type = 1 and orgID = " + orgID + " and examType =" + examType;
                try
                {
                        conn = getConnection();
                        stmt = conn.createStatement();
                        rs = stmt.executeQuery(sql);
                        while (rs.next())
                        {
                                alluser = rs.getInt(1);
                        }
                }
                catch (SQLException se)
                {
                        se.printStackTrace();
                }
                finally
                {
                        closeResultSet(rs);
                        closeStatement(stmt);
                        closeConnection();
                }
                return alluser;
        }

        public List getAllStudentsBySchool(String school, int examType, int orgID, int type) throws StudyInfoDAOSysException
        {
                List studyInfoList = new ArrayList();
                String sqlStr = "select * from T_STUDYINFO_TAB where type = " + type + " and orgID = " + orgID + " and school = '" + school + "' and examType = " + examType;
                try
                {
                        conn = getConnection();
                        stmt = conn.createStatement();
                        rs = stmt.executeQuery(sqlStr);
                        while (rs.next())
                        {
                                StudyInfoForm pf = convertRs2Form(rs);
                                studyInfoList.add(pf);
                        }
                }
                catch (SQLException se)
                {
                        se.printStackTrace();
                        throw new StudyInfoDAOSysException("" + se);
                }
                finally
                {
                        closeResultSet(rs);
                        closeStatement(stmt);
                        closeConnection();
                }
                return studyInfoList;
        }

        //取不同试卷的总成绩
        public int totalScore(int orgID, int examType) throws StudyInfoDAOSysException
        {
                int totalScore = 0;
                Statement stmt = null;
                ResultSet rs = null;
                String sql = "select zongChengJi from T_STUDYINFO_TAB where type = 1 and orgID = " + orgID + " and examType =" + examType;
                try
                {
                        conn = getConnection();
                        stmt = conn.createStatement();
                        rs = stmt.executeQuery(sql);
                        while (rs.next())
                        {
                                totalScore = rs.getInt(1);
                        }
                }
                catch (SQLException se)
                {
                        se.printStackTrace();
                }
                finally
                {
                        closeResultSet(rs);
                        closeStatement(stmt);
                        closeConnection();
                }
                return totalScore;
        }

        /*不同分数段的人数*/
        public int usersNumber(int orgID, int examType, String school,double max,double min) throws StudyInfoDAOSysException
        {
                int userGood = 0;
                Statement stmt = null;
                ResultSet rs = null;
                String sql = "";
                if((!school.equals("")) && (school.trim().length() > 0)){
                 sql = "select count(name) from T_STUDYINFO_TAB where type = 1 and orgID = "+orgID+" and examType = " + examType + "and school = '"+school+"' and zongChengJiScore >= zongChengJi * "+ min +" and zongChengJiScore <= zongChengJi *" + max;
                }
                 sql = "select count(name) from T_STUDYINFO_TAB where type = 1 and orgID = "+orgID+" and examType = " + examType + " and zongChengJiScore >= zongChengJi * " + min +" and zongChengJiScore <= zongChengJi *" + max;
                System.out.println("sql = " + sql);
                try
                {
                        conn = getConnection();
                        stmt = conn.createStatement();
                        rs = stmt.executeQuery(sql);
                        while (rs.next())
                        {
                                userGood = rs.getInt(1);
                        }
                }
                catch (SQLException se)
                {
                        se.printStackTrace();
                }
                finally
                {
                        closeResultSet(rs);
                        closeStatement(stmt);
                        closeConnection();
                }
                return userGood;
        }

        /*不同分数段的人数*/
        public int usersNum(int orgID, int examType,String school,int max,int min) throws StudyInfoDAOSysException
        {
                int userNuma = 0;
                Statement stmt = null;
                ResultSet rs = null;
                String sql = "";
                if((!school.equals("")) && (school.trim().length() > 0)){
                 sql = "select count(name) from T_STUDYINFO_TAB where type = 1 and orgID = "+orgID+" and examType = " + examType + "and school = '"+school+"' and zongChengJiScore >= "+ min +" and zongChengJiScore <= " + max;
                }
                 sql = "select count(name) from T_STUDYINFO_TAB where type = 1 and orgID = "+orgID+" and examType = " + examType + " and zongChengJiScore >= "+ min +" and zongChengJiScore <= " + max;
                System.out.println("sql = " + sql);
                try
                {
                        conn = getConnection();
                        stmt = conn.createStatement();
                        rs = stmt.executeQuery(sql);
                        while (rs.next())
                        {
                                userNuma = rs.getInt(1);
                        }
                }
                catch (SQLException se)
                {
                        se.printStackTrace();
                }
                finally
                {
                        closeResultSet(rs);
                        closeStatement(stmt);
                        closeConnection();
                }
                return userNuma;
        }

        private StudyInfoForm convertRs2Form(ResultSet rs)
        {
                StudyInfoForm sf = new StudyInfoForm();

                try
                {
                        sf.setStudyInfoID(rs.getInt("StudyInfoID"));
                        sf.setOrgID(rs.getInt("OrgID"));
                        sf.setCourseID(rs.getInt("CourseID"));
                        sf.setUserID(rs.getInt("UserID"));
                        sf.setType(rs.getInt("Type"));
                        sf.setExamType(rs.getInt("ExamType"));
                        sf.setTestType(rs.getInt("TestType"));
                        sf.setYear(StringUtil.nullToStr(rs.getString("Year")));
                        sf.setGrade(rs.getInt("Grade"));
                        sf.setSubject(StringUtil.nullToStr(rs.getString("Subject")));
                        sf.setName(StringUtil.nullToStr(rs.getString("Name")));
                        sf.setSchool(StringUtil.nullToStr(rs.getString("School")));
                        sf.setBook(StringUtil.nullToStr(rs.getString("Book")));
                        sf.setMail(StringUtil.nullToStr(rs.getString("Mail")));
                        sf.setCard(StringUtil.nullToStr(rs.getString("Card")));
                        sf.setSex(StringUtil.nullToStr(rs.getString("Sex")));
                        sf.setPhone(StringUtil.nullToStr(rs.getString("Phone")));
                        sf.setCellphone(StringUtil.nullToStr(rs.getString("Cellphone")));
                        sf.setAddress(StringUtil.nullToStr(rs.getString("Address")));
                        sf.setPostalcode(StringUtil.nullToStr(rs.getString("Postalcode")));

                        sf.setTingLi(rs.getInt("TingLi"));
                        sf.setTingLiScore(rs.getInt("TingLiScore"));
                        sf.setTingLiPingYu(StringUtil.nullToStr(rs.getString("TingLiPingYu")));

                        sf.setYuYin(rs.getInt("YuYin"));
                        sf.setYuYinScore(rs.getInt("YuYinScore"));
                        sf.setYuYinPingYu(StringUtil.nullToStr(rs.getString("YuYinPingYu")));

                        sf.setCiHui(rs.getInt("CiHui"));
                        sf.setCiHuiScore(rs.getInt("CiHuiScore"));
                        sf.setCiHuiPingYu(StringUtil.nullToStr(rs.getString("CiHuiPingYu")));

                        sf.setHuiHua(rs.getInt("HuiHua"));
                        sf.setHuiHuaScore(rs.getInt("HuiHuaScore"));
                        sf.setHuiHuaPingYu(StringUtil.nullToStr(rs.getString("HuiHuaPingYu")));

                        sf.setYuFa(rs.getInt("YuFa"));
                        sf.setYuFaScore(rs.getInt("YuFaScore"));
                        sf.setYuFaPingYu(StringUtil.nullToStr(rs.getString("YuFaPingYu")));

                        sf.setYueDu(rs.getInt("YueDu"));
                        sf.setYueDuScore(rs.getInt("YueDuScore"));
                        sf.setYueDuPingYu(StringUtil.nullToStr(rs.getString("YueDuPingYu")));

                        sf.setXieZuo(rs.getInt("XieZuo"));
                        sf.setXieZuoScore(rs.getInt("XieZuoScore"));
                        sf.setXieZuoPingYu(StringUtil.nullToStr(rs.getString("XieZuoPingYu")));

                        sf.setZongHe(rs.getInt("ZongHe"));
                        sf.setZongHeScore(rs.getInt("ZongHeScore"));
                        sf.setZongHePingYu(StringUtil.nullToStr(rs.getString("ZongHePingYu")));

                        sf.setZongChengJi(rs.getInt("ZongChengJi"));
                        sf.setZongChengJiScore(rs.getInt("ZongChengJiScore"));
                        sf.setZongPingYu(StringUtil.nullToStr(rs.getString("ZongPingYu")));

                        sf.setLuRuRen(StringUtil.nullToStr(rs.getString("LuRuRen")));
                        sf.setLuRuDate(StringUtil.nullToStr(rs.getString("LuRuDate")));
                        sf.setCreateDate(rs.getDate("CreateDate"));
                        sf.setZhengShu(rs.getInt("ZhengShu"));
                        sf.setZhengShuNeiRong(StringUtil.nullToStr(rs.getString("ZhengShuNeiRong")));
                        sf.setDescription(StringUtil.nullToStr(rs.getString("Description")));
                        sf.setRemark1(StringUtil.nullToStr(rs.getString("Remark1")));
                        sf.setRemark2(StringUtil.nullToStr(rs.getString("Remark2")));
                        sf.setRemark3(StringUtil.nullToStr(rs.getString("Remark3")));
                        sf.setRemark4(StringUtil.nullToStr(rs.getString("Remark4")));
                        sf.setRemark5(StringUtil.nullToStr(rs.getString("Remark5")));
                        sf.setRemark6(StringUtil.nullToStr(rs.getString("Remark6")));
                        sf.setRemark7(StringUtil.nullToStr(rs.getString("Remark7")));
                        sf.setRemark8(StringUtil.nullToStr(rs.getString("Remark8")));

                }
                catch (SQLException sql)
                {
                        sql.printStackTrace();
                }

                return sf;
        }
        /*       public int getStudyInfoIDByPostalcode(String postalcode) throws StudyInfoDAOSysException
       {
               int orgID = -1;
               List tmList = null;
               String hql = " from StudyInfoModel  where postalcode = '" + postalcode + "'";

               try
               {
                       tmList = HibernateDAO.find(hql);
               }
               catch (ULMSSysException e)
               {
                       e.printStackTrace();
                       throw new StudyInfoDAOSysException("" + e);
               }

               if ((tmList != null) && (tmList.size() > 0))
               {
               }
               else
               {
                       orgID = getStudyInfoIDByPostalcode(postalcode);
               }

               return orgID;
       } */
}
