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
         * 根据ID获取一条记录
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
         * 读取所有常见问题
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
         * 读取常见问题已回复或未回复
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
         * 我的问题
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
         * 我的已回复或未回复问题
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
         * 根据关键字查询
         *
         * @param type
         * @param questionKey
         * @param relationID
         * @param status      null 表示 此条件无效
         * @param isReply     null 表示 此条件无效
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
         * 按问题标题读取所有问题
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
         * 按问题内容查询问题
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
         * 按提问人查询
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
         * 按答复人查询
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
         * 按问题类别查询
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
         * 读取所有公共属性常见问题
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
         * 按问题标题读取公共属性所有问题
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
         * 按问题内容查询公共属性问题
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
         * 按提问人查询
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
         * 按答复人查询公共属性
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
         * 按问题类别查询公共属性
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
         * 按问题标题读取所有问题已答复问题
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
         * 按问题内容查询问题已答复问题
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
         * 按提问人查询已答复问题
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
         * 按答复人查询已答复问题
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
         * 按问题类别查询已答复问题
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
         * 读取所有属性为公共或每人的回复或未回复问题
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
         * 按问题标题读取所有问题已答复问题
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
         * 按问题内容查询问题已答复问题
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
         * 按提问人查询已答复问题
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
         * 按答复人查询已答复问题
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
         * 按问题类别查询已答复问题
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
         * 按类别查询我的问题
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
         * 按问题标题读取我的问题
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
         * 按问题内容查询我的问题
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
         * 按答复人查询我的问题
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
         * 按类别查询我的回复或未回复问题
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
         * 按问题标题读取我的回复或未回复问题
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
         * 按问题内容查询我的回复或未回复问题
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
         * 按答复人查询我的回复或未回复问题
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
         * 系统首页列表查询-----公共问题或个人问题中的匿名登陆者的问题(submiterID=0)
         *
         * @param type        类型 0：常见问题    1：问题解答  2：课程答疑
         * @param relationID
         * @param submmiterID 提问人
         * @param replyerID   答复人
         * @param status      null表示 此条件忽略    1:表示公共问题，0：表示个人问题
         * @param title       null表示 此条件忽略    问题标题
         * @param content     null表示 此条件忽略    问题内容
         * @param parentID    -1表示 此条件忽略      类型ID
         * @param isReply     null表示 此条件忽略    1:表示已回复问题，0：表示未回复问题
         * @param isUserful   null表示 此条件忽略  1:表示常见问题，0：表示非常柬问题
         * @param startDate   null表示 此条件忽略  时间上限
         * @param endDate     null表示 此条件忽略    时间下限
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
