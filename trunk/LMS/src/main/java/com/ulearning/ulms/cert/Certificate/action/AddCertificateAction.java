/**
 * Copyright (c) 2000-2005.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.cert.Certificate.action;

import com.ulearning.ulms.cert.Certificate.dao.CertificateDAO;
import com.ulearning.ulms.cert.Certificate.dao.CertificateDAOFactory;
import com.ulearning.ulms.cert.Certificate.form.CertificateForm;

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
 * Date: 20060109
 * Time: 101040
 */
public class AddCertificateAction extends Action
{
        public ActionForward execute(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response)
                throws Exception
        {
                String resultScreen = "success";
                CertificateForm tf = (CertificateForm) form;
                CertificateDAO dao = CertificateDAOFactory.getDAO();
                dao.insertCertificate(tf);

                // Forward to result page
                return mapping.findForward(resultScreen);
        }
}
