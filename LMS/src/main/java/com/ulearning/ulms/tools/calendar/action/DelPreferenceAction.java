/**
 * DelPreferenceAction.java.
 * User: keyh  Date: 2004-8-4
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.tools.calendar.action;

import com.ulearning.ulms.tools.calendar.dao.PreferenceDAO;
import com.ulearning.ulms.tools.calendar.dao.PreferenceDAOFactory;
import com.ulearning.ulms.util.log.LogUtil;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class DelPreferenceAction extends Action
{
        public ActionForward execute(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response)
                throws Exception
        {
                String resultScreen = "success";
                int userID = Integer.parseInt(request.getParameter("userID"));
                PreferenceDAO dao = PreferenceDAOFactory.getDAO();
                dao.delete(userID);

                LogUtil.info("system",
                        "[DelPreferenceAction]===========resultScreen = " + resultScreen);

                // Forward to result page
                return mapping.findForward(resultScreen);
        }
}
