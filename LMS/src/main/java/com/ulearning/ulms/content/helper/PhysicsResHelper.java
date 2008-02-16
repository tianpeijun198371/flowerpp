/**
 * PhysicsResHelper.java.
 * User: liz  Date: 2006-2-17
 * 资源字典辅助类
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.content.helper;

import com.ulearning.ulms.content.dao.PhysicsResDAO;
import com.ulearning.ulms.content.dao.PhysicsResDAOFactory;
import com.ulearning.ulms.content.form.PhysicsResForm;
import com.ulearning.ulms.content.model.RPhysicsresModel;
import com.ulearning.ulms.core.exceptions.ULMSException;
import com.ulearning.ulms.organ.bean.OrganHelper;
import com.ulearning.ulms.organ.form.OrganForm;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class PhysicsResHelper
{
        /**
         * 增加资源数据
         *
         * @param frm
         * @return
         * @throws ULMSException
         */
        public static int insertRes(PhysicsResForm frm) throws ULMSException
        {
                int result = 0;
                PhysicsResDAO dao = PhysicsResDAOFactory.getDAO();
                result = dao.addRes(frm.makeModel());

                return result;
        }

        /**
         * 更新资源字典
         *
         * @param frm
         */
        public static void updateRes(PhysicsResForm frm) throws ULMSException
        {
                RPhysicsresModel mod = frm.makeModel();
                PhysicsResDAO dao = PhysicsResDAOFactory.getDAO();
                dao.updateRes(mod);
        }

        /**
         * 删除资源数据
         *
         * @param resid
         */
        public static void delRes(String resid) throws ULMSException
        {
                StringBuffer hql = new StringBuffer();
                hql.append(" from RPhysicsresModel where resid=");
                hql.append(resid);

                PhysicsResDAO dao = PhysicsResDAOFactory.getDAO();
                dao.removeRes(hql.toString());
        }

        /**
         * 按给定的条件取资源数据
         *
         * @param aspid
         * @param orgid
         * @param id       主键
         * @param resname  　资源名称
         * @param resType  资源类型0：没意义  1：教室  2:会议室
         * @param resstate 资源状态0：可用　　1：不可用
         * @return PhysicsResForm集合
         * @throws ULMSException
         */
        public static List getAllRes(String aspid, String orgid, String id,
                                     String resname, String resType, String resstate)
                throws ULMSException
        {
                StringBuffer hql = new StringBuffer();
                hql.append(" from RPhysicsresModel where 1>0");

                if ((null != aspid) && !aspid.equals(""))
                {
                        hql.append(" and aspid=");
                        hql.append(aspid);
                }

                if ((null != orgid) && !orgid.equals(""))
                {
                        hql.append(" and orgid=");
                        hql.append(orgid);
                }

                if ((null != id) && !id.equals(""))
                {
                        hql.append(" and resid=");
                        hql.append(id);
                }

                if ((null != resname) && !resname.equals(""))
                {
                        hql.append(" and resname like '%");
                        hql.append(resname);
                        hql.append("%'");
                }

                if ((null != resType) && !resType.equals(""))
                {
                        hql.append(" and resType=");
                        hql.append(resType);
                }

                if ((null != resstate) && !resstate.equals(""))
                {
                        hql.append(" and resstate=");
                        hql.append(resstate);
                }

                hql.append(" order by restype");

                List resultList = null;
                PhysicsResDAO dao = PhysicsResDAOFactory.getDAO();
                resultList = dao.getData(hql.toString(), "", "", -1, -1);

                if ((null != resultList) && (1 <= resultList.size()))
                {
                        resultList = makeResTypeName(resultList); //调用
                }

                return resultList;
        }

        /**
         * 建立设施类型名称
         *
         * @param list
         * @return
         */
        private static List makeResTypeName(List list)
        {
                List resultlist = new ArrayList();
                String orgname;

                for (Iterator iter = list.iterator(); iter.hasNext();)
                {
                        PhysicsResForm frm = (PhysicsResForm) iter.next();

                        if (0 == frm.getRestype())
                        {
                                frm.setRestypename("　"); //没有意义，添加一个全角空格
                        }
                        else if (1 == frm.getRestype())
                        {
                                frm.setRestypename("教室");
                        }
                        else if (2 == frm.getRestype())
                        {
                                frm.setRestypename("会议室");
                        }
                        else if (3 == frm.getRestype())
                        {
                                frm.setRestypename("客房");
                        }
                        else if (4 == frm.getRestype())
                        {
                                frm.setRestypename("餐厅");
                        }
                        else if (5 == frm.getRestype())
                        {
                                frm.setRestypename("机房");
                        }

                        orgname = getorgName(frm.getOrgid()); //调用
                        frm.setOrgname(orgname);
                        frm.setResstatename(makeResStateName(Integer.toString(
                                frm.getResstate()))); //调用
                        resultlist.add(frm);
                }

                return resultlist;
        }

        /**
         * 辅助为method，取机构名称
         *
         * @param orgid
         * @return
         */
        private static String getorgName(int orgid)
        {
                String sname = "　";
                OrganForm frm = OrganHelper.getOrgan(orgid);
                sname = frm.getOrgName();

                if ((null == sname) || sname.equals(""))
                {
                        sname = "机构已删除";
                }

                return sname;
        }

        /**
         * 辅助method，建立设施状态名称
         *
         * @param stateId
         * @return
         */
        private static String makeResStateName(String stateId)
        {
                String stateName = "　";

                if (stateId.equals("0"))
                {
                        stateName = "可用";
                }
                else if (stateId.equals("1"))
                {
                        stateName = "不可用";
                }

                return stateName;
        }

        /**
         * @param aspid
         * @param orgid
         * @param resType 资源类型0：没意义  1：教室  2:会议室
         * @param used    资源状态0：可用　　1：不可用
         * @return
         */
        public static List getResUserdClass(String aspid, String orgid,
                                            String resType, String used)
        {
                List resultList = null;
                resultList = getAllRes(aspid, orgid, null, null, resType, used);

                return resultList;
        }

        /**
         * 按资源ID查找
         *
         * @param resId
         * @return
         */
        public static List getResById(String resId)
        {
                List resultList = null;
                resultList = getAllRes(null, null, resId, null, null, null);

                return resultList;
        }

        /**
         * 按资源名称查找
         *
         * @param resname
         * @return
         */
        public static List getResByName(String resname)
        {
                List resultList = null;
                resultList = getAllRes(null, null, null, resname, null, null);

                return resultList;
        }

        /**
         * 根据资源状态取所有资源
         *
         * @param resstate
         * @return
         */
        public static List getResAllUsed(String resstate, String resType)
        {
                List resultList = null;
                resultList = getAllRes(null, null, null, null, resType, resstate);

                return resultList;
        }
}
