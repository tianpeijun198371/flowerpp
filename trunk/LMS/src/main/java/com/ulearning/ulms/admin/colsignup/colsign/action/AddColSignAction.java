/**
 * Created by IntelliJ IDEA.
 * User: zengwj
 * Date: 2004-8-23
 * Time: 14:59:47
 * To change this template use File | Settings | File Templates.
 */
package com.ulearning.ulms.admin.colsignup.colsign.action;

import com.ulearning.ulms.admin.colsignup.colsign.dao.ColSignDAO;
import com.ulearning.ulms.admin.colsignup.colsign.dao.ColSignDAOFactory;
import com.ulearning.ulms.admin.colsignup.colsign.form.ColSignForm;
import com.ulearning.ulms.admin.colsignup.colsigndetail.bean.ColSignDetailHelper;
import com.ulearning.ulms.admin.colsignup.colsigndetail.form.ColSignDetailForm;
import com.ulearning.ulms.admin.colsignup.colstudent.bean.ColStudentHelper;
import com.ulearning.ulms.admin.colsignup.colstudent.exceptions.ColStudentAppException;
import com.ulearning.ulms.admin.colsignup.colstudent.form.ColStudentForm;
import com.ulearning.ulms.core.security.bean.SecurityConstants;
import com.ulearning.ulms.core.util.StringUtil;
import com.ulearning.ulms.course.helper.CourseUserHelper;
import com.ulearning.ulms.util.LMSConstants;
import com.ulearning.ulms.util.log.LogUtil;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class AddColSignAction extends Action
{
        public ActionForward execute(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response)
                throws Exception
        {
                HttpSession session = request.getSession();
                String resultScreen = "success";
                ColSignForm csf = (ColSignForm) form;
                LogUtil.info("course", "[AddColSignAction]-----start");

                String orgID_str = "0";
                int orgID = 0;

                if (session.getAttribute(LMSConstants.USER_ORGID_KEY) != null)
                {
                        orgID_str = (String) session.getAttribute(LMSConstants.USER_ORGID_KEY);
                        orgID = StringUtil.parseInt(orgID_str);
                }

                LogUtil.info("course", "[AddColSignAction]-----orgID=" + orgID);

                String userID_str = (String) session.getAttribute(LMSConstants.USERID_KEY);
                int userID = StringUtil.parseInt(userID_str);
                csf.setOrgID(orgID);
                csf.setCreator(userID);
                LogUtil.info("course", "[AddColSignAction]-----userID=" + userID);
                LogUtil.info("course",
                        "[AddColSignAction]-----request.getPathInfo()=" +
                                request.getPathInfo());

                if (request.getParameter("isFromMySpace") != null)
                {
                        //from myspace:个人报名
                        int relationID = StringUtil.parseInt(request.getParameter(
                                "relationID"));
                        int typeID = StringUtil.parseInt(request.getParameter("typeID"));
                        List colStudentList = ColStudentHelper.getColStudentList(userID,
                                relationID, typeID);
                        LogUtil.info("course",
                                "[AddColSignAction]-----colStudentList=" + colStudentList);

                        /*if (colStudentList != null && !colStudentList.isEmpty())
                       {
                               LogUtil.info("course", "[AddColSignAction]-----colStudentList=" + colStudentList.size());
                               throw new ColStudentAppException("对不起，您已经申请过了，请不要重复申请！");
                       } */
                        if (CourseUserHelper.isExisitCourseUserRole(userID,
                                SecurityConstants.ROLE_COURSR_STUDENT, relationID,
                                typeID))
                        {
                                throw new ColStudentAppException("对不起，您已经在本课程中了，请不要申请了！");
                        }

                        LogUtil.info("course", "[AddColSignAction]-----个人报名");

                        String personal_signUp_name = request.getParameter(
                                "personal_signUp_name");
                        LogUtil.info("course",
                                "[AddColSignAction]-----personal_signUp_name=" +
                                        personal_signUp_name);
                        csf.setName(personal_signUp_name + "-" +
                                session.getAttribute(LMSConstants.LOGINNAME_KEY));

                        int ColSignID = 0;
                        csf.setApproved("0");
                        csf.setIsSubmit("1");

                        ColSignDAO dao = ColSignDAOFactory.getDAO();
                        ColSignID = dao.addColSign(csf);

                        ColSignDetailForm csdf = new ColSignDetailForm(0, ColSignID,
                                relationID, typeID, null);
                        int colSignDetailID = ColSignDetailHelper.addColSignDetail(csdf);

                        ColStudentForm cstf = new ColStudentForm();
                        cstf.setColSignDetailID(colSignDetailID);
                        cstf.setRelationID(userID);
                        cstf.setApproved("0");
                        cstf.setFeeState("0");
                        cstf.setType(SecurityConstants.USER_DEFAULT_RELATION);

                        LogUtil.info("course", "[AddColSignAction]-----addUser start");
                        ColStudentHelper.addUser(cstf);
                        LogUtil.info("course", "[AddColSignAction]-----addUser end");
                        resultScreen = "personalSignUp_sucess";
                }
                else
                {
                        //from myspace:集体报名
                        LogUtil.info("course", "[AddColSignAction]-----集体报名");

                        String type = request.getParameter("type");
                        LogUtil.info("course", "[AddColSignAction]-----type=" + type);

                        int ColSignID = 0;
                        csf.setApproved("0");
                        csf.setIsSubmit("0");

                        ColSignDAO dao = ColSignDAOFactory.getDAO();
                        ColSignID = dao.addColSign(csf);

                        if (type.equals("1"))
                        {
                                resultScreen = "success_ok";
                        }

                        if (ColSignID != 0)
                        {
                                request.setAttribute("colSignID", String.valueOf(ColSignID));
                        }
                }

                LogUtil.info("course",
                        "[AddColSignAction]-----resultScreen=" + resultScreen);

                return mapping.findForward(resultScreen);
        }
}
