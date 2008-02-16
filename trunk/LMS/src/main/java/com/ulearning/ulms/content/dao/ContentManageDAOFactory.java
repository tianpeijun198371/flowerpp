/**
 * ContentManageDAOFactory.java.
 * User: shid Date: 2005-5-31 11:57:42
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.content.dao;

import com.ulearning.ulms.content.exceptions.ContentManageSysException;


public class ContentManageDAOFactory
{
        public static ContentManageDAO getDAO() throws ContentManageSysException
        {
                ContentManageDAO dao = null;

                try
                {
                        dao = new ContentManageDAOImpl();
                }
                catch (Exception se)
                {
                        throw new ContentManageSysException(
                                "CourseContentDAOFactory.getDAO:  Exception while getting DAO type : \n" +
                                        se.getMessage());
                }

                return dao;
        }
}
