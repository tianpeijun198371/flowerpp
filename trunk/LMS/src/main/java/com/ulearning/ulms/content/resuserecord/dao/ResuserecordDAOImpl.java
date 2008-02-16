/**
 * Copyright (c) 2000-2005.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.content.resuserecord.dao;

import com.ulearning.ulms.content.resuserecord.exceptions.ResuserecordDAOSysException;
import com.ulearning.ulms.content.resuserecord.form.ResuserecordForm;
import com.ulearning.ulms.content.resuserecord.model.ResuserecordModel;
import com.ulearning.ulms.core.exceptions.ULMSSysException;
import com.ulearning.ulms.core.util.DateTimeUtil;
import com.ulearning.ulms.core.util.StringUtil;
import com.ulearning.ulms.util.HibernateDAO;
import com.ulearning.ulms.util.HibernateUtil;

import net.sf.hibernate.Query;
import net.sf.hibernate.Session;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * Class description goes here.
 * <p/>
 * Created by Auto Code Produce System
 * User: xiejh
 * Date: 20060317
 * Time: 103906
 */
public class ResuserecordDAOImpl implements ResuserecordDAO
{
        public Serializable insertResuserecord(ResuserecordForm details)
                throws ResuserecordDAOSysException
        {
                Serializable s = null;

                try
                {
                        s = HibernateDAO.add(details.getResuserecordModel());
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new ResuserecordDAOSysException("" + e);
                }

                return s;
        }

        public void updateResuserecord(ResuserecordForm details)
                throws ResuserecordDAOSysException
        {
                try
                {
                        HibernateDAO.update(details.getResuserecordModel());
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new ResuserecordDAOSysException("" + e);
                }
        }

        /**
         * Remove the Resuserecord from database by the RESUSEID
         *
         * @param RESUSEID
         * @throws ResuserecordDAOSysException
         */
        public void deleteResuserecord(int pkID) throws ResuserecordDAOSysException
        {
                String hql = " from ResuserecordModel where RESUSEID = " + pkID;

                try
                {
                        HibernateDAO.delete(hql);
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new ResuserecordDAOSysException("" + e);
                }
        }

        /**
         * Get the Resuserecord info via the unique RESUSEID
         *
         * @param RESUSEID
         * @return the prepared ResuserecordForm, default is null
         * @throws ResuserecordDAOSysException
         */
        public ResuserecordForm getResuserecord(int pkID)
                throws ResuserecordDAOSysException
        {
                ResuserecordForm bf = new ResuserecordForm();
                ResuserecordForm res = null;
                List tmList = null;
                String hql = " from ResuserecordModel where RESUSEID = " + pkID;

                try
                {
                        tmList = HibernateDAO.find(hql);
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new ResuserecordDAOSysException("" + e);
                }

                if ((tmList != null) && (tmList.size() > 0))
                {
                        ResuserecordModel bm = (ResuserecordModel) tmList.get(0);
                        res = bf.getResuserecordForm(bm);
                }

                return res;
        }

        /**
         * Get the Resuserecord list by the catalogID
         *
         * @param type relationID
         * @return The prepared arraylist object,default size is 0
         * @throws ResuserecordDAOSysException
         */
        public List getResuserecordList() throws ResuserecordDAOSysException
        {
                ResuserecordForm bf = new ResuserecordForm();
                ResuserecordModel bm = null;
                ArrayList ResuserecordList = new ArrayList();
                List tmList = null;
                String hql = " from ResuserecordModel order by resID";

                try
                {
                        tmList = HibernateDAO.find(hql);
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new ResuserecordDAOSysException("" + e);
                }

                for (int i = 0; i < tmList.size(); i++)
                {
                        bm = (ResuserecordModel) tmList.get(i);
                        ResuserecordList.add(bf.getResuserecordForm(bm));
                }

                return ResuserecordList;
        }

