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
         * 根据ID获取一条记录
         *
         * @param AQID
         * @return
         * @throws AnswerQuestionSysException
         */
        public AnswerQuestionModel getQuestion(int AQID)
                throws AnswerQuestionSysException;

        /**
         * 读取所有常见问题
         *
         * @param type
         * @return
         * @throws AnswerQuestionSysException
         */
        public List getQuestionAll(int type, int relationID)
                throws AnswerQuestionSysException;

        /**
         * 读取所有公共或私人问题
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
         * 读取常见问题已回复或未回复
         *
         * @param type
         * @param isReply
         * @return
         * @throws AnswerQuestionSysException
         */
        public List getQuestionReply(int type, String isReply, int relationID)
                throws AnswerQuestionSysException;

        /**
         * 我的问题
         *
         * @param type
         * @param submmiterID
         * @return
         * @throws AnswerQuestionSysException
         */
        public List getMyQuestion(int type, int submmiterID, int relationID)
                throws AnswerQuestionSysException;

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
                                         int relationID) throws AnswerQuestionSysException;

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
                                   String status, String isReply) throws AnswerQuestionSysException;

        /**
         * 添加一条问题
         *
         * @param addQuestionModel
         * @throws AnswerQuestionSysException
         */
        public void insertQuestion(AnswerQuestionModel addQuestionModel)
                throws AnswerQuestionSysException;

        /**
         * 修改一条问题
         *
         * @param updQuestionModel
         * @throws AnswerQuestionSysException
         */
        public void updateQuestiont(AnswerQuestionModel updQuestionModel)
                throws AnswerQuestionSysException;

        /**
         * 删除一或多条问题
         *
         * @param aqIDs
         * @throws AnswerQuestionSysException
         */
        public void deleteQuestiont(List aqIDs) throws AnswerQuestionSysException;

        /**
         * 根据ID获取一条记录
         *
         * @param catalogID
         * @return
         * @throws AnswerQuestionSysException
         */
        public AQCatalogModel getCatalog(int catalogID)
                throws AnswerQuestionSysException;

        /**
         * 根据类别名称读取一个问题类别
         *
         * @param name
         * @return
         * @throws AnswerQuestionSysException
         */
        public AQCatalogModel getCatalog(String name)
                throws AnswerQuestionSysException;

        /**
         * 读取所有常见问题
         *
         * @param type
         * @return
         * @throws AnswerQuestionSysException
         */
        public List getAllCatalog(int type) throws AnswerQuestionSysException;

        /**
         * 添加一个类别
         *
         * @param addQuestionModel
         * @throws AnswerQuestionSysException
         */
        public void insertCatalog(AQCatalogModel addQuestionModel)
                throws AnswerQuestionSysException;

        /**
         * 修改一个类别
         *
         * @param updQuestionModel
         * @throws AnswerQuestionSysException
         */
        public void updateCatalog(AQCatalogModel updQuestionModel)
                throws AnswerQuestionSysException;

        /**
         * 删除一或多条类别
         *
         * @param catalogIDs
         * @throws AnswerQuestionSysException
         */
        public void deleteCatalog(List catalogIDs)
                throws AnswerQuestionSysException;

        public int getParent(int parent) throws AnswerQuestionSysException;

        /**
         * 按问题标题查询
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
         * 按问题内容查询
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
         * 按提问人查询
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
         * 按答复人查询
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
         * 按问题类别查询
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
         * 按问题标题读取公共属性所有问题
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
                throws AnswerQuestionSysException;

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
                                                         String submmiterName, String status) throws AnswerQuestionSysException;

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
                                                       String replyerName, String status) throws AnswerQuestionSysException;

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
                                                   int parentID, String status) throws AnswerQuestionSysException;

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
                                             String questionTitle, String isReply) throws AnswerQuestionSysException;

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
                throws AnswerQuestionSysException;

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
                                                     String submmiterName, String isReply) throws AnswerQuestionSysException;

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
                                                   String replyerName, String isReply) throws AnswerQuestionSysException;

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
                                               int parentID, String isReply) throws AnswerQuestionSysException;

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
                                                 int relationID, String status) throws AnswerQuestionSysException;

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
                                                      String questionTitle, String isReply, String statrs)
                throws AnswerQuestionSysException;

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
                throws AnswerQuestionSysException;

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
                throws AnswerQuestionSysException;

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
                throws AnswerQuestionSysException;

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
                throws AnswerQuestionSysException;

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
                                         int submmiterID) throws AnswerQuestionSysException;

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
                throws AnswerQuestionSysException;

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
                throws AnswerQuestionSysException;

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
                                             String replyerName, int submmiterID) throws AnswerQuestionSysException;

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
                throws AnswerQuestionSysException;

        /**
         * 按问题内容查询我回复或未回复问题
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
                throws AnswerQuestionSysException;

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
                throws AnswerQuestionSysException;

        /**
         * add:yangds
         * 系统首页列表查询-----公共问题或个人问题中的匿名登陆者的问题(submiterID=0)
         *
         * @param type        类型 0：常见问题    1：问题解答  2：课程答疑
         * @param relationID
         * @param submmiterID 提问人
         * @param replyerID   答复人
         * @param status      null表示 此条件忽略    1:表示常见问题，0：表示非常柬问题
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
                                        String isReply, String isUserful, int replyerID, Date startDate,
                                        Date endDate) throws AnswerQuestionSysException;

        /**
         * 统计回复问题数
         *
         * @param startDate
         * @param stopDate
         * @return
         * @throws AnswerQuestionSysException
         */
        public List countAnswer(String startDate, String stopDate)
                throws AnswerQuestionSysException;
}
