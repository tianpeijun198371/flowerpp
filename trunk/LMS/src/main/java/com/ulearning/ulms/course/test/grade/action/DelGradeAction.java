/**
 * Created by IntelliJ IDEA.
 * Grade: dengj
 * Date: Apr 14, 2004
 * Time: 12:05:08 PM
 * To change this template use Options | File Templates.
 */
package com.ulearning.ulms.course.test.grade.action;

import com.ulearning.ulms.course.test.grade.dao.GradeDAO;
import com.ulearning.ulms.course.test.grade.dao.GradeDAOFactory;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class DelGradeAction extends Action
{
        public ActionForward execute(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response)
                throws Exception
        {
                String resultScreen = "success";

                GradeDAO dao = GradeDAOFactory.getDAO();
                String[] GradeIDList = request.getParameterValues("gradeID");

                if ((GradeIDList != null) || (GradeIDList.length > 0))
                {
                        for (int i = 0; i < GradeIDList.length; i++)
                        {
                                dao.removeGrade(GradeIDList[i]);
                        }
                }

                // Forward to result page
                return mapping.findForward(resultScreen);
        }
}
