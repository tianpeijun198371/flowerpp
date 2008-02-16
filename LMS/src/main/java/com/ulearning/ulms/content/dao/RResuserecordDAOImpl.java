/**
 * RResuserecordDAOImpl.java.
 * User: liz  Date: 2006-2-20
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.content.dao;

import com.ulearning.ulms.content.form.RResuserecordForm;
import com.ulearning.ulms.content.model.RResuserecordModel;
import com.ulearning.ulms.core.exceptions.ULMSException;
import com.ulearning.ulms.util.HibernateDAO;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;


public class RResuserecordDAOImpl implements RResuserecordDAO
{
        /**
         * 添加资源使用记录
         *
         * @param mod RResuserecordModel
         * @return 1成功　　0：失败
         * @throws com.ulearning.ulms.core.exceptions.ULMSException
         *
         */
        public int addResUsed(RResuserecordModel mod) throws ULMSException
        {
                int result = 0;

                HibernateDAO.add(mod);
                result = 1;

                return result;
        }

        /**
         * 更新资源使用记录
         *
         * @param mod RResuserecordModel
         * @throws com.ulearning.ulms.core.exceptions.ULMSException
         *
         */
        public void updateResUsed(RResuserecordModel mod) throws ULMSException
        {
                HibernateDAO.update(mod);
        }

        /**
         * 删除资源记录，只作删除标记
         *
         * @param resuseId 资源使用记录ID
         * @throws com.ulearning.ulms.core.exceptions.ULMSException
         *
         */
        public void removeResUsed(int resuseId) throws ULMSException
        {
                StringBuffer hql = new StringBuffer();
                List list = null;
                hql.append(" from RResuserecordModel where resuseid=");
                hql.append(resuseId);
                list = getResUsedData(hql.toString(), null, null, -1, -1);

                if ((null != list) && (1 <= list.size()))
                {
                        RResuserecordModel mod = ((RResuserecordForm) list.get(0)).makeModel();
                        mod.setUserdel(1);
                        updateResUsed(mod);
                }
        }

        /**
         * 取符合条件的资源使用记录
         *
         * @param hql           查询SQL
         * @param startDateTime 如有日期　添的查询的开始
         * @param endDateTime   如有日期　添的查询的结束
         * @param firstResult   翻页使用
         * @param maxResults    翻页使用
         * @return
         * @throws com.ulearning.ulms.core.exceptions.ULMSException
         *
         */
        public List getResUsedData(String hql, Date startDateTime,
                                   Date endDateTime, int firstResult, int maxResults)
                throws ULMSException
        {
                List list = null;
                List resultList = null;

                list = HibernateDAO.find(hql, startDateTime, endDateTime, firstResult,
                        maxResults);

                RResuserecordModel mod = null;

                if (null != list)
                {
                        resultList = new ArrayList();

                        for (Iterator iter = list.iterator(); iter.hasNext();)
                        {
                                mod = (RResuserecordModel) iter.next();
                                resultList.add(new RResuserecordForm(mod));
                        }
                }

                return resultList;
        }
}
