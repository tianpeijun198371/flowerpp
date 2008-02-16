/**
 * CertDAOImpl.java.
 * User: huangsb  Date: 2004-4-26
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd. * All rights reserved.
 */
package com.ulearning.ulms.admin.certificate.dao;

import com.ulearning.ulms.admin.certificate.form.CertPlanForm;
import com.ulearning.ulms.admin.certificate.model.CertPlanModel;
import com.ulearning.ulms.core.exceptions.ULMSException;
import com.ulearning.ulms.util.HibernateDAO;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class CertPlanDAOImpl implements CertPlanDAO
{
        /**
         * �����ѵ�ల��
         *
         * @param mod
         * @return 0:���ɹ���1���ɹ�
         * @throws com.ulearning.ulms.core.exceptions.ULMSException
         *
         */
        public int addCertPlan(CertPlanModel mod) throws ULMSException
        {
                int result = 0;

                HibernateDAO.add(mod);
                result = 1;

                return result;
        }

        /**
         * ������ѵ�ల��
         *
         * @param mod
         * @throws com.ulearning.ulms.core.exceptions.ULMSException
         *
         */
        public void updateCertPlan(CertPlanModel mod) throws ULMSException
        {
                HibernateDAO.update(mod);
        }

        /**
         * ȡ��ѵ�ల������
         *
         * @param hql
         * @return PhysicsResForm����
         * @throws com.ulearning.ulms.core.exceptions.ULMSException
         *
         */
        public List getData(String hql) throws ULMSException
        {
                List list = null;
                List resultList = null;

                list = HibernateDAO.find(hql);

                CertPlanModel mod = null;

                if (null != list)
                {
                        resultList = new ArrayList();

                        for (Iterator iter = list.iterator(); iter.hasNext();)
                        {
                                mod = (CertPlanModel) iter.next();
                                resultList.add(new CertPlanForm(mod));
                        }
                }

                return resultList;
        }

        /**
         * ɾ����ѵ�ల������
         *
         * @param hql
         * @throws com.ulearning.ulms.core.exceptions.ULMSException
         *
         */
        public void removeCertPlan(String hql) throws ULMSException
        {
                HibernateDAO.delete(hql);
        }
}
