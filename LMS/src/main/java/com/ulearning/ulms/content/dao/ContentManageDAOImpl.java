/**
 * ContentManageDAOImpl.java.
 * Date: 2006-8-7
 *
 * Copyright (c) 2000-2006.Beijing Wenhua Online Sci-tech Development Co.,Ltd
 * All rights reserved.
 */
package com.ulearning.ulms.content.dao;

import com.ulearning.ulms.content.exceptions.ContentManageSysException;
import com.ulearning.ulms.content.form.ContentAdvSearchForm;
import com.ulearning.ulms.content.model.*;
import com.ulearning.ulms.content.util.ContentManageConstants;
import com.ulearning.ulms.core.util.PagerList;
import com.ulearning.ulms.core.util.PagerUtil;
import com.ulearning.ulms.core.util.Config;
import com.ulearning.ulms.core.security.bean.SecurityConstants;
import com.ulearning.ulms.util.DBUtil;
import com.ulearning.ulms.util.HibernateDAO;
import com.ulearning.ulms.util.HibernateUtil;
import net.sf.hibernate.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.StringEscapeUtils;


public class ContentManageDAOImpl implements ContentManageDAO
{
        protected static Log logger = LogFactory.getLog(ContentManageDAOImpl.class);

        /**
         * 返回特定类型的资源列表。
         *
         * @throws ContentManageSysException
         */
        public PagerList getContentsByContentType(int contentType, int pageNo,
                                                  int pageSize) throws ContentManageSysException
        {
                int cruentIndex = pageNo * pageSize;
                PagerList pl = new PagerList();
                List list = null;

                try
                {
                        //取数据
                        String hql = "from ContentModel where ( contentTypeID='" +
                                contentType + "' and " + "isview='" +
                                ContentManageConstants.doView + "') ";
                        list = HibernateDAO.find(hql, cruentIndex, pageSize);

                        int totalCount = 0;

                        //取总记录数
                        String hqlCount = "select count(*) " + hql;
                        List hqlCountList = HibernateDAO.find(hqlCount);
                        Iterator it = hqlCountList.iterator();

                        while (it.hasNext())
                        {
                                Object row = (Object) it.next();
                                totalCount = ((Integer) row).intValue();
                        }

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
         * 根据传入的hql和开始日期，结束日期，pageSize,pageNumber查询
         *
         * @param hql
         * @param beginDate
         * @param endDate
         * @param pageSize
         * @param pageNumber
         * @return
         * @throws ContentManageSysException
         */
        public ContentAdvSearchForm advSearch(String hql, Date beginDate,
                                              Date endDate, int pageSize, int pageNumber)
                throws ContentManageSysException
        {
                List lt = new ArrayList();
                Session session = null;
                int pageCount = 0;
                int totalCount = 0;

                try
                {
                        session = HibernateUtil.getSession();

                        Query q = session.createQuery(hql);

                        if ((beginDate != null) && (endDate != null))
                        {
                                q.setDate("begin", beginDate);
                                q.setDate("end", endDate);
                        }

                        q.setMaxResults(pageSize);
                        q.setFirstResult(pageSize * pageNumber);
                        lt = q.list();

                        int index = hql.lastIndexOf("order by");

                        if (index != -1)
                        {
                                hql = hql.substring(0, index);
                        }

                        String count = "select count(*) " + hql;
                        q = session.createQuery(count);

                        if ((beginDate != null) && (endDate != null))
                        {
                                q.setDate("begin", beginDate);
                                q.setDate("end", endDate);
                        }

                        List hqlCountList = q.list();
                        Iterator it = hqlCountList.iterator();

                        while (it.hasNext())
                        {
                                Object row = (Object) it.next();
                                totalCount = ((Integer) row).intValue();
                        }

                        pageCount = PagerUtil.getPageCount(totalCount, pageSize);
                }
                catch (HibernateException e)
                {
                        throw new ContentManageSysException(e);
                }
                catch (ContentManageSysException e)
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

                ContentAdvSearchForm c = new ContentAdvSearchForm();
                c.setPageSize(pageSize);
                c.setPageCount(pageCount);
                c.setPageNo(pageNumber);
                c.setTotalCount(totalCount);
                c.setContentModels(lt);

                c.setStartDate(beginDate);
                c.setOverDate(endDate);
                c.setHql(hql);

                return c;
        }

        public void move(List moveCatalogs, List moveContent, int catalogID,
                         String aspID) throws ContentManageSysException
        {
                for (int i = 0; i < moveCatalogs.size(); i++)
                {
                        ContentCatalogModel ccm = getContentCatalog(moveCatalogs.get(i)
                                .toString());
                        ccm.setParentID(catalogID);
                        updateContentCatalog(ccm);
                }

                for (int i = 0; i < moveContent.size(); i++)
                {
                        ContentModel cm = getContent(Integer.parseInt(
                                moveContent.get(i).toString()));
                        cm.setParentID(catalogID);
                        updateContent(cm);
                }
        }

        /**
         * @param userID
         * @return
         */
        public List getContentListByUser(int userID)
        {
                List lt = new ArrayList();
                ContentModel ccm = new ContentModel();

                String hql = "from ContentModel where userID=" + userID +
                        " and parentID!=122";

                try
                {
                        lt = HibernateDAO.find(hql);
                }
                catch (ContentManageSysException e)
                {
                        throw new ContentManageSysException(e);
                }

                return lt;
        }

        //***************************  ContentModel  *******************************//
        public ContentModel getContent(int contentID)
                throws ContentManageSysException
        {
                List lt = new ArrayList();
                ContentModel ccm = new ContentModel();

                String hql = "from ContentModel where CONTENTID=" + contentID;

                try
                {
                        lt = HibernateDAO.find(hql);
                }
                catch (ContentManageSysException e)
                {
                        throw new ContentManageSysException(e);
                }

                for (int i = 0; i < lt.size(); i++)
                {
                        ccm = (ContentModel) lt.get(i);
                }

                return ccm;
        }

        public List getContent(int parentID, int relationID, int type, String aspID)
                throws ContentManageSysException
        {
                List lt = new ArrayList();

                String hql = "from ContentModel where parentID=" + parentID + "" +
                        " and relationID=" + relationID + "" + " and type='" + type + "'";

                try
                {
                        lt = HibernateDAO.find(hql);
                }
                catch (ContentManageSysException e)
                {
                        throw new ContentManageSysException(e);
                }

                return lt;
        }

        /**
         * add a content.
         *
         * @param cm
         * @throws com.ulearning.ulms.content.exceptions.ContentManageSysException
         *
         */
        public void addContent(ContentModel cm) throws ContentManageSysException
        {
                //这里需要对Clob特殊处理
                cm.setContentClob(Hibernate.createClob(" "));
                Session session = null;
                java.io.Writer pw = null;
                try
                {

                        session = HibernateUtil.getSession();

                        session.save(cm);
                        session.flush();
                        session.refresh(cm, LockMode.UPGRADE);
                        oracle.sql.CLOB clob = (oracle.sql.CLOB) cm.getContentClob();

                        pw = clob.getCharacterOutputStream();
                        if (cm.getTempClobString() != null)
                        {
                                pw.write(cm.getTempClobString());
                        }
                        else
                        {
                                pw.write("");
                        }
                        pw.flush();
                        //pw.close();
                        session.flush();
                        session.connection().commit();
                }
                catch (MappingException e)
                {
                        throw new ContentManageSysException("MappingException while Hibernate add:  " + e, e);
                }
                catch (HibernateException e)
                {
                        throw new ContentManageSysException("HibernateException while Hibernate add:  " + e, e);
                }
                catch (SQLException e)
                {
                        throw new ContentManageSysException("SQLException while Hibernate add:  " + e, e);
                }
                catch (IOException e)
                {
                        throw new ContentManageSysException("SQLException while Hibernate add:  " + e, e);
                }
                finally
                {
                        try
                        {
                                pw.close();
                        }
                        catch (Exception e)
                        {
                        }

                        try
                        {
                                HibernateUtil.releaseSession(session);
                        }
                        catch (HibernateException e)
                        {
                                throw new ContentManageSysException("HibernateException while Hibernate save:  " + e, e);
                        }
                }
        }

        /**
         * update a content.
         *
         * @param cm
         * @throws ContentManageSysException
         */
        public void updateContent(ContentModel cm) throws ContentManageSysException
        {
                //这里需要对Clob特殊处理
                cm.setContentClob(Hibernate.createClob(" "));
                Session session = null;
                try
                {

                        session = HibernateUtil.getSession();
                        session.clear();
                        session.update(cm);
                        session.flush();
                        session.refresh(cm, LockMode.UPGRADE);
                        oracle.sql.CLOB clob = (oracle.sql.CLOB) cm.getContentClob();

                        java.io.Writer pw = clob.getCharacterOutputStream();
                        if (cm.getTempClobString() != null)
                        {
                                pw.write(cm.getTempClobString());
                        }
                        else
                        {
                                pw.write("");
                        }
                        pw.flush();
                        pw.close();
                        session.flush();
                        session.connection().commit();
                }
                catch (MappingException e)
                {
                        throw new ContentManageSysException("MappingException while Hibernate add:  " + e, e);
                }
                catch (HibernateException e)
                {
                        throw new ContentManageSysException("HibernateException while Hibernate add:  " + e, e);
                }
                catch (SQLException e)
                {
                        throw new ContentManageSysException("SQLException while Hibernate add:  " + e, e);
                }
                catch (IOException e)
                {
                        throw new ContentManageSysException("SQLException while Hibernate add:  " + e, e);
                }
                finally
                {
                        try
                        {
                                HibernateUtil.releaseSession(session);
                        }
                        catch (HibernateException e)
                        {
                                throw new ContentManageSysException("HibernateException while Hibernate update:  " + e, e);
                        }
                }
        }

        /**
         * delete the contents.
         *
         * @param l the List that contain the Integer contentIDs
         * @throws com.ulearning.ulms.content.exceptions.ContentManageSysException
         *
         */
        public void deleteContent(List l) throws ContentManageSysException
        {
                String sql_str = null;

                if (l.size() > 0)
                {
                        sql_str = "from ContentModel where contentID = " + l.get(0);

                        for (int i = 1; i < l.size(); i++)
                        {
                                sql_str += (" or contentID = " + l.get(i));
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

        /**
         * delete the content.
         *
         * @param contentID
         * @throws ContentManageSysException
         */
        public void deleteContent(int contentID) throws ContentManageSysException
        {
                String sql_str = "from ContentModel where contentID = " + contentID;

                try
                {
                        HibernateDAO.delete(sql_str);
                }
                catch (ContentManageSysException e)
                {
                        throw new ContentManageSysException(e);
                }
        }

        //***************************  ContentCatalogModel  *******************************//
        public ContentCatalogModel getContentCatalog(String contentCatalogID)
                throws ContentManageSysException
        {
                List lt = new ArrayList();
                ContentCatalogModel ccm = new ContentCatalogModel();

                String hql = "from ContentCatalogModel where CONTENTCATALOGID=" +
                        contentCatalogID;

                try
                {
                        lt = HibernateDAO.find(hql);
                }
                catch (ContentManageSysException e)
                {
                        throw new ContentManageSysException(e);
                }

                for (int i = 0; i < lt.size(); i++)
                {
                        ccm = (ContentCatalogModel) lt.get(i);
                }

                return ccm;
        }

        public List getContentCatalog(int parentID, int relationID, int type,
                                      String aspID) throws ContentManageSysException
        {
                List lt = new ArrayList();

                String hql = "from ContentCatalogModel where parentID=" + parentID +
                        "" + " and relationID=" + relationID + "" + " and type='" + type +
                        "'";

                try
                {
                        lt = HibernateDAO.find(hql);
                }
                catch (ContentManageSysException e)
                {
                        throw new ContentManageSysException(e);
                }

                return lt;
        }

        public boolean isHasSameCatalogByParentID(String catalogName, int parentID,
                                                  int relationID, int type, String aspID)
                throws ContentManageSysException
        {
                List lt = new ArrayList();

                String hql = "from ContentCatalogModel where parentID=" + parentID +
                        " and relationID=" + relationID + " and type='" + type + "'";

                try
                {
                        lt = HibernateDAO.find(hql);
                }
                catch (ContentManageSysException e)
                {
                        throw new ContentManageSysException(e);
                }

                for (int i = 0; i < lt.size(); i++)
                {
                        ContentCatalogModel ccm = (ContentCatalogModel) lt.get(i);

                        if (ccm.getTitle().equals(catalogName))
                        {
                                return true;
                        }
                }

                return false;
        }

        public boolean isHasSameCatalogByParentID_update(String catalogName,
                                                         int parentID, int catalogID, int relationID, int type, String aspID)
                throws ContentManageSysException
        {
                List lt = new ArrayList();

                String hql = "from ContentCatalogModel where parentID=" + parentID +
                        " and relationID=" + relationID + " and type='" + type +
                        "' and CONTENTCATALOGID<>" + catalogID;

                try
                {
                        lt = HibernateDAO.find(hql);
                }
                catch (ContentManageSysException e)
                {
                        throw new ContentManageSysException(e);
                }

                for (int i = 0; i < lt.size(); i++)
                {
                        ContentCatalogModel ccm = (ContentCatalogModel) lt.get(i);

                        if (ccm.getTitle().equals(catalogName))
                        {
                                return true;
                        }
                }

                return false;
        }

        /**
         * add a ContentCatalogModel.
         *
         * @param cm
         * @throws ContentManageSysException
         */
        public void addContentCatalog(ContentCatalogModel cm)
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

        /**
         * update a ContentCatalogModel.
         *
         * @param cm
         * @throws ContentManageSysException
         */
        public void updateContentCatalog(ContentCatalogModel cm)
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

        /**
         * delete the ContentCatalogModel.
         *
         * @param l the List that contain the Integer contentCatalogIDs
         * @throws ContentManageSysException
         */
        public void deleteContentCatalog(List l) throws ContentManageSysException
        {
                String sql_str = null;

                if (l.size() > 0)
                {
                        sql_str = "from ContentCatalogModel where CONTENTCATALOGID =" +
                                l.get(0);

                        for (int i = 1; i < l.size(); i++)
                        {
                                sql_str += (" or CONTENTCATALOGID ='" + l.get(i) + "'");
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

        /**
         * elete the Catalog..
         *
         * @param contentCatalogID
         * @throws ContentManageSysException
         */
        public void deleteContentCatalog(int contentCatalogID)
                throws ContentManageSysException
        {
                String sql_str = "from ContentCatalogModel where CONTENTCATALOGID =" +
                        contentCatalogID;

                try
                {
                        HibernateDAO.delete(sql_str);
                }
                catch (ContentManageSysException e)
                {
                        throw new ContentManageSysException(e);
                }
        }

        //***************************  ContentConfig  *******************************//
        public ContentConfigModel getContentConfig(String type, String relationID)
                throws ContentManageSysException
        {
                List lt = new ArrayList();
                ContentConfigModel ccm = null;
                String sql_str = "from ContentConfigModel where TYPE='" + type +
                        "' and RELATIONID=" + relationID + "";

                try
                {
                        lt = HibernateDAO.find(sql_str);
                }
                catch (ContentManageSysException e)
                {
                        throw new ContentManageSysException(e);
                }

                for (int i = 0; i < lt.size(); i++)
                {
                        ccm = (ContentConfigModel) lt.get(i);
                }

                return ccm;
        }

        /**
         * add a ContentConfig.
         *
         * @param cm
         * @throws com.ulearning.ulms.content.exceptions.ContentManageSysException
         *
         */
        public void addContentConfig(ContentConfigModel cm)
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

        /**
         * update a ContentConfig.
         *
         * @param cm
         * @throws ContentManageSysException
         */
        public void updateContentConfig(ContentConfigModel cm)
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

        /*public void deleteContentConfig(List l)
       throws ContentManageSysException
       {
               String sql_str = null;
               if (l.size() > 0)
               {
                       sql_str = "from ContentConfigModel where ( TYPE ='" + ((ContentConfigModelPK) l.get(0)).getType() + "' and RELATIONID=" + ((ContentConfigModelPK) l.get(0)).getRelationID() + " )";
                       for (int i = 1; i < l.size(); i++)
                       {
                               sql_str += "or ( TYPE ='" + ((ContentConfigModelPK) l.get(i)).getType() + "' and RELATIONID=" + ((ContentConfigModelPK) l.get(i)).getRelationID() + " )";
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
       } */
        public int getMaxContentConfigID() throws ContentManageSysException
        {
                List lt = new ArrayList();
                String sql = "select max(ctm.contentConfigID) from ContentConfigModel ctm";
                Session session = null;

                //int orderIndex = 0;
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

                String ccm = null;

                if (!lt.isEmpty())
                {
                        ccm = lt.get(0).toString();
                }

                if (ccm != null)
                {
                        return Integer.parseInt(ccm);
                }
                else
                {
                        return -1;
                }
        }

        //***************************  ContentLanguageModel  *******************************//
        public ContentLanguageModel getContentLanguage(int languageID)
                throws ContentManageSysException
        {
                ContentLanguageModel contentLanguageModel = null;
                List clList = new ArrayList();
                String sql_str = " from ContentLanguageModel " + "where LANGUAGEID = " +
                        languageID;

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
                        contentLanguageModel = (ContentLanguageModel) clList.get(i);
                }

                return contentLanguageModel;
        }

        public List getAllContentLanguage(String aspID)
                throws ContentManageSysException
        {
                List clList = new ArrayList();
                String sql_str = " from ContentLanguageModel  order by languageid asc";

                try
                {
                        clList = HibernateDAO.find(sql_str);
                }
                catch (ContentManageSysException e)
                {
                        throw new ContentManageSysException(e);
                }

                return clList;
        }

        /**
         * add a ContentLanguageModel.
         *
         * @param cm
         * @throws com.ulearning.ulms.content.exceptions.ContentManageSysException
         *
         */
        public void addContentLanguage(ContentLanguageModel cm)
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

        /**
         * update a ContentLanguageModel.
         *
         * @param cm
         * @throws ContentManageSysException
         */
        public void updateContentLanguage(ContentLanguageModel cm)
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

        /**
         * delete the ContentLanguageModels.
         *
         * @param l the List that contain the Integer LANGUAGEIDs
         * @throws com.ulearning.ulms.content.exceptions.ContentManageSysException
         *
         */
        public void deleteContentLanguage(List l) throws ContentManageSysException
        {
                String sql_str = null;

                if (l.size() > 0)
                {
                        sql_str = "from ContentLanguageModel where LANGUAGEID = " +
                                l.get(0);

                        for (int i = 1; i < l.size(); i++)
                        {
                                sql_str += ("or LANGUAGEID = '" + l.get(i) + "'");
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

        public boolean isExistLanguage(String language, String aspID)
                throws ContentManageSysException
        {
                ContentLanguageModel contentLanguageModel = new ContentLanguageModel();
                List clList = new ArrayList();
                String sql_str = " from ContentLanguageModel " + "where LANGUAGE ='" +
                        language + "'";

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
                        contentLanguageModel = (ContentLanguageModel) clList.get(i);
                }

                if ((contentLanguageModel.getLanguage() != null) &&
                        (contentLanguageModel.getLanguage().length() != 0))
                {
                        return true;
                }
                else
                {
                        return false;
                }
        }

        /**
         * 判断contentLanage是否被使用
         *
         * @param languageID
         * @throws ContentManageSysException
         */
        public boolean isUsingContentLanguage(int languageID)
                throws ContentManageSysException
        {
                ContentModel contentModel = new ContentModel();
                List clList = new ArrayList();
                String sql_str = " from ContentModel " + "where LANGUAGEID =" +
                        languageID;

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

        //***************************  ContentServerModel  *******************************//

        /**
         * add a ContentServerModel.
         *
         * @param cm
         * @throws com.ulearning.ulms.content.exceptions.ContentManageSysException
         *
         */
        public void addContentServer(ContentServerModel cm)
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

        /**
         * update a ContentServerModel.
         *
         * @param cm
         * @throws ContentManageSysException
         */
        public void updateContentServer(ContentServerModel cm)
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

        /**
         * delete the ContentServerModels.
         *
         * @param l the List that contain the Integer CONTENTSERVERIDs
         * @throws com.ulearning.ulms.content.exceptions.ContentManageSysException
         *
         */
        public void deleteContentServer(List l) throws ContentManageSysException
        {
                String sql_str = null;

                if (l.size() > 0)
                {
                        sql_str = "from ContentServerModel where CONTENTSERVERID = " +
                                l.get(0);

                        for (int i = 1; i < l.size(); i++)
                        {
                                sql_str += ("or CONTENTSERVERID = '" + l.get(i) + "'");
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

        public boolean isExistContentServer(String name, String aspID)
                throws ContentManageSysException
        {
                ContentServerModel contentServerModel = new ContentServerModel();
                List clList = new ArrayList();
                String sql_str = " from ContentServerModel " + "where name ='" + name +
                        "'";

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
                        contentServerModel = (ContentServerModel) clList.get(i);
                }

                if ((contentServerModel.getName() != null) &&
                        (contentServerModel.getName().length() != 0))
                {
                        return true;
                }
                else
                {
                        return false;
                }
        }

        public boolean isExistContentServer(int congtentServerID, String name,
                                            String aspID) throws ContentManageSysException
        {
                ContentServerModel contentServerModel = new ContentServerModel();
                List clList = new ArrayList();
                String sql_str = " from ContentServerModel " + "where name ='" + name +
                        "' and CONTENTSERVERID<>'" + congtentServerID + "'";

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
                        contentServerModel = (ContentServerModel) clList.get(i);
                }

                if ((contentServerModel.getName() != null) &&
                        (contentServerModel.getName().length() != 0))
                {
                        return true;
                }
                else
                {
                        return false;
                }
        }

        public List getAllContentServer(String aspID)
                throws ContentManageSysException
        {
                List clList = new ArrayList();
                String sql_str = " from ContentServerModel";

                try
                {
                        clList = HibernateDAO.find(sql_str);
                }
                catch (ContentManageSysException e)
                {
                        throw new ContentManageSysException(e);
                }

                return clList;
        }

        public ContentServerModel getContentServer(int contentServerID)
                throws ContentManageSysException
        {
                ContentServerModel contentServerModel = new ContentServerModel();
                List clList = new ArrayList();
                String sql_str = " from ContentServerModel " +
                        "where CONTENTSERVERID ='" + contentServerID + "'";

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
                        contentServerModel = (ContentServerModel) clList.get(i);
                }

                return contentServerModel;
        }

        public ContentServerModel getDefaultContentServer(String aspID)
                throws ContentManageSysException
        {
                ContentServerModel contentServerModel = new ContentServerModel();
                List clList = new ArrayList();
                String sql_str = " from ContentServerModel " + "where isDefault ='1'";

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
                        contentServerModel = (ContentServerModel) clList.get(i);
                }

                return contentServerModel;
        }

        //***************************  ContentTypeModel  *******************************//
        public boolean isExistContentType(String contentType, String aspID)
                throws ContentManageSysException
        {
                boolean isExist = true;

                ContentTypeModel CTM = getContentType(contentType, aspID);

                if ((CTM.getContentType() == null) ||
                        (CTM.getContentType().length() == 0))
                {
                        isExist = false;
                }

                return isExist;
        }

        public int getMaxOrderIndexFromContentType(String aspID)
                throws ContentManageSysException
        {
                List lt = new ArrayList();
                String sql = "select max(ctm.orderIndex) from ContentTypeModel ctm";
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

        public List getAllContentType(String aspID)
                throws ContentManageSysException
        {
                List lt = new ArrayList();
                String sql_str = "from ContentTypeModel order by orderindex";

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

        public ContentTypeModel getContentTypeByContentTypeID(int contentTypeID)
                throws ContentManageSysException
        {
                ContentTypeModel contentTypeModel = new ContentTypeModel();
                List clList = new ArrayList();
                String sql_str = " from ContentTypeModel " + "where CONTENTTYPEID =" +
                        contentTypeID + "";

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
                        contentTypeModel = (ContentTypeModel) clList.get(i);
                }

                return contentTypeModel;
        }

        public ContentTypeModel getContentType(String contentType, String aspID)
                throws ContentManageSysException
        {
                ContentTypeModel contentTypeModel = new ContentTypeModel();
                List clList = new ArrayList();
                String sql_str = " from ContentTypeModel " + "where CONTENTTYPE ='" +
                        contentType + "'";

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
                        contentTypeModel = (ContentTypeModel) clList.get(i);
                }

                return contentTypeModel;
        }

        /**
         * add a ContentTypeModel.
         *
         * @param cm
         * @throws ContentManageSysException
         */
        public void addContentType(ContentTypeModel cm)
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

        /**
         * update a ContentTypeModel.
         *
         * @param cm
         * @throws ContentManageSysException
         */
        public void updateContentType(ContentTypeModel cm)
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

        public void updateContentTypeOrderIndex(int orderIndex, String aspID)
                throws ContentManageSysException
        {
                Connection conn = null;
                Statement stmt = null;
                ResultSet rs = null;

                try
                {
                        conn = DBUtil.getConnection();
                        stmt = conn.createStatement();

                        String sql = "update R_CONTENTTYPE_TAB rct set rct.orderIndex=rct.orderIndex-1 where rct.orderIndex>" +
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

        /**
         * delete the ContentTypeModels.
         *
         * @param l the List that contain the String CONTENTTYPEs
         * @throws ContentManageSysException
         */
        public void deleteContentType(List l) throws ContentManageSysException
        {
                String sql_str = null;

                if (l.size() > 0)
                {
                        sql_str = "from ContentTypeModel where CONTENTTYPEID =" + l.get(0) +
                                "";

                        for (int i = 1; i < l.size(); i++)
                        {
                                sql_str += ("or CONTENTTYPEID = " + l.get(i) + "");
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

                /*for (int i = 0; i < l.size(); i++)
               {
                       String sql = "select ctm.* from R_CONTENTTYPE_TAB as ctm where ctm.CONTENTTYPEID>" + l.get(i).toString();
                       Session session = null;
                       try
                       {
                               logger.info(i);
                               session = HibernateUtil.getSession();
                               //session.createQuery(sql);
                               Query query = session.createSQLQuery(sql,"ctm",ContentTypeModel.class);
                               List lt = query.list();
                              logger.info("ddddddddddddddddd   "+lt.size());
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
               } */
        }

        public void deleteContentType(int id) throws ContentManageSysException
        {
                String sql_str = "from ContentTypeModel where CONTENTTYPEID =" + id +
                        "";

                try
                {
                        HibernateDAO.delete(sql_str);
                }
                catch (ContentManageSysException e)
                {
                        throw new ContentManageSysException(e);
                }
        }

        /**
         * 判断contentType是否被使用
         *
         * @param contentTypeID
         * @throws ContentManageSysException
         */
        public boolean isUsingContentType(int contentTypeID)
                throws ContentManageSysException
        {
                ContentModel contentModel = new ContentModel();
                List clList = new ArrayList();
                String sql_str = " from ContentModel " + "where contentTypeID =" +
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

        //***************************  ScormItemModel  *******************************//
        /**
         * add a ScormItemModel.
         *
         * @param cm
         * @throws ContentManageSysException
         */
        public void addScormItem(ScormItemModel cm)
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

        /**
         * update a ScormItemModel.
         *
         * @param cm
         * @throws ContentManageSysException
         */
        public void updateScormItem(ScormItemModel cm)
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

        /**
         * delete the ScormItemModels.
         *
         * @param l the List that contain the String ITEMIDs
         * @throws ContentManageSysException
         */
        public void deleteScormItem(List l) throws ContentManageSysException
        {
                String sql_str = null;

                if (l.size() > 0)
                {
                        sql_str = "from ScormItemModel where ITEMID = " + l.get(0);

                        for (int i = 1; i < l.size(); i++)
                        {
                                sql_str += ("or ITEMID = '" + l.get(i) + "'");
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

        //***************************  ScormUserScoModel  *******************************//
        /**
         * add a ScormUserScoModel.
         *
         * @param cm
         * @throws ContentManageSysException
         */
        public void addScormUserSco(ScormUserScoModel cm)
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

        /**
         * update a ScormUserScoModel.
         *
         * @param cm
         * @throws ContentManageSysException
         */
        public void updateScormUserSco(ScormUserScoModel cm)
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

        /**
         * delete the ScormUserScoModels.
         *
         * @param l the List that contain the ScormUserScoModelPK
         * @throws ContentManageSysException
         */
        public void deleteScormUserSco(List l) throws ContentManageSysException
        {
                String sql_str = null;

                if (l.size() > 0)
                {
                        sql_str = "from ScormUserScoModel where ( USERID ='" +
                                ((ScormUserScoModelPK) l.get(0)).getUserID() + "' and SCOID='" +
                                ((ScormUserScoModelPK) l.get(0)).getScoID() + "' )";

                        for (int i = 1; i < l.size(); i++)
                        {
                                sql_str += ("or ( USERID ='" +
                                        ((ScormUserScoModelPK) l.get(i)).getUserID() + "' and SCOID='" +
                                        ((ScormUserScoModelPK) l.get(i)).getScoID() + "' )");
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

        /**
         * 返回特定目录下的资源列表。
         *
         * @throws ContentManageSysException
         */
        public PagerList getContentsByCatalog(int parentID, int pageNo,
                                              int pageSize) throws ContentManageSysException
        {
                int cruentIndex = pageNo * pageSize;
                PagerList pl = new PagerList();
                List list = null;

                try
                {
                        //取数据
                        String hql = "from ContentModel where ( parentID='" +
                                parentID + "' and " + "isview='" +
                                ContentManageConstants.doView + "') order by contentID desc";
                        list = HibernateDAO.find(hql, cruentIndex, pageSize);

                        int totalCount = 0;

                        //取总记录数
                        String hqlCount = "select count(*) " + hql;
                        List hqlCountList = HibernateDAO.find(hqlCount);
                        Iterator it = hqlCountList.iterator();

                        if (it.hasNext())
                        {
                                Object row = it.next();
                                totalCount = ((Integer) row).intValue();
                        }

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
         * 返回资源列表
         *
         * @param type            -1返回所有
         * @param relationID      所说，-1返回所有
         * @param relationIDs     可以包含多个机构， -1返回所有
         * @param creatorID       -1返回所有
         * @param contentTypeID   -1返回所有
         * @param parentID        所述目录， -1返回所有
         * @param auditByAdmin    -1返回所有
         * @param auditBySubAdmin -1返回所有
         * @param isview          -1返回所有
         * @param pageNo          -1返回所有
         * @param pageSize        -1返回所有
         * @return
         * @throws ContentManageSysException
         */
        public PagerList getContents(int type, int relationID, int[] relationIDs, int creatorID, int contentTypeID,
                                     int parentID, int auditByAdmin, int auditBySubAdmin, int isview,
                                     int pageNo, int pageSize) throws ContentManageSysException
        {
                int cruentIndex = pageNo * pageSize;
                PagerList pl = new PagerList();
                List list = null;
                try
                {
                        //取数据
                        String hql = "from ContentModel";
                        String tmpCondition = "";
                        if (relationIDs != null)
                        {
                                if (relationIDs.length > 0)
                                {
                                        tmpCondition += " and (";
                                        for (int i = 0; i < relationIDs.length; i++)
                                        {
                                                if (i == 0)
                                                {
                                                        tmpCondition += " relationID=" + relationIDs[i];
                                                }
                                                else
                                                {
                                                        tmpCondition += "  or relationID=" + relationIDs[i];
                                                }
                                        }
                                        tmpCondition += ") ";
                                }
                        }
                        if (creatorID != -1)
                        {
                                tmpCondition += " and userID=" + creatorID;
                        }
                        if (parentID != -1)
                        {
                                tmpCondition += " and parentID=" + parentID;
                        }
                        if (isview != -1)
                        {
                                tmpCondition += " and isview='" + isview + "'";
                        }
                        if (relationID != -1)
                        {
                                tmpCondition += " and relationID=" + relationID;
                        }
                        if (contentTypeID != -1)
                        {
                                tmpCondition += " and contentTypeID=" + contentTypeID;
                        }
                        if (type != -1)
                        {
                                tmpCondition += " and type='" + type + "'";
                        }
                        if (auditBySubAdmin != -1)
                        {
                                tmpCondition += " and auditBySubAdmin='" + auditBySubAdmin + "'";
                        }
                        if (auditByAdmin != -1)
                        {
                                tmpCondition += " and auditByAdmin='" + auditByAdmin + "'";
                        }
                        if (!tmpCondition.equals(""))
                        {
                                tmpCondition = tmpCondition.substring(5);
                                tmpCondition = " where " + tmpCondition;
                        }

                        hql += tmpCondition;
                        logger.info("hql=" + hql);
                        list = HibernateDAO.find(hql + " order by contentID desc", cruentIndex, pageSize);

                        int totalCount = 0;

                        //取总记录数
                        String hqlCount = "select count(*) " + hql;
                        List hqlCountList = HibernateDAO.find(hqlCount);
                        Iterator it = hqlCountList.iterator();

                        if (it.hasNext())
                        {
                                Object row = it.next();
                                totalCount = ((Integer) row).intValue();
                        }

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
         * 返回某种类型的资源。
         *
         * @param type        -1返回所有
         * @param relationID  -1返回所有
         * @param contentType -1返回所有
         * @param pageNo
         * @param pageSize
         * @return
         * @throws ContentManageSysException
         */
        public PagerList getContentsByType(int type, int relationID, int contentType, int pageNo,
                                           int pageSize) throws ContentManageSysException
        {
                int cruentIndex = pageNo * pageSize;
                PagerList pl = new PagerList();
                List list = null;
                try
                {
                        //取数据
                        String hql = "from ContentModel where  isview='" +
                                ContentManageConstants.doView + "'";
                        if (relationID != -1)
                        {
                                hql += " and relationID=" + relationID;
                        }
                        if (contentType != -1)
                        {
                                hql += " and contentTypeID=" + contentType;
                        }
                        if (type != -1)
                        {
                                hql += " and type='" + type + "'";
                        }
                        hql += " order by contentID desc";
                        logger.info("hql=" + hql);
                        list = HibernateDAO.find(hql, cruentIndex, pageSize);

                        int totalCount = 0;

                        //取总记录数
                        String hqlCount = "select count(*) " + hql;
                        List hqlCountList = HibernateDAO.find(hqlCount);
                        Iterator it = hqlCountList.iterator();

                        if (it.hasNext())
                        {
                                Object row = it.next();
                                totalCount = ((Integer) row).intValue();
                        }

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
         * 返回示范校的某种类型的示范校资源。
         *
         * @param relationID          示范校ＩＤ
         * @param courseContentTypeID 示范校资源类型　目前使用courseContentType,==-1:为显示全部
         * @param auditBySubAdmin     是否被示范校管理员审核 ,==-1:为显示全部
         * @param pageNo
         * @param pageSize
         * @return
         * @throws ContentManageSysException
         */
        public PagerList getShiFanXiaoContentsAuditBySubAdmin(int relationID, int courseContentTypeID,
                                                              int auditBySubAdmin, int pageNo, int pageSize)
                throws ContentManageSysException
        {
                int cruentIndex = pageNo * pageSize;
                PagerList pl = new PagerList();
                List list = null;
                try
                {
                        //取数据
                        String hql = "from ContentModel where type='" +
                                ContentManageConstants.SHIFANXIAO_TYPE
                                + "' and relationID=" + relationID
                                + " and isview='" +
                                ContentManageConstants.doView + "'";

                        if (courseContentTypeID != -1)
                        {
                                hql += " and courseContentTypeID=" + courseContentTypeID;
                        }
                        if (auditBySubAdmin != -1)
                        {
                                hql += " and auditBySubAdmin='" + auditBySubAdmin + "'";
                        }
                        hql += " order by contentID desc";
                        logger.info("hql= " + hql);
                        list = HibernateDAO.find(hql, cruentIndex, pageSize);

                        int totalCount = 0;
                        //取总记录数
                        String hqlCount = "select count(*) " + hql;
                        List hqlCountList = HibernateDAO.find(hqlCount);
                        Iterator it = hqlCountList.iterator();

                        if (it.hasNext())
                        {
                                Object row = it.next();
                                totalCount = ((Integer) row).intValue();
                        }
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
         * 返回多个示范校的某种类型的示范校资源。
         *
         * @param relationID          示范校ＩＤ
         * @param courseContentTypeID 示范校资源类型　目前使用courseContentType,==-1:为显示全部
         * @param auditBySubAdmin     是否被示范校管理员审核 ,==-1:为显示全部
         * @param pageNo
         * @param pageSize
         * @return
         * @throws ContentManageSysException
         */
        public PagerList getShiFanXiaoContentsAuditBySubAdmin(int relationID[], int courseContentTypeID,
                                                              int auditBySubAdmin, int pageNo, int pageSize)
                throws ContentManageSysException
        {
                int cruentIndex = pageNo * pageSize;
                PagerList pl = new PagerList();
                List list = null;
                try
                {
                        //取数据
                        String hql = "from ContentModel where type='" +
                                ContentManageConstants.SHIFANXIAO_TYPE
                                + "' and isview='" +
                                ContentManageConstants.doView + "'";

                        if (relationID != null)
                        {
                                if (relationID.length > 0)
                                {
                                        hql += " and (";
                                        for (int i = 0; i < relationID.length; i++)
                                        {
                                                if (i == 0)
                                                {
                                                        hql += " relationID=" + relationID[i];
                                                }
                                                else
                                                {
                                                        hql += "  or relationID=" + relationID[i];
                                                }
                                        }
                                        hql += ") ";
                                }
                        }

                        if (courseContentTypeID != -1)
                        {
                                hql += " and courseContentTypeID=" + courseContentTypeID;
                        }
                        if (auditBySubAdmin != -1)
                        {
                                hql += " and auditBySubAdmin='" + auditBySubAdmin + "'";
                        }
                        hql += " order by contentID desc";
                        logger.info("hql= " + hql);
                        list = HibernateDAO.find(hql, cruentIndex, pageSize);

                        int totalCount = 0;
                        //取总记录数
                        String hqlCount = "select count(*) " + hql;
                        List hqlCountList = HibernateDAO.find(hqlCount);
                        Iterator it = hqlCountList.iterator();

                        if (it.hasNext())
                        {
                                Object row = it.next();
                                totalCount = ((Integer) row).intValue();
                        }
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
         * (示范校首页)返回总校和示范校审核通过的某种类型的示范校资源。
         *
         * @param relationID          示范校ＩＤ
         * @param courseContentTypeID 示范校资源类型　目前使用courseContentType,==-1:为显示全部
         * @param pageNo
         * @param pageSize
         * @return
         * @throws ContentManageSysException
         */
        public PagerList getShiFanXiaoContentsDispalyInPortal(int relationID, int courseContentTypeID,
                                                              int pageNo, int pageSize)
        {
                int cruentIndex = pageNo * pageSize;
                PagerList pl = new PagerList();
                List list = null;
                try
                {
                        //取数据
                        String hql = "from ContentModel where type='" +
                                ContentManageConstants.SHIFANXIAO_TYPE
                                + "' and isview='" +
                                ContentManageConstants.doView + "'";

                        hql += " and ((relationID=" + relationID + " and auditBySubAdmin='" +
                                ContentManageConstants.AUDIT_APPROVED + "')" +
                                " or (relationID=" + SecurityConstants.DEFAULT_RELATIONID + " and auditByAdmin='"
                                + ContentManageConstants.AUDIT_APPROVED + "'))";

                        if (courseContentTypeID != -1)
                        {
                                hql += " and courseContentTypeID=" + courseContentTypeID;
                        }
                        hql += " order by contentID desc";
                        logger.info("hql= " + hql);
                        list = HibernateDAO.find(hql, cruentIndex, pageSize);

                        int totalCount = 0;
                        //取总记录数
                        String hqlCount = "select count(*) " + hql;
                        List hqlCountList = HibernateDAO.find(hqlCount);
                        Iterator it = hqlCountList.iterator();

                        if (it.hasNext())
                        {
                                Object row = it.next();
                                totalCount = ((Integer) row).intValue();
                        }
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
         * 返回所有的某种类型的示范校资源。
         *
         * @param courseContentTypeID 示范校资源类型　目前使用courseContentType,==-1:为显示全部
         * @param auditByAdmin        是否被总校管理员审核,==-1:为显示全部
         * @param pageNo
         * @param pageSize
         * @return
         * @throws ContentManageSysException
         */
        public PagerList getShiFanXiaoContentsAuditByAdmin(int courseContentTypeID, int auditByAdmin,
                                                           int pageNo, int pageSize) throws ContentManageSysException
        {
                int cruentIndex = pageNo * pageSize;
                PagerList pl = new PagerList();
                List list = null;
                try
                {

                        //取数据
                        String hql = "from ContentModel where type='" +
                                ContentManageConstants.SHIFANXIAO_TYPE
                                + "' and isview='" +
                                ContentManageConstants.doView + "'";

                        if (courseContentTypeID != -1)
                        {
                                hql += " and courseContentTypeID=" + courseContentTypeID;
                        }
                        if (auditByAdmin != -1)
                        {
                                hql += " and auditByAdmin='" + auditByAdmin + "'";
                        }
                        hql += " order by contentID desc";
                        logger.info("hql=" + hql);
                        list = HibernateDAO.find(hql, cruentIndex, pageSize);

                        int totalCount = 0;
                        //取总记录数
                        String hqlCount = "select count(*) " + hql;
                        List hqlCountList = HibernateDAO.find(hqlCount);
                        Iterator it = hqlCountList.iterator();

                        if (it.hasNext())
                        {
                                Object row = it.next();
                                totalCount = ((Integer) row).intValue();
                        }
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
         * 显示所属教师的示范校资源
         *
         * @param creatorID           所属教师
         * @param courseContentTypeID 是否被总校管理员审核,==-1:为显示全部
         * @param auditByAdmin        是否被总校管理员审核,==-1:为显示全部
         * @param pageNo
         * @param pageSize
         * @return
         */
        public PagerList getShiFanXiaoContentsByCreator(int creatorID, int courseContentTypeID, int auditByAdmin, int pageNo,
                                                        int pageSize) throws ContentManageSysException
        {
                int cruentIndex = pageNo * pageSize;
                PagerList pl = new PagerList();
                List list = null;
                try
                {
                        //取数据
                        String hql = "from ContentModel where  type='" +
                                ContentManageConstants.SHIFANXIAO_TYPE
                                + "' and userID=" + creatorID
                                + " and isview='" +
                                ContentManageConstants.doView + "'";

                        if (courseContentTypeID != -1)
                        {
                                hql += " and courseContentTypeID=" + courseContentTypeID;
                        }
                        if (auditByAdmin != -1)
                        {
                                hql += " and auditByAdmin='" + auditByAdmin + "'";
                        }
                        hql += " order by contentID desc";
                        logger.info("hql=" + hql);
                        list = HibernateDAO.find(hql, cruentIndex, pageSize);

                        int totalCount = 0;
                        //取总记录数
                        String hqlCount = "select count(*) " + hql;
                        List hqlCountList = HibernateDAO.find(hqlCount);
                        Iterator it = hqlCountList.iterator();

                        if (it.hasNext())
                        {
                                Object row = it.next();
                                totalCount = ((Integer) row).intValue();
                        }
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
         * 总校管理员审核资源
         *
         * @param ids         资源列表
         * @param auditStatus
         * @throws ContentManageSysException
         */
        public void auditContentsByAdmin(List ids, int auditStatus) throws ContentManageSysException
        {
                Session session = null;
                try
                {
                        session = HibernateUtil.getSession();
                        for (int i = 0; i < ids.size(); i++)
                        {
                                ContentModel model = (ContentModel) session.load(ContentModel.class,
                                        (Integer) (ids.get(i)));
                                model.setAuditByAdmin(String.valueOf(auditStatus));
                        }
                        session.flush();
                        session.clear();
                        session.connection().commit();
                }
                catch (Exception e)
                {
                        throw new ContentManageSysException("Exception while Hibernate :  " + e, e);
                }
                finally
                {
                        try
                        {
                                HibernateUtil.releaseSession(session);
                        }
                        catch (HibernateException e)
                        {
                                throw new ContentManageSysException("HibernateException while Hibernate releaseSession:  " + e, e);
                        }
                }
        }

        /**
         * 示范校管理员审核资源
         *
         * @param ids         资源列表
         * @param auditStatus
         * @throws ContentManageSysException
         */
        public void auditContentsBySubAdmin(List ids, int auditStatus) throws ContentManageSysException
        {
                Session session = null;
                try
                {
                        session = HibernateUtil.getSession();
                        for (int i = 0; i < ids.size(); i++)
                        {
                                ContentModel model = (ContentModel) session.load(ContentModel.class,
                                        (Integer) (ids.get(i)));
                                model.setAuditBySubAdmin(String.valueOf(auditStatus));
                        }
                        session.flush();
                        session.clear();
                        session.connection().commit();
                }
                catch (Exception e)
                {
                        throw new ContentManageSysException("Exception while Hibernate :  " + e, e);
                }
                finally
                {
                        try
                        {
                                HibernateUtil.releaseSession(session);
                        }
                        catch (HibernateException e)
                        {
                                throw new ContentManageSysException("HibernateException while Hibernate releaseSession:  " + e, e);
                        }
                }
        }


        /**
         * 返回资源列表
         *
         * @param type                -1返回所有
         * @param relationID          -1返回所有
         * @param relationIDs         -1返回所有
         * @param creatorID           -1返回所有
         * @param contentTypeID       -1返回所有
         * @param courseContentTypeID -1返回所有
         * @param parentID            -1返回所有
         * @param auditByAdmin        -1返回所有
         * @param auditBySubAdmin     -1返回所有
         * @param isview              -1返回所有
         * @param creator             null返回所有
         * @param publisher           null返回所有
         * @param keyword             null返回所有
         * @param orderBy             排序字段，默认按ID降序
         * @param isIncludeSubCatalog 是否包含子目录
         * @param pageNo
         * @param pageSize
         * @return
         * @throws ContentManageSysException
         */
        public PagerList getContents(int type, int relationID, int[] relationIDs, int creatorID, int contentTypeID,
                                     int courseContentTypeID, int parentID, int auditByAdmin, int auditBySubAdmin, int isview,
                                     String creator, String publisher, String keyword, String orderBy,
                                     boolean isIncludeSubCatalog, int pageNo, int pageSize)
                throws ContentManageSysException
        {
                int cruentIndex = pageNo * pageSize;
                PagerList pl = new PagerList();
                List list = null;
                Session session = null;
                try
                {
                        session = HibernateUtil.getSession();
                        //取数据
                        String hql = "from ContentModel";
                        String tmpCondition = "";
                        if (relationIDs != null)
                        {
                                if (relationIDs.length > 0)
                                {
                                        tmpCondition += " and (";
                                        for (int i = 0; i < relationIDs.length; i++)
                                        {
                                                if (i == 0)
                                                {
                                                        tmpCondition += " relationID=" + relationIDs[i];
                                                }
                                                else
                                                {
                                                        tmpCondition += "  or relationID=" + relationIDs[i];
                                                }
                                        }
                                        tmpCondition += ") ";
                                }
                        }
                        if (creatorID != -1)
                        {
                                tmpCondition += " and userID=" + creatorID;
                        }
                        if (parentID != -1)
                        {
                                if (isIncludeSubCatalog)
                                {
                                        List catalogs = getContentCatalogChildrenID(parentID, true);
                                        String catCondition = "parentID=" + parentID;
                                        for (int i = 0; i < catalogs.size(); i++)
                                        {
                                                Integer integer = (Integer) catalogs.get(i);
                                                catCondition += "  or  parentID=" + integer;
                                        }
                                        tmpCondition += " and (" + catCondition + ")";
                                }
                                else
                                {
                                        tmpCondition += " and parentID=" + parentID;
                                }
                        }
                        if (isview != -1)
                        {
                                tmpCondition += " and isview='" + isview + "'";
                        }
                        if (relationID != -1)
                        {
                                tmpCondition += " and relationID=" + relationID;
                        }
                        if (contentTypeID != -1)
                        {
                                tmpCondition += " and contentTypeID=" + contentTypeID;
                        }
                        if (courseContentTypeID != -1)
                        {
                                tmpCondition += " and courseContentTypeID=" + courseContentTypeID;
                        }
                        if (type != -1)
                        {
                                tmpCondition += " and type='" + type + "'";
                        }
                        if (auditBySubAdmin != -1)
                        {
                                tmpCondition += " and auditBySubAdmin='" + auditBySubAdmin + "'";
                        }
                        if (auditByAdmin != -1)
                        {
                                tmpCondition += " and auditByAdmin='" + auditByAdmin + "'";
                        }

                        if (!StringUtils.isBlank(creator))
                        {
                                tmpCondition += " and  creator like :creator";
                                creator = StringEscapeUtils.escapeSql(creator);
                                creator = "%" + creator + "%";
                        }

                        if (!StringUtils.isBlank(publisher))
                        {
                                tmpCondition += " and  publisher like :creator";
                                publisher = StringEscapeUtils.escapeSql(publisher);
                                keyword = "%" + creator + "%";
                        }

                        if (!StringUtils.isBlank(keyword))
                        {
                                tmpCondition += " and  (title like :keyword or description like :keyword " +
                                        "or contentClob like :keyword or keyword like :keyword";
                                keyword = StringEscapeUtils.escapeSql(keyword);
                                keyword = "%" + keyword + "%";
                        }
                        if (!tmpCondition.equals(""))
                        {
                                tmpCondition = tmpCondition.substring(5);
                                tmpCondition = " where " + tmpCondition;
                        }
                        if (StringUtils.isBlank(orderBy))
                        {
                                orderBy = " order by contentID desc ";
                        }

                        hql += tmpCondition;
                        logger.info("hql=" + (hql + orderBy));
                        Query q = session.createQuery(hql + orderBy);

                        if (-1 != pageNo)
                        {
                                q.setFirstResult(cruentIndex);
                        }

                        if (-1 != pageSize)
                        {
                                q.setMaxResults(pageSize);
                        }
                        if (!StringUtils.isBlank(publisher))
                        {
                                q.setString("publisher", publisher);
                        }
                        if (!StringUtils.isBlank(creator))
                        {
                                q.setString("creator", creator);
                        }
                        if (!StringUtils.isBlank(keyword))
                        {
                                q.setString("keyword", keyword);
                        }
                        list = q.list();
                        int totalCount = 0;

                        //取总记录数
                        String hqlCount = "select count(*) " + hql;
                        q = session.createQuery(hqlCount);

                        if (!StringUtils.isBlank(publisher))
                        {
                                q.setString("publisher", publisher);
                        }
                        if (!StringUtils.isBlank(creator))
                        {
                                q.setString("creator", creator);
                        }
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
         * @param contentCatalogID
         * @param isIncludeSubCatalog 是否递归包含子目录下的内容
         * @return
         */
        public List getContentCatalogChildrenID(int contentCatalogID, boolean isIncludeSubCatalog)
        {
                List result = new ArrayList();

                String hql = "from ContentCatalogModel where parentID=" +
                        contentCatalogID;

                logger.info("hql=" + hql);

                List catalogs = HibernateDAO.find(hql);
                if (!catalogs.isEmpty())
                {
                        for (int i = 0; i < catalogs.size(); i++)
                        {
                                ContentCatalogModel catalog = (ContentCatalogModel) catalogs.get(i);
                                Integer integer = new Integer(catalog.getContentCatalogID());
                                result.add(integer);
                                if (isIncludeSubCatalog)
                                {
                                        result.addAll(getContentCatalogChildrenID(catalog.getContentCatalogID(), isIncludeSubCatalog));
                                        //todo:防止应为可能的数据错误，导致的死循环
                                }
                        }
                }
                return result;
        }

        /**
         * 取某目录下所有的子目录(ContentCatalogModel)
         *
         * @param contentCatalogID
         * @param isIncludeSubCatalog 是否递归包含子目录下的内容
         * @return
         */
        public List getContentCatalogChildren(int contentCatalogID, boolean isIncludeSubCatalog)
        {
                List result = new ArrayList();

                String hql = "from ContentCatalogModel where CONTENTCATALOGID=" +
                        contentCatalogID;

                logger.info("hql=" + hql);

                List catalogs = HibernateDAO.find(hql);
                if (!catalogs.isEmpty())
                {
                        for (int i = 0; i < catalogs.size(); i++)
                        {
                                ContentCatalogModel catalog = (ContentCatalogModel) catalogs.get(i);
                                result.add(catalog);
                                if (isIncludeSubCatalog)
                                {
                                        result.addAll(getContentCatalogChildren(catalog.getContentCatalogID(), isIncludeSubCatalog));
                                        //todo:防止应为可能的数据错误，导致的死循环
                                }
                        }
                }
                return result;
        }
}
