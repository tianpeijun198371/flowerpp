/**
 * Created by IntelliJ IDEA.
 * User: zengwj
 * Date: 2004-7-2
 * Time: 13:59:26
 * To change this template use File | Settings | File Templates.
 */
package com.ulearning.ulms.admin.webconfig.webconfigitem.dao;

import com.ulearning.ulms.admin.webconfig.webconfigitem.exceptions.WebConfigItemDAOSysException;
import com.ulearning.ulms.admin.webconfig.webconfigitem.form.WebConfigItemForm;
import com.ulearning.ulms.core.exceptions.ULMSSysException;
import com.ulearning.ulms.util.OperateDB;
import com.ulearning.ulms.util.log.LogUtil;


public class WebConfigItemDAOOracle extends WebConfigItemDAOImpl
{
        /**
         * @param wcif
         * @throws WebConfigItemDAOSysException
         */
        public void addWebConfigItem(WebConfigItemForm wcif)
                throws WebConfigItemDAOSysException
        {
                strSql = "insert into U_ConfigItem_Tab Values(ConfigItemID.nextval,'" +
                        wcif.getItemType() + "','" + wcif.getType() + "','" +
                        wcif.getContent() + "','" + wcif.getDescription() + "')";
                operateDB = new OperateDB();

                try
                {
                        LogUtil.debug("WebConfigItem",
                                "[WebConfigItemDAOOracle]====================the sql string is : " +
                                        strSql);
                        operateDB.exeupdate(strSql);
                }
                catch (ULMSSysException se)
                {
                        throw new WebConfigItemDAOSysException(
                                "SQLException while insert into WebConfigItem; Serial = " +
                                        wcif.getDescription() + " :\n" + se);
                }
        }

        /**
         * @param wcif
         * @throws WebConfigItemDAOSysException
         */
        public void updateWebConfigItem(WebConfigItemForm wcif)
                throws WebConfigItemDAOSysException
        {
                strSql = "update U_ConfigItem_Tab set Content = '" + wcif.getContent() +
                        "',Descrption = '" + wcif.getDescription() +
                        "' where ConfigItemID = " + wcif.getConfigItemID();
                operateDB = new OperateDB();

                try
                {
                        LogUtil.debug("WebConfigItem",
                                "[WebConfigItemDAOOracle]====================the sql string is : " +
                                        strSql);
                        operateDB.exeupdate(strSql);
                }
                catch (ULMSSysException se)
                {
                        throw new WebConfigItemDAOSysException(
                                "SQLException while insert into WebCustom; Serial = " +
                                        wcif.getDescription() + " :\n" + se);
                }
        }
}
