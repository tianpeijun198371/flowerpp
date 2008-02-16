/**
 * ForumDAOImpl.java.
 * User: huangsb  Date: 2004-6-30
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd. * All rights reserved.
 */
package com.ulearning.ulms.tools.discussion.dao;

import com.ulearning.ulms.core.exceptions.ULMSSysException;
import com.ulearning.ulms.core.util.StringUtil;
import com.ulearning.ulms.tools.discussion.exceptions.ForumDAOSysException;
import com.ulearning.ulms.tools.discussion.form.ForumForm;
import com.ulearning.ulms.tools.discussion.model.ForumModel;
import com.ulearning.ulms.util.DBUtil;
import com.ulearning.ulms.util.HibernateDAO;
import com.ulearning.ulms.util.HibernateUtil;
import com.ulearning.ulms.util.log.LogUtil;

import net.sf.hibernate.HibernateException;
import net.sf.hibernate.MappingException;
import net.sf.hibernate.Session;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.List;


public class ForumDAOImpl implements ForumDAO
{
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        /**
         * publish a new forum
         *
         * @param forumForm the new forum
         * @throws ForumDAOSysException If an lmsSys error has occurred.
         */
        public void insert(ForumForm forumForm) throws ForumDAOSysException
        {
                try
                {
                        HibernateDAO.add(forumForm.getForumModel());
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new ForumDAOSysException("insert(ForumForm forumForm) method" +
                                e);
                }
        }

        /**
         * update a Forum
         *
         * @param forumForm
         * @throws ForumDAOSysException If an lmsSys error has occurred.
         */
        public void update(ForumForm forumForm) throws ForumDAOSysException
        {
                try
                {
                        HibernateDAO.update(forumForm.getForumModel());
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new ForumDAOSysException("update(ForumForm forumForm) method" +
                                e);
                }
        }

        /**
         * Get all the ForumID according to the title
         *
         * @param forumID
         * @return
         * @throws ForumDAOSysException
         */
        public int getTotalDisucss(int forumID) throws ForumDAOSysException
        {
                ResultSet rs = null;
                int totalDiscuss = 0;
                String sqlStr = "select count(*) as total from C_Discuss_Tab  where" +
                        " parentID = 0" + " and forumID=" + forumID;

                try
                {
                        conn = DBUtil.getConnection();
                        stmt = conn.createStatement();
                        rs = stmt.executeQuery(sqlStr);

                        if (rs.next())
                        {
                                totalDiscuss = rs.getInt(1);
                        }
                }
                catch (SQLException se)
                {
                        throw new ForumDAOSysException("SQLException while updating " +
                                "forum; Serial = " + forumID + " :\n" + se, se);
                }
                catch (ULMSSysException se)
                {
                        throw new ForumDAOSysException("SQLException while updating " +
                                "Forum; Serial = " + forumID + " :\n" + se, se);
                }
                finally
                {
                        DBUtil.closeResultSet(rs);
                        DBUtil.closeStatement(stmt);
                        DBUtil.closeConnection(conn);
                }

                return totalDiscuss;
        }

        /**
         * 获得一个板块的所有主题的回复记录数
         *
         * @param forumID
         * @return
         * @throws ForumDAOSysException
         */
        public int getTotalRevert(int forumID) throws ForumDAOSysException
        {
                ResultSet rs = null;
                int totalRevert = 0;
                String sqlStr = "select count(*) as total from C_Discuss_Tab  where" +
                        " parentID != 0" + " and forumID=" + forumID;

                try
                {
                        conn = DBUtil.getConnection();
                        stmt = conn.createStatement();
                        rs = stmt.executeQuery(sqlStr);

                        if (rs.next())
                        {
                                totalRevert = rs.getInt(1);
                        }
                }
                catch (SQLException se)
                {
                        throw new ForumDAOSysException("SQLException while updating " +
                                "forum; Serial = " + forumID + " :\n" + se, se);
                }
                catch (ULMSSysException se)
                {
                        throw new ForumDAOSysException("SQLException while updating " +
                                "Forum; Serial = " + forumID + " :\n" + se, se);
                }
                finally
                {
                        DBUtil.closeResultSet(rs);
                        DBUtil.closeStatement(stmt);
                        DBUtil.closeConnection(conn);
                }

                return totalRevert;
        }

        public int getForumID(String title) throws ForumDAOSysException
        {
                int forumID = 0;

                //String sqlStr = "Select ForumID from C_Forum_Tab where title = '" + title + "'";
                String hql = " from ForumModel where title = '" + title + "'";

                Session session = null;

                try
                {
                        session = HibernateUtil.getSession();

                        List fmList = session.find(hql);
                        ForumModel fm = null;

                        if ((fmList != null) && (fmList.size() > 0))
                        {
                                fm = (ForumModel) fmList.get(0);
                                forumID = fm.getForumid();
                        }
                }
                catch (HibernateException he)
                {
                        throw new ForumDAOSysException(
                                "SQLException while getForumID(String title) " + "forum; " +
                                        "forumID = " + forumID + " :\n" + he);
                }
                finally
                {
                        try
                        {
                                HibernateUtil.releaseSession(session);
                        }
                        catch (HibernateException e)
                        {
                                LogUtil.debug("system",
                                        "getForumID(String title) Method Hibernate Exception" +
                                                e.getMessage());
                        }
                }

                return forumID;
        }

