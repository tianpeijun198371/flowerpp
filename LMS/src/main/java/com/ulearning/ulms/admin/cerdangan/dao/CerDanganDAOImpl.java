/**
 * Copyright (c) 2000-2005.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.admin.cerdangan.dao;

import com.ulearning.ulms.admin.cerdangan.exceptions.CerDanganDAOSysException;
import com.ulearning.ulms.admin.cerdangan.form.CerNewDanganForm;
import com.ulearning.ulms.admin.cerdangan.model.CerNewdanganTab;
import com.ulearning.ulms.admin.cerdangan.model.CerNewdanganTab;
import com.ulearning.ulms.core.exceptions.ULMSException;
import com.ulearning.ulms.core.exceptions.ULMSSysException;
import com.ulearning.ulms.util.HibernateDAO;

import java.io.Serializable;

import java.sql.ResultSet;
import java.sql.SQLException;

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
public class CerDanganDAOImpl implements com.ulearning.ulms.admin.cerdangan.dao.CerDanganDAO
{
        public void insertCerDangan(CerNewDanganForm details)
                throws CerDanganDAOSysException
        {
                try
                {
                        HibernateDAO.add(details.getCerNewdanganTab());
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new CerDanganDAOSysException("" + e);
                }
        }

        public void updateCerDangan(CerNewDanganForm details)
                throws CerDanganDAOSysException
        {
                try
                {
                        HibernateDAO.update(details.getCerNewdanganTab());
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new CerDanganDAOSysException("" + e);
                }
        }

        /**
         * Remove the CerDangan from database by the DANGANID
         *
         * @throws CerDanganDAOSysException
         */
        public void deleteCerDangan(int pkID) throws CerDanganDAOSysException
        {
                String hql = " from CerNewdanganTab where DANGANID = " + pkID;

                try
                {
                        HibernateDAO.delete(hql);
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new CerDanganDAOSysException("" + e);
                }
        }

        /**
         * Get the CerDangan info via the unique DANGANID
         *
         * @return the prepared CerNewDanganForm, default is null
         * @throws CerDanganDAOSysException
         */
        public CerNewDanganForm getCerDangan(int pkID)
                throws CerDanganDAOSysException
        {
                CerNewDanganForm bf = new CerNewDanganForm();
                CerNewDanganForm res = null;
                List tmList = null;
                String hql = " from CerNewdanganTab where DANGANID = " + pkID;

                try
                {
                        tmList = HibernateDAO.find(hql);
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new CerDanganDAOSysException("" + e);
                }

                if ((tmList != null) && (tmList.size() > 0))
                {
                        CerNewdanganTab bm = (CerNewdanganTab) tmList.get(0);
                        res = bf.getCerDanganForm(bm);
                }

                return res;
        }

        /**
         * Get the CerDangan list by the catalogID
         * DAOSysException
         */
        public List getCerDanganList()
        {
                CerNewDanganForm bf = new CerNewDanganForm();
                CerNewdanganTab bm = null;
                ArrayList CerDanganList = new ArrayList();
                List tmList = null;
                String hql = " from CerNewdanganTab where  1 = 1 ";

                try
                {
                        tmList = HibernateDAO.find(hql);
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new CerDanganDAOSysException("" + e);
                }

                for (int i = 0; i < tmList.size(); i++)
                {
                        bm = (CerNewdanganTab) tmList.get(i);
                        CerDanganList.add(bf.getCerDanganForm(bm));
                }

                return CerDanganList;
        }

        public static void main(String[] args) throws Exception
        {
                CerDanganDAOImpl asdf = new CerDanganDAOImpl();
                asdf.getCerDanganList();
        }
}
