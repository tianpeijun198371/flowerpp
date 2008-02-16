/**
 * CourseUserDAO.java.
 * User: fengch  Date: 2004-5-9
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.course.dao;

import com.ulearning.ulms.course.exceptions.CourseAppException;
import com.ulearning.ulms.course.exceptions.CourseSysException;
import com.ulearning.ulms.course.model.CourseRoleListModel;
import com.ulearning.ulms.course.model.CourseUserListModel;
import com.ulearning.ulms.course.model.CourseUserModel;
import com.ulearning.ulms.course.model.JieFoTrainClerkForm;
import com.ulearning.ulms.user.form.UserForm;

import java.sql.Connection;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public interface CourseUserDAO
{
        /**
         * add jiefoTrainclerk
         *
         * @param form
         * @throws CourseSysException
         * @throws CourseAppException
         */
        public void addJieFoTrainClerk(JieFoTrainClerkForm form)
                throws CourseSysException, CourseAppException;

        /**
         * update jiefoTrainclerk
         *
         * @param form
         * @throws CourseSysException
         * @throws CourseAppException
         */
        public void updateJieFoTrainClerk(JieFoTrainClerkForm form)
                throws CourseSysException, CourseAppException;

        /**
         * delete jiefoTrainclerk
         *
         * @param courseID
         * @param userIDs
         * @throws CourseSysException
         * @throws CourseAppException
         */
        public void deleteJieFoTrainClerk(int courseID, String[] userIDs)
                throws CourseSysException, CourseAppException;

        public void addCourseUser(CourseUserModel cum)
                throws CourseSysException, CourseAppException;

        /**
         * 批量添加申请培训班的用户
         *
         * @param cum
         * @throws CourseSysException
         * @throws CourseAppException
         */
        public void addCertUser(CourseUserModel cum)
                throws CourseSysException, CourseAppException;

        /**
         * 判断此用户在这个培训班中是否存在，如果存在返回true否则返回false
         *
         * @param userID
         * @param relationID
         * @param type
         * @return
         * @throws CourseSysException
         * @throws CourseAppException
         */
        public boolean isaddCertUser(int userID, int relationID, int type)
                throws CourseSysException, CourseAppException;

        //add
        public void addCourseUser(int relationID, int type, int userID, int state,
                                  int roleID) throws CourseSysException, CourseAppException;

        //add
        public void updateCourseUser(int relationID, int type, int userID,
                                     int roleID) throws CourseSysException, CourseAppException;

        public void updateCourseUser(CourseUserModel cum)
                throws CourseSysException, CourseAppException;

        // Add
        public void updateCourseUser(int userID, int relationID, int type,
                                     Date finishedTime, int state)
                throws CourseSysException, CourseAppException;

        //add
        public void deleteCourseUser(int userID, int relationID, int type)
                throws CourseSysException, CourseAppException;

        public void deleteByUserID(int userID)
                throws CourseSysException, CourseAppException;

        /**
         * delete JieforTrainClerk
         *
         * @throws CourseSysException
         * @throws CourseAppException
         */
        public void deleteJieFoTrainByClerk(String userID)
                throws CourseSysException, CourseAppException;

        //add
        public void deleteCourseUsers(ArrayList userIDs, int relationID, int type)
                throws CourseSysException, CourseAppException;

        //add
        public void deleteCourseUserRole(int userID, int relationID, int type,
                                         int roleID) throws CourseSysException, CourseAppException;

        //add
        public CourseRoleListModel getUserRoles(int usreID, int relationID,
                                                int type, Connection conn) throws CourseSysException;

        public void confirmApply(List userIDs, List states, int relationID, int type)
                throws CourseSysException;

        //add
        public CourseUserListModel getCourseAllUsers(int relationID, int type,
                                                     int pageNo, int pageSize) throws CourseSysException;

        public int getCourseAllUserscount(int relationID, int type, int pageNo,
                                          int pageSize) throws CourseSysException;

        public CourseUserListModel getCourseUsers(String[] relationID, int type,
                                                  int pageNo, int pageSize) throws CourseSysException;

        //add
        public CourseUserListModel getCourseUsersByState(int relationID, int type,
                                                         int state, int pageNo, int pageSize) throws CourseSysException;

        //ADD
        public CourseUserListModel getCourseUsersByRole(int relationID, int type,
                                                        int roleID, int pageNo, int pageSize) throws CourseSysException;

        public CourseUserModel getCourseUser(int relationID, int type, int userID)
                throws CourseSysException;

        /**
         * return the course user list by state and role.
         *
         * @throws CourseSysException
         */
        public CourseUserListModel getCourseUsers(int relationID, int type,
                                                  int role, int state) throws CourseSysException;

        public CourseUserListModel getCourseStudents(int relationID, int type,
                                                     int pageNo, int pageSize) throws CourseSysException;

        public CourseUserListModel getCourseTeachers(int relationID, int type,
                                                     int pageNo, int pageSize) throws CourseSysException;

        public CourseUserListModel getCourseAdministrators(int relationID,
                                                           int type, int pageNo, int pageSize) throws CourseSysException;

        public int getTotalNumberByCourseStudent(int relationID, int type)
                throws CourseSysException;

        public int getTotalNumberByCourseStudent(int relationID, int type, int state)
                throws CourseSysException;

        public List getCourseList(int relationID, int type)
                throws CourseSysException;

        public boolean isCreator(int RelationID, int Type, int UserID)
                throws CourseSysException;

        public List getAllUser(int relationID, int type) throws CourseSysException;

        /*     public boolean isUserInCourse(int type,int relationID,int userID)
          throws CourseSysException;
        */
        public CourseUserListModel searchCourseUsers(UserForm uf, int relationID,
                                                     int type, int first, int max) throws CourseSysException;

        public CourseUserListModel searchCourseUsersByRole(UserForm uf, int relationID,int roleID,
                                                     int type, int first, int max) throws CourseSysException;

        public int countSearchCourseUsers(UserForm uf, int relationID, int type)
                throws CourseSysException;

        public CourseUserListModel getAllCourseUsers(int relationID, int type,
                                                     int first, int max) throws CourseSysException;

        public int countAllCourseUsers(int relationID, int type)
                throws CourseSysException;

        public int countCourseUsers(int relationID, int type, int roleID, int state)
                throws CourseSysException;
}
