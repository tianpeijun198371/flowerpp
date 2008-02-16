/**
 * PhysicsResDAO.java.
 * User: liz  Date: 2006-2-16
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.content.dao;

import com.ulearning.ulms.content.model.RPhysicsresModel;
import com.ulearning.ulms.core.exceptions.ULMSException;

import java.util.List;


public interface PhysicsResDAO
{
        /**
         * 添加资源
         *
         * @param mod
         * @return
         * @throws ULMSException
         */
        public int addRes(RPhysicsresModel mod) throws ULMSException;

        /**
         * 更新资源
         *
         * @param mod
         * @throws ULMSException
         */
        public void updateRes(RPhysicsresModel mod) throws ULMSException;

        /**
         * 取资源数据
         *
         * @param hql
         * @return
         * @throws ULMSException
         */
        public List getData(String hql, String startDateTime, String endDateTime,
                            int firstResult, int maxResults) throws ULMSException;

        /**
         * 删除资源数据
         *
         * @param hql
         * @throws ULMSException
         */
        public void removeRes(String hql) throws ULMSException;
}
