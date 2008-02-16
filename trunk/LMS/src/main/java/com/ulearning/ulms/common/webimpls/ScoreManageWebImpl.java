/**
 * ScoreManageWebImpl.java.
 * User: fengch  Date: 2004-5-27
 *
 * Copyright (c) 2000-2004.Huaxia Dadi Distance Learning Services Co.,Ltd.
 * All rights reserved.
 */
package com.ulearning.ulms.common.webimpls;

import com.ulearning.ulms.common.dao.CodeMaintainDAO;
import com.ulearning.ulms.common.dao.CodeMaintainDAOFactory;
import com.ulearning.ulms.common.dao.ScoreManageDAO;
import com.ulearning.ulms.common.dao.ScoreManageDAOFactory;
import com.ulearning.ulms.common.exceptions.ScoreSysException;
import com.ulearning.ulms.common.model.ScoreModel;
import com.ulearning.ulms.core.exceptions.ULMSSysException;
import com.ulearning.ulms.util.log.LogUtil;

import java.io.Serializable;

import java.util.List;


public class ScoreManageWebImpl implements Serializable
{
        private ScoreManageDAO scoreManageDAO;
        private CodeMaintainDAO codeMaintainDAO;

        public ScoreManageWebImpl()
        {
                try
                {
                        scoreManageDAO = ScoreManageDAOFactory.getDAO();
                        codeMaintainDAO = CodeMaintainDAOFactory.getDAO();
                        LogUtil.debug("codeMaintainDAO",
                                "[codeMaintainDAO] ==========Stringprint = " + codeMaintainDAO);
                }
                catch (Exception ex)
                {
                        ex.printStackTrace();
                }
        }

        public ScoreModel getScore(int scoreID) throws ScoreSysException
        {
                return scoreManageDAO.get(scoreID);
        }

        /**
         * get  the user-relationed score
         * type
         * ==0:�γ̳ɼ�
         * ==1:֤��ɼ�
         * ==2:��Ŀ�ɼ�
         *
         * @throws com.ulearning.ulms.common.exceptions.ScoreSysException
         *
         */
        public ScoreModel get(int userID, int relationID, int type)
                throws ScoreSysException
        {
                return scoreManageDAO.get(userID, relationID, type);
        }

        /**
         * �ж���ѵ��������ͨ���γ̵�ѧ��
         *
         * @param userID   �û�
         * @param courseID ��ѵ���µĿγ�
         * @return
         * @throws ScoreSysException
         */
        public List getPassCourse(int userID, List courseID)
                throws ScoreSysException
        {
                return scoreManageDAO.getPassCourse(userID, courseID);
        }

        /**
         * ��ѵ���µĿγ̳ɼ�
         *
         * @param relationID
         * @param isPass
         * @param type
         * @return
         * @throws ScoreSysException
         */
        public List getCourseScore(int relationID, int isPass, int type)
                throws ScoreSysException
        {
                return scoreManageDAO.getCourseScore(relationID, isPass, type);
        }

        /*
        * get the course'score type
        */
        public String getScoreType(int scoreType) throws ULMSSysException
        {
                return codeMaintainDAO.getScoreType(scoreType);
        }

        /*
        * get the list of score type
        */
        public List getScoreTypes() throws ULMSSysException
        {
                return codeMaintainDAO.getScoreTypes();
        }

        /*
        * get the value of the SpecScore
        */
        public String getSpecScore(int specScore, int scoreType)
                throws ULMSSysException
        {
                return codeMaintainDAO.getSpecScore(specScore, scoreType);
        }

        /*
        * get the list of the scoreType
        */
        public List getSpecScores(int scoreType) throws ULMSSysException
        {
                return codeMaintainDAO.getSpecScores(scoreType);
        }

        /*
        * get the TeachMode
        */
        public String getTeachMode(int teachModeID) throws ULMSSysException
        {
                return codeMaintainDAO.getTeachMode(teachModeID);
        }

        /*
        * get the TeachMode list.
        */
        public List getTeachModes() throws ULMSSysException
        {
                return codeMaintainDAO.getTeachModes();
        }

        /*
        * get the CourseType
        */
        public String getCourseType(int courseTypeID) throws ULMSSysException
        {
                return codeMaintainDAO.getCourseType(courseTypeID);
        }

        /*
        * get the CourseType list
        */
        public List getCourseTypes() throws ULMSSysException
        {
                return codeMaintainDAO.getCourseTypes();
        }
}
