/**
 * OrganAccountDAOImpl.java.
 * User: liz  Date: 2005-12-12
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.finance.dao;

import com.ulearning.ulms.core.exceptions.ULMSSysException;
import com.ulearning.ulms.finance.form.OrganAccountDetailForm;
import com.ulearning.ulms.finance.form.OrganAccountForm;
import com.ulearning.ulms.finance.model.ForganAccountDetailModel;
import com.ulearning.ulms.finance.model.ForganAccountModel;
import com.ulearning.ulms.util.HibernateDAO;
import com.ulearning.ulms.util.HibernateUtil;

import net.sf.hibernate.HibernateException;
import net.sf.hibernate.MappingException;
import net.sf.hibernate.Session;
import net.sf.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;


/**
 * �����û��ʻ�DAO�ӿ�  ʵ��
 *
 * @author Liz
 * @ date 2005.12.09
 */
public class OrganAccountDAOImpl implements OrganAccountDAO
{
        /**
         * ��ӻ����ʻ���ϸͬʱ�޸�������Ϣ
         *
         * @param organAcotFrm
         * @return
         * @throws ULMSSysException
         */
        public int inster(OrganAccountDetailForm organAcotFrm)
                throws ULMSSysException
        {
                int iReturn = 0;
                Session session = null;

                try
                {
                        session = HibernateUtil.getSession();

                        Transaction tx = session.beginTransaction();
                        instDetailAccount(session,
                                organAcotFrm.getForganAccountDetailModel());
                        addMainAccount(session, makeAccontModel(organAcotFrm), organAcotFrm);
                        tx.commit();

                        iReturn = 1;
                }
                catch (MappingException e)
                {
                        throw new ULMSSysException(
                                "MappingException while Hibernate add:  " + e, e);
                }
                catch (HibernateException e)
                {
                        throw new ULMSSysException(
                                "HibernateException while Hibernate add:  " + e, e);
                }
                /* catch (SQLException e)
                {
                        throw new ULMSSysException("SQLException while Hibernate add:  " + e, e);
                }*/
                finally
                {
                        try
                        {
                                HibernateUtil.releaseSession(session);
                        }
                        catch (HibernateException e)
                        {
                                throw new ULMSSysException(
                                        "HibernateException while Hibernate update:  " + e, e);
                        }
                }

                return iReturn;
        }

        /**
         * ��Ӹ����ʻ���ϸ��Ϣ
         *
         * @param session
         * @param model
         * @throws HibernateException
         */
        private void instDetailAccount(Session session,
                                       ForganAccountDetailModel model) throws HibernateException
        {
                try
                {
                        session.save(model);
                        session.flush();
                }
                catch (HibernateException e)
                {
                        throw new HibernateException(e.toString());
                }
        }

        /**
         * ��Ӹ����ʻ���ϸ��Ϣ
         *
         * @param session
         * @param model
         * @throws HibernateException
         */
        private void updateDetailAccount(Session session,
                                         ForganAccountDetailModel model) throws HibernateException
        {
                try
                {
                        session.update(model);
                        session.flush();
                }
                catch (HibernateException e)
                {
                        throw new HibernateException(e.toString());
                }
        }

        /**
         * ��ӻ��޸ĸ������ʻ���Ϣ
         *
         * @param session
         */
        private void addMainAccount(Session session, ForganAccountModel model,
                                    OrganAccountDetailForm organAcotFrm)
        {
                double total = 0;
                ForganAccountModel currentModel = null;

                try
                {
                        currentModel = getAccount(model.getOrgId());

                        if (null == currentModel)
                        { //��û���ʻ�����
                                session.save(model);
                                session.flush();
                        }
                        else
                        { //�Ѿ����ʻ��޸Ľ��
                                //���ʻ���������ӵĽ��ϲ��������µ��ʻ����

                                if (1 == organAcotFrm.getOaDetailInOutType())
                                { //1���� 2��֧
                                        currentModel.setOacotTotal(currentModel.getOacotTotal() +
                                                model.getOacotTotal());
                                        currentModel.setOacotLastUpdate(model.getOacotLastUpdate());
                                }
                                else
                                {
                                        currentModel.setOacotTotal(currentModel.getOacotTotal() -
                                                model.getOacotTotal());
                                        currentModel.setOacotLastUpdate(model.getOacotLastUpdate());
                                }

                                session.update(currentModel);
                                session.flush();
                        }
                }
                catch (HibernateException e)
                {
                        e.printStackTrace();
                }
        }

        /**
         * ����������Ϣ
         *
         * @param frm
         * @return
         */
        private ForganAccountModel makeAccontModel(OrganAccountDetailForm frm)
        {
                ForganAccountModel model = null;
                model = frm.getForganAccountModel();

                return model;
        }

        /**
         * ��ѯ�Ƿ��е�ǰ�û���������Ϣ
         *
         * @param organId
         * @return model�����ʻ�
         */
        private ForganAccountModel getAccount(int organId)
        {
                ForganAccountModel model = null;
                OrganAccountForm listFrm = null;
                List list = null;

                try
                {
                        String hql = " from ForganAccountModel where orgId=" + organId;
                        list = getOMainAcotFrm(hql);

                        if ((null != list) && (0 != list.size()))
                        {
                                listFrm = (OrganAccountForm) list.get(0);
                                model = listFrm.getForganAccountModel();
                        }
                }
                catch (Exception e)
                {
                        throw new ULMSSysException(e);
                }

                return model;
        }

