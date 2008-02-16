/**
 * OrganAccountHelper.java.
 * User: liz  Date: 2005-12-12
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.finance.helper;

import com.ulearning.ulms.core.exceptions.ULMSSysException;
import com.ulearning.ulms.finance.dao.OrganAccountDAOFactory;
import com.ulearning.ulms.finance.dao.OrganAccountDAOImpl;
import com.ulearning.ulms.finance.form.OrganAccountDetailForm;
import com.ulearning.ulms.finance.form.OrganAccountForm;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


/**
 * 机构帐户的辅助类,用户接口
 *
 * @author Liz
 * @ date 2005.12.09
 */
public class OrganAccountHelper
{
        /**
         * 添加机构用户帐户明细同时修改总帐信息。
         *
         * @param detailFrm 机构帐户FormBean
         * @return
         */
        public int insert(OrganAccountDetailForm detailFrm)
        {
                int iReturn = 0;

                try
                {
                        OrganAccountDAOImpl dao = (OrganAccountDAOImpl) OrganAccountDAOFactory.getDAO();
                        iReturn = dao.inster(detailFrm);
                }
                catch (Exception e)
                {
                        e.printStackTrace();
                }

                return iReturn;
        }

        /**
         * 修改机构明细信息同时修改总帐信息
         *
         * @param detailFrm
         */
        public void update(OrganAccountDetailForm detailFrm)
        {
                try
                {
                        OrganAccountDAOImpl dao = (OrganAccountDAOImpl) OrganAccountDAOFactory.getDAO();
                        dao.updateOrganAccount(detailFrm);
                }
                catch (Exception e)
                {
                        e.printStackTrace();
                }
        }

        /**
         * 根据机构ID、时间段和明细类型(收或支)取机构明细列表。
         *
         * @param orgID      机构ID
         * @param begindate  开始时间
         * @param endDate    结束时间
         * @param detailType 明细类型(1：收   2：支  0:取所有)
         * @return
         */
        public List getOADetailOfIDDate(int orgID, String begindate,
                                        String endDate, int detailType)
        {
                List list = null;
                list = getOADetailFrm(0, orgID, 0, begindate, endDate, detailType, -1,
                        -1);

                return list;
        }

        /**
         * 根据收支类型ID、机构ID和明细类型(收/支)取机构帐户明细
         *
         * @param orgID      机构ID
         * @param typeID     收支类型ID
         * @param detailType 明细类型(1：收   2：支  0:取所有)
         * @return
         */
        public List getOADetailOfType(int orgID, int typeID, int detailType)
        {
                List list = null;
                list = getOADetailFrm(0, orgID, typeID, "", "", detailType, -1, -1);

                return list;
        }

        /**
         * 根据收支类型ID、机构ID和明细类型(收/支)取机构帐户明细     有翻页
         *
         * @param orgID      机构ID
         * @param typeID     收支类型ID
         * @param detailType 明细类型(1：收   2：支  0:取所有)
         * @return
         */
        public List getOADetailOfTypePage(int orgID, int typeID, int detailType,
                                          int firstResult, int maxResults)
        {
                List list = null;
                list = getOADetailFrm(0, orgID, typeID, "", "", detailType,
                        firstResult, maxResults);

                return list;
        }

        /**
         * 根据机构ID取的帐户明细并建立对应业务支出的名称
         * 如：课程名称、班级名称...
         *
         * @param orgID
         * @param beginDate
         * @param endDate
         * @param detailType 1：收 2：支  0:取所有(包括收支)
         * @return List OrganAccountDetailForm  个人帐户FormBean
         * @throws com.ulearning.ulms.core.exceptions.ULMSSysException
         *
         */
        public List getUADetailENameForUID(int orgID, String beginDate,
                                           String endDate, int detailType) throws ULMSSysException
        {
                List list = getOADetailFrm(0, orgID, 0, beginDate, endDate, detailType,
                        -1, -1);
                makeEntityName(list);

                return list;
        }

        /**
         * 完成用户帐户明细和业务实体等其它信息的组合
         *
         * @param list
         */
        private void makeEntityName(List list)
        {
                List lResult = new ArrayList();

                if ((null != list) && (0 != list.size()))
                {
                        OrganAccountDetailForm frm = null;
                        String sName = "　"; //全角空格HTML显示用
                        Map map = null;

                        for (Iterator iter = list.iterator(); iter.hasNext();)
                        {
                                frm = (OrganAccountDetailForm) iter.next();
                                map = publicHelper.getTypeName(frm.getOaDetailTypeID(),
                                        frm.getOaDetailInOutType()); //调用
                                frm.setAssIntOrOut(map.get("typeDesc").toString());
                                frm.setAssTypeName(map.get("typeName").toString());

                                //setDetailDate(frm);//调用
                                if (0 == frm.getOaDetailTypeID())
                                {
                                        sName = "　"; //全角空格HTML显示用
                                }
                                else if (3 == frm.getOaDetailTypeID())
                                { //课程
                                        sName = publicHelper.getMastName(frm.getOaDetailEntityValue()); //调用
                                }

                                frm.setAssEntityName(sName);
                                lResult.add(frm);
                        }

                        list = lResult;
                }
        }

