/**
 * SchoolWorkDAOImpl.java
 * Created by Yud
 * Date 2005.04.11
 * Copyright (c) 2000-2005.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.tools.schoolwork.dao;

import com.ulearning.ulms.core.exceptions.ULMSSysException;
import com.ulearning.ulms.core.util.DateTimeUtil;
import com.ulearning.ulms.tools.schoolwork.exceptions.SchoolWorkSysException;
import com.ulearning.ulms.tools.schoolwork.model.SchoolWorkModel;
import com.ulearning.ulms.tools.schoolwork.form.SchoolWorkForm;
//import com.ulearning.ulms.tools.newdocument.exceptions.NewDocumentSysException;
import com.ulearning.ulms.util.HibernateDAO;
import com.ulearning.ulms.util.HibernateUtil;
import com.ulearning.ulms.util.log.DebugUtil;
import com.ulearning.ulms.util.log.LogUtil;

import java.util.List;
import java.util.ArrayList;
import java.util.Date;

import net.sf.hibernate.Session;
import net.sf.hibernate.Query;
import net.sf.hibernate.HibernateException;

public class SchoolWorkDAOImpl implements SchoolWorkDAO
{
        /**
         * @param swID
         * @return
         * @throws SchoolWorkSysException
         */
        public SchoolWorkModel getSchoolModel(int swID) throws SchoolWorkSysException
        {
                SchoolWorkModel schoolWorkModel = null;
                List clList = null;
                String sql_str = "from SchoolWorkModel where swID = " + swID;

                try
                {
                        clList = HibernateDAO.find(sql_str);

                }
                catch (ULMSSysException e)
                {
                        throw new SchoolWorkSysException(e);
                }
                for (int i = 0; i < clList.size(); i++)
                {
                        schoolWorkModel = (SchoolWorkModel) clList.get(i);
                }
                return schoolWorkModel;
        }

        /**
         * @param relationID
         * @param type
         * @return
         * @throws SchoolWorkSysException
         */
        public List getSchoolList(int relationID, int type)
                throws SchoolWorkSysException
        {
                List tmList = null;
                String sql_str = "from SchoolWorkModel where relationID=" + relationID
                        + " and type='" + type + "'";
                try
                {
                        tmList = HibernateDAO.find(sql_str);
                }
                catch (ULMSSysException e)
                {
                        throw new SchoolWorkSysException(e);
                }
                return tmList;
        }

        /**
         * get schoolwork list by date
         *
         * @param relationID
         * @param type
         * @return
         * @throws SchoolWorkSysException
         */
        public List getSchoolListFromDate(int relationID, int type)
                throws SchoolWorkSysException
        {
                List tmList = null;
                Session session = null;
                List newdocumentList = new ArrayList();
                String hql = "from SchoolWorkModel where relationID=" + relationID
                        + " and type='" + type + "'";
                try
                {
                        session = HibernateUtil.getSession();

                        String visitSQL = "";
                        if (true)
                        {
                                visitSQL = " and ("
                                        + "(displayBeginDate IS NULL and displayEndDate IS NULL)"
                                        + "or (displayBeginDate IS NULL and displayEndDate IS NOT NULL and "
                                        + "displayEndDate >=:now_day)"
                                        + "or (displayBeginDate IS NOT NULL and displayBeginDate<=:now_day and displayEndDate IS NULL)"
                                        + "or (displayBeginDate IS NOT NULL and displayBeginDate<=:now_day and displayEndDate IS NOT NULL and "
                                        + " displayEndDate >=:now_day)"
                                        + " )";
                        }
                        String dateConditions = "";
                        hql = hql + visitSQL + dateConditions + " order by createDate desc";
                        Query q = session.createQuery(hql);
                        if (true)
                        {
                                Date d = new Date();
                                q.setDate("now_day", d);
                        }
                        tmList = q.list();
                }
                catch (HibernateException he)
                {
                        throw new SchoolWorkSysException("HibernateException while getSchoolWorkFormList: \n" + he);
                }
                try
                {
                        HibernateUtil.releaseSession(session);
                }
                catch (HibernateException he)
                {
                        throw new SchoolWorkSysException("HibernateException while getSchoolWorkFormList: \n" + he);
                }
                SchoolWorkModel am = null;
                SchoolWorkForm at = new SchoolWorkForm();
                for (int i = 0; i < tmList.size(); i++)
                {
                        am = (SchoolWorkModel) tmList.get(i);
                        newdocumentList.add(at.getSchoolWorkForm(am));
                }
                return newdocumentList;
        }


        public boolean getSchoolAnswerFromDate(int swID)
                throws SchoolWorkSysException
        {
                List tmList = null;
                Session session = null;
                String hql = "from SchoolWorkModel where swID = " + swID;
                try
                {
                        session = HibernateUtil.getSession();

                        String visitSQL = "";
                        Date date = new Date();
                        if (true)
                        {
                                visitSQL = " and("
                                        + "(remark3 IS NULL and remark4 IS NULL)"
                                        + "or (remark3 IS NULL and remark4 IS NOT NULL and remark4 >='" + DateTimeUtil.format(date, "yyyy-MM-dd") + "')"
                                        + "or (remark3 IS NOT NULL and remark3<='" + DateTimeUtil.format(date, "yyyy-MM-dd") + "' and remark4 IS NULL)"
                                        + "or (remark3 IS NOT NULL and remark3<='" + DateTimeUtil.format(date, "yyyy-MM-dd") + "' and remark4 IS NOT NULL and remark4 >='" + DateTimeUtil.format(date, "yyyy-MM-dd") + "')"
                                        + " )";
                        }

                        //hql = hql + visitSQL ;
                        LogUtil.info("schoolwork", "SchoolWorkDAOImpl=======sql=" + hql);
//                        System.out.println("sql="+hql);
                        Query q = session.createQuery(hql);
//                        if (true)
//                        {
//                                Date d = new Date();
//                                q.setParameter("now_day",DateTimeUtil.format(d,"yyyy-MM-dd"));
//                        }
                        tmList = q.list();
                }
                catch (HibernateException he)
                {
                        throw new SchoolWorkSysException("HibernateException while getSchoolWorkFormList: \n" + he);
                }
                try
                {
                        HibernateUtil.releaseSession(session);
                }
                catch (HibernateException he)
                {
                        throw new SchoolWorkSysException("HibernateException while getSchoolWorkFormList: \n" + he);
                }
                if (tmList.size() != 0)
                {
                        return true;
                }
                else
                {
                        return false;
                }
        }

        /**
         * @param addSchoolWorkModel
         * @throws SchoolWorkSysException
         */
        public void insertSchoolWork(SchoolWorkModel addSchoolWorkModel) throws SchoolWorkSysException
        {
                try
                {
                        HibernateDAO.add(addSchoolWorkModel);
                }
                catch (ULMSSysException e)
                {
                        throw new SchoolWorkSysException(e);
                }
        }

        /**
         * @param updSchoolWorkModel
         * @throws SchoolWorkSysException
         */
        public void updateSchoolWork(SchoolWorkModel updSchoolWorkModel) throws SchoolWorkSysException
        {
                try
                {
                        HibernateDAO.update(updSchoolWorkModel);
                }
                catch (ULMSSysException e)
                {
                        throw new SchoolWorkSysException(e);
                }
        }

        /**
         * @param l
         * @throws SchoolWorkSysException
         */
        public void deleteSchoolWork(List l) throws SchoolWorkSysException
        {
                String sql_str = "";
                if (l.size() > 0)
                {
                        sql_str = " from SchoolWorkModel where swID = " + (Integer) l.get(0);
                        for (int i = 1; i < l.size(); i++)
                        {
                                sql_str += " or swID = " + (Integer) l.get(i) + "";
                        }
                }

                try
                {
                        DebugUtil.print("[Debug ]  = " + sql_str);
                        HibernateDAO.delete(sql_str);
                }
                catch (ULMSSysException e)
                {
                        throw new SchoolWorkSysException(e);
                }
        }
}
