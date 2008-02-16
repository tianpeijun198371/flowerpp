/**
 * Copyright (c) 2000-2005.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.admin.gradeuser.bean;

import com.ulearning.ulms.admin.gradeuser.dao.GradeUserDAO;
import com.ulearning.ulms.admin.gradeuser.dao.GradeUserDAOFactory;
import com.ulearning.ulms.admin.gradeuser.exceptions.GradeUserDAOSysException;
import com.ulearning.ulms.admin.gradeuser.form.GradeUserForm;

import java.util.List;


/**
 * Class description goes here.
 * <p/>
 * Created by Auto Code Produce System
 * User: xiejh
 * Date: 20060321
 * Time: 182730
 */
public class GradeUserImpl
{
        public static List getGradeUserList() throws GradeUserDAOSysException
        {
                List GradeUserList = null;

                try
                {
                        GradeUserDAO dao = GradeUserDAOFactory.getDAO();
                        GradeUserList = dao.getGradeUserList();
                }
                catch (GradeUserDAOSysException tdse)
                {
                        tdse.printStackTrace();
                }

                return GradeUserList;
        }

        public static GradeUserForm getGradeUser(int gradeUserID)
                throws GradeUserDAOSysException
        {
                GradeUserForm tf = null;

                try
                {
                        GradeUserDAO dao = GradeUserDAOFactory.getDAO();
                        tf = dao.getGradeUser(gradeUserID);
                }
                catch (GradeUserDAOSysException tdse)
                {
                        tdse.printStackTrace();
                }

                return tf;
        }

        public static GradeUserForm getGradeUser(String name, String pwd)
                throws GradeUserDAOSysException
        {
                GradeUserForm tf = null;

                try
                {
                        GradeUserDAO dao = GradeUserDAOFactory.getDAO();
                        tf = dao.getGradeUser(name, pwd);
                }
                catch (GradeUserDAOSysException tdse)
                {
                        tdse.printStackTrace();
                }

                return tf;
        }

        public static void deleteGradeUser(int gradeUserID)
                throws GradeUserDAOSysException
        {
                try
                {
                        GradeUserDAO dao = GradeUserDAOFactory.getDAO();
                        dao.deleteGradeUser(gradeUserID);
                }
                catch (GradeUserDAOSysException tdse)
                {
                        tdse.printStackTrace();
                }
        }
}
