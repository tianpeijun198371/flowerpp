package com.ulearning.ulms.tools.message.dao;

import com.ulearning.ulms.core.exceptions.ULMSSysException;
import com.ulearning.ulms.core.util.DateTimeUtil;
import com.ulearning.ulms.core.util.StringUtil;
import com.ulearning.ulms.tools.message.bean.MessageUserDAOHelper;
import com.ulearning.ulms.tools.message.exceptions.MessageDAOSysException;
import com.ulearning.ulms.tools.message.form.MessageForm;
import com.ulearning.ulms.tools.message.model.MessageModel;
import com.ulearning.ulms.tools.message.model.MessageUserModel;
import com.ulearning.ulms.user.bean.UserHelper;
import com.ulearning.ulms.user.model.UserModel;
import com.ulearning.ulms.util.HibernateDAO;
import com.ulearning.ulms.util.HibernateUtil;

import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Query;
import net.sf.hibernate.Session;

import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;


/**
 * Created by IntelliJ IDEA.
 * User: keyh
 * Date: 2004-7-23
 * Time: 16:23:46
 * To change this template use File | Settings | File Templates.
 */
public class MessageDAOImpl implements MessageDAO
{
        public int insertMessage(MessageForm messageForm)
                throws MessageDAOSysException
        {
                Session session = null;
                messageForm.setSendTime(new Date());

                String key = "";

                try
                {
                        session = HibernateUtil.getSession();
                        key = session.save(messageForm.getMessageModel()).toString();
                        session.flush();
                        session.connection().commit();
                }
                catch (HibernateException he)
                {
                        he.printStackTrace();
                }
                catch (SQLException se)
                {
                        se.printStackTrace();
                }
                finally
                {
                        try
                        {
                                if (session != null)
                                {
                                        HibernateUtil.releaseSession(session);
                                }
                        }
                        catch (HibernateException he)
                        {
                                he.printStackTrace();
                        }
                }

                return Integer.parseInt(key);
        }

        public void updateMessage(MessageForm messageForm)
                throws MessageDAOSysException
        {
                Session session = null;

                try
                {
                        session = HibernateUtil.getSession();

                        Object object = session.get(MessageModel.class,
                                new Integer(messageForm.getMessageID()));
                        MessageModel mm = new MessageModel();
                        mm = (MessageModel) object;
                        mm.setIspopupmessage(0);
                        session.save(mm);
                        session.flush();
                        session.connection().commit();
                }
                catch (HibernateException he)
                {
                        he.printStackTrace();
                }
                catch (SQLException se)
                {
                        se.printStackTrace();
                }
                finally
                {
                        try
                        {
                                if (session != null)
                                {
                                        HibernateUtil.releaseSession(session);
                                }
                        }
                        catch (HibernateException he)
                        {
                                he.printStackTrace();
                        }
                }
        }

        public void deleteMessage(int messageID) throws MessageDAOSysException
        {
                String hql = " from MessageModel where messageid=" + messageID;
                Session session = null;

                try
                {
                        session = HibernateUtil.getSession();
                        session.delete(hql);
                        session.flush();
                        session.connection().commit();
                }
                catch (HibernateException he)
                {
                        he.printStackTrace();
                }
                catch (SQLException se)
                {
                        se.printStackTrace();
                }
                finally
                {
                        try
                        {
                                if (session != null)
                                {
                                        HibernateUtil.releaseSession(session);
                                }
                        }
                        catch (HibernateException he)
                        {
                                he.printStackTrace();
                        }
                }
        }

        public void deleteMessageFromRecieveBox(int messageID)
                throws MessageDAOSysException
        {
                MessageModel messageModel = new MessageModel();
                Session session = null;

                try
                {
                        session = HibernateUtil.getSession();

                        Object message = session.get(MessageModel.class,
                                new Integer(messageID));

                        if (message != null)
                        {
                                messageModel = (MessageModel) message;
                                messageModel.setType(0);
                                session.saveOrUpdateCopy(messageModel);
                                session.flush();
                                session.connection().commit();
                        }
                }
                catch (HibernateException he)
                {
                        he.printStackTrace();
                }
                catch (SQLException se)
                {
                        se.printStackTrace();
                }
                finally
                {
                        try
                        {
                                if (session != null)
                                {
                                        HibernateUtil.releaseSession(session);
                                }
                        }
                        catch (HibernateException he)
                        {
                                he.printStackTrace();
                        }
                }
        }

