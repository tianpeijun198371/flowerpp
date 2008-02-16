/**
 * Created by IntelliJ IDEA.
 * Book: dengj
 * Date: Apr 8, 2004
 * Time: 3:15:39 PM
 * To change this template use Options | File Templates.
 */
package com.ulearning.ulms.admin.book.action;

import com.ulearning.ulms.admin.book.dao.BookDAO;
import com.ulearning.ulms.admin.book.dao.BookDAOFactory;
import com.ulearning.ulms.admin.book.form.BookForm;
import com.ulearning.ulms.core.util.DateTimeUtil;
import com.ulearning.ulms.core.util.StringUtil;
import com.ulearning.ulms.util.log.LogUtil;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class AddBookAction extends Action
{
        public ActionForward execute(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response)
                throws Exception
        {
                String resultScreen = "success";
                BookForm pf = (BookForm) form;
                BookDAO dao = BookDAOFactory.getDAO();
                pf.setCreateDate(new Date());

                String[] tmp = StringUtil.splitString(pf.getPublishDateStr(), "-");
                pf.setPublishDate(DateTimeUtil.toDate(tmp[1], tmp[2], tmp[0], "0", "0",
                        "0"));
                //System.out.println(pf.getPublishDate());
                dao.addBook(pf);

                LogUtil.info("admin",
                        "[AddBookAction]===========resultScreen = " + resultScreen);

                // Forward to result page
                return mapping.findForward(resultScreen);
        }
}
