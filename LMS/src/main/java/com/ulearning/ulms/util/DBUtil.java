/**
 * Created by IntelliJ IDEA.
 * User: dengj
 * Date: Apr 8, 2004
 * Time: 9:27:47 AM
 * To change this template use Options | File Templates.
 */
package com.ulearning.ulms.util;

import com.ulearning.ulms.util.log.LogUtil;
import com.ulearning.ulms.core.util.Config;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.*;

public class DBUtil
{

        private static DataSource ds = null;
        private static String catalogStr = "system";

        public final static int ORACLE = 0;
        public final static int SQLServer2000 = 1;
        public final static int MySQL = 2;
        public final static int DB2 = 3;

        /**
         * which database,default is oracle.
         * 0 == oracle817
         * 1 == MS SQL Server 2000
         * 2 == mySQL
         */
        static int whichDatabase = getWhichDatabaseFromConfig();

        /**
         * get which database from SystemConfig
         */
        public static int getWhichDatabase()
        {
                //com.ulearning.ulms.util.log.LogUtil.debug(catalogStr, "[DBUtil]getWhichDatabase========================return=" + whichDatabase);
                return whichDatabase;
        }

        /**
         * get which database from SystemConfig
         * 0 == oracle817
         * 1 == MS SQL Server 2000
         * 2 == mySQL
         */
        private static int getWhichDatabaseFromConfig()
        {
                int database = 0;
                String strDatabase = Config.getDatabase();
                LogUtil.debug("core", "[DBUtil]getWhichDatabaseFromConfig----strDatabase=" + strDatabase);
                if (strDatabase.compareTo("oracle") == 0)
                {
                        database = DBUtil.ORACLE;
                }
                else if (strDatabase.compareTo("mssql2000") == 0)
                {
                        database = DBUtil.SQLServer2000;
                }
                else if (strDatabase.compareTo("mySQL") == 0)
                {
                        database = DBUtil.MySQL;
                }
                else if (strDatabase.compareTo("DB2") == 0)
                {
                        database = DBUtil.DB2;
                }
                //LogUtil.debug(catalogStr, "[DBUtil]getWhichDatabaseFromConfig=============database=" + database);
                return database;
        }

        /**
         * get a connection for database access according to LMS configuration
         * for database type.
         * todo: we will use config to decide how to get connection
         */
        public static Connection getConnection() throws SQLException
        {

                Connection conn = null;

                try
                {
                        if (ds == null)
                        {
                                ds = getJNDIDS();
                                if (ds == null)
                                {
                                        conn = getJDBCConnection();
                                        if (conn == null)
                                        {
                                                SQLException sqlexcep = new SQLException("Can't get connection from DataSource");
                                                throw sqlexcep;
                                        }
                                        else
                                        {
                                                return conn;
                                        }
                                }

                        }
                        conn = ds.getConnection();

                }
                catch (SQLException se)
                {
                        LogUtil.info(catalogStr, "in [DBUtil] " + se);
                        se.printStackTrace();
                        throw se;
                }

                return conn;
        }

        /**
         * Add \ at beginning of each '.
         *
         * @param input string.
         * @return new escaped string.
         */
        public static String escape(String input)
        {
                if (input == null)
                {
                        return "";
                }
                if (input != null && input.length() > 0)
                {
                        input = input.trim();
                }
                int length = input.length();
                StringBuffer sb = new StringBuffer(length);

                for (int i = 0; i < length; i++)
                {
                        char character = input.charAt(i);

                        if (character == '\'')
                        {
                                sb.append("\''");
                        }
                        else
                        {
                                sb.append(character);
                        }
                }

                return sb.toString();
        }

        private static Connection getJDBCConnection()
        {
                Connection conn = null;
                try
                {

                        if (whichDatabase == DBUtil.ORACLE) //Oracle
                        {
                                conn = getOracleJDBCConnection();
                        }
                        else if (whichDatabase == DBUtil.SQLServer2000) //Microsoft SQL Server
                        {
                                conn = getSQLServerJDBCConnection();
                        }
                        else if (whichDatabase == DBUtil.MySQL)
                        {
                                //todo: implemented later.
                        }
                        else if (whichDatabase == DBUtil.DB2)
                        {
                                conn = getDB2JDBCConnection();
                        }
                }
                catch (Exception e)
                {
                        e.printStackTrace();
                }
                return conn;
        }

