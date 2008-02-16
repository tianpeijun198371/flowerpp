/**
 * Created by IntelliJ IDEA.
 * User: zengwj
 * Date: 2004-8-25
 * Time: 18:51:49
 * To change this template use File | Settings | File Templates.
 */
package com.ulearning.ulms.admin.colsignup.colstudent.action;

import com.ulearning.ulms.admin.colsignup.colsign.bean.ColSignHelper;
import com.ulearning.ulms.admin.colsignup.colstudent.bean.ColStudentHelper;
import com.ulearning.ulms.admin.colsignup.colstudent.form.ColStudentForm;
import com.ulearning.ulms.core.security.bean.SecurityConstants;
import com.ulearning.ulms.core.util.StringUtil;
import com.ulearning.ulms.course.helper.CourseUserHelper;
import com.ulearning.ulms.course.model.CourseRoleListModel;
import com.ulearning.ulms.course.model.CourseRoleModel;
import com.ulearning.ulms.course.model.CourseUserModel;
import com.ulearning.ulms.course.util.CourseKeys;
import com.ulearning.ulms.util.log.LogUtil;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class AddStudentAction extends Action
{
        public ActionForward execute(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response)
                throws Exception
        {
                String resultScreen = "success";

                int type = 0;
                String approved = "0";
                int colSignID = 0;
                int colSignDetailID = 0;
                int courseID = 0;
                int roleID = SecurityConstants.ROLE_COURSR_STUDENT;

                if (request.getParameter("type") != null)
                {
                        type = Integer.parseInt(request.getParameter("type"));
                        approved = request.getParameter("approved");
                        colSignID = Integer.parseInt(request.getParameter("colSignID"));
                        colSignDetailID = Integer.parseInt(request.getParameter(
                                "colSignDetailID"));
                        courseID = Integer.parseInt(request.getParameter("courseID"));
                }

                ArrayList roles = new ArrayList();
                roles.add(new CourseRoleModel(roleID, ""));

                CourseRoleListModel courseRoles = new CourseRoleListModel();
                courseRoles.setRoles(roles);

                Date joinTime = new Date();
                Date applyTime = new Date();
                Date finishedTime = null;

                //Update for sign up management
                int state = CourseKeys.COURSE_USER_AVAILABLE_STATE;
                CourseUserModel cum = new CourseUserModel(courseID, type, 0, state, "",
                        "", courseRoles, joinTime, applyTime, finishedTime);

                String[] relationID = request.getParameterValues("relationID");

                String feeType = request.getParameter("feeType");
                boolean isOnlyApproved = false;
                boolean isOnlyCharged = false;
                boolean isApprovedAndCharged = false;
                LogUtil.info("course", "[addStudentAction]-----feeType=" + feeType);

                if (feeType.equals("approved"))
                {
                        isOnlyApproved = true;
                        isOnlyCharged = false;
                        isApprovedAndCharged = false;
                }
                else if (feeType.equals("chargeAndApproved"))
                {
                        isOnlyApproved = false;
                        isOnlyCharged = false;
                        isApprovedAndCharged = true;
                }
                else if (feeType.equals("charge"))
                {
                        isOnlyApproved = false;
                        isOnlyCharged = true;
                        isApprovedAndCharged = false;
                }

                LogUtil.info("course",
                        "[addStudentAction]-----isOnlyApproved=" + isOnlyApproved);
                LogUtil.info("course",
                        "[addStudentAction]-----isOnlyCharged=" + isOnlyCharged);
                LogUtil.info("course",
                        "[addStudentAction]-----isApprovedAndCharged=" +
                                isApprovedAndCharged);

                ColStudentForm csf_original = null;
                LogUtil.info("course", "[addStudentAction]-----1");
                //sign this colSign has approved
                ColSignHelper.approvedColSign(colSignID, "1");

                /// sign this colStudent has approved
                ColStudentForm csf = new ColStudentForm();
                csf.setColSignDetailID(colSignDetailID);
                csf.setApproved(approved);
                csf.setType(SecurityConstants.DEFAULT_USERID);

                int relationID_int;
                LogUtil.info("course",
                        "[addStudentAction]-----colSignDetailID=" + colSignDetailID);

                for (int i = 0; i < relationID.length; i++)
                {
                        relationID_int = StringUtil.parseInt(relationID[i]);
                        LogUtil.info("course",
                                "[addStudentAction]-----relationID_int=" + relationID_int);
                        csf_original = ColStudentHelper.getColStudent(colSignDetailID,
                                relationID_int, SecurityConstants.DEFAULT_USERID);
                        LogUtil.info("course",
                                "[addStudentAction]-----csf_original=" + csf_original);
                        csf.setRelationID(relationID_int);

                        if (isOnlyApproved)
                        {
                                csf.setApproved("1");
                                csf.setFeeState(csf_original.getFeeState());

                                ColStudentHelper.updateColStudent(csf);
                                //add course from user
                                addCourseUser(cum, courseRoles, relationID_int);
                        }
                        else if (isApprovedAndCharged)
                        {
                                csf.setApproved("1");
                                csf.setFeeState("1");
                                ColStudentHelper.updateColStudent(csf);
                                //add course from user
                                addCourseUser(cum, courseRoles, relationID_int);
                        }
                        else if (isOnlyCharged)
                        {
                                csf.setApproved(csf_original.getApproved());
                                csf.setFeeState("1");
                                ColStudentHelper.updateColStudent(csf);
                        }
                }

                LogUtil.info("course",
                        "[addStudentAction]-----resultScreen=" + resultScreen);

                return mapping.findForward(resultScreen);
        }

        private void addCourseUser(CourseUserModel cum,
                                   CourseRoleListModel courseRoles, int relatioID)
        {
                //add course to user
                courseRoles.setUserID(relatioID); //= new CourseRoleListModel(Integer.parseInt(relationID[i]), roles);
                cum.setUserID(relatioID);

                try
                {
                        CourseUserHelper.addCourseUser(cum);
                }
                catch (Exception e)
                {
                }
        }
}
