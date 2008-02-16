/**
 * PaperQuestionHelper.java.
 * User: huangsb  Date: 2004-6-22
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd. * All rights reserved.
 */
package com.ulearning.ulms.course.test.paper.bean;

import com.ulearning.ulms.course.test.paper.dao.PaperQuestionDAO;
import com.ulearning.ulms.course.test.paper.dao.PaperQuestionDAOFactory;
import com.ulearning.ulms.course.test.paper.exceptions.PaperDAOSysException;
import com.ulearning.ulms.course.test.paper.form.PaperQuestionForm;

import java.util.List;


public class PaperQuestionHelper
{
        /**
         * Wrapping the get paperList method for JSP and  the other modules
         *
         * @param paperID
         * @return the plan list according to the paperID
         */
        public static List getPaperQuestionList(int paperID, int type)
        {
                List paperQuestionList = null;

                try
                {
                        PaperQuestionDAO paperQuestionDao = PaperQuestionDAOFactory.getDAO();
                        paperQuestionList = paperQuestionDao.getPaperQuestionList(paperID,
                                type);
                }
                catch (PaperDAOSysException udse)
                {
                        udse.printStackTrace();
                }

                return paperQuestionList;
        }

        public static void removePaperQuestion(int paperID)
        {
                try
                {
                        PaperQuestionDAO paperQuestionDao = PaperQuestionDAOFactory.getDAO();
                        paperQuestionDao.removePaperQuestion(paperID);
                }
                catch (PaperDAOSysException udse)
                {
                        udse.printStackTrace();
                }
        }

        /**
         * Wrapping the get paperList method for JSP and  the other modules
         *
         * @param paperID
         * @return the plan list according to the paperID
         */
        public static List getPaperQuestionList(int paperID)
        {
                List paperQuestionList = null;

                try
                {
                        PaperQuestionDAO paperQuestionDao = PaperQuestionDAOFactory.getDAO();
                        paperQuestionList = paperQuestionDao.getPaperQuestionList(paperID);
                }
                catch (PaperDAOSysException udse)
                {
                        udse.printStackTrace();
                }

                return paperQuestionList;
        }

        public static PaperQuestionForm getPaperQuestionForm(int paperID,
                                                             int questionID)
        {
                PaperQuestionForm paperQuestionForm = null;

                try
                {
                        PaperQuestionDAO paperQuestionDao = PaperQuestionDAOFactory.getDAO();
                        paperQuestionForm = paperQuestionDao.getPaperQuestion(paperID,
                                questionID);
                }
                catch (PaperDAOSysException udse)
                {
                        udse.printStackTrace();
                }

                return paperQuestionForm;
        }

        public static int getIsSubjectPaperQuestion(int paperID)
        {
                int count = 0;

                try
                {
                        PaperQuestionDAO paperQuestionDao = PaperQuestionDAOFactory.getDAO();
                        count = paperQuestionDao.getIsSubjectPaperQuestion(paperID);
                }
                catch (PaperDAOSysException udse)
                {
                        udse.printStackTrace();
                }

                return count;
        }
}
