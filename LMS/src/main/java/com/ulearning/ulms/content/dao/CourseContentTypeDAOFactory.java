/**
 * CourseContentTypeDAOFactory.java.
 * User: shid Date: 2005-9-7 11:08:45
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.content.dao;

import com.ulearning.ulms.content.exceptions.ContentManageSysException;


public class CourseContentTypeDAOFactory
{
        public static CourseContentTypeDAO getDAO()
                throws ContentManageSysException
        {
                CourseContentTypeDAO dao = null;

                try
                {
                        dao = new CourseContentTypeDAOImpl();
                }
                catch (Exception se)
                {
                        throw new ContentManageSysException(
                                "CourseContentTypeDAOFactory.getDAO:  Exception while getting DAO type : \n" +
                                        se.getMessage());
                }

                return dao;
        }
}
