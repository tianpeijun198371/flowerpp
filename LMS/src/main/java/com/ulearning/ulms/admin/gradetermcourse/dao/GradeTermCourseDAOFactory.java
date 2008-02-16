/**
 * Copyright (c) 2000-2005.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.admin.gradetermcourse.dao;

import com.ulearning.ulms.admin.gradetermcourse.exceptions.GradeTermCourseDAOSysException;


/**
 * Class description goes here.
 * <p/>
 * Created by Auto Code Produce System
 * User: xiejh
 * Date: 20060321
 * Time: 182730
 */
public class GradeTermCourseDAOFactory
{
        public static GradeTermCourseDAO getDAO()
                throws GradeTermCourseDAOSysException
        {
                GradeTermCourseDAO dao = null;

                try
                {
                        dao = new GradeTermCourseDAOImpl();
                }
                catch (Exception se)
                {
                        throw new GradeTermCourseDAOSysException(
                                "GradeTermCourseDAOFactory.getDAO:  Exception while getting DAO type : \n" +
                                        se.getMessage());
                }

                return dao;
        }
}
