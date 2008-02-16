/**
 * IncomeTypeHelper.java.
 * User: liz  Date: 2005-12-9
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.finance.helper;

import com.ulearning.ulms.core.exceptions.ULMSSysException;
import com.ulearning.ulms.finance.dao.IncomeTypeDAOFactory;
import com.ulearning.ulms.finance.dao.IncomeTypeDAOImpl;
import com.ulearning.ulms.finance.form.IncomeTypeForm;

import java.util.List;


/**
 * �����������͵ĸ�����,�û��ӿ�
 *
 * @author Liz
 * @ date 2005.12.09
 */
public class IncomeTypeHelper
{
        /**
         * ������������IDȡ����������Ϣ
         *
         * @param incomeID ��������ID
         * @return List:IncomeTypeForm��������FormBean
         * @throws com.ulearning.ulms.core.exceptions.ULMSSysException
         *
         */
        public List getIncomeOfID(int incomeID) throws ULMSSysException
        {
                List list = null;
                list = getIncomeFrm(incomeID, 0);

                return list;
        }

        /**
         * ������������IDȡ������������
         *
         * @param incomeID ��������ID
         * @return String:������������
         * @throws com.ulearning.ulms.core.exceptions.ULMSSysException
         *
         */
        public String getIncomenameOfID(int incomeID) throws ULMSSysException
        {
                List list = null;
                list = getIncomeFrm(incomeID, 0);

                String sName = "��"; //ȫ�ǿո�HTML��ʾ��

                if ((null != list) && (0 != list.size()))
                {
                        sName = ((IncomeTypeForm) list.get(0)).getIncomeName();
                }

                return sName;
        }

        /**
         * �����������ͱ��ȡ�����������ͣ�
         *
         * @param incomeType �������ͱ�� 1������  2������
         * @return List:IncomeTypeForm��������FormBean
         * @throws ULMSSysException
         */
        public List getAllIncomeType(int incomeType) throws ULMSSysException
        {
                List list = null;
                list = getIncomeFrm(0, incomeType);

                return list;
        }

        /**
         * ���ݵ���������ID���������ͱ��ȡ��������
         *
         * @param incomeID
         * @param incomeType 1������ 2������
         * @return
         */
        public List getIncomeFrm(int incomeID, int incomeType)
        {
                List list = null;
                StringBuffer hql = new StringBuffer();
                hql.append(" from FincomeTypeModel where 1>0");

                if (0 != incomeID) //��ID����
                {
                        hql.append(" and incomeId = ");
                        hql.append(incomeID);
                }

                if (0 != incomeType) //�����ͱ������
                {
                        hql.append(" and incomeType = ");
                        hql.append(incomeType);
                }

                IncomeTypeDAOImpl dao = (IncomeTypeDAOImpl) IncomeTypeDAOFactory.getDAO();

                list = dao.getIncomeFrm(hql.toString());

                return list;
        }
}
