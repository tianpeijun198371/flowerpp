//Source file: d:\\ulms\\source\\com\\eduedu\\ulms\\course\\webimpls\\MasterWebImpl.java
package com.ulearning.ulms.course.webimpls;

import com.ulearning.ulms.course.dao.MasterDAO;
import com.ulearning.ulms.course.dao.MasterDAOFactory;
import com.ulearning.ulms.course.exceptions.CourseAppException;
import com.ulearning.ulms.course.exceptions.CourseSysException;
import com.ulearning.ulms.course.model.CatalogModel;
import com.ulearning.ulms.course.model.MasterListModel;
import com.ulearning.ulms.course.model.MasterModel;
import com.ulearning.ulms.course.model.MasterTreeModel;

import java.util.List;


public class MasterWebImpl
{
        private MasterDAO dao;

        public MasterWebImpl()
        {
                try
                {
                        dao = MasterDAOFactory.getDAO();
                }
                catch (Exception ex)
                {
                        ex.printStackTrace();
                }
        }

        /**
         * @param value
         * @throws com.ulearning.ulms.course.exceptions.CourseSysException
         *
         */
        public void createCatalog(CatalogModel value) throws CourseSysException
        {
                dao.createCatalog(value);
        }

        /**
         * @param value
         * @throws com.ulearning.ulms.course.exceptions.CourseSysException
         *
         */
        public void createMaster(MasterModel value)
                throws CourseSysException, CourseAppException
        {
                dao.createMaster(value);
        }

        /**
         * @param values
         * @throws com.ulearning.ulms.course.exceptions.CourseSysException
         *
         */
        public void deleteCatalog(List values) throws CourseSysException
        {
                dao.deleteCatalog(values);
        }

        /**
         * @param values
         * @throws com.ulearning.ulms.course.exceptions.CourseSysException
         *
         */
        public void deleteMaster(List values) throws CourseSysException
        {
                dao.deleteMaster(values);
        }

        /**
         * @param catalogID
         * @throws com.ulearning.ulms.course.exceptions.CourseSysException
         *
         */
        public CatalogModel getCatalog(int catalogID) throws CourseSysException
        {
                CatalogModel cat;

                cat = dao.getCatalog(catalogID);

                return cat;
        }

        /**
         * @param catalogID
         * @throws com.ulearning.ulms.course.exceptions.CourseSysException
         *
         */
        public List getCatalogPath(int catalogID) throws CourseSysException
        {
                List l;

                l = dao.getCatalogPath(catalogID);

                return l;
        }

        /**
         * @param masterID
         * @throws com.ulearning.ulms.course.exceptions.CourseSysException
         *
         */
        public MasterModel getMaster(int masterID) throws CourseSysException
        {
                MasterModel l;

                l = dao.getMaster(masterID);

                return l;
        }

        public MasterTreeModel getTree(int catalogID, int type, int aspID)
                throws CourseSysException
        {
                MasterTreeModel l;

                l = dao.getTree(catalogID, type, aspID, -1);

                return l;
        }

        public MasterTreeModel getTree(int catalogID, int type, int aspID, int orgID)
                throws CourseSysException
        {
                MasterTreeModel l;

                l = dao.getTree(catalogID, type, aspID, orgID);

                return l;
        }

        public MasterTreeModel getTree(int catalogID, int type, int aspID,
                                       int orgID, int catalogType) throws CourseSysException
        {
                MasterTreeModel l;

                l = dao.getTree(catalogID, type, aspID, orgID, catalogType);

                return l;
        }

        /**
         * @param catalogID
         * @throws com.ulearning.ulms.course.exceptions.CourseSysException
         *
         */
        public MasterTreeModel getTree(int catalogID, int type)
                throws CourseSysException
        {
                MasterTreeModel l;

                l = dao.getTree(catalogID, type);

                return l;
        }

        /**
         * @param catalogID
         * @throws com.ulearning.ulms.course.exceptions.CourseSysException
         *
         */
        public MasterTreeModel getTree(int catalogID) throws CourseSysException
        {
                MasterTreeModel l;

                l = dao.getTree(catalogID);

                return l;
        }

        /**
         * @param value
         * @throws com.ulearning.ulms.course.exceptions.CourseSysException
         *
         */
        public void updateCatalog(CatalogModel value) throws CourseSysException
        {
                dao.updateCatalog(value);
        }

        /**
         * @param value
         * @throws com.ulearning.ulms.course.exceptions.CourseSysException
         *
         */
        public void updateMaster(MasterModel value)
                throws CourseSysException, CourseAppException
        {
                dao.updateMaster(value);
        }

        /**
         * search the master according to the keywords.
         *
         * @param masterKey
         * @throws com.ulearning.ulms.course.exceptions.CourseSysException
         *
         */
        public MasterListModel search(String masterKey) throws CourseSysException
        {
                return dao.search(masterKey);
        }

        /**
         * Search the master according to the keywords and master type
         *
         * @param masterKey
         * @param masterType please see CourseKeys
         * @return
         * @throws CourseSysException
         */
        public MasterListModel search(String masterKey, int masterType)
                throws CourseSysException
        {
                return dao.search(masterKey, masterType);
        }

        public MasterListModel search(String masterKey, int masterType, int aspID)
                throws CourseSysException
        {
                return dao.search(masterKey, masterType, aspID, -1);
        }

        public MasterListModel search(String masterKey, int masterType, int aspID,
                                      int orgID) throws CourseSysException
        {
                return dao.search(masterKey, masterType, aspID, orgID);
        }

        /*
        * 判断在所在目录下，该目录名是否已存在
        */
        public boolean isExistCatalog(String thisCatalogName, int thisCatalogID,
                                      int catalogID, int catType, int aspID) throws CourseSysException
        {
                return dao.isExistCatalog(thisCatalogName, thisCatalogID, catalogID,
                        catType, aspID, -1);
        }

        public boolean isExistCatalog(String thisCatalogName, int thisCatalogID,
                                      int catalogID, int catType, int aspID, int orgID)
                throws CourseSysException
        {
                return dao.isExistCatalog(thisCatalogName, thisCatalogID, catalogID,
                        catType, aspID, orgID);
        }

        public List getMasterInfo(String CourseName, String[] catalogIDs,
                                  String startDate, String endDate, int aspID) throws CourseSysException
        {
                return dao.getMasterInfo(CourseName, catalogIDs, startDate, endDate,
                        aspID, -1);
        }

        public List getMasterInfo(String CourseName, String[] catalogIDs,
                                  String startDate, String endDate, int aspID, int orgID)
                throws CourseSysException
        {
                return dao.getMasterInfo(CourseName, catalogIDs, startDate, endDate,
                        aspID, orgID);
        }

        public List getCourseFromMaster(int masterID, int type)
                throws CourseSysException
        {
                return dao.getCourseFromMaster(masterID, type);
        }
}
