/**
 * Created by IntelliJ IDEA.
 * User: zengwj
 * Date: 2004-8-20
 * Time: 10:09:55
 * To change this template use File | Settings | File Templates.
 */
package com.ulearning.ulms.admin.colsignup.colsigndetail.dao;

import com.ulearning.ulms.admin.colsignup.colsign.exceptions.ColSignDAOSysException;
import com.ulearning.ulms.admin.colsignup.colsigndetail.exceptions.ColSignDetailDAOSysException;
import com.ulearning.ulms.admin.colsignup.colsigndetail.form.ColSignDetailForm;
import com.ulearning.ulms.util.DBUtil;
import com.ulearning.ulms.util.log.LogUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class ColSignDetailDAOOracle extends ColSignDetailDAOImpl
{
        public int addColSignDetail(ColSignDetailForm csdf)
                throws ColSignDetailDAOSysException
        {
                int colSignDetailID = 0;
                String strSql;
                Connection conn = null;
                Statement stmt = null;
                ResultSet rs = null;
                String colSignDetailIDID_str = "ColSignDetailID.nextval";

                try
                {
                        conn = DBUtil.getConnection();
                        stmt = conn.createStatement();
                        strSql = "insert into C_ColSignDetail_Tab Values(" +
                                colSignDetailIDID_str + "," + csdf.getColSignID() + "," +
                                csdf.getRelationID() + "," + csdf.getTypeID() + ",'" +
                                csdf.getDesc1() + "')";

                        LogUtil.debug("colSignDetail", "[ColSignDetailDAOOracle] " +
                                strSql);

                        int totalCountInserted = stmt.executeUpdate(strSql);
                        LogUtil.debug("colSignDetail",
                                "[ColSignDetailDAOOracle] " + "一共插入 " + totalCountInserted +
                                        "行");

                        rs = stmt.executeQuery(
                                "SELECT ColSignDetailID.currval  as ColSignDetailID FROM DUAL");

                        if (rs.next())
                        {
                                colSignDetailID = rs.getInt("ColSignDetailID");
                        }
                }
                catch (SQLException se)
                {
                        se.printStackTrace();
                        throw new ColSignDetailDAOSysException(
                                "ColSignDetailDAOSysException======================error" + se);
                }
                finally
                {
                        closeStatement(stmt);
                        closeConnection(conn);
                }

                return colSignDetailID;
        }

        public void updateColSignDetail(ColSignDetailForm csdf)
                throws ColSignDetailDAOSysException
        {
                //To change body of implemented methods use File | Settings | File Templates.
        }

        protected void closeResultSet(ResultSet result)
                throws ColSignDetailDAOSysException
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
                        throw new ColSignDetailDAOSysException("" + se);
                }
        }

        protected void closeStatement(Statement stmt)
                throws ColSignDetailDAOSysException
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
                        throw new ColSignDetailDAOSysException("" + se);
                }
        }

        protected void closeConnection(Connection dbConnection)
                throws ColSignDetailDAOSysException
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
                        throw new ColSignDetailDAOSysException("" + se);
                }
        }
}
