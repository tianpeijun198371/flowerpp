/**
 * PaperQuestionDAO.java.
 * User: huangsb  Date: 2004-6-18
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd. * All rights reserved.
 */
package com.ulearning.ulms.course.test.paper.dao;

import com.ulearning.ulms.course.test.paper.exceptions.PaperDAOSysException;
import com.ulearning.ulms.course.test.paper.form.PaperQuestionForm;

import java.util.List;


public interface PaperQuestionDAO
{
        public void addPaperQuestion(PaperQuestionForm paperQuestionForm)
                throws PaperDAOSysException;

        // public void updatePaperQuestion(PaperQuestionForm paperQuestionForm) throws PaperDAOSysException;
        public void removePaperQuestion(int paperID) throws PaperDAOSysException;

        public void removePaperQuestion(int paperID, String paperQuestionID)
                throws PaperDAOSysException;

        public void removePaperQuestion(int paperID, String[] paperQuestionID)
                throws PaperDAOSysException;

        public int getPaperQuestion(int paperID, String paperQuestionID)
                throws PaperDAOSysException;

        public List getPaperQuestionList(int paperID, int type)
                throws PaperDAOSysException;

        public List getPaperQuestionList(int paperID) throws PaperDAOSysException;

        public PaperQuestionForm getPaperQuestion(int paperID, int paperQuestionID)
                throws PaperDAOSysException;

        public int getIsSubjectPaperQuestion(int paperID)
                throws PaperDAOSysException;

        //public List getPaperQuestionList(int courseID,int type) throws PaperDAOSysException;
}