        private static Connection getOracleJDBCConnection()
        {
                Connection conn = null;
                try
                {
                        Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();
                        String url = Config.getOracleJDBCURL();
                        String user = Config.getOracleJDBCUser();
                        String password = Config.getOracleJDBCPWD();
//                        String url = "jdbc:oracle:thin:@172.20.29.10:1521:orawas";
//                        String user = "ulms_test1";
//                        String password = "wxh";
                        // Class.forName("com.mysql.jdbc.Driver").newInstance();
                        //String url = "jdbc:mysql://172.20.27.35/2008";
                        //String user = "2008";
                        //String password = "2008";
                        conn = DriverManager.getConnection(url, user, password);
                }
                catch (Exception e)
                {
                        e.printStackTrace();
                }
                return conn;
        }

        private static Connection getSQLServerJDBCConnection()
        {
                Connection conn = null;
                try
                {
                        Class.forName("com.microsoft.jdbc.sqlserver.SQLServerDriver").newInstance();

                        String url = Config.getMssqlJDBCURL();
                        String user = Config.getMssqlJDBCUser();
                        String password = Config.getMssqlJDBCPWD();

                        conn = DriverManager.getConnection(url, user, password);
                }
                catch (Exception e)
                {
                        e.printStackTrace();
                }
                return conn;
        }

        public static Connection getDB2JDBCConnection()
        {
                Connection conn = null;
                try
                {
                        Class.forName("COM.ibm.db2.jdbc.net.DB2Driver").newInstance();

                        String url = Config.getDB2JDBCURL();
                        //System.out.println("url = " + url);
                        String user = Config.getDB2JDBCUser();
                        //System.out.println("user = " + user);
                        String password = Config.getDB2JDBCPWD();
                        //System.out.println("password = " + password);

                        conn = DriverManager.getConnection(url, user, password);
                }
                catch (Exception e)
                {
                        e.printStackTrace();
                }
                return conn;
        }

       

        public static Connection getJieFoJDBCConnection()
        {
                Connection conn = null;
                try
                {
                        Class.forName("com.microsoft.jdbc.sqlserver.SQLServerDriver").newInstance();

                        String url = Config.getExamJDBCURL();
                        String user = Config.getExamJDBCUser();
                        String password = Config.getExamJDBCPWD();
//                        String url = "jdbc:microsoft:sqlserver://172.20.29.40:1433;DatabaseName=Ilearning2004";
//                        String user = "webexamuser";
//                        String password = "jiefodotcom";
                        conn = DriverManager.getConnection(url, user, password);
                }
                catch (Exception e)
                {
                        e.printStackTrace();
                }
                return conn;
        }


        private static Connection getJNDIConnection()
        {
                Connection conn = null;
                try
                {
                        DataSource dataSource = getJNDIDS();
                        if (dataSource == null)
                        {
                                return getJDBCConnection();
                        }
                        conn = dataSource.getConnection();
                }
                catch (Exception e)
                {
                        e.printStackTrace();
                }
                return conn;
        }

        private static DataSource getJNDIDS()
        {
                try
                {
                        if (ds == null)
                        {
                                InitialContext ic = new InitialContext();
                                ds = (DataSource) ic.lookup("java:comp/env/JDBC/ULMS_DataSource");
                        }

                }
                catch (NamingException ne)
                {
                        LogUtil.info(catalogStr, "NamingException when lookup DataSource " + ne);
                        //ne.printStackTrace();
                        return null;
                }
                return ds;
        }


        // to close the db connection.
        public static void closeConnection(Connection dbConnection)
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
                        LogUtil.info(catalogStr, "Exception when closeing Connection!");
                }
        }

        // to close the db resultset.
        public static void closeResultSet(ResultSet result)
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
                        LogUtil.info(catalogStr, "Exception when closeing ResultSet!");
                }
        }

        // to close the db statement.
        public static void closeStatement(Statement stmt)
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
                        LogUtil.info(catalogStr, "Exception when closeing Statement!");
                }
        }

        public static void main()
        {

        }
}
