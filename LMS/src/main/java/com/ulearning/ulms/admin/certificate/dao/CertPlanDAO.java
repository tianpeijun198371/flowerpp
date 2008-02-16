package com.ulearning.ulms.admin.certificate.dao;

import com.ulearning.ulms.admin.certificate.model.CertPlanModel;
import com.ulearning.ulms.core.exceptions.ULMSException;

import java.util.List;


public interface CertPlanDAO
{
        /**
         * 添加培训班安排
         *
         * @param mod
         * @return
         * @throws ULMSException
         */
        public int addCertPlan(CertPlanModel mod) throws ULMSException;

        /**
         * 更新培训班安排
         *
         * @param mod
         * @throws ULMSException
         */
        public void updateCertPlan(CertPlanModel mod) throws ULMSException;

        /**
         * 取培训班安排数据
         *
         * @param hql
         * @return
         * @throws ULMSException
         */
        public List getData(String hql) throws ULMSException;

        /**
         * 删除培训班安排数据
         *
         * @param hql
         * @throws ULMSException
         */
        public void removeCertPlan(String hql) throws ULMSException;
}
