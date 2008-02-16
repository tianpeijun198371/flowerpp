/*
 * Copyright (c) 2004 Your Corporation. All Rights Reserved.
 */
package com.ulearning.ulms.course.courseconfig.webimpls;

import com.ulearning.ulms.course.courseconfig.dao.GradeWeightingFactorDAO;
import com.ulearning.ulms.course.courseconfig.dao.GradeWeightingFactorDAOFactory;
import com.ulearning.ulms.course.courseconfig.exceptions.GradeWeightingFactorSysException;
import com.ulearning.ulms.course.courseconfig.form.GradeWeightingFactorForm;

import java.io.Serializable;


/**
 * Created by IntelliJ IDEA.
 * GradeWeightingFactorWebImpl.java .
 * <p/>
 * User: keyh * Date: 2004-9-16
 * Time: 10:44:14
 * <p/>
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
public class GradeWeightingFactorWebImpl
{
        public GradeWeightingFactorWebImpl()
        {
        }

        public GradeWeightingFactorForm get(int typeID, int relationID)
                throws GradeWeightingFactorSysException
        {
                GradeWeightingFactorDAO dao = GradeWeightingFactorDAOFactory.getDAO();

                return dao.get(typeID, relationID);
        }
}
