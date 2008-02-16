/**
 * RCertHelper.java.
 * User: liz  Date: 2006-4-30
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.tools.report.general.bean;

import com.ulearning.ulms.core.exceptions.ULMSException;
import com.ulearning.ulms.tools.report.general.dao.ReportDAO;
import com.ulearning.ulms.tools.report.general.dao.ReportDAOFactory;
import com.ulearning.ulms.tools.report.general.model.RCertreportForm;

import java.util.List;

public class RCertHelper
{
        private String classname = "com.ulearning.ulms.tools.report.general.dao.RCertreportDAOImpl";

        /**
         * /**
         * 明细查询
         *
         * @param id   主键
         * @param name 班级名称
         * @param work 工作单位
         * @return
         * @throws com.ulearning.ulms.core.exceptions.ULMSException
         *
         */

        public List getChargeList(int id, String name, String work) throws ULMSException
        {
                List list = null;
                StringBuffer hql = new StringBuffer();
                hql.append(" from RCertreportModel where 1>0");
                if (id != 0)
                {
                        hql.append(" and certrid=");
                        hql.append(id);
                }
                if (null != name && !name.equals(""))
                {
                        hql.append(" and remark1 like '%");
                        hql.append(name);
                        hql.append("%'");
                }
                if (null != work && !work.equals(""))
                {
                        hql.append(" and certrwork like '%");
                        hql.append(work);
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
                hql.append(" from RCertreportModel where certrid=");
                hql.append(id);

                ReportDAO dao = ReportDAOFactory.getDAO(classname);
                dao.removeData(hql.toString());
        }

        /**
         * 新增数据
         *
         * @param frm
         */
        public void insterData(RCertreportForm frm)
        {
                ReportDAO dao = ReportDAOFactory.getDAO(classname);

                dao.addData((frm.getMod()));


        }


        /**
         * 修改数据
         *
         * @param frm
         */
        public void updateData(RCertreportForm frm)
        {
                ReportDAO dao = ReportDAOFactory.getDAO(classname);
                dao.updateData((frm.getMod()));
        }
}
