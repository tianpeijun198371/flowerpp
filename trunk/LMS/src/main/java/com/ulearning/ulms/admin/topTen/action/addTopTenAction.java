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
import com.ulearning.ulms.util.LMSConstants;
import com.ulearning.ulms.util.log.LogUtil;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class addTopTenAction extends Action
{
        public ActionForward execute(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response)
                throws Exception
        {
                String resultScreen = "success";
                TopTenForm tf = (TopTenForm) form;
                String name = tf.getName();
                int ID = 0; //TopTenHelper.getID(name,tf.getType());

                if (ID > 0)
                {
                        request.setAttribute(LMSConstants.ERROR_PAGE_INFO, "名称不能重复！");

                        return mapping.findForward(LMSConstants.ERROR_FORWARD);
                }

                tf.setCreattime(new Date());

                //modified zengwj获取选择的月份
                if ((tf.getDesc1() == null) || tf.getDesc1().equals(""))
                {
                        Date t = new Date();
                        Calendar calendar = Calendar.getInstance();
                        calendar.setTime(t);

                        int month1 = calendar.get(Calendar.MONTH);
                        month1 = month1 + 1;
                        tf.setDesc1(String.valueOf(month1));
                }

                TopTenDAO dao = TopTenDAOFactory.getDAO();
                dao.addTopTen(tf);

                LogUtil.info("admin",
                        "[AddTopTenAction]===========resultScreen = " + resultScreen);

                // Forward to result page
                return mapping.findForward(resultScreen);
        }
}
