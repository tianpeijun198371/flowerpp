/**
 * Created by IntelliJ IDEA.
 * Backup: xiejh
 * Date: Apr 14, 2004
 * Time: 12:05:08 PM
 * To change this template use Options | File Templates.
 */
package com.ulearning.ulms.tools.backup.action;

import com.ulearning.ulms.tools.backup.bean.BackupHelper;
import com.ulearning.ulms.tools.backup.dao.BackupDAO;
import com.ulearning.ulms.tools.backup.dao.BackupDAOFactory;
import com.ulearning.ulms.tools.backup.form.BackupForm;
import com.ulearning.ulms.util.log.LogUtil;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class DelBackupAction extends Action
{
        public ActionForward execute(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response)
                throws Exception
        {
                String resultScreen = "success";
                String[] backupID = request.getParameterValues("backupID");
                BackupDAO dao = BackupDAOFactory.getDAO();

                for (int i = 0; i < backupID.length; i++)
                {
                        try
                        {
                                int id = Integer.parseInt(backupID[i].trim());
                                BackupForm backupForm = dao.getBackup(id);
                                String filePath = backupForm.getFilePath().trim(); //得到文件名

                                LogUtil.debug("course",
                                        "[BackupAction] ==========filePath = " + filePath);

                                BackupHelper.delbackupSQLServer(filePath); //删除存放在磁盘上的数据
                                LogUtil.debug("course",
                                        "[BackupAction] ==========BackUPID = " + backupID[i]);
                                dao.removeBackup(backupID[i]); //删除数据库中的纪录
                        }
                        catch (Exception e)
                        {
                                e.printStackTrace();
                        }
                }

                // Forward to result page
                return mapping.findForward(resultScreen);
        }
}
