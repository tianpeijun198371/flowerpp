/**
 * EvaluateManageDAOFactory.java.
 * User: yud Date: 2005-5-31 11:57:42
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.evaluate.dao;

import com.ulearning.ulms.evaluate.exceptions.EvaluateManageSysException;


public class FeedBackDAOFactory
{
        public static FeedBackDAO getDAO() throws EvaluateManageSysException
        {
                FeedBackDAO dao = null;

                try
                {
                        dao = new FeedBackDAOImpl();
                }
                catch (Exception se)
                {
                        throw new EvaluateManageSysException(
                                "CourseContentDAOFactory.getDAO:  Exception while getting DAO type : \n" +
                                        se.getMessage());
                }

                return dao;
        }
}
