/**
 * ForumDAOFactory.java.
 * User: huangsb  Date: 2004-6-30
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd. * All rights reserved.
 */
package com.ulearning.ulms.tools.discussion.dao;

import com.ulearning.ulms.tools.discussion.exceptions.ForumDAOSysException;
import com.ulearning.ulms.util.DBUtil;


public class ForumDAOFactory
{
        /**
         * This method instantiates a particular subclass implementing
         * the DAO methods based on the information obtained from the
         * deployment descriptor
         */
        public static ForumDAO getDAO() throws ForumDAOSysException
        {
                ForumDAO dao = null;

                try
                {
                        dao = new ForumDAOImpl();
                }
                catch (Exception se)
                {
                        throw new ForumDAOSysException(
                                "DiscussDAOFactory.getDAO:  Exception while getting DAO type : \n" +
                                        se.getMessage());
                }

                return dao;
        }
}
