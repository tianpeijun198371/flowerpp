/**
 * UserAccountDAOFactory.java.
 * User: liz  Date: 2005-12-12
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.finance.dao;

import com.ulearning.ulms.core.exceptions.ULMSSysException;


/**
 * 个人用户帐户DAO接口的工厂 类
 *
 * @author Liz
 * @ date 2005.12.09
 */
public class UserAccountDAOFactory
{
        private static UserAccountDAO dao = null;

        public static UserAccountDAO getDAO()
        {
                try
                {
                        if (null == dao)
                        {
                                dao = new UserAccountDAOImpl();
                        }
                }
                catch (Exception e)
                {
                        throw new ULMSSysException("UserAccountDAOFactory.getDAO:  " +
                                "Exception while getting DAO type : \n" + e.getMessage());
                }

                return dao;
        }
}
