/** * AddAssignmentAction.java.
 * User: xiejh  Date: 2004-4-22 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.tools.assignment.action;

import com.ulearning.ulms.core.crypto.MD5Encrypt;
import com.ulearning.ulms.core.util.Config;
import com.ulearning.ulms.core.util.DateTimeUtil;
import com.ulearning.ulms.core.util.StringUtil;
import com.ulearning.ulms.course.dao.CourseUserDAOImpl;
import com.ulearning.ulms.course.helper.CourseUserHelper;
import com.ulearning.ulms.course.model.CourseUserModel;
import com.ulearning.ulms.tools.assignment.dao.AssignmentDAO;
import com.ulearning.ulms.tools.assignment.dao.AssignmentDAOFactory;
import com.ulearning.ulms.tools.assignment.form.AssignmentForm;
import com.ulearning.ulms.tools.upload.action.MultipartParamUtils;
import com.ulearning.ulms.tools.upload.action.UploadAction;
import com.ulearning.ulms.tools.upload.model.UploadForm;
import com.ulearning.ulms.util.LMSConstants;
import com.ulearning.ulms.util.log.LogUtil;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class AddAssignmentAction extends UploadAction
{
        public ActionForward execute(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response)
                throws Exception
        {
                String resultScreen = "success";
                String expertID = request.getParameter("expertID");
                AssignmentForm pf = (AssignmentForm) form;
                int courseID = StringUtil.parseInt(request.getParameter("courseID"));

                if (request.getContentType().startsWith("multipart/form-data"))
                {
                        try
                        {
                                UploadForm uploadForm = pf.getUploadForm();
                                executeUpload(mapping, uploadForm, request, response);
                        }
                        catch (Exception e)
                        {
                                LogUtil.debug("system",
                                        "[AddAssignmentAction] Exeception====================" + e);
                        }

                        if (!String.valueOf(request.getAttribute("size")).equals("0"))
                        {
                                pf.setLinkFileUrl(pf.getFilePath() +
                                        request.getAttribute("newFileName"));
                        }
                        else
                        {
                                pf.setLinkFileName("");
                                pf.setLinkFileUrl("");
                        }
                }

                AssignmentDAO dao = AssignmentDAOFactory.getDAO();
                pf.setCreateTime(DateTimeUtil.FormatDateToWeb1(new Date()));

                CourseUserDAOImpl courseUserDAOImpl = new CourseUserDAOImpl();
                List courseUserList = courseUserDAOImpl.getCourseAllUsers(courseID, 3)
                        .getCourseUsers();

                if ((StringUtil.parseInt(pf.getViewable()) == 1) &&
                        (StringUtil.parseInt(pf.getAvailable()) == 1))
                {
                        for (int i = 0; i < courseUserList.size(); i++)
                        {
                                CourseUserModel cum = (CourseUserModel) courseUserList.get(i);
                                int userID = cum.getUserID();
                                CourseUserHelper.informhomework(userID, courseID, 5);
                        }
                }

                dao.addAssignment(pf);
                LogUtil.info("admin",
                        "[AddAssignmentAction]===========resultScreen = " + resultScreen);

                // Forward to result page
                MultipartParamUtils mp = new MultipartParamUtils(request,
                        1024 * 1014 * 10);
                request.setAttribute("expertID", expertID);
                request.setAttribute("mp", mp);

                return mapping.findForward(resultScreen);
        }
}
