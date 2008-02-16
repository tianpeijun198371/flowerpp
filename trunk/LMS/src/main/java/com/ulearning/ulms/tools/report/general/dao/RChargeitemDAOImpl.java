/**
 * RChargeitemDAOImpl.java.
 * User: liz  Date: 2006-4-29
 * 收费明细报表DAO实现
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.tools.report.general.dao;

import com.ulearning.ulms.core.exceptions.ULMSException;
import com.ulearning.ulms.tools.report.general.model.RChargeitemModel;
import com.ulearning.ulms.util.HibernateDAO;

import java.util.ArrayList;
import java.util.List;

public class RChargeitemDAOImpl implements ReportDAO
{
        /**
         * 明细查询
         *
         * @param hql 查询SQL
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
                        RChargeitemModel mod = null;
                        for (int i = 0; i < list.size(); i++)
                        {
                                mod = (RChargeitemModel) list.get(i);
                                frmList.add(mod.getFrm(mod));
                        }

                }

                return frmList;
        }

        public void addData(Object obj) throws ULMSException
        {
                HibernateDAO.add((RChargeitemModel) obj);
        }

        public void updateData(Object obj) throws ULMSException
        {
                HibernateDAO.update((RChargeitemModel) obj);
        }

        public void removeData(String hql) throws ULMSException
        {

                HibernateDAO.delete(hql);
        }
}
