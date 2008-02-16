/**
 * SchoolWorkDAO.java.
 * User: yud  Date: 2005-4-14
 *
 * Copyright (c) 2000-2005.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.tools.schoolwork.dao;

import com.ulearning.ulms.tools.schoolwork.model.SchoolWorkModel;
import com.ulearning.ulms.tools.schoolwork.exceptions.SchoolWorkSysException;

import com.ulearning.ulms.course.exceptions.CourseSysException;

import java.util.List;

public interface SchoolWorkDAO
{

        public SchoolWorkModel getSchoolModel(int swID)
                throws SchoolWorkSysException;

        public List getSchoolList(int relationID, int type)
                throws SchoolWorkSysException;

        public boolean getSchoolAnswerFromDate(int swID)
                throws SchoolWorkSysException;

        public List getSchoolListFromDate(int relationID, int type)
                throws SchoolWorkSysException;

        public void insertSchoolWork(SchoolWorkModel addSchoolWorkModel)
                throws SchoolWorkSysException;

        public void updateSchoolWork(SchoolWorkModel updSchoolWorkModel)
                throws SchoolWorkSysException;

        public void deleteSchoolWork(List l)
                throws SchoolWorkSysException;
}

