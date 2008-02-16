/**
 * Created by IntelliJ IDEA.
 * User: zengwj
 * Date: 2004-8-24
 * Time: 13:30:25
 * To change this template use File | Settings | File Templates.
 */
package com.ulearning.ulms.admin.colsignup.colstudent.action;

import com.ulearning.ulms.admin.colsignup.colstudent.bean.ColStudentHelper;
import com.ulearning.ulms.admin.colsignup.colstudent.form.ColStudentForm;
import com.ulearning.ulms.organ.bean.OrganHelper;
import com.ulearning.ulms.organ.form.OrganForm;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class SignUpByOrgAction extends Action
{
        public ActionForward execute(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response)
                throws Exception
        {
                String resultScreen = "success";
                ColStudentForm csf = (ColStudentForm) form;
                int ColSignDetailID = csf.getColSignDetailID();
                String[] orgIDs = request.getParameterValues("orgID");

                for (int i = 0; i < orgIDs.length; i++)
                {
                        ColStudentHelper.signUpByOrg(Integer.parseInt(orgIDs[i]),
                                ColSignDetailID);
                        signUpBySubOrg(Integer.parseInt(orgIDs[i]), ColSignDetailID);
                }

                request.setAttribute("catalogID", request.getParameter("orgID"));

                return mapping.findForward(resultScreen);
        }

        private void signUpBySubOrg(int orgID, int ColSignDetailID)
        {
                List orgList = OrganHelper.getOrganList(orgID);
                OrganForm of = null;
                int nowOrgID = 0;

                for (int i = 0; i < orgList.size(); i++)
                {
                        of = (OrganForm) orgList.get(i);
                        nowOrgID = of.getOrgID();
                        ColStudentHelper.signUpByOrg(nowOrgID, ColSignDetailID);
                        signUpBySubOrg(nowOrgID, ColSignDetailID);
                }
        }
}
