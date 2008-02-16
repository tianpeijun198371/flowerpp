/**
 * MatchDAO.java.
 * User: fengch  Date: 2004-7-23
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.common.match.dao;

import com.ulearning.ulms.common.match.exceptions.MatchSysException;
import com.ulearning.ulms.common.match.model.JobModel;
import com.ulearning.ulms.common.match.model.MatchModel;

import java.util.List;


public interface MatchDAO
{
        /**
         * get  the match.
         * type
         * ==0:master
         * ==1:course
         * ==2:certificate
         * ==3:project
         *
         * @throws com.ulearning.ulms.common.match.exceptions.MatchSysException
         *
         */
        public MatchModel get(int matchID) throws MatchSysException;

        /**
         * get  the relational match list
         * type
         * ==0:master
         * ==1:course
         * ==2:certificate
         * ==3:project
         *
         * @throws com.ulearning.ulms.common.match.exceptions.MatchSysException
         *
         */
        public List get(int type, int relationID) throws MatchSysException;

        /**
         * get  all of the match list
         *
         * @throws com.ulearning.ulms.common.match.exceptions.MatchSysException
         *
         */
        public List getAllMatches() throws MatchSysException;

        /**
         * get  all of the relational job list
         *
         * @throws com.ulearning.ulms.common.match.exceptions.MatchSysException
         *
         */
        public List getAllJobs() throws MatchSysException;

        /**
         * get   the relational job list according to the specified relationID,type
         *
         * @param relationID
         * @param type
         * @return
         * @throws MatchSysException
         */
        public List getAllJobs(int relationID, int type) throws MatchSysException;

        /*
        * get relational Users.
        */
        public List getMatchUsers(JobModel jm) throws MatchSysException;

        /**
         * update  a match.
         *
         * @throws MatchSysException
         */
        public void update(MatchModel mm) throws MatchSysException;

        /**
         * insert a match.
         *
         * @throws MatchSysException
         */
        public void insert(MatchModel mm) throws MatchSysException;

        /**
         * delete a match
         *
         * @throws MatchSysException
         */
        public void delete(int matchID, int relationID, int type)
                throws MatchSysException;

        /**
         * delete all match
         *
         * @throws MatchSysException
         */
        public void deleteAll(int relationID, int type) throws MatchSysException;

        /**
         * judge if the match exisited.
         *
         * @throws MatchSysException
         */
        public boolean isExisit(int matchID, int relationID, int type)
                throws MatchSysException;
}
