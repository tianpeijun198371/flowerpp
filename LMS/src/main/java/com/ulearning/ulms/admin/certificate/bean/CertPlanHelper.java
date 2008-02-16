/**
 * CertHelper.java.
 * User: huangsb  Date: 2004-4-26
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd. * All rights reserved.
 */
package com.ulearning.ulms.admin.certificate.bean;

import com.ulearning.ulms.admin.certificate.dao.CertPlanDAO;
import com.ulearning.ulms.admin.certificate.dao.CertPlanDAOFactory;
import com.ulearning.ulms.admin.certificate.form.CertPlanForm;
import com.ulearning.ulms.core.exceptions.ULMSException;

import java.util.List;


public class CertPlanHelper
{
        /**
         * 按给定的条件取课程安排数据
         *
         * @return PhysicsResForm集合
         * @throws com.ulearning.ulms.core.exceptions.ULMSException
         *
         */
        public static List getAllCertPlanBy(String cpacotid)
                throws ULMSException
        {
                StringBuffer hql = new StringBuffer();
                hql.append("from CertPlanModel where 1>0 ");

                if ((null != cpacotid) && !cpacotid.equals(""))
                {
                        hql.append(" and cpacotid=");
                        hql.append(cpacotid);
                }

                hql.append(" order by cpacotid desc");

                List resultList = null;
                CertPlanDAO dao = CertPlanDAOFactory.getDAO();
                resultList = dao.getData(hql.toString());

                return resultList;
        }

        public static List getAllCertPlanByid(String cpacotid)
                throws ULMSException
        {
                List resultList = null;
                resultList = getAllCertPlanBy(cpacotid);

                return resultList;
        }

        public static List getAllCertPlan() throws ULMSException
        {
                List resultList = null;
                resultList = getAllCertPlanBy(null);

                return resultList;
        }

        public static int insertCertPlan(CertPlanForm frm)
                throws ULMSException
        {
                int result = 0;
                CertPlanDAO dao = CertPlanDAOFactory.getDAO();
                result = dao.addCertPlan(frm.getModel());

                return result;
        }

        public static void updateCertPlan(CertPlanForm frm)
                throws ULMSException
        {
                CertPlanDAO dao = CertPlanDAOFactory.getDAO();
                dao.updateCertPlan(frm.getModel());
        }

        public static void removeCertPlan(String cpacotid)
                throws ULMSException
        {
                StringBuffer hql = new StringBuffer();
                hql.append(" from CertPlanModel where cpacotid=");
                hql.append(cpacotid);

                CertPlanDAO dao = CertPlanDAOFactory.getDAO();
                dao.removeCertPlan(hql.toString());
        }
}
