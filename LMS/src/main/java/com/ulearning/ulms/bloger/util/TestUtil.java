/*
 * Created on 2004-9-30
 * Author: Huaxia, Copyright (C) 2004, Huaxia.
 */
package com.ulearning.ulms.bloger.util;

import com.ibatis.sqlmap.client.SqlMapClient;

import com.ulearning.ulms.bloger.dao.ibatis.SqlConfig;

import java.sql.Connection;
import java.sql.Statement;


/**
 * TODO Description here...
 *
 * @author Huaxia
 */
public abstract class TestUtil
{
        private static SqlMapClient sqlMap = SqlConfig.getSqlMapInstance();

        public static void executeSQL(String sql)
        {
                try
                {
                        sqlMap.startTransaction();

                        Connection conn = sqlMap.getCurrentConnection();
                        Statement sm = conn.createStatement();
                        sm.executeUpdate(sql);
                        sm.close();
                        conn.commit();
                        conn.close();
                        sqlMap.commitTransaction();
                }
                catch (Exception e)
                {
                        e.printStackTrace();
                }
                finally
                {
                        try
                        {
                                sqlMap.endTransaction();
                        }
                        catch (Exception e)
                        {
                        }
                }
        }
}
