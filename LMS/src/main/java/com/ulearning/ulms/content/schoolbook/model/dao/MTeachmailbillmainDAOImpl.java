/**
 * MTeachmailbillmainDAOImpl.java.
 * User: liz  Date: 2006-5-22
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.content.schoolbook.model.dao;

import com.ulearning.ulms.content.schoolbook.model.MTeachmailbillmainTab;
import com.ulearning.ulms.core.exceptions.ULMSException;
import com.ulearning.ulms.tools.report.general.dao.ReportDAO;
import com.ulearning.ulms.util.HibernateDAO;

import java.util.ArrayList;
import java.util.List;


public class MTeachmailbillmainDAOImpl implements ReportDAO
{
        //��ѯ
        public List getList(String hql) throws ULMSException
        {
                List list = null;
                List frmList = new ArrayList();
                list = HibernateDAO.find(hql.toString());

                if (null != list)
                {
                        MTeachmailbillmainTab mod = null;

                        for (int i = 0; i < list.size(); i++)
                        {
                                mod = (MTeachmailbillmainTab) list.get(i);
                                frmList.add(mod.getFrm());
                        }
                }

                return frmList;
        }

        public void addData(Object obj) throws ULMSException
        {
        }

        /**
         * �����������������ز��������I
         *
         * @param obj
         * @return
         */
        public int instData(Object obj)
        {
                int i = 0;
                i = Integer.parseInt(HibernateDAO.add((MTeachmailbillmainTab) obj)
                        .toString());

                return i;
        }

        public void updateData(Object obj) throws ULMSException
        {
                HibernateDAO.update((MTeachmailbillmainTab) obj);
        }

        /**
         * @param hql
         * @throws ULMSException
         */
        public void removeData(String hql) throws ULMSException
        {
        }

        /**
         * ɾ���������������ݣ��ɹ����أ�1�����򷵻أ�0
         *
         * @param hql
         * @return
         * @throws ULMSException
         */
        public int delData(String hql) throws ULMSException
        {
                int i = 0;

                try
                {
                        HibernateDAO.delete(hql);
                        i = 1;
                }
                catch (ULMSException e)
                {
                        e.printStackTrace();
                }

                return i;
        }
}
