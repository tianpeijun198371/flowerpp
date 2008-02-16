package com.ulearning.ulms.tools.message.dao;

import com.ulearning.ulms.tools.message.exceptions.MessageDAOSysException;
import com.ulearning.ulms.tools.message.form.MessageForm;

import java.util.Date;
import java.util.List;


/**
 * Created by IntelliJ IDEA.
 * User: keyh
 * Date: 2004-7-23
 * Time: 16:18:14
 * To change this template use File | Settings | File Templates.
 */
public interface MessageDAO
{
        public int insertMessage(MessageForm mf) throws MessageDAOSysException;

        public void updateMessage(MessageForm mf) throws MessageDAOSysException;

        public void deleteMessage(int messageID) throws MessageDAOSysException;

        public void deleteMessageFromRecieveBox(int messageID)
                throws MessageDAOSysException;

        public void deleteMessageFromSendBox(int messageID)
                throws MessageDAOSysException;

        public MessageForm getMessage(int messageID) throws MessageDAOSysException;

        public List getNewMessage(int senderID) throws MessageDAOSysException;

        public List getMessageListBySender(int senderID, int type)
                throws MessageDAOSysException;

        public List getMessageListByReciever(int recieverID, int type)
                throws MessageDAOSysException;

        public int autoDel(Date date, int num, int type)
                throws MessageDAOSysException;
}