        public void deleteMessageFromSendBox(int messageID)
                throws MessageDAOSysException
        {
                MessageModel messageModel = new MessageModel();
                Session session = null;

                try
                {
                        session = HibernateUtil.getSession();

                        Object message = session.get(MessageModel.class,
                                new Integer(messageID));

                        if (message != null)
                        {
                                messageModel = (MessageModel) message;
                                messageModel.setIssave(0);
                                session.saveOrUpdateCopy(messageModel);
                                session.flush();
                                session.connection().commit();
                        }
                }
                catch (HibernateException he)
                {
                        he.printStackTrace();
                }
                catch (SQLException se)
                {
                        se.printStackTrace();
                }
                finally
                {
                        try
                        {
                                if (session != null)
                                {
                                        HibernateUtil.releaseSession(session);
                                }
                        }
                        catch (HibernateException he)
                        {
                                he.printStackTrace();
                        }
                }
        }

        public MessageForm getMessage(int messageID) throws MessageDAOSysException
        {
                String hql = "from MessageModel as mm where mm.messageid=" + messageID;
                MessageForm messageForm = new MessageForm();
                List messageList = null;

                try
                {
                        messageList = HibernateDAO.find(hql);

                        if ((messageList != null) && (messageList.size() > 0))
                        {
                                MessageModel messageModel = new MessageModel();
                                messageModel = (MessageModel) messageList.get(0);
                                messageForm = messageForm.getMessageForm(messageModel);
                        }
                }
                catch (ULMSSysException se)
                {
                        se.printStackTrace();
                }

                return messageForm;
        }

        public List getNewMessage(int recieverID) throws MessageDAOSysException
        {
                String hql = " from MessageModel as mm, UserModel as um, MessageUserModel  as  mum" +
                        " where mm.messageid=mum.comp_id.messageid " + " and mm.type= 1 " +
                        " and mum.comp_id.userid = " + recieverID + " and um.userid = " +
                        recieverID + " and mum.type= 1    order  by mm.sendtime DESC";
                Session session = null;
                List messageList = null;
                List list = new ArrayList();

                try
                {
                        session = HibernateUtil.getSession();

                        Query query = session.createQuery(hql);
                        messageList = query.list();
                        list = getFormList(messageList, 0);
                        session.flush();
                        session.connection().commit();
                }
                catch (Exception e)
                {
                        e.printStackTrace();
                }
                finally
                {
                        try
                        {
                                if (session != null)
                                {
                                        HibernateUtil.releaseSession(session);
                                }
                        }
                        catch (HibernateException he)
                        {
                                he.printStackTrace();
                        }
                }

                return list;
        }

        public List getMessageListBySender(int senderID, int type)
                throws MessageDAOSysException
        {
                String hql = " from MessageModel as mm,UserModel as um" +
                        " where mm.senduserid =" + senderID + " and mm.type= " + type +
                        " and mm.issave=1 " + " and um.userid=" + senderID +
                        " order  by mm.messageid DESC";
                ;

                Session session = null;
                List messageList = null;
                List list = new ArrayList();

                try
                {
                        session = HibernateUtil.getSession();

                        Query query = session.createQuery(hql);
                        messageList = query.list();
                        list = getFormList(messageList, 1);
                        session.flush();
                        session.connection().commit();
                }
                catch (Exception e)
                {
                        e.printStackTrace();
                }
                finally
                {
                        try
                        {
                                if (session != null)
                                {
                                        HibernateUtil.releaseSession(session);
                                }
                        }
                        catch (HibernateException he)
                        {
                                he.printStackTrace();
                        }
                }

                return list; //To change body of implemented methods use File | Settings | File Templates.
        }

        public List getMessageListByReciever(int recieverID, int type)
                throws MessageDAOSysException
        {
                String hql = " from MessageModel as mm, UserModel as um, MessageUserModel  as  mum" +
                        " where mm.messageid=mum.comp_id.messageid " +
                        " and mum.comp_id.userid = um.userid" + " and um.userid = " +
                        recieverID + " order  by mm.messageid DESC";
                Session session = null;
                List messageList = null;
                List list = new ArrayList();

                try
                {
                        session = HibernateUtil.getSession();

                        Query query = session.createQuery(hql);
                        messageList = query.list();
                        list = getFormList(messageList, 0);
                        session.flush();
                        session.connection().commit();
                }
                catch (Exception e)
                {
                        e.printStackTrace();
                }
                finally
                {
                        try
                        {
                                if (session != null)
                                {
                                        HibernateUtil.releaseSession(session);
                                }
                        }
                        catch (HibernateException he)
                        {
                                he.printStackTrace();
                        }
                }

                return list;
        }

