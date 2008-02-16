/**
 * NewDocumentDao.java.
 * User: Administrator  Date: 2005-3-4
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.tools.newdocument.dao;


import com.ulearning.ulms.tools.newdocument.exceptions.NewDocumentSysException;
import com.ulearning.ulms.tools.newdocument.form.NewDocumentTreeModel;
import com.ulearning.ulms.tools.newdocument.model.NewDocumentCatalogModel;
import com.ulearning.ulms.tools.newdocument.model.NewDocumentModel;
import com.ulearning.ulms.tools.announcement.exceptions.AnnouncementSysException;

import java.util.List;

public interface NewDocumentDAO
{


        public NewDocumentTreeModel getTree(int orgID, int type, int parentID)
                throws NewDocumentSysException;

        public List getCatalogPath(int catalogID)
                throws NewDocumentSysException;

        public NewDocumentCatalogModel getCatalog(int catalogID)
                throws NewDocumentSysException;

        public void insertCatalog(NewDocumentCatalogModel addcatalogModel)
                throws NewDocumentSysException;

        public void updateCatalog(NewDocumentCatalogModel updcatalogModel)
                throws NewDocumentSysException;

        public void deleteCatalog(List catalogIDs)
                throws NewDocumentSysException;

        ;


        public NewDocumentModel getDocument(int documenID)
                throws NewDocumentSysException;

        public List getDocument(int orgID, int relationID, List type, int parentID)
                throws NewDocumentSysException;

        public List getDocumentByAll(int orgID, int relationID, List type, int parentID)
                throws NewDocumentSysException;

        public List getDocumentzl(int orgID, List type, int parentID)
                throws NewDocumentSysException;

        public List getCheck(int orgID, int relationID, int type, int parentID, int logType, String logTxt)
                throws NewDocumentSysException;

        public List getUserCheck(int orgID, int logType, String logTxt)
                throws NewDocumentSysException;

        public List getAllFromDate(int aspID, int relationID, int type, int parentID) throws NewDocumentSysException;

        public void insertDocument(NewDocumentModel addDocModel)
                throws NewDocumentSysException;


        public void updateDocument(NewDocumentModel updDocModel)
                throws NewDocumentSysException;


        public void deleteDocument(List documenIDs)
                throws NewDocumentSysException;

        public NewDocumentModel getCss(int aspID, int relationID, int type, int parentID)
                throws NewDocumentSysException;

        public List getAllCss(int aspID, int type)
                throws NewDocumentSysException;

        public void updateCss(NewDocumentModel updCssModel)
                throws NewDocumentSysException;

        public List getDocumentByOrderindex(int aspID, int relationID1, int typeZ, int parentID)
                throws NewDocumentSysException;

        public int getMaxOrderIndex()
                throws NewDocumentSysException;

        public List getDocumentByOrderindex_user(int aspID, int relationID1, int typeZ, int parentID, int userID)
                throws NewDocumentSysException;

        /**
         * 删除招生简章时使用,因为有排序，所以删除时要考虑一下orderIndex
         * <p/>
         * 因为不需要排序，所以暂时不用
         *
         * @param documenIDs
         * @throws NewDocumentSysException
         */
        public void delDocumentForOrderIndex(List documenIDs)
                throws NewDocumentSysException;

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
                throws NewDocumentSysException;

        public NewDocumentModel getNewDocument(String name, String type)
                throws NewDocumentSysException;

        public List search(int aspID, int relationID, List type, String key)
                throws NewDocumentSysException;

        public List search(int aspID, int relationID, List types, List excludeTypes, String key) throws NewDocumentSysException;

        public void updateNewDocumentModel(NewDocumentModel model)
                throws NewDocumentSysException;

        public List getDocumentByFlag(int aspID, int relationID, List type, int parentID, int flag)
                throws NewDocumentSysException;

        /**
         * 重置其它专家库字段(是否要在学习平台首页的教师推荐处显示)的状态为0.
         */
        public void resetOtherExpertViewInLearningPortalStatus(int docid)
                throws NewDocumentSysException;

        /**
         * 返回要在学习平台首页的推荐专家.
         */
        public NewDocumentModel getViewInLearningPortalExpert()
                throws NewDocumentSysException;
}

