package com.ulearning.ulms.content.schoolbook.model.base;

import com.ulearning.ulms.content.schoolbook.model.dao.MTeachsortDAO;

import net.sf.hibernate.Hibernate;
import net.sf.hibernate.Session;


/**
 * This class has been automatically generated by Hibernate Synchronizer.
 * For more information or documentation, visit The Hibernate Synchronizer page
 * at http://www.binamics.com/hibernatesync or contact Joe Hudson at joe@binamics.com.
 * <p/>
 * This is an automatically generated DAO class which should not be edited.
 */
public abstract class BaseMTeachsortDAO extends com.ulearning.ulms.content.schoolbook.model.dao._RootDAO
{
        public static MTeachsortDAO instance;

        /**
         * Return a singleton of the DAO
         */
        public static MTeachsortDAO getInstance()
        {
                if (null == instance)
                {
                        instance = new MTeachsortDAO();
                }

                return instance;
        }

        /**
         * com.ulearning.ulms.content.schoolbook.model.dao._RootDAO _RootDAO.getReferenceClass()
         */
        public Class getReferenceClass()
        {
                return com.ulearning.ulms.content.schoolbook.model.MTeachsort.class;
        }

        public com.ulearning.ulms.content.schoolbook.model.MTeachsort load(long key)
                throws net.sf.hibernate.HibernateException
        {
                return (com.ulearning.ulms.content.schoolbook.model.MTeachsort) load(getReferenceClass(),
                        new java.lang.Long(key));
        }

        public com.ulearning.ulms.content.schoolbook.model.MTeachsort load(
                long key, Session s) throws net.sf.hibernate.HibernateException
        {
                return (com.ulearning.ulms.content.schoolbook.model.MTeachsort) load(getReferenceClass(),
                        new java.lang.Long(key), s);
        }

        public com.ulearning.ulms.content.schoolbook.model.MTeachsort loadInitialize(
                long key, Session s) throws net.sf.hibernate.HibernateException
        {
                com.ulearning.ulms.content.schoolbook.model.MTeachsort obj = load(key, s);

                if (!Hibernate.isInitialized(obj))
                {
                        Hibernate.initialize(obj);
                }

                return obj;
        }

        /**
         * Persist the given transient instance, first assigning a generated identifier. (Or using the current value
         * of the identifier property if the assigned generator is used.)
         *
         * @param mTeachsort a transient instance of a persistent class
         * @return the class identifier
         */
        public java.lang.Long save(
                com.ulearning.ulms.content.schoolbook.model.MTeachsort mTeachsort)
                throws net.sf.hibernate.HibernateException
        {
                return (java.lang.Long) super.save(mTeachsort);
        }

        /**
         * Persist the given transient instance, first assigning a generated identifier. (Or using the current value
         * of the identifier property if the assigned generator is used.)
         * Use the Session given.
         *
         * @param mTeachsort a transient instance of a persistent class
         * @param s          the Session
         * @return the class identifier
         */
        public java.lang.Long save(
                com.ulearning.ulms.content.schoolbook.model.MTeachsort mTeachsort,
                Session s) throws net.sf.hibernate.HibernateException
        {
                return (java.lang.Long) super.save(mTeachsort, s);
        }

        /**
         * Either save() or update() the given instance, depending upon the value of its identifier property. By default
         * the instance is always saved. This behaviour may be adjusted by specifying an unsaved-value attribute of the
         * identifier property mapping.
         *
         * @param mTeachsort a transient instance containing new or updated state
         */
        public void saveOrUpdate(
                com.ulearning.ulms.content.schoolbook.model.MTeachsort mTeachsort)
                throws net.sf.hibernate.HibernateException
        {
                super.saveOrUpdate(mTeachsort);
        }

        /**
         * Either save() or update() the given instance, depending upon the value of its identifier property. By default the
         * instance is always saved. This behaviour may be adjusted by specifying an unsaved-value attribute of the identifier
         * property mapping.
         * Use the Session given.
         *
         * @param mTeachsort a transient instance containing new or updated state.
         * @param s          the Session.
         */
        public void saveOrUpdate(
                com.ulearning.ulms.content.schoolbook.model.MTeachsort mTeachsort,
                Session s) throws net.sf.hibernate.HibernateException
        {
                super.saveOrUpdate(mTeachsort, s);
        }

        /**
         * Update the persistent state associated with the given identifier. An exception is thrown if there is a persistent
         * instance with the same identifier in the current session.
         *
         * @param mTeachsort a transient instance containing updated state
         */
        public void update(
                com.ulearning.ulms.content.schoolbook.model.MTeachsort mTeachsort)
                throws net.sf.hibernate.HibernateException
        {
                super.update(mTeachsort);
        }

        /**
         * Update the persistent state associated with the given identifier. An exception is thrown if there is a persistent
         * instance with the same identifier in the current session.
         * Use the Session given.
         *
         * @param mTeachsort a transient instance containing updated state
         * @param the        Session
         */
        public void update(
                com.ulearning.ulms.content.schoolbook.model.MTeachsort mTeachsort,
                Session s) throws net.sf.hibernate.HibernateException
        {
                super.update(mTeachsort, s);
        }

        /**
         * Remove a persistent instance from the datastore. The argument may be an instance associated with the receiving
         * Session or a transient instance with an identifier associated with existing persistent state.
         *
         * @param id the instance ID to be removed
         */
        public void delete(long id) throws net.sf.hibernate.HibernateException
        {
                super.delete(load(id));
        }

        /**
         * Remove a persistent instance from the datastore. The argument may be an instance associated with the receiving
         * Session or a transient instance with an identifier associated with existing persistent state.
         * Use the Session given.
         *
         * @param id the instance ID to be removed
         * @param s  the Session
         */
        public void delete(long id, Session s)
                throws net.sf.hibernate.HibernateException
        {
                super.delete(load(id, s), s);
        }

        /**
         * Remove a persistent instance from the datastore. The argument may be an instance associated with the receiving
         * Session or a transient instance with an identifier associated with existing persistent state.
         *
         * @param mTeachsort the instance to be removed
         */
        public void delete(
                com.ulearning.ulms.content.schoolbook.model.MTeachsort mTeachsort)
                throws net.sf.hibernate.HibernateException
        {
                super.delete(mTeachsort);
        }

        /**
         * Remove a persistent instance from the datastore. The argument may be an instance associated with the receiving
         * Session or a transient instance with an identifier associated with existing persistent state.
         * Use the Session given.
         *
         * @param mTeachsort the instance to be removed
         * @param s          the Session
         */
        public void delete(
                com.ulearning.ulms.content.schoolbook.model.MTeachsort mTeachsort,
                Session s) throws net.sf.hibernate.HibernateException
        {
                super.delete(mTeachsort, s);
        }

        /**
         * Re-read the state of the given instance from the underlying database. It is inadvisable to use this to implement
         * long-running sessions that span many business tasks. This method is, however, useful in certain special circumstances.
         * For example
         * <ul>
         * <li>where a database trigger alters the object state upon insert or update</li>
         * <li>after executing direct SQL (eg. a mass update) in the same session</li>
         * <li>after inserting a Blob or Clob</li>
         * </ul>
         */
        public void refresh(
                com.ulearning.ulms.content.schoolbook.model.MTeachsort mTeachsort,
                Session s) throws net.sf.hibernate.HibernateException
        {
                super.refresh(mTeachsort, s);
        }

        public String getDefaultOrderProperty()
        {
                return null;
        }
}
