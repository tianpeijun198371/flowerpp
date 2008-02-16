/**
 * Created by IntelliJ IDEA.
 * User: zengwj
 * Date: 2004-8-23
 * Time: 17:24:34
 * To change this template use File | Settings | File Templates.
 */
package com.ulearning.ulms.admin.colsignup.colsigndetail.action;

import com.ulearning.ulms.admin.colsignup.colsigndetail.bean.ColSignDetailHelper;
import com.ulearning.ulms.admin.colsignup.colsigndetail.form.ColSignDetailForm;
import com.ulearning.ulms.core.security.bean.SecurityConstants;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class AddColSignDetailAction extends Action
{
        public ActionForward execute(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response)
                throws Exception
        {
                String resultScreen = "success";
                ColSignDetailForm csf = (ColSignDetailForm) form;
                int colSignDetailID = ColSignDetailHelper.addColSignDetail(csf);

                if (colSignDetailID != 0)
                {
                        request.setAttribute("colSignDetailID",
                                String.valueOf(colSignDetailID));
                }

                return mapping.findForward(resultScreen);
        }
}
