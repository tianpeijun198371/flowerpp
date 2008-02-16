/**
 * Created by IntelliJ IDEA.
 * User: zengwj
 * Date: 2004-7-2
 * Time: 17:43:34
 * To change this template use File | Settings | File Templates.
 */
package com.ulearning.ulms.admin.webconfig.webcustomconfigitem.bean;

import com.ulearning.ulms.admin.webconfig.webconfigitem.exceptions.WebConfigItemDAOSysException;
import com.ulearning.ulms.admin.webconfig.webcustomconfigitem.dao.WebCustomConfigItemDAO;
import com.ulearning.ulms.admin.webconfig.webcustomconfigitem.dao.WebCustomConfigItemDAOFactory;
import com.ulearning.ulms.admin.webconfig.webcustomconfigitem.exceptions.CustomConfigItemDAOSysException;
import com.ulearning.ulms.admin.webconfig.webcustomconfigitem.form.WebCustomConfigItemForm;


public class WebCustomConfigItemHelper
{
        public static void addWebCustomConfigItem(int CustomID, int ConfigItemID)
        {
                try
                {
                        WebCustomConfigItemDAO webCustomConfigItemDao = WebCustomConfigItemDAOFactory.getDAO();
                        webCustomConfigItemDao.addWebCustomConfigItem(CustomID, ConfigItemID);
                }
                catch (CustomConfigItemDAOSysException wcdse)
                {
                        wcdse.printStackTrace();
                }
        }
}
