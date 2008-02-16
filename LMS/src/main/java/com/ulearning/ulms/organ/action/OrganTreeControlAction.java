/**
 * Created by IntelliJ IDEA.
 * User: zengwj
 * Date: 2004-7-27
 * Time: 13:41:18
 * To change this template use File | Settings | File Templates.
 */
package com.ulearning.ulms.organ.action;

import com.ulearning.ulms.core.util.StringUtil;
import com.ulearning.ulms.organ.bean.OrganHelper;
import com.ulearning.ulms.organ.form.OrganForm;
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


public class OrganTreeControlAction extends Action
{
        public ActionForward execute(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response)
                throws IOException, ServletException
        {
                String resultScreen = "success";
                LogUtil.debug("organtreecontrol",
                        "Entered OrganTreeControlAction:perform()");

                String parentID = "";

                String name = null;
                String treeType = request.getParameter("treeType");
                HttpSession session = request.getSession();
                TreeControl control = (TreeControl) session.getAttribute(treeType);
                TreeControlNode node = null;
                name = request.getParameter("tree");

                if ((name != null) && (name.indexOf("/") > 0))
                {
                        node = control.findNode(name);

                        List tempList = StringUtil.split(name, "/");
                        parentID = (String) tempList.get(1);

                        //add organ node to tree
                        List organList = null;
                        organList = OrganHelper.getOrganList(Integer.parseInt(parentID));

                        OrganForm of = null;
                        TreeControlNode subtree = null;

                        if ((node != null) && (organList != null))
                        {
                                LogUtil.debug("organtreecontrol", "Found Node: " + name);

                                for (int i = 0; i < organList.size(); i++)
                                {
                                        String organName = "";
                                        of = (OrganForm) organList.get(i);

                                        boolean isCatalog = false;
                                        TreeControlNode temp = null;

                                        //isCatalog = OrganHelper.isHasSubOrgan(of.getOrgID());
                                        if (of.getIsAsp() == 1)
                                        {
                                                organName += ("(单位)" + of.getOrgName());
                                        }
                                        else
                                        {
                                                organName += ("(部门)" + of.getOrgName());
                                        }

                                        /* if(isCatalog)
                                       {
                                               subtree = new TreeControlNode
                                                       (
                                                               "catalogID/" + of.getOrgID(),
                                                               "../../images/fclosed.gif",
                                                               organName,
                                                               null,
                                                               null,
                                                               false, "Organ");
                                               temp = control.findNode("catalogID/"+of.getOrgID());
                                               if(temp == null)
                                               node.addChild(subtree);
                                       }
                                       else
                                       { */
                                        subtree = new TreeControlNode("orgID/" + of.getOrgID(),
                                                "../../images/index_dot06.gif", organName, null,
                                                null, false, "Organ");
                                        temp = control.findNode("orgID/" + of.getOrgID());

                                        if (temp == null)
                                        {
                                                node.addChild(subtree);
                                        }

                                        // }
                                }

                                // Handle a tree expand/contract event
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
                                                        List parentList = StringUtil.split(temp, "/");
                                                        parentID = (String) parentList.get(1);
                                                }
                                                else
                                                {
                                                        parentID = "0";
                                                }
                                        }
                                }
                        }
                }

                MultipartParamUtils mp = new MultipartParamUtils(request,
                        1024 * 1014 * 10);
                request.setAttribute("parentID", parentID);
                request.setAttribute("mp", mp);
                request.setAttribute("catalogID", "1");

                if (treeType.equals(LMSConstants.TREE_ORGAN))
                {
                        resultScreen = "return_tree_organ";
                }

                if ((request.getParameter("operateType") != null) &&
                        (request.getParameter("operateType")).equals("0"))
                {
                        resultScreen = "return_tree_map";
                }

                return mapping.findForward(resultScreen);
        }
}
