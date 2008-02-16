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
 * ����֧������DAO Interface
 *
 * @author Liz
 * @ date 2005.12.09
 */
public interface PayoutTypeDAO
{
        /**
         * add ֧������
         *
         * @param payoutFrm ��֧�����͵�FormBean
         * @return int
         * @throws ULMSSysException
         */
        public int addPayoutType(PayoutTypeForm payoutFrm)
                throws ULMSSysException;

        /**
         * Update ֧������
         *
         * @param payoutTypeFrm :֧�����͵�FormBean
         * @throws ULMSSysException
         */
        public void updatePayoutType(PayoutTypeForm payoutTypeFrm)
                throws ULMSSysException;

        /**
         * ����IDɾ��֧������
         *
         * @param payoutTypeID ֧������ID
         * @throws ULMSSysException
         */
        public void removePayouttype(int payoutTypeID) throws ULMSSysException;

        /**
         * ���ݸ������������֧��������ϢFrm
         *
         * @param sql ����
         * @return List: PayoutTypeForm ֧������FormBean
         * @throws ULMSSysException
         */
        public List getPayoutFrm(String sql) throws ULMSSysException;
}
