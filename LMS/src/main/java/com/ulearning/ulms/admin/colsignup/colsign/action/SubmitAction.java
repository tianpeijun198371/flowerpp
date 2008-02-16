/**
 * Created by IntelliJ IDEA.
 * User: zengwj
 * Date: 2004-8-24
 * Time: 17:47:45
 * To change this template use File | Settings | File Templates.
 */
package com.ulearning.ulms.admin.colsignup.colsign.action;

import com.ulearning.ulms.admin.colsignup.colsign.bean.ColSignHelper;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class SubmitAction extends Action
{
        public ActionForward execute(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response)
                throws Exception
        {
                String resultScreen = "success";
                String[] colSignIDs = request.getParameterValues("colSignID");
                String isSubmit = request.getParameter("isSubmit");

                for (int i = 0; i < colSignIDs.length; i++)
                {
                        ColSignHelper.submitColSign(Integer.parseInt(colSignIDs[i]),
                                isSubmit);
                }

                return mapping.findForward(resultScreen);
        }
}
