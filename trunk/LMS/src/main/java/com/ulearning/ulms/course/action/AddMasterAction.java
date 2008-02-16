/**
 * AddMasterAction.java.
 * User: fengch  Date: 2004-4-28
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.course.action;

import com.ulearning.ulms.core.security.bean.SecurityConstants;
import com.ulearning.ulms.core.util.Config;
import com.ulearning.ulms.core.util.StringUtil;
import com.ulearning.ulms.course.dao.MasterDAO;
import com.ulearning.ulms.course.dao.MasterDAOFactory;
import com.ulearning.ulms.course.exceptions.CourseCodeRepeatedException;
import com.ulearning.ulms.course.model.MasterModel;
import com.ulearning.ulms.course.util.TreeFlush;
import com.ulearning.ulms.util.LMSConstants;
import com.ulearning.ulms.util.log.LogUtil;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import org.apache.webapp.admin.TreeControl;
import org.apache.webapp.admin.TreeControlNode;

import java.util.Date;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class AddMasterAction extends Action
{
        public ActionForward execute(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response)
                throws Exception
        {
                String resultScreen = "success";
                MasterModel mas = (MasterModel) form;
                MasterDAO dao = MasterDAOFactory.getDAO();
                String code = mas.getMasterCode();
                ServletContext servletContext = getServlet()
                        .getServletContext();
                int aspID = 0;
                int orgID = 0;
                HttpSession session = request.getSession();

                if (session.getAttribute(LMSConstants.USER_ORGID_KEY) != null)
                {
                        orgID = Integer.parseInt((String) session.getAttribute(
                                LMSConstants.USER_ORGID_KEY));
                }

                if (session.getAttribute(LMSConstants.USER_ASPID_KEY) != null)
                {
                        String str = (String) session.getAttribute(LMSConstants.USER_ASPID_KEY);
                        aspID = StringUtil.parseInt(str);
                }

                mas.setAspID(aspID);
                mas.setOrgID(orgID);

                if (dao.isExistMasterCode(code, -1, mas.getType(), aspID, 0))
                {
                        if (mas.getType() == SecurityConstants.USER_CERTIFICATE_RELATION)
                        {
                                LogUtil.debug("course", "[AddMasterAction]=========== 编号重复!");
                        }

                        throw new CourseCodeRepeatedException("编号已存在!请重新输入!");
                }

                mas.setEstablishDate(new Date());

                int masterID = dao.createMaster(mas);

                LogUtil.debug("course",
                        "[AddMasterAction]===========period1=" + mas.getPeriod());
                LogUtil.debug("course",
                        "[AddMasterAction]===========credit1=" + mas.getCredit());
                LogUtil.debug("course",
                        "[AddMasterAction]===========period2=" +
                                mas.getMaster().getPeriod());
                LogUtil.debug("course",
                        "[AddMasterAction]===========credit2=" +
                                mas.getMaster().getCredit());
                LogUtil.debug("master",
                        "[AddMasterAction]===========resultScreen = " + resultScreen);

                // add new node in session's tree and context's tree
                int catalogID = mas.getCatalogID();

                TreeControl control = null;
                String images = "course.gif";
                String treeType = null;

                String nodeName = "catalogIDs/" + catalogID;

                if (catalogID == 0)
                {
                        nodeName = "ROOT-NODE";
                }

                if ((mas.getType() == SecurityConstants.USER_CERTIFICATE_RELATION) ||
                        (mas.getType() == SecurityConstants.USER_OFFLINE_CERTIFICATE_RELATION))
                {
                        control = (TreeControl) session.getAttribute(LMSConstants.TREE_CERT);
                        treeType = LMSConstants.TREE_CERT;
                        images = "icon/project.gif";
                }
                else if (mas.getType() == SecurityConstants.USER_COURSE_RELATION)
                {
                        control = (TreeControl) session.getAttribute(LMSConstants.TREE_COURSE);
                        treeType = LMSConstants.TREE_COURSE;
                        images = "icon/project.gif";
                }

                if (control != null)
                {
                        TreeControlNode parentNode = control.findNode(nodeName);
                        parentNode.setExpanded(true);
                        control.selectNode(nodeName);

                        TreeControlNode subtree = null;

                        if (parentNode != null)
                        {
                                subtree = new TreeControlNode("masterIDs/" + masterID,
                                         Config.getContextRoot() + "/images/" + images,
                                        mas.getName(), "viewMaster.jsp?masterID=" + masterID,
                                        null, false, "Master");
                                parentNode.addChild(subtree);
                        }
                }

                //更新树
                TreeFlush.flush(servletContext, session, orgID, treeType);
                request.setAttribute("catalogID", String.valueOf(catalogID));

                // Forward to result page
                if ((mas.getType() == SecurityConstants.USER_CERTIFICATE_RELATION) ||
                        (mas.getType() == SecurityConstants.USER_OFFLINE_CERTIFICATE_RELATION))
                {
                        resultScreen = "successandaddcourse";
                        request.setAttribute("masterID", String.valueOf(masterID));
                }

                String actionStr = request.getParameter("actionStr");

                if (actionStr != null)
                {
                        request.setAttribute("masterIDs", String.valueOf(masterID));

                        if (actionStr.equals("saveandpublish"))
                        {
                                resultScreen = "successandpublish";
                        }
                        else if (actionStr.equals("successandaddcourse"))
                        {
                                resultScreen = "successandaddcourse";
                        }
                        else if (actionStr.equals("addCourseInCert"))
                        {
                                resultScreen = "addCourseInCert";
                                request.setAttribute("masterID",
                                        (request.getParameter("certID")).toString());
                        }
                }

                if (mas.getType() == SecurityConstants.USER_OFFLINE_CERTIFICATE_RELATION)
                {
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
