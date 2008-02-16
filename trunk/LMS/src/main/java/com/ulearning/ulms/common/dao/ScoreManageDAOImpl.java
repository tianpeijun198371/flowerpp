/**
 * ScoreManageDAOImpl.java.
 * User: fengch  Date: 2004-5-26
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.common.dao;

import com.ulearning.ulms.common.exceptions.ScoreSysException;
import com.ulearning.ulms.common.model.ScoreListModel;
import com.ulearning.ulms.common.model.ScoreModel;
import com.ulearning.ulms.core.exceptions.ULMSSysException;
import com.ulearning.ulms.core.util.DateTimeUtil;
import com.ulearning.ulms.course.helper.CourseHelper;
import com.ulearning.ulms.course.helper.CourseUserHelper;
import com.ulearning.ulms.course.util.CourseKeys;
import com.ulearning.ulms.util.DBUtil;
import com.ulearning.ulms.util.HibernateDAO;
import com.ulearning.ulms.util.log.LogUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.List;


public class ScoreManageDAOImpl implements ScoreManageDAO
{
        /**
         * get  the relationed scores.
         * type
         * ==0:课程成绩
         * ==1:证书成绩
         * ==2:项目成绩
         *
         * @throws com.ulearning.ulms.common.exceptions.ScoreSysException
         *
         */
        public ScoreListModel get(int relationID, int type, int pageNo, int pageSize)
                throws ScoreSysException
        {
                return null;
        }

        /**
         * get  the user-relationed score
         * type
         * ==0:课程成绩
         * ==1:证书成绩
         * ==2:项目成绩
         *
         * @throws com.ulearning.ulms.common.exceptions.ScoreSysException
         *
         */
        public ScoreModel get(int userID, int relationID, int type)
                throws ScoreSysException
        {
                Connection conn = null;
                Statement stmt = null;
                ResultSet rs = null;
                ScoreModel sm = null;

                try
                {
                        conn = DBUtil.getConnection();
                        stmt = conn.createStatement();

                        String queryStr = "select ScoreID,UserID,RelationID,Type,Score," +
                                "ScoreType,Credit,IsPass,ExamType,CreateDate,ModifyDate from TM_Score_Tab" +
                                " Where type=" + type + " and userID=" + userID;
                        queryStr += (" and RelationID=" + relationID);
                        rs = stmt.executeQuery(queryStr);

                        if (rs.next())
                        {
                                sm = new ScoreModel(rs.getInt("ScoreID"), rs.getInt("UserID"),
                                        rs.getInt("RelationID"), rs.getInt("Type"),
                                        rs.getString("Score"), rs.getInt("ScoreType"),
                                        rs.getInt("Credit"), rs.getInt("IsPass"),
                                        rs.getInt("ExamType"),
                                        DateTimeUtil.toDate(rs.getDate("CreateDate"),
                                                rs.getTime("CreateDate")),
                                        DateTimeUtil.toDate(rs.getDate("ModifyDate"),
                                                rs.getTime("ModifyDate")));
                        }
                }
                catch (SQLException se)
                {
                        se.printStackTrace();
                        throw new ScoreSysException(se);
                }
                finally
                {
                        DBUtil.closeResultSet(rs);
                        DBUtil.closeStatement(stmt);
                        DBUtil.closeConnection(conn);
                }

                return sm;
        }

        /**
         * 判断培训班下所有通过课程的学生
         *
         * @param userID   用户
         * @param courseID 培训班下的课程
         * @return
         * @throws ScoreSysException
         */
        public List getPassCourse(int userID, List courseID)
                throws ScoreSysException
        {
                Connection conn = null;
                Statement stmt = null;
                ResultSet rs = null;
                ScoreModel sm = null;
                List scormList = new ArrayList();

                try
                {
                        conn = DBUtil.getConnection();
                        stmt = conn.createStatement();

                        String queryStr = "select ScoreID,UserID,RelationID,Type," +
                                "Score,ScoreType,Credit,IsPass,ExamType,CreateDate,ModifyDate " +
                                " from TM_Score_Tab" + " Where isPass='1'" + " and userID=" +
                                userID;

                        // queryStr += " and RelationID=" + relationID;
                        if (courseID.size() > 0)
                        {
                                queryStr += (" and ( RelationID=" + courseID.get(0) + " ");

                                for (int i = 1; i < courseID.size(); i++)
                                {
                                        queryStr += (" or RelationID = " + courseID.get(i) + " ");
                                }

                                queryStr += " ) ";
                        }

                        LogUtil.debug("common",
                                "[ScorManageDAOImpl] ==========queryStr = " + queryStr);
                        System.out.println("queryStr = " + queryStr);
                        rs = stmt.executeQuery(queryStr);

                        while (rs.next())
                        {
                                sm = new ScoreModel(rs.getInt("ScoreID"), rs.getInt("UserID"),
                                        rs.getInt("RelationID"), rs.getInt("Type"),
                                        rs.getString("Score"), rs.getInt("ScoreType"),
                                        rs.getInt("Credit"), rs.getInt("IsPass"),
                                        rs.getInt("ExamType"),
                                        DateTimeUtil.toDate(rs.getDate("CreateDate"),
                                                rs.getTime("CreateDate")),
                                        DateTimeUtil.toDate(rs.getDate("ModifyDate"),
                                                rs.getTime("ModifyDate")));
                                scormList.add(sm);
                        }
                }
                catch (SQLException se)
                {
                        se.printStackTrace();
                        throw new ScoreSysException(se);
                }
                finally
                {
                        DBUtil.closeResultSet(rs);
                        DBUtil.closeStatement(stmt);
                        DBUtil.closeConnection(conn);
                }

                return scormList;
        }

        public List getCourseScore(int relationID, int isPass, int type)
                throws ScoreSysException
        {
                Connection conn = null;
                Statement stmt = null;
                ResultSet rs = null;
                ScoreModel sm = null;
                ArrayList scormList = new ArrayList();

                try
                {
                        conn = DBUtil.getConnection();
                        stmt = conn.createStatement();

                        String queryStr = "select ScoreID,UserID,RelationID,Type," +
                                "Score,ScoreType,Credit,IsPass,ExamType,CreateDate,ModifyDate " +
                                " from TM_Score_Tab" + " Where type = " + type +
                                " and relationID=" + relationID;

                        if (isPass != -1)
                        {
                                queryStr += (" and IsPass= '" + isPass + "'");
                        }

                        rs = stmt.executeQuery(queryStr);

                        while (rs.next())
                        {
                                sm = new ScoreModel(rs.getInt("ScoreID"), rs.getInt("UserID"),
                                        rs.getInt("RelationID"), rs.getInt("Type"),
                                        rs.getString("Score"), rs.getInt("ScoreType"),
                                        rs.getInt("Credit"), rs.getInt("IsPass"),
                                        rs.getInt("ExamType"),
                                        DateTimeUtil.toDate(rs.getDate("CreateDate"),
                                                rs.getTime("CreateDate")),
                                        DateTimeUtil.toDate(rs.getDate("ModifyDate"),
                                                rs.getTime("ModifyDate")));
                                scormList.add(sm);
                        }
                }
                catch (SQLException se)
                {
                        se.printStackTrace();
                        throw new ScoreSysException(se);
                }
                finally
                {
                        DBUtil.closeResultSet(rs);
                        DBUtil.closeStatement(stmt);
                        DBUtil.closeConnection(conn);
                }

                return scormList;
        }

        /**
         * get  a score
         *
         * @throws com.ulearning.ulms.common.exceptions.ScoreSysException
         *
         */
        public ScoreModel get(int scoreID) throws ScoreSysException
        {
                Connection conn = null;
                Statement stmt = null;
                ResultSet rs = null;
                ScoreModel sm = null;

                try
                {
                        conn = DBUtil.getConnection();
                        stmt = conn.createStatement();

                        String queryStr = "select ScoreID,UserID,RelationID," +
                                "Type,Score," +
                                "ScoreType,Credit,IsPass,ExamType from TM_Score_Tab" +
                                " WHERE ScoreID=" + scoreID;
                        LogUtil.debug("common",
                                "[ScorManageDAOImpl] ==========queryStr = " + queryStr);

                        rs = stmt.executeQuery(queryStr);

                        if (rs.next())
                        {
                                rs.getInt("ScoreID");
                                rs.getInt("UserID");
                                rs.getInt("RelationID");
                                rs.getInt("Type");
                                rs.getString("Score");
                                rs.getInt("ScoreType");
                                rs.getFloat("Credit");
                                rs.getInt("IsPass");
                                rs.getString("ExamType");

                                sm = new ScoreModel(rs.getInt("ScoreID"), rs.getInt("UserID"),
                                        rs.getInt("RelationID"), rs.getInt("Type"),
                                        rs.getString("Score"), rs.getInt("ScoreType"),
                                        rs.getFloat("Credit"), rs.getInt("IsPass"),
                                        rs.getInt("ExamType"));
                        }
                }
                catch (SQLException se)
                {
                        se.printStackTrace();
                        throw new ScoreSysException(se);
                }
                finally
                {
                        DBUtil.closeResultSet(rs);
                        DBUtil.closeStatement(stmt);
                        DBUtil.closeConnection(conn);
                }

                return sm;
        }

        /**
         * update a a score.
         *
         * @throws com.ulearning.ulms.common.exceptions.ScoreSysException
         *
         */
        public void update(ScoreModel sm) throws ScoreSysException
        {
                LogUtil.debug("common", "[ScoreManageDAOImpl]update ==========start!");

                try
                {
                        LogUtil.info("common",
                                "[ScoreManageDAOImpl]update One score.scoreID=" +
                                        sm.getScoreID());
                        HibernateDAO.update(sm.getScoreHibernateModel());
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new ScoreSysException(e);
                }
        }

        /**
         * get a score.
         *
         * @throws com.ulearning.ulms.common.exceptions.ScoreSysException
         *
         */
        public void insert(ScoreModel sm) throws ScoreSysException
        {
                try
                {
                        HibernateDAO.add(sm.getScoreHibernateModel());
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new ScoreSysException(e);
                }
        }

        /**
         * delete a score
         *
         * @throws ScoreSysException
         */
        public void delete(int scoreID) throws ScoreSysException
        {
        }

        /**
         * delete  scores by userID.
         *
         * @throws ScoreSysException
         */
        public void deleteByUserID(int userID) throws ScoreSysException
        {
                String sql_str;
                int CountDeleted;
                Connection conn = null;
                Statement stmt = null;

                sql_str = "delete from TM_Score_Tab" + " Where userID=" + userID;

                LogUtil.debug("course", "[ScorManageDAOImpl] sql_str=" + sql_str);

                try
                {
                        conn = DBUtil.getConnection();
                        stmt = conn.createStatement();

                        CountDeleted = stmt.executeUpdate(sql_str);
                        LogUtil.debug("course",
                                "[ScorManageDAOImpl] " + "删除 " + CountDeleted + "行");
                }
                catch (SQLException se)
                {
                        se.printStackTrace();
                        throw new ScoreSysException(se);
                }
                finally
                {
                        DBUtil.closeStatement(stmt);
                        DBUtil.closeConnection(conn);
                }
        }

        /**
         * delete a score
         *
         * @throws com.ulearning.ulms.common.exceptions.ScoreSysException
         *
         */
        public void delete(int userID, int relationID, int type)
                throws ScoreSysException
        {
                String sql_str;
                int CountDeleted;
                Connection conn = null;
                Statement stmt = null;

                sql_str = "delete from TM_Score_Tab" + " Where type='" + type +
                        "' and userID=" + userID;

                sql_str += (" and relationID=" + relationID);

                LogUtil.debug("course", "[ScorManageDAOImpl] sql_str=" + sql_str);

                try
                {
                        conn = DBUtil.getConnection();
                        stmt = conn.createStatement();

                        CountDeleted = stmt.executeUpdate(sql_str);
                        LogUtil.debug("course",
                                "[ScorManageDAOImpl] " + "删除 " + CountDeleted + "行");
                }
                catch (SQLException se)
                {
                        se.printStackTrace();
                        throw new ScoreSysException(se);
                }
                finally
                {
                        DBUtil.closeStatement(stmt);
                        DBUtil.closeConnection(conn);
                }
        }

        /**
         * return the pass radio of the module.
         * <p>if all module user's number is 0,then reurn -1
         * <p>module type ,Please see SecurityContants.
         *
         * @throws com.ulearning.ulms.common.exceptions.ScoreSysException
         *
         */
        public float getPassRadio(int relationID, int type)
                throws ScoreSysException
        {
                Connection conn = null;
                Statement stmt = null;
                ResultSet rs = null;

                try
                {
                        conn = DBUtil.getConnection();
                        stmt = conn.createStatement();

                        String queryStrPass = "select count(DISTINCT C_User_Tab.UserID)" +
                                " from TM_Score_Tab,C_User_Tab " +
                                " Where C_User_Tab.relationID=TM_Score_Tab.relationID " +
                                " and C_User_Tab.type=TM_Score_Tab.type " +
                                " and C_User_Tab.userID=TM_Score_Tab.userID  " +
                                " and C_User_Tab.type=" + type + " and C_User_Tab.state<>'" +
                                CourseKeys.COURSE_USER_APPLY_STATE + "' and isPass='1'" +
                                " and C_User_Tab.RelationID=" + relationID;

                        int all = 0;

                        try
                        {
                                all = CourseUserHelper.getTotalNumberByCourseStudent(relationID,
                                        type);
                        }
                        catch (Exception ex)
                        {
                        }

                        int pass = 0;
                        float result = -1;

                        LogUtil.debug("common",
                                "[ScorManageDAOImpl]getPassRadio ==========All = " + all);
                        LogUtil.debug("common",
                                "[ScorManageDAOImpl]getPassRadio ==========queryStrPass = " +
                                        queryStrPass);

                        rs = stmt.executeQuery(queryStrPass);

                        if (rs.next())
                        {
                                pass = rs.getInt(1);
                        }

                        LogUtil.debug("common",
                                "[ScorManageDAOImpl]getPassRadio ==========Pass = " + pass);

                        if (all > 0)
                        {
                                result = ((float) pass / all);
                        }

                        if (all < pass)
                        {
                                result = 1;
                        }

                        LogUtil.debug("common",
                                "[ScorManageDAOImpl]getPassRadio ==========result = " + result);

                        return result;
                }
                catch (SQLException se)
                {
                        se.printStackTrace();
                        throw new ScoreSysException(se);
                }
                finally
                {
                        DBUtil.closeResultSet(rs);
                        DBUtil.closeStatement(stmt);
                        DBUtil.closeConnection(conn);
                }
        }

        public static void main(String[] args)
        {
                ScoreManageDAOImpl dd = new ScoreManageDAOImpl();
                List courseList = new ArrayList();
                courseList.add(new Integer(21));
                courseList.add(new Integer(41));
                courseList.add(new Integer(61));

                //String useriD[] = {"103","105"};
                List bb = dd.getPassCourse(103, courseList);

                for (int i = 0; i < bb.size(); i++)
                {
                        ScoreModel sm = (ScoreModel) bb.get(i);
                        System.out.println("sm.getRelationID() = " +
                                CourseHelper.getCourse(sm.getRelationID()).getName());
                        System.out.println("sm.getUserID() = " + sm.getUserID());
                        System.out.println("sm.getScore() = " + sm.getScore());
                }

                //System.out.println("bb = " + bb);
        }
}
