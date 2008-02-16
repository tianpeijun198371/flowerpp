/**
 * SecurityDAOFactory.java.
 * User: dengj  Date: 2004-7-1
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.core.security.dao;

import com.ulearning.ulms.core.security.exceptions.SecurityDAOSysException;
import com.ulearning.ulms.util.DBUtil;
import com.ulearning.ulms.util.log.LogUtil;


public class SecurityDAOFactory
{
        /**
         * This method instantiates a particular subclass implementing
         * the DAO methods based on the information obtained from the
         * deployment descriptor
         */
        public static SecurityDAO getDAO() throws SecurityDAOSysException
        {
                SecurityDAO dao = null;

                try
                {
                        dao = new SecurityDAOImpl();
                }
                catch (Exception se)
                {
                        throw new SecurityDAOSysException(se);
                }

                return dao;
        }
}
