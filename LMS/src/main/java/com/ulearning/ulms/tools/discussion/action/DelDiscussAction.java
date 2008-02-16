/**
 * DelDiscussAction.java.
 * User: huangsb  Date: 2004-7-1
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd. * All rights reserved.
 */
package com.ulearning.ulms.tools.discussion.action;

import com.ulearning.ulms.tools.discussion.dao.DiscussDAO;
import com.ulearning.ulms.tools.discussion.dao.DiscussDAOFactory;
import com.ulearning.ulms.tools.discussion.dao.ForumDAO;
import com.ulearning.ulms.tools.discussion.dao.ForumDAOFactory;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class DelDiscussAction extends Action
{
        public ActionForward execute(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response)
                throws Exception
        {
                String resultScreen = "success";
                DiscussDAO dao = DiscussDAOFactory.getDAO();
                String[] articleID = request.getParameterValues("articleID");
                dao.remove(articleID);

                // Forward to result page
                String courseID = request.getParameter("courseID");
                String forumID = request.getParameter("forumID");
                String articleIDs = request.getParameter("articleIDs");
                String open = request.getParameter("open");
                String fromShowdiscuss = request.getParameter("fromShowdiscuss");

                if ((fromShowdiscuss != null) && fromShowdiscuss.equals("true"))
                {
                        ActionForward forward = mapping.findForward("delete");
                        StringBuffer bf = new StringBuffer(forward.getPath());
                        bf.append("?courseID=" + courseID + "&forumID=" + forumID +
                                "&articleIDs=" + articleIDs + "&open=" + open);

                        return new ActionForward(bf.toString());
                }
                else
                {
                        return mapping.findForward(resultScreen);
                }
        }
}
