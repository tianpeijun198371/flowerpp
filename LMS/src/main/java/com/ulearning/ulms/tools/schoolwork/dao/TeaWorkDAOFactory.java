/**
 * TeaWorkDAOFactory.java.
 * User: yud  Date: 2005-4-13
 *
 * Copyright (c) 2000-2005.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.tools.schoolwork.dao;


import com.ulearning.ulms.tools.schoolwork.exceptions.SchoolWorkSysException;

public class TeaWorkDAOFactory
{
        public static TeaWorkDAO getDAO() throws SchoolWorkSysException
        {
                TeaWorkDAO dao = null;
                try
                {
                        dao = new TeaWorkDAOImpl();
                }
                catch (Exception se)
                {
                        throw new SchoolWorkSysException("TeaWorkDAOFactory.getDAO:  Exception while getting DAO type : \n" + se.getMessage());
                }

                return dao;
        }
}