        public int autoDel(Date date, int num, int type)
                throws MessageDAOSysException
        {
                String hql = "from MessageModel as  mm, UserModel as um,MessageUserModel as mum where " +
                        "  mm.senduserid=um.userid and " +
                        "  mm.messageid=mum.comp_id.messageid  order  by mm.sendtime ";
                Session session = null;
                List messageList = null;

                int delnum = 0;
                List list = new ArrayList();

                try
                {
                        session = HibernateUtil.getSession();

                        Query query = session.createQuery(hql);
                        messageList = query.list();
                        list = getFormList(messageList, 1);
                        session.flush();
                        session.connection().commit();

                        int cnt = list.size();

                        if (cnt > num)
                        {
                                MessageForm mf = (MessageForm) list.get(num - 1);
                                Date tmpDate = mf.getSendTime();

                                if (date.before(tmpDate))
                                {
                                        date = tmpDate;
                                }
                        }

                        hql = "from MessageModel as  mm, UserModel as um,MessageUserModel as mum where " +
                                " mm.sendtime<=:date and mm.senduserid=um.userid and " +
                                "  mm.messageid=mum.comp_id.messageid  order  by mm.sendtime ";

                        query = session.createQuery(hql);
                        query.setParameter("date", date);
                        messageList = query.list();
                        list = getFormList(messageList, 1);
                        session.flush();
                        session.connection().commit();

                        MessageUserDAOHelper mudh = new MessageUserDAOHelper();

                        if (list != null)
                        {
                                for (int i = 0; i < list.size(); i++)
                                {
                                        delnum++;

                                        MessageForm mf = (MessageForm) list.get(i);
                                        int messageID = mf.getMessageID();
                                        mudh.deleteMessageUser(messageID);
                                        deleteMessage(messageID);
                                }
                        }
                }
                catch (Exception e)
                {
                        e.printStackTrace();
                }
                finally
                {
                        try
                        {
                                if (session != null)
                                {
                                        HibernateUtil.releaseSession(session);
                                }
                        }
                        catch (HibernateException he)
                        {
                                he.printStackTrace();
                        }
                }

                return delnum;
        }

        private List getFormList(List modelList, int type)
        {
                List list = new ArrayList();
                MessageForm mf = new MessageForm();
                UserModel um = new UserModel();
                MessageModel mm = new MessageModel();
                Object[] record = null;

                for (Iterator iter = modelList.iterator(); iter.hasNext();)
                {
                        record = (Object[]) iter.next();
                        mm = (MessageModel) record[0];
                        mf = mf.getMessageForm(mm);
                        um = (UserModel) record[1];

                        if (mm.getSenduserid() == -1)
                        {
                                mf.setSenderName("ϵͳ");
                        }
                        else
                        {
                                mf.setSenderName(UserHelper.getUser(String.valueOf(
                                        mm.getSenduserid())).getName());
                        }

                        if (type == 0)
                        {
                                MessageUserModel mum = new MessageUserModel();
                                mum = (MessageUserModel) record[2];

                                if (mum.getType() == 1)
                                {
                                        mf.setRead(true);
                                }
                                else
                                {
                                        mf.setRead(false);
                                }
                        }
                        else
                        {
                                mf.setRead(true);
                        }

                        list.add(mf);
                }

                return list;
        }

        private MessageForm convertRs2Form(ResultSet rs)
        {
                MessageForm mf = new MessageForm();
                int rsIndex = 1;

                try
                {
                        mf.setMessageID(rs.getInt("MessageID"));
                        mf.setSenderID(rs.getInt("SendUserID"));
                        mf.setSenderName("Name");
                        mf.setTitle(StringUtil.nullToStr(rs.getString("Title")));
                        mf.setContent(StringUtil.nullToStr(rs.getString("Content")));
                        mf.setIsPopUpMessage(rs.getInt("IsPopupMessage"));
                        mf.setIsSave(rs.getInt("IsSave"));
                        mf.setType(rs.getInt("Type"));
                        mf.setSendTime(DateTimeUtil.toDate(rs.getDate("SendTime"),
                                rs.getTime("SendTime")));

                        mf.setRecieverName("Name");
                }
                catch (SQLException sql)
                {
                        sql.printStackTrace();
                }

                return mf;
        }
}
