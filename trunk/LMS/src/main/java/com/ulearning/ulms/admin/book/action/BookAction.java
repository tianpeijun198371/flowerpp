/** * BookAction.java.
 * User: xiejh  Date: 2004-4-22 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.admin.book.action;

import com.ulearning.ulms.admin.book.dao.BookDAO;
import com.ulearning.ulms.admin.book.dao.BookDAOFactory;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class BookAction extends Action
{
        public ActionForward execute(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response)
                throws Exception
        {
                String resultScreen = "success";
                String bookID = request.getParameter("bookID");

                String type = request.getParameter("type");

                BookDAO dao = BookDAOFactory.getDAO();
                dao.removeBook(bookID);

                // Forward to result page
                return mapping.findForward(resultScreen);
        }
}
