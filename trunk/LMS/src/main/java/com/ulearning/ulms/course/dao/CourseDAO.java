/**
 * CourseDAO.java.
 * User: fengch  Date: 2004-5-9
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.course.dao;

import com.ulearning.ulms.course.exceptions.CourseAppException;
import com.ulearning.ulms.course.exceptions.CourseSysException;
import com.ulearning.ulms.course.model.*;
import com.ulearning.ulms.core.util.PagerList;

import java.util.List;


public interface CourseDAO
{
        /**
         * add jiefocourse by form
         *
         * @param form
         * @throws CourseSysException
         */
        public void addJieFoCourse(JieFoCourseForm form) throws CourseSysException;

        /**
         * @param courseID
         * @param userID
         * @param type     1:考试 2: 作业
         * @return
         * @throws CourseSysException
         */
        public float getJieFoChenji(int courseID, int userID, int type)
                throws CourseSysException;

        /**
         * update jiefoCourse
         *
         * @param form
         * @throws CourseSysException
         */
        public void updateJieFoCourse(JieFoCourseForm form)
                throws CourseSysException;

        /**
         * delet jiefocourse
         *
         * @param courseIDs
         * @throws CourseSysException
         */
        public void deleteJieFoCourse(String[] courseIDs) throws CourseSysException;

        /**
         * @param catalogID
         * @throws CourseSysException
         */
        public CourseTreeModel getTree(int catalogID) throws CourseSysException;

        /**
         * @param catalogID
         * @return
         * @throws CourseSysException
         */
        public CourseTreeModel getTree(int catalogID, int aspID, int orgID,
                                       int status) throws CourseSysException;

        /*课程推荐*/
        /**
         * @param catalogID
         * @return
         * @throws CourseSysException
         */
        public CourseTreeModel getCourseCommend(int catalogID, int aspID, int orgID,
                                       int status,int isCommend) throws CourseSysException;

        public CourseTreeModel getCourseByXLN(int catalogID, int aspID, int orgID,
                                       int status,int isCommend) throws CourseSysException;

        public CourseTreeModel getCourseByTeach(int catalogID, int aspID, int orgID,
                                       int status,int isCommend,int techID) throws CourseSysException;
        /**
         * @param courseID
         * @throws CourseSysException
         */
        public CourseModel getCourse(int courseID) throws CourseSysException;

        /**
         * 根据课程编号返回CourseModel.<br>
         *
         * @param aspID
         * @param code
         * @return
         * @throws CourseSysException
         */
        public CourseModel getCourse(int aspID, int orgID, String code)
                throws CourseSysException;

        /**
         * @param courseID
         * @throws com.ulearning.ulms.course.exceptions.CourseSysException
         *
         */
        public String getCourseName(int courseID) throws CourseSysException;

        /**
         * @param value
         * @throws CourseSysException
         */
        public void updateCourse(CourseModel value)
                throws CourseSysException, CourseAppException;

        /**
         * @param value
         * @throws CourseSysException
         */
        public int createCourse(CourseModel value)
                throws CourseSysException, CourseAppException;

        /**
         * @param values
         * @throws CourseSysException
         */
        public void deleteCourse(List values) throws CourseSysException;

        /**
         * @param catalogID
         * @throws CourseSysException
         */
        public CatalogModel getCatalog(int catalogID) throws CourseSysException;

        /**
         * @param values
         * @throws CourseSysException
         */
        public void deleteCatalog(List values) throws CourseSysException;

        public void deleteByMaster(int masterID) throws CourseSysException;

        /**
         * @param value
         * @throws CourseSysException
         */
        public void updateCatalog(CatalogModel value) throws CourseSysException;

        /**
         * @param value
         * @throws CourseSysException
         */
        public void createCatalog(CatalogModel value) throws CourseSysException;

        /**
         * return the catalog's path list.
         *
         * @param catalogID
         * @throws CourseSysException
         */
        public List getCatalogPath(int catalogID) throws CourseSysException;

        /**
         * search the course according to the keywords.
         *
         * @param courseKey
         * @throws CourseSysException
         */
        public CourseListModel search(String courseKey) throws CourseSysException;

        /**
         * @param courseKey
         * @param aspID
         * @return
         * @throws CourseSysException
         */
        public CourseListModel search(String courseKey, int aspID, int orgID)
                throws CourseSysException;

        public List searchCourse(String courseKey, int aspID, int orgID)
                throws CourseSysException;

        public int getAspTotalCourse(int aspID, int orgID, String startDate,
                                     String endDate) throws CourseSysException;


        /**
         * Gets the total course count from database according to aspID and orgID
         * @param aspID ,if aspID = 0,means getting all the course
         * @param orgID ,if orgID = 0,the program will not get count by orgID
         * @return  repared course count ,default is 0
         * @throws CourseSysException
         */
        public int getAspTotalCourse(int aspID, int orgID) throws CourseSysException;

        public List getAspCourseInfo(int aspID, int orgID, String startDate,
                                     String endDate) throws CourseSysException;

        public List getCatalogList(int parentID, int type, int aspID, int orgID)
                throws CourseSysException;

        public List getCourseInfo(String CourseName, String[] catalogIDs,
                                  String startDate, String endDate, int aspID, int orgID)
                throws CourseSysException;

        public CourseCurrentStatus getCourseNowStatus(int courseID, int type)
                throws CourseSysException;

        //addby:keyh
        public List getCourseListByParent(int parentID, int aspID, int orgID)
                throws CourseSysException;


        public List getCourseListByTeach(int parentID, int aspID, int orgID,int teachmod,int status)
                throws CourseSysException;

        public List getCatalogListByParent(int parentID, int type, int aspID,
                                           int orgID) throws CourseSysException;

        /*
        * 判断课程代码是否存在
        */
        public boolean isExistCourseCode(String courseCode, int courseID,
                                         int aspID, int orgID) throws CourseSysException;

        /*
        * 判断课程生命周期
        */
        public boolean getCourseLifeStatus(int courseID) throws CourseSysException;

        //add by zhangy
        /**
         * 返回某一门课程的所有信息
         *
         * @param courseID
         * @return
         * @throws CourseSysException
         */
        public List getCourseFormByParent(int courseID) throws CourseSysException;

        /**
         * 返回某一门课程已参加的人数
         *
         * @param courseID
         * @return
         * @throws CourseSysException
         */
        public int getCourseFormCount(int courseID) throws CourseSysException;

        public List getCourseTree(int aspID, int orgID) throws CourseSysException;

        public List getSubCatalog(int catalog) throws CourseSysException;

        public boolean getIsSubCatalog(int catalog) throws CourseSysException;


         /*
         ******************************************************* tuning ***************************************************************
         */

        /**
         * 返回Catalog
         *
         * @param id
         * @return
         * @throws CourseSysException
         */
        public Catalog readCatalog(int id)
                throws CourseSysException;

        /**
         * 搜索课程
         *
         * @param aspID
         * @param orgID
         * @param catalogID 包含其下面的子目录
         * @param orgIDs  可以搜索多个学校
         * @param type
         * @param creator
         * @param teachmode
         * @param keyword
         * @param pageNo
         * @param pageSize
         * @return
         * @throws CourseSysException
         */
        public PagerList search(int aspID, int orgID, int[] orgIDs,int catalogID, String type, int creator, int teachmode,
                                String ischarge,String allowfreedomreg,String needapprove,String registermode,String guest,
                                String status,String isCommend,
                                String keyword, int pageNo, int pageSize)
                throws CourseSysException;

        /**
         * 取某目录下所有的子目录(Catalog)
         *
         * @param aspID
         * @param orgID
         * @param catalogID
         * @param isIncludeSubCatalog 是否递归包含子目录下的内容
         * @return
         */
        public List getCatalogChildren(int aspID, int orgID, int catalogID, boolean isIncludeSubCatalog);

        /**
         * 取某目录下所有的子目录(CatalogID)
         *
         * @param aspID
         * @param orgID
         * @param catalogID
         * @param isIncludeSubCatalog 是否递归包含子目录下的内容
         * @return
         */
        public List getCatalogChildrenID(int aspID, int orgID, int catalogID, boolean isIncludeSubCatalog);
}
