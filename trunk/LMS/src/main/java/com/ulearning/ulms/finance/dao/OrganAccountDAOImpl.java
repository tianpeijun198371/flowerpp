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
 * 机构用户帐户DAO接口  实现
 *
 * @author Liz
 * @ date 2005.12.09
 */
public class OrganAccountDAOImpl implements OrganAccountDAO
{
        /**
         * 添加机构帐户明细同时修改总帐信息
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
         * 添加个人帐户明细信息
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
         * 添加个人帐户明细信息
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
         * 添加或修改个人总帐户信息
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
                        { //还没有帐户建立
                                session.save(model);
                                session.flush();
                        }
                        else
                        { //已经有帐户修改金额
                                //将帐户余额和新添加的金额合并，产生新的帐户余额

                                if (1 == organAcotFrm.getOaDetailInOutType())
                                { //1：收 2：支
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
         * 建立总帐信息
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
         * 查询是否有当前用户的总帐信息
         *
         * @param organId
         * @return model个人帐户
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
         * 修改机构明细同时修改机构总信息
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
         * 公用的查询，其它查询调用它，也可以直接调用它 进行查询
         *
         * @param hql         查询条件
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
         * 根据给入的条件返回机构总帐FromBean
         *
         * @param hql
         * @return List UserAccountForm  机构总帐FromBean
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
         * 根据机构ID修改帐户金额
         *
         * @param frm  已经查到的帐户
         * @param sum  要扣除的金额
         * @param type 1=减少 2=增加
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
