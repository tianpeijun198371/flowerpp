/**
 * Created by IntelliJ IDEA.
 * User: zengwj
 * Date: 2004-8-24
 * Time: 16:00:33
 * To change this template use File | Settings | File Templates.
 */
package com.ulearning.ulms.admin.colsignup.colstudent.action;

import com.ulearning.ulms.admin.colsignup.colstudent.bean.ColStudentHelper;
import com.ulearning.ulms.admin.colsignup.colstudent.form.ColStudentForm;
import com.ulearning.ulms.core.security.bean.SecurityConstants;
import com.ulearning.ulms.util.log.LogUtil;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class SignUpByStuAction extends Action
{
        public ActionForward execute(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response)
                throws Exception
        {
                String resultScreen = "success";
                ColStudentForm csf = (ColStudentForm) form;
                String[] UserIDs = request.getParameterValues("userID");

                int orgID = 0;

                if (request.getParameter("orgID") != null)
                {
                        orgID = Integer.parseInt(request.getParameter("orgID"));
                }

                LogUtil.debug("SignUpByStuAction",
                        "orgID =========================" + orgID);
                ColStudentHelper.removeByOrg(orgID, csf.getColSignDetailID());

                LogUtil.debug("SignUpByStuAction", "删除以前的学生记录选");

                for (int i = 0; i < UserIDs.length; i++)
                {
                        csf.setRelationID(Integer.parseInt(UserIDs[i]));
                        csf.setApproved("0");
                        csf.setFeeState("0");
                        csf.setType(SecurityConstants.USER_DEFAULT_RELATION);
                        ColStudentHelper.addUser(csf);
                }

                request.setAttribute("catalogID", String.valueOf(orgID));

                return mapping.findForward(resultScreen);
        }
}
