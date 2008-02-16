/*
 * Created on 2004-9-28
 * Author: Huaxia, Copyright (C) 2004, Huaxia.
 */
package com.ulearning.ulms.bloger.dao.ibatis;

import com.ibatis.common.resources.Resources;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;


/**
 * TODO Description here...
 *
 * @author Huaxia
 */
public class SqlConfig
{
        private static final SqlMapClient sqlMap;

        static
        {
                try
                {
                        java.io.Reader reader = Resources.getResourceAsReader(
                                "sql-map-config.xml");
                        sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);
                }
                catch (Exception e)
                {
                        e.printStackTrace();
                        throw new RuntimeException(
                                "Error initializing SqlConfig, application should be terminated. Cause: " +
                                        e);
                }
        }

        private SqlConfig()
        {
        }

        public static SqlMapClient getSqlMapInstance()
        {
                return sqlMap;
        }
}
