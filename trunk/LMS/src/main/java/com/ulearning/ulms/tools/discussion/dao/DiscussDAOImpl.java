/**
 * DiscussDAOImpl.java.
 * User: huangsb  Date: 2004-6-30
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd. * All rights reserved.
 */
package com.ulearning.ulms.tools.discussion.dao;

import com.ulearning.ulms.core.exceptions.ULMSSysException;
import com.ulearning.ulms.core.util.StringUtil;
import com.ulearning.ulms.tools.discussion.exceptions.DiscussDAOSysException;
import com.ulearning.ulms.tools.discussion.form.DiscussForm;
import com.ulearning.ulms.tools.discussion.model.DiscussModel;
import com.ulearning.ulms.util.DBUtil;
import com.ulearning.ulms.util.HibernateDAO;
import com.ulearning.ulms.util.log.LogUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class DiscussDAOImpl implements DiscussDAO
{
        private List discussTree = new ArrayList();
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        /**
         * publish a new discuss
         *
         * @param discussForm the new discuss
         * @throws com.ulearning.ulms.tools.discussion.exceptions.DiscussDAOSysException
         *          If an lmsSys error has occurred.
         */
        public void insert(DiscussForm discussForm) throws DiscussDAOSysException
        {
                try
                {
                        HibernateDAO.add(discussForm.getDiscussModel());
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new DiscussDAOSysException(
                                "insert(DiscussForm discussForm) method" + e);
                }
        }

        /**
         * update a discuss
         *
         * @param discussForm
         * @throws DiscussDAOSysException If an lmsSys error has occurred.
         */
        public void update(DiscussForm discussForm) throws DiscussDAOSysException
        {
                try
                {
                        HibernateDAO.update(discussForm.getDiscussModel());
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new DiscussDAOSysException(
                                "update(DiscussForm discussForm) method" + e);
                }
        }

        /**
         * update a discuss
         *
         * @param discussForm
         * @throws DiscussDAOSysException If an lmsSys error has occurred.
         */
        public void updateDetail(DiscussForm discussForm)
                throws DiscussDAOSysException
        {
                String strStr = "update C_Discuss_Tab set " + "IsLock = '" +
                        discussForm.getIsLock() + "'" + ",IsRead = '" +
                        discussForm.getIsRead() + "'" + ",best = " + discussForm.getBest() +
                        " where ArticleID = " + discussForm.getArticleID();

                try
                {
                        List discussTree = getDiscussTree(discussForm.getArticleID());

                        for (int i = 0; i < discussTree.size(); i++)
                        {
                                DiscussForm df = (DiscussForm) discussTree.get(i);
                                String sql = "update C_Discuss_Tab set " + "IsLock = '" +
                                        discussForm.getIsLock() + "',IsRead = '" +
                                        discussForm.getIsRead() + "' where ArticleID = " +
                                        df.getArticleID();
                                LogUtil.debug("test",
                                        "[DiscussDAOImpl]====================the sql string is : " +
                                                sql);
                                conn = DBUtil.getConnection();
                                stmt = conn.createStatement();
                                stmt.execute(sql);
                        }

                        LogUtil.debug("test",
                                "[DiscussDAOOracle],updateDetail====================the sql string is : " +
                                        strStr);
                        conn = DBUtil.getConnection();
                        stmt = conn.createStatement();
                        stmt.executeQuery(strStr);
                }
                catch (SQLException se)
                {
                        throw new DiscussDAOSysException("SQLException while update " +
                                "DiscussDAO; Serial = " + discussForm.getArticleID() + " :\n" +
                                se, se);
                }
                finally
                {
                        //DBUtil.closeResultSet(rs);
                        DBUtil.closeStatement(stmt);
                        DBUtil.closeConnection(conn);
                }
        }

        /**
         * delete some discusss
         *
         * @param discussIDs IDs of discuss
         * @throws DiscussDAOSysException If an lmsSys error has occurred.
         */
        public void remove(String[] discussIDs) throws DiscussDAOSysException
        {
                String sqlStr = "delete from C_Discuss_Tab where ";
                String condition = "";

                for (int i = 0; i < discussIDs.length; i++)
                {
                        condition = condition + " or ArticleID=" + discussIDs[i];
                }

                if (!condition.equals(""))
                {
                        condition = condition.substring(4);
                        sqlStr = sqlStr + condition;

                        try
                        {
                                for (int i = 0; i < discussIDs.length; i++)
                                {
                                        List discussTree = getDiscussTree(Integer.parseInt(
                                                discussIDs[i]));

                                        for (int j = 0; i < discussTree.size(); i++)
                                        {
                                                DiscussForm df = (DiscussForm) discussTree.get(j);
                                                String sql = "delete from C_Discuss_Tab  " +
                                                        " where ParentID = " + df.getParentID();//getArticleID();
                                                LogUtil.debug("test",
                                                        "[DiscussDAOOracle]====================the sql string is : " +
                                                                sql);
                                                conn = DBUtil.getConnection();
                                                stmt = conn.createStatement();
                                                rs = stmt.executeQuery(sql);
                                        }
                                }

                                conn = DBUtil.getConnection();
                                stmt = conn.createStatement();
                                rs = stmt.executeQuery(sqlStr);
                        }
                        catch (SQLException se)
                        {
                                throw new DiscussDAOSysException("SQLException while updating " +
                                        "Discuss; sqlStr = " + sqlStr + " :\n" + se, se);
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
         * delete some discusss
         *
         * @param articleID IDs of discuss
         * @throws DiscussDAOSysException If an lmsSys error has occurred.
         */
        public void remove(int articleID) throws DiscussDAOSysException
        {
                String sqlStr = "delete from C_Discuss_Tab where ArticleID = " +
                        articleID;

                try
                {
                        List discussTree = getDiscussTree(articleID);

                        for (int i = 0; i < discussTree.size(); i++)
                        {
                                DiscussForm df = (DiscussForm) discussTree.get(i);
                                String sql = "delete from C_Discuss_Tab  " +
                                        " where ParentID = " + df.getArticleID();
                                LogUtil.debug("test",
                                        "[DiscussDAOOracle]====================the sql string is : " +
                                                sql);
                                conn = DBUtil.getConnection();
                                stmt = conn.createStatement();
                                rs = stmt.executeQuery(sql);
                        }

                        conn = DBUtil.getConnection();
                        stmt = conn.createStatement();
                        rs = stmt.executeQuery(sqlStr);
                }
                catch (SQLException se)
                {
                        throw new DiscussDAOSysException(
                                "SQLException while remove(int articleID) method " +
                                        "Discuss; Serial = " + articleID + " :\n" + se, se);
                }
                finally
                {
                        DBUtil.closeResultSet(rs);
                        DBUtil.closeStatement(stmt);
                        DBUtil.closeConnection(conn);
                }
        }

        /**
         * Get all the Discuss according to the forumID ,parentID
         *
         * @param forumID
         * @param parentID
         * @return
         * @throws DiscussDAOSysException
         */
        public List getDiscussList(int forumID, int parentID)
                throws DiscussDAOSysException
        {
                DiscussForm df = null;
                ArrayList discussList = new ArrayList();
                List tmList = null;
                String hql = " from DiscussModel where forumID = " + forumID +
                        " and ParentID=" + parentID + "  order by DateTime asc";

                try
                {
                        tmList = HibernateDAO.find(hql);

                        DiscussModel tm = null;

                        if ((tmList != null) && (tmList.size() > 0))
                        {
                                for (int i = 0; i < tmList.size(); i++)
                                {
                                        tm = (DiscussModel) tmList.get(i);
                                        df = new DiscussForm(tm);
                                        discussList.add(df);
                                }
                        }
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new DiscussDAOSysException(
                                "SQLException while getDiscussList(int forumID, int parentID) method" +
                                        e);
                }

                return discussList;
        }

        /**
         * Get all the Discuss according to the forumID ,parentID
         *
         * @param parentID
         * @return
         * @throws DiscussDAOSysException
         */
        public List getDiscussList(int parentID) throws DiscussDAOSysException
        {
                DiscussForm df = null;
                ResultSet rs = null;
                ArrayList discussList = new ArrayList();
                String sqlStr = "select * from C_Discuss_Tab where ParentID = " +
                        parentID + "  order by DateTime desc";

                try
                {
                        LogUtil.debug("system",
                                "[DiscussDAOImpl]====================the sql string is : " +
                                        sqlStr);
                        conn = DBUtil.getConnection();
                        stmt = conn.createStatement();
                        rs = stmt.executeQuery(sqlStr);

                        while (rs.next())
                        {
                                df = convertRs2Form(rs);
                                discussList.add(df);
                        }
                }
                catch (SQLException se)
                {
                        throw new DiscussDAOSysException("SQLException while select " +
                                "Discuss; articleID=" + parentID + ":\n" + se, se);
                }
                catch (ULMSSysException se)
                {
                        throw new DiscussDAOSysException("SQLException while select " +
                                "Discuss; articleID = " + parentID + " :\n" + se, se);
                }
                finally
                {
                        DBUtil.closeResultSet(rs);
                        DBUtil.closeStatement(stmt);
                        DBUtil.closeConnection(conn);
                }

                return discussList;
        }

        /**
         * get a piece of information
         *
         * @param discussID
         * @return a string contains user's basic email information
         * @throws DiscussDAOSysException If an ulmsSys error has occurred.
         */
        public DiscussForm getDiscuss(int discussID) throws DiscussDAOSysException
        {
                DiscussForm df = null;

                //String hql = " from DiscussModel where discussID = " + discussID;
                try
                {
                        DiscussModel dm = null;
                        Object discuss = HibernateDAO.load(DiscussModel.class,
                                new Integer(discussID));

                        if (discuss != null)
                        {
                                dm = (DiscussModel) discuss;
                        }

                        df = new DiscussForm(dm);
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new DiscussDAOSysException(
                                "SQLException while getDiscussList(int forumID) method" + e);
                }

                return df;
        }

        /**
         * @param parentID
         * @return
         * @throws DiscussDAOSysException
         */
        public List getDiscussTree(int parentID) throws DiscussDAOSysException
        {
                List root = getDiscussList(parentID);
                int level = 0;

                for (Iterator it = root.iterator(); it.hasNext();)
                {
                        DiscussForm of = (DiscussForm) it.next();
                        of.setLayer(level);

                        int rootID = of.getArticleID();
                        discussTree.add(of);

                        if (isHasSubDiscuss(rootID))
                        {
                                getDiscussAllTree(rootID, level);
                        }
                }

                return discussTree;
        }

        /**
         * @param parentID
         * @param currentLevel
         * @return
         * @throws DiscussDAOSysException
         */
        public List getDiscussAllTree(int parentID, int currentLevel)
                throws DiscussDAOSysException
        {
                List root = getDiscussList(parentID);
                int level = currentLevel + 1;

                for (Iterator it = root.iterator(); it.hasNext();)
                {
                        DiscussForm df = (DiscussForm) it.next();
                        df.setLayer(level);

                        int rootID = df.getArticleID();
                        discussTree.add(df);

                        if (isHasSubDiscuss(rootID))
                        {
                                getDiscussAllTree(rootID, level);
                        }
                }

                return discussTree;
        }

        /**
         * get list by forumID parentID
         *
         * @param forumID
         * @param parentID
         * @return
         * @throws DiscussDAOSysException
         */
        public List getDiscussTree(int forumID, int parentID)
                throws DiscussDAOSysException
        {
                List root = getDiscussList(forumID, parentID);
                int level = 0;

                for (Iterator it = root.iterator(); it.hasNext();)
                {
                        DiscussForm of = (DiscussForm) it.next();
                        //System.out.println("of.getDateTime() ================ " + of.getDateTime());
                        of.setLayer(level);

                        int rootID = of.getArticleID();
                        discussTree.add(of);

                        if (isHasSubDiscuss(rootID))
                        {
                                getDiscussTree(forumID, rootID, level);
                        }
                }

                return discussTree;
        }

        /**
         * @param forumID
         * @param parentID
         * @param currentLevel
         * @return
         * @throws DiscussDAOSysException
         */
        public List getDiscussTree(int forumID, int parentID, int currentLevel)
                throws DiscussDAOSysException
        {
                List root = getDiscussList(forumID, parentID);
                int level = currentLevel + 1;

                for (Iterator it = root.iterator(); it.hasNext();)
                {
                        DiscussForm df = (DiscussForm) it.next();
                        System.out.println("df.getDateTime() ================ " +
                                df.getDateTime());
                        df.setLayer(level);

                        int rootID = df.getArticleID();
                        discussTree.add(df);

                        if (isHasSubDiscuss(rootID))
                        {
                                getDiscussTree(forumID, rootID, level);
                        }
                }

                return discussTree;
        }

        /**
         * @param articleID
         * @return
         * @throws DiscussDAOSysException
         */
        public boolean isHasSubDiscuss(int articleID) throws DiscussDAOSysException
        {
                ResultSet rs = null;
                String sqlStr = "select COUNT(*) as subCount from C_Discuss_Tab where parentID = " +
                        articleID;

                try
                {
                        LogUtil.debug("user",
                                "[DiscussDAOOracle]====================the sql string is : " +
                                        sqlStr);
                        conn = DBUtil.getConnection();
                        stmt = conn.createStatement();
                        rs = stmt.executeQuery(sqlStr);

                        while (rs.next())
                        {
                                if (rs.getInt("subCount") > 0)
                                {
                                        return true;
                                }
                        }
                }
                catch (SQLException se)
                {
                        throw new DiscussDAOSysException("SQLException while select " +
                                "Discuss; articleID=" + articleID + ":\n" + se, se);
                }
                catch (ULMSSysException se)
                {
                        throw new DiscussDAOSysException("SQLException while select " +
                                "Discuss; articleID = " + articleID + " :\n" + se, se);
                }
                finally
                {
                        DBUtil.closeResultSet(rs);
                        DBUtil.closeStatement(stmt);
                        DBUtil.closeConnection(conn);
                }

                return false;
        }

        /**
         * Convert the resultSet object to baseForm
         *
         * @param rs the resultSet which needs to convert
         * @return the wanted baseForm
         */
        public DiscussForm convertRs2Form(ResultSet rs)
        {
                DiscussForm df = new DiscussForm();
                int rsIndex = 1;

                try
                {
                        df.setArticleID(rs.getInt(rsIndex++));
                        df.setForumID(rs.getInt(rsIndex++));
                        df.setUserID(rs.getInt(rsIndex++));
                        df.setParentID(rs.getInt(rsIndex++));
                        df.setTitle(StringUtil.nullToStr(rs.getString(rsIndex++)));
                        df.setContent(StringUtil.nullToStr(rs.getString(rsIndex++)));
                        df.setDateTime(rs.getDate(rsIndex++));
                        df.setFileLink(StringUtil.nullToStr(rs.getString(rsIndex++)));
                        df.setLinkType(rs.getInt(rsIndex++));
                        df.setIsLock(rs.getInt(rsIndex++));
                        df.setIsRead(rs.getInt(rsIndex++));
                        df.setLayer(rs.getInt(rsIndex++));
                        df.setDescription(StringUtil.nullToStr(rs.getString(rsIndex++)));
                }
                catch (SQLException sql)
                {
                        sql.printStackTrace();
                }

                return df;
        }

        /**
         * 获得一个板块的最新的一个主题
         *
         * @param forumID
         * @return
         * @throws DiscussDAOSysException
         */
        public DiscussForm getlastDiscuss(int forumID)
                throws DiscussDAOSysException
        {
                DiscussForm res = null;

                List dmList = null;

                String hql = " from DiscussModel where   ForumID = " + forumID +
                        " and parentID = 0 order by ArticleID desc";

                try
                {
                        dmList = HibernateDAO.find(hql);
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new DiscussDAOSysException("" + e);
                }

                if ((dmList != null) && (dmList.size() > 0))
                {
                        DiscussModel tm = (DiscussModel) dmList.get(0);
                        res = new DiscussForm(tm);
                }

                return res;
        }

        /**
         * 获得一个板块的所有主题的回复记录数
         *
         * @param parentID
         * @return
         * @throws DiscussDAOSysException
         */
        public int getDiscussRevert(int parentID) throws DiscussDAOSysException
        {
                ResultSet rs = null;
                int discuss_num = 0;
                String sql = "select count(*) from C_Discuss_Tab where ParentID=" +
                        parentID;

                try
                {
                        conn = DBUtil.getConnection();
                        stmt = conn.createStatement();
                        rs = stmt.executeQuery(sql);

                        if (rs.next())
                        {
                                discuss_num = rs.getInt(1);
                        }
                }
                catch (SQLException se)
                {
                        throw new DiscussDAOSysException(
                                "SQLException while select count Serial = " + parentID + " :n" +
                                        se, se);
                }
                catch (ULMSSysException se)
                {
                        throw new DiscussDAOSysException(
                                "SQLException while select count Serial = " + parentID + " :n" +
                                        se, se);
                }
                finally
                {
                        DBUtil.closeResultSet(rs);
                        DBUtil.closeStatement(stmt);
                        DBUtil.closeConnection(conn);
                }

                return discuss_num;
        }

        /**
         * 查询符合条件的记录
         *
         * @param forumID
         * @param title
         * @return
         * @throws DiscussDAOSysException
         */
        public List selectDiscuss(int forumID, String title)
                throws DiscussDAOSysException
        {
                DiscussForm df = null;
                ResultSet rs = null;
                ArrayList discussList = new ArrayList();
                String sqlStr = "select * from C_Discuss_Tab where ForumID=" + forumID +
                        " and Title like '%" + title + "%' or Content like '%" + title +
                        "%'order by DateTime desc";

                try
                {
                        LogUtil.debug("system",
                                "[DiscussDAOImpl]====================the sql string is : " +
                                        sqlStr);
                        conn = DBUtil.getConnection();
                        stmt = conn.createStatement();
                        rs = stmt.executeQuery(sqlStr);

                        while (rs.next())
                        {
                                df = convertRs2Form(rs);
                                discussList.add(df);
                        }
                }
                catch (SQLException se)
                {
                        throw new DiscussDAOSysException("SQLException while select " +
                                "Discuss; title=" + title + ":\n" + se, se);
                }
                catch (ULMSSysException se)
                {
                        throw new DiscussDAOSysException("SQLException while select " +
                                "Discuss; title = " + title + " :\n" + se, se);
                }
                finally
                {
                        DBUtil.closeResultSet(rs);
                        DBUtil.closeStatement(stmt);
                        DBUtil.closeConnection(conn);
                }

                return discussList;
        }

        /**
         * 精华帖子
         *
         * @param forumID
         * @return
         * @throws DiscussDAOSysException
         */
        public List selecttopDiscuss(int forumID) throws DiscussDAOSysException
        {
                DiscussForm df = null;
                ResultSet rs = null;
                ArrayList discussList = new ArrayList();
                String sqlStr = "select * from C_Discuss_Tab where ForumID=" + forumID +
                        " order by Description asc";

                try
                {
                        LogUtil.debug("system",
                                "[DiscussDAOImpl]====================the sql string is : " +
                                        sqlStr);
                        conn = DBUtil.getConnection();
                        stmt = conn.createStatement();
                        rs = stmt.executeQuery(sqlStr);

                        while (rs.next())
                        {
                                df = convertRs2Form(rs);
                                discussList.add(df);
                        }
                }
                catch (SQLException se)
                {
                        throw new DiscussDAOSysException("SQLException while select " +
                                "Discuss; forumID=" + forumID + ":\n" + se, se);
                }
                catch (ULMSSysException se)
                {
                        throw new DiscussDAOSysException("SQLException while select " +
                                "Discuss; forumID = " + forumID + " :\n" + se, se);
                }
                finally
                {
                        DBUtil.closeResultSet(rs);
                        DBUtil.closeStatement(stmt);
                        DBUtil.closeConnection(conn);
                }

                return discussList;
        }

        /**
         * 我发表或参与的帖子
         *
         * @param UserID
         * @param is_topic
         * @return
         * @throws DiscussDAOSysException
         */
        public List myDiscuss(int UserID, int is_topic)
                throws DiscussDAOSysException
        {
                DiscussForm df = null;
                ResultSet rs = null;
                ArrayList discussList = new ArrayList();
                String sqlStr = null;

                //我发表的主题
                if (is_topic == 0)
                {
                        sqlStr = "select * from C_Discuss_Tab where UserID=" + UserID +
                                " and ParentID=0 order by DateTime desc";
                }
                else
                {
                        sqlStr = "select * from C_Discuss_Tab where UserID=" + UserID +
                                " and ParentID!=0 order by DateTime desc";
                }

                try
                {
                        LogUtil.debug("system",
                                "[DiscussDAOImpl]====================the sql string is : " +
                                        sqlStr);
                        conn = DBUtil.getConnection();
                        stmt = conn.createStatement();
                        rs = stmt.executeQuery(sqlStr);

                        while (rs.next())
                        {
                                df = convertRs2Form(rs);
                                discussList.add(df);
                        }
                }
                catch (SQLException se)
                {
                        throw new DiscussDAOSysException("SQLException while select " +
                                "Discuss; UserID=" + UserID + ":\n" + se, se);
                }
                catch (ULMSSysException se)
                {
                        throw new DiscussDAOSysException("SQLException while select " +
                                "Discuss; UserID = " + UserID + " :\n" + se, se);
                }
                finally
                {
                        DBUtil.closeResultSet(rs);
                        DBUtil.closeStatement(stmt);
                        DBUtil.closeConnection(conn);
                }

                return discussList;
        }

        /**
         * 获得某个人发表的帖子数
         *
         * @param UserID
         * @return
         * @throws DiscussDAOSysException
         */
        public int getmydiscuss_num(int UserID) throws DiscussDAOSysException
        {
                ResultSet rs = null;
                int mydiscuss_num = 0;
                String sql = "select count(*) from C_Discuss_Tab where UserID=" +
                        UserID;

                try
                {
                        conn = DBUtil.getConnection();
                        stmt = conn.createStatement();
                        rs = stmt.executeQuery(sql);

                        if (rs.next())
                        {
                                mydiscuss_num = rs.getInt(1);
                        }
                }
                catch (SQLException se)
                {
                        throw new DiscussDAOSysException(
                                "SQLException while select count Serial = " + UserID + " :n" +
                                        se, se);
                }
                catch (ULMSSysException se)
                {
                        throw new DiscussDAOSysException(
                                "SQLException while select count Serial = " + UserID + " :n" +
                                        se, se);
                }
                finally
                {
                        DBUtil.closeResultSet(rs);
                        DBUtil.closeStatement(stmt);
                        DBUtil.closeConnection(conn);
                }

                return mydiscuss_num;
        }

        public List getBest(int forumID) throws DiscussDAOSysException
        {
                List bestLt = null;

                try
                {
                        bestLt = HibernateDAO.find(
                                "from DiscussModel where best=1 and ForumID=" + forumID);
                }
                catch (Exception e)
                {
                        throw new DiscussDAOSysException(e);
                }

                return bestLt;
        }

        public static void main(String[] args)
        {
                DiscussDAOImpl nes = new DiscussDAOImpl();
                List ss = nes.getDiscussTree(2, 225);
                System.out.println("ss.size() = " + ss.size());

                for (int i = 0; i < ss.size(); i++)
                {
                        DiscussForm ds = (DiscussForm) ss.get(i);
                        System.out.println("ds.getDateTime() =ssi " + ds.getDateTime());
                }
        }
}
