/** * UpdateServiceItemConfigAction.java.
 * User: chenxj  Date: 2004-8-18
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved. */
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


public class UpdateServiceItemConfigAction extends Action
{
        public ActionForward execute(ActionMapping actionMapping,
                                     ActionForm actionForm, HttpServletRequest httpServletRequest,
                                     HttpServletResponse httpServletResponse) throws Exception
        {
                String resultScreen = "success";
                SysConfigForm sf = (SysConfigForm) actionForm;
                SysConfigDAO dao = SysConfigDAOFactory.getDAO();
                dao.updateServiceItemConfig(sf);
                LogUtil.info("system",
                        "[AddSysConfigAction]===========resultScreen = " + resultScreen);

                return actionMapping.findForward(resultScreen);
        }
}
