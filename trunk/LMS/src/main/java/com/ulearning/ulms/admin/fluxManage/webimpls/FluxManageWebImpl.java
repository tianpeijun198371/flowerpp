/**
 * FluxManageWebImpl.java.
 * User: fengch  Date: 2004-11-24
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.admin.fluxManage.webimpls;

import com.ulearning.ulms.admin.fluxManage.dao.FluxManageDAOFactory;
import com.ulearning.ulms.admin.fluxManage.exceptions.FluxManageSysException;

import java.util.Date;
import java.util.List;


public class FluxManageWebImpl
{
        public List getByYear(int aspID, Date startDate, Date endDate)
                throws FluxManageSysException
        {
                return FluxManageDAOFactory.getDAO().getByYear(aspID, startDate, endDate);
        }

        public List getByMonth(int aspID, Date startDate, Date endDate)
                throws FluxManageSysException
        {
                return FluxManageDAOFactory.getDAO()
                        .getByMonth(aspID, startDate, endDate);
        }

        public List getByWeek(int aspID, Date startDate, Date endDate)
                throws FluxManageSysException
        {
                return FluxManageDAOFactory.getDAO().getByWeek(aspID, startDate, endDate);
        }

        public List getByDay(int aspID, Date startDate, Date endDate)
                throws FluxManageSysException
        {
                return FluxManageDAOFactory.getDAO().getByDay(aspID, startDate, endDate);
        }

        public List getByHour(int aspID, Date startDate, Date endDate)
                throws FluxManageSysException
        {
                return FluxManageDAOFactory.getDAO().getByHour(aspID, startDate, endDate);
        }

        public List getByUser(String key, int aspID, Date startDate, Date endDate)
                throws FluxManageSysException
        {
                return FluxManageDAOFactory.getDAO()
                        .getByUser(key, aspID, startDate, endDate);
        }
}
