/**
 * ConfigDAOFactory.java.
 * User: fengch  Date: 2004-5-13
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.core.config;

import com.ulearning.ulms.core.exceptions.ULMSSysException;
import com.ulearning.ulms.util.log.LogUtil;


public class ConfigDAOFactory
{
        /**
         * This method instantiates a particular subclass implementing
         * the DAO methods based on the information obtained from the
         * deployment descriptor
         */
        public static ConfigDAO getDAO()
        {
                ConfigDAO dao = null;

                try
                {
                        //now only a xml config implementation
                        dao = new ConfigXMLDAOImpl();
                        LogUtil.debug("core",
                                "[ConfigDAOFactory]---- now only a xml config implementation");
                }
                catch (Exception se)
                {
                        LogUtil.debug("core",
                                "[ConfigDAOFactory]====================== un known Exception=" +
                                        se.getMessage());

                        //throw new ULMSSysException(se);
                }

                return dao;
        }
}
