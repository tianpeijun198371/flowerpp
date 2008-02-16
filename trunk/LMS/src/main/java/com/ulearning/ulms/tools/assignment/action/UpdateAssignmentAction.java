/** * UpdateAssignmentAction.java.
 * User: xiejh  Date: 2004-4-22 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.tools.assignment.action;

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
import com.ulearning.ulms.util.log.LogUtil;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

import java.io.*;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class UpdateAssignmentAction extends UploadAction
{
        public ActionForward execute(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response)
                throws Exception
        {
                MultipartParamUtils mp = new MultipartParamUtils(request,
                        1024 * 1014 * 10);
                String resultScreen = "success";
                AssignmentForm uf = (AssignmentForm) form;
                CourseUserDAOImpl courseUserDAOImpl = new CourseUserDAOImpl();
                List courseUserList = courseUserDAOImpl.getCourseAllUsers(uf.getCourseID(),
                        3).getCourseUsers();

                if ((StringUtil.parseInt(uf.getViewable()) == 1) &&
                        (StringUtil.parseInt(uf.getAvailable()) == 1))
                {
                        for (int i = 0; i < courseUserList.size(); i++)
                        {
                                CourseUserModel cum = (CourseUserModel) courseUserList.get(i);
                                int userID = cum.getUserID();
                                CourseUserHelper.informhomework(userID, uf.getCourseID(), 5);
                        }
                }

                String expertID = request.getParameter("expertID");

                if (request.getContentType().startsWith("multipart/form-data"))
                {
                        try
                        {
                                UploadForm uploadForm = uf.getUploadForm();
                                //  LogUtil.info("system", "[UpdateAssignmentAction] getFileExtendName====================" +uf.getFileExtendName());
                                LogUtil.info("system",
                                        "[UpdateAssignmentAction] getFilePath====================" +
                                                uf.getFilePath());
                                LogUtil.info("system",
                                        "[UpdateAssignmentAction] FileName====================" +
                                                uf.getNewFileName());
                                LogUtil.info("system",
                                        "[UpdateAssignmentAction] FileName====================" +
                                                uf.getLinkFileName());

                                executeUpload(mapping, uploadForm, request, response);
                        }
                        catch (Exception e)
                        {
                                LogUtil.debug("system",
                                        "[UpdateAssignmentAction] Exeception====================" +
                                                e);
                        }

                        //if (uf.getLinkFileName()!=null&& !uf.getLinkFileName().equals(""))
                        //{
                        //    LogUtil.info("system", "[UpdateAssignmentAction] size====================" +request.getAttribute("size"));
                        //   LogUtil.info("system", "[UpdateAssignmentAction] newFileName====================" +request.getAttribute("newFileName"));
                        if (!String.valueOf(request.getAttribute("size")).equals("0"))
                        {
                                uf.setLinkFileUrl(uf.getFilePath() +
                                        request.getAttribute("newFileName"));
                        }

                        //}
                }

                AssignmentDAO dao = AssignmentDAOFactory.getDAO();
                dao.updateAssignment(uf);
                LogUtil.info("admin",
                        "[UpdateAssignmentAction]===========resultScreen = " +
                                resultScreen);
                request.setAttribute("UpdexpertID", expertID);
                request.setAttribute("arenaID", String.valueOf(uf.getAssignmentID()));
                request.setAttribute("mp", mp);

                return mapping.findForward(resultScreen);
        }
}
