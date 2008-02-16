/**
 * HistoryProfileDAOImpl.java.
 * User: fengch  Date: 2004-5-26
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.common.dao;

import com.ulearning.ulms.common.exceptions.HistoryProfileSysException;
import com.ulearning.ulms.common.model.HistoryModel;
import com.ulearning.ulms.common.model.HistoryProfileListModel;
import com.ulearning.ulms.common.model.HistoryProfileModel;
import com.ulearning.ulms.core.exceptions.ULMSSysException;
import com.ulearning.ulms.core.util.Calendar;
import com.ulearning.ulms.core.util.I18Util;
import com.ulearning.ulms.core.util.StringUtil;
import com.ulearning.ulms.course.util.CourseKeys;
import com.ulearning.ulms.util.DBUtil;
import com.ulearning.ulms.util.HibernateDAO;
import com.ulearning.ulms.util.HibernateUtil;
import com.ulearning.ulms.util.log.LogUtil;

import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Query;
import net.sf.hibernate.Session;
import net.sf.hibernate.SessionFactory;

import java.sql.*;

import java.text.SimpleDateFormat;

import java.util.*;
import java.util.Date;

import javax.sql.DataSource;


public class HistoryProfileDAOImpl implements HistoryProfileDAO
{
        protected static SessionFactory sessionFactory = null;
        protected transient Connection dbConnection = null;
        protected transient DataSource datasource = null;

        /**
         * get  the user-relationed profile
         * type  :see SecurityConstants.
         *
         * @throws com.ulearning.ulms.common.exceptions.HistoryProfileSysException
         *
         */
        public HistoryProfileListModel get(int userID, int type,
                                           java.util.Date startDate, java.util.Date endDate)
                throws HistoryProfileSysException
        {
                LogUtil.info("common",
                        "[HistoryProfileDAOImpl]get--endDate0=" +
                                I18Util.FormatDateTime(endDate, Locale.CHINA));

                com.ulearning.ulms.core.util.Calendar calendar = new com.ulearning.ulms.core.util.Calendar(endDate);
                endDate = calendar.nextDay();

                LogUtil.info("common",
                        "[HistoryProfileDAOImpl]get--startDate=" +
                                I18Util.FormatDateTime(startDate, Locale.CHINA));
                LogUtil.info("common",
                        "[HistoryProfileDAOImpl]get--endDate=" +
                                I18Util.FormatDateTime(endDate, Locale.CHINA));

                String hql = " from HistoryModel where " + "  type='" + type +
                        "' and userID=" + userID + " and modifydate >= :startDate" +
                        " and modifydate <= :endDate" + " order by profileID";

                HistoryProfileListModel hplm = new HistoryProfileListModel();

                List list = new ArrayList();
                hplm.setLists(list);

                HistoryProfileModel hpm = null;

                Session session = null;

                try
                {
                        session = HibernateUtil.getSession();

                        Query query = session.createQuery(hql);
                        query.setParameter("startDate", startDate);
                        query.setParameter("endDate", endDate);

                        List l = query.list();
                        HistoryModel hm = null;

                        for (int i = 0; i < l.size(); i++)
                        {
                                hm = (HistoryModel) l.get(i);
                                hpm = new HistoryProfileModel(hm);
                                list.add(hpm);
                        }

                        session.flush();
                        session.connection().commit();
                }
                catch (Exception he)
                {
                        he.printStackTrace();
                        throw new HistoryProfileSysException(he);
                }
                finally
                {
                        try
                        {
                                HibernateUtil.releaseSession(session);
                        }
                        catch (HibernateException he)
                        {
                                throw new HistoryProfileSysException(he);
                        }
                }

                return hplm;
        }

        /**
         * @param userID
         * @param type
         * @param time
         * @param time2
         * @return
         * @throws HistoryProfileSysException
         */
        public HistoryProfileListModel getByShowConditon(int userID, int type,
                                                         String time, String time2) throws HistoryProfileSysException
        {
                List times = StringUtil.split(time, "-");
                System.out.println("time1--time2" + time + "--" + time2);

                java.util.Calendar calB = java.util.Calendar.getInstance();
                calB.set(java.util.Calendar.YEAR,
                        Integer.parseInt((String) times.get(0)));
                calB.set(java.util.Calendar.MONTH,
                        Integer.parseInt((String) times.get(1)) - 1);
                calB.set(java.util.Calendar.DAY_OF_MONTH,
                        Integer.parseInt((String) times.get(2)));
                calB.set(java.util.Calendar.HOUR_OF_DAY, 0);
                calB.set(java.util.Calendar.MINUTE, 0);
                calB.set(java.util.Calendar.SECOND, 0);

                Date startDate = calB.getTime();

                if (time2.length() > 1)
                {
                        times = StringUtil.split(time2, "-");
                }

                java.util.Calendar calE = java.util.Calendar.getInstance();
                calE.set(java.util.Calendar.YEAR,
                        Integer.parseInt((String) times.get(0)));
                calE.set(java.util.Calendar.MONTH,
                        Integer.parseInt((String) times.get(1)) - 1);
                calE.set(java.util.Calendar.DAY_OF_MONTH,
                        Integer.parseInt((String) times.get(2)));
                calE.set(java.util.Calendar.HOUR_OF_DAY, 23);
                calE.set(java.util.Calendar.MINUTE, 59);
                calE.set(java.util.Calendar.SECOND, 59);

                Date endDate = calE.getTime();

                LogUtil.info("common",
                        "[HistoryProfileDAOImpl]get--endDate0=" +
                                I18Util.FormatDateTime(endDate, Locale.CHINA));

                com.ulearning.ulms.core.util.Calendar calendar = new com.ulearning.ulms.core.util.Calendar(endDate);
                endDate = calendar.nextDay();

                LogUtil.info("common",
                        "[HistoryProfileDAOImpl]get--startDate=" +
                                I18Util.FormatDateTime(startDate, Locale.CHINA));
                LogUtil.info("common",
                        "[HistoryProfileDAOImpl]get--endDate=" +
                                I18Util.FormatDateTime(endDate, Locale.CHINA));

                String hql = " from HistoryModel where " + "  type='" + type +
                        "' and userID=" + userID + " and modifydate >= :startDate" +
                        " and modifydate <= :endDate" + " order by profileID";

                HistoryProfileListModel hplm = new HistoryProfileListModel();

                List list = new ArrayList();
                hplm.setLists(list);

                HistoryProfileModel hpm = null;

                Session session = null;

                try
                {
                        session = HibernateUtil.getSession();

                        Query query = session.createQuery(hql);
                        query.setParameter("startDate", startDate);
                        query.setParameter("endDate", endDate);

                        List l = query.list();
                        HistoryModel hm = null;

                        for (int i = 0; i < l.size(); i++)
                        {
                                hm = (HistoryModel) l.get(i);
                                hpm = new HistoryProfileModel(hm);
                                list.add(hpm);
                        }

                        session.flush();
                        session.connection().commit();
                }
                catch (Exception he)
                {
                        he.printStackTrace();
                        throw new HistoryProfileSysException(he);
                }
                finally
                {
                        try
                        {
                                HibernateUtil.releaseSession(session);
                        }
                        catch (HibernateException he)
                        {
                                throw new HistoryProfileSysException(he);
                        }
                }

                return hplm;
        }

        /**
         * get  the user-relation profiles
         * type
         * ==0:课程记录
         * ==1:证书记录
         * ==2:项目记录
         *
         * @throws com.ulearning.ulms.common.exceptions.HistoryProfileSysException
         *
         */
        public HistoryProfileListModel get(int userID, int type, int pageNo,
                                           int pageSize) throws HistoryProfileSysException
        {
                String hql = " from HistoryModel where " + "  type='" + type +
                        "' and userID=" + userID + " order by profileID";

                HistoryProfileListModel hplm = new HistoryProfileListModel();

                List list = new ArrayList();
                hplm.setLists(list);

                HistoryProfileModel hpm = null;

                Session session = null;

                try
                {
                        session = HibernateUtil.getSession();

                        List hmList = session.find(hql);

                        System.out.println(
                                "[HistoryProfileDAOImpl] get ****************** hmList.size=" +
                                        hmList.size());

                        HistoryModel hm = null;

                        if (hmList != null)
                        {
                                for (int i = 0; i < hmList.size(); i++)
                                {
                                        hm = (HistoryModel) hmList.get(i);
                                        System.out.println(
                                                "[HistoryProfileDAOImpl] get ******************getState= " +
                                                        hm.getState());
                                        hpm = new HistoryProfileModel(hm);
                                        list.add(hpm);
                                }
                        }
                }
                catch (HibernateException he)
                {
                        he.printStackTrace();
                        throw new HistoryProfileSysException(he);
                }
                finally
                {
                        try
                        {
                                HibernateUtil.releaseSession(session);
                        }
                        catch (HibernateException he)
                        {
                                throw new HistoryProfileSysException(he);
                        }
                }

                return hplm;
        }

        /**
         * 返回特定asp在特定日期（月份）的培训人次
         *
         * @param aspID
         * @param date
         * @return
         * @throws HistoryProfileSysException
         */
        public int getHistoryPersonalTimeReport(int aspID, Date date)
                throws HistoryProfileSysException
        {
                Connection conn = null;
                PreparedStatement ps = null;
                ResultSet rs = null;
                int personalTime = 0;
                Calendar cal = new Calendar(date);

                LogUtil.info("common",
                        "[HistoryProfileDAOImpl]getHistoryPersonalTimeReport--cal.getDayOfMonth()=" +
                                cal.getDayOfMonth());

                Date startDate = new Calendar(cal.getYear(), cal.getMonth(), 1).getDate();
                Date endDate = new Calendar(cal.getYear(), cal.getMonth() + 1, 1).getDate();
                LogUtil.info("common",
                        "[HistoryProfileDAOImpl]getHistoryPersonalTimeReport--startDate=" +
                                I18Util.FormatDateTime(startDate, Locale.CHINA));
                LogUtil.info("common",
                        "[HistoryProfileDAOImpl]getHistoryPersonalTimeReport--endDate=" +
                                I18Util.FormatDateTime(endDate, Locale.CHINA));

                String sql = "select count(h.userID) as personalTime " +
                        "from tm_history_tab h,u_user_tab u ,tm_org_tab o" +
                        " where h.userID=u.userID and u.catalogID=o.orgID and " +
                        "o.aspID= " + aspID +
                        " and h.EnrollmentDate>? and h.EnrollmentDate<?";

                try
                {
                        conn = DBUtil.getConnection();

                        LogUtil.debug("common",
                                "[HistoryProfileDAOImpl]getHistoryPersonalTimeReport ==========queryStr = " +
                                        sql);

                        ps = conn.prepareStatement(sql);
                        ps.setDate(1, new java.sql.Date(startDate.getTime()));
                        ps.setDate(2, new java.sql.Date(endDate.getTime()));
                        rs = ps.executeQuery();

                        if (rs.next())
                        {
                                personalTime = rs.getInt("personalTime");
                        }
                }
                catch (SQLException se)
                {
                        se.printStackTrace();
                        throw new HistoryProfileSysException(se);
                }
                finally
                {
                        DBUtil.closeResultSet(rs);
                        DBUtil.closeStatement(ps);
                        DBUtil.closeConnection(conn);
                }

                return personalTime;
        }

        /**
         * 返回特定asp在特定日期的总培训人次
         *
         * @param aspID
         * @param startDate
         * @param endDate
         * @return
         * @throws HistoryProfileSysException
         */
        public int getHistoryPersonalTimeReport(int aspID, Date startDate,
                                                Date endDate) throws HistoryProfileSysException
        {
                Connection conn = null;
                PreparedStatement ps = null;
                ResultSet rs = null;
                int personalTime = 0;

                LogUtil.info("common",
                        "[HistoryProfileDAOImpl]getCourseInfo--endDate0=" +
                                I18Util.FormatDateTime(endDate, Locale.CHINA));

                com.ulearning.ulms.core.util.Calendar calendar = new com.ulearning.ulms.core.util.Calendar(endDate);
                endDate = calendar.nextDay();

                LogUtil.info("common",
                        "[HistoryProfileDAOImpl]getHistoryPersonalTimeReport--startDate=" +
                                I18Util.FormatDateTime(startDate, Locale.CHINA));
                LogUtil.info("common",
                        "[HistoryProfileDAOImpl]getHistoryPersonalTimeReport--endDate=" +
                                I18Util.FormatDateTime(endDate, Locale.CHINA));

                String sql = "select count(h.userID) as personalTime " +
                        "from tm_history_tab h,u_user_tab u ,tm_org_tab o" +
                        " where h.userID=u.userID and u.catalogID=o.orgID and " +
                        "o.aspID= " + aspID +
                        " and h.EnrollmentDate>? and h.EnrollmentDate<?";

                try
                {
                        conn = DBUtil.getConnection();

                        LogUtil.debug("common",
                                "[HistoryProfileDAOImpl]getHistoryPersonalTimeReport ==========queryStr = " +
                                        sql);

                        ps = conn.prepareStatement(sql);
                        ps.setDate(1, new java.sql.Date(startDate.getTime()));
                        ps.setDate(2, new java.sql.Date(endDate.getTime()));
                        rs = ps.executeQuery();

                        if (rs.next())
                        {
                                personalTime = rs.getInt("personalTime");
                        }
                }
                catch (SQLException se)
                {
                        se.printStackTrace();
                        throw new HistoryProfileSysException(se);
                }
                finally
                {
                        DBUtil.closeResultSet(rs);
                        DBUtil.closeStatement(ps);
                        DBUtil.closeConnection(conn);
                }

                return personalTime;
        }

        /**
         * 返回用户的总学分.<br>
         *
         * @param userID
         * @param startDate
         * @param endDate
         * @return
         */
        public float getUserTotalPeriod(int userID, Date startDate, Date endDate)
                throws HistoryProfileSysException
        {
                Connection conn = null;
                PreparedStatement ps = null;
                ResultSet rs = null;
                float totalPeriod = 0;
                LogUtil.info("common",
                        "[HistoryProfileDAOImpl]getUserTotalPeriod--endDate0=" +
                                I18Util.FormatDateTime(endDate, Locale.CHINA));

                com.ulearning.ulms.core.util.Calendar calendar = new com.ulearning.ulms.core.util.Calendar(endDate);
                endDate = calendar.nextDay();

                LogUtil.info("common",
                        "[HistoryProfileDAOImpl]getUserTotalPeriod--startDate=" +
                                I18Util.FormatDateTime(startDate, Locale.CHINA));
                LogUtil.info("common",
                        "[HistoryProfileDAOImpl]getUserTotalPeriod--endDate=" +
                                I18Util.FormatDateTime(endDate, Locale.CHINA));

                String sql = "select sum(period) as TotalPeriod " +
                        "from tm_history_tab " + " where userID= " + userID +
                        " and EnrollmentDate>? and EnrollmentDate<?";

                try
                {
                        conn = DBUtil.getConnection();

                        LogUtil.debug("common",
                                "[HistoryProfileDAOImpl]getUserTotalPeriod ==========queryStr = " +
                                        sql);

                        ps = conn.prepareStatement(sql);
                        ps.setDate(1, new java.sql.Date(startDate.getTime()));
                        ps.setDate(2, new java.sql.Date(endDate.getTime()));
                        rs = ps.executeQuery();

                        if (rs.next())
                        {
                                totalPeriod = rs.getInt("TotalPeriod");
                        }
                }
                catch (SQLException se)
                {
                        se.printStackTrace();
                        throw new HistoryProfileSysException(se);
                }
                finally
                {
                        DBUtil.closeResultSet(rs);
                        DBUtil.closeStatement(ps);
                        DBUtil.closeConnection(conn);
                }

                return totalPeriod;
        }

        /**
         * 返回用户的总学时.<br>
         *
         * @param userID
         * @param startDate
         * @param endDate
         * @return
         */
        public float getUserTotalPassPeriod(int userID, Date startDate, Date endDate)
                throws HistoryProfileSysException
        {
                Connection conn = null;
                PreparedStatement ps = null;
                ResultSet rs = null;
                float totalPeriod = 0;
                LogUtil.info("common",
                        "[HistoryProfileDAOImpl]getUserTotalPeriod--endDate0=" +
                                I18Util.FormatDateTime(endDate, Locale.CHINA));

                com.ulearning.ulms.core.util.Calendar calendar = new com.ulearning.ulms.core.util.Calendar(endDate);
                endDate = calendar.nextDay();

                LogUtil.info("common",
                        "[HistoryProfileDAOImpl]getUserTotalPeriod--startDate=" +
                                I18Util.FormatDateTime(startDate, Locale.CHINA));
                LogUtil.info("common",
                        "[HistoryProfileDAOImpl]getUserTotalPeriod--endDate=" +
                                I18Util.FormatDateTime(endDate, Locale.CHINA));

                String sql = "select sum(period) as TotalPeriod " +
                        "from tm_history_tab " + " where userID= " + userID +
                        "   and Ispass='1'   and EnrollmentDate>? and EnrollmentDate<?";

                try
                {
                        conn = DBUtil.getConnection();

                        LogUtil.debug("common",
                                "[HistoryProfileDAOImpl]getUserTotalPeriod ==========queryStr = " +
                                        sql);

                        ps = conn.prepareStatement(sql);
                        ps.setDate(1, new java.sql.Date(startDate.getTime()));
                        ps.setDate(2, new java.sql.Date(endDate.getTime()));
                        rs = ps.executeQuery();

                        if (rs.next())
                        {
                                totalPeriod = rs.getInt("TotalPeriod");
                        }
                }
                catch (SQLException se)
                {
                        se.printStackTrace();
                        throw new HistoryProfileSysException(se);
                }
                finally
                {
                        DBUtil.closeResultSet(rs);
                        DBUtil.closeStatement(ps);
                        DBUtil.closeConnection(conn);
                }

                return totalPeriod;
        }

        /**
         * get  the user-relationed profile
         * type
         * reference SecurityConstants.
         *
         * @throws com.ulearning.ulms.common.exceptions.HistoryProfileSysException
         *
         */
        public HistoryProfileModel get(int userID, int relationID, int type)
                throws HistoryProfileSysException
        {
                Session session = null;

                HistoryProfileModel hpm = null;

                try
                {
                        String queryStr = " from HistoryModel" + " Where type='" + type +
                                "' and userID=" + userID;

                        queryStr += (" and RelationID=" + relationID);

                        LogUtil.debug("common",
                                "[HistoryProfileDAOImpl] ==========queryStr = " + queryStr);

                        HistoryModel hm = null;
                        session = HibernateUtil.getSession();

                        Query query = session.createQuery(queryStr);

                        List hmList = query.list();
                        LogUtil.debug("course",
                                "[HistoryProfileDAOImpl] ==========hmList.size = " +
                                        hmList.size());

                        if (!hmList.isEmpty() && (hmList.size() >= 1))
                        {
                                hm = (HistoryModel) hmList.get(0);
                                hpm = new HistoryProfileModel(hm);
                        }
                }
                catch (HibernateException he)
                {
                        throw new HistoryProfileSysException(he);
                }
                finally
                {
                        try
                        {
                                HibernateUtil.releaseSession(session);
                        }
                        catch (HibernateException he)
                        {
                                throw new HistoryProfileSysException(he);
                        }
                }

                return hpm;
        }

        /**
         * update  a profile.
         *
         * @throws com.ulearning.ulms.common.exceptions.HistoryProfileSysException
         *
         */
        public void update(HistoryProfileModel hpm)
                throws HistoryProfileSysException
        {
                List l = null;

                int profileID;
                int relationID = hpm.getRelationID();
                int userID = hpm.getUserID();
                int type = hpm.getType();

                try
                {
                        l = HibernateDAO.find(" from HistoryModel" + " Where type='" +
                                type + "' and userID=" + userID + " and relationID=" +
                                relationID);

                        if ((l != null) && (l.size() > 0))
                        {
                                HistoryModel hm = (HistoryModel) l.get(0);
                                profileID = hm.getProfileid();
                                hpm.setProfileID(profileID);
                                HibernateDAO.update(hpm.getHistoryModel());
                                LogUtil.info("common",
                                        "[HistoryProfileDAOImpl]update One history.profileID=" +
                                                profileID);
                        }
                        else
                        {
                                LogUtil.info("common",
                                        "[HistoryProfileDAOImpl]update not  find  history. insert!");
                                insert(hpm);
                        }
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new HistoryProfileSysException(e);
                }
        }

        /**
         * update  a profile' state,enrollmentDate,completionDate.
         *
         * @throws com.ulearning.ulms.common.exceptions.HistoryProfileSysException
         *
         */
        public void update(int userID, int relationID, int type, int state,
                           Date enrollmentDate, Date completionDate)
                throws HistoryProfileSysException
        {
                Connection conn = null;
                PreparedStatement ps = null;

                String sql = null;

                try
                {
                        conn = DBUtil.getConnection();

                        if ((state == CourseKeys.COURSE_USER_FINISH_STATE) &&
                                (completionDate != null))
                        {
                                sql = "update TM_History_Tab set state=?,completionDate=? " +
                                        "  Where  type=? " + " and userID=? " +
                                        " and relationID=?";
                                ps = conn.prepareStatement(sql);

                                ps.setString(1, String.valueOf(state));
                                ps.setString(3, String.valueOf(type));
                                ps.setInt(4, userID);
                                ps.setInt(5, relationID);
                                ps.setTimestamp(2, new Timestamp(completionDate.getTime()));
                        }
                        else if ((state == CourseKeys.COURSE_USER_AVAILABLE_STATE) &&
                                (enrollmentDate != null))
                        {
                                sql = "update TM_History_Tab set state=?,enrollmentDate=? " +
                                        "  Where  type=? " + " and userID=? " +
                                        " and relationID=?";
                                ps = conn.prepareStatement(sql);

                                ps.setString(1, String.valueOf(state));
                                ps.setString(3, String.valueOf(type));
                                ps.setInt(4, userID);
                                ps.setInt(5, relationID);
                                ps.setTimestamp(2, new Timestamp(enrollmentDate.getTime()));
                        }
                        else
                        {
                                sql = "update TM_History_Tab set state=? " + " Where  type=? " +
                                        " and userID=? " + " and relationID=?";
                                ps = conn.prepareStatement(sql);
                                ps.setString(1, String.valueOf(state));
                                ps.setString(2, String.valueOf(type));
                                ps.setInt(3, userID);
                                ps.setInt(4, relationID);
                        }

                        ps.executeUpdate();

                        LogUtil.info("common",
                                "[HistoryProfileDAOImpl]update finished type=" + type +
                                        " userID=" + userID + " relationID=" + relationID);
                }
                catch (SQLException e)
                {
                        e.printStackTrace();
                        throw new HistoryProfileSysException(e);
                }
                finally
                {
                        DBUtil.closeStatement(ps);
                        DBUtil.closeConnection(conn);
                }
        }

        /**
         * update  a profile' state.
         * 若不存在，不会insert.
         *
         * @throws com.ulearning.ulms.common.exceptions.HistoryProfileSysException
         *
         */
        public void update(int userID, int relationID, int type, int state)
                throws HistoryProfileSysException
        {
                Connection conn = null;
                PreparedStatement ps = null;

                String sql = null;

                try
                {
                        conn = DBUtil.getConnection();

                        sql = "update TM_History_Tab set state=?" + "  Where  type=? " +
                                " and userID=? " + " and relationID=?";
                        ps = conn.prepareStatement(sql);

                        ps.setString(1, String.valueOf(state));
                        ps.setString(2, String.valueOf(type));
                        ps.setInt(3, userID);
                        ps.setInt(4, relationID);

                        ps.executeUpdate();

                        LogUtil.info("common",
                                "[HistoryProfileDAOImpl]update finished type=" + type +
                                        " userID=" + userID + " relationID=" + relationID);
                }
                catch (SQLException e)
                {
                        e.printStackTrace();
                        throw new HistoryProfileSysException(e);
                }
                finally
                {
                        DBUtil.closeStatement(ps);
                        DBUtil.closeConnection(conn);
                }
        }

        /**
         * insert  a profile.
         *
         * @throws com.ulearning.ulms.common.exceptions.HistoryProfileSysException
         *
         */
        public void insert(HistoryProfileModel hpm)
                throws HistoryProfileSysException
        {
                try
                {
                        HibernateDAO.add(hpm.getHistoryModel());
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new HistoryProfileSysException(e);
                }
        }

        /**
         * delete a profile
         *
         * @throws com.ulearning.ulms.common.exceptions.HistoryProfileSysException
         *
         */
        public void delete(int relationID) throws HistoryProfileSysException
        {
                try
                {
                        String hql = " from HistoryModel where relationID =" + relationID;
                        HibernateDAO.delete(hql);
                        ;
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new HistoryProfileSysException(e);
                } //To change body of implemented methods use File | Settings | File Templates.
        }

        /**
         * return yyyy-MM string.
         *
         * @param thisDate
         * @return
         */
        protected String getDateString(Date thisDate)
        {
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM");

                if (thisDate != null)
                {
                        return formatter.format(thisDate);
                }
                else
                {
                        return null;
                }
        }
}
