/**
 * DiscussDAOFactory.java.
 * User: huangsb  Date: 2004-6-30
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd. * All rights reserved.
 */
package com.ulearning.ulms.tools.discussion.dao;

import com.ulearning.ulms.tools.discussion.exceptions.DiscussDAOSysException;
import com.ulearning.ulms.util.DBUtil;


public class DiscussDAOFactory
{
        /**
         * This method instantiates a particular subclass implementing
         * the DAO methods based on the information obtained from the
         * deployment descriptor
         */
        public static DiscussDAO getDAO() throws DiscussDAOSysException
        {
                DiscussDAO dao = null;

                try
                {
                        dao = new DiscussDAOImpl();
                }
                catch (Exception se)
                {
                        throw new DiscussDAOSysException(
                                "DiscussDAOFactory.getDAO:  Exception while getting DAO type : \n" +
                                        se.getMessage());
                }

                return dao;
        }
}
