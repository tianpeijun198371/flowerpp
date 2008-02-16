/** * DocumentContentAction.java.
 * User: xiejh  Date: 2004-4-22 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.tools.doc.documentcontent.action;

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

import java.io.Serializable;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class AddDocumentContentAction extends Action
{
        /*
        *     给document 和 doccontent表同时添加数据
        */
        public ActionForward execute(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response)
                throws Exception
        {
                String resultScreen = "success";
                DocumentContentForm dcf = (DocumentContentForm) form;
                String isOpenGuest = request.getParameter("isOpenGuest");
                dcf.setOpenGuest(isOpenGuest);
                System.out.println("dcf.getOpenGuest();==========" + dcf.getOpenGuest());
                DocumentForm df = dcf.getDocumentForm();

                System.out.println("df.getOpenGuest();==========" + df.getOpenGuest());
                DocContentForm dc = dcf.getDocContentForm();

                DocumentDAO dao = DocumentDAOFactory.getDAO();
                Date nowDate = new Date();
                df.setCreateDate(nowDate);
                df.setModifyDate(nowDate);

                Serializable s = dao.addDocument(df);
                LogUtil.info("system",
                        "[AddDocumentAction]===========resultScreen = " + resultScreen);
                dc.setDocID(s.hashCode());

                DocContentDAO dao1 = DocContentDAOFactory.getDAO();
                dao1.addDocContent(dc);
                LogUtil.info("system",
                        "[AddDocContentAction]===========resultScreen = " + resultScreen);

                //request.setAttribute("relationID",dcf.getRelationID() + "");
                ActionForward forward = mapping.findForward("success");
                StringBuffer bf = new StringBuffer(forward.getPath());
                bf.append("?relationID=" + dcf.getRelationID());

                return new ActionForward(bf.toString());

                // Forward to result page
                //return mapping.findForward(resultScreen);
        }
}
