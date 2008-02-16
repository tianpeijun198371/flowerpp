/**
 * UserAccountDAOImpl.java.
 * User: liz  Date: 2005-12-12
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.finance.dao;

import com.ulearning.ulms.core.exceptions.ULMSSysException;
import com.ulearning.ulms.finance.form.UserAccountDetailForm;
import com.ulearning.ulms.finance.form.UserAccountForm;
import com.ulearning.ulms.finance.model.FuserAccountDetailModel;
import com.ulearning.ulms.finance.model.FuserAccountModel;
import com.ulearning.ulms.util.HibernateDAO;
import com.ulearning.ulms.util.HibernateUtil;

import net.sf.hibernate.HibernateException;
import net.sf.hibernate.MappingException;
import net.sf.hibernate.Session;
import net.sf.hibernate.Transaction;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * �����û��ʻ�DAO�ӿ�ʵ��
 *
 * @author Liz
 * @ date 2005.12.09
 */
public class UserAccountDAOImpl implements UserAccountDAO
{
        /**
         * �����û�ID������ֱ�ӽ����ʻ���Ϣ�����Ϊ0
         *
         * @param userID
         * @param userName
         * @param lastUser ����޸���
         * @return int=0�������ɹ�����1�����ɹ�
         */
        public int instMainAccount(int userID, String userName, String lastUser)
                throws ULMSSysException
        {
                int isInst = 0;

                try
                {
                        if (null == userName)
                        {
                                return isInst;
                        }

                        if (userID < 1)
                        {
                                return isInst;
                        }

                        FuserAccountModel maccount = new FuserAccountModel();
                        Date date = new Date();
                        maccount.setUacotlastUpDate(date);
                        maccount.setUacotLastUser(lastUser);
                        maccount.setUacotStatus(1);
                        maccount.setUacotTotal(0.0);
                        maccount.setUacotUserName(userName);
                        maccount.setUserId(userID);
                        HibernateDAO.add(maccount);
                }
                catch (Exception e)
                {
                        throw new ULMSSysException(
                                "MappingException while Hibernate add:  " + e);
                }

                return isInst;
        }

