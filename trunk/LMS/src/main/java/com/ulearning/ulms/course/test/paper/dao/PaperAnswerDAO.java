/**
 * PaperAnswerDAO.java.
 * User: huangsb  Date: 2004-6-21
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd. * All rights reserved.
 */
package com.ulearning.ulms.course.test.paper.dao;

import com.ulearning.ulms.course.test.paper.exceptions.PaperDAOSysException;
import com.ulearning.ulms.course.test.paper.form.PaperAnswerForm;

import java.util.List;


public interface PaperAnswerDAO
{
        public void addPaperAnswer(PaperAnswerForm paperAnswerForm)
                throws PaperDAOSysException;

        public void updatePaperAnswer(PaperAnswerForm paperAnswerForm)
                throws PaperDAOSysException;

        public void removePaperAnswer(int answerID) throws PaperDAOSysException;

        public PaperAnswerForm getPaperAnswer(int userID, int paperID,
                                              int questionID) throws PaperDAOSysException;

        public int getPaperAnswer(int userID, int paperID)
                throws PaperDAOSysException;

        public float getResearchAnswer(int questionID, char answer_select)
                throws PaperDAOSysException;

        public float getResearchMulAnswer(int questionID, char answer_select)
                throws PaperDAOSysException;

        public List getPaperAnswerList(int courseID, int type)
                throws PaperDAOSysException;

        public int getPaperAnswerCount(int courseID, int questionID)
                throws PaperDAOSysException;

        public int getTheAnswerCount(int questionID, char answer_select)
                throws PaperDAOSysException;

        public int getAllAnswerCount(int questionID, char answer_select)
                throws PaperDAOSysException;

        public int getPaperAnswerTimes(int userID, int paperID)
                throws PaperDAOSysException;

        /**
         * 综合查询
         *
         * @param userID     -1 忽略此条件
         * @param paperID    -1 忽略此条件
         * @param questionID -1 忽略此条件
         * @param type       -1 忽略此条件
         * @param grade      -2 忽略此条件
         * @param orderType  null 忽略此条件
         * @return
         * @throws PaperDAOSysException
         */
        public List getPaperUserAnswer(int userID, int paperID, int questionID,
                                       int type, float grade, String orderType) throws PaperDAOSysException;
}
