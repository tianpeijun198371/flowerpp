/**
 * CreditCourseWiseHelper.java.
 * User: keyh  Date: 2004-9-2
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.course.courseconfig.bean;

import com.ulearning.ulms.course.courseconfig.dao.CreditCourseWiseDAO;
import com.ulearning.ulms.course.courseconfig.dao.CreditCourseWiseDAOFactory;
import com.ulearning.ulms.course.courseconfig.exceptions.CreditCourseWiseSysException;
import com.ulearning.ulms.course.courseconfig.form.CreditCourseWiseForm;

import java.util.ArrayList;
import java.util.List;


public class CreditCourseWiseHelper
{
        public static CreditCourseWiseForm get(int typeID, int relationID)
                throws CreditCourseWiseSysException
        {
                try
                {
                        CreditCourseWiseDAO dao = CreditCourseWiseDAOFactory.getDAO();
                        CreditCourseWiseForm ccwf = new CreditCourseWiseForm();
                        ccwf = dao.get(typeID, relationID);

                        return ccwf;
                }
                catch (CreditCourseWiseSysException ccwe)
                {
                        ccwe.printStackTrace();
                }

                return null;
        }

        public static List getAll() throws CreditCourseWiseSysException
        {
                List list = new ArrayList();

                try
                {
                        CreditCourseWiseDAO dao = CreditCourseWiseDAOFactory.getDAO();
                        list = dao.getAll();
                }
                catch (CreditCourseWiseSysException ccwe)
                {
                        ccwe.printStackTrace();
                }

                return list;
        }

        public static void delete(int typeID, int relationID)
                throws CreditCourseWiseSysException
        {
                CreditCourseWiseDAO dao = CreditCourseWiseDAOFactory.getDAO();
                dao.delete(typeID, relationID);
        }
}
