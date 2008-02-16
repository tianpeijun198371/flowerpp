/**
 * MasterDAOImpl.java.
 * User: fengch  Date: 2004-4-27
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.course.dao;

import com.ulearning.ulms.core.exceptions.ULMSSysException;
import com.ulearning.ulms.core.security.bean.SecurityConstants;
import com.ulearning.ulms.core.util.DateTimeUtil;
import com.ulearning.ulms.core.util.I18Util;
import com.ulearning.ulms.course.exceptions.CourseAppException;
import com.ulearning.ulms.course.exceptions.CourseSysException;
import com.ulearning.ulms.course.model.*;
import com.ulearning.ulms.course.util.CourseKeys;
import com.ulearning.ulms.util.HibernateDAO;
import com.ulearning.ulms.util.HibernateUtil;
import com.ulearning.ulms.util.log.LogUtil;

import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Query;
import net.sf.hibernate.Session;

import java.io.Serializable;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.*;


public class MasterDAOImpl implements MasterDAO
{
        public MasterDAOImpl()
        {
        }

        /**
         * @param value
         * @throws com.ulearning.ulms.course.exceptions.CourseSysException
         *
         */
        public int createCatalog(CatalogModel value) throws CourseSysException
        {
                int catalogID = 0;
                value.setEstablishTime(new Date());

                try
                {
                        Serializable s = HibernateDAO.add(value.getCatalog());
                        catalogID = Integer.parseInt(s.toString());
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new CourseSysException(e);
                }

                return catalogID;
        }

        /**
         * @param value
         * @throws com.ulearning.ulms.course.exceptions.CourseSysException
         *
         */
        public int createMaster(MasterModel value)
                throws CourseSysException, CourseAppException
        {
                int masterID = 0;

                try
                {
                        LogUtil.debug("course",
                                "[MasterDAOImpl]===========period1=" + value.getPeriod());
                        LogUtil.debug("course",
                                "[MasterDAOImpl]===========credit1=" + value.getCredit());

                        LogUtil.debug("course",
                                "[MasterDAOImpl]===========period2=" +
                                        value.getMaster().getPeriod());
                        LogUtil.debug("course",
                                "[MasterDAOImpl]===========credit2=" +
                                        value.getMaster().getCredit());

                        Serializable s = HibernateDAO.add(value.getMaster());
                        masterID = Integer.parseInt(s.toString());
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new CourseSysException(e);
                }

                return masterID;
        }

        /**
         * @param catalogIDs
         * @throws com.ulearning.ulms.course.exceptions.CourseSysException
         *
         */
        public void deleteCatalog(List catalogIDs) throws CourseSysException
        {
                String sql_str;
                int i = 0;
                int catalogID;

                try
                {
                        while (i < catalogIDs.size())
                        {
                                catalogID = ((Integer) catalogIDs.get(i)).intValue();
                                sql_str = " from Catalog where catalogID=" + catalogID;
                                HibernateDAO.delete(sql_str);
                                LogUtil.debug("course", "[MasterDAOOracle] " + "删除 1 行");
                                i++;
                        }
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new CourseSysException(e);
                }
        }

        /**
         * @param masterIDs
         * @throws com.ulearning.ulms.course.exceptions.CourseSysException
         *
         */
        public void deleteMaster(List masterIDs) throws CourseSysException
        {
                String sql_str;
                int i = 0;
                int masterID;

                try
                {
                        while (i < masterIDs.size())
                        {
                                masterID = ((Integer) masterIDs.get(i)).intValue();
                                sql_str = " from Master where masterID=" + masterID;
                                HibernateDAO.delete(sql_str);
                                LogUtil.debug("course", "[MasterDAOOracle] " + "删除 1行");
                                i++;
                        }
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new CourseSysException(e);
                }
        }

        /**
         * @param catalogID
         * @throws com.ulearning.ulms.course.exceptions.CourseSysException
         *
         */
        public CatalogModel getCatalog(int catalogID) throws CourseSysException
        {
                CatalogModel cat = null;

                try
                {
                        String hqlStr1 = "from Catalog WHERE catalogID=" + catalogID;
                        List tmList = HibernateDAO.find(hqlStr1);
                        Catalog cc = null;

                        if ((tmList != null) && (tmList.size() > 0))
                        {
                                cc = (Catalog) tmList.get(0);
                                cat = new CatalogModel(cc);
                        }
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new CourseSysException(e);
                }

                return cat;
        }

        /**
         * @param catalogID
         * @throws com.ulearning.ulms.course.exceptions.CourseSysException
         *
         */
        public List getCatalogPath(int catalogID) throws CourseSysException
        {
                CatalogModel cat = null;
                ArrayList catalogPathList = new ArrayList();

                try
                {
                        String queryStr1 = " from Catalog WHERE  catalogID=" + catalogID;
                        LogUtil.debug("course",
                                "[MasterDAOImpl] ==========queryStr1 = " + queryStr1);

                        List tmList = HibernateDAO.find(queryStr1);
                        Catalog tmCat = null;

                        while ((tmList != null) && (tmList.size() > 0))
                        {
                                tmCat = (Catalog) tmList.get(0);
                                cat = new CatalogModel(tmCat);
                                catalogPathList.add(cat);
                                catalogID = cat.getParentID();

                                if (catalogID == 0)
                                {
                                        break;
                                }

                                String hql = " from Catalog WHERE type= '" +
                                        CourseKeys.CATALOG_MASTER_TYPE + "' and catalogID=" +
                                        catalogID;
                                LogUtil.debug("course",
                                        "[MasterDAOImpl] ==========queryStr = " + hql);
                                tmList = null;
                                tmList = HibernateDAO.find(hql);
                        }
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new CourseSysException(e);
                }

                return catalogPathList;
        }

        /**
         * @param masterID
         * @throws com.ulearning.ulms.course.exceptions.CourseSysException
         *
         */
        public MasterModel getMaster(int masterID) throws CourseSysException
        {
                MasterModel mm = null;
                String queryStr1 = " from Master WHERE masterID=" + masterID;
                LogUtil.debug("course", "[MasterDAOImpl]  queryStr1=" + queryStr1);

                try
                {
                        List tmList = HibernateDAO.find(queryStr1);
                        Master master = null;

                        if ((tmList != null) && (tmList.size() > 0))
                        {
                                master = (Master) tmList.get(0);
                        }

                        if (master != null)
                        {
                                mm = new MasterModel(master);
                        }
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new CourseSysException(e);
                }

                return mm;
        }

        /**
         * @param catalogID
         * @throws com.ulearning.ulms.course.exceptions.CourseSysException
         *
         */
        public MasterTreeModel getTree(int catalogID) throws CourseSysException
        {
                MasterModel mas = null;
                CatalogModel cat = null;
                ArrayList masters = new ArrayList();
                ArrayList catalogs = new ArrayList();

                MasterListModel masterList = new MasterListModel(masters);
                CatalogListModel catalogList = new CatalogListModel(catalogs);

                MasterTreeModel mt = new MasterTreeModel(masterList, catalogList);

                try
                {
                        String hql = " from  Master WHERE masterID!=0 and catalogID=" +
                                catalogID + " order by masterID desc";

                        LogUtil.debug("course", "[MasterDAOImpl]  hql=" + hql);

                        List tmList = HibernateDAO.find(hql);
                        Master tmMaster = null;

                        for (int i = 0; i < tmList.size(); i++)
                        {
                                tmMaster = (Master) tmList.get(i);
                                mas = new MasterModel(tmMaster);
                                masters.add(mas);
                        }

                        LogUtil.debug("course",
                                "[MasterDAOImpl] ******************masters size" +
                                        masters.size());

                        hql = " from Catalog WHERE catalogID != 0 " + "and type = '" +
                                SecurityConstants.USER_COURSE_RELATION + "'" +
                                " and parentID = " + catalogID + " order by catalogID desc";

                        LogUtil.debug("course", "[MasterDAOImpl]  " + hql);

                        tmList = HibernateDAO.find(hql);

                        Catalog tmCat = null;

                        for (int j = 0; j < tmList.size(); j++)
                        {
                                tmCat = (Catalog) tmList.get(j);
                                cat = new CatalogModel(tmCat);
                                catalogs.add(cat);
                        }

                        LogUtil.debug("course",
                                "[MasterDAOImpl] ******************catalogs size" +
                                        catalogs.size());
                        LogUtil.debug("course", "[MasterDAOImpl]getTree over" + "\n");
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new CourseSysException(e);
                }

                return mt;
        }

        /**
         * @param catalogID
         * @throws com.ulearning.ulms.course.exceptions.CourseSysException
         *
         */
        public MasterTreeModel getTree(int catalogID, int type)
                throws CourseSysException
        {
                MasterModel mas = null;
                CatalogModel cat = null;

                ArrayList masters = new ArrayList();
                ArrayList catalogs = new ArrayList();

                MasterListModel masterList = null;
                CatalogListModel catalogList = null;

                MasterTreeModel mt = null;

                try
                {
                        String hql = " from Master WHERE masterID != 0 " +
                                " and catalogID=" + catalogID + " and type = '" + type +
                                "' order by masterID desc";

                        LogUtil.debug("course", "[MasterDAOImpl]  hql = " + hql);

                        List tmList = HibernateDAO.find(hql);
                        Master tmMas = null;

                        for (int i = 0; i < tmList.size(); i++)
                        {
                                tmMas = (Master) tmList.get(i);
                                mas = new MasterModel(tmMas);
                                masters.add(mas);
                        }

                        masterList = new MasterListModel(masters);
                        LogUtil.debug("course",
                                "[MasterDAOImpl] ******************masters size" +
                                        masters.size());

                        hql = " from Catalog WHERE catalogID != 0 " + " and type='" +
                                SecurityConstants.USER_COURSE_RELATION + "' and parentID=" +
                                catalogID + " order by catalogID desc";
                        ;
                        LogUtil.debug("course", "[MasterDAOImpl]  " + hql);
                        tmList = HibernateDAO.find(hql);

                        Catalog tmCat = null;

                        for (int i = 0; i < tmList.size(); i++)
                        {
                                tmCat = (Catalog) tmList.get(i);
                                cat = new CatalogModel(tmCat);
                                catalogs.add(cat);
                        }

                        catalogList = new CatalogListModel(catalogs);
                        LogUtil.debug("course",
                                "[MasterDAOImpl] ******************catalogs size" +
                                        catalogs.size());
                        LogUtil.debug("course", "[MasterDAOImpl]getTree over" + "\n");
                        mt = new MasterTreeModel(masterList, catalogList);
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new CourseSysException(e);
                }

                return mt;
        }

        /**
         * @param catalogID
         * @param type
         * @param aspID
         * @return
         * @throws CourseSysException
         */
        public MasterTreeModel getTree(int catalogID, int type, int aspID, int orgID)
                throws CourseSysException
        {
                MasterModel mas = null;
                CatalogModel cat = null;

                ArrayList masters = new ArrayList();
                ArrayList catalogs = new ArrayList();

                MasterListModel masterList = new MasterListModel(masters);
                CatalogListModel catalogList = new CatalogListModel(catalogs);

                MasterTreeModel mt = new MasterTreeModel(masterList, catalogList);

                try
                {
                        String aspID_str = "";
                        String orgID_str = "";

                        if (aspID != 0)
                        {
                                aspID_str += (" and aspID=" + aspID);
                        }

                        if (orgID != 0)
                        {
                                orgID_str += (" and orgID=" + orgID);
                        }

                        String hql = " from Master WHERE masterID != 0 " +
                                " and catalogID = " + catalogID + " and type = '" + type +
                                "' " + aspID_str + orgID_str + " order by masterID desc";

                        LogUtil.debug("course", "[MasterDAOImpl]  sql=" + hql);

                        List tmList = HibernateDAO.find(hql);
                        System.out.println("Master,aspID=" + aspID + ",size=" +
                                tmList.size());

                        Master tmMas = null;

                        for (int i = 0; i < tmList.size(); i++)
                        {
                                tmMas = (Master) tmList.get(i);
                                mas = new MasterModel(tmMas);
                                masters.add(mas);
                        }

                        LogUtil.debug("course",
                                "[MasterDAOImpl] ******************masters size" +
                                        masters.size());

                        hql = " from  Catalog WHERE catalogID != 0 " + " and type ='0' " +
                                " and parentID = " + catalogID + " and aspID=" + aspID +
                                " order by catalogID desc";

                        LogUtil.debug("course", "[MasterDAOImpl]  " + hql);
                        tmList = HibernateDAO.find(hql);
                        System.out.println("Catalog,aspID=" + aspID + ",size=" +
                                tmList.size());

                        Catalog tmCat = null;

                        for (int i = 0; i < tmList.size(); i++)
                        {
                                tmCat = (Catalog) tmList.get(i);
                                cat = new CatalogModel(tmCat);
                                catalogs.add(cat);
                        }

                        LogUtil.debug("course",
                                "[MasterDAOImpl] ******************catalogs size" +
                                        catalogs.size());
                        LogUtil.debug("course", "[MasterDAOImpl]getTree over" + "\n");
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new CourseSysException(e);
                }

                return mt;
        }

        public MasterTreeModel getTree(int catalogID, int type, int aspID,
                                       int orgID, int catalogType) throws CourseSysException
        {
                MasterModel mas = null;
                CatalogModel cat = null;

                ArrayList masters = new ArrayList();
                ArrayList catalogs = new ArrayList();

                MasterListModel masterList = new MasterListModel(masters);
                CatalogListModel catalogList = new CatalogListModel(catalogs);

                MasterTreeModel mt = new MasterTreeModel(masterList, catalogList);

                try
                {
                        String aspID_str = "";
                        String orgID_str = "";

                        if (aspID != 0)
                        {
                                aspID_str += (" and aspID=" + aspID);
                        }

                        if (orgID != 0)
                        {
                                orgID_str += (" and orgID=" + orgID);
                        }

                        String hql = " from Master WHERE masterID != 0 " +
                                " and catalogID = " + catalogID + aspID_str + orgID_str +
                                " order by type,masterID desc";

                        LogUtil.debug("course", "[MasterDAOImpl]  sql=" + hql);

                        List tmList = HibernateDAO.find(hql);
                        System.out.println("Master,aspID=" + aspID + ",size=" +
                                tmList.size());

                        Master tmMas = null;

                        for (int i = 0; i < tmList.size(); i++)
                        {
                                tmMas = (Master) tmList.get(i);
                                mas = new MasterModel(tmMas);
                                masters.add(mas);
                        }

                        LogUtil.debug("course",
                                "[MasterDAOImpl] ******************masters size" +
                                        masters.size());

                        hql = " from  Catalog WHERE catalogID != 0 " + " and type = '" +
                                catalogType + "' and parentID = " + catalogID + " and aspID=" +
                                aspID + " order by catalogID desc";

                        LogUtil.debug("course", "[MasterDAOImpl]  " + hql);
                        tmList = HibernateDAO.find(hql);
                        System.out.println("Catalog,aspID=" + aspID + ",size=" +
                                tmList.size());

                        Catalog tmCat = null;

                        for (int i = 0; i < tmList.size(); i++)
                        {
                                tmCat = (Catalog) tmList.get(i);
                                cat = new CatalogModel(tmCat);
                                catalogs.add(cat);
                        }

                        LogUtil.debug("course",
                                "[MasterDAOImpl] ******************catalogs size" +
                                        catalogs.size());
                        LogUtil.debug("course", "[MasterDAOImpl]getTree over" + "\n");
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new CourseSysException(e);
                }

                return mt;
        }

        /**
         * @param value
         * @throws com.ulearning.ulms.course.exceptions.CourseSysException
         *
         */
        public void updateCatalog(CatalogModel value) throws CourseSysException
        {
                int catalogID = value.getCatalogID();
                CatalogModel cm = getCatalog(catalogID);
                cm.setName(value.getName());

                if (value.getParentID() != -1)
                {
                        cm.setParentID(value.getParentID());
                }

                cm.setDescription(value.getDescription());

                try
                {
                        HibernateDAO.update(cm.getCatalog());
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new CourseSysException(e);
                }
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
                int totalNumber;
                MasterModel mas = null;

                ArrayList masters = new ArrayList();
                MasterListModel masterList = new MasterListModel(masters);

                try
                {
                        String hql = "select count(*)  from Master where " +
                                " keyword like '%" + masterKey + "%' or " + " name like '%" +
                                masterKey + "%' or " + " masterCode like '%" + masterKey +
                                "%'";

                        LogUtil.debug("master", "[MasterDAOImpl]  sql=" + hql);

                        totalNumber = HibernateDAO.count(hql);

                        hql = " from Master where " + "keyword like '%" + masterKey +
                                "%' or " + "name like '%" + masterKey + "%' or " +
                                "masterCode like '%" + masterKey + "%' order by masterID";

                        LogUtil.debug("master", "[MasterDAOImpl]  sql=" + hql);

                        List tmList = HibernateDAO.find(hql);
                        Master tmMas = null;
                        int masterID = 0;

                        for (int i = 0; i < tmList.size(); i++)
                        {
                                tmMas = (Master) tmList.get(i);
                                masterID = tmMas.getMasterID();

                                if (masterID == 0)
                                {
                                        totalNumber--;

                                        continue;
                                }

                                mas = new MasterModel(tmMas);
                                masters.add(mas);
                        }

                        masterList.setTotalNumber(totalNumber);
                        LogUtil.debug("master", "[MasterDAOImpl]getTree over" + "\n");
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new CourseSysException(e);
                }

                return masterList;
        }

        /**
         * Search the master according to the keywords and master type.
         *
         * @param masterKey
         * @param masterType
         * @return
         * @throws CourseSysException
         */
        public MasterListModel search(String masterKey, int masterType)
                throws CourseSysException
        {
                int masterID;
                int totalNumber;
                MasterModel mas = null;

                ArrayList masters = new ArrayList();
                MasterListModel masterList = new MasterListModel(masters);

                try
                {
                        String hql = " select count(*) from Master " + " where type = '" +
                                masterType + "' AND (keyword like '%" + masterKey + "%' or " +
                                " name like '%" + masterKey + "%' or " + "masterCode like '%" +
                                masterKey + "%')";
                        LogUtil.debug("master", "[MasterDAOImpl]  sql=" + hql);
                        totalNumber = HibernateDAO.count(hql);

                        hql = " from Master " + " where type = '" + masterType +
                                "' AND ( keyword like '%" + masterKey + "%' or " +
                                " name like '%" + masterKey + "%' or " + " masterCode like '%" +
                                masterKey + "%') order by masterID";

                        LogUtil.debug("master", "[MasterDAOImpl]  hql=" + hql);

                        List tmList = HibernateDAO.find(hql);
                        Master tmMas = null;

                        for (int i = 0; i < tmList.size(); i++)
                        {
                                tmMas = (Master) tmList.get(i);
                                masterID = tmMas.getMasterID();

                                if (masterID == 0)
                                {
                                        totalNumber--;

                                        continue;
                                }

                                mas = new MasterModel(tmMas);
                                masters.add(mas);
                        }

                        masterList.setTotalNumber(totalNumber);
                        LogUtil.debug("master", "[MasterDAOImpl]getTree over" + "\n");
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new CourseSysException(e);
                }

                return masterList;
        }

        public MasterListModel search(String masterKey, int masterType, int aspID,
                                      int orgID) throws CourseSysException
        {
                int masterID;
                int totalNumber;
                MasterModel mas = null;

                ArrayList masters = new ArrayList();
                MasterListModel masterList = new MasterListModel(masters);

                try
                {
                        String aspID_str = "";
                        String orgID_str = "";

                        if (aspID != 0)
                        {
                                aspID_str += (" and aspID=" + aspID);
                        }

                        if (orgID != 0)
                        {
                                orgID_str += (" and orgID=" + orgID);
                        }

                        String hql = " select count(*) from Master " + " where type = '" +
                                masterType + "'" + aspID_str + orgID_str +
                                " AND (keyword like '%" + masterKey + "%' or " +
                                " name like '%" + masterKey + "%' or " + "masterCode like '%" +
                                masterKey + "%')";
                        LogUtil.debug("master", "[MasterDAOImpl]  sql=" + hql);
                        totalNumber = HibernateDAO.count(hql);

                        hql = " from Master " + " where type = '" + masterType + "'" +
                                aspID_str + orgID_str + " AND ( keyword like '%" + masterKey +
                                "%' or " + " name like '%" + masterKey + "%' or " +
                                " masterCode like '%" + masterKey + "%') order by masterID";

                        LogUtil.debug("master", "[MasterDAOImpl]  hql=" + hql);

                        List tmList = HibernateDAO.find(hql);
                        Master tmMas = null;

                        for (int i = 0; i < tmList.size(); i++)
                        {
                                tmMas = (Master) tmList.get(i);
                                masterID = tmMas.getMasterID();

                                if (masterID == 0)
                                {
                                        totalNumber--;

                                        continue;
                                }

                                mas = new MasterModel(tmMas);
                                masters.add(mas);
                        }

                        masterList.setTotalNumber(totalNumber);
                        LogUtil.debug("master", "[MasterDAOImpl]getTree over" + "\n");
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new CourseSysException(e);
                }

                return masterList;
        }

        /**
         * @param value
         * @throws com.ulearning.ulms.course.exceptions.CourseSysException
         *
         */
        public void updateMaster(MasterModel value)
                throws CourseSysException, CourseAppException
        {
                int masterID = value.getMasterID();
                MasterModel newMas = getMaster(masterID);
                newMas.setName(value.getName());
                newMas.setMasterCode(value.getMasterCode());
                newMas.setDescription(value.getDescription());
                newMas.setKey(value.getKey());
                newMas.setType(value.getType());
                newMas.setPeriod(value.getPeriod());
                newMas.setPlan(value.getPlan());
                newMas.setCredit(value.getCredit());
                newMas.setStatus(value.getStatus());
                newMas.setOperator(value.getOperator());

                try
                {
                        HibernateDAO.update(newMas.getMaster());
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new CourseSysException(e);
                }
        }

        /*
        * 判断课程代码是否存在
        */
        public boolean isExistMasterCode(String masterCode, int masterID, int type,
                                         int aspID, int orgID) throws CourseSysException
        {
                boolean isExist = false;
                String hql = " from Master " + " WHERE masterCode = '" + masterCode +
                        "' and type = '" + type + "'";

                if (aspID != 0)
                {
                        hql += (" and aspID = " + aspID);
                }

                if (orgID != 0)
                {
                        hql += (" and orgID = " + orgID);
                }

                if (masterID != -1)
                {
                        hql += (" and masterID != " + masterID);
                }

                try
                {
                        LogUtil.info("course",
                                "[MasterDAOImpl]isExistMasterCode---hql=" + hql);

                        List tmList = HibernateDAO.find(hql);

                        if ((tmList != null) && (tmList.size() > 0))
                        {
                                isExist = true;
                        }
                }
                catch (ULMSSysException se)
                {
                        se.printStackTrace();
                        throw new CourseSysException(se);
                }

                return isExist;
        }

        public int getAspTotalMaster(int aspID, int orgID, int type)
                throws CourseSysException
        {
                int total = 0;
                String aspID_str = "";
                String orgID_str = "";

                if (aspID != 0)
                {
                        aspID_str += (" and aspID=" + aspID);
                }

                if (orgID != 0)
                {
                        orgID_str += (" and orgID=" + orgID);
                }

                String strSql = "select count(*) from Master where masterID > 0" +
                        aspID_str + orgID_str + " and type = '" + type + "'";

                try
                {
                        total = HibernateDAO.count(strSql);
                }
                catch (ULMSSysException se)
                {
                        se.printStackTrace();
                        throw new CourseSysException(
                                "SQLException while query total master in a ASP; aspID = " +
                                        aspID + " :\n" + se);
                }

                return total;
        }

        public int getAspTotalMaster(int aspID, int orgID, int type,
                                     String startDates, String endDates) throws CourseSysException
        {
                int total = 0;
                Date startDate = DateTimeUtil.parseDate(startDates);
                Date endDate = DateTimeUtil.parseDate(endDates);

                //update for querying the Masters of current day
                Calendar cal = Calendar.getInstance();
                cal.setTime(endDate);
                cal.set(10, 23);
                cal.set(12, 59);
                cal.set(13, 59);
                endDate = cal.getTime();
                LogUtil.info("course",
                        "[CourseDAOImpl]getAspTotalCourse--endDate0=" +
                                I18Util.FormatDateTime(endDate, Locale.CHINA));

                com.ulearning.ulms.core.util.Calendar calendar = new com.ulearning.ulms.core.util.Calendar(endDate);
                endDate = calendar.nextDay();

                Session session = null;
                String aspID_str = "";
                String orgID_str = "";

                if (aspID != 0)
                {
                        aspID_str += (" and master.aspID=" + aspID);
                }

                if (orgID != 0)
                {
                        orgID_str += (" and master.orgID=" + orgID);
                }

                String hql = "select count(*) from Master master where master.masterID > 0" +
                        aspID_str + orgID_str + " and master.type = '" + type +
                        "' and master.establishDate >= :beginDate" +
                        " and master.establishDate <= :endDate";

                try
                {
                        session = HibernateUtil.getSession();

                        Query query = session.createQuery(hql);
                        query.setParameter("beginDate", startDate);
                        query.setParameter("endDate", endDate);

                        Iterator it = query.iterate();

                        if ((it != null) && it.hasNext())
                        {
                                Integer amount = (Integer) it.next();
                                total = amount.intValue();
                        }

                        session.flush();
                        session.connection().commit();
                }
                catch (HibernateException e)
                {
                        e.printStackTrace();
                }
                catch (SQLException e)
                {
                        e.printStackTrace();
                }
                finally
                {
                        try
                        {
                                HibernateUtil.releaseSession(session);
                        }
                        catch (HibernateException he)
                        {
                                he.printStackTrace();
                        }
                }

                return total;
        }

        public List getMasterInfo(String masterName, String[] catalogIDs,
                                  String startDates, String endDates, int aspID, int orgID)
                throws CourseSysException
        {
                ArrayList masterList = new ArrayList();
                String catalogIDString = "";

                Date startDate = DateTimeUtil.parseDate(startDates);
                Date endDate = DateTimeUtil.parseDate(endDates);

                LogUtil.info("course",
                        "[MasterDAOImpl]getMasterInfo--endDate0=" +
                                I18Util.FormatDateTime(endDate, Locale.CHINA));

                com.ulearning.ulms.core.util.Calendar calendar = new com.ulearning.ulms.core.util.Calendar(endDate);
                endDate = calendar.nextDay();

                LogUtil.info("course",
                        "[MasterDAOImpl]getMasterInfo--startDate=" +
                                I18Util.FormatDateTime(startDate, Locale.CHINA));
                LogUtil.info("course",
                        "[MasterDAOImpl]getMasterInfo--endDate=" +
                                I18Util.FormatDateTime(endDate, Locale.CHINA));

                Session session = null;

                if (catalogIDs != null)
                {
                        for (int i = 0; i < catalogIDs.length; i++)
                        {
                                if (i != 0)
                                {
                                        catalogIDString += ("," + catalogIDs[i]);
                                }
                                else
                                {
                                        catalogIDString += (" and catalogID in ( " + catalogIDs[i]);
                                }
                        }

                        catalogIDString += " )";
                }

                String nameString = "";

                if (!masterName.equals(""))
                {
                        nameString = " name like '%" + masterName + "%' and ";
                }

                String aspID_str = "";
                String orgID_str = "";

                if (aspID != 0)
                {
                        aspID_str += (" and aspID=" + aspID);
                }

                if (orgID != 0)
                {
                        orgID_str += (" and orgID=" + orgID);
                }

                String hql = " from Master where " + nameString +
                        " EstablishDate >= :startDate" + " and EstablishDate <= :endDate" +
                        aspID_str + orgID_str + catalogIDString;
                LogUtil.info("course", "[MasterDAOImpl]getMasterInfo--hql=" + hql);

                try
                {
                        session = HibernateUtil.getSession();

                        Query query = session.createQuery(hql);
                        query.setDate("startDate", startDate);
                        query.setDate("endDate", endDate);

                        List tmList = query.list();
                        MasterModel mas = null;
                        Master tmMas = null;

                        for (int i = 0; i < tmList.size(); i++)
                        {
                                tmMas = (Master) tmList.get(i);
                                mas = new MasterModel(tmMas);
                                masterList.add(mas);
                        }
                }
                catch (HibernateException e)
                {
                        e.printStackTrace();
                        throw new CourseSysException(
                                "SQLException while query  master in a ASP+" + e);
                }
                finally
                {
                        try
                        {
                                HibernateUtil.releaseSession(session);
                        }
                        catch (HibernateException e)
                        {
                                e.printStackTrace();
                                throw new CourseSysException(
                                        "SQLException while query  master in a ASP+" + e);
                        }
                }

                return masterList;
        }

        public int getMasterHasPubNum(int masterID, int type)
                throws CourseSysException
        {
                int hasPubNum = 0;
                String hql = "";

                if (type == SecurityConstants.USER_CERTIFICATE_RELATION)
                {
                        hql = "select count(*) from CertModel where masterID = " +
                                masterID;
                }
                else
                {
                        hql = "select count(*) from Course where masterID = " + masterID;
                }

                try
                {
                        hasPubNum = HibernateDAO.count(hql);
                }
                catch (ULMSSysException se)
                {
                        se.printStackTrace();
                        throw new CourseSysException(
                                "SQLException while query the number of master has publish; masterID = " +
                                        masterID + " :\n" + se);
                }

                return hasPubNum;
        }

        public boolean isHierachical(int ancestorID, int children)
                throws CourseSysException
        {
                boolean isHierachical = false;
                int parentID = 0;
                CatalogModel cm = null;

                try
                {
                        cm = getCatalog(children);
                        parentID = cm.getParentID();

                        if ((parentID == 0) || (parentID == -1))
                        {
                                isHierachical = false;
                        }
                        else if (ancestorID == parentID)
                        {
                                isHierachical = true;
                        }
                        else
                        {
                                isHierachical = isHierachical(ancestorID, parentID);
                        }
                }
                catch (ULMSSysException se)
                {
                        se.printStackTrace();
                        throw new CourseSysException(
                                "SQLException while query  master in a ASP" + se);
                }

                return isHierachical;
        }

        /*
        * 判断在所在目录下，该目录名是否已存在
        */
        public boolean isExistCatalog(String thisCatalogName, int thisCatalogID,
                                      int catalogID, int catType, int aspID, int orgID)
                throws CourseSysException
        {
                try
                {
                        String aspID_str = "";
                        String orgID_str = "";

                        if (aspID != 0)
                        {
                                aspID_str += (" and aspID=" + aspID);
                        }

                        if (orgID != 0)
                        {
                                orgID_str += (" and orgID=" + orgID);
                        }

                        String queryStr1 = " from Catalog  WHERE name='" + thisCatalogName +
                                "' and type='" + catType + "'" + " and parentID = " +
                                catalogID + aspID_str + orgID_str;

                        if (thisCatalogID != -1)
                        {
                                queryStr1 += (" and catalogID != " + thisCatalogID);
                        }

                        LogUtil.debug("course",
                                "[MasterDAOImpl] ==========queryStr1 = " + queryStr1);

                        List tmList = HibernateDAO.find(queryStr1);

                        if ((tmList != null) && (tmList.size() > 0))
                        {
                                return true;
                        }
                        else
                        {
                                return false;
                        }
                }
                catch (ULMSSysException se)
                {
                        se.printStackTrace();
                        throw new CourseSysException(
                                "SQLException while query  master in a ASP" + se);
                }
        }

        public List getCourseFromMaster(int masterID, int type)
                throws CourseSysException
        {
                List returnList = new ArrayList();
                CourseModel course = null;
                String hql = "";

                if (type == SecurityConstants.USER_COURSE_RELATION)
                {
                        hql = "from Course where masterid = " + masterID;
                }

                try
                {
                        List tempList = HibernateDAO.find(hql);
                        Course courseModel = null;

                        for (int i = 0; (tempList != null) && (i < tempList.size()); i++)
                        {
                                courseModel = (Course) tempList.get(i);
                                course = new CourseModel(courseModel);
                                returnList.add(course);
                        }
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new CourseSysException("CourseDAOImpl" + e);
                }

                return returnList;
        }

        public int getCatalogID(String thisCatalogName, int catalogID, int catType)
                throws CourseSysException
        {
                int currentCatalogID = 0;

                try
                {
                        String queryStr1 = " from Catalog WHERE name='" + thisCatalogName +
                                "' and type='" + catType + "'";

                        if (catalogID != -1)
                        {
                                queryStr1 += ("and parentID = " + catalogID);
                        }

                        LogUtil.debug("course",
                                "[MasterDAOImpl] ==========queryStr1 = " + queryStr1);

                        List tmList = HibernateDAO.find(queryStr1);

                        Catalog tmCat = null;

                        if ((tmList != null) && (tmList.size() > 0))
                        {
                                tmCat = (Catalog) tmList.get(0);
                                currentCatalogID = tmCat.getCatalogID();
                        }
                        else
                        {
                                currentCatalogID = -1;
                        }
                }
                catch (ULMSSysException se)
                {
                        se.printStackTrace();
                        throw new CourseSysException(
                                "SQLException while query  master in a ASP" + se);
                }

                return currentCatalogID;
        }

        public List getAllMaster(int aspID, int orgID, int type)
                throws CourseSysException
        {
                List list = new ArrayList();
                String aspID_str = "";
                String orgID_str = "";

                if (aspID != 0)
                {
                        aspID_str += (" and aspID=" + aspID);
                }

                if (orgID != 0)
                {
                        orgID_str += (" and orgID=" + orgID);
                }

                String strSql = "from Master where masterID > 0" + aspID_str +
                        orgID_str + " and type = '" + type + "' order by masterID desc";

                try
                {
                        list = HibernateDAO.find(strSql);
                }
                catch (ULMSSysException se)
                {
                        se.printStackTrace();
                        throw new CourseSysException(
                                "SQLException while query total master in a ASP; aspID = " +
                                        aspID + " :\n" + se);
                }

                return list;
        }

        protected void closeConnection(Connection dbConnection)
                throws CourseSysException
        {
                try
                {
                        if (dbConnection != null)
                        {
                                dbConnection.close();
                        }
                }
                catch (SQLException se)
                {
                        throw new CourseSysException(se);
                }
        }

        protected void closeResultSet(ResultSet result) throws CourseSysException
        {
                try
                {
                        if (result != null)
                        {
                                result.close();
                        }
                }
                catch (SQLException se)
                {
                        throw new CourseSysException(se);
                }
        }

        protected void closeStatement(Statement stmt) throws CourseSysException
        {
                try
                {
                        if (stmt != null)
                        {
                                stmt.close();
                        }
                }
                catch (SQLException se)
                {
                        throw new CourseSysException(se);
                }
        }

        public static void main(String[] args)
        {
                MasterDAO catDao = MasterDAOFactory.getDAO();
                System.out.println(catDao.isHierachical(7, 0));
        }
}
