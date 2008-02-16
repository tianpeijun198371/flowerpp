/**
 * FluxManageDAO.java.
 * User: fengch  Date: 2004-6-23
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.admin.fluxManage.dao;

import com.ulearning.ulms.admin.fluxManage.exceptions.FluxManageSysException;
import com.ulearning.ulms.admin.fluxManage.model.FluxModel;

import java.util.Date;
import java.util.List;


public interface FluxManageDAO
{
        /**
         * ����������¼.<br>
         *
         * @param fm
         * @throws FluxManageSysException
         */
        public void insert(FluxModel fm) throws FluxManageSysException;

        /**
         * ���귵��������¼�б�.<br>
         *
         * @param aspID
         * @param startDate
         * @param endDate
         * @return
         * @throws FluxManageSysException
         */
        public List getByYear(int aspID, Date startDate, Date endDate)
                throws FluxManageSysException;

        /**
         * ���·���������¼�б�.<br>
         *
         * @param aspID
         * @param startDate
         * @param endDate
         * @return
         * @throws FluxManageSysException
         */
        public List getByMonth(int aspID, Date startDate, Date endDate)
                throws FluxManageSysException;

        /**
         * �����ڷ���������¼�б�.<br>
         *
         * @param aspID
         * @param startDate
         * @param endDate
         * @return
         * @throws FluxManageSysException
         */
        public List getByWeek(int aspID, Date startDate, Date endDate)
                throws FluxManageSysException;

        /**
         * ���췵��������¼�б�.<br>
         *
         * @param aspID
         * @param startDate
         * @param endDate
         * @return
         * @throws FluxManageSysException
         */
        public List getByTheDay(int aspID, Date startDate, Date endDate)
                throws FluxManageSysException;

        /**
         * ��ÿ�µ��췵��������¼�б�.<br>
         *
         * @param aspID
         * @param startDate
         * @param endDate
         * @return
         * @throws FluxManageSysException
         */
        public List getByDay(int aspID, Date startDate, Date endDate)
                throws FluxManageSysException;

        /**
         * ��Сʱ����������¼�б�.<br>
         *
         * @param aspID
         * @param startDate
         * @param endDate
         * @return
         * @throws FluxManageSysException
         */
        public List getByHour(int aspID, Date startDate, Date endDate)
                throws FluxManageSysException;

        /**
         * ���û�����������¼�б�.<br>
         *
         * @param aspID
         * @param startDate
         * @param endDate
         * @return
         * @throws FluxManageSysException
         */
        public List getByUser(String key, int aspID, Date startDate, Date endDate)
                throws FluxManageSysException;
}
