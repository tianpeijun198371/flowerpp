/**
 * UserAccountDAO.java.
 * User: liz  Date: 2005-12-9
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.finance.dao;

import com.ulearning.ulms.core.exceptions.ULMSSysException;
import com.ulearning.ulms.finance.form.UserAccountDetailForm;

import java.util.Date;
import java.util.List;


/**
 * 个人用户帐户DAO接口
 *
 * @author Liz
 * @ date 2005.12.09
 */
public interface UserAccountDAO
{
        /**
         * 添加个人用户帐户明细同时修改总帐资金信息。
         *
         * @param userAcotDetailFrm 用户帐户明细FormBean
         * @return ID
         * @throws ULMSSysException
         */
        public int addUserAccount(UserAccountDetailForm userAcotDetailFrm)
                throws ULMSSysException;

        /**
         * 修改个人帐户明细同时修改总帐资金信息
         *
         * @param userAcotFrm 用户帐户明细FormBean
         * @throws ULMSSysException
         */
        public void updateUserAccount(UserAccountDetailForm userAcotFrm)
                throws ULMSSysException;

        /**
         * 根据个人帐户明细ID删除明细同时修改总帐
         *
         * @param uDetailID 明细ID
         * @throws ULMSSysException
         */
        public void removeUserAccount(int uDetailID) throws ULMSSysException;

        /**
         * 根据给入的条件返回个人帐户信息
         *
         * @param hql
         * @return List OrganAccountDetailForm  个人帐户FormBean
         * @throws ULMSSysException
         */
        public List getUADetailFrm(String hql, String beginDate, String endDate,
                                   int firstResult, int maxResults) throws ULMSSysException;

        /**
         * 根据给入的条件返回个人总帐FromBean
         *
         * @param sql
         * @return List UserAccountForm  个人总帐FromBean
         * @throws ULMSSysException
         */
        public List getUMainAcotList(String sql) throws ULMSSysException;
}
