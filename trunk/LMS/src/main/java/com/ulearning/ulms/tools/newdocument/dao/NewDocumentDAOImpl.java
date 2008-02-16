/**
 * NewDocumentDaoImpl.java.
 * Date: 2006-8-7
 *
 * Copyright (c) 2000-2006.Beijing Wenhua Online Sci-tech Development Co.,Ltd
 * All rights reserved.
 */
package com.ulearning.ulms.tools.newdocument.dao;


import com.ulearning.ulms.core.exceptions.ULMSSysException;
import com.ulearning.ulms.core.util.ClobUtil;
import com.ulearning.ulms.core.util.PagerUtil;
import com.ulearning.ulms.tools.newdocument.exceptions.NewDocumentSysException;
import com.ulearning.ulms.tools.newdocument.form.NewDocumentForm;
import com.ulearning.ulms.tools.newdocument.form.NewDocumentTreeModel;
import com.ulearning.ulms.tools.newdocument.model.NewDocumentCatalogModel;
import com.ulearning.ulms.tools.newdocument.model.NewDocumentModel;
import com.ulearning.ulms.tools.newdocument.model.NewDocumentModel1;
import com.ulearning.ulms.tools.newdocument.util.NewDocumentConstants;
import com.ulearning.ulms.util.DBUtil;
import com.ulearning.ulms.util.HibernateDAO;
import com.ulearning.ulms.util.HibernateUtil;
import com.ulearning.ulms.util.log.LogUtil;
import net.sf.hibernate.HibernateException;
import net.sf.hibernate.MappingException;
import net.sf.hibernate.Query;
import net.sf.hibernate.Session;
import oracle.sql.CLOB;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringEscapeUtils;

public class NewDocumentDAOImpl implements NewDocumentDAO
{

        /**
         * 根据flag返回不同的列表，0时返回允许在门户显示的，1时返回在门户不可见的，其他则返回全部的
         *
         * @param aspID
         * @param relationID
         * @param type
         * @param parentID
         * @param flag
         * @return
         * @throws NewDocumentSysException
         */
        public List getDocumentByFlag(int aspID, int relationID, List type, int parentID, int flag)
                throws NewDocumentSysException
        {
                List tmList = null;
                String sql_str = "from NewDocumentModel as doc where 1=1";
                if (aspID >= 0)
                {
                        sql_str += " and doc.aspID=" + aspID;
                }

                if (relationID >= 0)
                {
                        sql_str += " and doc.relationID=" + relationID;
                }

                if (type.size() > 0)
                {
                        sql_str += " and ( doc.type='" + type.get(0) + "' ";

                        for (int i = 1; i < type.size(); i++)
                        {
                                sql_str += " or doc.type = '" + type.get(i) + "' ";
                        }

                        sql_str += " ) ";
                }

                if (parentID >= 0)
                {
                        sql_str += " and doc.parentID=" + parentID;
                }

                if (flag == 0)
                {
                        sql_str += " and doc.isView='" + NewDocumentConstants.IS_VIEW + "' ";
                }
                else if (flag == 1)
                {
                        sql_str += " and doc.isView='" + NewDocumentConstants.NOT_IS_VIEW + "' ";
                }

                sql_str += " order by doc.docID desc";
                try
                {
                        tmList = HibernateDAO.find(sql_str);
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new NewDocumentSysException(e);
                }
                return tmList;
        }

