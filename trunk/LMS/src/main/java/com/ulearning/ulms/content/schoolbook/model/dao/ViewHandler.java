package com.ulearning.ulms.content.schoolbook.model.dao;

import net.sf.hibernate.Criteria;
import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Query;
import net.sf.hibernate.Session;
import net.sf.hibernate.expression.Criterion;
import net.sf.hibernate.expression.Order;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 视图查询类
 *
 */
import java.util.List;


public class ViewHandler
{
        private static final Log log = LogFactory.getLog(ViewHandler.class);
        private static final MTeachmainstorDAO maindao = MTeachmainstorDAO.getInstance();
        private static final MTeachstorItemTabDAO storedao = MTeachstorItemTabDAO.getInstance();
        private static ViewHandler instance;

        private ViewHandler()
        {
                log.debug("initialize");
        }

        public static ViewHandler getInstance()
        {
                if (null != instance)
                {
                        instance = new ViewHandler();
                }

                return instance;
        }

        //入库单查询
        public List getStoreList()
        {
                Query q = null;
                List list = null;
                Session s = null;

                try
                {
                        s = MTeachmainstorDAO.createSession();
                        q = s.createQuery("select main.tcmaindate from MTeachmainstor main");
                        list = q.list();

                        for (int i = 0; i < list.size(); i++)
                        {
                                System.out.println(list.get(i));
                        }
                }
                catch (Exception e)
                {
                        e.printStackTrace();
                }
                finally
                {
                        if (s != null)
                        {
                                try
                                {
                                        s.close();
                                }
                                catch (HibernateException e)
                                {
                                        // TODO Auto-generated catch block
                                        e.printStackTrace();
                                }
                        }
                }

                return list;
        }

        public static void main(String[] args)
        {
                ViewHandler view = ViewHandler.getInstance();
                view.getStoreList();
        }

        /**
         * 查询示例
         *
         Query q = s.createQuery("from foo in class Foo where foo.name=:name and foo.size=:size");
         q.setProperties(fooBean); // fooBean has getName() and getSize()
         List foos = q.list();
         Query q = sess.createQuery("select cat.name, cat from DomesticCat cat " +
         "order by cat.name");
         Query q = sess.createQuery("from DomesticCat cat where cat.name = :name");
         q.setString("name", "Fritz");
         Iterator cats = q.iterate();
         */
}
