/**
 * IncomeTypeDAOImpl.java.
 * User: liz  Date: 2005-12-9
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.finance.dao;

import com.ulearning.ulms.core.exceptions.ULMSSysException;
import com.ulearning.ulms.finance.form.IncomeTypeForm;
import com.ulearning.ulms.finance.model.FincomeTypeModel;
import com.ulearning.ulms.util.HibernateDAO;

import java.util.ArrayList;
import java.util.List;


/**
 * ������������DAO Interface��ʵ��
 *
 * @author Liz
 * @ date 2005.12.09
 */
public class IncomeTypeDAOImpl implements IncomeTypeDAO
{
        /**
         * add ��������
         *
         * @param inTypeFrm ���������͵�FormBean
         * @return int
         * @throws ULMSSysException
         */
        public int addIncomeType(IncomeTypeForm inTypeFrm)
                throws ULMSSysException
        {
                return 0;
        }

        /**
         * Update ��������
         *
         * @param inTypeFrm :�������͵�FormBean
         * @throws ULMSSysException
         */
        public void updateIncomeType(IncomeTypeForm inTypeFrm)
                throws ULMSSysException
        {
        }

        /**
         * ����IDɾ����������
         *
         * @param incomeID ��������ID
         * @throws ULMSSysException
         */
        public void removeIncomeType(int incomeID) throws ULMSSysException
        {
        }

        /**
         * ���ݸ��������ȡ����������Ϣ
         *
         * @param hql ��ѯ����
         * @return List:IncomeTypeForm ��������FormBean
         * @throws ULMSSysException
         */
        public List getIncomeFrm(String hql) throws ULMSSysException
        {
                List listFrm = null;

                try
                {
                        if (null == hql)
                        {
                                throw new ULMSSysException("����ȷ����������!");
                        }

                        IncomeTypeForm typeFrom = new IncomeTypeForm();
                        FincomeTypeModel typeModel = null;
                        List list = null;
                        list = HibernateDAO.find(hql); //Hibernate��ѯ

                        if ((null != list) && (0 < list.size()))
                        {
                                listFrm = new ArrayList();

                                for (int i = 0; i < list.size(); i++)
                                {
                                        typeModel = (FincomeTypeModel) list.get(i);
                                        listFrm.add(typeFrom.getIncomeTypeForm(typeModel));
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
