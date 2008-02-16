/**
 * DelUserAction.java.
 * User: fengch  Date: 2004-4-28
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.course.action;

import com.ulearning.ulms.common.dao.ScoreManageDAO;
import com.ulearning.ulms.common.dao.ScoreManageDAOFactory;
import com.ulearning.ulms.core.security.bean.SecurityConstants;
import com.ulearning.ulms.core.util.Config;
import com.ulearning.ulms.core.util.StringUtil;
import com.ulearning.ulms.course.dao.CourseUserDAO;
import com.ulearning.ulms.course.dao.CourseUserDAOFactory;
import com.ulearning.ulms.course.helper.CourseUserHelper;
import com.ulearning.ulms.course.model.CourseRoleListModel;
import com.ulearning.ulms.course.model.CourseUserModel;
import com.ulearning.ulms.util.log.LogUtil;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class DelUserAction extends Action
{
        public ActionForward execute(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response)
                throws Exception
        {
                String resultScreen = "success";

                //判断是否是从培训事务的“学习报名”提交
                String isTag = request.getParameter("isTag");

                String[] userIDs;
                int userID;
                String courseID_str = request.getParameter("relationID");
                int relationID = Integer.parseInt(courseID_str);
                int type = StringUtil.parseInt((String) request.getParameter("type"));
                CourseUserDAO courseUserDAO = CourseUserDAOFactory.getDAO();
                ScoreManageDAO scoreManage = ScoreManageDAOFactory.getDAO();
                userIDs = request.getParameterValues("userID");

                if (userIDs != null)
                {
                        for (int i = 0; i < userIDs.length; i++)
                        {
                                userID = Integer.parseInt(userIDs[i]);

                                //delete course user
                                CourseUserHelper.deleteCourseUser(userID, relationID, type);
                                LogUtil.info("course",
                                        "[DelUserAction]===========在课程 " + relationID + "删除用户 " +
                                                userID);
                        }
                }

                if (!((isTag == null) || isTag.equals("")))
                {
                        resultScreen = "success_personalSignUp";
                }

                return mapping.findForward(resultScreen);
        }
}
