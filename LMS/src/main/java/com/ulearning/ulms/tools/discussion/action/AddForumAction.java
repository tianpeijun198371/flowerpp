/**
 * AddForumAction.java.
 * User: huangsb  Date: 2004-7-1
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd. * All rights reserved.
 */
package com.ulearning.ulms.tools.discussion.action;

import com.ulearning.ulms.tools.discussion.bean.ForumHelper;
import com.ulearning.ulms.tools.discussion.dao.ForumDAO;
import com.ulearning.ulms.tools.discussion.dao.ForumDAOFactory;
import com.ulearning.ulms.tools.discussion.form.ForumForm;
import com.ulearning.ulms.util.LMSConstants;
import com.ulearning.ulms.util.log.LogUtil;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class AddForumAction extends Action
{
        public ActionForward execute(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response)
                throws Exception
        {
                String resultScreen = "success";
                ForumForm ff = (ForumForm) form;

                //        String title=ff.getTitle();
                //        int forumID=ForumHelper.getForumID(title);
                //        if(forumID>0)
                //            {
                //                        request.setAttribute(LMSConstants.ERROR_PAGE_INFO, "此论坛已经存在！");
                //                        return mapping.findForward(LMSConstants.ERROR_FORWARD);
                //            }
                ForumDAO dao = ForumDAOFactory.getDAO();
                dao.insert(ff);

                LogUtil.info("system",
                        "[AddForumAction]===========resultScreen = " + resultScreen);

                // Forward to result page
                return mapping.findForward(resultScreen);
        }
}
