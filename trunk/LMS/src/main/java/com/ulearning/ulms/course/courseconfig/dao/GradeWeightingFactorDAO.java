/**
 * GradeWeightingFactorDAO.java.
 * User: keyh  Date: 2004-9-2
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.course.courseconfig.dao;

import com.ulearning.ulms.course.courseconfig.exceptions.CreditCourseWiseSysException;
import com.ulearning.ulms.course.courseconfig.exceptions.GradeWeightingFactorSysException;
import com.ulearning.ulms.course.courseconfig.form.GradeWeightingFactorForm;

import java.util.List;


public interface GradeWeightingFactorDAO
{
        public void add(GradeWeightingFactorForm ccff)
                throws GradeWeightingFactorSysException;

        public void delete(int typeID, int relationID)
                throws GradeWeightingFactorSysException;

        public void update(GradeWeightingFactorForm ccff)
                throws GradeWeightingFactorSysException;

        public float compute(int relationID, int typeID, float exerciseWF,
                             float testWF, float examWF) throws GradeWeightingFactorSysException;

        public GradeWeightingFactorForm get(int typeID, int relationID)
                throws GradeWeightingFactorSysException;

        public List getAll() throws GradeWeightingFactorSysException;
}
