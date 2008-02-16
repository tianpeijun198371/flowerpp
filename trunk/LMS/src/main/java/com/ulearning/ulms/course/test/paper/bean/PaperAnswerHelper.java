/**
 * PaperAnswerHelper.java.
 * User: huangsb  Date: 2004-6-22
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd. * All rights reserved.
 */
package com.ulearning.ulms.course.test.paper.bean;

import com.ulearning.ulms.core.util.Config;
import com.ulearning.ulms.course.test.grade.bean.PaperUserHelper;
import com.ulearning.ulms.course.test.grade.dao.PaperUserDAO;
import com.ulearning.ulms.course.test.grade.dao.PaperUserDAOFactory;
import com.ulearning.ulms.course.test.grade.exceptions.GradeDAOSysException;
import com.ulearning.ulms.course.test.grade.form.PaperUserForm;
import com.ulearning.ulms.course.test.paper.dao.PaperAnswerDAO;
import com.ulearning.ulms.course.test.paper.dao.PaperAnswerDAOFactory;
import com.ulearning.ulms.course.test.paper.dao.PaperQuestionDAO;
import com.ulearning.ulms.course.test.paper.dao.PaperQuestionDAOFactory;
import com.ulearning.ulms.course.test.paper.exceptions.PaperDAOSysException;
import com.ulearning.ulms.course.test.paper.form.PaperAnswerForm;
import com.ulearning.ulms.course.test.paper.form.PaperQuestionForm;
import com.ulearning.ulms.course.test.question.base.bean.BaseHelper;
import com.ulearning.ulms.course.test.question.base.dao.BaseDAO;
import com.ulearning.ulms.course.test.question.base.dao.BaseDAOFactory;
import com.ulearning.ulms.course.test.question.base.exceptions.BaseDAOSysException;
import com.ulearning.ulms.course.test.question.base.form.BaseForm;
import com.ulearning.ulms.course.test.testbase.PaperXML;

import java.io.File;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;


public class PaperAnswerHelper
{
        /**
         * Wrapping the get paper method for JSP and  the other modules
         *
         * @param paperID
         * @return the admin modle according to the paperID
         */
        public PaperAnswerForm getPaper(int userID, int paperID, int questionID)
        {
                PaperAnswerForm pf = null;

                try
                {
                        PaperAnswerDAO paperDao = PaperAnswerDAOFactory.getDAO();
                        pf = paperDao.getPaperAnswer(userID, paperID, questionID);
                }
                catch (PaperDAOSysException udse)
                {
                        udse.printStackTrace();
                }

                return pf;
        }

        public int getPaperAnswer(int userID, int paperID)
        {
                int conunt = 0;

                try
                {
                        PaperAnswerDAO paperDao = PaperAnswerDAOFactory.getDAO();
                        conunt = paperDao.getPaperAnswer(userID, paperID);
                }
                catch (PaperDAOSysException udse)
                {
                        udse.printStackTrace();
                }

                return conunt;
        }

        public int getPaperAnswerTimes(int userID, int paperID)
        {
                int conunt = 0;

                try
                {
                        PaperAnswerDAO paperDao = PaperAnswerDAOFactory.getDAO();
                        conunt = paperDao.getPaperAnswerTimes(userID, paperID);
                }
                catch (PaperDAOSysException udse)
                {
                        udse.printStackTrace();
                }

                return conunt;
        }

        /**
         * Wrapping the get paper method for JSP and  the other modules
         *
         * @param questionID
         * @param answer_select
         * @return the admin modle according to the questionID
         */
        public float getResearchAnswer(int questionID, char answer_select)
        {
                float ff = 0;

                try
                {
                        PaperAnswerDAO paperDao = PaperAnswerDAOFactory.getDAO();
                        ff = paperDao.getResearchAnswer(questionID, answer_select);
                }
                catch (PaperDAOSysException udse)
                {
                        udse.printStackTrace();
                }

                return ff;
        }

        /**
         * Wrapping the get paper method for JSP and  the other modules
         *
         * @param questionID
         * @param answer_select
         * @return the admin modle according to the questionID
         */
        public float getResearchMulAnswer(int questionID, char answer_select)
        {
                float ff = 0;

                try
                {
                        PaperAnswerDAO paperDao = PaperAnswerDAOFactory.getDAO();
                        ff = paperDao.getResearchMulAnswer(questionID, answer_select);
                }
                catch (PaperDAOSysException udse)
                {
                        udse.printStackTrace();
                }

                return ff;
        }

        /**
         * Public a method for the number of paperanswers
         *
         * @return the wanted number
         * @ name = getPaperAnswerCount
         */
        public int getPaperAnswerCount(int courseID, int questionID)
        {
                int result = 0;

                try
                {
                        PaperAnswerDAO paperDao = PaperAnswerDAOFactory.getDAO();
                        result = paperDao.getPaperAnswerCount(courseID, questionID);
                }
                catch (PaperDAOSysException udse)
                {
                        udse.printStackTrace();
                }

                return result;
        }

