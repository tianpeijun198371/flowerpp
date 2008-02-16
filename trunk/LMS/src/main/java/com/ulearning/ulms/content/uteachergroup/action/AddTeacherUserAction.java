package com.ulearning.ulms.content.uteachergroup.action;

import com.ulearning.ulms.content.uteachergroup.dao.UTeacherGroupDAO;
import com.ulearning.ulms.content.uteachergroup.dao.UTeacherGroupDAOFactory;
import com.ulearning.ulms.content.uteachergroup.form.UTeacherGroupForm;
import com.ulearning.ulms.user.form.UserForm;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Created by IntelliJ IDEA.
 * User: suh
 * Date: 2006-3-19
 * Time: 14:06:12
 * To change this template use File | Settings | File Templates.
 */
public class AddTeacherUserAction extends Action
{
        public ActionForward execute(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response)
                throws Exception
        {
                String resultScreen = "success";
                String u_ship_id = request.getParameter("u_ship_id");
                UserForm uf = (UserForm) form;
                UTeacherGroupForm ug = new UTeacherGroupForm();

                try
                {
                        TeacherHandler th = new TeacherHandler();
                        int userId = th.addUser(uf);
                        //System.out.println(userId);
                        ug.setUserID(userId);
                        ug.setG_Ship_ID(Integer.parseInt(u_ship_id));
                        System.out.println(userId);
                        System.out.println(u_ship_id);

                        UTeacherGroupDAO dao = UTeacherGroupDAOFactory.getDAO();
                        dao.insertUTeacherGroup(ug);
                }
                catch (Exception e)
                {
                        System.err.println("TeacherHandler inside exception:" + e);
                }

                /*
                  UTeacherGroupForm ug = new UTeacherGroupForm();
                  ug.setUserID(userId);
                */

                /*
           ug.setG_Ship_ID(Integer.parseInt(u_ship_id));
           UTeacherGroupDAO dao = UTeacherGroupDAOFactory.getDAO();
           dao.insertUTeacherGroup(ug);     */
                return mapping.findForward(resultScreen);

                //return null;
        }
}
