/**
 * Copyright (c) 2000-2005.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.admin.gradeusercourse.bean;

import com.ulearning.ulms.admin.gradeusercourse.dao.GradeUserCourseDAO;
import com.ulearning.ulms.admin.gradeusercourse.dao.GradeUserCourseDAOFactory;
import com.ulearning.ulms.admin.gradeusercourse.exceptions.GradeUserCourseDAOSysException;
import com.ulearning.ulms.admin.gradeusercourse.form.GradeUserCourseForm;

import java.util.List;


/**
 * Class description goes here.
 * <p/>
 * Created by Auto Code Produce System
 * User: xiejh
 * Date: 20060321
 * Time: 182730
 */
public class GradeUserCourseImpl
{
        public static List getGradeUserCourseList()
                throws GradeUserCourseDAOSysException
        {
                List GradeUserCourseList = null;

                try
                {
                        GradeUserCourseDAO dao = GradeUserCourseDAOFactory.getDAO();
                        GradeUserCourseList = dao.getGradeUserCourseList();
                }
                catch (GradeUserCourseDAOSysException tdse)
                {
                        tdse.printStackTrace();
                }

                return GradeUserCourseList;
        }

        public static List getGradeUserCourseList(int userIsd)
                throws GradeUserCourseDAOSysException
        {
                List GradeUserCourseList = null;

                try
                {
                        GradeUserCourseDAO dao = GradeUserCourseDAOFactory.getDAO();
                        GradeUserCourseList = dao.getGradeUserCourseList(userIsd);
                }
                catch (GradeUserCourseDAOSysException tdse)
                {
                        tdse.printStackTrace();
                }

                return GradeUserCourseList;
        }

        public static List getGradeUserCourseList(int gradeTID, int courseID)
                throws GradeUserCourseDAOSysException
        {
                List GradeUserCourseList = null;

                try
                {
                        GradeUserCourseDAO dao = GradeUserCourseDAOFactory.getDAO();
                        GradeUserCourseList = dao.getGradeUserCourseList(gradeTID, courseID);
                }
                catch (GradeUserCourseDAOSysException tdse)
                {
                        tdse.printStackTrace();
                }

                return GradeUserCourseList;
        }

        public static GradeUserCourseForm checkuser(int gradetID, int gradeuserID,
                                                    int gradecID) throws GradeUserCourseDAOSysException
        {
                GradeUserCourseForm tf = null;

                try
                {
                        GradeUserCourseDAO dao = GradeUserCourseDAOFactory.getDAO();
                        tf = dao.checkuser(gradetID, gradeuserID, gradecID);
                }
                catch (GradeUserCourseDAOSysException tdse)
                {
                        tdse.printStackTrace();
                }

                return tf;
        }

        public static GradeUserCourseForm getGradeUserCourse(int gradeUCID)
                throws GradeUserCourseDAOSysException
        {
                GradeUserCourseForm tf = null;

                try
                {
                        GradeUserCourseDAO dao = GradeUserCourseDAOFactory.getDAO();
                        tf = dao.getGradeUserCourse(gradeUCID);
                }
                catch (GradeUserCourseDAOSysException tdse)
                {
                        tdse.printStackTrace();
                }

                return tf;
        }

        public static void deleteGradeUserCourse(int gradeUCID)
                throws GradeUserCourseDAOSysException
        {
                try
                {
                        GradeUserCourseDAO dao = GradeUserCourseDAOFactory.getDAO();
                        dao.deleteGradeUserCourse(gradeUCID);
                }
                catch (GradeUserCourseDAOSysException tdse)
                {
                        tdse.printStackTrace();
                }
        }
}
