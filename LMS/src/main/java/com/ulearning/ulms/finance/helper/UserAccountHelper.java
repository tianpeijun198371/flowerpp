/**
 * UserAccountHelper.java.
 * User: liz  Date: 2005-12-9
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.finance.helper;

import com.ulearning.ulms.core.exceptions.ULMSSysException;
import com.ulearning.ulms.core.util.DateTimeUtil;
import com.ulearning.ulms.finance.dao.UserAccountDAOFactory;
import com.ulearning.ulms.finance.dao.UserAccountDAOImpl;
import com.ulearning.ulms.finance.form.UserAccountDetailForm;
import com.ulearning.ulms.finance.form.UserAccountForm;
import com.ulearning.ulms.finance.model.FuserAccountModel;
import com.ulearning.ulms.util.HibernateDAO;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


/**
 * 个人帐户的辅助类
 *
 * @author Liz
 * @ date 2005.12.09
 */
public class UserAccountHelper
{
        /**
         * only build Main Account
         *
         * @param userID
         * @param userName
         * @param lastUser
         * @return
         */
        public int addMaccount(int userID, String userName, String lastUser)
        {
                int isInst = 0;
                UserAccountDAOImpl dao = (UserAccountDAOImpl) UserAccountDAOFactory.getDAO();
                isInst = dao.instMainAccount(userID, userName, lastUser);

                return isInst;
        }

        /**
         * 添加个人用户帐户明细同时修改总帐信息。
         *
         * @param detailFrm 个人帐户FormBean
         * @return
         */
        public int insert(UserAccountDetailForm detailFrm)
        {
                int iReturn = 0;

                try
                {
                        UserAccountDAOImpl dao = (UserAccountDAOImpl) UserAccountDAOFactory.getDAO();
                        iReturn = dao.addUserAccount(detailFrm);
                }
                catch (Exception e)
                {
                        e.printStackTrace();
                }

                return iReturn;
        }

        /**
         * 修改个人明细信息同时修改总帐信息
         *
         * @param detailFrm
         */
        public void update(UserAccountDetailForm detailFrm)
        {
                try
                {
                        UserAccountDAOImpl dao = (UserAccountDAOImpl) UserAccountDAOFactory.getDAO();
                        dao.updateUserAccount(detailFrm);
                }
                catch (Exception e)
                {
                        e.printStackTrace();
                }
        }

        /**
         * 根据个人用户ID和明细类型(收入/支出)标记取用户的帐户明细
         *
         * @param userID
         * @param detilaType 1：收 2：支  0:取所有(包括收支)
         * @return List OrganAccountDetailForm  个人帐户FormBean
         * @throws com.ulearning.ulms.core.exceptions.ULMSSysException
         *
         */
        public List getUADetailOfUID(int userID, int detilaType)
                throws ULMSSysException
        {
                List list = getUADetailFrm(0, 0, userID, 0, null, null, detilaType, -1,
                        -1);

                return list;
        }

        /**
         * 根据个人用户ID和业务类型ID和业务实体ID返回是否有费用支出
         *
         * @param userID
         * @param TypeID   业务类型ID
         * @param entityID 业务实体的ID如：课程ID、班级ID...
         * @return boolean OrganAccountDetailForm  个人帐户FormBean
         * @throws com.ulearning.ulms.core.exceptions.ULMSSysException
         *
         */
        public boolean isUADetail(int entityID, int userID, int TypeID)
                throws ULMSSysException
        {
                boolean bln = false;
                List list = getUADetailFrm(entityID, 0, userID, TypeID, null, null, 2,
                        -1, -1);

                if ((null != list) && (0 != list.size()))
                {
                        bln = true;
                }

                return bln;
        }

        /**
         * 根据个人用户业务类型ID和业务实体ID返回是否有费用支出或收入
         *
         * @param TypeID     业务类型ID
         * @param entityID   业务实体的ID如：课程ID、班级ID...
         * @param detailType 1：收 2：支  0:取所有(包括收支)
         * @return List OrganAccountDetailForm  个人帐户FormBean
         * @throws com.ulearning.ulms.core.exceptions.ULMSSysException
         *
         */
        public List getUADetail(int entityID, int TypeID, int detailType)
                throws ULMSSysException
        {
                List list = getUADetailFrm(entityID, 0, 0, TypeID, null, null,
                        detailType, -1, -1);

                return list;
        }

