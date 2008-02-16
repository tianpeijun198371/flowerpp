/*
 * Copyright (c) 2004 Your Corporation. All Rights Reserved.
 */
package com.ulearning.ulms.course.courseconfig.webimpls;

import com.ulearning.ulms.course.courseconfig.dao.CreditCourseWiseDAO;
import com.ulearning.ulms.course.courseconfig.dao.CreditCourseWiseDAOFactory;
import com.ulearning.ulms.course.courseconfig.exceptions.CreditCourseWiseSysException;
import com.ulearning.ulms.course.courseconfig.form.CreditCourseWiseForm;


/**
 * Created by IntelliJ IDEA.
 * CreditCourseWiseWebImpl.java .
 * <p/>
 * User: keyh * Date: 2004-9-16
 * Time: 10:46:13
 * <p/>
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
public class CreditCourseWiseWebImpl
{
        public CreditCourseWiseWebImpl()
        {
        }

        public CreditCourseWiseForm get(int typeID, int relationID)
                throws CreditCourseWiseSysException
        {
                CreditCourseWiseDAO dao = CreditCourseWiseDAOFactory.getDAO();

                return dao.get(typeID, relationID);
        }
}
