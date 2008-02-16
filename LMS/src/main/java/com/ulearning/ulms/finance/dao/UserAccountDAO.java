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
 * �����û��ʻ�DAO�ӿ�
 *
 * @author Liz
 * @ date 2005.12.09
 */
public interface UserAccountDAO
{
        /**
         * ��Ӹ����û��ʻ���ϸͬʱ�޸������ʽ���Ϣ��
         *
         * @param userAcotDetailFrm �û��ʻ���ϸFormBean
         * @return ID
         * @throws ULMSSysException
         */
        public int addUserAccount(UserAccountDetailForm userAcotDetailFrm)
                throws ULMSSysException;

        /**
         * �޸ĸ����ʻ���ϸͬʱ�޸������ʽ���Ϣ
         *
         * @param userAcotFrm �û��ʻ���ϸFormBean
         * @throws ULMSSysException
         */
        public void updateUserAccount(UserAccountDetailForm userAcotFrm)
                throws ULMSSysException;

        /**
         * ���ݸ����ʻ���ϸIDɾ����ϸͬʱ�޸�����
         *
         * @param uDetailID ��ϸID
         * @throws ULMSSysException
         */
        public void removeUserAccount(int uDetailID) throws ULMSSysException;

        /**
         * ���ݸ�����������ظ����ʻ���Ϣ
         *
         * @param hql
         * @return List OrganAccountDetailForm  �����ʻ�FormBean
         * @throws ULMSSysException
         */
        public List getUADetailFrm(String hql, String beginDate, String endDate,
                                   int firstResult, int maxResults) throws ULMSSysException;

        /**
         * ���ݸ�����������ظ�������FromBean
         *
         * @param sql
         * @return List UserAccountForm  ��������FromBean
         * @throws ULMSSysException
         */
        public List getUMainAcotList(String sql) throws ULMSSysException;
}
