/**
 * PayoutTypeDAO.java.
 * User: liz  Date: 2005-12-9
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.finance.dao;

import com.ulearning.ulms.core.exceptions.ULMSSysException;
import com.ulearning.ulms.finance.form.PayoutTypeForm;

import java.util.List;


/**
 * 经费支出类型DAO Interface
 *
 * @author Liz
 * @ date 2005.12.09
 */
public interface PayoutTypeDAO
{
        /**
         * add 支出类型
         *
         * @param payoutFrm ：支出类型的FormBean
         * @return int
         * @throws ULMSSysException
         */
        public int addPayoutType(PayoutTypeForm payoutFrm)
                throws ULMSSysException;

        /**
         * Update 支出类型
         *
         * @param payoutTypeFrm :支出类型的FormBean
         * @throws ULMSSysException
         */
        public void updatePayoutType(PayoutTypeForm payoutTypeFrm)
                throws ULMSSysException;

        /**
         * 根据ID删除支出类型
         *
         * @param payoutTypeID 支出类型ID
         * @throws ULMSSysException
         */
        public void removePayouttype(int payoutTypeID) throws ULMSSysException;

        /**
         * 根据给入的条件返回支出类型信息Frm
         *
         * @param sql 条件
         * @return List: PayoutTypeForm 支出类型FormBean
         * @throws ULMSSysException
         */
        public List getPayoutFrm(String sql) throws ULMSSysException;
}
