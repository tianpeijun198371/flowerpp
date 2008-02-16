/**
 * Copyright (c) 2000-2005.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.admin.gradeusercourse.dao;

import com.ulearning.ulms.admin.gradeusercourse.exceptions.GradeUserCourseDAOSysException;


/**
 * Class description goes here.
 * <p/>
 * Created by Auto Code Produce System
 * User: xiejh
 * Date: 20060321
 * Time: 182730
 */
public class GradeUserCourseDAOFactory
{
        public static GradeUserCourseDAO getDAO()
                throws GradeUserCourseDAOSysException
        {
                GradeUserCourseDAO dao = null;

                try
                {
                        dao = new GradeUserCourseDAOImpl();
                }
                catch (Exception se)
                {
                        throw new GradeUserCourseDAOSysException(
                                "GradeUserCourseDAOFactory.getDAO:  Exception while getting DAO type : \n" +
                                        se.getMessage());
                }

                return dao;
        }
}
