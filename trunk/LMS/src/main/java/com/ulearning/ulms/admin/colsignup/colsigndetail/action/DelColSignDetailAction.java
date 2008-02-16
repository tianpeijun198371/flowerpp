/**
 * Created by IntelliJ IDEA.
 * User: zengwj
 * Date: 2004-8-25
 * Time: 13:08:02
 * To change this template use File | Settings | File Templates.
 */
package com.ulearning.ulms.admin.colsignup.colsigndetail.action;

import com.ulearning.ulms.admin.colsignup.colsigndetail.bean.ColSignDetailHelper;
import com.ulearning.ulms.admin.colsignup.colsigndetail.dao.ColSignDetailDAO;
import com.ulearning.ulms.admin.colsignup.colsigndetail.dao.ColSignDetailDAOFactory;
import com.ulearning.ulms.admin.colsignup.colsigndetail.form.ColSignDetailForm;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class DelColSignDetailAction extends Action
{
        public ActionForward execute(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response)
                throws Exception
        {
                String resultScreen = "success";

                int colSignDetailID = 0;

                if (request.getParameter("colSignDetailID") != null)
                {
                        colSignDetailID = Integer.parseInt(request.getParameter(
                                "colSignDetailID"));
                }

                if (colSignDetailID != 0)
                {
                        ColSignDetailDAO DAO = ColSignDetailDAOFactory.getDAO();
                        DAO.removeColSignDetail(colSignDetailID);
                }

                return mapping.findForward(resultScreen);
        }
}
