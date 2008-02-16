/**
 * Created by IntelliJ IDEA.
 * User: zengwj
 * Date: 2004-8-24
 * Time: 18:57:45
 * To change this template use File | Settings | File Templates.
 */
package com.ulearning.ulms.admin.colsignup.colsign.action;

import com.ulearning.ulms.admin.colsignup.colsign.dao.ColSignDAO;
import com.ulearning.ulms.admin.colsignup.colsign.dao.ColSignDAOFactory;
import com.ulearning.ulms.admin.colsignup.colsign.form.ColSignForm;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class ModifySignUpAction extends Action
{
        public ActionForward execute(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response)
                throws Exception
        {
                String resultScreen = "success";
                ColSignForm csf = (ColSignForm) form;

                if (csf != null)
                {
                        ColSignDAO DAO = ColSignDAOFactory.getDAO();
                        DAO.updateColSign(csf);
                }

                return mapping.findForward(resultScreen);
        }
}
