/**
 * Copyright (c) 2000-2005.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.course.test.Answer.dao;

import com.ulearning.ulms.course.test.Answer.exceptions.AnswerDAOSysException;


/**
 * Class description goes here.
 * <p/>
 * Created by Auto Code Produce System
 * User: xiejh
 * Date: 20051121
 * Time: 135243
 */
public class AnswerDAOFactory
{
        public static AnswerDAO getDAO() throws AnswerDAOSysException
        {
                AnswerDAO dao = null;

                try
                {
                        dao = new AnswerDAOImpl();
                }
                catch (Exception se)
                {
                        throw new AnswerDAOSysException(
                                "AnswerDAOFactory.getDAO:  Exception while getting DAO type : \n" +
                                        se.getMessage());
                }

                return dao;
        }
}
