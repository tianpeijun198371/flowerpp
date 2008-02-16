/**
 * Created by IntelliJ IDEA.
 * Grade: dengj
 * Date: Apr 19, 2004
 * Time: 8:54:23 AM
 * To change this template use Options | File Templates.
 */
package com.ulearning.ulms.course.test.grade.action;

import com.ulearning.ulms.course.test.grade.dao.GradeDAO;
import com.ulearning.ulms.course.test.grade.dao.GradeDAOFactory;
import com.ulearning.ulms.course.test.grade.form.GradeForm;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class UpdateGradeAction extends Action
{
        public ActionForward execute(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response)
                throws Exception
        {
                String resultScreen = "success";
                GradeForm gf = (GradeForm) form;
                String courseID = request.getParameter("courseID");
                request.setAttribute("courseID", courseID);

                //To use Grade DAO insert a Grade to system
                GradeDAO dao = GradeDAOFactory.getDAO();
                dao.updateGrade(gf);

                // Forward to result page
                return mapping.findForward(resultScreen);
        }
}
