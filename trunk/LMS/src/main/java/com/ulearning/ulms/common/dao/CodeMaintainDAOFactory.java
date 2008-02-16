/**
 * CodeMaintainDAOFactory.java.
 * User: fengch  Date: 2004-5-26
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.common.dao;

import com.ulearning.ulms.core.exceptions.ULMSSysException;
import com.ulearning.ulms.util.DBUtil;
import com.ulearning.ulms.util.log.LogUtil;

import java.sql.Connection;


public class CodeMaintainDAOFactory
{
        /**
         * This method instantiates a particular subclass implementing
         * the DAO methods based on the information obtained from the
         * deployment descriptor
         */
        public static CodeMaintainDAO getDAO() throws ULMSSysException
        {
                CodeMaintainDAO dao = null;

                try
                {
                        dao = new CodeMaintainDAOImpl();
                }
                catch (Exception se)
                {
                        LogUtil.debug("common",
                                "[CodeMaintainDAOFactory]======================SQLException=" +
                                        se.getMessage());
                        throw new ULMSSysException(se);
                }

                return dao;
        }
}
