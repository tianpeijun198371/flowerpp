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

        //�����ݿ������йص�Bean����
        private Connection con = null;

        public ConDB() //�ڽ�������������ݿ�����
        {
                //BulidConnection();
                //�������ݿ�����
        }

        //����Driver
        public void setDriver(String s)
        {
                driver = s;
        }

        //����Url
        public void setUrl(String s)
        {
                url = s;
        }

        //����User
        public void setUser(String s)
        {
                user = s;
        }

        //����password
        public void setPassword(String s)
        {
                password = s;
        }

        //�������ݿ�����ķ���
        private void BulidConnection() throws BackupDAOSysException
        {
                try
                {
                        Class.forName(driver);
                        //����������ʽ���
                        con = DriverManager.getConnection(url, user, password);

                        //�������ݿ�����
                }
                catch (Exception ex)
                {
                        throw new BackupDAOSysException("" + ex);
                }
        }

        //����Connection����ķ���
        public Connection getConnection() throws BackupDAOSysException
        {
                //��conΪnullʱ, ���½������ݿ�����
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
                                con.close(); //�ر�Connection���
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
