/**
 * FcourseAction.java.
 * User: Administrator Date: 2005-12-31 2:19:26
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.finance.action;

import com.ulearning.ulms.admin.certificate.bean.CertHelper;
import com.ulearning.ulms.admin.certificate.form.CertForm;
import com.ulearning.ulms.admin.certificate.webimpls.CertWebImpl;
import com.ulearning.ulms.core.security.bean.SecurityConstants;
import com.ulearning.ulms.core.util.StringUtil;
import com.ulearning.ulms.course.exceptions.UserExistedInCourseException;
import com.ulearning.ulms.course.helper.CourseHelper;
import com.ulearning.ulms.course.helper.CourseUserHelper;
import com.ulearning.ulms.course.model.CourseModel;
import com.ulearning.ulms.course.util.CourseKeys;
import com.ulearning.ulms.finance.form.UserAccountDetailForm;
import com.ulearning.ulms.finance.helper.UserAccountHelper;
import com.ulearning.ulms.user.bean.UserHelper;
import com.ulearning.ulms.user.form.UserForm;
import com.ulearning.ulms.util.LMSConstants;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 */
public class FcourseAction extends Action
{
        public ActionForward execute(ActionMapping actionMapping,
                                     ActionForm actionForm, HttpServletRequest request,
                                     HttpServletResponse response) throws Exception
        {
                String resultScreen = "success";
                String[] userIDs = null;
                String state_str = null;
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

                String operID = (String) request.getSession()
                        .getAttribute(LMSConstants.USERID_KEY);
                String operName = (String) request.getSession()
                        .getAttribute(LMSConstants.LOGINNAME_KEY_NAME);
                List userIDs1 = new ArrayList();
                List states1 = new ArrayList();
                userIDs = request.getParameterValues("userID");

                List certusers = CertHelper.getCertStudayingUser(relationID);

                int certusersstuday = certusers.size(); //在培训班中学习、毕业的学员
                int certinusers = certForm.getStudenthow(); //培训班中允许添加的人数
                int addusers = 0; //培训班允许添加的人数
                int requser = 0; //勾选的人数

                if (certusersstuday < certinusers)
                {
                        addusers = certinusers - certusersstuday;
                }

                for (int i = 0; i < userIDs.length; i++)
                {
                        state_str = request.getParameter("give_" + userIDs[i]);

                        if ((state_str != null) && (state_str.equals("1")))
                        {
                                requser++;
                        }
                }

                if (addusers <= 0)
                {
                        throw new UserExistedInCourseException("本培训班的人员已满！");
                }
                else if ((addusers > 0) && (requser > addusers))
                {
                        throw new UserExistedInCourseException("您添加的人数过多！" + "最多还能添加" +
                                addusers + "人");
                }

                for (int i = 0; i < userIDs.length; i++)
                {
                        UserForm uf = UserHelper.getUser(userIDs[i]);
                        state_str = request.getParameter("give_" + userIDs[i]);

                        if ((state_str != null) && (state_str.equals("1")))
                        {
                                if (isCert)
                                {
                                        if (certForm.getApproveUser().equals("1|2"))
                                        {
                                                state = CourseKeys.COURSE_USER_NOT_CHECK_STATE;
                                        }
                                        else
                                        {
                                                state = CourseKeys.COURSE_USER_AVAILABLE_STATE;
                                        }
                                }
                                else
                                {
                                        state = CourseKeys.COURSE_USER_NOT_CHECK_STATE;
                                }

                                UserAccountHelper uacHelp = new UserAccountHelper();
                                UserAccountDetailForm uacf = new UserAccountDetailForm();
                                uacf.setUserID(Integer.parseInt(userIDs[i]));
                                uacf.setUaDetailUserName(uf.getName());
                                uacf.setUaDetailInOutType(2);
                                uacf.setUaDetailTypeID(10);
                                uacf.setUaDetailAmount(charge);
                                uacf.setUaDetailDate(new Date());
                                uacf.setUaDetailOperatorID(Integer.parseInt(operID));
                                uacf.setUaDetailOperatorName(operName);
                                uacf.setUaDetailEntityValue(relationID);
                                uacHelp.insert(uacf);
                                userIDs1.add(new Integer(userIDs[i]));
                                states1.add(new Integer(state));
                        }
                }

                CourseUserHelper.confirmApply(userIDs1, states1, relationID, type);

                return actionMapping.findForward(resultScreen);
        }
}
