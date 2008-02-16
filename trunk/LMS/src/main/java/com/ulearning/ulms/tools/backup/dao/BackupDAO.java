/**
 * Created by IntelliJ IDEA.
 * Backup: xiejh
 * Date: Apr 7, 2004
 * Time: 5:06:46 PM
 * To change this template use Options | File Templates.
 */
package com.ulearning.ulms.tools.backup.dao;

import com.ulearning.ulms.tools.backup.exceptions.BackupDAOSysException;
import com.ulearning.ulms.tools.backup.form.BackupForm;

import java.util.List;


public interface BackupDAO
{
        public void addBackup(BackupForm details) throws BackupDAOSysException;

        public void updateBackup(BackupForm details) throws BackupDAOSysException;

        public void removeBackup(String backupID) throws BackupDAOSysException;

        public BackupForm getBackup(int backupID) throws BackupDAOSysException;

        public List getBackupList(String OperateType) throws BackupDAOSysException;

        public boolean backupDB(BackupForm backupForm) throws BackupDAOSysException;

        public boolean delbackupSQLServer(String filename)
                throws BackupDAOSysException;
}
