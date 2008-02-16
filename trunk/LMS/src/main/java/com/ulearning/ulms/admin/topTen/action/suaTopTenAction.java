/**
 * Created by IntelliJ IDEA.
 * author: houct
 * Date: 2005/04/08
 * Time: 10:58:23 AM
 * To change this template use Options | File Templates.
 */
package com.ulearning.ulms.admin.topTen.action;

import com.ulearning.ulms.admin.topTen.bean.TopTenHelper;
import com.ulearning.ulms.admin.topTen.dao.TopTenDAO;
import com.ulearning.ulms.admin.topTen.dao.TopTenDAOFactory;
import com.ulearning.ulms.admin.topTen.form.TopTenForm;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class suaTopTenAction extends Action
{
        public ActionForward execute(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response)
                throws Exception
        {
                String resultScreen = "success";
                String[] ID = request.getParameterValues("ID");
                int month1 = 0;

                if ((request.getParameter("change") == null) ||
                        request.getParameter("change").equals(""))
                {
                        Date t = new Date();
                        Calendar calendar = Calendar.getInstance();
                        calendar.setTime(t);
                        month1 = calendar.get(Calendar.MONTH);
                        month1 = month1 + 1;
                }
                else
                {
                        month1 = Integer.parseInt(request.getParameter("change"));
                }

                for (int i = 0; i < ID.length; i++)
                {
                        TopTenHelper topTenHelper = new TopTenHelper();
                        TopTenForm newForm = topTenHelper.getTopTen(Integer.parseInt(ID[i]));
                        newForm.setIs_display(request.getParameter("is_display"));
                        newForm.setDesc1(String.valueOf(month1));

                        TopTenDAO dao = TopTenDAOFactory.getDAO();
                        dao.updateTopTen(newForm);
                }

                // Forward to result page
                return mapping.findForward(resultScreen);
        }
}
