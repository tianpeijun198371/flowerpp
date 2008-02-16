/**
 * StuWorkHelper.java.
 * User: yud  Date: 2005-4-16
 *
 * Copyright (c) 2000-2005.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.tools.schoolwork.bean;

import com.ulearning.ulms.util.log.LogUtil;
import com.ulearning.ulms.util.log.DebugUtil;
import com.ulearning.ulms.util.HibernateDAO;
import com.ulearning.ulms.tools.schoolwork.dao.StuWorkDAO;
import com.ulearning.ulms.tools.schoolwork.dao.StuWorkDAOFactory;
import com.ulearning.ulms.tools.schoolwork.form.StuWorkForm;
import com.ulearning.ulms.tools.schoolwork.model.StuWorkModel;
import com.ulearning.ulms.tools.schoolwork.exceptions.StuWorkSysException;
import com.ulearning.ulms.core.exceptions.ULMSSysException;

import java.util.Date;
import java.util.List;


public class StuWorkHelper
{
        private static StuWorkDAO stuWorkDAO;
        private static StuWorkForm stuWorkForm = new StuWorkForm();

        static
        {
                try
                {
                        stuWorkDAO = StuWorkDAOFactory.getDAO();

                }
                catch (Exception ex)
                {
                        ex.printStackTrace();
                }
        }

        public static void insertStuWork(StuWorkForm addSchoolWorkForm) throws StuWorkSysException
        {
                addSchoolWorkForm.setCreateDate(new Date());
                StuWorkModel stuWorkModel = (StuWorkModel) addSchoolWorkForm.getStuWorkModel();
                stuWorkDAO.insertStuWork(stuWorkModel);
        }

        public static List getall(int swId) throws StuWorkSysException
        {
                List list = stuWorkDAO.getall(swId);
                return list;
        }

        public static List getStuList(int swID, int userID) throws StuWorkSysException
        {
                List list = stuWorkDAO.getStuList(swID, userID);
                return list;
        }

        public static List getChouChaList(int swID, int orgID, String firstDate, String lastDate) throws StuWorkSysException
        {
                List list = stuWorkDAO.getChouChaList(swID, orgID, firstDate, lastDate);
                return list;
        }

        public static void deleteStuWork(List l) throws StuWorkSysException
        {
                stuWorkDAO.deleteStuWork(l);
        }

        public static StuWorkModel getStuModel(int userSWID) throws StuWorkSysException
        {
                StuWorkModel stuWorkModel = stuWorkDAO.getStuModel(userSWID);
                return stuWorkModel;

        }


}
