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
 * �����ʻ��ĸ�����,�û��ӿ�
 *
 * @author Liz
 * @ date 2005.12.09
 */
public class OrganAccountHelper
{
        /**
         * ��ӻ����û��ʻ���ϸͬʱ�޸�������Ϣ��
         *
         * @param detailFrm �����ʻ�FormBean
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
         * �޸Ļ�����ϸ��Ϣͬʱ�޸�������Ϣ
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
         * ���ݻ���ID��ʱ��κ���ϸ����(�ջ�֧)ȡ������ϸ�б�
         *
         * @param orgID      ����ID
         * @param begindate  ��ʼʱ��
         * @param endDate    ����ʱ��
         * @param detailType ��ϸ����(1����   2��֧  0:ȡ����)
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
         * ������֧����ID������ID����ϸ����(��/֧)ȡ�����ʻ���ϸ
         *
         * @param orgID      ����ID
         * @param typeID     ��֧����ID
         * @param detailType ��ϸ����(1����   2��֧  0:ȡ����)
         * @return
         */
        public List getOADetailOfType(int orgID, int typeID, int detailType)
        {
                List list = null;
                list = getOADetailFrm(0, orgID, typeID, "", "", detailType, -1, -1);

                return list;
        }

        /**
         * ������֧����ID������ID����ϸ����(��/֧)ȡ�����ʻ���ϸ     �з�ҳ
         *
         * @param orgID      ����ID
         * @param typeID     ��֧����ID
         * @param detailType ��ϸ����(1����   2��֧  0:ȡ����)
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
         * ���ݻ���IDȡ���ʻ���ϸ��������Ӧҵ��֧��������
         * �磺�γ����ơ��༶����...
         *
         * @param orgID
         * @param beginDate
         * @param endDate
         * @param detailType 1���� 2��֧  0:ȡ����(������֧)
         * @return List OrganAccountDetailForm  �����ʻ�FormBean
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
         * ����û��ʻ���ϸ��ҵ��ʵ���������Ϣ�����
         *
         * @param list
         */
        private void makeEntityName(List list)
        {
                List lResult = new ArrayList();

                if ((null != list) && (0 != list.size()))
                {
                        OrganAccountDetailForm frm = null;
                        String sName = "��"; //ȫ�ǿո�HTML��ʾ��
                        Map map = null;

                        for (Iterator iter = list.iterator(); iter.hasNext();)
                        {
                                frm = (OrganAccountDetailForm) iter.next();
                                map = publicHelper.getTypeName(frm.getOaDetailTypeID(),
                                        frm.getOaDetailInOutType()); //����
                                frm.setAssIntOrOut(map.get("typeDesc").toString());
                                frm.setAssTypeName(map.get("typeName").toString());

                                //setDetailDate(frm);//����
                                if (0 == frm.getOaDetailTypeID())
                                {
                                        sName = "��"; //ȫ�ǿո�HTML��ʾ��
                                }
                                else if (3 == frm.getOaDetailTypeID())
                                { //�γ�
                                        sName = publicHelper.getMastName(frm.getOaDetailEntityValue()); //����
                                }

                                frm.setAssEntityName(sName);
                                lResult.add(frm);
                        }

                        list = lResult;
                }
        }

        /**
         * ���ݸ����û�ID��һ����ϸ��ʱ��Ρ���ϸ����(����/֧��)��ȡ�����û��ʻ���ϸ��
         *
         * @param detailID
         * @param organID
         * @param startDateTime û�����ڲ��ҿ�����null
         * @param endDateTime   û�����ڲ��ҿ�����null
         * @param firstResult   ����ҳ��-1
         * @param maxResults    ����ҳ��-1
         * @param detilaType    1���� 2��֧  0:ȡ����(������֧)
         * @param typeID        ��֧����ID
         * @return List OrganAccountDetailForm  �����ʻ�FormBean
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
         * ���÷������ػ�����������Ϣ
         *
         * @param organID
         * @param organName
         * @param status    �ʻ�״̬ 1:����  2:������ 3....
         * @return List  OrganAccountForm ����FrmBean
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
         * ���ݻ���IDȡ������Ϣ
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
         * ���ݻ���ID���ص�ǰ�ʻ����
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
                        list = getOMainAccountOfId(orgID);

                        if ((null != list) && (0 != list.size()))
                        {
                                OrganAccountDAOImpl dao = (OrganAccountDAOImpl) OrganAccountDAOFactory.getDAO();
                                dao.updateMainAcot((OrganAccountForm) list.get(0), sum, 1); //����
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
         * ���ݻ���ID�ӻ����ʻ����ϸ���Ľ��
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
                        list = getOMainAccountOfId(orgID);

                        if ((null != list) && (0 != list.size()))
                        {
                                OrganAccountDAOImpl dao = (OrganAccountDAOImpl) OrganAccountDAOFactory.getDAO();
                                dao.updateMainAcot((OrganAccountForm) list.get(0), sum, 2); //����
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
}
