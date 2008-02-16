/**
 * CourseMyCourseDAO.java.
 * User: fengch  Date: 2004-5-9
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.course.dao;

import com.ulearning.ulms.course.exceptions.CourseSysException;
import com.ulearning.ulms.course.model.CourseListModel;


public interface CourseMyCourseDAO
{
        public CourseListModel getMyAllCourses(int userID)
                throws CourseSysException;

        public CourseListModel getMyAllCourses_by(int userID)
                throws CourseSysException;

        public CourseListModel getMyTeachingCourse(int userID)
                throws CourseSysException;

        public CourseListModel getMyEstablishCourse(int userID)
                throws CourseSysException;

        public CourseListModel getMyAdminCourse(int userID)
                throws CourseSysException;

        public CourseListModel getMyApplyingCourse(int userID)
                throws CourseSysException;

        public CourseListModel getMyAdminOrTeachingCourse(int userID)
                throws CourseSysException;

        public CourseListModel getMyLearningCourse(int userID)
                throws CourseSysException;

        public CourseListModel getMyLearnedCourse(int userID)
                throws CourseSysException;
}
