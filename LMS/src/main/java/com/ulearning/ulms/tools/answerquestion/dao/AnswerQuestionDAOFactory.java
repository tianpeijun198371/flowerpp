/**
 * NewDcumentDAOFactory.java.
 * User: Administrator  Date: 2005-3-9
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.tools.answerquestion.dao;

import com.ulearning.ulms.tools.answerquestion.dao.AnswerQuestionDAO;
import com.ulearning.ulms.tools.answerquestion.exceptions.AnswerQuestionSysException;


public class AnswerQuestionDAOFactory
{
        public static AnswerQuestionDAO getDAO() throws AnswerQuestionSysException
        {
                AnswerQuestionDAO dao = null;

                try
                {
                        dao = new AnswerQuestionDAOImpl();
                }
                catch (Exception se)
                {
                        throw new AnswerQuestionSysException(
                                "NewDocumentDAOFactory.getDAO:  Exception while getting DAO type : \n" +
                                        se.getMessage());
                }

                return dao;
        }
}
