/**
 * MTeachmailbillitemDAOImpl.java.
 * User: liz  Date: 2006-5-22
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.content.schoolbook.model.dao;

import com.ulearning.ulms.content.schoolbook.model.MTeachmailbillitemTab;
import com.ulearning.ulms.core.exceptions.ULMSException;
import com.ulearning.ulms.tools.report.general.dao.ReportDAO;
import com.ulearning.ulms.util.HibernateDAO;

import java.util.ArrayList;
import java.util.List;


public class MTeachmailbillitemDAOImpl implements ReportDAO
{
        //≤È—Ø
        public List getList(String hql) throws ULMSException
        {
                List list = null;
                List frmList = new ArrayList();
                list = HibernateDAO.find(hql.toString());

                if (null != list)
                {
                        MTeachmailbillitemTab mod = null;

                        for (int i = 0; i < list.size(); i++)
                        {
                                mod = (MTeachmailbillitemTab) list.get(i);
                                frmList.add(mod.getFrm());
                        }
                }

                return frmList;
        }

        public void addData(Object obj) throws ULMSException
        {
                HibernateDAO.add((MTeachmailbillitemTab) obj);
        }

        public void updateData(Object obj) throws ULMSException
        {
                HibernateDAO.update((MTeachmailbillitemTab) obj);
        }

        public void removeData(String hql) throws ULMSException
        {
                HibernateDAO.delete(hql);
        }
}
