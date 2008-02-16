/**
 * PaperQuestionDAOFactory.java.
 * User: huangsb  Date: 2004-6-18
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd. * All rights reserved.
 */
package com.ulearning.ulms.course.test.paper.dao;

import com.ulearning.ulms.course.test.paper.exceptions.PaperDAOSysException;
import com.ulearning.ulms.util.DBUtil;


public class PaperQuestionDAOFactory
{
        /**
         * This method instantiates a particular subclass implementing
         * the DAO methods based on the information obtained from the
         * deployment descriptor
         */
        public static PaperQuestionDAO getDAO() throws PaperDAOSysException
        {
                PaperQuestionDAO dao = null;

                try
                {
                        dao = new PaperQuestionDAOImpl();
                }
                catch (Exception se)
                {
                        throw new PaperDAOSysException(
                                "PaperQuestionDAOFactory.getDAO:  Exception while getting DAO type : \n" +
                                        se.getMessage());
                }

                return dao;
        }
}
