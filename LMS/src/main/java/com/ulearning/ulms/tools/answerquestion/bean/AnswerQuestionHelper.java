/**
 * AnswerQuestionHelper.java.
 * User: Mouzb  Date: 2005-4-14
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.tools.answerquestion.bean;

import com.ulearning.ulms.core.exceptions.ULMSSysException;
import com.ulearning.ulms.core.mail.EmailServices;
import com.ulearning.ulms.core.mail.SmtpModel;
import com.ulearning.ulms.core.util.Config;
import com.ulearning.ulms.core.util.I18Util;
import com.ulearning.ulms.core.util.IOUtil;
import com.ulearning.ulms.core.util.PagerList;
import com.ulearning.ulms.tools.answerquestion.dao.AnswerQuestionDAO;
import com.ulearning.ulms.tools.answerquestion.dao.AnswerQuestionDAOFactory;
import com.ulearning.ulms.tools.answerquestion.exceptions.AnswerQuestionAppException;
import com.ulearning.ulms.tools.answerquestion.exceptions.AnswerQuestionSysException;
import com.ulearning.ulms.tools.answerquestion.form.AQCatalogForm;
import com.ulearning.ulms.tools.answerquestion.form.AnswerQuestionForm;
import com.ulearning.ulms.tools.answerquestion.model.AQCatalogModel;
import com.ulearning.ulms.tools.answerquestion.model.AnswerQuestionModel;
import com.ulearning.ulms.tools.message.bean.MessageDAOHelper;
import com.ulearning.ulms.util.log.LogUtil;

import java.io.File;
import java.util.*;


public class AnswerQuestionHelper
{
        private static AnswerQuestionDAO dao;

        static
        {
                try
                {
                        dao = AnswerQuestionDAOFactory.getDAO();
                }
                catch (Exception ex)
                {
                        ex.printStackTrace();
                }
        }

        /**
         * 添加一条问题
         *
         * @param qForm
         * @throws com.ulearning.ulms.tools.answerquestion.exceptions.AnswerQuestionSysException
         *
         */
        public static void insertQuestion(AnswerQuestionForm qForm)
                throws AnswerQuestionSysException
        {
                LogUtil.debug("course",
                        "[AnswerQuestionHelper:insertQuestion] Start insertQuestion");

                if (qForm.getSubmmiterName() == null)
                {
                        qForm.setSubmmiterName("未登录用户");
                }

                AnswerQuestionModel qModel = qForm.setAnswerQuestionModel();
                LogUtil.debug("yangds:",
                        "[AnswerQuestionHelper:insertQuestion]================relationID=" +
                                qModel.getReplyerID() + ";relationName=" +
                                qModel.getRelationName());
                //String userName = get***(userID);
                //qModel.setSubmmiterName(userName);
                //String courseName = get***(relationID);
                //qModel.setRelationID(courseName);
                qModel.setModifyDate(new Date());
                qModel.setCreateDate(new Date());

                try
                {
                        dao.insertQuestion(qModel);
                }
                catch (ULMSSysException e)
                {
                        LogUtil.debug("course",
                                "[AnswerQuestionHelper:insertQuestion] catch=" +
                                        "into the catch");
                        e.printStackTrace();
                        throw new AnswerQuestionSysException(e);
                }
        }

        /**
         * 修改一条问题
         *
         * @param qForm
         * @throws AnswerQuestionSysException
         */
        public static void updateDepth(AnswerQuestionForm qForm)
                throws AnswerQuestionSysException
        {
                LogUtil.debug("course",
                        "[AnswerQuestionHelper:updateQuestiont] Start updateQuestiont");

                AnswerQuestionModel qModel = dao.getQuestion(qForm.getAqID());
                LogUtil.debug("AnswerQuestion",
                        "[AnswerQuestionAction]-----updateQuesqForm=" +
                                qForm.getQuestionTitle());
                LogUtil.debug("AnswerQuestion",
                        "[AnswerQuestionAction]-----updateQuestiont ****qForm2getDepth" +
                                qForm.getDepth());
                qModel.setDepth(qModel.getDepth() + 1);

                try
                {
                        LogUtil.debug("course",
                                "[AnswerQuestionHelper:updateQuestiont] Start updateDAO");
                        dao.updateQuestiont(qModel);
                }
                catch (ULMSSysException e)
                {
                        LogUtil.debug("course",
                                "[AnswerQuestionHelper:updateQuestiont] catch=" +
                                        "into the catch");
                        e.printStackTrace();
                        throw new AnswerQuestionSysException(e);
                }
        }

        public static void updateQuestiont(AnswerQuestionForm qForm)
                throws AnswerQuestionSysException, AnswerQuestionAppException
        {
                LogUtil.debug("course",
                        "[AnswerQuestionHelper:updateQuestiont] Start updateQuestiont");

                AnswerQuestionModel qModel = dao.getQuestion(qForm.getAqID());
                LogUtil.debug("AnswerQuestion",
                        "[AnswerQuestionAction]-----updateQuestiont ****1");
                LogUtil.debug("AnswerQuestion",
                        "[AnswerQuestionAction]-----updateQuestiont ****qForm2getDepth" +
                                qForm.getDepth());
                LogUtil.debug("tools",
                        "[AnswerQuestionHelper:updateQuestiont]=============type=" +
                                qModel.getType());
                //qModel.setRelationID(qForm.getRelationID());
                //                qModel.setRelationName(qForm.getRelationName());
                qModel.setReplyerID(qForm.getReplyerID());
                qModel.setReplyerName(qForm.getReplyerName());
                //                qModel.setSubmmiterID(qForm.getSubmmiterID());
                //                qModel.setSubmmiterName(qForm.getSubmmiterName());
                qModel.setStatus(qForm.getStatus());
                qModel.setAnswerContent(qForm.getAnswerContent());
                //                qModel.setAnswerLink(qForm.getAnswerLink());
                //                qModel.setAnswerLinkTitle(qForm.getAnswerLinkTitle());
                //                qModel.setDepth(qForm.getDepth());
                //                qModel.setDisplayBeginDate(qForm.getDisplayBeginDate());
                //                qModel.setDisplayEndDate(qForm.getDisplayEndDate());
                qModel.setIsMail(qForm.getIsMail());
                qModel.setIsMessage(qForm.getIsMessage());
                //                qModel.setIsOpenGuest(qForm.getIsOpenGuest());
                qModel.setIsReply(qForm.getIsReply());
                qModel.setIsUserful(qForm.getIsUserful());
                //                qModel.setIsView(qForm.getIsView());
                qModel.setModifyDate(new Date());
                //                qModel.setOrderIndex(qForm.getOrderIndex());
                qModel.setParentID(qForm.getParentID());
                qModel.setQuestionContent(qForm.getQuestionContent());
                qModel.setQuestionKey(qForm.getQuestionKey());
                //                qModel.setQuestionLink(qForm.getQuestionLink());
                //                qModel.setQuestionLinkTitle(qForm.getQuestionLinkTitle());
                qModel.setQuestionTitle(qForm.getQuestionTitle());
                //                qModel.setRemark(qForm.getRemark());
                //                qModel.setRemark1(qForm.getRemark1());
                //                qModel.setRemark2(qForm.getRemark2());
                //                qModel.setRemark3(qForm.getRemark3());
                //                qModel.setRemark4(qForm.getRemark4());
                //                qModel.setRemark5(qForm.getRemark5());
                //                qModel.setRemark6(qForm.getRemark6());
                LogUtil.debug("AnswerQuestion",
                        "[AnswerQuestionAction]-----updateQuestiont ****2qModelgetDepth" +
                                qModel.getDepth());

                //假如QuestionLink＝＝－1，则说明附件没有输入。这时就不要修改他原来附件了
                if (qForm.getQuestionLink() != null)
                {
                        if ((qForm.getQuestionLink() != null) &&
                                !qForm.getQuestionLink().equals("") &&
                                !qForm.getQuestionLink().equals("-1"))
                        {
                                String link = qModel.getQuestionLink();

                                if ((link != null) && !link.equals("") && !link.equals("-1"))
                                {
                                        IOUtil.delAllFile(new File(Config.getUploadPhysicalPath() +
                                                qModel.getQuestionLink()));
                                }

                                if (!qForm.getQuestionLink().trim().equals("2"))
                                {
                                        qModel.setQuestionLinkTitle(qForm.getQuestionLinkTitle());
                                        qModel.setQuestionLink(qForm.getQuestionLink());
                                }
                                else
                                {
                                        qModel.setQuestionLinkTitle("");
                                        qModel.setQuestionLink("-1");
                                }
                        }

                        //假如QuestionLink＝＝－1，则说明附件没有输入。这时就不要修改他原来附件了
                }

                if (qForm.getAnswerLink() != null)
                {
                        if (!qForm.getAnswerLink().equals("") &&
                                !qForm.getAnswerLink().equals("-1"))
                        {
                                String link = qModel.getAnswerLink();

                                if ((link != null) && !link.equals("") && !link.equals("-1"))
                                {
                                        IOUtil.delAllFile(new File(Config.getUploadPhysicalPath() +
                                                qModel.getAnswerLink()));
                                }

                                if (!qForm.getAnswerLink().trim().equals("2"))
                                {
                                        qModel.setAnswerLinkTitle(qForm.getAnswerLinkTitle());
                                        qModel.setAnswerLink(qForm.getAnswerLink());
                                }
                                else
                                {
                                        qModel.setAnswerLinkTitle("");
                                        qModel.setAnswerLink("-1");
                                }
                        }
                }

                try
                {
                        LogUtil.debug("course",
                                "[AnswerQuestionHelper:updateQuestiont] Start updateDAO");
                        dao.updateQuestiont(qModel);
                }
                catch (ULMSSysException e)
                {
                        LogUtil.debug("course",
                                "[AnswerQuestionHelper:updateQuestiont] catch=" +
                                        "into the catch");
                        e.printStackTrace();
                        throw new AnswerQuestionSysException(e);
                }

                //发送信息
                if ((qModel.getType() != null) && qModel.getType().trim().equals("1"))
                {
                        try
                        {
                                LogUtil.debug("course",
                                        "[AnswerQuestionHelper:updateQuestiont] Start updateDAO===发送邮件！isMail=" +
                                                qModel.getIsMail());

                                //发送邮件
                                if ((qModel.getIsMail() != null) &&
                                        qModel.getIsMail().trim().equals("1"))
                                {
                                        sendMail(qModel);
                                }
                        }
                        catch (ULMSSysException e)
                        {
                                LogUtil.debug("course",
                                        "[AnswerQuestionHelper:updateQuestiont] catch=" +
                                                "into the catch");
                                e.printStackTrace();
                                throw new AnswerQuestionAppException(
                                        "[AnswerQuestionHelper:updateQuestiont]===发送邮件出错！" + e, e);
                        }
                }
                else
                {
                        try
                        {
                                LogUtil.debug("course",
                                        "[AnswerQuestionHelper:updateQuestiont] Start updateDAO======发送短消息！isMessage=" +
                                                qModel.getIsMessage());

                                //发送短消息
                                if ((qModel.getIsMessage() != null) &&
                                        qModel.getIsMessage().trim().equals("1"))
                                {
                                        sendMessage(qModel);
                                }
                        }
                        catch (ULMSSysException e)
                        {
                                LogUtil.debug("course",
                                        "[AnswerQuestionHelper:updateQuestiont] catch=" +
                                                "into the catch");
                                e.printStackTrace();
                                throw new AnswerQuestionAppException(
                                        "[AnswerQuestionHelper:updateQuestiont]===发送短消息出错！" + e, e);
                        }
                }
        }

        public static void update(AnswerQuestionForm qForm)
                throws AnswerQuestionSysException
        {
                LogUtil.debug("course",
                        "[AnswerQuestionHelper:updateQuestiont] Start updateQuestiont");

                AnswerQuestionModel qModel = dao.getQuestion(qForm.getAqID());
                LogUtil.debug("AnswerQuestion",
                        "[AnswerQuestionAction]-----updateQuestiont ****1");
                LogUtil.debug("AnswerQuestion",
                        "[AnswerQuestionAction]-----updateQuestiont ****qForm2getDepth" +
                                qForm.getDepth());
                LogUtil.debug("tools",
                        "[AnswerQuestionHelper:updateQuestiont]=============type=" +
                                qModel.getType());
                //qModel.setRelationID(qForm.getRelationID());
                //                qModel.setRelationName(qForm.getRelationName());
                //                qModel.setReplyerID(qForm.getReplyerID());
                //                qModel.setReplyerName(qForm.getReplyerName());
                //                qModel.setSubmmiterID(qForm.getSubmmiterID());
                //                qModel.setSubmmiterName(qForm.getSubmmiterName());
                //                qModel.setStatus(qForm.getStatus());
                //                qModel.setAnswerContent(qForm.getAnswerContent());
                //                qModel.setAnswerLink(qForm.getAnswerLink());
                //                qModel.setAnswerLinkTitle(qForm.getAnswerLinkTitle());
                //                qModel.setDepth(qForm.getDepth());
                //                qModel.setDisplayBeginDate(qForm.getDisplayBeginDate());
                //                qModel.setDisplayEndDate(qForm.getDisplayEndDate());
                //                qModel.setIsMail(qForm.getIsMail());
                //                qModel.setIsMessage(qForm.getIsMessage());
                //                qModel.setIsOpenGuest(qForm.getIsOpenGuest());
                //                qModel.setIsReply(qForm.getIsReply());
                //                qModel.setIsUserful(qForm.getIsUserful());
                //                qModel.setIsView(qForm.getIsView());
                //                qModel.setModifyDate(new Date());
                //                qModel.setOrderIndex(qForm.getOrderIndex());
                //                qModel.setParentID(qForm.getParentID());
                //                qModel.setQuestionContent(qForm.getQuestionContent());
                //                qModel.setQuestionKey(qForm.getQuestionKey());
                //                qModel.setQuestionLink(qForm.getQuestionLink());
                //                qModel.setQuestionLinkTitle(qForm.getQuestionLinkTitle());
                //                qModel.setQuestionTitle(qForm.getQuestionTitle());
                //                qModel.setRemark(qForm.getRemark());
                qModel.setRemark1(qForm.getRemark1());
                qModel.setRemark2(qForm.getRemark2());
                qModel.setRemark3(qForm.getRemark3());
                //                qModel.setRemark4(qForm.getRemark4());
                //                qModel.setRemark5(qForm.getRemark5());
                //                qModel.setRemark6(qForm.getRemark6());
                LogUtil.debug("AnswerQuestion",
                        "[AnswerQuestionAction]-----updateQuestiont ****2qModelgetDepth" +
                                qModel.getDepth());

                //假如QuestionLink＝＝－1，则说明附件没有输入。这时就不要修改他原来附件了
                try
                {
                        LogUtil.debug("course",
                                "[AnswerQuestionHelper:updateQuestiont] Start updateDAO");
                        dao.updateQuestiont(qModel);
                }
                catch (ULMSSysException e)
                {
                        LogUtil.debug("course",
                                "[AnswerQuestionHelper:updateQuestiont] catch=" +
                                        "into the catch");
                        e.printStackTrace();
                        throw new AnswerQuestionSysException(e);
                }
        }

        /**
         * 删除一或多条问题
         *
         * @param aqIDs
         * @throws AnswerQuestionSysException
         */
        public static void deleteQuestiont(List aqIDs)
                throws AnswerQuestionSysException
        {
                LogUtil.debug("course",
                        "[AnswerQuestionHelper:deleteQuestiont] Start deleteQuestiont");

                try
                {
                        AnswerQuestionModel questionModel = null;

                        if ((aqIDs != null) && (aqIDs.size() > 0))
                        {
                                for (int i = 0; i < aqIDs.size(); i++)
                                {
                                        questionModel = dao.getQuestion(((Integer) aqIDs.get(i)).intValue());

                                        String qLink = questionModel.getQuestionLink();
                                        String aLink = questionModel.getAnswerLink();

                                        if ((qLink != null) && !qLink.equals("-1") &&
                                                !qLink.equals(""))
                                        {
                                                IOUtil.delAllFile(new File(Config.getUploadPhysicalPath() +
                                                        qLink));
                                        }

                                        if ((aLink != null) && !aLink.equals("-1") &&
                                                !aLink.equals(""))
                                        {
                                                IOUtil.delAllFile(new File(Config.getUploadPhysicalPath() +
                                                        aLink));
                                        }
                                }
                        }

                        dao.deleteQuestiont(aqIDs);
                }
                catch (ULMSSysException e)
                {
                        LogUtil.debug("course",
                                "[AnswerQuestionHelper:deleteQuestiont] catch=" +
                                        "into the catch");
                        e.printStackTrace();
                        throw new AnswerQuestionSysException(e);
                }
        }

        public static List getQuestionKey(AnswerQuestionForm qForm)
                throws AnswerQuestionSysException
        {
                List quList = null;

                try
                {
                        String key = qForm.getQuestionKey();
                        int relationID = qForm.getRelationID();
                        int type = Integer.parseInt(qForm.getType());
                        String[] str_key = key.split(",");
                        key = key.replace('，', ',');
                        quList = dao.getQuestionKey(type, str_key, relationID, null, null);
                }
                catch (ULMSSysException e)
                {
                        LogUtil.debug("course",
                                "[AnswerQuestionHelper:deleteQuestiont] catch=" +
                                        "into the catch");
                        e.printStackTrace();
                        throw new AnswerQuestionSysException(e);
                }

                return quList;
        }

        public static void addCatalog(AQCatalogForm cForm)
                throws AnswerQuestionSysException
        {
                LogUtil.debug("course",
                        "[AnswerQuestionHelper:insertQuestion] Start insertQuestion");

                AQCatalogModel cModel = new AQCatalogModel();
                //String userName = get***(userID);
                //qModel.setSubmmiterName(userName);
                //String courseName = get***(relationID);
                //qModel.setRelationID(courseName);
                cModel.setCatalogName(cForm.getCatalogName());
                cModel.setDescription(cForm.getDescription());
                cModel.setType(cForm.getType());
                cModel.setCreateDate(new Date());

                try
                {
                        dao.insertCatalog(cModel);
                }
                catch (ULMSSysException e)
                {
                        LogUtil.debug("course",
                                "[AnswerQuestionHelper:insertQuestion] catch=" +
                                        "into the catch");
                        e.printStackTrace();
                        throw new AnswerQuestionSysException(e);
                }
        }

        /**
         * @param id
         * @return
         * @throws AnswerQuestionSysException
         */
        public static AQCatalogModel getCatalog(int id)
                throws AnswerQuestionSysException
        {
                LogUtil.debug("course",
                        "[AnswerQuestionHelper:insertQuestion] Start insertQuestion");

                AQCatalogModel cModel = null;

                //String userName = get***(userID);
                //qModel.setSubmmiterName(userName);
                //String courseName = get***(relationID);
                //qModel.setRelationID(courseName);
                try
                {
                        cModel = dao.getCatalog(id);
                }
                catch (ULMSSysException e)
                {
                        LogUtil.debug("course",
                                "[AnswerQuestionHelper:insertQuestion] catch=" +
                                        "into the catch");
                        e.printStackTrace();
                        throw new AnswerQuestionSysException(e);
                }

                return cModel;
        }

        public static void updCatalog(AQCatalogForm cForm)
                throws AnswerQuestionSysException
        {
                LogUtil.debug("course",
                        "[AnswerQuestionHelper:insertQuestion] Start insertQuestion");

                AQCatalogModel cModel = new AQCatalogModel();
                LogUtil.debug("course",
                        "[AnswerQuestionAction*updCatalog]getCatalogID=" +
                                cForm.getCatalogID());
                LogUtil.debug("course",
                        "[AnswerQuestionHelper*updCatalog]cForm getCatalogName=" +
                                cForm.getCatalogName());
                LogUtil.debug("course",
                        "[AnswerQuestionHelper*updCatalog]cForm getType=" +
                                cForm.getType());
                LogUtil.debug("course",
                        "[AnswerQuestionHelper*updCatalog]cForm getDescription=" +
                                cForm.getDescription());
                cModel = dao.getCatalog(cForm.getCatalogID());
                //String userName = get***(userID);
                //qModel.setSubmmiterName(userName);
                //String courseName = get***(relationID);
                //qModel.setRelationID(courseName);
                cModel.setCatalogName(cForm.getCatalogName());
                cModel.setDescription(cForm.getDescription());
                cModel.setType(cForm.getType());
                cModel.setModifyDate(new Date());
                LogUtil.debug("course",
                        "[AnswerQuestionAction*updCatalog]getCatalogID=" +
                                cModel.getCatalogID());
                LogUtil.debug("course",
                        "[AnswerQuestionHelper*updCatalog]cModel getCatalogName=" +
                                cModel.getCatalogName());
                LogUtil.debug("course",
                        "[AnswerQuestionHelper*updCatalog]cModel getType=" +
                                cModel.getType());
                LogUtil.debug("course",
                        "[AnswerQuestionHelper*updCatalog]cModel getDescription=" +
                                cModel.getDescription());

                try
                {
                        dao.updateCatalog(cModel);
                }
                catch (ULMSSysException e)
                {
                        LogUtil.debug("course",
                                "[AnswerQuestionHelper:insertQuestion] catch=" +
                                        "into the catch");
                        e.printStackTrace();
                        throw new AnswerQuestionSysException(e);
                }
        }

        public static void deleteCatalog(List catalogIDs)
                throws AnswerQuestionSysException
        {
                LogUtil.debug("course",
                        "[AnswerQuestionHelper:deleteQuestiont] Start deleteQuestiont");

                try
                {
                        dao.deleteCatalog(catalogIDs);
                }
                catch (ULMSSysException e)
                {
                        LogUtil.debug("course",
                                "[AnswerQuestionHelper:deleteQuestiont] catch=" +
                                        "into the catch");
                        e.printStackTrace();
                        throw new AnswerQuestionSysException(e);
                }
        }

        /**
         * 发短消息提示客户已回复问题
         *
         * @param aqForm
         * @throws AnswerQuestionSysException
         */
        private static void sendMessage(AnswerQuestionModel aqForm)
                throws AnswerQuestionSysException
        {
                //
                MessageDAOHelper mh = new MessageDAOHelper();

                try
                {
                        if ((aqForm.getType() != null) && !aqForm.getType().equals("1") &&
                                aqForm.getIsMessage().equals("1"))
                        {
                                //格式化发送的信息的内容
                                String content = getInformTemplate(I18Util.FormatDate(
                                        aqForm.getCreateDate(), Locale.CHINA),
                                        Integer.parseInt(aqForm.getType().trim()),
                                        aqForm.getSubmmiterName(), aqForm.getQuestionTitle(),
                                        aqForm.getRelationID(), aqForm.getRelationName());
                                //发送
                                mh.insertMessage(-1, aqForm.getSubmmiterName(),
                                        aqForm.getSubmmiterID(),
                                        "《" + aqForm.getQuestionTitle() + "》问题已答复提示", content, 0);
                                LogUtil.debug("course",
                                        "[AnswerQuestionHelper:sendMessage] sendMessage successful!" +
                                                aqForm.getType() + aqForm.getRemark6() +
                                                aqForm.getSubmmiterID() + aqForm.getSubmmiterName() +
                                                aqForm.getQuestionTitle());
                        }
                }
                catch (ULMSSysException ex)
                {
                        LogUtil.debug("course",
                                "[AnswerQuestionHelper:sendMessage] catch=" + ex +
                                        "into the catch");
                        ex.printStackTrace();
                        throw new AnswerQuestionSysException(ex);
                }
        }

        /**
         * 发邮件提示客户已回复问题
         *
         * @param qForm
         * @throws AnswerQuestionSysException
         */
        private static void sendMail(AnswerQuestionModel qForm)
                throws AnswerQuestionSysException
        {
                //
                try
                {
                        //发送邮件
                        ArrayList list = new ArrayList();
                        list.add(qForm.getRemark1());

                        String content = getInformTemplate(I18Util.FormatDate(
                                qForm.getCreateDate(), Locale.CHINA),
                                qForm.getAnswerLinkTitle(), "管理员",
                                qForm.getQuestionTitle(), qForm.getAnswerContent());
                        SmtpModel smtpModel = new SmtpModel();
                        smtpModel.setBody(content);
                        smtpModel.setSendTo(list);
                        smtpModel.setSubject("《" + qForm.getQuestionTitle() + "》问题答复内容");
                        smtpModel.setSendFrom(Config.getSmtpUser()); //验证时要于 SmtpUser一致
                        smtpModel.setAttachment(Config.getUploadPhysicalPath() +
                                qForm.getAnswerLink());
                        EmailServices.sendMail(smtpModel);
                }
                catch (ULMSSysException ex)
                {
                        LogUtil.debug("course",
                                "[AnswerQuestionHelper:sendMessage] catch=" + ex +
                                        "into the catch");
                        ex.printStackTrace();
                        throw new AnswerQuestionSysException(ex);
                }
        }

        /**
         * add:yangds
         * 系统首页列表查询-----公共问题或个人问题中的匿名登陆者的问题(submiterID=0)
         *
         * @param type
         * @param relationID
         * @param submmiterID
         * @param replyerID
         * @param status      null表示 此条件忽略
         * @param title       null表示 此条件忽略
         * @param content     null表示 此条件忽略
         * @param parentID    -1表示 此条件忽略
         * @param isReply     null表示 此条件忽略
         * @param isUserful   null表示 此条件忽略  1:表示常见问题，0：表示非常柬问题
         * @return
         * @throws AnswerQuestionSysException
         */
        public List getQuestions(int type, int relationID, int submmiterID,
                                 String status, String title, String content, int parentID,
                                 String isReply, String isUserful, int replyerID)
                throws AnswerQuestionSysException
        {
                LogUtil.debug("answerQuestionHelper", "getQuestionAllReplyerName");

                return dao.getMyQuestionOnShow(type, relationID, submmiterID, status,
                        title, content, parentID, isReply, isUserful, replyerID, null, null);
        }

        /**
         * 统计回复问题数
         *
         * @param startDate
         * @param stopDate
         * @return
         * @throws AnswerQuestionSysException
         */
        public static List countAnswer(String startDate, String stopDate)
                throws AnswerQuestionSysException
        {
                List list = null;

                try
                {
                        list = dao.countAnswer(startDate, stopDate);
                }
                catch (AnswerQuestionSysException e)
                {
                        LogUtil.info("AnswerQuestionHelper", "countAnswer exception:" + e);
                        e.printStackTrace();
                }

                return list;
        }

        public static Float sum(int count) throws AnswerQuestionSysException
        {
                Float f = null;

                try
                {
                        AnswerQuestionModel aqm = (AnswerQuestionModel) dao.getQuestionAll(3,
                                0).get(0);
                        f = new Float(aqm.getRemark1());

                        if (count > Integer.parseInt(aqm.getRemark2()))
                        {
                                f = new Float(((count - Integer.parseInt(aqm.getRemark2())) * Float.parseFloat(
                                        aqm.getRemark3())) + f.floatValue());
                        }
                }
                catch (ULMSSysException e)
                {
                        LogUtil.info("AnswerQuestionHelper", "countAnswer exception:" + e);
                        e.printStackTrace();
                }

                return f;
        }

        /**
         * 判断是否重名
         *
         * @param type
         * @param name
         * @return
         * @throws AnswerQuestionSysException
         */
        public static boolean isRename(int type, String name, int catalogID)
                throws AnswerQuestionSysException
        {
                List list = dao.getAllCatalog(type);
                Iterator ite = list.iterator();

                while (ite.hasNext())
                {
                        AQCatalogModel aModel = (AQCatalogModel) ite.next();

                        if (aModel.getCatalogName().equals(name) &&
                                (aModel.getCatalogID() != catalogID))
                        {
                                return true;
                        }
                }

                return false;
        }

        /**
         * gererate the inform-template doc.<br>
         *
         * @param submitTime
         * @param type
         * @param recieverName
         * @param title
         * @return
         * @throws AnswerQuestionSysException
         */
        private static String getInformTemplate(String submitTime, int type,
                                                String recieverName, String title, int relationID, String relationName)
                throws AnswerQuestionSysException
        {
                String content = "<p style=\"font-size:10pt;\">";
                content += ("您好，" + recieverName + "<br><br>");

                content += ("&nbsp;&nbsp;&nbsp;&nbsp;您于" + submitTime + "在");

                if (type == 0)
                {
                        content += "“常见问题”";
                }
                else if (type == 1)
                {
                        content += "“问题解答”";
                }
                else if (type == 2)
                {
                        content += "“课程答疑”";
                }

                content += ("中提交的《" + title + "》问题已得到答复，请去");

                if (type == 0)
                {
                        content += "<a href=\"../../tools/answerquestion/answer/manageQuestion.jsp?qtype=0\">常见问题</a>";
                }
                else if (type == 1)
                {
                        content += "<a href=\"../../tools/answerquestion/answer/manageQuestion.jsp?qtype=1\">问题解答</a>";
                }
                else if (type == 2)
                {
                        content += ("<a href=\"../../tools/answerquestion/answer/manageQuestion.jsp?qtype=2&relationID=" +
                                relationID + "&relationName=" + relationName + "\">课程答疑</a>");
                }

                content += "察看！</p>";

                return content;
        }

        /**
         * 格式化邮件内容
         *
         * @param submitTime 问题提交时间
         * @param type       是否有附件
         * @param sendName   发送人姓名
         * @param title      问题标题
         * @param body       问题内容
         * @return
         * @throws AnswerQuestionSysException
         */
        private static String getInformTemplate(String submitTime, String type,
                                                String sendName, String title, String body)
                throws AnswerQuestionSysException
        {
                String content = "<p style=\"font-size:14pt;\">";
                content += "您好,<br><br>";

                content += ("&nbsp;&nbsp;&nbsp;&nbsp;您于" + submitTime +
                        "在北京师范大学网络教育学院网站中");
                content += ("提交的《" + title +
                        "》问题我们已仔细查看，并立即回复，<br>希望对你有所帮助，内容如下：<br><br>");
                content += "<table width='80%'align='center' border='0'>";
                content += ("<tr><td style=\"font-size:14pt;\">" + body +
                        "</td></tr></table>");
                content += "<br>";

                if ((type != null) && !type.equals(""))
                {
                        content += "附件：见邮件附件！";
                }

                content += "<br><br><br>";
                content += "<P>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;致";
                content += "<P>&nbsp;&nbsp;礼！";
                content += "<br>";
                content += ("<p style=\"font-size:14pt;\" align='right'>" + sendName +
                        "</p>");
                content += ("<p style=\"font-size:14pt;\" align='right'>" +
                        I18Util.FormatDate(new Date(), Locale.CHINA) + "</p>");

                return content;
        }

        /**
         * 本班最新问题
         * @param courseId
         * @param pageNo
         * @param pageSize
         * @return
         */
        public static PagerList getAQByCourse(int courseId,int pageNo,int pageSize)
        {
                PagerList pl=new PagerList();
                return pl;
        }

        /**
         * 本班已经解决问题
         * @param courseId
         * @param pageNo
         * @param pageSize
         * @return
         */
        public static PagerList getSolvedAQByCourse(int courseId,int pageNo,int pageSize)
        {
                PagerList pl=new PagerList();
                return pl;
        }


        /**
         * 最新问题
         * @param pageNo
         * @param pageSize
         * @return
         */
        public static PagerList getAQ (int pageNo,int pageSize)
        {
                PagerList pl=new PagerList();
                return pl;
        }

        /**
         * 已经解决问题
         *
         * @param pageNo
         * @param pageSize
         * @return
         */
        public static PagerList getSolvedAQByCourse(int pageNo,int pageSize)
        {
                PagerList pl=new PagerList();
                return pl;
        }

        /**
         * 精彩问题
         * 
         * @param pageNo
         * @param pageSize
         * @return
         */
        public static PagerList getHighlightAQ (int pageNo,int pageSize)
        {
                PagerList pl=new PagerList();
                return pl;
        }
}
