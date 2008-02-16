/**
 * Copyright (c) 2000-2005.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.admin.gradetermcourse.bean;

import com.ulearning.ulms.admin.gradetermcourse.dao.GradeTermCourseDAO;
import com.ulearning.ulms.admin.gradetermcourse.dao.GradeTermCourseDAOFactory;
import com.ulearning.ulms.admin.gradetermcourse.exceptions.GradeTermCourseDAOSysException;
import com.ulearning.ulms.admin.gradetermcourse.form.GradeTermCourseForm;

import java.util.List;


/**
 * Class description goes here.
 * <p/>
 * Created by Auto Code Produce System
 * User: xiejh
 * Date: 20060321
 * Time: 182730
 */
public class GradeTermCourseImpl
{
        public static List getGradeTermCourseList(int gradeTID)
                throws GradeTermCourseDAOSysException
        {
                List GradeTermCourseList = null;

                try
                {
                        GradeTermCourseDAO dao = GradeTermCourseDAOFactory.getDAO();
                        GradeTermCourseList = dao.getGradeTermCourseList(gradeTID);
                }
                catch (GradeTermCourseDAOSysException tdse)
                {
                        tdse.printStackTrace();
                }

                return GradeTermCourseList;
        }

        public static GradeTermCourseForm getGradeTermCourse(int gradeCID)
                throws GradeTermCourseDAOSysException
        {
                GradeTermCourseForm tf = null;

                try
                {
                        GradeTermCourseDAO dao = GradeTermCourseDAOFactory.getDAO();
                        tf = dao.getGradeTermCourse(gradeCID);
                }
                catch (GradeTermCourseDAOSysException tdse)
                {
                        tdse.printStackTrace();
                }

                return tf;
        }

        public static GradeTermCourseForm getGradeTermCourseListByName(
                int gradeTID, String courseName) throws GradeTermCourseDAOSysException
        {
                GradeTermCourseForm tf = null;

                try
                {
                        GradeTermCourseDAO dao = GradeTermCourseDAOFactory.getDAO();
                        tf = dao.getGradeTermCourseListByName(gradeTID, courseName);
                }
                catch (GradeTermCourseDAOSysException tdse)
                {
                        tdse.printStackTrace();
                }

                return tf;
        }

        /*        public  static List getGradeTermCourseListByName(String[] courseName) throws GradeTermCourseDAOSysException
        {
                List GradeTermCourseList=null;
                try
                {
                        GradeTermCourseDAO dao=GradeTermCourseDAOFactory.getDAO();
                        GradeTermCourseList=dao.getGradeTermCourseListByName( courseName);
                }
                catch(GradeTermCourseDAOSysException tdse)
                {
                        tdse.printStackTrace();
                }
                return GradeTermCourseList;
        }*/
        public static void deleteGradeTermCourse(int gradeCID)
                throws GradeTermCourseDAOSysException
        {
                try
                {
                        GradeTermCourseDAO dao = GradeTermCourseDAOFactory.getDAO();
                        dao.deleteGradeTermCourse(gradeCID);
                }
                catch (GradeTermCourseDAOSysException tdse)
                {
                        tdse.printStackTrace();
                }
        }
}
