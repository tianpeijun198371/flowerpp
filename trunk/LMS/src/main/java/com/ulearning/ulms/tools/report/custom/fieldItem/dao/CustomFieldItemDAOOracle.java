/**
 * Created by IntelliJ IDEA.
 * CustomFieldItem: dengj
 * Date: Apr 8, 2004
 * Time: 9:36:03 AM
 * To change this template use Options | File Templates.
 */

package com.ulearning.ulms.tools.report.custom.fieldItem.dao;

import com.ulearning.ulms.tools.report.custom.fieldItem.exceptions.CustomFieldItemDAOSysException;
import com.ulearning.ulms.tools.report.custom.fieldItem.form.CustomFieldItemForm;
import com.ulearning.ulms.util.log.LogUtil;
import com.ulearning.ulms.util.OperateDB;
import com.ulearning.ulms.core.exceptions.ULMSSysException;

import java.sql.Statement;


public class CustomFieldItemDAOOracle extends CustomFieldItemDAOImpl
{

        /**
         * Insert a new fieldItem record to database
         *
         * @param fieldItemForm the value object to be added
         * @throws CustomFieldItemDAOSysException
         */
        public void addCustomFieldItem(CustomFieldItemForm fieldItemForm) throws CustomFieldItemDAOSysException
        {
                String sqlStr = "insert into R_CustomFieldItem_Tab values(CustomFieldItemID.nextval," +
                        fieldItemForm.getReportID() + "," +
                        fieldItemForm.getMReportID() + "," +
                        fieldItemForm.getFieldItemID() + ",'" +
                        fieldItemForm.getNewItemName() + "'," +
                        fieldItemForm.getSequence() + ")";

                OperateDB operateDB = new OperateDB();
                try
                {
                        LogUtil.debug("system", "[CustomFieldItemDAOOracle]====================the sql string is : " + sqlStr);
                        operateDB.exeupdate(sqlStr);

                }
                catch (ULMSSysException se)
                {
                        throw new CustomFieldItemDAOSysException("SQLException while addCustomFieldItem: sql = " + sqlStr + " :\n" + se);
                }

        }

        /**
         * Update fieldItemInfo by the new Form
         *
         * @param fieldItemForm value object for changed
         * @throws CustomFieldItemDAOSysException
         */
        public void updateCustomFieldItem(CustomFieldItemForm fieldItemForm) throws CustomFieldItemDAOSysException
        {
                String sqlStr = "update R_CustomFieldItem_Tab set " +
                        "ReportID = " + fieldItemForm.getReportID() + "," +
                        "MReportID = " + fieldItemForm.getMReportID() + "," +
                        "FieldItemID = " + fieldItemForm.getFieldItemID() + "," +
                        "NewItemName = '" + fieldItemForm.getNewItemName() + "'," +
                        "Sequence = " + fieldItemForm.getSequence() + " " +
                        "where CustomFieldItemID = " + fieldItemForm.getCustomFieldItemID();
                OperateDB operateDB = new OperateDB();
                try
                {
                        LogUtil.debug("system", "[CustomFieldItemDAOOracle]====================the sql string is : " + sqlStr);
                        operateDB.exeupdate(sqlStr);

                }
                catch (ULMSSysException se)
                {
                        throw new CustomFieldItemDAOSysException("SQLException while updateCustomFieldItem sql = " + sqlStr + " :\n" + se);
                }
        }


}