        public boolean getResListWeek(String Bday, String Eday, String resID)
                throws ResuserecordDAOSysException
        {
                ResuserecordForm bf = new ResuserecordForm();
                ResuserecordModel bm = null;
                ArrayList ResuserecordList = new ArrayList();
                List tmList = null;

                String[] tmp = StringUtil.splitString(Bday, "-");
                Date nowtime = DateTimeUtil.toDate(tmp[1], tmp[2], tmp[0], tmp[3],
                        tmp[4], "0");
                String[] tmp2 = StringUtil.splitString(Eday, "-");
                Date nowtime2 = DateTimeUtil.toDate(tmp2[1], tmp2[2], tmp2[0], tmp[3],
                        tmp[4], "0");

                String hql = " from ResuserecordModel where userdel=0 and audittag=1 and ((userbegindate>=:beginDate and userenddate<=:endDate)" +
                        " or (userbegindate>=:beginDate and userenddate>=:endDate and userbegindate<=:endDate) " +
                        "or (userbegindate<=:beginDate and userenddate<=:endDate and userenddate>=:beginDate) " +
                        "or (userbegindate<=:beginDate and userenddate>=:endDate)) and resID =" +
                        resID + " order by resID";

                try
                {
                        //tmList = HibernateDAO.find(hql);
                        Session session = HibernateUtil.getSession();
                        Query query = session.createQuery(hql);
                        query.setParameter("beginDate", nowtime);
                        query.setParameter("endDate", nowtime2);
                        tmList = query.list();
                        session.flush();
                        session.connection().commit();
                        session.close();
                }
                catch (Exception e)
                {
                        //session.close();
                        //e.printStackTrace();
                        //throw new ResuserecordDAOSysException("" + e);
                }

                //System.out.println(tmList.size());
                if (tmList.size() == 0)
                {
                        return true;
                }
                else
                {
                        return false;
                }

                /*for (int i = 0; i < tmList.size(); i++)
               {
                       bm = (ResuserecordModel) tmList.get(i);
                       ResuserecordList.add(bf.getResuserecordForm(bm));
               }
               return ResuserecordList;*/
        }

        public List getResListWeek(String week) throws ResuserecordDAOSysException
        {
                ResuserecordForm bf = new ResuserecordForm();
                ResuserecordModel bm = null;
                ArrayList ResuserecordList = new ArrayList();
                List tmList = null;

                String[] tmp = StringUtil.splitString(week, "-");
                Date nowtime = DateTimeUtil.toDate(tmp[1], tmp[2], tmp[0], "0", "0", "0");
                String endtime = DateTimeUtil.addDateTime(nowtime, "D", 7);
                String[] tmp2 = StringUtil.splitString(endtime, "-");
                Date nowtime2 = DateTimeUtil.toDate(tmp2[1], tmp2[2].substring(0, 2),
                        tmp2[0], "0", "0", "0");

                String hql = " from ResuserecordModel where userdel=0 and audittag=1 and (userbegindate>=:beginDate and userenddate<=:endDate)" +
                        " or (userbegindate>=:beginDate and userenddate>=:endDate and userbegindate<=:endDate) " +
                        "or (userbegindate<=:beginDate and userenddate<=:endDate and userenddate>=:beginDate) " +
                        "or (userbegindate<=:beginDate and userenddate>=:endDate) order by resID";

                try
                {
                        //tmList = HibernateDAO.find(hql);
                        Session session = HibernateUtil.getSession();
                        Query query = session.createQuery(hql);
                        query.setParameter("beginDate", nowtime);
                        query.setParameter("endDate", nowtime2);
                        tmList = query.list();
                        session.flush();
                        session.connection().commit();
                        session.close();
                }
                catch (Exception e)
                {
                        //session.close();
                        //e.printStackTrace();
                        //throw new ResuserecordDAOSysException("" + e);
                }

                //System.out.println("================================"+tmList);
                if (tmList.size() == 0)
                {
                }
                else
                {
                        for (int i = 0; i < tmList.size(); i++)
                        {
                                bm = (ResuserecordModel) tmList.get(i);
                                ResuserecordList.add(bf.getResuserecordForm(bm));
                        }
                }

                return ResuserecordList;
        }

        public static void main(String[] args) throws Exception
        {
                ResuserecordDAOImpl aa = new ResuserecordDAOImpl();
                boolean bb = aa.getResListWeek("2006-05-07-00-00", "2006-05-09-12-00",
                        "22");
                System.out.println(bb);
        }
}
