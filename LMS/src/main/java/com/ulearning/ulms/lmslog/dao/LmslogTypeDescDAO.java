/**
 * LmslogTypeDescDAO.java.
 * User: keyh  Date: 2004-8-21
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.lmslog.dao;

import com.ulearning.ulms.lmslog.exceptions.LmslogOperDescSysException;
import com.ulearning.ulms.lmslog.exceptions.LmslogTypeDescSysException;
import com.ulearning.ulms.lmslog.form.LmslogOperDescForm;
import com.ulearning.ulms.lmslog.form.LmslogTypeDescForm;

import java.util.List;


public interface LmslogTypeDescDAO
{
        public void insert(LmslogTypeDescForm ltdf)
                throws LmslogTypeDescSysException;

        public List getAll() throws LmslogTypeDescSysException;
}
