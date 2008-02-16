/**
 * PaperAnswerDAOFactory.java.
 * User: huangsb  Date: 2004-6-22
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd. * All rights reserved.
 */
package com.ulearning.ulms.course.test.paper.dao;

import com.ulearning.ulms.course.test.paper.exceptions.PaperDAOSysException;
import com.ulearning.ulms.util.DBUtil;


public class PaperAnswerDAOFactory
{
        /**
         * This method instantiates a particular subclass implementing
         * the DAO methods based on the information obtained from the
         * deployment descriptor
         */
        public static PaperAnswerDAO getDAO() throws PaperDAOSysException
        {
                PaperAnswerDAO dao = null;

                try
                {
                        dao = new PaperAnswerDAOImpl();
                }
                catch (Exception se)
                {
                        throw new PaperDAOSysException(
                                "PaperDAOFactory.getDAO:  Exception while getting DAO type : \n" +
                                        se.getMessage());
                }

                return dao;
        }
}
