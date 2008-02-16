/**
 * Copyright (c) 2000-2005.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.cert.Certificate.action;

import com.ulearning.ulms.cert.Certificate.dao.CertificateDAO;
import com.ulearning.ulms.cert.Certificate.dao.CertificateDAOFactory;
import com.ulearning.ulms.cert.Certificate.form.CertificateForm;
import com.ulearning.ulms.core.util.StringUtil;

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
public class UpdateCertificateAction extends Action
{
        public ActionForward execute(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response)
                throws Exception
        {
                String resultScreen = "success";
                CertificateDAO dao = CertificateDAOFactory.getDAO();
                CertificateForm tf = dao.getCercourseID(StringUtil.parseInt(
                        request.getParameter("courseid")));

                if (tf == null)
                {
                        CertificateForm tf2 = new CertificateForm();
                        tf2.setCourseID(StringUtil.parseInt(request.getParameter("courseid")));
                        dao.insertCertificate(tf2);
                        tf = dao.getCercourseID(StringUtil.parseInt(request.getParameter(
                                "courseid")));
                }

                tf.setCZuyeci(request.getParameter("Zuyeci"));
                tf.setCZuoyeGrade(request.getParameter("ZuoyeGrade"));
                tf.setCZuoyeAllGrade(request.getParameter("ZuoyeAllGrade"));
                tf.setCZuoyeMoren(request.getParameter("ZuoyeMoren"));
                tf.setCKaoshiID(request.getParameter("KaoshiID"));
                tf.setCKaoshiTitle(request.getParameter("KaoshiTitle"));
                tf.setCKaoshiBili(request.getParameter("KaoshiBili"));
                tf.setCKaoshiMoren(request.getParameter("KaoshiMoren"));
                request.setAttribute("isFromGraduateManage", "true");
                dao.updateCertificate(tf);

                // Forward to result page
                return mapping.findForward(resultScreen);
        }
}
