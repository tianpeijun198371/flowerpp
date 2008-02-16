/**
 * DelCourseAction.java.
 * User: fengch  Date: 2004-4-28
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.course.action;

import com.ulearning.ulms.admin.certificate.dao.CertDAO;
import com.ulearning.ulms.admin.certificate.dao.CertDAOFactory;
import com.ulearning.ulms.core.security.bean.SecurityConstants;
import com.ulearning.ulms.core.util.Config;
import com.ulearning.ulms.course.dao.CourseDAO;
import com.ulearning.ulms.course.dao.CourseDAOFactory;
import com.ulearning.ulms.course.helper.CourseHelper;
import com.ulearning.ulms.course.helper.CourseUserHelper;
import com.ulearning.ulms.course.util.TreeFlush;
import com.ulearning.ulms.util.LMSConstants;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import org.apache.webapp.admin.TreeControl;
import org.apache.webapp.admin.TreeControlNode;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class DelCourseAction extends Action
{
        public ActionForward execute(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response)
                throws Exception
        {
                String resultScreen = "success";
                String[] courseIDs;
                ArrayList al = null;
                courseIDs = request.getParameterValues("courseIDs");

                String iscoursecert = request.getParameter("iscoursecert"); //用来判断是哪个页面做删除（课程页面、培训班页面）

                HttpSession session = request.getSession();
                ServletContext servletContext = getServlet().getServletContext();

                String aspID = "0";

                if (session.getAttribute(LMSConstants.USER_ASPID_KEY) != null)
                {
                        aspID = (String) session.getAttribute(LMSConstants.USER_ASPID_KEY);
                }

                String orgID = "0";

                if (session.getAttribute(LMSConstants.USER_ORGID_KEY) != null)
                {
                        orgID = (String) session.getAttribute(LMSConstants.USER_ORGID_KEY);
                }

                TreeControl control = null;
                String treeType = LMSConstants.TREE_PUBLISHED_COURSE;

                //Judge which object the delete action will execute
                if ((courseIDs == null) || (courseIDs.length == 0))
                {
                        treeType = LMSConstants.TREE_PUBLISHED_CERT;
                        resultScreen = "success_cert";
                }

                control = (TreeControl) session.getAttribute(treeType);

                //
                //		if (servletContext.getAttribute(LMSConstants.TREE_PUBLISH + aspID) != null)
                //			servletContext.removeAttribute(LMSConstants.TREE_PUBLISH + aspID);
                //
                //
                //		if (session.getAttribute(LMSConstants.TREE_SELECT) != null)
                //			session.removeAttribute(LMSConstants.TREE_SELECT);
                //		if (servletContext.getAttribute(LMSConstants.TREE_SELECT + aspID) != null)
                //			servletContext.removeAttribute(LMSConstants.TREE_SELECT + aspID);
                //
                //                if (session.getAttribute(LMSConstants.TREE_STUDENT_SIGNUP) != null)
                //	        session.removeAttribute(LMSConstants.TREE_STUDENT_SIGNUP);
                //		if (servletContext.getAttribute(LMSConstants.TREE_STUDENT_SIGNUP + aspID) != null)
                //		servletContext.removeAttribute(LMSConstants.TREE_STUDENT_SIGNUP + aspID);
                //
                TreeControlNode currentNode = null;
                CourseDAO dao = CourseDAOFactory.getDAO();

                if (courseIDs != null)
                {
                        al = new ArrayList();

                        for (int i = 0; i < courseIDs.length; i++)
                        {
                                CourseHelper.deletedRelationData(Integer.parseInt(courseIDs[i]));
                                CourseUserHelper.deleteCourseUser(0,
                                        Integer.parseInt(courseIDs[i]), -1); //-1代表删除本课程所有用户
                                al.add(new Integer(courseIDs[i]));

                                if (control != null)
                                {
                                        currentNode = control.findNode("courseIDs/" + courseIDs[i]);

                                        if (currentNode != null)
                                        {
                                                control.removeCurrentNode(currentNode);
                                        }
                                }
                        }

                        CourseHelper.deleteCourse(al);
                }

                //delete jiefocourse
                try
                {
                        if (Config.getIsIntegrateJieFo())
                        {
                                dao.deleteJieFoCourse(courseIDs);
                        }
                }
                catch (Exception e)
                {
                        e.printStackTrace(); //To change body of catch statement use File | Settings | File Templates.
                }

                //begin to del certificat
                String[] certificateIDs = request.getParameterValues("certificateIDs");
                al = new ArrayList();

                CertDAO certDao = CertDAOFactory.getDAO();

                if (certificateIDs != null)
                {
                        for (int i = 0; i < certificateIDs.length; i++)
                        {
                                al.add(new Integer(certificateIDs[i]));

                                if (control != null)
                                {
                                        currentNode = control.findNode("certificateIDs/" +
                                                certificateIDs[i]);

                                        if (currentNode != null)
                                        {
                                                control.removeCurrentNode(currentNode);
                                        }
                                }
                        }

                        certDao.deleteCert(al);
                }

                request.setAttribute("catalogID", request.getParameter("catalogID"));

                String[] catalogIDs;
                catalogIDs = request.getParameterValues("catalogIDs");
                al = new ArrayList();

                if (catalogIDs != null)
                {
                        for (int i = 0; i < catalogIDs.length; i++)
                        {
                                al.add(new Integer(catalogIDs[i]));

                                if (control != null)
                                {
                                        currentNode = control.findNode("catalogIDs/" +
                                                catalogIDs[i]);

                                        if (currentNode != null)
                                        {
                                                control.removeCurrentNode(currentNode);
                                        }
                                }
                        }

                        request.setAttribute("catalogID", "0"); ///modified by zengwj  预防当前的目录被删了而在添加课程或目录是报错,但还能进行进一步的修改!!!
                        dao.deleteCatalog(al);
                }

                if (iscoursecert.equals("" + SecurityConstants.USER_COURSE_RELATION))
                {
                        resultScreen = "success";
                }
                else if (iscoursecert.equals("" +
                        SecurityConstants.USER_CERTIFICATE_RELATION))
                {
                        resultScreen = "success_cert";
                }

                //更新树
                TreeFlush.flush(servletContext, session, Integer.parseInt(orgID),
                        treeType);
                return mapping.findForward(resultScreen);
        }
}
