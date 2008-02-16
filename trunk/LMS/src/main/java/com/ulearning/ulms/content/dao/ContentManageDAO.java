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
         * �����ض����͵���Դ�б�
         *
         * @throws ContentManageSysException
         */
        public PagerList getContentsByContentType(int contentType, int pageNo,
                                                  int pageSize) throws ContentManageSysException;

        /**
         * ���ݴ����hql�Ϳ�ʼ���ڣ��������ڣ�pageSize,pageNumber��ѯ
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
         * �ƶ�.
         *
         * @param moveCatalogs Ҫ���ƶ���Ŀ¼
         * @param moveContent  Ҫ���ƶ�������
         * @param catalogID    Ҫ�ƶ���Ŀ��Ŀ¼
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
         * get a content List.��ʱ����
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
         * �ж���ͬһ����Ŀ¼���Ƿ����ͬ������Ŀ¼
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
         * �ж���ͬһ����Ŀ¼���Ƿ����ͬ������Ŀ¼ updateʱʹ��
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
         * ��ʱ���á�
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
         * �������configID
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
         * �ж������Ƿ����
         *
         * @param language
         * @param aspID
         * @throws ContentManageSysException
         */
        public boolean isExistLanguage(String language, String aspID)
                throws ContentManageSysException;

        /**
         * �ж�contentLanguage�Ƿ�ʹ��
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
         * ��������ӵ�ʱ���ж�contentServer�Ƿ����
         *
         * @param name
         * @param aspID
         * @return
         * @throws ContentManageSysException
         */
        public boolean isExistContentServer(String name, String aspID)
                throws ContentManageSysException;

        /**
         * �������޸ĵ�ʱ���ж�contengServer�Ƿ����
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
         * ���ȫ��contentServer
         *
         * @param aspID
         * @throws ContentManageSysException
         */
        public List getAllContentServer(String aspID)
                throws ContentManageSysException;

        /**
         * ͨ��contentServerID�õ�contentServer
         *
         * @param contentServerID
         * @throws ContentManageSysException
         */
        public ContentServerModel getContentServer(int contentServerID)
                throws ContentManageSysException;

        /**
         * �õ�Ĭ�ϵ�server
         *
         * @param aspID
         * @return
         * @throws ContentManageSysException
         */
        public ContentServerModel getDefaultContentServer(String aspID)
                throws ContentManageSysException;

        //***************************  ContentTypeModel  *******************************//
        /**
         * �ж�contentType�Ƿ��Ѿ�����
         *
         * @param contentType
         * @param aspID
         * @return boolean
         * @throws ContentManageSysException
         */
        public boolean isExistContentType(String contentType, String aspID)
                throws ContentManageSysException;

        /**
         * �õ�����orderIndex
         *
         * @param aspID
         * @return
         * @throws ContentManageSysException
         */
        public int getMaxOrderIndexFromContentType(String aspID)
                throws ContentManageSysException;

        /**
         * �õ�ȫ����contentType
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
         * �ж�contentType�Ƿ�ʹ��
         *
         * @param contentTypeID
         * @throws ContentManageSysException
         */
        public boolean isUsingContentType(int contentTypeID)
                throws ContentManageSysException;

        /**
         * ͨ��contentTypeID�õ�contentTypeModel
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
         * ʹ��jdbc�޸�orderIndex,˭����hibernateд���Ը�һ��
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
         * ɾ��һ��contentType
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
         * �����ض�Ŀ¼�µ���Դ�б�
         *
         * @throws ContentManageSysException
         */
        public PagerList getContentsByCatalog(int parentID, int pageNo,
                                              int pageSize) throws ContentManageSysException;

        /**
         * ������Դ�б�
         * @param type     -1��������
         * @param relationID     ��˵��-1��������
         * @param relationIDs   ���԰������������ -1��������
         * @param creatorID      -1��������
         * @param contentTypeID   -1��������
         * @param parentID       ����Ŀ¼�� -1��������
         * @param auditByAdmin     -1��������
         * @param auditBySubAdmin    -1��������
         * @param isview             -1��������
         * @param pageNo             -1��������
         * @param pageSize           -1��������
         * @return
         * @throws ContentManageSysException
         */
        public PagerList getContents(int type, int relationID,int[] relationIDs,int creatorID,int contentTypeID,
                                     int parentID,int auditByAdmin,int auditBySubAdmin,int isview,
                                     int pageNo,int pageSize) throws ContentManageSysException;
        
        /**
         * ����ĳ�����͵���Դ��
         *
         * @param type        -1��������
         * @param relationID  -1��������
         * @param contentType -1��������
         * @param pageNo
         * @param pageSize
         * @return
         * @throws ContentManageSysException
         */
        public PagerList getContentsByType(int type, int relationID, int contentType, int pageNo,
                                           int pageSize) throws ContentManageSysException;


        /**
         * ����ʾ��У��ĳ�����͵�ʾ��У��Դ��
         *
         * @param relationID          ʾ��У�ɣ�
         * @param courseContentTypeID ʾ��У��Դ���͡�Ŀǰʹ��courseContentType,==-1:Ϊ��ʾȫ��
         * @param auditBySubAdmin     �Ƿ�ʾ��У����Ա��� ,==-1:Ϊ��ʾȫ��
         * @param pageNo
         * @param pageSize
         * @return
         * @throws ContentManageSysException
         */
        public PagerList getShiFanXiaoContentsAuditBySubAdmin(int relationID, int courseContentTypeID,
                                                              int auditBySubAdmin, int pageNo, int pageSize);

         /**
         * ���ض��ʾ��У��ĳ�����͵�ʾ��У��Դ��
         *
         * @param relationID          ʾ��У�ɣ�
         * @param courseContentTypeID ʾ��У��Դ���͡�Ŀǰʹ��courseContentType,==-1:Ϊ��ʾȫ��
         * @param auditBySubAdmin     �Ƿ�ʾ��У����Ա��� ,==-1:Ϊ��ʾȫ��
         * @param pageNo
         * @param pageSize
         * @return
         * @throws ContentManageSysException
         */
        public PagerList getShiFanXiaoContentsAuditBySubAdmin(int relationID[], int courseContentTypeID,
                                                              int auditBySubAdmin, int pageNo, int pageSize);
        /**
         * (ʾ��У��ҳ)������У��ʾ��У���ͨ����ĳ�����͵�ʾ��У��Դ��
         *
         * @param relationID          ʾ��У�ɣ�
         * @param courseContentTypeID ʾ��У��Դ���͡�Ŀǰʹ��courseContentType,==-1:Ϊ��ʾȫ��
         * @param pageNo
         * @param pageSize
         * @return
         * @throws ContentManageSysException
         */
        public PagerList getShiFanXiaoContentsDispalyInPortal(int relationID, int courseContentTypeID,
                                                              int pageNo, int pageSize);
        /**
         * �������е�ĳ�����͵�ʾ��У��Դ��
         *
         * @param courseContentTypeID ʾ��У��Դ���͡�Ŀǰʹ��courseContentType,==-1:Ϊ��ʾȫ��
         * @param auditByAdmin        �Ƿ���У����Ա���,==-1:Ϊ��ʾȫ��
         * @param pageNo
         * @param pageSize
         * @return
         * @throws ContentManageSysException
         */
        public PagerList getShiFanXiaoContentsAuditByAdmin(int courseContentTypeID, int auditByAdmin,
                                                           int pageNo, int pageSize) throws ContentManageSysException;

        /**
         * ��ʾ������ʦ��ʾ��У��Դ
         *
         * @param creatorID           ������ʦ
         * @param courseContentTypeID �Ƿ���У����Ա���,==-1:Ϊ��ʾȫ��
         * @param auditByAdmin        �Ƿ���У����Ա���,==-1:Ϊ��ʾȫ��
         * @param pageNo
         * @param pageSize
         * @return
         */
        public PagerList getShiFanXiaoContentsByCreator(int creatorID, int courseContentTypeID, int auditByAdmin, int pageNo,
                                                        int pageSize) throws ContentManageSysException;

        /**
         * ��У����Ա�����Դ
         *
         * @param ids         ��Դ�б�
         * @param auditStatus
         * @throws ContentManageSysException
         */
        public void auditContentsByAdmin(List ids, int auditStatus) throws ContentManageSysException;

        /**
         * ʾ��У����Ա�����Դ
         *
         * @param ids         ��Դ�б�
         * @param auditStatus
         * @throws ContentManageSysException
         */
        public void auditContentsBySubAdmin(List ids, int auditStatus) throws ContentManageSysException;

        /**
         * ������Դ�б�
         *
         * @param type                -1��������
         * @param relationID          -1��������
         * @param relationIDs         -1��������
         * @param creatorID           -1��������
         * @param contentTypeID       -1��������
         * @param courseContentTypeID -1��������
         * @param parentID            -1��������
         * @param auditByAdmin        -1��������
         * @param auditBySubAdmin     -1��������
         * @param isview              -1��������
         * @param creator             null��������
         * @param publisher           null��������
         * @param keyword             null��������
         * @param orderBy             �����ֶΣ�Ĭ�ϰ�ID����
         * @param isIncludeSubCatalog �Ƿ������Ŀ¼
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
