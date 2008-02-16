/** * AutoInformHelper.java.
 * User: chenxj  Date: 2004-8-19
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved. */
package com.ulearning.ulms.admin.sysconfig.bean;

import com.ulearning.ulms.admin.sysconfig.dao.SysConfigDAO;
import com.ulearning.ulms.admin.sysconfig.dao.SysConfigDAOFactory;
import com.ulearning.ulms.admin.sysconfig.exceptions.SysConfigDAOSysException;
import com.ulearning.ulms.admin.sysconfig.form.AutoInformForm;

import java.util.ArrayList;


public class AutoInformHelper
{
        public ArrayList getAutoInformByID(String orgID)
        {
                ArrayList al = new ArrayList();

                try
                {
                        SysConfigDAO sysConfigDao = SysConfigDAOFactory.getDAO();
                        al = sysConfigDao.getAutoInformByID(orgID);
                }
                catch (SysConfigDAOSysException udse)
                {
                        udse.printStackTrace();
                }

                return al;
        }

        public AutoInformForm getAutoInformByIDandType(String id, int type)
        {
                AutoInformForm aif = null;

                try
                {
                        SysConfigDAO sysConfigDao = SysConfigDAOFactory.getDAO();
                        aif = sysConfigDao.getAutoInfromFormByOrgIDAndType(id, type);
                }
                catch (SysConfigDAOSysException udse)
                {
                        udse.printStackTrace();
                }

                return aif;
        }
}
