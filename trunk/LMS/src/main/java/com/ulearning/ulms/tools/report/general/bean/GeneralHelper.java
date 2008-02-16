/** * GeneralHelper.java. 
 * User: xiejh  Date: 2004-7-19 *  
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd. 
 * All rights reserved. 
 */
package com.ulearning.ulms.tools.report.general.bean;

import com.ulearning.ulms.tools.report.general.exceptions.GeneralDAOSysException;
import com.ulearning.ulms.tools.report.general.dao.GeneralDAO;
import com.ulearning.ulms.tools.report.general.dao.GeneralDAOFactory;

import java.util.List;

public class GeneralHelper
{
        public List getCategoryFormList(int ModulID)
        {
                List formList = null;
                try
                {
                        GeneralDAO GeneralDao = GeneralDAOFactory.getDAO();
                        formList = GeneralDao.getCategoryFormList(ModulID);
                }
                catch (GeneralDAOSysException se)
                {
                        se.printStackTrace();
                }
                return formList;
        }

        public List getReportTypeFormList(int CategoryID)
        {
                List formList = null;
                try
                {
                        GeneralDAO GeneralDao = GeneralDAOFactory.getDAO();
                        formList = GeneralDao.getReportTypeFormList(CategoryID);
                }
                catch (GeneralDAOSysException se)
                {
                        se.printStackTrace();
                }
                return formList;
        }

        public List getMReportFormList(String ReportType)
        {
                List formList = null;
                try
                {
                        GeneralDAO GeneralDao = GeneralDAOFactory.getDAO();
                        formList = GeneralDao.getMReportFormList(ReportType);
                }
                catch (GeneralDAOSysException se)
                {
                        se.printStackTrace();
                }
                return formList;
        }

        public List getFieldItemFormList(int MReportID)
        {
                List formList = null;
                try
                {
                        GeneralDAO GeneralDao = GeneralDAOFactory.getDAO();
                        formList = GeneralDao.getFieldItemFormList(MReportID);
                }
                catch (GeneralDAOSysException se)
                {
                        se.printStackTrace();
                }
                return formList;
        }

        public List getConditionItemFormList(String ReportType)
        {
                List formList = null;
                try
                {
                        GeneralDAO GeneralDao = GeneralDAOFactory.getDAO();
                        formList = GeneralDao.getConditionItemFormList(ReportType);
                }
                catch (GeneralDAOSysException se)
                {
                        se.printStackTrace();
                }
                return formList;
        }
}