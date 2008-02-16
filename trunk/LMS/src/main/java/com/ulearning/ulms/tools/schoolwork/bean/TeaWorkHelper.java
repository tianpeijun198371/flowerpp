/**
 * TeaWorkHelper.java.
 * User: yud  Date: 2005-4-18
 *
 * Copyright (c) 2000-2005.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.tools.schoolwork.bean;

import com.ulearning.ulms.tools.schoolwork.dao.TeaWorkDAO;
import com.ulearning.ulms.tools.schoolwork.dao.TeaWorkDAOFactory;
import com.ulearning.ulms.tools.schoolwork.model.TeaWorkModel;
import com.ulearning.ulms.tools.schoolwork.exceptions.TeaWorkSysException;
import com.ulearning.ulms.tools.schoolwork.form.TeaWorkForm;
import com.ulearning.ulms.core.exceptions.ULMSSysException;

import java.util.Date;
import java.util.List;


public class TeaWorkHelper
{
        private static TeaWorkDAO teaWorkDAO;
        private static TeaWorkForm teaWorkForm = new TeaWorkForm();

        static
        {
                try
                {
                        teaWorkDAO = TeaWorkDAOFactory.getDAO();

                }
                catch (Exception ex)
                {
                        ex.printStackTrace();
                }
        }

        public static void insertTeaWork(TeaWorkForm addTeaWorkForm) throws TeaWorkSysException
        {
                addTeaWorkForm.setCreateDate(new Date());
                TeaWorkModel teaWorkModel = (TeaWorkModel) addTeaWorkForm.getTeaWorkModel();
                teaWorkDAO.insertTeaWork(teaWorkModel);
        }

        public static void deleteTeaWork(List l) throws TeaWorkSysException
        {
                teaWorkDAO.deleteTeaWork(l);
        }

        public static TeaWorkModel getTeaModel(int postilSWId) throws TeaWorkSysException
        {
                TeaWorkModel teaWorkModel = teaWorkDAO.getTeaModel(postilSWId);
                return teaWorkModel;
        }

        public static List getTeaListall(int userswID)
                throws TeaWorkSysException
        {
                List tmList = teaWorkDAO.getTeaListall(userswID);
                return tmList;
        }

        public static List getCheck(int userswID)
                throws TeaWorkSysException
        {
                List tmList = teaWorkDAO.getCheck(userswID);
                return tmList;
        }

        public static void updateTeaWork(TeaWorkModel updTeaWorkModel) throws TeaWorkSysException
        {
                updTeaWorkModel.setCreateDate(new Date());
                teaWorkDAO.updateTeaWork(updTeaWorkModel);
        }
}
