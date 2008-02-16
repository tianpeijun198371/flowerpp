/**
 * SysConfigDAO.java.
 * User: huangsb  Date: 2004-4-27
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd. * All rights reserved.
 */
package com.ulearning.ulms.admin.sysconfig.dao;

import com.ulearning.ulms.admin.sysconfig.exceptions.SysConfigDAOSysException;
import com.ulearning.ulms.admin.sysconfig.form.AutoInformForm;
import com.ulearning.ulms.admin.sysconfig.form.SysConfigForm;
import com.ulearning.ulms.admin.sysconfig.model.AutoInformModel;

import java.util.ArrayList;
import java.util.List;


public interface SysConfigDAO
{
        public void addSysConfig(SysConfigForm details)
                throws SysConfigDAOSysException;

        public void updateSysConfig(SysConfigForm details)
                throws SysConfigDAOSysException;

        public void addSysConfigByID(String orgID) throws SysConfigDAOSysException;

        public SysConfigForm getSysConfig(String name)
                throws SysConfigDAOSysException;

        public SysConfigForm getSysConfig() throws SysConfigDAOSysException;

        public ArrayList getAutoInformByID(String orgID)
                throws SysConfigDAOSysException;

        public AutoInformForm getAutoInfromFormByOrgIDAndType(String orgID, int type)
                throws SysConfigDAOSysException;

        public List getSysConfigList(int IsFree) throws SysConfigDAOSysException;

        /**
         * @param sysConfigForm
         * @throws SysConfigDAOSysException
         */
        public void updateUserRegister(SysConfigForm sysConfigForm)
                throws SysConfigDAOSysException;

        public void updateGeneralConfig(SysConfigForm sysConfigForm)
                throws SysConfigDAOSysException;

        /**
         * @param sysConfigForm
         */
        public void updatePWDConfig(SysConfigForm sysConfigForm)
                throws SysConfigDAOSysException;

        /**
         * @param sysConfigForm
         */
        public void updateLogConfig(SysConfigForm sysConfigForm)
                throws SysConfigDAOSysException;

        /**
         * @param sysConfigForm
         */
        public void updateServiceItemConfig(SysConfigForm sysConfigForm)
                throws SysConfigDAOSysException;

        /**
         * @param sysConfigForm
         */
        public void updateSMTPConfig(SysConfigForm sysConfigForm)
                throws SysConfigDAOSysException;

        /**
         * @param al
         */
        public void updateAutoInformConfig(ArrayList al)
                throws SysConfigDAOSysException;

        /**
         * @param sysConfigForm
         */
        public void updateLDAPConfig(SysConfigForm sysConfigForm)
                throws SysConfigDAOSysException;
}
