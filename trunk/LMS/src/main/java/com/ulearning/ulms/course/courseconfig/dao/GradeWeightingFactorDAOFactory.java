/**
 * GradeWeightingFactorDAOFactory.java.
 * User: keyh  Date: 2004-9-2
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.course.courseconfig.dao;

import com.ulearning.ulms.course.courseconfig.exceptions.GradeWeightingFactorSysException;
import com.ulearning.ulms.util.log.LogUtil;


public class GradeWeightingFactorDAOFactory
{
        public static GradeWeightingFactorDAO getDAO()
        {
                GradeWeightingFactorDAO dao = null;

                try
                {
                        dao = new GradeWeightingFactorDAOImpl();
                }
                catch (Exception se)
                {
                        LogUtil.debug("common",
                                "[GradeWeightingFactorDAOFactory]======================SQLException=" +
                                        se.getMessage());
                }

                return dao;
        }
}
