/**
 * Copyright (c) 2000-2005.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.admin.gradetermcourse.dao;

import com.ulearning.ulms.admin.gradetermcourse.exceptions.GradeTermCourseDAOSysException;
import com.ulearning.ulms.admin.gradetermcourse.form.GradeTermCourseForm;

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
public interface GradeTermCourseDAO
{
        public Serializable insertGradeTermCourse(GradeTermCourseForm tf)
                throws GradeTermCourseDAOSysException;

        public void updateGradeTermCourse(GradeTermCourseForm tf)
                throws GradeTermCourseDAOSysException;

        public void deleteGradeTermCourse(int gradeCID)
                throws GradeTermCourseDAOSysException;

        public List getGradeTermCourseList(int gradetID)
                throws GradeTermCourseDAOSysException;

        public GradeTermCourseForm getGradeTermCourseListByName(int gradeTID,
                                                                String courseName) throws GradeTermCourseDAOSysException;

        public GradeTermCourseForm getGradeTermCourse(int gradeCID)
                throws GradeTermCourseDAOSysException;
}
