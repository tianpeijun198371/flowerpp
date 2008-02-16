/**
 * Copyright (c) 2000-2005.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */

package com.ulearning.ulms.user.group.action;

import com.ulearning.ulms.user.group.dao.UserGroupDAO;
import com.ulearning.ulms.user.group.dao.UserGroupDAOFactory;
import com.ulearning.ulms.user.group.dao.GroupDAOImpl;
import com.ulearning.ulms.user.group.form.UserGroupForm;
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
public class AddUserGroupAction extends Action
{
        public ActionForward execute(ActionMapping mapping,
                                     ActionForm form,
                                     HttpServletRequest request,
                                     HttpServletResponse response)
                throws Exception
        {

                String resultScreen = "success";
                String[] usergroupid = request.getParameterValues("usergroupid");
                String group = request.getParameter("groupidlocal");
                UserGroupForm tf = (UserGroupForm) form;
                GroupDAOImpl groupDAOImpl = new GroupDAOImpl();
                groupDAOImpl.insert(Integer.parseInt(group), usergroupid);
                //UserGroupDAO dao = UserGroupDAOFactory.getDAO();
                //dao.insertUserGroup(tf);
                // Forward to result page
                return mapping.findForward(resultScreen);
        }
}
