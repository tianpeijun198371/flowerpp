/**
 * Copyright (c) 2000-2005.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.admin.gradeuser.action;

import com.ulearning.ulms.admin.gradeuser.dao.GradeUserDAO;
import com.ulearning.ulms.admin.gradeuser.dao.GradeUserDAOFactory;

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
public class DelGradeUserAction extends Action
{
        public ActionForward execute(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response)
                throws Exception
        {
                String resultScreen = "success";
                String[] gradeUserID = request.getParameterValues("gradeUserID");
                GradeUserDAO dao = GradeUserDAOFactory.getDAO();

                for (int i = 0; i < gradeUserID.length; i++)
                {
                        dao.deleteGradeUser(Integer.parseInt(gradeUserID[i]));
                }

                // Forward to result page
                return mapping.findForward(resultScreen);
        }
}
