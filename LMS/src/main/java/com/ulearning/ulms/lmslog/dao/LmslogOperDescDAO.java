/**
 * LmslogOperDescDAO.java.
 * User: keyh  Date: 2004-8-21
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.lmslog.dao;

import com.ulearning.ulms.lmslog.exceptions.LmslogOperDescSysException;
import com.ulearning.ulms.lmslog.form.LmslogOperDescForm;

import java.util.List;


public interface LmslogOperDescDAO
{
        public void insert(LmslogOperDescForm llodf)
                throws LmslogOperDescSysException;

        public List getByLogType(int logType) throws LmslogOperDescSysException;

        public List getAll() throws LmslogOperDescSysException;
}
