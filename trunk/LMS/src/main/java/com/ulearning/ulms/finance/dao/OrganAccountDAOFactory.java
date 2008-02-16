/**
 * OrganAccountDAOFactory.java.
 * User: liz  Date: 2005-12-12
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.finance.dao;

import com.ulearning.ulms.core.exceptions.ULMSSysException;


/**
 * 机构用户帐户DAO实现 工厂类
 *
 * @author Liz
 * @ date 2005.12.09
 */
public class OrganAccountDAOFactory
{
        private static OrganAccountDAO dao = null;

        public static OrganAccountDAO getDAO()
        {
                try
                {
                        if (null == dao)
                        {
                                dao = new OrganAccountDAOImpl();
                        }
                }
                catch (Exception e)
                {
                        throw new ULMSSysException("OrganAccountDAOFactory.getDAO:  " +
                                "Exception while getting DAO type : \n" + e.getMessage());
                }

                return dao;
        }
}
