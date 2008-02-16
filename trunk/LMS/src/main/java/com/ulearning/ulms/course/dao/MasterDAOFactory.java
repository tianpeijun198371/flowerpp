/**
 * MasterDAOFactory.java.
 * User: fengch  Date: 2004-4-27
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.course.dao;

import com.ulearning.ulms.course.exceptions.CourseSysException;
import com.ulearning.ulms.util.DBUtil;
import com.ulearning.ulms.util.log.LogUtil;

import java.sql.Connection;


public class MasterDAOFactory
{
        /**
         * This method instantiates a particular subclass implementing
         * the DAO methods based on the information obtained from the
         * deployment descriptor
         */
        public static MasterDAO getDAO() throws CourseSysException
        {
                MasterDAO dao = null;

                try
                {
                        dao = new MasterDAOImpl();
                }
                catch (Exception se)
                {
                        LogUtil.debug("course",
                                "[MasterDAOFactory]======================SQLException=" +
                                        se.getMessage());
                        throw new CourseSysException(se);
                }

                return dao;
        }
}
