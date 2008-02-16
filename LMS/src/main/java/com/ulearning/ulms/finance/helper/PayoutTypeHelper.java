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
 * 经费支出类型的辅助类，用户接口
 *
 * @author Liz
 * @ date 2005.12.09
 */
public class PayoutTypeHelper
{
        /**
         * 根据支出类型ID取支出类型信息
         *
         * @param payoutTypeID 支出类型ID
         * @return List: PayoutTypeForm 支出类型FormBean
         * @throws com.ulearning.ulms.core.exceptions.ULMSSysException
         *
         */
        public List getPayoutOfID(int payoutTypeID) throws ULMSSysException
        {
                List list = getPayoutFrm(payoutTypeID, 0);

                return list;
        }

        /**
         * 根据支出类型ID取支出类型名称
         *
         * @param payoutTypeID 支出类型ID
         * @return String:  支出类型名称
         * @throws com.ulearning.ulms.core.exceptions.ULMSSysException
         *
         */
        public String getPayoutNameOfID(int payoutTypeID) throws ULMSSysException
        {
                String sName = "　"; //全角空格HTML显示用
                List list = getPayoutFrm(payoutTypeID, 0);

                if ((null != list) && (0 != list.size()))
                {
                        sName = ((PayoutTypeForm) list.get(0)).getPayoutName();
                }

                return sName;
        }

        /**
         * 根据支出类型标记取所有支出类型，
         *
         * @param PayoutType 支出类型标记 1：机构  2：个人
         * @return List: payoutTypeFrm 支出类型FormBean
         * @throws ULMSSysException
         */
        public List getAllPayoutType(int PayoutType) throws ULMSSysException
        {
                List list = getPayoutFrm(0, PayoutType);

                return list;
        }

        /**
         * 根据支出ID和类型标记取支出类型信息
         *
         * @param payoutID   支出ID
         * @param payoutType 支出类型标记 1：机构  2：个人  0:所有的
         * @return payoutTypeFrm 支出类型FormBean
         */
        public List getPayoutFrm(int payoutID, int payoutType)
        {
                List list = null;
                StringBuffer hql = new StringBuffer();
                hql.append(" from FpayoutTypeModel where 1>0");

                if (0 != payoutID) //按ID搜索
                {
                        hql.append(" and payoutId = " + payoutID);
                }

                if (0 != payoutType) //按类型标记搜索
                {
                        hql.append(" and payoutType = " + payoutType);
                }

                PayoutTypeDAOImpl dao = (PayoutTypeDAOImpl) PayoutTypeDAOFactory.getDAO();

                list = dao.getPayoutFrm(hql.toString());

                return list;
        }
}
