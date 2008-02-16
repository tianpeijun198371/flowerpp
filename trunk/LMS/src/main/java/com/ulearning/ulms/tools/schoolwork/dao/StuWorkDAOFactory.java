/**
 * StuWorkDAOFactory.java.
 * User: yud  Date: 2005-4-13
 *
 * Copyright (c) 2000-2005.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.tools.schoolwork.dao;

import com.ulearning.ulms.tools.schoolwork.exceptions.StuWorkSysException;

public class StuWorkDAOFactory
{
        public static StuWorkDAO getDAO() throws StuWorkSysException
        {
                StuWorkDAO dao = null;
                try
                {
                        dao = new StuWorkDAOImpl();
                }
                catch (Exception se)
                {
                        throw new StuWorkSysException("StuWorkDAOFactory.getDAO:  Exception while getting DAO type : \n" + se.getMessage());
                }

                return dao;
        }
}