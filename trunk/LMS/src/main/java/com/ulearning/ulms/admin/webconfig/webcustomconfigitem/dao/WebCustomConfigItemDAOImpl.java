/**
 * Created by IntelliJ IDEA.
 * User: zengwj
 * Date: 2004-7-2
 * Time: 11:20:32
 * To change this template use File | Settings | File Templates.
 */
package com.ulearning.ulms.admin.webconfig.webcustomconfigitem.dao;

import com.ulearning.ulms.admin.webconfig.webconfigitem.bean.WebConfigItemHelper;
import com.ulearning.ulms.admin.webconfig.webconfigitem.form.WebConfigItemForm;
import com.ulearning.ulms.admin.webconfig.webcustom.bean.WebCustomHelper;
import com.ulearning.ulms.admin.webconfig.webcustom.form.WebCustomForm;
import com.ulearning.ulms.admin.webconfig.webcustomconfigitem.exceptions.CustomConfigItemDAOSysException;
import com.ulearning.ulms.admin.webconfig.webcustomconfigitem.form.WebCustomConfigItemForm;
import com.ulearning.ulms.core.exceptions.ULMSSysException;
import com.ulearning.ulms.util.OperateDB;
import com.ulearning.ulms.util.log.LogUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;


public class WebCustomConfigItemDAOImpl implements WebCustomConfigItemDAO
{
        protected OperateDB operateDB = null;
        protected String strSql = "";

        public void addWebCustomConfigItem(int CustomID, int ConfigItemID)
                throws CustomConfigItemDAOSysException
        {
                //To change body of implemented methods use File | Settings | File Templates.
        }

        public void updateWebCustomConfigItem(int CustomID, int ConfigItemID)
                throws CustomConfigItemDAOSysException
        {
                //To change body of implemented methods use File | Settings | File Templates.
        }

        /**
         * @param CustomID
         * @param type
         * @param ItemType
         * @return
         * @throws CustomConfigItemDAOSysException
         *
         */
        public WebCustomConfigItemForm getWebCustomConfigInfo(int CustomID,
                                                              String type, String ItemType) throws CustomConfigItemDAOSysException
        {
                WebCustomConfigItemForm ccif = new WebCustomConfigItemForm();
                WebCustomForm wcf = null;
                ArrayList WebConfigItemList = new ArrayList();
                WebConfigItemForm wcif = null;
                operateDB = new OperateDB();

                strSql = "select cci.* from U_CustomConfigItem_Tab cci, U_ConfigItem_Tab ci " +
                        " where cci.CustomID = " + CustomID +
                        " and cci.ConfigItemID = ci.ConfigItemID";

                if (!type.equals("-1"))
                {
                        strSql += (" and ci.type = '" + type + "'");
                }

                if (!ItemType.equals("-1"))
                {
                        strSql += (" and ci.ItemType = '" + ItemType + "'");
                }

                try
                {
                        LogUtil.debug("WebCustomConfigItem",
                                "[WebCustomConfigItemDAOImpl]====================the sql string is : " +
                                        strSql);

                        ResultSet rs = operateDB.exequery(strSql);
                        int i = 0;

                        while ((rs != null) && rs.next())
                        {
                                if (i == 0)
                                {
                                        wcf = WebCustomHelper.getWebCustom(CustomID);
                                }

                                int configItemID = rs.getInt(2);
                                wcif = WebConfigItemHelper.getWebConfigItem(configItemID);
                                WebConfigItemList.add(wcif);
                                i++;
                        }

                        ccif.setCostomID(CustomID);
                        ccif.setList(WebConfigItemList);
                        ccif.setWcf(wcf);
                }
                catch (SQLException e)
                {
                        throw new CustomConfigItemDAOSysException(
                                "SQLException while query WebCustomItemFormList; CustomID = " +
                                        CustomID + " Type = " + type + " :\n" + e);
                }
                catch (ULMSSysException se)
                {
                        throw new CustomConfigItemDAOSysException(
                                "ULMSSysException while query WebCustomItemFormList; CustomID = " +
                                        CustomID + " Type = " + type + " :\n" + se);
                }
                finally
                {
                        try
                        {
                                operateDB.closeDB();
                        }
                        catch (SQLException e)
                        {
                        }
                }

                return ccif;
        }

        /**
         * @param CustomID
         * @return
         * @throws CustomConfigItemDAOSysException
         *
         */
        public WebCustomConfigItemForm getWebCustomConfigInfo(int CustomID)
                throws CustomConfigItemDAOSysException
        {
                WebCustomConfigItemForm ccif = new WebCustomConfigItemForm();
                WebCustomForm wcf = null;
                ArrayList WebConfigItemList = new ArrayList();
                WebConfigItemForm wcif = null;
                operateDB = new OperateDB();

                strSql = "select * from U_CustomConfigItem_Tab where CustomID = " +
                        CustomID;

                try
                {
                        LogUtil.debug("WebCustomConfigItem",
                                "[WebCustomConfigItemDAOImpl]====================the sql string is : " +
                                        strSql);

                        ResultSet rs = operateDB.exequery(strSql);
                        int i = 0;

                        while ((rs != null) && rs.next())
                        {
                                if (i == 0)
                                {
                                        wcf = WebCustomHelper.getWebCustom(CustomID);
                                }

                                int configItemID = rs.getInt(2);
                                wcif = WebConfigItemHelper.getWebConfigItem(configItemID);
                                WebConfigItemList.add(wcif);
                                i++;
                        }

                        ccif.setCostomID(CustomID);
                        ccif.setList(WebConfigItemList);
                        ccif.setWcf(wcf);
                }
                catch (SQLException e)
                {
                        throw new CustomConfigItemDAOSysException(
                                "SQLException while query WebCustomConfigItemForm; CustomID = " +
                                        CustomID + " :\n" + e);
                }
                catch (ULMSSysException se)
                {
                        throw new CustomConfigItemDAOSysException(
                                "ULMSSysException while query WebCustomConfigItemForm; CustomID = " +
                                        CustomID + " :\n" + se);
                }
                finally
                {
                        try
                        {
                                operateDB.closeDB();
                        }
                        catch (SQLException e)
                        {
                        }
                }

                return ccif;
        }
}
