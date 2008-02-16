/**
 * CourseUserWebImpl.java.
 * User: fengch  Date: 2004-5-9
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.course.webimpls;

import com.ulearning.ulms.common.helper.HistoryHelper;
import com.ulearning.ulms.core.exceptions.ULMSException;
import com.ulearning.ulms.core.security.bean.SecurityConstants;
import com.ulearning.ulms.core.util.StringUtil;
import com.ulearning.ulms.course.dao.CourseDAOFactory;
import com.ulearning.ulms.course.dao.CourseUserDAO;
import com.ulearning.ulms.course.dao.CourseUserDAOFactory;
import com.ulearning.ulms.course.exceptions.CourseAppException;
import com.ulearning.ulms.course.exceptions.CourseSysException;
import com.ulearning.ulms.course.helper.CourseHelper;
import com.ulearning.ulms.course.model.*;
import com.ulearning.ulms.course.util.CourseKeys;
import com.ulearning.ulms.user.form.UserForm;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class CourseUserWebImpl
{
        private CourseUserDAO courseUserDAO;

        public CourseUserWebImpl()
        {
                try
                {
                        courseUserDAO = CourseUserDAOFactory.getDAO();
                }
                catch (Exception ex)
                {
                        ex.printStackTrace();
                }
        }

        public CourseUserListModel getCourseAllUsers(int relationID, int type,
                                                     int pageNo, int pageSize) throws CourseSysException
        {
                return courseUserDAO.getCourseAllUsers(relationID, type, pageNo,
                        pageSize);
        }

        public int getCourseAllUserscount(int relationID, int type, int pageNo,
                                          int pageSize) throws CourseSysException
        {
                return courseUserDAO.getCourseAllUserscount(relationID, type, pageNo,
                        pageSize);
        }

        public CourseUserListModel getCourseStudents(int relationID, int type,
                                                     int pageNo, int pageSize) throws CourseSysException
        {
                return courseUserDAO.getCourseStudents(relationID, type, pageNo,
                        pageSize);
        }

        public CourseUserListModel getCourseTeachers(int relationID, int type,
                                                     int pageNo, int pageSize) throws CourseSysException
        {
                return courseUserDAO.getCourseTeachers(relationID, type, pageNo,
                        pageSize);
        }

        public CourseUserListModel getCourseAdministrators(int relationID,
                                                           int type, int pageNo, int pageSize) throws CourseSysException
        {
                return courseUserDAO.getCourseAdministrators(relationID, type, pageNo,
                        pageSize);
        }

        public CourseUserModel getCourseUser(int relationID, int type, int userID)
                throws CourseSysException
        {
                return courseUserDAO.getCourseUser(relationID, type, userID);
        }

        public int getTotalNumberByCourseStudent(int relationID, int type)
                throws CourseSysException
        {
                return courseUserDAO.getTotalNumberByCourseStudent(relationID, type);
        }

        public int getTotalNumberByCourseStudent(int relationID, int type, int state)
                throws CourseSysException
        {
                return courseUserDAO.getTotalNumberByCourseStudent(relationID, type,
                        state);
        }

        public CourseRoleListModel getUserRoles(int usreID, int relationID, int type)
                throws CourseSysException
        {
                return courseUserDAO.getUserRoles(usreID, relationID, type, null);
        }

        public CourseUserListModel getCourseUsers(String[] relationID, int type,
                                                  int pageNo, int pageSize) throws CourseSysException
        {
                return courseUserDAO.getCourseUsers(relationID, type, pageNo, pageSize);
        }

        public int countAllCourseUsers(int relationID, int type)
                throws CourseSysException
        {
                return courseUserDAO.countAllCourseUsers(relationID, type);
        }

        public CourseUserListModel getAllCourseUsers(int relationID, int type,
                                                     int first, int max) throws CourseSysException
        {
                return courseUserDAO.getAllCourseUsers(relationID, type, first, max);
        }

        public CourseUserListModel searchCourseUsers(UserForm uf, int relationID,
                                                     int type, int first, int max) throws CourseSysException
        {
                return courseUserDAO.searchCourseUsers(uf, relationID, type, first, max);
        }

        public int countSearchCourseUsers(UserForm uf, int relationID, int type)
                throws CourseSysException
        {
                return courseUserDAO.countSearchCourseUsers(uf, relationID, type);
        }
}
