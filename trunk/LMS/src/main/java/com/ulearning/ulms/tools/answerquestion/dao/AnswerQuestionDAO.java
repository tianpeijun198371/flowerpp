/**
 * NewDocumentDao.java.
 * User: Administrator  Date: 2005-3-4
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.tools.answerquestion.dao;

import com.ulearning.ulms.tools.answerquestion.exceptions.AnswerQuestionSysException;
import com.ulearning.ulms.tools.answerquestion.model.AQCatalogModel;
import com.ulearning.ulms.tools.answerquestion.model.AnswerQuestionModel;

import java.util.Date;
import java.util.List;


public interface AnswerQuestionDAO
{
        /**
         * ����ID��ȡһ����¼
         *
         * @param AQID
         * @return
         * @throws AnswerQuestionSysException
         */
        public AnswerQuestionModel getQuestion(int AQID)
                throws AnswerQuestionSysException;

        /**
         * ��ȡ���г�������
         *
         * @param type
         * @return
         * @throws AnswerQuestionSysException
         */
        public List getQuestionAll(int type, int relationID)
                throws AnswerQuestionSysException;

        /**
         * ��ȡ���й�����˽������
         *
         * @param type
         * @param relationID
         * @param status
         * @return
         * @throws AnswerQuestionSysException
         */
        public List getQuestionAllAttribute(int type, int relationID, String status)
                throws AnswerQuestionSysException;

        /**
         * ��ȡ���������ѻظ���δ�ظ�
         *
         * @param type
         * @param isReply
         * @return
         * @throws AnswerQuestionSysException
         */
        public List getQuestionReply(int type, String isReply, int relationID)
                throws AnswerQuestionSysException;

        /**
         * �ҵ�����
         *
         * @param type
         * @param submmiterID
         * @return
         * @throws AnswerQuestionSysException
         */
        public List getMyQuestion(int type, int submmiterID, int relationID)
                throws AnswerQuestionSysException;

        /**
         * �ҵ��ѻظ���δ�ظ�����
         *
         * @param type
         * @param submmiterID
         * @param isReply
         * @return
         * @throws AnswerQuestionSysException
         */
        public List getMyQuestionIsReply(int type, int submmiterID, String isReply,
                                         int relationID) throws AnswerQuestionSysException;

        /**
         * ���ݹؼ��ֲ�ѯ
         *
         * @param type
         * @param questionKey
         * @param relationID
         * @param status      null ��ʾ ��������Ч
         * @param isReply     null ��ʾ ��������Ч
         * @return
         * @throws AnswerQuestionSysException
         */
        public List getQuestionKey(int type, String[] questionKey, int relationID,
                                   String status, String isReply) throws AnswerQuestionSysException;

        /**
         * ���һ������
         *
         * @param addQuestionModel
         * @throws AnswerQuestionSysException
         */
        public void insertQuestion(AnswerQuestionModel addQuestionModel)
                throws AnswerQuestionSysException;

        /**
         * �޸�һ������
         *
         * @param updQuestionModel
         * @throws AnswerQuestionSysException
         */
        public void updateQuestiont(AnswerQuestionModel updQuestionModel)
                throws AnswerQuestionSysException;

        /**
         * ɾ��һ���������
         *
         * @param aqIDs
         * @throws AnswerQuestionSysException
         */
        public void deleteQuestiont(List aqIDs) throws AnswerQuestionSysException;

        /**
         * ����ID��ȡһ����¼
         *
         * @param catalogID
         * @return
         * @throws AnswerQuestionSysException
         */
        public AQCatalogModel getCatalog(int catalogID)
                throws AnswerQuestionSysException;

        /**
         * ����������ƶ�ȡһ���������
         *
         * @param name
         * @return
         * @throws AnswerQuestionSysException
         */
        public AQCatalogModel getCatalog(String name)
                throws AnswerQuestionSysException;

        /**
         * ��ȡ���г�������
         *
         * @param type
         * @return
         * @throws AnswerQuestionSysException
         */
        public List getAllCatalog(int type) throws AnswerQuestionSysException;

        /**
         * ���һ�����
         *
         * @param addQuestionModel
         * @throws AnswerQuestionSysException
         */
        public void insertCatalog(AQCatalogModel addQuestionModel)
                throws AnswerQuestionSysException;

        /**
         * �޸�һ�����
         *
         * @param updQuestionModel
         * @throws AnswerQuestionSysException
         */
        public void updateCatalog(AQCatalogModel updQuestionModel)
                throws AnswerQuestionSysException;

        /**
         * ɾ��һ��������
         *
         * @param catalogIDs
         * @throws AnswerQuestionSysException
         */
        public void deleteCatalog(List catalogIDs)
                throws AnswerQuestionSysException;

        public int getParent(int parent) throws AnswerQuestionSysException;

        /**
         * ����������ѯ
         *
         * @param type
         * @param relationID
         * @param questionTitle
         * @return
         * @throws AnswerQuestionSysException
         */
        public List getQuestionAllTitle(int type, int relationID,
                                        String questionTitle) throws AnswerQuestionSysException;

        /**
         * ���������ݲ�ѯ
         *
         * @param type
         * @param relationID
         * @param questionContent
         * @return
         * @throws AnswerQuestionSysException
         */
        public List getQuestionAllContent(int type, int relationID,
                                          String questionContent) throws AnswerQuestionSysException;

        /**
         * �������˲�ѯ
         *
         * @param type
         * @param relationID
         * @param submmiterName
         * @return
         * @throws AnswerQuestionSysException
         */
        public List getQuestionAllSubmmiterName(int type, int relationID,
                                                String submmiterName) throws AnswerQuestionSysException;

        /**
         * �����˲�ѯ
         *
         * @param type
         * @param relationID
         * @param replyerName
         * @return
         * @throws AnswerQuestionSysException
         */
        public List getQuestionAllReplyerName(int type, int relationID,
                                              String replyerName) throws AnswerQuestionSysException;

        /**
         * ����������ѯ
         *
         * @param type
         * @param relationID
         * @param parentID
         * @return
         * @throws AnswerQuestionSysException
         */
        public List getQuestionAllCatalog(int type, int relationID, int parentID)
                throws AnswerQuestionSysException;

        /**
         * ����������ȡ����������������
         *
         * @param type
         * @param relationID
         * @param questionTitle
         * @return
         * @throws AnswerQuestionSysException
         */
        public List getQuestionAllTitleAttribute(int type, int relationID,
                                                 String questionTitle, String status) throws AnswerQuestionSysException;

        /**
         * ���������ݲ�ѯ������������
         *
         * @param type
         * @param relationID
         * @param questionContent
         * @return
         * @throws AnswerQuestionSysException
         */
        public List getQuestionAllContentAttribute(int type, int relationID,
                                                   String questionContent, String status)
                throws AnswerQuestionSysException;

        /**
         * �������˲�ѯ
         *
         * @param type
         * @param relationID
         * @param submmiterName
         * @return
         * @throws AnswerQuestionSysException
         */
        public List getQuestionAllSubmmiterNameAttribute(int type, int relationID,
                                                         String submmiterName, String status) throws AnswerQuestionSysException;

        /**
         * �����˲�ѯ��������
         *
         * @param type
         * @param relationID
         * @param replyerName
         * @return
         * @throws AnswerQuestionSysException
         */
        public List getQuestionAllReplyerNameAttribute(int type, int relationID,
                                                       String replyerName, String status) throws AnswerQuestionSysException;

        /**
         * ����������ѯ��������
         *
         * @param type
         * @param relationID
         * @param parentID
         * @return
         * @throws AnswerQuestionSysException
         */
        public List getQuestionAllCatalogAttribute(int type, int relationID,
                                                   int parentID, String status) throws AnswerQuestionSysException;

        /**
         * ����������ȡ���������Ѵ�����
         *
         * @param type
         * @param relationID
         * @param questionTitle
         * @return
         * @throws AnswerQuestionSysException
         */
        public List getQuestionAllTitleReply(int type, int relationID,
                                             String questionTitle, String isReply) throws AnswerQuestionSysException;

        /**
         * ���������ݲ�ѯ�����Ѵ�����
         *
         * @param type
         * @param relationID
         * @param questionContent
         * @return
         * @throws AnswerQuestionSysException
         */
        public List getQuestionAllContentReply(int type, int relationID,
                                               String questionContent, String isReply)
                throws AnswerQuestionSysException;

        /**
         * �������˲�ѯ�Ѵ�����
         *
         * @param type
         * @param relationID
         * @param submmiterName
         * @return
         * @throws AnswerQuestionSysException
         */
        public List getQuestionAllSubmmiterNameReply(int type, int relationID,
                                                     String submmiterName, String isReply) throws AnswerQuestionSysException;

        /**
         * �����˲�ѯ�Ѵ�����
         *
         * @param type
         * @param relationID
         * @param replyerName
         * @return
         * @throws AnswerQuestionSysException
         */
        public List getQuestionAllReplyerNameReply(int type, int relationID,
                                                   String replyerName, String isReply) throws AnswerQuestionSysException;

        /**
         * ����������ѯ�Ѵ�����
         *
         * @param type
         * @param relationID
         * @param parentID
         * @return
         * @throws AnswerQuestionSysException
         */
        public List getQuestionAllCatalogReply(int type, int relationID,
                                               int parentID, String isReply) throws AnswerQuestionSysException;

        /**
         * ��ȡ��������Ϊ������ÿ�˵Ļظ���δ�ظ�����
         *
         * @param type
         * @param isReply
         * @param relationID
         * @param status
         * @return
         * @throws AnswerQuestionSysException
         */
        public List getQuestionAllAttributeReply(int type, String isReply,
                                                 int relationID, String status) throws AnswerQuestionSysException;

        /**
         * ����������ȡ���������Ѵ�����
         *
         * @param type
         * @param relationID
         * @param questionTitle
         * @return
         * @throws AnswerQuestionSysException
         */
        public List getQuestionAllTitleReplyAttribute(int type, int relationID,
                                                      String questionTitle, String isReply, String statrs)
                throws AnswerQuestionSysException;

        /**
         * ���������ݲ�ѯ�����Ѵ�����
         *
         * @param type
         * @param relationID
         * @param questionContent
         * @return
         * @throws AnswerQuestionSysException
         */
        public List getQuestionAllContentReplyAttribute(int type, int relationID,
                                                        String questionContent, String isReply, String status)
                throws AnswerQuestionSysException;

        /**
         * �������˲�ѯ�Ѵ�����
         *
         * @param type
         * @param relationID
         * @param submmiterName
         * @return
         * @throws AnswerQuestionSysException
         */
        public List getQuestionAllSubmmiterNameReplyAttribute(int type,
                                                              int relationID, String submmiterName, String isReply, String status)
                throws AnswerQuestionSysException;

        /**
         * �����˲�ѯ�Ѵ�����
         *
         * @param type
         * @param relationID
         * @param replyerName
         * @return
         * @throws AnswerQuestionSysException
         */
        public List getQuestionAllReplyerNameReplyAttribute(int type,
                                                            int relationID, String replyerName, String isReply, String status)
                throws AnswerQuestionSysException;

        /**
         * ����������ѯ�Ѵ�����
         *
         * @param type
         * @param relationID
         * @param parentID
         * @return
         * @throws AnswerQuestionSysException
         */
        public List getQuestionAllCatalogReplyAttribute(int type, int relationID,
                                                        int parentID, String isReply, String status)
                throws AnswerQuestionSysException;

        /**
         * ������ѯ�ҵ�����
         *
         * @param type
         * @param relationID
         * @param parentID
         * @param submmiterID
         * @return
         * @throws AnswerQuestionSysException
         */
        public List getMyQuestionCatalog(int type, int relationID, int parentID,
                                         int submmiterID) throws AnswerQuestionSysException;

        /**
         * ����������ȡ�ҵ�����
         *
         * @param type
         * @param relationID
         * @param questionTitle
         * @return
         * @throws AnswerQuestionSysException
         */
        public List getMyQuestionTitle(int type, int relationID,
                                       String questionTitle, int submmiterID)
                throws AnswerQuestionSysException;

        /**
         * ���������ݲ�ѯ�ҵ�����
         *
         * @param type
         * @param relationID
         * @param questionContent
         * @return
         * @throws AnswerQuestionSysException
         */
        public List getMyQuestionContent(int type, int relationID,
                                         String questionContent, int submmiterID)
                throws AnswerQuestionSysException;

        /**
         * �����˲�ѯ�ҵ�����
         *
         * @param type
         * @param relationID
         * @param replyerName
         * @return
         * @throws AnswerQuestionSysException
         */
        public List getMyQuestionReplyerName(int type, int relationID,
                                             String replyerName, int submmiterID) throws AnswerQuestionSysException;

        /**
         * �����˲�ѯ�ҵĻظ���δ�ظ�����
         *
         * @param type
         * @param relationID
         * @param replyerName
         * @return
         * @throws AnswerQuestionSysException
         */
        public List getMyQuestionReplyerNameIsReply(int type, int relationID,
                                                    String replyerName, int submmiterID, String isReply)
                throws AnswerQuestionSysException;

        /**
         * ���������ݲ�ѯ�һظ���δ�ظ�����
         *
         * @param type
         * @param relationID
         * @param questionContent
         * @return
         * @throws AnswerQuestionSysException
         */
        public List getMyQuestionContentIsReply(int type, int relationID,
                                                String questionContent, int submmiterID, String isReply)
                throws AnswerQuestionSysException;

        /**
         * ����������ȡ�ҵĻظ���δ�ظ�����
         *
         * @param type
         * @param relationID
         * @param questionTitle
         * @return
         * @throws AnswerQuestionSysException
         */
        public List getMyQuestionTitleIsReply(int type, int relationID,
                                              String questionTitle, int submmiterID, String isReply)
                throws AnswerQuestionSysException;

        /**
         * ������ѯ�ҵĻظ���δ�ظ�����
         *
         * @param type
         * @param relationID
         * @param parentID
         * @param submmiterID
         * @return
         * @throws AnswerQuestionSysException
         */
        public List getMyQuestionCatalogIsReply(int type, int relationID,
                                                int parentID, int submmiterID, String isReply)
                throws AnswerQuestionSysException;

        /**
         * add:yangds
         * ϵͳ��ҳ�б��ѯ-----�����������������е�������½�ߵ�����(submiterID=0)
         *
         * @param type        ���� 0����������    1��������  2���γ̴���
         * @param relationID
         * @param submmiterID ������
         * @param replyerID   ����
         * @param status      null��ʾ ����������    1:��ʾ�������⣬0����ʾ�ǳ�������
         * @param title       null��ʾ ����������    �������
         * @param content     null��ʾ ����������    ��������
         * @param parentID    -1��ʾ ����������      ����ID
         * @param isReply     null��ʾ ����������    1:��ʾ�ѻظ����⣬0����ʾδ�ظ�����
         * @param isUserful   null��ʾ ����������  1:��ʾ�������⣬0����ʾ�ǳ�������
         * @param startDate   null��ʾ ����������  ʱ������
         * @param endDate     null��ʾ ����������    ʱ������
         * @return
         * @throws AnswerQuestionSysException
         */
        public List getMyQuestionOnShow(int type, int relationID, int submmiterID,
                                        String status, String title, String content, int parentID,
                                        String isReply, String isUserful, int replyerID, Date startDate,
                                        Date endDate) throws AnswerQuestionSysException;

        /**
         * ͳ�ƻظ�������
         *
         * @param startDate
         * @param stopDate
         * @return
         * @throws AnswerQuestionSysException
         */
        public List countAnswer(String startDate, String stopDate)
                throws AnswerQuestionSysException;
}
