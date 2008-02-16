//Source file: d:\\ulms\\source\\com\\eduedu\\ulms\\course\\dao\\MasterDAO.java
package com.ulearning.ulms.course.dao;

import com.ulearning.ulms.course.exceptions.CourseAppException;
import com.ulearning.ulms.course.exceptions.CourseSysException;
import com.ulearning.ulms.course.model.*;

import java.util.List;


public interface MasterDAO
{
        /**
         * @param catalogID
         * @throws CourseSysException
         */
        public MasterTreeModel getTree(int catalogID) throws CourseSysException;

        /**
         * @param catalogID
         * @throws com.ulearning.ulms.course.exceptions.CourseSysException
         *
         */
        public MasterTreeModel getTree(int catalogID, int type)
                throws CourseSysException;

        /**
         * @param catalogID
         * @param type
         * @param aspID
         * @return
         * @throws CourseSysException
         */
        public MasterTreeModel getTree(int catalogID, int type, int aspID, int orgID)
                throws CourseSysException;

        public MasterTreeModel getTree(int catalogID, int type, int aspID,
                                       int orgID, int catalogType) throws CourseSysException;

        /**
         * @param masterID
         * @throws CourseSysException
         */
        public MasterModel getMaster(int masterID) throws CourseSysException;

        /**
         * @param value
         * @throws CourseSysException
         */
        public void updateMaster(MasterModel value)
                throws CourseSysException, CourseAppException;

        /**
         * @param value
         * @throws CourseSysException
         */
        public int createMaster(MasterModel value)
                throws CourseSysException, CourseAppException;

        /**
         * @param values
         * @throws CourseSysException
         */
        public void deleteMaster(List values) throws CourseSysException;

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

        /**
         * @param value
         * @throws CourseSysException
         */
        public void updateCatalog(CatalogModel value) throws CourseSysException;

        /**
         * @param value
         * @throws CourseSysException
         */
        public int createCatalog(CatalogModel value) throws CourseSysException;

        /**
         * @param catalogID
         * @throws CourseSysException
         */
        public List getCatalogPath(int catalogID) throws CourseSysException;

        /**
         * search the master according to the keywords.
         *
         * @param key
         * @throws CourseSysException
         */
        public MasterListModel search(String key) throws CourseSysException;

        /**
         * Search the master according to the keywords and type.
         *
         * @param key
         * @param masterType
         * @return
         * @throws CourseSysException
         */
        public MasterListModel search(String key, int masterType)
                throws CourseSysException;

        public MasterListModel search(String key, int masterType, int aspID,
                                      int orgID) throws CourseSysException;

        /*
        * 判断在所在目录下，该目录名是否已存在
        */
        public boolean isExistCatalog(String thisCatalogName, int thisCatalogID,
                                      int catalogID, int catType, int aspID, int orgID)
                throws CourseSysException;

        public int getCatalogID(String thisCatalogName, int catalogID, int catType)
                throws CourseSysException;

        /*
        * 判断课程代码是否存在
        */
        public boolean isExistMasterCode(String masterCode, int masterID, int type,
                                         int aspID, int orgID) throws CourseSysException;

        public int getAspTotalMaster(int aspID, int orgID, int type)
                throws CourseSysException;

        public int getAspTotalMaster(int aspID, int orgID, int type,
                                     String startDate, String endDate) throws CourseSysException;

        public List getMasterInfo(String CourseName, String[] catalogIDs,
                                  String startDate, String endDate, int aspID, int orgID)
                throws CourseSysException;

        public int getMasterHasPubNum(int masterID, int type)
                throws CourseSysException;

        public boolean isHierachical(int ancestorID, int children)
                throws CourseSysException;

        public List getCourseFromMaster(int masterID, int type)
                throws CourseSysException;

        //得到全部培训班
        public List getAllMaster(int aspID, int orgID, int type)
                throws CourseSysException;
}
