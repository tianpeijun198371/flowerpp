/**
 * UpdatDiscussDetail.java.
 * User: huangsb  Date: 2004-7-12
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd. * All rights reserved.
 */
package com.ulearning.ulms.tools.discussion.action;

import com.ulearning.ulms.tools.discussion.bean.DiscussHelper;
import com.ulearning.ulms.tools.discussion.dao.DiscussDAO;
import com.ulearning.ulms.tools.discussion.dao.DiscussDAOFactory;
import com.ulearning.ulms.tools.discussion.form.DiscussForm;
import com.ulearning.ulms.util.log.LogUtil;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class UpdatDiscussDetailAction extends Action
{
        public ActionForward execute(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response)
                throws Exception
        {
                String resultScreen = "success";
                DiscussDAO dao = DiscussDAOFactory.getDAO();

                //ForumForm ff = (ForumForm) form;
                //int articleID =Integer.parseInt(request.getParameter("articleID"));
                String[] strArticleID = request.getParameterValues("articleID");
                String temp1 = request.getParameter("isLock");

                //LogUtil.info("admin", "[UpdateDiscussDeatailAction]===========temp1 ===== " + temp1);
                String temp2 = request.getParameter("isRead");
                String best = request.getParameter("best");

                // LogUtil.info("admin", "[UpdateDiscussDeatailAction]===========temp2 ==== " + temp2);
                for (int i = 0; i < strArticleID.length; i++)
                {
                        DiscussForm df = new DiscussHelper().getDiscuss(Integer.parseInt(
                                strArticleID[i]));

                        if ((temp1.equals("1")) || (temp1.equals("0")))
                        {
                                df.setIsLock(Integer.parseInt(temp1));
                        }

                        if ((temp2.equals("1")) || (temp2.equals("0")))
                        {
                                df.setIsRead(Integer.parseInt(temp2));
                        }

                        if ((best.equals("1") || (best.equals("0"))))
                        {
                                df.setBest(Integer.parseInt(best));
                        }

                        dao.updateDetail(df);
                }

                LogUtil.info("admin",
                        "[UpdateDiscussDeatailAction]===========resultScreen = " +
                                resultScreen);

                // Forward to result page
                return mapping.findForward(resultScreen);
        }
}
