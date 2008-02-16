/** * CustomFieldItemHelper.java.
 * CustomFieldItem: xiejh  Date: 2004-4-22 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.tools.report.custom.fieldItem.bean;

import com.ulearning.ulms.tools.report.custom.fieldItem.dao.CustomFieldItemDAO;
import com.ulearning.ulms.tools.report.custom.fieldItem.dao.CustomFieldItemDAOFactory;
import com.ulearning.ulms.tools.report.custom.fieldItem.exceptions.CustomFieldItemDAOSysException;
import com.ulearning.ulms.tools.report.custom.fieldItem.form.CustomFieldItemForm;

import java.util.List;

public class CustomFieldItemHelper
{
        CustomFieldItemDAO fieldItemDao = null;

        public void addCustomFieldItem(CustomFieldItemForm formDetail)
        {
                try
                {
                        if (fieldItemDao == null)
                        {
                                fieldItemDao = CustomFieldItemDAOFactory.getDAO();
                        }
                        fieldItemDao.addCustomFieldItem(formDetail);
                }
                catch (CustomFieldItemDAOSysException udse)
                {
                        udse.printStackTrace();
                }
        }

        public void updateCustomFieldItem(CustomFieldItemForm formDetail)
        {
                try
                {
                        if (fieldItemDao == null)
                        {
                                fieldItemDao = CustomFieldItemDAOFactory.getDAO();
                        }
                        fieldItemDao.updateCustomFieldItem(formDetail);
                }
                catch (CustomFieldItemDAOSysException udse)
                {
                        udse.printStackTrace();
                }
        }

        public void removeCustomFieldItem(int ReportID, int MReportID)
        {
                try
                {
                        if (fieldItemDao == null)
                        {
                                fieldItemDao = CustomFieldItemDAOFactory.getDAO();
                        }
                        fieldItemDao.removeCustomFieldItem(ReportID, MReportID);
                }
                catch (CustomFieldItemDAOSysException udse)
                {
                        udse.printStackTrace();
                }
        }

        public void deleteCustomFieldItem(int ReportID, int MReportID, int FieldItemID)
        {
                try
                {
                        if (fieldItemDao == null)
                        {
                                fieldItemDao = CustomFieldItemDAOFactory.getDAO();
                        }
                        fieldItemDao.removeCustomFieldItem(ReportID, MReportID, FieldItemID);
                }
                catch (CustomFieldItemDAOSysException udse)
                {
                        udse.printStackTrace();
                }
        }

        /**
         * Wrapping the get fieldItemList method for JSP and  the other modules
         *
         * @param ReportID
         * @return the fieldItem list according to the catalog
         */
        public List getCustomFieldItemList(int ReportID)
        {
                List fieldItemList = null;
                try
                {
                        if (fieldItemDao == null)
                        {
                                fieldItemDao = CustomFieldItemDAOFactory.getDAO();
                        }
                        fieldItemList = fieldItemDao.getCustomFieldItemList(ReportID);
                }
                catch (CustomFieldItemDAOSysException udse)
                {
                        udse.printStackTrace();
                }
                return fieldItemList;
        }

        /**
         * Wrapping the get fieldItemList method for JSP and  the other modules
         *
         * @param ReportID
         * @return the fieldItem list according to the catalog
         */
        public List getCustomFieldItemList(int ReportID, int MReportID)
        {
                List fieldItemList = null;
                try
                {
                        if (fieldItemDao == null)
                        {
                                fieldItemDao = CustomFieldItemDAOFactory.getDAO();
                        }
                        fieldItemList = fieldItemDao.getCustomFieldItemList(ReportID, MReportID);
                }
                catch (CustomFieldItemDAOSysException udse)
                {
                        udse.printStackTrace();
                }
                return fieldItemList;
        }
}