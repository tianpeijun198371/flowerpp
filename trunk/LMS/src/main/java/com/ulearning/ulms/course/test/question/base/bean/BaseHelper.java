/** * BaseHelper.java.
 * Base: xiejh  Date: 2004-4-22 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.course.test.question.base.bean;

import com.ulearning.ulms.core.exceptions.ULMSSysException;
import com.ulearning.ulms.course.test.question.base.dao.BaseDAO;
import com.ulearning.ulms.course.test.question.base.dao.BaseDAOFactory;
import com.ulearning.ulms.course.test.question.base.exceptions.BaseDAOSysException;
import com.ulearning.ulms.course.test.question.base.form.BaseForm;
import com.ulearning.ulms.course.test.question.base.model.QuestionModel;
import com.ulearning.ulms.util.HibernateDAO;

import java.io.IOException;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.jsp.JspWriter;


public class BaseHelper
{
        /**
         * Wrapping the get base method for JSP and  the other modules
         *
         * @param questionID
         * @return the base modle according to the baseID
         */
        public BaseForm getBase(int questionID)
        {
                BaseForm bf = null;

                try
                {
                        BaseDAO baseDao = BaseDAOFactory.getDAO();
                        bf = baseDao.getBase(questionID);
                }
                catch (BaseDAOSysException udse)
                {
                        udse.printStackTrace();
                }

                return bf;
        }

        /**
         * Wrapping the get baseList method for JSP and  the other modules
         *
         * @param courseID parentID
         * @return the base list according to the catalog
         */
        public List getBaseList(int courseID, int parentID)
        {
                List baseList = null;

                try
                {
                        BaseDAO baseDao = BaseDAOFactory.getDAO();
                        baseList = baseDao.getBaseList(courseID, parentID);
                }
                catch (BaseDAOSysException udse)
                {
                        udse.printStackTrace();
                }

                return baseList;
        }

        /**
         * 得到题库列表
         *
         * @param courseID parentID
         * @return the base list according to the catalog
         */
        public List getBaseListExam(int courseID, int parentID)
        {
                List baseList = null;

                try
                {
                        BaseDAO baseDao = BaseDAOFactory.getDAO();
                        baseList = baseDao.getBaseListExam(courseID, parentID);
                }
                catch (BaseDAOSysException udse)
                {
                        udse.printStackTrace();
                }

                return baseList;
        }

        /**
         * 生成A卷
         *
         * @param questionID
         */
        public List getBaseAList(List questionID)
        {
                List baseList = null;

                try
                {
                        BaseDAO baseDao = BaseDAOFactory.getDAO();
                        baseList = baseDao.getBaseAList(questionID);
                }
                catch (BaseDAOSysException udse)
                {
                        udse.printStackTrace();
                }

                return baseList;
        }

        public List getBaseList(List questionID)
        {
                List baseList = null;

                try
                {
                        BaseDAO baseDao = BaseDAOFactory.getDAO();
                        baseList = baseDao.getBaseList(questionID);
                }
                catch (BaseDAOSysException udse)
                {
                        udse.printStackTrace();
                }

                return baseList;
        }

        /**
         * 得到题库目录列表
         *
         * @param courseID
         * @return
         * @throws BaseDAOSysException
         */
        public static List getBaseListExamFloder(int courseID)
                throws BaseDAOSysException
        {
                List baseFloderList = null;

                try
                {
                        BaseDAO baseDao = BaseDAOFactory.getDAO();
                        baseFloderList = baseDao.getBaseListExamFloder(courseID);
                }
                catch (BaseDAOSysException udse)
                {
                        udse.printStackTrace();
                }

                return baseFloderList;
        }

        public static List getBaseListExam(int courseID) throws BaseDAOSysException
        {
                List baseFloderList = null;

                try
                {
                        BaseDAO baseDao = BaseDAOFactory.getDAO();
                        baseFloderList = baseDao.getBaseListExam(courseID);
                }
                catch (BaseDAOSysException udse)
                {
                        udse.printStackTrace();
                }

                return baseFloderList;
        }

        public static List getBaseListExamChapter(int courseID)
                throws BaseDAOSysException
        {
                List baseFloderList = null;

                try
                {
                        BaseDAO baseDao = BaseDAOFactory.getDAO();
                        baseFloderList = baseDao.getBaseListExamChapter(courseID);
                }
                catch (BaseDAOSysException udse)
                {
                        udse.printStackTrace();
                }

                return baseFloderList;
        }

        public static List getBaseListExamPoint(int courseID)
                throws BaseDAOSysException
        {
                List baseFloderList = null;

                try
                {
                        BaseDAO baseDao = BaseDAOFactory.getDAO();
                        baseFloderList = baseDao.getBaseListExamPoint(courseID);
                }
                catch (BaseDAOSysException udse)
                {
                        udse.printStackTrace();
                }

                return baseFloderList;
        }

        public List searchBase(int courseID, int parentID, String type,
                               String hardLevel, String scope, String[] chapter, String[] point,
                               String[] key)
        {
                List baseList = null;

                try
                {
                        BaseDAO baseDao = BaseDAOFactory.getDAO();
                        baseList = baseDao.searchBase(courseID, parentID, type, hardLevel,
                                scope, chapter, point, key);
                }
                catch (BaseDAOSysException udse)
                {
                        udse.printStackTrace();
                }

                return baseList;
        }

        public List searchBase(int courseID, int parentID, String chapter,
                               String point, int hardLevel, String type, String strscope)
        {
                List baseList = null;

                try
                {
                        BaseDAO baseDao = BaseDAOFactory.getDAO();
                        baseList = baseDao.searchBase(courseID, parentID, chapter, point,
                                hardLevel, type, strscope);
                }
                catch (BaseDAOSysException udse)
                {
                        udse.printStackTrace();
                }

                return baseList;
        }

        //获得下一级目录
        public static List getSubFolder(int courseID, int parentID)
                throws BaseDAOSysException
        {
                BaseForm bf = new BaseForm();
                QuestionModel bm = null;
                ArrayList baseList = new ArrayList();
                List tmList = null;
                String hql = " from QuestionModel " + " where ParentID=" + parentID +
                        " and CourseID=" + courseID + " and type='0'" +
                        " order by isContent,QuestionID desc";

                try
                {
                        tmList = HibernateDAO.find(hql);
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new BaseDAOSysException("" + e);
                }

                for (int i = 0; i < tmList.size(); i++)
                {
                        bm = (QuestionModel) tmList.get(i);
                        baseList.add(bf.getBaseForm(bm));
                }

                return baseList;
        }

        /*        public static String getIndent(int i)
        throws IOException
        {
                StringBuffer sb=new StringBuffer();
                for (int ji = 0; ji < i-1; ji++)
                {
                        sb.append("&nbsp;&nbsp;");
                }
                return sb.toString();
        }
        public static void printTree(int level,int courseID,int parentID) throws IOException
        {
                List lt = BaseHelper.getSubFolder(courseID,parentID);
                for(int i=0;i<lt.size();i++)
                {
                        BaseForm bf = (BaseForm) lt.get(i);
                        System.out.println("level = " + level);
                        System.out.println("<option value="+bf.getQuestionID()+">"+getIndent(level)+bf.getTitle()+"</option>");
                        printTree(level+1,courseID,bf.getQuestionID());
                }
        }*/
}
