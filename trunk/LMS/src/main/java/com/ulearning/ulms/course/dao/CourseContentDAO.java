/**
 * CourseContentDAO.java.
 * User: dengj  Date: 2004-4-30
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.course.dao;

import com.ulearning.ulms.core.util.PagerList;
import com.ulearning.ulms.course.exceptions.CourseDAOSysException;
import com.ulearning.ulms.course.model.CourseContentForm;

import java.util.List;


public interface CourseContentDAO
{
        /**
         * 返回用户所能看到的的所有课程文档列表
         *
         * @param userID
         * @return
         */
        public PagerList getAllCourseContentsByUser(int userID, int pageNo,
                                                    int pageSize) throws CourseDAOSysException;

        /**
         * Add a course content node to course
         *
         * @param details
         * @return the course content ID which has been added
         * @throws CourseDAOSysException
         */
        public int addCourseContent(CourseContentForm details)
                throws CourseDAOSysException;

        /**
         * get physicsAddress
         *
         * @param nodeID
         * @return
         * @throws CourseDAOSysException
         */
        public String getCourseContentURL(int nodeID) throws CourseDAOSysException;

        /**
         * Update a course content node to course
         *
         * @param details
         * @throws CourseDAOSysException
         */
        public void updateCourseContent(CourseContentForm details)
                throws CourseDAOSysException;

        /**
         * Delete a content node by the specified nodeID
         *
         * @param nodeID
         * @throws CourseDAOSysException
         */
        public void removeCourseContent(String nodeID) throws CourseDAOSysException;

        /**
         * Get a single course content info according to nodeID
         *
         * @param nodeID
         * @return
         * @throws CourseDAOSysException
         */
        public CourseContentForm getCourseContent(String nodeID)
                throws CourseDAOSysException;

        /**
         * 返回nodetype类型的课程内容列表
         * @param relationID
         * @param nodeType
         * @return
         * @throws CourseDAOSysException
         */
        public List getCourseContentByNodeType(int relationID, int nodeType)
                throws CourseDAOSysException;
        
        /**
         * Get a single course content info according to nodeID
         *
         * @param realationID
         * @return
         * @throws CourseDAOSysException
         */
        public CourseContentForm getCourseContentByType(int realationID, int type)
                throws CourseDAOSysException;

        /**
         * Get the content list by the appointed catalogID
         *
         * @param relationID
         * @param parentID
         * @return
         * @throws CourseDAOSysException
         */
        public List getCourseContentList(int relationID, int parentID)
                throws CourseDAOSysException;

        /**
         * Get the content list by the appointed catalogID
         *
         * @param isAdmin
         * @param relationID
         * @param parentID
         * @return
         * @throws CourseDAOSysException
         */
        public List getCourseContentList(boolean isAdmin, int relationID,
                                         int parentID) throws CourseDAOSysException;

        /**
         * Get the content list by the appointed catalogID
         *
         * @param courseID
         * @param parentID
         * @return
         * @throws CourseDAOSysException
         */
        public List getCourseContentTree(int courseID, int parentID)
                throws CourseDAOSysException;

        public List getCourseContentList(int relationID)
                throws CourseDAOSysException;
}
