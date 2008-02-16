package com.ulearning.ulms.content.uteachergroup.action;

import com.ulearning.ulms.content.uteachergroup.dao.UTeacherGroupDAO;
import com.ulearning.ulms.content.uteachergroup.dao.UTeacherGroupDAOFactory;
import com.ulearning.ulms.content.uteachergroup.form.UTeacherGroupForm;
import com.ulearning.ulms.user.dao.UserDAO;
import com.ulearning.ulms.user.dao.UserDAOFactory;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;

/**
 * Created by IntelliJ IDEA.
 * User: suh
 * Date: 2006-3-19
 * Time: 16:58:32
 * To change this template use File | Settings | File Templates.
 */
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class viewTeacherUserAction extends Action
{
        public ActionForward execute(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response)
                throws Exception
        {
                String resultScreen = "success";
                UTeacherGroupDAO dao = UTeacherGroupDAOFactory.getDAO();
                String u_ship_id = request.getParameter("u_ship_id");
                List list = null;
                list = getShipUser(Integer.parseInt(u_ship_id));
                request.setAttribute("ship_user", list);

                return mapping.findForward(resultScreen);
        }

        public List getShipUser(int ushipid)
        {
                UTeacherGroupDAO dao = UTeacherGroupDAOFactory.getDAO();
                List list = null;
                list = dao.findbyShipId(ushipid);

                int[] userids = new int[list.size()];

                for (int i = 0; i < list.size(); i++)
                {
                        UTeacherGroupForm ugf;
                        ugf = (UTeacherGroupForm) list.get(i);
                        userids[i] = ugf.getUserID();
                }

                UserDAO udao = UserDAOFactory.getDAO();
                List userlist = new ArrayList();

                for (int i = 0; i < userids.length; i++)
                {
                        userlist.add(udao.getUser("" + userids[i]));
                }

                return userlist;

                //udao.getUser(userids[i])
        }
}
