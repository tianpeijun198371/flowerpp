/**
 * ScoreManageAction.java.
 * User: fengch  Date: 2004-5-27
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.course.action;

import com.ulearning.ulms.common.helper.HistoryHelper;
import com.ulearning.ulms.common.helper.ScoreHelper;
import com.ulearning.ulms.common.model.HistoryProfileModel;
import com.ulearning.ulms.common.model.ScoreModel;
import com.ulearning.ulms.core.security.bean.SecurityConstants;
import com.ulearning.ulms.core.util.StringUtil;
import com.ulearning.ulms.course.helper.CourseHelper;
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


public class ScoreManageAction extends Action
{
        public ActionForward execute(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response)
                throws Exception
        {
                String resultScreen = "success";
                ScoreModel sm = null;

                String[] userIDs;
                int scoreID;
                int userID;
                int relationID;
                int type = StringUtil.parseInt((String) request.getParameter("type"));

                String score = null;
                int scoreType = 0;
                float credit = 0;
                float period = 0;
                int isPass = 0;
                int examType = 0;
                String scoreID_str = null;

                userIDs = request.getParameterValues("userIDs");

                String relationID_str = (String) request.getParameter("relationID");
                relationID = Integer.parseInt(relationID_str);
                scoreType = StringUtil.parseInt((String) request.getParameter(
                        "scoreType"));
                period = StringUtil.parseFloat((String) request.getParameter("period"));

                if (userIDs != null)
                {
                        for (int i = 0; i < userIDs.length; i++)
                        {
                                userID = Integer.parseInt(userIDs[i]);
                                scoreID_str = (String) request.getParameter("scoreID" + userID);
                                scoreID = Integer.parseInt(scoreID_str);

                                score = (String) request.getParameter("score" + userID);
                                LogUtil.info("common",
                                        "[ScoreManageAction]===========userID=" + userID);
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
                                        CourseUserHelper.updateCourseUser(userID, relationID,
                                                SecurityConstants.USER_COURSE_RELATION, new Date(),
                                                CourseKeys.COURSE_USER_FINISH_STATE);
                                }

                                LogUtil.info("common",
                                        "[ScoreManageAction]===========更新结束日期完成～～～～");

                                String credittmp = (String) request.getParameter("credit" +
                                        userID);

                                if ((credittmp != null) && !credittmp.equals(""))
                                {
                                        credit = Float.parseFloat(credittmp);
                                }

                                scoreID_str = (String) request.getParameter("isPass" + userID);
                                LogUtil.info("common",
                                        "[ScoreManageAction]===========是否通过更新分数...isPass=" +
                                                scoreID_str);

                                isPass = Integer.parseInt(scoreID_str);

                                int state = CourseKeys.COURSE_USER_FINISH_STATE;

                                Date now = new Date();
                                sm = new ScoreModel(scoreID, userID, relationID, type, score,
                                        scoreType, credit, isPass, examType, now, now);
                                ScoreHelper.insert(sm);
                                HistoryHelper.update(userID, relationID, type, score, state,
                                        scoreType, period, credit, isPass);
                        }
                }

                LogUtil.info("common",
                        "[ScoreManageAction]===========resultScreen = " + resultScreen);

                // Forward to result page
                return mapping.findForward(resultScreen);
        }
}
