/**
 * CertDAOImpl.java.
 * User: huangsb  Date: 2004-4-26
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd. * All rights reserved.
 */
package com.ulearning.ulms.admin.certificate.dao;

import com.ulearning.ulms.admin.certificate.bean.CertHelper;
import com.ulearning.ulms.admin.certificate.exceptions.CertDAOSysException;
import com.ulearning.ulms.admin.certificate.form.CertCourseForm;
import com.ulearning.ulms.admin.certificate.form.CertForm;
import com.ulearning.ulms.admin.certificate.form.CertListForm;
import com.ulearning.ulms.admin.certificate.form.CertTreeForm;
import com.ulearning.ulms.admin.certificate.model.CertCurrentStatus;
import com.ulearning.ulms.admin.certificate.model.CertModel;
import com.ulearning.ulms.core.exceptions.ULMSSysException;
import com.ulearning.ulms.core.security.bean.SecurityConstants;
import com.ulearning.ulms.core.util.Calendar;
import com.ulearning.ulms.core.util.DateTimeUtil;
import com.ulearning.ulms.core.util.I18Util;
import com.ulearning.ulms.core.util.StringUtil;
import com.ulearning.ulms.course.exceptions.CourseSysException;
import com.ulearning.ulms.course.helper.CourseHelper;
import com.ulearning.ulms.course.model.*;
import com.ulearning.ulms.course.util.CourseKeys;
import com.ulearning.ulms.util.*;
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

import javax.sql.DataSource;

import org.apache.commons.lang.StringEscapeUtils;


public class CertDAOImpl implements CertDAO
{
        protected transient Connection dbConnection = null;
        protected transient DataSource datasource = null;

        public int addCert(CertForm details) throws CertDAOSysException
        {
                int certID = 0;

                try
                {
                        Serializable s = HibernateDAO.add(details.getCertModel());
                        certID = Integer.parseInt(s.toString());
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new CertDAOSysException(
                                "SQLException while insert Certificate" + e);
                }

                return certID;
        }

        public void updateCert(CertForm details) throws CertDAOSysException
        {
                int certID = details.getCertificateID();
                CertForm newCsf = getCert(certID);

                if (details.getCatalogID() == 0)
                {
                        details.setCatalogID(newCsf.getCatalogID());
                }

                details.setOrgID(newCsf.getOrgID());

                try
                {
                        HibernateDAO.update(details.getCertModel());
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new CertDAOSysException(
                                "SQLException while update Certificate certificateID = " +
                                        certID);
                }
        }

        /**
         * @param value
         * @throws CertDAOSysException
         */
        public void createCatalog(CatalogModel value) throws CertDAOSysException
        {
                value.setEstablishTime(new Date());

                try
                {
                        HibernateDAO.add(value.getCatalog());
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new CertDAOSysException(
                                "SQLException while create Catalog ============" + e);
                }
        }

        /**
         * @param certIDs
         * @throws CertDAOSysException
         */
        public void deleteCert(List certIDs) throws CertDAOSysException
        {
                String hql;
                int i = 0;
                int certificateID;

                try
                {
                        while (i < certIDs.size())
                        {
                                certificateID = ((Integer) certIDs.get(i)).intValue();
                                //删除培训班前,首先删除相关CertCourseModel
                                hql = "from CertCourseModel where CertificateID = "+certificateID;
                                HibernateDAO.delete(hql);
                                LogUtil.debug("system", "删除相关CertCourseModel");

                                hql = " from CertModel where CertificateID=" + certificateID;
                                LogUtil.debug("system", "hql " + hql);
                                HibernateDAO.delete(hql);
                                LogUtil.debug("system", "[certDAOOracle] " + "删除 1 行");
                                i++;
                        }
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new CertDAOSysException(
                                "SQLException delete Certificate ============" + e);
                }
        }

