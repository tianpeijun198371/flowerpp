/**
 * Copyright (c) 2000-2005.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.cert.Certificate.action;

import com.ulearning.ulms.cert.Certificate.dao.CertificateDAO;
import com.ulearning.ulms.cert.Certificate.dao.CertificateDAOFactory;

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
public class DelCertificateAction extends Action
{
        public ActionForward execute(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response)
                throws Exception
        {
                String resultScreen = "success";
                String[] CtifiID = request.getParameterValues("CtifiID");
                CertificateDAO dao = CertificateDAOFactory.getDAO();

                for (int i = 0; i < CtifiID.length; i++)
                {
                        dao.deleteCertificate(Integer.parseInt(CtifiID[i]));
                }

                // Forward to result page
                return mapping.findForward(resultScreen);
        }
}
