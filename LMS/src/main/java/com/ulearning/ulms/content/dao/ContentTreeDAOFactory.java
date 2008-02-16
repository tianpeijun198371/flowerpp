/**
 * ContentTreeDAOFactory.java.
 * User: shid Date: 2005-6-24 13:23:39
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.content.dao;

import com.ulearning.ulms.content.exceptions.ContentManageSysException;


public class ContentTreeDAOFactory
{
        public static ContentTreeDAO getDAO() throws ContentManageSysException
        {
                ContentTreeDAO dao = null;

                try
                {
                        dao = new ContentTreeDAOImpl();
                }
                catch (Exception se)
                {
                        throw new ContentManageSysException(
                                "ContentTreeDAOFactory.getDAO:  Exception while getting DAO type : \n" +
                                        se.getMessage());
                }

                return dao;
        }
}
