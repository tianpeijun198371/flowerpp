/**
 * Copyright (c) 2000-2005.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.cert.Certificate.action;

import com.ulearning.ulms.GCertificate.dao.GCertificateDAO;
import com.ulearning.ulms.GCertificate.dao.GCertificateDAOFactory;
import com.ulearning.ulms.GCertificate.form.GCertificateForm;
import com.ulearning.ulms.common.helper.HistoryHelper;
import com.ulearning.ulms.common.helper.ScoreHelper;
import com.ulearning.ulms.common.model.ScoreModel;
import com.ulearning.ulms.core.security.bean.SecurityConstants;
import com.ulearning.ulms.core.util.StringUtil;
import com.ulearning.ulms.course.helper.CourseUserHelper;
import com.ulearning.ulms.course.util.CourseKeys;
import com.ulearning.ulms.util.log.LogUtil;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Class description goes here.
 * <p/>
 * Created by Auto Code Produce System
 * User: xiejh
 * Date: 20060109
 * Time: 101040
 */
public class AddGradeAction extends Action
{
        public ActionForward execute(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response)
                throws Exception
        {
                String resultScreen = "success";

                ScoreModel sm = null;

                String[] username = request.getParameterValues("username");
                String[] userIDs = request.getParameterValues("userIDs");
                String courseName = request.getParameter("courseName");
                String[] lastgrade = request.getParameterValues("lastgrade");
                String courseid = request.getParameter("courseid");
                String period_str = request.getParameter("period");

                String isPass_str = null; //ok
                String scoreID_str = null;
                String score = null;
                int scoreID;
                int scoreType = 0; //ok
                int isPass = 0; //ok
                float period = 0;
                float credit = 0;
                int examType = 0;

                int type = StringUtil.parseInt((String) request.getParameter("type")); //ok
                int relationID = StringUtil.parseInt((String) request.getParameter(
                        "relationID")); //ok
                scoreType = StringUtil.parseInt((String) request.getParameter(
                        "scoreType")); //ok
                period = StringUtil.parseFloat(period_str); //ok

                GCertificateForm tf = new GCertificateForm();
                GCertificateDAO dao = GCertificateDAOFactory.getDAO();

                for (int i = 0; i < userIDs.length; i++) //for (int i = 0; i < username.length; i++)
                {
                        tf = dao.getGCertificate(StringUtil.parseInt(userIDs[i]),
                                StringUtil.parseInt(courseid));

                        if (tf == null)
                        {
                                GCertificateForm tf2 = new GCertificateForm();
                                tf2.setGCGrade(lastgrade[i]);
                                tf2.setGCourseID(StringUtil.parseInt(courseid));
                                tf2.setGCourseName(courseName);
                                tf2.setGUserID(StringUtil.parseInt(userIDs[i]));
                                tf2.setGUserName(username[i]);
                                tf2.setGCTime(period_str);
                                dao.insertGCertificate(tf2);
                        }
                        else
                        {
                                tf.setGCGrade(lastgrade[i]);
                                tf.setGCourseID(StringUtil.parseInt(courseid));
                                tf.setGCourseName(courseName);
                                tf.setGUserID(StringUtil.parseInt(userIDs[i]));
                                tf.setGUserName(username[i]);
                                tf.setGCTime(period_str);
                                dao.updateGCertificate(tf);
                        }

                        //userID = Integer.parseInt(userIDs[i]);
                        scoreID_str = (String) request.getParameter("scoreID" + userIDs[i]);
                        scoreID = Integer.parseInt(scoreID_str);

                        score = (String) request.getParameter("score" + userIDs[i]);
                        LogUtil.info("common",
                                "[ScoreManageAction]===========userID=" + userIDs[i]);
                        LogUtil.info("common",
                                "[ScoreManageAction]===========score=" + score);

                        //没有给成绩
                        if ((score == null) || score.trim().equals("") ||
                                score.trim().equals("-1"))
                        {
                                LogUtil.info("common",
                                        "[ScoreManageAction]===========没有毕业审核该学员，继续！");

                                continue;
                        }

                        //若成绩还没有，表明这是第一次审核该学生成绩，则停止该学生学习该课程，更新其结束日期
                        if (scoreID == -1)
                        {
                                LogUtil.info("common",
                                        "[ScoreManageAction]===========成绩还没有，表明这是第一次审核该学生成绩，则停止该学生学习该课程，更新其结束日期");
                                CourseUserHelper.updateCourseUser(Integer.parseInt(userIDs[i]),
                                        relationID, SecurityConstants.USER_COURSE_RELATION,
                                        new Date(), CourseKeys.COURSE_USER_FINISH_STATE);
                        }

                        LogUtil.info("common", "[ScoreManageAction]===========更新结束日期完成～～～～");

                        String credittmp = (String) request.getParameter("credit" +
                                userIDs[i]);

                        if ((credittmp != null) && !credittmp.equals(""))
                        {
                                credit = Float.parseFloat(credittmp);
                        }

                        isPass_str = (String) request.getParameter("isPass" + userIDs[i]);
                        LogUtil.info("common",
                                "[ScoreManageAction]===========是否通过更新分数...isPass=" +
                                        scoreID_str);

                        isPass = Integer.parseInt(isPass_str);

                        int state = CourseKeys.COURSE_USER_FINISH_STATE;

                        Date now = new Date();
                        sm = new ScoreModel(scoreID, Integer.parseInt(userIDs[i]),
                                relationID, type, score, scoreType, credit, isPass,
                                examType, now, now);
                        ScoreHelper.insert(sm);
                        HistoryHelper.update(Integer.parseInt(userIDs[i]), relationID,
                                type, score, state, scoreType, period, credit, isPass);
                }

                return mapping.findForward(resultScreen);
        }
}
