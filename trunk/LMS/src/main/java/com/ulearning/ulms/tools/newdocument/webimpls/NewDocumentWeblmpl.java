/**
 * NewDocumentWeblmpl.java.
 * User: Administrator  Date: 2005-3-16
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.tools.newdocument.webimpls;

import com.ulearning.ulms.tools.newdocument.dao.NewDcumentDAOFactory;
import com.ulearning.ulms.tools.newdocument.dao.NewDocumentDAO;
import com.ulearning.ulms.tools.newdocument.exceptions.NewDocumentSysException;
import com.ulearning.ulms.tools.newdocument.model.NewDocumentModel;
import com.ulearning.ulms.tools.newdocument.model.NewDocumentCatalogModel;
import com.ulearning.ulms.tools.newdocument.form.NewDocumentTreeModel;
import com.ulearning.ulms.tools.announcement.exceptions.AnnouncementSysException;
import com.ulearning.ulms.core.util.StringUtil;

import java.util.List;

public class NewDocumentWeblmpl
{
        private NewDocumentDAO dao;

        public NewDocumentWeblmpl()
                throws NewDocumentSysException
        {
                try
                {

                        dao = NewDcumentDAOFactory.getDAO();
                }
                catch (Exception e)
                {
                        e.printStackTrace();
                        throw new NewDocumentSysException(e);
                }
        }


        public NewDocumentTreeModel getTree(int orgID, int type, int parentID)
                throws NewDocumentSysException
        {
                return dao.getTree(orgID, type, parentID);
        }

        public List getCatalogPath(int catalogID)
                throws NewDocumentSysException
        {
                return dao.getCatalogPath(catalogID);
        }

        public NewDocumentCatalogModel getCatalog(int catalogID)
                throws NewDocumentSysException
        {
                return dao.getCatalog(catalogID);
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
                List clList = dao.getCatalogList(type, parentID, aspID, name, description);
                return clList;
        }

        public List getAllFromDate(int aspID, int relationID, int type, int parentID) throws NewDocumentSysException
        {
                List acm = dao.getAllFromDate(aspID, relationID, type, parentID);
                return acm;
        }

        public List getDocument(int orgID, int relationID, List type, int parentID)
                throws NewDocumentSysException
        {
                return dao.getDocument(orgID, relationID, type, parentID);
        }

    /**
     * 得到总校的所有信息
     * @param orgID
     * @param relationID
     * @param type
     * @param parentID
     * @return
     * @throws NewDocumentSysException
     */
        public List getDocumentByAll(int orgID, int relationID, List type, int parentID)
                throws NewDocumentSysException
        {
                return dao.getDocumentByAll(orgID, relationID, type, parentID);
        }

        public List getDocumentzl(int orgID, List type, int parentID)
                throws NewDocumentSysException
        {
                return dao.getDocumentzl(orgID, type, parentID);
        }

        public List getCheck(int orgID, int relationID, int type, int parentID, int logType, String logTxt)
                throws NewDocumentSysException
        {
                return dao.getCheck(orgID, relationID, type, parentID, logType, logTxt);
        }

        public List getUserCheck(int orgID, int logType, String logTxt)
                throws NewDocumentSysException
        {
                return dao.getUserCheck(orgID, logType, logTxt);
        }

        /**
         * 察看一次详细内容，就将浏览次数加一
         *
         * @throws NewDocumentSysException
         */
        public NewDocumentModel getDocument(int document) throws NewDocumentSysException
        {
                NewDocumentModel ndm = dao.getDocument(document);
                String looks = ndm.getRemark1();
                ndm.setRemark1(String.valueOf(StringUtil.parseInt(looks) + 1));
                dao.updateNewDocumentModel(ndm);
                return ndm;
        }

        public NewDocumentModel getCss(int aspID, int relationID, int type, int parentID)
                throws NewDocumentSysException
        {
                return dao.getCss(aspID, relationID, type, parentID);
        }

        public List getCssOrder(int orgID, int relationID, int type, int parentID)
                throws NewDocumentSysException
        {
                return dao.getDocumentByOrderindex(orgID, relationID, type, parentID);
        }

        public List getCssOrder_user(int orgID, int relationID, int type, int parentID, int userID)
                throws NewDocumentSysException
        {
                return dao.getDocumentByOrderindex_user(orgID, relationID, type, parentID, userID);
        }

        public List search(int aspID, int relationID, List type, String key) throws NewDocumentSysException
        {
                return dao.search(aspID, relationID, type, key);
        }

        public List search(int aspID, int relationID, List types, List excludeTypes, String key) throws NewDocumentSysException
        {
                return dao.search(aspID, relationID, types, excludeTypes, key);
        }
}
