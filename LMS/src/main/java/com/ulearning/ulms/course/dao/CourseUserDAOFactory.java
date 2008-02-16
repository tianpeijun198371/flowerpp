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


public class CourseUserDAOFactory
{
        /**
         * This method instantiates a particular subclass implementing
         * the DAO methods based on the information obtained from the
         * deployment descriptor
         */
        public static CourseUserDAO getDAO() throws CourseSysException
        {
                CourseUserDAO dao = null;
                int dateBaseType = DBUtil.getWhichDatabase();

                try
                {
                        if (dateBaseType == DBUtil.ORACLE)
                        {
                                dao = new CourseUserDAOOracle();
                        }
                        else
                        {
                                dao = new CourseUserDAOImpl();
                        }
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