        /**
         * delete some Forum
         *
         * @param forumIDs IDs of Forum
         * @throws ForumDAOSysException If an lmsSys error has occurred.
         */
        public void remove(String[] forumIDs) throws ForumDAOSysException
        {
                String sqlStr = "delete from C_Forum_Tab where ";
                String condition = "";

                for (int i = 0; i < forumIDs.length; i++)
                {
                        condition = condition + " or ForumID=" + forumIDs[i];
                }

                if (!condition.equals(""))
                {
                        condition = condition.substring(4);
                        sqlStr = sqlStr + condition;

                        try
                        {
                                conn = DBUtil.getConnection();
                                stmt = conn.createStatement();
                                rs = stmt.executeQuery(sqlStr);
                        }
                        catch (SQLException se)
                        {
                                throw new ForumDAOSysException("SQLException while updating " +
                                        "Forum; sqlStr = " + sqlStr + " :\n" + se, se);
                        }
                        finally
                        {
                                DBUtil.closeResultSet(rs);
                                DBUtil.closeStatement(stmt);
                                DBUtil.closeConnection(conn);
                        }
                }
        }

        /**
         * delete some Forum
         *
         * @param forumID of Forum
         * @throws ForumDAOSysException If an lmsSys error has occurred.
         */
        public void remove(int forumID) throws ForumDAOSysException
        {
                String hql = " from ForumModel where ForumID = " + forumID;
                Session session = null;

                try
                {
                        session = HibernateUtil.getSession();
                        session.delete(hql);
                        session.flush();
                        session.connection().commit();
                }
                catch (MappingException e)
                {
                        LogUtil.debug("system",
                                "remove(int forumID) Method Mapping Exception" +
                                        e.getMessage());
                }
                catch (HibernateException e)
                {
                        LogUtil.debug("system",
                                "remove(int forumID) Method Hibernate Exception" +
                                        e.getMessage());
                }
                catch (SQLException e)
                {
                        LogUtil.debug("system",
                                "remove(int forumID) Method Hibernate Exception" +
                                        e.getMessage());
                }
                finally
                {
                        try
                        {
                                HibernateUtil.releaseSession(session);
                        }
                        catch (HibernateException e)
                        {
                                LogUtil.debug("system",
                                        "remove(int forumID) Method Hibernate Exception" +
                                                e.getMessage());
                        }
                }
        }

        /**
         * get a piece of information
         *
         * @param forumID
         * @return a string contains user's basic email information
         * @throws ForumDAOSysException If an ulmsSys error has occurred.
         */
        public ForumForm getForum(int forumID) throws ForumDAOSysException
        {
                ForumForm ff = null;
                Session session = null;

                try
                {
                        session = HibernateUtil.getSession();

                        ForumModel fm = null;
                        Object forum = session.get(ForumModel.class, new Integer(forumID));

                        if (forum != null)
                        {
                                fm = (ForumModel) forum;
                        }

                        ff = new ForumForm(fm);
                }
                catch (HibernateException he)
                {
                        throw new ForumDAOSysException(
                                "SQLException while updating  getForum(int forumID)" +
                                        "forumID; " + "planID = " + forumID + " :\n" + he);
                }
                finally
                {
                        try
                        {
                                HibernateUtil.releaseSession(session);
                        }
                        catch (HibernateException e)
                        {
                                LogUtil.debug("system",
                                        " getForum(int forumID) Method Hibernate Exception" +
                                                e.getMessage());
                        }
                }

                return ff;
        }

        /**
         * Get all the Forum according to the forumID
         *
         * @param courseID
         * @return
         * @throws ForumDAOSysException
         */
        public List getForumList(int courseID) throws ForumDAOSysException
        {
                List discussList = new ArrayList();
                String hql = " from ForumModel where courseID=" + courseID;
                Session session = null;

                try
                {
                        session = HibernateUtil.getSession();

                        List tmList = session.find(hql);
                        ForumModel tm = null;

                        if (tmList != null)
                        {
                                for (int i = 0; i < tmList.size(); i++)
                                {
                                        tm = (ForumModel) tmList.get(i);

                                        ForumForm pf = new ForumForm(tm);
                                        discussList.add(pf);
                                }
                        }
                }
                catch (HibernateException he)
                {
                        throw new ForumDAOSysException(
                                "SQLException while updating getForumList(int courseID) " +
                                        "Forum; " + "courseID = " + courseID + " :\n" + he);
                }
                finally
                {
                        try
                        {
                                HibernateUtil.releaseSession(session);
                        }
                        catch (HibernateException e)
                        {
                                LogUtil.debug("system",
                                        "getForumList(int courseID) Hibernate Exception" +
                                                e.getMessage());
                        }
                }

                return discussList;
        }

        /**
         * Convert the resultSet object to baseForm
         *
         * @param rs the resultSet which needs to convert
         * @return the wanted baseForm
         */
        public ForumForm convertRs2Form(ResultSet rs)
        {
                ForumForm df = new ForumForm();
                int rsIndex = 1;

                try
                {
                        df.setForumID(rs.getInt(rsIndex++));
                        df.setCourseID(rs.getInt(rsIndex++));
                        df.setOrderIndex(rs.getInt(rsIndex++));
                        df.setTitle(StringUtil.nullToStr(rs.getString(rsIndex++)));
                        df.setIsAllowAnn(rs.getInt(rsIndex++));
                        df.setIsUpdateAft(rs.getInt(rsIndex++));
                        df.setIsDeleteAft(rs.getInt(rsIndex++));
                        df.setIsAccessory(rs.getInt(rsIndex++));
                        df.setIsNewItem(rs.getInt(rsIndex++));
                        df.setDescription(StringUtil.nullToStr(rs.getString(rsIndex++)));
                }
                catch (SQLException sql)
                {
                        sql.printStackTrace();
                }

                return df;
        }
}
