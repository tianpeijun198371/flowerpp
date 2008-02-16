/**
 * User.java.
 * User: dengj  Date: 2004-6-10
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.user.ejb;

import com.ulearning.ulms.user.form.UserForm;
import com.ulearning.ulms.user.exceptions.UserDuplicatException;

import javax.ejb.EJBObject;
import javax.ejb.CreateException;
import javax.ejb.EJBException;
import java.rmi.RemoteException;

public interface User extends EJBObject
{

        /**
         * Add a user to system
         *
         * @param details user model
         * @return userID, default is 0
         * @throws RemoteException
         * @throws CreateException
         * @throws UserDuplicatException if the user loginname is duplicate
         */
        public int addUser(UserForm details) throws RemoteException, EJBException, UserDuplicatException;

}
