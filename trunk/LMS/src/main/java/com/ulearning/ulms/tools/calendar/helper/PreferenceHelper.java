/**
 * PreferenceHelper.java.
 * User: keyh  Date: 2004-8-4
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.tools.calendar.helper;

import com.ulearning.ulms.tools.calendar.dao.PreferenceDAO;
import com.ulearning.ulms.tools.calendar.dao.PreferenceDAOFactory;
import com.ulearning.ulms.tools.calendar.exceptions.PreferenceSysException;
import com.ulearning.ulms.tools.calendar.form.PreferenceForm;
import com.ulearning.ulms.tools.calendar.model.PreferenceModel;

import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;


public class PreferenceHelper
{
        public static PreferenceForm get(int userID) throws PreferenceSysException
        {
                PreferenceForm pf = null;

                try
                {
                        PreferenceDAO dao = PreferenceDAOFactory.getDAO();
                        pf = dao.get(userID);
                }
                catch (PreferenceSysException gse)
                {
                        gse.printStackTrace();
                }

                return pf;
        }

        public static void delete(int userID) throws PreferenceSysException
        {
                PreferenceDAO dao = PreferenceDAOFactory.getDAO();
                dao.delete(userID);
        }
}
