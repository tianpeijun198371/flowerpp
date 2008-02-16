/**
 * MatchHelper.java.
 * User: fengch  Date: 2004-7-23
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.common.match.helper;

import com.ulearning.ulms.common.match.dao.MatchDAO;
import com.ulearning.ulms.common.match.dao.MatchDAOFactory;
import com.ulearning.ulms.common.match.exceptions.MatchSysException;
import com.ulearning.ulms.common.match.job.MatchJob;
import com.ulearning.ulms.common.match.model.JobModel;
import com.ulearning.ulms.common.match.model.MatchModel;
import com.ulearning.ulms.common.match.util.MatchConstants;
import com.ulearning.ulms.course.dao.CourseUserDAO;
import com.ulearning.ulms.course.dao.CourseUserDAOFactory;
import com.ulearning.ulms.user.dao.UserDAO;
import com.ulearning.ulms.user.dao.UserDAOFactory;
import com.ulearning.ulms.util.log.LogUtil;

import java.util.ArrayList;
import java.util.List;


public class MatchHelper
{
        private static MatchDAO matchDAO;
        private static CourseUserDAO courseUserDAO;
        private static UserDAO userDAO;

        static
        {
                try
                {
                        matchDAO = MatchDAOFactory.getDAO();
                        courseUserDAO = CourseUserDAOFactory.getDAO();
                        userDAO = UserDAOFactory.getDAO();
                }
                catch (Exception ex)
                {
                        ex.printStackTrace();
                }
        }

        /*
        * client's method'
        */
        public static void update(MatchModel mm) throws MatchSysException
        {
                if (matchDAO.isExisit(mm.getMatchID(), mm.getRelationID(), mm.getType()))
                {
                        LogUtil.info("common", "[MatchHelper] update--match已存在！需要更新");
                        matchDAO.update(mm);
                }
                else
                {
                        matchDAO.insert(mm);
                }
        }

        /**
         * delete a match
         *
         * @throws MatchSysException
         */
        public static void delete(int matchID, int relationID, int type)
                throws MatchSysException
        {
                matchDAO.delete(matchID, relationID, type);
        }

        /**
         * delete all match
         *
         * @throws MatchSysException
         */
        public static void deleteAll(int relationID, int type)
                throws MatchSysException
        {
                matchDAO.deleteAll(relationID, type);
        }

        /*
        *  match all the job
        */
        public static void match()
        {
                List list = null;

                try
                {
                        list = matchDAO.getAllJobs();

                        MatchJob mj = new MatchJob();
                        mj.execute(list);
                }
                catch (Exception ex)
                {
                        ex.printStackTrace();
                }
        }

        /*
        * match  the jobs according to the specified relationID,type
        */
        public static void match(int relationID, int type)
        {
                List list = null;

                try
                {
                        list = matchDAO.getAllJobs(relationID, type);

                        MatchJob mj = new MatchJob();
                        mj.execute(list);
                }
                catch (Exception ex)
                {
                        ex.printStackTrace();
                }
        }

        /*
        * get relational Users.
        */
        public static List getMatchUsers(JobModel mm)
        {
                List l = new ArrayList();

                try
                {
                        l = matchDAO.getMatchUsers(mm);
                }
                catch (Exception ex)
                {
                        ex.printStackTrace();
                }

                return l;
        }
}
