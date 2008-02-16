/**
 * DelCertAction.java.
 * User: huangsb  Date: 2004-4-27
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd. * All rights reserved.
 */
package com.ulearning.ulms.admin.certificate.action;

import com.ulearning.ulms.admin.certificate.bean.CertHelper;
import com.ulearning.ulms.admin.certificate.dao.CertDAO;
import com.ulearning.ulms.admin.certificate.dao.CertDAOFactory;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class DelCertAction extends Action
{
        public ActionForward execute(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response)
                throws Exception
        {
                String resultScreen = "success";

                ArrayList al = new ArrayList();
                String[] certificateIDs = request.getParameterValues("certificateIDs");
                String[] certCatalogIDs = request.getParameterValues("catalogIDs");

                CertDAO dao = CertDAOFactory.getDAO();

                if (certificateIDs != null)
                {
                        for (int i = 0; i < certificateIDs.length; i++)
                        {
                                al.add(new Integer(certificateIDs[i]));
                                CertHelper.deletedRelationData(Integer.parseInt(
                                        certificateIDs[i]));
                        }
                }

                dao.deleteCert(al);

                if (certCatalogIDs != null)
                {
                        al = new ArrayList();

                        for (int i = 0; i < certCatalogIDs.length; i++)
                        {
                                al.add(new Integer(certCatalogIDs[i]));
                        }
                }

                dao.deleteCatalog(al);

                return mapping.findForward(resultScreen);
        }
}
