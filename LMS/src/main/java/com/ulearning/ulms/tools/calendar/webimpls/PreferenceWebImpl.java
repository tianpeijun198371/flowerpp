/**
 * PreferenceWebImpl.java.
 * User: keyh  Date: 2004-8-17
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.tools.calendar.webimpls;

import com.ulearning.ulms.tools.calendar.dao.PreferenceDAO;
import com.ulearning.ulms.tools.calendar.dao.PreferenceDAOFactory;
import com.ulearning.ulms.tools.calendar.exceptions.PreferenceSysException;


public class PreferenceWebImpl
{
        public static boolean isPrefExist(int userID) throws PreferenceSysException
        {
                boolean exist = false;

                try
                {
                        PreferenceDAO dao = PreferenceDAOFactory.getDAO();
                        exist = dao.isPrefExist(userID);
                }
                catch (PreferenceSysException cse)
                {
                        cse.printStackTrace();
                }

                return exist;
        }
}
