/**
 * NewDocumentWeblmpl.java.
 * User: Administrator  Date: 2005-3-16
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.tools.answerquestion.webimpls;

import com.ulearning.ulms.tools.answerquestion.dao.AnswerQuestionDAO;
import com.ulearning.ulms.tools.answerquestion.dao.AnswerQuestionDAOFactory;
import com.ulearning.ulms.tools.answerquestion.exceptions.AnswerQuestionSysException;
import com.ulearning.ulms.tools.answerquestion.model.AQCatalogModel;
import com.ulearning.ulms.tools.answerquestion.model.AnswerQuestionModel;
import com.ulearning.ulms.util.log.LogUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;


public class AnswerQuestionWeblmpl
{
        private static AnswerQuestionDAO dao;

        public AnswerQuestionWeblmpl() throws AnswerQuestionSysException
        {
                try
                {
                        dao = AnswerQuestionDAOFactory.getDAO();
                }
                catch (Exception e)
                {
                        e.printStackTrace();
                        throw new AnswerQuestionSysException(e);
                }
        }

        /**
         * ����ID��ȡһ����¼
         *
         * @param aqID
         * @return
         * @throws AnswerQuestionSysException
         */
        public AnswerQuestionModel getQuestion(int aqID)
                throws AnswerQuestionSysException
        {
                return dao.getQuestion(aqID);
        }

        /**
         * ��ȡ���г�������
         *
         * @param type
         * @return
         * @throws AnswerQuestionSysException
         */
        public List getQuestionAll(int type, int relationID)
                throws AnswerQuestionSysException
        {
                return dao.getQuestionAll(type, relationID);
        }

        /**
         * ��ȡ���������ѻظ���δ�ظ�
         *
         * @param type
         * @param isReply
         * @return
         * @throws AnswerQuestionSysException
         */
        public List getQuestionReply(int type, String isReply, int relationID)
                throws AnswerQuestionSysException
        {
                return dao.getQuestionReply(type, isReply, relationID);
        }

        /**
         * �ҵ�����
         *
         * @param type
         * @param submmiterID
         * @return
         * @throws AnswerQuestionSysException
         */
        public List getMyQuestion(int type, int submmiterID, int relationID)
                throws AnswerQuestionSysException
        {
                return dao.getMyQuestion(type, submmiterID, relationID);
        }

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
                                         int relationID) throws AnswerQuestionSysException
        {
                return dao.getMyQuestionIsReply(type, submmiterID, isReply, relationID);
        }

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
                                   String status, String isReply) throws AnswerQuestionSysException
        {
                return dao.getQuestionKey(type, questionKey, relationID, status, isReply);
        }

        public AQCatalogModel getCatalog(int catalogID)
                throws AnswerQuestionSysException
        {
                return dao.getCatalog(catalogID);
        }

        public List getAllCatalog(int type) throws AnswerQuestionSysException
        {
                return dao.getAllCatalog(type);
        }

        public int getParent(int parent) throws AnswerQuestionSysException
        {
                return dao.getParent(parent);
        }

        public void updAnswerQuestion(AnswerQuestionModel qModel)
                throws AnswerQuestionSysException
        {
                dao.updateQuestiont(qModel);
        }

        /**
         * ����������ȡ��������
         *
         * @param type
         * @param relationID
         * @param questionTitle
         * @return
         * @throws AnswerQuestionSysException
         */
        public List getQuestionAllTitle(int type, int relationID,
                                        String questionTitle) throws AnswerQuestionSysException
        {
                LogUtil.debug("answerQuestionWeblmpl", "getQuestionAllTitle");

                return dao.getQuestionAllTitle(type, relationID, questionTitle);
        }

        /**
         * ���������ݲ�ѯ����
         *
         * @param type
         * @param relationID
         * @param questionContent
         * @return
         * @throws AnswerQuestionSysException
         */
        public List getQuestionAllContent(int type, int relationID,
                                          String questionContent) throws AnswerQuestionSysException
        {
                LogUtil.debug("answerQuestionWeblmpl", "getQuestionAllContent");

                return dao.getQuestionAllContent(type, relationID, questionContent);
        }

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
                                                String submmiterName) throws AnswerQuestionSysException
        {
                LogUtil.debug("answerQuestionWeblmpl", "getQuestionAllSubmmiterName");

                return dao.getQuestionAllSubmmiterName(type, relationID, submmiterName);
        }

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
                                              String replyerName) throws AnswerQuestionSysException
        {
                LogUtil.debug("answerQuestionWeblmpl", "getQuestionAllReplyerName");

                return dao.getQuestionAllReplyerName(type, relationID, replyerName);
        }

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
                throws AnswerQuestionSysException
        {
                LogUtil.debug("answerQuestionWeblmpl", "getQuestionAllCatalog");

                return dao.getQuestionAllCatalog(type, relationID, parentID);
        }

        /**
         * ��ȡ���й������Գ�������
         *
         * @param type
         * @return
         * @throws AnswerQuestionSysException
         */
        public List getQuestionAllAttribute(int type, int relationID, String status)
                throws AnswerQuestionSysException
        {
                return dao.getQuestionAllAttribute(type, relationID, status);
        }

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
                                                 String questionTitle, String status) throws AnswerQuestionSysException
        {
                LogUtil.debug("answerQuestionWeblmpl", "getQuestionAllTitle");

                return dao.getQuestionAllTitleAttribute(type, relationID,
                        questionTitle, status);
        }

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
                throws AnswerQuestionSysException
        {
                LogUtil.debug("answerQuestionWeblmpl", "getQuestionAllContent");

                return dao.getQuestionAllContentAttribute(type, relationID,
                        questionContent, status);
        }

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
                                                         String submmiterName, String status) throws AnswerQuestionSysException
        {
                LogUtil.debug("answerQuestionWeblmpl", "getQuestionAllSubmmiterName");

                return dao.getQuestionAllSubmmiterNameAttribute(type, relationID,
                        submmiterName, status);
        }

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
                                                       String replyerName, String status) throws AnswerQuestionSysException
        {
                LogUtil.debug("answerQuestionWeblmpl", "getQuestionAllReplyerName");

                return dao.getQuestionAllReplyerNameAttribute(type, relationID,
                        replyerName, status);
        }

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
                                                   int parentID, String status) throws AnswerQuestionSysException
        {
                LogUtil.debug("answerQuestionWeblmpl", "getQuestionAllCatalog");

                return dao.getQuestionAllCatalogAttribute(type, relationID, parentID,
                        status);
        }

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
                                             String questionTitle, String isReply) throws AnswerQuestionSysException
        {
                LogUtil.debug("answerQuestionWeblmpl", "getQuestionAllTitle");

                return dao.getQuestionAllTitleReply(type, relationID, questionTitle,
                        isReply);
        }

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
                throws AnswerQuestionSysException
        {
                LogUtil.debug("answerQuestionWeblmpl", "getQuestionAllContent");

                return dao.getQuestionAllContentReply(type, relationID,
                        questionContent, isReply);
        }

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
                                                     String submmiterName, String isReply) throws AnswerQuestionSysException
        {
                LogUtil.debug("answerQuestionWeblmpl", "getQuestionAllSubmmiterName");

                return dao.getQuestionAllSubmmiterNameReply(type, relationID,
                        submmiterName, isReply);
        }

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
                                                   String replyerName, String isReply) throws AnswerQuestionSysException
        {
                LogUtil.debug("answerQuestionWeblmpl", "getQuestionAllReplyerName");

                return dao.getQuestionAllReplyerNameReply(type, relationID,
                        replyerName, isReply);
        }

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
                                               int parentID, String isReply) throws AnswerQuestionSysException
        {
                LogUtil.debug("answerQuestionWeblmpl", "getQuestionAllCatalog");

                return dao.getQuestionAllCatalogReply(type, relationID, parentID,
                        isReply);
        }

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
                                                 int relationID, String status) throws AnswerQuestionSysException
        {
                return dao.getQuestionAllAttributeReply(type, isReply, relationID,
                        status);
        }

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
                                                      String questionTitle, String isReply, String status)
                throws AnswerQuestionSysException
        {
                LogUtil.debug("answerQuestionWeblmpl", "getQuestionAllTitle");

                return dao.getQuestionAllTitleReplyAttribute(type, relationID,
                        questionTitle, isReply, status);
        }

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
                throws AnswerQuestionSysException
        {
                LogUtil.debug("answerQuestionWeblmpl", "getQuestionAllContent");

                return dao.getQuestionAllContentReplyAttribute(type, relationID,
                        questionContent, isReply, status);
        }

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
                throws AnswerQuestionSysException
        {
                LogUtil.debug("answerQuestionWeblmpl", "getQuestionAllSubmmiterName");

                return dao.getQuestionAllSubmmiterNameReplyAttribute(type, relationID,
                        submmiterName, isReply, status);
        }

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
                throws AnswerQuestionSysException
        {
                LogUtil.debug("answerQuestionWeblmpl", "getQuestionAllReplyerName");

                return dao.getQuestionAllReplyerNameReplyAttribute(type, relationID,
                        replyerName, isReply, status);
        }

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
                throws AnswerQuestionSysException
        {
                LogUtil.debug("answerQuestionWeblmpl", "getQuestionAllCatalog");

                return dao.getQuestionAllCatalogReplyAttribute(type, relationID,
                        parentID, isReply, status);
        }

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
                                         int submmiterID) throws AnswerQuestionSysException
        {
                return dao.getMyQuestionCatalog(type, relationID, parentID, submmiterID);
        }

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
                throws AnswerQuestionSysException
        {
                LogUtil.debug("answerQuestionWeblmpl", "getQuestionAllTitle");

                return dao.getMyQuestionTitle(type, relationID, questionTitle,
                        submmiterID);
        }

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
                throws AnswerQuestionSysException
        {
                LogUtil.debug("answerQuestionWeblmpl", "getQuestionAllContent");

                return dao.getMyQuestionContent(type, relationID, questionContent,
                        submmiterID);
        }

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
                                             String replyerName, int submmiterID) throws AnswerQuestionSysException
        {
                LogUtil.debug("answerQuestionWeblmpl", "getQuestionAllReplyerName");

                return dao.getMyQuestionReplyerName(type, relationID, replyerName,
                        submmiterID);
        }

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
                throws AnswerQuestionSysException
        {
                return dao.getMyQuestionCatalogIsReply(type, relationID, parentID,
                        submmiterID, isReply);
        }

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
                throws AnswerQuestionSysException
        {
                LogUtil.debug("answerQuestionWeblmpl", "getQuestionAllTitle");

                return dao.getMyQuestionTitleIsReply(type, relationID, questionTitle,
                        submmiterID, isReply);
        }

        /**
         * ���������ݲ�ѯ�ҵĻظ���δ�ظ�����
         *
         * @param type
         * @param relationID
         * @param questionContent
         * @return
         * @throws AnswerQuestionSysException
         */
        public List getMyQuestionContentIsReply(int type, int relationID,
                                                String questionContent, int submmiterID, String isReply)
                throws AnswerQuestionSysException
        {
                LogUtil.debug("answerQuestionWeblmpl", "getQuestionAllContent");

                return dao.getMyQuestionContentIsReply(type, relationID,
                        questionContent, submmiterID, isReply);
        }

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
                throws AnswerQuestionSysException
        {
                LogUtil.debug("answerQuestionWeblmpl", "getQuestionAllReplyerName");

                return dao.getMyQuestionReplyerNameIsReply(type, relationID,
                        replyerName, submmiterID, isReply);
        }

        /**
         * add:yangds
         * ϵͳ��ҳ�б��ѯ-----�����������������е�������½�ߵ�����(submiterID=0)
         *
         * @param type        ���� 0����������    1��������  2���γ̴���
         * @param relationID
         * @param submmiterID ������
         * @param replyerID   ����
         * @param status      null��ʾ ����������    1:��ʾ�������⣬0����ʾ��������
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
                                        String isReply, String isUserful, int replyerID, String startDate,
                                        String endDate) throws AnswerQuestionSysException
        {
                Date start = null;
                Date end = null;
                LogUtil.debug("answerQuestionWeblmpl", "getQuestionAllReplyerName");

                if ((startDate != null) && !startDate.equals("null") &&
                        !startDate.equals(""))
                {
                        String updStart = startDate.replaceAll("-", "/");
                        start = new Date(updStart);
                }

                if ((endDate != null) && !endDate.equals("null") &&
                        !endDate.equals(""))
                {
                        String updEnd = endDate.replaceAll("-", "/");
                        end = new Date(updEnd);
                }

                return dao.getMyQuestionOnShow(type, relationID, submmiterID, status,
                        title, content, parentID, isReply, isUserful, replyerID, start, end);
        }
}
