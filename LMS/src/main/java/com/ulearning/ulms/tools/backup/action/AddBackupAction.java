/**
 * Created by IntelliJ IDEA.
 * Backup: xiejh
 * Date: Apr 8, 2004
 * Time: 3:15:39 PM
 * To change this template use Options | File Templates.
 */
package com.ulearning.ulms.tools.backup.action;

import com.ulearning.ulms.lmslog.dao.LmslogDAO;
import com.ulearning.ulms.lmslog.dao.LmslogDAOFactory;
import com.ulearning.ulms.lmslog.form.LmslogForm;
import com.ulearning.ulms.lmslog.util.LmslogConstants;
import com.ulearning.ulms.tools.backup.bean.BackupHelper;
import com.ulearning.ulms.tools.backup.dao.BackupDAO;
import com.ulearning.ulms.tools.backup.dao.BackupDAOFactory;
import com.ulearning.ulms.tools.backup.form.BackupForm;
import com.ulearning.ulms.util.LMSConstants;
import com.ulearning.ulms.util.log.LogUtil;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class AddBackupAction extends Action
{
        public ActionForward execute(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response)
                throws Exception
        {
                String resultScreen = "success";
                BackupForm pf = (BackupForm) form;

                BackupHelper backupHelper = new BackupHelper();
                boolean res = backupHelper.backupDB(pf);

                if (res)
                {
                        BackupDAO dao = BackupDAOFactory.getDAO();
                        pf.setCreateDate(new Date());
                        dao.addBackup(pf);
                }

                String userID = (String) request.getSession()
                        .getAttribute(LMSConstants.USERID_KEY);
                String orgID = (String) request.getSession()
                        .getAttribute(LMSConstants.USER_ORGID_KEY);

                LmslogForm lmslogForm = new LmslogForm();
                lmslogForm.setLogTypeID(LmslogConstants.LOGTYPE_SYSTEM);
                lmslogForm.setUserID(Integer.parseInt(userID));
                lmslogForm.setOrgID(Integer.parseInt(orgID));
                lmslogForm.setUserIP(request.getRemoteAddr());
                lmslogForm.setOperationTypeID(LmslogConstants.OPERATION_SYSTEM_AUTO_BACKUP);
                lmslogForm.setOperationTable("LMSDB");
                lmslogForm.setOperationObjectID(1);
                lmslogForm.setDescription("备份数据库名称:" + pf.getDbName() + ",:" +
                        pf.getFilePath() + ".");

                LmslogDAO dao = LmslogDAOFactory.getDAO();
                dao.insert(lmslogForm);

                LogUtil.info("admin",
                        "[AddBackupAction]===========resultScreen = " + resultScreen);

                // Forward to result page
                return mapping.findForward(resultScreen);
        }
}
