/**
 * AddSysConfigAction.java.
 * User: huangsb  Date: 2004-4-27
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd. * All rights reserved.
 */
package com.ulearning.ulms.admin.sysconfig.action;

import com.ulearning.ulms.admin.sysconfig.dao.SysConfigDAO;
import com.ulearning.ulms.admin.sysconfig.dao.SysConfigDAOFactory;
import com.ulearning.ulms.admin.sysconfig.form.SysConfigForm;
import com.ulearning.ulms.util.log.LogUtil;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class AddSysConfigAction extends Action
{
        public ActionForward excute(ActionMapping mapping, ActionForm form,
                                    HttpServletRequest request, HttpServletResponse response)
                throws Exception
        {
                String resultScreen = "success";
                SysConfigForm sf = (SysConfigForm) form;
                SysConfigDAO dao = SysConfigDAOFactory.getDAO();
                dao.addSysConfig(sf);

                LogUtil.info("system",
                        "[AddSysConfigAction]===========resultScreen = " + resultScreen);

                // Forward to result page
                return mapping.findForward(resultScreen);
        }
}
