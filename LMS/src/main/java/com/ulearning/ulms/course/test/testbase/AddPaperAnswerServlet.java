package com.ulearning.ulms.course.test.testbase;

import com.ulearning.ulms.core.util.Config;
import com.ulearning.ulms.core.util.DateTimeUtil;
import com.ulearning.ulms.core.util.StringUtil;
import com.ulearning.ulms.course.test.grade.form.PaperUserForm;
import com.ulearning.ulms.course.test.paper.bean.PaperAnswerHelper;
import com.ulearning.ulms.course.test.paper.bean.PaperHelper;
import com.ulearning.ulms.course.test.paper.dao.PaperAnswerDAO;
import com.ulearning.ulms.course.test.paper.dao.PaperAnswerDAOFactory;
import com.ulearning.ulms.course.test.paper.exceptions.PaperDAOSysException;
import com.ulearning.ulms.course.test.paper.form.PaperAnswerForm;
import com.ulearning.ulms.course.test.paper.form.PaperForm;
import com.ulearning.ulms.course.test.question.base.bean.baseConstants;
import com.ulearning.ulms.user.bean.UserHelper;
import com.ulearning.ulms.util.LMSConstants;
import com.ulearning.ulms.util.log.LogUtil;

import java.io.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import javax.servlet.*;
import javax.servlet.http.*;


public class AddPaperAnswerServlet extends HttpServlet
{
        ServletContext context = null;

        public void init(ServletConfig servletConfig) throws ServletException
        {
                context = servletConfig.getServletContext();
                super.init(servletConfig);
        }

        protected void doGet(HttpServletRequest request,
                             HttpServletResponse response) throws ServletException, IOException
        {
                doPost(request, response);
        }

