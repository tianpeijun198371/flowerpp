/**
 * UserHome.java.
 * User: dengj  Date: 2004-6-10
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.user.ejb;

import javax.ejb.CreateException;
import javax.ejb.EJBHome;
import java.rmi.RemoteException;

public interface UserHome extends EJBHome
{
        public User create() throws RemoteException, CreateException;
}
