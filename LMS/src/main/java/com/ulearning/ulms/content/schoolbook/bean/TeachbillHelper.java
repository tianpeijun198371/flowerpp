/**
 * TeachbillHelper.java.
 * User: liz  Date: 2006-5-22
 * 教材征订单辅助类
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.content.schoolbook.bean;

import com.ulearning.ulms.content.schoolbook.form.MTeachmailbillitemForm;
import com.ulearning.ulms.content.schoolbook.form.MTeachmailbillmainform;
import com.ulearning.ulms.content.schoolbook.model.dao.MTeachmailbillmainDAOImpl;
import com.ulearning.ulms.tools.report.general.dao.ReportDAO;
import com.ulearning.ulms.tools.report.general.dao.ReportDAOFactory;

import java.util.List;


public class TeachbillHelper
{
        private String mainclassname = "com.ulearning.ulms.content.schoolbook.model.dao.MTeachmailbillmainDAOImpl";
        private String itemnclassname = "com.ulearning.ulms.content.schoolbook.model.dao.MTeachmailbillitemDAOImpl";

        /**
         * 取征订单主表数据 ,日期查询暂时不实现，有时间再进行处理
         *
         * @param id        主鍵
         * @param depts     征订单位
         * @param operator  征订经办人
         * @param startdate 查询开始添单日期
         * @param ednData   查询结束添单日期
         * @return
         */
        public List getMainData(String id, String depts, String operator,
                                String startdate, String ednData)
        {
                List rlist = null;
                StringBuffer hql = new StringBuffer();
                hql.append(" from MTeachmailbillmainTab where 1>0");

                if ((null != id) && !id.equals(""))
                {
                        hql.append(" and tcmlid=");
                        hql.append(id);
                }

                if ((null != depts) && !depts.equals(""))
                {
                        hql.append(" and tcmllevydept like '%");
                        hql.append(depts);
                        hql.append("%'");
                }

                if ((null != operator) && !operator.equals(""))
                {
                        hql.append(" and tcmllevyoperator like '%");
                        hql.append(operator);
                        hql.append("%'");
                } /*
           if(null!=startdate && !startdate.equals("")){
                   hql.append(" and tcmllevyoperator =%");
                   hql.append(startdate);
                   hql.append("%'");
           }  */
                ReportDAO dao = ReportDAOFactory.getDAO(mainclassname);
                rlist = dao.getList(hql.toString());

                return rlist;
        }

        /**
         * 根据主表ID取教材征订明细数据
         *
         * @param mainid
         * @return
         */
        public List getItemData(String mainid)
        {
                List rlist = null;

                if ((null != mainid) && !mainid.equals("") && !mainid.equals("0"))
                {
                        StringBuffer hql = new StringBuffer();
                        hql.append(" from MTeachmailbillitemTab where tcmlid=");
                        hql.append(mainid);

                        ReportDAO dao = ReportDAOFactory.getDAO(itemnclassname);
                        rlist = dao.getList(hql.toString());
                }

                return rlist;
        }

        /**
         * 取教材明细
         *
         * @param id
         * @return
         */
        public List getItemDataById(String id)
        {
                List rlist = null;

                if ((null != id) && !id.equals("") && !id.equals("0"))
                {
                        StringBuffer hql = new StringBuffer();
                        hql.append(" from MTeachmailbillitemTab where titemid=");
                        hql.append(id);

                        ReportDAO dao = ReportDAOFactory.getDAO(itemnclassname);
                        rlist = dao.getList(hql.toString());
                }

                return rlist;
        }

        /**
         * 添加主表数据
         *
         * @param frm
         * @return
         */
        public int instNewMainData(MTeachmailbillmainform frm)
        {
                int i = 0;
                MTeachmailbillmainDAOImpl dao = new MTeachmailbillmainDAOImpl();
                i = dao.instData(frm.getMod());

                return i;
        }

        /**
         * 添加子表数据
         *
         * @param frm
         */
        public void instNewItemData(MTeachmailbillitemForm frm)
        {
                ReportDAO dao = ReportDAOFactory.getDAO(itemnclassname);
                dao.addData(frm.getMod());
        }

        /**
         * 删除主表数据，同时删除子表数据
         *
         * @param mainid
         */
        public void delMainAndItemData(String mainid)
        {
                int i = 0;
                StringBuffer hql = new StringBuffer();
                hql.append(" from MTeachmailbillmainTab where tcmlid=");
                hql.append(mainid);

                MTeachmailbillmainDAOImpl dao = new MTeachmailbillmainDAOImpl();
                i = dao.delData(hql.toString());

                if (1 == i)
                {
                        hql = new StringBuffer();
                        hql.append(" from MTeachmailbillitemTab where tcmlid=");
                        hql.append(mainid);

                        ReportDAO daoi = ReportDAOFactory.getDAO(itemnclassname);
                        daoi.removeData(hql.toString());
                }
        }

        /**
         * 删除子表数据
         *
         * @param itemid
         */
        public void delItemData(String itemid)
        {
                StringBuffer hql = new StringBuffer();
                hql.append(" from MTeachmailbillitemTab where titemid=");
                hql.append(itemid);

                ReportDAO daoi = ReportDAOFactory.getDAO(itemnclassname);
                daoi.removeData(hql.toString());
        }

        /**
         * 修改主表数据
         */
        public void updateMainData(MTeachmailbillmainform frm)
        {
                ReportDAO dao = ReportDAOFactory.getDAO(mainclassname);
                dao.updateData(frm.getMod());
        }

        /**
         * 修改子表数据
         */
        public void updateItemData(MTeachmailbillitemForm frm)
        {
                ReportDAO dao = ReportDAOFactory.getDAO(itemnclassname);
                dao.updateData(frm.getMod());
        }
}
