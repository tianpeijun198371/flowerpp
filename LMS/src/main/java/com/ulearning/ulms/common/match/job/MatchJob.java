/**
 * MatchJob.java.
 * User: fengch  Date: 2004-7-23
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.common.match.job;

import com.ulearning.ulms.common.match.exceptions.MatchSysException;
import com.ulearning.ulms.common.match.helper.MatchHelper;
import com.ulearning.ulms.common.match.model.JobModel;
import com.ulearning.ulms.common.match.model.MatchModel;
import com.ulearning.ulms.common.match.util.MatchConstants;
import com.ulearning.ulms.core.security.bean.SecurityConstants;
import com.ulearning.ulms.course.helper.CourseHelper;
import com.ulearning.ulms.course.helper.CourseUserHelper;
import com.ulearning.ulms.course.util.CourseKeys;
import com.ulearning.ulms.util.log.LogUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * <p/>
 * A auto_match implementation of Job.
 * todo: later to implement sechedule.
 * </p>
 */
public class MatchJob
{
        public MatchJob()
        {
        }

        /**
         * <p/>
         * Called by the <code>{@link com.ulearning.ulms.common.match.helper.MatchHelper}</code> when a trigger occur</code>
         * fires that is associated with the <code>Job</code>.
         * </p>
         *
         * @throws com.ulearning.ulms.common.match.exceptions.MatchSysException
         *          if there is an exception while executing the job.
         */
        public void execute(List matchList) throws MatchSysException
        {
                JobModel jm = null;

                LogUtil.debug("common",
                        "[MatchJob]execute--jobList.size()=" + matchList.size());

                for (int i = 0; i < matchList.size(); i++)
                {
                        jm = (JobModel) matchList.get(i);
                        LogUtil.debug("common",
                                "[MatchJob]execute--job getRelationID=" + jm.getRelationID());
                        LogUtil.debug("common",
                                "[MatchJob]execute--job getType=" + jm.getType());
                        match(jm);
                }
        }

        private void match(JobModel jm)
        {
                try
                {
                        List l = MatchHelper.getMatchUsers(jm);
                        LogUtil.debug("common", "[MatchJob]match--job start");
                        LogUtil.debug("common", "[MatchJob]match--job =" + jm.getType());
                        LogUtil.debug("common",
                                "[MatchJob]match--job =" + jm.getRelationID());
                        LogUtil.debug("common",
                                "[MatchJob]match--job getMatchUsers size=" + l.size());

                        int userID;

                        for (int i = 0; i < l.size(); i++)
                        {
                                userID = ((Integer) l.get(i)).intValue();
                                LogUtil.debug("common", "[MatchJob]match------userID=" +
                                        userID);

                                try
                                {
                                        if (jm.getType() == SecurityConstants.USER_COURSE_RELATION)
                                        {
                                                LogUtil.debug("common",
                                                        "[MatchJob]match-- Type is USER_COURSE_RELATION");

                                                if (!CourseUserHelper.isCreator(jm.getRelationID(),
                                                        SecurityConstants.USER_COURSE_RELATION,
                                                        userID))
                                                {
                                                        LogUtil.debug("common",
                                                                "[MatchJob]match-- User is not Creator,go on add user!");
                                                        CourseUserHelper.addCourseUser(jm.getRelationID(),
                                                                jm.getType(), userID,
                                                                CourseKeys.COURSE_USER_AVAILABLE_STATE,
                                                                SecurityConstants.ROLE_COURSR_STUDENT);
                                                }
                                                else
                                                {
                                                        LogUtil.debug("common",
                                                                "[MatchJob]match-- User is Creator");
                                                }
                                        }
                                        else
                                        {
                                                LogUtil.debug("common",
                                                        "[MatchJob]match-- Type is not USER_COURSE_RELATION,go on add user!");
                                                CourseUserHelper.addCourseUser(jm.getRelationID(),
                                                        jm.getType(), userID,
                                                        CourseKeys.COURSE_USER_AVAILABLE_STATE,
                                                        SecurityConstants.ROLE_COURSR_STUDENT);
                                        }
                                }
                                catch (Exception ex)
                                {
                                        LogUtil.debug("common", "[MatchJob]match-- start");
                                        ex.printStackTrace();
                                        LogUtil.debug("common", "[MatchJob]match-- end");
                                }
                        }
                }
                catch (Exception ex)
                {
                        ex.printStackTrace();
                }
        }
}
