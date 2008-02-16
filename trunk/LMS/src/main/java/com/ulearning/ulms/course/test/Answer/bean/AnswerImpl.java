/**
 * Copyright (c) 2000-2005.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.course.test.Answer.bean;

import com.ulearning.ulms.course.test.Answer.dao.AnswerDAO;
import com.ulearning.ulms.course.test.Answer.dao.AnswerDAOFactory;
import com.ulearning.ulms.course.test.Answer.exceptions.AnswerDAOSysException;
import com.ulearning.ulms.course.test.Answer.form.AnswerForm;

import java.util.List;


/**
 * Class description goes here.
 * <p/>
 * Created by Auto Code Produce System
 * User: xiejh
 * Date: 20051121
 * Time: 135243
 */
public class AnswerImpl
{
        public List getAnswerList() throws AnswerDAOSysException
        {
                List AnswerList = null;

                try
                {
                        AnswerDAO dao = AnswerDAOFactory.getDAO();
                        AnswerList = dao.getAnswerList();
                }
                catch (AnswerDAOSysException tdse)
                {
                        tdse.printStackTrace();
                }

                return AnswerList;
        }

        public AnswerForm getAnswer(int answerID) throws AnswerDAOSysException
        {
                AnswerForm tf = null;

                try
                {
                        AnswerDAO dao = AnswerDAOFactory.getDAO();
                        tf = dao.getAnswer(answerID);
                }
                catch (AnswerDAOSysException tdse)
                {
                        tdse.printStackTrace();
                }

                return tf;
        }

        public void deleteAnswer(int answerID) throws AnswerDAOSysException
        {
                try
                {
                        AnswerDAO dao = AnswerDAOFactory.getDAO();
                        dao.deleteAnswer(answerID);
                }
                catch (AnswerDAOSysException tdse)
                {
                        tdse.printStackTrace();
                }
        }
}
