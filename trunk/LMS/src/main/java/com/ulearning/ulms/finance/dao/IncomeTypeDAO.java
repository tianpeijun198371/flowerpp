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
 * ������������DAO Interface
 *
 * @author Liz
 * @ date 2005.12.09
 */
public interface IncomeTypeDAO
{
        /**
         * add ��������
         *
         * @param inTypeFrm ���������͵�FormBean
         * @return int
         * @throws ULMSSysException
         */
        public int addIncomeType(IncomeTypeForm inTypeFrm)
                throws ULMSSysException;

        /**
         * Update ��������
         *
         * @param inTypeFrm :�������͵�FormBean
         * @throws ULMSSysException
         */
        public void updateIncomeType(IncomeTypeForm inTypeFrm)
                throws ULMSSysException;

        /**
         * ����IDɾ����������
         *
         * @param incomeID ��������ID
         * @throws ULMSSysException
         */
        public void removeIncomeType(int incomeID) throws ULMSSysException;

        /**
         * ���ݸ��������ȡ����������Ϣ
         *
         * @param sql ��ѯ����
         * @return List:IncomeTypeForm ��������FormBean
         * @throws ULMSSysException
         */
        public List getIncomeFrm(String sql) throws ULMSSysException;
}
