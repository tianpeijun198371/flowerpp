/**
 * PhysicsResHelper.java.
 * User: liz  Date: 2006-2-17
 * ��Դ�ֵ丨����
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
         * ������Դ����
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
         * ������Դ�ֵ�
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
         * ɾ����Դ����
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
         * ������������ȡ��Դ����
         *
         * @param aspid
         * @param orgid
         * @param id       ����
         * @param resname  ����Դ����
         * @param resType  ��Դ����0��û����  1������  2:������
         * @param resstate ��Դ״̬0�����á���1��������
         * @return PhysicsResForm����
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
                        resultList = makeResTypeName(resultList); //����
                }

                return resultList;
        }

        /**
         * ������ʩ��������
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
                                frm.setRestypename("��"); //û�����壬���һ��ȫ�ǿո�
                        }
                        else if (1 == frm.getRestype())
                        {
                                frm.setRestypename("����");
                        }
                        else if (2 == frm.getRestype())
                        {
                                frm.setRestypename("������");
                        }
                        else if (3 == frm.getRestype())
                        {
                                frm.setRestypename("�ͷ�");
                        }
                        else if (4 == frm.getRestype())
                        {
                                frm.setRestypename("����");
                        }
                        else if (5 == frm.getRestype())
                        {
                                frm.setRestypename("����");
                        }

                        orgname = getorgName(frm.getOrgid()); //����
                        frm.setOrgname(orgname);
                        frm.setResstatename(makeResStateName(Integer.toString(
                                frm.getResstate()))); //����
                        resultlist.add(frm);
                }

                return resultlist;
        }

        /**
         * ����Ϊmethod��ȡ��������
         *
         * @param orgid
         * @return
         */
        private static String getorgName(int orgid)
        {
                String sname = "��";
                OrganForm frm = OrganHelper.getOrgan(orgid);
                sname = frm.getOrgName();

                if ((null == sname) || sname.equals(""))
                {
                        sname = "������ɾ��";
                }

                return sname;
        }

        /**
         * ����method��������ʩ״̬����
         *
         * @param stateId
         * @return
         */
        private static String makeResStateName(String stateId)
        {
                String stateName = "��";

                if (stateId.equals("0"))
                {
                        stateName = "����";
                }
                else if (stateId.equals("1"))
                {
                        stateName = "������";
                }

                return stateName;
        }

        /**
         * @param aspid
         * @param orgid
         * @param resType ��Դ����0��û����  1������  2:������
         * @param used    ��Դ״̬0�����á���1��������
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
         * ����ԴID����
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
         * ����Դ���Ʋ���
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
         * ������Դ״̬ȡ������Դ
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
