/**
 * Copyright (c)2006 Beijing WenhuaOnline Sci-tech DevelopmentCo.,Ltd.
 * All rights reserved.
 *
 * User: fengch
 * Date: 2007-9-21 13:55:41
 */
package com.ulearning.ulms.scorm.dao;

import com.ulearning.ulms.common.exceptions.HistoryProfileSysException;
import com.ulearning.ulms.core.exceptions.ULMSSysException;
import com.ulearning.ulms.scorm.exceptions.ScormSysException;
import com.ulearning.ulms.scorm.model.NewScorm;
import com.ulearning.ulms.scorm.model.NewScormScoes;
import com.ulearning.ulms.scorm.util.ScormConstants;
import com.ulearning.ulms.util.HibernateDAO;
import com.ulearning.ulms.util.HibernateUtil;

import net.sf.hibernate.Query;
import net.sf.hibernate.Session;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


public class NewScormDAOImpl implements NewScormDAO
{
        protected static Log logger = LogFactory.getLog(NewScormDAOImpl.class);

        /**
         * 查找
         *
         * @param id
         * @throws ScormSysException
         */
        public NewScorm get(Integer id) throws ScormSysException
        {
                try
                {
                        return (NewScorm) HibernateDAO.load(NewScorm.class, id);
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new ScormSysException(e);
                }
        }

        /**
         * 根据相关ID，返回对应Scorm
         *
         * @throws ScormSysException
         */
        public NewScorm getByRelationID(Integer relationID, String type)
                throws ScormSysException
        {
                NewScorm scorm = null;

                try
                {
                        Session session = HibernateUtil.getSession();
                        session.clear();

                        String hqlStr = "from NewScorm where  relationID=" + relationID +
                                " and type='" + type + "'";
                        logger.info("hsql: " + hqlStr);

                        Query query = session.createQuery(hqlStr);
                        scorm = (NewScorm) query.uniqueResult();
                }
                catch (Exception e)
                {
                        e.printStackTrace();
                        throw new ScormSysException(e);
                }

                return scorm;
        }

        /**
         * 插入
         *
         * @param newScorm
         * @throws com.ulearning.ulms.scorm.exceptions.ScormSysException
         *
         */
        public void insert(NewScorm newScorm) throws ScormSysException
        {
                try
                {
                        HibernateDAO.add(newScorm);
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new ScormSysException(e);
                }
        }

        /**
         * 更新
         *
         * @param newScorm
         * @throws ScormSysException
         */
        public void update(NewScorm newScorm) throws ScormSysException
        {
                try
                {
                        HibernateDAO.update(newScorm);
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new ScormSysException(e);
                }
        }

        /**
         * 删除
         *
         * @param newScorm
         * @throws ScormSysException
         */
        public void delete(NewScorm newScorm) throws ScormSysException
        {
                try
                {
                        HibernateDAO.delete(newScorm);
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new ScormSysException(e);
                }
        }
}
