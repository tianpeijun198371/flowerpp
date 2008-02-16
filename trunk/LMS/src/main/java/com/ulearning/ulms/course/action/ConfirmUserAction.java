/**
 * ConfirmUserAction.java.
 * User: ff  Date: 2004-9-21
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.course.action;

import com.ulearning.ulms.admin.certificate.form.CertForm;
import com.ulearning.ulms.admin.certificate.webimpls.CertWebImpl;
import com.ulearning.ulms.core.security.bean.SecurityConstants;
import com.ulearning.ulms.core.util.StringUtil;
import com.ulearning.ulms.course.helper.CourseHelper;
import com.ulearning.ulms.course.helper.CourseUserHelper;
import com.ulearning.ulms.course.model.CourseModel;
import com.ulearning.ulms.course.util.CourseKeys;
import com.ulearning.ulms.finance.form.UserAccountDetailForm;
import com.ulearning.ulms.finance.helper.UserAccountHelper;
import com.ulearning.ulms.user.bean.UserHelper;
import com.ulearning.ulms.user.form.UserForm;
import com.ulearning.ulms.util.LMSConstants;

import org.apache.commons.lang.StringUtils;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class ConfirmUserAction extends Action
{
        public ActionForward execute(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response)
                throws Exception
        {
                String resultScreen = "success";

                String[] userIDs = null;
                String state_str = null;
                int userID;
                int state;
                String relationID_str = request.getParameter("relationID");
                int relationID = StringUtil.parseInt(relationID_str);
                int type = StringUtil.parseInt((String) request.getParameter("type"));
                CourseModel course = null;
                CertForm certForm = null;
                double charge = 0;
                boolean isCert = false;

                //课程支出
                if (type == SecurityConstants.USER_COURSE_RELATION)
                {
                        course = CourseHelper.getCourse(relationID);
                        charge = course.getCharge();
                        isCert = false;
                }

                //培训班支出
                if (type == SecurityConstants.USER_CERTIFICATE_RELATION)
                {
                        certForm = new CertWebImpl().getCert(relationID);
                        charge = certForm.getFee();
                        isCert = true;
                }

                //CourseModel cm = CourseHelper.getCourse(relationID);
                //double fee = cm.getCharge();
                String operID = (String) request.getSession()
                        .getAttribute(LMSConstants.USERID_KEY);
                String operName = (String) request.getSession()
                        .getAttribute(LMSConstants.LOGINNAME_KEY_NAME);

                userIDs = request.getParameterValues("userID");

                List userIDList = new ArrayList();
                List stateList = new ArrayList();

                int i = 0;

                if (userIDs != null)
                {
                        for (; i < userIDs.length; i++)
                        {
                                userID = StringUtil.parseInt(userIDs[i]);

                                state_str = request.getParameter("state_" + userID);

                                System.out.println("[ConfirmUserAction]===========Parameter " +
                                        ("state_" + userID) + "=" + state_str);

                                if ((state_str != null) && state_str.equals("1"))
                                {
                                        UserForm uf = UserHelper.getUser(userIDs[i]);
                                        state = CourseKeys.COURSE_USER_AVAILABLE_STATE;

                                        UserAccountHelper uacHelp = new UserAccountHelper();
                                        UserAccountDetailForm uacf = new UserAccountDetailForm();
                                        uacf.setUserID(userID);
                                        uacf.setUaDetailUserName(uf.getName());
                                        uacf.setUaDetailInOutType(2);
                                        uacf.setUaDetailTypeID(10);
                                        uacf.setUaDetailAmount(charge);
                                        uacf.setUaDetailDate(new Date());
                                        uacf.setUaDetailOperatorID(Integer.parseInt(operID));
                                        uacf.setUaDetailOperatorName(operName);
                                        uacf.setUaDetailEntityValue(relationID);
                                }
                                else
                                {
                                        CourseUserHelper.inform(userID, relationID, type, true,
                                                false);

                                        if (isCert)
                                        {
                                                System.out.println(
                                                        "[ConfirmUserAction]===========certForm.getApproveUser()= " +
                                                                certForm.getApproveUser());

                                                String approveUser = StringUtils.trimToEmpty(certForm.getApproveUser());

                                                //需要财务或管理员审批
                                                if ((approveUser.indexOf("|2") != -1) ||
                                                        (approveUser.indexOf("1|") != -1))
                                                {
                                                        state = CourseKeys.COURSE_USER_NOT_CHECK_STATE;
                                                }
                                                else
                                                {
                                                        state = CourseKeys.COURSE_USER_APPLY_STATE;
                                                }
                                        }
                                        else
                                        {
                                                state = CourseKeys.COURSE_USER_NOT_CHECK_STATE;
                                        }
                                }

                                System.out.println("[ConfirmUserAction]===========state =" +
                                        state);
                                userIDList.add(new Integer(userID));
                                stateList.add(new Integer(state));
                        }
                }

                for (int j = 0; (stateList != null) && (j < stateList.size()); j++)
                {
                        userID = ((Integer) (userIDList.get(j))).intValue();
                        state = ((Integer) (stateList.get(j))).intValue();
                        System.out.println("[ConfirmUserAction]===========# userID=" +
                                userID + "， state =" + state);
                }

                CourseUserHelper.confirmApply(userIDList, stateList, relationID, type);

                if ((request.getParameter("returnType") != null) &&
                        ((String) request.getParameter("returnType")).equals("1"))
                {
                        resultScreen = "success_approve";
                }

                System.out.println("resultScreen================ " + resultScreen);

                System.out.println("[ConfirmUserAction]===========在 " + relationID +
                        " confirm " + (i));

                return mapping.findForward(resultScreen);
        }
}
