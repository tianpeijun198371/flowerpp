/**
 * Created by IntelliJ IDEA.
 * User: zengwj
 * Date: 2004-7-8
 * Time: 17:39:08
 * To change this template use File | Settings | File Templates.
 */
package com.ulearning.ulms.admin.certificate.action;

import com.ulearning.ulms.admin.certificate.dao.CertDAO;
import com.ulearning.ulms.admin.certificate.dao.CertDAOFactory;
import com.ulearning.ulms.admin.certificate.form.CertCourseForm;
import com.ulearning.ulms.util.log.LogUtil;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class DelCertCourseAction extends Action
{
        public ActionForward execute(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response)
                throws Exception
        {
                String resultScreen = "success";
                String certificateID = "-1";
                certificateID = request.getParameter("masterID");

                String type = request.getParameter("type");
                String[] courseIDS = request.getParameterValues("courseIDS");
                CertDAO certDao = CertDAOFactory.getDAO();

                for (int i = 0; i < courseIDS.length; i++)
                {
                        CertCourseForm ccf = new CertCourseForm();
                        ccf.setCertificateID(Integer.parseInt(certificateID));
                        ccf.setCourseID(Integer.parseInt(courseIDS[i]));
                        ccf.setType(Integer.parseInt(type));
                        certDao.removeCourseFromCert(ccf);
                }

                // Forward to result page
                return mapping.findForward(resultScreen);
        }
}
