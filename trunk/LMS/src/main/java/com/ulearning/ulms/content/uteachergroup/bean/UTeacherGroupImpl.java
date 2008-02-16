/**
 * Copyright (c) 2000-2005.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.content.uteachergroup.bean;

import com.ulearning.ulms.content.uteachergroup.dao.UTeacherGroupDAO;
import com.ulearning.ulms.content.uteachergroup.dao.UTeacherGroupDAOFactory;
import com.ulearning.ulms.content.uteachergroup.exceptions.UTeacherGroupDAOSysException;
import com.ulearning.ulms.content.uteachergroup.form.UTeacherGroupForm;

import java.util.List;


/**
 * Class description goes here.
 * <p/>
 * Created by Auto Code Produce System
 * User: xiejh
 * Date: 20060317
 * Time: 103906
 */
public class UTeacherGroupImpl
{
        public List getUTeacherGroupList() throws UTeacherGroupDAOSysException
        {
                List UTeacherGroupList = null;

                try
                {
                        UTeacherGroupDAO dao = UTeacherGroupDAOFactory.getDAO();
                        UTeacherGroupList = dao.getUTeacherGroupList();
                }
                catch (UTeacherGroupDAOSysException tdse)
                {
                        tdse.printStackTrace();
                }

                return UTeacherGroupList;
        }

        public List findbyUserId(int userid) throws UTeacherGroupDAOSysException
        {
                List UTeacherGroupList = null;

                try
                {
                        UTeacherGroupDAO dao = UTeacherGroupDAOFactory.getDAO();
                        UTeacherGroupList = dao.findbyUserId(userid);
                }
                catch (UTeacherGroupDAOSysException tdse)
                {
                        tdse.printStackTrace();
                }

                return UTeacherGroupList;
        }

        public UTeacherGroupForm getUTeacherGroup(int uteachgroupID)
                throws UTeacherGroupDAOSysException
        {
                UTeacherGroupForm tf = null;

                try
                {
                        UTeacherGroupDAO dao = UTeacherGroupDAOFactory.getDAO();
                        tf = dao.getUTeacherGroup(uteachgroupID);
                }
                catch (UTeacherGroupDAOSysException tdse)
                {
                        tdse.printStackTrace();
                }

                return tf;
        }

        public void deleteUTeacherGroup(int uteachgroupID)
                throws UTeacherGroupDAOSysException
        {
                try
                {
                        UTeacherGroupDAO dao = UTeacherGroupDAOFactory.getDAO();
                        dao.deleteUTeacherGroup(uteachgroupID);
                }
                catch (UTeacherGroupDAOSysException tdse)
                {
                        tdse.printStackTrace();
                }
        }
}
