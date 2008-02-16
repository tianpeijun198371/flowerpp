/**
 * Created by IntelliJ IDEA.
 * Grade: dengj
 * Date: Apr 8, 2004
 * Time: 9:12:45 AM
 * To change this template use Options | File Templates.
 */
package com.ulearning.ulms.course.test.grade.dao;

import com.ulearning.ulms.course.test.grade.exceptions.GradeDAOSysException;
import com.ulearning.ulms.util.DBUtil;
import com.ulearning.ulms.util.log.LogUtil;


public class GradeDAOFactory
{
        /**
         * This method instantiates a particular subclass implementing
         * the DAO methods based on the information obtained from the
         * deployment descriptor
         */
        public static GradeDAO getDAO() throws GradeDAOSysException
        {
                GradeDAO dao = null;

                try
                {
                        dao = new GradeDAOImpl();
                }
                catch (Exception se)
                {
                        throw new GradeDAOSysException(
                                "PlanDAOFactory.getDAO:  Exception while getting DAO type : \n" +
                                        se.getMessage());
                }

                return dao;
        }
}
