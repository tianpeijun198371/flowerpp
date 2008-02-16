/**
 * Copyright (c) 2000-2005.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.content.uteachergroup.dao;

import com.ulearning.ulms.content.uteachergroup.exceptions.UTeacherGroupDAOSysException;
import com.ulearning.ulms.content.uteachergroup.form.UTeacherGroupForm;

import java.io.Serializable;

import java.util.List;


/**
 * Class description goes here.
 * <p/>
 * Created by Auto Code Produce System
 * User: xiejh
 * Date: 20060317
 * Time: 103906
 */
public interface UTeacherGroupDAO
{
        public Serializable insertUTeacherGroup(UTeacherGroupForm tf)
                throws UTeacherGroupDAOSysException;

        public void updateUTeacherGroup(UTeacherGroupForm tf)
                throws UTeacherGroupDAOSysException;

        public void deleteUTeacherGroup(int uteachgroupID)
                throws UTeacherGroupDAOSysException;

        public List getUTeacherGroupList() throws UTeacherGroupDAOSysException;

        public UTeacherGroupForm getUTeacherGroup(int uteachgroupID)
                throws UTeacherGroupDAOSysException;

        public List findbyUserId(int userid) throws UTeacherGroupDAOSysException;

        public List findbyShipId(int ushipId) throws UTeacherGroupDAOSysException;

        public void deletebyUserId(int userId) throws UTeacherGroupDAOSysException;
}
