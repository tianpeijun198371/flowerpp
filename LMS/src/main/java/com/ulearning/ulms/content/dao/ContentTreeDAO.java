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
                                   String aspID) throws ContentManageSysException;

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
                throws ContentManageSysException;

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
                                        int pageNo, int pageSize, String aspID, boolean isAdmin)
                throws ContentManageSysException;

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
                                  String aspID) throws ContentManageSysException;

        /**
         * 判断这个目录是否还包括子目录
         *
         * @param catalogID
         * @param aspID
         * @return
         * @throws ContentManageSysException
         */
        public boolean isHasSubCatalog(int catalogID, int relationID, int type,
                                       String aspID) throws ContentManageSysException;

        /**
         * 返回该目录下一级的内容附件的大小总和
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
         * 搜索资源.按资源的Identifier/Title/Subject/Description/Keyword进行搜索
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
