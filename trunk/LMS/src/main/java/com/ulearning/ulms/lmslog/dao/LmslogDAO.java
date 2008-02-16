/**
 * LmslogDAO.java.
 * User: keyh  Date: 2004-8-18
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.lmslog.dao;

import com.ulearning.ulms.lmslog.exceptions.LmslogSysException;
import com.ulearning.ulms.lmslog.form.LmslogForm;

import java.util.Date;
import java.util.List;


public interface LmslogDAO
{
        public void insert(LmslogForm lmslogForm) throws LmslogSysException;

        public LmslogForm get(int logID) throws LmslogSysException;

        public void delete(int logID) throws LmslogSysException;

        public void deleteByUserID(int userID) throws LmslogSysException;

        public List get(int logTypeID, int operatoinTypeID, int userID, int orgID,
                        String time, String endTime) throws LmslogSysException;

        public List getAllUser(int logTypeID, int operatoinTypeID, int orgID,
                               String time, String endTime) throws LmslogSysException;

        public void update(LmslogForm lmslogForm) throws LmslogSysException;

        public int countAll(int aspID);

        public List getAllLogInfoOfUser(int userID, int logTypeID,
                                        int operationTypeID) throws LmslogSysException;
}
