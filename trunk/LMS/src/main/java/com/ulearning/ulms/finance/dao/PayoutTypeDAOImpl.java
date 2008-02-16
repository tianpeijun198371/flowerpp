/**
 * PayoutTypeDAOImpl.java.
 * User: liz  Date: 2005-12-9
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.finance.dao;

import com.ulearning.ulms.core.exceptions.ULMSSysException;
import com.ulearning.ulms.finance.form.PayoutTypeForm;
import com.ulearning.ulms.finance.model.FpayoutTypeModel;
import com.ulearning.ulms.util.HibernateDAO;

import java.util.ArrayList;
import java.util.List;


/**
 * ����֧������DAO Interface ��ʵ��
 *
 * @author Liz
 * @ date 2005.12.09
 */
public class PayoutTypeDAOImpl implements PayoutTypeDAO
{
        /**
         * add ֧������
         *
         * @param payoutFrm ��֧�����͵�FormBean
         * @return int
         * @throws ULMSSysException
         */
        public int addPayoutType(PayoutTypeForm payoutFrm)
                throws ULMSSysException
        {
                return 0;
        }

        /**
         * Update ֧������
         *
         * @param payoutTypeFrm :֧�����͵�FormBean
         * @throws ULMSSysException
         */
        public void updatePayoutType(PayoutTypeForm payoutTypeFrm)
                throws ULMSSysException
        {
        }

        /**
         * ����IDɾ��֧������
         *
         * @param payoutTypeID ֧������ID
         * @throws ULMSSysException
         */
        public void removePayouttype(int payoutTypeID) throws ULMSSysException
        {
        }

        /**
         * ���ݸ������������֧��������ϢFrm
         *
         * @param hql ����
         * @return List: PayoutTypeForm ֧������FormBean
         * @throws ULMSSysException
         */
        public List getPayoutFrm(String hql) throws ULMSSysException
        {
                List listFrm = null;

                try
                {
                        if (null == hql)
                        {
                                throw new ULMSSysException("����ȷ����������!");
                        }

                        PayoutTypeForm typeFrom = new PayoutTypeForm();
                        FpayoutTypeModel typeModel = null;
                        List list = null;
                        list = HibernateDAO.find(hql); //Hibernate��ѯ

                        if ((null != list) && (0 < list.size()))
                        {
                                listFrm = new ArrayList();

                                for (int i = 0; i < list.size(); i++)
                                {
                                        typeModel = (FpayoutTypeModel) list.get(i);
                                        listFrm.add(typeFrom.getPayoutTypeForm(typeModel));
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