        protected void doPost(HttpServletRequest request,
                              HttpServletResponse response) throws ServletException, IOException
        {
                //                context = getServletContext();
                //                context = request.getSession().getServletContext();
                //                context = getServletContext();
                HashMap paperAnswerHM = null;
                HashMap paperUserHM = null;
                int dupID = 0;
                PaperUserForm puf = null;
                PaperAnswerForm paf = null;
                ArrayList al = null;
                PaperAnswerDAO dao = null;
                PaperHelper ph = new PaperHelper();
                PaperForm pf = null;

                try
                {
                        dao = PaperAnswerDAOFactory.getDAO();
                }
                catch (PaperDAOSysException e)
                {
                        e.printStackTrace();
                }

                String userID = request.getParameter("userID");
                String paperID = request.getParameter("paperID");
                String[] type = request.getParameterValues("questionType");
                String[] questionID = request.getParameterValues("questionID");
                String papertype = request.getParameter("papertype");
                LogUtil.info("yangds", "userID=" + userID + ":paperID=" + paperID);

                String beginTime = request.getParameter("beginTime");

                //得到考试次数
                PaperAnswerHelper paperhelper = new PaperAnswerHelper();

                // System.out.println(userID);
                int timesint = paperhelper.getPaperAnswerTimes(Integer.parseInt(userID),
                        Integer.parseInt(paperID));

                if (paperID == null)
                {
                        paperID = "0";
                }

                if (userID == null)
                {
                        userID = "0";
                }

                try
                {
                        dupID = dao.getPaperAnswer(Integer.parseInt(userID),
                                Integer.parseInt(paperID));
                }
                catch (PaperDAOSysException e)
                {
                        e.printStackTrace();
                }

                if (false)
                {
                        if ((papertype != null) &&
                                papertype.equals(PaperBaseConstants.PAPER_EXAMINATION_TYPE))
                        {
                                request.setAttribute(LMSConstants.ERROR_PAGE_INFO,
                                        "您参加此次考试的次数已超过允许的最大考试次数，请选择其他考试！");
                                request.setAttribute("examed",
                                        PaperBaseConstants.PAPER_EXAMINATION_TYPE);
                                //return mapping.findForward(resultScreen);
                                response.sendRedirect(request.getContextPath() +
                                        "/mystudy/course/test/paper/alertexam.jsp?papertype=" +
                                        papertype + "&examed=" +
                                        PaperBaseConstants.PAPER_EXAMINATION_TYPE);
                        }
                        else
                        {
                                request.setAttribute(LMSConstants.ERROR_PAGE_INFO,
                                        "您已经参加过此次考试了，请选择其他考试！");
                                response.sendRedirect(request.getContextPath() +
                                        "/mystudy/course/test/paper/alertexam.jsp?papertype=" +
                                        papertype);

                                //return mapping.findForward(LMSConstants.ERROR_FORWARD);
                        }
                }
                else
                {
                        paperAnswerHM = new HashMap();
                        al = new ArrayList();

                        for (int i = 0; i < questionID.length; i++)
                        {
                                String answerStr = "";
                                String[] answer = request.getParameterValues("answer_" +
                                        questionID[i]);

                                for (int j = 0; (answer != null) && (j < answer.length); j++)
                                {
                                        if ((type[i] != null) &&
                                                type[i].equals(baseConstants.QUESTION_FILL_TYPE))
                                        {
                                                if (!answer[j].equals(""))
                                                {
                                                        answerStr = answerStr + "%" +
                                                                StringUtil.replaceString(answer[j], "%",
                                                                        "&#037;");
                                                }

                                                if (!answerStr.equals(""))
                                                {
                                                        answerStr = answerStr.substring(1);
                                                }
                                        }
                                        else
                                        {
                                                answerStr += answer[j];
                                        }

                                        LogUtil.info("system",
                                                "[Answer AnswerDAOAction]====================the sql string is : " +
                                                        answer[j]);
                                }

                                paf = new PaperAnswerForm();

                                if (userID != null)
                                {
                                        paf.setUserID(Integer.parseInt(userID));
                                }
                                else
                                {
                                        paf.setUserID(0);
                                }

                                if (timesint != 0)
                                {
                                        paf.setExamtimes(timesint + 1);
                                }
                                else
                                {
                                        paf.setExamtimes(0);
                                }

                                if (paperID != null)
                                {
                                        paf.setPaperID(Integer.parseInt(paperID));
                                }
                                else
                                {
                                        paf.setPaperID(0);
                                }

                                if (questionID[i] != null)
                                {
                                        paf.setQuestionID(Integer.parseInt(questionID[i]));
                                }
                                else
                                {
                                        paf.setQuestionID(0);
                                }

                                paf.setAnswer(answerStr);

                                if (type[i] != null)
                                {
                                        paf.setType(Integer.parseInt(type[i]));
                                }
                                else
                                {
                                        paf.setType(0);
                                }

                                //把所有提交的试题存入paperAnswerHM 哈希表中
                                paperAnswerHM.put(questionID[i], paf);
                        }

                        //先把试题项对象压入数组
                        al.add(paperAnswerHM);
                        puf = new PaperUserForm();
                        puf.setPaperID(Integer.parseInt(paperID));
                        puf.setUserID(Integer.parseInt(userID));

                        if ((papertype != null) && !papertype.equals(""))
                        {
                                puf.setType(StringUtil.parseInt(papertype));
                        }
                        else
                        {
                                puf.setType(1);
                        }

                        pf = ph.getPaper(Integer.parseInt(paperID));
                        puf.setStartTime(pf.getStartTime());
                        puf.setEndTime(new Date());
                        //desc4提交时间
                        //                        puf.setDesc4(DateTimeUtil.format(new Date(),"yyyy-MM-dd HH:mm:ss"));
                        //再把试卷用户表对象压入
                        al.add(puf);

                        //把学生提交的答卷缓存
                        if (context.getAttribute(paperID) == null)
                        {
                                paperUserHM = new HashMap();
                        }
                        else
                        {
                                paperUserHM = (HashMap) context.getAttribute(paperID);
                        }

                        paperUserHM.put(userID, al);
                        context.setAttribute(paperID, paperUserHM);

                        //把学生提交的答卷写入xml文件
                        try
                        {
                                LogUtil.info("system",
                                        "[Answer AnswerDAOAction]====================size1=" +
                                                al.size());

                                if (PaperXML.writeXMLFile(Config.getUploadPhysicalPath() +
                                        "PaperXML" + File.separator + paperID +
                                        File.separator + userID + File.separator +
                                        DateTimeUtil.format(new Date(), "yyyyMMddHHmm") +
                                        "_stuAnswer.xml", al))
                                {
                                }
                                else
                                {
                                        papertype = "4";
                                }
                        }
                        catch (Exception e)
                        {
                                e.printStackTrace();
                        }
                }

                response.sendRedirect(request.getContextPath() +
                        "/mystudy/course/test/paper/alertexam.jsp?papertype=" + papertype);
        }
}
