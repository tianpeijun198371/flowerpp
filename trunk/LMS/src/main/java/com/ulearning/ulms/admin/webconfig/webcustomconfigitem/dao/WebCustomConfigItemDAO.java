/**
 * Created by IntelliJ IDEA.
 * User: zengwj
 * Date: 2004-7-2
 * Time: 10:45:01
 * To change this template use File | Settings | File Templates.
 */
package com.ulearning.ulms.admin.webconfig.webcustomconfigitem.dao;

import com.ulearning.ulms.admin.webconfig.webcustomconfigitem.exceptions.CustomConfigItemDAOSysException;
import com.ulearning.ulms.admin.webconfig.webcustomconfigitem.form.WebCustomConfigItemForm;


public interface WebCustomConfigItemDAO
{
        public void addWebCustomConfigItem(int CustomID, int ConfigItemID)
                throws CustomConfigItemDAOSysException;

        public void updateWebCustomConfigItem(int CustomID, int ConfigItemID)
                throws CustomConfigItemDAOSysException;

        public WebCustomConfigItemForm getWebCustomConfigInfo(int CustomID,
                                                              String type, String ItemType) throws CustomConfigItemDAOSysException;

        public WebCustomConfigItemForm getWebCustomConfigInfo(int CustomID)
                throws CustomConfigItemDAOSysException;
}
