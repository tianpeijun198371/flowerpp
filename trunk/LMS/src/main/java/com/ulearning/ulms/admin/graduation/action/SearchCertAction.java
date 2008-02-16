package com.ulearning.ulms.admin.graduation.action;

import com.ulearning.ulms.admin.graduation.bean.GraduationHelper;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Created by IntelliJ IDEA.
 * User: huang
 * Date: 2007-7-31
 * Time: 16:19:25
 * To change this template use File | Settings | File Templates.
 */
public class SearchCertAction
{
        public ActionForward execute(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response)
                throws Exception
        {
                String resultScreen = "success";
                String loginName = request.getParameter("loginName");
                String name = request.getParameter("name");
                List graduateCertList = GraduationHelper.searchCert(loginName, name);
                request.setAttribute("graduateCertList", graduateCertList);

                return mapping.findForward(resultScreen);
        }
}
