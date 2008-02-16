/**
 * Created by IntelliJ IDEA.
 * User: zengwj
 * Date: 2004-7-2
 * Time: 13:58:16
 * To change this template use File | Settings | File Templates.
 */
package com.ulearning.ulms.admin.webconfig.webconfigitem.dao;

import com.ulearning.ulms.admin.webconfig.webconfigitem.exceptions.WebConfigItemDAOSysException;
import com.ulearning.ulms.admin.webconfig.webconfigitem.form.WebConfigItemForm;

import java.util.List;


public interface WebConfigItemDAO
{
        /**
         * @param wcif
         * @throws WebConfigItemDAOSysException
         */
        public void addWebConfigItem(WebConfigItemForm wcif)
                throws WebConfigItemDAOSysException;

        /**
         * @param wcif
         * @throws WebConfigItemDAOSysException
         */
        public void updateWebConfigItem(WebConfigItemForm wcif)
                throws WebConfigItemDAOSysException;

        /**
         * @param ItemType
         * @param Type
         * @return
         * @throws WebConfigItemDAOSysException
         */
        public List getWebConfigItemList(String ItemType, String Type)
                throws WebConfigItemDAOSysException;

        /**
         * @param ConfigItemID
         * @return
         * @throws WebConfigItemDAOSysException
         */
        public WebConfigItemForm getWebConfigItem(int ConfigItemID)
                throws WebConfigItemDAOSysException;

        /**
         * @param ConfigItemID
         * @throws WebConfigItemDAOSysException
         */
        public void removeWebConfigItem(int ConfigItemID)
                throws WebConfigItemDAOSysException;
}
