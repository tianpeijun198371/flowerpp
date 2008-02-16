/**
 * Preference.java.
 * User: keyh  Date: 2004-8-4
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.tools.calendar.dao;

import com.ulearning.ulms.tools.calendar.exceptions.PreferenceSysException;
import com.ulearning.ulms.tools.calendar.form.PreferenceForm;
import com.ulearning.ulms.tools.calendar.model.PreferenceModel;


public interface PreferenceDAO
{
        public void insert(PreferenceForm pf) throws PreferenceSysException;

        public void update(PreferenceForm pf) throws PreferenceSysException;

        public void delete(int userID) throws PreferenceSysException;

        public boolean isPrefExist(int userID) throws PreferenceSysException;

        public PreferenceForm get(int userID) throws PreferenceSysException;
}