        /**
         * @param orgID
         * @param type
         * @param parentID
         * @return
         * @throws NewDocumentSysException
         */
        public NewDocumentTreeModel getTree(int orgID, int type, int parentID)
                throws NewDocumentSysException
        {
                NewDocumentForm newDocumentForm = new NewDocumentForm();
                NewDocumentCatalogModel catalogModel = null;
                ArrayList catalogList = new ArrayList();
                List clList = null;
                Session session = null;
                String sql_str = " from NewDocumentModel ";
                try
                {
                        session = HibernateUtil.getSession();
                        clList = session.createQuery(sql_str).setCacheable(true).setCacheRegion("com.ulearning.ulms.tools.newdocument.dao.NewDocumentDAOImpl").list(); //clList = HibernateDAO.find(sql_str);
                }
                catch (Exception e)
                {
                        e.printStackTrace();
                        throw new NewDocumentSysException("" + e);
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
                for (int i = 0; i < clList.size(); i++)
                {
                        catalogModel = (NewDocumentCatalogModel) clList.get(i);
                        catalogList.add(null);
                }
                return null;
        }

        /**
         * @param catalogID
         * @return
         * @throws NewDocumentSysException
         */
        public List getCatalogPath(int catalogID) throws NewDocumentSysException
        {
                return null;
        }

        /**
         * @param catalogID
         * @return
         * @throws NewDocumentSysException
         */
        public NewDocumentCatalogModel getCatalog(int catalogID)
                throws NewDocumentSysException
        {
                NewDocumentCatalogModel catalogModel = null;
                List clList = null;
                String sql_str = " from NewDocumentCatalogModel " +
                        "where catalogID = " + catalogID;
                Session session = null;
                try
                {
                        session = HibernateUtil.getSession();
                        clList = session.createQuery(sql_str).setCacheable(true).setCacheRegion("com.ulearning.ulms.tools.newdocument.dao.NewDocumentDAOImpl").list(); // HibernateDAO.find(sql_str);
                        for (int i = 0; i < clList.size(); i++)
                        {
                                catalogModel = (NewDocumentCatalogModel) clList.get(i);

                        }

                }
                catch (Exception e)
                {
                        e.printStackTrace();
                        throw new NewDocumentSysException(e);
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
                return catalogModel;
        }

        /**
         * catalog的综合查询
         *
         * @param type        模块分类            null：不限
         * @param parentID    newdocumentID的值   -1：不限
         * @param aspID       null：不限
         * @param name        catalogName         null：不限
         * @param description null：不限
         * @return
         * @throws NewDocumentSysException
         */
        public List getCatalogList(String type, int parentID, int aspID, String name, String description)
                throws NewDocumentSysException
        {
                List clList = null;
                String sql_str = " from NewDocumentCatalogModel as cm" +
                        " where 1 = 1";
                Session session = null;
                if (parentID != -1)
                {
                        sql_str += " and cm.parentID = " + parentID;
                }

                if (aspID != -1)
                {
                        sql_str += " and cm.aspID = " + aspID;
                }


                if (type != null && !type.equals("null"))
                {
                        sql_str += " and cm.type ='" + type + "' ";
                }

                if (name != null && !name.equals("null"))
                {
                        name= StringEscapeUtils.escapeSql(name);
                        sql_str += " and cm.catalogName like '" + name + "'";
                }

                if (description != null && !description.equals("null"))
                {
                        description= StringEscapeUtils.escapeSql(description);
                        sql_str += " and cm.description like '" + description + "'";
                }

                try
                {
                        session = HibernateUtil.getSession();
                        clList = session.createQuery(sql_str).setCacheable(true).setCacheRegion("com.ulearning.ulms.tools.newdocument.dao.NewDocumentDAOImpl").list(); //clList = HibernateDAO.find(sql_str);
                }
                catch (Exception e)
                {
                        e.printStackTrace();
                        throw new NewDocumentSysException(e);
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
                return clList;
        }


        public NewDocumentModel getNewDocument(String name, String type)
                throws NewDocumentSysException
        {
                NewDocumentModel docModel = null;
                List clList = null;
                String sql_str = "from NewDocumentModel where docName = '" + name + "' and type='" + type + "'";

                Session session = null;
                try
                {
                        LogUtil.debug("course", "[NewDocumentDAOImpl] try=" + "into the try");
                        session = HibernateUtil.getSession();
                        clList = session.createQuery(sql_str).setCacheable(true).setCacheRegion("com.ulearning.ulms.tools.newdocument.dao.NewDocumentDAOImpl").list(); //clList = HibernateDAO.find(sql_str);
                        for (int i = 0; i < clList.size(); i++)
                        {
                                LogUtil.debug("course", "[NewDocumentDAOImpl] list=" + "list.size");
                                docModel = (NewDocumentModel) clList.get(i);

                        }
                }
                catch (Exception e)
                {
                        LogUtil.debug("course", "[NewDocumentDAOImpl] catch=" + "into the catch");
                        e.printStackTrace();
                        throw new NewDocumentSysException(e);
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
                return docModel;
        }

        /**
         * @param catalogIDs
         * @throws NewDocumentSysException
         */
        public void deleteCatalog(List catalogIDs) throws NewDocumentSysException
        {
                String sql_str = null;

                if (catalogIDs.size() > 0)
                {
                        sql_str = "from NewDocumentCatalogModel where catalogID = " + catalogIDs.get(0);

                        for (int i = 1; i < catalogIDs.size(); i++)
                        {
                                sql_str += "or catalogID = '" + catalogIDs.get(i) + "'";
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
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new NewDocumentSysException(e);
                }


        }

        /**
         * @param documenID
         * @return
         * @throws NewDocumentSysException
         */
        public NewDocumentModel getDocument(int documenID)
                throws NewDocumentSysException
        {
                NewDocumentModel docModel = null;
                List clList = null;
                String sql_str = "from NewDocumentModel where docID = " + documenID;

                String typeS = Integer.toString(documenID);
                LogUtil.debug("course", "[NewDocumentDAOImpl]---type=" + typeS);
                Session session = null;
                try
                {
                        LogUtil.debug("course", "[NewDocumentDAOImpl] try=" + "into the try");
                        session = HibernateUtil.getSession();
                        clList = session.createQuery(sql_str).setCacheable(true).setCacheRegion("com.ulearning.ulms.tools.newdocument.dao.NewDocumentDAOImpl").list(); //clList = HibernateDAO.find(sql_str);

                }
                catch (Exception e)
                {
                        LogUtil.debug("course", "[NewDocumentDAOImpl] catch=" + "into the catch");
                        e.printStackTrace();
                        throw new NewDocumentSysException(e);
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
                for (int i = 0; i < clList.size(); i++)
                {
                        LogUtil.debug("course", "[NewDocumentDAOImpl] list=" + "list.size");
                        docModel = (NewDocumentModel) clList.get(i);

                }

                return docModel;
        }

        /**
         * @param aspID
         * @param type
         * @param parentID
         * @return
         * @throws NewDocumentSysException
         */

        public List getDocument(int aspID, int relationID, List type, int parentID)
                throws NewDocumentSysException
        {
                List tmList = null;
                String sql_str = "select from NewDocumentModel as doc where 1=1";
                if (aspID >= 0)
                {
                        sql_str += " and doc.aspID=" + aspID;
                }

                if (relationID >= 0)
                {
                        sql_str += " and doc.relationID=" + relationID;
                }

                if (type.size() > 0)
                {
                        sql_str += " and ( doc.type='" + type.get(0) + "' ";

                        for (int i = 1; i < type.size(); i++)
                        {
                                sql_str += " or doc.type = '" + type.get(i) + "' ";
                        }

                        sql_str += " ) ";
                }

                if (parentID >= 0)
                {
                        sql_str += " and doc.parentID=" + parentID;
                }

                sql_str += " order by doc.createDate desc";
                Session session = null;
                try
                {
                        session = HibernateUtil.getSession();
                        tmList = session.createQuery(sql_str).list();//.setCacheable(true).setCacheRegion("com.ulearning.ulms.tools.newdocument.dao.NewDocumentDAOImpl").list(); //tmList = HibernateDAO.find(sql_str);
                }
                catch (Exception e)
                {
                        e.printStackTrace();
                        throw new NewDocumentSysException(e);
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
                return tmList;
        }

        /**
         * @param aspID
         * @param type
         * @param parentID
         * @return
         * @throws NewDocumentSysException
         */

        public List getDocumentByAll(int aspID, int relationID, List type, int parentID)
                throws NewDocumentSysException
        {
                List tmList = null;
                String sql_str = "select from NewDocumentModel as doc where 1=1";
                if (aspID > 0)
                {
                        sql_str += " and doc.aspID=" + aspID;
                }

                if (relationID >= 0)
                {
                        sql_str += " and doc.relationID=" + relationID;
                }

                if (type.size() > 0)
                {
                        sql_str += " and ( doc.type='" + type.get(0) + "' ";

                        for (int i = 1; i < type.size(); i++)
                        {
                                sql_str += " or doc.type = '" + type.get(i) + "' ";
                        }

                        sql_str += " ) ";
                }

                if (parentID >= 0)
                {
                        sql_str += " and doc.parentID=" + parentID;
                }

                sql_str += " order by doc.createDate desc";
                Session session = null;
                try
                {
                        session = HibernateUtil.getSession();
                        tmList = session.createQuery(sql_str).list();//.setCacheable(true).setCacheRegion("com.ulearning.ulms.tools.newdocument.dao.NewDocumentDAOImpl").list(); //tmList = HibernateDAO.find(sql_str);
                }
                catch (Exception e)
                {
                        e.printStackTrace();
                        throw new NewDocumentSysException(e);
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
                return tmList;
        }

        public List getDocumentzl(int aspID, List type, int parentID)
                throws NewDocumentSysException
        {
                List tmList = null;
                String sql_str = "select from NewDocumentModel as doc where 1=1";
                if (aspID >= 0)
                {
                        sql_str += " and doc.aspID=" + aspID;
                }
                if (type.size() > 0)
                {
                        sql_str += " and ( doc.type='" + type.get(0) + "' ";

                        for (int i = 1; i < type.size(); i++)
                        {
                                sql_str += " or doc.type = '" + type.get(i) + "' ";
                        }

                        sql_str += " ) ";
                }
                sql_str += " order by doc.createDate desc";
                Session session = null;
                try
                {
                        session = HibernateUtil.getSession();
                        tmList = session.createQuery(sql_str).list();//.setCacheable(true).setCacheRegion("com.ulearning.ulms.tools.newdocument.dao.NewDocumentDAOImpl").list(); //tmList = HibernateDAO.find(sql_str);
                }
                catch (Exception e)
                {
                        e.printStackTrace();
                        throw new NewDocumentSysException(e);
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
                return tmList;
        }

        /**
         * @param aspID
         * @param type
         * @param parentID
         * @return
         * @throws NewDocumentSysException
         */
        public List getDocument1(int aspID, int relationID, List type, int parentID)
                throws NewDocumentSysException
        {
                List tmList = null;
                String sql_str = "from NewDocumentModel1";

                Session session = null;
                try
                {
                        session = HibernateUtil.getSession();
                        tmList = session.createQuery(sql_str).setCacheable(true).setCacheRegion("com.ulearning.ulms.tools.newdocument.dao.NewDocumentDAOImpl").list(); //tmList = HibernateDAO.find(sql_str);
                }
                catch (Exception e)
                {
                        e.printStackTrace();
                        throw new NewDocumentSysException(e);
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
                return tmList;
        }

        /**
         * @param aspID
         * @param relationID
         * @param type
         * @param parentID
         * @param logType
         * @param logTxt
         * @return
         * @throws NewDocumentSysException
         */
        public List getCheck(int aspID, int relationID, int type, int parentID, int logType, String logTxt)
                throws NewDocumentSysException
        {
                List tmList = null;
                String hql = null;

                hql = " from NewDocumentModel where type ='" + type + "' "
                        + " and relationID=" + relationID
                        + " and aspID=" + aspID
                        + " and parentID=" + parentID;
                logTxt= StringEscapeUtils.escapeSql(logTxt);
                if (logType == 0)
                {
                        hql = hql + " and docName like '%" + logTxt + "%'";
                }
                else if (logType == 1)
                {
                        hql = hql + " and content like '%" + logTxt + "%'";
                }
                Session session = null;
                try
                {
                        session = HibernateUtil.getSession();
                        tmList = session.createQuery(hql).setCacheable(true).setCacheRegion("com.ulearning.ulms.tools.newdocument.dao.NewDocumentDAOImpl").list(); //tmList = HibernateDAO.find(hql);
                }
                catch (Exception e)
                {
                        throw new NewDocumentSysException(e);
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
                return tmList;
        }

        /**
         * get user from orgid
         *
         * @param orgID
         * @param logType
         * @param logTxt
         * @return
         * @throws NewDocumentSysException
         */
        public List getUserCheck(int orgID, int logType, String logTxt)
                throws NewDocumentSysException
        {
                List tmList = null;
                String hql = null;

                if ((orgID == 1) || (orgID == 0))
                {
                        hql = " from UserModel where";
                }
                else
                {
                        hql = " from UserModel where catalogid=" + orgID + "and ";
                }
                if (logType == 0)
                {
                        hql = hql + " loginname like '%" + logTxt + "%'";
                }
                else if (logType == 1)
                {
                        hql = hql + " name like '%" + logTxt + "%'";
                }
                Session session = null;
                try
                {
                        session = HibernateUtil.getSession();
                        tmList = session.createQuery(hql).setCacheable(true).setCacheRegion("com.ulearning.ulms.tools.newdocument.dao.NewDocumentDAOImpl").list(); //tmList = HibernateDAO.find(hql);
                }
                catch (Exception e)
                {
                        throw new NewDocumentSysException(e);
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
                return tmList;
        }

        /**
         * 查找出可在当前日期显示的所有数据
         *
         * @param type
         * @return
         * @throws NewDocumentSysException
         */
        public List getAllFromDate(int aspID, int relationID, int type, int parentID)
                throws NewDocumentSysException
        {
                List tmList = null;
                List newdocumentList = new ArrayList();
                Session session = null;
                String hql = null;

                hql = " from NewDocumentModel where type ='" + type + "' "
                        + " and relationID=" + relationID
                        + " and aspID=" + aspID
                        + " and parentID=" + parentID;

                try
                {
                        session = HibernateUtil.getSession();

                        String visitSQL = "";
                        if (true)
                        {
                                visitSQL = " and ("
                                        + "(displayBeginDate IS NULL and displayEndDate IS NULL)"
                                        + "or (displayBeginDate IS NULL and displayEndDate IS NOT NULL and "
                                        + "displayEndDate >=:now_day)"
                                        + "or (displayBeginDate IS NOT NULL and displayBeginDate<=:now_day and displayEndDate IS NULL)"
                                        + "or (displayBeginDate IS NOT NULL and displayBeginDate<=:now_day and displayEndDate IS NOT NULL and "
                                        + " displayEndDate >=:now_day)"
                                        + " )";
                        }
                        String dateConditions = "";
                        hql = hql + visitSQL + dateConditions + " order by modifyDate desc";
                        Query q = session.createQuery(hql).setCacheable(true).setCacheRegion("com.ulearning.ulms.tools.newdocument.dao.NewDocumentDAOImpl");
                        if (true)
                        {
                                Date d = new Date();
                                q.setDate("now_day", d);
                        }
                        tmList = q.list();
                }
                catch (HibernateException he)
                {
                        throw new NewDocumentSysException("HibernateException while getNewdocumentFormList: \n" + he);
                }
                try
                {
                        HibernateUtil.releaseSession(session);
                }
                catch (HibernateException he)
                {
                        throw new NewDocumentSysException("HibernateException while getNewdocumentFormList: \n" + he);
                }
                NewDocumentModel am = null;
                NewDocumentForm at = new NewDocumentForm();
                for (int i = 0; i < tmList.size(); i++)
                {
                        am = (NewDocumentModel) tmList.get(i);
                        newdocumentList.add(at.getNewDocumentForm(am));
                }
                return newdocumentList;
        }

        /**
         * @param documenIDs
         * @throws NewDocumentSysException
         */
        public void deleteDocument(List documenIDs)
                throws NewDocumentSysException
        {
                String sql_str = null;
                if (documenIDs.size() > 0)
                {
                        sql_str = "from NewDocumentModel where docID = " + documenIDs.get(0);
                        for (int i = 1; i < documenIDs.size(); i++)
                        {
                                sql_str += " or docID = " + documenIDs.get(i) + "";
                        }
                        LogUtil.debug("course", "[NewDocumentDAOImpl] sql_str=" + sql_str);
                }
                if (sql_str == null)
                {
                        return;
                }
                try
                {
                        HibernateDAO.delete(sql_str);
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new NewDocumentSysException(e);
                }
        }

        public NewDocumentModel getCss(int aspID, int relationID, int type, int parentID)
                throws NewDocumentSysException
        {
                NewDocumentModel newDocumentModel = new NewDocumentModel();
                List clList = null;
                String sql_str = "from NewDocumentModel where aspID=" + aspID + " and type='" + type + "' "
                        + "and relationID=" + relationID + " and parentID=" + parentID + " and isUserful='1'";
                Session session = null;
                try
                {
                        session = HibernateUtil.getSession();
                        clList = session.createQuery(sql_str).setCacheable(true).setCacheRegion("com.ulearning.ulms.tools.newdocument.dao.NewDocumentDAOImpl").list(); //clList = HibernateDAO.find(sql_str);
                }
                catch (Exception e)
                {
                        LogUtil.debug("course", "[NewDocumentDAOImpl]--getCss catch=" + "into the catch");
                        e.printStackTrace();
                        throw new NewDocumentSysException(e);
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

                for (int i = 0; i < clList.size(); i++)
                {
                        LogUtil.debug("course", "[NewDocumentDAOImpl] list=" + "list.size");
                        newDocumentModel = (NewDocumentModel) clList.get(i);

                }
                return newDocumentModel;

        }

        public List getAllCss(int aspID, int type)
                throws NewDocumentSysException
        {
                List clList = null;
                String sql_str = "from NewDocumentModel where aspID=" + aspID + "and type='" + type + "' ";
                Session session = null;
                try
                {
                        session = HibernateUtil.getSession();
                        clList = session.createQuery(sql_str).setCacheable(true).setCacheRegion("com.ulearning.ulms.tools.newdocument.dao.NewDocumentDAOImpl").list(); //clList = HibernateDAO.find(sql_str);
                }
                catch (Exception e)
                {
                        LogUtil.debug("course", "[NewDocumentDAOImpl]--getAllCss catch=" + "into the catch");
                        e.printStackTrace();
                        throw new NewDocumentSysException(e);
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

                return clList;
        }


        /**
         * @param addcatalogModel
         * @throws NewDocumentSysException
         */
        public void insertCatalog(NewDocumentCatalogModel addcatalogModel)
                throws NewDocumentSysException
        {
                try
                {
                        HibernateDAO.add(addcatalogModel);
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new NewDocumentSysException(e);
                }
        }

        /**
         * @param updcatalogModel
         * @throws NewDocumentSysException
         */
        public void updateCatalog(NewDocumentCatalogModel updcatalogModel)
                throws NewDocumentSysException
        {
                try
                {
                        HibernateDAO.update(updcatalogModel);
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new NewDocumentSysException(e);
                }
        }


        /**
         * @param addModel
         * @throws NewDocumentSysException
         */
        public void insertDocument(NewDocumentModel addModel)
                throws NewDocumentSysException
        {
                Connection conn = null;
                PreparedStatement pstmt = null;
                ResultSet rs = null;
                try
                {
                        addModel.setContentClob(oracle.sql.CLOB.empty_lob());
                        String docID = HibernateDAO.add(addModel).toString();

                        //update the Clob colunm
                        String sql = "select contentClob from T_PORTAL_TAB where docID=" + docID + " for update";
                        conn = DBUtil.getConnection();
                        conn.setAutoCommit(false);
                        pstmt = conn.prepareStatement(sql);
                        rs = pstmt.executeQuery();

                        CLOB clobtt = null;
                        if (rs.next())
                        {
                                clobtt = (CLOB) rs.getClob(1);
                                clobtt.putString(1, addModel.getTempClobString());
                        }
                        conn.commit();
                }
                catch (SQLException e)
                {
                        e.printStackTrace();
                        throw new ULMSSysException("SQLException while Hibernate add:  " + e, e);
                }
                finally
                {
                        try
                        {
                                DBUtil.closeResultSet(rs);
                                DBUtil.closeStatement(pstmt);
                                DBUtil.closeConnection(conn);
                        }
                        catch (Exception e)
                        {
                                e.printStackTrace();
                                throw new ULMSSysException("HibernateException while Hibernate save:  " + e, e);
                        }
                }
        }

        /**
         * @param addModel
         * @throws NewDocumentSysException
         */
        public void insertDocument0(NewDocumentModel addModel)
                throws NewDocumentSysException
        {
                try
                {

                        //System.out.println("addModel.getCreateDate() = " + addModel.getCreateDate());
                        //System.out.println("addModel.getModifyDate() = " + addModel.getModifyDate());
                        //System.out.println("addModel.() = 执行前" + addModel.getContentSize());
                        addModel.setContentClob(null);
                        String docID = HibernateDAO.add(addModel).toString();
                        //System.out.println("addModel.getContentSize() =执行后 " + addModel.getContentSize());
                        addModel.setDocID(Integer.parseInt(docID));
                        addModel.setContentClob(ClobUtil.getClob(addModel.getTempClobString()));
                        HibernateDAO.update(addModel);
                }
                catch (Exception e)
                {
                        throw new ULMSSysException("SQLException while Hibernate add:  " + e, e);
                }
        }

        /**
         * @param addModel
         * @throws NewDocumentSysException
         */
        public void insertDocument1(NewDocumentModel1 addModel)
                throws NewDocumentSysException
        {
                Session session = null;
                try
                {

                        session = HibernateUtil.getSession();

                        session.save(addModel);

                        session.flush();
                        session.connection().commit();
                }
                catch (MappingException e)
                {
                        throw new ULMSSysException("MappingException while Hibernate add:  " + e, e);
                }
                catch (HibernateException e)
                {
                        throw new ULMSSysException("HibernateException while Hibernate add:  " + e, e);
                }
                catch (SQLException e)
                {
                        throw new ULMSSysException("SQLException while Hibernate add:  " + e, e);
                }
                finally
                {
                        try
                        {
                                HibernateUtil.releaseSession(session);
                        }
                        catch (HibernateException e)
                        {
                                throw new ULMSSysException("HibernateException while Hibernate save:  " + e, e);
                        }
                }


        }

        /**
         * @param updDocModel
         * @throws NewDocumentSysException
         */
        public void updateDocument(NewDocumentModel updDocModel)
                throws NewDocumentSysException
        {
                Connection conn = null;
                PreparedStatement pstmt = null;
                ResultSet rs = null;
                try
                {
                        updDocModel.setContentClob(oracle.sql.CLOB.empty_lob());
                        HibernateDAO.update(updDocModel);
                        int docID = updDocModel.getDocID();

                        //update the Clob colunm
                        String sql = "select contentClob from T_PORTAL_TAB where docID=" + docID + " for update";
                        conn = DBUtil.getConnection();
                        conn.setAutoCommit(false);
                        pstmt = conn.prepareStatement(sql);
                        rs = pstmt.executeQuery();

                        CLOB clobtt = null;
                        if (rs.next())
                        {
                                clobtt = (CLOB) rs.getClob(1);
                                clobtt.putString(1, updDocModel.getTempClobString());
                        }
                        conn.commit();
                }
                catch (SQLException e)
                {
                        e.printStackTrace();
                        throw new ULMSSysException("SQLException while Hibernate add:  " + e, e);
                }
                finally
                {
                        try
                        {
                                DBUtil.closeResultSet(rs);
                                DBUtil.closeStatement(pstmt);
                                DBUtil.closeConnection(conn);
                        }
                        catch (Exception e)
                        {
                                e.printStackTrace();
                                throw new ULMSSysException("HibernateException while Hibernate save:  " + e, e);
                        }
                }
        }

        /**
         * @param updDocModel
         * @throws NewDocumentSysException
         */
        public void updateDocument1(NewDocumentModel1 updDocModel) throws NewDocumentSysException
        {
                Session session = null;
                try
                {

                        session = HibernateUtil.getSession();

                        session.update(updDocModel);

                        session.flush();
                        session.connection().commit();
                }
                catch (MappingException e)
                {
                        throw new ULMSSysException("MappingException while Hibernate add:  " + e, e);
                }
                catch (HibernateException e)
                {
                        throw new ULMSSysException("HibernateException while Hibernate add:  " + e, e);
                }
                catch (SQLException e)
                {
                        throw new ULMSSysException("SQLException while Hibernate add:  " + e, e);
                }
                finally
                {
                        try
                        {
                                HibernateUtil.releaseSession(session);
                        }
                        catch (HibernateException e)
                        {
                                throw new ULMSSysException("HibernateException while Hibernate update:  " + e, e);
                        }
                }
        }

        public void updateCss(NewDocumentModel updCssModel)
                throws NewDocumentSysException
        {
                try
                {

                        HibernateDAO.update(updCssModel);
                        LogUtil.debug("course", "[NewDocumentDAO]updateCss");
                }
                catch (ULMSSysException e)
                {

                        e.printStackTrace();
                        throw new NewDocumentSysException(e);
                }

        }

        public List getDocumentByOrderindex(int aspID, int relationID, int type, int parentID)
                throws NewDocumentSysException
        {
                List tmList = null;
                String sql_str = "from NewDocumentModel where aspID=" + aspID
                        + " and relationID=" + relationID
                        + " and type='" + type + "' "
                        + " and parentID=" + parentID + " order by orderIndex ASC";
                Session session = null;
                try
                {
                        session = HibernateUtil.getSession();
                        tmList = session.createQuery(sql_str).setCacheable(true).setCacheRegion("com.ulearning.ulms.tools.newdocument.dao.NewDocumentDAOImpl").list(); //tmList = HibernateDAO.find(sql_str);
                        LogUtil.debug("course", "[NewDocumentDAO]getDocument***********tmList.size=" + tmList.size());
                }
                catch (Exception e)
                {
                        e.printStackTrace();
                        throw new NewDocumentSysException(e);
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

                return tmList;
        }

        public List getDocumentByOrderindex_user(int aspID, int relationID, int type, int parentID, int userID)
                throws NewDocumentSysException
        {
                List tmList = null;
                String sql_str = "from NewDocumentModel where aspID=" + aspID
                        + " and relationID=" + relationID
                        + " and type='" + type + "' "
                        + " and parentID=" + parentID
                        + " and userID=" + userID
                        + " order by orderIndex ASC";
                Session session = null;
                try
                {
                        session = HibernateUtil.getSession();
                        tmList = session.createQuery(sql_str).setCacheable(true).setCacheRegion("com.ulearning.ulms.tools.newdocument.dao.NewDocumentDAOImpl").list(); //tmList = HibernateDAO.find(sql_str);
                        LogUtil.debug("course", "[NewDocumentDAO]getDocument***********tmList.size=" + tmList.size());
                }
                catch (Exception e)
                {
                        e.printStackTrace();
                        throw new NewDocumentSysException(e);
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

                return tmList;
        }

        public int getMaxOrderIndex()
                throws NewDocumentSysException
        {
                List lt = null;
                String sql = "select max(ndm.orderIndex) from NewDocumentModel ndm";
                Session session = null;
                int orderIndex = 0;
                try
                {
                        session = HibernateUtil.getSession();
                        Query q = session.createQuery(sql).setCacheable(true).setCacheRegion("com.ulearning.ulms.tools.newdocument.dao.NewDocumentDAOImpl");
                        lt = q.list();
                }
                catch (HibernateException he)
                {
                        throw new NewDocumentSysException(he);
                }
                finally
                {
                        try
                        {
                                HibernateUtil.releaseSession(session);
                        }
                        catch (HibernateException e)
                        {
                                throw new NewDocumentSysException(e);
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

        public List getAll(int pageNo, int pageSize)
        {
                List page = null;
                Session session = null;
                try
                {
                        session = HibernateUtil.getSession();
                        String hqlStr = " from NewDocumentModel  order by docID desc";

                        LogUtil.debug("course", "count sql: " + hqlStr);
                        Query q = session.createQuery(hqlStr).setCacheable(true).setCacheRegion("com.ulearning.ulms.tools.newdocument.dao.NewDocumentDAOImpl");

                        q.setMaxResults(pageSize);
                        q.setFirstResult(pageSize * pageNo);

                        page = q.list();

                        int totalCount = session.createQuery(" from NewDocumentModel").setCacheable(true).setCacheRegion("com.ulearning.ulms.tools.newdocument.dao.NewDocumentDAOImpl").list().size();
                        int pageCount = PagerUtil.getPageCount(totalCount, pageSize);

                        LogUtil.debug("course", "@pageCount = " + pageCount);
                        LogUtil.debug("course", "@totalCcount = " + totalCount);
                        LogUtil.debug("course", "@pageNo = " + pageNo);
                        LogUtil.debug("course", "@pageSize = " + pageSize);

                }
                catch (HibernateException he)
                {
                        throw new ULMSSysException("HibernateException while find: \n" + he);
                }
                finally
                {
                        try
                        {
                                HibernateUtil.releaseSession(session);
                        }
                        catch (HibernateException e)
                        {
                                throw new ULMSSysException("HibernateException while Hibernate update:  " + e, e);
                        }
                }


                return page;

        }

        public List search(int aspID, int relationID, List type, String key)
                throws NewDocumentSysException
        {
                List lt = null;
                Session session = null;
                try
                {
                        key= StringEscapeUtils.escapeSql(key);
                        String sql = "from NewDocumentModel where docid>1 and aspID=" + aspID + " and relationID=" + relationID + " and (docName like '%" + key + "%' or contentClob like '%" + key + "%')";

                        if (type.size() > 0)
                        {
                                sql += " and ( type='" + type.get(0) + "' ";

                                for (int i = 1; i < type.size(); i++)
                                {
                                        sql += " or type ='" + type.get(i) + "' ";
                                }

                                sql += " ) ";
                        }
                        sql += " order by docID desc";
                        session = HibernateUtil.getSession();
                        lt = session.createQuery(sql).setCacheable(true).setCacheRegion("com.ulearning.ulms.tools.newdocument.dao.NewDocumentDAOImpl").list(); //lt = HibernateDAO.find(sql);
                }
                catch (Exception e)
                {
                        e.printStackTrace();
                        throw new NewDocumentSysException(e);
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

                return lt;
        }

        public List search(int aspID, int relationID, List types, List excludeTypes, String key)
                throws NewDocumentSysException
        {
                List lt = null;
                Session session = null;
                try
                {
                        key= StringEscapeUtils.escapeSql(key);
                        String sql = "from NewDocumentModel where docid>1 and aspID=" + aspID + " and relationID=" + relationID + " and (docName like '%" + key + "%' or contentClob like '%" + key + "%')";

                        if (types != null && types.size() > 0)
                        {
                                sql += " and ( type='" + types.get(0) + "' ";

                                for (int i = 1; i < types.size(); i++)
                                {
                                        sql += " or type ='" + types.get(i) + "' ";
                                }

                                sql += " ) ";
                        }

                        if (excludeTypes != null && excludeTypes.size() > 0)
                        {
                                sql += " and ( type!='" + excludeTypes.get(0) + "' ";

                                for (int i = 1; i < excludeTypes.size(); i++)
                                {
                                        sql += " and type !='" + excludeTypes.get(i) + "' ";
                                }

                                sql += " ) ";
                        }
                        sql += " order by docID desc";
                        //System.out.println("[NewDocumentDAOImpl]search-------###sql = " + sql);
                        session = HibernateUtil.getSession();
                        lt = session.createQuery(sql).setCacheable(true).setCacheRegion("com.ulearning.ulms.tools.newdocument.dao.NewDocumentDAOImpl").list(); //lt = HibernateDAO.find(sql);
                }
                catch (Exception e)
                {
                        e.printStackTrace();
                        throw new NewDocumentSysException(e);
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

                return lt;
        }

        public void updateNewDocumentModel(NewDocumentModel model)
                throws NewDocumentSysException
        {
                HibernateDAO.update(model);
        }

        public void delDocumentForOrderIndex(List documenIDs)
                throws NewDocumentSysException
        {
/*                Connection conn = null;
                Statement stmt = null;
                ResultSet rs = null;

                try
                {
                        conn = DBUtil.getConnection();
                        stmt = conn.createStatement();
                        for (int i = 0; i < documenIDs.size(); i++)
                        {

                                String updatesql = "update T_NEWDOCUMENT_TAB tnt set tnt.orderIndex=tnt.orderIndex-1 where tnt.DOCID>" + documenIDs.get(i) + " and type='" + NewDocumentConstants.ZHAOSHENG_JIANZHANG_TYPE + "'";
                                stmt.executeUpdate(updatesql);
                        }
                }
                catch (SQLException se)
                {
                        throw new NewDocumentSysException(se);
                }
                finally
                {
                        DBUtil.closeResultSet(rs);
                        DBUtil.closeStatement(stmt);
                        DBUtil.closeConnection(conn);
                }

                deleteDocument(documenIDs);*/
        }

        
        public static void insert()
                throws NewDocumentSysException
        {
                try
                {
                        NewDocumentModel addModel = new NewDocumentModel();
                        addModel.setType("11");
                        addModel.setParentID(0);
                        addModel.setAspID(0);
                        addModel.setRelationID(0);
                        addModel.setUserID(0);
                        addModel.setDocName("Title1");   //标题
                        addModel.setRemark2("FromWhere");    //来源

                        addModel.setUserName("Author");
                        addModel.setCreateDate(new Date());
                        addModel.setModifyDate(new Date());
                        addModel.setRemark1("Jacky");
                        addModel.setLink("-1");
                        NewDocumentDAOImpl newDocumentDAOImpl = new NewDocumentDAOImpl();
                        newDocumentDAOImpl.insertDocument(addModel);
                }
                catch (Exception e)
                {
                        e.printStackTrace();
                }

        }

        /**
         * 重置其它专家库字段(是否要在学习平台首页的教师推荐处显示)的状态为0.
         */
        public void resetOtherExpertViewInLearningPortalStatus(int docid)
                throws NewDocumentSysException
        {
                List lt = null;
                Connection conn = null;
                Statement stmt = null;
                try
                {
                        String sql = "update T_PORTAL_TAB set isOpenGuest='0' where docID!=" + docid +
                                " and trim(type)='" + NewDocumentConstants.EXPERT + "'";
                        //System.out.println("[NewDocumentDAOImpl]resetOtherExpertViewInLearningPortalStatus sql="+sql);
                        conn = DBUtil.getConnection();
                        stmt = conn.createStatement();
                        int num = stmt.executeUpdate(sql);
                        //System.out.println("[NewDocumentDAOImpl]resetOtherExpertViewInLearningPortalStatus update "+num+" line!");
                }
                catch (SQLException e)
                {
                        e.printStackTrace();
                        throw new ULMSSysException("SQLException while Hibernate add:  " + e, e);
                }
                finally
                {
                        try
                        {
                                DBUtil.closeStatement(stmt);
                                DBUtil.closeConnection(conn);
                        }
                        catch (Exception e)
                        {
                                e.printStackTrace();
                                throw new ULMSSysException("HibernateException while Hibernate save:  " + e, e);
                        }
                }
        }

        /**
         * 返回要在学习平台首页的推荐专家.
         */
        public NewDocumentModel getViewInLearningPortalExpert()
                throws NewDocumentSysException
        {
                List list = null;
                Session session = null;
                NewDocumentModel ndm = null;
                try
                {
                        String sql = "from NewDocumentModel where isOpenGuest='1'" +
                                " and type='" + NewDocumentConstants.EXPERT + "'";

                        sql += " order by docID desc";
                        session = HibernateUtil.getSession();
                        list = session.createQuery(sql).list();
                        if (!list.isEmpty())
                        {
                                ndm = (NewDocumentModel) list.get(0);
                        }
                }
                catch (Exception e)
                {
                        e.printStackTrace();
                        throw new NewDocumentSysException(e);
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
                return ndm;
        }

}