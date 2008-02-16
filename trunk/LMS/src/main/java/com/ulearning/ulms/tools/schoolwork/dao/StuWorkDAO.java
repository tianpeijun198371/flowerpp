/**
 * StuWorkDAO.java.
 * User: yud  Date: 2005-4-16
 *
 * Copyright (c) 2000-2005.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.tools.schoolwork.dao;

import com.ulearning.ulms.tools.schoolwork.model.StuWorkModel;
import com.ulearning.ulms.tools.schoolwork.exceptions.StuWorkSysException;

import java.util.List;
import java.util.Date;

public interface StuWorkDAO
{

        public StuWorkModel getStuModel(int userSWID)
                throws StuWorkSysException;

        public List getall(int swID)
                throws StuWorkSysException;

        public List getStuList(int swID, int userID)
                throws StuWorkSysException;

        public List getChouChaList(int swId, int orgID, String firstDate, String lastDate)
                throws StuWorkSysException;

        public void insertStuWork(StuWorkModel addStuWorkModel)
                throws StuWorkSysException;

        public void updateStuWork(StuWorkModel updStuWorkModel)
                throws StuWorkSysException;

        public void deleteStuWork(List l)
                throws StuWorkSysException;
}

