/** * CourseMyCourseDAOImpl.java.
 * User: fengch  Date: 2004-4-29
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.course.dao;

import com.ulearning.ulms.core.security.bean.SecurityConstants;
import com.ulearning.ulms.course.exceptions.CourseSysException;
import com.ulearning.ulms.course.model.CourseListModel;
import com.ulearning.ulms.course.model.CourseModel;
import com.ulearning.ulms.course.util.CourseKeys;
import com.ulearning.ulms.util.DBUtil;
import com.ulearning.ulms.util.log.LogUtil;
import com.ulearning.ulms.util.log.LogUtil;

import java.sql.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;


public class CourseMyCourseDAOImpl implements CourseMyCourseDAO
{
        public CourseListModel getMyAllCourses(int userID)
                throws CourseSysException
        {
                Statement stmt = null;
                Connection conn = null;
                ResultSet rs = null;

                CourseModel cm = null;
                ArrayList cml = new ArrayList();
                String sqlStr = "SELECT DISTINCT  LifeStartDate,LifeEndDate,LifeSort,CourseID,CourseName,CourseCode,CourseUser_Status,TeachMode,CourseEstablishDate,LogoPic,CatalogID  FROM C_MYCOURSE_VIEW " +
                        " WHERE CourseStatus='" + CourseKeys.COURSE_AVAILABLE_STATE +
                        "'  And  CourseUser_Status='" +
                        CourseKeys.COURSE_USER_AVAILABLE_STATE + "' And UserID=" + userID +
                        " ORDER BY CourseEstablishDate,CourseName ASC";

                try
                {
                        conn = DBUtil.getConnection();
                        stmt = conn.createStatement();
                        rs = stmt.executeQuery(sqlStr);

                        while (rs.next())
                        {
                                cm = new CourseModel();
                                cm.setCourseID(rs.getInt("CourseID"));
                                cm.setName(rs.getString("CourseName"));
                                cm.setCourseCode(rs.getString("CourseCode"));
                                cm.setCourseUserStatus(rs.getInt("CourseUser_Status"));
                                cm.setTeachMode(rs.getInt("TeachMode"));
                                cm.setLifeSort(rs.getInt("LifeSort"));
                                cm.setLifeStartDate(rs.getDate("LifeStartDate"));
                                cm.setLifeEndDate(rs.getDate("LifeEndDate"));
                                cm.setLogopic(rs.getString("LogoPic"));
                                cm.setCatalogID(rs.getInt("CatalogID"));
                                cml.add(cm);
                        }
                }
                catch (SQLException se)
                {
                        se.printStackTrace();
                        throw new CourseSysException(se);
                }
                finally
                {
                        closeResultSet(rs);
                        closeStatement(stmt);
                        closeConnection(conn);
                }

                CourseListModel clm = new CourseListModel();
                clm.setList(cml);

                return clm;
        }

        public CourseListModel getMyAllCourses_by(int userID)
                throws CourseSysException
        {
                Statement stmt = null;
                Connection conn = null;
                ResultSet rs = null;

                CourseModel cm = null;
                ArrayList cml = new ArrayList();
                String sqlStr = "SELECT   CourseID,Name CourseName,CourseCode,Status CourseUser_Status,TeachMode  FROM C_Course_tab " +
                        " WHERE Status='" + CourseKeys.COURSE_AVAILABLE_STATE +
                        "' ORDER BY Name  ASC";

                try
                {
                        conn = DBUtil.getConnection();
                        stmt = conn.createStatement();
                        LogUtil.debug("course",
                                "[CourseMyCourseDAOImpl]====================the sql string is : " +
                                        sqlStr);
                        rs = stmt.executeQuery(sqlStr);

                        while (rs.next())
                        {
                                cm = new CourseModel();
                                cm.setCourseID(rs.getInt("CourseID"));
                                cm.setName(rs.getString("CourseName"));
                                cm.setCourseCode(rs.getString("CourseCode"));
                                cm.setCourseUserStatus(rs.getInt("CourseUser_Status"));
                                cm.setTeachMode(rs.getInt("TeachMode"));
                                LogUtil.debug("course",
                                        "[CourseMyCourseDAOImpl]====================  #");
                                cml.add(cm);
                        }
                }
                catch (SQLException se)
                {
                        se.printStackTrace();
                        throw new CourseSysException(se);
                }
                finally
                {
                        closeResultSet(rs);
                        closeStatement(stmt);
                        closeConnection(conn);
                }

                CourseListModel clm = new CourseListModel();
                clm.setList(cml);

                return clm;
        }

        public CourseListModel getMyTeachingCourse(int userID)
                throws CourseSysException
        {
                return null;
        }

        public CourseListModel getMyEstablishCourse(int userID)
                throws CourseSysException
        {
                return null;
        }

        public CourseListModel getMyAdminCourse(int userID)
                throws CourseSysException
        {
                return null;
        }

        public CourseListModel getMyAdminOrTeachingCourse(int userID)
                throws CourseSysException
        {
                Statement stmt = null;
                Connection conn = null;
                ResultSet rs = null;

                CourseModel cm = null;
                ArrayList cml = new ArrayList();
                String sqlStr = "SELECT DISTINCT  CourseID,CourseName,CourseCode,CourseUser_Status FROM C_MYCOURSE_VIEW " +
                        " WHERE CourseStatus='" + CourseKeys.COURSE_AVAILABLE_STATE +
                        "' And (RoleID =" + SecurityConstants.ROLE_COURSE_ADMINISTRATOR +
                        " or RoleID =" + SecurityConstants.ROLE_COURSE_TEACHER +
                        ") AND USERID=" + userID + " ORDER BY CourseName ASC";

                try
                {
                        conn = DBUtil.getConnection();
                        stmt = conn.createStatement();
                        LogUtil.debug("course",
                                "[CourseMyCourseDAOImpl]====================the sql string is : " +
                                        sqlStr);
                        rs = stmt.executeQuery(sqlStr);

                        while (rs.next())
                        {
                                cm = new CourseModel();
                                cm.setCourseID(rs.getInt("CourseID"));
                                cm.setName(rs.getString("CourseName"));
                                cm.setCourseCode(rs.getString("CourseCode"));
                                cm.setCourseUserStatus(rs.getInt("CourseUser_Status"));

                                cml.add(cm);
                        }
                }
                catch (SQLException se)
                {
                        se.printStackTrace();
                        throw new CourseSysException(se);
                }
                finally
                {
                        closeResultSet(rs);
                        closeStatement(stmt);
                        closeConnection(conn);
                }

                CourseListModel clm = new CourseListModel();
                clm.setList(cml);

                return clm;
        }

        public CourseListModel getMyLearningCourse(int userID)
                throws CourseSysException
        {
                Statement stmt = null;
                Connection conn = null;
                ResultSet rs = null;

                CourseModel cm = null;
                ArrayList cml = new ArrayList();
                String sqlStr = "SELECT DISTINCT  CourseID,CourseName,CourseCode,CourseUser_Status FROM C_MYCOURSE_VIEW " +
                        " WHERE CourseUser_Status = '" +
                        CourseKeys.COURSE_USER_AVAILABLE_STATE + "' and CourseStatus = '" +
                        CourseKeys.COURSE_AVAILABLE_STATE + "' And RoleID =" +
                        SecurityConstants.ROLE_COURSR_STUDENT + " AND USERID=" + userID +
                        " ORDER BY CourseID DESC";

                try
                {
                        conn = DBUtil.getConnection();
                        stmt = conn.createStatement();
                        LogUtil.debug("course",
                                "[CourseMyCourseDAOImpl]====================the sql string is : " +
                                        sqlStr);
                        rs = stmt.executeQuery(sqlStr);

                        while (rs.next())
                        {
                                cm = new CourseModel();
                                cm.setCourseID(rs.getInt("CourseID"));
                                cm.setName(rs.getString("CourseName"));
                                cm.setCourseCode(rs.getString("CourseCode"));
                                cm.setCourseUserStatus(rs.getInt("CourseUser_Status"));

                                cml.add(cm);
                        }
                }
                catch (SQLException se)
                {
                        se.printStackTrace();
                        throw new CourseSysException(se);
                }
                finally
                {
                        closeResultSet(rs);
                        closeStatement(stmt);
                        closeConnection(conn);
                }

                CourseListModel clm = new CourseListModel();
                clm.setList(cml);

                return clm;
        }

        public CourseListModel getMyApplyingCourse(int userID)
                throws CourseSysException
        {
                Statement stmt = null;
                Connection conn = null;
                ResultSet rs = null;

                CourseModel cm = null;
                ArrayList cml = new ArrayList();
                String sqlStr = "SELECT DISTINCT  CourseID,CourseName,CourseCode,CourseUser_Status FROM C_MYCOURSE_VIEW " +
                        " WHERE CourseUser_Status = " + CourseKeys.COURSE_USER_APPLY_STATE +
                        " and CourseStatus = '" + CourseKeys.COURSE_AVAILABLE_STATE +
                        "' And RoleID =" + SecurityConstants.ROLE_COURSR_STUDENT +
                        " AND USERID=" + userID + " ORDER BY CourseID DESC";

                try
                {
                        conn = DBUtil.getConnection();
                        stmt = conn.createStatement();
                        LogUtil.debug("course",
                                "[CourseMyCourseDAOImpl]====================the sql string is : " +
                                        sqlStr);
                        rs = stmt.executeQuery(sqlStr);

                        while (rs.next())
                        {
                                cm = new CourseModel();
                                cm.setCourseID(rs.getInt("CourseID"));
                                cm.setName(rs.getString("CourseName"));
                                cm.setCourseCode(rs.getString("CourseCode"));
                                cm.setCourseUserStatus(rs.getInt("CourseUser_Status"));

                                cml.add(cm);
                        }
                }
                catch (SQLException se)
                {
                        se.printStackTrace();
                        throw new CourseSysException(se);
                }
                finally
                {
                        closeResultSet(rs);
                        closeStatement(stmt);
                        closeConnection(conn);
                }

                CourseListModel clm = new CourseListModel();
                clm.setList(cml);

                return clm;
        }

        public CourseListModel getMyLearnedCourse(int userID)
                throws CourseSysException
        {
                return null;
        }

        protected void closeConnection(Connection dbConnection)
                throws CourseSysException
        {
                try
                {
                        if (dbConnection != null)
                        {
                                dbConnection.close();
                        }
                }
                catch (SQLException se)
                {
                        throw new CourseSysException(se);
                }
        }

        protected void closeResultSet(ResultSet result) throws CourseSysException
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
                        throw new CourseSysException(se);
                }
        }

        protected void closeStatement(Statement stmt) throws CourseSysException
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
                        throw new CourseSysException(se);
                }
        }
}
