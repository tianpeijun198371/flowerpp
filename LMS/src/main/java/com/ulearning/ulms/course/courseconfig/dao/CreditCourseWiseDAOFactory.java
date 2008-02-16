/**
 * CreditCourseWiseDAOFactory.java.
 * User: keyh  Date: 2004-9-2
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.course.courseconfig.dao;

import com.ulearning.ulms.course.courseconfig.exceptions.CreditCourseWiseSysException;
import com.ulearning.ulms.util.log.LogUtil;


public class CreditCourseWiseDAOFactory
{
        public static CreditCourseWiseDAO getDAO()
                throws CreditCourseWiseSysException
        {
                CreditCourseWiseDAO dao = null;

                try
                {
                        dao = new CreditCourseWiseDAOImpl();
                }
                catch (Exception se)
                {
                        LogUtil.debug("common",
                                "[CreditCourseWiseDAOFactory]======================SQLException=" +
                                        se.getMessage());
                }

                return dao;
        }
}
