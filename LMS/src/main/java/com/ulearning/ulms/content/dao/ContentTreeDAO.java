/**
 * ContentTreeDAO.java.
 * User: fengch Date: 2005-6-17 15:40:53
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.content.dao;

import com.ulearning.ulms.content.exceptions.ContentManageSysException;
import com.ulearning.ulms.content.model.ContentTreeModel;

import java.util.List;


public interface ContentTreeDAO
{
        /**
         * return the catalog's path list.
         * <br>��Ŀ¼�е���ʱʹ�ô˷�����λ��
         *
         * @param catalogID
         * @param relationID
         * @param type
         * @param aspID
         * @return
         * @throws ContentManageSysException
         */
        public List getCatalogPath(int catalogID, int relationID, int type,
                                   String aspID) throws ContentManageSysException;

        /**
         * ���ظ�Ŀ¼��һ�����������ݣ�����Ŀ¼�����ݣ���
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
                throws ContentManageSysException;

        /**
         * ���ظ�Ŀ¼��һ�����������ݣ�����Ŀ¼�����ݣ���
         *
         * @param catalogID
         * @param pageNo
         * @param pageSize
         * @param aspID
         * @return
         * @throws ContentManageSysException
         */
        public ContentTreeModel getTree(int catalogID, int relationID, int type,
                                        int pageNo, int pageSize, String aspID, boolean isAdmin)
                throws ContentManageSysException;

        /**
         * ���ظ�Ŀ¼��һ����Ŀ¼����
         *
         * @param catalogID
         * @param relationID
         * @param type
         * @param aspID
         * @return
         * @throws ContentManageSysException
         */
        public List getSubCatalog(int catalogID, int relationID, int type,
                                  String aspID) throws ContentManageSysException;

        /**
         * �ж����Ŀ¼�Ƿ񻹰�����Ŀ¼
         *
         * @param catalogID
         * @param aspID
         * @return
         * @throws ContentManageSysException
         */
        public boolean isHasSubCatalog(int catalogID, int relationID, int type,
                                       String aspID) throws ContentManageSysException;

        /**
         * ���ظ�Ŀ¼��һ�������ݸ����Ĵ�С�ܺ�
         *
         * @param catalogID
         * @param relationID
         * @param type
         * @param aspID
         * @return
         * @throws ContentManageSysException
         */
        public long sumSubCatalogContent(int catalogID, int relationID, int type,
                                         String aspID) throws ContentManageSysException;

        /**
         * ������Դ.����Դ��Identifier/Title/Subject/Description/Keyword��������
         * todo:shid
         *
         * @param key
         * @param pageNo
         * @param pageSize
         * @param aspID
         * @return
         * @throws ContentManageSysException
         */
        public ContentTreeModel search(String key, int relationID, int type,
                                       int pageNo, int pageSize, boolean isAdmin, String aspID)
                throws ContentManageSysException;

        public ContentTreeModel searchCourse(String key, int type, int pageNo,
                                             int pageSize, boolean isAdmin, String aspID)
                throws ContentManageSysException;
}
