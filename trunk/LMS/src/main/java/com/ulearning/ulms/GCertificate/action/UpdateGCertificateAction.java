/**
 * Copyright (c) 2000-2005.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.GCertificate.action;

import com.ulearning.ulms.GCertificate.dao.GCertificateDAO;
import com.ulearning.ulms.GCertificate.dao.GCertificateDAOFactory;
import com.ulearning.ulms.GCertificate.form.GCertificateForm;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Class description goes here.
 * <p/>
 * Created by Auto Code Produce System
 * User: xiejh
 * Date: 20060111
 * Time: 110302
 */
public class UpdateGCertificateAction extends Action
{
        public ActionForward execute(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response)
                throws Exception
        {
                String resultScreen = "success";
                GCertificateForm tf = (GCertificateForm) form;
                GCertificateDAO dao = GCertificateDAOFactory.getDAO();
                dao.updateGCertificate(tf);

                // Forward to result page
                return mapping.findForward(resultScreen);
        }
}
