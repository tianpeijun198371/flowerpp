/**
 * Copyright (c)2006 Beijing WenhuaOnline Sci-tech DevelopmentCo.,Ltd. 
 * All rights reserved. 
 *
 * User: fengch
 * Date: 2008-1-14 17:31:41 
 */
package com.ulearning.ulms.core.hibernate.clob;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.sql.Clob;

import net.sf.hibernate.Hibernate;
import net.sf.hibernate.HibernateException;
import net.sf.hibernate.UserType;

public class StringClobType implements UserType
{
        public int[] sqlTypes()
        {
                return new int[]{Types.CLOB};
        }

        public Class returnedClass()
        {
                return String.class;
        }

        public boolean equals(Object x, Object y)
        {
                return (x == y)
                        || (x != null
                        && y != null
                        && (x.equals(y)));
        }

        public Object nullSafeGet(ResultSet rs, String[] names, Object owner)
                throws HibernateException, SQLException
        {
                Clob clob = rs.getClob(names[0]);
                return clob.getSubString(1, (int) clob.length());
        }

        public void nullSafeSet(PreparedStatement st, Object value, int index)
                throws HibernateException, SQLException
        {
                st.setClob(index, Hibernate.createClob((String) value));
        }

        public Object deepCopy(Object value)
        {
                if (value == null)
                {
                        return null;
                }
                return new String((String) value);
        }

        public boolean isMutable()
        {
                return false;
        }
}

