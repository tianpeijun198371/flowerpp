/**
 * DelMatchAction.java.
 * User: zhangy Date: 2005-6-2 15:27:55
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.match.action;

import com.ulearning.ulms.match.dao.MatchDaoFactory;
import com.ulearning.ulms.match.dao.MatchItermDaoFactory;
import com.ulearning.ulms.util.log.LogUtil;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class DelMatchAction extends Action
{
        public ActionForward execute(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response)
                throws Exception
        {
                String resultScreen = "success";

                String[] ss = null;
                ss = (String[]) request.getParameterValues("radio");

                for (int i = 0; i < ss.length; i++)
                {
                        MatchDaoFactory.getDAO().delMatch(Integer.parseInt(ss[i]));
                        MatchItermDaoFactory.getDAO().delMatchIterm(Integer.parseInt(ss[i]));
                }

                LogUtil.debug("system",
                        "[DelMatchAction]===========resultScreen = " + resultScreen);

                // Forward to result page
                return mapping.findForward(resultScreen);
        }
}
