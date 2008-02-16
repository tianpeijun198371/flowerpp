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
         * ���һ������
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
                        qForm.setSubmmiterName("δ��¼�û�");
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
         * �޸�һ������
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

                //����QuestionLink������1����˵������û�����롣��ʱ�Ͳ�Ҫ�޸���ԭ��������
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

                        //����QuestionLink������1����˵������û�����롣��ʱ�Ͳ�Ҫ�޸���ԭ��������
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

                //������Ϣ
                if ((qModel.getType() != null) && qModel.getType().trim().equals("1"))
                {
                        try
                        {
                                LogUtil.debug("course",
                                        "[AnswerQuestionHelper:updateQuestiont] Start updateDAO===�����ʼ���isMail=" +
                                                qModel.getIsMail());

                                //�����ʼ�
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
                                        "[AnswerQuestionHelper:updateQuestiont]===�����ʼ�����" + e, e);
                        }
                }
                else
                {
                        try
                        {
                                LogUtil.debug("course",
                                        "[AnswerQuestionHelper:updateQuestiont] Start updateDAO======���Ͷ���Ϣ��isMessage=" +
                                                qModel.getIsMessage());

                                //���Ͷ���Ϣ
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
                                        "[AnswerQuestionHelper:updateQuestiont]===���Ͷ���Ϣ����" + e, e);
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

                //����QuestionLink������1����˵������û�����롣��ʱ�Ͳ�Ҫ�޸���ԭ��������
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
         * ɾ��һ���������
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
                        key = key.replace('��', ',');
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
         * ������Ϣ��ʾ�ͻ��ѻظ�����
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
                                //��ʽ�����͵���Ϣ������
                                String content = getInformTemplate(I18Util.FormatDate(
                                        aqForm.getCreateDate(), Locale.CHINA),
                                        Integer.parseInt(aqForm.getType().trim()),
                                        aqForm.getSubmmiterName(), aqForm.getQuestionTitle(),
                                        aqForm.getRelationID(), aqForm.getRelationName());
                                //����
                                mh.insertMessage(-1, aqForm.getSubmmiterName(),
                                        aqForm.getSubmmiterID(),
                                        "��" + aqForm.getQuestionTitle() + "�������Ѵ���ʾ", content, 0);
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
         * ���ʼ���ʾ�ͻ��ѻظ�����
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
                        //�����ʼ�
                        ArrayList list = new ArrayList();
                        list.add(qForm.getRemark1());

                        String content = getInformTemplate(I18Util.FormatDate(
                                qForm.getCreateDate(), Locale.CHINA),
                                qForm.getAnswerLinkTitle(), "����Ա",
                                qForm.getQuestionTitle(), qForm.getAnswerContent());
                        SmtpModel smtpModel = new SmtpModel();
                        smtpModel.setBody(content);
                        smtpModel.setSendTo(list);
                        smtpModel.setSubject("��" + qForm.getQuestionTitle() + "�����������");
                        smtpModel.setSendFrom(Config.getSmtpUser()); //��֤ʱҪ�� SmtpUserһ��
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
         * ϵͳ��ҳ�б��ѯ-----�����������������е�������½�ߵ�����(submiterID=0)
         *
         * @param type
         * @param relationID
         * @param submmiterID
         * @param replyerID
         * @param status      null��ʾ ����������
         * @param title       null��ʾ ����������
         * @param content     null��ʾ ����������
         * @param parentID    -1��ʾ ����������
         * @param isReply     null��ʾ ����������
         * @param isUserful   null��ʾ ����������  1:��ʾ�������⣬0����ʾ�ǳ�������
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
         * ͳ�ƻظ�������
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
         * �ж��Ƿ�����
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
                content += ("���ã�" + recieverName + "<br><br>");

                content += ("&nbsp;&nbsp;&nbsp;&nbsp;����" + submitTime + "��");

                if (type == 0)
                {
                        content += "���������⡱";
                }
                else if (type == 1)
                {
                        content += "��������";
                }
                else if (type == 2)
                {
                        content += "���γ̴��ɡ�";
                }

                content += ("���ύ�ġ�" + title + "�������ѵõ��𸴣���ȥ");

                if (type == 0)
                {
                        content += "<a href=\"../../tools/answerquestion/answer/manageQuestion.jsp?qtype=0\">��������</a>";
                }
                else if (type == 1)
                {
                        content += "<a href=\"../../tools/answerquestion/answer/manageQuestion.jsp?qtype=1\">������</a>";
                }
                else if (type == 2)
                {
                        content += ("<a href=\"../../tools/answerquestion/answer/manageQuestion.jsp?qtype=2&relationID=" +
                                relationID + "&relationName=" + relationName + "\">�γ̴���</a>");
                }

                content += "�쿴��</p>";

                return content;
        }

        /**
         * ��ʽ���ʼ�����
         *
         * @param submitTime �����ύʱ��
         * @param type       �Ƿ��и���
         * @param sendName   ����������
         * @param title      �������
         * @param body       ��������
         * @return
         * @throws AnswerQuestionSysException
         */
        private static String getInformTemplate(String submitTime, String type,
                                                String sendName, String title, String body)
                throws AnswerQuestionSysException
        {
                String content = "<p style=\"font-size:14pt;\">";
                content += "����,<br><br>";

                content += ("&nbsp;&nbsp;&nbsp;&nbsp;����" + submitTime +
                        "�ڱ���ʦ����ѧ�������ѧԺ��վ��");
                content += ("�ύ�ġ�" + title +
                        "��������������ϸ�鿴���������ظ���<br>ϣ�����������������������£�<br><br>");
                content += "<table width='80%'align='center' border='0'>";
                content += ("<tr><td style=\"font-size:14pt;\">" + body +
                        "</td></tr></table>");
                content += "<br>";

                if ((type != null) && !type.equals(""))
                {
                        content += "���������ʼ�������";
                }

                content += "<br><br><br>";
                content += "<P>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��";
                content += "<P>&nbsp;&nbsp;��";
                content += "<br>";
                content += ("<p style=\"font-size:14pt;\" align='right'>" + sendName +
                        "</p>");
                content += ("<p style=\"font-size:14pt;\" align='right'>" +
                        I18Util.FormatDate(new Date(), Locale.CHINA) + "</p>");

                return content;
        }

        /**
         * ������������
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
         * �����Ѿ��������
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
         * ��������
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
         * �Ѿ��������
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
         * ��������
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
