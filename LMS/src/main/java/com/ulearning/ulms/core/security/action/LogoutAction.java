/**
 * LogoutAction.java.
 * User: dengj  Date: 2004-4-29
 *
 *  updated by fengch. Date: 2004-11-12
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.core.security.action;

import com.ulearning.ulms.admin.sysconfig.bean.SysConfigHelper;
import com.ulearning.ulms.admin.sysconfig.form.SysConfigForm;
import com.ulearning.ulms.core.util.StringUtil;
import com.ulearning.ulms.lmslog.dao.LmslogDAO;
import com.ulearning.ulms.lmslog.dao.LmslogDAOFactory;
import com.ulearning.ulms.lmslog.form.LmslogForm;
import com.ulearning.ulms.lmslog.util.LmslogConstants;
import com.ulearning.ulms.tools.access.webimpls.AccessWebImpl;
import com.ulearning.ulms.util.LMSConstants;
import com.ulearning.ulms.util.log.LogUtil;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class LogoutAction extends Action
{
        public ActionForward execute(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response)
                throws Exception
        {
                //Clear the session
                HttpSession session = request.getSession();
                String visutType = request.getParameter("visutType");
                String visitID = request.getParameter("visitID");
                if ((session != null) &&
                        (session.getAttribute(LMSConstants.USERID_KEY) != null) &&
                        (session.getAttribute(LMSConstants.USER_ORGID_KEY) != null))
                {
                        SysConfigHelper helper = new SysConfigHelper();
                        SysConfigForm sysConfigForm = helper.getSysConfig("0");

                        if (sysConfigForm.getIsLogLogin().equals("1"))
                        {
                                int lmsUserID = StringUtil.parseInt((String) session.getAttribute(
                                        LMSConstants.USERID_KEY));
                                int lmsOrgID = StringUtil.parseInt((String) session.getAttribute(
                                        LMSConstants.USER_ORGID_KEY));
                                LogUtil.debug("system",
                                        "[LogoutAction]====================the name string is : ");

                                LmslogForm lmslogForm = new LmslogForm();
                                lmslogForm.setLogTypeID(LmslogConstants.LOGTYPE_USER);
                                lmslogForm.setUserID(lmsUserID);
                                lmslogForm.setOrgID(lmsOrgID);
                                lmslogForm.setUserIP(request.getRemoteAddr());
                                lmslogForm.setOperationTypeID(LmslogConstants.OPERATION_USER_LOGOUT);
                                lmslogForm.setOperationTable("LMSDB");
                                lmslogForm.setOperationObjectID(lmsUserID);
                                lmslogForm.setDescription("用户注销时将被记录到日志.");

                                LmslogDAO lmsdao = LmslogDAOFactory.getDAO();
                                lmsdao.insert(lmslogForm);

                                AccessWebImpl accessWebImpl = new AccessWebImpl();
                                accessWebImpl.logoutUpdate(lmsUserID);
                        }
                }

                session.invalidate();

                // Forward to result page
                //return mapping.findForward("success");

                    ActionForward forward = mapping.findForward("success");
                    StringBuffer bf = new StringBuffer(forward.getPath());
                    //首页
                    if(visutType!=null&&!visutType.equals(""))
                    {
                        if(visutType.equals(String.valueOf(LMSConstants.VISIT_USER)))
                        {
                                forward = mapping.findForward("portal");
                                bf = new StringBuffer(forward.getPath());

                        }else if(visutType.equals(String.valueOf(LMSConstants.VISIT_TEACHER)))
                        {
                                forward = mapping.findForward("teacher");
                                bf = new StringBuffer(forward.getPath());
                                bf.append("?userID=" + visitID);
                        }else if(visutType.equals(String.valueOf(LMSConstants.VISIT_SCHOOL)))
                        {
                                forward = mapping.findForward("school");
                                bf = new StringBuffer(forward.getPath());
                                bf.append("?aspID=" + visitID);
                        }else if(visutType.equals(String.valueOf(LMSConstants.VISIT_CLASS)))
                        {
                                forward = mapping.findForward("class");
                                bf = new StringBuffer(forward.getPath());
                                bf.append("?courseID=" + visitID);
                        }else if(visutType.equals(String.valueOf(LMSConstants.VISIT_STUDENT)))
                        {
                                forward = mapping.findForward("student");
                                bf = new StringBuffer(forward.getPath());
                                bf.append("?userID=" + visitID);
                        }
                   }
                    return new ActionForward(bf.toString());
        }
}
