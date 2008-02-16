/**
 * Copyright (c) 2000-2005.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.content.uteachergroup.action;

import com.ulearning.ulms.content.uteachergroup.dao.UTeacherGroupDAO;
import com.ulearning.ulms.content.uteachergroup.dao.UTeacherGroupDAOFactory;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Class description goes here.
 * <p/>
 * Created by Auto Code Produce System
 * User: xiejh
 * Date: 20060317
 * Time: 103906
 */
public class DelUTeacherGroupAction extends Action
{
        public ActionForward execute(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response)
                throws Exception
        {
                String resultScreen = "success";
                UTeacherGroupDAO dao = UTeacherGroupDAOFactory.getDAO();

                try
                {
                        String[] userids = request.getParameterValues("userids");

                        if (userids.length > 0)
                        {
                                for (int i = 0; i < userids.length; i++)
                                {
                                        //System.out.println(userids[i]);
                                        dao.deletebyUserId(Integer.parseInt(userids[i]));
                                }

                                return mapping.findForward(resultScreen);
                        }
                }
                catch (Exception e)
                {
                        e.printStackTrace();
                }

                //String u_ship_id = request.getParameter("u_ship_id");
                //System.out.println("---------------" + userid + "---------------");
                try
                {
                        String userid = request.getParameter("userid");
                        dao.deletebyUserId(Integer.parseInt(userid));
                }
                catch (Exception e)
                {
                        e.printStackTrace();
                }

                //mapping.setParameter(u_ship_id);
                // Forward to result page
                return mapping.findForward(resultScreen);
        }
}
