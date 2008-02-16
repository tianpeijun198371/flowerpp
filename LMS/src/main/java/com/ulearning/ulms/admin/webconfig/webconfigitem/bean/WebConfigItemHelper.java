/**
 * Created by IntelliJ IDEA.
 * User: zengwj
 * Date: 2004-7-2
 * Time: 16:46:27
 * To change this template use File | Settings | File Templates.
 */
package com.ulearning.ulms.admin.webconfig.webconfigitem.bean;

import com.ulearning.ulms.admin.webconfig.webconfigitem.dao.WebConfigItemDAO;
import com.ulearning.ulms.admin.webconfig.webconfigitem.dao.WebConfigItemDAOFactory;
import com.ulearning.ulms.admin.webconfig.webconfigitem.exceptions.WebConfigItemDAOSysException;
import com.ulearning.ulms.admin.webconfig.webconfigitem.form.WebConfigItemForm;


public class WebConfigItemHelper
{
        /**
         * @param ConfigItemID
         * @return
         */
        public static WebConfigItemForm getWebConfigItem(int ConfigItemID)
        {
                WebConfigItemForm wcif = null;

                try
                {
                        WebConfigItemDAO webConfigItemDao = WebConfigItemDAOFactory.getDAO();
                        wcif = webConfigItemDao.getWebConfigItem(ConfigItemID);
                }
                catch (WebConfigItemDAOSysException wcdse)
                {
                        wcdse.printStackTrace();
                }

                return wcif;
        }

        /**
         * @param webConfigItemForm
         */
        public static void addWebConfigItem(WebConfigItemForm webConfigItemForm)
        {
                try
                {
                        WebConfigItemDAO webConfigItemDao = WebConfigItemDAOFactory.getDAO();
                        webConfigItemDao.addWebConfigItem(webConfigItemForm);
                }
                catch (WebConfigItemDAOSysException wcdse)
                {
                        wcdse.printStackTrace();
                }
        }

        /**
         * @param webConfigItemForm
         */
        public static void updateWebConfigItem(WebConfigItemForm webConfigItemForm)
        {
                try
                {
                        WebConfigItemDAO webConfigItemDao = WebConfigItemDAOFactory.getDAO();
                        webConfigItemDao.updateWebConfigItem(webConfigItemForm);
                }
                catch (WebConfigItemDAOSysException wcdse)
                {
                        wcdse.printStackTrace();
                }
        }
}
