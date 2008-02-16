/**
 * UpdateMasterAction.java.
 * User: fengch  Date: 2004-4-28
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.course.action;

import com.ulearning.ulms.core.security.bean.SecurityConstants;
import com.ulearning.ulms.core.util.StringUtil;
import com.ulearning.ulms.course.dao.MasterDAO;
import com.ulearning.ulms.course.dao.MasterDAOFactory;
import com.ulearning.ulms.course.exceptions.CourseCodeRepeatedException;
import com.ulearning.ulms.course.helper.CourseHelper;
import com.ulearning.ulms.course.model.MasterModel;
import com.ulearning.ulms.course.util.CourseKeys;
import com.ulearning.ulms.course.util.TreeFlush;
import com.ulearning.ulms.util.LMSConstants;
import com.ulearning.ulms.util.log.LogUtil;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import org.apache.webapp.admin.TreeControl;
import org.apache.webapp.admin.TreeControlNode;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class UpdateMasterAction extends Action
{
        public ActionForward execute(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response)
                throws Exception
        {
                String resultScreen = "success";
                MasterModel mas = (MasterModel) form;

                int aspID = 0;
                int orgID = 0;

                HttpSession session = request.getSession();
                ServletContext servletContext = getServlet()
                        .getServletContext();

                if (session.getAttribute(LMSConstants.USER_ASPID_KEY) != null)
                {
                        String str = (String) session.getAttribute(LMSConstants.USER_ASPID_KEY);
                        aspID = StringUtil.parseInt(str);
                        mas.setOrgID(aspID);

                        String strOrg = (String) session.getAttribute(LMSConstants.USER_ORGID_KEY);
                        orgID = StringUtil.parseInt(strOrg);
                }

                if (CourseHelper.isExistMasterCode(mas.getMasterCode(),
                        mas.getMasterID(), mas.getType(), aspID))
                {
                        LogUtil.debug("course", "[UpdateMasterAction]=========== 课程代号重复!");
                        throw new CourseCodeRepeatedException("编号已存在!请重新输入!");
                }

                TreeControl control = null;
                String treeType = null;

                if (mas.getType() == SecurityConstants.USER_COURSE_RELATION)
                {
                        control = (TreeControl) session.getAttribute(LMSConstants.TREE_COURSE);
                        treeType = LMSConstants.TREE_COURSE;
                }
                else if (mas.getType() == SecurityConstants.USER_CERTIFICATE_RELATION)
                {
                        control = (TreeControl) session.getAttribute(LMSConstants.TREE_CERT);
                        treeType = LMSConstants.TREE_CERT;
                }
                else if (mas.getType() == SecurityConstants.USER_OFFLINE_CERTIFICATE_RELATION)
                {
                        control = (TreeControl) session.getAttribute(LMSConstants.TREE_CERT_OFFLINE);
                        treeType = LMSConstants.TREE_CERT_OFFLINE;
                }

                if (control != null)
                {
                        TreeControlNode currentNode = control.findNode("masterIDs/" +
                                mas.getMasterID());
                        currentNode.setLabel(mas.getName());
                }

                MasterDAO dao = MasterDAOFactory.getDAO();
                dao.updateMaster(mas);

                //更新树
                TreeFlush.flush(servletContext, session, orgID, treeType);

                request.setAttribute("catalogID", String.valueOf(mas.getCatalogID()));

                // Forward to result page
                if ((mas.getType() == SecurityConstants.USER_CERTIFICATE_RELATION) ||
                        (mas.getType() == SecurityConstants.USER_OFFLINE_CERTIFICATE_RELATION))
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
