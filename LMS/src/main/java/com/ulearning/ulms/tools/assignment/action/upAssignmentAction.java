/**
 * upAssignmentAction.java.
 * User: liz  Date: 2006-6-16
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.tools.assignment.action;

import com.ulearning.ulms.tools.assignment.assignprocess.bean.AssignProcessHelper;
import com.ulearning.ulms.tools.assignment.assignprocess.dao.AssignProcessDAO;
import com.ulearning.ulms.tools.assignment.assignprocess.dao.AssignProcessDAOFactory;
import com.ulearning.ulms.tools.assignment.assignprocess.form.AssignProcessForm;
import com.ulearning.ulms.tools.upload.action.UploadAction;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class upAssignmentAction extends UploadAction
{
        public ActionForward execute(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response)
                throws Exception
        {
                AssignProcessForm frm = null;
                AssignProcessHelper helper = new AssignProcessHelper();

                //String relationID=request.getParameter("relationID");
                String[] assid = request.getParameterValues("assid");
                String[] score = request.getParameterValues("score");

                if (null != assid)
                {
                        AssignProcessDAO dao = AssignProcessDAOFactory.getDAO();
                        frm = new AssignProcessForm();

                        for (int i = 0; i < assid.length; i++)
                        {
                                frm = helper.getAssignProcess(Integer.parseInt(assid[i]));

                                if (!score[i].equals(""))
                                {
                                        //frm.setState("2");
                                        frm.setScore(score[i]);

                                        dao.updateAssignProcess(frm);
                                }
                        }
                }

                return mapping.findForward("success");
        }
}
