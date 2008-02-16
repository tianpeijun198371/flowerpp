package com.ulearning.ulms.tools.message.bean;

import com.ulearning.ulms.tools.message.dao.MessageDAO;
import com.ulearning.ulms.tools.message.dao.MessageDAOFactory;
import com.ulearning.ulms.tools.message.dao.MessageUserDAO;
import com.ulearning.ulms.tools.message.dao.MessageUserDAOFactory;
import com.ulearning.ulms.tools.message.exceptions.MessageDAOSysException;
import com.ulearning.ulms.tools.message.exceptions.MessageUserDAOSysException;
import com.ulearning.ulms.tools.message.form.MessageForm;
import com.ulearning.ulms.tools.message.form.MessageUserForm;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * Created by IntelliJ IDEA.
 * User: keyh
 * Date: 2004-7-23
 * Time: 17:05:27
 * To change this template use File | Settings | File Templates.
 */
public class MessageDAOHelper
{
        public MessageForm getMessage(int messageID) throws MessageDAOSysException
        {
                MessageForm mf = null;

                try
                {
                        MessageDAO dao = MessageDAOFactory.getDAO();
                        mf = dao.getMessage(messageID);
                }
                catch (MessageDAOSysException gse)
                {
                        gse.printStackTrace();
                }

                return mf;
        }

        public List getNewMessage(int senderID) throws MessageDAOSysException
        {
                List list = new ArrayList();

                try
                {
                        MessageDAO dao = MessageDAOFactory.getDAO();
                        list = dao.getNewMessage(senderID);
                }
                catch (MessageDAOSysException gse)
                {
                        gse.printStackTrace();
                }

                return list;
        }

        public List getMessageListBySender(int SenderID, int isSave)
                throws MessageDAOSysException
        {
                List groupList = null;

                try
                {
                        MessageDAO dao = MessageDAOFactory.getDAO();
                        groupList = dao.getMessageListBySender(SenderID, isSave);
                }
                catch (MessageDAOSysException gse)
                {
                        gse.printStackTrace();
                }

                return groupList;
        }

        public List getMessageListByReciever(int RecieverID, int type)
                throws MessageDAOSysException
        {
                List groupList = null;

                try
                {
                        MessageDAO dao = MessageDAOFactory.getDAO();
                        groupList = dao.getMessageListByReciever(RecieverID, type);
                }
                catch (MessageDAOSysException gse)
                {
                        gse.printStackTrace();
                }

                return groupList;
        }

        public int autoDel(Date date, int num, int type)
                throws MessageDAOSysException
        {
                int res = -1;

                try
                {
                        MessageDAO dao = MessageDAOFactory.getDAO();
                        res = dao.autoDel(date, num, type);
                }
                catch (MessageDAOSysException gse)
                {
                        gse.printStackTrace();
                }

                return res;
        }

        public int insertMessage(int sender, String recieverName, int reciever,
                                 String title, String content, int type) throws MessageDAOSysException
        {
                int ret = 0;
                MessageForm mf = new MessageForm();
                MessageUserForm muf = new MessageUserForm();
                mf.setTitle(title);
                mf.setContent(content);
                mf.setSenderID(sender);
                mf.setRecieverName(recieverName);

                if (sender == -1)
                {
                        mf.setIsSave(0);
                }
                else
                {
                        mf.setIsSave(1);
                }

                mf.setIsPopUpMessage(1);
                mf.setType(1);
                muf.setUserID(reciever);
                muf.setType(1);

                MessageDAO dao = null;

                try
                {
                        dao = MessageDAOFactory.getDAO();
                        ret = dao.insertMessage(mf);
                        muf.setMessageID(ret);

                        MessageUserDAO muDAO = MessageUserDAOFactory.getDAO();
                        muDAO.insertMessageUser(muf);
                }
                catch (MessageUserDAOSysException e)
                {
                        e.printStackTrace();
                }

                return ret;
        }
}
