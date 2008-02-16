/** * UpdateCustomAction.java.
 * User: xiejh  Date: 2004-7-5 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.admin.webconfig.webcustom.action;

import com.ulearning.ulms.admin.webconfig.webcustom.dao.WebCustomDAO;
import com.ulearning.ulms.admin.webconfig.webcustom.dao.WebCustomDAOFactory;
import com.ulearning.ulms.admin.webconfig.webcustom.form.WebCustomForm;
import com.ulearning.ulms.util.log.LogUtil;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class UpdateCustomAction extends Action
{
        public ActionForward execute(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response)
                throws Exception
        {
                String resultScreen = "success";
                LogUtil.info("system", "[AddCustomAction]===========Begin");

                WebCustomForm wcf = (WebCustomForm) form;
                WebCustomDAO dao = WebCustomDAOFactory.getDAO();

                if (wcf.getCustomID() == 0)
                {
                        dao.addWebCustom(wcf);
                }
                else
                {
                        dao.updateWebCustom(wcf);
                }

                HashMap myConfig = (HashMap) request.getSession(true)
                        .getAttribute("myConfig");
                myConfig.put("cssFileNo", String.valueOf(wcf.getConfigTypeName()));
                request.getSession().setAttribute("myConfig", myConfig);

                LogUtil.info("system",
                        "[AddCustomAction]===========resultScreen = " + resultScreen);

                // Forward to result page
                return mapping.findForward(resultScreen);
        }
}
