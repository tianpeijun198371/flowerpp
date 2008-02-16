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
         * @param type     1:���� 2: ��ҵ
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

        /*�γ��Ƽ�*/
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
         * ���ݿγ̱�ŷ���CourseModel.<br>
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
        * �жϿγ̴����Ƿ����
        */
        public boolean isExistCourseCode(String courseCode, int courseID,
                                         int aspID, int orgID) throws CourseSysException;

        /*
        * �жϿγ���������
        */
        public boolean getCourseLifeStatus(int courseID) throws CourseSysException;

        //add by zhangy
        /**
         * ����ĳһ�ſγ̵�������Ϣ
         *
         * @param courseID
         * @return
         * @throws CourseSysException
         */
        public List getCourseFormByParent(int courseID) throws CourseSysException;

        /**
         * ����ĳһ�ſγ��Ѳμӵ�����
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
         * ����Catalog
         *
         * @param id
         * @return
         * @throws CourseSysException
         */
        public Catalog readCatalog(int id)
                throws CourseSysException;

        /**
         * �����γ�
         *
         * @param aspID
         * @param orgID
         * @param catalogID �������������Ŀ¼
         * @param orgIDs  �����������ѧУ
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
         * ȡĳĿ¼�����е���Ŀ¼(Catalog)
         *
         * @param aspID
         * @param orgID
         * @param catalogID
         * @param isIncludeSubCatalog �Ƿ�ݹ������Ŀ¼�µ�����
         * @return
         */
        public List getCatalogChildren(int aspID, int orgID, int catalogID, boolean isIncludeSubCatalog);

        /**
         * ȡĳĿ¼�����е���Ŀ¼(CatalogID)
         *
         * @param aspID
         * @param orgID
         * @param catalogID
         * @param isIncludeSubCatalog �Ƿ�ݹ������Ŀ¼�µ�����
         * @return
         */
        public List getCatalogChildrenID(int aspID, int orgID, int catalogID, boolean isIncludeSubCatalog);
}
