/**
 * PayoutTypeDAOFactory.java.
 * User: liz  Date: 2005-12-9
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.finance.dao;

import com.ulearning.ulms.core.exceptions.ULMSSysException;


/**
 * ����֧������DAO Interface ʵ�ֵĹ�����
 *
 * @author Liz
 * @ date 2005.12.09
 */
public class PayoutTypeDAOFactory
{
        private static PayoutTypeDAO dao = null;

        public static PayoutTypeDAO getDAO()
        {
                try
                {
                        if (null == dao)
                        {
                                dao = new PayoutTypeDAOImpl();
                        }
                }
                catch (Exception e)
                {
                        throw new ULMSSysException("PayoutTypeDAOFactory.getDAO:  " +
                                "Exception while getting DAO type : \n" + e.getMessage());
                }

                return dao;
        }
}
