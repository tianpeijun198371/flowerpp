/**
 * PhysicsResDAOFactory.java.
 * User: liz  Date: 2006-2-17
 *  资源字典工厂
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.admin.certificate.dao;

import com.ulearning.ulms.core.exceptions.ULMSSysException;


public class CertPlanDAOFactory
{
        private static CertPlanDAO dao = null;

        public static CertPlanDAO getDAO()
        {
                try
                {
                        if (null == dao)
                        {
                                dao = new CertPlanDAOImpl();
                        }
                }
                catch (Exception e)
                {
                        throw new ULMSSysException("PhysicsResDAOFactory.getDAO:  " +
                                "Exception while getting DAO type : \n" + e.getMessage());
                }

                return dao;
        }
}
