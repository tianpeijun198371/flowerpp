/**
 * SelectCourseUserAction.java.
 * User: zhangy Date: 2005-8-10 10:34:55
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.course.action;

import com.ulearning.ulms.course.dao.CourseUserDAOImpl;
import com.ulearning.ulms.course.model.CourseUserListModel;
import com.ulearning.ulms.user.form.UserForm;
import com.ulearning.ulms.util.log.LogUtil;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class SelectCourseUserAction extends Action
{
        public ActionForward execute(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response)
                throws Exception
        {
                String resultScreen = "success";
                UserForm uf = (UserForm) form;
                CourseUserDAOImpl cudi = new CourseUserDAOImpl();

                String getRelationID = request.getParameter("relationID");
                int relationID = Integer.parseInt(getRelationID);
                int type = Integer.parseInt(request.getParameter("type"));

                CourseUserListModel culm = cudi.searchCourseUsers(uf, relationID, type,
                        0, 9999);
                int CourseUserCount = cudi.countSearchCourseUsers(uf, relationID, type);

                request.setAttribute("CourseUserListModel", culm);
                LogUtil.debug("system",
                        "[SelectCourseUserAction]'culm.getCourseUsers().size()=======" +
                                culm.getCourseUsers().size());
                LogUtil.debug("system", "[SelectCourseUserAction]'type=======" + type);
                LogUtil.debug("system",
                        "[SelectCourseUserAction]'uf.getLoginName()=======" +
                                uf.getLoginName());
                LogUtil.debug("system",
                        "[SelectCourseUserAction]'uf.getName()=======" + uf.getName());
                LogUtil.debug("system",
                        "[SelectCourseUserAction]'uf.getRemark8()=======" +
                                uf.getRemark8());
                LogUtil.debug("system",
                        "[SelectCourseUserAction]'uf.getRemark9()=======" +
                                uf.getRemark9());
                LogUtil.debug("system",
                        "[SelectCourseUserAction]'getRelationID=======" + getRelationID);
                request.setAttribute("relationID", getRelationID);
                request.setAttribute("CourseUserCount", CourseUserCount + "");

                return mapping.findForward(resultScreen);
        }
}
