/**
 * ContentTreeDAOImpl.java.
 * Date: 2006-8-7
 *
 * Copyright (c) 2000-2006.Beijing Wenhua Online Sci-tech Development Co.,Ltd
 * All rights reserved.
 */
package com.ulearning.ulms.content.dao;

import com.ulearning.ulms.content.exceptions.ContentManageSysException;
import com.ulearning.ulms.content.form.ContentCatalogForm;
import com.ulearning.ulms.content.model.ContentTreeModel;
import com.ulearning.ulms.content.util.ContentManageConstants;
import com.ulearning.ulms.core.util.PagerUtil;
import com.ulearning.ulms.core.util.StringUtil;
import com.ulearning.ulms.course.exceptions.CourseSysException;
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
import java.util.Date;
import java.util.Iterator;
import java.util.List;


public class ContentTreeDAOImpl implements ContentTreeDAO
{
        /**
         * return the catalog's path list.
         * <br>在目录中导航时使用此方法定位。
         *
         * @param catalogID
         * @param relationID
         * @param type
         * @param aspID
         * @return
         * @throws ContentManageSysException
         */
        public List getCatalogPath(int catalogID, int relationID, int type,
                                   String aspID) throws ContentManageSysException
        {
                String title;
                ContentCatalogForm ccf = new ContentCatalogForm();
                Connection conn = null;
                Statement stmt = null;
                ResultSet rs = null;
                ArrayList catalogPathList = new ArrayList();

                try
                {
                        conn = DBUtil.getConnection();
                        stmt = conn.createStatement();

                        String queryStr1 = "select PARENTID,TITLE from R_CONTENTCATALOG_TAB" +
                                " WHERE CONTENTCATALOGID=" + catalogID + " and relationID=" +
                                relationID + " and type='" + type + "'";

                        if (relationID == -1)
                        {
                                queryStr1 = "select PARENTID,TITLE from R_CONTENTCATALOG_TAB" +
                                        " WHERE CONTENTCATALOGID=" + catalogID;
                        }

                        rs = stmt.executeQuery(queryStr1);

                        while (rs.next())
                        {
                                title = rs.getString("title");

                                ccf = new ContentCatalogForm(catalogID, title);
                                catalogPathList.add(ccf);

                                catalogID = rs.getInt("PARENTID");

                                if (catalogID == 0)
                                {
                                        break;
                                }

                                String queryStr = "select PARENTID,TITLE from R_CONTENTCATALOG_TAB" +
                                        " WHERE CONTENTCATALOGID=" + catalogID;

                                DBUtil.closeResultSet(rs);

                                rs = stmt.executeQuery(queryStr);
                        }
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

                return catalogPathList;
        }

        /**
         * 返回该目录下一级的所有内容（包括目录和内容）。
         *
         * @param catalogID
         * @param pageNo
         * @param pageSize
         * @param aspID
         * @return
         * @throws ContentManageSysException
         */
        public ContentTreeModel getTree(int catalogID, int relationID, int type,
                                        int pageNo, int pageSize, String aspID)
                throws ContentManageSysException
        {
                int totalCount = 0;
                int pageCount = 0;

                List contentCatelogList = null;
                List contentList = null;

                Session session = null;

                try
                {
                        session = HibernateUtil.getSession();

                        String cataloghql = "from ContentCatalogModel where parentID=" +
                                catalogID + " and relationID=" + relationID + " and type='" +
                                type + "'" + " order by CONTENTCATALOGID desc";
                        String hqlStr = " from ContentModel where parentID=" + catalogID +
                                " and relationID=" + relationID + " and type='" + type + "'" +
                                " order by contentID desc";
                        String hqlCountStr = "select count(*) from ContentModel where parentID=" +
                                catalogID + " and relationID=" + relationID + " and type='" +
                                type + "'";

                        if (relationID == -1)
                        {
                                cataloghql = "from ContentCatalogModel where parentID=" +
                                        catalogID + " order by CONTENTCATALOGID asc";
                                hqlStr = " from ContentModel where parentID=" + catalogID +
                                        " order by contentID desc";
                                hqlCountStr = "select count(*) from ContentModel where parentID=" +
                                        catalogID;
                        }

                        //get Catalog
                        Query q = session.createQuery(cataloghql);
                        contentCatelogList = q.list();

                        //get Content
                        q = session.createQuery(hqlStr);
                        q.setMaxResults(pageSize);
                        q.setFirstResult(pageSize * pageNo);
                        contentList = q.list();

                        //get totalCount
                        q = session.createQuery(hqlCountStr);

                        List hqlCountList = q.list();
                        Iterator it = hqlCountList.iterator();

                        while (it.hasNext())
                        {
                                Object row = (Object) it.next();
                                totalCount = ((Integer) row).intValue();
                        }

                        //get pageCount
                        pageCount = PagerUtil.getPageCount(totalCount, pageSize);
                }
                catch (HibernateException he)
                {
                        throw new ContentManageSysException(
                                "HibernateException while find: \n" + he);
                }
                finally
                {
                        try
                        {
                                HibernateUtil.releaseSession(session);
                        }
                        catch (HibernateException e)
                        {
                                throw new ContentManageSysException(
                                        "HibernateException while Hibernate update:  " + e, e);
                        }
                }

                ContentTreeModel ctm = new ContentTreeModel();
                ctm.setContentCatalogs(contentCatelogList);
                ctm.setContents(contentList);
                ctm.setPageCount(pageCount);
                ctm.setPageNo(pageNo);
                ctm.setPageSize(pageSize);
                ctm.setTotalCount(totalCount);

                return ctm;
        }

        /**
         * 返回该目录下一级的所有内容（包括目录和内容）。
         *
         * @param catalogID
         * @param pageNo
         * @param pageSize
         * @return
         * @throws ContentManageSysException
         */
        public ContentTreeModel getTree(int catalogID, int relationID, int type,
                                        int pageNo, int pageSize, String aspID, boolean isAdmin)
                throws ContentManageSysException
        {
                int totalCount = 0;
                int pageCount = 0;

                List contentCatelogList = null;
                List contentList = null;

                Session session = null;

                try
                {
                        session = HibernateUtil.getSession();

                        String cataloghql = "from ContentCatalogModel where parentID=" +
                                catalogID + " and relationID=" + relationID + " and type='" +
                                type + "' order by CONTENTCATALOGID desc";
                        String hqlStr = " from ContentModel where parentID=" + catalogID +
                                " and relationID=" + relationID + " and type='" + type + "'";
                        String hqlCountStr = "select count(*) from ContentModel where parentID=" +
                                catalogID + " and relationID=" + relationID + " and type='" +
                                type + "'";

                        if (relationID == -1)
                        {
                                cataloghql = "from ContentCatalogModel where parentID=" +
                                        catalogID + " order by CONTENTCATALOGID asc";
                                hqlStr = " from ContentModel where parentID=" + catalogID;
                                hqlCountStr = "select count(*) from ContentModel where parentID=" +
                                        catalogID;
                        }

                        if ((relationID == -1) && aspID.equals("-1")) //当relationID和aspID都为-1时则搜索所有asp中的资源
                        {
                                cataloghql = "from ContentCatalogModel where parentID=" +
                                        catalogID + " order by CONTENTCATALOGID asc";
                                hqlStr = " from ContentModel where parentID=" + catalogID;
                                hqlCountStr = "select count(*) from ContentModel where parentID=" +
                                        catalogID;
                        }

                        String visitSQL = "";

                        if (!isAdmin)
                        {
                                visitSQL = " and (isView='1' and (" +
                                        "(DisplayBeginDate IS NULL and DisplayEndDate IS NULL)" +
                                        "or (DisplayBeginDate IS NULL and DisplayEndDate IS NOT NULL and " +
                                        "DisplayEndDate >=:now_day)" +
                                        "or (DisplayBeginDate IS NOT NULL and DisplayBeginDate<=   :now_day and DisplayEndDate IS NULL)" +
                                        "or (DisplayBeginDate IS NOT NULL and DisplayBeginDate<=   :now_day and DisplayEndDate IS NOT NULL and " +
                                        " DisplayEndDate >= :now_day)" + " ))";
                        }

                        hqlStr = hqlStr + visitSQL + " order by contentID desc";
                        hqlCountStr = hqlCountStr + visitSQL;

                        //get Catalog
                        Query q = session.createQuery(cataloghql);
                        contentCatelogList = q.list();

                        //get Content
                        q = session.createQuery(hqlStr);

                        Date now = new Date();

                        if (!isAdmin)
                        {
                                q.setDate("now_day", now);
                        }

                        q.setMaxResults(pageSize);
                        q.setFirstResult(pageSize * pageNo);
                        contentList = q.list();

                        //get totalCount
                        q = session.createQuery(hqlCountStr);

                        if (!isAdmin)
                        {
                                q.setDate("now_day", now);
                        }

                        List hqlCountList = q.list();
                        Iterator it = hqlCountList.iterator();

                        while (it.hasNext())
                        {
                                Object row = (Object) it.next();
                                totalCount = ((Integer) row).intValue();
                        }

                        //get pageCount
                        pageCount = PagerUtil.getPageCount(totalCount, pageSize);
                }
                catch (HibernateException he)
                {
                        throw new ContentManageSysException(
                                "HibernateException while find: \n" + he);
                }
                finally
                {
                        try
                        {
                                HibernateUtil.releaseSession(session);
                        }
                        catch (HibernateException e)
                        {
                                throw new ContentManageSysException(
                                        "HibernateException while Hibernate update:  " + e, e);
                        }
                }

                ContentTreeModel ctm = new ContentTreeModel();
                ctm.setContentCatalogs(contentCatelogList);
                ctm.setContents(contentList);
                ctm.setPageCount(pageCount);
                ctm.setPageNo(pageNo);
                ctm.setPageSize(pageSize);
                ctm.setTotalCount(totalCount);

                return ctm;
        }

        /**
         * 返回该目录下一级的目录树。
         *
         * @param catalogID
         * @param relationID
         * @param type
         * @param aspID
         * @return
         * @throws ContentManageSysException
         */
        public List getSubCatalog(int catalogID, int relationID, int type,
                                  String aspID) throws ContentManageSysException
        {
                ArrayList catalogList = new ArrayList();

                String contenthql = "from ContentCatalogModel where parentID=" +
                        catalogID + " and relationID=" + relationID + " and type='" + type +
                        "' order by CONTENTCATALOGID desc";

                if (relationID == -1)
                {
                        contenthql = "from ContentCatalogModel where parentID=" +
                                catalogID + " order by CONTENTCATALOGID desc";
                }

                try
                {
                        catalogList = (ArrayList) HibernateDAO.find(contenthql);
                }
                catch (ContentManageSysException e)
                {
                        throw new ContentManageSysException(e);
                }

                return catalogList;
        }

        /**
         * 返回该目录下一级的内容附件的大小总和。
         * <p/>
         * todo:shid.
         *
         * @param catalogID
         * @param relationID
         * @param type
         * @param aspID
         * @return
         * @throws ContentManageSysException
         */
        public long sumSubCatalogContent(int catalogID, int relationID, int type,
                                         String aspID) throws ContentManageSysException
        {
                long totalCount = 0;
                Session session = null;

                String hql = "select sum(cm.contentSize) from ContentModel as cm where parentID=" +
                        catalogID + " and relationID=" + relationID + " and type='" + type +
                        "' and contentSize<>-1";

                try
                {
                        session = HibernateUtil.getSession();

                        Query q = session.createQuery(hql);

                        List hqlCountList = q.list();
                        Iterator it = hqlCountList.iterator();

                        while (it.hasNext())
                        {
                                Object row = (Object) it.next();

                                if (row == null)
                                {
                                        totalCount = 0;
                                }
                                else
                                {
                                        totalCount = StringUtil.parseLong(String.valueOf(row));

                                        //totalCount = ((Integer) row).longValue();
                                }
                        }
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

                return totalCount;
        }

        /**
         * 判断这个目录是否还包括子目录
         *
         * @param catalogID
         * @param aspID
         * @return
         * @throws ContentManageSysException
         */
        public boolean isHasSubCatalog(int catalogID, int relationID, int type,
                                       String aspID) throws ContentManageSysException
        {
                String contenthql = "from ContentCatalogModel where parentID=" +
                        catalogID + " and relationID=" + relationID + " and type='" + type +
                        "'";

                if (relationID == -1)
                {
                        contenthql = "from ContentCatalogModel where parentID=" +
                                catalogID;
                }

                List catalogList = null;

                try
                {
                        catalogList = (ArrayList) HibernateDAO.find(contenthql);
                }
                catch (ContentManageSysException e)
                {
                        throw new ContentManageSysException(e);
                }

                return !catalogList.isEmpty();
        }

        public ContentTreeModel searchCourse(String key, int type, int pageNo,
                                             int pageSize, boolean isAdmin, String aspID)
                throws ContentManageSysException
        {
                ContentTreeModel ctm = new ContentTreeModel();
                Connection conn = null;
                Statement stmt = null;
                ResultSet rs = null;

                List courseIDlt = new ArrayList();

                try
                {
                        conn = DBUtil.getConnection();
                        stmt = conn.createStatement();

                        String queryStr1 = "select courseID from C_Course_Tab" +
                                " WHERE orgid=" + aspID + " and (name like '%" + key +
                                "%' or keyword like '%" + key + "%')";

                        rs = stmt.executeQuery(queryStr1);

                        while (rs.next())
                        {
                                courseIDlt.add(rs.getString(1));
                        }
                }
                catch (SQLException se)
                {
                        se.printStackTrace();
                        throw new CourseSysException(se);
                }
                finally
                {
                        DBUtil.closeResultSet(rs);
                        DBUtil.closeStatement(stmt);
                        DBUtil.closeConnection(conn);
                }

                //System.out.println("courseIDlt.size() = " + courseIDlt.size());
                List lt = new ArrayList();
                int total = 0;

                for (int i = 0; i < courseIDlt.size(); i++)
                {
                        ContentTreeModel ctmtemp = search(key,
                                Integer.parseInt(courseIDlt.get(i).toString()), type,
                                pageNo, pageSize, isAdmin, aspID);
                        List temp = ctmtemp.getContents();
                        int size = temp.size();
                        total = total + ctmtemp.getTotalCount();

                        for (int j = 0; j < size; j++)
                        {
                                lt.add(temp.get(j));
                        }
                }

                ctm.setContents(lt);
                ctm.setTotalCount(total);

                //System.out.println("ctm.getContents().size() = " + ctm.getContents().size());
                return ctm;
        }

        public ContentTreeModel search(String key, int relationID, int type,
                                       int pageNo, int pageSize, boolean isAdmin, String aspID)
                throws ContentManageSysException
        {
                int totalCount = 0;
                int pageCount = 0;

                List contentList = new ArrayList();
                ContentTreeModel ctm = new ContentTreeModel();

                Session session = null;

                try
                {
                        session = HibernateUtil.getSession();

                        String cataloghql = "from ContentModel where relationID=" +
                                relationID + " and type='" + type + "'" +
                                " and (Identifier like '%" + key + "%'" + " or Title like '%" +
                                key + "%'" + " or Subject like '%" + key + "%'" +
                                " or Description like '%" + key + "%'" + " or Keyword like '%" +
                                key + "%')";

                        String hqlCountStr = "select count(*) from ContentModel where relationID=" +
                                relationID + " and type='" + type + "'" +
                                " and (Identifier like '%" + key + "%'" + " or Title like '%" +
                                key + "%'" + " or Subject like '%" + key + "%'" +
                                " or Description like '%" + key + "%'" + " or Keyword like '%" +
                                key + "%')";

                        if ((relationID == -1) || (type == -1))
                        {
                                cataloghql = "from ContentModel where " +
                                        " (Identifier like '%" + key + "%'" + " or Title like '%" +
                                        key + "%'" + " or Subject like '%" + key + "%'" +
                                        " or Description like '%" + key + "%'" +
                                        " or Keyword like '%" + key + "%'" + ")";

                                hqlCountStr = "select count(*) from ContentModel where " +
                                        " (Identifier like '%" + key + "%'" + " or Title like '%" +
                                        key + "%'" + " or Subject like '%" + key + "%'" +
                                        " or Description like '%" + key + "%'" +
                                        " or Keyword like '%" + key + "%'" + ")";
                        }

                        //当要搜索的内容为课程资源并且relationid==-1，表示他在content/manage/searchCourse.jsp这一页面，要在所有课程类资源中搜索
                        if ((relationID == -1) &&
                                (type == ContentManageConstants.COURSE_CONTENT_TYPE))
                        {
                                cataloghql = "from ContentModel where type='" + type + "'" +
                                        " and (Identifier like '%" + key + "%'" +
                                        " or Title like '%" + key + "%'" + " or Subject like '%" +
                                        key + "%'" + " or Description like '%" + key + "%'" +
                                        " or Keyword like '%" + key + "%'" + ")";

                                hqlCountStr = "select count(*) from ContentModel where type='" +
                                        type + "'" + " and (Identifier like '%" + key + "%'" +
                                        " or Title like '%" + key + "%'" + " or Subject like '%" +
                                        key + "%'" + " or Description like '%" + key + "%'" +
                                        " or Keyword like '%" + key + "%'" + ")";
                        }

                        if (!isAdmin)
                        {
                                cataloghql += (" and       (" +
                                        "(displaybegindate IS NULL and displayenddate IS NULL)" +
                                        "or (displaybegindate IS NULL and displayenddate IS NOT NULL and " +
                                        "displayenddate <=:end)" +
                                        "or (displaybegindate IS NOT NULL and displaybegindate>=:begin and displayenddate IS NULL)" +
                                        "or (displaybegindate IS NOT NULL and displaybegindate>=:begin and displayenddate IS NOT NULL and " +
                                        " displayenddate <=:end)" + ")");
                                hqlCountStr += (" and       (" +
                                        "(displaybegindate IS NULL and displayenddate IS NULL)" +
                                        "or (displaybegindate IS NULL and displayenddate IS NOT NULL and " +
                                        "displayenddate <=:end)" +
                                        "or (displaybegindate IS NOT NULL and displaybegindate>=:begin and displayenddate IS NULL)" +
                                        "or (displaybegindate IS NOT NULL and displaybegindate>=:begin and displayenddate IS NOT NULL and " +
                                        " displayenddate <=:end)" + ")");
                        }

                        cataloghql += " order by CONTENTID desc";

                        Query q = session.createQuery(cataloghql);

                        if (!isAdmin)
                        {
                                Date d = new Date();
                                q.setDate("begin", d);
                                q.setDate("end", d);
                        }

                        q.setMaxResults(pageSize);
                        q.setFirstResult(pageSize * pageNo);
                        contentList = q.list();

                        ctm.setContents(contentList);

                        //get totalCount
                        q = session.createQuery(hqlCountStr);

                        if (!isAdmin)
                        {
                                Date d = new Date();
                                q.setDate("begin", d);
                                q.setDate("end", d);
                        }

                        List hqlCountList = q.list();
                        Iterator it = hqlCountList.iterator();

                        while (it.hasNext())
                        {
                                Object row = (Object) it.next();
                                totalCount = ((Integer) row).intValue();
                        }

                        //get pageCount
                        pageCount = PagerUtil.getPageCount(totalCount, pageSize);

                        ctm.setPageSize(pageSize);
                        ctm.setPageCount(pageCount);
                        ctm.setTotalCount(totalCount);
                        ctm.setPageNo(pageNo);
                }
                catch (HibernateException e)
                {
                        throw new ContentManageSysException(e);
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

                //System.out.println("ctm.getTotalCount() = " + ctm.getTotalCount());
                //System.out.println("ctm.getContents().size() = " + ctm.getContents().size());
                return ctm;
        }
}