        /**
         * ��Ӹ����û��ʻ���ϸͬʱ�޸������ʽ���Ϣ��
         *
         * @param userAcotDetailFrm �û��ʻ���ϸFormBean
         * @return ID
         * @throws ULMSSysException
         */
        public int addUserAccount(UserAccountDetailForm userAcotDetailFrm)
                throws ULMSSysException
        {
                int iReturn = 0;
                Session session = null;

                try
                {
                        session = HibernateUtil.getSession();

                        Transaction tx = session.beginTransaction();
                        instDetailAccount(session, userAcotDetailFrm.getDetailModel());
                        addMainAccount(session, makeAccontModel(userAcotDetailFrm),
                                userAcotDetailFrm);
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
                                       FuserAccountDetailModel model) throws HibernateException
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
                                         FuserAccountDetailModel model) throws HibernateException
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
         * @param model             ����ӵ��ʻ�Model
         * @param userAcotDetailFrm ��ϸForm
         */
        private void addMainAccount(Session session, FuserAccountModel model,
                                    UserAccountDetailForm userAcotDetailFrm)
        {
                double total = 0;
                FuserAccountModel currentModel = null;

                try
                {
                        currentModel = getAccount(model.getUserId());

                        if (null == currentModel)
                        { //��û���ʻ�����
                                session.save(model);
                                session.flush();
                        }
                        else
                        { //�Ѿ����ʻ��޸Ľ��
                                //���ʻ���������ӵĽ��ϲ��������µ��ʻ����

                                if (1 == userAcotDetailFrm.getUaDetailInOutType())
                                { //1���� 2��֧
                                        currentModel.setUacotTotal(currentModel.getUacotTotal() +
                                                model.getUacotTotal());
                                        currentModel.setUacotlastUpDate(model.getUacotlastUpDate());
                                }
                                else
                                {
                                        currentModel.setUacotTotal(currentModel.getUacotTotal() -
                                                model.getUacotTotal());
                                        currentModel.setUacotlastUpDate(model.getUacotlastUpDate());
                                }

                                session.clear();
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
         */
        private FuserAccountModel makeAccontModel(UserAccountDetailForm frm)
        {
                FuserAccountModel model = null;
                model = frm.getFuserAccountModel();

                return model;
        }

        /**
         * ��ѯ�Ƿ��е�ǰ�û���������Ϣ
         *
         * @param userId
         * @return model�����ʻ�
         */
        private FuserAccountModel getAccount(int userId)
        {
                FuserAccountModel model = null;
                UserAccountForm listFrm = null;
                List list = null;

                try
                {
                        String hql = " from FuserAccountModel where userId=" + userId;
                        list = getUMainAcotList(hql);

                        if ((null != list) && (0 != list.size()))
                        {
                                listFrm = (UserAccountForm) list.get(0);
                                model = listFrm.getFuserAccountModel();
                        }
                }
                catch (Exception e)
                {
                        throw new ULMSSysException(e);
                }

                return model;
        }

        /**
         * �޸ĸ����ʻ���ϸͬʱ�޸������ʽ���Ϣ
         *
         * @param userAcotFrm �û��ʻ���ϸFormBean
         * @throws ULMSSysException
         */
        public void updateUserAccount(UserAccountDetailForm userAcotFrm)
                throws ULMSSysException
        {
                Session session = null;

                try
                {
                        session = HibernateUtil.getSession();

                        Transaction tx = session.beginTransaction();
                        updateDetailAccount(session, userAcotFrm.getDetailModel());
                        addMainAccount(session, makeAccontModel(userAcotFrm), userAcotFrm);
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

        /**
         * ���ݸ����ʻ���ϸIDɾ����ϸͬʱ�޸�����
         *
         * @param uDetailID ��ϸID
         * @throws ULMSSysException
         */
        public void removeUserAccount(int uDetailID) throws ULMSSysException
        {
        }

        /**
         * ���ݸ�����������ظ����ʻ���Ϣ
         *
         * @param hql
         * @param beginDate
         * @param beginDate
         * @param firstResult
         * @param maxResults
         * @return List OrganAccountDetailForm  �����ʻ�FormBean
         * @throws ULMSSysException
         */
        public List getUADetailFrm(String hql, String beginDate, String endDate,
                                   int firstResult, int maxResults) throws ULMSSysException
        {
                List listFrm = null;

                try
                {
                        UserAccountDetailForm typeFrom = new UserAccountDetailForm();
                        FuserAccountDetailModel typeModel = null;
                        List list = null;
                        System.out.println("hql===============" + hql);

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
                                        typeModel = (FuserAccountDetailModel) list.get(i);
                                        listFrm.add(typeFrom.getUserAccountDetailForm(typeModel));
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
         * ���ݸ�����������ظ�������FromBean
         *
         * @param hql
         * @return List UserAccountForm  ��������FromBean
         * @throws ULMSSysException
         */
        public List getUMainAcotList(String hql) throws ULMSSysException
        {
                List listFrm = null;

                try
                {
                        UserAccountForm typeFrom = new UserAccountForm();
                        FuserAccountModel typeModel = null;
                        List list = null;
                        list = HibernateDAO.find(hql);

                        if ((null != list) && (0 < list.size()))
                        {
                                listFrm = new ArrayList();

                                for (int i = 0; i < list.size(); i++)
                                {
                                        typeModel = (FuserAccountModel) list.get(i);
                                        listFrm.add(typeFrom.getUserAccountForm(typeModel));
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
         * �����û�ID�޸��ʻ����
         *
         * @param frm  �Ѿ��鵽���ʻ�
         * @param sum  Ҫ�۳��Ľ��
         * @param type 1=���� 2=����
         */
        public void updateMainAcot(UserAccountForm frm, double sum, int type)
                throws ULMSSysException
        {
                try
                {
                        FuserAccountModel mod;
                        mod = frm.getFuserAccountModel();

                        if (1 == type)
                        {
                                mod.setUacotTotal(mod.getUacotTotal() - sum);
                        }
                        else if (2 == type)
                        {
                                mod.setUacotTotal(mod.getUacotTotal() + sum);
                        }

                        HibernateDAO.update(mod);
                }
                catch (Exception e)
                {
                        throw new ULMSSysException(e);
                }
        }
}
