/**
 * UpdateOrganAction.java.
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
import java.util.Date;


public class UpdateOrganAction extends Action
{
        public ActionForward execute(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response)
                throws Exception
        {
                String resultScreen = "success";
                OrganForm of = (OrganForm) form;
                String orgName = of.getOrgName();
                String orgNo = of.getOrgNO();
                if((of.getProvince() == null)||(of.getProvince().trim().length() == 0))
                {
                    of.setProvince(request.getParameter("province_up"));
                    of.setCity(request.getParameter("city_up"));
                    of.setCounty(request.getParameter("county_up"));
                }
                int dupOrgIDs = OrganHelper.getOrgID(of.getParentID(), orgName);

                if ((dupOrgIDs != -1) && (dupOrgIDs != of.getOrgID()))
                {
                        request.setAttribute(LMSConstants.ERROR_PAGE_INFO, "单位名称不能重复！");

                        return mapping.findForward(LMSConstants.ERROR_FORWARD);
                }

                OrganForm organForm = OrganHelper.getOrgan(of.getOrgID());
                //int dupOrgID= organForm.getOrgID();
                //if ((dupOrgID != -1) && (dupOrgID != of.getOrgID()))
                //{
                        //request.setAttribute(LMSConstants.ERROR_PAGE_INFO, "单位编号不能重复！");

                        //return mapping.findForward(LMSConstants.ERROR_FORWARD);
                //}
                if(organForm.getRegDate()==null)
                {
                    of.setRegDate(new Date(System.currentTimeMillis()));
                }else{
                    of.setRegDate(organForm.getRegDate());
                }

                //Judgy what feature user want to update
                //                System.out.println("orgName == " + orgName);
                //                System.out.println("organ.getOrgName() == " + organ.getOrgName());
                //                if (!organ.getOrgName().equalsIgnoreCase( orgName))
                //                {
                //                        int dupOrgID = OrganHelper.getOrgID(of.getParentID(), orgName);
                //                        System.out.println("dupOrgID == " + dupOrgID);
                //                        if (dupOrgID != -1)
                //                        {
                //                                request.setAttribute(LMSConstants.ERROR_PAGE_INFO, "机构名称或ASP名称不能重复！");
                //                                return mapping.findForward(LMSConstants.ERROR_FORWARD);
                //                        }
                //                }
                //                System.out.println("orgNo == " + orgNo);
                //                System.out.println("organ.getOrgNO()  == " + organ.getOrgNO());
                //                if (!organ.getOrgNO().equalsIgnoreCase(orgNo))
                //                {
                //                        int dupOrgID = OrganHelper.getOrgIDByCode(orgNo);
                //                        System.out.println("dupOrgID == " + dupOrgID);
                //                        if (dupOrgID != -1)
                //                        {
                //                                request.setAttribute(LMSConstants.ERROR_PAGE_INFO, "机构编号或ASP编号不能重复！");
                //                                return mapping.findForward(LMSConstants.ERROR_FORWARD);
                //                        }
                //                }
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
//                        of.setLogo(tmp);
                        if(tmp!=null&&!tmp.equals(""))
                        {
                           of.setLogo(tmp);
                        }
                }

                OrganDAO dao = OrganDAOFactory.getDAO();
                dao.updateOrgan(of);

                try
                {
                        if (Config.getIsIntegrateJieFo())
                        {
                                if (of.getIsAsp() == 1)
                                {
                                        OrganJieFo ojf = new OrganJieFo();
                                        ojf.setOrganID(of.getOrgID());
                                        ojf.setOrganName(of.getOrgName());
                                        ojf.setOrganBrief(of.getDescription());
                                        dao.updateJieFoOrgan(ojf);
                                }
                        }
                }
                catch (Exception e)
                {
                        e.printStackTrace(); //To change body of catch statement use File | Settings | File Templates.
                }

                //update a organ node in tree and context
                HttpSession session = request.getSession();
                String aspID = "0";

                if (session.getAttribute(LMSConstants.USER_ASPID_KEY) != null)
                {
                        aspID = (String) session.getAttribute(LMSConstants.USER_ASPID_KEY);
                }

                TreeControl controlInContext = null;
                TreeControl control = (TreeControl) session.getAttribute(LMSConstants.TREE_ORGAN);

                controlInContext = (TreeControl) getServlet().getServletContext()
                        .getAttribute(LMSConstants.TREE_ORGAN +
                                aspID);

                if (control != null)
                {
                        TreeControlNode currentNode = null;
                        currentNode = control.findNode("orgID/" + of.getOrgID());

                        String name = "";

                        if (of.getIsAsp() == 1)
                        {
                                name += ("(单位)" + of.getOrgName());
                        }
                        else
                        {
                                name += ("(单位)" + of.getOrgName());
                        }

                        if (currentNode != null)
                        {
                                currentNode.setLabel(name);
                        }

                        if (controlInContext != null)
                        {
                                currentNode = controlInContext.findNode("orgID/" +
                                        of.getOrgID());

                                if (currentNode != null)
                                {
                                        currentNode.setLabel(name);
                                }
                        }
                }

                request.setAttribute("catalogID", String.valueOf(of.getParentID()));
                LogUtil.info("system",
                        "[UpdateOrganAction]===========resultScreen = " + resultScreen);

                // Forward to result page
                return mapping.findForward(resultScreen);
        }
}
