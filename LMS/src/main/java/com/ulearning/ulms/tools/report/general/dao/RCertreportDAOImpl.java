/**
 * RCertreportDAOImpl.java.
 * User: liz  Date: 2006-4-30
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.tools.report.general.dao;

import com.ulearning.ulms.core.exceptions.ULMSException;
import com.ulearning.ulms.tools.report.general.model.RCertreportModel;
import com.ulearning.ulms.util.HibernateDAO;

import java.util.List;
import java.util.ArrayList;

public class RCertreportDAOImpl implements ReportDAO
{
        /**
         * Ã÷Ï¸²éÑ¯
         *
         * @param hql ²éÑ¯SQL
         * @return
         * @throws ULMSException
         */

        public List getList(String hql) throws ULMSException
        {
                List list = null;
                List frmList = new ArrayList();
                list = HibernateDAO.find(hql.toString());
                if (null != list)
                {
                        RCertreportModel mod = null;
                        for (int i = 0; i < list.size(); i++)
                        {
                                mod = (RCertreportModel) list.get(i);
                                frmList.add(mod.getFrm());
                        }

                }

                return frmList;
        }

        public void addData(Object obj) throws ULMSException
        {
                HibernateDAO.add((RCertreportModel) obj);
        }

        public void updateData(Object obj) throws ULMSException
        {
                HibernateDAO.update((RCertreportModel) obj);
        }

        public void removeData(String hql) throws ULMSException
        {

                HibernateDAO.delete(hql);
        }
}
