/**
 * OrganDAOFactory
 * User: dengj
 * Date: Apr 14, 2006
 * Time: 5:17:40 PM
 * Copyright (c) 2006-2007.Beijing WenhuaOnline Sci-tech DevelopmentCo.,Ltd.
 */
package com.ulearning.ulms.organ.dao;

import com.ulearning.ulms.organ.exceptions.OrganDAOSysException;
import com.ulearning.ulms.util.DBUtil;


public class OrganDAOFactory
{
        /**
         * This method instantiates a particular subclass implementing
         * the DAO methods based on the information obtained from the
         * deployment descriptor
         */
        public static OrganDAO getDAO() throws OrganDAOSysException
        {
                OrganDAO dao = null;

                try
                {
                        dao = new OrganDAOImpl();
                }
                catch (Exception se)
                {
                        throw new OrganDAOSysException(
                                "OrganDAOFactory.getDAO:  Exception while getting DAO type : \n" +
                                        se.getMessage());
                }

                return dao;
        }
}
