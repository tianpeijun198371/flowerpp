/**
 * ContentTreeHelper.java.
 * User: Fengch  Date: 2005-6-25 17:02:15
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.content.helper;

import com.ulearning.ulms.content.dao.ContentManageDAO;
import com.ulearning.ulms.content.dao.ContentManageDAOFactory;
import com.ulearning.ulms.content.dao.ContentTreeDAO;
import com.ulearning.ulms.content.dao.ContentTreeDAOFactory;
import com.ulearning.ulms.content.exceptions.ContentManageSysException;
import com.ulearning.ulms.content.form.ContentCatalogForm;
import com.ulearning.ulms.content.form.ContentForm;
import com.ulearning.ulms.content.model.ContentCatalogModel;
import com.ulearning.ulms.content.model.ContentConfigModel;
import com.ulearning.ulms.content.model.ContentModel;
import com.ulearning.ulms.content.model.ContentTreeModel;
import com.ulearning.ulms.content.util.ContentManageConstants;
import com.ulearning.ulms.course.model.Course;
import com.ulearning.ulms.util.HibernateUtil;
import com.ulearning.ulms.core.util.Config;

import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Query;
import net.sf.hibernate.Session;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class ContentTreeHelper
{
        private static ContentTreeDAO contentTreeDAO;

        static
        {
                try
                {
                        contentTreeDAO = ContentTreeDAOFactory.getDAO();
                }
                catch (Exception ex)
                {
                        ex.printStackTrace();
                }
        }

        /**
         * return the catalog's path list.
         * <br>��Ŀ¼�е���ʱʹ�ô˷�����λ��
         *
         * @param catalogID
         * @param relationID
         * @param type
         * @param aspID
         * @return
         * @throws ContentManageSysException
         */
        public static List getCatalogPath(int catalogID, int relationID, int type,
                                          String aspID) throws ContentManageSysException
        {
                return contentTreeDAO.getCatalogPath(catalogID, relationID, type, aspID);
        }

        /**
         * ���ظ�Ŀ¼��һ�����������ݣ�����Ŀ¼�����ݣ���
         *
         * @param catalogID
         * @param pageNo
         * @param pageSize
         * @return
         * @throws ContentManageSysException
         */
        public static ContentTreeModel getTree(int catalogID, int relationID,
                                               int type, int pageNo, int pageSize, String aspID, boolean isAdmin)
                throws ContentManageSysException
        {
                if ((type == ContentManageConstants.PUBLIC_CONTENT_TYPE) &&
                        (catalogID == 0))
                {
                        ContentTreeModel ctm = contentTreeDAO.getTree(catalogID,
                                relationID, type, pageNo, pageSize, aspID, isAdmin);
                        List catlogList = ctm.getContentCatalogs();
                        int j = 0;
                        
                        //����������Ŀ����Ҫ���� δ��֤��Դ
                        if (!Config.isXLNProject())
                        {
                                for (int i = 0; i < catlogList.size(); i++)
                                {
                                        ContentCatalogModel ccm = (ContentCatalogModel) catlogList.get(i);

                                        if (ccm.getTitle().equals("δ��֤��Դ"))
                                        {
                                                j = 1;

                                                break;
                                        }
                                }

                                if (j == 0) //û��δ��֤��Դ
                                {
                                        ContentCatalogForm cf = new ContentCatalogForm();
                                        cf.setTitle("δ��֤��Դ");
                                        cf.setParentID(0);
                                        cf.setType(String.valueOf(
                                                ContentManageConstants.PUBLIC_CONTENT_TYPE));
                                        cf.setRelationID(relationID);
                                        cf.setRemark3(String.valueOf(relationID));
                                        ContentManageHelper.addContentCatalog(cf);
                                }
                        }
                }

                return contentTreeDAO.getTree(catalogID, relationID, type, pageNo,
                        pageSize, aspID, isAdmin);
        }

        /**
         * ���ظ�Ŀ¼��һ�����������ݣ�����Ŀ¼�����ݣ���
         *
         * @param catalogID
         * @param pageNo
         * @param pageSize
         * @param aspID
         * @return
         * @throws ContentManageSysException
         */
        public static ContentTreeModel getTree(int catalogID, int relationID,
                                               int type, int pageNo, int pageSize, String aspID)
                throws ContentManageSysException
        {
                if ((type == ContentManageConstants.PUBLIC_CONTENT_TYPE) &&
                        (catalogID == 0))
                {
                        ContentTreeModel ctm = contentTreeDAO.getTree(catalogID,
                                relationID, type, pageNo, pageSize, aspID);
                        List catlogList = ctm.getContentCatalogs();
                        int j = 0;

                        //����������Ŀ����Ҫ���� δ��֤��Դ
                        if (!Config.isXLNProject())
                        {
                                for (int i = 0; i < catlogList.size(); i++)
                                {
                                        ContentCatalogModel ccm = (ContentCatalogModel) catlogList.get(i);

                                        if (ccm.getTitle().equals("δ��֤��Դ"))
                                        {
                                                j = 1;

                                                break;
                                        }
                                }

                                if (j == 0) //û��δ��֤��Դ
                                {
                                        ContentCatalogForm cf = new ContentCatalogForm();
                                        cf.setTitle("δ��֤��Դ");
                                        cf.setParentID(0);
                                        cf.setType(String.valueOf(
                                                ContentManageConstants.PUBLIC_CONTENT_TYPE));
                                        cf.setRelationID(relationID);
                                        cf.setRemark3(String.valueOf(relationID));
                                        ContentManageHelper.addContentCatalog(cf);
                                }
                        }
                }

                return contentTreeDAO.getTree(catalogID, relationID, type, pageNo,
                        pageSize, aspID);
        }

        /**
         * ���ظ�Ŀ¼��һ����Ŀ¼����
         *
         * @param catalogID
         * @param relationID
         * @param type
         * @param aspID
         * @return
         * @throws ContentManageSysException
         */
        public static List getSubCatalog(int catalogID, int relationID, int type,
                                         String aspID) throws ContentManageSysException
        {
                return contentTreeDAO.getSubCatalog(catalogID, relationID, type, aspID);
        }

        /**
         * �ж����Ŀ¼�Ƿ񻹰�����Ŀ¼
         *
         * @param catalogID
         * @param aspID
         * @return
         * @throws ContentManageSysException
         */
        public static boolean isHasSubCatalog(int catalogID, int relationID,
                                              int type, String aspID) throws ContentManageSysException
        {
                return contentTreeDAO.isHasSubCatalog(catalogID, relationID, type, aspID);
        }

        /**
         * ��ʼ���ÿγ���Դ��Ŀ¼(������ʦ����ѧ��١����Դ�١��μ����γ̽��塢�����������̲ļ��ο�����).
         * <br>����Ҫ��ʼ����ЩĿ¼,������:ContentManageConstants.INIT_COURSE_CONTENT_CATALOG .
         * todo:shid
         *
         * @param courseID
         * @throws ContentManageSysException
         */
        public static void initCourseContentTree(int courseID)
                throws ContentManageSysException
        {
                /*                String[] title = ContentManageConstants.INIT_COURSE_CONTENT_CATALOG;
                int relationID = courseID;
                int parentID = 0;
                int type = ContentManageConstants.COURSE_CONTENT_TYPE;
                List lt = new ArrayList();
                String sql = "from Course c where c.courseid="+courseID;
                Session session = null;
                String coursecode = null;
                try
                {
                        session = HibernateUtil.getSession();
                        Query q = session.createQuery(sql);
                        lt = q.list();
                }
                catch (HibernateException he)
                {
                        throw new ContentManageSysException(he);
                }
                for(int i=0;i<lt.size();i++)
                {
                        Course c = (Course) lt.get(i);
                        coursecode = c.getCoursecode().trim();
                }
                List courseCatalogList = contentTreeDAO.getSubCatalog(0, relationID, type);
                List courseTitleList = new ArrayList();
                for (int i = 0; i < courseCatalogList.size(); i++)
                {
                        ContentCatalogModel ccm = (ContentCatalogModel) courseCatalogList.get(i);
                        courseTitleList.add(ccm.getTitle());
                }
                for (int i = 0; i < title.length; i++)
                {
                        String titleTemp = title[i];
                        if (!courseTitleList.contains(titleTemp))
                        {
                                ContentCatalogForm ccm = new ContentCatalogForm();
                                ccm.setCreateDate(new Date());
                                ccm.setLastModDate(new Date());
                                ccm.setParentID(parentID);
                                ccm.setRelationID(relationID);
                                ccm.setType(String.valueOf(type));
                                ccm.setTitle(titleTemp);
                                ccm.setRemark(coursecode);
                                ContentManageHelper.addContentCatalog(ccm);
                                courseTitleList.add(titleTemp);
                        }
                }*/
        }

        /**
         * �������и�����Դ��ռ�ܹ�������Դ�ܿռ�İٷֱ� .
         * todo:shid
         *
         * @param userID
         * @throws ContentManageSysException
         */
        public static float getPersonalContentPercent(int userID, String aspID)
                throws ContentManageSysException
        {
                long size = 0;

                int relationID = userID;
                int parentID = 0;
                int type = ContentManageConstants.PERSONAL_CONTENT_TYPE;

                size = sumCatalogContent(parentID, relationID, type, size, aspID);

                ContentConfigModel ccm = ContentManageHelper.getContentConfig(aspID);
                int total = Integer.parseInt(ccm.getLimitSpaceSize()) * 1000 * 1000;
                float percent = (float) size / total;

                return percent;
        }

        /**
         * ������catalogID,relationID,typeΪ���ڵ�����µ��������ݸ�����С���ܺ�
         *
         * @param catalogID
         * @param relationID
         * @param type
         * @param sum
         * @param aspID
         * @return
         */
        public static long sumCatalogContent(int catalogID, int relationID,
                                             int type, long sum, String aspID)
        {
                long l = contentTreeDAO.sumSubCatalogContent(catalogID, relationID,
                        type, aspID);
                sum = sum + l;

                List catalogList = getSubCatalog(catalogID, relationID, type, aspID);

                for (int i = 0; i < catalogList.size(); i++)
                {
                        ContentCatalogModel ccm = (ContentCatalogModel) catalogList.get(i);
                        int id = ccm.getContentCatalogID();

                        sum = sumCatalogContent(id, relationID, type, sum, aspID);
                }

                return sum;
        }

        /**
         * ����
         *
         * @param key
         * @param pageNo
         * @param pageSize
         * @param aspID
         * @return
         * @throws ContentManageSysException
         */
        public static ContentTreeModel search(String key, int relationID, int type,
                                              int pageNo, int pageSize, boolean isAdmin, String aspID)
                throws ContentManageSysException
        {
                return contentTreeDAO.search(key, relationID, type, pageNo, pageSize,
                        isAdmin, aspID);
        }

        public static ContentTreeModel searchCourse(String key, int type,
                                                    int pageNo, int pageSize, boolean isAdmin, String aspID)
                throws ContentManageSysException
        {
                return contentTreeDAO.searchCourse(key, type, pageNo, pageSize,
                        isAdmin, aspID);
        }
}