        /**
         * 根据个人用户ID、一个明细的时间段、明细类型(收入/支出)，取个人用户帐户明细。
         *
         * @param detailID
         * @param organID
         * @param startDateTime 没有日期查找可以添null
         * @param endDateTime   没有日期查找可以添null
         * @param firstResult   不分页添-1
         * @param maxResults    不分页添-1
         * @param detilaType    1：收 2：支  0:取所有(包括收支)
         * @param typeID        收支类型ID
         * @return List OrganAccountDetailForm  个人帐户FormBean
         */
        public List getOADetailFrm(int detailID, int organID, int typeID,
                                   String startDateTime, String endDateTime, int detilaType,
                                   int firstResult, int maxResults)
        {
                List listFrm = null;

                try
                {
                        StringBuffer hql = new StringBuffer();
                        hql.append(" from ForganAccountDetailModel where 1>0 ");

                        if (0 != organID)
                        {
                                hql.append(" and  orgId= ");
                                hql.append(organID);
                        }

                        if (0 != detilaType)
                        {
                                hql.append(" and oadetailInOutType = ");
                                hql.append(detilaType);
                        }

                        if (0 != detailID)
                        {
                                hql.append(" and oadetailId = ");
                                hql.append(detailID);
                        }

                        if (0 != typeID)
                        {
                                hql.append(" and oadetailTypeId = ");
                                hql.append(typeID);
                        }

                        if ((null != startDateTime) && !startDateTime.equals(""))
                        {
                                hql.append(" and oadetailDate>=:startDateTime");

                                //hql.append(detailID);
                        }

                        if ((null != endDateTime) && !endDateTime.equals(""))
                        {
                                hql.append(" and oadetailDate<=:endDateTime");

                                //hql.append(endDate);
                        }

                        OrganAccountDAOImpl dao = (OrganAccountDAOImpl) OrganAccountDAOFactory.getDAO();

                        listFrm = dao.getDAOetailFrm(hql.toString(), startDateTime,
                                endDateTime, firstResult, maxResults);
                }
                catch (Exception e)
                {
                        e.printStackTrace();
                }

                return listFrm;
        }

        /**
         * 能用方法返回机构的总帐信息
         *
         * @param organID
         * @param organName
         * @param status    帐户状态 1:可用  2:不可用 3....
         * @return List  OrganAccountForm 总帐FrmBean
         */
        public List getOMainAcotFrm(int organID, String organName, int status)
        {
                List list = null;

                try
                {
                        StringBuffer hql = new StringBuffer();
                        hql.append(" from ForganAccountModel where 1>0 ");

                        if (0 != organID)
                        {
                                hql.append(" and orgId=");
                                hql.append(organID);
                        }

                        if ((null != organName) && !organName.equals(""))
                        {
                                hql.append(" and oacotOrganName like '%");
                                hql.append(organName);
                                hql.append("%'");
                        }

                        if (0 != status)
                        {
                                hql.append(" and oacotStatus=");
                                hql.append(status);
                        }

                        OrganAccountDAOImpl dao = (OrganAccountDAOImpl) OrganAccountDAOFactory.getDAO();
                        list = dao.getOMainAcotFrm(hql.toString());
                }
                catch (Exception e)
                {
                        e.printStackTrace();
                }

                return list;
        }

        /**
         * 根据机构ID取总帐信息
         *
         * @param organID
         * @return
         */
        public List getOMainAccountOfId(int organID)
        {
                List list = null;
                list = getOMainAcotFrm(organID, null, 0);

                return list;
        }

        /**
         * 根据机构ID返回当前帐户金额
         *
         * @param organID
         * @return
         */
        public double getActtotalOfId(int organID)
        {
                List list = null;
                double dReturn = 0.0;
                list = getOMainAccountOfId(organID);

                if ((null != list) && (0 != list.size()))
                {
                        dReturn = ((OrganAccountForm) list.get(0)).getoAcotTotal();
                }

                return dReturn;
        }

        /**
         * 根据机构ID从机构帐户减去给入的金额
         *
         * @param orgID
         * @param sum   要减去的金额
         */
        public boolean subtractAcotTotal(int orgID, double sum)
        {
                boolean bReturn = false;

                try
                {
                        List list = null;
                        list = getOMainAccountOfId(orgID);

                        if ((null != list) && (0 != list.size()))
                        {
                                OrganAccountDAOImpl dao = (OrganAccountDAOImpl) OrganAccountDAOFactory.getDAO();
                                dao.updateMainAcot((OrganAccountForm) list.get(0), sum, 1); //调用
                                bReturn = true;
                        }
                        else
                        {
                                System.out.println("帐户不存在，请和管理员联系!");
                        }
                }
                catch (Exception e)
                {
                        e.printStackTrace();
                }

                return bReturn;
        }

        /**
         * 根据机构ID从机构帐户加上给入的金额
         *
         * @param orgID
         * @param sum   要增加的金额
         */
        public boolean addAcotTotal(int orgID, double sum)
        {
                boolean bReturn = false;

                try
                {
                        List list = null;
                        list = getOMainAccountOfId(orgID);

                        if ((null != list) && (0 != list.size()))
                        {
                                OrganAccountDAOImpl dao = (OrganAccountDAOImpl) OrganAccountDAOFactory.getDAO();
                                dao.updateMainAcot((OrganAccountForm) list.get(0), sum, 2); //调用
                                bReturn = true;
                        }
                        else
                        {
                                System.out.println("帐户不存在，请和管理员联系!");
                        }
                }
                catch (Exception e)
                {
                        e.printStackTrace();
                }

                return bReturn;
        }
}
