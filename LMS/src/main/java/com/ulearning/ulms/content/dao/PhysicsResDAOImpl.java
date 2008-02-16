/**
 * PhysicsResDAOImpl.java.
 * User: liz  Date: 2006-2-17
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.content.dao;

import com.ulearning.ulms.content.form.PhysicsResForm;
import com.ulearning.ulms.content.model.RPhysicsresModel;
import com.ulearning.ulms.core.exceptions.ULMSException;
import com.ulearning.ulms.util.HibernateDAO;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class PhysicsResDAOImpl implements PhysicsResDAO
{
        /**
         * �����Դ
         *
         * @param mod
         * @return 0:���ɹ���1���ɹ�
         * @throws com.ulearning.ulms.core.exceptions.ULMSException
         *
         */
        public int addRes(RPhysicsresModel mod) throws ULMSException
        {
                int result = 0;

                HibernateDAO.add(mod);
                result = 1;

                return result;
        }

        /**
         * ������Դ
         *
         * @param mod
         * @throws com.ulearning.ulms.core.exceptions.ULMSException
         *
         */
        public void updateRes(RPhysicsresModel mod) throws ULMSException
        {
                HibernateDAO.update(mod);
        }

        /**
         * ȡ��Դ����
         *
         * @param hql
         * @return PhysicsResForm����
         * @throws com.ulearning.ulms.core.exceptions.ULMSException
         *
         */
        public List getData(String hql, String startDateTime, String endDateTime,
                            int firstResult, int maxResults) throws ULMSException
        {
                List list = null;
                List resultList = null;

                list = HibernateDAO.find(hql, startDateTime, endDateTime, firstResult,
                        maxResults);

                RPhysicsresModel mod = null;

                if (null != list)
                {
                        resultList = new ArrayList();

                        for (Iterator iter = list.iterator(); iter.hasNext();)
                        {
                                mod = (RPhysicsresModel) iter.next();
                                resultList.add(new PhysicsResForm(mod));
                        }
                }

                return resultList;
        }

        /**
         * ɾ����Դ����
         *
         * @param hql
         * @throws com.ulearning.ulms.core.exceptions.ULMSException
         *
         */
        public void removeRes(String hql) throws ULMSException
        {
                HibernateDAO.delete(hql);
        }
}
