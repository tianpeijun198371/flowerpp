/**
 * Copyright (c) 2000-2005.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.admin.gradeuser.dao;

import com.ulearning.ulms.admin.gradeuser.exceptions.GradeUserDAOSysException;
import com.ulearning.ulms.admin.gradeuser.form.GradeUserForm;

import java.io.Serializable;

import java.util.List;


/**
 * Class description goes here.
 * <p/>
 * Created by Auto Code Produce System
 * User: xiejh
 * Date: 20060321
 * Time: 182730
 */
public interface GradeUserDAO
{
        public int insertGradeUser(GradeUserForm tf)
                throws GradeUserDAOSysException;

        public void updateGradeUser(GradeUserForm tf)
                throws GradeUserDAOSysException;

        public void deleteGradeUser(int gradeUserID)
                throws GradeUserDAOSysException;

        public List getGradeUserList() throws GradeUserDAOSysException;

        public GradeUserForm getGradeUser(int gradeUserID)
                throws GradeUserDAOSysException;

        public GradeUserForm getGradeUser(String name, String pwd)
                throws GradeUserDAOSysException;
}
