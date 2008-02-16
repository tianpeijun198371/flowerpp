/** * BackupHelper.java.
 * Backup: xiejh  Date: 2004-4-22 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.tools.backup.bean;

import com.ulearning.ulms.core.util.Config;
import com.ulearning.ulms.core.util.IOUtil;
import com.ulearning.ulms.tools.backup.dao.BackupDAO;
import com.ulearning.ulms.tools.backup.dao.BackupDAOFactory;
import com.ulearning.ulms.tools.backup.exceptions.BackupDAOSysException;
import com.ulearning.ulms.tools.backup.form.BackupForm;
import com.ulearning.ulms.util.log.LogUtil;

import java.io.File;

import java.util.List;


public class BackupHelper
{
        /**
         * Wrapping the get backup method for JSP and  the other modules
         *
         * @param backupID
         * @return the backup modle according to the backupID
         */
        public BackupForm getBackup(int backupID)
        {
                BackupForm bf = null;

                try
                {
                        BackupDAO backupDao = BackupDAOFactory.getDAO();
                        bf = backupDao.getBackup(backupID);
                }
                catch (BackupDAOSysException udse)
                {
                        udse.printStackTrace();
                }

                return bf;
        }

        /**
         * Wrapping the get backupList method for JSP and  the other modules
         *
         * @param OperateType
         * @return the backup list according to the catalog
         */
        public List getBackupList(String OperateType)
        {
                List backupList = null;

                try
                {
                        BackupDAO backupDao = BackupDAOFactory.getDAO();
                        backupList = backupDao.getBackupList(OperateType);
                }
                catch (BackupDAOSysException udse)
                {
                        udse.printStackTrace();
                }

                return backupList;
        }

        public boolean backupDB(BackupForm backupForm)
        {
                boolean res = false;

                try
                {
                        BackupDAO backupDao = BackupDAOFactory.getDAO();
                        res = backupDao.backupDB(backupForm);
                }
                catch (BackupDAOSysException udse)
                {
                        udse.printStackTrace();
                }

                return res;
        }

        /**
         * 删除存储在磁盘上的备份数据
         *
         * @param fileName 数据名称
         * @return boolean
         */
        public static boolean delbackupSQLServer(String fileName)
                throws Exception
        {
                boolean res = false;

                try
                {
                        BackupDAO backupDao = BackupDAOFactory.getDAO();
                        backupDao.delbackupSQLServer(fileName);
                }
                catch (BackupDAOSysException udse)
                {
                        udse.printStackTrace();
                }

                return res;
        }
}
