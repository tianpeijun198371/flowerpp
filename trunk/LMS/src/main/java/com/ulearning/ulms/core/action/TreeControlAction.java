/**
 * Created by IntelliJ IDEA.
 * User: zengwj
 * Date: 2004-7-19
 * Time: 14:50:01
 * To change this template use File | Settings | File Templates.
 */
package com.ulearning.ulms.core.action;

import com.ulearning.ulms.core.util.StringUtil;
import com.ulearning.ulms.tools.upload.action.MultipartParamUtils;
import com.ulearning.ulms.util.LMSConstants;
import com.ulearning.ulms.util.log.LogUtil;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import org.apache.webapp.admin.TreeControl;
import org.apache.webapp.admin.TreeControlNode;

import java.io.IOException;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class TreeControlAction extends Action
{
        public ActionForward execute(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response)
                throws IOException, ServletException
        {
                String resultScreen = "success";
                LogUtil.debug("course",
                        "[TreeControlAction]Entered TreeControlTestAction:perform()");

                String name = null;

                String catalogID = "";
                HttpSession session = request.getSession();

                String treeType = request.getParameter("treeType");

                ///////////////////////////////   temp
                String treeTypeTemp = treeType;

                if (treeType.equals(LMSConstants.TREE_CERT_MASTER))
                {
                        treeTypeTemp = LMSConstants.TREE_COURSE;
                }
                else if (treeType.equals(LMSConstants.TREE_PUBLISH_COURSE))
                {
                        treeTypeTemp = LMSConstants.TREE_COURSE;
                }
                else if (treeType.equals(LMSConstants.TREE_PUBLISH_CERT))
                {
                        treeTypeTemp = LMSConstants.TREE_CERT;
                }
                else if (treeType.equals(LMSConstants.TREE_COL_SELECT))
                {
                        treeTypeTemp = LMSConstants.TREE_SELECT_NO_SELECT_COURSE;
                }
                else if (treeType.equals(LMSConstants.TREE_COL_PERSONAL_SELECT))
                {
                        treeTypeTemp = LMSConstants.TREE_STUDENT_SIGNUP;
                }
                else if (treeType.equals(LMSConstants.TREE_SELECT_NO_SELECT_COURSE))
                {
                        treeTypeTemp = LMSConstants.TREE_SELECT_NO_SELECT_COURSE;
                }

                TreeControl control = (TreeControl) session.getAttribute(treeTypeTemp);
                // Handle a tree expand/contract event
                name = request.getParameter("tree");

                LogUtil.info("CourseTree==========",
                        "name ======================" + name);

                if ((name != null) && (name.indexOf("/") > 0))
                {
                        List temp = StringUtil.split(name, "/");
                        catalogID = (String) temp.get(1);
                        LogUtil.info("CourseTree==",
                                "catalogID's length ================" + catalogID.length());
                }

                if (name != null)
                {
                        LogUtil.info("course",
                                "[TreeControlAction]Tree expand/contract on " + name);

                        TreeControlNode node = null;
                        node = control.findNode(name);

                        if (node != null)
                        {
                                LogUtil.debug("course", "[TreeControlAction]Found Node: " +
                                        name);
                                node.setExpanded(!node.isExpanded());
                                control.selectNode(name);

                                if (!node.isExpanded())
                                {
                                        TreeControlNode parentNode = node.getParent();

                                        if (parentNode != null)
                                        {
                                                String temp = parentNode.getName();

                                                if (!temp.equals("ROOT-NODE"))
                                                {
                                                        List tempList = StringUtil.split(temp, "/");
                                                        catalogID = (String) tempList.get(1);
                                                }
                                                else
                                                {
                                                        catalogID = "0";
                                                }
                                        }
                                }
                        }
                }
                else
                {
                        LogUtil.debug("course", "[TreeControlAction]tree param is null");
                }

                // Handle a select item event
                name = request.getParameter("select");

                if (name != null)
                {
                        LogUtil.debug("course", "[TreeControlAction]Select event on " +
                                name);
                        control.selectNode(name);
                }

                MultipartParamUtils mp = new MultipartParamUtils(request,
                        1024 * 1014 * 10);
                request.setAttribute("catalogID", catalogID);
                request.setAttribute("mp", mp);

                if (treeType.equals(LMSConstants.TREE_COURSE))
                {
                        resultScreen = "return_tree_course";
                }
                else if (treeType.equals(LMSConstants.TREE_CERT))
                {
                        resultScreen = "return_tree_cert";
                }
                else if (treeType.equals(LMSConstants.TREE_CERT_MASTER))
                {
                        resultScreen = "return_tree_cert_master";
                }
                else if (treeType.equals(LMSConstants.TREE_PUBLISH))
                {
                        resultScreen = "return_tree_publish";

                        if (request.getParameter("selectCourse") != null)
                        {
                                LogUtil.debug("TreeControlAction",
                                        "[TreeControlAction] selectCourse====== " +
                                                request.getParameter("selectCourse"));
                                resultScreen = "return_tree_publish_catRemove";
                        }
                }
                else if (treeType.equals(LMSConstants.TREE_PUBLISHED_COURSE))
                {
                        resultScreen = "return_tree_published_course";
                }
                else if (treeType.equals(LMSConstants.TREE_PUBLISHED_CERT))
                {
                        resultScreen = "return_tree_published_cert";
                }
                else if (treeType.equals(LMSConstants.TREE_SELECT))
                {
                        resultScreen = "return_tree_select";
                }
                else if (treeType.equals(LMSConstants.TREE_PUBLISH_COURSE))
                {
                        resultScreen = "return_tree_publish_course";
                }
                else if (treeType.equals(LMSConstants.TREE_PUBLISH_CERT))
                {
                        resultScreen = "return_tree_publish_cert";
                }
                else if (treeType.equals(LMSConstants.TREE_COL_SELECT))
                {
                        resultScreen = "return_tree_col_select";
                }
                else if (treeType.equals(LMSConstants.TREE_COL_PERSONAL_SELECT))
                {
                        resultScreen = "return_tree_col_personal_select";
                }
                else if (treeType.equals(LMSConstants.TREE_SELECT_NO_SELECT_COURSE))
                {
                        resultScreen = "return_tree_graduateManage";
                }

                LogUtil.debug("course",
                        "[TreeControlAction]--resultScreen=" + resultScreen);

                // Forward back to the test page
                return mapping.findForward(resultScreen);
        }
}
