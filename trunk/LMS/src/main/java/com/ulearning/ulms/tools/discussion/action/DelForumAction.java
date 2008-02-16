/**
 * DelForumAction.java.
 * User: huangsb  Date: 2004-7-1
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd. * All rights reserved.
 */
package com.ulearning.ulms.tools.discussion.action;

import com.ulearning.ulms.tools.discussion.dao.ForumDAO;
import com.ulearning.ulms.tools.discussion.dao.ForumDAOFactory;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class DelForumAction extends Action
{
        public ActionForward execute(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response)
                throws Exception
        {
                String resultScreen = "success";
                String[] forumID = request.getParameterValues("forumID");
                ForumDAO dao = ForumDAOFactory.getDAO();
                dao.remove(forumID);

                // Forward to result page
                return mapping.findForward(resultScreen);
        }
}
