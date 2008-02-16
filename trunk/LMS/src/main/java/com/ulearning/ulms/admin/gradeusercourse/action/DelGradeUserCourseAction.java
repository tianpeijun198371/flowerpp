/**
 * Copyright (c) 2000-2005.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.admin.gradeusercourse.action;

import com.ulearning.ulms.admin.gradeusercourse.dao.GradeUserCourseDAO;
import com.ulearning.ulms.admin.gradeusercourse.dao.GradeUserCourseDAOFactory;

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
public class DelGradeUserCourseAction extends Action
{
        public ActionForward execute(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response)
                throws Exception
        {
                String resultScreen = "success";
                String[] gradeUCID = request.getParameterValues("gradeUCID");
                GradeUserCourseDAO dao = GradeUserCourseDAOFactory.getDAO();

                for (int i = 0; i < gradeUCID.length; i++)
                {
                        dao.deleteGradeUserCourse(Integer.parseInt(gradeUCID[i]));
                }

                // Forward to result page
                return mapping.findForward(resultScreen);
        }
}
