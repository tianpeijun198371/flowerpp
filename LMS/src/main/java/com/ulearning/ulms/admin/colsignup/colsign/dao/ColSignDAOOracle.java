/**
 * Created by IntelliJ IDEA.
 * User: zengwj
 * Date: 2004-8-20
 * Time: 10:09:55
 * To change this template use File | Settings | File Templates.
 */
package com.ulearning.ulms.admin.colsignup.colsign.dao;

import com.ulearning.ulms.admin.colsignup.colsign.exceptions.ColSignDAOSysException;
import com.ulearning.ulms.admin.colsignup.colsign.form.ColSignForm;
import com.ulearning.ulms.core.exceptions.ULMSSysException;
import com.ulearning.ulms.util.DBUtil;
import com.ulearning.ulms.util.OperateDB;
import com.ulearning.ulms.util.log.LogUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class ColSignDAOOracle extends ColSignDAOImpl
{
        public int addColSign(ColSignForm csf) throws ColSignDAOSysException
        {
                int colSignID = 0;
                String strSql;
                Connection conn = null;
                Statement stmt = null;
                ResultSet rs = null;
                String colSignIDID_str = "ColSignID.nextval";
                java.sql.Date dayToInsert = new java.sql.Date(System.currentTimeMillis());
                String createDate = "to_date('" + dayToInsert + "','yyyy-mm-dd')";

                try
                {
                        conn = DBUtil.getConnection();
                        stmt = conn.createStatement();
                        strSql = "insert into C_ColSign_Tab Values(" + colSignIDID_str +
                                ",'" + csf.getName() + "'," + csf.getOrgID() + ",'" +
                                csf.getDescription() + "'," + createDate + "," +
                                csf.getCreator() + ",'" + csf.getIsSubmit() + "','" +
                                csf.getApproved() + "','" + csf.getDesc1() + "')";

                        LogUtil.debug("colSign", "[ColSignDAOOracle] " + strSql);

                        int totalCountInserted = stmt.executeUpdate(strSql);
                        LogUtil.debug("colSign",
                                "[ColSignDAOOracle] " + "一共插入 " + totalCountInserted + "行");

                        rs = stmt.executeQuery(
                                "SELECT ColSignID.currval  as ColSignID FROM DUAL");

                        if (rs.next())
                        {
                                colSignID = rs.getInt("ColSignID");
                        }
                }
                catch (SQLException se)
                {
                        se.printStackTrace();
                        throw new ColSignDAOSysException(
                                "addColSing======================error" + se);
                }
                finally
                {
                        closeStatement(stmt);
                        closeConnection(conn);
                }

                return colSignID;
        }

        public void updateColSign(ColSignForm csf) throws ColSignDAOSysException
        {
                String strSql = "";
                OperateDB operateDB = new OperateDB();
                strSql = "update C_ColSign_Tab set " + " Name = '" + csf.getName() +
                        "'," + " Description = '" + csf.getDescription() + "'," +
                        " isSubmit = '" + csf.getIsSubmit() + "'," + " Approved = '" +
                        csf.getApproved() + "'," + " Desc1 = '" + csf.getDesc1() + "'" +
                        " Where ColSignID = " + csf.getColSignID();
                LogUtil.debug("colsign",
                        "[ColSignDAOOracle]====================the sql string is : " +
                                strSql);

                try
                {
                        operateDB.exeupdate(strSql);
                }
                catch (ULMSSysException se)
                {
                        se.printStackTrace();
                        throw new ColSignDAOSysException(
                                "SQLException while updating colsign; Name = " + csf.getName() +
                                        " :\n" + se);
                }
        }

        public void approvedColSign(int ColSignID, String Approved)
                throws ColSignDAOSysException
        {
                String strSql = "";
                OperateDB operateDB = new OperateDB();
                strSql = "Update C_ColSign_Tab Set Approved = '" + Approved +
                        "' Where ColSignID = " + ColSignID;
                LogUtil.debug("colSign", "[ColSignDAOImpl] " + strSql);

                try
                {
                        operateDB.exeupdate(strSql);
                }
                catch (ULMSSysException se)
                {
                        se.printStackTrace();
                        throw new ColSignDAOSysException(
                                "SQLException while updating colsign; ColSignID = " +
                                        ColSignID + " : Approved = " + Approved + "\n" + se);
                }
        }

        protected void closeResultSet(ResultSet result)
                throws ColSignDAOSysException
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
                        throw new ColSignDAOSysException("" + se);
                }
        }

        protected void closeStatement(Statement stmt) throws ColSignDAOSysException
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
                        throw new ColSignDAOSysException("" + se);
                }
        }

        protected void closeConnection(Connection dbConnection)
                throws ColSignDAOSysException
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
                        throw new ColSignDAOSysException("" + se);
                }
        }
}
