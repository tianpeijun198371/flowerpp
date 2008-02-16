/**
 * CourseWebImpl.java.
 * User: fengch  Date: 2004-5-9
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.course.webimpls;

import com.ulearning.ulms.course.dao.*;
import com.ulearning.ulms.course.exceptions.CourseAppException;
import com.ulearning.ulms.course.exceptions.CourseSysException;
import com.ulearning.ulms.course.model.*;
import com.ulearning.ulms.course.util.CourseKeys;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;


public class CourseWebImpl implements Serializable
{
        private CourseDAO courseDAO;
        private CourseUserDAO courseUserDAO;
        private CourseMyCourseDAO courseMyCourseDAO;

        public CourseWebImpl()
        {
                try
                {
                        courseUserDAO = CourseUserDAOFactory.getDAO();
                        courseDAO = CourseDAOFactory.getDAO();
                        courseMyCourseDAO = CourseMyCourseDAOFactory.getDAO();
                }
                catch (Exception ex)
                {
                        ex.printStackTrace();
                }
        }

        public CourseModel getCourse(int course) throws CourseSysException
        {
                CourseModel cat;

                cat = courseDAO.getCourse(course);

                return cat;
        }

        /**
         * Getting all the course selected by  one person
         *
         * @param userID
         * @return
         * @throws CourseSysException
         */
        public CourseListModel getMyApplyingCourse(int userID)
                throws CourseSysException
        {
                courseMyCourseDAO = CourseMyCourseDAOFactory.getDAO();

                return courseMyCourseDAO.getMyApplyingCourse(userID);
        }

        /**
         * @param value
         * @throws com.ulearning.ulms.course.exceptions.CourseSysException
         *
         */
        public int createCourse(CourseModel value)
                throws CourseSysException, CourseAppException
        {
                return courseDAO.createCourse(value);
        }

        /**
         * @param catalogID
         * @throws CourseSysException
         */
        public CourseTreeModel getTree(int catalogID) throws CourseSysException
        {
                return courseDAO.getTree(catalogID);
        }

        public CourseTreeModel getTree(int catalogID, int aspID)
                throws CourseSysException
        {
                return courseDAO.getTree(catalogID, aspID, 0, 0);
        }

        public CourseTreeModel getCourseCommend(int catalogID, int aspID)throws CourseSysException
        {
                return courseDAO.getCourseCommend(catalogID, aspID, 0, 1,1);
        }
        //得到示范校和总校班级列表
        public CourseTreeModel getCourseByAsp(int catalogID, int aspID)throws CourseSysException
        {
                return courseDAO.getCourseCommend(catalogID, aspID, 0, 1,0);
        }

        //得到示范校新理念级列表
        public CourseTreeModel getCourseByXLN(int catalogID, int aspID)throws CourseSysException
        {
                return courseDAO.getCourseByXLN(catalogID, aspID, 0, 1,0);
        }

        //得到示范校网络培训班级列表
        public CourseTreeModel getCourseByTeachModeCourse(int catalogID, int aspID)throws CourseSysException
        {
                return courseDAO.getCourseByTeach(catalogID, aspID, 0, 1,0, CourseKeys.TEACH_MODE_COURSE);
        }

        //得到示范校新理念级列表
        public CourseTreeModel getCourseByTeachModeClassical(int catalogID, int aspID)throws CourseSysException
        {
                return courseDAO.getCourseByTeach(catalogID, aspID, 0, 1,0, CourseKeys.TEACH_MODE_CLASSICAL);
        }

        public CourseTreeModel getTree(int catalogID, int aspID, int orgID)
                throws CourseSysException
        {
                return courseDAO.getTree(catalogID, aspID, orgID, 0);
        }

        public CourseTreeModel getTree(int catalogID, int aspID, int orgID,
                                       int status) throws CourseSysException
        {
                return courseDAO.getTree(catalogID, aspID, orgID, status);
        }

        /**
         * @param value
         * @throws CourseSysException
         */
        public void updateCourse(CourseModel value)
                throws CourseSysException, CourseAppException
        {
                courseDAO.updateCourse(value);
        }

        /**
         * @param values
         * @throws CourseSysException
         */
        public void deleteCourse(List values) throws CourseSysException
        {
                courseDAO.deleteCourse(values);
        }

        /**
         * @param catalogID
         * @throws CourseSysException
         */
        public CatalogModel getCatalog(int catalogID) throws CourseSysException
        {
                return courseDAO.getCatalog(catalogID);
        }

        /**
         * @param values
         * @throws CourseSysException
         */
        public void deleteCatalog(List values) throws CourseSysException
        {
                courseDAO.deleteCatalog(values);
        }

        /**
         * @param value
         * @throws CourseSysException
         */
        public void updateCatalog(CatalogModel value) throws CourseSysException
        {
                courseDAO.updateCatalog(value);
        }

        /**
         * @param value
         * @throws CourseSysException
         */
        public void createCatalog(CatalogModel value) throws CourseSysException
        {
                courseDAO.createCatalog(value);
        }

        /**
         * @param catalogID
         * @throws CourseSysException
         */
        public List getCatalogPath(int catalogID) throws CourseSysException
        {
                return courseDAO.getCatalogPath(catalogID);
        }

        public CourseListModel getMyAllCourses(int userID)
                throws CourseSysException
        {
                return courseMyCourseDAO.getMyAllCourses(userID);
        }

        public CourseListModel getMyAllCourses_by(int userID)
                throws CourseSysException
        {
                return courseMyCourseDAO.getMyAllCourses_by(userID);
        }

        public CourseListModel getMyLearningCourse(int userID)
                throws CourseSysException
        {
                return courseMyCourseDAO.getMyLearningCourse(userID);
        }

        public CourseListModel getMyAdminOrTeachingCourse(int userID)
                throws CourseSysException
        {
                return courseMyCourseDAO.getMyAdminOrTeachingCourse(userID);
        }

        public CourseListModel search(String courseKey) throws CourseSysException
        {
                return courseDAO.search(courseKey);
        }


        public CourseListModel search(String courseKey, int aspID)
                throws CourseSysException
        {
                return courseDAO.search(courseKey, aspID, 0);
        }

        public CourseListModel search(String courseKey, int aspID, int orgID)
                throws CourseSysException
        {
                return courseDAO.search(courseKey, aspID, orgID);
        }

        public List searchCourse(String courseKey, int aspID)
                throws CourseSysException
        {
                return courseDAO.searchCourse(courseKey, aspID, 0);
        }

        public List searchCourse(String courseKey, int aspID, int orgID)
                throws CourseSysException
        {
                return courseDAO.searchCourse(courseKey, aspID, orgID);
        }

        public List getCatalogList(int parentID, int aspID)
                throws CourseSysException
        {
                return courseDAO.getCatalogList(parentID, -1, aspID, 0);
        }

        public List getCatalogList(int parentID, int aspID, int orgID)
                throws CourseSysException
        {
                return courseDAO.getCatalogList(parentID, -1, aspID, orgID);
        }

        public List getCourseInfo(String CourseName, String[] catalogIDs,
                                  String startDate, String endDate, int aspID) throws CourseSysException
        {
                return courseDAO.getCourseInfo(CourseName, catalogIDs, startDate,
                        endDate, aspID, 0);
        }

        public List getCourseInfo(String CourseName, String[] catalogIDs,
                                  String startDate, String endDate, int aspID, int orgID)
                throws CourseSysException
        {
                return courseDAO.getCourseInfo(CourseName, catalogIDs, startDate,
                        endDate, aspID, orgID);
        }

        public CourseCurrentStatus getCourseNowStatus(int courseID, int type)
                throws CourseSysException
        {
                return courseDAO.getCourseNowStatus(courseID, type); //SecurityConstants.USER_COURSE_RELATION);
        }

        public List getCourseListByParent(int parentID, int aspID)
                throws CourseSysException
        {
                return courseDAO.getCourseListByParent(parentID, aspID, 0);
        }

        public List getCourseListByTeach(int parentID,int aspID,int teachmod,int status)throws CourseSysException
        {
                return courseDAO.getCourseListByTeach(parentID, aspID, 0,teachmod,status);
        }

        public List getCourseListByParent(int parentID, int aspID, int orgID)
                throws CourseSysException
        {
                return courseDAO.getCourseListByParent(parentID, aspID, orgID);
        }

        /**
         * 返回某一门课程的所有信息
         *
         * @param courseID
         * @return
         * @throws CourseSysException
         */
        public List getCourseFormByParent(int courseID) throws CourseSysException
        {
                return courseDAO.getCourseFormByParent(courseID);
        }

        /**
         * 返回某一门课程已参加的人数
         *
         * @param courseID
         * @return
         * @throws CourseSysException
         */
        public int getCourseFormCount(int courseID) throws CourseSysException
        {
                return courseDAO.getCourseFormCount(courseID);
        }

        public List getCatalogListByParent(int parentID, int type, int aspID)
                throws CourseSysException
        {
                return courseDAO.getCatalogListByParent(parentID, type, aspID, 0);
        }

        public List getCatalogListByParent(int parentID, int type, int aspID,
                                           int orgID) throws CourseSysException
        {
                return courseDAO.getCatalogListByParent(parentID, type, aspID, orgID);
        }

        public List getCourseTree(int aspID) throws CourseSysException
        {
                return courseDAO.getCourseTree(aspID, 0);
        }

        public List getCourseTree(int aspID, int orgID) throws CourseSysException
        {
                return courseDAO.getCourseTree(aspID, orgID);
        }

        public StringBuffer getCatTree(int parentID, StringBuffer sTemp, int aspID,
                                       int type, int level, int selectID)
        {
                StringBuffer temp = sTemp;

                try
                {
                        CourseWebImpl courseWebImpl = new CourseWebImpl();
                        List allList = new LinkedList();
                        List catalogList = courseWebImpl.getCatalogListByParent(parentID,
                                type, aspID);
                        allList.addAll(catalogList);

                        CatalogModel catm = null;
                        int num = level;
                        String blankString = "";

                        for (int j = 0; j < num; j++)
                        {
                                blankString += "&nbsp;";
                        }

                        String selectedString = "";

                        for (int i = 0; i < allList.size(); i++)
                        {
                                if (allList.get(i) instanceof CatalogModel)
                                {
                                        catm = (CatalogModel) allList.get(i);

                                        if (selectID == catm.getCatalogID())
                                        {
                                                selectedString = "selected";
                                        }
                                        else
                                        {
                                                selectedString = "";
                                        }

                                        temp.append("<option value='" + catm.getCatalogID() + "' " +
                                                selectedString + " >" + blankString + catm.getName() +
                                                "</option>");
                                        temp = getCatTree(catm.getCatalogID(), temp, aspID, type,
                                                num + 1, selectID);
                                }
                        }
                }
                catch (Exception e)
                {
                }

                return temp;
        }
}
