/** * DocumentContentAction.java.
 * User: xiejh  Date: 2004-4-22 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.tools.doc.documentcontent.action;

import com.ulearning.ulms.core.util.DateTimeUtil;
import com.ulearning.ulms.tools.doc.doccontent.dao.DocContentDAO;
import com.ulearning.ulms.tools.doc.doccontent.dao.DocContentDAOFactory;
import com.ulearning.ulms.tools.doc.doccontent.form.DocContentForm;
import com.ulearning.ulms.tools.doc.document.dao.DocumentDAO;
import com.ulearning.ulms.tools.doc.document.dao.DocumentDAOFactory;
import com.ulearning.ulms.tools.doc.document.form.DocumentForm;
import com.ulearning.ulms.tools.doc.documentcontent.form.DocumentContentForm;
import com.ulearning.ulms.util.log.LogUtil;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class UpdateDocumentContentAction extends Action
{
        public ActionForward execute(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response)
                throws Exception
        {
                String resultScreen = "success";
                DocumentContentForm dcf = (DocumentContentForm) form;

                DocumentForm df = dcf.getDocumentForm();
                DocContentForm dc = dcf.getDocContentForm();

                DocumentDAO dao = DocumentDAOFactory.getDAO();
                df.setCreateDate(DateTimeUtil.toDate(df.getCreateDateStr()));

                Date nowDate = new Date();
                df.setModifyDate(nowDate);
                dao.updateDocument(df);

                LogUtil.info("system",
                        "[UpdateDocumentAction]===========resultScreen = " + resultScreen);

                DocContentDAO dao1 = DocContentDAOFactory.getDAO();
                dao1.updateDocContent(dc);

                LogUtil.info("system",
                        "[UpdateDocumentContentAction]===========resultScreen = " +
                                resultScreen);

                // Forward to result page
                return mapping.findForward(resultScreen);
        }
}
