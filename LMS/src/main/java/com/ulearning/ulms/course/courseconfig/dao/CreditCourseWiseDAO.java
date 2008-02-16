/**
 * CreditCourseWiseDAO.java.
 * User: keyh  Date: 2004-9-2
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.course.courseconfig.dao;

import com.ulearning.ulms.course.courseconfig.exceptions.CreditCourseWiseSysException;
import com.ulearning.ulms.course.courseconfig.form.CreditCourseWiseForm;

import java.util.List;


public interface CreditCourseWiseDAO
{
        public void add(CreditCourseWiseForm ccwf)
                throws CreditCourseWiseSysException;

        public void delete(int typeID, int relationID)
                throws CreditCourseWiseSysException;

        public void update(CreditCourseWiseForm ccwf)
                throws CreditCourseWiseSysException;

        public CreditCourseWiseForm get(int typeID, int relationID)
                throws CreditCourseWiseSysException;

        public List getAll() throws CreditCourseWiseSysException;
}
