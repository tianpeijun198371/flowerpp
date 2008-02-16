/**
 * RChargeHelper.java.
 * User: liz  Date: 2006-4-29
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.content.schoolbook.bean;

import com.ulearning.ulms.content.schoolbook.form.OrderForm;
import com.ulearning.ulms.core.exceptions.ULMSException;
import com.ulearning.ulms.tools.report.general.dao.ReportDAO;
import com.ulearning.ulms.tools.report.general.dao.ReportDAOFactory;

import java.util.List;


public class OrderHelper
{
        private String classname = "com.ulearning.ulms.content.schoolbook.model.dao.OrderDAOImpl";

        /**
         * /**
         * 明细查询
         *
         * @param bsifbookname 主键
         * @param tcmlman      用户姓名
         * @param tcmldept     工作单位
         * @return
         * @throws com.ulearning.ulms.core.exceptions.ULMSException
         *
         */
        public List getChargeList(String id, String bsifbookname, String tcmlman,
                                  String tcmldept) throws ULMSException
        {
                List list = null;
                StringBuffer hql = new StringBuffer();
                hql.append(
                        " from MTeachmailbillTab a,MTeachbeseinfoTab b where 1>0 and a.bsifid=b.id");

                if ((null != id) && !id.equals(""))
                {
                        hql.append(" and a.tcmlid=");
                        hql.append(id);
                }

                if ((bsifbookname != null) && !bsifbookname.equals(""))
                {
                        hql.append(" and a.bsifbookname like '%");
                        hql.append(bsifbookname);
                        hql.append("%'");
                }

                if ((null != tcmlman) && !tcmlman.equals(""))
                {
                        hql.append(" and a.tcmlman like '%");
                        hql.append(tcmlman);
                        hql.append("%'");
                }

                if ((null != tcmldept) && !tcmldept.equals(""))
                {
                        hql.append(" and a.tcmldept like '%");
                        hql.append(tcmldept);
                        hql.append("%'");
                }

                ReportDAO dao = ReportDAOFactory.getDAO(classname);
                list = dao.getList(hql.toString());

                return list;
        }

        /**
         * 根据ID删除数据
         *
         * @param id
         * @throws ULMSException
         */
        public void delData(int id) throws ULMSException
        {
                StringBuffer hql = new StringBuffer();
                hql.append(" from MTeachmailbillTab where tcmlid=");
                hql.append(id);

                ReportDAO dao = ReportDAOFactory.getDAO(classname);
                dao.removeData(hql.toString());
        }

        /**
         * 新增数据
         *
         * @param frm
         */
        public void insterData(OrderForm frm)
        {
                ReportDAO dao = ReportDAOFactory.getDAO(classname);

                dao.addData((frm.getMod()));
        }

        /**
         * 修改数据
         *
         * @param frm
         */
        public void updateData(OrderForm frm)
        {
                ReportDAO dao = ReportDAOFactory.getDAO(classname);
                dao.updateData((frm.getMod()));
        }
}
