/**
 * RResuserecordDAOFactory.java.
 * User: liz  Date: 2006-2-20
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.content.dao;

import com.ulearning.ulms.core.exceptions.ULMSSysException;


public class RResuserecordDAOFactory
{
        private static RResuserecordDAO dao = null;

        public static RResuserecordDAO getDAO()
        {
                try
                {
                        if (null == dao)
                        {
                                dao = new RResuserecordDAOImpl();
                        }
                }
                catch (Exception e)
                {
                        throw new ULMSSysException("RResuserecordDAOFactory.getDAO:  " +
                                "Exception while getting DAO type : \n" + e.getMessage());
                }

                return dao;
        }
}
