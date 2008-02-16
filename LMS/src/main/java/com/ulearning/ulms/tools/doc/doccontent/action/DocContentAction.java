/** * DocContentAction.java.
 * User: xiejh  Date: 2004-4-22 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.tools.doc.doccontent.action;

import com.ulearning.ulms.tools.doc.doccontent.dao.DocContentDAO;
import com.ulearning.ulms.tools.doc.doccontent.dao.DocContentDAOFactory;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class DocContentAction extends Action
{
        public ActionForward execute(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response)
                throws Exception
        {
                String resultScreen = "success";
                String docID = request.getParameter("docID");

                DocContentDAO dao = DocContentDAOFactory.getDAO();
                dao.removeDocContent(docID);

                // Forward to result page
                return mapping.findForward(resultScreen);
        }
}
