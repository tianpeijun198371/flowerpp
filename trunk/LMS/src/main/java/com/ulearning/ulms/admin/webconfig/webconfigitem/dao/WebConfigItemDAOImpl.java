/**
 * Created by IntelliJ IDEA.
 * User: zengwj
 * Date: 2004-7-2
 * Time: 13:59:04
 * To change this template use File | Settings | File Templates.
 */
package com.ulearning.ulms.admin.webconfig.webconfigitem.dao;

import com.ulearning.ulms.admin.webconfig.webconfigitem.exceptions.WebConfigItemDAOSysException;
import com.ulearning.ulms.admin.webconfig.webconfigitem.form.WebConfigItemForm;
import com.ulearning.ulms.core.exceptions.ULMSSysException;
import com.ulearning.ulms.util.OperateDB;
import com.ulearning.ulms.util.log.LogUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;


public class WebConfigItemDAOImpl implements WebConfigItemDAO
{
        protected OperateDB operateDB = null;
        protected String strSql = "";

        public void addWebConfigItem(WebConfigItemForm wcif)
                throws WebConfigItemDAOSysException
        {
                //To change body of implemented methods use File | Settings | File Templates.
        }

        public void updateWebConfigItem(WebConfigItemForm wcif)
                throws WebConfigItemDAOSysException
        {
                //To change body of implemented methods use File | Settings | File Templates.
        }

        /**
         * @param ItemType
         * @param Type
         * @return
         * @throws WebConfigItemDAOSysException
         */
        public List getWebConfigItemList(String ItemType, String Type)
                throws WebConfigItemDAOSysException
        {
                ArrayList webConfigItemList = new ArrayList();
                WebConfigItemForm wcif = new WebConfigItemForm();

                strSql = "select * from U_ConfigItem_Tab where ItemType = '" +
                        ItemType + "' and Type = '" + Type + "'";
                operateDB = new OperateDB();

                try
                {
                        LogUtil.debug("WebConfigItem",
                                "[WebConfigItemDAOImpl]====================the sql string is : " +
                                        strSql);

                        ResultSet rs = operateDB.exequery(strSql);

                        while ((rs != null) && rs.next())
                        {
                                wcif = convertRs2Form(rs);
                                webConfigItemList.add(wcif);
                        }
                }
                catch (SQLException e)
                {
                        throw new WebConfigItemDAOSysException(
                                "SQLException while query WebCustomItemFormList; ItemType = " +
                                        ItemType + " Type = " + Type + " :\n" + e);
                }
                catch (ULMSSysException se)
                {
                        throw new WebConfigItemDAOSysException(
                                "ULMSSysException while query WebCustomItemFormList; ItemType = " +
                                        ItemType + " Type = " + Type + " :\n" + se);
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

                return webConfigItemList;
        }

        /**
         * @param ConfigItemID
         * @return
         * @throws WebConfigItemDAOSysException
         */
        public WebConfigItemForm getWebConfigItem(int ConfigItemID)
                throws WebConfigItemDAOSysException
        {
                WebConfigItemForm wcif = new WebConfigItemForm();
                strSql = "select * from U_ConfigItem_Tab where ConfigItemID = " +
                        ConfigItemID;
                operateDB = new OperateDB();

                try
                {
                        LogUtil.debug("WebConfigItem",
                                "[WebConfigItemDAOImpl]====================the sql string is : " +
                                        strSql);

                        ResultSet rs = operateDB.exequery(strSql);

                        if ((rs != null) && rs.next())
                        {
                                wcif = convertRs2Form(rs);
                        }
                }
                catch (SQLException e)
                {
                        throw new WebConfigItemDAOSysException(
                                "SQLException while query WebCustomItemForm; ConfigItemID = " +
                                        ConfigItemID + " :\n" + e);
                }
                catch (ULMSSysException se)
                {
                        throw new WebConfigItemDAOSysException(
                                "ULMSSysException while query WebCustomItemForm; ConfigItemID = " +
                                        ConfigItemID + " :\n" + se);
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

                return wcif;
        }

        /**
         * @param ConfigItemID
         * @throws WebConfigItemDAOSysException
         */
        public void removeWebConfigItem(int ConfigItemID)
                throws WebConfigItemDAOSysException
        {
                strSql = "delect U_ConfigItem_Tab where ConfigItemID = " +
                        ConfigItemID;
                operateDB = new OperateDB();

                try
                {
                        LogUtil.debug("WebConfigItem",
                                "[WebConfigItemDAOImpl]====================the sql string is : " +
                                        strSql);
                        operateDB.exeupdate(strSql);
                }
                catch (ULMSSysException se)
                {
                        throw new WebConfigItemDAOSysException(
                                "SQLException while delete U_ConfigItem_Tab info; ConfigItemID = " +
                                        ConfigItemID + " :\n" + se);
                }
        }

        /**
         * @param rs
         * @return
         * @throws WebConfigItemDAOSysException
         */
        private WebConfigItemForm convertRs2Form(ResultSet rs)
                throws WebConfigItemDAOSysException
        {
                WebConfigItemForm wcif = new WebConfigItemForm();
                int rsIndex = 1;

                try
                {
                        wcif.setConfigItemID(rs.getInt(rsIndex++));
                        wcif.setItemType(rs.getString(rsIndex++));
                        wcif.setType(rs.getString(rsIndex++));
                        wcif.setContent(rs.getString(rsIndex++));
                        wcif.setDescription(rs.getString(rsIndex++));
                }
                catch (SQLException sql)
                {
                        throw new WebConfigItemDAOSysException(
                                "SQLException while query :\n" + sql);
                }

                return wcif;
        }
}
