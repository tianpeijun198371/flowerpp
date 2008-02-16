/**
 * OrganTreeBuilder
 * User: dengj
 * Date: Apr 26, 2006
 * Time: 5:30:57 PM
 * Copyright (c) 2006-2007.Beijing WenhuaOnline Sci-tech DevelopmentCo.,Ltd.
 */
package com.ulearning.ulms.organ.util;

import com.ulearning.ulms.organ.bean.OrganHelper;
import com.ulearning.ulms.organ.form.OrganForm;
import com.ulearning.ulms.util.LMSConstants;
import com.ulearning.ulms.util.treeControl.UlmsTreeBuilder;

import org.apache.webapp.admin.ApplicationServlet;
import org.apache.webapp.admin.TreeControl;
import org.apache.webapp.admin.TreeControlNode;

import java.util.List;

import javax.servlet.http.HttpServletRequest;


public class OrganTreeBuilder implements UlmsTreeBuilder
{
        private String domain = "Organ";

        public void buildTree(TreeControl treeControl,
                              ApplicationServlet applicationServlet,
                              HttpServletRequest httpServletRequest)
        {
                buildTree(treeControl, httpServletRequest);
        }

        /**
         * Add the required nodes to the specified <code>treeControl</code> instance
         *
         * @param treeControl The <code>TreeControl</code> to which we should add our nodes
         */
        public void buildTree(TreeControl treeControl)
        {
        }

        /**
         * Add the required nodes to the specified <code>treeControl</code> instance
         * according to trrContol object and the servlet request we are processing
         *
         * @param treeControl
         * @param httpServletRequest
         */
        public void buildTree(TreeControl treeControl,
                              HttpServletRequest httpServletRequest)
        {
                try
                {
                        TreeControlNode root = treeControl.getRoot();
                        int parentID = 0;

                        if ((String) httpServletRequest.getSession()
                                .getAttribute(LMSConstants.USER_ORGID_KEY) != null)
                        {
                                parentID = Integer.parseInt((String) httpServletRequest.getSession()
                                        .getAttribute(LMSConstants.USER_ORGID_KEY));
                        }

                        List organList = OrganHelper.getOrganList(parentID);
                        OrganForm organForm = null;
                        TreeControlNode subtree = null;

                        for (int i = 0; i < organList.size(); i++)
                        {
                                String name = "";
                                organForm = (OrganForm) organList.get(i);

                                if (organForm.getIsAsp() == 1)
                                {
                                        name += ("(单位)" + organForm.getOrgName());
                                }
                                else
                                {
                                        name += ("(单位)" + organForm.getOrgName());
                                }

                                subtree = new TreeControlNode("orgID/" + organForm.getOrgID(),
                                        "../../images/index_dot06.gif", name, null, null,
                                        false, domain);
                                root.addChild(subtree);
                        }
                }
                catch (Exception e)
                {
                        e.printStackTrace();
                }
        }
}
