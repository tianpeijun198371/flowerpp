/**
 * PostDAOFactory.java.
 * User: shid Date: 2005-7-21 10:39:16
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.admin.post.dao;

import com.ulearning.ulms.admin.post.exceptions.PostDAOSysException;


public class PostDAOFactory
{
        public static PostDAO getDAO() throws PostDAOSysException
        {
                PostDAO dao = null;

                try
                {
                        dao = new PostDAOImpl();
                }
                catch (Exception se)
                {
                        throw new PostDAOSysException(
                                "PostDAOFactory.getDAO:  Exception while getting DAO type : \n" +
                                        se.getMessage());
                }

                return dao;
        }
}
