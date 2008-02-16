/**
 * Created by IntelliJ IDEA.
 * User: zengwj
 * Date: 2004-8-25
 * Time: 14:31:59
 * To change this template use File | Settings | File Templates.
 */
package com.ulearning.ulms.admin.colsignup.colstudent.action;

import com.ulearning.ulms.admin.colsignup.colstudent.bean.ColStudentHelper;
import com.ulearning.ulms.admin.colsignup.colstudent.dao.ColStudentDAO;
import com.ulearning.ulms.admin.colsignup.colstudent.dao.ColStudentDAOFactory;
import com.ulearning.ulms.admin.colsignup.colstudent.form.ColStudentForm;
import com.ulearning.ulms.core.security.bean.SecurityConstants;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class DelColSignUpStu extends Action
{
        public ActionForward execute(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response)
                throws Exception
        {
                String resultScreen = "success";
                String[] relationIDs = request.getParameterValues("relationID");
                String colSignDetailID = request.getParameter("colSignDetailID");
                ColStudentDAO DAO = ColStudentDAOFactory.getDAO();

                for (int i = 0; i < relationIDs.length; i++)
                {
                        DAO.removeColStudent(Integer.parseInt(colSignDetailID),
                                Integer.parseInt(relationIDs[i]),
                                SecurityConstants.DEFAULT_USERID);
                }

                return mapping.findForward(resultScreen);
        }
}
