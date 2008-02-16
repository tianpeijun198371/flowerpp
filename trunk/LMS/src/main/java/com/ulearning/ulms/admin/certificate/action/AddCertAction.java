/**
 * AddCertAction.java.
 * User: huangsb  Date: 2004-4-27
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd. * All rights reserved.
 */
package com.ulearning.ulms.admin.certificate.action;

import com.ulearning.ulms.admin.certificate.bean.CertHelper;
import com.ulearning.ulms.admin.certificate.dao.CertDAO;
import com.ulearning.ulms.admin.certificate.dao.CertDAOFactory;
import com.ulearning.ulms.admin.certificate.form.CertCourseForm;
import com.ulearning.ulms.admin.certificate.form.CertForm;
import com.ulearning.ulms.core.security.bean.SecurityConstants;
import com.ulearning.ulms.core.security.bean.SecurityHelper;
import com.ulearning.ulms.core.util.Config;
import com.ulearning.ulms.core.util.DateTimeUtil;
import com.ulearning.ulms.core.crypto.MD5Encrypt;
import com.ulearning.ulms.course.helper.CourseHelper;
import com.ulearning.ulms.course.helper.CourseUserHelper;
import com.ulearning.ulms.course.util.CourseKeys;
import com.ulearning.ulms.course.util.TreeFlush;
import com.ulearning.ulms.util.LMSConstants;
import com.ulearning.ulms.util.HardWareUtil;
import com.ulearning.ulms.util.log.LogUtil;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import org.apache.webapp.admin.TreeControl;
import org.apache.webapp.admin.TreeControlNode;

