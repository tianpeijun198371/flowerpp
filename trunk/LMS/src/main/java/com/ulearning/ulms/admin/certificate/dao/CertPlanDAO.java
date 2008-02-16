package com.ulearning.ulms.admin.certificate.dao;

import com.ulearning.ulms.admin.certificate.model.CertPlanModel;
import com.ulearning.ulms.core.exceptions.ULMSException;

import java.util.List;


public interface CertPlanDAO
{
        /**
         * �����ѵ�ల��
         *
         * @param mod
         * @return
         * @throws ULMSException
         */
        public int addCertPlan(CertPlanModel mod) throws ULMSException;

        /**
         * ������ѵ�ల��
         *
         * @param mod
         * @throws ULMSException
         */
        public void updateCertPlan(CertPlanModel mod) throws ULMSException;

        /**
         * ȡ��ѵ�ల������
         *
         * @param hql
         * @return
         * @throws ULMSException
         */
        public List getData(String hql) throws ULMSException;

        /**
         * ɾ����ѵ�ల������
         *
         * @param hql
         * @throws ULMSException
         */
        public void removeCertPlan(String hql) throws ULMSException;
}
