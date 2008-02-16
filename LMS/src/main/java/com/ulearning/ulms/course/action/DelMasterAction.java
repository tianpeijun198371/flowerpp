/**
 * DelMasterAction.java.
 * User: fengch  Date: 2004-4-28
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.course.action;

import com.ulearning.ulms.admin.certificate.dao.CertDAO;
import com.ulearning.ulms.admin.certificate.dao.CertDAOFactory;
import com.ulearning.ulms.core.security.bean.SecurityConstants;
import com.ulearning.ulms.course.dao.CourseDAO;
import com.ulearning.ulms.course.dao.CourseDAOFactory;
import com.ulearning.ulms.course.dao.MasterDAO;
import com.ulearning.ulms.course.dao.MasterDAOFactory;
import com.ulearning.ulms.course.util.CourseKeys;
import com.ulearning.ulms.course.util.TreeFlush;
import com.ulearning.ulms.tools.calendar.dao.CalendarDAO;
import com.ulearning.ulms.tools.calendar.dao.CalendarDAOFactory;
import com.ulearning.ulms.util.LMSConstants;
import com.ulearning.ulms.util.log.LogUtil;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import org.apache.webapp.admin.TreeControl;
import org.apache.webapp.admin.TreeControlNode;

import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class DelMasterAction extends Action
{
        public ActionForward execute(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response)
                throws Exception
        {
                String resultScreen = "success";
                String type = request.getParameter("type");
                String[] masterIDs;

                ArrayList al = new ArrayList();
                masterIDs = request.getParameterValues("masterIDs");

                HttpSession session = request.getSession();
                ServletContext servletContext = getServlet().getServletContext();
                String aspID = (String) session.getAttribute(LMSConstants.USER_ASPID_KEY);
                String orgID = (String) session.getAttribute(LMSConstants.USER_ORGID_KEY);

                TreeControl control = null;
                String treeType = null;

                if ((type != null) &&
                        (Integer.parseInt(type) == SecurityConstants.USER_COURSE_RELATION))
                {
                        treeType = LMSConstants.TREE_COURSE;
                        control = (TreeControl) session.getAttribute(treeType);
                }
                else if ((type != null) &&
                        (Integer.parseInt(type) == SecurityConstants.USER_CERTIFICATE_RELATION))
                {
                        treeType = LMSConstants.TREE_CERT;
                        control = (TreeControl) session.getAttribute(treeType);
                }
                else if ((type != null) &&
                        (Integer.parseInt(type) == SecurityConstants.USER_OFFLINE_CERTIFICATE_RELATION))
                {
                        treeType = LMSConstants.TREE_CERT_OFFLINE;
                        control = (TreeControl) session.getAttribute(treeType);
                }

                TreeControlNode currentNode = null;
                MasterDAO dao = MasterDAOFactory.getDAO();

                if (masterIDs != null)
                {
                        for (int i = 0; i < masterIDs.length; i++)
                        {
                                al.add(new Integer(masterIDs[i]));

                                if (control != null)
                                {
                                        LogUtil.debug("DelMasterAction",
                                                "I am in Master =================" + "masterIDs/" +
                                                        masterIDs[i]);
                                        currentNode = control.findNode("masterIDs/" + masterIDs[i]);

                                        if (currentNode != null)
                                        {
                                                control.removeCurrentNode(currentNode);
                                        }
                                }

                                //remove form Cer_Course_Tab
                                CertDAO certdao = CertDAOFactory.getDAO();
                                certdao.removeCourseFormCert(new Integer(masterIDs[i]).intValue());

                                CalendarDAO calendardao = CalendarDAOFactory.getDAO();
                                calendardao.delete(2, new Integer(masterIDs[i]).intValue());

                                CourseDAO coursedao = CourseDAOFactory.getDAO();
                                coursedao.deleteByMaster(new Integer(masterIDs[i]).intValue());
                        }

                        dao.deleteMaster(al);
                }

                String catalogID = "0";

                if (request.getParameter("catalogID") != null)
                {
                        catalogID = request.getParameter("catalogID");
                }

                request.setAttribute("catalogID", catalogID);

                String[] catalogIDs;
                al = new ArrayList();
                catalogIDs = request.getParameterValues("catalogIDs");

                if (catalogIDs != null)
                {
                        for (int i = 0; i < catalogIDs.length; i++)
                        {
                                al.add(new Integer(catalogIDs[i]));

                                if (control != null)
                                {
                                        LogUtil.debug("DelMasterAction",
                                                "I am in Catalog =================" + "catalogIDs/" +
                                                        catalogIDs[i]);
                                        currentNode = control.findNode("catalogIDs/" +
                                                catalogIDs[i]);

                                        if (currentNode != null)
                                        {
                                                control.removeCurrentNode(currentNode);
                                        }
                                }
                        }

                        request.setAttribute("catalogID", "0");
                        dao.deleteCatalog(al);
                }

                //¸üÐÂÊ÷
                TreeFlush.flush(servletContext, session, Integer.parseInt(orgID),
                        treeType);

                if ((Integer.parseInt(type) == SecurityConstants.USER_CERTIFICATE_RELATION) ||
                        (Integer.parseInt(type) == SecurityConstants.USER_OFFLINE_CERTIFICATE_RELATION))
                {
                        resultScreen = "success_cert";

                        ActionForward forward = mapping.findForward(resultScreen);
                        StringBuffer bf = new StringBuffer(forward.getPath());
                        bf.append("?offline=true");

                        return new ActionForward(bf.toString());
                }
                else
                {
                        return mapping.findForward(resultScreen);
                }
        }
}
