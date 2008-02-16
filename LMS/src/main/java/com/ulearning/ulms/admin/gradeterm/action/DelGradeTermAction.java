/**
 * Copyright (c) 2000-2005.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.admin.gradeterm.action;

import com.ulearning.ulms.admin.gradeterm.dao.GradeTermDAO;
import com.ulearning.ulms.admin.gradeterm.dao.GradeTermDAOFactory;

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
public class DelGradeTermAction extends Action
{
        public ActionForward execute(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response)
                throws Exception
        {
                String resultScreen = "success";
                String[] gradetID = request.getParameterValues("gradetID");
                GradeTermDAO dao = GradeTermDAOFactory.getDAO();

                for (int i = 0; i < gradetID.length; i++)
                {
                        dao.deleteGradeTerm(Integer.parseInt(gradetID[i]));
                }

                // Forward to result page
                return mapping.findForward(resultScreen);
        }
}
