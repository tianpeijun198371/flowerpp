/**
 * Created by IntelliJ IDEA.
 * Grade: dengj
 * Date: Apr 8, 2004
 * Time: 3:15:39 PM
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

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class AddGradeAction extends Action
{
        public ActionForward execute(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response)
                throws Exception
        {
                String resultScreen = "success";
                GradeForm gf = (GradeForm) form;
                int GradeID = 0;
                GradeDAO dao = GradeDAOFactory.getDAO();

                //To use Grade DAO insert a Grade to system
                if (!request.getParameter("GradeID").equals("0"))
                {
                        GradeID = Integer.parseInt(request.getParameter("GradeID"));
                }
                else
                {
                        GradeID = dao.addGrade(gf);
                }

                //add paper info list to grade
                String[] paperIDS = request.getParameterValues("paperID");
                gf.setGradeID(GradeID);
                gf.setPaperID(paperIDS);
                gf.setRelationID(1);
                gf.setCreateTime(new Date());
                dao.addPaper2Grade(gf);

                int courseID = gf.getCourseID();
                request.setAttribute("courseID", String.valueOf(courseID));
                request.setAttribute("GradeID", String.valueOf(GradeID));

                // Forward to result page
                return mapping.findForward(resultScreen);
        }
}
