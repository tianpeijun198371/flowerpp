package com.ulearning.ulms.content.uteachergroup.action;

import com.ulearning.ulms.content.uteachergroup.dao.UTeacherGroupDAO;
import com.ulearning.ulms.content.uteachergroup.dao.UTeacherGroupDAOFactory;
import com.ulearning.ulms.content.uteachergroup.form.UTeacherGroupForm;
import com.ulearning.ulms.user.dao.UserDAO;
import com.ulearning.ulms.user.dao.UserDAOFactory;
import com.ulearning.ulms.user.form.UserForm;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;

/**
 * Created by IntelliJ IDEA.
 * User: suh
 * Date: 2006-3-20
 * Time: 15:16:22
 * To change this template use File | Settings | File Templates.
 */
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class EditTeacherUserAction extends Action
{
        public ActionForward execute(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response)
                throws Exception
        {
                String resultScreen = "success";
                String u_ship_id = request.getParameter("u_ship_id");
                String userid = request.getParameter("userid");

                //System.out.println(u_ship_id);
                //System.out.println(userid);
                UserForm user = (UserForm) form;
                user.setUserID(Integer.parseInt(userid));

                try
                {
                        UserDAO udao = UserDAOFactory.getDAO();
                        udao.updateUser(user);
                }
                catch (Exception e)
                {
                        // e.printStackTrace();
                }

                //mapping.setParameter(u_ship_id);
                return mapping.findForward(resultScreen);

                //return null;
        }
}
