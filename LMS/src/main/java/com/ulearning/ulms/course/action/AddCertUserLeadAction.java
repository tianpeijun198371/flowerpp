package com.ulearning.ulms.course.action;

import com.ulearning.ulms.admin.certificate.bean.CertHelper;
import com.ulearning.ulms.admin.certificate.model.CertCurrentStatus;
import com.ulearning.ulms.core.security.bean.SecurityConstants;
import com.ulearning.ulms.core.security.bean.SecurityHelper;
import com.ulearning.ulms.core.util.Config;
import com.ulearning.ulms.core.util.StringUtil;
import com.ulearning.ulms.course.exceptions.CourseAppException;
import com.ulearning.ulms.course.exceptions.CourseUserHasFullException;
import com.ulearning.ulms.course.exceptions.UserExistedInCourseException;
import com.ulearning.ulms.course.helper.CourseUserHelper;
import com.ulearning.ulms.course.model.*;
import com.ulearning.ulms.course.util.CourseKeys;
import com.ulearning.ulms.course.webimpls.CourseWebImpl;
import com.ulearning.ulms.user.bean.UserHelper;
import com.ulearning.ulms.util.LMSConstants;
import com.ulearning.ulms.util.log.LogUtil;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 * Created by IntelliJ IDEA.
 * User: zhangy
 * Date: 2007-7-18
 * Time: 10:19:53
 * To change this template use File | Settings | File Templates.
 */
public class AddCertUserLeadAction extends Action
{
        CourseWebImpl cwi = new CourseWebImpl();

        public ActionForward execute(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response)
                throws Exception
        {
                String resultScreen = "success";
                int userID = 0;

                HttpSession session = request.getSession();

                String relationID_str = request.getParameter("relationID");
                int relationID = Integer.parseInt(relationID_str);

                int state = CourseKeys.COURSE_USER_APPLY_STATE;
                int type = StringUtil.parseInt((String) request.getParameter("type"));

                //		String approveUser = request.getParameter("approveUser");
                //		if(approveUser != null && approveUser.equals("1|2"))
                //		{
                //			state = CourseKeys.COURSE_USER_NOT_CHECK_STATE;
                //
                //		}
                //		else if(approveUser != null && approveUser.equals("1|"))
                //		{
                //			state = CourseKeys.COURSE_USER_APPLY_STATE;
                //		}
                //		else if(approveUser != null && approveUser.equals("|2"))
                //		{
                //			state = CourseKeys.COURSE_USER_APPLY_STATE;
                //		}
                LinkedList userIDList = (LinkedList) session.getAttribute("existList");

                //检查用户是否存在此课程中
                checkUsers(userIDList, relationID, type);

                for (int i = 0; i < userIDList.size(); i++)
                {
                        userID = Integer.parseInt((String) userIDList.get(i));

                        Date joinTime = new Date();
                        Date applyTime = new Date();
                        Date finishedTime = null;

                        CourseUserModel cum = new CourseUserModel(relationID, type, userID,
                                state, "",
                                (UserHelper.getUser(Integer.toString(userID))).getName(),
                                joinTime, applyTime, finishedTime);

                        //CourseUserHelper.addCertUser(cum);
                        CourseUserHelper.addCourseUser(cum.getRelationID(), cum.getType(),
                                cum.getUserID(), state, SecurityConstants.ROLE_COURSR_STUDENT);
                }

                session.removeAttribute("existList");

                // Forward to result page
                LogUtil.info("course",
                        "[AddOneUserAction]===========resultScreen = " + resultScreen);

                return mapping.findForward(resultScreen);
        }

        /**
         * 检查多个用户是否存在此课程中
         *
         * @param users
         * @param relationID
         * @param type
         * @throws CourseAppException
         */
        private void checkUsers(List users, int relationID, int type)
                throws CourseAppException
        {
                int roleID = 0;
                int userID = 0;
                String exceptionMsg = "<br>";
                String name = "培训班";

                if (type == SecurityConstants.USER_CERTIFICATE_RELATION)
                {
                        name = Config.getCERT_I18N_NAME();
                }

                for (int j = 0; j < users.size(); j++)
                {
                        userID = Integer.parseInt((String) users.get(j));

                        boolean isaddCertUser = CourseUserHelper.isaddCertUser(userID,
                                relationID, type);

                        if (isaddCertUser)
                        {
                                exceptionMsg += ("■ 用户-" +
                                        UserHelper.getUser(String.valueOf(userID)).getLoginName() +
                                        "已经存在此" + name + "中!<br>");
                        }
                }

                if (!exceptionMsg.equals("<br>"))
                {
                        throw new UserExistedInCourseException(exceptionMsg);
                }
        }
}
