/**
 * CourseContentTypeDAO.java.
 * User: shid Date: 2005-9-7 11:08:00
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.content.dao;

import com.ulearning.ulms.content.exceptions.ContentManageSysException;
import com.ulearning.ulms.content.model.CourseContentTypeModel;

import java.util.List;


public interface CourseContentTypeDAO
{
        /**
         * �ж�contentType�Ƿ��Ѿ�����
         *
         * @param CourseContentType
         * @param aspID
         * @return boolean
         * @throws ContentManageSysException
         */
        public boolean isExistCourseContentType(String CourseContentType,
                                                String aspID) throws ContentManageSysException;

        /**
         * �õ�����orderIndex
         *
         * @param aspID
         * @return
         * @throws ContentManageSysException
         */
        public int getMaxOrderIndexFromCourseContentType(String aspID)
                throws ContentManageSysException;

        /**
         * �õ�ȫ����CourseContentType
         *
         * @param aspID
         * @return
         * @throws ContentManageSysException
         */
        public List getAllCourseContentType(String aspID)
                throws ContentManageSysException;

        /**
         * get a ContentTypeModel by a contentType.
         *
         * @param CourseContentType
         * @param aspID
         * @throws ContentManageSysException
         */
        public CourseContentTypeModel getCourseContentType(
                String CourseContentType, String aspID)
                throws ContentManageSysException;

        /**
         * �ж�contentType�Ƿ�ʹ��
         *
         * @param CourseContentTypeID
         * @throws ContentManageSysException
         */
        public boolean isUsingCourseContentType(int CourseContentTypeID)
                throws ContentManageSysException;

        /**
         * ͨ��CourseContentTypeID�õ�CourseContentTypeModel
         *
         * @param CourseContentTypeID
         * @return
         * @throws ContentManageSysException
         */
        public CourseContentTypeModel getCourseContentTypeByCourseContentTypeID(
                int CourseContentTypeID) throws ContentManageSysException;

        /**
         * add a CourseContentTypeModel.
         *
         * @param cm
         * @throws ContentManageSysException
         */
        public void addCourseContentType(CourseContentTypeModel cm)
                throws ContentManageSysException;

        /**
         * update a CourseContentTypeModel.
         *
         * @param cm
         * @throws ContentManageSysException
         */
        public void updateCourseContentType(CourseContentTypeModel cm)
                throws ContentManageSysException;

        /**
         * ʹ��jdbc�޸�orderIndex,˭����hibernateд���Ը�һ��
         *
         * @param orderIndex
         * @param aspID
         * @throws ContentManageSysException
         */
        public void updateCourseContentTypeOrderIndex(int orderIndex, String aspID)
                throws ContentManageSysException;

        /**
         * delete the CourseContentTypeModels.
         *
         * @param l the List that contain the String CourseCONTENTTYPEs
         * @throws ContentManageSysException
         */
        public void deleteCourseContentType(List l)
                throws ContentManageSysException;

        /**
         * ɾ��һ��CourseContentType
         *
         * @param id
         * @throws ContentManageSysException
         */
        public void deleteCourseContentType(int id)
                throws ContentManageSysException;

        /**
         * ͨ�����Ƶõ�id
         *
         * @param name
         * @param aspID
         * @return
         * @throws ContentManageSysException
         */
        public int getCourseContentTypeID(String name, String aspID)
                throws ContentManageSysException;
}
