/**
 * Copyright (c) 2000-2005.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.content.uteachergroup.dao;

import com.ulearning.ulms.content.uteachergroup.exceptions.UTeacherGroupDAOSysException;
import com.ulearning.ulms.content.uteachergroup.form.UTeacherGroupForm;
import com.ulearning.ulms.content.uteachergroup.model.UTeacherGroupModel;
import com.ulearning.ulms.core.exceptions.ULMSSysException;
import com.ulearning.ulms.util.HibernateDAO;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;


/**
 * Class description goes here.
 * <p/>
 * Created by Auto Code Produce System
 * User: xiejh
 * Date: 20060317
 * Time: 103906
 */
public class UTeacherGroupDAOImpl implements UTeacherGroupDAO
{
        public Serializable insertUTeacherGroup(UTeacherGroupForm details)
                throws UTeacherGroupDAOSysException
        {
                Serializable s = null;

                try
                {
                        s = HibernateDAO.add(details.getUTeacherGroupModel());
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new UTeacherGroupDAOSysException("" + e);
                }

                return s;
        }

        public void updateUTeacherGroup(UTeacherGroupForm details)
                throws UTeacherGroupDAOSysException
        {
                try
                {
                        HibernateDAO.update(details.getUTeacherGroupModel());
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new UTeacherGroupDAOSysException("" + e);
                }
        }

        /**
         * Remove the UTeacherGroup from database by the U_TEACHGROUP_ID
         *
         * @param
         * @throws UTeacherGroupDAOSysException
         */
        public void deleteUTeacherGroup(int pkID)
                throws UTeacherGroupDAOSysException
        {
                String hql = " from UTeacherGroupModel where U_TEACHGROUP_ID = " +
                        pkID;

                try
                {
                        HibernateDAO.delete(hql);
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new UTeacherGroupDAOSysException("" + e);
                }
        }

        /**
         * 根据userId删除师资组中的师资信息
         */
        public void deletebyUserId(int userId) throws UTeacherGroupDAOSysException
        {
                String hql = " from UTeacherGroupModel where userID=" + userId;

                try
                {
                        UTeacherGroupForm form = (UTeacherGroupForm) findbyUserId(userId)
                                .get(0);
                        deleteUTeacherGroup(form.getUteachgroupID());
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new UTeacherGroupDAOSysException("" + e);
                }
        }

        public static void main(String[] args)
        {
                UTeacherGroupDAOImpl imp = new UTeacherGroupDAOImpl();
                imp.deletebyUserId(401);

                //UTeacherGroupForm uf = new UTeacherGroupForm();
                //uf.setG_Ship_ID(35);
                //uf.setUserID(389);
                //imp.insertUTeacherGroup(uf);
                //List list = imp.findbyShipId(35) ;
                //System.out.print(list.size());
        }

        /**
         * Get the UTeacherGroup info via the unique U_TEACHGROUP_ID
         *
         * @param
         * @return the prepared UTeacherGroupForm, default is null
         * @throws UTeacherGroupDAOSysException
         */
        public UTeacherGroupForm getUTeacherGroup(int pkID)
                throws UTeacherGroupDAOSysException
        {
                UTeacherGroupForm bf = new UTeacherGroupForm();
                UTeacherGroupForm res = null;
                List tmList = null;
                String hql = " from UTeacherGroupModel where U_TEACHGROUP_ID = " +
                        pkID;

                try
                {
                        tmList = HibernateDAO.find(hql);
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new UTeacherGroupDAOSysException("" + e);
                }

                if ((tmList != null) && (tmList.size() > 0))
                {
                        UTeacherGroupModel bm = (UTeacherGroupModel) tmList.get(0);
                        res = bf.getUTeacherGroupForm(bm);
                }

                return res;
        }

        /**
         * Get the UTeacherGroup list by the catalogID
         *
         * @param
         * @return The prepared arraylist object,default size is 0
         * @throws UTeacherGroupDAOSysException
         */
        public List getUTeacherGroupList() throws UTeacherGroupDAOSysException
        {
                UTeacherGroupForm bf = new UTeacherGroupForm();
                UTeacherGroupModel bm = null;
                ArrayList UTeacherGroupList = new ArrayList();
                List tmList = null;
                String hql = " from UTeacherGroupModel where  1 = 1 ";

                try
                {
                        tmList = HibernateDAO.find(hql);
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new UTeacherGroupDAOSysException("" + e);
                }

                for (int i = 0; i < tmList.size(); i++)
                {
                        bm = (UTeacherGroupModel) tmList.get(i);
                        UTeacherGroupList.add(bf.getUTeacherGroupForm(bm));
                }

                return UTeacherGroupList;
        }

        public List findbyUserId(int userid) throws UTeacherGroupDAOSysException
        {
                UTeacherGroupForm bf = new UTeacherGroupForm();
                UTeacherGroupModel bm = null;
                ArrayList list = new ArrayList();
                List tmList = null;
                String hql = " from UTeacherGroupModel where 1=1 and userID=" + userid;

                try
                {
                        tmList = HibernateDAO.find(hql);
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new UTeacherGroupDAOSysException("" + e);
                }

                for (int i = 0; i < tmList.size(); i++)
                {
                        bm = (UTeacherGroupModel) tmList.get(i);
                        list.add(bf.getUTeacherGroupForm(bm));
                }

                return list;
        }

        public List findbyShipId(int ushipId) throws UTeacherGroupDAOSysException
        {
                UTeacherGroupForm bf = new UTeacherGroupForm();
                UTeacherGroupModel bm = null;
                ArrayList list = new ArrayList();
                List tmList = null;
                String hql = null;

                if (ushipId == 0)
                {
                        hql = " from UTeacherGroupModel ";
                }
                else
                {
                        hql = " from UTeacherGroupModel where 1=1 and g_Ship_ID=" +
                                ushipId;
                }

                try
                {
                        tmList = HibernateDAO.find(hql);
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new UTeacherGroupDAOSysException("" + e);
                }

                for (int i = 0; i < tmList.size(); i++)
                {
                        bm = (UTeacherGroupModel) tmList.get(i);
                        list.add(bf.getUTeacherGroupForm(bm));
                }

                return list;
        }
}
