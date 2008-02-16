/**
 * Created by IntelliJ IDEA.
 * Book: dengj
 * Date: Apr 14, 2004
 * Time: 12:05:08 PM
 * To change this template use Options | File Templates.
 */
package com.ulearning.ulms.admin.book.action;

import com.ulearning.ulms.admin.book.dao.BookDAO;
import com.ulearning.ulms.admin.book.dao.BookDAOFactory;
import com.ulearning.ulms.admin.book.form.BookForm;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class DelBookAction extends Action
{
        public ActionForward execute(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response)
                throws Exception
        {
                String resultScreen = "success";
                String[] bookID = request.getParameterValues("bookID");
                BookDAO dao = BookDAOFactory.getDAO();

                for (int i = 0; i < bookID.length; i++)
                {
                        dao.removeBook(bookID[i]);
                }

                // Forward to result page
                return mapping.findForward(resultScreen);
        }
}
