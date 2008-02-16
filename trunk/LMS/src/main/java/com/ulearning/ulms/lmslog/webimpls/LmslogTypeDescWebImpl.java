/**
 * LmslogTypeDescWemImpl.java.
 * User: keyh  Date: 2004-8-21
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.lmslog.webimpls;

import com.ulearning.ulms.lmslog.dao.LmslogTypeDescDAO;
import com.ulearning.ulms.lmslog.dao.LmslogTypeDescDAOFactory;
import com.ulearning.ulms.lmslog.exceptions.LmslogTypeDescSysException;

import java.util.List;


public class LmslogTypeDescWebImpl
{
        public static List getAll() throws LmslogTypeDescSysException
        {
                List lmslogOperDescFormList = null;

                try
                {
                        LmslogTypeDescDAO dao = LmslogTypeDescDAOFactory.getDAO();
                        lmslogOperDescFormList = dao.getAll();
                }
                catch (LmslogTypeDescSysException cse)
                {
                        cse.printStackTrace();
                }

                return lmslogOperDescFormList;
        }
}