import java.util.Date;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class AddCertAction extends Action
{
        public ActionForward execute(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response)
                throws Exception
        {
                String resultScreen = "success";
                int certificateID = 0;

                CertForm cf = (CertForm) form;
                System.out.println(
                        "***********************************   cf.getRegMethed() = " +
                                cf.getRegMethed());

                CertCourseForm ccf = new CertCourseForm();

                CertDAO dao = CertDAOFactory.getDAO();

                int aspID = Integer.parseInt((String) request.getSession()
                        .getAttribute(LMSConstants.USER_ASPID_KEY));

                int orgID = Integer.parseInt((String) request.getSession()
                        .getAttribute(LMSConstants.USER_ORGID_KEY));
                //Check whether the cert code is duplicate
                int certID = CertHelper.getCertID(cf.getCode(), orgID);
                if(Config.isGuoZiWeiProject())
                { 
                    ServletContext sc = getServlet().getServletContext();
                    Object certObject = sc.getAttribute(LMSConstants.CERTIFICATE_KEY);
                    if (certObject == null)
                    {
                        String cert_key = Config.get(LMSConstants.CERTIFICATE_KEY);
                        String nwID = HardWareUtil.getMacAddress();

                        if (!MD5Encrypt.compareEncrypted(cert_key, nwID))
                        {
                            sc.setAttribute(LMSConstants.CERTIFICATE_KEY, "0");
                            request.setAttribute(LMSConstants.ERROR_PAGE_INFO, LMSConstants.CERTERROR_INFO);
                            return mapping.findForward(LMSConstants.ERROR_LOGIN_FORWARD);
                        } else
                        {
                            sc.setAttribute(LMSConstants.CERTIFICATE_KEY, "1");
                        }
                    } else
                    {
                        if (certObject.toString().trim().equalsIgnoreCase("0"))
                        {
                            request.setAttribute(LMSConstants.ERROR_PAGE_INFO, LMSConstants.CERTERROR_INFO);
                            return mapping.findForward(LMSConstants.ERROR_LOGIN_FORWARD);
                        }
                    }
               }
            
                if (certID > 0)
                {
                        request.setAttribute(LMSConstants.ERROR_PAGE_INFO, "编号不能重复！");

                        return mapping.findForward(LMSConstants.ERROR_FORWARD);
                }

                cf.setAspID(aspID);
                cf.setOrgID(orgID);

                //and certificate
                Date startDate = null;
                Date endDate = null;
                String reStartDate = request.getParameter("regStartDateValue");

                if ((reStartDate != null) && !reStartDate.equals(""))
                {
                        startDate = DateTimeUtil.parseDate(reStartDate);
                        cf.setRegTimeBegin((startDate)); /////////////////////
                }

                String reEndDate = request.getParameter("regEndDateValue");

                if ((reStartDate != null) && !reStartDate.equals(""))
                {
                        ;
                }

                endDate = DateTimeUtil.parseDate(reEndDate);
                cf.setRegTimeEnd(endDate); //.///////
                certificateID = dao.addCert(cf);

                boolean validate = CourseHelper.IsCourseAvailable(certificateID,
                        SecurityConstants.USER_CERTIFICATE_RELATION);

                HttpSession session = request.getSession();
                ServletContext servletContext = getServlet().getServletContext();
                int userID = 1;

                if (session.getAttribute(LMSConstants.USERID_KEY) != null)
                {
                        userID = Integer.parseInt((String) session.getAttribute(
                                LMSConstants.USERID_KEY));
                }
                        //add creator as course teacher  xln项目需要发布课程是暂时不加角色
                        //如果是教师发布的培训班则加到课程里
                        if(SecurityHelper.getUserRoleForPortal(certificateID,aspID,1)==SecurityConstants.ROLE_COURSE_TEACHER)
                        {
                            CourseUserHelper.addCourseUser(certificateID,
                                    SecurityConstants.USER_CERTIFICATE_RELATION, userID,
                                    CourseKeys.COURSE_USER_AVAILABLE_STATE,
                                    SecurityConstants.ROLE_COURSE_TEACHER);
                        }
                // and course of certificate
                if (certificateID != 0)
                {
                        String[] courseID = request.getParameterValues("courseID");

                        if (courseID != null)
                        {
                                double credit = 0;
                                double period = 0;
                                int courseType = 0;
                                ccf.setCertificateID(certificateID);

                                for (int n = 0; n < courseID.length; n++)
                                {
                                        courseType = Integer.parseInt(request.getParameter(
                                                "courseType" + courseID[n]));

                                        if ((request.getParameter("credit" + courseID[n]) != null) &&
                                                !request.getParameter("credit" + courseID[n])
                                                        .equals(""))
                                        {
                                                credit = Double.parseDouble(request.getParameter(
                                                        "credit" + courseID[n]));
                                        }

                                        if ((request.getParameter("period" + courseID[n]) != null) &&
                                                !request.getParameter("period" + courseID[n])
                                                        .equals(""))
                                        {
                                                period = Double.parseDouble(request.getParameter(
                                                        "period" + courseID[n]));
                                        }

                                        ccf.setCourseType(courseType);
                                        ccf.setCredit(credit);
                                        ccf.setPeriod(period);
                                        ccf.setCourseID(Integer.parseInt(courseID[n]));
                                        ccf.setType(LMSConstants.LEARNING_TUTORIAL);
                                        dao.addCourseToCert(ccf);
                                }
                        }
                }

                LogUtil.info("admin",
                        "[AddCertAction]===========resultScreen = " + resultScreen);

                //add a certificate node to tree
                int catalogID = cf.getCatalogID();

                TreeControl control = null;
                String treeType = LMSConstants.TREE_PUBLISHED_CERT;
                TreeControl selectControl = null;

                control = (TreeControl) session.getAttribute(treeType);

                /*        if(servletContext.getAttribute(LMSConstants.TREE_PUBLISH+aspID) != null)
                servletContext.removeAttribute(LMSConstants.TREE_PUBLISH+aspID);
                selectControl = (TreeControl) session.getAttribute(LMSConstants.TREE_SELECT);
                if(servletContext.getAttribute(LMSConstants.TREE_SELECT+aspID) != null)
                servletContext.removeAttribute(LMSConstants.TREE_SELECT+aspID);
                if(servletContext.getAttribute(LMSConstants.TREE_STUDENT_SIGNUP+aspID) != null)
                servletContext.removeAttribute(LMSConstants.TREE_STUDENT_SIGNUP+aspID);
                if(session.getAttribute(LMSConstants.TREE_STUDENT_SIGNUP) != null)
                session.removeAttribute(LMSConstants.TREE_STUDENT_SIGNUP);*/
                String nodeName = "catalogIDs/" + catalogID;

                if (catalogID == 0)
                {
                        nodeName = "ROOT-NODE";
                }

                if (control != null)
                {
                        TreeControlNode parentNode = control.findNode(nodeName);
                        parentNode.setExpanded(true);
                        control.selectNode(nodeName);

                        if (parentNode != null)
                        {
                                String certName = cf.getName();

                                if (Integer.parseInt(cf.getCertType()) == SecurityConstants.USER_OFFLINE_CERTIFICATE_RELATION)
                                {
                                        certName += "  [脱产]";
                                }
                                else
                                if (Integer.parseInt(cf.getCertType()) == SecurityConstants.USER_CERTIFICATE_RELATION)
                                {
                                        certName += "  [远程]";
                                }

                                TreeControlNode subtree = new TreeControlNode("certificateIDs/" +
                                        certificateID,
                                         Config.getContextRoot() + "/images/cert.gif",
                                        certName,
                                         Config.getContextRoot() +
                                                "/admin/course/course/certinfo.jsp?isNeedApply=1&certificateID=" +
                                                certificateID, null, false, "Publish");
                                parentNode.addChild(subtree);
                        }
                }



                //更新树
                TreeFlush.flush(servletContext, session, orgID, treeType);

                String actionStr = request.getParameter("actionStr");

                if (actionStr != null)
                {
                        if (actionStr.equals("successandselectcert"))
                        {
                                request.setAttribute("relationID", String.valueOf(certificateID));
                                request.setAttribute("type",
                                        String.valueOf(SecurityConstants.USER_CERTIFICATE_RELATION));
                                resultScreen = "successandselectcourse";
                        }
                }

                request.setAttribute("catalogID", String.valueOf(catalogID));

                // Forward to result page
                return mapping.findForward(resultScreen);
        }
}
