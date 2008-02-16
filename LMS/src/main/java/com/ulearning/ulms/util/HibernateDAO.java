package com.ulearning.ulms.util;

import com.ulearning.ulms.core.exceptions.ULMSSysException;
import com.ulearning.ulms.core.util.DateTimeUtil;
import com.ulearning.ulms.util.log.LogUtil;
import net.sf.hibernate.*;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class HibernateDAO
{
        protected static SessionFactory sessionFactory = null;

        public static Serializable add(Object details) throws ULMSSysException
        {
                Session session = null;
                Serializable serializable = null;
                try
                {
                        session = HibernateUtil.getSession();
                        session.clear();
                        serializable = session.save(details);
                        session.flush();
                        session.connection().commit();
                }
                catch (MappingException e)
                {
                        throw new ULMSSysException("MappingException while Hibernate add:  " + e, e);
                }
                catch (HibernateException e)
                {
                        throw new ULMSSysException("HibernateException while Hibernate add:  " + e, e);
                }
                catch (SQLException e)
                {
                        throw new ULMSSysException("SQLException while Hibernate add:  " + e, e);
                }
                finally
                {
                        try
                        {
                                HibernateUtil.releaseSession(session);
                        }
                        catch (HibernateException e)
                        {
                                throw new ULMSSysException("HibernateException while Hibernate update:  " + e, e);
                        }
                }
                return serializable;
        }

        public static void update(Object details) throws ULMSSysException
        {
                Session session = null;
                try
                {
                        session = HibernateUtil.getSession();
                        session.clear();
                        session.update(details);
                        session.flush();
                        session.connection().commit();
                }
                catch (MappingException e)
                {
                        throw new ULMSSysException("MappingException while Hibernate update:  " + e, e);
                }
                catch (HibernateException e)
                {
                        throw new ULMSSysException("HibernateException while Hibernate update:  " + e, e);
                }
                catch (SQLException e)
                {
                        throw new ULMSSysException("SQLException while Hibernate update:  " + e, e);
                }
                finally
                {
                        try
                        {
                                HibernateUtil.releaseSession(session);
                        }
                        catch (HibernateException e)
                        {
                                throw new ULMSSysException("HibernateException while Hibernate update:  " + e, e);
                        }
                }

        }

        public static void saveOrUpdateCopy(Object details) throws ULMSSysException
        {
                Session session = null;
                try
                {
                        session = HibernateUtil.getSession();
                        session.clear();
                        session.saveOrUpdateCopy(details);
                        session.flush();
                        session.connection().commit();
                }
                catch (MappingException e)
                {
                        throw new ULMSSysException("MappingException while Hibernate update:  " + e, e);
                }
                catch (HibernateException e)
                {
                        throw new ULMSSysException("HibernateException while Hibernate update:  " + e, e);
                }
                catch (SQLException e)
                {
                        throw new ULMSSysException("SQLException while Hibernate update:  " + e, e);
                }
                finally
                {
                        try
                        {
                                HibernateUtil.releaseSession(session);
                        }
                        catch (HibernateException e)
                        {
                                throw new ULMSSysException("HibernateException while Hibernate update:  " + e, e);
                        }
                }

        }

        public static void saveOrUpdate(Object details) throws ULMSSysException
        {
                Session session = null;
                try
                {
                        session = HibernateUtil.getSession();
                        session.clear();
                        session.saveOrUpdate(details);
                        session.flush();
                        session.connection().commit();
                }
                catch (MappingException e)
                {
                        throw new ULMSSysException("MappingException while Hibernate update:  " + e, e);
                }
                catch (HibernateException e)
                {
                        throw new ULMSSysException("HibernateException while Hibernate update:  " + e, e);
                }
                catch (SQLException e)
                {
                        throw new ULMSSysException("SQLException while Hibernate update:  " + e, e);
                }
                finally
                {
                        try
                        {
                                HibernateUtil.releaseSession(session);
                        }
                        catch (HibernateException e)
                        {
                                throw new ULMSSysException("HibernateException while Hibernate update:  " + e, e);
                        }
                }

        }

        public static void delete(Object details) throws ULMSSysException
        {
                Session session = null;
                try
                {
                        session = HibernateUtil.getSession();
                        session.clear();
                        session.delete(details);
                        session.flush();
                        session.connection().commit();
                }
                catch (MappingException e)
                {
                        throw new ULMSSysException("MappingException while Hibernate add:  " + e, e);
                }
                catch (HibernateException e)
                {
                        throw new ULMSSysException("HibernateException while Hibernate add:  " + e, e);
                }
                catch (SQLException e)
                {
                        throw new ULMSSysException("SQLException while Hibernate add:  " + e, e);
                }
                finally
                {
                        try
                        {
                                HibernateUtil.releaseSession(session);
                        }
                        catch (HibernateException e)
                        {
                                throw new ULMSSysException("HibernateException while Hibernate update:  " + e, e);
                        }
                }

        }

        public static void delete(String hql) throws ULMSSysException
        {
                Session session = null;
                try
                {
                        session = HibernateUtil.getSession();
                        session.clear();
                        LogUtil.debug("system", "delete sql: " + hql);
                        session.delete(hql);
                        session.flush();
                        session.connection().commit();
                }
                catch (MappingException e)
                {
                        throw new ULMSSysException("MappingException while Hibernate add:  " + e, e);
                }
                catch (HibernateException e)
                {
                        throw new ULMSSysException("HibernateException while Hibernate add:  " + e, e);
                }
                catch (SQLException e)
                {
                        throw new ULMSSysException("SQLException while Hibernate add:  " + e, e);
                }
                finally
                {
                        try
                        {
                                HibernateUtil.releaseSession(session);
                        }
                        catch (HibernateException e)
                        {
                                throw new ULMSSysException("HibernateException while Hibernate update:  " + e, e);
                        }
                }

        }


        public static List creatQuery(String hqlStr) throws ULMSSysException
        {
                List tmList = new ArrayList();
                try
                {
                        tmList = find(hqlStr, "", "", -1, -1); //调用
                }
                catch (Exception e)
                {
                        throw new ULMSSysException(e);
                }
                return tmList;
        }

        public static List find(String hqlStr) throws ULMSSysException
        {
                List tmList = new ArrayList();

/*                Session session = null;
                try
                {
                        session = HibernateUtil.getSession();
                        session.clear();
                        LogUtil.debug("system", "List find sql: " + hqlStr);
                        tmList = session.find(hqlStr);
                }
                catch (HibernateException he)
                {
                        throw new ULMSSysException("HibernateException while find:" + hqlStr + " \n" + he);
                }
                finally
                {
                        try
                        {
                                HibernateUtil.releaseSession(session);
                        }
                        catch (HibernateException e)
                        {
                                throw new ULMSSysException("HibernateException while Hibernate update:  " + e, e);
                        }
                }*/

                try
                {
                        tmList = find(hqlStr, "", "", -1, -1); //调用
                }
                catch (Exception e)
                {
                        throw new ULMSSysException(e);
                }
                return tmList;
        }

        /**
         * query          *
         *
         * @param hqlStr      :  "from Cat as c"
         * @param firstResult : start result
         * @param maxResults  : max result num           *
         * @ ULMSSysException If an ulmsSys error has occurred.
         */

        public static List find(String hqlStr, int firstResult, int maxResults) throws ULMSSysException
        {
                List tmList = new ArrayList();
                /*
                Session session = null;
                try
                {
                        session = HibernateUtil.getSession();
                        LogUtil.debug("system", "List find sql: " + hqlStr + " firstResult="
                                + firstResult + " maxResults=" + maxResults);
                        Query q = session.createQuery(hqlStr);
                        q.setFirstResult(firstResult);
                        q.setMaxResults(maxResults);
                        tmList = q.list();
                }
                catch (HibernateException he)
                {
                        throw new ULMSSysException("HibernateException while find:" + hqlStr + " \n" + he);
                }
                finally
                {
                        try
                        {
                                HibernateUtil.releaseSession(session);
                        }
                        catch (HibernateException e)
                        {
                                throw new ULMSSysException("HibernateException while Hibernate update:  " + e, e);
                        }
                }
                */
                try
                {
                        tmList = find(hqlStr, "", "", firstResult, maxResults); //调用
                }
                catch (Exception e)
                {
                        throw new ULMSSysException(e);
                }
                return tmList;
        }

        /**
         * query
         *
         * @param hqlStr        :  "from Cat as c"
         * @param startDateTime 查询开始日期时间 为“”时不起作用。
         * @param endDateTime   查询结束日期时间 为“”时不起作用。
         * @param firstResult   : start result  翻页使用 -1时不起作用  和maxResults一起使用
         * @param maxResults    : max result num   翻页使用 -1时不起作用  firstResult      *
         * @ ULMSSysException If an ulmsSys error has occurred.
         */
        public static List find(String hqlStr, String startDateTime, String endDateTime,
                                int firstResult, int maxResults) throws ULMSSysException
        {
                List tmList = new ArrayList();
                Session session = null;
                try
                {
                        session = HibernateUtil.getSession();
                        session.clear();
                        LogUtil.debug("system", "List find sql: " + hqlStr + " firstResult="
                                + firstResult + " maxResults=" + maxResults);

                        Query q = session.createQuery(hqlStr);
                        if (null != startDateTime && !startDateTime.trim().equals(""))
                        {
                                q.setDate("startDateTime", DateTimeUtil.toDate(startDateTime + " 00:00:00"));
                                //q.setParameter("startDateTime", DateTimeUtil.parseDate(startDateTime));
                        }
                        if (null != endDateTime && !endDateTime.trim().equals(""))
                        {
                                q.setDate("endDateTime", DateTimeUtil.toDate(endDateTime + " 24:00:00"));
                                //q.setParameter("endDateTime", DateTimeUtil.toDate(endDateTime + " 23:59:59"));
                                //q.setParameter("endDateTime", DateTimeUtil.parseDate(endDateTime));
                        }
                        if (firstResult>=0)
                        {
                                q.setFirstResult(firstResult);
                        }
                        if (maxResults>0)
                        {
                                q.setMaxResults(maxResults);
                        }
                        tmList = q.list();
                }
                catch (HibernateException he)
                {
                        throw new ULMSSysException("HibernateException while find:" + hqlStr + " \n" + he);
                }
                finally
                {
                        try
                        {
                                HibernateUtil.releaseSession(session);
                        }
                        catch (HibernateException e)
                        {
                                throw new ULMSSysException("HibernateException while Hibernate update:  " + e, e);
                        }
                }
                return tmList;
        }


        /**
         * query
         * 一个find的重载
         *
         * @param hqlStr        :  "from Cat as c"
         * @param startDateTime 查询开始日期时间 为“”时不起作用。
         * @param endDateTime   查询结束日期时间 为“”时不起作用。
         * @param firstResult   : start result  翻页使用 -1时不起作用  和maxResults一起使用
         * @param maxResults    : max result num   翻页使用 -1时不起作用  firstResult      *
         * @ ULMSSysException If an ulmsSys error has occurred.
         */
        public static List find(String hqlStr, Date startDateTime, Date endDateTime,
                                int firstResult, int maxResults) throws ULMSSysException
        {
                List tmList = new ArrayList();
                Session session = null;
                try
                {
                        session = HibernateUtil.getSession();
                        session.clear();
                        LogUtil.debug("system", "List find sql: " + hqlStr + " firstResult="
                                + firstResult + " maxResults=" + maxResults);

                        Query q = session.createQuery(hqlStr);
                        if (null != startDateTime)
                        {
                                q.setDate("startDateTime", startDateTime);
                                //q.setParameter("startDateTime", DateTimeUtil.parseDate(startDateTime));
                        }
                        if (null != endDateTime)
                        {
                                q.setDate("endDateTime", endDateTime);
                                //q.setParameter("endDateTime", DateTimeUtil.toDate(endDateTime + " 23:59:59"));
                                //q.setParameter("endDateTime", DateTimeUtil.parseDate(endDateTime));
                        }
                        if (-1 != firstResult)
                        {
                                q.setFirstResult(firstResult);
                        }
                        if (-1 != maxResults)
                        {
                                q.setMaxResults(maxResults);
                        }
                        tmList = q.list();
                }
                catch (HibernateException he)
                {
                        throw new ULMSSysException("HibernateException while find:" + hqlStr + " \n" + he);
                }
                finally
                {
                        try
                        {
                                HibernateUtil.releaseSession(session);
                        }
                        catch (HibernateException e)
                        {
                                throw new ULMSSysException("HibernateException while Hibernate update:  " + e, e);
                        }
                }
                return tmList;
        }

        public static Object load(Class c, Serializable serializable) throws ULMSSysException
        {
                Session session = null;
                Object object = null;
                try
                {
                        session = HibernateUtil.getSession();
                        session.clear();
                        object = session.get(c, serializable);
                }
                catch (MappingException e)
                {
                        throw new ULMSSysException("MappingException while Hibernate add:  " + e, e);
                }
                catch (HibernateException e)
                {
                        throw new ULMSSysException("HibernateException while Hibernate add:  " + e, e);
                }
                finally
                {
                        try
                        {
                                HibernateUtil.releaseSession(session);
                        }
                        catch (HibernateException e)
                        {
                                throw new ULMSSysException("HibernateException while Hibernate update:  " + e, e);
                        }
                }
                return object;
        }

        //统计记录数 如 hqlStr="select count(*) from AnnouncementModel" ;
        public static int count(String hqlStr) throws ULMSSysException
        {
                int num = -1;
                Session session = null;
                try
                {
                        session = HibernateUtil.getSession();
                        session.clear();
                        LogUtil.debug("system", "count sql: " + hqlStr);
                        Query q = session.createQuery(hqlStr);
                        //方法1
                        Iterator it = q.iterate();
                        if (it.hasNext())
                        {
                                Integer amount = (Integer) it.next();
                                num = amount.intValue();
                        }
                        /*  方法2
                        Integer amount = new Integer(0);
                        if(!q.list().isEmpty())
                        {
                                Integer amount  = (Integer) q.list().get(0);
                                num=amount.intValue();
                        }
                        */
                }
                catch (HibernateException he)
                {
                        throw new ULMSSysException("HibernateException while find:" + hqlStr + " \n" + he);
                }
                finally
                {
                        try
                        {
                                HibernateUtil.releaseSession(session);
                        }
                        catch (HibernateException e)
                        {
                                throw new ULMSSysException("HibernateException while Hibernate update:  " + e, e);
                        }
                }
                return num;
        }

        //统计函数的使用
        public static int count1(String hqlStr) throws ULMSSysException
        {
                hqlStr = "select sum(a.userTime),max(a.userTime) ,min(a.userTime),a.courseID  from AccessModel  a group by courseID";
                int num = -1;
                Session session = null;
                try
                {
                        session = HibernateUtil.getSession();
                        session.clear();
                        LogUtil.debug("system", "count sql: " + hqlStr);
                        Query q = session.createQuery(hqlStr);
                        List it = q.list();
                        Iterator it1 = it.iterator();
                        while (it1.hasNext())
                        {
                                Object[] row = (Object[]) it1.next();
                                for (int i = 0; i < row.length; i++)
                                {
                                        Object obj = (Object) row[i];
                                        if (obj != null)
                                        {
                                                System.out.println(obj.getClass().getName());
                                                System.out.println(obj.toString());
                                        }
                                }
                        }
                }
                catch (HibernateException he)
                {
                        throw new ULMSSysException("HibernateException while find:" + hqlStr + " \n" + he);
                }
                finally
                {
                        try
                        {
                                HibernateUtil.releaseSession(session);
                        }
                        catch (HibernateException e)
                        {
                                throw new ULMSSysException("HibernateException while Hibernate update:  " + e, e);
                        }
                }
                return num;
        }

        public static void main(String[] args)
        {
                try
                {
                        System.out.println(HibernateDAO.count1(""));
                }
                catch (ULMSSysException e)
                {
                        System.out.println("" + e);
                }

        }
}