/**
 * OrganAccountDAO.java.
 * User: liz  Date: 2005-12-12
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.finance.dao;

import com.ulearning.ulms.core.exceptions.ULMSSysException;
import com.ulearning.ulms.finance.form.OrganAccountDetailForm;

import java.util.List;


/**
 * 机构用户帐户DAO接口
 *
 * @author Liz
 * @ date 2005.12.09
 */
public interface OrganAccountDAO
{
        /**
         * 添加机构费用明细，同时修改总帐信息。
         *
         * @param organAcotFrm 明细FormBean
         * @return int
         * @throws ULMSSysException
         */
        public int inster(OrganAccountDetailForm organAcotFrm)
                throws ULMSSysException;

        /**
         * 修改机构费用明细，同时修改总帐信息。
         *
         * @param organAcotFrm 明细FormBean
         * @throws ULMSSysException
         */
        public void updateOrganAccount(OrganAccountDetailForm organAcotFrm)
                throws ULMSSysException;

        /**
         * 根据ID删除机构费用明细，同时修改总帐信息。
         *
         * @param oDetailID 明细ID
         * @throws ULMSSysException
         */
        public void removeOrganAccount(int oDetailID) throws ULMSSysException;

        /**
         * 根据给入的条件取机构明细列表
         *
         * @param hql
         * @return List  OrganAccountDetailForm
         * @throws ULMSSysException
         */
        public List getDAOetailFrm(String hql, String beginDate, String endDate,
                                   int firstResult, int maxResults) throws ULMSSysException;

        /**
         * 根据给入的条件取机构总帐列表
         *
         * @param sql
         * @return List  OrganAccountDetailForm
         * @throws ULMSSysException
         */
        public List getOMainAcotFrm(String sql) throws ULMSSysException;
}
