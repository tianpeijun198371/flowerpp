package com.ulearning.ulms.tools.message.dao;

import com.ulearning.ulms.core.exceptions.ULMSSysException;
import com.ulearning.ulms.tools.message.exceptions.MessageUserDAOSysException;
import com.ulearning.ulms.tools.message.form.MessageUserForm;
import com.ulearning.ulms.tools.message.model.MessageUserModel;
import com.ulearning.ulms.tools.message.model.MessageUserModelPK;
import com.ulearning.ulms.util.HibernateDAO;
import com.ulearning.ulms.util.HibernateUtil;

import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Query;
import net.sf.hibernate.Session;

import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by IntelliJ IDEA.
 * User: keyh
 * Date: 2004-7-26
 * Time: 9:56:20
 * To change this template use File | Settings | File Templates.
 */
public class MessageUserDAOImpl implements MessageUserDAO
{
        public void insertMessageUser(MessageUserForm muf)
                throws MessageUserDAOSysException
        {
                Session session = null;

                try
                {
                        muf.setType(1);
                        session = HibernateUtil.getSession();
                        session.save(muf.getMessageUserModel());
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

        public void updateMessageUserByMessage(MessageUserForm muf)
                throws MessageUserDAOSysException
        {
                /*Session session = null;
               try
               {
                       Configuration config = new Configuration().configure();
                       SessionFactory sessionFactory = config.buildSessionFactory();
                       session = sessionFactory.openSession();
                       session.saveOrUpdate(muf.getMessageUserModel());
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
               } */
        }

        public void updateMessageUserByUser(MessageUserForm muf)
                throws MessageUserDAOSysException
        {
                //To change body of implemented methods use File | Settings | File Templates.
        }

        public void deleteMessageUser(int messageID)
                throws MessageUserDAOSysException
        {
                String hql = " from MessageUserModel as mum where mum.comp_id.messageid=" +
                        messageID;
                Session session = null;

                try
                {
                        session = session = HibernateUtil.getSession();
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

        public void deleteMessageUser(int userID, int messageID)
                throws MessageUserDAOSysException
        {
                String hql = "from MessageUserModel as mum where mum.comp_id.userid= " +
                        userID + " and  mum.comp_id.messageid=" + messageID;

                try
                {
                        HibernateDAO.delete(hql);
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                }
        }

        public MessageUserForm getMessageUserByMessage(int messageID)
                throws MessageUserDAOSysException
        {
                MessageUserForm muf = new MessageUserForm();
                MessageUserModel mum = new MessageUserModel();

                String hql = " from MessageUserModel as mum where mum.comp_id.messageid = " +
                        messageID;
                Session session = null;

                try
                {
                        session = session = HibernateUtil.getSession();

                        Query query = session.createQuery(hql);
                        List messageList = query.list();

                        if ((messageList != null) && (messageList.size() > 0))
                        {
                                mum = (MessageUserModel) messageList.get(0);
                                muf = muf.getMessageUserForm(mum);
                        }

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

                return muf;
        }

        public MessageUserForm getMessageUserByUser(int userID)
                throws MessageUserDAOSysException
        {
                MessageUserForm muf = new MessageUserForm();
                MessageUserModel mum = new MessageUserModel();

                String hql = " from MessageUserModel as mum where mum.comp_id.userid = " +
                        userID;
                Session session = null;

                try
                {
                        session = session = HibernateUtil.getSession();

                        Query query = session.createQuery(hql);
                        List messageList = query.list();

                        if ((messageList != null) && (messageList.size() > 0))
                        {
                                mum = (MessageUserModel) messageList.get(0);
                                muf = muf.getMessageUserForm(mum);
                        }

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

                return muf;
        }

        public List getMessageUserListByMessage(int messageID)
                throws MessageUserDAOSysException
        {
                String hql = " from MessageUserModel as mum where mum.comp_id.messageid=" +
                        messageID;
                Session session = null;
                List messageList = null;
                List list = new ArrayList();

                try
                {
                        session = HibernateUtil.getSession();

                        Query query = session.createQuery(hql);
                        messageList = query.list();
                        list = getFormList(messageList);
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

        public List getMessageUserListByUser(int userID)
                throws MessageUserDAOSysException
        {
                String hql = " from MessageUserModel as mum where mum.comp_id.userid=" +
                        userID;
                Session session = null;
                List messageList = null;
                List list = new ArrayList();

                try
                {
                        session = HibernateUtil.getSession();

                        Query query = session.createQuery(hql);
                        messageList = query.list();
                        list = getFormList(messageList);
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

        public void viewMessage(int messageID, int userID)
                throws MessageUserDAOSysException
        {
                MessageUserModelPK mpk = new MessageUserModelPK(messageID, userID);
                MessageUserModel mum = new MessageUserModel(mpk, 0);

                try
                {
                        HibernateDAO.update(mum);
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new MessageUserDAOSysException();
                }
        }

        private List getFormList(List modelList)
        {
                MessageUserForm muf = new MessageUserForm();
                MessageUserModel mum = new MessageUserModel();
                List list = new ArrayList();

                for (int i = 0; i < modelList.size(); i++)
                {
                        mum = (MessageUserModel) modelList.get(i);
                        muf = muf.getMessageUserForm(mum);
                        list.add(muf);
                }

                return list;
        }
}
