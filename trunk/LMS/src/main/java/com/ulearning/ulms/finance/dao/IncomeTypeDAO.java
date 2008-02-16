/**
 * IncomeTypeDAO.java.
 * User: liz  Date: 2005-12-9
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.finance.dao;

import com.ulearning.ulms.core.exceptions.ULMSSysException;
import com.ulearning.ulms.finance.form.IncomeTypeForm;

import java.util.List;


/**
 * 经费收入类型DAO Interface
 *
 * @author Liz
 * @ date 2005.12.09
 */
public interface IncomeTypeDAO
{
        /**
         * add 收入类型
         *
         * @param inTypeFrm ：收入类型的FormBean
         * @return int
         * @throws ULMSSysException
         */
        public int addIncomeType(IncomeTypeForm inTypeFrm)
                throws ULMSSysException;

        /**
         * Update 收入类型
         *
         * @param inTypeFrm :收入类型的FormBean
         * @throws ULMSSysException
         */
        public void updateIncomeType(IncomeTypeForm inTypeFrm)
                throws ULMSSysException;

        /**
         * 根据ID删除收入类型
         *
         * @param incomeID 收入类型ID
         * @throws ULMSSysException
         */
        public void removeIncomeType(int incomeID) throws ULMSSysException;

        /**
         * 根据给入的条件取收入类型信息
         *
         * @param sql 查询条件
         * @return List:IncomeTypeForm 收入类型FormBean
         * @throws ULMSSysException
         */
        public List getIncomeFrm(String sql) throws ULMSSysException;
}
