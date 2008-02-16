/** * operateDB.java. 
 * User: xiejh  Date: 2004-6-2 *  
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd. 
 * All rights reserved. 
 */
package com.ulearning.ulms.util;

import com.ulearning.ulms.core.exceptions.ULMSSysException;
import com.ulearning.ulms.util.log.LogUtil;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class OperateDB
{
        private transient Connection dbConnection = null;
        private transient DataSource datasource = null;
        private Statement stmt = null;
        private ResultSet rs = null;

        private String sql_value_last = "";//测试是否关闭连接用，记录实例最后查询的sql值
        private String sql_value_all = ""; //测试是否关闭连接用，记录实例所有的查询的sql值
        private String sql_value_all2 = ""; //测试是否关闭连接用，记录exequery(String strSql,Statement stmt1)的strSql值

        public int exeupdate(String strSql) throws ULMSSysException
        {
                Statement stmt_update = null;
                int bln = -1;
                try
                {
                        LogUtil.debug("system", "[connec]====================the sql string is : " + strSql);
                        if (dbConnection == null || dbConnection.isClosed())
                        {
                                dbConnection = getConnection();
                        }
                        stmt_update = dbConnection.createStatement();
                        bln = stmt_update.executeUpdate(strSql);
                }
                catch (SQLException se)
                {
                        throw new ULMSSysException("SQLException while exeupdate:  " + strSql + " \n" + se, se);
                }
                catch (ULMSSysException se)
                {
                        throw new ULMSSysException("ULMSSysException while exeupdate: " + strSql + " :\n" + se);
                }
                finally
                {
                        try
                        {
                                stmt_update.close();
                                closeConnection();
                        }
                        catch (Exception se)
                        {
                                LogUtil.debug("system", "while exeupdate :stmt.close() or closeConnection()  Exception the sql string is : " + strSql + " ");
                        }
                }
                return bln;
        }

        public void exequerysql(String strSql) throws ULMSSysException
        {
                try
                {
                        LogUtil.debug("system", "[connec]====================the sql string is : " + strSql);
                        if (dbConnection == null || dbConnection.isClosed())
                        {
                                dbConnection = getConnection();
                        }
                        sql_value_last = strSql;
                        sql_value_all = strSql + sql_value_all;
                        stmt = dbConnection.createStatement();
                        //System.out.println(strSql);
                        stmt.executeQuery(strSql);
                }
                catch (SQLException se)
                {
                        throw new ULMSSysException("SQLException while exeupdate:  " + strSql + " \n" + se);
                }
                catch (ULMSSysException se)
                {
                        throw new ULMSSysException("ULMSSysException while exeupdate: " + strSql + " :\n" + se);
                }
                finally
                {
                        try
                        {
                                stmt.close();
                                closeConnection();
                        }
                        catch (Exception se)
                        {
                                LogUtil.debug("system", "while exeupdate :stmt.close() or closeConnection()  Exception the sql string is : " + strSql + " ");
                        }
                }

        }

        public ResultSet exequery(String strSql) throws ULMSSysException
        {
                try
                {
                        LogUtil.debug("system", "[connec]====================the sql string is : " + strSql);
                        if (dbConnection == null || dbConnection.isClosed())
                        {
                                dbConnection = getConnection();
                        }
                        sql_value_last = strSql;
                        sql_value_all = strSql + sql_value_all;
                        stmt = dbConnection.createStatement();
                        System.out.println(strSql);
                        rs = stmt.executeQuery(strSql);
                }
                catch (SQLException se)
                {
                        throw new ULMSSysException("SQLException while exeupdate:  " + strSql + " \n" + se);
                }
                catch (ULMSSysException se)
                {
                        throw new ULMSSysException("ULMSSysException while exeupdate: " + strSql + " :\n" + se);
                }
                return rs;
        }

        public ResultSet exequery(String strSql, Statement stmt1) throws ULMSSysException
        {
                try
                {
                        sql_value_all2 = sql_value_all2 + " //// " + strSql;
                        LogUtil.debug("system", "[connec]====================the sql string is : " + strSql);
                        rs = stmt1.executeQuery(strSql);
                }
                catch (SQLException se)
                {
                        throw new ULMSSysException("SQLException while exeupdate:  " + strSql + " \n" + se);
                }
                return rs;
        }

        public Statement getStmt() throws ULMSSysException
        {
                Statement stmt1 = null;
                try
                {
                        LogUtil.debug("system", "[connec]  getStmt()");
                        if (dbConnection == null || dbConnection.isClosed())
                        {
                                dbConnection = getConnection();
                        }
                        stmt1 = dbConnection.createStatement();
                }
                catch (SQLException se)
                {
                        throw new ULMSSysException("SQLException  while getStmt()" + " \n" + se);
                }
                return stmt1;
        }

        public void closeDB() throws SQLException
        {
                if (sql_value_all.indexOf(sql_value_last) == 0)
                {
                        sql_value_all = sql_value_all.substring(sql_value_last.length());
                        sql_value_last = "";
                }
                if (!sql_value_all2.equals(""))
                {
                        sql_value_all2 = "";
                }
                try
                {
                        closeResultSet(rs);
                        closeStatement(stmt);
                        closeConnection();
                }
                catch (Exception se)
                {
                        LogUtil.debug("system", "SQL Exception while  closeDB() after exequery");
                }
        }

        public void closeItems(Statement stmt, ResultSet rs) throws SQLException
        {
                try
                {
                        closeResultSet(rs);
                        closeStatement(stmt);
                }
                catch (Exception se)
                {
                        LogUtil.debug("system", "SQL Exception while  closeDB() after exequery");
                }
        }

        private void closeConnection() throws ULMSSysException
        {
                try
                {
                        if (dbConnection != null && !dbConnection.isClosed())
                        {
                                dbConnection.close();
                        }
                }
                catch (SQLException se)
                {
                        throw new ULMSSysException("SQL Exception while closing " +
                                "DB connection : \n" + se);
                }
        }

        private void closeResultSet(ResultSet result) throws ULMSSysException
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
                        throw new ULMSSysException("SQL Exception while closing " +
                                "Result Set : \n" + se);
                }
        }

        private void closeStatement(Statement stmt) throws ULMSSysException
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
                        throw new ULMSSysException("SQL Exception while closing " +
                                "Statement : \n" + se);
                }
        }

        private Connection getConnection() throws ULMSSysException
        {
                try
                {
                        dbConnection = DBUtil.getConnection();
                }
                catch (Exception se)
                {
                        throw new ULMSSysException("SQL Exception while getting " +
                                "DB connection : \n" + se);
                }
                return dbConnection;
        }

        protected void finalize() throws Throwable
        {
                if (sql_value_all.length() > 0)
                {
                        LogUtil.debug("system", "Connect not  close for exeupdate(String strSql), the sql=" + sql_value_all);
                        closeDB();
                }
                if (!sql_value_all2.equals(""))
                {
                        LogUtil.debug("system", "Connect not  close for exequery(String strSql,Statement stmt1) , the sql=" + sql_value_all2);
                        closeDB();
                }
        }
}