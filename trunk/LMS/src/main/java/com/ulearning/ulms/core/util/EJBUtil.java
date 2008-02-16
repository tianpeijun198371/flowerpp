/**
 * EJBUtil.java.
 * User: dengj  Date: 2004-6-10
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.core.util;

import com.ulearning.ulms.user.ejb.UserHome;

import javax.naming.InitialContext;

import javax.rmi.PortableRemoteObject;


public class EJBUtil
{
        public static UserHome getUserHome() throws javax.naming.NamingException
        {
                InitialContext initial = new InitialContext();
                Object objref = initial.lookup(JNDINames.USER_EJBHOME);

                return (UserHome) PortableRemoteObject.narrow(objref, UserHome.class);
        }
}
