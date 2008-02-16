/**
 * MatchWebImpl.java.
 * User: fengch  Date: 2004-8-18
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.common.match.webimpl;

import com.ulearning.ulms.common.match.dao.MatchDAO;
import com.ulearning.ulms.common.match.dao.MatchDAOFactory;
import com.ulearning.ulms.common.match.exceptions.MatchSysException;
import com.ulearning.ulms.common.match.model.JobModel;
import com.ulearning.ulms.common.match.model.MatchModel;

import java.io.Serializable;

import java.util.List;


public class MatchWebImpl implements Serializable
{
        private MatchDAO matchDAO;

        public MatchWebImpl()
        {
                try
                {
                        matchDAO = MatchDAOFactory.getDAO();
                }
                catch (Exception ex)
                {
                        ex.printStackTrace();
                }
        }

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
        public MatchModel get(int matchID) throws MatchSysException
        {
                return matchDAO.get(matchID);
        }

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
        public List get(int type, int relationID) throws MatchSysException
        {
                return matchDAO.get(type, relationID);
        }

        /**
         * get  all of the relational match list
         *
         * @throws com.ulearning.ulms.common.match.exceptions.MatchSysException
         *
         */
        public List getAllMatches() throws MatchSysException
        {
                return matchDAO.getAllMatches();
        }

        /*
        * get relational Users.
        */
        public List getMatchUsers(JobModel jm) throws MatchSysException
        {
                return matchDAO.getMatchUsers(jm);
        }
}
