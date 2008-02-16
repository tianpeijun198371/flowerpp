/**
 * Copyright (c) 2000-2005.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.admin.gradeterm.action;

import com.ulearning.ulms.admin.gradeterm.dao.GradeTermDAO;
import com.ulearning.ulms.admin.gradeterm.dao.GradeTermDAOFactory;
import com.ulearning.ulms.admin.gradeterm.form.GradeTermForm;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Class description goes here.
 * <p/>
 * Created by Auto Code Produce System
 * User: xiejh
 * Date: 20060321
 * Time: 182730
 */
public class UpdateGradeTermAction extends Action
{
        public ActionForward execute(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response)
                throws Exception
        {
                String resultScreen = "success";
                GradeTermForm tf = (GradeTermForm) form;
                GradeTermDAO dao = GradeTermDAOFactory.getDAO();
                dao.updateGradeTerm(tf);

                // Forward to result page
                return mapping.findForward(resultScreen);
        }
}
