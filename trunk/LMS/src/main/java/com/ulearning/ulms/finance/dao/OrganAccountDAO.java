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
 * �����û��ʻ�DAO�ӿ�
 *
 * @author Liz
 * @ date 2005.12.09
 */
public interface OrganAccountDAO
{
        /**
         * ��ӻ���������ϸ��ͬʱ�޸�������Ϣ��
         *
         * @param organAcotFrm ��ϸFormBean
         * @return int
         * @throws ULMSSysException
         */
        public int inster(OrganAccountDetailForm organAcotFrm)
                throws ULMSSysException;

        /**
         * �޸Ļ���������ϸ��ͬʱ�޸�������Ϣ��
         *
         * @param organAcotFrm ��ϸFormBean
         * @throws ULMSSysException
         */
        public void updateOrganAccount(OrganAccountDetailForm organAcotFrm)
                throws ULMSSysException;

        /**
         * ����IDɾ������������ϸ��ͬʱ�޸�������Ϣ��
         *
         * @param oDetailID ��ϸID
         * @throws ULMSSysException
         */
        public void removeOrganAccount(int oDetailID) throws ULMSSysException;

        /**
         * ���ݸ��������ȡ������ϸ�б�
         *
         * @param hql
         * @return List  OrganAccountDetailForm
         * @throws ULMSSysException
         */
        public List getDAOetailFrm(String hql, String beginDate, String endDate,
                                   int firstResult, int maxResults) throws ULMSSysException;

        /**
         * ���ݸ��������ȡ���������б�
         *
         * @param sql
         * @return List  OrganAccountDetailForm
         * @throws ULMSSysException
         */
        public List getOMainAcotFrm(String sql) throws ULMSSysException;
}
