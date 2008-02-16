/**
 * DelCatalogAction.java.
 * User: huangsb  Date: 2004-5-19
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd. * All rights reserved.
 */
package com.ulearning.ulms.admin.certificate.action;

import com.ulearning.ulms.admin.certificate.dao.CertDAO;
import com.ulearning.ulms.admin.certificate.dao.CertDAOFactory;
import com.ulearning.ulms.course.dao.MasterDAO;
import com.ulearning.ulms.course.dao.MasterDAOFactory;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class DelCatalogAction extends Action
{
        public ActionForward execute(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response)
                throws Exception
        {
                String resultScreen = "success";

                String[] catalogIDs;

                ArrayList al = new ArrayList();
                catalogIDs = request.getParameterValues("catalogIDs");

                if (catalogIDs != null)
                {
                        for (int i = 0; i < catalogIDs.length; i++)
                        {
                                al.add(new Integer(catalogIDs[i]));
                        }
                }

                CertDAO dao = CertDAOFactory.getDAO();
                dao.deleteCatalog(al);

                return mapping.findForward(resultScreen);
        }
}
