/**
 * AddCalendarAction.java.
 * User: keyh  Date: 2004-8-4
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.tools.calendar.action;

import com.ulearning.ulms.core.util.StringUtil;
import com.ulearning.ulms.tools.calendar.dao.CalendarDAO;
import com.ulearning.ulms.tools.calendar.dao.CalendarDAOFactory;
import com.ulearning.ulms.tools.calendar.form.EventForm;
import com.ulearning.ulms.tools.calendar.model.EventModel;
import com.ulearning.ulms.util.log.LogUtil;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class AddCalendarAction extends Action
{
        public ActionForward execute(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response)
                throws Exception
        {
                String resultScreen = "success";

                EventForm ef = (EventForm) form;
                CalendarDAO dao = CalendarDAOFactory.getDAO();
                dao.insert(ef);

                LogUtil.info("system",
                        "[AddCalendarAction]===========resultScreen = " + resultScreen);

                // Forward to result page
                return mapping.findForward(resultScreen);
        }
}
