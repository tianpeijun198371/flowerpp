/**
 * CourseContentDAOImpl.java.
 * User: dengj  Date: 2004-4-30
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.course.dao;

import com.ulearning.ulms.core.exceptions.ULMSException;
import com.ulearning.ulms.core.exceptions.ULMSSysException;
import com.ulearning.ulms.core.util.Config;
import com.ulearning.ulms.core.util.PagerList;
import com.ulearning.ulms.course.exceptions.CourseDAOSysException;
import com.ulearning.ulms.course.model.CourseContentForm;
import com.ulearning.ulms.course.model.CourseContentModel;
import com.ulearning.ulms.course.model.CourseListModel;
import com.ulearning.ulms.course.model.CourseModel;
import com.ulearning.ulms.course.util.CourseContentKeys;
import com.ulearning.ulms.tools.assignment.exceptions.AssignmentDAOSysException;
import com.ulearning.ulms.util.DBUtil;
import com.ulearning.ulms.util.HibernateDAO;
import com.ulearning.ulms.util.HibernateUtil;
import com.ulearning.ulms.util.log.LogUtil;

import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Query;
import net.sf.hibernate.Session;

import java.sql.*;
import java.sql.Date;

import java.util.*;

import javax.sql.DataSource;


public class CourseContentDAOImpl implements CourseContentDAO
{
        protected transient Connection dbConnection = null;
        protected transient DataSource datasource = null;
        private List courseContentTree = new ArrayList();

        public int addCourseContent(CourseContentForm details)
                throws CourseDAOSysException
        {
                int courseContenID = 0;

                try
                {
                        String courseContenIDs = HibernateDAO.add(details.getCourseContentModel())
                                .toString();
                        courseContenID = Integer.parseInt(courseContenIDs);
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new CourseDAOSysException(e);
                }

                return courseContenID;
        }

        public String getCourseContentURL(int nodeID) throws CourseDAOSysException
        {
                Statement stmt = null;
                CourseContentForm ccf = null;
                ResultSet rs = null;
                String url = null;
                String sqlStr = "";
                CourseContentForm form = getCourseContent(new Integer(nodeID).toString());

                if ((form.getNodetype() == CourseContentKeys.FILE_NODETYPE) ||
                        (form.getNodetype() == CourseContentKeys.STREAMMEDIA_NODETYPE))
                {
                        sqlStr = "select *  from C_CourseContentTreeNode_Tab where " +
                                " nodeID=" + nodeID;

                        try
                        {
                                dbConnection = getConnection();
                                stmt = dbConnection.createStatement();
                                LogUtil.debug("system",
                                        "[CourseContentDAOImpl]====================the sql string is : " +
                                                sqlStr);
                                rs = stmt.executeQuery(sqlStr);

                                while (rs.next())
                                {
                                        url = Config.getUploadVirtualPath() +
                                                rs.getString("catalog");
                                }
                        }
                        catch (SQLException se)
                        {
                                throw new CourseDAOSysException("SQLException while ge t " +
                                        "CourseContent; " + "nodeID = " + nodeID + " :\n" + se);
                        }
                        finally
                        {
                                try
                                {
                                        closeResultSet(rs);
                                        stmt.close();
                                        closeConnection();
                                }
                                catch (SQLException se)
                                {
                                }
                        }
                }

                return url; 
        }

        public void updateCourseContent(CourseContentForm details)
                throws CourseDAOSysException
        {
                try
                {
                        HibernateDAO.update(details.getCourseContentModel());
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new CourseDAOSysException(e);
                }
        }

        /**
         * Remove the courseContent from database by the nodeID
         *
         * @param nodeID
         * @throws CourseDAOSysException
         */
        public void removeCourseContent(String nodeID) throws CourseDAOSysException
        {
                Statement stmt = null;
                ResultSet rs = null;

                try
                {
                        dbConnection = getConnection();
                        stmt = dbConnection.createStatement();

                        String sqlStr = "delete from C_CourseContentTreeNode_Tab where NodeID = " +
                                nodeID;
                        stmt.execute(sqlStr);
                        LogUtil.debug("system",
                                "[CourseContentDAOOracle]====================the sql string is : " +
                                        sqlStr);

                        String sql = "select * from C_CourseContentTreeNode_Tab where parentID=" +
                                nodeID;
                        rs = stmt.executeQuery(sql);
                        LogUtil.debug("system",
                                "[CourseContentDAOOracle]====================the sql string is : " +
                                        sql);

                        if (rs.next())
                        {
                                String sql2 = "delete from C_CourseContentTreeNode_Tab where parentID=" +
                                        nodeID;
                                stmt.execute(sql2);
                                LogUtil.debug("system",
                                        "[CourseContentDAOOracle]====================the sql string is : " +
                                                sql2);
                        }
                }
                catch (SQLException se)
                {
                        throw new CourseDAOSysException("SQLException while updating " +
                                "CourseContent;" + " Serial=" + nodeID + ":\n" + se);
                }
                finally
                {
                        try
                        {
                                if (stmt != null)
                                {
                                        closeResultSet(rs);
                                        stmt.close();
                                        closeConnection();
                                }
                        }
                        catch (Exception e)
                        {
                                e.printStackTrace();
                        }
                }
        }

        /**
         * Get the plan info via the unique nodeID
         *
         * @param nodeID
         * @return the prepared planForm, default is null
         * @throws CourseDAOSysException
         */
        public CourseContentForm getCourseContent(String nodeID)
                throws CourseDAOSysException
        {
                CourseContentForm ccf = null;
                Session session = null;

                //String hql = "from CourseContentModel where NodeID = " + nodeID;
                try
                {
                        CourseContentModel ccm = null;
                        Object oo = HibernateDAO.load(CourseContentModel.class,
                                new Integer(nodeID));

                        if (oo != null)
                        {
                                ccm = (CourseContentModel) oo;
                        }

                        ccf = new CourseContentForm(ccm);
                }
                catch (ULMSException e)
                {
                        e.printStackTrace();
                        throw new CourseDAOSysException(e);
                }
                finally
                {
                        try
                        {
                                HibernateUtil.releaseSession(session);
                        }
                        catch (HibernateException e)
                        {
                                LogUtil.debug("system", "Hibernate Exception" + e.getMessage());
                        }
                }

                return ccf;
        }

        /**
         * 返回nodetype类型的课程内容列表
         * @param relationID
         * @param nodeType
         * @return
         * @throws CourseDAOSysException
         */
        public List getCourseContentByNodeType(int relationID, int nodeType)
                throws CourseDAOSysException
        {
                CourseContentForm ccf = null;
                String hql = "from CourseContentModel where nodetype='" + nodeType +
                        "' and relationid = " + relationID+" order by nodeid desc";
                LogUtil.debug("system","[CourseContentDAOImpl]====================the sql string is : " +
                                hql);

                List list = null;
                try
                {
                        list = HibernateDAO.find(hql);
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new CourseDAOSysException("" + e);
                }

                return list;
        }

        public CourseContentForm getCourseContentByType(int relationID, int type)
                throws CourseDAOSysException
        {
                CourseContentForm ccf = null;
                String hql = "from CourseContentModel where type='" + type +
                        "' and RelationID = " + relationID;
                LogUtil.debug("system",
                        "[CourseContentDAOImpl]====================the sql string is : " +
                                hql);

                List list = null;

                try
                {
                        list = HibernateDAO.find(hql);
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new CourseDAOSysException("" + e);
                }

                if ((list != null) && (list.size() > 0))
                {
                        CourseContentModel ccm = (CourseContentModel) list.get(0);
                        ccf = new CourseContentForm(ccm);
                }

                return ccf;
        }

        /**
         * 返回用户所能看到的的所有课程文档列表
         *
         * @param userID
         * @return
         */
        public PagerList getAllCourseContentsByUser(int userID, int pageNo,
                                                    int pageSize) throws CourseDAOSysException
        {
                List pagerList = new ArrayList();
                Session session = null;
                PagerList pl = null;
                int totalCount = 0;
                String visitHql = " iscontent = '1' and  isuserful = '1' ";
                String hql = " from CourseContentModel where " + visitHql;

                //返回我的所有课程
                CourseMyCourseDAO courseMyCourseDAO = CourseMyCourseDAOFactory.getDAO();
                CourseListModel clm = courseMyCourseDAO.getMyAllCourses(userID);

                if (clm != null)
                {
                        List courseList = clm.getList();

                        if ((courseList != null) && !courseList.isEmpty())
                        {
                                hql += " and (";

                                for (int i = 0; i < courseList.size(); i++)
                                {
                                        CourseModel cm = (CourseModel) courseList.get(i);

                                        if (i == 0)
                                        {
                                                hql += ("relationid = " + cm.getCourseID());
                                        }
                                        else
                                        {
                                                hql += (" or relationid = " + cm.getCourseID());
                                        }
                                }

                                hql += ")";

                                try
                                {
                                        //得到数据
                                        java.util.Date nowDate = new java.util.Date();
                                        session = HibernateUtil.getSession();

                                        Query query = session.createQuery(hql +
                                                " order by relationid desc,nodeid desc");
                                        query.setMaxResults(pageSize);
                                        query.setFirstResult(pageSize * pageNo);
                                        pagerList = query.list();

                                        //计算总页数
                                        String hql_count = "select count(*) " + hql;
                                        query = session.createQuery(hql_count);
                                        totalCount = ((Integer) query.uniqueResult()).intValue();

                                        CourseContentModel cm = null;
                                        List formList = new ArrayList();

                                        for (int i = 0; i < pagerList.size(); i++)
                                        {
                                                cm = (CourseContentModel) pagerList.get(i);
                                                formList.add(new CourseContentForm(cm));
                                        }

                                        //初始化PagerList其它项
                                        pl = new PagerList();
                                        pl.setPageSize(pageSize);
                                        pl.setPageNo(pageNo);
                                        pl.setTotalCount(totalCount);
                                        pl.setPagerList(formList);
                                }
                                catch (HibernateException he)
                                {
                                        throw new AssignmentDAOSysException(
                                                "HibernateException while getAssignmentFormList: \n" +
                                                        he);
                                }
                        }
                }

                return pl;
        }

        /**
         * Get the courseContentList list by the parentID
         *
         * @param relationID
         * @param parentID
         * @return The prepared arraylist object,default size is 0
         * @throws CourseDAOSysException
         */
        public List getCourseContentList(int relationID, int parentID)
                throws CourseDAOSysException
        {
                Statement stmt = null;
                CourseContentForm ccf = null;
                List planList = new ArrayList();
                ResultSet rs = null;
                String sqlStr = "select  *  from C_CourseContentTreeNode_Tab where " +
                        " parentID=" + parentID + " and RelationID = " + relationID +
                        " order by iscontent asc,nodeid desc";

                //String hql="";
                try
                {
                        dbConnection = getConnection();
                        stmt = dbConnection.createStatement();
                        LogUtil.info("system",
                                "[CourseContentDAOImpl]====================the sql string is : " +
                                        sqlStr);
                        rs = stmt.executeQuery(sqlStr);

                        while (rs.next())
                        {
                                ccf = convertRs2Form(rs);
                                planList.add(ccf);
                        }
                }
                catch (SQLException se)
                {
                        throw new CourseDAOSysException("SQLException while updating " +
                                "CourseContent; " + "catalogID = " + relationID + " :\n" + se);
                }
                finally
                {
                        try
                        {
                                closeResultSet(rs);
                                stmt.close();
                                closeConnection();
                        }
                        catch (SQLException se)
                        {
                        }
                }

                return planList;
        }

        public List getCourseContentList(boolean isAdmin, int relationID,
                                         int parentID) throws CourseDAOSysException
        {
                Statement stmt = null;
                CourseContentForm ccf = null;
                List planList = new ArrayList();
                ResultSet rs = null;
                String sqlStr = "";

                if (isAdmin)
                {
                        sqlStr = "select *  from C_CourseContentTreeNode_Tab where " +
                                " parentID=" + parentID + " and RelationID = " + relationID +
                                " order by iscontent asc,nodeid desc";
                }
                else
                {
                        sqlStr = "select *  from C_CourseContentTreeNode_Tab where " +
                                " parentID=" + parentID + " and RelationID = " + relationID +
                                " and IsUserful = '1'" + " order by iscontent asc,nodeid desc";
                }

                if (parentID == -1)
                {
                        sqlStr = "select *  from C_CourseContentTreeNode_Tab";
                }

                try
                {
                        dbConnection = getConnection();
                        stmt = dbConnection.createStatement();
                        LogUtil.debug("system",
                                "[CourseContentDAOImpl]====================the sql string is : " +
                                        sqlStr);
                        rs = stmt.executeQuery(sqlStr);

                        while (rs.next())
                        {
                                ccf = convertRs2Form(rs);
                                planList.add(ccf);
                        }
                }
                catch (SQLException se)
                {
                        throw new CourseDAOSysException("SQLException while updating " +
                                "CourseContent; " + "catalogID = " + relationID + " :\n" + se);
                }
                finally
                {
                        try
                        {
                                closeResultSet(rs);
                                stmt.close();
                                closeConnection();
                        }
                        catch (SQLException se)
                        {
                        }
                }

                return planList;
        }

        /**
         * get list by forumID parentID
         *
         * @param courseID
         * @param parentID
         * @return
         * @throws CourseDAOSysException
         */
        public List getCourseContentTree(int courseID, int parentID)
                throws CourseDAOSysException
        {
                List root = getCourseContentList(courseID, parentID);
                int level = 0;

                for (Iterator it = root.iterator(); it.hasNext();)
                {
                        CourseContentForm ccf = (CourseContentForm) it.next();
                        ccf.setDepth(level);

                        int rootID = ccf.getNodeID();
                        courseContentTree.add(ccf);

                        if (isHasSubDiscuss(rootID))
                        {
                                getCourseContentTree(courseID, rootID, level);
                        }
                }

                return courseContentTree;
        }

        /**
         * @param courseID
         * @param parentID
         * @param currentLevel
         * @return
         * @throws CourseDAOSysException
         */
        public List getCourseContentTree(int courseID, int parentID,
                                         int currentLevel) throws CourseDAOSysException
        {
                List root = getCourseContentList(courseID, parentID);
                int level = currentLevel + 1;

                for (Iterator it = root.iterator(); it.hasNext();)
                {
                        CourseContentForm df = (CourseContentForm) it.next();
                        df.setDepth(level);

                        int rootID = df.getNodeID();
                        courseContentTree.add(df);

                        if (isHasSubDiscuss(rootID))
                        {
                                getCourseContentTree(courseID, rootID, level);
                        }
                }

                return courseContentTree;
        }

        /**
         * @param nodeID
         * @return
         * @throws CourseDAOSysException
         */
        public boolean isHasSubDiscuss(int nodeID)
                throws CourseDAOSysException /*{
           ResultSet rs = null;
           String sqlStr = "select COUNT(*) as subCount from C_CourseContentTreeNode_Tab where parentID = " + nodeID
           String sqlStr = "select COUNT(*) as subCount from CourseContentModel where parentID = " + nodeID;
           try
           {
                   LogUtil.debug("course", "[CourseContentDAOImpl]====================the sql string is : " + sqlStr);
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
                   throw new CourseDAOSysException("SQLException while select " + "CourseContent; nodeID=" + nodeID + ":\n" + se, se);
           }
           catch (ULMSSysException se)
           {
                   throw new CourseDAOSysException("SQLException while select " + "CourseContent; nodeID = " + nodeID + " :\n" + se, se);
           }
           finally
           {
                   try
                   {
                   }
                   catch (SQLException se)
                   {
                   }
           }
           return false;
           }*/
        {
                int totle = 0;
                String sqlStr = "select COUNT(*) as subCount from CourseContentModel where parentID = " +
                        nodeID;

                try
                {
                        LogUtil.debug("course",
                                "[CourseContentDAOImpl]====================the sql string is : " +
                                        sqlStr);
                        totle = HibernateDAO.count(sqlStr);

                        if (totle > 0)
                        {
                                return true;
                        }
                }
                catch (ULMSSysException se)
                {
                        throw new CourseDAOSysException("SQLException while select " +
                                "CourseContent; nodeID = " + nodeID + " :\n" + se, se);
                }

                return false;
        }

        public List getCourseContentList(int relationID)
                throws CourseDAOSysException
        {
                Statement stmt = null;
                CourseContentForm ccf = null;
                List planList = new ArrayList();
                ResultSet rs = null;
                String sqlStr = "select *  from C_CourseContentTreeNode_Tab where " +
                        " RelationID = " + relationID + " and type = '" +
                        CourseContentKeys.TYPE_COURSE + "' ";

                try
                {
                        dbConnection = getConnection();
                        stmt = dbConnection.createStatement();
                        LogUtil.debug("system",
                                "[CourseContentDAOImpl]====================the sql string is : " +
                                        sqlStr);
                        rs = stmt.executeQuery(sqlStr);

                        while (rs.next())
                        {
                                ccf = convertRs2Form(rs);
                                planList.add(ccf);
                        }
                }
                catch (SQLException se)
                {
                        throw new CourseDAOSysException("SQLException while updating " +
                                "CourseContent; " + "catalogID = " + relationID + " :\n" + se);
                }
                finally
                {
                        try
                        {
                                closeResultSet(rs);
                                stmt.close();
                                closeConnection();
                        }
                        catch (SQLException se)
                        {
                        }
                }

                return planList;
        }

        protected void closeConnection() throws CourseDAOSysException
        {
                try
                {
                        if ((dbConnection != null) && !dbConnection.isClosed())
                        {
                                dbConnection.close();
                        }
                }
                catch (SQLException se)
                {
                        throw new CourseDAOSysException("SQL Exception while closing " +
                                "DB connection : \n" + se);
                }
        }

        protected void closeResultSet(ResultSet result)
                throws CourseDAOSysException
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
                        throw new CourseDAOSysException("SQL Exception while closing " +
                                "Result Set : \n" + se);
                }
        }

        protected void closeStatement(PreparedStatement stmt)
                throws CourseDAOSysException
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
                        throw new CourseDAOSysException("SQL Exception while closing " +
                                "Statement : \n" + se);
                }
        }

        protected Connection getConnection() throws CourseDAOSysException
        {
                try
                {
                        dbConnection = DBUtil.getConnection();
                }
                catch (Exception se)
                {
                        throw new CourseDAOSysException("SQL Exception while getting " +
                                "DB connection : \n" + se);
                }

                return dbConnection;
        }

        /**
         * Convert the resultSet object to planForm
         *
         * @param rs the resultSet which needs to convert
         * @return the wanted planForm
         */
        private CourseContentForm convertRs2Form(ResultSet rs)
        {
                CourseContentForm ccf = new CourseContentForm();
                int rsIndex = 1;

                try
                {
                        ccf.setNodeID(rs.getInt(rsIndex++));
                        ccf.setNodeTitle(rs.getString(rsIndex++));
                        ccf.setRelationID(rs.getInt(rsIndex++));
                        ccf.setType(rs.getInt(rsIndex++));
                        ccf.setNodetype(rs.getInt(rsIndex++));
                        ccf.setIsUserful(rs.getInt(rsIndex++));
                        ccf.setIsOpenGuest(rs.getInt(rsIndex++));
                        ccf.setIsContent(rs.getInt(rsIndex++));
                        ccf.setLink(rs.getString(rsIndex++));
                        ccf.setIsHyperLink(rs.getInt(rsIndex++));
                        ccf.setIsView(rs.getInt(rsIndex++));
                        ccf.setDepth(rs.getInt(rsIndex++));
                        ccf.setParentID(rs.getInt(rsIndex++));
                        ccf.setCreateDate(rs.getDate(rsIndex++));
                        ccf.setLastModDate(rs.getDate(rsIndex++));
                        ccf.setOrderIndex(rs.getInt(rsIndex++));
                        ccf.setCatalog(rs.getString(rsIndex++));
                }
                catch (SQLException sql)
                {
                        sql.printStackTrace();
                }

                return ccf;
        }
}
