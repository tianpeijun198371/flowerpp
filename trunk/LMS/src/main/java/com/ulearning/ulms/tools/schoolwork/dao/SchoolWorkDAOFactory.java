/**
 * SchoolWorkDAOFactory.java
 * SchoolWorkDAOFactory.java.
 * User: yud  Date: 2005-4-16
 *
 * Copyright (c) 2000-2005.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.tools.schoolwork.dao;


import com.ulearning.ulms.tools.schoolwork.exceptions.SchoolWorkSysException;
import com.ulearning.ulms.tools.schoolwork.dao.SchoolWorkDAO;
import com.ulearning.ulms.tools.schoolwork.dao.SchoolWorkDAOImpl;

public class SchoolWorkDAOFactory
{
        public static SchoolWorkDAO getDAO() throws SchoolWorkSysException
        {
                SchoolWorkDAO dao = null;
                try
                {
                        dao = new SchoolWorkDAOImpl();
                }
                catch (Exception se)
                {
                        throw new SchoolWorkSysException("SchoolWorkDAOFactory.getDAO:  Exception while getting DAO type : \n" + se.getMessage());
                }

                return dao;
        }
}