/**
 * IncomeTypeDAOFactory.java.
 * User: liz  Date: 2005-12-9
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.finance.dao;

import com.ulearning.ulms.core.exceptions.ULMSSysException;
import com.ulearning.ulms.organ.exceptions.OrganDAOSysException;


/**
 * 经费收入类型DAO Interface实现的工厂
 *
 * @author Liz
 * @ date 2005.12.09
 */
public class IncomeTypeDAOFactory
{
        private static IncomeTypeDAO dao = null;

        public static IncomeTypeDAO getDAO()
        {
                try
                {
                        if (null == dao)
                        {
                                dao = new IncomeTypeDAOImpl();
                        }
                }
                catch (Exception e)
                {
                        throw new ULMSSysException("IncomeTypeDAOFactory.getDAO:  " +
                                "Exception while getting DAO type : \n" + e.getMessage());
                }

                return dao;
        }
}
