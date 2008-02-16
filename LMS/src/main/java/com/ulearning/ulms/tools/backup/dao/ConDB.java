/** * ConDB.java.
 * User: xiejh  Date: 2004-9-13 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.tools.backup.dao;

import com.ulearning.ulms.tools.backup.exceptions.BackupDAOSysException;

import net.sf.hibernate.cfg.Configuration;
import net.sf.hibernate.cfg.Environment;

import java.sql.*;

import java.util.Properties;


public class ConDB
{
        private String driver = null;
        private String url = null;
        private String user = null;
        private String password = null;

        //与数据库连结有关的Bean属性
        private Connection con = null;

        public ConDB() //在建构子中完成数据库连结
        {
                //BulidConnection();
                //建立数据库连结
        }

        //设置Driver
        public void setDriver(String s)
        {
                driver = s;
        }

        //设置Url
        public void setUrl(String s)
        {
                url = s;
        }

        //设置User
        public void setUser(String s)
        {
                user = s;
        }

        //设置password
        public void setPassword(String s)
        {
                password = s;
        }

        //建立数据库连结的方法
        private void BulidConnection() throws BackupDAOSysException
        {
                try
                {
                        Class.forName(driver);
                        //载入驱动程式类别
                        con = DriverManager.getConnection(url, user, password);

                        //建立数据库连线
                }
                catch (Exception ex)
                {
                        throw new BackupDAOSysException("" + ex);
                }
        }

        //传出Connection物件的方法
        public Connection getConnection() throws BackupDAOSysException
        {
                //若con为null时, 重新建立数据库连结
                if (con == null)
                {
                        try
                        {
                                BulidConnection();
                        }
                        catch (Exception ex)
                        {
                                throw new BackupDAOSysException("" + ex);
                        }
                }

                return this.con;
        }

        public void close()
        {
                try
                {
                        if (con != null)
                        {
                                con.close(); //关闭Connection物件
                                con = null;
                        }
                }
                catch (SQLException sex)
                {
                        System.out.println(sex.toString());
                }
        }

        public void execute(String mysql) throws BackupDAOSysException
        {
                Statement stmt = null;
                ResultSet rs = null;

                if (con == null)
                {
                        try
                        {
                                BulidConnection();
                        }
                        catch (Exception ex)
                        {
                                throw new BackupDAOSysException("" + ex);
                        }
                }

                try
                {
                        stmt = con.createStatement();

                        boolean b = stmt.execute(mysql);
                }
                catch (Exception se)
                {
                        System.out.println("" + se);
                }
                finally
                {
                        close();
                }
        }

        public static void main(String[] args)
        {
                Properties p = null;
                Configuration c = new Configuration();

                try
                {
                        Configuration b = c.configure();
                        p = b.getProperties();
                        System.out.println(p.getProperty("hibernate.connection.url"));
                }
                catch (net.sf.hibernate.HibernateException h)
                {
                }
        }
}
