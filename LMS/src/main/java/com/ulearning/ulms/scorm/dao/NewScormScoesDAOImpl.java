/**
 * Copyright (c)2006 Beijing WenhuaOnline Sci-tech DevelopmentCo.,Ltd.
 * All rights reserved.
 *
 * User: fengch
 * Date: 2007-9-21 13:58:47
 */
package com.ulearning.ulms.scorm.dao;

import com.ulearning.ulms.content.exceptions.ContentManageSysException;
import com.ulearning.ulms.core.exceptions.ULMSSysException;
import com.ulearning.ulms.scorm.exceptions.ScormSysException;
import com.ulearning.ulms.scorm.model.NewScormScoes;
import com.ulearning.ulms.scorm.util.ScormConstants;
import com.ulearning.ulms.util.HibernateDAO;
import com.ulearning.ulms.util.HibernateUtil;

import net.sf.hibernate.Query;
import net.sf.hibernate.Session;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.ArrayList;
import java.util.List;


public class NewScormScoesDAOImpl implements NewScormScoesDAO
{
        protected static Log logger = LogFactory.getLog(NewScormScoesDAOImpl.class);

        /**
         * ����
         *
         * @param id
         * @throws ScormSysException
         */
        public NewScormScoes get(Integer id) throws ScormSysException
        {
                try
                {
                        return (NewScormScoes) HibernateDAO.load(NewScormScoes.class, id);
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new ScormSysException(e);
                }
        }

        /**
         * ����
         *
         * @param identifier
         * @throws ScormSysException
         */
        public NewScormScoes getByIdentifier(Integer scormId, String identifier)
                throws ScormSysException
        {
                NewScormScoes sco = null;

                try
                {
                        Session session = HibernateUtil.getSession();
                        session.clear();

                        String hqlStr = "from NewScormScoes where scormId=" + scormId +
                                " and identifier='" + identifier + "'";
                        logger.info("hsql: " + hqlStr);

                        Query query = session.createQuery(hqlStr);
                        sco = (NewScormScoes) query.uniqueResult();
                }
                catch (Exception e)
                {
                        e.printStackTrace();
                        throw new ScormSysException(e);
                }

                return sco;
        }

        /**
         * ����Scorm�����нڵ�
         *
         * @param @return
         * @throws com.ulearning.ulms.content.exceptions.ContentManageSysException
         *
         */
        public List getByScorm(Integer scormId, String scoType)
                throws ScormSysException
        {
                ArrayList contents = new ArrayList();

                String hql = "from NewScormScoes where scormId=" + scormId +
                        " and scoType'" + scoType + "' order by sequence asc";

                logger.info("hql = " + hql);

                try
                {
                        contents = (ArrayList) HibernateDAO.find(hql);
                }
                catch (ContentManageSysException e)
                {
                        throw new ContentManageSysException(e);
                }

                return contents;
        }

        /**
         * ����Scorm�����һ��Sco�ڵ�
         *
         * @param @return
         * @throws com.ulearning.ulms.content.exceptions.ContentManageSysException
         *
         */
        public NewScormScoes getLastScoByScorm(Integer scormId)
                throws ScormSysException
        {
                NewScormScoes sco = null;

                String hql = "from NewScormScoes where scormId=" + scormId +
                        " and (scoType='au' or scoType='sco') order by sequence desc";

                logger.info("hql = " + hql);

                try
                {
                        Session session = HibernateUtil.getSession();
                        Query query = session.createQuery(hql);
                        query.setFetchSize(1);
                        query.setFirstResult(0);
                        sco = (NewScormScoes) query.uniqueResult();
                }
                catch (Exception e)
                {
                        throw new ContentManageSysException(e);
                }

                return sco;
        }

        /**
         * ����Scorm�ĵ�һ��Sco�ڵ�
         *
         * @param @return
         * @throws com.ulearning.ulms.content.exceptions.ContentManageSysException
         *
         */
        public NewScormScoes getFirstScoByScorm(Integer scormId)
                throws ScormSysException
        {
                NewScormScoes sco = null;

                String hql = "from NewScormScoes where scormId=" + scormId +
                        " and (scoType='au' or scoType='sco') order by sequence asc";

                logger.info("hql = " + hql);

                try
                {
                        Session session = HibernateUtil.getSession();
                        Query query = session.createQuery(hql);
                        query.setFetchSize(1);
                        query.setFirstResult(0);
                        sco = (NewScormScoes) query.uniqueResult();
                }
                catch (Exception e)
                {
                        throw new ContentManageSysException(e);
                }

                return sco;
        }

        /**
         * ����Scorm������Sco�ڵ�
         *
         * @param @return
         * @throws com.ulearning.ulms.content.exceptions.ContentManageSysException
         *
         */
        public List getScoByScorm(Integer scormId) throws ScormSysException
        {
                ArrayList contents = new ArrayList();

                String hql = "from NewScormScoes where scormId=" + scormId +
                        " and (scoType='au' or scoType='sco') order by sequence asc";

                logger.info("hql = " + hql);

                try
                {
                        contents = (ArrayList) HibernateDAO.find(hql);
                }
                catch (ContentManageSysException e)
                {
                        throw new ContentManageSysException(e);
                }

                return contents;
        }

        /**
         * ����Scorm�����нڵ�
         *
         * @param @return
         * @throws ContentManageSysException
         */
        public List getByScorm(Integer scormId) throws ScormSysException
        {
                ArrayList contents = new ArrayList();

                String hql = "from NewScormScoes where scormId=" + scormId +
                        " order by sequence asc";

                logger.info("hql = " + hql);

                try
                {
                        contents = (ArrayList) HibernateDAO.find(hql);
                }
                catch (ContentManageSysException e)
                {
                        throw new ContentManageSysException(e);
                }

                return contents;
        }

        /**
         * ����ĳ�ڵ���������ݡ���������Ŀ¼
         *
         * @param @return
         * @throws ContentManageSysException
         */
        public List getSubContent(Integer scormId, String identifier)
                throws ScormSysException
        {
                ArrayList contents = new ArrayList();

                String hql = "from NewScormScoes where scormId=" + scormId +
                        " and  parent='" + identifier + "' order by sequence asc";

                logger.info("hql = " + hql);

                try
                {
                        contents = (ArrayList) HibernateDAO.find(hql);
                }
                catch (ContentManageSysException e)
                {
                        throw new ContentManageSysException(e);
                }

                return contents;
        }

        /**
         * ����
         *
         * @param newScormScoes
         * @throws com.ulearning.ulms.scorm.exceptions.ScormSysException
         *
         */
        public void insert(NewScormScoes newScormScoes) throws ScormSysException
        {
                try
                {
                        HibernateDAO.add(newScormScoes);
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new ScormSysException(e);
                }
        }

        /**
         * ����
         *
         * @param newScormScoes
         * @throws ScormSysException
         */
        public void update(NewScormScoes newScormScoes) throws ScormSysException
        {
                try
                {
                        HibernateDAO.update(newScormScoes);
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new ScormSysException(e);
                }
        }

        /**
         * ɾ��
         *
         * @param newScormScoes
         * @throws ScormSysException
         */
        public void delete(NewScormScoes newScormScoes) throws ScormSysException
        {
                try
                {
                        HibernateDAO.delete(newScormScoes);
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new ScormSysException(e);
                }
        }
}
