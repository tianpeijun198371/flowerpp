/**
 * Created by IntelliJ IDEA.
 * Backup: xiejh
 * Date: Apr 8, 2004
 * Time: 9:36:22 AM
 * To change this template use Options | File Templates.
 */
package com.ulearning.ulms.tools.backup.dao;

import com.ulearning.ulms.core.exceptions.ULMSSysException;
import com.ulearning.ulms.core.util.Config;
import com.ulearning.ulms.core.util.IOUtil;
import com.ulearning.ulms.core.util.StringUtil;
import com.ulearning.ulms.tools.backup.exceptions.BackupDAOSysException;
import com.ulearning.ulms.tools.backup.form.BackupForm;
import com.ulearning.ulms.tools.backup.model.BackupModel;
import com.ulearning.ulms.util.DBUtil;
import com.ulearning.ulms.util.HibernateDAO;
import com.ulearning.ulms.util.log.LogUtil;

import net.sf.hibernate.cfg.Configuration;

import java.io.File;

import java.sql.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;


public class BackupDAOImpl implements BackupDAO
{
        public void addBackup(BackupForm details) throws BackupDAOSysException
        {
                try
                {
                        HibernateDAO.add(details.getBackupModel());
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new BackupDAOSysException("" + e);
                }
        }

        public void updateBackup(BackupForm details) throws BackupDAOSysException
        {
                try
                {
                        HibernateDAO.update(details.getBackupModel());
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new BackupDAOSysException("" + e);
                }
        }

        /**
         * Remove the backup from database by the backupID
         *
         * @param backupID
         * @throws BackupDAOSysException
         */
        public void removeBackup(String backupID) throws BackupDAOSysException
        {
                String hql = " from BackupModel where BackupID = " + backupID;

                try
                {
                        HibernateDAO.delete(hql);
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new BackupDAOSysException("" + e);
                }
        }

        /**
         * Get the backup info via the unique backupID
         *
         * @param backupID
         * @return the prepared backupForm, default is null
         * @throws BackupDAOSysException
         */
        public BackupForm getBackup(int backupID) throws BackupDAOSysException
        {
                BackupForm bf = new BackupForm();
                BackupForm res = null;
                List tmList = null;
                String hql = " from BackupModel where BackupID = " + backupID;

                try
                {
                        tmList = HibernateDAO.find(hql);
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new BackupDAOSysException("" + e);
                }

                if ((tmList != null) && (tmList.size() > 0))
                {
                        BackupModel bm = (BackupModel) tmList.get(0);
                        res = bf.getBackupForm(bm);
                }

                return res;
        }

        public List getBackupList(String OperateType) throws BackupDAOSysException
        {
                BackupForm bf = new BackupForm();
                BackupModel bm = null;
                ArrayList doccontentList = new ArrayList();
                List tmList = null;
                String hql = " from BackupModel where OperateType='" + OperateType +
                        "' order by createDate desc";

                try
                {
                        tmList = HibernateDAO.find(hql);
                }
                catch (ULMSSysException e)
                {
                        e.printStackTrace();
                        throw new BackupDAOSysException("" + e);
                }

                for (int i = 0; i < tmList.size(); i++)
                {
                        bm = (BackupModel) tmList.get(i);
                        doccontentList.add(bf.getBackupForm(bm));
                }

                return doccontentList;
        }

        public boolean backupDB(BackupForm backupForm)
        {
                boolean res = false;
                int dbtype = DBUtil.SQLServer2000; //DBUtil.getWhichDatabase();

                if (dbtype == DBUtil.SQLServer2000)
                {
                        res = backupSQLServer(backupForm);
                }
                else
                {
                        System.out.println("目前还不支持此类型数据库的备份");
                        res = false;
                }

                return res;
        }

