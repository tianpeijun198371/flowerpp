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
         * �����Դ
         *
         * @param mod
         * @return
         * @throws ULMSException
         */
        public int addRes(RPhysicsresModel mod) throws ULMSException;

        /**
         * ������Դ
         *
         * @param mod
         * @throws ULMSException
         */
        public void updateRes(RPhysicsresModel mod) throws ULMSException;

        /**
         * ȡ��Դ����
         *
         * @param hql
         * @return
         * @throws ULMSException
         */
        public List getData(String hql, String startDateTime, String endDateTime,
                            int firstResult, int maxResults) throws ULMSException;

        /**
         * ɾ����Դ����
         *
         * @param hql
         * @throws ULMSException
         */
        public void removeRes(String hql) throws ULMSException;
}
