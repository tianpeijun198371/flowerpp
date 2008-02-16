/**
 * CourseContentDAOFactory.java.
 * User: huangsb  Date: 2004-5-5
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd. * All rights reserved.
 */
package com.ulearning.ulms.course.dao;

import com.ulearning.ulms.course.exceptions.CourseDAOSysException;
import com.ulearning.ulms.util.DBUtil;


public class CourseContentDAOFactory
{
        /**
         * This method instantiates a particular subclass implementing
         * the DAO methods based on the information obtained from the
         * deployment descriptor
         *
         * @return CourseContentDAO according to database
         * @throws CourseDAOSysException
         */
        public static CourseContentDAO getDAO() throws CourseDAOSysException
        {
                CourseContentDAO dao = null;

                try
                {
                        dao = new CourseContentDAOImpl();
                }
                catch (Exception se)
                {
                        throw new CourseDAOSysException(
                                "CourseContentDAOFactory.getDAO:  Exception while getting DAO type : \n" +
                                        se.getMessage());
                }

                return dao;
        }
}
