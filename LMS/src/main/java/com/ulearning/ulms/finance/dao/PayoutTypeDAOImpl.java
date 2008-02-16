/**
 * PayoutTypeDAOImpl.java.
 * User: liz  Date: 2005-12-9
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.finance.dao;

import com.ulearning.ulms.core.exceptions.ULMSSysException;
import com.ulearning.ulms.finance.form.PayoutTypeForm;
import com.ulearning.ulms.finance.model.FpayoutTypeModel;
import com.ulearning.ulms.util.HibernateDAO;

import java.util.ArrayList;
import java.util.List;


/**
 * 经费支出类型DAO Interface 的实现
 *
 * @author Liz
 * @ date 2005.12.09
 */
public class PayoutTypeDAOImpl implements PayoutTypeDAO
{
        /**
         * add 支出类型
         *
         * @param payoutFrm ：支出类型的FormBean
         * @return int
         * @throws ULMSSysException
         */
        public int addPayoutType(PayoutTypeForm payoutFrm)
                throws ULMSSysException
        {
                return 0;
        }

        /**
         * Update 支出类型
         *
         * @param payoutTypeFrm :支出类型的FormBean
         * @throws ULMSSysException
         */
        public void updatePayoutType(PayoutTypeForm payoutTypeFrm)
                throws ULMSSysException
        {
        }

        /**
         * 根据ID删除支出类型
         *
         * @param payoutTypeID 支出类型ID
         * @throws ULMSSysException
         */
        public void removePayouttype(int payoutTypeID) throws ULMSSysException
        {
        }

        /**
         * 根据给入的条件返回支出类型信息Frm
         *
         * @param hql 条件
         * @return List: PayoutTypeForm 支出类型FormBean
         * @throws ULMSSysException
         */
        public List getPayoutFrm(String hql) throws ULMSSysException
        {
                List listFrm = null;

                try
                {
                        if (null == hql)
                        {
                                throw new ULMSSysException("请先确定搜索条件!");
                        }

                        PayoutTypeForm typeFrom = new PayoutTypeForm();
                        FpayoutTypeModel typeModel = null;
                        List list = null;
                        list = HibernateDAO.find(hql); //Hibernate查询

                        if ((null != list) && (0 < list.size()))
                        {
                                listFrm = new ArrayList();

                                for (int i = 0; i < list.size(); i++)
                                {
                                        typeModel = (FpayoutTypeModel) list.get(i);
                                        listFrm.add(typeFrom.getPayoutTypeForm(typeModel));
                                }
                        }
                }
                catch (Exception e)
                {
                        throw new ULMSSysException(e.toString());
                }

                return listFrm;
        }
}
