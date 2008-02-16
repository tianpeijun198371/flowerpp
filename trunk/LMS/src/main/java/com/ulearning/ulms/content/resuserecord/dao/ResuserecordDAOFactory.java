/**
 * Copyright (c) 2000-2005.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.content.resuserecord.dao;

import com.ulearning.ulms.content.resuserecord.exceptions.ResuserecordDAOSysException;


/**
 * Class description goes here.
 * <p/>
 * Created by Auto Code Produce System
 * User: xiejh
 * Date: 20060317
 * Time: 103906
 */
public class ResuserecordDAOFactory
{
        public static ResuserecordDAO getDAO() throws ResuserecordDAOSysException
        {
                ResuserecordDAO dao = null;

                try
                {
                        dao = new ResuserecordDAOImpl();
                }
                catch (Exception se)
                {
                        throw new ResuserecordDAOSysException(
                                "ResuserecordDAOFactory.getDAO:  Exception while getting DAO type : \n" +
                                        se.getMessage());
                }

                return dao;
        }
}
