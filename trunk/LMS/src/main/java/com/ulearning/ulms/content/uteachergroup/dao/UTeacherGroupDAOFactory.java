/**
 * Copyright (c) 2000-2005.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.content.uteachergroup.dao;

import com.ulearning.ulms.content.uteachergroup.exceptions.UTeacherGroupDAOSysException;


/**
 * Class description goes here.
 * <p/>
 * Created by Auto Code Produce System
 * User: xiejh
 * Date: 20060317
 * Time: 103906
 */
public class UTeacherGroupDAOFactory
{
        public static UTeacherGroupDAO getDAO() throws UTeacherGroupDAOSysException
        {
                UTeacherGroupDAO dao = null;

                try
                {
                        dao = new UTeacherGroupDAOImpl();
                }
                catch (Exception se)
                {
                        throw new UTeacherGroupDAOSysException(
                                "UTeacherGroupDAOFactory.getDAO:  Exception while getting DAO type : \n" +
                                        se.getMessage());
                }

                return dao;
        }
}
