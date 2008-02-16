/**
 * ClobUtil.java.
 * User: Fengch  Date: 2005-6-1 0:03:13
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.core.util;

import net.sf.hibernate.Hibernate;

import java.sql.Clob;
import java.sql.SQLException;


/**
 * Clob 和 String的相互转换的实用类。
 */
public class ClobUtil
{
        /**
         * return the string,according to the clob.
         *
         * @param clob
         * @return
         */
        public static String getString(Clob clob)
        {
                String str = "";

                try
                {
                        if ((clob != null) && (clob.length() > 0))
                        {
                                str = clob.getSubString((long) 1, (int) clob.length());
                        }
                }
                catch (SQLException e)
                {
                }

                return str;
        }

        /**
         * return the clobg,according to the string.
         *
         * @param str
         * @return
         */
        public static Clob getClob(String str)
        {
                Clob clob = null;

                if (str == null)
                {
                        clob = Hibernate.createClob("");
                }
                else
                {
                        clob = Hibernate.createClob(str);
                }

                return clob;
        }
}
