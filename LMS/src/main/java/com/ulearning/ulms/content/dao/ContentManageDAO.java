/**
 * ContentManageDAO.java.
 * User: fengch Date: 2005-5-30 17:08:16
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.content.dao;

import com.ulearning.ulms.content.exceptions.ContentManageSysException;
import com.ulearning.ulms.content.form.ContentAdvSearchForm;
import com.ulearning.ulms.content.model.*;
import com.ulearning.ulms.core.util.PagerList;

import java.util.Date;
import java.util.List;


public interface ContentManageDAO
{
        /**
         * 返回特定类型的资源列表。
         *
         * @throws ContentManageSysException
         */
        public PagerList getContentsByContentType(int contentType, int pageNo,
                                                  int pageSize) throws ContentManageSysException;

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
                throws ContentManageSysException;

        /**
         * 移动.
         *
         * @param moveCatalogs 要被移动的目录
         * @param moveContent  要被移动的内容
         * @param catalogID    要移动到目标目录
         * @param aspID
         */
        public void move(List moveCatalogs, List moveContent, int catalogID,
                         String aspID) throws ContentManageSysException;

        //***************************  ContentModel  *******************************//
        /**
         * get a content
         *
         * @param contentID
         * @return
         * @throws ContentManageSysException
         */
        public ContentModel getContent(int contentID)
                throws ContentManageSysException;

        /**
         * get a content List.暂时不用
         *
         * @param parentID
         * @param relationID
         * @param type
         * @param aspID
         * @return
         * @throws ContentManageSysException
         */
        public List getContent(int parentID, int relationID, int type, String aspID)
                throws ContentManageSysException;

        /**
         * add a content.
         *
         * @param cm
         * @throws ContentManageSysException
         */
        public void addContent(ContentModel cm) throws ContentManageSysException;

        /**
         * update a content.
         *
         * @param cm
         * @throws ContentManageSysException
         */
        public void updateContent(ContentModel cm) throws ContentManageSysException;

        /**
         * delete the contents.
         *
         * @param l the List that contain the Integer contentIDs
         * @throws ContentManageSysException
         */
        public void deleteContent(List l) throws ContentManageSysException;

        /**
         * delete the content.
         *
         * @param contentID
         * @throws ContentManageSysException
         */
        public void deleteContent(int contentID) throws ContentManageSysException;

        //***************************  ContentCatalogModel  *******************************//
        /**
         * get a ContentCatalogModel
         *
         * @param contentCatalogID
         * @throws ContentManageSysException
         */
        public ContentCatalogModel getContentCatalog(String contentCatalogID)
                throws ContentManageSysException;

        /**
         * 判断在同一个根目录下是否存在同名的子目录
         *
         * @param parentID
         * @param relationID
         * @param type
         * @param aspID
         * @return
         * @throws ContentManageSysException
         */
        public boolean isHasSameCatalogByParentID(String catalogName, int parentID,
                                                  int relationID, int type, String aspID)
                throws ContentManageSysException;

        /**
         * 判断在同一个根目录下是否存在同名的子目录 update时使用
         *
         * @param catalogName
         * @param parentID
         * @param relationID
         * @param type
         * @param aspID
         * @return
         * @throws ContentManageSysException
         */
        public boolean isHasSameCatalogByParentID_update(String catalogName,
                                                         int parentID, int catalogID, int relationID, int type, String aspID)
                throws ContentManageSysException;

        /**
         * 暂时不用。
         *
         * @param parentID
         * @param relationID
         * @param type
         * @param aspID
         * @return
         * @throws ContentManageSysException
         */
        public List getContentCatalog(int parentID, int relationID, int type,
                                      String aspID) throws ContentManageSysException;

        /**
         * add a Catalog.
         *
         * @param cm
         * @throws ContentManageSysException
         */
        public void addContentCatalog(ContentCatalogModel cm)
                throws ContentManageSysException;

        /**
         * update a Catalog.
         *
         * @param cm
         * @throws ContentManageSysException
         */
        public void updateContentCatalog(ContentCatalogModel cm)
                throws ContentManageSysException;

        /**
         * delete the Catalogs.
         *
         * @param l the List that contain the Integer contentCatalogIDs
         * @throws ContentManageSysException
         */
        public void deleteContentCatalog(List l) throws ContentManageSysException;

        /**
         * elete the Catalog..
         *
         * @param contentCatalogID
         * @throws ContentManageSysException
         */
        public void deleteContentCatalog(int contentCatalogID)
                throws ContentManageSysException;

        //***************************  ContentConfig  *******************************//
        /**
         * get a ContentConfig
         *
         * @param type
         * @param relationID
         * @return
         * @throws ContentManageSysException
         */
        public ContentConfigModel getContentConfig(String type, String relationID)
                throws ContentManageSysException;

        /**
         * add a ContentConfigModel.
         *
         * @param cm
         * @throws ContentManageSysException
         */
        public void addContentConfig(ContentConfigModel cm)
                throws ContentManageSysException;

        /**
         * update a ContentConfigModel.
         *
         * @param cm
         * @throws ContentManageSysException
         */
        public void updateContentConfig(ContentConfigModel cm)
                throws ContentManageSysException;

        /**
         * delete the ContentConfigModel.
         * @param l  the List that contain the ContentConfigModelPK
         * @throws ContentManageSysException
         */

        //public void deleteContentConfig(List l)
        //        throws ContentManageSysException;

        /**
         * 获得最大的configID
         *
         * @return
         * @throws ContentManageSysException
         */
        public int getMaxContentConfigID() throws ContentManageSysException;

        //***************************  ContentLanguageModel  *******************************//
        /**
         * get a ContentLanguageModel
         *
         * @param languageID
         * @return
         * @throws ContentManageSysException
         */
        public ContentLanguageModel getContentLanguage(int languageID)
                throws ContentManageSysException;

        /**
         * get all ContentLanguage
         *
         * @param aspID
         * @return
         * @throws ContentManageSysException
         */
        public List getAllContentLanguage(String aspID)
                throws ContentManageSysException;

        /**
         * add a ContentLanguageModel
         *
         * @param clm
         * @throws ContentManageSysException
         */
        public void addContentLanguage(ContentLanguageModel clm)
                throws ContentManageSysException;

        /**
         * update a ContentLanguageModel
         *
         * @param clm
         * @throws ContentManageSysException
         */
        public void updateContentLanguage(ContentLanguageModel clm)
                throws ContentManageSysException;

        /**
         * delete a ContentLanguateModel
         *
         * @param l
         * @throws ContentManageSysException
         */
        public void deleteContentLanguage(List l) throws ContentManageSysException;

        /**
         * 判断语言是否存在
         *
         * @param language
         * @param aspID
         * @throws ContentManageSysException
         */
        public boolean isExistLanguage(String language, String aspID)
                throws ContentManageSysException;

        /**
         * 判断contentLanguage是否被使用
         *
         * @param languageID
         * @throws ContentManageSysException
         */
        public boolean isUsingContentLanguage(int languageID)
                throws ContentManageSysException;

        //***************************  ContentServerModel  *******************************//
        /**
         * add a ContentServerModel.
         *
         * @param cm
         * @throws ContentManageSysException
         */
        public void addContentServer(ContentServerModel cm)
                throws ContentManageSysException;

        /**
         * update a ContentServerModel.
         *
         * @param cm
         * @throws ContentManageSysException
         */
        public void updateContentServer(ContentServerModel cm)
                throws ContentManageSysException;

        /**
         * delete the ContentServerModela.
         *
         * @param l the List that contain the Integer CONTENTSERVERIDs
         * @throws ContentManageSysException
         */
        public void deleteContentServer(List l) throws ContentManageSysException;

        /**
         * 用来在添加的时候判断contentServer是否存在
         *
         * @param name
         * @param aspID
         * @return
         * @throws ContentManageSysException
         */
        public boolean isExistContentServer(String name, String aspID)
                throws ContentManageSysException;

        /**
         * 用来在修改的时候判断contengServer是否存在
         *
         * @param congtentServerID
         * @param name
         * @param aspID
         * @return
         * @throws ContentManageSysException
         */
        public boolean isExistContentServer(int congtentServerID, String name,
                                            String aspID) throws ContentManageSysException;

        /**
         * 获得全部contentServer
         *
         * @param aspID
         * @throws ContentManageSysException
         */
        public List getAllContentServer(String aspID)
                throws ContentManageSysException;

        /**
         * 通过contentServerID得到contentServer
         *
         * @param contentServerID
         * @throws ContentManageSysException
         */
        public ContentServerModel getContentServer(int contentServerID)
                throws ContentManageSysException;

        /**
         * 得到默认的server
         *
         * @param aspID
         * @return
         * @throws ContentManageSysException
         */
        public ContentServerModel getDefaultContentServer(String aspID)
                throws ContentManageSysException;

        //***************************  ContentTypeModel  *******************************//
        /**
         * 判断contentType是否已经存在
         *
         * @param contentType
         * @param aspID
         * @return boolean
         * @throws ContentManageSysException
         */
        public boolean isExistContentType(String contentType, String aspID)
                throws ContentManageSysException;

        /**
         * 得到最大的orderIndex
         *
         * @param aspID
         * @return
         * @throws ContentManageSysException
         */
        public int getMaxOrderIndexFromContentType(String aspID)
                throws ContentManageSysException;

        /**
         * 得到全部的contentType
         *
         * @param aspID
         * @return
         * @throws ContentManageSysException
         */
        public List getAllContentType(String aspID)
                throws ContentManageSysException;

        /**
         * get a ContentTypeModel by a contentType.
         *
         * @param contentType
         * @param aspID
         * @throws ContentManageSysException
         */
        public ContentTypeModel getContentType(String contentType, String aspID)
                throws ContentManageSysException;

        /**
         * 判断contentType是否被使用
         *
         * @param contentTypeID
         * @throws ContentManageSysException
         */
        public boolean isUsingContentType(int contentTypeID)
                throws ContentManageSysException;

        /**
         * 通过contentTypeID得到contentTypeModel
         *
         * @param contentTypeID
         * @return
         * @throws ContentManageSysException
         */
        public ContentTypeModel getContentTypeByContentTypeID(int contentTypeID)
                throws ContentManageSysException;

        /**
         * add a ContentTypeModel.
         *
         * @param cm
         * @throws ContentManageSysException
         */
        public void addContentType(ContentTypeModel cm)
                throws ContentManageSysException;

        /**
         * update a ContentTypeModel.
         *
         * @param cm
         * @throws ContentManageSysException
         */
        public void updateContentType(ContentTypeModel cm)
                throws ContentManageSysException;

        /**
         * 使用jdbc修改orderIndex,谁会用hibernate写可以改一下
         *
         * @param orderIndex
         * @param aspID
         * @throws ContentManageSysException
         */
        public void updateContentTypeOrderIndex(int orderIndex, String aspID)
                throws ContentManageSysException;

        /**
         * delete the ContentTypeModels.
         *
         * @param l the List that contain the String CONTENTTYPEs
         * @throws ContentManageSysException
         */
        public void deleteContentType(List l) throws ContentManageSysException;

        /**
         * 删除一个contentType
         *
         * @param id
         * @throws ContentManageSysException
         */
        public void deleteContentType(int id) throws ContentManageSysException;

        //***************************  ScormItemModel  *******************************//
        /**
         * add a ScormItemModel.
         *
         * @param cm
         * @throws ContentManageSysException
         */
        public void addScormItem(ScormItemModel cm)
                throws ContentManageSysException;

        /**
         * update a ScormItemModel.
         *
         * @param cm
         * @throws ContentManageSysException
         */
        public void updateScormItem(ScormItemModel cm)
                throws ContentManageSysException;

        /**
         * delete the ScormItemModels.
         *
         * @param l the List that contain the String ITEMIDs
         * @throws ContentManageSysException
         */
        public void deleteScormItem(List l) throws ContentManageSysException;

        //***************************  ScormUserScoModel  *******************************//
        /**
         * add a ScormUserScoModel.
         *
         * @param cm
         * @throws ContentManageSysException
         */
        public void addScormUserSco(ScormUserScoModel cm)
                throws ContentManageSysException;

        /**
         * update a ScormUserScoModel.
         *
         * @param cm
         * @throws ContentManageSysException
         */
        public void updateScormUserSco(ScormUserScoModel cm)
                throws ContentManageSysException;

        /**
         * delete the ScormUserScoModels.
         *
         * @param l the List that contain the ScormUserScoModelPK
         * @throws ContentManageSysException
         */
        public void deleteScormUserSco(List l) throws ContentManageSysException;

        public List getContentListByUser(int userID)
                throws ContentManageSysException;

        /**
         * 返回特定目录下的资源列表。
         *
         * @throws ContentManageSysException
         */
        public PagerList getContentsByCatalog(int parentID, int pageNo,
                                              int pageSize) throws ContentManageSysException;

        /**
         * 返回资源列表
         * @param type     -1返回所有
         * @param relationID     所说，-1返回所有
         * @param relationIDs   可以包含多个机构， -1返回所有
         * @param creatorID      -1返回所有
         * @param contentTypeID   -1返回所有
         * @param parentID       所述目录， -1返回所有
         * @param auditByAdmin     -1返回所有
         * @param auditBySubAdmin    -1返回所有
         * @param isview             -1返回所有
         * @param pageNo             -1返回所有
         * @param pageSize           -1返回所有
         * @return
         * @throws ContentManageSysException
         */
        public PagerList getContents(int type, int relationID,int[] relationIDs,int creatorID,int contentTypeID,
                                     int parentID,int auditByAdmin,int auditBySubAdmin,int isview,
                                     int pageNo,int pageSize) throws ContentManageSysException;
        
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
                                           int pageSize) throws ContentManageSysException;


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
                                                              int auditBySubAdmin, int pageNo, int pageSize);

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
                                                              int auditBySubAdmin, int pageNo, int pageSize);
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
                                                              int pageNo, int pageSize);
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
                                                           int pageNo, int pageSize) throws ContentManageSysException;

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
                                                        int pageSize) throws ContentManageSysException;

        /**
         * 总校管理员审核资源
         *
         * @param ids         资源列表
         * @param auditStatus
         * @throws ContentManageSysException
         */
        public void auditContentsByAdmin(List ids, int auditStatus) throws ContentManageSysException;

        /**
         * 示范校管理员审核资源
         *
         * @param ids         资源列表
         * @param auditStatus
         * @throws ContentManageSysException
         */
        public void auditContentsBySubAdmin(List ids, int auditStatus) throws ContentManageSysException;

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
                                     boolean isIncludeSubCatalog, int pageNo, int pageSize);
}
