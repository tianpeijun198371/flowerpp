/**
 * TeaWorkDAO.java.
 * User: yud  Date: 2005-4-17
 *
 * Copyright (c) 2000-2005.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.tools.schoolwork.dao;

import com.ulearning.ulms.tools.schoolwork.model.TeaWorkModel;
import com.ulearning.ulms.tools.schoolwork.exceptions.TeaWorkSysException;

import java.util.List;

public interface TeaWorkDAO
{

        public TeaWorkModel getTeaModel(int postilSWId)
                throws TeaWorkSysException;

        public List getTeaList(int userID)
                throws TeaWorkSysException;

        public List getTeaListall(int userswID)
                throws TeaWorkSysException;

        public List getCheck(int userswID)
                throws TeaWorkSysException;

        public void insertTeaWork(TeaWorkModel addTeaWorkModel)
                throws TeaWorkSysException;

        public void updateTeaWork(TeaWorkModel updTeaWorkModel)
                throws TeaWorkSysException;

        public void deleteTeaWork(List l)
                throws TeaWorkSysException;
}

