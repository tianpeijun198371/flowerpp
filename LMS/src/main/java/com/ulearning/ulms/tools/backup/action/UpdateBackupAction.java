/**
 * Created by IntelliJ IDEA.
 * Backup: xiejh
 * Date: Apr 19, 2004
 * Time: 8:54:23 AM
 * To change this template use Options | File Templates.
 */
package com.ulearning.ulms.tools.backup.action;

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


public class UpdateBackupAction extends Action
{
        public ActionForward execute(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response)
                throws Exception
        {
                String resultScreen = "success";
                BackupForm uf = (BackupForm) form;
                BackupDAO dao = BackupDAOFactory.getDAO();
                dao.updateBackup(uf);
                LogUtil.info("admin",
                        "[UpdateBackupAction]===========resultScreen = " + resultScreen);

                // Forward to result page
                return mapping.findForward(resultScreen);
        }
}
