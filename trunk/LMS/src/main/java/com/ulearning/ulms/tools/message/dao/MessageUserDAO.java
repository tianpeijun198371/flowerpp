package com.ulearning.ulms.tools.message.dao;

import com.ulearning.ulms.tools.message.exceptions.MessageUserDAOSysException;
import com.ulearning.ulms.tools.message.form.MessageUserForm;

import java.util.List;


/**
 * Created by IntelliJ IDEA.
 * User: keyh
 * Date: 2004-7-26
 * Time: 9:40:06
 * To change this template use File | Settings | File Templates.
 */
public interface MessageUserDAO
{
        public void insertMessageUser(MessageUserForm muf)
                throws MessageUserDAOSysException;

        public void updateMessageUserByMessage(MessageUserForm muf)
                throws MessageUserDAOSysException;

        public void updateMessageUserByUser(MessageUserForm muf)
                throws MessageUserDAOSysException;

        public void deleteMessageUser(int messageID)
                throws MessageUserDAOSysException;

        public void deleteMessageUser(int userID, int messageID)
                throws MessageUserDAOSysException;

        public MessageUserForm getMessageUserByMessage(int messageID)
                throws MessageUserDAOSysException;

        public MessageUserForm getMessageUserByUser(int userID)
                throws MessageUserDAOSysException;

        public List getMessageUserListByMessage(int messageID)
                throws MessageUserDAOSysException;

        public List getMessageUserListByUser(int userID)
                throws MessageUserDAOSysException;

        public void viewMessage(int messageID, int userID)
                throws MessageUserDAOSysException;
}