        /**
         * Public a method for all the number of paperanswers
         *
         * @return the wanted number
         * @ name = getAllAnswerCount
         */
        public int getAllAnswerCount(int questionID, char answer_select)
        {
                int count1 = 0;

                try
                {
                        PaperAnswerDAO paperDao = PaperAnswerDAOFactory.getDAO();
                        count1 = paperDao.getAllAnswerCount(questionID, answer_select);
                }
                catch (PaperDAOSysException udse)
                {
                        udse.printStackTrace();
                }

                return count1;
        }

        /**
         * Public a method for the number of paperanswers
         *
         * @return the wanted number
         * @ name = getTheAnswerCount
         */
        public int getTheAnswerCount(int questionID, char answer_select)
        {
                int answer = 0;

                try
                {
                        PaperAnswerDAO paperDao = PaperAnswerDAOFactory.getDAO();
                        answer = paperDao.getTheAnswerCount(questionID, answer_select);
                }
                catch (PaperDAOSysException udse)
                {
                        udse.printStackTrace();
                }

                return answer;
        }

        /**
         * Public a method for the number of paperanswers
         *
         * @param list
         * @param isHasAnswer
         */
        public static void addBacthPaperAnswer(List list, boolean isHasAnswer)
        {
                try
                {
                        List al = null;
                        HashMap hm = null;
                        PaperAnswerDAO paperDao = PaperAnswerDAOFactory.getDAO();
                        PaperUserDAO pud = PaperUserDAOFactory.getDAO();
                        BaseDAO bdao = BaseDAOFactory.getDAO();
                        PaperAnswerForm paf = null;
                        BaseForm bf = null;

                        for (int i = 0; i < list.size(); i++)
                        {
                                //alΪ�Ծ��ÿ���������
                                al = (List) list.get(i);
                                hm = (HashMap) al.get(0);

                                Collection coll = hm.values();
                                Iterator ite = coll.iterator();
                                float score = 0;

                                while (ite.hasNext())
                                {
                                        paf = (PaperAnswerForm) ite.next();

                                        //isHasAnswer Ϊtrue�ǽ������Ĳ���
                                        if (isHasAnswer)
                                        {
                                                bf = bdao.getBase(paf.getQuestionID());

                                                if ((paf != null) && (bf != null) &&
                                                        (paf.getAnswer() != null) &&
                                                        (bf.getCorrectAnswer() != null) &&
                                                        paf.getAnswer().toUpperCase()
                                                                .equals(bf.getCorrectAnswer()
                                                                        .toUpperCase()))
                                                {
                                                        paf.setGrade(bf.getScore());
                                                        score += bf.getScore();
                                                }
                                                else
                                                {
                                                        paf.setGrade(0);
                                                }
                                        }

                                        //System.out.print(paf.getExamTimes());
                                        paperDao.addPaperAnswer(paf);
                                }

                                PaperUserForm puf = (PaperUserForm) al.get(1);
                                puf.setGrade(score);
                                puf.setObjective(score);
                                puf.setStatus(2);
                                pud.addPaperUser(puf);
                        }
                }
                catch (PaperDAOSysException udse)
                {
                        udse.printStackTrace();
                }
                catch (GradeDAOSysException e)
                {
                        e.printStackTrace();
                }
                catch (BaseDAOSysException e)
                {
                        e.printStackTrace();
                }
        }

        /**
         * Public a method for the number of paperanswers
         *
         * @param paperID
         * @param isHasAnswer
         */
        public static void bacthPaperAnswerXML(int paperID, boolean isHasAnswer)
        {
                try
                {
                        String inFile = Config.getUploadPhysicalPath() + "PaperXML" +
                                File.separator + paperID;

                        //                        System.out.println("filepath="+inFile);
                        //���²����ѵ�ǰ�������ݷ���HashMap��
                        HashMap hm = new HashMap();

                        if (isHasAnswer)
                        {
                                BaseDAO bdao = BaseDAOFactory.getDAO();
                                List questionList = PaperQuestionHelper.getPaperQuestionList(paperID);
                                List baseList = bdao.getBaseList(questionList);

                                for (int i = 0; (baseList != null) && (i < baseList.size());
                                     i++)
                                {
                                        BaseForm bf = (BaseForm) baseList.get(i);
                                        hm.put(bf.getQuestionID() + "", bf);
                                }
                        }

                        PaperXML.batchReadXML(inFile, isHasAnswer, hm);
                }
                catch (PaperDAOSysException udse)
                {
                        udse.printStackTrace();
                }
                catch (GradeDAOSysException e)
                {
                        e.printStackTrace();
                }
                catch (BaseDAOSysException e)
                {
                        e.printStackTrace();
                }
        }

