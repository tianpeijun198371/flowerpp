/**
 * PaperDAOFactory.java.
 * User: huangsb  Date: 2004-6-15
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd. * All rights reserved.
 */
package com.ulearning.ulms.course.test.paper.dao;

import com.ulearning.ulms.course.test.paper.exceptions.PaperDAOSysException;
import com.ulearning.ulms.util.DBUtil;


public class PaperDAOFactory
{
        /**
         * This method instantiates a particular subclass implementing
         * the DAO methods based on the information obtained from the
         * deployment descriptor
         */
        public static PaperDAO getDAO() throws PaperDAOSysException
        {
                PaperDAO dao = null;

                try
                {
                        dao = new PaperDAOImpl();
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