        /**
         * 根据业务类型ID和业务实体ID返回帐户信息
         *
         * @param TypeID   业务类型ID
         * @param entityID 业务实体的ID如：课程ID、班级ID...
         * @return boolean OrganAccountDetailForm  个人帐户FormBean
         * @throws com.ulearning.ulms.core.exceptions.ULMSSysException
         *
         */
        public List isUADetail(int entityID, int TypeID) throws ULMSSysException
        {
                List list = getUADetailFrm(entityID, 0, 0, TypeID, null, null, 0, -1, -1);

                return list;
        }

        /**
         * 根据个人用户ID用户的帐户明细并建立对应业务支出的名称
         * 如：课程名称、班级名称...
         *
         * @param userID
         * @param detailType 1：收 2：支  0:取所有(包括收支)
         * @param beginDate
         * @param endDate
         * @return List OrganAccountDetailForm  个人帐户FormBean
         * @throws com.ulearning.ulms.core.exceptions.ULMSSysException
         *
         */
        public List getUADetailENameForUID(int userID, String beginDate,
                                           String endDate, int detailType) throws ULMSSysException
        {
                List list = getUADetailFrm(0, 0, userID, 0, beginDate, endDate,
                        detailType, -1, -1);
                makeEntityName(list); //调用

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
                        UserAccountDetailForm frm = null;
                        String sName = "　"; //全角空格HTML显示用
                        Map map;

                        for (Iterator iter = list.iterator(); iter.hasNext();)
                        {
                                frm = (UserAccountDetailForm) iter.next();
                                map = publicHelper.getTypeName(frm.getUaDetailTypeID(),
                                        frm.getUaDetailInOutType()); //调用
                                frm.setAssIntOrOut(map.get("typeDesc").toString());
                                frm.setAssTypeName(map.get("typeName").toString());

                                if (0 == frm.getUaDetailTypeID())
                                {
                                        sName = "　"; //全角空格HTML显示用
                                }
                                else if (3 == frm.getUaDetailTypeID())
                                { //课程
                                        sName = publicHelper.getMastName(frm.getUaDetailEntityValue()); //调用
                                }

                                frm.setAssEntityName(sName);
                                lResult.add(frm);
                        }

                        list = lResult;
                }
        }

        /**
         * 将日期只保留年月日
         *
         * @param frm
         */
        private void setDetailDate(UserAccountDetailForm frm)
        {
                frm.setUaDetailDate(DateTimeUtil.parseDate(frm.getUaDetailDate()
                        .toString()
                        .substring(0, 10)));
        }

        /**
         * 根据个人用户ID、一个明细的时间段、明细类型(收入/支出)，取个人用户帐户明细。
         *
         * @param entityID      //对应业务实体的ID如：课程ID、班级ID...
         * @param detailID
         * @param userID
         * @param startDateTime 没有日期查找可以添null
         * @param endDateTime   没有日期查找可以添null
         * @param firstResult   不分页添-1
         * @param maxResults    不分页添-1
         * @param detilaType    1：收 2：支  0:取所有(包括收支)
         * @param typeID        收支类型ID
         * @return List OrganAccountDetailForm  个人帐户FormBean
         * @throws ULMSSysException
         */
        public List getUADetailFrm(int entityID, int detailID, int userID,
                                   int typeID, String startDateTime, String endDateTime, int detilaType,
                                   int firstResult, int maxResults)
        {
                List listFrm = null;

                try
                {
                        StringBuffer hql = new StringBuffer();
                        hql.append(
                                " from com.ulearning.ulms.finance.model.FuserAccountDetailModel where 0<1 ");

                        if (0 != entityID)
                        {
                                hql.append(" and  uadetailEntityValue=");
                                hql.append(entityID);
                        }

                        if (0 != userID)
                        {
                                hql.append(" and  userId=");
                                hql.append(userID);
                        }

                        if (0 != detilaType)
                        {
                                hql.append(" and uadetailInOutType=");
                                hql.append(detilaType);
                        }

                        if (0 != detailID)
                        {
                                hql.append(" and uadetailId=");
                                hql.append(detailID);
                        }

                        if (0 != typeID)
                        {
                                hql.append(" and uadetailTypeId=");
                                hql.append(typeID);
                        }

                        if ((null != startDateTime) && !startDateTime.equals(""))
                        {
                                hql.append(" and uadetailDate>=:startDateTime");

                                //hql.append(detailID);
                        }

                        if ((null != endDateTime) && !endDateTime.equals(""))
                        {
                                hql.append(" and uadetailDate<=:endDateTime");

                                //hql.append(endDate);
                        }

                        UserAccountDAOImpl dao = (UserAccountDAOImpl) UserAccountDAOFactory.getDAO();

                        listFrm = dao.getUADetailFrm(hql.toString(), startDateTime,
                                endDateTime, firstResult, maxResults);
                }
                catch (Exception e)
                {
                        e.printStackTrace();
                }

                return listFrm;
        }

        /**
         * 根据个人用户ID、一个明细的时间段、明细类型(收入/支出)，取个人用户帐户明细和明细对应的课程或其它相关业务信息。
         *
         * @param uhql          用户给入的一个查询条件(from XXX where ...)
         * @param startDateTime 没有日期查找可以添null
         * @param endDateTime   没有日期查找可以添null
         * @param firstResult   不分页添-1
         * @param maxResults    不分页添-1
         * @return List OrganAccountDetailForm  个人帐户FormBean
         * @throws ULMSSysException
         */

        /*
          public List getRelationDB(String uhql, String startDateTime, String endDateTime, int firstResult, int maxResults)
          {
                  List listFrm = null;
                  try
                  {
                          UserAccountDAOImpl dao = (UserAccountDAOImpl) UserAccountDAOFactory.getDAO();
                          listFrm = dao.getUADetailFrm(uhql, startDateTime, endDateTime, firstResult, maxResults);
                  }
                  catch (Exception e)
                  {
                          e.printStackTrace();
                  }
                  return listFrm;
          }
        */

        /**
         * 查询帐户明细和相关的课程信息
         *
         * @param userID
         * @param startDateTime 没有日期查找可以添null
         * @param endDateTime   没有日期查找可以添null
         * @param firstResult   不分页添-1
         * @param maxResults    不分页添-1
         * @return
         */

        /*
          public List getDetailCourse(int userID, String startDateTime, String endDateTime, int firstResult, int maxResults)
          {
                  List list = null;
                  StringBuffer hql = new StringBuffer();
                  hql.append(" from com.ulearning.ulms.finance.model.FuserAccountDetailModel as a" +
                          ",com.ulearning.ulms.course.model.Master ");
                  hql.append(" as b where a.uadetailEntityValue=" +
                          "b.masterID and 1>0 ");
                  if (0 != userID)
                  {
                          hql.append(" and a.userId=");
                          hql.append(userID);
                  }
                  if (null != startDateTime && !startDateTime.equals(""))
                  {
                          hql.append(" and a.uadetailDate>=:startDateTime");
                          //hql.append(detailID);
                  }
                  if (null != endDateTime && !endDateTime.equals(""))
                  {
                          hql.append(" and a.uadetailDate<=:endDateTime");
                          //hql.append(endDate);
                  }
                  list = getRelationDB(hql.toString(), startDateTime, endDateTime, firstResult, maxResults);
                  return list;
          }
        */

        /**
         * 根据用户ID取帐户明细和课程信息
         *
         * @param userID
         * @return
         */

        /*
          public List getDetailCous(int userID)
          {
                  List list = null;
                  list = getDetailCourse(userID, null, null, -1, -1);
                  return list;
          }
        */

        /**
         * 根据个人用户ID、收/支ID、明细类型(收入/支出)取帐户明细
         *
         * @param userID
         * @param inoutID    收入或支出ID
         * @param detilaType 1：收 2：支  0:取所有(包括收支)
         * @return
         * @throws ULMSSysException
         */
        public List getUADetailOfType(int userID, int inoutID, int detilaType)
        {
                List list = getUADetailFrm(0, 0, userID, inoutID, null, null,
                        detilaType, -1, -1);

                return list;
        }

        /**
         * 根据个人用户ID、收/支ID、明细类型(收入/支出)取帐户明细， 有翻页功能
         *
         * @param userID
         * @param inoutID    收入或支出ID
         * @param detilaType 1：收 2：支  0:取所有(包括收支)
         * @return
         * @throws ULMSSysException
         */
        public List getUADetailOfTypePage(int userID, int inoutID, int detilaType,
                                          int firstResult, int maxResults) throws ULMSSysException
        {
                List list = getUADetailFrm(0, 0, userID, inoutID, null, null,
                        detilaType, firstResult, maxResults);

                return list;
        }

        /**
         * @param userID
         * @param userName
         * @param status
         * @param pageno
         * @param maxrow
         * @return
         */
        public List getUMainAcotFrm(int userID, String userName, int status,
                                    int pageno, int maxrow)
        {
                List list = null;

                try
                {
                        StringBuffer hql = new StringBuffer();
                        hql.append(" from FuserAccountModel where 1>0 ");

                        if (0 != userID)
                        {
                                hql.append(" and userId=");
                                hql.append(userID);
                        }

                        if ((null != userName) && !userName.equals(""))
                        {
                                hql.append(" and uacotUserName like '%");
                                hql.append(userName);
                                hql.append("%'");
                        }

                        if (0 != status)
                        {
                                hql.append(" and uacotStatus=");
                                hql.append(status);
                        }

                        UserAccountDAOImpl dao = (UserAccountDAOImpl) UserAccountDAOFactory.getDAO();
                        list = dao.getUADetailFrm(hql.toString(), null, null, pageno, maxrow);
                }
                catch (Exception e)
                {
                        e.printStackTrace();
                }

                return list;
        }

        /**
         * 能用方法返回用户的总帐信息
         *
         * @param userID
         * @param userName
         * @param status   帐户状态 1:可用  2:不可用 3....
         * @return List  UserAccountForm 总帐FrmBean
         */
        public List getUMainAcotFrm(int userID, String userName, int status)
        {
                List list = null;

                try
                {
                        StringBuffer hql = new StringBuffer();
                        hql.append(" from FuserAccountModel where 1>0 ");

                        if (0 != userID)
                        {
                                hql.append(" and userId=");
                                hql.append(userID);
                        }

                        if ((null != userName) && !userName.equals(""))
                        {
                                hql.append(" and uacotUserName like '%");
                                hql.append(userName);
                                hql.append("%'");
                        }

                        if (0 != status)
                        {
                                hql.append(" and uacotStatus=");
                                hql.append(status);
                        }

                        UserAccountDAOImpl dao = (UserAccountDAOImpl) UserAccountDAOFactory.getDAO();
                        list = dao.getUMainAcotList(hql.toString());
                }
                catch (Exception e)
                {
                        e.printStackTrace();
                }

                return list;
        }

        /**
         * 能用方法返回用户的总帐信息
         *
         * @param userID
         * @param userName
         * @param status   帐户状态 1:可用  2:不可用 3....
         * @param relaId   班级ID或课程ID
         * @param relaType 区班级和课程等
         * @return List  UserAccountForm 总帐FrmBean
         */
        public List getUMainAcotFrm(int userID, String userName, int status,
                                    Integer relaId, Integer relaType)
        {
                List list = null;
                List listFrm = null;

                try
                {
                        StringBuffer hql = new StringBuffer();
                        hql.append(" from FuserAccountModel as fuaMod where 1=1");

                        if (0 != userID)
                        {
                                hql.append(" and fuaMod.userId=");
                                hql.append(userID);
                        }

                        if ((null != userName) && !userName.equals(""))
                        {
                                hql.append(" and fuaMod.uacotUserName like '%");
                                hql.append(userName);
                                hql.append("%'");
                        }

                        if (0 != status)
                        {
                                hql.append(" and fuaMod.uacotStatus=");
                                hql.append(status);
                        }

                        /* if (null != relaId && !relaId.equals("0"))
                        {
                                hql.append(" and cuMod.comp_id.relationID=");
                                hql.append(relaId);
                        }
                          if (null != relaType && !relaType.equals(""))
                        {
                                hql.append(" and cuMod.comp_id.type=");
                                hql.append(relaType);
                        }*/
                        Object[] obj = null;
                        //System.out.println("helper++hql=============="+hql.toString());
                        list = HibernateDAO.find(hql.toString());

                        // System.out.println("help+++list----------------------"+list);
                        FuserAccountModel fumod = null;
                        UserAccountForm typeFrom = null;
                        listFrm = new ArrayList();

                        if ((list != null) && (list.size() > 0))
                        {
                                for (int i = 0; i < list.size(); i++)
                                {
                                        //fumod=new FuserAccountModel();
                                        fumod = (FuserAccountModel) list.get(i);
                                        typeFrom = new UserAccountForm();
                                        listFrm.add(typeFrom.getUserAccountForm(fumod));
                                }
                        }
                }
                catch (Exception e)
                {
                        e.printStackTrace();
                }

                return listFrm;
        }

        /**
         * 根据用户ID取总帐信息
         *
         * @param userID
         * @return
         */
        public List getUMainAccountOfId(int userID)
        {
                List list = null;
                list = getUMainAcotFrm(userID, null, 0);

                return list;
        }

        /**
         * 根据用户ID返回当前帐户金额
         *
         * @param organID
         * @return
         */
        public double getActtotalOfId(int organID)
        {
                List list = null;
                double dReturn = 0.0;
                list = getUMainAccountOfId(organID);

                if ((null != list) && (0 != list.size()))
                {
                        dReturn = ((UserAccountForm) list.get(0)).getuAcotTotal();
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
                        list = getUMainAccountOfId(orgID);

                        if ((null != list) && (0 != list.size()))
                        {
                                UserAccountDAOImpl dao = (UserAccountDAOImpl) UserAccountDAOFactory.getDAO();
                                dao.updateMainAcot((UserAccountForm) list.get(0), sum, 1); //调用
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
         * 根据用户ID从机构帐户加上给入的金额
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
                        list = getUMainAccountOfId(orgID);

                        if ((null != list) && (0 != list.size()))
                        {
                                UserAccountDAOImpl dao = (UserAccountDAOImpl) UserAccountDAOFactory.getDAO();
                                dao.updateMainAcot((UserAccountForm) list.get(0), sum, 2); //调用
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

        public void addCertUserFanace(String[] userid, String[] cuserid,
                                      String[] username, String[] sDate, String[] optName, String[] optID,
                                      String[] sum, String orgID_str, String aspID, int inorout)
        {
                UserAccountDetailForm frm = new UserAccountDetailForm();

                for (int i = 0; i < userid.length; i++)
                {
                        for (int k = 0; k < cuserid.length; k++)
                        {
                                if (userid[i].equals(cuserid[k]))
                                {
                                        System.out.println("userid[" + k + "]=========" +
                                                Integer.parseInt(cuserid[k]));
                                        // System.out.println("userid[" + k + "]=========" + userid[k]);
                                        System.out.println("username[" + k + "]=========" +
                                                username[k]);
                                        System.out.println("Double.valueOf(sum[k]).doubleValue()" +
                                                k + "]=========" +
                                                Double.valueOf(sum[k]).doubleValue());
                                        frm.setUserID(Integer.parseInt(cuserid[k]));
                                        frm.setUaDetailAmount(Double.valueOf(sum[k]).doubleValue());
                                        frm.setUaDetailUserName(username[k]);
                                        frm.setUaDetailDate(DateTimeUtil.parseDate(sDate[k]));
                                        frm.setOrgId(Integer.parseInt(orgID_str));
                                        frm.setAspId(Integer.parseInt(aspID));
                                        frm.setUaDetailOperatorID(Integer.parseInt(optID[k]));
                                        frm.setUaDetailOperatorName(optName[k]);
                                        frm.setUaDetailInOutType(inorout);
                                        insert(frm);

                                        break;
                                }
                        }
                }
        }
}
