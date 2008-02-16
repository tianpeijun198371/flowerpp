/**
 * Created by IntelliJ IDEA.
 * User: zengwj
 * Date: 2004-8-26
 * Time: 11:23:07
 * To change this template use File | Settings | File Templates.
 */
package com.ulearning.ulms.admin.colsignup.colstudent.action;

import com.ulearning.ulms.admin.colsignup.colsign.bean.ColSignHelper;
import com.ulearning.ulms.admin.colsignup.colstudent.bean.ColStudentHelper;
import com.ulearning.ulms.admin.colsignup.colstudent.form.ColStudentForm;
import com.ulearning.ulms.core.security.bean.SecurityConstants;
import com.ulearning.ulms.core.util.StringUtil;
import com.ulearning.ulms.course.helper.CourseUserHelper;
import com.ulearning.ulms.util.log.LogUtil;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class DelStudentAction extends Action
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

                if (request.getParameter("type") != null)
                {
                        type = Integer.parseInt(request.getParameter("type"));
                        approved = request.getParameter("approved");
                        colSignID = Integer.parseInt(request.getParameter("colSignID"));
                        colSignDetailID = Integer.parseInt(request.getParameter(
                                "colSignDetailID"));
                        courseID = Integer.parseInt(request.getParameter("courseID"));
                }

                String[] relationID = request.getParameterValues("relationID");

                String feeType = request.getParameter("feeType");
                boolean isOnlyUnApproved = false;
                boolean isOnlyUnCharged = false;
                boolean isUnApprovedAndUnCharged = false;
                LogUtil.info("course", "[DelStudentAction]-----feeType=" + feeType);

                if (feeType.equals("unApproved"))
                {
                        isOnlyUnApproved = true;
                        isOnlyUnCharged = false;
                        isUnApprovedAndUnCharged = false;
                }
                else if (feeType.equals("unChargeAndunApproved"))
                {
                        isOnlyUnApproved = false;
                        isOnlyUnCharged = false;
                        isUnApprovedAndUnCharged = true;
                }
                else if (feeType.equals("unCharge"))
                {
                        isOnlyUnApproved = false;
                        isOnlyUnCharged = true;
                        isUnApprovedAndUnCharged = false;
                }

                LogUtil.info("course",
                        "[DelStudentAction]-----isOnlyUnApproved=" + isOnlyUnApproved);
                LogUtil.info("course",
                        "[DelStudentAction]-----isOnlyUnCharged=" + isOnlyUnCharged);
                LogUtil.info("course",
                        "[DelStudentAction]-----isUnApprovedAndUnCharged=" +
                                isUnApprovedAndUnCharged);

                ColStudentForm csf_original = null;

                //sign this colSign has approved
                ColSignHelper.approvedColSign(colSignID, "1");

                // sign this colStudent has approved
                ColStudentForm csf = new ColStudentForm();
                csf.setColSignDetailID(colSignDetailID);
                csf.setApproved(approved);
                csf.setType(SecurityConstants.DEFAULT_USERID);

                int relationID_int;
                LogUtil.info("course",
                        "[addStudentAction]-----colSignDetailID=" + colSignDetailID);
                LogUtil.info("course", "[addStudentAction]-----type=" + type);

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

                        if (isOnlyUnApproved)
                        {
                                csf.setApproved("0");
                                csf.setFeeState(csf_original.getFeeState());
                                ColStudentHelper.updateColStudent(csf);
                                //delete course from user
                                CourseUserHelper.deleteCourseUser(relationID_int, courseID, type);
                        }
                        else if (isUnApprovedAndUnCharged)
                        {
                                csf.setApproved("0");
                                csf.setFeeState("0");
                                ColStudentHelper.updateColStudent(csf);
                                //delete course from user
                                CourseUserHelper.deleteCourseUser(relationID_int, courseID, type);
                        }
                        else if (isOnlyUnCharged)
                        {
                                csf.setApproved(csf_original.getApproved());
                                csf.setFeeState("0");
                                ColStudentHelper.updateColStudent(csf);
                        }
                }

                LogUtil.info("course",
                        "[addStudentAction]-----resultScreen=" + resultScreen);

                return mapping.findForward(resultScreen);
        }
}
