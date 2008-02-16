package com.ulearning.ulms.tools.report.custom.conditionitem.bean;


import com.ulearning.ulms.tools.report.custom.conditionitem.dao.CustomConditionItemDAO;
import com.ulearning.ulms.tools.report.custom.conditionitem.dao.CustomConditionItemDAOFactory;
import com.ulearning.ulms.tools.report.custom.conditionitem.exceptions.CustomConditionItemDAOSysException;
import com.ulearning.ulms.tools.report.custom.conditionitem.form.CustomConditionItemForm;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: keyh
 * Date: 2004-7-19
 * Time: 14:19:48
 * To change this template use File | Settings | File Templates.
 */
public class CustomConditionItemHelper
{
        CustomConditionItemDAO dao = null;

        public List getCustomConditionItemFormList(int reportID) throws CustomConditionItemDAOSysException
        {
                if (dao == null)
                {
                        dao = CustomConditionItemDAOFactory.getDAO();
                }
                return dao.getCustomConditionItemFormList(reportID);

        }

        public void insertCustomConditionItem(CustomConditionItemForm ccf) throws CustomConditionItemDAOSysException
        {
                if (dao == null)
                {
                        dao = CustomConditionItemDAOFactory.getDAO();
                }
                dao.insertCustomConditionItem(ccf);
        }

        public void deleteCustomConditionItem(int reportID) throws CustomConditionItemDAOSysException
        {
                if (dao == null)
                {
                        dao = CustomConditionItemDAOFactory.getDAO();
                }
                dao.deleteCustomConditionItem(reportID);
        }

}
