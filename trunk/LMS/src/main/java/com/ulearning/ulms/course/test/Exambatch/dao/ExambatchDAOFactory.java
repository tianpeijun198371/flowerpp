/**
 * Copyright (c) 2000-2005.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.course.test.Exambatch.dao;

import com.ulearning.ulms.course.test.Exambatch.exceptions.ExambatchDAOSysException;


/**
 * Class description goes here.
 * <p/>
 * 考场连接工厂
 * User: zhuyr
 * Date: 20051121
 * Time: 135243
 */
public class ExambatchDAOFactory
{
        public static ExambatchDAO getDAO() throws ExambatchDAOSysException
        {
                ExambatchDAO dao = null;

                try
                {
                        dao = new ExambatchDAOImpl();
                }
                catch (Exception se)
                {
                        throw new ExambatchDAOSysException(
                                "ExambatchDAOFactory.getDAO:  Exception while getting DAO type : \n" +
                                        se.getMessage());
                }

                return dao;
        }
}
