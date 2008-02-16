/**
 * Copyright (c) 2000-2005.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.admin.gradeuser.dao;

import com.ulearning.ulms.admin.gradeuser.exceptions.GradeUserDAOSysException;


/**
 * Class description goes here.
 * <p/>
 * Created by Auto Code Produce System
 * User: xiejh
 * Date: 20060321
 * Time: 182730
 */
public class GradeUserDAOFactory
{
        public static GradeUserDAO getDAO() throws GradeUserDAOSysException
        {
                GradeUserDAO dao = null;

                try
                {
                        dao = new GradeUserDAOImpl();
                }
                catch (Exception se)
                {
                        throw new GradeUserDAOSysException(
                                "GradeUserDAOFactory.getDAO:  Exception while getting DAO type : \n" +
                                        se.getMessage());
                }

                return dao;
        }
}
