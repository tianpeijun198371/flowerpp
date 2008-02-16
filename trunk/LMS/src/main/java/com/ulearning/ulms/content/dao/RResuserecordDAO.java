/**
 * RResuserecordDAO.java.
 * User: liz  Date: 2006-2-20
 * 资源使用记录的DAO接口
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.content.dao;

import com.ulearning.ulms.content.model.RResuserecordModel;
import com.ulearning.ulms.core.exceptions.ULMSException;

import java.util.Date;
import java.util.List;


public interface RResuserecordDAO
{
        /**
         * 添加资源使用记录
         *
         * @param mod RResuserecordModel
         * @return 1成功　　0：失败
         * @throws ULMSException
         */
        public int addResUsed(RResuserecordModel mod) throws ULMSException;

        /**
         * 更新资源使用记录
         *
         * @param mod RResuserecordModel
         * @throws ULMSException
         */
        public void updateResUsed(RResuserecordModel mod) throws ULMSException;

        /**
         * 删除资源记录，只作删除标记
         *
         * @param resuseId 资源使用记录ID
         * @throws ULMSException
         */
        public void removeResUsed(int resuseId) throws ULMSException;

        /**
         * 取符合条件的资源使用记录
         *
         * @param hql           查询SQL
         * @param startDateTime 如有日期　添的查询的开始
         * @param endDateTime   如有日期　添的查询的结束
         * @param firstResult   翻页使用
         * @param maxResults    翻页使用
         * @return
         * @throws ULMSException
         */
        public List getResUsedData(String hql, Date startDateTime,
                                   Date endDateTime, int firstResult, int maxResults)
                throws ULMSException;
}
