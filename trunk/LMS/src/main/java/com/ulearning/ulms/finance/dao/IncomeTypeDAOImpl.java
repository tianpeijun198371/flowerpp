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
 * 经费收入类型DAO Interface的实现
 *
 * @author Liz
 * @ date 2005.12.09
 */
public class IncomeTypeDAOImpl implements IncomeTypeDAO
{
        /**
         * add 收入类型
         *
         * @param inTypeFrm ：收入类型的FormBean
         * @return int
         * @throws ULMSSysException
         */
        public int addIncomeType(IncomeTypeForm inTypeFrm)
                throws ULMSSysException
        {
                return 0;
        }

        /**
         * Update 收入类型
         *
         * @param inTypeFrm :收入类型的FormBean
         * @throws ULMSSysException
         */
        public void updateIncomeType(IncomeTypeForm inTypeFrm)
                throws ULMSSysException
        {
        }

        /**
         * 根据ID删除收入类型
         *
         * @param incomeID 收入类型ID
         * @throws ULMSSysException
         */
        public void removeIncomeType(int incomeID) throws ULMSSysException
        {
        }

        /**
         * 根据给入的条件取收入类型信息
         *
         * @param hql 查询条件
         * @return List:IncomeTypeForm 收入类型FormBean
         * @throws ULMSSysException
         */
        public List getIncomeFrm(String hql) throws ULMSSysException
        {
                List listFrm = null;

                try
                {
                        if (null == hql)
                        {
                                throw new ULMSSysException("请先确定搜索条件!");
                        }

                        IncomeTypeForm typeFrom = new IncomeTypeForm();
                        FincomeTypeModel typeModel = null;
                        List list = null;
                        list = HibernateDAO.find(hql); //Hibernate查询

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
