/**
 * UserLocal.java.
 * User: dengj  Date: 2004-6-10
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.user.ejb;

import com.ulearning.ulms.user.exceptions.UserDuplicatException;
import com.ulearning.ulms.user.form.UserForm;

import javax.ejb.EJBLocalObject;
import javax.ejb.EJBException;

public interface UserLocal extends EJBLocalObject
{

        public int addUser(UserForm details) throws EJBException, UserDuplicatException;

}
