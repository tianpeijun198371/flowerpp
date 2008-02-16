/**
 * Created by IntelliJ IDEA.
 * User: zengwj
 * Date: 2004-8-19
 * Time: 16:59:17
 * To change this template use File | Settings | File Templates.
 */
package com.ulearning.ulms.tools.doc.documentcontent.action;

import com.ulearning.ulms.tools.doc.document.bean.DocumentHelper;
import com.ulearning.ulms.tools.doc.document.dao.DocumentDAO;
import com.ulearning.ulms.tools.doc.document.dao.DocumentDAOFactory;
import com.ulearning.ulms.tools.doc.document.form.DocumentForm;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class PublishDocumentContentAction extends Action
{
        public ActionForward execute(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response)
                throws Exception
        {
                String resultScreen = "success";
                String[] docID = request.getParameterValues("docID");
                DocumentForm df = new DocumentForm();
                DocumentDAO dao = DocumentDAOFactory.getDAO();
                String isView = "0";
                String nowView = "";

                for (int i = 0; i < docID.length; i++)
                {
                        isView = "0";
                        df = dao.getDocument(Integer.parseInt(docID[i]));
                        nowView = df.getView();

                        if (nowView.equals("0"))
                        {
                                isView = "1";
                        }

                        df.setView(isView);
                        dao.updateDocument(df);
                }

                // Forward to result page
                return mapping.findForward(resultScreen);
        }
}
