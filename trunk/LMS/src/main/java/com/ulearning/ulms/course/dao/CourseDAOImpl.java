/**
 * CourseDAOImpl.java.
 * User: fengch  Date: 2004-5-9
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.course.dao;

import com.ulearning.ulms.core.exceptions.ULMSException;
import com.ulearning.ulms.core.exceptions.ULMSSysException;
import com.ulearning.ulms.core.security.bean.SecurityConstants;
import com.ulearning.ulms.core.security.bean.SecurityHelper;
import com.ulearning.ulms.core.util.*;
import com.ulearning.ulms.core.util.Calendar;
import com.ulearning.ulms.course.exceptions.CourseAppException;
import com.ulearning.ulms.course.exceptions.CourseSysException;
import com.ulearning.ulms.course.helper.CourseUserHelper;
import com.ulearning.ulms.course.model.*;
import com.ulearning.ulms.course.util.CourseKeys;
import com.ulearning.ulms.course.webimpls.CourseUserWebImpl;
import com.ulearning.ulms.util.DBUtil;
import com.ulearning.ulms.util.HibernateDAO;
import com.ulearning.ulms.util.HibernateUtil;
import com.ulearning.ulms.util.LMSConstants;
import com.ulearning.ulms.util.log.DebugUtil;
import com.ulearning.ulms.util.log.LogUtil;
import com.ulearning.ulms.familyeducation.exception.FamilyEducationSysException;

import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Query;
import net.sf.hibernate.Session;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.*;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


public class CourseDAOImpl implements CourseDAO
{
        protected static Log logger = LogFactory.getLog(CourseDAOImpl.class);

        public CourseDAOImpl()
        {
        }

        /**
         * Create Course to JieFo
         *
         * @param form
         * @throws CourseSysException
         */
        public void addJieFoCourse(JieFoCourseForm form) throws CourseSysException
        {
                Connection dbConnection = null;
                Statement stmt = null;
                String sqlStr = "insert into train(TrainID,TrainName) values(" +
                        form.getTrainID() + "," + "'" + form.getTrainName() + "')";
                LogUtil.debug("course", "[CourseDAOImpl] " + sqlStr);

                try
                {
                        dbConnection = getJieFoConnection();
                        stmt = dbConnection.createStatement();
                        // String sqlStr_identy = "Set Identity_Insert Train  On";
                        //stmt.execute(sqlStr_identy);
                        stmt.execute(sqlStr);

                        //sqlStr_identy = "Set Identity_Insert Train  Off";
                        // stmt.execute(sqlStr_identy);
                }
                catch (SQLException se)
                {
                        se.printStackTrace();
                        throw new CourseSysException(
                                "SQLException while addJieFoCourse(JieFoCourseForm details) " +
                                        "Organ; Serial = " + form.getTrainName() + " :\n" + se);
                }
                catch (ULMSSysException se)
                {
                        se.printStackTrace();
                        throw new CourseSysException(
                                "SQLException while addJieFoCourse(JieFoCourseForm details) " +
                                        "Organ; Serial = " + form.getTrainName() + " :\n" + se);
                }
                finally
                {
                        closeStatement(stmt);
                        closeConnection(dbConnection);
                }
        }

        public float getJieFoChenji(int courseID, int userID, int type)
                throws CourseSysException
        {
                Connection dbConnection = null;
                Statement stmt = null;
                ResultSet rs = null;
                float ff = 0;
                String sqlStr = "select avg(b.cj) as chengj from tk_cl a,clerk_kscj b where a.Tk_cl_id=b.Tk_cl_id " +
                        "and a.Tk_cl_type=" + type +
                        " and b.Clerk_ks_status=1 and a.IsJob=" + courseID +
                        " and b.clerk_ID= " + userID;
                LogUtil.debug("course", "[CourseDAOImpl] " + sqlStr);

                try
                {
                        dbConnection = getJieFoConnection();
                        stmt = dbConnection.createStatement();
                        rs = stmt.executeQuery(sqlStr);

                        if (rs.next())
                        {
                                ff = rs.getFloat(1);
                        }
                }
                catch (SQLException se)
                {
                        se.printStackTrace();
                        throw new CourseSysException(
                                "SQLException while delJieFoCourse(List courseIDs) " +
                                        "Course; Serial = " + courseID + " :\n" + se);
                }
                catch (ULMSSysException se)
                {
                        se.printStackTrace();
                        throw new CourseSysException(
                                "SQLException while delJieFoCourse(List courseIDs) " +
                                        "Course; Serial = " + courseID + " :\n" + se);
                }
                finally
                {
                        closeResultSet(rs);
                        closeStatement(stmt);
                        closeConnection(dbConnection);
                }

                return ff; //To change body of implemented methods use File | Settings | File Templates.
        }

        public void updateJieFoCourse(JieFoCourseForm form)
                throws CourseSysException
        {
                Connection dbConnection = null;
                Statement stmt = null;
                String sqlStr = "update train set TrainName='" + form.getTrainName() +
                        "'" + "where TrainID =" + form.getTrainID();
                LogUtil.debug("course", "[CourseDAOImpl] " + sqlStr);

                try
                {
                        dbConnection = getJieFoConnection();
                        stmt = dbConnection.createStatement();
                        stmt.execute(sqlStr);
                }
                catch (SQLException se)
                {
                        se.printStackTrace();
                        throw new CourseSysException(
                                "SQLException while addJieFoCourse(JieFoCourseForm details) " +
                                        "Organ; Serial = " + form.getTrainName() + " :\n" + se);
                }
                catch (ULMSSysException se)
                {
                        se.printStackTrace();
                        throw new CourseSysException(
                                "SQLException while addJieFoCourse(JieFoCourseForm details) " +
                                        "Organ; Serial = " + form.getTrainName() + " :\n" + se);
                }
                finally
                {
                        closeStatement(stmt);
                        closeConnection(dbConnection);
                }
        }

        /**
         * delete jiefocourse
         *
         * @param courseIDs
         * @throws CourseSysException
         */
        public void deleteJieFoCourse(String[] courseIDs) throws CourseSysException
        {
                Connection dbConnection = null;
                Statement stmt = null;
                ResultSet rs = null;
                String sqlStr1;
                String sqlStr2;
                String sqlStr3;

                try
                {
                        dbConnection = getJieFoConnection();
                        stmt = dbConnection.createStatement();

                        for (int i = 0; i < courseIDs.length; i++)
                        {
                                sqlStr1 = "select * from train_clerk where trainID=" +
                                        courseIDs[i];
                                sqlStr3 = "delete from train_clerk where trainID =" +
                                        courseIDs[i];
                                rs = stmt.executeQuery(sqlStr1);

                                if (rs.next())
                                {
                                        stmt.execute(sqlStr3);
                                }

                                //trainID = Integer.parseInt((String) courseIDs.get(i));
                                sqlStr2 = "delete from train where trainID=" + courseIDs[i] +
                                        "";
                                LogUtil.debug("course", "[CourseDAOImpl] " + sqlStr2);
                                stmt.execute(sqlStr2);
                        }
                }
                catch (SQLException se)
                {
                        se.printStackTrace();
                        throw new CourseSysException(
                                "SQLException while delJieFoCourse(List courseIDs) " +
                                        "Course; Serial = " + courseIDs + " :\n" + se);
                }
                catch (ULMSSysException se)
                {
                        se.printStackTrace();
                        throw new CourseSysException(
                                "SQLException while delJieFoCourse(List courseIDs) " +
                                        "Course; Serial = " + courseIDs + " :\n" + se);
                }
                finally
                {
                        closeResultSet(rs);
                        closeStatement(stmt);
                        closeConnection(dbConnection);
                }
        }

        /**
         * Create Course and Certificate Catalog
         *
         * @param value
         * @throws CourseSysException
         */
        public void createCatalog(CatalogModel value) throws CourseSysException
        {
                try
                {
                        HibernateDAO.add(value.getCatalog());
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new CourseSysException(
                                "ULMSSysException while createCatalog method" + e);
                }
        }

        /**
         * Create Course according to the Course Model object
         *
         * @param value
         * @return
         * @throws CourseSysException
         * @throws CourseAppException
         */
        public int createCourse(CourseModel value)
                throws CourseSysException, CourseAppException
        {
                int courseID = 0;
                String courseCode = value.getCourseCode();
                courseCode = "'" + courseCode + "'";

                String regStartDate_str = value.getRegStartDateValue(); //DateTimeUtil.FormatDate_Ora(regStartDate);

                if (regStartDate_str != null)
                {
                        Date regStartDate = DateTimeUtil.parseDate(regStartDate_str);
                        value.setRegStartDate(regStartDate);
                }

                String regEndDate_str = value.getRegEndDateValue();

                if (regEndDate_str != null)
                {
                        Date regEndDate = DateTimeUtil.parseDate(regEndDate_str);
                        value.setRegEndDate(regEndDate);
                }

                String lifeStartDate_str = value.getLifeStartDateValue(); //DateTimeUtil.FormatDate_Ora(lifeStartDate);

                if (lifeStartDate_str != null)
                {
                        Date lifeStartDate = DateTimeUtil.parseDate(lifeStartDate_str);
                        value.setLifeStartDate(lifeStartDate);
                }

                String lifeEndDate_str = value.getLifeEndDateValue(); //DateTimeUtil.FormatDate_Ora(lifeEndDate);

                if (lifeEndDate_str != null)
                {
                        Date lifeEndDate = DateTimeUtil.parseDate(lifeEndDate_str);
                        value.setLifeEndDate(lifeEndDate);

                        if (value.getLifeEndDate() != null)
                        {
                                java.util.Calendar cal = java.util.Calendar.getInstance();
                                cal.setTime(value.getLifeEndDate());
                                cal.set(java.util.Calendar.HOUR_OF_DAY, 23);
                                cal.set(java.util.Calendar.MINUTE, 59);
                                cal.set(java.util.Calendar.SECOND, 59);
                                cal.set(java.util.Calendar.MILLISECOND, 999);
                                value.setLifeEndDate(cal.getTime());
                        }
                }

                value.setEstablishDate(new Date());

                try
                {
                        String result = HibernateDAO.add(value.getCourse()).toString();
                        LogUtil.debug("course",
                                "[CourseDAOImpl]createCourse===========needApprove=" +
                                        value.getNeedApprove());
                        LogUtil.debug("course",
                                "[CourseDAOImpl]createCourse===========needApprove1=" +
                                        value.getCourse().getNeedapprove());
                        courseID = Integer.parseInt(result);

                        try
                        {
                                //add jiefocours
                                if (Config.getIsIntegrateJieFo())
                                {
                                        CourseDAO dao = CourseDAOFactory.getDAO();
                                        JieFoCourseForm jff = new JieFoCourseForm();
                                        jff.setTrainID(courseID);
                                        jff.setTrainName(value.getName());
                                        dao.addJieFoCourse(jff);
                                }
                        }
                        catch (Exception ex)
                        {
                        }
                        //add creator as course teacher  xln项目需要发布课程是暂时不加角色
                        //如果是教师发布的课程则加到课程里
                        if (SecurityHelper.getUserRoleForPortal(value.getCreator(), value.getAspID(), 1) == SecurityConstants.ROLE_COURSE_TEACHER)
                        {
                                CourseUserHelper.addCourseUser(courseID,
                                        SecurityConstants.USER_COURSE_RELATION, value.getCreator(),
                                        CourseKeys.COURSE_USER_AVAILABLE_STATE,
                                        SecurityConstants.ROLE_COURSE_TEACHER);
                        }
                }
                catch (ULMSException se)
                {
                        se.printStackTrace();
                        throw new CourseSysException(se);
                }

                return courseID;
        }

        /**
         * @param catalogIDs
         * @throws com.ulearning.ulms.course.exceptions.CourseSysException
         *
         */
        public void deleteCatalog(List catalogIDs) throws CourseSysException
        {
                String sql_str;
                int i = 0;
                int CountDeleted;
                int catalogID;

                Connection conn = null;
                Statement stmt = null;

                try
                {
                        conn = DBUtil.getConnection();
                        stmt = conn.createStatement();

                        while (i < catalogIDs.size())
                        {
                                catalogID = ((Integer) catalogIDs.get(i)).intValue();

                                sql_str = "delete from C_Catalog_Tab where CatalogID=" +
                                        catalogID;
                                LogUtil.debug("course", "[CourseDAOImpl] " + sql_str);
                                CountDeleted = stmt.executeUpdate(sql_str);
                                LogUtil.debug("course",
                                        "[CourseDAOImpl] " + "删除 " + CountDeleted + "行");
                                i++;
                        }
                }
                catch (SQLException se)
                {
                        se.printStackTrace();
                        throw new CourseSysException(se);
                }
                finally
                {
                        closeStatement(stmt);
                        closeConnection(conn);
                }
        }

        public void deleteByMaster(int masterID) throws CourseSysException
        {
                String sql_str;
                Connection conn = null;
                Statement stmt = null;

                try
                {
                        conn = DBUtil.getConnection();
                        stmt = conn.createStatement();
                        sql_str = "update  C_COURSE_TAB set MasterID=0 where MasterID=" +
                                masterID;
                        stmt.executeUpdate(sql_str);
                }
                catch (SQLException se)
                {
                        se.printStackTrace();
                        throw new CourseSysException(se);
                }
                finally
                {
                        closeStatement(stmt);
                        closeConnection(conn);
                }
        }

        /**
         * @param courseIDs
         * @throws com.ulearning.ulms.course.exceptions.CourseSysException
         *
         */
        public void deleteCourse(List courseIDs) throws CourseSysException
        {
                String sql_str;
                int i = 0;
                int CountDeleted;
                int courseID;

                Connection conn = null;
                Statement stmt = null;

                try
                {
                        conn = DBUtil.getConnection();
                        stmt = conn.createStatement();

                        while (i < courseIDs.size())
                        {
                                courseID = ((Integer) courseIDs.get(i)).intValue();

                                sql_str = "delete from C_Course_Tab where CourseID=" +
                                        courseID;
                                LogUtil.debug("course", "[CourseDAOImpl] " + sql_str);
                                CountDeleted = stmt.executeUpdate(sql_str);
                                LogUtil.debug("course",
                                        "[CourseDAOImpl] " + "删除 " + CountDeleted + "行");
                                i++;
                        }
                }
                catch (SQLException se)
                {
                        se.printStackTrace();
                        throw new CourseSysException(se);
                }
                finally
                {
                        closeStatement(stmt);
                        closeConnection(conn);
                }
        }

        /**
         * @param catalogID
         * @throws com.ulearning.ulms.course.exceptions.CourseSysException
         *
         */
        public CatalogModel getCatalog(int catalogID) throws CourseSysException
        {
                String name;
                String description;
                Connection conn = null;
                Statement stmt = null;
                ResultSet rs = null;
                CatalogModel cat = null;

                try
                {
                        conn = DBUtil.getConnection();
                        stmt = conn.createStatement();

                        String queryStr1 = "select Name,Description,defaultCourseIcon,icon from C_Catalog_Tab" +
                                " WHERE catalogID=" + catalogID;
                        LogUtil.debug("course",
                                "[CourseDAOImpl] ==========queryStr1 = " + queryStr1);

                        rs = stmt.executeQuery(queryStr1);

                        if (rs.next())
                        {
                                name = rs.getString("Name");
                                description = rs.getString("Description");
                                cat = new CatalogModel(catalogID, name, description,StringUtils.trimToEmpty(rs.getString("icon")),StringUtils.trimToEmpty(rs.getString("defaultCourseIcon")));
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

                return cat;
        }

        /**
         * @param catalogID
         * @throws com.ulearning.ulms.course.exceptions.CourseSysException
         *
         */
        public List getCatalogPath(int catalogID) throws CourseSysException
        {
                String name;
                Connection conn = null;
                Statement stmt = null;
                ResultSet rs = null;
                CatalogModel cat = null;
                ArrayList catalogPathList = new ArrayList();

                try
                {
                        conn = DBUtil.getConnection();
                        stmt = conn.createStatement();

                        String queryStr1 = "select ParentID,Name from C_Catalog_Tab" +
                                " WHERE  catalogID=" + catalogID;
                        LogUtil.debug("course",
                                "[CourseDAOImpl] ==========queryStr1 = " + queryStr1);

                        rs = stmt.executeQuery(queryStr1);

                        while (rs.next())
                        {
                                name = rs.getString("Name");

                                cat = new CatalogModel(catalogID, name);
                                catalogPathList.add(cat);
                                catalogID = rs.getInt("parentID");

                                if (catalogID == 0)
                                {
                                        break;
                                }

                                String queryStr = "select ParentID,Name from C_Catalog_Tab" +
                                        " WHERE type='" + CourseKeys.CATALOG_COURSE_TYPE +
                                        "' and catalogID=" + catalogID;
                                LogUtil.debug("course",
                                        "[CourseDAOImpl] ==========queryStr = " + queryStr);

                                rs = stmt.executeQuery(queryStr);
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

                return catalogPathList;
        }

        /**
         * 根据课程编号返回CourseModel.<br>
         *
         * @param aspID
         * @param code
         * @return
         * @throws CourseSysException
         */
        public CourseModel getCourse(int aspID, int orgID, String code)
                throws CourseSysException
        {
                String name;
                String description;
                Connection conn = null;
                Statement stmt = null;
                ResultSet rs = null;
                CourseModel mas = null;

                try
                {
                        conn = DBUtil.getConnection();
                        stmt = conn.createStatement();

                        String queryStr1 = "select * " + " from C_Course_Tab" +
                                " WHERE CourseCode='" + code + "'";

                        if (aspID != 0)
                        {
                                queryStr1 += (" and aspID=" + aspID);
                        }

                        if (orgID != 0)
                        {
                                queryStr1 += (" and orgID=" + orgID);
                        }

                        LogUtil.debug("course",
                                "[CourseDAOImpl] ==========queryStr1 = " + queryStr1);

                        rs = stmt.executeQuery(queryStr1);

                        if (rs.next())
                        {
                                mas = new CourseModel(rs.getInt("CourseID"),
                                        rs.getInt("MasterID"),
                                        StringUtil.nullToStr(rs.getString("Name")),
                                        StringUtil.nullToStr(rs.getString("CourseCode")),
                                        "MasterName",
                                        StringUtil.nullToStr(rs.getString("Description")),
                                        rs.getInt("OrgID"), rs.getInt("AspID"),
                                        StringUtil.nullToStr(rs.getString("Type")),
                                        StringUtil.nullToStr(rs.getString("Keyword")),
                                        rs.getInt("Creator"), rs.getInt("IsCharge"),
                                        rs.getFloat("Charge"), rs.getFloat("MemberCharge"),
                                        "ChargeInfo",
                                        DateTimeUtil.toDate(rs.getDate("EstablishDate"),
                                                rs.getTime("EstablishDate")),
                                        DateTimeUtil.toDate(rs.getDate("ModifyDate"),
                                                rs.getTime("ModifyDate")),
                                        rs.getInt("allowFreedomReg"), rs.getInt("needApprove"),
                                        rs.getInt("RegisterMode"), rs.getInt("Guest"),
                                        rs.getInt("CatalogID"), rs.getInt("ObjectID"),
                                        rs.getInt("IsSendMail"), rs.getInt("IsTimeLimit"),
                                        //rs.getDate("RegStartDate"),
                                        //rs.getDate("RegEndDate"),
                                        DateTimeUtil.toDate(rs.getDate("RegStartDate"),
                                                rs.getTime("RegStartDate")),
                                        DateTimeUtil.toDate(rs.getDate("RegEndDate"),
                                                rs.getTime("RegEndDate")), rs.getInt("IsPassword"),
                                        StringUtil.nullToStr(rs.getString("password")),
                                        rs.getInt("lifeSort"),
                                        //rs.getDate("LifeStartDate"),
                                        //rs.getDate("LifeEndDate"),
                                        DateTimeUtil.toDate(rs.getDate("LifeStartDate"),
                                                rs.getTime("LifeStartDate")),
                                        DateTimeUtil.toDate(rs.getDate("LifeEndDate"),
                                                rs.getTime("LifeEndDate")), rs.getInt("lifeTime"),
                                        rs.getInt("Status"), rs.getInt("MaximumEnrollment"),
                                        rs.getInt("MinimumEnrollment"), rs.getInt("Period"),
                                        rs.getFloat("Credit"), rs.getInt("ScoreType"),
                                        StringUtil.nullToStr(rs.getString("ScoreLimit")),
                                        rs.getInt("TeachMode"),
                                        StringUtil.nullToStr(rs.getString("Logo")),
                                        rs.getString("operator"),
                                        rs.getString("logopic"),
                                        rs.getString("isCommend"),
                                        rs.getString("remark"));
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

                return mas;
        }

        /**
         * @param courseID
         * @throws com.ulearning.ulms.course.exceptions.CourseSysException
         *
         */
        public CourseModel getCourse(int courseID) throws CourseSysException
        {
                String name;
                String description;
                Connection conn = null;
                Statement stmt = null;
                ResultSet rs = null;
                CourseModel mas = null;

                try
                {
                        conn = DBUtil.getConnection();
                        stmt = conn.createStatement();

                        String queryStr1 = "select * " + " from C_Course_Tab" +
                                " WHERE courseID=" + courseID;
                        LogUtil.debug("course",
                                "[CourseDAOImpl] ==========queryStr1 = " + queryStr1);

                        rs = stmt.executeQuery(queryStr1);

                        if (rs.next())
                        {
                                mas = new CourseModel(rs.getInt("CourseID"),
                                        rs.getInt("MasterID"),
                                        StringUtil.nullToStr(rs.getString("Name")),
                                        StringUtil.nullToStr(rs.getString("CourseCode")),
                                        "MasterName",
                                        StringUtil.nullToStr(rs.getString("Description")),
                                        rs.getInt("OrgID"), rs.getInt("AspID"),
                                        StringUtil.nullToStr(rs.getString("Type")),
                                        StringUtil.nullToStr(rs.getString("Keyword")),
                                        rs.getInt("Creator"), rs.getInt("IsCharge"),
                                        rs.getFloat("Charge"), rs.getFloat("MemberCharge"),
                                        "ChargeInfo",
                                        DateTimeUtil.toDate(rs.getDate("EstablishDate"),
                                                rs.getTime("EstablishDate")),
                                        DateTimeUtil.toDate(rs.getDate("ModifyDate"),
                                                rs.getTime("ModifyDate")),
                                        rs.getInt("allowFreedomReg"), rs.getInt("needApprove"),
                                        rs.getInt("RegisterMode"), rs.getInt("Guest"),
                                        rs.getInt("CatalogID"), rs.getInt("ObjectID"),
                                        rs.getInt("IsSendMail"), rs.getInt("IsTimeLimit"),
                                        //rs.getDate("RegStartDate"),
                                        //rs.getDate("RegEndDate"),
                                        DateTimeUtil.toDate(rs.getDate("RegStartDate"),
                                                rs.getTime("RegStartDate")),
                                        DateTimeUtil.toDate(rs.getDate("RegEndDate"),
                                                rs.getTime("RegEndDate")), rs.getInt("IsPassword"),
                                        StringUtil.nullToStr(rs.getString("password")),
                                        rs.getInt("lifeSort"),
                                        //rs.getDate("LifeStartDate"),
                                        //rs.getDate("LifeEndDate"),
                                        DateTimeUtil.toDate(rs.getDate("LifeStartDate"),
                                                rs.getTime("LifeStartDate")),
                                        DateTimeUtil.toDate(rs.getDate("LifeEndDate"),
                                                rs.getTime("LifeEndDate")), rs.getInt("lifeTime"),
                                        rs.getInt("Status"), rs.getInt("MaximumEnrollment"),
                                        rs.getInt("MinimumEnrollment"), rs.getFloat("Period"),
                                        rs.getFloat("Credit"), rs.getInt("ScoreType"),
                                        StringUtil.nullToStr(rs.getString("ScoreLimit")),
                                        rs.getInt("TeachMode"),
                                        StringUtil.nullToStr(rs.getString("Logo")),
                                        rs.getString("operator"),
                                        rs.getString("logopic"),
                                        rs.getString("isCommend"),
                                        rs.getString("remark")
                                );
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

                return mas;
        }

        /**
         * @param courseID
         * @throws com.ulearning.ulms.course.exceptions.CourseSysException
         *
         */
        public String getCourseName(int courseID) throws CourseSysException
        {
                String name = null;
                Connection conn = null;
                Statement stmt = null;
                ResultSet rs = null;
                CourseModel mas = null;

                try
                {
                        conn = DBUtil.getConnection();
                        stmt = conn.createStatement();

                        String queryStr1 = "select " + "Name from C_Course_Tab" +
                                " WHERE courseID=" + courseID;
                        LogUtil.debug("course",
                                "[CourseDAOImpl] ==========queryStr1 = " + queryStr1);

                        rs = stmt.executeQuery(queryStr1);

                        if (rs.next())
                        {
                                name = StringUtil.nullToStr(rs.getString("Name"));
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

                return name;
        }

        /**
         * @param catalogID
         * @throws com.ulearning.ulms.course.exceptions.CourseSysException
         *
         */
        public CourseTreeModel getTree(int catalogID) throws CourseSysException
        {
                String name;
                String courseCode;
                String masterName;
                String description;
                int courseID;
                int masterID;
                Connection conn = null;
                Statement stmt = null;
                ResultSet rs = null;

                CourseModel mas = null;
                CatalogModel cat = null;

                ArrayList courses = new ArrayList();
                ArrayList catalogs = new ArrayList();

                CourseListModel courseList = new CourseListModel(courses);
                CatalogListModel catalogList = new CatalogListModel(catalogs);

                CourseTreeModel mt = new CourseTreeModel(courseList, catalogList);

                try
                {
                        conn = DBUtil.getConnection();
                        stmt = conn.createStatement();

                        String sql = "SELECT DISTINCT c.CourseID,c.MasterID,c.Name AS CourseName,c.Description AS CourseDescription,c.CourseCode,m.Name AS MasterName  " +
                                "FROM C_Course_Tab c,C_Master_Tab m WHERE c.MasterID = m.MasterID AND " +
                                "c.catalogID = " + catalogID + " ORDER BY c.CourseID DESC ";

                        LogUtil.debug("course", "[CourseDAOImpl]  sql=" + sql);
                        stmt.execute(sql);

                        rs = stmt.getResultSet();

                        while (rs.next())
                        {
                                courseID = rs.getInt("CourseID");
                                masterID = rs.getInt("MasterID");
                                name = rs.getString("CourseName");
                                courseCode = rs.getString("CourseCode");
                                masterName = ((masterID == 0) ? "&nbsp;"
                                        : rs.getString("MasterName"));
                                description = rs.getString("CourseDescription");
                                mas = new CourseModel(courseID, name, courseCode, masterName,
                                        description);
                                courses.add(mas);
                        }

                        sql = "SELECT CatalogID,Name,Description,defaultCourseIcon,icon FROM C_CATALOG_TAB WHERE catalogID!=0 " +
                                " and type='" + CourseKeys.CATALOG_COURSE_TYPE +
                                "' and parentID=" + catalogID + " order by CatalogID";
                        LogUtil.debug("course", "[CourseDAOImpl]  " + sql);
                        stmt.execute(sql);

                        rs = stmt.getResultSet();

                        while (rs.next())
                        {
                                name = rs.getString("Name");
                                description = rs.getString("Description");
                                cat = new CatalogModel(rs.getInt("CatalogID"), name, description,StringUtils.trimToEmpty(rs.getString("icon")),StringUtils.trimToEmpty(rs.getString("defaultCourseIcon")));
                                catalogs.add(cat);
                        }

                        LogUtil.debug("course", "[CourseDAOImpl]getTree over" + "\n");
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

                return mt;
        }

        public CourseTreeModel getTree(int catalogID, int aspID, int orgID,
                                       int status) throws CourseSysException
        {
                String name;
                String courseCode;
                String masterName;
                String description;
                String operator;
                Date lifeStartDate;
                String logoPic;
                String remark;
                int statuss;
                int aspIDs;
                int courseID;
                int masterID;
                int guest;
                Connection conn = null;
                Statement stmt = null;
                ResultSet rs = null;

                CourseModel mas = null;
                CatalogModel cat = null;

                ArrayList courses = new ArrayList();
                ArrayList catalogs = new ArrayList();
                String aspID_str = "";
                String orgID_str = "";
                String status_str = "";

                if (aspID != 0)
                {
                        aspID_str = " and c.aspID=" + aspID;
                }

                if (orgID != 0)
                {
                        orgID_str = " and c.orgID=" + orgID;
                }

                if (status != 0)
                {
                        status_str = " and CourseStatus = " + status;
                }

                CourseListModel courseList = new CourseListModel(courses);
                CatalogListModel catalogList = new CatalogListModel(catalogs);

                CourseTreeModel mt = new CourseTreeModel(courseList, catalogList);

                try
                {
                        conn = DBUtil.getConnection();
                        stmt = conn.createStatement();

                        String sql = "SELECT DISTINCT c.CourseID,c.MasterID,c.Name AS CourseName,c.Description AS CourseDescription,c.CourseCode,c.Guest  " +
                                ",c.operator,c.LIFESTARTDATE,c.LOGOPIC,c.REMARK,c.catalogID,c.Status,c.aspID FROM C_Course_Tab c WHERE c.catalogID = " +
                                catalogID + aspID_str + orgID_str +
                                "  ORDER BY c.CourseID DESC";

                        LogUtil.debug("course", "[CourseDAOImpl]  sql=" + sql);
                        stmt.execute(sql);

                        rs = stmt.getResultSet();

                        while (rs.next())
                        {
                                courseID = rs.getInt("CourseID");
                                masterID = rs.getInt("MasterID");
                                name = rs.getString("CourseName");
                                courseCode = rs.getString("CourseCode");
                                masterName = ((masterID == 0) ? "&nbsp;"
                                        : rs.getString("MasterName"));
                                description = rs.getString("CourseDescription");
                                guest = rs.getInt("Guest");
                                operator = rs.getString("operator");
                                lifeStartDate = rs.getDate("LIFESTARTDATE");
                                logoPic = rs.getString("LOGOPIC");
                                remark = rs.getString("REMARK");
                                statuss = rs.getInt("Status");
                                aspIDs = rs.getInt("aspID");
                                catalogID = rs.getInt("catalogID");
                                mas = new CourseModel(courseID, name, courseCode, masterName,
                                        description, guest, operator, lifeStartDate, logoPic, remark, statuss, aspIDs,catalogID);
                                courses.add(mas);
                        }

                        sql = "SELECT CatalogID,Name,Description,defaultCourseIcon,icon FROM C_CATALOG_TAB c WHERE c.catalogID!=0 " +
                                " and c.type='" + CourseKeys.CATALOG_COURSE_TYPE +
                                "' and c.parentID=" + catalogID + //+ aspID_str + 新理念项目暂时屏蔽掉aspID
                                " order by c.CatalogID";
                        LogUtil.debug("course", "[CourseDAOImpl]  " + sql);
                        stmt.execute(sql);

                        rs = stmt.getResultSet();

                        while (rs.next())
                        {
                                name = rs.getString("Name");
                                description = rs.getString("Description");
                                cat = new CatalogModel(rs.getInt("CatalogID"), name, description,
                                        StringUtils.trimToEmpty(rs.getString("icon")),StringUtils.trimToEmpty(rs.getString("defaultCourseIcon")));
                                catalogs.add(cat);
                        }

                        LogUtil.debug("course", "[CourseDAOImpl]getTree over" + "\n");
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
                return mt;
        }

        public CourseTreeModel getCourseCommend(int catalogID, int aspID, int orgID,
                                                int status, int isCommend) throws CourseSysException
        {
                String name;
                String courseCode;
                String masterName;
                String description;
                String operator;
                Date lifeStartDate;
                String logoPic;
                String remark;
                String teachmode;
                int courseID;
                int masterID;
                int guest;
                int aspIDRs;
                Connection conn = null;
                Statement stmt = null;
                ResultSet rs = null;

                CourseModel mas = null;
                CatalogModel cat = null;

                ArrayList courses = new ArrayList();
                ArrayList catalogs = new ArrayList();
                String aspID_str = "";
                String orgID_str = "";
                String status_str = "";
                String commend_str = "";
                String catalog_str = "";
                if (aspID != 0)
                {
                        aspID_str = " and c.aspID=" + aspID;
                }

                if (orgID != 0)
                {
                        orgID_str = " and c.orgID=" + orgID;
                }

                if (status != 0)
                {
                        status_str = " and c.Status = " + status;
                }
                if (isCommend != 0)
                {
                        commend_str = " and c.IsCommend=" + isCommend;
                }
                if (catalogID != 0)
                {
                        catalog_str = " and c.catalogID =" + catalogID;
                }

                CourseListModel courseList = new CourseListModel(courses);
                CatalogListModel catalogList = new CatalogListModel(catalogs);

                CourseTreeModel mt = new CourseTreeModel(courseList, catalogList);

                try
                {
                        conn = DBUtil.getConnection();
                        stmt = conn.createStatement();
                        String sql = "SELECT DISTINCT c.CourseID,c.MasterID,c.Name AS CourseName,c.Description AS CourseDescription,c.CourseCode,c.Guest  " +
                                ",c.operator,c.LIFESTARTDATE,c.LOGOPIC,c.REMARK,c.teachmode,c.AspID FROM C_Course_Tab c WHERE  1=1 " +
                                catalog_str + aspID_str + orgID_str + status_str + commend_str +
                                "  ORDER BY c.CourseID DESC";

                        LogUtil.debug("course", "[CourseDAOImpl]  sql=" + sql);
                        stmt.execute(sql);

                        rs = stmt.getResultSet();

                        while (rs.next())
                        {
                                courseID = rs.getInt("CourseID");
                                masterID = rs.getInt("MasterID");
                                name = rs.getString("CourseName");
                                courseCode = rs.getString("CourseCode");
                                masterName = ((masterID == 0) ? "&nbsp;"
                                        : rs.getString("MasterName"));
                                description = rs.getString("CourseDescription");
                                guest = rs.getInt("Guest");
                                operator = rs.getString("operator");
                                lifeStartDate = rs.getDate("LIFESTARTDATE");
                                logoPic = rs.getString("LOGOPIC");
                                remark = rs.getString("REMARK");
                                teachmode = rs.getString("TEACHMODE");
                                aspIDRs = rs.getInt("AspID");
                                mas = new CourseModel(courseID, name, courseCode, masterName,
                                        description, guest, operator, lifeStartDate, logoPic, remark, teachmode, aspIDRs);
                                courses.add(mas);
                        }

                        sql = "SELECT CatalogID,Name,Description,defaultCourseIcon,icon FROM C_CATALOG_TAB c WHERE c.catalogID!=0 " +
                                " and c.type='" + CourseKeys.CATALOG_COURSE_TYPE +
                                "' and c.parentID=" + catalogID + aspID_str +
                                " order by c.CatalogID";
                        LogUtil.debug("course", "[CourseDAOImpl]  " + sql);
                        stmt.execute(sql);

                        rs = stmt.getResultSet();

                        while (rs.next())
                        {
                                name = rs.getString("Name");
                                description = rs.getString("Description");
                                cat = new CatalogModel(rs.getInt("CatalogID"), name, description,
                                        StringUtils.trimToEmpty(rs.getString("icon")),StringUtils.trimToEmpty(rs.getString("defaultCourseIcon")));
                                catalogs.add(cat);
                        }

                        LogUtil.debug("course", "[CourseDAOImpl]getTree over" + "\n");
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
                return mt;
        }

        public CourseTreeModel getCourseByXLN(int catalogID, int aspID, int orgID,
                                              int status, int isCommend) throws CourseSysException
        {
                String name;
                String courseCode;
                String masterName;
                String description;
                String operator;
                Date lifeStartDate;
                String logoPic;
                int aspIDR;
                int statuss;
                String remark;
                int courseID;
                int masterID;
                int guest;
                Connection conn = null;
                Statement stmt = null;
                ResultSet rs = null;

                CourseModel mas = null;
                CatalogModel cat = null;

                ArrayList courses = new ArrayList();
                ArrayList catalogs = new ArrayList();
                String aspID_str = "";
                String orgID_str = "";
                String status_str = "";
                String commend_str = "";
                String catalog_str = "";
                if (aspID != 0)
                {
                        aspID_str = " and c.aspID=" + aspID;
                }

                if (orgID != 0)
                {
                        orgID_str = " and c.orgID=" + orgID;
                }

                if (status != 0)
                {
                        status_str = " and c.Status = " + status;
                }
                if (isCommend != 0)
                {
                        commend_str = " and c.IsCommend=" + isCommend;
                }
                if (catalogID != 0)
                {
                        if (catalogID == CourseKeys.XLN_ENGLISH_ERTONG)
                        {
                                catalog_str = " and c.catalogID in (" + CourseKeys.XLN_ENGLISH_ERTONG + "," + CourseKeys.XLN_ENGLISH_ERTONG_ONE + "," + CourseKeys.XLN_ENGLISH_ERTONG_TWO + "," + CourseKeys.XLN_ENGLISH_ERTONG_THREE + "," + CourseKeys.XLN_ENGLISH_ERTONG_FORE + ")";

                        }
                        else if (catalogID == CourseKeys.XLN_ENGLISH_SHAOER)
                        {
                                catalog_str = " and c.catalogID in (" + CourseKeys.XLN_ENGLISH_SHAOER + "," + CourseKeys.XLN_ENGLISH_SHAOER_ONE + "," + CourseKeys.XLN_ENGLISH_SHAOER_TWO + "," + CourseKeys.XLN_ENGLISH_SHAOER_THREE + "," + CourseKeys.XLN_ENGLISH_SHAOER_FORE + "," + CourseKeys.XLN_ENGLISH_SHAOER_FIVE + ")";
                        }
                        else if (catalogID == CourseKeys.XLN_ENGLISH_CHUZHONG)
                        {
                                catalog_str = " and c.catalogID in (" + CourseKeys.XLN_ENGLISH_CHUZHONG + "," + CourseKeys.XLN_ENGLISH_CHUZHONG_ONE + "," + CourseKeys.XLN_ENGLISH_CHUZHONG_TWO + "," + CourseKeys.XLN_ENGLISH_CHUZHONG_THREE + "," + CourseKeys.XLN_ENGLISH_CHUZHONG_FORE + "," + CourseKeys.XLN_ENGLISH_CHUZHONG_FIVE + "," + CourseKeys.XLN_ENGLISH_CHUZHONG_SIX + ")";
                        }
                        else
                        {
                                catalog_str = " and c.catalogID not in (" + CourseKeys.XLN_ENGLISH_ERTONG + "," + CourseKeys.XLN_ENGLISH_ERTONG_ONE + "," + CourseKeys.XLN_ENGLISH_ERTONG_TWO + "," + CourseKeys.XLN_ENGLISH_ERTONG_THREE + "," + CourseKeys.XLN_ENGLISH_ERTONG_FORE + "," +
                                        +CourseKeys.XLN_ENGLISH_SHAOER + "," + CourseKeys.XLN_ENGLISH_SHAOER_ONE + "," + CourseKeys.XLN_ENGLISH_SHAOER_TWO + "," + CourseKeys.XLN_ENGLISH_SHAOER_THREE + "," + CourseKeys.XLN_ENGLISH_SHAOER_FORE + "," + CourseKeys.XLN_ENGLISH_SHAOER_FIVE + "," +
                                        +CourseKeys.XLN_ENGLISH_CHUZHONG + "," + CourseKeys.XLN_ENGLISH_CHUZHONG_ONE + "," + CourseKeys.XLN_ENGLISH_CHUZHONG_TWO + "," + CourseKeys.XLN_ENGLISH_CHUZHONG_THREE + "," + CourseKeys.XLN_ENGLISH_CHUZHONG_FORE + "," + CourseKeys.XLN_ENGLISH_CHUZHONG_FIVE + "," + CourseKeys.XLN_ENGLISH_CHUZHONG_SIX + ")";
                        }
                }
                else
                {
                        catalog_str = "  and c.catalogID between 1 and 20 ";
                }

                CourseListModel courseList = new CourseListModel(courses);
                CatalogListModel catalogList = new CatalogListModel(catalogs);

                CourseTreeModel mt = new CourseTreeModel(courseList, catalogList);

                try
                {
                        conn = DBUtil.getConnection();
                        stmt = conn.createStatement();
                        String sql = "SELECT DISTINCT c.CourseID,c.MasterID,c.Name AS CourseName,c.Description AS CourseDescription,c.CourseCode,c.Guest  " +
                                ",c.operator,c.LIFESTARTDATE,c.LOGOPIC,c.REMARK,c.catalogID,c.Status,c.aspID FROM C_Course_Tab c WHERE 1=1 " +
                                catalog_str + aspID_str + orgID_str + status_str + commend_str +
                                "  ORDER BY c.CourseID DESC";

                        LogUtil.debug("course", "[CourseDAOImpl]  sql=" + sql);
                        stmt.execute(sql);

                        rs = stmt.getResultSet();

                        while (rs.next())
                        {
                                courseID = rs.getInt("CourseID");
                                masterID = rs.getInt("MasterID");
                                name = rs.getString("CourseName");
                                courseCode = rs.getString("CourseCode");
                                masterName = ((masterID == 0) ? "&nbsp;"
                                        : rs.getString("MasterName"));
                                description = rs.getString("CourseDescription");
                                guest = rs.getInt("Guest");
                                operator = rs.getString("operator");
                                lifeStartDate = rs.getDate("LIFESTARTDATE");
                                logoPic = rs.getString("LOGOPIC");
                                remark = rs.getString("REMARK");
                                statuss = rs.getInt("Status");
                                aspIDR = rs.getInt("aspID");
                                catalogID = rs.getInt("catalogID");
                                mas = new CourseModel(courseID, name, courseCode, masterName,
                                        description, guest, operator, lifeStartDate, logoPic, remark,statuss,aspIDR,catalogID);
                                courses.add(mas);
                        }

                        sql = "SELECT CatalogID,Name,Description,defaultCourseIcon,icon FROM C_CATALOG_TAB c WHERE c.catalogID!=0 " +
                                " and c.type='" + CourseKeys.CATALOG_COURSE_TYPE +
                                "' and c.parentID=" + catalogID + aspID_str +
                                " order by c.CatalogID";
                        LogUtil.debug("course", "[CourseDAOImpl]  " + sql);
                        stmt.execute(sql);

                        rs = stmt.getResultSet();

                        while (rs.next())
                        {
                                name = rs.getString("Name");
                                description = rs.getString("Description");
                                cat = new CatalogModel(rs.getInt("CatalogID"), name, description,
                                        StringUtils.trimToEmpty(rs.getString("icon")),StringUtils.trimToEmpty(rs.getString("defaultCourseIcon")));
                                catalogs.add(cat);
                        }

                        LogUtil.debug("course", "[CourseDAOImpl]getTree over" + "\n");
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
                return mt;
        }

        public CourseTreeModel getCourseByTeach(int catalogID, int aspID, int orgID,
                                                int status, int isCommend,int teachID) throws CourseSysException
        {
                String name;
                String courseCode;
                String masterName;
                String description;
                String operator;
                Date lifeStartDate;
                String logoPic;
                String remark;
                String teachmode;
                int courseID;
                int masterID;
                int guest;
                int aspIDRs;
                Connection conn = null;
                Statement stmt = null;
                ResultSet rs = null;

                CourseModel mas = null;
                CatalogModel cat = null;

                ArrayList courses = new ArrayList();
                ArrayList catalogs = new ArrayList();
                String aspID_str = "";
                String orgID_str = "";
                String status_str = "";
                String commend_str = "";
                String catalog_str = "";
                String techIDStr ="";
                if (aspID != 0)
                {
                        aspID_str = " and c.aspID=" + aspID;
                }

                if (orgID != 0)
                {
                        orgID_str = " and c.orgID=" + orgID;
                }

                if (status != 0)
                {
                        status_str = " and c.Status = " + status;
                }
                if (isCommend != 0)
                {
                        commend_str = " and c.IsCommend=" + isCommend;
                }
                if (catalogID != 0)
                {
                        catalog_str = " and c.catalogID =" + catalogID;
                }
                if(teachID !=0)
                {
                      techIDStr = " and c.teachmode =" + teachID;
                }

                CourseListModel courseList = new CourseListModel(courses);
                CatalogListModel catalogList = new CatalogListModel(catalogs);

                CourseTreeModel mt = new CourseTreeModel(courseList, catalogList);

                try
                {
                        conn = DBUtil.getConnection();
                        stmt = conn.createStatement();
                        String sql = "SELECT DISTINCT c.CourseID,c.MasterID,c.Name AS CourseName,c.Description AS CourseDescription,c.CourseCode,c.Guest  " +
                                ",c.operator,c.LIFESTARTDATE,c.LOGOPIC,c.REMARK,c.teachmode,c.AspID,c.catalogID FROM C_Course_Tab c WHERE  1=1 " +
                                catalog_str + aspID_str + orgID_str + status_str + commend_str + techIDStr+
                                "  ORDER BY c.CourseID DESC";

                        LogUtil.debug("course", "[CourseDAOImpl]  sql=" + sql);
                        stmt.execute(sql);

                        rs = stmt.getResultSet();

                        while (rs.next())
                        {
                                courseID = rs.getInt("CourseID");
                                masterID = rs.getInt("MasterID");
                                name = rs.getString("CourseName");
                                courseCode = rs.getString("CourseCode");
                                masterName = ((masterID == 0) ? "&nbsp;"
                                        : rs.getString("MasterName"));
                                description = rs.getString("CourseDescription");
                                guest = rs.getInt("Guest");
                                operator = rs.getString("operator");
                                lifeStartDate = rs.getDate("LIFESTARTDATE");
                                logoPic = rs.getString("LOGOPIC");
                                remark = rs.getString("REMARK");
                                teachmode = rs.getString("TEACHMODE");
                                aspIDRs = rs.getInt("AspID");
                                catalogID = rs.getInt("catalogID");
                                mas = new CourseModel(courseID, name, courseCode, masterName,
                                        description, guest, operator, lifeStartDate, logoPic, remark, teachmode, aspIDRs,catalogID);
                                courses.add(mas);
                        }

                        sql = "SELECT CatalogID,Name,Description,defaultCourseIcon,icon FROM C_CATALOG_TAB c WHERE c.catalogID!=0 " +
                                " and c.type='" + CourseKeys.CATALOG_COURSE_TYPE +
                                "' and c.parentID=" + catalogID + aspID_str +
                                " order by c.CatalogID";
                        LogUtil.debug("course", "[CourseDAOImpl]  " + sql);
                        stmt.execute(sql);

                        rs = stmt.getResultSet();

                        while (rs.next())
                        {
                                name = rs.getString("Name");
                                description = rs.getString("Description");
                                cat = new CatalogModel(rs.getInt("CatalogID"), name, description,
                                        StringUtils.trimToEmpty(rs.getString("icon")),StringUtils.trimToEmpty(rs.getString("defaultCourseIcon")));
                                catalogs.add(cat);
                        }

                        LogUtil.debug("course", "[CourseDAOImpl]getTree over" + "\n");
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
                return mt;
        }

        /**
         * @param value
         * @throws com.ulearning.ulms.course.exceptions.CourseSysException
         *
         */
        public void updateCatalog(CatalogModel value) throws CourseSysException
        {
                int catalogID = value.getCatalogID();
                String sql_str = null;
                String name = value.getName();
                name = "'" + name.trim() + "'";

                String description = value.getDescription();

                if (description != null)
                {
                        description = "'" + description.trim() + "'";
                }
                else
                {
                        description = "''";
                }

                Connection conn = null;
                Statement stmt = null;

                try
                {
                        conn = DBUtil.getConnection();
                        stmt = conn.createStatement();

                        //prepare the sql string to insert
                        sql_str = "update C_Catalog_TAB set name=" + name +
                                ",description=" + description + " Where CatalogID = " +
                                catalogID;

                        LogUtil.debug("CatalogModel", "[CatalogDAO_Oracle817] " + sql_str);
                        stmt.executeUpdate(sql_str);
                }
                catch (SQLException se)
                {
                        se.printStackTrace();
                        throw new CourseSysException(se);
                }
                finally
                {
                        closeStatement(stmt);
                        closeConnection(conn);
                }
        }

        /**
         * @param value
         * @throws com.ulearning.ulms.course.exceptions.CourseSysException
         *
         */
        public void updateCourse(CourseModel value)
                throws CourseSysException, CourseAppException
        {
                int courseID = value.getCourseID();
                CourseModel newCm = getCourse(courseID);
                String courseCode = value.getCourseCode();

                courseCode = "'" + courseCode + "'";

                String regStartDate_str = value.getRegStartDateValue();
                Date regStartDate = newCm.getRegStartDate();

                if (regStartDate_str != null)
                {
                        regStartDate = DateTimeUtil.parseDate(regStartDate_str);
                }

                String regEndDate_str = value.getRegEndDateValue();
                Date regEndDate = newCm.getRegEndDate();

                if (regEndDate_str != null)
                {
                        regEndDate = DateTimeUtil.parseDate(regEndDate_str);
                }

                String lifeStartDate_str = value.getLifeStartDateValue();
                Date lifeStartDate = newCm.getLifeStartDate();

                if (lifeStartDate_str != null)
                {
                        lifeStartDate = DateTimeUtil.parseDate(lifeStartDate_str);
                }

                String lifeEndDate_str = value.getLifeEndDateValue(); //DateTimeUtil.FormatDate_Ora(lifeEndDate);
                Date lifeEndDate = newCm.getLifeEndDate();

                if (lifeEndDate_str != null)
                {
                        lifeEndDate = DateTimeUtil.parseDate(lifeEndDate_str);
                }

                value.setRegStartDate(regStartDate);
                value.setRegEndDate(regEndDate);
                value.setLifeStartDate(lifeStartDate);
                value.setLifeEndDate(lifeEndDate);
                value.setType(newCm.getType());
                value.setOrgID(newCm.getOrgID());
                value.setAspID(newCm.getAspID());
                value.setMasterID(newCm.getMasterID());
                value.setCreator(newCm.getCreator());

                LogUtil.debug("course",
                        "[CourseDAOImpl]update===========needApprove1=" +
                                value.getCourse().getNeedapprove());

                try
                {
                        HibernateDAO.update(value.getCourse());
                }
                catch (ULMSSysException se)
                {
                        se.printStackTrace();
                        throw new CourseSysException(se);
                }
        }

        /**
         * search the course according to the keywords.
         *
         * @param courseKey
         * @throws CourseSysException
         */
        public CourseListModel search(String courseKey) throws CourseSysException
        {
                int courseID;
                int masterID;
                String name;
                String courseCode;
                String masterName;
                String description;

                /*int orgID;
                  String type;
                  String key;
                  int creator;
                  int isCharge;
                  float charge;
                  int memberCharge;
                  String chargeInfo;
                  Date establishDate;
                  Date modifyDate;
                  String register;
                  int registerMode;
                  int guest;
                  String plan;
                  int catalogID;
                  int objectID;
                  int isSendMail;
                  int isTimeLimit;
                  Date regStartDate;
                  Date regEndDate;
                  boolean isPassword;
                  String password;
                  int lifeSort;
                  Date lifeStartDate;
                  Date lifeEndDate;
                  int status;
                  int maximumEnrollment;
                  int minimumEnrollment;
                  int period;
                  int credit;
                  int scoreType;
                  String scoreLimit;
                  int teachMode;
                  String logo;
                */
                int totalNumber;
                Connection conn = null;
                Statement stmt = null;
                ResultSet rs = null;

                CourseModel mas = null;
                CatalogModel cat = null;

                ArrayList courses = new ArrayList();
                CourseListModel courseList = new CourseListModel(courses);

                try
                {
                        conn = DBUtil.getConnection();
                        stmt = conn.createStatement();
                        courseKey= StringEscapeUtils.escapeSql(courseKey);
                        String sql = "select count(distinct CourseID) " +
                                "from C_MyCourse_View " + "where " + "CourseKey like '%" +
                                courseKey + "%' or " + "CourseName like '%" + courseKey +
                                "%' or " + "CourseCode like '%" + courseKey + "%'";
                        LogUtil.debug("course", "[CourseDAOImpl]  sql=" + sql);

                        stmt.execute(sql);

                        rs = stmt.getResultSet();
                        rs.next();
                        totalNumber = rs.getInt(1);

                        courseList.setTotalNumber(totalNumber);
                        sql = "select distinct CourseID,CourseName,CourseKey,MasterID,MasterName,CourseCode," +
                                "IsCharge,Charge,MemberCharge,ChargeInfo,CourseDescription," +
                                "RegisterMode,Creator,LifeEndDate,LifeStartDate," +
                                "LifeSort,IsOpenGuest," + "CourseStatus " +
                                "from C_MyCourse_View " + "where " + "CourseKey like '%" +
                                courseKey + "%' or " + "CourseName like '%" + courseKey +
                                "%' or " + "CourseCode like '%" + courseKey +
                                "%' order by CourseID";

                        LogUtil.debug("course", "[CourseDAOImpl]  sql=" + sql);
                        stmt.execute(sql);

                        rs = stmt.getResultSet();

                        while (rs.next())
                        {
                                courseID = rs.getInt("CourseID");
                                masterID = rs.getInt("MasterID");
                                name = rs.getString("CourseName");
                                courseCode = rs.getString("CourseCode");
                                masterName = ((masterID == 0) ? "&nbsp;"
                                        : rs.getString("MasterName"));
                                description = rs.getString("CourseDescription");
                                mas = new CourseModel(courseID, name, courseCode, masterName,
                                        description);
                                courses.add(mas);
                        }

                        LogUtil.debug("course", "[CourseDAOImpl]getTree over" + "\n");
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

                return courseList;
        }

        public CourseListModel search(String courseKey, int aspID, int orgID)
                throws CourseSysException
        {
                int courseID;
                int masterID;
                String name;
                String courseCode;
                String masterName;
                String key;
                String description;
                int totalNumber;
                Connection conn = null;
                Statement stmt = null;
                ResultSet rs = null;

                CourseModel mas = null;
                CatalogModel cat = null;
                String aspID_str = "";
                String orgID_str = "";

                if (aspID != 0)
                {
                        aspID_str += (" and aspID=" + aspID);
                }

                if (orgID != 0)
                {
                        orgID_str += (" and orgID=" + orgID);
                }

                ArrayList courses = new ArrayList();
                CourseListModel courseList = new CourseListModel(courses);

                try
                {
                        conn = DBUtil.getConnection();
                        stmt = conn.createStatement();
                         System.out.println("courseKey11 = " + courseKey);
                        courseKey= StringEscapeUtils.escapeSql(courseKey);
                         System.out.println("courseKey11 = " + courseKey);
                        String sql = "select count(distinct CourseID) " +
                                "from C_MyCourse_View " + "where " + "(CourseKey like '%" +
                                courseKey + "%' or " + "CourseName like '%" + courseKey +
                                "%' or " + "CourseCode like '%" + courseKey + "%') " +
                                aspID_str + orgID_str;
                        LogUtil.debug("course", "[CourseDAOImpl]  sql=" + sql);

                        stmt.execute(sql);

                        rs = stmt.getResultSet();
                        rs.next();
                        totalNumber = rs.getInt(1);

                        courseList.setTotalNumber(totalNumber);
                        sql = "select distinct CourseID,CourseName,CourseKey,MasterID,MasterName,CourseCode," +
                                "IsCharge,Charge,MemberCharge,ChargeInfo,CourseDescription," +
                                "RegisterMode,Creator,LifeEndDate,LifeStartDate," +
                                "LifeSort,IsOpenGuest," + "CourseStatus " +
                                "from C_MyCourse_View " + "where " + "(CourseKey like '%" +
                                courseKey + "%' or " + "CourseName like '%" + courseKey +
                                "%' or " + "CourseCode like '%" + courseKey + "%')" +
                                aspID_str + orgID_str + " order by CourseID";

                        LogUtil.debug("course", "[CourseDAOImpl]  sql=" + sql);
                        stmt.execute(sql);

                        rs = stmt.getResultSet();

                        while (rs.next())
                        {
                                courseID = rs.getInt("CourseID");
                                masterID = rs.getInt("MasterID");
                                name = rs.getString("CourseName");
                                courseCode = rs.getString("CourseCode");
                                courseCode = rs.getString("CourseCode");
                                key = rs.getString("CourseKey");
                                masterName = ((masterID == 0) ? "&nbsp;"
                                        : rs.getString("MasterName"));
                                description = rs.getString("CourseDescription");
                                mas = new CourseModel(courseID, name, courseCode, masterName,
                                        description);
                                mas.setKey(key);
                                courses.add(mas);
                        }

                        LogUtil.debug("course", "[CourseDAOImpl]getTree over" + "\n");
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

                courseList.setList(courses);

                return courseList;
        }

        public List searchCourse(String courseKey, int aspID, int orgID)
                throws CourseSysException
        {
                int courseID;
                int masterID;
                String name;
                String courseCode;
                String description;
                String keyword;
                Connection conn = null;
                Statement stmt = null;
                ResultSet rs = null;

                String aspID_str = "";
                String orgID_str = "";

                if (aspID != 0)
                {
                        aspID_str += (" and aspID=" + aspID);
                }

                if (orgID != 0)
                {
                        orgID_str += (" and orgID=" + orgID);
                }

                List courses = new ArrayList();

                try
                {
                        conn = DBUtil.getConnection();
                        stmt = conn.createStatement();
                        courseKey= StringEscapeUtils.escapeSql(courseKey);
                        String sql = "select *  from C_Course_tab " + "where " +
                                "(KeyWord like '%" + courseKey + "%' or " + "Name like '%" +
                                courseKey + "%' or " + "CourseCode like '%" + courseKey +
                                "%') " + aspID_str + orgID_str;
                        LogUtil.debug("course", "[CourseDAOImpl]  sql=" + sql);

                        stmt.execute(sql);

                        rs = stmt.getResultSet();
                        rs = stmt.getResultSet();

                        while (rs.next())
                        {
                                CourseModel mas = convertRs2CourseModel(rs);
                                courses.add(mas);
                        }

                        LogUtil.debug("course", "[CourseDAOImpl]getTree over" + "\n");
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

                return courses;
        }

        public int getAspTotalCourse(int aspID, int orgID, String startDates,
                                     String endDates) throws CourseSysException
        {
                int total = 0;

                Date startDate = DateTimeUtil.parseDate(startDates);
                Date endDate = DateTimeUtil.parseDate(endDates);

                LogUtil.info("course",
                        "[CourseDAOImpl]getAspTotalCourse--endDate0=" +
                                I18Util.FormatDateTime(endDate, Locale.CHINA));

                com.ulearning.ulms.core.util.Calendar calendar = new com.ulearning.ulms.core.util.Calendar(endDate);
                endDate = calendar.nextDay();

                Session session = null;
                String aspID_str = "";
                String orgID_str = "";

                if (aspID != 0)
                {
                        aspID_str += (" and aspID=" + aspID);
                }

                if (orgID != 0)
                {
                        orgID_str += (" and orgID=" + orgID);
                }

                String hql = "select count(*)  from Course where " +
                        " establishdate >= :beginDate" + " and establishdate <= :endDate" +
                        aspID_str + orgID_str;

                LogUtil.info("course",
                        "[CourseDAOImpl]getAspTotalCourse--startDate=" +
                                I18Util.FormatDateTime(startDate, Locale.CHINA));
                LogUtil.info("course",
                        "[CourseDAOImpl]getAspTotalCourse--endDate=" +
                                I18Util.FormatDateTime(endDate, Locale.CHINA));

                LogUtil.info("course", "[CourseDAOImpl]getAspTotalCourse--hql=" + hql);

                try
                {
                        session = HibernateUtil.getSession();

                        Query query = session.createQuery(hql);
                        query.setParameter("beginDate", startDate);
                        query.setParameter("endDate", endDate);

                        Iterator it = query.iterate();

                        if ((it != null) && it.hasNext())
                        {
                                Integer amount = (Integer) it.next();
                                total = amount.intValue();
                        }

                        session.flush();
                        session.connection().commit();
                }
                catch (Exception e)
                {
                        e.printStackTrace();
                }
                finally
                {
                        try
                        {
                                HibernateUtil.releaseSession(session);
                        }
                        catch (HibernateException he)
                        {
                                he.printStackTrace();
                        }
                }

                return total;
        }

        public int getAspTotalCourse(int aspID, int orgID) throws CourseSysException
        {
                int total = 0;

                String aspID_str = "";
                String orgID_str = "";

                if (aspID != 0)
                {
                        aspID_str += (" and aspID=" + aspID);
                }

                if (orgID != 0)
                {
                        orgID_str += (" and orgID=" + orgID);
                }

                String hql = "select count(*)  from Course where 1=1 " + aspID_str + orgID_str;

                LogUtil.info("course", "[CourseDAOImpl]getAspTotalCourse--hql=" + hql);

                try
                {
                        total = HibernateDAO.count(hql);
                }
                catch (Exception e)
                {
                        e.printStackTrace();
                }
                return total;
        }

        public List getAspCourseInfo(int aspID, int orgID, String startDates,
                                     String endDates) throws CourseSysException
        {
                Date startDate = DateTimeUtil.parseDate(startDates);
                Date endDate = DateTimeUtil.parseDate(endDates);

                LogUtil.info("course",
                        "[CourseDAOImpl]getAspCourseInfo--endDate0=" +
                                I18Util.FormatDateTime(endDate, Locale.CHINA));

                com.ulearning.ulms.core.util.Calendar calendar = new com.ulearning.ulms.core.util.Calendar(endDate);
                endDate = calendar.nextDay();

                List courseList = new ArrayList();
                List list = new ArrayList();
                Session session = null;
                String aspID_str = "";
                String orgID_str = "";

                if (aspID != 0)
                {
                        aspID_str += (" and aspID=" + aspID);
                }

                if (orgID != 0)
                {
                        orgID_str += (" and orgID=" + orgID);
                }

                String hql = "from Course where courseid>0 " + aspID_str + orgID_str +
                        " and establishdate >= :beginDate" +
                        " and establishdate <= :endDate";

                LogUtil.info("course",
                        "[CourseDAOImpl]getAspCourseInfo--startDate=" +
                                I18Util.FormatDateTime(startDate, Locale.CHINA));
                LogUtil.info("course",
                        "[CourseDAOImpl]getAspCourseInfo--endDate=" +
                                I18Util.FormatDateTime(endDate, Locale.CHINA));
                LogUtil.info("course", "[CourseDAOImpl]getAspCourseInfo--hql=" + hql);

                try
                {
                        session = HibernateUtil.getSession();

                        Query query = session.createQuery(hql);
                        query.setParameter("beginDate", startDate);
                        query.setParameter("endDate", endDate);
                        courseList = query.list();
                        list = getCourseModelList(courseList);
                        session.flush();
                        session.connection().commit();
                }
                catch (Exception e)
                {
                        e.printStackTrace();
                }
                finally
                {
                        try
                        {
                                HibernateUtil.releaseSession(session);
                        }
                        catch (HibernateException he)
                        {
                                he.printStackTrace();
                        }
                }

                return list;
        }

        public List getCatalogList(int parentID, int type, int aspID, int orgID)
                throws CourseSysException
        {
                Connection dbConnection = null;
                Statement stmt = null;
                ResultSet rs = null;
                ArrayList catalogs = new ArrayList();
                String typeStr = "";

                if (type != -1)
                {
                        typeStr = " And type = '" + type + "' ";
                }
                else
                {
                        typeStr = " and type = '" + CourseKeys.CATALOG_COURSE_TYPE + "' ";
                }

                String aspID_str = "";
                String orgID_str = "";

                if (aspID != 0)
                {
                        aspID_str += (" and aspid=" + aspID);
                }

                //if (orgID != 0) orgID_str += " and orgid=" + orgID;
                String strSql = "SELECT CatalogID,Name,Description,defaultCourseIcon,icon FROM C_CATALOG_TAB WHERE catalogID!=0 " +
                        typeStr + "  and parentID=" + parentID + orgID_str +
                        " order by CatalogID";

                try
                {
                        dbConnection = DBUtil.getConnection();
                        stmt = dbConnection.createStatement();
                        rs = stmt.executeQuery(strSql);

                        CatalogModel cat = null;
                        String name = "";
                        String description = "";

                        while ((rs != null) && rs.next())
                        {
                                name = rs.getString("Name");
                                description = rs.getString("Description");
                                cat = new CatalogModel(rs.getInt("CatalogID"), name, description,
                                        StringUtils.trimToEmpty(rs.getString("icon")),StringUtils.trimToEmpty(rs.getString("defaultCourseIcon")));
                                catalogs.add(cat);
                        }
                }
                catch (ULMSSysException ese)
                {
                        throw new CourseSysException(
                                "SQLException while query total course in a ASP; aspID = " +
                                        aspID + " :\n" + ese);
                }
                catch (SQLException sqle)
                {
                        throw new CourseSysException(
                                "SQLException while query total course in a ASP; aspID = " +
                                        aspID + " :\n" + sqle);
                }
                finally
                {
                        closeResultSet(rs);
                        closeStatement(stmt);
                        closeConnection(dbConnection);
                }

                return catalogs;
        }

        public List getCourseInfo(String CourseName, String[] catalogIDs,
                                  String startDates, String endDates, int aspID, int orgID)
                throws CourseSysException
        {
                Date startDate = DateTimeUtil.parseDate(startDates);
                Date endDate = DateTimeUtil.parseDate(endDates);

                LogUtil.info("course",
                        "[CourseDAOImpl]getCourseInfo--endDate0=" +
                                I18Util.FormatDateTime(endDate, Locale.CHINA));

                com.ulearning.ulms.core.util.Calendar calendar = new com.ulearning.ulms.core.util.Calendar(endDate);
                endDate = calendar.nextDay();

                List courseList = new ArrayList();
                List list = new ArrayList();
                Session session = null;

                String catalogIDString = "";

                if (catalogIDs != null)
                {
                        for (int i = 0; i < catalogIDs.length; i++)
                        {
                                if (i != 0)
                                {
                                        catalogIDString += ("," + catalogIDs[i]);
                                }
                                else
                                {
                                        catalogIDString += (" and catalogid in ( " + catalogIDs[i]);
                                }
                        }

                        catalogIDString += " )";
                }

                String nameString = "";

                if (!CourseName.equals(""))
                {
                        CourseName= StringEscapeUtils.escapeSql(CourseName);
                        nameString = " name like '%" + CourseName + "%' and ";
                }

                String aspID_str = "";
                String orgID_str = "";

                if (aspID != 0)
                {
                        aspID_str += (" and aspid=" + aspID);
                }

                if (orgID != 0)
                {
                        orgID_str += (" and orgid=" + orgID);
                }

                String hql = " from Course where " + nameString +
                        " establishdate >= :beginDate" + " and establishdate <= :endDate" +
                        aspID_str + orgID_str + catalogIDString;

                LogUtil.info("course",
                        "[CourseDAOImpl]getCourseInfo--startDate=" +
                                I18Util.FormatDateTime(startDate, Locale.CHINA));
                LogUtil.info("course",
                        "[CourseDAOImpl]getCourseInfo--endDate=" +
                                I18Util.FormatDateTime(endDate, Locale.CHINA));
                LogUtil.info("course", "[CourseDAOImpl]getCourseInfo--hql=" + hql);

                try
                {
                        session = HibernateUtil.getSession();

                        Query query = session.createQuery(hql);
                        query.setParameter("beginDate", startDate);
                        query.setParameter("endDate", endDate);
                        courseList = query.list();
                        list = getCourseModelList(courseList);
                        session.flush();
                        session.connection().commit();
                }
                catch (Exception e)
                {
                        e.printStackTrace();
                }
                finally
                {
                        try
                        {
                                if (session != null)
                                {
                                        HibernateUtil.releaseSession(session);
                                }
                        }
                        catch (HibernateException he)
                        {
                                he.printStackTrace();
                        }
                }

                return list;
        }

        public CourseCurrentStatus getCourseNowStatus(int relationID, int type)
                throws CourseSysException
        {
                CourseCurrentStatus ccs = new CourseCurrentStatus();
                CourseUserWebImpl impl = new CourseUserWebImpl();
                int year = 0;
                int month = 0;
                int day = 0;

                Date currentDate = new Date();
                String temp = DateTimeUtil.FormatDateToWeb1(currentDate);
                LogUtil.info("course", "[CourseDAOImpl]getCourseNowStatus--temp=" +
                        temp);

                List timeList = StringUtil.split(temp, "-");

                if ((timeList != null) && (timeList.size() == 3))
                {
                        year = Integer.parseInt((String) timeList.get(0));
                        month = Integer.parseInt((String) timeList.get(1));
                        day = Integer.parseInt((String) timeList.get(2));
                }

                Date currentDateStartTime = DateTimeUtil.toDate(month, day, year, 0, 0,
                        0);
                Date currentDateEndTime = DateTimeUtil.toDate(month, day, year, 23, 59,
                        59);

                int currentStudent = impl.getTotalNumberByCourseStudent(relationID, type);
                int allMaxStudent = 0;
                int isCharge = 0;
                int allowFreeReg = 0;
                int needApprove = 0;
                String establishDate = null;
                Date regStartDate = null;
                Date regEndDate = null;
                Date lifeStartDate = null;
                Date lifeEndDate = null;
                int charge = 0;
                String enrollPassword = "";
                int lifeSort = 0;
                int lifeTime = 0;
                int guest = 0;
                String strSql = "";
                ResultSet rs = null;
                Connection dbConnection = null;
                Statement stmt = null;

                try
                {
                        // if (type == SecurityConstants.USER_COURSE_RELATION)
                        {
                                strSql = "select * from C_Course_Tab where CourseID = " +
                                        relationID;
                                dbConnection = DBUtil.getConnection();
                                stmt = dbConnection.createStatement();
                                rs = stmt.executeQuery(strSql);

                                if ((rs != null) && rs.next())
                                {
                                        allMaxStudent = rs.getInt("MaximumEnrollment");
                                        isCharge = rs.getInt("IsCharge");
                                        allowFreeReg = rs.getInt("allowFreedomReg");
                                        needApprove = rs.getInt("needApprove");
                                        regStartDate = rs.getDate("RegStartDate");
                                        regEndDate = rs.getDate("RegEndDate");
                                        lifeSort = rs.getInt("LifeSort");
                                        lifeStartDate = rs.getDate("LifeStartDate");
                                        lifeEndDate = rs.getDate("LifeEndDate");
                                        lifeTime = rs.getInt("LifeTime");
                                        establishDate = rs.getString("EstablishDate");
                                        guest = rs.getInt("Guest");
                                        charge = rs.getInt("Charge");
                                        enrollPassword = rs.getString("Password");
                                }

                                //can add student still
                                ccs.setNowStudentNum(currentStudent);

                                if (allMaxStudent <= 0)
                                {
                                        ccs.setMaxEnrollStatus(true);
                                }
                                else
                                {
                                        if (currentStudent < allMaxStudent)
                                        {
                                                ccs.setMaxEnrollStatus(true);
                                        }
                                }

                                //whether shall to charge
                                if (isCharge == 1)
                                {
                                        ccs.setFeeStatus(true);
                                        ccs.setCharge(charge);
                                }

                                //whether can Reg free
                                if (allowFreeReg == 1)
                                {
                                        if (regStartDate == null)
                                        {
                                                if (regEndDate == null)
                                                {
                                                        ccs.setEnrollStatus(true);
                                                }
                                                else if (currentDate.before(regEndDate))
                                                {
                                                        ccs.setEnrollStatus(true);
                                                }
                                        }
                                        else
                                        {
                                                if ((regEndDate == null) &&
                                                        currentDateStartTime.after(regStartDate))
                                                {
                                                        ccs.setEnrollStatus(true);
                                                }
                                                else if (currentDateStartTime.after(regStartDate) &&
                                                        currentDateEndTime.before(regEndDate))
                                                {
                                                        ccs.setEnrollStatus(true);
                                                }
                                        }

                                        ccs.setEnrollPassword(enrollPassword);
                                }

                                //whether shall to approve
                                if (needApprove == 1)
                                {
                                        ccs.setApproveStatus(true);
                                }

                                //whether can brower with role of guest
                                if (guest == 1)
                                {
                                        ccs.setBrowseStatus(true);
                                }

                                if (lifeSort == 1)
                                {
                                        ccs.setLifeStatus(true);
                                }
                                else if (lifeSort == 2)
                                {
                                        if (lifeStartDate == null)
                                        {
                                                if (lifeEndDate == null)
                                                {
                                                        ccs.setLifeStatus(true);
                                                }
                                                else if (currentDateEndTime.before(lifeEndDate))
                                                {
                                                        ccs.setLifeStatus(true);
                                                }
                                        }
                                        else
                                        {
                                                if ((lifeEndDate == null) &&
                                                        currentDateStartTime.after(lifeStartDate))
                                                {
                                                        ccs.setLifeStatus(true);
                                                }
                                                else if (currentDateStartTime.after(lifeStartDate) &&
                                                        currentDateEndTime.before(lifeEndDate))
                                                {
                                                        ccs.setLifeStatus(true);
                                                }
                                        }
                                }
                                else if (lifeSort == 3)
                                {
                                        if (lifeTime == 0)
                                        {
                                                ccs.setLifeStatus(true);
                                        }
                                        else
                                        {
                                                SimpleDateFormat objFormat = new SimpleDateFormat(
                                                        "yyyy-mm-dd");

                                                try
                                                {
                                                        String newDate_str = Calendar.addDate(establishDate.substring(
                                                                0, 10), "-", lifeTime);
                                                        Date newDate = objFormat.parse(newDate_str);

                                                        if (currentDateEndTime.before(newDate))
                                                        {
                                                                ccs.setLifeStatus(true);
                                                        }
                                                }
                                                catch (ParseException e)
                                                {
                                                        ccs.setLifeStatus(false);
                                                }
                                                catch (Exception e)
                                                {
                                                        ccs.setLifeStatus(false);
                                                }
                                        }
                                }
                        }
                        //else if (type == SecurityConstants.USER_CERTIFICATE_RELATION)
                        {
                        }
                }
                catch (ULMSSysException esse)
                {
                        throw new CourseSysException(
                                "SQLException while query total course in  catalogIDs;  :\n" +
                                        esse);
                }
                catch (SQLException sqle)
                {
                }
                finally
                {
                        closeResultSet(rs);
                        closeStatement(stmt);
                        closeConnection(dbConnection);
                }

                return ccs;
        }

        public List getCourseListByParent(int parentID, int aspID, int orgID)
                throws CourseSysException
        {
                ArrayList courses = new ArrayList();
                Connection dbConnection = null;
                Statement stmt = null;
                ResultSet rs = null;
                String aspID_str = "";
                String orgID_str = "";

                if (aspID != 0)
                {
                        aspID_str += (" and aspid=" + aspID);
                }

                if (orgID != 0)
                {
                        orgID_str += (" and orgid=" + orgID);
                }

                String strSql = "SELECT * FROM C_COURSE_TAB WHERE CatalogID= " +
                        parentID + //" and type=" + CourseKeys.CATALOG_COURSE_TYPE +        modified by zengwj at 05-08-31 for type express 必修 or 选修
                        aspID_str + orgID_str + " order by CourseID desc";

                try
                {
                        dbConnection = DBUtil.getConnection();
                        stmt = dbConnection.createStatement();
                        rs = stmt.executeQuery(strSql);

                        while ((rs != null) && rs.next())
                        {
                                CourseModel cm = new CourseModel();
                                cm = convertRs2CourseModel(rs);
                                courses.add(cm);
                                System.out.println(cm.getName());
                        }
                }
                catch (ULMSSysException ese)
                {
                        throw new CourseSysException(
                                "SQLException while query total course in a ASP; aspID = " +
                                        aspID + " :\n" + ese);
                }
                catch (SQLException sqle)
                {
                        throw new CourseSysException(
                                "SQLException while query total course in a ASP; aspID = " +
                                        aspID + " :\n" + sqle);
                }
                finally
                {
                        closeResultSet(rs);
                        closeStatement(stmt);
                        closeConnection(dbConnection);
                }

                return courses;
        }

        public List getCourseListByTeach(int parentID, int aspID, int orgID, int teachmod, int status)
                throws CourseSysException
        {
                ArrayList courses = new ArrayList();
                Connection dbConnection = null;
                Statement stmt = null;
                ResultSet rs = null;
                String aspID_str = "";
                String orgID_str = "";

                if (aspID != 0)
                {
                        aspID_str += (" and aspid=" + aspID);
                }

                if (orgID != 0)
                {
                        orgID_str += (" and orgid=" + orgID);
                }

                String strSql = "SELECT * FROM C_COURSE_TAB WHERE TeachMode=" + teachmod + " and Status=" + status + " and  CatalogID= " +
                        parentID + //" and type=" + CourseKeys.CATALOG_COURSE_TYPE +        modified by zengwj at 05-08-31 for type express 必修 or 选修
                        aspID_str + orgID_str + " order by CourseID desc";

                try
                {
                        dbConnection = DBUtil.getConnection();
                        stmt = dbConnection.createStatement();
                        rs = stmt.executeQuery(strSql);

                        while ((rs != null) && rs.next())
                        {
                                CourseModel cm = new CourseModel();
                                cm = convertRs2CourseModel(rs);
                                courses.add(cm);
                                System.out.println(cm.getName());
                        }
                }
                catch (ULMSSysException ese)
                {
                        throw new CourseSysException(
                                "SQLException while query total course in a ASP; aspID = " +
                                        aspID + " :\n" + ese);
                }
                catch (SQLException sqle)
                {
                        throw new CourseSysException(
                                "SQLException while query total course in a ASP; aspID = " +
                                        aspID + " :\n" + sqle);
                }
                finally
                {
                        closeResultSet(rs);
                        closeStatement(stmt);
                        closeConnection(dbConnection);
                }

                return courses;
        }

        /**
         * 返回某一门课程的所有信息
         *
         * @param courseID
         * @return
         * @throws CourseSysException
         */
        public List getCourseFormByParent(int courseID) throws CourseSysException
        {
                ArrayList courses = new ArrayList();
                Connection dbConnection = null;
                Statement stmt = null;
                ResultSet rs = null;
                String strSql = "SELECT * FROM C_COURSE_TAB WHERE CourseID= " +
                        courseID + " order by CourseID desc";

                try
                {
                        dbConnection = DBUtil.getConnection();
                        stmt = dbConnection.createStatement();
                        rs = stmt.executeQuery(strSql);

                        while ((rs != null) && rs.next())
                        {
                                CourseModel cm = new CourseModel();
                                cm = convertRs2CourseModel(rs);
                                courses.add(cm);
                                System.out.println(cm.getName());
                        }
                }
                catch (ULMSSysException ese)
                {
                        throw new CourseSysException(
                                "SQLException while query total course in a ASP; courseID = " +
                                        courseID + " :\n" + ese);
                }
                catch (SQLException sqle)
                {
                        throw new CourseSysException(
                                "SQLException while query total course in a ASP; courseID = " +
                                        courseID + " :\n" + sqle);
                }
                finally
                {
                        closeResultSet(rs);
                        closeStatement(stmt);
                        closeConnection(dbConnection);
                }

                return courses;
        }

        /**
         * 返回某一门课程已参加的人数
         *
         * @param courseID
         * @return
         * @throws CourseSysException
         */
        public int getCourseFormCount(int courseID) throws CourseSysException
        {
                ArrayList courses = new ArrayList();
                Connection dbConnection = null;
                Statement stmt = null;
                ResultSet rs = null;
                String strSql = "SELECT * FROM SEC_USERROLE_TAB WHERE RelationID= " +
                        courseID + " order by RelationID desc";
                int CourseCount = 0;

                try
                {
                        dbConnection = DBUtil.getConnection();
                        stmt = dbConnection.createStatement();
                        rs = stmt.executeQuery(strSql);

                        while ((rs != null) && rs.next())
                        {
                                CourseCount++;
                        }
                }
                catch (ULMSSysException ese)
                {
                        throw new CourseSysException(
                                "SQLException while query total course in a ASP; courseID = " +
                                        courseID + " :\n" + ese);
                }
                catch (SQLException sqle)
                {
                        throw new CourseSysException(
                                "SQLException while query total course in a ASP; courseID = " +
                                        courseID + " :\n" + sqle);
                }
                finally
                {
                        closeResultSet(rs);
                        closeStatement(stmt);
                        closeConnection(dbConnection);
                }

                return CourseCount;
        }

        public List getCatalogListByParent(int parentID, int type, int aspID,
                                           int orgID) throws CourseSysException
        {
                ArrayList catalogs = new ArrayList();
                Connection dbConnection = null;
                Statement stmt = null;
                ResultSet rs = null;
                String aspID_str = "";
                String orgID_str = "";

                if (aspID != 0)
                {
                        aspID_str += (" and aspid=" + aspID);
                }

                //if (orgID != 0) orgID_str += " and orgid=" + orgID;
                String strSql = "SELECT CatalogID,Name,Description,defaultCourseIcon,icon FROM C_CATALOG_TAB WHERE catalogID!=0 " +
                        " and type='" + type + orgID_str + "' and parentID=" + parentID +
                        " order by CatalogID";

                try
                {
                        dbConnection = DBUtil.getConnection();
                        stmt = dbConnection.createStatement();
                        rs = stmt.executeQuery(strSql);

                        CatalogModel cat = null;
                        String name = "";
                        String description = "";

                        while ((rs != null) && rs.next())
                        {
                                name = rs.getString("Name");
                                description = rs.getString("Description");
                                cat = new CatalogModel(rs.getInt("CatalogID"), name, description,
                                        StringUtils.trimToEmpty(rs.getString("icon")),StringUtils.trimToEmpty(rs.getString("defaultCourseIcon")));
                                catalogs.add(cat);
                        }
                }
                catch (ULMSSysException ese)
                {
                        throw new CourseSysException(
                                "SQLException while query total course in a ASP; aspID = " +
                                        aspID + " :\n" + ese);
                }
                catch (SQLException sqle)
                {
                        throw new CourseSysException(
                                "SQLException while query total course in a ASP; aspID = " +
                                        aspID + " :\n" + sqle);
                }
                finally
                {
                        closeResultSet(rs);
                        closeStatement(stmt);
                        closeConnection(dbConnection);
                }

                return catalogs;
        }

        /*
        * 判断课程代码是否存在
        */
        public boolean isExistCourseCode(String courseCode, int courseID,
                                         int aspID, int orgID) throws CourseSysException
        {
                Connection conn = null;
                Statement stmt = null;
                ResultSet rs = null;
                boolean isExist = false;

                try
                {
                        conn = DBUtil.getConnection();
                        stmt = conn.createStatement();

                        String aspID_str = "";
                        String orgID_str = "";

                        if (aspID != 0)
                        {
                                aspID_str += (" and aspid=" + aspID);
                        }

                        if (orgID != 0)
                        {
                                orgID_str += (" and orgid=" + orgID);
                        }

                        String queryStr1 = "select courseCode from C_Course_Tab" +
                                " WHERE courseCode='" + courseCode + "'" + aspID_str +
                                orgID_str;

                        if (courseID != -1)
                        {
                                queryStr1 += (" and courseID!=" + courseID);
                        }

                        DebugUtil.print("[CourseDAOImpl] ==========queryStr1 = " +
                                queryStr1);

                        rs = stmt.executeQuery(queryStr1);

                        if (rs.next())
                        {
                                isExist = true;
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

                return isExist;
        }

        /*
        * 判断课程生命周期
        */
        public boolean getCourseLifeStatus(int courseID) throws CourseSysException
        {
                Connection conn = null;
                Statement stmt = null;
                ResultSet rs = null;
                CourseModel courseModel = null;

                try
                {
                        conn = DBUtil.getConnection();
                        stmt = conn.createStatement();

                        String sql = "select * from c_course_tab where CourseID = " +
                                courseID;
                        rs = stmt.executeQuery(sql);

                        while (rs.next())
                        {
                                courseModel = convertRs2CourseModel(rs);
                        }

                        if (courseModel == null)
                        {
                                return false;
                        }

                        if (courseModel.getLifeSort() == 1)
                        {
                                return true;
                        }
                        else if (courseModel.getLifeSort() == 2)
                        {
                                Date now = new Date();

                                if (now.before(courseModel.getLifeStartDate()) ||
                                        now.after(courseModel.getLifeEndDate()))
                                {
                                        return false;
                                }
                                else
                                {
                                        return true;
                                }
                        }
                        else if (courseModel.getLifeSort() == 3)
                        {
                                java.util.Calendar calEndDate = java.util.Calendar.getInstance();
                                calEndDate.setTime(courseModel.getEstablishDate());
                                calEndDate.add(java.util.Calendar.DAY_OF_MONTH,
                                        courseModel.getLifeTime());

                                if (!java.util.Calendar.getInstance().after(calEndDate))
                                {
                                        return true;
                                }
                                else
                                {
                                        return false;
                                }
                        }
                }
                catch (SQLException e)
                {
                        e.printStackTrace();
                }
                finally
                {
                        closeResultSet(rs);
                        closeStatement(stmt);
                        closeConnection(conn);
                }

                return true;
        }

        public List getCourseTree(int aspID, int orgID) throws CourseSysException
        {
                List courseList = new ArrayList();
                CourseModel cm = null;
                String hql = " from Course where courseid>0 ";

                String aspID_str = "";
                String orgID_str = "";

                if (aspID != 0)
                {
                        aspID_str += (" and aspid=" + aspID);
                }

                if (orgID != 0)
                {
                        orgID_str += (" and orgid=" + orgID);
                }

                hql += (aspID_str + orgID_str);
                hql += " order by courseID desc ";

                try
                {
                        LogUtil.debug("CourseDAOImpl", "hql ======================" + hql);

                        List tempList = HibernateDAO.find(hql);
                        Course course = null;

                        for (int i = 0; (tempList != null) && (i < tempList.size()); i++)
                        {
                                course = (Course) tempList.get(i);
                                cm = new CourseModel(course);
                                courseList.add(cm);
                        }
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new CourseSysException("CourseDAOImpl" + e);
                }

                return courseList;
        }

        /**
         * @param aspID
         * @param orgID
         * @param courseName
         * @return
         * @throws CourseSysException
         */
        public List getCourseInfo(int aspID, int orgID, String courseName)
                throws CourseSysException
        {
                List courseList = new ArrayList();
                CourseModel cm = null;
                String hql = " from Course where courseid>0 ";

                String aspID_str = "";
                String orgID_str = "";
                String cname_str = "";

                if (aspID != 0)
                {
                        aspID_str += (" and aspid=" + aspID);
                }

                if (orgID != 0)
                {
                        orgID_str += (" and orgid=" + orgID);
                }

                if (null != courseName)
                {
                        courseName= StringEscapeUtils.escapeSql(courseName);
                        cname_str += (" and name like '%" + courseName + "%'");
                }

                hql += (aspID_str + orgID_str + cname_str);
                hql += " order by courseID desc ";

                try
                {
                        LogUtil.debug("CourseDAOImpl", "hql ======================" + hql);

                        List tempList = HibernateDAO.find(hql);
                        Course course = null;

                        for (int i = 0; (tempList != null) && (i < tempList.size()); i++)
                        {
                                course = (Course) tempList.get(i);
                                cm = new CourseModel(course);
                                courseList.add(cm);
                        }
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new CourseSysException("CourseDAOImpl" + e);
                }

                return courseList;
        }

        protected Connection getJieFoConnection() throws CourseSysException
        {
                Connection dbConnection = null;

                try
                {
                        dbConnection = DBUtil.getJieFoJDBCConnection();
                }
                catch (Exception se)
                {
                        throw new CourseSysException("SQL Exception while getting " +
                                "DB connection : \n" + se);
                }

                return dbConnection;
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

        public List getCourseModelList(List courseList)
        {
                List list = new ArrayList();

                for (int i = 0; i < courseList.size(); i++)
                {
                        Course course = new Course();
                        course = (Course) courseList.get(i);

                        CourseModel courseModel = new CourseModel(course);
                        list.add(courseModel);
                }

                return list;
        }

        private CourseModel convertRs2CourseModel(ResultSet rs)
        {
                CourseModel cm = new CourseModel();
                int rsIndex = 1;

                try
                {
                        cm.setCourseID(rs.getInt(rsIndex++));
                        cm.setMasterID(rs.getInt(rsIndex++));
                        cm.setCourseCode(rs.getString(rsIndex++));
                        cm.setName(rs.getString(rsIndex++));
                        cm.setDescription(rs.getString(rsIndex++));
                        cm.setOrgID(rs.getInt(rsIndex++));
                        cm.setAspID(rs.getInt(rsIndex++));
                        cm.setType(rs.getString(rsIndex++));
                        cm.setKey(rs.getString(rsIndex++));
                        cm.setCreator(rs.getInt(rsIndex++));
                        cm.setIsCharge(rs.getInt(rsIndex++));
                        cm.setCharge(rs.getFloat(rsIndex++));
                        cm.setMemberCharge(rs.getFloat(rsIndex++));
                        cm.setChargeInfo(rs.getString(rsIndex++));
                        cm.setEstablishDate(rs.getDate(rsIndex++));
                        cm.setModifyDate(rs.getDate(rsIndex++));
                        cm.setAllowFreedomReg(rs.getInt(rsIndex++));
                        cm.setNeedApprove(rs.getInt(rsIndex++));
                        cm.setRegisterMode(rs.getInt(rsIndex++));
                        cm.setGuest(rs.getInt(rsIndex++));
                        cm.setCatalogID(rs.getInt(rsIndex++));
                        cm.setObjectID(rs.getInt(rsIndex++));
                        cm.setIsSendMail(rs.getInt(rsIndex++));
                        cm.setIsTimeLimit(rs.getInt(rsIndex++));
                        cm.setRegStartDate(rs.getDate(rsIndex++));
                        cm.setRegEndDate(rs.getDate(rsIndex++));
                        cm.setIsPassword(rs.getInt(rsIndex++));
                        cm.setPassword(rs.getString(rsIndex++));
                        cm.setLifeSort(rs.getInt(rsIndex++));
                        cm.setLifeStartDate(rs.getDate(rsIndex++));
                        cm.setLifeEndDate(rs.getDate(rsIndex++));
                        cm.setLifeTime(rs.getInt(rsIndex++));
                        cm.setStatus(rs.getInt(rsIndex++));
                        cm.setMaximumEnrollment(rs.getInt(rsIndex++));
                        cm.setMinimumEnrollment(rs.getInt(rsIndex++));
                        cm.setPeriod(rs.getInt(rsIndex++));
                        cm.setCredit(rs.getFloat(rsIndex++));
                        cm.setScoreType(rs.getInt(rsIndex++));
                        cm.setScoreLimit(rs.getString(rsIndex++));
                        cm.setTeachMode(rs.getInt(rsIndex++));
                        cm.setLogo(rs.getString(rsIndex++));
                }
                catch (SQLException sql)
                {
                        sql.printStackTrace();
                }

                return cm;
        }

        public List getSubCatalog(int catalog) throws CourseSysException
        {
                return null; //To change body of implemented methods use File | Settings | File Templates.
        }

        public boolean getIsSubCatalog(int catalog) throws CourseSysException
        {
                return false; //To change body of implemented methods use File | Settings | File Templates.
        }

        /*
         ******************************************************* tuning ***************************************************************
         */

        /**
         * 返回Catalog
         *
         * @param id
         * @return
         * @throws CourseSysException
         */
        public Catalog readCatalog(int id)
                throws CourseSysException
        {
                return (Catalog) HibernateDAO.load(Catalog.class,
                        new Integer(id));
        }

        /**
         * 搜索课程
         *
         * @param aspID
         * @param orgID
         * @param orgIDs  可以搜索多个学校
         * @param catalogID 包含其下面的子目录
         * @param type
         * @param creator
         * @param teachmode
         * @param keyword
         * @param pageNo
         * @param pageSize
         * @return
         * @throws CourseSysException
         */
        public PagerList search(int aspID, int orgID, int[] orgIDs,int catalogID, String type, int creator, int teachmode,
                                String ischarge,String allowfreedomreg,String needapprove,String registermode,String guest,
                                String status,String isCommend,
                                String keyword, int pageNo, int pageSize)
                throws CourseSysException
        {
                logger.info("search start!");
                int cruentIndex = pageNo * pageSize;
                PagerList pl = new PagerList();
                List list = null;
                Session session = null;
                try
                {
                        session = HibernateUtil.getSession();

                        //取数据
                        String hql = "from Course model";

                        String tmpCondition = "";
                        if (aspID != -1)
                        {
                                tmpCondition += " and  aspid=" + aspID;

                        }
                        if (orgID != -1)
                        {
                                tmpCondition += " and  orgid=" + orgID;

                        }

                        if (orgIDs != null && orgIDs.length>0)
                        {
                                String orgCondition = "";
                                for (int i = 0; i < orgIDs.length; i++)
                                {
                                        int orgID_tmp = orgIDs[i];
                                        orgCondition += " or   orgid=" + orgID_tmp;
                                }

                                if (!orgCondition.equals(""))
                                {
                                        orgCondition = orgCondition.substring(5);
                                        tmpCondition += " and (" + orgCondition + ")";
                                }
                        }

                        if (catalogID != -1 && catalogID != 0)
                        {
                                List catalogs = null;
                                if(Config.isXLNProject())
                                {
                                        catalogs=getCatalogChildrenID(-1, -1, catalogID, true);
                                }
                                else
                                {
                                        catalogs=getCatalogChildrenID(aspID, -1, catalogID, true);
                                }
                                String catCondition = "catalogid=" + catalogID;
                                for (int i = 0; i < catalogs.size(); i++)
                                {
                                        Integer integer = (Integer) catalogs.get(i);
                                        catCondition += "  or  catalogid=" + integer;
                                }
                                tmpCondition += " and (" + catCondition + ")";
                        }

                        if (!StringUtils.isBlank(type))
                        {
                                tmpCondition += " and  type='" + type + "'";

                        }
                        if (creator != -1)
                        {
                                tmpCondition += " and  creator=" + creator;

                        }
                        if (teachmode!=-1)
                        {
                                tmpCondition += " and  teachmode=" + teachmode;

                        }

                        if (!StringUtils.isBlank(ischarge))
                        {
                                tmpCondition += " and  ischarge='" + ischarge + "'";

                        }
                        if (!StringUtils.isBlank(allowfreedomreg))
                        {
                                tmpCondition += " and  allowfreedomreg='" + allowfreedomreg + "'";

                        }
                        if (!StringUtils.isBlank(needapprove))
                        {
                                tmpCondition += " and  needapprove='" + needapprove + "'";

                        }
                        if (!StringUtils.isBlank(registermode))
                        {
                                tmpCondition += " and  registermode='" + registermode + "'";

                        }
                        if (!StringUtils.isBlank(guest))
                        {
                                tmpCondition += " and  guest='" + guest + "'";

                        }
                        if (!StringUtils.isBlank(status))
                        {
                                tmpCondition += " and  status='" + status + "'";

                        }
                        if (!StringUtils.isBlank(isCommend))
                        {
                                tmpCondition += " and  isCommend='" + isCommend + "'";

                        }

                        if (!StringUtils.isBlank(keyword))
                        {
                                tmpCondition += " and  (coursecode like :keyword or model.key like :keyword" +
                                        " or name like :keyword)";
                                keyword = "%" + keyword + "%";
                        }

                        if (!tmpCondition.equals(""))
                        {
                                tmpCondition = tmpCondition.substring(5);
                                tmpCondition = " where " + tmpCondition;
                        }
                        hql += tmpCondition;
                        logger.info("hql=" + (hql + " order by courseid desc"));
                        Query q = session.createQuery(hql + " order by courseid desc");
                        if (-1 != cruentIndex)
                        {
                                q.setFirstResult(cruentIndex);
                        }
                        if (-1 != pageSize)
                        {
                                q.setMaxResults(pageSize);
                        }
                        if (!StringUtils.isBlank(keyword))
                        {
                               /* q.setString("coursecode", keyword);
                                q.setString("key", keyword);
                                q.setString("name", keyword);*/
                                q.setString("keyword", keyword);
                        }
                        list = q.list();

                        int totalCount = 0;
                        //取总记录数
                        String hqlCount = "select count(*) " + hql;

                        q = session.createQuery(hqlCount);
                        if (!StringUtils.isBlank(keyword))
                        {
                                q.setString("keyword", keyword);
                        }
                        totalCount = ((Integer) q.uniqueResult()).intValue();

                        pl.setPageSize(pageSize);
                        pl.setPageNo(pageNo);
                        pl.setTotalCount(totalCount);
                        pl.setPagerList(list);
                }
                catch (Exception se)
                {
                        se.printStackTrace();
                }

                return pl;
        }

        /**
         * 取某目录下所有的子目录(CatalogID)
         *
         * @param aspID
         * @param orgID
         * @param catalogID
         * @param isIncludeSubCatalog 是否递归包含子目录下的内容
         * @return
         */
        public List getCatalogChildrenID(int aspID, int orgID, int catalogID, boolean isIncludeSubCatalog)
        {
                List result = new ArrayList();

                String hql = "from Catalog where parentID=" + catalogID;
                String tmpCondition = "";
                if (aspID != -1)
                {
                        tmpCondition += " and  aspID=" + aspID;

                }
                if (orgID != -1)
                {
                        tmpCondition += " and  orgID=" + orgID;

                }

                hql += tmpCondition;
                logger.info("hql=" + hql);

                List catalogs = HibernateDAO.find(hql);
                if (!catalogs.isEmpty())
                {
                        List tmp = null;
                        for (int i = 0; i < catalogs.size(); i++)
                        {
                                Catalog catalog = (Catalog) catalogs.get(i);
                                Integer integer = new Integer(catalog.getCatalogID());
                                result.add(integer);
                                if (isIncludeSubCatalog)
                                {
                                        result.addAll(getCatalogChildrenID(aspID, orgID, catalog.getCatalogID(),isIncludeSubCatalog));
                                        //todo:防止应为可能的数据错误，导致的死循环
                                }
                        }
                }
                return result;
        }

        /**
         * 取某目录下所有的子目录(Catalog)
         *
         * @param aspID
         * @param orgID
         * @param catalogID
         * @param isIncludeSubCatalog 是否递归包含子目录下的内容
         * @return
         */
        public List getCatalogChildren(int aspID, int orgID, int catalogID, boolean isIncludeSubCatalog)
        {
                List result = new ArrayList();

                String hql = "from Catalog where parentID=" + catalogID;
                String tmpCondition = "";
                if (aspID != -1)
                {
                        tmpCondition += " and  aspID=" + aspID;

                }
                if (orgID != -1)
                {
                        tmpCondition += " and  orgID=" + orgID;

                }

                hql += tmpCondition;
                logger.info("hql=" + hql);

                List catalogs = HibernateDAO.find(hql);
                if (!catalogs.isEmpty())
                {
                        for (int i = 0; i < catalogs.size(); i++)
                        {
                                Catalog catalog = (Catalog) catalogs.get(i);
                                result.add(catalog);
                                if (isIncludeSubCatalog)
                                {
                                        result.addAll(getCatalogChildren(aspID, orgID, catalog.getCatalogID(),isIncludeSubCatalog));
                                        //todo:防止应为可能的数据错误，导致的死循环
                                }
                        }
                }
                return result;
        }

}
