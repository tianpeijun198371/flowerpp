/**
 * Copyright (c) 2000-2005.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.admin.gradeusercourse.dao;

import com.ulearning.ulms.admin.gradeusercourse.exceptions.GradeUserCourseDAOSysException;
import com.ulearning.ulms.admin.gradeusercourse.form.GradeUserCourseForm;

import java.io.Serializable;

import java.util.List;


/**
 * Class description goes here.
 * <p/>
 * Created by Auto Code Produce System
 * User: xiejh
 * Date: 20060321
 * Time: 182730
 */
public interface GradeUserCourseDAO
{
        public int insertGradeUserCourse(GradeUserCourseForm tf)
                throws GradeUserCourseDAOSysException;

        public GradeUserCourseForm checkuser(int gradeID, int termID, int gradecID)
                throws GradeUserCourseDAOSysException;

        public void updateGradeUserCourse(GradeUserCourseForm tf)
                throws GradeUserCourseDAOSysException;

        public void deleteGradeUserCourse(int gradeUCID)
                throws GradeUserCourseDAOSysException;

        public List getGradeUserCourseList() throws GradeUserCourseDAOSysException;

        public List getGradeUserCourseList(int guID)
                throws GradeUserCourseDAOSysException;

        public List getGradeUserCourseList(int gradeTID, int courseID)
                throws GradeUserCourseDAOSysException;

        public GradeUserCourseForm getGradeUserCourse(int gradeUCID)
                throws GradeUserCourseDAOSysException;
}
