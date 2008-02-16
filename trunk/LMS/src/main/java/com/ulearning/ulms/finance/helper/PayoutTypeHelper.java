/**
 * PayoutTypeHelper.java.
 * User: liz  Date: 2005-12-9
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.finance.helper;

import com.ulearning.ulms.core.exceptions.ULMSSysException;
import com.ulearning.ulms.finance.dao.PayoutTypeDAOFactory;
import com.ulearning.ulms.finance.dao.PayoutTypeDAOImpl;
import com.ulearning.ulms.finance.form.PayoutTypeForm;

import java.util.List;


/**
 * ����֧�����͵ĸ����࣬�û��ӿ�
 *
 * @author Liz
 * @ date 2005.12.09
 */
public class PayoutTypeHelper
{
        /**
         * ����֧������IDȡ֧��������Ϣ
         *
         * @param payoutTypeID ֧������ID
         * @return List: PayoutTypeForm ֧������FormBean
         * @throws com.ulearning.ulms.core.exceptions.ULMSSysException
         *
         */
        public List getPayoutOfID(int payoutTypeID) throws ULMSSysException
        {
                List list = getPayoutFrm(payoutTypeID, 0);

                return list;
        }

        /**
         * ����֧������IDȡ֧����������
         *
         * @param payoutTypeID ֧������ID
         * @return String:  ֧����������
         * @throws com.ulearning.ulms.core.exceptions.ULMSSysException
         *
         */
        public String getPayoutNameOfID(int payoutTypeID) throws ULMSSysException
        {
                String sName = "��"; //ȫ�ǿո�HTML��ʾ��
                List list = getPayoutFrm(payoutTypeID, 0);

                if ((null != list) && (0 != list.size()))
                {
                        sName = ((PayoutTypeForm) list.get(0)).getPayoutName();
                }

                return sName;
        }

        /**
         * ����֧�����ͱ��ȡ����֧�����ͣ�
         *
         * @param PayoutType ֧�����ͱ�� 1������  2������
         * @return List: payoutTypeFrm ֧������FormBean
         * @throws ULMSSysException
         */
        public List getAllPayoutType(int PayoutType) throws ULMSSysException
        {
                List list = getPayoutFrm(0, PayoutType);

                return list;
        }

        /**
         * ����֧��ID�����ͱ��ȡ֧��������Ϣ
         *
         * @param payoutID   ֧��ID
         * @param payoutType ֧�����ͱ�� 1������  2������  0:���е�
         * @return payoutTypeFrm ֧������FormBean
         */
        public List getPayoutFrm(int payoutID, int payoutType)
        {
                List list = null;
                StringBuffer hql = new StringBuffer();
                hql.append(" from FpayoutTypeModel where 1>0");

                if (0 != payoutID) //��ID����
                {
                        hql.append(" and payoutId = " + payoutID);
                }

                if (0 != payoutType) //�����ͱ������
                {
                        hql.append(" and payoutType = " + payoutType);
                }

                PayoutTypeDAOImpl dao = (PayoutTypeDAOImpl) PayoutTypeDAOFactory.getDAO();

                list = dao.getPayoutFrm(hql.toString());

                return list;
        }
}
