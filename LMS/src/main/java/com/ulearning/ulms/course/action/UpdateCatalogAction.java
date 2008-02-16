/**
 * UpdateCatalogAction.java.
 * User: fengch  Date: 2004-4-28
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.course.action;

import com.ulearning.ulms.core.security.bean.SecurityConstants;
import com.ulearning.ulms.core.util.Config;
import com.ulearning.ulms.course.dao.MasterDAO;
import com.ulearning.ulms.course.dao.MasterDAOFactory;
import com.ulearning.ulms.course.exceptions.CatalogIsExisitException;
import com.ulearning.ulms.course.model.CatalogModel;
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
import org.apache.commons.lang.StringUtils;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class UpdateCatalogAction extends Action
{
        public ActionForward execute(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response)
                throws Exception
        {
                String resultScreen = "success";
                String type = request.getParameter("masterType");
                CatalogModel cat = (CatalogModel) form;
                MasterDAO dao = MasterDAOFactory.getDAO();
                HttpSession session = request.getSession();
                ServletContext servletContext = getServlet().getServletContext();
                String iscoursecert = request.getParameter("iscoursecert"); //用来判断是哪个页面做删除（课程页面、培训班页面）

                int aspID = 0;
                int orgID = 0;

                if (session.getAttribute(LMSConstants.USER_ASPID_KEY) != null)
                {
                        aspID = Integer.parseInt((String) session.getAttribute(
                                LMSConstants.USER_ASPID_KEY));
                }

                if (session.getAttribute(LMSConstants.USER_ORGID_KEY) != null)
                {
                        orgID = Integer.parseInt((String) session.getAttribute(
                                LMSConstants.USER_ORGID_KEY));
                }

                if (dao.isExistCatalog(cat.getName(), cat.getCatalogID(),
                        cat.getParentID(), cat.getType(), aspID, orgID))
                {
                        throw new CatalogIsExisitException("指定的目录名与现有的目录名重名,请指定另一目录名!");
                }

                cat.setOrgID(orgID);
                cat.setAspID(aspID);
                dao.updateCatalog(cat);

                LogUtil.debug("Catalog",
                        "[updateCatalogAction]===========resultScreen = " + resultScreen);

                //update catalog node in tree
                TreeControl control = null;
                String treeType = null;

                /*TreeControl control1 =
              (TreeControl) session.getAttribute(LMSConstants.TREE_CERT);
              TreeControl control2 =
              (TreeControl) session.getAttribute(LMSConstants.TREE_PUBLISH);
              TreeControl control3 =
              (TreeControl) session.getAttribute(LMSConstants.TREE_SELECT); */
                if (cat.getType() == CourseKeys.CATALOG_MASTER_COURSE)
                {
                        control = (TreeControl) session.getAttribute(LMSConstants.TREE_COURSE);
                        treeType = LMSConstants.TREE_COURSE;
                }
                else if (cat.getType() == CourseKeys.CATALOG_MASTER_CERT)
                {
                        control = (TreeControl) session.getAttribute(LMSConstants.TREE_CERT);
                        treeType = LMSConstants.TREE_CERT;
                }
                else if (cat.getType() == CourseKeys.CATALOG_COURSE_TYPE)
                {
                        control = (TreeControl) session.getAttribute(LMSConstants.TREE_PUBLISH);
                        treeType = LMSConstants.TREE_PUBLISH;

                        /*if((request.getParameter("relationType")).equals(String.valueOf(SecurityConstants.USER_COURSE_RELATION)))
                       {
                                       control = (TreeControl) session.getAttribute(LMSConstants.TREE_PUBLISHED_COURSE);
                                       treeType =  LMSConstants.TREE_PUBLISHED_COURSE;
                       }else
                       {
                                       control = (TreeControl) session.getAttribute(LMSConstants.TREE_PUBLISHED_CERT);
                                       treeType =  LMSConstants.TREE_PUBLISHED_CERT;
                       } */
                }

                if (control != null)
                {
                        TreeControlNode currentNode = control.findNode("catalogIDs/" +
                                cat.getCatalogID());

                        if (currentNode != null)
                        {
                                currentNode.setLabel(cat.getName());
                        }
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
                TreeFlush.flush(servletContext, session, orgID, treeType);
                request.setAttribute("catalogID", String.valueOf(cat.getCatalogID()));

                // Forward to result page
                if ((type != null) &&
                        (Integer.parseInt(type) == SecurityConstants.USER_CERTIFICATE_RELATION))
                {
                        resultScreen = "success_cert";
                }

               
                return mapping.findForward(resultScreen);
        }
}
