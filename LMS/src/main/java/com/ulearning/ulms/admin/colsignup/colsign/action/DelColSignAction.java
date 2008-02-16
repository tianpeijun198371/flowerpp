/**
 * Created by IntelliJ IDEA.
 * User: zengwj
 * Date: 2004-8-24
 * Time: 18:02:16
 * To change this template use File | Settings | File Templates.
 */
package com.ulearning.ulms.admin.colsignup.colsign.action;

import com.ulearning.ulms.admin.colsignup.colsign.dao.ColSignDAO;
import com.ulearning.ulms.admin.colsignup.colsign.dao.ColSignDAOFactory;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class DelColSignAction extends Action
{
        public ActionForward execute(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response)
                throws Exception
        {
                String resultScreen = "success";
                String[] colSignIDs = request.getParameterValues("colSignID");
                ColSignDAO DAO = ColSignDAOFactory.getDAO();

                for (int i = 0; i < colSignIDs.length; i++)
                {
                        DAO.removeColSign(Integer.parseInt(colSignIDs[i]));
                }

                return mapping.findForward(resultScreen);
        }
}
