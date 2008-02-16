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
         * 插入流量记录.<br>
         *
         * @param fm
         * @throws FluxManageSysException
         */
        public void insert(FluxModel fm) throws FluxManageSysException;

        /**
         * 按年返回流量记录列表.<br>
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
         * 按月返回流量记录列表.<br>
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
         * 按星期返回流量记录列表.<br>
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
         * 按天返回流量记录列表.<br>
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
         * 按每月的天返回流量记录列表.<br>
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
         * 按小时返回流量记录列表.<br>
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
         * 按用户返回流量记录列表.<br>
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
