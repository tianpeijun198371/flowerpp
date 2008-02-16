/**
 * DelOrganAction.java.
 * User: huangsb  Date: 2004-4-23
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd. * All rights reserved.
 */
package com.ulearning.ulms.organ.action;

import com.ulearning.ulms.core.util.Config;
import com.ulearning.ulms.course.dao.CourseDAO;
import com.ulearning.ulms.course.dao.CourseDAOFactory;
import com.ulearning.ulms.course.helper.CourseHelper;
import com.ulearning.ulms.course.model.CourseListModel;
import com.ulearning.ulms.course.model.CourseModel;
import com.ulearning.ulms.organ.bean.OrganHelper;
import com.ulearning.ulms.organ.dao.OrganDAO;
import com.ulearning.ulms.organ.dao.OrganDAOFactory;
import com.ulearning.ulms.organ.form.OrganForm;
import com.ulearning.ulms.user.bean.UserHelper;
import com.ulearning.ulms.util.LMSConstants;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import org.apache.webapp.admin.TreeControl;
import org.apache.webapp.admin.TreeControlNode;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class DelOrganAction extends Action
{
        public ActionForward execute(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response)
                throws Exception
        {
                String resultScreen = "success";
                String[] orgIDs = request.getParameterValues("orgID");
                String res = "";

                for (int i = 0; i < orgIDs.length; i++)
                {
                        List orgList = OrganHelper.getOrganList(Integer.parseInt(orgIDs[i]));

                        List userList = UserHelper.getUserList(Integer.parseInt(orgIDs[i]));
                        CourseDAO dao = CourseDAOFactory.getDAO();
                        CourseListModel courseList = dao.search("", 0,
                                Integer.parseInt(orgIDs[i]));

                        if (((orgList != null) && (orgList.size() > 0)) ||
                                ((userList != null) && (userList.size() > 0)) ||
                                ((courseList != null) && !courseList.getList().isEmpty()))
                        {
                                res += ("机构 '" +
                                        OrganHelper.getOrgan(Integer.parseInt(orgIDs[i])).getOrgName() +
                                        "' 下有子机构,用户或是课程 ，不能被删除！<BR>");
                        }
                }

                if (res.length() > 0)
                {
                        request.setAttribute(LMSConstants.ERROR_PAGE_INFO, res);

                        return mapping.findForward(LMSConstants.ERROR_FORWARD);
                }

                OrganDAO dao = OrganDAOFactory.getDAO();
                HttpSession session = request.getSession();
                String aspID = "0";

                if (session.getAttribute(LMSConstants.USER_ASPID_KEY) != null)
                {
                        aspID = (String) session.getAttribute(LMSConstants.USER_ASPID_KEY);
                }

                TreeControl control = null;
                TreeControl controlInContext = null;
                control = (TreeControl) session.getAttribute(LMSConstants.TREE_ORGAN);
                controlInContext = (TreeControl) getServlet().getServletContext()
                        .getAttribute(LMSConstants.TREE_ORGAN +
                                aspID);

                TreeControlNode currentNode = null;

                for (int i = 0; i < orgIDs.length; i++)
                {
                        String orgID = orgIDs[i];
                        dao.removeOrgan(orgID);

                        //delete removejieFo organ
                        try
                        {
                                if (Config.getIsIntegrateJieFo())
                                {
                                        OrganForm oof = dao.getOrgan(orgID);

                                        if (oof.getIsAsp() == 1)
                                        {
                                                dao.removeJieFoOrgan(orgID);
                                        }
                                }
                        }
                        catch (Exception e)
                        {
                                e.printStackTrace(); //To change body of catch statement use File | Settings | File Templates.
                        }

                        if (control != null)
                        {
                                currentNode = control.findNode("orgID/" + orgID);

                                if (currentNode != null)
                                {
                                        control.removeCurrentNode(currentNode);
                                }

                                if (controlInContext != null)
                                {
                                        currentNode = controlInContext.findNode("orgID/" + orgID);

                                        if (currentNode != null)
                                        {
                                                controlInContext.removeCurrentNode(currentNode);
                                        }
                                }
                        }
                }

                //remove a organ node from tree
                request.setAttribute("catalogID", aspID);

                // Forward to result page
                return mapping.findForward(resultScreen);
        }
}
