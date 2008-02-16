/**
 * CourseContentTypeDAOImpl.java.
 * User: shid Date: 2005-9-7 11:08:34
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.content.dao;

import com.ulearning.ulms.content.exceptions.ContentManageSysException;
import com.ulearning.ulms.content.model.ContentModel;
import com.ulearning.ulms.content.model.CourseContentTypeModel;
import com.ulearning.ulms.util.DBUtil;
import com.ulearning.ulms.util.HibernateDAO;
import com.ulearning.ulms.util.HibernateUtil;

import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Query;
import net.sf.hibernate.Session;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.List;


public class CourseContentTypeDAOImpl implements CourseContentTypeDAO
{
        public boolean isExistCourseContentType(String contentType, String aspID)
                throws ContentManageSysException
        {
                boolean isExist = true;

                CourseContentTypeModel CTM = getCourseContentType(contentType, aspID);

                if ((CTM.getCourseContentType() == null) ||
                        (CTM.getCourseContentType().length() == 0))
                {
                        isExist = false;
                }

                return isExist;
        }

        public List getAllCourseContentType(String aspID)
                throws ContentManageSysException
        {
                List lt = new ArrayList();
                String sql_str = "from CourseContentTypeModel  order by orderIndex";

                try
                {
                        lt = HibernateDAO.find(sql_str);
                }
                catch (ContentManageSysException e)
                {
                        throw new ContentManageSysException(e);
                }

                return lt;
        }

        public CourseContentTypeModel getCourseContentTypeByCourseContentTypeID(
                int contentTypeID) throws ContentManageSysException
        {
                CourseContentTypeModel CourseContentTypeModel = new CourseContentTypeModel();
                List clList = new ArrayList();
                String sql_str = " from CourseContentTypeModel " +
                        "where CourseContentTypeID=" + contentTypeID;

                try
                {
                        clList = HibernateDAO.find(sql_str);
                }
                catch (ContentManageSysException e)
                {
                        throw new ContentManageSysException(e);
                }

                for (int i = 0; i < clList.size(); i++)
                {
                        CourseContentTypeModel = (CourseContentTypeModel) clList.get(i);
                }

                return CourseContentTypeModel;
        }

        public CourseContentTypeModel getCourseContentType(String contentType,
                                                           String aspID) throws ContentManageSysException
        {
                CourseContentTypeModel CourseContentTypeModel = new CourseContentTypeModel();
                List clList = new ArrayList();
                String sql_str = " from CourseContentTypeModel " +
                        "where CourseCONTENTTYPE ='" + contentType + "'";

                try
                {
                        clList = HibernateDAO.find(sql_str);
                }
                catch (ContentManageSysException e)
                {
                        throw new ContentManageSysException(e);
                }

                for (int i = 0; i < clList.size(); i++)
                {
                        CourseContentTypeModel = (CourseContentTypeModel) clList.get(i);
                }

                return CourseContentTypeModel;
        }

        public void addCourseContentType(CourseContentTypeModel cm)
                throws ContentManageSysException
        {
                try
                {
                        HibernateDAO.add(cm);
                }
                catch (ContentManageSysException e)
                {
                        throw new ContentManageSysException(e);
                }
        }

        public void updateCourseContentType(CourseContentTypeModel cm)
                throws ContentManageSysException
        {
                try
                {
                        HibernateDAO.update(cm);
                }
                catch (ContentManageSysException e)
                {
                        throw new ContentManageSysException(e);
                }
        }

        public void deleteCourseContentType(List l)
                throws ContentManageSysException
        {
                String sql_str = null;

                if (l.size() > 0)
                {
                        sql_str = "from CourseContentTypeModel where CourseCONTENTTYPEID =" +
                                l.get(0) + "";

                        for (int i = 1; i < l.size(); i++)
                        {
                                sql_str += ("or CourseCONTENTTYPEID = " + l.get(i) + "");
                        }
                }

                if (sql_str == null)
                {
                        return;
                }

                try
                {
                        HibernateDAO.delete(sql_str);
                }
                catch (ContentManageSysException e)
                {
                        throw new ContentManageSysException(e);
                }
        }

        public void deleteCourseContentType(int id)
                throws ContentManageSysException
        {
                String sql_str = "from CourseContentTypeModel where CourseCONTENTTYPEID =" +
                        id + "";

                try
                {
                        HibernateDAO.delete(sql_str);
                }
                catch (ContentManageSysException e)
                {
                        throw new ContentManageSysException(e);
                }
        }

        public boolean isUsingCourseContentType(int contentTypeID)
                throws ContentManageSysException
        {
                ContentModel contentModel = new ContentModel();
                List clList = new ArrayList();
                String sql_str = " from ContentModel " + "where CoursecontentTypeID =" +
                        contentTypeID;

                try
                {
                        clList = HibernateDAO.find(sql_str);
                }
                catch (ContentManageSysException e)
                {
                        throw new ContentManageSysException(e);
                }

                for (int i = 0; i < clList.size(); i++)
                {
                        contentModel = (ContentModel) clList.get(i);
                }

                if (contentModel.getContentID() > 0)
                {
                        return true;
                }
                else
                {
                        return false;
                }
        }

        public int getMaxOrderIndexFromCourseContentType(String aspID)
                throws ContentManageSysException
        {
                List lt = new ArrayList();
                String sql = "select max(ctm.orderIndex) from CourseContentTypeModel ctm";
                Session session = null;
                int orderIndex = 0;

                try
                {
                        session = HibernateUtil.getSession();

                        Query q = session.createQuery(sql);
                        lt = q.list();
                }
                catch (HibernateException he)
                {
                        throw new ContentManageSysException(he);
                }
                finally
                {
                        try
                        {
                                HibernateUtil.releaseSession(session);
                        }
                        catch (HibernateException e)
                        {
                                throw new ContentManageSysException(e);
                        }
                }

                for (int i = 0; i < lt.size(); i++)
                {
                        if (lt.get(i) != null) //用max查询只会得到一个结果
                        {
                                orderIndex = Integer.parseInt(lt.get(i).toString());
                        }
                        else
                        {
                                orderIndex = 0;
                        }
                }

                return orderIndex;
        }

        public void updateCourseContentTypeOrderIndex(int orderIndex, String aspID)
                throws ContentManageSysException
        {
                Connection conn = null;
                Statement stmt = null;
                ResultSet rs = null;

                try
                {
                        conn = DBUtil.getConnection();
                        stmt = conn.createStatement();

                        String sql = "update R_CourseContentType_Tab rct set rct.orderIndex=rct.orderIndex-1 where rct.orderIndex>" +
                                orderIndex;

                        stmt.executeUpdate(sql);
                }
                catch (SQLException se)
                {
                        throw new ContentManageSysException(se);
                }
                finally
                {
                        DBUtil.closeResultSet(rs);
                        DBUtil.closeStatement(stmt);
                        DBUtil.closeConnection(conn);
                }
        }

        public int getCourseContentTypeID(String name, String aspID)
                throws ContentManageSysException
        {
                String hql = "from CourseContentTypeModel where COURSECONTENTTYPE='" +
                        name + "'";
                List lt = new ArrayList();

                try
                {
                        lt = HibernateDAO.find(hql);
                }
                catch (ContentManageSysException e)
                {
                        throw new ContentManageSysException(e);
                }

                int a = -1;

                if (!lt.isEmpty())
                {
                        CourseContentTypeModel c = (CourseContentTypeModel) lt.get(0);
                        a = c.getCourseContentTypeID();
                }

                return a;
        }
}
