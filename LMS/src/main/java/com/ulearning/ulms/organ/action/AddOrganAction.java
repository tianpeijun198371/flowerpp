/**
 * AddOrganAction.java.
 * User: huangsb  Date: 2004-4-23
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd. * All rights reserved.
 */
package com.ulearning.ulms.organ.action;

import com.ulearning.ulms.core.util.Config;
import com.ulearning.ulms.core.util.StringUtil;
import com.ulearning.ulms.core.util.DateTimeUtil;
import com.ulearning.ulms.core.util.UploadUtil;
import com.ulearning.ulms.organ.bean.OrganHelper;
import com.ulearning.ulms.organ.dao.OrganDAO;
import com.ulearning.ulms.organ.dao.OrganDAOFactory;
import com.ulearning.ulms.organ.exceptions.OrganDAOSysException;
import com.ulearning.ulms.organ.form.OrganForm;
import com.ulearning.ulms.organ.form.OrganJieFo;
import com.ulearning.ulms.util.LMSConstants;
import com.ulearning.ulms.util.log.LogUtil;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import org.apache.webapp.admin.TreeControl;
import org.apache.webapp.admin.TreeControlNode;
import org.apache.commons.lang.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class AddOrganAction extends Action
{
        public ActionForward execute(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response)
                throws Exception
        {
                String resultScreen = "success";
                OrganForm of = (OrganForm) form;
                String orgName = of.getOrgName();
                String orgID = of.getOrgNO();
                //int dupOrgIDs=OrganHelper.getOrgID(orgName);
                int dupOrgIDs = OrganHelper.getOrgID(of.getParentID(), orgName);
                if (dupOrgIDs != -1)
                {
                        request.setAttribute(LMSConstants.ERROR_PAGE_INFO, "学校名称不能重复！");

                        return mapping.findForward(LMSConstants.ERROR_FORWARD);
                }

                //int dupOrgID = OrganHelper.getOrgIDByCode(orgID);

                //if (dupOrgID != -1)
                //{
                        //request.setAttribute(LMSConstants.ERROR_PAGE_INFO, "学校编号不能重复！");

                        //return mapping.findForward(LMSConstants.ERROR_FORWARD);
                //}

                if (of.getStartDateStr()!=null&&!of.getStartDateStr().equals(""))
                {
                        String[] tmp = StringUtil.splitString(of.getStartDateStr(), "-");
                        //of.setStartDate(DateTimeUtil.toDate(tmp[1], tmp[2], tmp[0], "0","0", "0"));
                        of.setStartDate(DateTimeUtil.parseDate(of.getStartDateStr()));
                        //System.out.println(DateTimeUtil.toDate(tmp[1], tmp[2], tmp[0],"0", "0", "0"));
                }
                if (of.getEndDateStr()!=null&&!of.getEndDateStr().equals(""))
                {
                        String[] tmp = StringUtil.splitString(of.getEndDateStr(), "-");
                        //of.setEndDate(DateTimeUtil.toDate(tmp[1], tmp[2], tmp[0], "0","0", "0"));
                        of.setStartDate(DateTimeUtil.parseDate(of.getEndDateStr()));
                        //System.out.println(DateTimeUtil.toDate(tmp[1], tmp[2], tmp[0],"0","0", "0"));
                }

                if (request.getContentType().startsWith("multipart/form-data"))
                {
                        try
                        {
                                UploadUtil.executeUpload(of,
                                        request, response);
                        }
                        catch (Exception e)
                        {
                                e.printStackTrace();
                                throw e;
                        }
                        String tmp = StringUtils.trimToEmpty((String) request.getAttribute("newFilePath"));
                        of.setLogo(tmp);
                }
                //add organ to jieFo
                int organID = 0;

                try
                {
                        OrganDAO dao = OrganDAOFactory.getDAO();
                        organID = dao.addOrgan(of);

                        try
                        {
                                if (Config.getIsIntegrateJieFo())
                                {
                                        if (of.getIsAsp() == 1)
                                        {
                                                OrganJieFo ojf = new OrganJieFo();
                                                ojf.setOrganID(organID);
                                                ojf.setOrganName(of.getOrgName());
                                                ojf.setOrganBrief(of.getDescription());
                                                dao.addJieFoOrgan(ojf);
                                        }
                                }
                        }
                        catch (Exception e)
                        {
                                e.printStackTrace(); //To change body of catch statement use File | Settings | File Templates.
                        }
                }
                catch (Exception e)
                {
                        LogUtil.debug("[AddJieFoAction] Exception Begin ******************",
                                orgName);
                        e.printStackTrace();
                        LogUtil.debug("[AddJieFoAction] Exception Begin ******************",
                                orgName);
                }

                //add a organ node to tree  and context
                HttpSession session = request.getSession();
                String aspID = "0";

                if (session.getAttribute(LMSConstants.USER_ASPID_KEY) != null)
                {
                        aspID = (String) session.getAttribute(LMSConstants.USER_ASPID_KEY);
                }

                TreeControl control = null;
                TreeControl controlInContext = null;

                control = (TreeControl) session.getAttribute(LMSConstants.TREE_ORGAN);
                //LogUtil.info("control = ","control = "+control);
                controlInContext = (TreeControl) getServlet().getServletContext()
                        .getAttribute(LMSConstants.TREE_ORGAN +
                                aspID);

                //LogUtil.info("controlInContext","controlInContext = "+control);
                int selfOrg = 0;

                if (session.getAttribute(LMSConstants.USER_ORGID_KEY) != null)
                {
                        selfOrg = Integer.parseInt((String) session.getAttribute(
                                LMSConstants.USER_ORGID_KEY));
                }

                String nodeName = "orgID/" + of.getParentID();

                if ((of.getParentID() == 0) || (of.getParentID() == selfOrg))
                {
                        nodeName = "ROOT-NODE";
                }

                if (control != null)
                {
                        System.out.println("nodeName = " + nodeName);

                        TreeControlNode parentNode = control.findNode(nodeName);
                        System.out.println("parentNode = " + parentNode);
                        parentNode.setExpanded(true);
                        control.selectNode(nodeName);

                        TreeControlNode subtree = null;
                        String name = "";

                        if (of.getIsAsp() == 1)
                        {
                                name += ("(单位)" + of.getOrgName());
                        }
                        else
                        {
                                name += ("(单位)" + of.getOrgName());
                        }

                        if (parentNode != null)
                        {
                                subtree = new TreeControlNode("orgID/" + organID,
                                        "../../images/index_dot06.gif", name, null, null,
                                        false, "Organ");
                                parentNode.addChild(subtree);
                        }

                        if (controlInContext != null)
                        {
                                parentNode = controlInContext.findNode(nodeName);

                                if (parentNode != null)
                                {
                                        subtree = new TreeControlNode("orgID/" + organID,
                                                "../../images/index_dot06.gif", name, null, null,
                                                false, "Organ");
                                        parentNode.addChild(subtree);
                                }
                        }
                }

                request.setAttribute("catalogID", String.valueOf(of.getParentID()));
                LogUtil.info("admin",
                        "[AddOrganAction]===========resultScreen = " + resultScreen);

                // Forward to result page
                return mapping.findForward(resultScreen);
        }
}
