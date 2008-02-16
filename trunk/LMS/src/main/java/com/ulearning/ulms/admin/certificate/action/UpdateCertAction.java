/**
 * UpdateCertAction.java.
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
import com.ulearning.ulms.core.util.DateTimeUtil;
import com.ulearning.ulms.course.model.CourseModel;
import com.ulearning.ulms.course.util.TreeFlush;
import com.ulearning.ulms.util.LMSConstants;
import com.ulearning.ulms.util.log.LogUtil;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import org.apache.webapp.admin.TreeControl;
import org.apache.webapp.admin.TreeControlNode;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class UpdateCertAction extends Action
{
        public ActionForward execute(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response)
                throws Exception
        {
                String resultScreen = "success";
                CertForm cf = (CertForm) form;

                //Check whether the cert code is duplicate
                int aspID = Integer.parseInt((String) request.getSession()
                        .getAttribute(LMSConstants.USER_ASPID_KEY));
                int orgID = Integer.parseInt((String) request.getSession()
                        .getAttribute(LMSConstants.USER_ORGID_KEY));

                int certID = CertHelper.getCertID(cf.getCode(), orgID);

                if ((certID > 0) && (certID != cf.getCertificateID()))
                {
                        request.setAttribute(LMSConstants.ERROR_PAGE_INFO, "编号不能重复！");

                        return mapping.findForward(LMSConstants.ERROR_FORWARD);
                }

                Date startDate;
                Date endDate;
                String reStartDate = request.getParameter("regStartDateValue");
                System.out.println(
                        "reStartDate ========================================" +
                                reStartDate);

                if ((reStartDate != null) && !reStartDate.equals(""))
                {
                        startDate = DateTimeUtil.parseDate(reStartDate);
                        cf.setRegTimeBegin(startDate); /////////////////////
                }

                String reEndDate = request.getParameter("regEndDateValue");
                System.out.println("reEndDate ========================================" +
                        reEndDate);

                if ((reStartDate != null) && !reStartDate.equals(""))
                {
                        ;
                }

                endDate = DateTimeUtil.parseDate(reEndDate);
                cf.setRegTimeEnd((endDate)); //.///////

                CertDAO dao = CertDAOFactory.getDAO();
                cf.setAspID(aspID);
                cf.setOrgID(orgID);
                dao.updateCert(cf);

                CertCourseForm ccf = new CertCourseForm();
                int certificateID = cf.getCertificateID();
                double credit = 0;
                double period = 0;
                int courseType = 0;

                String[] addCourseID = request.getParameterValues("courseID");

                List courseList = CertHelper.getCourseListFromCert(String.valueOf(
                        certificateID), LMSConstants.LEARNING_TUTORIAL);
                CourseModel courseModel = null;
                ccf.setCertificateID(certificateID);

                //delete  删除修改时去掉的课程
                boolean delete = true;

                for (int k = 0; k < courseList.size(); k++)
                {
                        courseModel = (CourseModel) courseList.get(k);

                        for (int i = 0; (addCourseID != null) && (i < addCourseID.length);
                             i++)
                        {
                                if (Integer.parseInt(addCourseID[i]) == courseModel.getCourseID())
                                {
                                        delete = false;

                                        break;
                                }
                        }

                        if (delete)
                        {
                                ccf.setCertificateID(certificateID);
                                ccf.setCourseID(courseModel.getCourseID());
                                ccf.setType(1);
                                dao.removeCourseFromCert(ccf);

                                //System.out.println("courseType===============OK"+courseType);
                        }

                        delete = true;
                }

                //add，修改时有新选的课程
                boolean add = true;

                for (int k = 0; k < addCourseID.length; k++)
                {
                        //removeCourseID[k]=String.valueOf(courseModel.getCourseID());
                        for (int i = 0; (courseList != null) && (i < courseList.size());
                             i++)
                        {
                                courseModel = (CourseModel) courseList.get(i);

                                if (Integer.parseInt(addCourseID[k]) == courseModel.getCourseID())
                                {
                                        add = false;

                                        break;
                                }
                        }

                        if (add)
                        {
                                LogUtil.debug("system",
                                        "[courseID]=======the sql string is : addCourseID=" +
                                                addCourseID[k]);

                                courseType = Integer.parseInt(request.getParameter("courseType" +
                                        addCourseID[k]));

                                if ((request.getParameter("credit" + addCourseID[k]) != null) &&
                                        !request.getParameter("credit" + addCourseID[k])
                                                .equals(""))
                                {
                                        credit = Double.parseDouble(request.getParameter("credit" +
                                                addCourseID[k]));
                                }

                                if ((request.getParameter("period" + addCourseID[k]) != null) &&
                                        !request.getParameter("period" + addCourseID[k])
                                                .equals(""))
                                {
                                        period = Double.parseDouble(request.getParameter("period" +
                                                addCourseID[k]));
                                }

                                ccf.setCourseType(courseType);
                                ccf.setCredit(credit);
                                ccf.setPeriod(period);
                                ccf.setCourseID(Integer.parseInt(addCourseID[k]));
                                ccf.setType(LMSConstants.LEARNING_TUTORIAL);
                                dao.addCourseToCert(ccf);
                        }

                        add = true;
                }

                //System.out.println("list.size()===============" + list.size());
                /*for (int n = 0; removeCourseID != null && n < removeCourseID.length; n++)
                  {
                          LogUtil.debug("system", "[courseID]========the sql string is : "
                                  + removeCourseID[n]);
                          ccf.setCertificateID(certificateID);
                          ccf.setCourseID(Integer.parseInt(removeCourseID[n]));
                          dao.removeCourseFromCert(ccf);
                          System.out.println("delete==================OK");
                  }
                */

                /*
               String[] CourseID = new String[list.size()];
               for (int i = 0; i < list.size(); i++)
               {
                       CourseID[i] = (String) list.get(i);
               }
               for (int n = 0; CourseID != null && n < CourseID.length; n++)
               {
                       LogUtil.debug("system", "[courseID]=======the sql string is : addCourseID="
                               + CourseID[n]);
                       courseType = Integer.parseInt(request.getParameter("courseType" + CourseID[n]));
                       if (request.getParameter("credit" + CourseID[n]) != null && !request.getParameter("credit" + CourseID[n]).equals(""))
                       {
                               credit = Double.parseDouble(request.getParameter("credit" + CourseID[n]));
                       }
                       if (request.getParameter("period" + CourseID[n]) != null && !request.getParameter("period" + CourseID[n]).equals(""))
                       {
                               period = Double.parseDouble(request.getParameter("period" + CourseID[n]));
                       }
                       ccf.setCourseType(courseType);
                       ccf.setCredit(credit);
                       ccf.setPeriod(period);
                       ccf.setCourseID(Integer.parseInt(CourseID[n]));
                       ccf.setType(LMSConstants.LEARNING_TUTORIAL);
                       dao.addCourseToCert(ccf);
                        ccf.setCourseType(courseType);
                        ccf.setCredit(credit);
                        ccf.setPeriod(period);
                        //ccf.setCertificateID(certificateID);
                        ccf.setCourseID(Integer.parseInt(addCourseID[n]));
                        ccf.setType(LMSConstants.LEARNING_TUTORIAL);
                        dao.updateCertCourse(ccf);
               } */
                LogUtil.info("system",
                        "[UpdateCertAction]===========resultScreen = " + resultScreen);

                HttpSession session = request.getSession();
                ServletContext servletContext = getServlet().getServletContext();

                String treeType = LMSConstants.TREE_PUBLISHED_CERT;
                TreeControl control = (TreeControl) session.getAttribute(treeType);

                if (control != null)
                {
                        TreeControlNode currentNode = control.findNode("certificateIDs/" +
                                certificateID);
                        String certName = cf.getName();

                        if (Integer.parseInt(cf.getCertType()) == SecurityConstants.USER_OFFLINE_CERTIFICATE_RELATION)
                        {
                                certName += "  [脱产]";
                        }
                        else if (Integer.parseInt(cf.getCertType()) == SecurityConstants.USER_CERTIFICATE_RELATION)
                        {
                                certName += "  [远程]";
                        }

                        if (currentNode != null)
                        {
                                currentNode.setLabel(certName);
                        }
                }

                //更新树
                TreeFlush.flush(servletContext, session, orgID, treeType);
                request.setAttribute("catalogID", String.valueOf(cf.getCatalogID()));

                // Forward to result page
                return mapping.findForward(resultScreen);
        }
}
