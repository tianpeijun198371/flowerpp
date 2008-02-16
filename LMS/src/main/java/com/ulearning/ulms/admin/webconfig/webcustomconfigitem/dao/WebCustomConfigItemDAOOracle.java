/**
 * Created by IntelliJ IDEA.
 * User: zengwj
 * Date: 2004-7-2
 * Time: 10:46:26
 * To change this template use File | Settings | File Templates.
 */
package com.ulearning.ulms.admin.webconfig.webcustomconfigitem.dao;

import com.ulearning.ulms.admin.webconfig.webcustomconfigitem.exceptions.CustomConfigItemDAOSysException;
import com.ulearning.ulms.core.exceptions.ULMSSysException;
import com.ulearning.ulms.util.OperateDB;
import com.ulearning.ulms.util.log.LogUtil;


public class WebCustomConfigItemDAOOracle extends WebCustomConfigItemDAOImpl
{
        /**
         * @param CustomID
         * @param ConfigItemID
         * @throws CustomConfigItemDAOSysException
         *
         */
        public void addWebCustomConfigItem(int CustomID, int ConfigItemID)
                throws CustomConfigItemDAOSysException
        {
                strSql = "insert into U_CustomConfigItem_Tab Values(" + CustomID + "," +
                        ConfigItemID + ")";
                operateDB = new OperateDB();

                try
                {
                        LogUtil.debug("WebCustomConfigItem",
                                "[WebCustomConfigItemDAOOracle]====================the sql string is : " +
                                        strSql);
                        operateDB.exeupdate(strSql);
                }
                catch (ULMSSysException se)
                {
                        throw new CustomConfigItemDAOSysException(
                                "SQLException while insert into WebCustomConfigItem; CustomID = " +
                                        CustomID + " ConfigItemID = " + ConfigItemID + " :\n" + se);
                }
        }

        /**
         * @param CustomID
         * @param oldConfigItemID
         * @param newConfigItemID
         * @throws CustomConfigItemDAOSysException
         *
         */
        public void updateWebCustomConfigItem(int CustomID, int oldConfigItemID,
                                              int newConfigItemID) throws CustomConfigItemDAOSysException
        {
                strSql = "update U_CustomConfigItem_Tab set ConfigItemID = " +
                        newConfigItemID + " where CustomID = " + CustomID +
                        " and ConfigItemID = " + oldConfigItemID;

                operateDB = new OperateDB();

                try
                {
                        LogUtil.debug("WebCustomConfigItem",
                                "[WebCustomConfigItemDAOOracle]====================the sql string is : " +
                                        strSql);
                        operateDB.exeupdate(strSql);
                }
                catch (ULMSSysException se)
                {
                        throw new CustomConfigItemDAOSysException(
                                "SQLException while update  WebCustomConfigItem; CustomID = " +
                                        CustomID + " ConfigItemID = " + oldConfigItemID + " :\n" + se);
                }
        }
}
