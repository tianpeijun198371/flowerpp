/**
 * publishCCommendAction.java.
 * User: zengwj Date: 2005-5-19 15:14:13
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.admin.commendCourse.action;

import com.ulearning.ulms.admin.commendCourse.dao.CCommendDAO;
import com.ulearning.ulms.admin.commendCourse.dao.CCommendDAOFactory;
import com.ulearning.ulms.admin.commendCourse.form.CCommendForm;
import com.ulearning.ulms.util.log.LogUtil;

import org.apache.struts.action.*;

import javax.servlet.http.*;


public class publishCCommendAction extends Action
{
        public ActionForward execute(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response)
                throws Exception
        {
                //coding here
                String resultScreen = "success";
                LogUtil.info("system",
                        "[AddCCommendAction]========================Begin");

                String[] ids = request.getParameterValues("ID");
                String is_display = "0";

                if (request.getParameter("is_display") != null)
                {
                        is_display = request.getParameter("is_display");
                }

                CCommendForm cCommendForm = null;
                int ccourseID = 0;
                CCommendDAO DAO = CCommendDAOFactory.getDAO();

                for (int i = 0; (ids != null) && (i < ids.length); i++)
                {
                        ccourseID = Integer.parseInt(ids[i]);
                        cCommendForm = DAO.getCCommend(ccourseID);
                        cCommendForm.setDisplayed(Integer.parseInt(is_display));
                        DAO.updateCCommend(cCommendForm);
                }

                LogUtil.info("system", "[AddCCommendAction]========================End");

                return mapping.findForward(resultScreen);
        }
}
