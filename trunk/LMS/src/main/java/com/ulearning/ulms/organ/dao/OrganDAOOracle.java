/**
 * Created by IntelliJ IDEA.
 * User: dengj
 * Date: Apr 14, 2004
 * Time: 5:17:16 PM
 * To change this template use Options | File Templates.
 */
package com.ulearning.ulms.organ.dao;

import com.ulearning.ulms.organ.exceptions.OrganDAOSysException;
import com.ulearning.ulms.organ.form.OrganForm;
import com.ulearning.ulms.util.log.LogUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class OrganDAOOracle extends OrganDAOImpl
{
        public int addOrgan(OrganForm organForm) throws OrganDAOSysException
        {
                Statement stmt = null;
                ResultSet rs = null;
                String sqlStr;
                int orgID = 0;

                if (organForm.getIsAsp() == 1)
                {
                        sqlStr = "insert into TM_Org_Tab values(orgID.nextval, " +
                                organForm.getParentID() + "," + "orgID.nextval," +
                                organForm.getLayer() + ", " + organForm.getOrgType() + ",'" +
                                organForm.getOrgName() + "'," + organForm.getOrgStatus() + "," +
                                organForm.getForGuest() + ",'" + organForm.getOrgNO() + "'," +
                                organForm.getIsAsp() + ",'" + organForm.getDescription() +
                                "')";
                }
                else
                {
                        sqlStr = "insert into TM_Org_Tab values(orgID.nextval, " +
                                organForm.getParentID() + ", " + organForm.getAspID() + ", " +
                                organForm.getLayer() + ", " + organForm.getOrgType() + ",'" +
                                organForm.getOrgName() + "'," + organForm.getOrgStatus() + "," +
                                organForm.getForGuest() + ",'" + organForm.getOrgNO() + "'," +
                                organForm.getIsAsp() + ",'" + organForm.getDescription() +
                                "')";
                }

                try
                {
                        dbConnection = getConnection();
                        stmt = dbConnection.createStatement();
                        LogUtil.debug("user",
                                "[OrganDAOOracle]====================the sql string is : " +
                                        sqlStr);
                        stmt.execute(sqlStr);

                        String isql = "SELECT orgID.currval  as orgID FROM DUAL";
                        stmt.execute(isql);
                        rs = stmt.getResultSet();

                        if (rs.next())
                        {
                                orgID = rs.getInt("orgID");
                        }
                }
                catch (SQLException se)
                {
                        throw new OrganDAOSysException("SQLException while updating " +
                                "account; Serial = " + organForm.getOrgName() + " :\n" + se);
                }
                finally
                {
                        closeResultSet(rs);
                        closeStatement(stmt);
                        closeConnection();
                }

                return orgID;
        }

        public void updateOrgan(OrganForm organForm) throws OrganDAOSysException
        {
                Statement stmt = null;

                String sqlStr = "update TM_Org_Tab set " + "ParentID = " +
                        organForm.getParentID() + ", " + "AspID =" + organForm.getAspID() +
                        ", " + "Layer = " + organForm.getLayer() + ", " + "OrgType = " +
                        organForm.getOrgType() + "," + "OrgName = '" +
                        organForm.getOrgName() + "'," + "OrgStatus = " +
                        organForm.getOrgStatus() + "," + "ForGuest =" +
                        organForm.getForGuest() + "," + "OrgNO = '" + organForm.getOrgNO() +
                        "'," + "isAsp = " + organForm.getIsAsp() + "," + "Description = '" +
                        organForm.getDescription() + "'  where orgID = " +
                        organForm.getOrgID();

                try
                {
                        dbConnection = getConnection();
                        stmt = dbConnection.createStatement();
                        LogUtil.debug("system",
                                "[OrganDAOOracle]====================the sql string is : " +
                                        sqlStr);
                        stmt.execute(sqlStr);
                }
                catch (SQLException se)
                {
                        throw new OrganDAOSysException("SQLException while updating " +
                                "account; Serial = " + organForm.getOrgName() + " :\n" + se);
                }
                finally
                {
                        closeStatement(stmt);
                        closeConnection();
                }
        }

        public void addOrganUser(int orgID, int userID) throws OrganDAOSysException
        {
                Statement stmt = null;
                int userCountinOrg = 0;
                ResultSet rs = null;
                String sqlpre = "select count(*) as userCountinOrg from TM_OrgUser_Tab where userID = " +
                        userID + " and orgID = " + orgID;
                String sqlStr = "insert into TM_OrgUser_Tab values( " + orgID + ", " +
                        userID + ", 0)";

                try
                {
                        dbConnection = getConnection();
                        stmt = dbConnection.createStatement();
                        rs = stmt.executeQuery(sqlpre);
                        LogUtil.debug("user",
                                "[OrganDAOOracle]====================the sql string is : " +
                                        sqlpre);

                        if (rs.next())
                        {
                                userCountinOrg = rs.getInt("userCountinOrg");
                                LogUtil.debug("user",
                                        "[userCountinOrg]====================the sql string is : " +
                                                userCountinOrg);

                                if (userCountinOrg == 0)
                                {
                                        stmt.execute(sqlStr);
                                        LogUtil.debug("user",
                                                "[OrganDAOOracle]====================the sql string is : " +
                                                        sqlStr);
                                }
                        }
                }
                catch (SQLException se)
                {
                        throw new OrganDAOSysException("SQLException while updating " +
                                "organUser; Serial = " + orgID + " :\n" + se);
                }
                finally
                {
                        closeResultSet(rs);
                        closeStatement(stmt);
                        closeConnection();
                }
        }
}
