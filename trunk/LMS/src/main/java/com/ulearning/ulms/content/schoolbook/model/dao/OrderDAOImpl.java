/**
 * RCertreportDAOImpl.java.
 * User: liz  Date: 2006-4-30
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.content.schoolbook.model.dao;

import com.ulearning.ulms.content.schoolbook.form.OrderForm;
import com.ulearning.ulms.content.schoolbook.model.MTeachbeseinfoTab;
import com.ulearning.ulms.content.schoolbook.model.MTeachmailbillTab;
import com.ulearning.ulms.core.exceptions.ULMSException;
import com.ulearning.ulms.tools.report.general.dao.ReportDAO;
import com.ulearning.ulms.util.HibernateDAO;

import java.util.ArrayList;
import java.util.List;


public class OrderDAOImpl implements ReportDAO
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

                Object[] obj = null;

                if (null != list)
                {
                        for (int i = 0; i < list.size(); i++)
                        {
                                obj = (Object[]) list.get(i);

                                OrderForm frm = null;
                                frm = makeOrderinfo(obj);
                                makeBookinfo(obj, frm);

                                frmList.add(frm);
                        }
                }

                return frmList;
        }

        /**
         * 建立教材基本信息，出版社和作者
         *
         * @param obj
         * @param frm
         */
        private void makeBookinfo(Object[] obj, OrderForm frm)
        {
                MTeachbeseinfoTab mod = null;
                mod = (MTeachbeseinfoTab) obj[1];
                frm.setAuthor(mod.getBsifauthor());
                frm.setCompay(mod.getBsifpublishname());
        }

        /**
         * 建立征订单信息
         *
         * @param obj
         * @return
         */
        private OrderForm makeOrderinfo(Object[] obj)
        {
                MTeachmailbillTab mod = null;
                mod = (MTeachmailbillTab) obj[0];

                OrderForm frm = null;
                frm = mod.getFrm();

                return frm;
        }

        public void addData(Object obj) throws ULMSException
        {
                HibernateDAO.add((MTeachmailbillTab) obj);
        }

        public void updateData(Object obj) throws ULMSException
        {
                HibernateDAO.update((MTeachmailbillTab) obj);
        }

        public void removeData(String hql) throws ULMSException
        {
                HibernateDAO.delete(hql);
        }
}