        /**
         * ���������Ծ� Ҫ���Ծ�����λ�͹��⣨��ѡ����ѡ���жϣ�
         *
         * @param paperID
         */
        public static void correct(int paperID)
                throws PaperDAOSysException, GradeDAOSysException
        {
                PaperAnswerForm paf = null;
                BaseForm bf = null;
                BaseHelper bh = new BaseHelper();
                PaperAnswerDAO paperDao = PaperAnswerDAOFactory.getDAO();
                List list = getPaperUserAnswer(paperID);
                PaperUserForm puf = null;
                PaperUserDAO pudao = PaperUserDAOFactory.getDAO();

                if (list != null)
                {
                        for (int i = 0; i < list.size(); i++)
                        {
                                paf = (PaperAnswerForm) list.get(i);
                                //if�����Ϊ�˼���ȡquestionIDʱ�������ݿ�Ĵ���
                                bf = bh.getBase(paf.getQuestionID());

                                if (bf.getCorrectAnswer().toUpperCase()
                                        .equals(paf.getAnswer().toUpperCase()))
                                {
                                        //                                        System.out.println("test===if====setGrade:qustionID="+pafgetQuestionID());
                                        paf.setGrade(bf.getScore());
                                }
                                else
                                {
                                        //                                        System.out.println("test===else====setGrade:qustionID="+paf.getQuestionID());
                                        paf.setGrade(0);
                                }

                                //if�����Ϊ�˼���ȡPaperUserFormʱ�������ݿ�Ĵ���
                                if ((puf == null) ||
                                        ((puf != null) && (puf.getUserID() != paf.getUserID())))
                                {
                                        //                                        System.out.println("test====================getPaperUser:userID="+(puf == null ? "null" : puf.getUserID()+""));
                                        puf = PaperUserHelper.getPaperUser(paf.getPaperID(),
                                                paf.getUserID());

                                        //status ֵΪ2��ʾ���޸�
                                        if (puf != null)
                                        {
                                                puf.setStatus(2);
                                        }

                                        pudao.modifyPaperUser(puf);
                                }

                                paperDao.updatePaperAnswer(paf);
                        }
                }
        }

        /**
         * ��������mxl�ļ�
         *
         * @param list
         * @param isHasAnswer
         */
        public static void correctXML(List list, boolean isHasAnswer)
        {
                try
                {
                        List al = null;
                        List all = null;
                        PaperAnswerDAO paperDao = PaperAnswerDAOFactory.getDAO();
                        PaperUserDAO pud = PaperUserDAOFactory.getDAO();
                        BaseDAO bdao = BaseDAOFactory.getDAO();
                        PaperAnswerForm paf = null;
                        BaseForm bf = null;

                        for (int i = 0; i < list.size(); i++)
                        {
                                //alΪ�Ծ��ÿ���������
                                al = (List) list.get(i);
                                all = (List) al.get(0);

                                float score = 0;

                                for (int j = 0; (all != null) && (j < all.size()); j++)
                                {
                                        paf = (PaperAnswerForm) all.get(j);

                                        //isHasAnswer Ϊtrue�ǽ������Ĳ���
                                        if (isHasAnswer)
                                        {
                                                bf = bdao.getBase(paf.getQuestionID());

                                                if ((paf != null) && (bf != null) &&
                                                        (paf.getAnswer() != null) &&
                                                        (bf.getCorrectAnswer() != null) &&
                                                        paf.getAnswer().toUpperCase()
                                                                .equals(bf.getCorrectAnswer()
                                                                        .toUpperCase()))
                                                {
                                                        paf.setGrade(bf.getScore());
                                                        score += bf.getScore();
                                                }
                                                else
                                                {
                                                        paf.setGrade(0);
                                                }
                                        }

                                        System.out.println("insert id=" + paf.getQuestionID());

                                        //                                        paperDao.addPaperAnswer(paf);
                                }

                                PaperUserForm puf = (PaperUserForm) al.get(1);
                                puf.setGrade(score);
                                puf.setObjective(score);

                                if (isHasAnswer)
                                {
                                        puf.setStatus(2);
                                }
                                else
                                {
                                        puf.setStatus(0);
                                }

                                System.out.println("insert userID=" + puf.getUserID());

                                //                                pud.addPaperUser(puf);
                        }
                }
                catch (PaperDAOSysException udse)
                {
                        udse.printStackTrace();
                }
                catch (GradeDAOSysException e)
                {
                        e.printStackTrace();
                }
                catch (BaseDAOSysException e)
                {
                        e.printStackTrace();
                }
        }

        /**
         * �г�paperID�Ծ��µ����п����Ĵ��
         *
         * @param paperID
         */
        public static List getPaperUserAnswer(int paperID)
        {
                List list = null;

                try
                {
                        PaperAnswerDAO paperDao = PaperAnswerDAOFactory.getDAO();
                        list = paperDao.getPaperUserAnswer(-1, paperID, -1, -1, -1,
                                "order by userID");
                }
                catch (PaperDAOSysException udse)
                {
                        udse.printStackTrace();
                }

                return list;
        }
}
