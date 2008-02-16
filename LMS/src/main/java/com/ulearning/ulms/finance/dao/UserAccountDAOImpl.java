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
 * 个人用户帐户DAO接口实现
 *
 * @author Liz
 * @ date 2005.12.09
 */
public class UserAccountDAOImpl implements UserAccountDAO
{
        /**
         * 根据用户ID和名称直接建立帐户信息，金额为0
         *
         * @param userID
         * @param userName
         * @param lastUser 最后修改人
         * @return int=0建立不成功，＝1建立成功
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
         * 添加个人用户帐户明细同时修改总帐资金信息。
         *
         * @param userAcotDetailFrm 用户帐户明细FormBean
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
         * 添加个人帐户明细信息
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
         * 添加个人帐户明细信息
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
         * 添加或修改个人总帐户信息
         *
         * @param session
         * @param model             新添加的帐户Model
         * @param userAcotDetailFrm 明细Form
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
                        { //还没有帐户建立
                                session.save(model);
                                session.flush();
                        }
                        else
                        { //已经有帐户修改金额
                                //将帐户余额和新添加的金额合并，产生新的帐户余额

                                if (1 == userAcotDetailFrm.getUaDetailInOutType())
                                { //1：收 2：支
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
         * 建立总帐信息
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
         * 查询是否有当前用户的总帐信息
         *
         * @param userId
         * @return model个人帐户
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
         * 修改个人帐户明细同时修改总帐资金信息
         *
         * @param userAcotFrm 用户帐户明细FormBean
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
         * 根据个人帐户明细ID删除明细同时修改总帐
         *
         * @param uDetailID 明细ID
         * @throws ULMSSysException
         */
        public void removeUserAccount(int uDetailID) throws ULMSSysException
        {
        }

        /**
         * 根据给入的条件返回个人帐户信息
         *
         * @param hql
         * @param beginDate
         * @param beginDate
         * @param firstResult
         * @param maxResults
         * @return List OrganAccountDetailForm  个人帐户FormBean
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
                        { //有日期带翻页
                                list = HibernateDAO.find(hql, beginDate, endDate, firstResult,
                                        maxResults); //Hibernate查询
                        }
                        else
                        {
                                if (((null != beginDate) && !beginDate.equals("")) &&
                                        ((null != endDate) && !endDate.equals("")))
                                { //有日期，没翻页
                                        list = HibernateDAO.find(hql, beginDate, endDate, -1, -1); //Hibernate查询
                                }
                                else if (((-1 != firstResult) && (-1 != maxResults)))
                                { //没日期，有翻页
                                        list = HibernateDAO.find(hql, firstResult, maxResults);
                                }
                                else
                                {
                                        //没日期,没翻页
                                        list = HibernateDAO.find(hql); //Hibernate查询
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
         * 根据给入的条件返回个人总帐FromBean
         *
         * @param hql
         * @return List UserAccountForm  个人总帐FromBean
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
         * 根据用户ID修改帐户金额
         *
         * @param frm  已经查到的帐户
         * @param sum  要扣除的金额
         * @param type 1=减少 2=增加
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
