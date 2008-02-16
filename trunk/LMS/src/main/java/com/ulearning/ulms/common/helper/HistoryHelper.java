/**
 * HistoryHelper.java.
 * User: fengch  Date: 2004-9-21
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.common.helper;

import com.ulearning.ulms.admin.certificate.bean.CertHelper;
import com.ulearning.ulms.admin.certificate.form.CertForm;
import com.ulearning.ulms.common.dao.HistoryProfileDAO;
import com.ulearning.ulms.common.dao.HistoryProfileDAOFactory;
import com.ulearning.ulms.common.exceptions.HistoryProfileSysException;
import com.ulearning.ulms.common.model.HistoryProfileListModel;
import com.ulearning.ulms.common.model.HistoryProfileModel;
import com.ulearning.ulms.core.exceptions.ULMSSysException;
import com.ulearning.ulms.core.security.bean.SecurityConstants;
import com.ulearning.ulms.course.helper.CourseHelper;
import com.ulearning.ulms.course.helper.CourseUserHelper;
import com.ulearning.ulms.course.model.CourseModel;
import com.ulearning.ulms.course.model.CourseUserModel;
import com.ulearning.ulms.user.bean.UserHelper;
import com.ulearning.ulms.user.form.UserForm;
import com.ulearning.ulms.util.log.LogUtil;

import java.util.Date;


public class HistoryHelper
{
        private static HistoryProfileDAO historyProfileDAO;

        static
        {
                try
                {
                        historyProfileDAO = HistoryProfileDAOFactory.getDAO();
                }
                catch (Exception ex)
                {
                        ex.printStackTrace();
                }
        }

        /**
         * get  the user-relationed profile
         * type  :see SecurityConstants.
         *
         * @throws com.ulearning.ulms.common.exceptions.HistoryProfileSysException
         *
         */
        public static HistoryProfileListModel get(int userID, int type,
                                                  java.util.Date startDate, java.util.Date endDate)
                throws HistoryProfileSysException
        {
                return historyProfileDAO.get(userID, type, startDate, endDate);
        }

        public static HistoryProfileListModel getByShowConditon(int userID,
                                                                int type, String startDate, String endDate)
                throws HistoryProfileSysException
        {
                return historyProfileDAO.getByShowConditon(userID, type, startDate,
                        endDate);
        }

        /**
         * get  the user-relationed profile
         * type
         * reference SecurityConstants.
         *
         * @throws com.ulearning.ulms.common.exceptions.HistoryProfileSysException
         *
         */
        public static HistoryProfileModel get(int userID, int relationID, int type)
                throws HistoryProfileSysException
        {
                return historyProfileDAO.get(userID, relationID, type);
        }

        /**
         * update  a profile.
         *
         * @throws com.ulearning.ulms.common.exceptions.HistoryProfileSysException
         *
         */
        public static void update(int userID, int relationID, int type,
                                  String score, int state, int scoreType, float period, float credit,
                                  int isPass) throws ULMSSysException
        {
                CourseUserModel cum = null;
                UserForm uf = UserHelper.getUser(String.valueOf(userID));

                //get Date
                cum = CourseUserHelper.getCourseUser(relationID, type, userID);

                Date joinTime = cum.getJoinTime();
                Date finishedTime = cum.getFinishedTime();

                //get Name
                String name = null;

                if (type == SecurityConstants.USER_COURSE_RELATION)
                {
                        LogUtil.info("common",
                                "[HistoryHelper]===========type==SecurityConstants.USER_COURSE_RELATION");

                        CourseModel cm = CourseHelper.getCourse(relationID);
                        name = cm.getName();
                }
                else if (type == SecurityConstants.USER_CERTIFICATE_RELATION)
                {
                        LogUtil.info("common",
                                "[ScoreManageAction]===========type==USER_CERTIFICATE_RELATION");

                        CertForm cert = CertHelper.getCert(relationID);
                        name = cert.getName();
                }

                HistoryProfileModel hpm = new HistoryProfileModel(userID, relationID,
                        type, state, score, scoreType, period, credit, isPass,
                        uf.getLoginName(), uf.getName(), name, 0, "", joinTime,
                        finishedTime, 0, "", "", "", new Date());
                historyProfileDAO.update(hpm);
        }

        /**
         * update  a profile' state.
         * 若不存在，不会insert.
         */
        public static void update(int userID, int relationID, int type, int state)
                throws HistoryProfileSysException
        {
                historyProfileDAO.update(userID, relationID, type, state);
        }

        /**
         * update  a profile' state,enrollmentDate,completionDate.
         *
         * @throws com.ulearning.ulms.common.exceptions.HistoryProfileSysException
         *
         */
        public static void update(int userID, int relationID, int type, int state,
                                  Date enrollmentDate, Date completionDate)
                throws HistoryProfileSysException
        {
                System.out.println("[CourseUserHelper]confirmApply=========== userID：" +
                        userID);
                System.out.println(
                        "[CourseUserHelper]confirmApply=========== relationID：" +
                                relationID);
                System.out.println("[CourseUserHelper]confirmApply=========== type：" +
                        type);
                System.out.println("[CourseUserHelper]confirmApply=========== state：" +
                        state);

                try
                {
                        HistoryProfileModel historyProfileModel = get(userID, relationID,
                                type);

                        if (historyProfileModel != null)
                        {
                                System.out.println("[HistoryHelper]update 更新学习档案状态");
                                historyProfileDAO.update(userID, relationID, type, state,
                                        enrollmentDate, completionDate);
                        }
                        else
                        {
                                System.out.println("[HistoryHelper]update 学习档案不能存在,insert学习档案");

                                CourseUserModel cum = null;
                                UserForm uf = UserHelper.getUser(String.valueOf(userID));

                                //get Date
                                cum = CourseUserHelper.getCourseUser(relationID, type, userID);

                                Date joinTime = enrollmentDate;
                                Date finishedTime = completionDate;

                                //get Name
                                String name = null;

                                if (type == SecurityConstants.USER_COURSE_RELATION)
                                {
                                        LogUtil.info("common",
                                                "[HistoryHelper]===========type==SecurityConstants.USER_COURSE_RELATION");

                                        CourseModel cm = CourseHelper.getCourse(relationID);
                                        name = cm.getName();
                                }
                                else if (type == SecurityConstants.USER_CERTIFICATE_RELATION)
                                {
                                        LogUtil.info("common",
                                                "[ScoreManageAction]===========type==USER_CERTIFICATE_RELATION");

                                        CertForm cert = CertHelper.getCert(relationID);
                                        name = cert.getName();
                                }

                                String score = "";
                                int scoreType = 0;
                                float period = 0;
                                float credit = 0;
                                int isPass = 0;
                                HistoryProfileModel hpm = new HistoryProfileModel(userID,
                                        relationID, type, state, score, scoreType, period,
                                        credit, isPass, uf.getLoginName(), uf.getName(), name,
                                        0, "", joinTime, finishedTime, 0, "", "", "", new Date());
                                insert(hpm);
                        }
                }
                catch (Exception ex)
                {
                        ex.printStackTrace();
                }
        }

        /**
         * insert  a profile.
         *
         * @throws com.ulearning.ulms.common.exceptions.HistoryProfileSysException
         *
         */
        public static void insert(HistoryProfileModel hpm)
                throws HistoryProfileSysException
        {
                historyProfileDAO.insert(hpm);
        }

        /**
         * 返回特定asp在特定日期（按月份）的培训人次
         *
         * @param aspID
         * @param date
         * @return
         * @throws HistoryProfileSysException
         */
        public static int getHistoryPersonalTimeByMonthReport(int aspID, Date date)
                throws HistoryProfileSysException
        {
                return historyProfileDAO.getHistoryPersonalTimeReport(aspID, date);
        }

        /**
         * 返回特定asp在特定日期的总培训人次
         *
         * @param aspID
         * @param startDate
         * @param endDate
         * @return
         * @throws HistoryProfileSysException
         */
        public static int getAllHistoryPersonalTimeReport(int aspID,
                                                          Date startDate, Date endDate) throws HistoryProfileSysException
        {
                return historyProfileDAO.getHistoryPersonalTimeReport(aspID, startDate,
                        endDate);
        }

        /**
         * 返回用户的总学分.<br>
         *
         * @param userID
         * @param startDate
         * @param endDate
         * @return
         */
        public static float getUserTotalPeriod(int userID, Date startDate,
                                               Date endDate) throws HistoryProfileSysException
        {
                return historyProfileDAO.getUserTotalPeriod(userID, startDate, endDate);
        }

        /**
         * 返回用户通过课程的总学时.
         *
         * @param userID
         * @param startDate
         * @param endDate
         * @return
         * @throws HistoryProfileSysException
         */
        public static float getUserTotalPassPeriod(int userID, Date startDate,
                                                   Date endDate) throws HistoryProfileSysException
        {
                return historyProfileDAO.getUserTotalPassPeriod(userID, startDate,
                        endDate);
        }

        //根据课程删除学习记录  add by huangsb
        public static void delete(int relationID) throws HistoryProfileSysException
        {
                historyProfileDAO.delete(relationID);
        }
}
