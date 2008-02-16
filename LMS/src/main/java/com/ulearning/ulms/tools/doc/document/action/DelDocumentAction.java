/** * DocumentAction.java.
 * User: xiejh  Date: 2004-4-22 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.tools.doc.document.action;

import com.ulearning.ulms.tools.doc.document.dao.DocumentDAO;
import com.ulearning.ulms.tools.doc.document.dao.DocumentDAOFactory;
import com.ulearning.ulms.tools.doc.document.form.DocumentForm;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class DelDocumentAction extends Action
{
        public ActionForward execute(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response)
                throws Exception
        {
                String resultScreen = "success";
                String[] docID = request.getParameterValues("docID");
                DocumentDAO dao = DocumentDAOFactory.getDAO();

                for (int i = 0; i < docID.length; i++)
                {
                        dao.removeDocument(docID[i]);
                }

                // Forward to result page
                return mapping.findForward(resultScreen);
        }
}
