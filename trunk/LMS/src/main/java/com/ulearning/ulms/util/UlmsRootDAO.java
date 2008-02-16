package com.ulearning.ulms.util;

import net.sf.hibernate.*;

import java.util.*;
import java.io.Serializable;

import org.apache.log4j.Logger;

/**
 * Created by IntelliJ IDEA.
 * User: suh
 * Date: 2006-3-20
 * Time: 18:40:39
 * To change this template use File | Settings | File Templates.
 */
public final class UlmsRootDAO extends _BaseRootDAO
{
        private static final Logger log = Logger.getLogger(UlmsRootDAO.class);
        //Session中的键集合
        private static Map configModelfiles = new HashMap();
        //Session中类作为键的集合
        private static Map configModelClasses = new HashMap();
        //系统加载
        private static UlmsRootDAO instance = null;

        static
        {
                log.debug("inside loading!");
                try
                {
                        instance = new UlmsRootDAO(configModelfiles, configModelClasses);
                }
                catch (Exception e)
                {
                        log.error("inside loading error!");
                }
        }

        private UlmsRootDAO(Map files, Map classes)
        {
                files = configModelfiles;
                classes = configModelClasses;
        }

        //得到单例
        public static UlmsRootDAO getInstance()
        {
                if (instance == null)
                {
                        instance = new UlmsRootDAO(configModelfiles, configModelClasses);
                }
                return instance;
        }

        private void putModelKey(String modelname)
        {
                configModelfiles.put(modelname, modelname);
        }

        private void putModelKey(Class modelClass)
        {
                configModelClasses.put(modelClass, modelClass);
        }

        /*
          分析hql语句，得到Model名，从而建立或取出Session
        */
        public String getModelName(String hql)
        {
                log.debug("inside model name!");
                String modelName = null;
                try
                {
                        String temp = "Model";
                        int last = hql.lastIndexOf(temp);
                        if (last == -1)
                        {
                                return null;
                        }
                        int end = last + temp.length();
                        modelName = hql.substring(0, end);
                        int start = modelName.lastIndexOf(" ");
                        modelName = modelName.substring(start + 1);
                }
                catch (Exception e)
                {
                        return null;
                }
                return modelName;
        }

        private Class getModelName(Object model)
        {
                log.debug("inside model object");
                Class modelName = null;
                try
                {
                        modelName = model.getClass();
                }
                catch (Exception e)
                {
                        return null;
                }
                return modelName;
        }

        /**
         * Return the name of the configuration file to be used with this DAO or null if default
         */
        public String getConfigurationFileName()
        {
                if (!configModelfiles.isEmpty())
                {
                        Object arr[] = configModelfiles.values().toArray();
                        return (String) arr[arr.length - 1];
                }
                return null;
        }

        protected Class getReferenceClass()
        {
                if (!configModelClasses.isEmpty())
                {
                        Object arr[] = configModelClasses.values().toArray();
                        return arr[arr.length - 1].getClass();
                }
                return null;  //To change body of implemented methods use File | Settings | File Templates.
        }

        /**
         * 三种得到Session的方式，建议使用后两种，参数规则同基类，
         * 建议放弃getSession()方法的使用，如果有特殊情况一定要使用请慎重考虑，
         * 不推荐使用getSession()得到Session后做批量或循环数据更新
         */
        protected Session getSession() throws HibernateException
        {
                return super.getSession();    //To change body of overridden methods use File | Settings | File Templates.
        }

        protected Session getSession(String configName) throws HibernateException
        {
                if (configModelfiles.get(configName) == null)
                {
                        this.putModelKey(configName);
                }
                return getSessionFactory().openSession();
        }

        protected Session getSession(Class configObj) throws HibernateException
        {
                if (configModelClasses.get(configObj) == null)
                {
                        this.putModelKey(configObj.getClass());
                }
                return this.getSession(configModelClasses.get(configObj).getClass().getName());
        }

        public static void main(String args[])
        {
                // ulmsRootDAO root = new ulmsRootDAO();
                Map sh = new HashMap();
                sh.put("1", "1");
                sh.put("2", "2");
                //System.out.println(sh.get("1"));
                //root.putModelKey("dd");
                //root.putModelKey("dd");
                //System.out.println();
                //root.getModelName(new java.util.Date());
                //root.getConfigurationFileName();
                //System.out.println(root.getConfigurationFileName());
        }

        //调用getSession得到Session后一定要关闭此Session
        public void closeSession(Session session) throws HibernateException
        {
                if (session == null)
                {
                        super.closeSession();
                }
                session.close();
        }

        public Serializable add(Object details) throws HibernateException
        {
                Class objclass = this.getModelName(details);
                try
                {
                        Session session = this.getSession(objclass);
                        Serializable trn = super.save(details, session);
                        return trn;
                }
                catch (HibernateException e)
                {
                        throw e;
                }
        }

        public void update(Object details) throws HibernateException
        {
                Class objclass = this.getModelName(details);
                try
                {
                        Session session = this.getSession(objclass);
                        super.update(details, session);
                }
                catch (HibernateException e)
                {
                        throw e;
                }
        }

        public void saveOrUpdateCopy(Object details) throws HibernateException
        {
                Class objclass = this.getModelName(details);
                try
                {
                        Session session = this.getSession(objclass);
                        session.saveOrUpdateCopy(details);
                        closeSession(session);
                }
                catch (HibernateException e)
                {
                        throw e;
                }
        }

        public void saveOrUpdate(Object details) throws HibernateException
        {
                Class objclass = this.getModelName(details);
                try
                {
                        Session session = this.getSession(objclass);
                        super.saveOrUpdate(details);
                }
                catch (HibernateException e)
                {
                        throw e;
                }
        }

        public void delete(Object details) throws HibernateException
        {
                Class objclass = this.getModelName(details);
                try
                {
                        Session session = this.getSession(objclass);
                        super.delete(details);
                }
                catch (HibernateException e)
                {
                        throw e;
                }
        }
        /**
         * 注意:使用Criteria后一定要执行DAO.closeSession()方法.
         * 如Criteria由AuthUserDAO建立,则一定要在finally块中加入AuthUserDAO.closeSession().
         */
        /**
         * 注意:使用Query后一定要执行DAO.closeSession()方法.
         * 如Query由AuthUserDAO建立,则一定要在finally块中加入AuthUserDAO.closeSession().
         */
        public Query createQuery(String arg) throws HibernateException
        {
                return super.getSession().createQuery(arg);
        }

        public Query createQuery(String arg, Session s) throws HibernateException
        {
                return s.createQuery(arg);
        }
}