        /**
         * @param catalogIDs
         * @throws CertDAOSysException
         */
        public void deleteCatalog(List catalogIDs) throws CertDAOSysException
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
                        throw new CertDAOSysException(
                                "SQLException while delete Catalog ============" + e);
                }
        }

        /**
         * @param catalogID
         * @throws CertDAOSysException
         */
        public CatalogModel getCatalog(int catalogID) throws CertDAOSysException
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
                        throw new CertDAOSysException(
                                "SQLException while get Catalog info=============== " + e);
                }

                return cat;
        }

        /**
         * Remove the Cert from database by the certificateID
         *
         * @param certificateID
         * @throws CertDAOSysException
         */
        public void removeCert(String certificateID) throws CertDAOSysException
        {
                Statement stmt = null;
                String hql = " from CertModel where CertificateID=" + certificateID;
                LogUtil.debug("[CertDAOImpl]", " hql = " + hql);

                try
                {
                        HibernateDAO.delete(hql);
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new CertDAOSysException(
                                "SQLException while delete Certificate ============" + e);
                }
        }

        /**
         * @param catalogID
         * @throws CertDAOSysException
         */
        public List getCatalogPath(int catalogID) throws CertDAOSysException
        {
                String name;
                Statement stmt = null;
                ResultSet rs = null;
                CatalogModel cat = null;
                ArrayList catalogPathList = new ArrayList();

                try
                {
                        dbConnection = getConnection();
                        stmt = dbConnection.createStatement();

                        String queryStr1 = "select ParentID,Name from C_Catalog_Tab" +
                                " WHERE  catalogID = " + catalogID;
                        LogUtil.debug("system",
                                "[CertDAOImpl] ==========queryStr1 = " + queryStr1);

                        rs = stmt.executeQuery(queryStr1);

                        while (rs.next())
                        {
                                name = rs.getString("Name");
                                cat = new CatalogModel(catalogID, name);
                                catalogPathList.add(cat);
                                catalogID = rs.getInt("parentID");

                                if (catalogID == 0)
                                {
                                        break;
                                }

                                String queryStr = "select ParentID,Name from C_Catalog_Tab" +
                                        " WHERE type='" + CourseKeys.CATALOG_COURSE_TYPE +
                                        "' and catalogID=" + catalogID;
                                LogUtil.debug("cert",
                                        "[CertDAOImpl] ==========queryStr = " + queryStr);

                                rs = stmt.executeQuery(queryStr);
                        }
                }
                catch (SQLException se)
                {
                        se.printStackTrace();
                        throw new CertDAOSysException("SQLException while updating " +
                                "Cert; " + "catalogID = " + catalogID + " :\n" + se);
                }
                finally
                {
                        closeResultSet(rs);
                        closeStatement(stmt);
                        closeConnection();
                }

                return catalogPathList;
        }

        public List getCatalogPath(int catalogID, int aspID)
                throws CertDAOSysException
        {
                String name;
                Statement stmt = null;
                ResultSet rs = null;
                CatalogModel cat = null;
                ArrayList catalogPathList = new ArrayList();

                try
                {
                        dbConnection = getConnection();
                        stmt = dbConnection.createStatement();

                        String queryStr1 = "select ParentID,Name from C_Catalog_Tab" +
                                " WHERE  catalogID = " + catalogID;

                        if (catalogID != 0)
                        {
                                queryStr1 += (" and OrgID=" + aspID);
                        }

                        LogUtil.debug("system",
                                "[CertDAOImpl] ==========queryStr1 = " + queryStr1);

                        rs = stmt.executeQuery(queryStr1);

                        while (rs.next())
                        {
                                name = rs.getString("Name");
                                cat = new CatalogModel(catalogID, name);
                                catalogPathList.add(cat);
                                catalogID = rs.getInt("parentID");

                                if (catalogID == 0)
                                {
                                        break;
                                }

                                String queryStr = "select ParentID,Name from C_Catalog_Tab" +
                                        " WHERE type='" + CourseKeys.CATALOG_COURSE_TYPE +
                                        "' and catalogID=" + catalogID;

                                if (catalogID != 0)
                                {
                                        queryStr += (" and OrgID=" + aspID);
                                }

                                LogUtil.debug("cert",
                                        "[CertDAOImpl] ==========queryStr = " + queryStr);

                                rs = stmt.executeQuery(queryStr);
                        }
                }
                catch (SQLException se)
                {
                        se.printStackTrace();
                        throw new CertDAOSysException("SQLException while updating " +
                                "Cert; " + "catalogID = " + catalogID + " :\n" + se);
                }
                finally
                {
                        closeResultSet(rs);
                        closeStatement(stmt);
                        closeConnection();
                }

                return catalogPathList;
        }

        public List getCatalogPath(int catalogID, int aspID, int orgID)
                throws CertDAOSysException
        {
                String name;
                Statement stmt = null;
                ResultSet rs = null;
                CatalogModel cat = null;
                ArrayList catalogPathList = new ArrayList();

                try
                {
                        String aspID_str = "";
                        String orgID_str = "";

                        if (aspID != 0)
                        {
                                aspID_str += (" and aspid=" + aspID);
                        }

                        if (orgID != 0)
                        {
                                orgID_str += (" and orgid=" + orgID);
                        }

                        dbConnection = getConnection();
                        stmt = dbConnection.createStatement();

                        String queryStr1 = "select ParentID,Name from C_Catalog_Tab" +
                                " WHERE  catalogID = " + catalogID;

                        if (catalogID != 0)
                        {
                                queryStr1 += (aspID_str + orgID_str);
                        }

                        LogUtil.debug("system",
                                "[CertDAOImpl] ==========queryStr1 = " + queryStr1);

                        rs = stmt.executeQuery(queryStr1);

                        while (rs.next())
                        {
                                name = rs.getString("Name");
                                cat = new CatalogModel(catalogID, name);
                                catalogPathList.add(cat);
                                catalogID = rs.getInt("parentID");

                                if (catalogID == 0)
                                {
                                        break;
                                }

                                String queryStr = "select ParentID,Name from C_Catalog_Tab" +
                                        " WHERE type='" + CourseKeys.CATALOG_COURSE_TYPE +
                                        "' and catalogID=" + catalogID;

                                if (catalogID != 0)
                                {
                                        queryStr += (aspID_str + orgID_str);
                                }

                                LogUtil.debug("cert",
                                        "[CertDAOImpl] ==========queryStr = " + queryStr);

                                rs = stmt.executeQuery(queryStr);
                        }
                }
                catch (SQLException se)
                {
                        se.printStackTrace();
                        throw new CertDAOSysException("SQLException while updating " +
                                "Cert; " + "catalogID = " + catalogID + " :\n" + se);
                }
                finally
                {
                        closeResultSet(rs);
                        closeStatement(stmt);
                        closeConnection();
                }

                return catalogPathList;
        }

        /**
         * @param catalogID
         * @throws CertDAOSysException
         */
        public CertTreeForm getTree(int catalogID) throws CertDAOSysException
        {
                String name;
                String code;
                String description;
                int certificateID;
                Statement stmt = null;
                ResultSet rs = null;

                CertForm mas = null;
                CatalogModel cat = null;

                ArrayList certs = new ArrayList();
                ArrayList catalogs = new ArrayList();

                CertListForm certList = new CertListForm(certs);
                CatalogListModel catalogList = new CatalogListModel(catalogs);

                CertTreeForm mt = new CertTreeForm(certList, catalogList);

                try
                {
                        dbConnection = getConnection();
                        stmt = dbConnection.createStatement();

                        String sql = "SELECT CertificateID,Name,Code,Description FROM Cer_Certificate_Tab WHERE certificateID!=0 and catalogID=" +
                                catalogID + " order by certificateID desc";

                        LogUtil.debug("system", "[CertDAOImpl]  sql=" + sql);
                        stmt.execute(sql);

                        rs = stmt.getResultSet();

                        while (rs.next())
                        {
                                certificateID = rs.getInt("CertificateID");
                                name = rs.getString("Name");
                                code = rs.getString("Code");
                                description = rs.getString("Description");
                                mas = new CertForm(certificateID, name, code, description);
                                certs.add(mas);
                        }

                        LogUtil.debug("system",
                                "[CertDAOImpl] ******************masters size" + certs.size());
                        sql = "SELECT CatalogID,Name,Description FROM C_CATALOG_TAB WHERE catalogID!=0 " +
                                " and type='" + CourseKeys.CATALOG_COURSE_TYPE +
                                "' and parentID=" + catalogID + " order by CatalogID desc";
                        LogUtil.debug("cert", "[CertDAOImpl]  " + sql);
                        stmt.execute(sql);

                        rs = stmt.getResultSet();

                        while (rs.next())
                        {
                                name = rs.getString("Name");
                                description = rs.getString("Description");
                                cat = new CatalogModel(rs.getInt("CatalogID"), name, description);
                                catalogs.add(cat);
                        }

                        LogUtil.debug("system",
                                "[CertDAOImpl] ******************catalogs size" +
                                        catalogs.size());
                        LogUtil.debug("system", "[CertDAOImpl]getTree over" + "\n");
                }
                catch (SQLException se)
                {
                        se.printStackTrace();
                        throw new CertDAOSysException("SQLException while updating " +
                                "Cert; " + "catalogID = " + catalogID + " :\n" + se);
                }
                finally
                {
                        closeResultSet(rs);
                        closeStatement(stmt);
                        closeConnection();
                }

                return mt;
        }

        /**
         * @param catalogID
         * @throws CertDAOSysException
         */
        public CertTreeForm getTree(int catalogID, int aspID)
                throws CertDAOSysException
        {
                String name;
                String code;
                String description;
                int certificateID;
                Statement stmt = null;
                ResultSet rs = null;

                CertForm mas = null;
                CatalogModel cat = null;

                ArrayList certs = new ArrayList();
                ArrayList catalogs = new ArrayList();

                CertListForm certList = new CertListForm(certs);
                CatalogListModel catalogList = new CatalogListModel(catalogs);

                CertTreeForm mt = new CertTreeForm(certList, catalogList);

                try
                {
                        dbConnection = getConnection();
                        stmt = dbConnection.createStatement();

                        String sql = "SELECT CertificateID,Name,Code,Description FROM Cer_Certificate_Tab WHERE certificateID!=0 " +
                                " and catalogID = " + catalogID + " and orgID = " + aspID +
                                " order by certificateID desc";

                        LogUtil.debug("system", "[CertDAOImpl]  sql=" + sql);
                        stmt.execute(sql);

                        rs = stmt.getResultSet();

                        while (rs.next())
                        {
                                certificateID = rs.getInt("CertificateID");
                                name = rs.getString("Name");
                                code = rs.getString("Code");
                                description = rs.getString("Description");
                                mas = new CertForm(certificateID, name, code, description);
                                certs.add(mas);
                        }

                        LogUtil.debug("system",
                                "[CertDAOImpl] ******************masters size" + certs.size());
                        sql = "SELECT CatalogID,Name,Description FROM C_CATALOG_TAB WHERE catalogID!=0 " +
                                " and type='" + CourseKeys.CATALOG_COURSE_TYPE +
                                "' and parentID=" + catalogID + " and orgID = " + aspID +
                                " order by CatalogID desc";
                        LogUtil.debug("cert", "[CertDAOImpl]  " + sql);
                        stmt.execute(sql);

                        rs = stmt.getResultSet();

                        while (rs.next())
                        {
                                name = rs.getString("Name");
                                description = rs.getString("Description");
                                cat = new CatalogModel(rs.getInt("CatalogID"), name, description);
                                catalogs.add(cat);
                        }

                        LogUtil.debug("system",
                                "[CertDAOImpl] ******************catalogs size" +
                                        catalogs.size());
                        LogUtil.debug("system", "[CertDAOImpl]getTree over" + "\n");
                }
                catch (SQLException se)
                {
                        se.printStackTrace();
                        throw new CertDAOSysException("SQLException while updating " +
                                "Cert; " + "catalogID = " + catalogID + " :\n" + se);
                }
                finally
                {
                        closeResultSet(rs);
                        closeStatement(stmt);
                        closeConnection();
                }

                return mt;
        }

        /**
         * @param catalogID
         * @throws CertDAOSysException
         */
        public CertTreeForm getTree(int catalogID, int aspID, int orgID)
                throws CertDAOSysException
        {
                String name;
                String code;
                String description;
                int certificateID;
                Statement stmt = null;
                ResultSet rs = null;

                CertForm mas = null;
                CatalogModel cat = null;

                ArrayList certs = new ArrayList();
                ArrayList catalogs = new ArrayList();

                CertListForm certList = new CertListForm(certs);
                CatalogListModel catalogList = new CatalogListModel(catalogs);

                CertTreeForm mt = new CertTreeForm(certList, catalogList);

                try
                {
                        dbConnection = getConnection();
                        stmt = dbConnection.createStatement();

                        String aspID_str = "";
                        String orgID_str = "";

                        if (aspID != 0)
                        {
                                aspID_str += (" and aspid=" + aspID);
                        }

                        if (orgID != 0)
                        {
                                orgID_str += (" and orgid=" + orgID);
                        }

                        String sql = "SELECT CertificateID,Name,Code,Description FROM Cer_Certificate_Tab WHERE certificateID!=0 " +
                                " and catalogID = " + catalogID + aspID_str + orgID_str +
                                " order by certificateID desc";

                        LogUtil.debug("system", "[CertDAOImpl]  sql=" + sql);
                        stmt.execute(sql);

                        rs = stmt.getResultSet();

                        while (rs.next())
                        {
                                certificateID = rs.getInt("CertificateID");
                                name = rs.getString("Name");
                                code = rs.getString("Code");
                                description = rs.getString("Description");
                                mas = new CertForm(certificateID, name, code, description);
                                certs.add(mas);
                        }

                        LogUtil.debug("system",
                                "[CertDAOImpl] ******************masters size" + certs.size());
                        sql = "SELECT CatalogID,Name,Description FROM C_CATALOG_TAB WHERE catalogID!=0 " +
                                " and type='" + CourseKeys.CATALOG_COURSE_TYPE +
                                "' and parentID=" + catalogID + aspID_str +
                                " order by CatalogID desc";
                        LogUtil.debug("cert", "[CertDAOImpl]  " + sql);
                        stmt.execute(sql);

                        rs = stmt.getResultSet();

                        while (rs.next())
                        {
                                name = rs.getString("Name");
                                description = rs.getString("Description");
                                cat = new CatalogModel(rs.getInt("CatalogID"), name, description);
                                catalogs.add(cat);
                        }

                        LogUtil.debug("system",
                                "[CertDAOImpl] ******************catalogs size" +
                                        catalogs.size());
                        LogUtil.debug("system", "[CertDAOImpl]getTree over" + "\n");
                }
                catch (SQLException se)
                {
                        se.printStackTrace();
                        throw new CertDAOSysException("SQLException while updating " +
                                "Cert; " + "catalogID = " + catalogID + " :\n" + se);
                }
                finally
                {
                        closeResultSet(rs);
                        closeStatement(stmt);
                        closeConnection();
                }

                return mt;
        }

        public void updateCatalog(CatalogModel value) throws CertDAOSysException
        {
                int catalogID = value.getCatalogID();
                CatalogModel cm = getCatalog(catalogID);
                cm.setName(value.getName());
                cm.setDescription(value.getDescription());

                try
                {
                        HibernateDAO.update(cm.getCatalog());
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new CertDAOSysException(
                                "SQLException while updating Catalog catalogID = " + catalogID +
                                        "==============" + e);
                }
        }

        /**
         * Get the Cert info via the unique certificateID
         *
         * @param certificateID
         * @return the prepared CertForm, default is null
         * @throws CertDAOSysException
         */
        public CertForm getCert(int certificateID) throws CertDAOSysException
        {
                CertForm cf = null;
                String hql = " from CertModel where CertificateID = " + certificateID;

                try
                {
                        LogUtil.debug("system",
                                "[CertDAOOracle]====================the hql string is : " +
                                        hql);

                        List tmList = HibernateDAO.find(hql);
                        CertModel cm = null;

                        if ((tmList != null) && (tmList.size() > 0))
                        {
                                cm = (CertModel) tmList.get(0);
                                cf = new CertForm(cm);
                        }
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new CertDAOSysException(
                                "SQLException while get Cert CertID = " + certificateID +
                                        "==============" + e);
                }

                return cf;
        }

        /**
         * 根据证书编号返回 CertForm . <br>
         *
         * @param certCode
         * @return
         * @throws CertDAOSysException
         */
        public CertForm getCert(int aspID, String certCode)
                throws CertDAOSysException
        {
                CertForm cf = null;
                String hql = " from CertModel where code = '" + certCode + "'" +
                        " and orgID=" + aspID;
                ;

                try
                {
                        LogUtil.debug("system",
                                "[CertDAOOracle]====================the hql string is : " +
                                        hql);

                        List tmList = HibernateDAO.find(hql);
                        CertModel cm = null;

                        if ((tmList != null) && (tmList.size() > 0))
                        {
                                cm = (CertModel) tmList.get(0);
                                cf = new CertForm(cm);
                        }
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new CertDAOSysException(
                                "SQLException while get Cert certCode = " + certCode +
                                        "==============" + e);
                }

                return cf;
        }

        public CertForm getCert(int aspID, int orgID, String certCode)
                throws CertDAOSysException
        {
                CertForm cf = null;
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

                String hql = " from CertModel where code = '" + certCode + "'" +
                        aspID_str + orgID_str;
                ;

                try
                {
                        LogUtil.debug("system",
                                "[CertDAOOracle]====================the hql string is : " +
                                        hql);

                        List tmList = HibernateDAO.find(hql);
                        CertModel cm = null;

                        if ((tmList != null) && (tmList.size() > 0))
                        {
                                cm = (CertModel) tmList.get(0);
                                cf = new CertForm(cm);
                        }
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new CertDAOSysException(
                                "SQLException while get Cert certCode = " + certCode +
                                        "==============" + e);
                }

                return cf;
        }

        public int getCertID(String certCode, int aspID) throws CertDAOSysException
        {
                Statement stmt = null;
                ResultSet rs = null;
                int certID = 0;
                String hql = " from CertModel where code = '" + certCode +
                        "' and orgID = " + aspID;

                try
                {
                        LogUtil.debug("system",
                                "[CertDAOImpl]====================hql sql string is : " + hql);

                        List tmList = HibernateDAO.find(hql);
                        CertModel cm = null;

                        if ((tmList != null) && (tmList.size() > 0))
                        {
                                cm = (CertModel) tmList.get(0);
                                certID = cm.getCertificateID();
                        }
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new CertDAOSysException(
                                "SQLException while get  CertID where code = " + certCode +
                                        "==============" + e);
                }

                return certID;
        }

        public int getCertID(String certCode, int aspID, int orgID)
                throws CertDAOSysException
        {
                Statement stmt = null;
                ResultSet rs = null;
                int certID = 0;
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

                String hql = " from CertModel where code = '" + certCode + "'" +
                        aspID_str + orgID_str;

                try
                {
                        LogUtil.debug("system",
                                "[CertDAOImpl]====================hql sql string is : " + hql);

                        List tmList = HibernateDAO.find(hql);
                        CertModel cm = null;

                        if ((tmList != null) && (tmList.size() > 0))
                        {
                                cm = (CertModel) tmList.get(0);
                                certID = cm.getCertificateID();
                        }
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new CertDAOSysException(
                                "SQLException while get  CertID where code = " + certCode +
                                        "==============" + e);
                }

                return certID;
        }

        /**
         * Get the plan list by the orgID
         *
         * @param catalogID
         * @return The prepared arraylist object,default size is 0
         * @throws CertDAOSysException
         */
        public List getCertList(int catalogID) throws CertDAOSysException
        {
                ArrayList planList = new ArrayList();
                CertForm cf = null;
                String hql = " from CertModel where CatalogID = " + catalogID +
                        " ORDER BY CertificateID desc";

                try
                {
                        LogUtil.debug("system",
                                "[CertDAOImpl.getCertList(int catalogID)]====================the sql string is : " +
                                        hql);

                        List tmList = HibernateDAO.find(hql);
                        CertModel cm = null;

                        for (int i = 0; i < tmList.size(); i++)
                        {
                                cm = (CertModel) tmList.get(i);
                                cf = new CertForm(cm);
                                planList.add(cf);
                        }
                }
                catch (ULMSSysException se)
                {
                        throw new CertDAOSysException("SQLException while updating " +
                                "Plan; " + "catalogID = " + catalogID + " :\n" + se);
                }

                return planList;
        }

        public List getCertList(int catalogID, int aspID)
                throws CertDAOSysException
        {
                CertForm cf = null;
                ArrayList certList = new ArrayList();
                String hql = " from CertModel where CatalogID = " + catalogID +
                        " and orgID = " + aspID + " ORDER BY CertificateID desc";

                try
                {
                        LogUtil.debug("system",
                                "[CertDAOImpl]====================the sql string is : " + hql);

                        List tmList = HibernateDAO.find(hql);
                        CertModel cm = null;

                        for (int i = 0; i < tmList.size(); i++)
                        {
                                cm = (CertModel) tmList.get(i);
                                cf = new CertForm(cm);
                                certList.add(cf);
                        }
                }
                catch (ULMSSysException se)
                {
                        throw new CertDAOSysException("SQLException while updating " +
                                "Plan; " + "catalogID = " + catalogID + " :\n" + se);
                }

                return certList;
        }

        public List getCertList(int catalogID, int aspID, int orgID)
                throws CertDAOSysException
        {
                CertForm cf = null;
                ArrayList certList = new ArrayList();
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

                String hql = " from CertModel where CatalogID = " + catalogID +
                        aspID_str + orgID_str + " ORDER BY CertificateID desc";

                try
                {
                        LogUtil.debug("system",
                                "[CertDAOImpl]====================the sql string is : " + hql);

                        List tmList = HibernateDAO.find(hql);
                        CertModel cm = null;

                        for (int i = 0; i < tmList.size(); i++)
                        {
                                cm = (CertModel) tmList.get(i);
                                cf = new CertForm(cm);
                                certList.add(cf);
                        }
                }
                catch (ULMSSysException se)
                {
                        throw new CertDAOSysException("SQLException while updating " +
                                "Plan; " + "catalogID = " + catalogID + " :\n" + se);
                }

                return certList;
        }

        public List getMyStudyingCertList(int userID) throws CertDAOSysException
        {
                Statement stmt = null;
                CertForm cf = null;
                ResultSet rs = null;
                List certList = new ArrayList();

                String sqlStr = "SELECT * FROM Cer_Certificate_Tab c,sec_userRole_tab s " +
                        " WHERE s.TYPE = " + SecurityConstants.USER_CERTIFICATE_RELATION +
                        " AND c.CERTIFICATEID = s.RELATIONID AND s.USERID = " + userID +
                        " AND s.ROLEID = " + SecurityConstants.ROLE_COURSR_STUDENT;

                try
                {
                        dbConnection = getConnection();
                        stmt = dbConnection.createStatement();
                        LogUtil.debug("system",
                                "[CertDAOImpl]====================the sql string is : " +
                                        sqlStr);
                        rs = stmt.executeQuery(sqlStr);

                        while (rs.next())
                        {
                                cf = convertRs2Form(rs);
                                certList.add(cf);
                        }
                }
                catch (SQLException se)
                {
                        throw new CertDAOSysException("SQLException while updating " +
                                "Plan; " + "catalogID = " + userID + " :\n" + se);
                }
                finally
                {
                        closeResultSet(rs);
                        closeStatement(stmt);
                        closeConnection();
                }

                return certList;
        }

        public List getMymanagingCertList(int userID) throws CertDAOSysException
        {
                Statement stmt = null;
                CertForm cf = null;
                ResultSet rs = null;
                List certList = new ArrayList();
                String sqlStr = "SELECT * FROM Cer_Certificate_Tab c,sec_userRole_tab s " +
                        " WHERE s.TYPE = " + SecurityConstants.USER_CERTIFICATE_RELATION +
                        " AND c.CERTIFICATEID = s.RELATIONID AND s.USERID = " + userID +
                        " AND (s.ROLEID = " + SecurityConstants.ROLE_COURSE_TEACHER +
                        " OR s.ROLEID = " + SecurityConstants.ROLE_COURSE_ADMINISTRATOR +
                        ")";

                try
                {
                        dbConnection = getConnection();
                        stmt = dbConnection.createStatement();
                        LogUtil.debug("system",
                                "[CertDAOImpl]====================the sql string is : " +
                                        sqlStr);
                        rs = stmt.executeQuery(sqlStr);

                        while (rs.next())
                        {
                                cf = convertRs2Form(rs);
                                certList.add(cf);
                        }
                }
                catch (SQLException se)
                {
                        throw new CertDAOSysException("SQLException while updating " +
                                "Plan; " + "catalogID = " + userID + " :\n" + se);
                }
                finally
                {
                        closeResultSet(rs);
                        closeStatement(stmt);
                        closeConnection();
                }

                return certList;
        }

        public List search(String certKey) throws CertDAOSysException
        {
                Statement stmt = null;
                CertForm cf = null;
                ResultSet rs = null;
                ArrayList planList = new ArrayList();
                certKey= StringEscapeUtils.escapeSql(certKey);
                String sqlStr = "select * from Cer_Certificate_Tab where " +
                        "KeyWord LIKE '%" + certKey + "%' OR " + "Code LIKE '%" + certKey +
                        "%' OR " + "Name LIKE '%" + certKey + "%' ORDER BY CertificateID desc";

                try
                {
                        dbConnection = getConnection();
                        stmt = dbConnection.createStatement();
                        LogUtil.debug("system",
                                "[CertDAOImpl]====================the sql string is : " +
                                        sqlStr);
                        rs = stmt.executeQuery(sqlStr);

                        while (rs.next())
                        {
                                cf = convertRs2Form(rs);
                                planList.add(cf);
                        }
                }
                catch (SQLException se)
                {
                        throw new CertDAOSysException("SQLException while searching cert " +
                                "Plan; " + " cerKey = " + certKey + " :\n" + se);
                }
                finally
                {
                        closeResultSet(rs);
                        closeStatement(stmt);
                        closeConnection();
                }

                return planList;
        }

        public List search(String certKey, int aspID) throws CertDAOSysException
        {
                Statement stmt = null;
                CertForm cf = null;
                ResultSet rs = null;
                ArrayList planList = new ArrayList();
                certKey= StringEscapeUtils.escapeSql(certKey);
                String sqlStr = "select * from Cer_Certificate_Tab where OrgID = " +
                        aspID + "  and (" + "KeyWord LIKE '%" + certKey + "%' OR " +
                        "Code LIKE '%" + certKey + "%' OR " + "Name LIKE '%" + certKey +
                        "%') ORDER BY CertificateID desc";

                try
                {
                        dbConnection = getConnection();
                        stmt = dbConnection.createStatement();
                        LogUtil.debug("system",
                                "[CertDAOImpl]====================the sql string is : " +
                                        sqlStr);
                        rs = stmt.executeQuery(sqlStr);

                        while (rs.next())
                        {
                                cf = convertRs2Form(rs);
                                planList.add(cf);
                        }
                }
                catch (SQLException se)
                {
                        throw new CertDAOSysException("SQLException while searching cert " +
                                "Plan; " + " cerKey = " + certKey + " :\n" + se);
                }
                finally
                {
                        closeResultSet(rs);
                        closeStatement(stmt);
                        closeConnection();
                }

                return planList;
        }

        public List search(String certKey, int aspID, int orgID)
                throws CertDAOSysException
        {
                Statement stmt = null;
                CertForm cf = null;
                ResultSet rs = null;
                ArrayList planList = new ArrayList();
                String aspID_str = "";
                String orgID_str = "";
                certKey= StringEscapeUtils.escapeSql(certKey);
                if (aspID != 0)
                {
                        aspID_str += (" and aspID=" + aspID);
                }

                if (orgID != 0)
                {
                        orgID_str += (" and orgID=" + orgID);
                }

                String sqlStr = "select * from Cer_Certificate_Tab where certificateID>0 " +
                        aspID_str + orgID_str + "  and (" + "KeyWord LIKE '%" + certKey +
                        "%' OR " + "Code LIKE '%" + certKey + "%' OR " + "Name LIKE '%" +
                        certKey + "%') ORDER BY CertificateID desc ";

                try
                {
                        dbConnection = getConnection();
                        stmt = dbConnection.createStatement();
                        LogUtil.debug("system",
                                "[CertDAOImpl]====================the sql string is : " +
                                        sqlStr);
                        rs = stmt.executeQuery(sqlStr);

                        while (rs.next())
                        {
                                cf = convertRs2Form(rs);
                                planList.add(cf);
                        }
                }
                catch (SQLException se)
                {
                        throw new CertDAOSysException("SQLException while searching cert " +
                                "Plan; " + " cerKey = " + certKey + " :\n" + se);
                }
                finally
                {
                        closeResultSet(rs);
                        closeStatement(stmt);
                        closeConnection();
                }

                return planList;
        }

        public void addCourseToCert(CertCourseForm ccf) throws CertDAOSysException
        {
                try
                {
                        HibernateDAO.add(ccf.getModel());
                }
                catch (ULMSSysException se)
                {
                        throw new CertDAOSysException("SQLException while updating " +
                                "cert_course ; " + "courseID() = " + ccf.getCourseID() +
                                " :\n" + se);
                }
        }

        public void updateCertCourse(CertCourseForm ccf) throws CertDAOSysException
        {
                try
                {
                        HibernateDAO.update(ccf.getModel());
                }
                catch (ULMSSysException se)
                {
                        throw new CertDAOSysException("SQLException while updating " +
                                "cert_course ; " + "courseID() = " + ccf.getCourseID() +
                                " :\n" + se);
                }
        }

        public void removeCourseFromCert(CertCourseForm ccf)
                throws CertDAOSysException
        {
                String hql = "from CertCourseModel where CertificateID = " +
                        ccf.getCertificateID() + " and CourseID = " + ccf.getCourseID() +
                        " and type = " + ccf.getType();

                try
                {
                        LogUtil.debug("system",
                                "[CertDAOImpl]====================the sql string is : " + hql);
                        HibernateDAO.delete(hql);
                }
                catch (ULMSSysException se)
                {
                        throw new CertDAOSysException("SQLException while updating " +
                                "cert_course ; " + "courseID() = " + ccf.getCourseID() +
                                " :\n" + se);
                }
        }

        public void removeCourseFormCert(int courseID) throws CertDAOSysException
        {
                String hql = "from CertCourseModel where CourseID  = " + courseID;

                try
                {
                        LogUtil.debug("system",
                                "[CertDAOImpl]====================the sql string is : " + hql);
                        HibernateDAO.delete(hql);
                }
                catch (ULMSSysException se)
                {
                        throw new CertDAOSysException("SQLException while updating " +
                                "cert_course ; " + "courseID() = " + courseID + " :\n" + se);
                }
        }

        public List getMyCertList(int userID) throws CertDAOSysException
        {
                Statement stmt = null;
                CertForm cf = null;
                ResultSet rs = null;
                List certList = new ArrayList();

                String sqlStr = "SELECT * FROM Cer_Certificate_Tab c, C_User_Tab cut " +
                        " WHERE cut.TYPE = " + SecurityConstants.USER_CERTIFICATE_RELATION +
                        " AND cut.relationID = c.CERTIFICATEID" + " AND  cut.USERID = " +
                        userID + " And c.status='" + +CourseKeys.COURSE_AVAILABLE_STATE +
                        "'  And  cut.state='" + CourseKeys.COURSE_USER_AVAILABLE_STATE +
                        "'" + " order by certificateID desc";

                try
                {
                        dbConnection = getConnection();
                        stmt = dbConnection.createStatement();
                        LogUtil.debug("system",
                                "[CertDAOImpl]====================the sql string is : " +
                                        sqlStr);
                        rs = stmt.executeQuery(sqlStr);

                        while (rs.next())
                        {
                                cf = convertRs2Form(rs);
                                certList.add(cf);
                        }
                }
                catch (SQLException se)
                {
                        throw new CertDAOSysException("SQLException while updating " +
                                "Plan; " + "catalogID = " + userID + " :\n" + se);
                }
                finally
                {
                        closeResultSet(rs);
                        closeStatement(stmt);
                        closeConnection();
                }

                return certList;
        }

        public List getNotMyCertList(int userID) throws CertDAOSysException
        {
                Statement stmt = null;
                CertForm cf = null;
                ResultSet rs = null;
                List certList = new ArrayList();

                String sqlStr = "SELECT * FROM Cer_Certificate_Tab c, C_User_Tab cut " +
                        " WHERE cut.TYPE = 3 AND cut.relationID = c.CERTIFICATEID" +
                        " AND  cut.USERID = " + userID + " And c.status='" +
                        +CourseKeys.COURSE_AVAILABLE_STATE + "'  And  cut.state='" +
                        CourseKeys.COURSE_USER_AVAILABLE_STATE + "'" +
                        " order by certificateID desc";

                try
                {
                        dbConnection = getConnection();
                        stmt = dbConnection.createStatement();
                        LogUtil.debug("system",
                                "[CertDAOImpl]====================the sql string is : " +
                                        sqlStr);
                        rs = stmt.executeQuery(sqlStr);

                        while (rs.next())
                        {
                                cf = convertRs2Form(rs);
                                certList.add(cf);
                        }
                }
                catch (SQLException se)
                {
                        throw new CertDAOSysException("SQLException while updating " +
                                "Plan; " + "catalogID = " + userID + " :\n" + se);
                }
                finally
                {
                        closeResultSet(rs);
                        closeStatement(stmt);
                        closeConnection();
                }

                return certList;
        }

        /**
         * 判断课程是否独立（属于一个培训班?)
         *
         * @param courseID
         * @return
         * @throws CertDAOSysException
         */
        public boolean isCourseAlone(int courseID) throws CertDAOSysException
        {
                Statement stmt = null;
                String sqlStr = "select * from  Cer_Course_Tab where courseID = " +
                        courseID + " and type = " + LMSConstants.LEARNING_TUTORIAL;
                dbConnection = getConnection();

                ResultSet rs = null;

                try
                {
                        stmt = dbConnection.createStatement();
                        LogUtil.debug("system",
                                "[CertDAOImpl]====================the sql string is : " +
                                        sqlStr);
                        rs = stmt.executeQuery(sqlStr);

                        return !rs.next();
                }
                catch (SQLException se)
                {
                        throw new CertDAOSysException(se.getMessage());
                }
                finally
                {
                        closeResultSet(rs);
                        closeStatement(stmt);
                        closeConnection();
                }
        }

        /**
         * 当type= LMSConstants.LEARNING_TUTORIAL_MASTER,
         * <br> 返回证书对应范本的所有发布课程         *
         *
         * @param certificateID
         * @param type          1 返回证书对应的发布课程
         *                      2 返回证书末使用范本的课程
         * @return
         * @throws CertDAOSysException
         */
        public List getCourseListFromCert(String certificateID, int type)
                throws CertDAOSysException
        {
                Statement stmt = null;
                String sqlStr = "select * from  Cer_Course_Tab where CertificateID = " +
                        certificateID + " and type = " + type;

                if (type == LMSConstants.LEARNING_TUTORIAL_MASTER)
                {
                        sqlStr = "SELECT * FROM C_Course_Tab WHERE masterID IN " +
                                "(SELECT courseID FROM  Cer_Course_Tab WHERE CertificateID = " +
                                certificateID + " AND TYPE = " + LMSConstants.LEARNING_MASTER +
                                ")";
                }

                if (type == LMSConstants.LEARNING_DEFAULT)
                {
                        sqlStr = "SELECT * FROM C_Course_Tab WHERE masterID = 0";
                }

                ResultSet rs = null;
                List result = new ArrayList();
                System.out.println(sqlStr);

                try
                {
                        dbConnection = getConnection();
                        stmt = dbConnection.createStatement();
                        LogUtil.debug("system",
                                "[CertDAOImpl]====================the sql string is : " +
                                        sqlStr);
                        rs = stmt.executeQuery(sqlStr);

                        int relationID = 0;

                        while (rs.next())
                        {
                                relationID = rs.getInt("courseID");

                                if (type == LMSConstants.LEARNING_MASTER)
                                {
                                        MasterModel mm = (MasterModel) CourseHelper.getMaster(relationID);

                                        if (mm != null)
                                        {
                                                result.add(mm);
                                        }
                                }
                                else
                                {
                                        CourseModel cm = CourseHelper.getCourse(relationID);

                                        if (cm != null)
                                        {
                                                if ((type != LMSConstants.LEARNING_TUTORIAL_MASTER) &&
                                                        (type != LMSConstants.LEARNING_DEFAULT))
                                                {
                                                        cm.setCredit(rs.getFloat("credit"));
                                                        cm.setPeriod(rs.getInt("period"));
                                                        cm.setType(rs.getString("courseType"));
                                                }

                                                result.add(cm);
                                        }
                                }
                        }
                }
                catch (SQLException se)
                {
                        throw new CertDAOSysException(se);
                }
                catch (CourseSysException cse)
                {
                        throw new CertDAOSysException(
                                "CourseSysException can't get course from courseHelper" +
                                        " certificateID = " + certificateID + " :\n" + cse, cse);
                }
                finally
                {
                        closeResultSet(rs);
                        closeStatement(stmt);
                        closeConnection();
                }

                return result;
        }

        public int getAspTotalCert(int aspID, String startDates, String endDates)
                throws CertDAOSysException
        {
                int total = 0;

                Date startDate = DateTimeUtil.parseDate(startDates);
                Date endDate = DateTimeUtil.parseDate(endDates);

                LogUtil.info("common",
                        "[CertDAOImpl]getAspTotalCert--endDate0=" +
                                I18Util.FormatDateTime(endDate, Locale.CHINA));

                com.ulearning.ulms.core.util.Calendar calendar = new com.ulearning.ulms.core.util.Calendar(endDate);
                endDate = calendar.nextDay();

                LogUtil.info("common",
                        "[CertDAOImpl]getAspTotalCert--startDate=" +
                                I18Util.FormatDateTime(startDate, Locale.CHINA));
                LogUtil.info("common",
                        "[CertDAOImpl]getAspTotalCert--endDate=" +
                                I18Util.FormatDateTime(endDate, Locale.CHINA));

                String hql = "select count(*) from CertModel where " + " aspID = " +
                        aspID + " and  (LifeForever='1' or  (LifeBeginDate >= :startDate" +
                        " and LifeEndDate <= :endDate))";
                LogUtil.info("course", "[CertDAOImpl]getAspTotalCert--hql=" + hql);

                Session session = null;

                try
                {
                        session = HibernateUtil.getSession();

                        Query query = session.createQuery(hql);

                        query.setDate("startDate", startDate);
                        query.setDate("endDate", endDate);

                        Iterator it = query.iterate();

                        if ((it != null) && it.hasNext())
                        {
                                Integer amount = (Integer) it.next();
                                total = amount.intValue();
                        }
                }
                catch (HibernateException e)
                {
                        e.printStackTrace();
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
                        }
                }

                return total;
        }

        public int getAspTotalCert(int aspID, int orgID, String startDates,
                                   String endDates) throws CertDAOSysException
        {
                int total = 0;

                Date startDate = DateTimeUtil.parseDate(startDates);
                Date endDate = DateTimeUtil.parseDate(endDates);

                LogUtil.info("common",
                        "[CertDAOImpl]getAspTotalCert--endDate0=" +
                                I18Util.FormatDateTime(endDate, Locale.CHINA));

                com.ulearning.ulms.core.util.Calendar calendar = new com.ulearning.ulms.core.util.Calendar(endDate);
                endDate = calendar.nextDay();

                LogUtil.info("common",
                        "[CertDAOImpl]getAspTotalCert--startDate=" +
                                I18Util.FormatDateTime(startDate, Locale.CHINA));
                LogUtil.info("common",
                        "[CertDAOImpl]getAspTotalCert--endDate=" +
                                I18Util.FormatDateTime(endDate, Locale.CHINA));

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

                String hql = "select count(*) from CertModel where " +
                        "  (LifeForever='1' or  (LifeBeginDate >= :startDate" +
                        " and LifeEndDate <= :endDate))" + aspID_str + orgID_str;
                LogUtil.info("course", "[CertDAOImpl]getAspTotalCert--hql=" + hql);

                Session session = null;

                try
                {
                        session = HibernateUtil.getSession();

                        Query query = session.createQuery(hql);

                        query.setDate("startDate", startDate);
                        query.setDate("endDate", endDate);

                        Iterator it = query.iterate();

                        if ((it != null) && it.hasNext())
                        {
                                Integer amount = (Integer) it.next();
                                total = amount.intValue();
                        }
                }
                catch (HibernateException e)
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

        public List getAspCertInfo(int aspID, String startDates, String endDates)
                throws CertDAOSysException
        {
                Date startDate = DateTimeUtil.parseDate(startDates);
                Date endDate = DateTimeUtil.parseDate(endDates);

                LogUtil.info("common",
                        "[CertDAOImpl]getAspCertInfo--endDate0=" +
                                I18Util.FormatDateTime(endDate, Locale.CHINA));

                com.ulearning.ulms.core.util.Calendar calendar = new com.ulearning.ulms.core.util.Calendar(endDate);
                endDate = calendar.nextDay();

                LogUtil.info("common",
                        "[CertDAOImpl]getAspCertInfo--startDate=" +
                                I18Util.FormatDateTime(startDate, Locale.CHINA));
                LogUtil.info("common",
                        "[CertDAOImpl]getAspCertInfo--endDate=" +
                                I18Util.FormatDateTime(endDate, Locale.CHINA));

                List certList = new ArrayList();

                Session session = null;

                String hql = "from CertModel where " + " aspID = " + aspID +
                        " and  (LifeForever='1' or  (LifeBeginDate >= :startDate" +
                        " and LifeEndDate <= :endDate))" + " ORDER BY CertificateID desc";
                LogUtil.info("course", "[CertDAOImpl]getAspCertInfo--hql=" + hql);

                try
                {
                        session = HibernateUtil.getSession();

                        Query query = session.createQuery(hql);
                        query.setParameter("startDate", startDate);
                        query.setParameter("endDate", endDate);
                        certList = query.list();
                        session.flush();
                        session.connection().commit();
                }
                catch (Exception e)
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

                return certList;
        }

        public List getAspCertInfo(int aspID, int orgID, String startDates,
                                   String endDates) throws CertDAOSysException
        {
                Date startDate = DateTimeUtil.parseDate(startDates);
                Date endDate = DateTimeUtil.parseDate(endDates);

                LogUtil.info("common",
                        "[CertDAOImpl]getAspCertInfo--endDate0=" +
                                I18Util.FormatDateTime(endDate, Locale.CHINA));

                com.ulearning.ulms.core.util.Calendar calendar = new com.ulearning.ulms.core.util.Calendar(endDate);
                endDate = calendar.nextDay();

                LogUtil.info("common",
                        "[CertDAOImpl]getAspCertInfo--startDate=" +
                                I18Util.FormatDateTime(startDate, Locale.CHINA));
                LogUtil.info("common",
                        "[CertDAOImpl]getAspCertInfo--endDate=" +
                                I18Util.FormatDateTime(endDate, Locale.CHINA));

                List certList = new ArrayList();

                Session session = null;
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

                String hql = "from CertModel where " +
                        "   (LifeForever='1' or  (LifeBeginDate >= :startDate" +
                        " and LifeEndDate <= :endDate))" + aspID_str + orgID_str +
                        " ORDER BY CertificateID desc";
                LogUtil.info("course", "[CertDAOImpl]getAspCertInfo--hql=" + hql);

                try
                {
                        session = HibernateUtil.getSession();

                        Query query = session.createQuery(hql);
                        query.setParameter("startDate", startDate);
                        query.setParameter("endDate", endDate);
                        certList = query.list();
                        session.flush();
                        session.connection().commit();
                }
                catch (Exception e)
                {
                        e.printStackTrace();
                }
                finally
                {
                        try
                        {
                                if (session != null)
                                {
                                        HibernateUtil.releaseSession(session);
                                }
                        }
                        catch (HibernateException he)
                        {
                                he.printStackTrace();
                        }
                }

                return certList;
        }

        public CertCurrentStatus getCertNowStatus(int certificateID, int type)
                throws CertDAOSysException
        {
                CertCurrentStatus ccs = new CertCurrentStatus();
                Calendar calendar = new Calendar();
                int allowFreeReg = 0;
                int guest = 0;

                int year = 0;
                int month = 0;
                int day = 0;

                Date currentDate = new Date();
                String temp = DateTimeUtil.FormatDateToWeb1(currentDate);
                System.out.println("temp=========================" + temp);

                List timeList = StringUtil.split(temp, "-");

                if ((timeList != null) && (timeList.size() == 3))
                {
                        year = Integer.parseInt((String) timeList.get(0));
                        month = Integer.parseInt((String) timeList.get(1));
                        day = Integer.parseInt((String) timeList.get(2));
                }

                Date currentDateStartTime = DateTimeUtil.toDate(month, day, year, 0, 0,
                        0);
                Date currentDateEndTime = DateTimeUtil.toDate(month, day, year, 23, 59,
                        59);

                Date regStartDate = null;
                Date regEndDate = null;
                String password = "";
                CertForm cf = CertHelper.getCert(certificateID);
                regStartDate = cf.getRegTimeBegin();
                regEndDate = cf.getRegTimeEnd();
                allowFreeReg = cf.getRegMethed();
                guest = cf.getGuest();
                password = cf.getPassword();

                Date startdate = cf.getLifeBeginDate();
                Date enddate = cf.getLifeEndDate();

                if (cf.getApprove() == 1)
                {
                        ccs.setApproveStatus(true);
                }
                else
                {
                        ccs.setApproveStatus(false);
                }

                if (guest == 1)
                {
                        ccs.setBrowseStatus(true);
                }

                if (allowFreeReg == 1)
                {
                        if (regStartDate == null)
                        {
                                if (regEndDate == null)
                                {
                                        ccs.setEnrollStatus(true);
                                }
                                else if (currentDateEndTime.before(regEndDate))
                                {
                                        ccs.setEnrollStatus(true);
                                }
                        }
                        else
                        {
                                if ((regEndDate == null) &&
                                        currentDateStartTime.after(regStartDate))
                                {
                                        ccs.setEnrollStatus(true);
                                }
                                else if (currentDateStartTime.after(regStartDate) &&
                                        currentDateEndTime.before(regEndDate))
                                {
                                        ccs.setEnrollStatus(true);
                                }
                        }
                }

                if ((null != startdate) && (null != enddate) &&
                        !startdate.equals(enddate))
                {
                        if (currentDate.after(startdate) && currentDate.before(enddate))
                        {
                                ccs.setRegdate(true);
                        }
                }
                else
                {
                        ccs.setRegdate(true);
                }

                ccs.setEnrollPassword(password);

                return ccs;
        }

        public int getPublishCourseNum(int certificateID)
                throws CertDAOSysException
        {
                String hql = "select count(*) from CertCourseModel as ccm  ,Course as cm   where ccm.comp_id.certificateID=" +
                        certificateID + " and   (ccm.comp_id.type=1" +
                        "   or (ccm.comp_id.courseID=cm.masterid and ccm.comp_id.type=0))";
                Session session = null;
                int cnt = 0;

                try
                {
                        session = HibernateUtil.getSession();

                        Query query = session.createQuery(hql);
                        Iterator it = query.iterate();

                        if ((it != null) && it.hasNext())
                        {
                                Integer amount = (Integer) it.next();
                                cnt = amount.intValue();
                        }
                }
                catch (HibernateException e)
                {
                        e.printStackTrace(); //To change body of catch statement use File | Settings | File Templates.
                }

                return cnt; //To change body of implemented methods use File | Settings | File Templates.
        }

        public int getClassIDByCertID(int certificateID) throws CertDAOSysException
        {
                int returnID = -1;
                Statement stmt = null;
                ResultSet rs = null;

                try
                {
                        dbConnection = getConnection();
                        stmt = dbConnection.createStatement();

                        String sql = "select relationID from cer_certificate_tab c,c_master_tab m " +
                                "where c.MASTERID = m.MASTERID and m.TYPE = '4' and c.CERTIFICATEID = " +
                                certificateID;
                        stmt.execute(sql);
                        rs = stmt.getResultSet();

                        while (rs.next())
                        {
                                returnID = rs.getInt("relationID");
                        }
                }
                catch (SQLException se)
                {
                        se.printStackTrace();
                }
                finally
                {
                        closeResultSet(rs);
                        closeStatement(stmt);
                        closeConnection();
                }

                return returnID;
        }

        protected void closeConnection() throws CertDAOSysException
        {
                try
                {
                        if ((dbConnection != null) && !dbConnection.isClosed())
                        {
                                dbConnection.close();
                        }
                }
                catch (SQLException se)
                {
                        throw new CertDAOSysException("SQL Exception while closing " +
                                "DB connection : \n" + se);
                }
        }

        protected void closeResultSet(ResultSet result) throws CertDAOSysException
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
                        throw new CertDAOSysException("SQL Exception while closing " +
                                "Result Set : \n" + se);
                }
        }

        protected void closeStatement(Statement stmt) throws CertDAOSysException
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
                        throw new CertDAOSysException("SQL Exception while closing " +
                                "Statement : \n" + se);
                }
        }

        protected Connection getConnection() throws CertDAOSysException
        {
                try
                {
                        dbConnection = DBUtil.getConnection();
                }
                catch (Exception se)
                {
                        throw new CertDAOSysException("SQL Exception while getting " +
                                "DB connection : \n" + se);
                }

                return dbConnection;
        }

        /**
         * Convert the resultSet object to planForm
         *
         * @param rs the resultSet which needs to convert
         * @return the wanted planForm
         */
        private CertForm convertRs2Form(ResultSet rs)
        {
                CertForm cf = new CertForm();
                int rsIndex = 1;

                try
                {
                        cf.setCertificateID(rs.getInt(rsIndex++));
                        cf.setCode(rs.getString(rsIndex++));
                        cf.setName(rs.getString(rsIndex++));
                        cf.setCatalogID(rs.getInt(rsIndex++));
                        cf.setOrgID(rs.getInt(rsIndex++));
                        cf.setAspID(rs.getInt(rsIndex++));
                        cf.setKey(rs.getString(rsIndex++));
                        cf.setType(rs.getString(rsIndex++));
                        cf.setStatus(rs.getString((rsIndex - 1)));
                        cf.setCreator(rs.getInt(rsIndex++));
                        cf.setFeePolicy(rs.getInt(rsIndex++));
                        cf.setFee(rs.getFloat(rsIndex++));
                        cf.setLife(rs.getInt(rsIndex++));
                        cf.setGuest(rs.getInt(rsIndex++));
                        cf.setDescription(rs.getString(rsIndex++));
                        cf.setManage(rs.getInt(rsIndex++));
                        cf.setCompulsoryType(rs.getString(rsIndex++));
                        cf.setCompulsoryCredit(rs.getFloat(rsIndex++));
                        cf.setTotalType(rs.getString(rsIndex++));
                        cf.setTotalCredit(rs.getFloat(rsIndex++));
                        cf.setRegMethed(rs.getInt(rsIndex++));
                        cf.setRegTimeBegin(rs.getDate(rsIndex++));
                        cf.setRegTimeEnd(rs.getDate(rsIndex++));
                        cf.setPassword(rs.getString(rsIndex++));
                        cf.setLifeForever(rs.getInt(rsIndex++));
                        cf.setLifeBeginDate(rs.getDate(rsIndex++));
                        cf.setLifeEndDate(rs.getDate(rsIndex++));
                        cf.setSelectMethod(rs.getInt(rsIndex++));
                        cf.setSelectBeginDate(rs.getDate(rsIndex++));
                        cf.setSelectEndDate(rs.getDate(rsIndex++));
                }
                catch (SQLException sql)
                {
                        sql.printStackTrace();
                }

                return cf;
        }

        public List getAllCertList(int aspID, int orgID) throws CertDAOSysException
        {
                CertForm cf = null;
                ArrayList certList = new ArrayList();
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

                String hql = " from CertModel where  1=1 " + aspID_str + orgID_str +
                        " order by certificateID desc";

                try
                {
                        LogUtil.debug("system",
                                "[CertDAOImpl]====================the sql string is : " + hql);

                        List tmList = HibernateDAO.find(hql);
                        CertModel cm = null;

                        for (int i = 0; i < tmList.size(); i++)
                        {
                                cm = (CertModel) tmList.get(i);
                                cf = new CertForm(cm);
                                certList.add(cf);
                        }
                }
                catch (ULMSSysException se)
                {
                        throw new CertDAOSysException("SQLException while getAllCertList " +
                                "Plan; " + se);
                }

                return certList;
        }

        public List getCertByMasterID(int masterID) throws CertDAOSysException
        {
                String hql = " from CertModel where masterID=" + masterID;

                List certLt = null;

                try
                {
                        certLt = HibernateDAO.find(hql);
                }
                catch (ULMSSysException se)
                {
                        throw new CertDAOSysException(
                                "SQLException while getCertByMasterID " + "Plan; " + se);
                }

                return certLt;
        }

        public List getCertStudayingUser(int relationid) throws CertDAOSysException
        {
                StringBuffer hql = new StringBuffer(); //" from CourseUser where relationID=" + relationid;
                hql.append("from CourseUser as cu where cu.comp_id.relationID=");
                hql.append(relationid);
                hql.append(" and cu.comp_id.type=4 and (state='3' or state='2')");
                hql.append(" and cu.comp_id.userID in ");

                hql.append(
                        "(select su.comp_id.userID from SecUserRoleModel su where su.comp_id.relationID=");
                hql.append(relationid);
                hql.append(" and su.comp_id.type=4 and su.comp_id.roleID=9)");

                //hql.append(" from CourseUser");
                List certLt = null;

                try
                {
                        certLt = HibernateDAO.find(hql.toString());
                }
                catch (ULMSSysException se)
                {
                        throw new CertDAOSysException(
                                "SQLException while getCertByMasterID " + "Plan; " + se);
                }

                return certLt;
        }

        public static void main(String[] srgs)
        {
                CertDAOImpl cd = new CertDAOImpl();
                List list = cd.getCertStudayingUser(21);

                for (int i = 0; i < list.size(); i++)
                {
                        CourseUser cu = (CourseUser) list.get(i);
                        System.out.println(cu.getComp_id().getUserID());
                }
        }
}
