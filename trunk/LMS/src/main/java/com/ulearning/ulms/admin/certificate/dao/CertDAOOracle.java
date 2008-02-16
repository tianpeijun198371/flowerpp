/**
 * CertDAOOracle.java.
 * User: huangsb  Date: 2004-4-26
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd. * All rights reserved.
 */
package com.ulearning.ulms.admin.certificate.dao;

import com.ulearning.ulms.admin.certificate.exceptions.CertDAOSysException;
import com.ulearning.ulms.admin.certificate.form.CertForm;
import com.ulearning.ulms.course.model.CatalogModel;
import com.ulearning.ulms.util.log.LogUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class CertDAOOracle extends CertDAOImpl
{
        public int addCert(CertForm certForm) throws CertDAOSysException
        {
                Statement stmt = null;
                int certificateID = 0;

                String regTimeBegin = "to_date('" + certForm.getRegTimeBegin() +
                        "','yyyy-mm-dd')";
                String regTimeEnd = "to_date('" + certForm.getRegTimeEnd() +
                        "','yyyy-mm-dd')";
                String lifeBeginDate = "to_date('" + certForm.getLifeBeginDate() +
                        "','yyyy-mm-dd')";
                String lifeEndDate = "to_date('" + certForm.getLifeEndDate() +
                        "','yyyy-mm-dd')";
                String SelectBeginDate = "to_date('" + certForm.getSelectBeginDate() +
                        "','yyyy-mm-dd')";
                String SelectEndDate = "to_date('" + certForm.getSelectEndDate() +
                        "','yyyy-mm-dd')";
                String sqlStr = "insert into Cer_Certificate_Tab values(CertificateID.nextval,'" +
                        certForm.getCode() + "','" + certForm.getName() + "','" +
                        certForm.getCatalogID() + "','" + certForm.getOrgID() + "','" +
                        certForm.getType() + "','" + certForm.getKey() + "'," +
                        certForm.getFeePolicy() + "," + certForm.getFee() + ",'" +
                        certForm.getLife() + "'," + certForm.getGuest() + ",'" +
                        certForm.getDescription() + "','" + certForm.getManage() + "'," +
                        certForm.getCompulsoryCredit() + "," + certForm.getRegMethed() +
                        "," + regTimeBegin + "," + regTimeEnd + ",'" +
                        certForm.getPassword() + "','" + certForm.getLifeForever() + "," +
                        lifeBeginDate + "," + lifeEndDate + "," +
                        certForm.getSelectMethod() + "," + SelectBeginDate + "," +
                        SelectEndDate + ")";

                try
                {
                        dbConnection = getConnection();
                        stmt = dbConnection.createStatement();
                        stmt.execute(sqlStr);
                        LogUtil.debug("system",
                                "[CertDAOOracle==============the SQL String]" + sqlStr);

                        String isql = "SELECT certificateID.currval  as certificateID FROM DUAL";
                        stmt.execute(isql);

                        LogUtil.debug("system",
                                "[CertDAOOracle==============the SQL String]" + isql);

                        ResultSet rs = stmt.getResultSet();

                        if (rs.next())
                        {
                                certificateID = rs.getInt("certificateID");
                        }

                        //closeResultSet(rs);
                }
                catch (SQLException se)
                {
                        throw new CertDAOSysException("SQLException while updating " +
                                "Cert;" + " Serial=" + certForm.getCode() + ":\n" + se);
                }
                finally
                {
                        try
                        {
                                if (stmt != null)
                                {
                                        stmt.close();
                                        closeConnection();
                                }
                        }
                        catch (Exception e)
                        {
                                e.printStackTrace();
                        }
                }

                return certificateID;
        }

        /**
         * Update certInfo by the new Form
         *
         * @param certForm value object for changed
         * @throws CertDAOSysException
         */
        public void updateCert(CertForm certForm) throws CertDAOSysException
        {
                Statement stmt = null;

                String regTimeBegin = "to_date('" + certForm.getRegTimeBegin() +
                        "','yyyy-mm-dd')";
                String regTimeEnd = "to_date('" + certForm.getRegTimeEnd() +
                        "','yyyy-mm-dd')";
                String lifeBeginDate = "to_date('" + certForm.getLifeBeginDate() +
                        "','yyyy-mm-dd')";
                String lifeEndDate = "to_date('" + certForm.getLifeEndDate() +
                        "','yyyy-mm-dd')";
                String SelectBeginDate = "to_date('" + certForm.getSelectBeginDate() +
                        "','yyyy-mm-dd')";
                String SelectEndDate = "to_date('" + certForm.getSelectEndDate() +
                        "','yyyy-mm-dd')";

                String sqlStr = "update Cer_Certificate_Tab set " + "Code = '" +
                        certForm.getCode() + "'," + "Name = '" + certForm.getName() + "'," +
                        "CatalogID = '" + certForm.getCatalogID() + "'," + "OrgID = '" +
                        certForm.getOrgID() + "'," + "Type = '" + certForm.getType() +
                        "'," + "Key = '" + certForm.getKey() + "'," + "FeePolicy = " +
                        certForm.getFeePolicy() + "," + "Fee = " + certForm.getFee() + "," +
                        "Life = '" + certForm.getLife() + "'," + "Guest = " +
                        certForm.getGuest() + "," + "Description = '" +
                        certForm.getDescription() + "'," + "Manage = '" +
                        certForm.getManage() + "'," + "Credit = " +
                        certForm.getCompulsoryCredit() + "," + "RegMethed = " +
                        certForm.getRegMethed() + "," + "RegTimeBegin = " + regTimeBegin +
                        "," + "RegTimeEnd = " + regTimeEnd + "," + "Password = '" +
                        certForm.getPassword() + "'," + "LifeForever = " +
                        certForm.getLifeForever() + "," + "LifeBeginDate = " +
                        lifeBeginDate + "," + "LifeEndDate = " + lifeEndDate + "," +
                        "SelectMethod = " + certForm.getSelectMethod() + "," +
                        "SelectBeginDate = " + SelectBeginDate + "," + "SelectEndDate = " +
                        SelectEndDate + "" + "  where CertificateID = " +
                        certForm.getCertificateID();

                try
                {
                        dbConnection = getConnection();
                        stmt = dbConnection.createStatement();
                        LogUtil.debug("system",
                                "[CertDAOOracle]====================the sql string is : " +
                                        sqlStr);
                        stmt.execute(sqlStr);
                }
                catch (SQLException se)
                {
                        throw new CertDAOSysException("SQLException while updating " +
                                "Cert; Serial = " + certForm.getCode() + " :\n" + se);
                }
                finally
                {
                        try
                        {
                                stmt.close();
                                closeConnection();
                        }
                        catch (SQLException se)
                        {
                        }
                }
        }

        /**
         * @param value
         * @throws CertDAOSysException
         */
        public void createCatalog(CatalogModel value) throws CertDAOSysException
        {
                String sql_str;

                Statement stmt = null;
                String catalogID = "CatalogID.nextval";

                String name = value.getName();
                name = "'" + name + "'";

                int type = value.getType();

                int parentID = value.getParentID();

                int orgID = value.getOrgID();

                String description = value.getDescription();
                description = "'" + description + "'";

                try
                {
                        dbConnection = getConnection();
                        stmt = dbConnection.createStatement();

                        //prepare the sql string to insert
                        sql_str = "insert into C_Catalog_Tab(CatalogID,Name,Type," +
                                "ParentID,OrgID,Description) values (" + catalogID + "," +
                                name + "," + type + "," + parentID + "," + orgID + "," +
                                description + ")";

                        LogUtil.debug("course", "[MasterDAOOracle] " + sql_str);

                        int totalCountInserted = stmt.executeUpdate(sql_str);
                        LogUtil.debug("course",
                                "[MasterDAOOracle] " + "一共插入 " + totalCountInserted + "行");
                }
                catch (SQLException se)
                {
                        se.printStackTrace();
                        throw new CertDAOSysException("SQLException while updating " +
                                "Catalog; Serial = " + value.getName() + " :\n" + se);
                }
                finally
                {
                        closeStatement(stmt);
                        closeConnection();
                }
        }
}
