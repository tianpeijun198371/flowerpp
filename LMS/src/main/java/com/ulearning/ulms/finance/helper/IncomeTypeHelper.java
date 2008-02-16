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
 * 经费收入类型的辅助类,用户接口
 *
 * @author Liz
 * @ date 2005.12.09
 */
public class IncomeTypeHelper
{
        /**
         * 根据收入类型ID取收入类型信息
         *
         * @param incomeID 收入类型ID
         * @return List:IncomeTypeForm收入类型FormBean
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
         * 根据收入类型ID取收入类型名称
         *
         * @param incomeID 收入类型ID
         * @return String:收入类型名称
         * @throws com.ulearning.ulms.core.exceptions.ULMSSysException
         *
         */
        public String getIncomenameOfID(int incomeID) throws ULMSSysException
        {
                List list = null;
                list = getIncomeFrm(incomeID, 0);

                String sName = "　"; //全角空格HTML显示用

                if ((null != list) && (0 != list.size()))
                {
                        sName = ((IncomeTypeForm) list.get(0)).getIncomeName();
                }

                return sName;
        }

        /**
         * 根据收入类型标记取所有收入类型，
         *
         * @param incomeType 收入类型标记 1：机构  2：个人
         * @return List:IncomeTypeForm收入类型FormBean
         * @throws ULMSSysException
         */
        public List getAllIncomeType(int incomeType) throws ULMSSysException
        {
                List list = null;
                list = getIncomeFrm(0, incomeType);

                return list;
        }

        /**
         * 根据的收入类型ID和收入类型标记取收入类型
         *
         * @param incomeID
         * @param incomeType 1：机构 2：个人
         * @return
         */
        public List getIncomeFrm(int incomeID, int incomeType)
        {
                List list = null;
                StringBuffer hql = new StringBuffer();
                hql.append(" from FincomeTypeModel where 1>0");

                if (0 != incomeID) //按ID搜索
                {
                        hql.append(" and incomeId = ");
                        hql.append(incomeID);
                }

                if (0 != incomeType) //按类型标记搜索
                {
                        hql.append(" and incomeType = ");
                        hql.append(incomeType);
                }

                IncomeTypeDAOImpl dao = (IncomeTypeDAOImpl) IncomeTypeDAOFactory.getDAO();

                list = dao.getIncomeFrm(hql.toString());

                return list;
        }
}
