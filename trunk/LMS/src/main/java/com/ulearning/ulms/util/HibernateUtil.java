/** * HibernateUtil.java.
 * User: xiejh  Date: 2004-8-30 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.util;

import com.ulearning.ulms.core.exceptions.ULMSAppException;
import net.sf.hibernate.*;
import net.sf.hibernate.cfg.Configuration;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.sql.SQLException;

public class HibernateUtil
{
        private static Log log = LogFactory.getLog(HibernateUtil.class);
        private static Configuration configuration;
        private static SessionFactory sessionFactory;
        private static final ThreadLocal threadSession = new ThreadLocal();
        private static final ThreadLocal threadTransaction = new ThreadLocal();

        // Create the initial SessionFactory from the default configuration files
        static
        {
                try
                {
                        configuration = new Configuration();
                        sessionFactory = configuration.configure().buildSessionFactory();
                        // We could also let Hibernate bind it to JNDI:
                        // configuration.configure().buildSessionFactory()
                }
                catch (Throwable ex)
                {
                        // We have to catch Throwable, otherwise we will miss
                        // NoClassDefFoundError and other subclasses of Error
                        log.error("Building SessionFactory failed.", ex);
                        throw new ExceptionInInitializerError(ex);
                }
        }

        /**
         * Returns the SessionFactory used for this static class.
         *
         * @return SessionFactory
         */
        public static SessionFactory getSessionFactory()
        {
                /* Instead of a static variable, use JNDI:
                SessionFactory sessions = null;
                try {
                        Context ctx = new InitialContext();
                        String jndiName = "java:hibernate/HibernateFactory";
                        sessions = (SessionFactory)ctx.lookup(jndiName);
                } catch (NamingException ex) {
                        throw new ELMSAppException(ex);
                }
                return sessions;
                */
                return sessionFactory;
        }

        /**
         * Returns the original Hibernate configuration.
         *
         * @return Configuration
         */
        public static Configuration getConfiguration()
        {
                return configuration;
        }

        /**
         * Rebuild the SessionFactory with the static Configuration.
         */
        public static void rebuildSessionFactory()
                throws ULMSAppException
        {
                synchronized (sessionFactory)
                {
                        try
                        {
                                sessionFactory = getConfiguration().buildSessionFactory();
                        }
                        catch (Exception ex)
                        {
                                throw new ULMSAppException(ex);
                        }
                }
        }

        /**
         * Rebuild the SessionFactory with the given Hibernate Configuration.
         *
         * @param cfg
         */
        public static void rebuildSessionFactory(Configuration cfg)
                throws ULMSAppException
        {
                synchronized (sessionFactory)
                {
                        try
                        {
                                sessionFactory = cfg.buildSessionFactory();
                                configuration = cfg;
                        }
                        catch (Exception ex)
                        {
                                throw new ULMSAppException(ex);
                        }
                }
        }

        /**
         * Get current Session
         *
         * @return Prepared Session Object
         * @throws HibernateException
         */
        public static Session getSession() throws HibernateException
        {
                Session s = (Session) threadSession.get();
                try
                {
                        if (s == null)
                        {
                                log.debug("Opening new Session for this thread.");
                                s = getSessionFactory().openSession();
                                threadSession.set(s);
                        }

                }
                catch (HibernateException ex)
                {
                        throw ex;
                }
                try
                {
                        //If JNDI datasource'autocommit is true, the connection need to update
                        if (s != null)
                        {
                                s.connection().setAutoCommit(false);
                        }
                }
                catch (SQLException se)
                {
                        se.printStackTrace();
                }
                return s;
        }

        /**
         * Release the session and disconnect the database
         *
         * @throws HibernateException
         */
        public static void closeSession() throws HibernateException
        {
                try
                {
                        Session s = (Session) threadSession.get();
                        threadSession.set(null);
                        if (s != null && s.isOpen())
                        {
                                log.debug("Closing Session of this thread.");
                                s.close();
                        }
                }
                catch (HibernateException ex)
                {
                        throw ex;
                }
        }

        /**
         * Release the session and disconnect the database
         * 这是个空方法，真正关闭session,是在request HibernateFilter里做的。
         *
         * @throws HibernateException
         */
        public static void releaseSession(Session s) throws HibernateException
        {


        }


        /**
         * Start a new database transaction.
         */
        public static void beginTransaction()
                throws ULMSAppException
        {
                Transaction tx = (Transaction) threadTransaction.get();
                try
                {
                        if (tx == null)
                        {
                                log.debug("Starting new database transaction in this thread.");
                                tx = getSession().beginTransaction();
                                threadTransaction.set(tx);
                        }
                }
                catch (HibernateException ex)
                {
                        throw new ULMSAppException(ex);
                }
        }

        /**
         * Commit the database transaction.
         */
        public static void commitTransaction()
                throws ULMSAppException
        {
                Transaction tx = (Transaction) threadTransaction.get();
                try
                {
                        if (tx != null && !tx.wasCommitted()
                                && !tx.wasRolledBack())
                        {
                                log.debug("Committing database transaction of this thread.");
                                tx.commit();
                        }
                        threadTransaction.set(null);
                }
                catch (HibernateException ex)
                {
                        rollbackTransaction();
                        throw new ULMSAppException(ex);
                }
        }

        /**
         * Commit the database transaction.
         */
        public static void rollbackTransaction()
                throws ULMSAppException
        {
                Transaction tx = (Transaction) threadTransaction.get();
                try
                {
                        threadTransaction.set(null);
                        if (tx != null && !tx.wasCommitted() && !tx.wasRolledBack())
                        {
                                log.debug("Tyring to rollback database transaction of this thread.");
                                tx.rollback();
                        }
                }
                catch (HibernateException ex)
                {
                        throw new ULMSAppException(ex);
                }
                finally
                {
                        try
                        {
                                closeSession();
                        }
                        catch (Exception ex)
                        {

                        }
                }
        }

        /**
         * Reconnects a Hibernate Session to the current Thread.
         *
         * @param session The Hibernate Session to be reconnected.
         */
        public static void reconnect(Session session)
                throws ULMSAppException
        {
                try
                {
                        session.reconnect();
                        threadSession.set(session);
                }
                catch (HibernateException ex)
                {
                        throw new ULMSAppException(ex);
                }
        }

        /**
         * Disconnect and return Session from current Thread.
         *
         * @return Session the disconnected Session
         */
        public static Session disconnectSession()
                throws ULMSAppException
        {

                Session session = null;
                try
                {
                        session = getSession();
                        threadSession.set(null);
                        if (session.isConnected() && session.isOpen())
                        {
                                session.disconnect();
                        }
                }
                catch (HibernateException ex)
                {
                        throw new ULMSAppException(ex);
                }
                return session;
        }

}