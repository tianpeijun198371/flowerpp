/**
 * ScoreHelper.java.
 * User: fengch  Date: 2004-5-28
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.common.helper;

import com.ulearning.ulms.common.dao.CodeMaintainDAO;
import com.ulearning.ulms.common.dao.CodeMaintainDAOFactory;
import com.ulearning.ulms.common.dao.ScoreManageDAO;
import com.ulearning.ulms.common.dao.ScoreManageDAOFactory;
import com.ulearning.ulms.common.exceptions.ScoreSysException;
import com.ulearning.ulms.common.model.ScoreModel;
import com.ulearning.ulms.core.util.Config;
import com.ulearning.ulms.course.dao.CourseDAO;
import com.ulearning.ulms.course.dao.CourseDAOFactory;
import com.ulearning.ulms.course.exceptions.CourseSysException;
import com.ulearning.ulms.util.log.LogUtil;

import java.text.DecimalFormat;


public class ScoreHelper
{
        private static ScoreManageDAO scoreManageDAO;
        private static CourseDAO courseDAO;
        private static CodeMaintainDAO codeMaintainDAO;

        static
        {
                try
                {
                        scoreManageDAO = ScoreManageDAOFactory.getDAO();
                        codeMaintainDAO = CodeMaintainDAOFactory.getDAO();
                        courseDAO = CourseDAOFactory.getDAO();
                }
                catch (Exception ex)
                {
                        ex.printStackTrace();
                }
        }

        /**
         * delete a score
         *
         * @throws ScoreSysException
         */
        public static void delete(int userID, int relationID, int type)
                throws ScoreSysException
        {
                scoreManageDAO.delete(userID, relationID, type);
        }

        /**
         * delete a score by userID.
         *
         * @throws ScoreSysException
         */
        public static void deleteByUserID(int userID, int relationID, int type)
                throws ScoreSysException
        {
                scoreManageDAO.deleteByUserID(userID);
        }

        /**
         * @param course
         * @param userID
         * @param type   1:¿¼ÊÔ 2£º×÷Òµ
         * @return
         * @throws com.ulearning.ulms.course.exceptions.CourseSysException
         *
         */
        public static float getCourseScore(int course, int userID, int type)
                throws CourseSysException
        {
                float ff = 0;

                try
                {
                        //add jiefo CourseUser
                        if (Config.getIsIntegrateJieFo())
                        {
                                ff = courseDAO.getJieFoChenji(course, userID, type);
                        }
                }
                catch (Exception ex)
                {
                }

                return ff;
        }

        public static ScoreModel getCourseScoreFromScore(int relationID,
                                                         int userID, int type) throws CourseSysException
        {
                ScoreModel sm = null;

                try
                {
                        sm = scoreManageDAO.get(userID, relationID, type);
                }
                catch (Exception ex)
                {
                        ex.printStackTrace();
                        throw new CourseSysException(ex);
                }

                return sm;
        }

        /**
         * return the pass radio
         * <p>if all course user's number is 0,then reurn -1
         *
         * @throws com.ulearning.ulms.common.exceptions.ScoreSysException
         *
         */
        public static String getPassRadio(int relationID, int type)
                throws ScoreSysException
        {
                float ratio = 0;

                ratio = scoreManageDAO.getPassRadio(relationID, type);

                DecimalFormat nf = new DecimalFormat("#,##0.00%");
                String ratioStirng = null;

                if (ratio == -1)
                {
                        ratioStirng = nf.format((float) 0);
                }
                else
                {
                        ratioStirng = nf.format(ratio);
                }

                return ratioStirng;
        }

        /**
         * update  a score.
         *
         * @throws com.ulearning.ulms.common.exceptions.ScoreSysException
         *
         */
        public static void update(ScoreModel sm) throws ScoreSysException
        {
                int scoreID = isExisit(sm.getUserID(), sm.getRelationID(), sm.getType());
                LogUtil.debug("common",
                        "[ScoreHelper]update ==========exisit scoreID = " + scoreID);

                if (scoreID != -1)
                {
                        LogUtil.debug("common", "[ScoreHelper]update ==========update");
                        sm.setScoreID(scoreID);
                        scoreManageDAO.update(sm);
                }
                else
                {
                        LogUtil.debug("common",
                                "[ScoreHelper]update ==========not this score!");
                }
        }

        /**
         * insert  a score.
         *
         * @throws com.ulearning.ulms.common.exceptions.ScoreSysException
         *
         */
        public static void insert(ScoreModel sm) throws ScoreSysException
        {
                int scoreID = isExisit(sm.getUserID(), sm.getRelationID(), sm.getType());
                LogUtil.debug("common",
                        "[ScoreHelper]insert ==========exisit scoreID = " + scoreID);

                if (scoreID != -1)
                {
                        LogUtil.debug("common", "[ScoreHelper]insert ==========update");
                        sm.setScoreID(scoreID);
                        scoreManageDAO.update(sm);
                }
                else
                {
                        LogUtil.debug("common", "[ScoreHelper]insert ==========insert");
                        scoreManageDAO.insert(sm);
                }
        }

        /**
         * if  Exisit  a score,return  scoreID.,else return -1;
         *
         * @param userID
         * @param relationID
         * @param type
         * @return scoreID
         * @throws ScoreSysException
         */
        public static int isExisit(int userID, int relationID, int type)
                throws ScoreSysException
        {
                ScoreModel scoreModel = scoreManageDAO.get(userID, relationID, type);

                if (scoreModel != null)
                {
                        return scoreModel.getScoreID();
                }
                else
                {
                        return -1;
                }
        }
}
