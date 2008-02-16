/**
 * Copyright (c) 2000-2005.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */

package com.ulearning.ulms.user.group.action;

import com.ulearning.ulms.user.group.dao.UserGroupDAO;
import com.ulearning.ulms.user.group.dao.UserGroupDAOFactory;
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
 * Date: 20051130
 * Time: 142230
 */

public class DelUserGroupAction extends Action
{
        public ActionForward execute(
                ActionMapping mapping,
                ActionForm form,
                HttpServletRequest request,
                HttpServletResponse response)
                throws Exception
        {

                String resultScreen = "success";
                String[] usergroupid = request.getParameterValues("usergroupid");
                String group = request.getParameter("groupidlocal");
                UserGroupDAO dao = UserGroupDAOFactory.getDAO();
                for (int i = 0; i < usergroupid.length; i++)
                {
                        dao.deleteGroupuser(Integer.parseInt(group), Integer.parseInt(usergroupid[i]));
                        //dao.deleteUserGroup(Integer.parseInt(usergroupid[i]));
                }

                // Forward to result page
                return mapping.findForward(resultScreen);
        }

}
