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
 * �����ʻ��ĸ�����
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
         * ��Ӹ����û��ʻ���ϸͬʱ�޸�������Ϣ��
         *
         * @param detailFrm �����ʻ�FormBean
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
         * �޸ĸ�����ϸ��Ϣͬʱ�޸�������Ϣ
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
         * ���ݸ����û�ID����ϸ����(����/֧��)���ȡ�û����ʻ���ϸ
         *
         * @param userID
         * @param detilaType 1���� 2��֧  0:ȡ����(������֧)
         * @return List OrganAccountDetailForm  �����ʻ�FormBean
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
         * ���ݸ����û�ID��ҵ������ID��ҵ��ʵ��ID�����Ƿ��з���֧��
         *
         * @param userID
         * @param TypeID   ҵ������ID
         * @param entityID ҵ��ʵ���ID�磺�γ�ID���༶ID...
         * @return boolean OrganAccountDetailForm  �����ʻ�FormBean
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
         * ���ݸ����û�ҵ������ID��ҵ��ʵ��ID�����Ƿ��з���֧��������
         *
         * @param TypeID     ҵ������ID
         * @param entityID   ҵ��ʵ���ID�磺�γ�ID���༶ID...
         * @param detailType 1���� 2��֧  0:ȡ����(������֧)
         * @return List OrganAccountDetailForm  �����ʻ�FormBean
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
         * ����ҵ������ID��ҵ��ʵ��ID�����ʻ���Ϣ
         *
         * @param TypeID   ҵ������ID
         * @param entityID ҵ��ʵ���ID�磺�γ�ID���༶ID...
         * @return boolean OrganAccountDetailForm  �����ʻ�FormBean
         * @throws com.ulearning.ulms.core.exceptions.ULMSSysException
         *
         */
        public List isUADetail(int entityID, int TypeID) throws ULMSSysException
        {
                List list = getUADetailFrm(entityID, 0, 0, TypeID, null, null, 0, -1, -1);

                return list;
        }

        /**
         * ���ݸ����û�ID�û����ʻ���ϸ��������Ӧҵ��֧��������
         * �磺�γ����ơ��༶����...
         *
         * @param userID
         * @param detailType 1���� 2��֧  0:ȡ����(������֧)
         * @param beginDate
         * @param endDate
         * @return List OrganAccountDetailForm  �����ʻ�FormBean
         * @throws com.ulearning.ulms.core.exceptions.ULMSSysException
         *
         */
        public List getUADetailENameForUID(int userID, String beginDate,
                                           String endDate, int detailType) throws ULMSSysException
        {
                List list = getUADetailFrm(0, 0, userID, 0, beginDate, endDate,
                        detailType, -1, -1);
                makeEntityName(list); //����

                return list;
        }

        /**
         * ����û��ʻ���ϸ��ҵ��ʵ���������Ϣ�����
         *
         * @param list
         */
        private void makeEntityName(List list)
        {
                List lResult = new ArrayList();

                if ((null != list) && (0 != list.size()))
                {
                        UserAccountDetailForm frm = null;
                        String sName = "��"; //ȫ�ǿո�HTML��ʾ��
                        Map map;

                        for (Iterator iter = list.iterator(); iter.hasNext();)
                        {
                                frm = (UserAccountDetailForm) iter.next();
                                map = publicHelper.getTypeName(frm.getUaDetailTypeID(),
                                        frm.getUaDetailInOutType()); //����
                                frm.setAssIntOrOut(map.get("typeDesc").toString());
                                frm.setAssTypeName(map.get("typeName").toString());

                                if (0 == frm.getUaDetailTypeID())
                                {
                                        sName = "��"; //ȫ�ǿո�HTML��ʾ��
                                }
                                else if (3 == frm.getUaDetailTypeID())
                                { //�γ�
                                        sName = publicHelper.getMastName(frm.getUaDetailEntityValue()); //����
                                }

                                frm.setAssEntityName(sName);
                                lResult.add(frm);
                        }

                        list = lResult;
                }
        }

        /**
         * ������ֻ����������
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
         * ���ݸ����û�ID��һ����ϸ��ʱ��Ρ���ϸ����(����/֧��)��ȡ�����û��ʻ���ϸ��
         *
         * @param entityID      //��Ӧҵ��ʵ���ID�磺�γ�ID���༶ID...
         * @param detailID
         * @param userID
         * @param startDateTime û�����ڲ��ҿ�����null
         * @param endDateTime   û�����ڲ��ҿ�����null
         * @param firstResult   ����ҳ��-1
         * @param maxResults    ����ҳ��-1
         * @param detilaType    1���� 2��֧  0:ȡ����(������֧)
         * @param typeID        ��֧����ID
         * @return List OrganAccountDetailForm  �����ʻ�FormBean
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
         * ���ݸ����û�ID��һ����ϸ��ʱ��Ρ���ϸ����(����/֧��)��ȡ�����û��ʻ���ϸ����ϸ��Ӧ�Ŀγ̻��������ҵ����Ϣ��
         *
         * @param uhql          �û������һ����ѯ����(from XXX where ...)
         * @param startDateTime û�����ڲ��ҿ�����null
         * @param endDateTime   û�����ڲ��ҿ�����null
         * @param firstResult   ����ҳ��-1
         * @param maxResults    ����ҳ��-1
         * @return List OrganAccountDetailForm  �����ʻ�FormBean
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
         * ��ѯ�ʻ���ϸ����صĿγ���Ϣ
         *
         * @param userID
         * @param startDateTime û�����ڲ��ҿ�����null
         * @param endDateTime   û�����ڲ��ҿ�����null
         * @param firstResult   ����ҳ��-1
         * @param maxResults    ����ҳ��-1
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
         * �����û�IDȡ�ʻ���ϸ�Ϳγ���Ϣ
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
         * ���ݸ����û�ID����/֧ID����ϸ����(����/֧��)ȡ�ʻ���ϸ
         *
         * @param userID
         * @param inoutID    �����֧��ID
         * @param detilaType 1���� 2��֧  0:ȡ����(������֧)
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
         * ���ݸ����û�ID����/֧ID����ϸ����(����/֧��)ȡ�ʻ���ϸ�� �з�ҳ����
         *
         * @param userID
         * @param inoutID    �����֧��ID
         * @param detilaType 1���� 2��֧  0:ȡ����(������֧)
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
         * ���÷��������û���������Ϣ
         *
         * @param userID
         * @param userName
         * @param status   �ʻ�״̬ 1:����  2:������ 3....
         * @return List  UserAccountForm ����FrmBean
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
         * ���÷��������û���������Ϣ
         *
         * @param userID
         * @param userName
         * @param status   �ʻ�״̬ 1:����  2:������ 3....
         * @param relaId   �༶ID��γ�ID
         * @param relaType ���༶�Ϳγ̵�
         * @return List  UserAccountForm ����FrmBean
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
         * �����û�IDȡ������Ϣ
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
         * �����û�ID���ص�ǰ�ʻ����
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
         * ���ݻ���ID�ӻ����ʻ���ȥ����Ľ��
         *
         * @param orgID
         * @param sum   Ҫ��ȥ�Ľ��
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
                                dao.updateMainAcot((UserAccountForm) list.get(0), sum, 1); //����
                                bReturn = true;
                        }
                        else
                        {
                                System.out.println("�ʻ������ڣ���͹���Ա��ϵ!");
                        }
                }
                catch (Exception e)
                {
                        e.printStackTrace();
                }

                return bReturn;
        }

        /**
         * �����û�ID�ӻ����ʻ����ϸ���Ľ��
         *
         * @param orgID
         * @param sum   Ҫ���ӵĽ��
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
                                dao.updateMainAcot((UserAccountForm) list.get(0), sum, 2); //����
                                bReturn = true;
                        }
                        else
                        {
                                System.out.println("�ʻ������ڣ���͹���Ա��ϵ!");
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
