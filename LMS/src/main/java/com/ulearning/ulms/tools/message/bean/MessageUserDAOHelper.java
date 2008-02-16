package com.ulearning.ulms.tools.message.bean;

import com.ulearning.ulms.tools.message.dao.MessageUserDAO;
import com.ulearning.ulms.tools.message.dao.MessageUserDAOFactory;
import com.ulearning.ulms.tools.message.exceptions.MessageDAOSysException;
import com.ulearning.ulms.tools.message.exceptions.MessageUserDAOSysException;
import com.ulearning.ulms.tools.message.form.MessageUserForm;

import java.util.List;


/**
 * Created by IntelliJ IDEA.
 * User: keyh
 * Date: 2004-7-26
 * Time: 11:36:39
 * To change this template use File | Settings | File Templates.
 */
public class MessageUserDAOHelper
{
        public MessageUserForm getMessageUserByMessgage(int messageID)
                throws MessageUserDAOSysException
        {
                MessageUserForm muf = null;

                try
                {
                        MessageUserDAO dao = MessageUserDAOFactory.getDAO();
                        muf = dao.getMessageUserByMessage(messageID);
                }
                catch (MessageUserDAOSysException gse)
                {
                        gse.printStackTrace();
                }

                return muf;
        }

        public MessageUserForm getMessageUserByUser(int userID)
                throws MessageDAOSysException
        {
                MessageUserForm muf = null;

                try
                {
                        MessageUserDAO dao = MessageUserDAOFactory.getDAO();
                        muf = dao.getMessageUserByUser(userID);
                }
                catch (MessageUserDAOSysException gse)
                {
                        gse.printStackTrace();
                }

                return muf;
        }

        public List getMessageUserListByMessage(int messageID)
                throws MessageDAOSysException
        {
                List messageUserList = null;

                try
                {
                        MessageUserDAO dao = MessageUserDAOFactory.getDAO();
                        messageUserList = dao.getMessageUserListByMessage(messageID);
                }
                catch (MessageUserDAOSysException gse)
                {
                        gse.printStackTrace();
                }

                return messageUserList;
        }

        public List getMessageUserListByUser(int userID)
                throws MessageDAOSysException
        {
                List messageUserList = null;

                try
                {
                        MessageUserDAO dao = MessageUserDAOFactory.getDAO();
                        messageUserList = dao.getMessageUserListByUser(userID);
                }
                catch (MessageUserDAOSysException gse)
                {
                        gse.printStackTrace();
                }

                return messageUserList;
        }

        public void deleteMessageUser(int messageID)
                throws MessageUserDAOSysException
        {
                try
                {
                        MessageUserDAO dao = MessageUserDAOFactory.getDAO();
                        dao.deleteMessageUser(messageID);
                }
                catch (MessageUserDAOSysException gse)
                {
                        gse.printStackTrace();
                }
        }
}
