/**
 * Created by IntelliJ IDEA.
 * User: zengwj
 * Date: 2004-6-15
 * Time: 17:47:05
 * To change this template use File | Settings | File Templates.
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


public class DelGradePaperAction extends Action
{
        public ActionForward execute(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response)
                throws Exception
        {
                String resultScreen = "success";
                GradeForm gf = (GradeForm) form;

                String[] paperIDs = request.getParameterValues("paperIDs");
                gf.setPaperID(paperIDs);

                //To use Grade DAO insert a Grade to system
                GradeDAO dao = GradeDAOFactory.getDAO();
                dao.RemoveGradePaper(gf);

                // Forward to result page
                return mapping.findForward(resultScreen);
        }
}
