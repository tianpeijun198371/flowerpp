/** * UpdateAutoInformConfig.java.
 * User: chenxj  Date: 2004-8-19
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved. */
package com.ulearning.ulms.admin.sysconfig.action;

import com.ulearning.ulms.admin.sysconfig.dao.SysConfigDAO;
import com.ulearning.ulms.admin.sysconfig.dao.SysConfigDAOFactory;
import com.ulearning.ulms.admin.sysconfig.form.AutoInformForm;
import com.ulearning.ulms.admin.sysconfig.form.SysConfigForm;
import com.ulearning.ulms.util.log.LogUtil;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import java.util.ArrayList;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class UpdateAutoInformConfig extends Action
{
        public ActionForward execute(ActionMapping actionMapping,
                                     ActionForm actionForm, HttpServletRequest httpServletRequest,
                                     HttpServletResponse httpServletResponse) throws Exception
        {
                String resultScreen = "success";
                StringTokenizer isMail = new StringTokenizer(httpServletRequest.getParameter(
                        "isMailArray"), "#");
                StringTokenizer isMSG = new StringTokenizer(httpServletRequest.getParameter(
                        "isMSGArray"), "#");
                StringTokenizer type = new StringTokenizer(httpServletRequest.getParameter(
                        "typeArray"), "#");

                String orgID = httpServletRequest.getParameter("orgID");

                int intOrgID = Integer.parseInt(orgID);

                //System.out.println("fff:"+intOrgID);
                //                String orgID = "1111";

                //AutoInformForm aic = new AutoInformForm();
                ArrayList al = new ArrayList();

                while (isMail.hasMoreTokens())
                {
                        AutoInformForm aic = new AutoInformForm();
                        aic.setIsMail(isMail.nextToken());
                        aic.setIsMSG(isMSG.nextToken());
                        aic.setType(Integer.parseInt(type.nextToken()));
                        aic.setOrgID(intOrgID);
                        al.add(aic);
                }

                SysConfigDAO dao = SysConfigDAOFactory.getDAO();
                dao.updateAutoInformConfig(al);
                LogUtil.info("system",
                        "[AddSysConfigAction]===========resultScreen = " + resultScreen);

                return actionMapping.findForward(resultScreen);
        }
}
