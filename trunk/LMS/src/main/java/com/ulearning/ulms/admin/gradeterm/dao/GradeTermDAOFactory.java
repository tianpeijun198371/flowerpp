/**
 * Copyright (c) 2000-2005.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.admin.gradeterm.dao;

import com.ulearning.ulms.admin.gradeterm.exceptions.GradeTermDAOSysException;


/**
 * Class description goes here.
 * <p/>
 * Created by Auto Code Produce System
 * User: xiejh
 * Date: 20060321
 * Time: 182730
 */
public class GradeTermDAOFactory
{
        public static GradeTermDAO getDAO() throws GradeTermDAOSysException
        {
                GradeTermDAO dao = null;

                try
                {
                        dao = new GradeTermDAOImpl();
                }
                catch (Exception se)
                {
                        throw new GradeTermDAOSysException(
                                "GradeTermDAOFactory.getDAO:  Exception while getting DAO type : \n" +
                                        se.getMessage());
                }

                return dao;
        }
}
