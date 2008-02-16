/*
 * Copyright (c) 2004 Your Corporation. All Rights Reserved.
 */
package com.ulearning.ulms.lmslog.helper;

import com.ulearning.ulms.lmslog.dao.LmslogDAO;
import com.ulearning.ulms.lmslog.dao.LmslogDAOFactory;
import com.ulearning.ulms.lmslog.exceptions.LmslogSysException;


/**
 * @author <a href="mailto:youmail@yourdomain.com">yourname</a> Date: 2004-12-30
 */
public class LmslogHelper
{
        public static void deleteByUserID(int userID) throws LmslogSysException
        {
                LmslogDAO dao = LmslogDAOFactory.getDAO();
                dao.deleteByUserID(userID);
        }

        public int countAll(int aspID)
        {
                try
                {
                        return LmslogDAOFactory.getDAO().countAll(aspID);
                }
                catch (LmslogSysException e)
                {
                        e.printStackTrace();
                }

                return -1;
        }
}
