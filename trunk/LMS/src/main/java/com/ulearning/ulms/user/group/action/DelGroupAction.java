/**
 * Copyright (c) 2000-2005.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */

package com.ulearning.ulms.user.group.action;

import com.ulearning.ulms.user.group.dao.GroupDAO;
import com.ulearning.ulms.user.group.dao.GroupDAOFactory;
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
 * Date: 20051124
 * Time: 155359
 */

public class DelGroupAction extends Action
{
        public ActionForward execute(
                ActionMapping mapping,
                ActionForm form,
                HttpServletRequest request,
                HttpServletResponse response)
                throws Exception
        {

                String resultScreen = "success";
                String[] groupid = request.getParameterValues("groupid");
                GroupDAO dao = GroupDAOFactory.getDAO();
                for (int i = 0; i < groupid.length; i++)
                {
                        dao.deleteGroup(Integer.parseInt(groupid[i]));
                }

                // Forward to result page
                return mapping.findForward(resultScreen);
        }

}