        public boolean backupSQLServer(BackupForm backupForm)
        {
                boolean res = false;
                ConDB conDB = new ConDB();
                conDB.setDriver("com.microsoft.jdbc.sqlserver.SQLServerDriver");

                String filePath = Config.getUploadPhysicalPath() + "system\\backup\\" +
                        backupForm.getFilePath(); //"E:\\temp\\aa.bak";

                String f = Config.getUploadPhysicalPath() + "system";
                File fp = new File(f);

                if (!fp.exists())
                {
                        fp.mkdirs();
                }

                f = f + "\\backup";

                if (!fp.exists())
                {
                        fp.mkdirs();
                }

                String backupName = "备份";
                String url = getUrl();
                conDB.setUrl(url);

                String dbName = url.substring(url.indexOf("=") + 1);
                conDB.setUser(backupForm.getUsername());
                conDB.setPassword(backupForm.getPassword());

                String backupSql = "BACKUP DATABASE [" + dbName + "] TO  DISK = N'" +
                        filePath + "' WITH  NOINIT ,  NOUNLOAD ,  NAME = N'" + backupName +
                        "',  NOSKIP ,  STATS = 10,  NOFORMAT ";

                try
                {
                        conDB.execute(backupSql);
                        res = true;
                }
                catch (BackupDAOSysException e)
                {
                        System.out.println("backup error:" + e);
                }

                return res;
        }

        private String getUrl()
        {
                String url = null;
                Configuration c = new Configuration();

                try
                {
                        Configuration b = c.configure();
                        Properties p = b.getProperties();
                        url = p.getProperty("hibernate.connection.url");
                }
                catch (net.sf.hibernate.HibernateException h)
                {
                        System.out.println("" + h);
                }

                return url;
        }

        /**
         * 删除存储在磁盘上的备份数据
         *
         * @param fileName 数据名称
         * @return boolean
         */
        public boolean delbackupSQLServer(String filename)
                throws BackupDAOSysException
        {
                boolean res = false;

                String filePath = Config.getUploadPhysicalPath() + "system\\backup\\" +
                        filename; //"E:\\temp\\aa.bak";

                String f = Config.getUploadPhysicalPath() + "system\\backup";
                File fp = new File(f);

                if (!fp.exists()) //判断文件夹是否存在
                {
                        LogUtil.debug("backup",
                                "[BackupHelper]==================delbackupSQLServer");

                        return res;
                }

                try
                {
                        fp = new File(filePath);
                        IOUtil.delAllFile(fp); //删除指定的文件
                }
                catch (Exception e)
                {
                        LogUtil.debug("back",
                                "[BackupHelper]==================delbackupSQLServer ---IOUtil.delAllFile() " +
                                        e);
                        throw new BackupDAOSysException("" + e);
                }

                res = true;

                return res;
        }

        /**
         * Convert the resultSet object to backupForm
         * @param rs  the resultSet which needs to convert
         * @return the wanted backupForm
         */

        /*
          private BackupForm convertRs2Form(ResultSet rs)
          {
              BackupForm bf = new BackupForm();
              int rsIndex = 1;
              try
              {
                      bf.setBackupID(rs.getInt(rsIndex++));
                      bf.setDbName(StringUtil.nullToStr(rs.getString(rsIndex++)));
                      bf.setDbType(StringUtil.nullToStr(rs.getString(rsIndex++)));
                      bf.setUsername(StringUtil.nullToStr(rs.getString(rsIndex++)));
                      bf.setPassword(StringUtil.nullToStr(rs.getString(rsIndex++)));
                      bf.setFilePath(StringUtil.nullToStr(rs.getString(rsIndex++)));
                      bf.setOperateType(StringUtil.nullToStr(rs.getString(rsIndex++)));
                      bf.setUserID(rs.getInt(rsIndex++));
                      bf.setCreateDate(rs.getDate(rsIndex++));
              } catch (SQLException sql)
              {
                      sql.printStackTrace();
              }
              return bf;
          }
              public static void main(String[] args)
              {
              }
        */
}
