package com.ulearning.ulms.course.dao;

import com.ulearning.ulms.course.exceptions.CourseSysException;
import com.ulearning.ulms.util.DBUtil;
import com.ulearning.ulms.util.log.LogUtil;

import java.sql.Connection;


/**
 * Created by IntelliJ IDEA.
 * User: ff
 * Date: 2004-4-28
 * Time: 0:10:40
 * To change this template use File | Settings | File Templates.
 */
public class CourseDAOFactory
{
        /**
         * This method instantiates a particular subclass implementing
         * the DAO methods based on the information obtained from the
         * deployment descriptor
         */
        public static CourseDAO getDAO() throws CourseSysException
        {
                CourseDAO dao = null;

                try
                {
                        dao = new CourseDAOImpl();
                }
                catch (Exception se)
                {
                        LogUtil.debug("course",
                                "[CourseDAOFactory]======================SQLException=" +
                                        se.getMessage());
                        throw new CourseSysException(se);
                }

                return dao;
        }
}
