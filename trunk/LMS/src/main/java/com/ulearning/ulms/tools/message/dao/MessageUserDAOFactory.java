/*
 * Copyright (c) 2004 Your Corporation. All Rights Reserved.
 */
package com.ulearning.ulms.tools.message.dao;

import com.ulearning.ulms.core.exceptions.ULMSSysException;
import com.ulearning.ulms.tools.message.exceptions.MessageUserDAOSysException;


/**
 * Created by IntelliJ IDEA.
 * MessageUserDAOFactory.java .
 * <p/>
 * User: keyh * Date: 2004-9-19
 * Time: 10:13:48
 * <p/>
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
public class MessageUserDAOFactory
{
        public static MessageUserDAO getDAO() throws MessageUserDAOSysException
        {
                MessageUserDAO dao = null;
                dao = new MessageUserDAOImpl();

                return dao;
        }
}