        /**
         * �޸Ļ�����ϸͬʱ�޸Ļ�������Ϣ
         *
         * @param organAcotFrm
         * @throws ULMSSysException
         */
        public void updateOrganAccount(OrganAccountDetailForm organAcotFrm)
                throws ULMSSysException
        {
                Session session = null;

                try
                {
                        session = HibernateUtil.getSession();

                        Transaction tx = session.beginTransaction();
                        updateDetailAccount(session,
                                organAcotFrm.getForganAccountDetailModel());
                        addMainAccount(session, makeAccontModel(organAcotFrm), organAcotFrm);
                        tx.commit();
                }
                catch (MappingException e)
                {
                        throw new ULMSSysException(
                                "MappingException while Hibernate add:  " + e, e);
                }
                catch (HibernateException e)
                {
                        throw new ULMSSysException(
                                "HibernateException while Hibernate add:  " + e, e);
                }
                /* catch (SQLException e)
                {
                        throw new ULMSSysException("SQLException while Hibernate add:  " + e, e);
                }*/
                finally
                {
                        try
                        {
                                HibernateUtil.releaseSession(session);
                        }
                        catch (HibernateException e)
                        {
                                throw new ULMSSysException(
                                        "HibernateException while Hibernate update:  " + e, e);
                        }
                }
        }

        public void removeOrganAccount(int oDetailID) throws ULMSSysException
        {
        }

        /**
         * ���õĲ�ѯ��������ѯ��������Ҳ����ֱ�ӵ����� ���в�ѯ
         *
         * @param hql         ��ѯ����
         * @param beginDate
         * @param endDate
         * @param firstResult
         * @param maxResults
         * @return
         * @throws ULMSSysException
         */
        public List getDAOetailFrm(String hql, String beginDate, String endDate,
                                   int firstResult, int maxResults) throws ULMSSysException
        {
                List listFrm = null;

                try
                {
                        OrganAccountDetailForm typeFrom = new OrganAccountDetailForm();
                        ForganAccountDetailModel typeModel = null;
                        List list = null;

                        if (((null != beginDate) && !beginDate.equals("")) &&
                                ((null != endDate) && !endDate.equals("")) &&
                                ((-1 != firstResult) && (-1 != maxResults)))
                        { //�����ڴ���ҳ
                                list = HibernateDAO.find(hql, beginDate, endDate, firstResult,
                                        maxResults); //Hibernate��ѯ
                        }
                        else
                        {
                                if (((null != beginDate) && !beginDate.equals("")) &&
                                        ((null != endDate) && !endDate.equals("")))
                                { //�����ڣ�û��ҳ
                                        list = HibernateDAO.find(hql, beginDate, endDate, -1, -1); //Hibernate��ѯ
                                }
                                else if (((-1 != firstResult) && (-1 != maxResults)))
                                { //û���ڣ��з�ҳ
                                        list = HibernateDAO.find(hql, firstResult, maxResults);
                                }
                                else
                                {
                                        //û����,û��ҳ
                                        list = HibernateDAO.find(hql); //Hibernate��ѯ
                                }
                        }

                        if ((null != list) && (0 < list.size()))
                        {
                                listFrm = new ArrayList();

                                for (int i = 0; i < list.size(); i++)
                                {
                                        typeModel = (ForganAccountDetailModel) list.get(i);
                                        listFrm.add(typeFrom.getOrganAccountDetailForm(typeModel));
                                }
                        }
                }
                catch (Exception e)
                {
                        throw new ULMSSysException(e.toString());
                }

                return listFrm;
        }

        /**
         * ���ݸ�����������ػ�������FromBean
         *
         * @param hql
         * @return List UserAccountForm  ��������FromBean
         * @throws ULMSSysException
         */
        public List getOMainAcotFrm(String hql) throws ULMSSysException
        {
                List listFrm = null;

                try
                {
                        OrganAccountForm typeFrom = new OrganAccountForm();
                        ForganAccountModel typeModel = null;
                        List list = null;
                        list = HibernateDAO.find(hql);

                        if ((null != list) && (0 < list.size()))
                        {
                                listFrm = new ArrayList();

                                for (int i = 0; i < list.size(); i++)
                                {
                                        typeModel = (ForganAccountModel) list.get(i);
                                        listFrm.add(typeFrom.getOrganAccountForm(typeModel));
                                }
                        }
                }
                catch (Exception e)
                {
                        throw new ULMSSysException(e.toString());
                }

                return listFrm;
        }

        /**
         * ���ݻ���ID�޸��ʻ����
         *
         * @param frm  �Ѿ��鵽���ʻ�
         * @param sum  Ҫ�۳��Ľ��
         * @param type 1=���� 2=����
         */
        public void updateMainAcot(OrganAccountForm frm, double sum, int type)
                throws ULMSSysException
        {
                try
                {
                        ForganAccountModel mod;
                        mod = frm.getForganAccountModel();

                        if (1 == type)
                        {
                                mod.setOacotTotal(mod.getOacotTotal() - sum);
                        }
                        else if (2 == type)
                        {
                                mod.setOacotTotal(mod.getOacotTotal() + sum);
                        }

                        HibernateDAO.update(mod);
                }
                catch (Exception e)
                {
                        throw new ULMSSysException(e);
                }
        }
}
