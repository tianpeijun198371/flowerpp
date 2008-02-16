/*
 * Created on 2004-10-14
 * Author: Huaxia, Copyright (C) 2004, Huaxia.
 */
package com.ulearning.ulms.bloger.dao;

import com.ulearning.ulms.bloger.domain.Message;
import com.ulearning.ulms.bloger.exception.*;

import java.util.List;


/**
 * DAO pattern for Message object.
 *
 * @author Huaxia
 */
public interface MessageDao
{
        void createMessage(Message message) throws CreateException;

        List getMessagesByAccount(int accountId) throws QueryException;

        List getFirstMessages(int num) throws QueryException;

        Message getMessage(int messageId) throws QueryException;

        void deleteMessage(int messageId) throws DeleteException;
}
