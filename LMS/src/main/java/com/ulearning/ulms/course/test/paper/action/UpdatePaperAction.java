/**
 * UpdatePaperAction.java.
 * User: huangsb  Date: 2004-6-15
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd. * All rights reserved.
 */
package com.ulearning.ulms.course.test.paper.action;

import com.ulearning.ulms.core.util.DateTimeUtil;
import com.ulearning.ulms.core.util.StringUtil;
import com.ulearning.ulms.course.dao.CourseUserDAOImpl;
import com.ulearning.ulms.course.helper.CourseUserHelper;
import com.ulearning.ulms.course.model.CourseUserModel;
import com.ulearning.ulms.course.test.Exambatch.dao.ExambatchDAO;
import com.ulearning.ulms.course.test.Exambatch.dao.ExambatchDAOFactory;
import com.ulearning.ulms.course.test.paper.bean.CreatPaperFile;
import com.ulearning.ulms.course.test.paper.dao.PaperDAO;
import com.ulearning.ulms.course.test.paper.dao.PaperDAOFactory;
import com.ulearning.ulms.course.test.paper.dao.PaperDAOImpl;
import com.ulearning.ulms.course.test.paper.form.PaperForm;
import com.ulearning.ulms.util.log.LogUtil;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class UpdatePaperAction extends Action
{
        public ActionForward execute(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response)
                throws Exception
        {
                PaperForm pf = (PaperForm) form;
                String strInstruction = request.getParameter("Instruction");
                LogUtil.info("yangds", "********************************");
                LogUtil.info("yangds", "********************************");
                LogUtil.info("yangds", "********************************");
                LogUtil.info("yangds", "Instruction=" + strInstruction);
                pf.setInstruction(strInstruction);

                CourseUserDAOImpl courseUserDAOImpl = new CourseUserDAOImpl();
                List courseUserList = courseUserDAOImpl.getCourseAllUsers(pf.getCourseID(),
                        3).getCourseUsers();

                if (pf.getIsAvailable() == 1)
                {
                        for (int i = 0; i < courseUserList.size(); i++)
                        {
                                CourseUserModel cum = (CourseUserModel) courseUserList.get(i);
                                int userID = cum.getUserID();
                                CourseUserHelper.informhomework(userID, pf.getCourseID(), 6);
                        }
                }

                String resultScreen = "success";

                //yangds add: start
                String type = request.getParameter("submitType");

                if ((type != null) && type.equals("1"))
                {
                        resultScreen = "nextPage";
                }

                //yangds add: end for portal/survey/updateresearch.jsp file
                // PaperForm pf=(PaperForm)request.getAttribute("paperForm");
                int isA = Integer.parseInt(request.getParameter("isAvailable"));

                LogUtil.info("yangds",
                        "[updPaperAction]================beginTime=" + pf.getBeginTime());
                LogUtil.info("yangds",
                        "[updPaperAction]================lastTime=" + pf.getLastTime());

                if (!pf.getBeginTime().equals(""))
                {
                        String[] tmp = StringUtil.splitString(pf.getBeginTime(), "-");
                        pf.setStartTime(DateTimeUtil.toDate(tmp[1], tmp[2], tmp[0], tmp[3],
                                tmp[4], "0"));

                        //pf.setStartTime(DateTimeUtil.parseDateTime(pf.getBeginTime()));
                }

                if (!pf.getLastTime().equals(""))
                {
                        String[] tmp = StringUtil.splitString(pf.getLastTime(), "-");
                        pf.setEndTime(DateTimeUtil.toDate(tmp[1], tmp[2], tmp[0], tmp[3],
                                tmp[4], "0"));

                        //pf.setEndTime(DateTimeUtil.parseDateTime(pf.getLastTime()));
                }

                LogUtil.info("yangds",
                        "[updPaperAction]===========startTime = " +
                                DateTimeUtil.format(pf.getStartTime(), "yyyy-MM-dd hh:mm"));
                LogUtil.info("yangds",
                        "[updPaperAction]===========endTime = " +
                                DateTimeUtil.format(pf.getEndTime(), "yyyy-MM-dd hh:mm"));
                pf.setIsAvailable(isA);

                PaperDAO dao = PaperDAOFactory.getDAO();
                dao.updatePaper(pf);

                ServletContext sc = request.getSession().getServletContext();

                if (sc.getAttribute(pf.getPaperID() + "_exam") != null)
                {
                        sc.removeAttribute(pf.getPaperID() + "_exam");
                }

                if (pf.getDesc2().indexOf("C") != -1)
                { //对于没有考场的考试做异常捕获

                        try
                        {
                                PaperDAOImpl pstr = new PaperDAOImpl();
                                ExambatchDAO dao1 = ExambatchDAOFactory.getDAO();
                                //更改试卷时间范围
                                pstr.updatePaper(pf.getPaperID(),
                                        dao1.getdatePaperTime(pf.getPaperID(), true),
                                        dao1.getdatePaperTime(pf.getPaperID(), false));
                        }
                        catch (Exception e)
                        {
                        }
                }

                LogUtil.info("system",
                        "[updPaperAction]===========resultScreen = " + resultScreen);

                CreatPaperFile creatPaperFile = new CreatPaperFile();

                if (creatPaperFile.makeFile(pf.getPaperID(),
                        request.getSession().getServletContext().getRealPath(""),
                        pf.getCourseID()))
                {
                        // Forward to result page
                        return mapping.findForward(resultScreen);
                }
                else
                {
                        return mapping.findForward(resultScreen);
                }

                // Forward to result page
                //return mapping.findForward(resultScreen);
        }
}
