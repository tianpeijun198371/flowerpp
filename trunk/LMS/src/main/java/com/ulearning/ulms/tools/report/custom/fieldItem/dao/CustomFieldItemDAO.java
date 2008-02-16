/**
 * Created by IntelliJ IDEA.
 * CustomFieldItem: dengj
 * Date: Apr 7, 2004
 * Time: 5:06:46 PM
 * To change this template use Options | File Templates.
 */

package com.ulearning.ulms.tools.report.custom.fieldItem.dao;

import com.ulearning.ulms.tools.report.custom.fieldItem.form.CustomFieldItemForm;
import com.ulearning.ulms.tools.report.custom.fieldItem.exceptions.CustomFieldItemDAOSysException;

import java.util.List;


public interface CustomFieldItemDAO
{

        public void addCustomFieldItem(CustomFieldItemForm details) throws CustomFieldItemDAOSysException;

        public void updateCustomFieldItem(CustomFieldItemForm details) throws CustomFieldItemDAOSysException;

        public void removeCustomFieldItem(int ReportID) throws CustomFieldItemDAOSysException;

        public void removeCustomFieldItem(int ReportID, int MReportID) throws CustomFieldItemDAOSysException;

        public void removeCustomFieldItem(int ReportID, int MReportID, int FieldItemID) throws CustomFieldItemDAOSysException;

        public List getCustomFieldItemList(int ReportID) throws CustomFieldItemDAOSysException;

        public List getCustomFieldItemList(int ReportID, int MReportID) throws CustomFieldItemDAOSysException;


}
