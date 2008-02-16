/** * CourseMyCourseDAOImpl.java.
 * User: fengch  Date: 2004-4-29
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.course.dao;

import com.ulearning.ulms.course.exceptions.CourseSysException;
import com.ulearning.ulms.util.DBUtil;
import com.ulearning.ulms.util.log.LogUtil;


public class CourseMyCourseDAOFactory
{
        /**
         * This method instantiates a particular subclass implementing
         * the DAO methods based on the information obtained from the
         * deployment descriptor
         */
        public static CourseMyCourseDAO getDAO() throws CourseSysException
        {
                CourseMyCourseDAO dao = null;

                try
                {
                        dao = new CourseMyCourseDAOImpl();
                }
                catch (Exception se)
                {
                        LogUtil.debug("CourseMyCourse",
                                "[CourseMyCourseDAOFactory]======================SQLException=" +
                                        se.getMessage());
                        throw new CourseSysException(se);
                }

                return dao;
        }
}
