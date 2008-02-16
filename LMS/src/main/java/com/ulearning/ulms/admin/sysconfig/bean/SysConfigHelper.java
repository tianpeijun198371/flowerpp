/**
 * SysConfigHelper.java.
 * User: huangsb  Date: 2004-4-27
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd. * All rights reserved.
 */
package com.ulearning.ulms.admin.sysconfig.bean;

import com.ulearning.ulms.admin.sysconfig.dao.SysConfigDAO;
import com.ulearning.ulms.admin.sysconfig.dao.SysConfigDAOFactory;
import com.ulearning.ulms.admin.sysconfig.exceptions.SysConfigDAOSysException;
import com.ulearning.ulms.admin.sysconfig.form.SysConfigForm;

import java.util.List;


public class SysConfigHelper
{
        /**
         * Wrapping the get plan method for JSP and  the other modules
         *
         * @param orgID
         * @return the admin modle according to the name
         */
        public SysConfigForm getSysConfig(String orgID)
        {
                SysConfigForm sf = null;

                try
                {
                        SysConfigDAO sysConfigDao = SysConfigDAOFactory.getDAO();
                        sf = sysConfigDao.getSysConfig(orgID);
                }
                catch (SysConfigDAOSysException udse)
                {
                        udse.printStackTrace();
                }

                return sf;
        }

        /**
         * Wrapping the get plan method for JSP and  the other modules
         *
         * @return the admin modle according to the name
         */
        public SysConfigForm getSysConfig()
        {
                SysConfigForm sf = null;

                try
                {
                        SysConfigDAO sysConfigDao = SysConfigDAOFactory.getDAO();
                        sf = sysConfigDao.getSysConfig();
                }
                catch (SysConfigDAOSysException udse)
                {
                        udse.printStackTrace();
                }

                return sf;
        }

        /**
         * Wrapping the get sysConfigList method for JSP and  the other modules
         *
         * @param IsFree
         * @return the sysConfigList list according to the IsFree
         */
        public List getSysConfigList(int IsFree)
        {
                List sysConfigList = null;

                try
                {
                        SysConfigDAO sysConfigDao = SysConfigDAOFactory.getDAO();
                        sysConfigList = sysConfigDao.getSysConfigList(IsFree);
                }
                catch (SysConfigDAOSysException udse)
                {
                        udse.printStackTrace();
                }

                return sysConfigList;
        }
}
